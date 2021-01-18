package com.open.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.common.web.Result;
import com.open.device.model.Devcontrol;
import com.open.device.model.InfiComboUrl;
import com.open.device.opentsdb.ExpectResponse;
import com.open.device.opentsdb.HttpClientImpl;
import com.open.device.opentsdb.builder.MetricBuilder;
import com.open.device.opentsdb.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("infiCombo")
public class InfiComboApiController {

    private static final String TOKEN_URL = "?token={token}";
    private static final String RESULT = "result";
    private static final String INFICOMBO_TOKEN = "inficombo_token:";
    private static final String TOKEN = "token";
    private static final String DEVTYPE = "devtype";
    private static final String PAGEINDEX = "pageindex";
    private static final String ADDURL = "?token={token}&devtype={devtype}";
    private static final String BEGINTIME = "begintime";
    private static final String ENDTIME = "endtime";
    private static final String DEVUIS = "deveuis";
    private static final String DEVEUIS_URL = "?token={token}&deveuis={deveuis}";
    private static final String TOKENMSG = "token为空！";
    private static final String DEST_URL = "desturl";

    static Logger logger = LoggerFactory.getLogger(InfiComboApiController.class);

    @Value("${opentsDB.saveUrl}")
    private String url;

    @Value("${infiCombo.username}")
    private String username;
    @Value("${infiCombo.password}")
    private String password;

    @Autowired
    @Qualifier("restTemplateNew")
    private RestTemplate restTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * post请求
     */
    private String postRequest(String url, Map<String, Object> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");//请求头
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        if (map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                postParameters.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParameters, headers);
        return restTemplate.postForObject(url, requestEntity, String.class);
    }

