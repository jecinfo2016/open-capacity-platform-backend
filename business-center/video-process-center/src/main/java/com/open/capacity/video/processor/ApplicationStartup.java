package com.open.capacity.video.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 应用启动后, 启动spark流式计算
 *
 * @author donglh
 * @date 2020/7/15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final SparkKafkaStreamExecutor sparkKafkaStreamExecutor;

    @Override
    public void run(String... args) {
        log.warn("++++++++++++ 服务启动....");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(sparkKafkaStreamExecutor);
    }
}
