package com.open.device.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.open.device.dao.DeviceInfoDao;
import com.open.device.model.DeviceInfo;
import com.open.device.model.InfiComboUrl;
import com.open.device.model.InficomboDevtype;
import com.open.device.service.DeviceStateService;
import com.open.device.service.InficomboDevtypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@SuppressWarnings("all")
public class DeviceStatusSchedule {

    private static final String USERNAME = "lyxf";
    private static final String PASSWORD = "lyxf";
    private static final String INFICOMBO_TOKEN = "inficombo_token:";

    @Autowired
    DeviceStateService deviceStateService;

    @Autowired
    InficomboDevtypeService inficomboDevtypeService;

    @Autowired
    DeviceInfoDao deviceInfoDao;

    @Autowired
    @Qualifier("restTemplateNew")
    private RestTemplate restTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 30min执行一次 检查设备是否在线
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void checkOnlineTask() {

        try {
            log.info("执行定时核对设备是否在线任务");
            Map<String, Object> params = new HashMap<>();
            //查询所有设备类型，根据设备类型，设备编码请求，查看是否有数据，如果有数据为在线，无数据为离线
            List<DeviceInfo> all = deviceInfoDao.findAll(params);
            List<Integer> idList = new ArrayList<>();//离线idList
            List<Integer> idOnlineList = new ArrayList<>();//在线idList
            for (DeviceInfo deviceInfo : all) {
                Integer type = deviceInfo.getDevceTypeId();
                String deviceSn = deviceInfo.getDeviceSn();
                log.info("typeId为：" + type + ",host为:" + deviceSn);
                if (null != deviceSn && !deviceSn.equals("") && type != null) {
                    //请求接口判断返回
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Accept", "application/json");//请求头
                    MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
                    postParameters.add("typeId", String.valueOf(type));
                    postParameters.add("host", deviceSn);
                    postParameters.add("start", "1h-ago");
                    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(postParameters, headers);
                    String s = restTemplate.postForObject("http://61.164.218.155:39200/api-device/reportdata", requestEntity, String.class);
                    log.info("请求返回结果是：" + s);
                    JSONObject jsonObject = JSON.parseObject(s);
                    JSONObject datas = (JSONObject) jsonObject.get("datas");
                    Integer state = null;
                    if (null != datas && null != datas.get("total") && datas.getInteger("total") > 0) {
                        idOnlineList.add(deviceInfo.getId());
                    } else {
                        idList.add(deviceInfo.getId());
                    }
                }
            }
            //批量修改在线
            deviceInfoDao.modifyOnline(idOnlineList);
            //批量修改离线
            deviceInfoDao.modifyOffline(idList);
            log.info("修改设备离线:" + idList);
        } catch (RestClientException e) {
            log.info("执行定时任务失败！");
        }
    }

    /**
     * 请求接口 维护DevType数据
     */
    @Scheduled(cron = "0 */100 * * * ?")
    public void checkDevTypes() {
        //判断表中是否含有DevTypes 没有即修改
        log.info("执行定时设备类型任务");
        //先请求获取token
        Map<String, Object> map = new HashMap<>();
        map.put("username", "lyxf");
        map.put("pswd", "lyxf");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");//请求头
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        if (map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                postParameters.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParameters, headers);
        String postStr = restTemplate.postForObject("http://61.164.218.155:39200/api-device/infiCombo/get/token", requestEntity, String.class);
//        String postStr = restTemplate.postForObject("http://127.0.0.1:9200/api-device/infiCombo/get/token", requestEntity, String.class);
        log.info("登录请求返回结果是：" + postStr);
        //再调用设备类型查询
        HttpEntity<String> formEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://61.164.218.155:39200/api-device/infiCombo/dev/devtypes", HttpMethod.GET, formEntity, String.class, new HashMap<>());
//        ResponseEntity<String> exchange = restTemplate.exchange("http://127.0.0.1:9200/api-device/infiCombo/dev/devtypes", HttpMethod.GET, formEntity, String.class, new HashMap<>());
        String devStr = exchange.getBody();
        log.info("查询设备类型返回结果是：" + devStr);
        JSONObject jsonObject = JSON.parseObject(devStr);
        JSONArray datas = (JSONArray) jsonObject.get("datas");
        if (datas.size() > 0) {
            for (Object data : datas) {
                JSONObject dev = (JSONObject) data;
                //先查询是否已经存在
                String devtype = dev.getString("devtype");
                InficomboDevtype devtypeByType = inficomboDevtypeService.getByType(devtype);
                log.info("中兴克拉设备类型：" + devtype);
                if (null == devtypeByType) {
                    InficomboDevtype add = new InficomboDevtype(dev.getString("devtype"), dev.getString("devtypedesc"), dev.getString("vendor"), dev.getString("appclass"), dev.getInteger("devtypeflag"));
                    inficomboDevtypeService.save(add);
                    log.info("新增设备类型成功");
                }
            }
        } else {
            log.info("未请求到数据！");
        }
    }

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
     * 每12小时 定时刷新token
     */
    @Scheduled(cron = "0 0 0/11 * * ? ")
    public void refreshToken() {

        Map<String, Object> map = new HashMap<>();
        map.put("username", USERNAME);
        map.put("pswd", PASSWORD);
        String request = postRequest(InfiComboUrl.smLoginUrl, map);
        //设置token过期时间11分钟
        JSONObject jsonObject = JSONObject.parseObject(request);
        if (jsonObject.getString("result").equals("0")) {
            JSONObject data = (JSONObject) jsonObject.get("data");
            String token = data.getString("token");
            redisTemplate.opsForValue().set(INFICOMBO_TOKEN, token);
            redisTemplate.expire(INFICOMBO_TOKEN, 11, TimeUnit.HOURS);
        }
    }

}
