package org.opentsdb.parser;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.opentsdb.device.model.Point;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.opentsdb.PoolingHttpClient;
import org.opentsdb.builder.MetricBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/***
 * Measurement name and field name are hard configured. Parser just tries to
 * interpret message payload as a float number value e.g.: 123 or 45.2.
 *
 * @author Michal Foksa
 */
public class PipeDeviceData implements PayloadParser {

    static Logger logger = LoggerFactory.getLogger(PipeDeviceData.class) ;

    private String calibrationAPI;
    /**
     * 设备数据字段
     */
    private static final String INDEX = "index";
    /**
     * 设备编号
     */
    private static final String HOST = "host";
    /**
     * 设备类型
     */
    private static final String PRESSURE = "pressure";
    private static final String FLOW = "flow";
    private static final String PITOMETER = "pitometer";
    private static final String ENERGY = "energy";

    PoolingHttpClient httpClient = new PoolingHttpClient();

    public PipeDeviceData(String calibrationAPI) {
        this.calibrationAPI = calibrationAPI;
    }

    @Override
    public Point parse(String topicName, MqttMessage message)
            throws ParseException {
        Float value;
        try {
            // Remove any non digits and dot characters before parse into float.
            value = new Float( new String(message.getPayload()).replaceAll("[^0-9.]", "") );
            JSONObject jsonObj = (JSONObject)(new JSONParser().parse(new String(message.getPayload())));

        } catch (NumberFormatException e) {
            throw new ParseException( e.getClass().getName() + " occured at message payload parsing: " + e.getMessage() , 0 );
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MetricBuilder parseList(String topicName, MqttMessage message)
            throws ParseException {
        MetricBuilder builder = MetricBuilder.getInstance();
        logger.info(new String(message.getPayload()));
        //判断属于哪个topic
        if (topicName.equals("NLM2.0/WuDi")||topicName.contains("jecinfo")) {
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject)(new JSONParser().parse(new String(message.getPayload())));
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            //时间戳
            long TIME = 0;
            //主板电压和温度
            double DT = 0,BV = 0;
            //设备编号
            String sn = "";
            Map<String,Double> map = new HashMap<>();
            ArrayList<Double> list = null;
            if (jsonObj!=null){
                sn = jsonObj.get("SN").toString();
            //解析
            if (jsonObj.get("DT").toString().length()!=0){
                DT = Double.parseDouble((String) jsonObj.get("DT"));
            }
            BV = (Double) jsonObj.get("BV");
            if (jsonObj.get("TIME")==null){
                TIME = (long) jsonObj.get("Time");
            }else {
                TIME = (long) jsonObj.get("TIME");
            }
            map.put("dt",DT);
            map.put("bv",BV);
            list = (ArrayList<Double>) jsonObj.get("DATA");
        }
            addMetrics(builder,"mete.",TIME,sn,map,topicName,list);
        }
        if (topicName.equals("AMR2.0/JiaXing")) {
            logger.info("水电集抄设备");
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject)(new JSONParser().parse(new String(message.getPayload())));
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            long TIME = 0;
            double DT = 0,BV = 0;
            String sn = "";
            Map<String,Double> map = new HashMap<>();
            ArrayList<Double> list = null;
            if (jsonObj!=null){
                 sn = jsonObj.get("SN").toString();
            if (jsonObj.get("DT").toString().length()!=0){
                DT = Double.parseDouble((String) jsonObj.get("DT"));
            }
            BV = (Double) jsonObj.get("BV");
            if (jsonObj.get("TIME")==null){
                TIME = (long) jsonObj.get("Time");
            }else {
                TIME = (long) jsonObj.get("TIME");
            }
                map.put("dt",DT);
                map.put("bv",BV);
            list = (ArrayList<Double>) jsonObj.get("Energy");
            }
            addMetrics(builder,"mete.",TIME,sn,map,topicName,list);
        }
        return builder;
    }
    private void addMetrics(MetricBuilder builder,String metric_name, long timestap, String host, Map<String,Double> data_value,String topic,ArrayList<Double> list) {
        Date date = new Date();
        long dateTime = date.getTime();
        long time = dateTime/1000;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtimestr = simpleDateFormat.format(date);

        Date timestaps = new Date(timestap*1000);
        String devicetime = simpleDateFormat.format(timestaps);
        if (timestap>time){
            logger.info("设备上报数据时间大于当前时间-->{"+nowtimestr+"} 设备上报的时间是-->{"+devicetime+"}  设备编号-->{"+host+"}");
        }
        //无棣和三水湾管网压力设备
            if (topic.equals("NLM2.0/WuDi")||topic.equals("jecinfo/yali")){
                //设备温度电压存存入opentsdb
                Iterator<Map.Entry<String, Double>> it = data_value.entrySet().iterator();
                while (it.hasNext()){
                    Map.Entry<String, Double> entry = it.next();
                    builder.addMetric(metric_name+PRESSURE).setDataPoint(timestap,entry.getValue())
                            .addTag(HOST,host).addTag(INDEX,entry.getKey());
                }
                //设备data数据存入opentsdb
                for (Double value : list){
                    builder.addMetric(metric_name+PRESSURE).setDataPoint(timestap,value)
                            .addTag(HOST,host).addTag(INDEX,PRESSURE);
                    timestap=timestap+60;
                }
            }
            //三水湾管网流量设备
        if (topic.equals("jecinfo/yongliang")){
            Iterator<Map.Entry<String, Double>> it = data_value.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String, Double> entry = it.next();
                builder.addMetric(metric_name+FLOW).setDataPoint(timestap,entry.getValue())
                        .addTag(HOST,host).addTag(INDEX,entry.getKey());
            }
            for (Double value : list){
                builder.addMetric(metric_name+FLOW).setDataPoint(timestap,value)
                        .addTag(HOST,host).addTag(INDEX,FLOW);
                timestap=timestap+60;
            }
        }
        //三水湾管网流速设备
            if (topic.equals("jecinfo/liusu")){
                Iterator<Map.Entry<String, Double>> it = data_value.entrySet().iterator();
                while (it.hasNext()){
                    Map.Entry<String, Double> entry = it.next();
                    builder.addMetric(metric_name+PITOMETER).setDataPoint(timestap,entry.getValue())
                            .addTag(HOST,host).addTag(INDEX,entry.getKey());
                }
                for (Double value : list){
                    builder.addMetric(metric_name+PITOMETER).setDataPoint(timestap,value)
                            .addTag(HOST,host).addTag(INDEX,PITOMETER);
                    timestap=timestap+60;
                }
            }

