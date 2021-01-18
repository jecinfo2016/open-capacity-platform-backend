package com.udp.nb.handler;

import com.alibaba.fastjson.JSON;
import com.udp.nb.config.GhtClient;
import com.udp.nb.entity.*;
import com.udp.nb.entity.AlmResult;
import com.udp.nb.opentsdb.client.ExpectResponse;
import com.udp.nb.opentsdb.client.HttpClient;
import com.udp.nb.opentsdb.client.builder.MetricBuilder;
import com.udp.nb.opentsdb.client.response.Response;
import com.udp.nb.service.NettyService;
import com.udp.nb.util.*;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.udp.nb.mapper.AirSwitchMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cloudy
 * @version 1.0
 * @date 18/2/3 下午5:07
 */
@Component
public class AlarmHandler {

    private Logger logger = LoggerFactory.getLogger(AlarmHandler.class);


    @Autowired
    private AirSwitchMapper airSwitchMapper;
    @Autowired
    private GhtClient ghtClient;

    @Autowired
    @Qualifier(value = "openTSDB")
    HttpClient client;


    public void handlerBackMessageUdp(final String body, final ChannelHandlerContext ctx, final DatagramPacket packet, final String ip, final String port) {
        String message = MessageUtil.getBackMessage(body);
        byte[] bytes = CRC16Util.toBytes(message);
        String key = ip + ":" + port;
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender());
        logger.debug("接收到报文1,{}注册消息>>>:{},向客户端发送消息:{}", key, body, message);
        ctx.writeAndFlush(data);//向客户端发送消息

    }

    /**
     * 真正执行的方法
     * @param airSwitchData
     */
    public void addA0ToOpentsdb(AirSwitchData airSwitchData) {
        HashMap<String, Integer> map = new HashMap<>();
        long time = airSwitchData.getTime();//时间戳
        String cmd = airSwitchData.getCmd();//类型
        String pver = airSwitchData.getPver();//协议的版本号
        //String hostid = airSwitchData.getHostid();//电箱号
        String mac = airSwitchData.getMac();//Mac地址
        List<AirSwitchData.AirData> dataList = airSwitchData.getDataList();
        MetricBuilder builder = MetricBuilder.getInstance();
        for (AirSwitchData.AirData a : dataList) {
            Integer nno = a.getNno();//空开
            String type = a.getType();//80或84时为有效数据
            Integer vol = a.getVol();//电压值 单位伏特 v
            Integer pwr = a.getPwr(); //功率 w/h
            Integer tmp = a.getTmp(); //温度 0.1 摄氏度
            Integer cur = a.getCur();//电流值 10mA
            Integer cur2 = a.getCur2();// N相电流
            Integer alm = a.getAlm();// 告警
            Integer lki = a.getLki();//漏电流值
            Integer ps = Math.toIntExact(a.getPs());//累计电量
            map.put("vol", vol);
            map.put("pwr", pwr);
            map.put("tmp", tmp);
            map.put("cur", cur);
            map.put("cur2", cur2);
            map.put("alm", alm);
            map.put("lki", lki);
            map.put("ps", ps);
            if (type.equals("80")||type.equals("84")){
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    builder.addMetric("elec.record").setDataPoint(time, entry.getValue())
                            .addTag("index", entry.getKey()).addTag("pver", pver)
                            .addTag("cmd", cmd).addTag("host", mac).addTag("nno",nno.toString())
                            .addTag("type", "airswitch");
                }
            }
            try {
                Response response = client.pushMetrics(builder,
                        ExpectResponse.SUMMARY);
                logger.info(response.toString(), "数据写入opentsdb");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理接收到的udp数据
     * 1、漏电报警->4；2、漏电预警->14；3、温度报警->3；4、短路报警->0；5、打火报警->10；
     *
     * @param body
     * @return
     */
    public void handlerAOUdp(String body) {
        AirSwitchData airSwitchData = new AirSwitchData(body);
        addA0ToOpentsdb(airSwitchData);
        logger.info("来了数据-------handlerAOUdp-----" + JSON.toJSONString(airSwitchData));
        String hostid = MessageUtil.getHostID(body);
        Integer len = MessageUtil.getLen(body);
        Integer singleDataLenth = MessageUtil.SingleDataLength;
        Integer nodeCount = MessageUtil.getCMDVER(body);
        String time = MessageUtil.getTIME(body);
        String allData = MessageUtil.getDATA(body);
        String mac = MessageUtil.getMac(body);
        String deviceid = airSwitchMapper.getDeviceInfo(mac);

        //删除当前记录记录表
        airSwitchMapper.deleteAirSwitchCurrent(mac);
        airSwitchMapper.deleteDeviceStatusCurrent(mac);
        logger.debug("hostid:{},len:{},len1(去除time):{},deviceid:{},nodeCount:{}", hostid, len, allData.length(), deviceid, nodeCount);
        for (int i = 0; i < len / singleDataLenth && (len == allData.length() + 8); i++) {
            logger.debug("处理第{}条data数据", i + 1);
//            logger.debug(allData);
            String data = allData.substring(i * singleDataLenth, (i + 1) * singleDataLenth);
//            logger.debug(data.length()+"--->"+data);
            data = time + data;
//            logger.debug(data.length()+"--->"+data); 4,14,3,0,10
            String nno = MessageUtil.getNNO(data);
            int r1 = insertAir(data, 4, deviceid, hostid, nno, mac);
            int r2 = insertAir(data, 14, deviceid, hostid, nno, mac);
            int r3 = insertAir(data, 3, deviceid, hostid, nno, mac);
            int r4 = insertAir(data, 0, deviceid, hostid, nno, mac);
            int r5 = insertAir(data, 10, deviceid, hostid, nno, mac);
            //0无报警、1报警
            int alarmstatus = (r1 + r2 + r3 + r4 + r5) == 0 ? 0 : 1;
            if (alarmstatus == 1) {
                logger.info("!!!!!!!!ao:{}-->body:{}", mac, body);
            }
            insertDeviceStatus(data, deviceid, hostid, nno, alarmstatus, mac);
            insertTLineRecord(data, deviceid);
        }
    }

    /**
     * 真正执行的方法
     * @param airSwitchReal
     */
    public void addAcToOpentsdb(AirSwitchReal airSwitchReal) {
        HashMap<String, Integer> map = new HashMap<>();
        long time = airSwitchReal.getTime();//时间戳
        String cmd = airSwitchReal.getCmd();//类型
        String pver = airSwitchReal.getPver();//协议版本号
        //String hostid = airSwitchReal.getHostid();//电箱号
        String mac = airSwitchReal.getMac();//Mac地址
        List<AirSwitchReal.AirData> dataList = airSwitchReal.getDataList();
        for (AirSwitchReal.AirData a : dataList) {
            Integer nno = a.getNno();
            String type = a.getType();
            Integer vol = a.getVol();//电压值 单位伏特 v
            Integer pwr = a.getPwr(); //功率 w/h
            Integer tmp = a.getTmp(); //温度 0.1 摄氏度
            Integer cur = a.getCur();//电流值 10mA
            Integer cur2 = a.getCur2();// N相电流
            Integer alm = a.getAlm();// 告警
            Integer lki = a.getLki();//漏电流值
            Integer ps = Math.toIntExact(a.getPs());//累计电量
            map.put("vol", vol);
            map.put("pwr", pwr);
            map.put("tmp", tmp);
            map.put("cur", cur);
            map.put("cur2", cur2);
            map.put("alm", alm);
            map.put("lki", lki);
            map.put("ps", ps);
            MetricBuilder builder = MetricBuilder.getInstance();
            if (type.equals("80")||type.equals("84")) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    builder.addMetric("elec.record").setDataPoint(time, entry.getValue())
                            .addTag("index", entry.getKey()).addTag("pver", pver)
                            .addTag("cmd", cmd).addTag("host", mac).addTag("nno", nno.toString())
                            .addTag("type", "airswitch");
                }
                try {
                    Response response = client.pushMetrics(builder,
                            ExpectResponse.SUMMARY);
                    logger.info(response.toString(), "数据写入opentsdb");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void handlerACUdp(String body) {
        AirSwitchReal airSwitchReal = new AirSwitchReal(body);
        addAcToOpentsdb(airSwitchReal);
        logger.info("来了数据-------handlerACUdp-----" + JSON.toJSONString(airSwitchReal));
        String hostid = MessageUtil.getHostID(body);
        Integer len = MessageUtil.getLen(body);
        Integer singleDataLenth = MessageUtil.ACsingleDataLength;
        Integer nodeCount = MessageUtil.getCMDVER(body);
        String time = MessageUtil.getTIME(body);
        logger.info("time" + time);
        String allData = MessageUtil.getDATA(body);
        String mac = MessageUtil.getMac(body);
        String deviceid = airSwitchMapper.getDeviceInfo(mac);

        //删除当前记录记录表
        airSwitchMapper.deleteAirSwitchCurrent(mac);
        airSwitchMapper.deleteDeviceStatusCurrent(mac);
        logger.debug("hostid:{},len:{},len1(去除time):{},deviceid:{},nodeCount:{}..", hostid, len, allData.length(), deviceid, nodeCount);
        for (int i = 0; i < len / singleDataLenth && (len == allData.length() + 8); i++) {
//            logger.debug(allData);
            String data = allData.substring(i * singleDataLenth, (i + 1) * singleDataLenth);
            data = time + data;
            // logger.debug(data.length()+"--->"+data);
            String nno = MessageUtil.getNNO(data);
            int r1 = insertAirByAC(data, 4, deviceid, hostid, nno, mac);
            int r2 = insertAirByAC(data, 14, deviceid, hostid, nno, mac);
            int r3 = insertAirByAC(data, 3, deviceid, hostid, nno, mac);
            int r4 = insertAirByAC(data, 0, deviceid, hostid, nno, mac);
            int r5 = insertAirByAC(data, 10, deviceid, hostid, nno, mac);
            //0无报警、1报警
            int alarmstatus = (r1 + r2 + r3 + r4 + r5) == 0 ? 0 : 1;
            if (alarmstatus == 1) {
                logger.info("!!!!!!!!ac:{}-->body:{}", mac, body);
            }
            insertDeviceStatusByAC(data, deviceid, hostid, nno, alarmstatus, mac);
            //insertTLineRecord(data, deviceid);
        }
    }

    public void handlerA2Udp(String body) {
        String hostid = MessageUtil.getHostID(body);
        String mac = MessageUtil.getMac(body);
        Integer len = MessageUtil.getLen(body);
        Integer singleDataLenth = MessageUtil.A2singleDataLength;
        Integer nodeCount = MessageUtil.getA2NodeCount(body);
        String time = MessageUtil.getA2TIME(body);
        String allData = MessageUtil.getA2DATA(body);
        String deviceid = airSwitchMapper.getDeviceInfo(mac);

        logger.debug("hostid:{},len:{},len1(去除time):{},deviceid:{},nodeCount:{}.", hostid, len, allData.length(), deviceid, nodeCount);
        for (int i = 0; i < (len - 16) / singleDataLenth && (len == allData.length() + 16); i++) {
            logger.debug("处理第{}条data数据", i + 1);
//            logger.debug(allData);
            String data = allData.substring(i * singleDataLenth, (i + 1) * singleDataLenth);
//            logger.debug(data.length()+"--->"+data);
            String nno = MessageUtil.getA2NNO(data);
            TTotalRecordDO tTotalRecordDO = MessageUtil.initTTotalRecordDO(data);
            tTotalRecordDO.setHostid(Integer.parseInt(hostid, 16));
            tTotalRecordDO.setDeviceId(Integer.parseInt(deviceid));
            tTotalRecordDO.setMac(mac);
            airSwitchMapper.insertTTotalRecord(tTotalRecordDO);
        }
    }


    //处理PUSH_EVENT 消息，消息ID 为0xA4
    public void handlerA4Udp(String body) {
        logger.info("来了数据-------handlerA4Udp-----" + body);
        String hostid = MessageUtil.getHostID(body);
        String mac = MessageUtil.getMac(body);
        Integer len = MessageUtil.getLen(body);
        Integer singleDataLenth = MessageUtil.A4singleDataLength;
        Integer nodeCount = MessageUtil.getA4NodeCount(body);
        String allData = MessageUtil.getA4DATA(body);
        String deviceid = airSwitchMapper.getDeviceInfo(mac);
        airSwitchMapper.deleteAirSwitchCurrent(mac);
        for (int i = 0; i < (len) / singleDataLenth && (len == allData.length()); i++) {
            String data = allData.substring(i * singleDataLenth, (i + 1) * singleDataLenth);
            String No = data.substring(0, 4);
            String CODE = data.substring(4, 6);
            String EVT_ID = data.substring(6, 8);
            String EVT_LV = data.substring(8, 10);
            String EVT_RE = data.substring(10, 12);
            String nno = data.substring(12, 14);
            String EVT_SP = data.substring(14, 22);
            String TIME = data.substring(22, 30);
            String APX = data.substring(30, 56);
            logger.debug("EVT_LV:" + EVT_LV);
            logger.debug(nno + ":" + EVT_SP);
            logger.debug("APX:" + APX);
            logger.debug(APX.substring(0, 8) + ":" + APX.substring(8, 16));
            if ("00".equals(nno)) {
                continue;
            }
            /*logger.debug("xor:"+CommonUtil.xor(APX.substring(0,8),APX.substring(8,16)));*/
            List<AlmResult> almResults = AlmTypeEnum.contentList(Long.valueOf(APX.substring(0, 8), 16), Long.valueOf(APX.substring(8, 16), 16));
            //List<AlmResult> almResults = AlmTypeEnum.contentList(Long.valueOf("C0008011", 16),  Long.valueOf("80000000", 16));
            int alarmstatus = 0;
            for (AlmResult alm : almResults) {
                // 4,14,3,0,10
                if (alm.type == 4 || alm.type == 14 || alm.type == 3 || alm.type == 0 || alm.type == 10) {
                    alarmstatus = 1;
                    logger.info(alm.type + ":" + alm.desc + ":" + alm.platformType);
                    if ("打火报警".equals(alm.desc)) {
                        logger.info("打火->{}:", body);
                    }
                    int alarmType = 0;
                    switch (alm.type) {
                        case 4:
                            alarmType = 1;
                            break;
                        case 14:
                            alarmType = 2;
                            break;
                        case 3:
                            alarmType = 3;
                            break;
                        case 0:
                            alarmType = 4;
                            break;
                        case 10:
                            alarmType = 5;
                            break;
                        default:
                            alarmType = 0;
                    }
                    insertAirByA4(data, alarmType, deviceid, hostid, nno, mac);
                } else if (alm.type == 30) {
                    String openstatus = "0";
                    if (alm.platformType == 301) {//开关开合状态 由开到关
                        openstatus = "1";
                    }
                    if (alm.platformType == 302) {//开关开合状态 由关到开
                        openstatus = "0";
                    }
                    Date date = new Date();
                    ghtClient.doSwitchcontrolRequest(date.getTime() + "", mac, nno, openstatus);
                }
                if (alarmstatus == 1) {
                    logger.debug("!!!!!!!!a4:{}-->body:{}", mac, body);
                }
            }
        }

    }


    @Async
    public void handlerNBClientMapUdp(final String body, final ChannelHandlerContext ctx, final DatagramPacket packet, final String ip, final String port) {
        logger.info(Thread.currentThread().getName()+"===>执行数据采集");
        String key=ip+":"+port;
        String mac = MessageUtil.getMac(body);
        logger.debug("=========hashmap mac" + mac + "," + NettyService.nbClientMap.size());
        NettyService.nbClientMap.put(mac, NBUtil.putNbClient(ctx, packet));
        if(body.startsWith("f1b0")) {
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1b2")){
            logger.debug("接收到报文2,{}的16进制请求数据,网络配置消息f1b2-------------->:{}", key, body);
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1b4")){
            logger.debug("接收到报文3,{}的16进制请求数据,网络配置消息f1b4-------------->:{}",  key,body);
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1a0")){
            String hostid = MessageUtil.getHostID(body);
            String nno = MessageUtil.getNNO(body);
            logger.debug("接收到报文4,{}的16进制请求数据,report_status-------------->:hostid:{},nno,{},{}",key,hostid, nno,body);
            if(body.length()>92&&body.startsWith("f1a0")){
                logger.debug("error.接收到报文4，{}的16进制请求数据,大于92-------------->:{}",  key,body);
            }
            if(!NBUtil.isRepeat(body)) {
               handlerAOUdp(body);
            }
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1aa")){
            logger.debug("接收到报文6,{}的16进制请求数据,心跳消息-------------->:{}",  key,body);
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1d3")){
            logger.info("接收到报文7,{}的COMMAND控制设置命令<<<<<<<<<<:{}",  key,body);
            if(!NBUtil.isRepeat(body)) {
                handlerUserSocketMapMapUdp(body, ctx, packet, ip, port);
            }
        }else if(body.startsWith("f1a2")){

            logger.debug("接收到报文6,{}的16进制请求数据,电量统计消息-------------->:{}",  key,body);
            if(body.startsWith("f1a2f2")) {//保存前一天的统计电量
                handlerA2Udp(body);
            }
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1a4")){
            if("98cc4d2053e5".equals(mac)) {
                logger.debug("接收到报文8,{}的PUSH_EVENT消息,PUSH_EVENT消息,PUSH_EVENT消息<<<<<<<<<<{}:{}", mac, key, body);
            }else {
                logger.debug("接收到报文8,{}的PUSH_EVENT消息,PUSH_EVENT消息,PUSH_EVENT消息<<<<<<<<<<{}:{}", mac, key, body);
            }
            //判断是否重复
            if(!NBUtil.isRepeat(body)) {
                handlerA4Udp(body);
            }
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else if(body.startsWith("f1ac")){
            String hostid = MessageUtil.getHostID(body);
            String nno = MessageUtil.getNNO(body);
            logger.debug("接收到报文9,{}的16进制请求数据,report_status-------------->:hostid:{},nno,{},{}",key,hostid, nno,body);
            if(!NBUtil.isRepeat(body)) {
                handlerACUdp(body);
            }
            handlerBackMessageUdp(body, ctx, packet, ip, port);
        }else{
            logger.debug("接收到报文5,{}的16进制请求数据,其他消息!!!!!-------------->:{}",  key,body);
        }
    }

    public void handlerUserSocketMapMapUdp(final String body, final ChannelHandlerContext ctx, final DatagramPacket packet, final String ip, final String port) {
        String hostid = body.substring(30, 32);
        String cmdno = body.substring(12, 16);
        String key = ip + ":" + port;
        String mac = MessageUtil.getMac(body);
        NettyService.userSocketMap.put(mac, key);
        Date date = new Date();
        String openstatus = "0";
        if (body.startsWith("f1d3a1")) {
            openstatus = "1";
        }
        String nno = NettyService.sendNN0Map.get(mac);
        logger.debug("通讯模块接受到command报文," + mac + ":" + key + ":" + nno + ":" + openstatus);
        if (StringUtil.isNotBlank(nno)) {
            logger.debug("===doSwitchcontrolRequest==");
            // ghtClient.doSwitchcontrolRequest(date.getTime() + "", mac, nno,openstatus);
        }
    }

    /**
     * 只保存状态位1的报警,0状态不再保存
     *
     * @param data
     * @param index
     * @param deviceid
     */
    int insertAir(String data, int index, String deviceid, String hostid, String nno, String mac) {
        AirSwitchDO air = MessageUtil.initAirSwitchDO(data, index);
        if (air != null) {
            if (StringUtil.isNotBlank(deviceid)) {
                air.setDeviceId(Integer.parseInt(deviceid));
            }
            air.setHostid(Integer.parseInt(hostid, 16));
            air.setNno(nno);
            air.setMac(mac);
            if (air.getType() == 1) {
                if (!"00".equals(nno)) {
                    logger.debug("写入alarm_air_switch表->index:{},deviceid:{},AirSwitchDO:{}", index, deviceid, JSON.toJSONString(air));
                    airSwitchMapper.save(air);
                    airSwitchMapper.saveAirSwitchCurrent(air);
                    ghtClient.doDeviceAiralertRequest(air.getAlarmType() + "", air.getAlarmTime().getTime() + "", mac, nno);

                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 只保存状态位1的报警,0状态不再保存
     *
     * @param data
     * @param index
     * @param deviceid
     */
    int insertAirByAC(String data, int index, String deviceid, String hostid, String nno, String mac) {
        AirSwitchDO air = MessageUtil.initAirSwitchDOByAC(data, index);
        if (air != null) {
            if (StringUtil.isNotBlank(deviceid)) {
                air.setDeviceId(Integer.parseInt(deviceid));
            }
            air.setHostid(Integer.parseInt(hostid, 16));
            air.setNno(nno);
            air.setMac(mac);
            if (air.getType() == 1) {
                logger.debug("写入alarm_air_switch表->index:{},deviceid:{},AirSwitchDO:{}.", index, deviceid, JSON.toJSONString(air));
                logger.info("data->alm:{}->{}写入了alarm_air_switch表->index:{},deviceid:{},AirSwitchDO:{}", MessageUtil.getReportAlmAC(data), data, index, deviceid, JSON.toJSONString(air));
                if (!"00".equals(nno)) {
                    airSwitchMapper.save(air);
                    airSwitchMapper.saveAirSwitchCurrent(air);
                    ghtClient.doDeviceAiralertRequest(air.getAlarmType() + "", air.getAlarmTime().getTime() + "", mac, nno);
                    return 1;
                } else {
                    //先屏蔽nno=00
                }
            }
        }
        return 0;
    }

    /**
     * 只保存状态位1的报警,0状态不再保存
     *
     * @param data
     * @param index
     * @param deviceid
     */
    int insertAirByA4(String data, int index, String deviceid, String hostid, String nno, String mac) {
        logger.info("数据9");
        AirSwitchDO air = new AirSwitchDO();
        air.setType(1);
        air.setAlarmType(index);
        if (StringUtil.isNotBlank(deviceid)) {
            air.setDeviceId(Integer.parseInt(deviceid));
        } else {
            return 0;
        }
        air.setHostid(Integer.parseInt(hostid, 16));
        air.setNno(nno);
        air.setMac(mac);
        air.setAlarmTime(new Date());
        logger.debug("写入alarm_air_switch表->index:{},deviceid:{},AirSwitchDO:{}.", index, deviceid, JSON.toJSONString(air));
        airSwitchMapper.save(air);
        airSwitchMapper.saveAirSwitchCurrent(air);
        ghtClient.doDeviceAiralertRequest(air.getAlarmType() + "", air.getAlarmTime().getTime() + "", mac, nno);
        return 1;
    }

    /**
     * 只保存状态位1的报警,0状态不再保存
     *
     * @param data
     * @param index
     * @param deviceid
     */
    int insertAirByA4(String data, int index, String deviceid, String hostid, String mac) {
        logger.info("数据10");
        AirSwitchDO air = MessageUtil.initAirSwitchDOByA4(data, index);
        if (air != null) {
            if (StringUtil.isNotBlank(deviceid)) {
                air.setDeviceId(Integer.parseInt(deviceid));
            }
            air.setHostid(Integer.parseInt(hostid, 16));
            air.setMac(mac);
            if (air.getType() == 1) {
                logger.debug("写入alarm_air_switch表->index:{},deviceid:{},AirSwitchDO:{}..", index, deviceid, JSON.toJSONString(air));
                airSwitchMapper.save(air);
                airSwitchMapper.saveAirSwitchCurrent(air);
                ghtClient.doDeviceAiralertRequest(air.getAlarmType() + "", air.getAlarmTime().getTime() + "", mac, air.getNno());
                return 1;
            }
        }
        return 0;
    }

    void insertDeviceStatus(String data, String deviceid, String hostid, String nno, int alarmstatus, String mac) {
        logger.info("数据11");
        DeviceStatusDO deviceStatusDO = MessageUtil.initDeviceStatusDO(data);
        if (StringUtil.isNotBlank(deviceid)) {
            deviceStatusDO.setDeviceId(Integer.parseInt(deviceid));
        }
        deviceStatusDO.setHostid(Integer.parseInt(hostid, 16));
        deviceStatusDO.setNno(nno);
        deviceStatusDO.setMac(mac);
        deviceStatusDO.setAlarmstatus(alarmstatus);
        logger.debug("写入device_status表->hostid:{},nno:{},deviceid:{},deviceStatusDO{}...", hostid, nno, deviceid, JSON.toJSONString(deviceStatusDO));
        airSwitchMapper.insertDeviceStatus(deviceStatusDO);
        airSwitchMapper.insertDeviceStatusCurrent(deviceStatusDO);
    }

    void insertDeviceStatusByAC(String data, String deviceid, String hostid, String nno, int alarmstatus, String mac) {
        DeviceStatusDO deviceStatusDO = MessageUtil.initDeviceStatusDOByAC(data);
        if (StringUtil.isNotBlank(deviceid)) {
            deviceStatusDO.setDeviceId(Integer.parseInt(deviceid));
        }
        deviceStatusDO.setHostid(Integer.parseInt(hostid, 16));
        deviceStatusDO.setNno(nno);
        deviceStatusDO.setMac(mac);
        deviceStatusDO.setAlarmstatus(alarmstatus);
        logger.debug("写入device_status表->hostid:{},nno:{},deviceid:{},deviceStatusDO{}...", hostid, nno, deviceid, JSON.toJSONString(deviceStatusDO));
        airSwitchMapper.insertDeviceStatus(deviceStatusDO);
        airSwitchMapper.insertDeviceStatusCurrent(deviceStatusDO);
    }

    /**
     * 若通过nno和deviceid在TAirSwitchLine表中查不到id,则默认lineid为0
     *
     * @param data
     * @param deviceid
     */
    void insertTLineRecord(String data, String deviceid) {
        logger.info("数据12");
        TLineRecordDO tLineRecordDO = MessageUtil.initTLineRecordDO(data);
        String nno = MessageUtil.getNNO(data);
        if (StringUtil.isNotBlank(deviceid)) {
            TAirSwitchLine tAirSwitchLine = new TAirSwitchLine();
            tAirSwitchLine.setDeviceId(Integer.parseInt(deviceid));
            tAirSwitchLine.setNno(nno);
            TAirSwitchLine newTair = airSwitchMapper.getAirSwitchLine(tAirSwitchLine);
            if (newTair != null) {
                tLineRecordDO.setLineId(newTair.getId());
            } else {
                tLineRecordDO.setLineId(0);
            }
        } else {
            tLineRecordDO.setLineId(0);
        }
        logger.debug("写入t_line_record表->nno:{},deviceid:{},TLineRecordDO{}.", nno, deviceid, JSON.toJSONString(tLineRecordDO));
        airSwitchMapper.insertTLineRecord(tLineRecordDO);
    }
}
