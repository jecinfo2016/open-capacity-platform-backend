package com.udp.nb.util;

import java.math.BigDecimal;
import java.util.Date;

import com.udp.nb.entity.TTotalRecordDO;
import org.eclipse.jetty.util.StringUtil;

import com.udp.nb.entity.AirSwitchDO;
import com.udp.nb.entity.DeviceStatusDO;
import com.udp.nb.entity.TLineRecordDO;

public class MessageUtil {
    //除去TIME每个节点的消息长度
    public static Integer SingleDataLength=44;
    public static Integer ACsingleDataLength=64;
    public static Integer A2singleDataLength=30;
    public static Integer A8singleDataLength=48;
    public static Integer A4singleDataLength=56;
    /**
     * 获取返回的信息

     * @param hexMessage
     * @return
     */
    public static String getBackMessage(String hexMessage) {
        String pver = hexMessage.substring(0, 2);
        String cmd = hexMessage.substring(2, 4);
        String cmdver = hexMessage.substring(4, 6);
        String cmdack="00";
        String cmdno = hexMessage.substring(12, 16);
        String utctime = (System.currentTimeMillis() / 100000) + "";
        String uid = "FFFFFF80";
        String content = pver + cmd + cmdver +cmdack+ "0000" + cmdno + utctime + uid;

        String crcSend = CRC16Util.crc32(content);
        content = content + crcSend;
        return content.toUpperCase();
    }

    /**
     *合成command报文
     * @param hostid 设备hostid
     * @param cmdver 操作类型
     * @param nno 对应节点号，每个占1位2字节
     * @return
     */
    public static String getCommandA1andA4Message(String hostid,String cmdver,String nno) {
        String currentTime=System.currentTimeMillis()+ "";
        String cmdno=currentTime.substring(9,13);
        String utctime = (System.currentTimeMillis() / 100000) + "";

        String uid = "FFFFFF80";
        String pmt1="00000000";//无意义，占4位
        String pmt2="00000000";//无意义，占4位
        String len="0009";
        //String nno="01";
        int nnoCount=nno.length()/2;
        int length=8+nnoCount;
        len=length+"";
        int currentLenth=len.length();
        for(int i=0;i<4-currentLenth;i++)
        {
            len="0"+len;
        }
        String content = "f1d3"  + cmdver + hostid +len+ cmdno + utctime + uid+pmt1+pmt2+nno;
        String crcSend = CRC16Util.crc32(content);
        content = content + crcSend;
        return content.toUpperCase();
    }

    public static String getMac(String hexMessage) {
        String mac = hexMessage.substring(16, 28);
        return mac;
    }


    /**
     * report_status,hostid为cmdver2，具体参考文档
    * @Title: getHostID
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author n005991
    * @param hexMessage
    * @return
    * @throws
     */
    public static String getHostID(String hexMessage) {
        String cmdver2 = hexMessage.substring(6, 8);
        return cmdver2;
    }

    public static String getNNO(String data) {
//        String data = hexMessage.substring(32, 44);

        String nno=data.substring(8,10);
        return nno;
    }

    public static String getReportAlm(String data) {
//        String data = hexMessage.substring(32, hexMessage.length() - 8);
        String alm = data.substring(36, 44);
        return alm;
    }
    public static String getReportAlmAC(String data) {
//        String data = hexMessage.substring(32, hexMessage.length() - 8);
        String alm = data.substring(48, 56);
        return alm;
    }
    /**
     * 节点数量
     * @param hexMessage
     * @return
     */
    public static Integer getCMDVER(String hexMessage) {
        String cmdver=hexMessage.substring(4,6);
        return Integer.parseInt(cmdver, 16);
    }


