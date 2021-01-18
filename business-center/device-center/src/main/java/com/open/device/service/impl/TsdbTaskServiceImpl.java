package com.open.device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.open.device.dao.TsdbDeviceDao;
import com.open.device.model.RequestModel;
import com.open.device.model.ResposeModel;
import com.open.device.model.TaskOutput;
import com.open.device.service.TsdbTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author DUJIN
 */
@Service
@Slf4j
public class TsdbTaskServiceImpl implements TsdbTaskService {

    @Value("${opentsDB.url}")
    private String queryUrl;

    @Autowired
    @Qualifier("restTemplateNew")
    private RestTemplate restTemplate;

    @Autowired
    TsdbDeviceDao tsdbDeviceDao;

    @Override
    public List<ResposeModel> analysisTsdbInfo(RequestModel requestModel) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        // 请求体
        HttpEntity<String> formEntity = new HttpEntity<>(JSON.toJSONString(requestModel), headers);
        // 发起请求
        String jsonResult = null;
        try {
            jsonResult = restTemplate.postForObject(queryUrl, formEntity, String.class);
            log.info("查询结果：{}", jsonResult);
        } catch (RestClientException e) {
            log.info("调用opentsdb查询接口异常~");
            log.error(e.getMessage());
        }
        return JSON.parseObject(jsonResult, new TypeReference<List<ResposeModel>>() {
        });
    }

    @Override
    public int saveTaskOutputInfo(List<TaskOutput> list) {
        return tsdbDeviceDao.saveTaskOutputInfo(list);
    }

    @Override
    public Long queryEndTimestamp(Integer taskId) {
        return tsdbDeviceDao.queryEndTimestamp(taskId);
    }
}
