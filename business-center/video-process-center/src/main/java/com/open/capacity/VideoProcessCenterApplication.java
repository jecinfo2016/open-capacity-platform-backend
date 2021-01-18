package com.open.capacity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author donglh
 */
@SpringBootApplication
public class VideoProcessCenterApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(VideoProcessCenterApplication.class);
        springApplication.run(args);

    }

}
