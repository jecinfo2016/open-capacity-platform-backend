package com.open.device.opentsdb;

import com.alibaba.fastjson.JSONArray;
import com.open.device.opentsdb.builder.MetricBuilder;
import com.open.device.opentsdb.response.Response;
import com.open.device.opentsdb.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Opentsdb读写工具类
 */
public class OpentsdbClient {
    private static Logger log = LoggerFactory.getLogger(OpentsdbClient.class);
    /**
     * 取平均值的聚合器
     */
    public static String AGGREGATOR_AVG = "avg";
    /**
     * 取累加值的聚合器
     */
    public static String AGGREGATOR_SUM = "sum";
    private HttpClient httpClient;

    public OpentsdbClient(String opentsdbUrl) {
        this.httpClient = new HttpClientImpl(opentsdbUrl);
    }

    /**
     * 写入数据
     * @param metric 指标
     * @param timestamp 时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, Date timestamp, long value, Map tagMap) throws Exception {
        long timsSecs = timestamp.getTime() / 1000;
        return this.putData(metric, timsSecs, value, tagMap);
    }

    /**
     * 写入数据
     * @param metric 指标
     * @param timestamp 时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, Date timestamp, double value, Map tagMap) throws Exception {
        long timsSecs = timestamp.getTime() / 1000;
        return this.putData(metric, timsSecs, value, tagMap);
    }

    /**
     * 写入数据
     * @param metric 指标
     * @param timestamp 转化为秒的时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, long timestamp, long value, Map tagMap) throws Exception {
        MetricBuilder builder = MetricBuilder.getInstance();
        builder.addMetric(metric).setDataPoint(timestamp, value).addTags(tagMap);
        try {
            log.debug("write quest：{}", builder.build());
            Response response = httpClient.pushMetrics(builder, ExpectResponse.SUMMARY);
            log.debug("response.statusCode: {}", response.getStatusCode());
            return response.isSuccess();
        } catch (Exception e) {
            log.error("put data to opentsdb error: ", e);
            throw e;
        }
    }

    /**
     * 写入数据
     * @param metric 指标
     * @param timestamp 转化为秒的时间点
     * @param value
     * @param tagMap
     * @return
     * @throws Exception
     */
    public boolean putData(String metric, long timestamp, double value, Map tagMap) throws Exception {
        MetricBuilder builder = MetricBuilder.getInstance();
        builder.addMetric(metric).setDataPoint(timestamp, value).addTags(tagMap);
        try {
            log.debug("write quest：{}", builder.build());
            Response response = httpClient.pushMetrics(builder, ExpectResponse.SUMMARY);
            log.debug("response.statusCode: {}", response.getStatusCode());
            return response.isSuccess();
        } catch (Exception e) {
            log.error("put data to opentsdb error: ", e);
            throw e;
        }
    }

    /**
     * 批量写入数据
     * @return
     * @throws Exception
     */
    public boolean putData(JSONArray jsonArr) throws Exception {
        return this.putDataBatch(jsonArr);
    }

    /**
     * 批量写入数据
     * @return
     * @throws Exception
     */
    public boolean putDataBatch(JSONArray jsonArr) throws Exception {
        MetricBuilder builder = MetricBuilder.getInstance();
        try {
            for(int i = 0; i < jsonArr.size(); i++){
                Map tagMap = new HashMap();
                for(String key : jsonArr.getJSONObject(i).getJSONObject("tags").keySet()){
                    tagMap.put(key, jsonArr.getJSONObject(i).getJSONObject("tags").get(key));
                }
                String metric = jsonArr.getJSONObject(i).getString("metric").toString();
                long timestamp = DateTimeUtil.parse(jsonArr.getJSONObject(i).getString("timestamp"), "yyyy/MM/dd HH:mm:ss").getTime() / 1000;
                double value = Double.valueOf(jsonArr.getJSONObject(i).getString("value"));
                builder.addMetric(metric).setDataPoint(timestamp, value).addTags(tagMap);
            }

            log.debug("write quest：{}", builder.build());
            Response response = httpClient.pushMetrics(builder, ExpectResponse.SUMMARY);
            log.debug("response.statusCode: {}", response.getStatusCode());
            return response.isSuccess();
        } catch (Exception e) {
            log.error("put data to opentsdb error: ", e);
            throw e;
        }
    }
}