            //jiaxing水电集抄设备
            if (topic.equals("AMR2.0/JiaXing")){
                Iterator<Map.Entry<String, Double>> it = data_value.entrySet().iterator();
                while (it.hasNext()){
                    Map.Entry<String, Double> entry = it.next();
                    builder.addMetric(metric_name+ENERGY).setDataPoint(timestap,entry.getValue())
                            .addTag(HOST,host).addTag(INDEX,entry.getKey());
                }
                if (list.size()>70){
                    ArrayList<Double> arrayList = new ArrayList<>();
                    for (int i = 1; i <= list.size()-70; i++) {
                        arrayList.add(list.get(69+i));
                    }
                    long times = timestap+700;
                    for (Double value : arrayList){
                        builder.addMetric(metric_name+ENERGY).setDataPoint(times,value)
                                .addTag(HOST,host).addTag(INDEX,ENERGY);
                        times=times+10;
                    }
                    for (int i = 1; i <= list.size()-70; i++) {
                        list.remove(list.size()-1);
                    }
                    for (Double value : arrayList){
                        builder.addMetric(metric_name+ENERGY).setDataPoint(timestap,value)
                                .addTag(HOST,host).addTag(INDEX,ENERGY);
                        timestap=timestap+10;
                    }
                }else {
                for (Double value : list){
                    builder.addMetric(metric_name+ENERGY).setDataPoint(timestap,value)
                            .addTag(HOST,host).addTag(INDEX,ENERGY);
                    timestap=timestap+10;
                }
                }
            }
    }


}
