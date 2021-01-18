package com.open.capacity.video.service.impl;

import com.open.capacity.video.collector.VideoEventGenerator;
import com.open.capacity.video.constant.TaskConstant;
import com.open.capacity.video.entity.Task;
import com.open.capacity.video.service.CollectorService;
import com.open.capacity.video.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author donglh
 * @date 2020/7/14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {

    private final TaskService taskService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${file.path}")
    private String path;
    @Value("${spring.kafka.topic}")
    private String topic;

    @Override
    public String videoCollector(MultipartFile file) throws ServerException {

        // 生成TASK_ID
        String taskId = UUID.randomUUID().toString().replace("-", "");
        log.warn("++++++ uuid=> {}", taskId);
        try {
            // 首先将文件暂存
            String filename = path + "/" + taskId + ".mp4";
            File saveFile = new File(filename);
            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);

            // 记录任务
            Task task = Task.builder()
                    .taskId(taskId)
                    .startTime(LocalDateTime.now())
                    .status(TaskConstant.TASK_STATUS_COLLECTOR)
                    .type(TaskConstant.TASK_TYPE_VIDEO)
                    .cnt(0)
                    .build();
            // 保存任务
            taskService.saveOne(task);

            // 启动线程处理
            ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            singleThreadExecutor.execute(new VideoEventGenerator(task, topic, filename, taskService, kafkaTemplate));

        } catch (IOException e) {
            log.error("open file input stream fail", e);
            throw new ServerException("open file input stream fail");
        }

        return taskId;

    }
}
