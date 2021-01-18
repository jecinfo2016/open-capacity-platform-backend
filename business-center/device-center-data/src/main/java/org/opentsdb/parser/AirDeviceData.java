package org.opentsdb.parser;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.opentsdb.PoolingHttpClient;
import org.opentsdb.builder.MetricBuilder;
import org.opentsdb.device.model.Point;
import org.opentsdb.model.Topics;
import org.opentsdb.service.ToTsdb;
import org.opentsdb.util.AQIUtil;
import org.opentsdb.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


/***
 * Measurement name and field name are hard configured. Parser just tries to
 * interpret message payload as a float number value e.g.: 123 or 45.2.
 *
 * @author Michal Foksa
 */
public class AirDeviceData implements PayloadParser {

    static Logger logger = LoggerFactory.getLogger(AirDeviceData.class);

    private static AQIUtil aqiUtil = new AQIUtil();

    private static final String METRICPR = "mete.";

    private static final String INDEX = "index";

    private String calibrationAPI;

    PoolingHttpClient httpClient = new PoolingHttpClient();

    public AirDeviceData(String calibrationAPI) {
        this.calibrationAPI = calibrationAPI;
    }

    @Override
    public Point parse(String topicName, MqttMessage message)
            throws ParseException {
        try {
            // Remove any non digits and dot characters before parse into float.
        } catch (NumberFormatException e) {
            throw new ParseException(e.getClass().getName() + " occured at message payload parsing: " + e.getMessage(), 0);
        }
        return null;
    }

    private static Pattern NUMBER_PATTERN = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");