    public static String getCMDVER2(String hexMessage) {
        String cmdver2 = hexMessage.substring(6, 8);
        return cmdver2;
    }
    public static Integer getA4NodeCount(String hexMessage) {
        String cmdver2 = hexMessage.substring(6, 8);
        return Integer.parseInt(cmdver2, 16);
    }
    public static Integer getA2NodeCount(String hexMessage) {
        String num=hexMessage.substring(38,40);
        return Integer.parseInt(num, 16);
    }
    public static String getA2TIME(String hexMessage) {
        String time = hexMessage.substring(40, 48);
        return time;
    }
    public static String getA2DATA(String hexMessage) {
        String data =hexMessage.substring(48,hexMessage.length()-8);
        return data;
    }
    public static String getA2NNO(String data) {
        String nno=data.substring(0,2);
        return nno;
    }



    //------------A4 PUSH_EVENT 消息
    public static String getA4DATA(String hexMessage) {
        String data =hexMessage.substring(32,hexMessage.length()-8);
        return data;
    }

    public static Integer  getA4DATACount(String hexMessage) {
        String num=hexMessage.substring(38,40);
        return Integer.parseInt(num, 16);
    }

    public static String getA4TIME(String hexMessage) {
        String time = hexMessage.substring(40, 48);
        return time;
    }



