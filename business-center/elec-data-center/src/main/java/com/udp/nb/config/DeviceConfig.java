package com.udp.nb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.udp.nb.opentsdb.client.HttpClientImpl;

@Configuration
public class DeviceConfig {

    @Bean(value = "openTSDB")
    public HttpClientImpl getClient() {
        return new HttpClientImpl("http://172.16.55.187:4242");
    }
}
