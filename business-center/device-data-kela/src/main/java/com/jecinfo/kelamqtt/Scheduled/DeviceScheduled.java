package com.jecinfo.kelamqtt.Scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jecinfo.kelamqtt.model.Querie;
import com.jecinfo.kelamqtt.model.RequestModel;
import com.jecinfo.kelamqtt.model.Tags;
import com.jecinfo.kelamqtt.opentsdb.ExpectResponse;
import com.jecinfo.kelamqtt.opentsdb.HttpClientImpl;
import com.jecinfo.kelamqtt.opentsdb.builder.MetricBuilder;
import com.jecinfo.kelamqtt.opentsdb.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
@SuppressWarnings("all")
public class DeviceScheduled {

    @Autowired
    @Qualifier("restTemplateNew")
    private RestTemplate restTemplate;

    /**
     * 定时任务半小时
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void sendOpentsdb() {
        MetricBuilder builder = MetricBuilder.getInstance();
        HttpClientImpl client = new HttpClientImpl("http://61.164.218.155:61242");
        Date date = new Date();
        //时间
        long timestap = date.getTime();
        Random random = new Random();
        //tds随机数300以内
        Double value = Double.valueOf(Math.floor(random.nextDouble() * 300));
        HashMap<String, Double> drinkTags = new HashMap<>();
        drinkTags.put("tds_origin",value);
        drinkTags.put("tds",value);
        //cod为0-8之间
        Double cod = Double.valueOf(Math.floor(random.nextDouble() * 8));
        drinkTags.put("cod",cod);
        //toc9以内
        Double tocs = Double.valueOf(Math.floor(random.nextDouble() * 9));
        drinkTags.put("toc",tocs);
        drinkTags.put("pitometer",value);
        drinkTags.put("flow",value);
        //水温35度以内
        Double tp_normal = Double.valueOf(Math.floor(random.nextDouble() * 35));
        drinkTags.put("tp_normal",tp_normal);
        //开水温度100
        drinkTags.put("tp_boiling",100.0);
        HashMap<String, Double> rwqmTags = new HashMap<>();
        double v = random.nextDouble();
        //余氯0.0-0.3之间保留两位小数
        String format = String.format("%.2f", v+0.3);
        double ppm = Double.parseDouble(format);
        rwqmTags.put("ppm",ppm);
        //浊度ntu，4以内保留两位小数
        double n = ((Math.random()*(4-0)))+0;
        String format1 = String.format("%.2f", n);
        double ntu = Double.parseDouble(format1);
        rwqmTags.put("ntu",ntu);
        //ph值6-10之间
        double ph = random.nextInt(100 - 60 + 10) + 55;
        rwqmTags.put("ph",ph/10);
        for (Map.Entry<String, Double> entry : drinkTags.entrySet()) {
            double drinkValue = entry.getValue();
            builder.addMetric("mete.water.drink").setDataPoint(timestap, drinkValue).addTag("host", "dr0001").addTag("index",entry.getKey());
            builder.addMetric("mete.water.drink").setDataPoint(timestap, drinkValue).addTag("host", "dr0002").addTag("index",entry.getKey());
            builder.addMetric("mete.water.drink").setDataPoint(timestap, drinkValue).addTag("host", "dr0003").addTag("index",entry.getKey());
        }
        for (Map.Entry<String, Double> entry : rwqmTags.entrySet()) {
            double rwqmValue = entry.getValue();
            builder.addMetric("mete.water.rwqm").setDataPoint(timestap, rwqmValue).addTag("host", "rw0001").addTag("index",entry.getKey());
            builder.addMetric("mete.water.rwqm").setDataPoint(timestap, rwqmValue).addTag("host", "rw0002").addTag("index",entry.getKey());
            builder.addMetric("mete.water.rwqm").setDataPoint(timestap, rwqmValue).addTag("host", "rw0003").addTag("index",entry.getKey());
        }
        try {
            //存入opentsdb
            Response response = client.pushMetrics(builder,
                    ExpectResponse.SUMMARY);
            log.info("饮水设备"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时任务半小时
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void flowmeter() {
        MetricBuilder builder = MetricBuilder.getInstance();
        HttpClientImpl client = new HttpClientImpl("http://61.164.218.155:61242");
        Date date = new Date();
        long timestap = date.getTime();
        HashMap<String, String> flowmeterTags = new HashMap<>();
        Random random = new Random();
        double flow = 0;
        double num = 20 + random.nextInt(31);
        flow += num / 24;
        //获取上一次添加的值
        RequestModel requestModel = new RequestModel("1h-ago", null, new ArrayList<>());
        Querie querie = new Querie();
        Tags tags = querie.getTags();
        querie.setAggregator("none");
        querie.setMetric("mete.flowmeter");
        querie.setTags(new Tags("fm0001"));
        requestModel.getQueries().add(querie);
        JSONObject jsonObj = (JSONObject) JSONObject.toJSON(requestModel);
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //请求体
        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj.toString(), headers);
        //发起请求
        String jsonResult = restTemplate.postForObject("http://61.164.218.155:39200/api-device/opentsdbData", formEntity, String.class);
        double v1 = 0.0;
        String s = "0.0";
        if (jsonResult!=null&&jsonResult!=""){
            JSONObject jsonObject = JSON.parseObject(jsonResult);
            JSONArray datas = (JSONArray) jsonObject.get("datas");
            if (datas.size()==0){
                v1=1.0;
            }else {
                JSONObject data = datas.getJSONObject(0);
                BigDecimal value = (BigDecimal) data.get("value");
                double v = value.doubleValue();
                flow+=v;
                s = String.format("%.2f", flow);
                v1 = Double.parseDouble(s);
            }
        }
        builder.addMetric("mete.flowmeter").setDataPoint(timestap,v1).addTag("host", "fm0001").addTag("index","flowmeter");
        builder.addMetric("mete.flowmeter").setDataPoint(timestap,v1).addTag("host", "fm0002").addTag("index","flowmeter");
        builder.addMetric("mete.flowmeter").setDataPoint(timestap,v1).addTag("host", "fm0003").addTag("index","flowmeter");
        try {
            //存入opentsdb
            Response response = client.pushMetrics(builder,
                    ExpectResponse.SUMMARY);
            log.info("水表设备"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void sendOpentsdb2() {
        MetricBuilder builder = MetricBuilder.getInstance();
        HttpClientImpl client = new HttpClientImpl("http://61.164.218.155:61242");
        HashMap<String, Double> sz = new HashMap<>();
        HashMap<String, Double> sz1 = new HashMap<>();
        HashMap<String, Double> sz2 = new HashMap<>();
        HashMap<String, Double> sz3 = new HashMap<>();
        HashMap<String, Double> sz4 = new HashMap<>();
        Date date = new Date();
        //时间
        long timestap = date.getTime();
        Random random = new Random();
        //水温0-15 秋冬自来水
        Double wt = Double.valueOf(Math.floor(random.nextDouble() * 15));
        sz.put("wt",wt);
        //水温0-8.5 深冬自来水 深冬中水
        Double wt1 = Double.valueOf(Math.floor(random.nextDouble() * 8.5));
        sz1.put("wt",wt1);
        //水温13-25 春夏自来水 中水
        Double wt2 = ran(13.0, 25.0);
        sz2.put("wt",wt2);
        //水温20-30 盛夏自来水
        Double wt3 = ran(20.0, 30.0);
        sz3.put("wt",wt3);
        //水温16-24 初秋中水
        Double wt4 = ran(16.0, 24.0);
        sz4.put("wt",wt4);
        //溶解氧 秋冬自来水
        Double dio = ran(7.5, 15.0);
        sz.put("do",dio);
        //溶解氧 深冬自来水 中水
        Double dio2 = ran(9.0, 16.0);
        sz1.put("do",dio2);
        //溶解氧 春夏自来水 中水
        Double dio3 = ran(6.5, 10.5);
        sz2.put("do",dio3);
        //溶解氧 盛夏自来水
        Double dio4 = ran(3.4, 8.9);
        sz3.put("do",dio4);
        //溶解氧 初秋中水
        Double dio5 = ran(7.9, 9.6);
        sz4.put("do",dio5);
        //浊度
        Double tur = ran(1.0, 20.0);
        sz.put("tur",tur);
        sz1.put("tur",tur);
        sz2.put("tur",tur);
        sz3.put("tur",tur);
        sz4.put("tur",tur);
        //PH
        Double ph = ran(6.0, 7.2);
        sz.put("ph",ph);
        sz1.put("ph",ph);
        sz2.put("ph",ph);
        sz3.put("ph",ph);
        sz4.put("ph",ph);
        //电导率
        Double ct = ran( 0.3,1.1);
        sz.put("ct",ct);
        sz1.put("ct",ct);
        sz2.put("ct",ct);
        sz3.put("ct",ct);
        sz4.put("ct",ct);
        //cod 自来水
        Double cod = ran( 10.0,20.0);
        sz.put("cod",cod);
        sz1.put("cod",cod);
        sz2.put("cod",cod);
        sz3.put("cod",cod);
        //cod 中水
        Double cod2 = ran( 50.0,120.0);
        sz4.put("cod",cod2);
        for (Map.Entry<String, Double> entry : sz.entrySet()) {
            double value = entry.getValue();
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721000").addTag("index", entry.getKey());
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721008").addTag("index", entry.getKey());
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721010").addTag("index", entry.getKey());
        }
        for (Map.Entry<String, Double> entry : sz1.entrySet()) {
            double value = entry.getValue();
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721001").addTag("index", entry.getKey());
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721002").addTag("index", entry.getKey());
        }
        for (Map.Entry<String, Double> entry : sz1.entrySet()) {
            double value = entry.getValue();
            if (sz1.containsKey("cod")){
                sz1.put("cod",cod2);
            }
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721003").addTag("index", entry.getKey())
            .addTag("type","rw");
        }
        for (Map.Entry<String, Double> entry : sz2.entrySet()) {
            double value = entry.getValue();
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721004").addTag("index", entry.getKey());
        }
        for (Map.Entry<String, Double> entry : sz2.entrySet()) {
            double value = entry.getValue();
            if (sz1.containsKey("cod")){
                sz1.put("cod",cod2);
            }
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721005").addTag("index", entry.getKey())
                    .addTag("type","rw");;
        }
        for (Map.Entry<String, Double> entry : sz3.entrySet()) {
            double value = entry.getValue();
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721006").addTag("index", entry.getKey());
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721007").addTag("index", entry.getKey());
        }
        for (Map.Entry<String, Double> entry : sz4.entrySet()) {
            double value = entry.getValue();
            builder.addMetric("mete.water.quality").setDataPoint(timestap, value).addTag("host", "SZ721009").addTag("index", entry.getKey())
                    .addTag("type","rw");;
        }
        try {
            //存入opentsdb
            Response response = client.pushMetrics(builder,
                    ExpectResponse.SUMMARY);
            log.info("水质监测仪"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  Double ran(Double max,Double min) {
        Random random = new Random();
            double ran2 = (double) (Math.random()*(max-min)+min);
            String format1 = String.format("%.1f", ran2);
            Double d = Double.parseDouble(format1);
            return d;
    }

    public  Double ran2(Double ran) {
        Random random = new Random();
        double ran2 = (double) (Math.random()*ran);
        String format1 = String.format("%.2f", ran2);
        Double d = Double.parseDouble(format1);
        return d;
    }

    public  Double ran3(Double ran) {
        String format1 = String.format("%.2f", ran);
        Double d = Double.parseDouble(format1);
        return d;
    }

    @Scheduled(cron = "0 */30 * * * ?")
    public void sendOpentsdb3() {
        MetricBuilder builder = MetricBuilder.getInstance();
        HttpClientImpl client = new HttpClientImpl("http://61.164.218.155:61242");
        Date date = new Date();
        //时间
        long timestap = date.getTime();
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(27.0, 46.0))
                .addTag("host", "HQB10001").addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(27.0, 46.0)).addTag("host", "HQB10002")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(52.0, 71.0)).addTag("host", "HQB10003")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(27.0, 45.0)).addTag("host", "HQB10004")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(18.0, 31.0)).addTag("host", "HQB10005")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(10.0, 16.7)).addTag("host", "HQB10007")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(10.0, 16.7)).addTag("host", "HQB10008")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(15.0, 27.0)).addTag("host", "HQB10009")
                .addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(15.0, 27.0)).addTag("host", "HQB10010")
                .addTag("index","pitometer");
        //中水
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(3.4, 6.3)).addTag("host", "HQB10006")
                .addTag("type","rw").addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(6.3, 12.5)).addTag("host", "HQB10011")
                .addTag("type","rw").addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(6.3, 12.5)).addTag("host", "HQB10012")
                .addTag("type","rw").addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(6.0, 10.0)).addTag("host", "HQB10015")
                .addTag("type","rw").addTag("index","pitometer");
        builder.addMetric("mete.pitometer").setDataPoint(timestap, ran(4.6, 9.3)).addTag("host", "HQB10017")
                .addTag("type","rw").addTag("index","pitometer");
        try {
            //存入opentsdb
            Response response = client.pushMetrics(builder,
                    ExpectResponse.SUMMARY);
            log.info("流量计"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 水表
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void sendOpentsdb4() {
        MetricBuilder builder = MetricBuilder.getInstance();
        HttpClientImpl client = new HttpClientImpl("http://61.164.218.155:61242");
        Date date = new Date();
        //时间
        long timestap = date.getTime();
        SimpleDateFormat dateFormater = new SimpleDateFormat("HHmm");
        String format = dateFormater.format(new Date());
        int time = Integer.parseInt(format);
        if (time > 600 && time < 2300) {
            System.out.println("白天流量");
            //获取上一次添加的值
            Double zx03601 = lastValue("ZX03601");
            Double ran =  800.0/30;
            Double d = ran3(ran);
            Double value = ran2(d);
            zx03601 = zx03601+value;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx03601).addTag("host", "ZX03601").addTag("index","flowmeter");

            Double zx03602 = lastValue("ZX03602");
            Double ran1 =  700.0/30;
            Double d2 = ran3(ran1);
            Double value1 = ran2(d2);
            zx03602 = zx03602+value1;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx03602).addTag("host", "ZX03602").addTag("index","flowmeter");

            Double zx03603 = lastValue("ZX03603");
            Double ran2 =  1250.0/30;
            Double d3 = ran3(ran2);
            Double value2 = ran2(d3);
            zx03603 = zx03603+value2;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx03603).addTag("host", "ZX03603").addTag("index","flowmeter");

            Double zx005201 = lastValue("ZX005201");
            Double ran3 =  800.0/30;
            Double d4 = ran3(ran3);
            Double value3 = ran2(d4);
            zx005201 = zx005201+value3;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx005201).addTag("host", "ZX005201").addTag("index","flowmeter");

            Double zx005202 = lastValue("ZX005202");
            Double ran4 =  600.0/30;
            Double value4 = ran2(ran3(ran4));
            zx005202 = zx005202+value4;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx005202).addTag("host", "ZX005202").addTag("index","flowmeter");

            Double zx152101 = lastValue("ZX152101");
            Double ran5 =  100.0/30;
            Double value5 = ran2(ran3(ran5));
            zx152101 = zx152101+value5;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx152101).addTag("host", "ZX152101").addTag("index","flowmeter");

            Double zx194201 = lastValue("ZX194201");
            Double ran6 =  300.0/30;
            Double value6 = ran2(ran3(ran6));
            zx194201 = zx194201+value6;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx194201).addTag("host", "ZX194201").addTag("index","flowmeter");

            Double zx152102 = lastValue("ZX152102");
            Double ran7 =  300.0/30;
            Double value7 = ran2(ran3(ran7));
            zx152102 = zx152102+value7;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx152102).addTag("host", "ZX152102").addTag("index","flowmeter");

            Double zx13001 = lastValue("ZS13001");
            Double ran8 =  450.0/30;
            Double value8 = ran2(ran3(ran8));
            zx13001 = zx13001+value8;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13001).addTag("host", "ZS13001").addTag("index","flowmeter");

            Double zx13002 = lastValue("ZS13002");
            Double ran9 =  450.0/30;
            Double value9 = ran2(ran3(ran9));
            zx13002 = zx13002+value9;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13002).addTag("host", "ZS13002").addTag("index","flowmeter");

            Double zx13003 = lastValue("ZS13003");
            Double ran10 =  200.0/30;
            Double value10 = ran2(ran3(ran10));
            zx13003 = zx13003+value10;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13003).addTag("host", "ZS13003").addTag("index","flowmeter");

            Double zx13004 = lastValue("ZS13004");
            Double ran11 =  200.0/30;
            Double value11 = ran2(ran3(ran11));
            zx13004 = zx13004+value11;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13004).addTag("host", "ZS13004").addTag("index","flowmeter");

            Double zs49001 = lastValue("ZS49001");
            Double ran12 =  100.0/30;
            Double value12 = ran2(ran3(ran12));
            zs49001 = zs49001+value12;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zs49001).addTag("host", "ZS49001").addTag("index","flowmeter");

            Double zx49002 = lastValue("ZS49002");
            Double ran13 =  35.0/30;
            Double value13 = ran2(ran3(ran13));
            zx49002 = zx49002+value13;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx49002).addTag("host", "ZS49002").addTag("index","flowmeter");
        } else {
            System.out.println("夜间流量");
            Double zx03601 = lastValue("ZX03601");
            Double ran =  180.0/14;
            Double value = ran2(ran3(ran));
            zx03601 = zx03601+value;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx03601).addTag("host", "ZX03601").addTag("index","flowmeter");

            Double zx03602 = lastValue("ZX03602");
            Double ran1 =  350.0/14;
            Double value1 = ran2(ran3(ran1));
            zx03602 = zx03602+value1;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx03602).addTag("host", "ZX03602").addTag("index","flowmeter");

            Double zx03603 = lastValue("ZX03603");
            Double ran2 =  360.0/14;
            Double value2 = ran2(ran3(ran2));
            zx03603 = zx03603+value2;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx03603).addTag("host", "ZX03603").addTag("index","flowmeter");

            Double zx005201 = lastValue("ZX005201");
            Double ran3 =  260.0/14;
            Double value3 = ran2(ran3(ran3));
            zx005201 = zx005201+value3;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx005201).addTag("host", "ZX005201").addTag("index","flowmeter");

            Double zx005202 = lastValue("ZX005202");
            Double ran4 =  110.0/14;
            Double value4 = ran2(ran3(ran4));
            zx005202 = zx005202+value4;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx005202).addTag("host", "ZX005202").addTag("index","flowmeter");

            Double zx152101 = lastValue("ZX152102");
            Double ran5 =  35.0/14;
            Double value5 = ran2(ran3(ran5));
            zx152101 = zx152101+value5;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx152101).addTag("host", "ZX152102").addTag("index","flowmeter");

            Double zx194201 = lastValue("ZX194201");
            Double ran6 =  90.0/14;
            Double value6 = ran2(ran3(ran6));
            zx194201 = zx194201+value6;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx194201).addTag("host", "ZX194201").addTag("index","flowmeter");

            Double zx152102 = lastValue("ZX152102");
            Double ran7 =  35.0/14;
            Double value7 = ran2(ran3(ran7));
            zx152102 = zx152102+value7;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx152102).addTag("host", "ZX152102").addTag("index","flowmeter");

            Double zx13001 = lastValue("ZS13001");
            Double ran8 =  165.0/14;
            Double value8 = ran2(ran3(ran8));
            zx13001 = zx13001+value8;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13001).addTag("host", "ZS13001").addTag("index","flowmeter");

            Double zx13002 = lastValue("ZS13002");
            Double ran9 =  165.0/14;
            Double value9 = ran2(ran3(ran9));
            zx13002 = zx13002+value9;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13002).addTag("host", "ZS13002").addTag("index","flowmeter");

            Double zx13003 = lastValue("ZS13003");
            Double ran10 =  86.0/14;
            Double value10 = ran2(ran3(ran10));
            zx13003 = zx13003+value10;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13003).addTag("host", "ZS13003").addTag("index","flowmeter");

            Double zx13004 = lastValue("ZS13004");
            Double ran11 =  86.0/14;
            Double value11 = ran2(ran3(ran11));
            zx13004 = zx13004+value11;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx13004).addTag("host", "ZS13004").addTag("index","flowmeter");

            Double zs49001 = lastValue("ZS49001");
            Double ran12 =  30.0/14;
            Double value12 = ran2(ran3(ran12));
            zs49001 = zs49001+value12;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zs49001).addTag("host", "ZS49001").addTag("index","flowmeter");

            Double zx49002 = lastValue("ZS49002");
            Double ran13 =  10.0/14;
            Double value13 = ran2(ran3(ran13));
            zx49002 = zx49002+value13;
            builder.addMetric("mete.flowmeter").setDataPoint(timestap, zx49002).addTag("host", "ZS49002").addTag("index","flowmeter");
        }

        try {
            //存入opentsdb
            Response response = client.pushMetrics(builder,
                    ExpectResponse.SUMMARY);
            log.info("水表"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  Double lastValue(String host) {
        RequestModel requestModel = new RequestModel("1h-ago", null, new ArrayList<>());
        Querie querie = new Querie();
        Tags tags = querie.getTags();
        querie.setAggregator("none");
        querie.setMetric("mete.flowmeter");
        querie.setTags(new Tags(host));
        requestModel.getQueries().add(querie);
        JSONObject jsonObj = (JSONObject) JSONObject.toJSON(requestModel);
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //请求体
        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj.toString(), headers);
        //发起请求
        String jsonResult = restTemplate.postForObject("http://61.164.218.155:39200/api-device/opentsdbData", formEntity, String.class);
        double v1 = 0.0;
        String s = "0.0";
        double flow = 0;
        if (jsonResult!=null&&jsonResult!=""){
            JSONObject jsonObject = JSON.parseObject(jsonResult);
            JSONArray datas = (JSONArray) jsonObject.get("datas");
            if (datas.size()==0){
                v1=1.0;
            }else {
                JSONObject data = datas.getJSONObject(0);
                BigDecimal value = (BigDecimal) data.get("value");
                double v = value.doubleValue();
                flow+=v;
                s = String.format("%.2f", flow);
                v1 = Double.parseDouble(s);
            }
        }else {
            v1=1.0;
        }
        System.out.println(v1);
        return v1;
    }



}
