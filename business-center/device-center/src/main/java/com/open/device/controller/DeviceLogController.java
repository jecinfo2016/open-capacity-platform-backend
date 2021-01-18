package com.open.device.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/DeviceLog")
@Api(tags = "设备任务日志")
public class DeviceLogController {

    @Autowired
    @Qualifier("restTemplateNew")
    private RestTemplate restTemplate;

    @Value("${xxl.job.admin.addresses}")
    public String jobCenterAddress;

    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        int jobId, int logStatus, String filterTime) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("jobGroup",1);
        map.add("start",start);
        map.add("length",length);
        map.add("jobId",jobId);
        map.add("logStatus",logStatus);
        //"2020-08-21 00:00:00 - 2020-08-21 23:59:59"
        map.add("filterTime",filterTime);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("XXL-JOB-ACCESS-TOKEN", "c2cfea94-2f32-4c2f-b3bf-e96fd5afa66b");
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
        Map<String, Object> maps = restTemplate.postForObject(jobCenterAddress + "/joblog/pageList", request, Map.class);
        return maps;
    }
}
