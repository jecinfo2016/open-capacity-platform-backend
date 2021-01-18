package com.jecinfo.spims;

import com.jecinfo.spims.accept.DelongServerSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpimsApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpimsApplication.class, args);
        context.getBean(DelongServerSocket.class).start();
    }

}
