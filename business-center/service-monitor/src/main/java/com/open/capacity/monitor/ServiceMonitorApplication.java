package com.open.capacity.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author DUJIN
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.open.capacity.monitor.dao"})
@EnableDiscoveryClient
public class ServiceMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMonitorApplication.class, args);
    }
}
