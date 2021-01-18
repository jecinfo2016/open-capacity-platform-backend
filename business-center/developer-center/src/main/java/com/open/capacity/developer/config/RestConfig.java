package com.open.capacity.developer.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author DUJIN
 * @Classname RestConfig
 * @Date 2020/8/27 9:53
 */
@Configuration
public class RestConfig {
    @Bean(value = "MyRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
