package com.open.device;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@ComponentScan(basePackages = {"com.open.capacity.common","com.open.device","com.open.capacity.uaa.client"})
@MapperScan(value = {"com.open.capacity.uaa.client.dao","com.open.device.dao"})
@EnableTransactionManagement
public class DeviceCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeviceCenterApplication.class,args);
    }
}
