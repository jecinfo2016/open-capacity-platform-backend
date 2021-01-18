package com.open.capacity.developer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@MapperScan(value = {"com.open.capacity.developer.device.dao","com.open.capacity.developer.dma.dao","com.open.capacity.developer.water.dao"})
@EnableAsync
public class DeveloperCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeveloperCenterApplication.class, args);
    }
}