    public static Integer getLen(String hexMessage) {
        String len=hexMessage.substring(8,12);
        return Integer.parseInt(len, 16)*2;
    }
    public static String getTIME(String hexMessage) {
        String time = hexMessage.substring(32, 40);
        return time;
    }
    public static String getDATA(String hexMessage) {
        String data =hexMessage.substring(40,hexMessage.length()-8);
        return data;
    }
    public static DeviceStatusDO initDeviceStatusDO(String data) {
        DeviceStatusDO deviceStatusDO = new DeviceStatusDO();
       /* String pver = hexMessage.substring(0, 2);
        String cmd = hexMessage.substring(2, 4);
        String cmdver = hexMessage.substring(4, 6);
        String cmdver2 = hexMessage.substring(6, 8);
        String len = hexMessage.substring(8, 12);
        String cmdno = hexMessage.substring(12, 16);
        String mac = hexMessage.substring(16, 28);
        String timezone = hexMessage.substring(28, 30);
        String hostid = hexMessage.substring(30, 32);
        String data = hexMessage.substring(32, hexMessage.length() - 8);
        String crc = hexMessage.substring(hexMessage.length() - 8, hexMessage.length());
*/
        String time = data.substring(0, 8);
        String nno = data.substring(8, 10);
        String type = data.substring(10, 12);
        String vol = data.substring(12, 16);
        String lki = data.substring(16, 20);
        String pwr = data.substring(20, 24);
        String tmp = data.substring(24, 28);
        String cur = data.substring(28, 32);
        String cur2 = data.substring(32, 36);
        String alm = data.substring(36, 44);
        String ps = data.substring(44, 52);
        BigDecimal ten = new BigDecimal(10);
        deviceStatusDO.setType(type);
        deviceStatusDO.setTemperature(new BigDecimal(Integer.parseInt(tmp, 16)).divide(ten));
        deviceStatusDO.setCurrent(new BigDecimal(Integer.parseInt(cur, 16)).divide(ten));
        deviceStatusDO.setPower(new BigDecimal(Integer.parseInt(pwr, 16)));
        deviceStatusDO.setRecordtime(new Date());
        deviceStatusDO.setVoltage(new BigDecimal(Integer.parseInt(vol, 16)));
        if (StringUtil.isNotBlank(CommonUtil.binaryValue(31, alm))) {
            deviceStatusDO.setStatus(Integer.parseInt(CommonUtil.binaryValue(31, alm)));
        }
        if (StringUtil.isNotBlank(CommonUtil.binaryValue(30, alm))) {
            deviceStatusDO.setOpenstatus(Integer.parseInt(CommonUtil.binaryValue(30, alm)));
        }
        return deviceStatusDO;
    }
    public static DeviceStatusDO initDeviceStatusDOByAC(String data) {
        DeviceStatusDO deviceStatusDO = new DeviceStatusDO();
        String time = data.substring(0, 8);
        String nno = data.substring(8, 10);
        String type = data.substring(10, 12);
        String vol = data.substring(12, 16);
        String lki = data.substring(16, 20);
        String pwr = data.substring(20, 28);
        String tmp = data.substring(28, 32);
        String cur = data.substring(32, 40);
        String cur2 = data.substring(40, 48);
        String alm = data.substring(48, 56);
        String ps = data.substring(56, 72);
        BigDecimal ten = new BigDecimal(10);
        deviceStatusDO.setType(type);
        deviceStatusDO.setTemperature(new BigDecimal(Integer.parseInt(tmp, 16)).divide(ten));
        deviceStatusDO.setCurrent(new BigDecimal(Integer.parseInt(cur, 16)).divide(ten));
        deviceStatusDO.setPower(new BigDecimal(Long.parseLong(pwr, 16)));
        deviceStatusDO.setRecordtime(new Date());
        deviceStatusDO.setVoltage(new BigDecimal(Integer.parseInt(vol, 16)));
        if (StringUtil.isNotBlank(CommonUtil.binaryValue(31, alm))) {
            deviceStatusDO.setStatus(Integer.parseInt(CommonUtil.binaryValue(31, alm)));
        }
        if (StringUtil.isNotBlank(CommonUtil.binaryValue(30, alm))) {
            deviceStatusDO.setOpenstatus(Integer.parseInt(CommonUtil.binaryValue(30, alm)));
        }
        return deviceStatusDO;
    }
    public static TLineRecordDO initTLineRecordDO(String data) {
        TLineRecordDO tLineRecordDO = new TLineRecordDO();
        String time = data.substring(0, 8);
        String nno = data.substring(8, 10);
        String type = data.substring(10, 12);
        String vol = data.substring(12, 16);
        String lki = data.substring(16, 20);
        String pwr = data.substring(20, 24);
        String tmp = data.substring(24, 28);
        String cur = data.substring(28, 32);
        String cur2 = data.substring(32, 36);
        String alm = data.substring(36, 44);
        String ps = data.substring(44, 52);
        BigDecimal ten = new BigDecimal(10);
        tLineRecordDO.setTemperature(new BigDecimal(Integer.parseInt(tmp, 16)).divide(ten));
        tLineRecordDO.setCurrent(new BigDecimal(Integer.parseInt(cur, 16)).divide(ten));
        tLineRecordDO.setPower(new BigDecimal(Integer.parseInt(pwr, 16)));
        tLineRecordDO.setCreateTime(new Date());
        tLineRecordDO.setVoltage(new BigDecimal(Integer.parseInt(vol, 16)));
        tLineRecordDO.setLeakageCurre(new BigDecimal(Integer.parseInt(lki, 16)).divide(ten));
        tLineRecordDO.setRecordType("1");
        tLineRecordDO.setRecordTime(new Date());
        if (StringUtil.isNotBlank(CommonUtil.binaryValue(31, alm))) {
            tLineRecordDO.setState(CommonUtil.binaryValue(31, alm));
        }
        return tLineRecordDO;
    }
    public static TTotalRecordDO initTTotalRecordDO(String data) {
        TTotalRecordDO tTotalRecordDO = new TTotalRecordDO();
        String nno = data.substring(0, 2);
        String type = data.substring(2, 4);
        String dn = data.substring(4, 6);
        String vol =data.substring(6, 10);
        String lki = data.substring(10, 14);
        String tmp = data.substring(14, 18);
        String cur = data.substring(18, 22);
        String ps = data.substring(22, 30);
        BigDecimal ten = new BigDecimal(10);
        tTotalRecordDO.setNno(nno);
        tTotalRecordDO.setDn(Integer.parseInt(dn,16));
        tTotalRecordDO.setVoltage(new BigDecimal(Integer.parseInt(vol, 16)));
        tTotalRecordDO.setLeakageCurre(new BigDecimal(Integer.parseInt(lki, 16)).divide(ten));
        tTotalRecordDO.setTemperature(new BigDecimal(Integer.parseInt(tmp, 16)).divide(ten));
        tTotalRecordDO.setCurrent(new BigDecimal(Integer.parseInt(cur, 16)).divide(ten));
        tTotalRecordDO.setPs(new BigDecimal(Integer.parseInt(ps, 16)));
        tTotalRecordDO.setCreateTime(new Date());
        tTotalRecordDO.setRecordType("1");
        tTotalRecordDO.setRecordTime(new Date());
        return tTotalRecordDO;
    }
    public static TTotalRecordDO initA8TTotalRecordDO(String data) {
        TTotalRecordDO tTotalRecordDO = new TTotalRecordDO();
        String nno = data.substring(0, 2);
        String type = data.substring(2, 4);
        String dn = data.substring(4, 6);
        String resv = data.substring(6, 12);
        String vol =data.substring(12, 16);
        String lki = data.substring(16, 20);
        String tmp = data.substring(20, 24);
        String cur = data.substring(24, 32);
        String ps = data.substring(32, 48);
        BigDecimal ten = new BigDecimal(10);
        tTotalRecordDO.setNno(nno);
        tTotalRecordDO.setDn(Integer.parseInt(dn,16));
        tTotalRecordDO.setVoltage(new BigDecimal(Integer.parseInt(vol, 16)));
        tTotalRecordDO.setLeakageCurre(new BigDecimal(Integer.parseInt(lki, 16)).divide(ten));
        tTotalRecordDO.setTemperature(new BigDecimal(Integer.parseInt(tmp, 16)).divide(ten));
        tTotalRecordDO.setCurrent(new BigDecimal(Integer.parseInt(cur, 16)).divide(ten));
        tTotalRecordDO.setPs(new BigDecimal(Integer.parseInt(ps, 16)));
        tTotalRecordDO.setCreateTime(new Date());
        tTotalRecordDO.setRecordType("1");
        tTotalRecordDO.setRecordTime(new Date());
        return tTotalRecordDO;
    }
    /**
     * 获取位数
     * 空开报警类型：1、漏电报警->4；2、漏电预警->14；3、温度报警->3；4、短路报警->0；5、打火报警->10；
     * @param data
     * @param index
     * @return
     */
    public static AirSwitchDO initAirSwitchDO(String data, int index) {
        AirSwitchDO airSwitchDO = new AirSwitchDO();
        String alm = getReportAlm(data);
        String value = CommonUtil.binaryValue(index, alm);
        if (StringUtil.isNotBlank(value)) {
            int alarmType = 0;
            switch (index) {
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
            airSwitchDO.setAlarmType(alarmType);
            airSwitchDO.setType(Integer.parseInt(value));
            airSwitchDO.setAlarmTime(new Date());
            return airSwitchDO;
        } else {
            return null;
        }
    }
    /**
     * 获取位数
     * 空开报警类型：1、漏电报警->4；2、漏电预警->14；3、温度报警->3；4、短路报警->0；5、打火报警->10；
     * @param data
     * @param index
     * @return
     */
    public static AirSwitchDO initAirSwitchDOByAC(String data, int index) {
        AirSwitchDO airSwitchDO = new AirSwitchDO();
        String alm = getReportAlmAC(data);
        String value = CommonUtil.binaryValue(index, alm);
        if (StringUtil.isNotBlank(value)) {
            int alarmType = 0;
            switch (index) {
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
            airSwitchDO.setAlarmType(alarmType);
            airSwitchDO.setType(Integer.parseInt(value));
            airSwitchDO.setAlarmTime(new Date());
            return airSwitchDO;
        } else {
            return null;
        }
    }
    /**
     * 获取位数
     * 空开报警类型：1、漏电报警->4；2、漏电预警->14；3、温度报警->3；4、短路报警->0；5、打火报警->10；
     * @param data
     * @param index
     * @return
     */
    public static AirSwitchDO initAirSwitchDOByA4(String data, int index) {
        AirSwitchDO airSwitchDO = new AirSwitchDO();
        String alm = getReportAlm(data);
        String value = CommonUtil.binaryValue(index, alm);
        if (StringUtil.isNotBlank(value)) {
            int alarmType = 0;
            switch (index) {
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
            airSwitchDO.setAlarmType(alarmType);
            airSwitchDO.setType(Integer.parseInt(value));
            airSwitchDO.setAlarmTime(new Date());
            return airSwitchDO;
        } else {
            return null;
        }
    }

}
