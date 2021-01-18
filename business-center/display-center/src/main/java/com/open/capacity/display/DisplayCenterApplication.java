package com.open.capacity.display;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DUJIN
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@MapperScan(value = {"com.open.capacity.display.dao"})
public class DisplayCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DisplayCenterApplication.class, args);
    }
}
