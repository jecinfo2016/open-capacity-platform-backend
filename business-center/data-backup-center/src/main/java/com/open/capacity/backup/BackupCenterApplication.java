package com.open.capacity.backup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DUJIN
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = {"com.open.capacity.backup.dao"})
public class BackupCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackupCenterApplication.class, args);
    }
}
