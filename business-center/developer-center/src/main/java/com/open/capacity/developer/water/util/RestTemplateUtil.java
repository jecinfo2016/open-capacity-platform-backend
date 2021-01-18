package com.open.capacity.developer.water.util;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * rest工具类，get,post请求封装
 */
public class RestTemplateUtil {

    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * get请求
     * 参数放入map中 token也可直接放入map
     */
    public static String getRequest(String url, Map<String, Object> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");//请求头
        HttpEntity<String> formEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET,
                formEntity, String.class, map);
        return exchange.getBody();
    }

    /**
     * post请求
     * 参数放入Map中，不可携带token
     */
    public static String postRequest(String url, Map<String, Object> map) {
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
     * post带token请求
     * token放入jsonStr中
     */
    public static String postWithToken(String url, String jsonStr){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return exchange.getBody();
    }

}
