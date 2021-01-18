package com.open.device.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.open.capacity.common.util.DateUtils;
import com.open.capacity.common.util.JsonResult;
import com.open.capacity.common.util.TokenUtil;
import com.open.capacity.common.web.Result;
import com.open.device.model.*;
import com.open.device.service.DeviceAppService;
import com.open.device.service.DeviceMetricService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeviceApiController {

    @Value("${opentsDB.url}")
    private String url;

    @Autowired
    @Qualifier("restTemplateNew")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate loadBalanced;

    @Autowired
    private DeviceMetricService deviceMetricService;


    @Autowired
    private DeviceAppService deviceAppService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/getDeviceInfo")
    public JsonResult getDeviceList() {
        return JsonResult.ok();
    }

    @PostMapping("/api/query")
    public List<ResposeModel> apiQuery(@RequestBody RequestModel requestModel) {
        return requestData(requestModel);
    }

    @GetMapping("/test")
    public void test() {
        String forObject = loadBalanced.getForObject("http://DEVICE-CENTER/getDeviceInfo", String.class);
        String forObject1 = restTemplate.getForObject("http://127.0.0.1:7001/getDeviceInfo", String.class);
        System.out.println(forObject);
        System.out.println(forObject1);
    }

    private List<ResposeModel> requestData(RequestModel requestModel) {
        JSONObject jsonObj = (JSONObject) JSONObject.toJSON(requestModel);
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //请求体
        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj.toString(), headers);
        //发起请求
        String jsonResult = restTemplate.postForObject(url, formEntity, String.class);
        //将Json字符串解析成对象
        jsonResult = "{arr:" + jsonResult + "}";
        JSONObject jsonObject = JSON.parseObject(jsonResult);
        JSONArray arr = jsonObject.getJSONArray("arr");
        List<ResposeModel> respose = JSON.parseObject(arr.toJSONString(), new TypeReference<List<ResposeModel>>() {
        });
        return respose;
    }

    /**
     * 设备上报数据（3天记录）
     */
    @PostMapping("/reportdata")
    public Result reportData(@RequestParam(value = "typeId") Long typeId,
                             @RequestParam(value = "host", defaultValue = "*") String host,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                             @RequestParam(value = "start", defaultValue = "3d-ago") String start) {

        DeviceMetric deviceMetric = deviceMetricService.selectByType(typeId);
        if (null == deviceMetric) {
            return Result.failed("未查询到typeId为" + typeId + "的数据");
        }
        String metric = deviceMetric.getMetric();
        RequestModel requestModel = new RequestModel(start, null, new ArrayList<>());
        Querie querie = new Querie();
        querie.setAggregator("none");
        querie.setMetric(metric);
        querie.setTags(new Tags(host, null,null));
        requestModel.getQueries().add(querie);
        try {
            List<ResposeModel> resposeModels = requestData(requestModel);
            //将集合转换为数组
            List list = getResposeModels(resposeModels);
            List resultList = new ArrayList();
            if (list.size() > limit) {
                //假分页
                for (int i = page * limit - limit; i < page * limit; i++) {
                    resultList.add(list.get(i));
                }
            } else {
                resultList.addAll(list);
            }
            PageInfo pageInfo = new PageInfo(resultList);
            pageInfo.setTotal(list.size());
            pageInfo.setSize(limit);
            return Result.succeed(pageInfo, "请求接口成功");
        } catch (Exception e) {
            return Result.failed("请求接口失败！请核对参数是否正确，msg:" + e.getMessage());
        }
    }

    /**
     * sonarQube bug 修复
     */
    private List getResposeModels(List<ResposeModel> resposeModels){
        List list = new ArrayList();
        for (ResposeModel resposeModel : resposeModels) {
            HashMap<Long, Double> map = resposeModel.getDps();
            if (map != null) {
                int i = 0;
                //对map中time排序
                Map<Long,Double> sortMap = Maps.newLinkedHashMap();
                map.entrySet().stream().sorted(Map.Entry.<Long,Double>comparingByKey().reversed())
                        .forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
                for (Map.Entry<Long, Double> entry : sortMap.entrySet()) {
                    Map result = new HashMap();
                    result.put("time", DateUtils.timeStamp2Date(entry.getKey().toString(), null));
                    result.put("value", entry.getValue());
                    result.put("metric", resposeModel.getMetric());
                    result.put("aggregateTags", resposeModel.getAggregateTags());
                    result.put("tags", resposeModel.getTags());
                    list.add(result);
                    i++;
                    if (i > 9) {
                        break;
                    }
                }
            }
        }
        return list;
    }

    /**
     * opentsdb设备查询接口
     */
    @PostMapping("/opentsdbData")
    public Result opentsdbData(@RequestBody RequestModel querys) {
//        String token = TokenUtil.getToken();
//        OAuth2Authentication authentication = (OAuth2Authentication) redisTemplate.opsForValue().get("auth:"+token);
//        String app = authentication.getPrincipal().toString();
//        boolean authenticated = authentication.isAuthenticated();
        RequestModel requestModel = new RequestModel(querys.getStart(), null, new ArrayList<>());
        List<Querie> queries = querys.getQueries();
//        if (authenticated&&app!=null) {
            for (Querie querie: queries){
                if (StringUtils.isEmpty(querie.getTags().getHost())){
                    return Result.failed("请求接口失败！host不能为空");
                }
                Tags tags = querie.getTags();
                String metric = querie.getMetric();
                querie.setAggregator(querie.getAggregator());
                querie.setMetric(metric);
                querie.setTags(new Tags(tags.getHost(), null, tags.getIndex()));
//                int appsn = deviceAppService.findAppsn(app, tags.getHost());
//                if (tags.getHost() != null && appsn <= 0) {
//                    return Result.failed("没有当前设备的权限!");
//                }
                requestModel.getQueries().add(querie);
            }
//        }
        try{
            List<ResposeModel> resposeModels = requestData(requestModel);
            //将集合转换为数组
            List list = new ArrayList();
            for (ResposeModel resposeModel : resposeModels) {
                HashMap<Long, Double> map = resposeModel.getDps();
                if (map != null) {
                    //对map中time排序
                    Map<Long,Double> sortMap = Maps.newLinkedHashMap();
                    map.entrySet().stream().sorted(Map.Entry.<Long,Double>comparingByKey().reversed())
                            .forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
                    for (Map.Entry<Long, Double> entry : sortMap.entrySet()) {
                        Map result = new HashMap();
                        result.put("time", DateUtils.timeStamp2Date(entry.getKey().toString(), null));
                        result.put("value", entry.getValue());
                        result.put("metric", resposeModel.getMetric());
                        result.put("aggregateTags", resposeModel.getAggregateTags());
                        result.put("tags", resposeModel.getTags());
                        list.add(result);
                    }
                }
            }
            return Result.succeed(list, "请求接口成功");
        } catch (Exception e) {
            return Result.failed("请求接口失败！请核对参数是否正确，msg:" + e.getMessage());
        }
    }
    /**
     * 中兴克拉业务数据上报回调接口
     */
    @PostMapping("/datapush/callback")
    public Result dpCallback(@RequestBody Datapush datapush){

        System.out.println(datapush);
        return Result.succeed("业务数据上报回调接口请求成功");
    }

    /**
     * 中兴克拉告警数据上报回调接口
     */
    @PostMapping("/alarmpush/callback")
    public Result apCallback(@RequestBody Alarmpush alarmpush){

        System.out.println(alarmpush);
        return Result.succeed("告警数据上报回调接口请求成功");
    }

    /**
     * 中兴克拉设备状态上报回调接口
     */
    @PostMapping("/devstatepush/callback")
    public Result dspCallback(@RequestBody Devstatepush devstatepush){

        System.out.println(devstatepush);
        return Result.succeed("告警数据上报回调接口请求成功");
    }
}