    @Override
    public MetricBuilder parseList(String topicName, MqttMessage message)
            throws ParseException {
        MetricBuilder builder = MetricBuilder.getInstance();
        List<String> strings = Topics.strings();
        strings.forEach(topic->{
            if (topic.equals(topicName)){
                ToTsdb.toOpenTsdb(message.getPayload(),builder,topicName);
            }
        });
        if (topicName.contains("AQM") && !"AQM2.0/liaoning".equals(topicName)) {
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject) (new JSONParser().parse(new String(message.getPayload())));
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            logger.info("空气数据");
            int aqi = 0;
            String sn = "";
            double pm10 = 0, pm25 = 0, o3 = 0, co = 0, no2 = 0, so2 = 0, AT = 0, AH = 0;
            if (jsonObj != null) {
                sn = jsonObj.get("SN").toString();
                Object a = jsonObj.get("PM10");
                pm10 = Double.parseDouble(a.toString());
                Object b = jsonObj.get("PM2.5");
                pm25 = Double.parseDouble(b.toString());
                so2 = (Double) jsonObj.get("SO2");
                no2 = (Double) jsonObj.get("NO2");
                co = (Double) jsonObj.get("CO");
                o3 = (Double) jsonObj.get("O3");
                AT = (Double) jsonObj.get("AT");
                AH = (Double) jsonObj.get("AH");
            }
            Map<String, Double> map = new HashMap<>();
            aqi = aqiUtil.returnNewAqi(pm25, pm10, so2, no2, 0.9, o3);
            map.put("pm10", pm10);
            map.put("pm25", pm25);
            map.put("so2", so2);
            map.put("no2", no2);
            map.put("co", co);
            map.put("o3", o3);
            map.put("aqi", (double) aqi);
            map.put("at", AT);
            map.put("ah", AH);
            long timestap = DateUtils.getFiveMinutePoint();
            addMetrics(builder, METRICPR, timestap, sn, map, topicName);
        }
        if ("AQM2.0/liaoning".equals(topicName)) {
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject) (new JSONParser().parse(new String(message.getPayload())));
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            logger.info("辽宁污水处理站设备");
            String sn = "";
            long timestap = 0;
            double dt = 0, bv = 0, rssi = 0, cod = 0, ct = 0, flow = 0, ph = 0, tur = 0, do1 = 0, at = 0;
            if (jsonObj != null) {
                timestap = (long) jsonObj.get("TIME");
                sn = jsonObj.get("SN").toString();
                if (jsonObj.get("DT").toString().length() != 0) {
                    dt = Double.parseDouble((String) jsonObj.get("DT"));
                }
                bv = (Double) jsonObj.get("BV");
                rssi = Double.parseDouble((String) jsonObj.get("RSSI"));
                cod = (Double) jsonObj.get("COD");
                ct = (Double) jsonObj.get("CT");
                long flow1 = (long) jsonObj.get("FLOW");
                flow = (double) flow1;
                ph = (Double) jsonObj.get("PH");
                tur = (Double) jsonObj.get("TUR");
                do1 = (Double) jsonObj.get("DO");
                at = (Double) jsonObj.get("AT");
            }
            Map<String, Double> map = new HashMap<>();
            map.put("dt", dt);
            map.put("bv", bv);
            map.put("rssi", rssi);
            map.put("cod", cod);
            map.put("ct", ct);
            map.put("flow", flow);
            map.put("ph", ph);
            map.put("tur", tur);
            map.put("at", at);
            map.put("do", do1);
            addMetrics(builder, METRICPR, timestap, sn, map, topicName);
        }
        if ("jecinfo/watermeter".equals(topicName)) {
            JSONObject jsonObj = null;
            String s = new String(message.getPayload());
            if (s.contains("nan")) {
                String json = s.replace("nan", "0.0");
                try {
                    jsonObj = (JSONObject) (new JSONParser().parse(json));
                } catch (org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    jsonObj = (JSONObject) (new JSONParser().parse(s));
                } catch (org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }
            }
            logger.info("污水厂设备");
            //设备编号
            String sn = "", rssi1 = "";
            //主板电压和温度
            double dt = 0, bv = 0, rssi = 0;
            if (jsonObj != null) {
                sn = jsonObj.get("SN").toString();
                //解析
                if (jsonObj.get("DT").toString().length() != 0) {
                    dt = Double.parseDouble((String) jsonObj.get("DT"));
                }
                bv = (Double) jsonObj.get("BV");
                rssi1 = (String) jsonObj.get("RSSI");
                rssi = Double.parseDouble(rssi1);
            }
            Map<String, Double> map = new HashMap<>();
            map.put("dt", dt);
            map.put("bv", bv);
            map.put("rssi", rssi);
            ArrayList<Object> list = null;
            list = (ArrayList<Object>) jsonObj.get("Value");
            addMetric(builder, METRICPR, sn, map, list);

        }
        if ("jecinfo/vocs".equals(topicName)) {
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject) (new JSONParser().parse(new String(message.getPayload())));
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            logger.info("vocs监测设备");
            String sn = "";
            double ec = 0, dt = 0, bv = 0, rssi = 0, tvoc = 0, at = 0, ah = 0, fw = 0;
            if (jsonObj != null) {
                fw = Double.parseDouble(jsonObj.get("FW").toString());
                sn = jsonObj.get("SN").toString();
                ec = Double.parseDouble(jsonObj.get("EC").toString());
                if (jsonObj.get("DT").toString().length() != 0) {
                    dt = Double.parseDouble((String) jsonObj.get("DT"));
                }
                bv = (Double) jsonObj.get("BV");
                tvoc = (Double) jsonObj.get("TVOC");
                at = (Double) jsonObj.get("AT");
                ah = (Double) jsonObj.get("AH");
            }
            long timesTap = DateUtils.getFiveMinutePoint();
            Map<String, Double> map = new HashMap<>();
            map.put("fw", fw);
            map.put("ec", ec);
            map.put("dt", dt);
            map.put("bv", bv);
            map.put("rssi", rssi);
            map.put("tvoc", tvoc);
            map.put("at", at);
            map.put("ah", ah);
            addMetrics(builder, METRICPR, timesTap, sn, map, topicName);
        }
        if ("jecinfo/water".equals(topicName)) {
            logger.info("水质设备");
            String sn, cod, do1, wt, tur, ct, ph;
            Map<String, Double> index_value = new TreeMap<>();
            long timestamp = 0;
            String s = new String(message.getPayload());
            String[] split = s.split(";");
            sn = split[0].split(":", 2)[1];
            wt = split[8].split(":", 2)[1];
            do1 = split[9].split(":", 2)[1];
            tur = split[10].split(":", 2)[1];
            ct = split[11].split(":", 2)[1];
            ph = split[12].split(":", 2)[1];
            cod = split[13].split(":", 2)[1];
            //如果不是数字类型数据则不存
            if (NUMBER_PATTERN.matcher(wt).matches()) {
                index_value.put("wt", Double.parseDouble(split[8].split(":", 2)[1]));
            }
            if (NUMBER_PATTERN.matcher(do1).matches()) {
                index_value.put("do", Double.parseDouble(split[9].split(":", 2)[1]));
            }
            if (NUMBER_PATTERN.matcher(tur).matches()) {
                index_value.put("tur", Double.parseDouble(split[10].split(":", 2)[1]));
            }
            if (NUMBER_PATTERN.matcher(ct).matches()) {
                index_value.put("ct", Double.parseDouble(split[11].split(":", 2)[1]));
            }
            if (NUMBER_PATTERN.matcher(ph).matches()) {
                index_value.put("ph", Double.parseDouble(split[12].split(":", 2)[1]));
            }
            if (NUMBER_PATTERN.matcher(cod).matches()) {
                index_value.put("cod", Double.parseDouble(split[13].split(":", 2)[1]));
            }
            timestamp = Calendar.getInstance().getTimeInMillis();
            addMetrics(builder, METRICPR, timestamp, sn, index_value, topicName);
        }
        if (topicName.equals("jecinfo/rwqm")) {
            logger.info("二次供水设备");
            Map<String, Double> index_value = new TreeMap<>();
            long timestamp = 0;
            String s = new String(message.getPayload());
            String[] split = s.split(";");
            String sn = split[1].split(":", 2)[1];
            index_value.put("fw", Double.parseDouble(split[2].split(":", 2)[1]));
            index_value.put("ec", Double.parseDouble(split[3].split(":", 2)[1]));
            index_value.put("dt", Double.parseDouble(split[6].split(":", 2)[1]));
            index_value.put("cl", Double.parseDouble(split[7].split(":", 2)[1]));
            index_value.put("tur", Double.parseDouble(split[8].split(":", 2)[1]));
            index_value.put("ph", Double.parseDouble(split[9].split(":", 2)[1]));
            timestamp = Calendar.getInstance().getTimeInMillis();
            addMetrics(builder, METRICPR, timestamp, sn, index_value, topicName);
        }
        return builder;
    }

    private void addMetrics(MetricBuilder builder, String metric_name, long timestap, String host, Map<String, Double> index_value, String topic) {
        Date date = new Date();
        long dateTime = date.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeStr = simpleDateFormat.format(date);
        Date timesTaps = new Date(timestap * 1000);
        String deviceTime = simpleDateFormat.format(timesTaps);
        if (timestap > dateTime) {
            logger.info("设备上报数据时间大于当前时间-->{" + nowTimeStr + "} 设备上报的时间是-->{" + deviceTime + "}  设备编号-->{" + host + "}");
        }
        for (Map.Entry<String, Double> entry : index_value.entrySet()) {
            double value = entry.getValue();
            if ("jecinfo/vocs".equals(topic)) {
                builder.addMetric(metric_name + "vocs").setDataPoint(timestap, value).addTag("host", host).addTag(INDEX, entry.getKey());
            }
            if ("AQM2.0/liaoning".equals(topic)) {
                builder.addMetric(metric_name + "sewage").setDataPoint(timestap, value).addTag("host", host).addTag(INDEX, entry.getKey());
            }
            if (entry.getKey().equals("aqi")) {
                value = (int) value;
            }
            builder.addMetric(metric_name + "air").setDataPoint(timestap, value).addTag("host", host).addTag(INDEX, entry.getKey());
        }
        for (Map.Entry<String, Double> entry : index_value.entrySet()) {
            double value = entry.getValue();
            value = (int) value;
            if ("jecinfo/water".equals(topic)) {
                builder.addMetric(metric_name + "water.quality").setDataPoint(timestap, value).addTag(INDEX, entry.getKey())
                        .addTag("host", host);
            }
            if ("jecinfo/rwqm".equals(topic)) {
                builder.addMetric(metric_name + "water.rwqm").setDataPoint(timestap, value).addTag(INDEX, entry.getKey())
                        .addTag("host", host);
            }
        }
    }


    private void addMetric(MetricBuilder builder, String metric_name, String host, Map<String, Double> data_value, ArrayList<Object> list) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        Iterator<Map.Entry<String, Double>> it = data_value.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            builder.addMetric(metric_name + "watermeter").setDataPoint(timestamp, entry.getValue())
                    .addTag("host", host).addTag(INDEX, entry.getKey());
        }
        Integer count = 0;
        for (Object value : list) {
            double v = Double.parseDouble(value.toString());
            count++;
            if (v > 0) {
                builder.addMetric(metric_name + "watermeter").setDataPoint(timestamp, v)
                        .addTag("host", host).addTag(INDEX, "flow").addTag("number", count.toString());
            }

        }
    }


}