    /**
     * post 请求带token
     */
    private String post(String url, String jsonStr) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonStr, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return exchange.getBody();
    }

    /**
     * delete请求
     */
    private String delete(String url, Map<String, Object> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> formEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.DELETE, formEntity, String.class, map);
        return exchange.getBody();
    }


    /**
     * get请求
     */
    private String getRequest(String url, Map<String, Object> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");//请求头
        HttpEntity<String> formEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET,
                formEntity, String.class, map);
        return exchange.getBody();
    }

    /**
     * 封装返回
     */
    private Result returnResult(String request) {

        JSONObject jsonObject = JSONObject.parseObject(request);
        if (jsonObject.getString(RESULT).equals("0")) {
            return Result.succeed(jsonObject.get("data"), jsonObject.getString("remark"));
        } else {
            return Result.failed(jsonObject.getString("remark"));
        }
    }

    /**
     * 从redis获取token
     */
    private String getTokenFromRedis() {

        Object o = redisTemplate.opsForValue().get(INFICOMBO_TOKEN);
        String token = null;
        if (null != o) {
            token = (String) o;
        }
        return token;
    }

    /**
     * 用户认证接口
     */
    @PostMapping("/get/token")
    public Result getToken(@RequestParam String username,
                           @RequestParam String pswd) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("pswd", pswd);
        String request = postRequest(InfiComboUrl.smLoginUrl, map);
        //设置token过期时间11小时
        JSONObject jsonObject = JSONObject.parseObject(request);
        if (jsonObject.getString(RESULT).equals("0")) {
            JSONObject data = (JSONObject) jsonObject.get("data");
            String token = data.getString(TOKEN);
            redisTemplate.opsForValue().set(INFICOMBO_TOKEN, token);
            redisTemplate.expire(INFICOMBO_TOKEN, 11, TimeUnit.HOURS);
        }
        return returnResult(request);
    }

    /**
     * 获取token封装
     */
    private void getToken() {
        if (null == getTokenFromRedis()) {

            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            map.put("pswd", password);
            String request = postRequest(InfiComboUrl.smLoginUrl, map);
            JSONObject jsonObject = JSONObject.parseObject(request);
            if (jsonObject.getString(RESULT).equals("0")) {
                JSONObject data = (JSONObject) jsonObject.get("data");
                String token = data.getString(TOKEN);
                redisTemplate.opsForValue().set(INFICOMBO_TOKEN, token);
                redisTemplate.expire(INFICOMBO_TOKEN, 11, TimeUnit.HOURS);
            }
        }
    }

    /**
     * 设备类型查询
     */
    @GetMapping("/dev/devtypes")
    public Result getDevtypes() {

        getToken();
        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        String request = getRequest(InfiComboUrl.devtypesUrl + TOKEN_URL, map);
        return returnResult(request);
    }

    /**
     * 实配类型查询
     */
    @GetMapping("/dev/projectdevtypes")
    public Result getProjectdevtypes() {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        String request = getRequest(InfiComboUrl.projectdevtypesUrl + TOKEN_URL, map);
        return returnResult(request);
    }

    /**
     * 设备列表查询
     */
    @GetMapping("/dev/devs")
    public Result getDevs(@RequestParam(required = false) String devtype,
                          @RequestParam(defaultValue = "1") Integer pageindex,
                          @RequestParam(defaultValue = "100") Integer batch) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVTYPE, devtype);
        map.put(PAGEINDEX, pageindex);
        map.put("batch", batch);
        String request = getRequest(InfiComboUrl.devsUrl + "?token={token}&devtype={devtype}&pageindex={pageindex}&batch={batch}", map);
        return returnResult(request);
    }

    /**
     * 数据模型查询
     */
    @GetMapping("/model/datamodel")
    public Result getDatamodel(@RequestParam(required = false) String devtype) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVTYPE, devtype);
        String request = getRequest(InfiComboUrl.datamodelUrl + ADDURL, map);
        return returnResult(request);
    }

    /**
     * 指令模型查询
     */
    @GetMapping("/model/controlmodel")
    public Result getControlmodel(@RequestParam(required = false) String devtype) {

        getToken();
        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVTYPE, devtype);
        String request = getRequest(InfiComboUrl.controlmodelUrl + ADDURL, map);
        return returnResult(request);
    }

    /**
     * 告警模型查询
     */
    @GetMapping("/model/alarmmodel")
    public Result getAlarmmodel(@RequestParam(required = false) String devtype) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVTYPE, devtype);
        String request = getRequest(InfiComboUrl.alarmmodelUrl + ADDURL, map);
        return returnResult(request);
    }

    /**
     * 业务数据查询
     */
    @GetMapping("/data/devdata")
    public Result getDevdata(@RequestParam String deveui,
                             @RequestParam String begintime,
                             @RequestParam String endtime) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put("deveui", deveui);
        map.put(BEGINTIME, begintime);
        map.put(ENDTIME, endtime);
        String request = getRequest(InfiComboUrl.devDataUrl + "?token={token}&deveui={deveui}&begintime={begintime}&endtime={endtime}", map);
        return returnResult(request);
    }

    /**
     * 业务数据批量查询
     */
    @GetMapping("/data/batchdevdata")
    public Result getBatchdevdata(@RequestParam String deveuis,
                                  @RequestParam String begintime,
                                  @RequestParam String endtime,
                                  @RequestParam(defaultValue = "1") String pageindex) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVUIS, deveuis);
        map.put(BEGINTIME, begintime);
        map.put(ENDTIME, endtime);
        map.put(PAGEINDEX, pageindex);
        String request = getRequest(InfiComboUrl.batchdevdataUrl + "?token={token}&deveuis={deveuis}&begintime={begintime}&endtime={endtime}&pageindex={pageindex}", map);
        return returnResult(request);
    }

    /**
     * 最新业务数据查询
     */
    @GetMapping("/data/latestdevdata")
    public Result getLatestdevdata(@RequestParam String deveuis) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVUIS, deveuis);
        String request = getRequest(InfiComboUrl.latestdevdataUrl + DEVEUIS_URL, map);
        return returnResult(request);
    }

    /**
     * 设备运行状态查询
     */
    @GetMapping("/data/devstatedata")
    public Result getDevstatedata(@RequestParam String deveuis) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVUIS, deveuis);
        String request = getRequest(InfiComboUrl.devstatedataUrl + DEVEUIS_URL, map);
        return returnResult(request);
    }

    /**
     * 设备当前告警查询
     */
    @GetMapping("/alarm/devcuralarm")
    public Result getDevcuralarm(@RequestParam String deveuis) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVUIS, deveuis);
        String request = getRequest(InfiComboUrl.devcuralarmUrl + DEVEUIS_URL, map);
        return returnResult(request);
    }

    /**
     * 最新告警列表查询
     */
    @GetMapping("/alarm/getlatestalarms")
    public Result getlatestalarms(@RequestParam String maxnum) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put("maxnum", maxnum);
        String request = getRequest(InfiComboUrl.getlatestalarmsUrl + "?token={token}&maxnum={maxnum}", map);
        return returnResult(request);
    }

    /**
     * 设备历史告警查询
     */
    @GetMapping("/alarm/devhisalarm")
    public Result getDevhisalarm(@RequestParam String deveui,
                                 @RequestParam String begintime,
                                 @RequestParam String endtime,
                                 @RequestParam(defaultValue = "1") String pageindex) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put("deveui", deveui);
        map.put(BEGINTIME, begintime);
        map.put(ENDTIME, endtime);
        map.put(PAGEINDEX, pageindex);
        String request = getRequest(InfiComboUrl.devhisalarmUrl + "?token={token}&deveui={deveui}&begintime={begintime}&endtime={endtime}&pageindex={pageindex}", map);
        return returnResult(request);
    }

    /**
     * 历史告警批量查询
     */
    @GetMapping("/alarm/hisalarmlist")
    public Result getHisalarmlist(@RequestParam String begintime,
                                  @RequestParam String endtime,
                                  @RequestParam(defaultValue = "1") String pageindex) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(BEGINTIME, begintime);
        map.put(ENDTIME, endtime);
        map.put(PAGEINDEX, pageindex);
        String request = getRequest(InfiComboUrl.hisalarmlistUrl + "?token={token}&begintime={begintime}&endtime={endtime}&pageindex={pageindex}", map);
        return returnResult(request);
    }

    /**
     * 设备控制指令下发
     */
    @PostMapping("/devcontrol/cmd")
    public Result devcontrolCmd(@RequestBody Devcontrol devcontrol) {

        if (null == getTokenFromRedis()) {
            return Result.failed(TOKENMSG);
        }
        devcontrol.setToken(getTokenFromRedis());
        String request = post(InfiComboUrl.cmdUrl, JSONObject.toJSONString(devcontrol));
        return returnResult(request);
    }

    /**
     * 设备组播指令下发
     */
    @PostMapping("/devcontrol/broadcastcmd")
    public Result broadcastcmd(@RequestBody Devcontrol devcontrol) {
        if (null == getTokenFromRedis()) {
            return Result.failed(TOKENMSG);
        }
        devcontrol.setToken(getTokenFromRedis());
        String request = post(InfiComboUrl.broadcastcmdUrl, JSONObject.toJSONString(devcontrol));
        return returnResult(request);
    }

    /**
     * 上报接口注册
     */
    @PostMapping("/datapush/registe")
    public Result dataRegiste(@RequestBody Devcontrol devcontrol) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVTYPE, devcontrol.getDevtype());
        map.put(DEST_URL, devcontrol.getDesturl());
        String request = postRequest(InfiComboUrl.dataRegisteUrl, map);
        return returnResult(request);
    }

    /**
     * 上报接口注销
     */
    @DeleteMapping("/datapush/unregiste")
    public Result dataUnregiste(@RequestBody Devcontrol devcontrol) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEVTYPE, devcontrol.getDevtype());
        map.put(DEST_URL, devcontrol.getDesturl());
        if (null == getTokenFromRedis()) {
            return Result.failed(TOKENMSG);
        }
        String request = delete(InfiComboUrl.dataUnregisteUrl + "?token={token}&devtype={devtype}&desturl={desturl}", map);
        return returnResult(request);
    }

    /**
     * 上报接口注册查询
     */
    @GetMapping("/datapush/registelist")
    public Result getDataRegistelist() {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        String request = getRequest(InfiComboUrl.dataRegistelistUrl + TOKEN_URL, map);
        return returnResult(request);
    }

    /**
     * 上报接口注册(告警数据)
     */
    @PostMapping("/alarmpush/registe")
    public Result alarmRegiste(@RequestBody Devcontrol devcontrol) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEST_URL, devcontrol.getDesturl());
        String request = postRequest(InfiComboUrl.alarmRegisteUrl, map);
        return returnResult(request);
    }

    /**
     * 上报接口注销
     */
    @DeleteMapping("/alarmpush/unregiste")
    public Result alarmUnregiste(@RequestBody Devcontrol devcontrol) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEST_URL, devcontrol.getDesturl());
        if (null == getTokenFromRedis()) {
            return Result.failed(TOKENMSG);
        }
        String request = delete(InfiComboUrl.alarmUnregisteUrl + "?token={token}&desturl={desturl}", map);
        return returnResult(request);
    }

    /**
     * 上报接口注册查询
     */
    @GetMapping("/alarmpush/registelist")
    public Result alarmRegistelist() {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        String request = getRequest(InfiComboUrl.alarmRegistelistUrl + TOKEN_URL, map);
        return returnResult(request);
    }

    /**
     * 上报接口注册(设备状态)
     */
    @PostMapping("/devstatepush/registe")
    public Result devRegiste(@RequestBody Devcontrol devcontrol) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEST_URL, devcontrol.getDesturl());
        String request = postRequest(InfiComboUrl.devRegisteUrl, map);
        return returnResult(request);
    }

    /**
     * 上报接口注销
     */
    @DeleteMapping("/devstatepush/unregiste")
    public Result devUnregiste(@RequestBody Devcontrol devcontrol) {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        map.put(DEST_URL, devcontrol.getDesturl());
        if (null == getTokenFromRedis()) {
            return Result.failed(TOKENMSG);
        }
        String request = delete(InfiComboUrl.devUnregisteUrl + "?token={token}&desturl={desturl}", map);
        return returnResult(request);
    }

    /**
     * 上报接口注册查询
     */
    @GetMapping("/devstatepush/registelist")
    public Result devRegistelist() {

        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getTokenFromRedis());
        String request = getRequest(InfiComboUrl.devRegistelistUrl + TOKEN_URL, map);
        return returnResult(request);
    }

    /**
     * 直真科技-接受iot数据接口
     */
    @PostMapping("/zzkj/iot")
    public Result receiveIOT(@RequestBody JSONObject jsonObject) {
        MetricBuilder builder = MetricBuilder.getInstance();
        String datatype = (String) jsonObject.get("datatype");
        if ("air.env".equals(datatype)) {
            logger.info("大气数据");
            List datarows = (List) jsonObject.get("datarows");
            long time = 0;
            String sn = "";
            Map<String, Double> map1 = new HashMap<>();
            for (Object a: datarows){
                LinkedHashMap map = (LinkedHashMap) a;
                Integer aqi = (Integer) map.get("aqi");
                time  = (Integer) map.get("time");
                sn = (String) map.get("sn");
                double pm10 = (double) map.get("pm10");
                double pm25 = (double) map.get("pm25");
                double o3 = (double) map.get("o3");
                double co = (double) map.get("co");
                double no2 = (double) map.get("no2");
                double so2 = (double) map.get("so2");
                double vocs = (double) map.get("vocs");
                double ch4 = (double) map.get("ch4");
                double nmhc = (double) map.get("nmhc");
                double nh3 = (double) map.get("nh3");
                double sulfide = (double) map.get("sulfide");
                double btex = (double) map.get("btex");
                map1.put("pm10", pm10);
                map1.put("pm25", pm25);
                map1.put("o3", o3);
                map1.put("so2", so2);
                map1.put("no2", no2);
                map1.put("co", co);
                map1.put("vocs", vocs);
                map1.put("ch4", ch4);
                map1.put("nmhc", nmhc);
                map1.put("nh3", nh3);
                map1.put("sulfide", sulfide);
                map1.put("btex", btex);
                double aqi1 = aqi;
                map.put("aqi", aqi1);
            }
            System.out.println(map1);

            addMetric(builder, datatype, time, sn, map1);
        }
        return Result.succeed("接收成功！");
    }

    private void addMetric(MetricBuilder builder, String datatype,long time, String sn, Map<String, Double> data_value) {
        HttpClientImpl client = new HttpClientImpl(url);
        Iterator<Map.Entry<String, Double>> it = data_value.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            builder.addMetric(datatype).setDataPoint(time, entry.getValue())
                    .addTag("host", sn).addTag("index", entry.getKey());
        }
        try {
            //存入opentsdb
            Response response = client.pushMetrics(builder,
                    ExpectResponse.SUMMARY);
            logger.info("大气设备"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
