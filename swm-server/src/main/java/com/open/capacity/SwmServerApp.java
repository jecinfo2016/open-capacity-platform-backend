package com.open.capacity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@EnableSidecar
@SpringBootApplication
public class SwmServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SwmServerApp.class, args);
    }
}
