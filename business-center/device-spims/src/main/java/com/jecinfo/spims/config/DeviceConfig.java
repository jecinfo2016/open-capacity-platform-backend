package com.jecinfo.spims.config;

import com.jecinfo.spims.opentsdb.HttpClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    @Bean(value = "openTSDB")
    public HttpClientImpl getClient() {
        return new HttpClientImpl("http://172.16.55.187:4242");
    }
}
