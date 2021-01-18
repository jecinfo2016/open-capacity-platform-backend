package com.open.capacity.video.collector;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.video.constant.TaskConstant;
import com.open.capacity.video.entity.Task;
import com.open.capacity.video.entity.VideoFrameData;
import com.open.capacity.video.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 * 视频抽帧采集
 *
 * @author donglh
 * @date 2020/7/14
 */
@Slf4j
public class VideoEventGenerator implements Runnable {

    // 加载库
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 任务
     **/
    private Task task;
    /**
     * kafka 主题
     */
    private String topic;
    /**
     * 保存文件地址
     */
    private String filePath;
    /**
     * 任务service
     */
    private TaskService taskService;


    /**
     * kafka
     */
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 构造函数
     *
     * @param task          任务
     * @param topic         保存到kafka的topic名字
     * @param filePath      暂存文件路径
     * @param taskService   任务service
     * @param kafkaTemplate kafka template
     */
    public VideoEventGenerator(Task task, String topic, String filePath,
                               TaskService taskService, KafkaTemplate<String, String> kafkaTemplate) {
        this.topic = topic;
        this.task = task;
        this.filePath = filePath;
        this.taskService = taskService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run() {
        try {
            generateEvent();
        } catch (Exception e) {
            log.error("视频流读取失败!", e);
            // 更新任务状态
            task.setStatus(TaskConstant.TASK_STATUS_FAIL);
            task.setFinishedTime(LocalDateTime.now());
            task.setDuration(Duration.between(task.getFinishedTime(), task.getStartTime()).toMillis());
            taskService.updateOne(task);
        } finally {
            // 删除文件
            FileUtils.deleteQuietly(new File(filePath));
        }
    }

    /**
     * 打开视频流并处理
     *
     * @throws Exception 抛出异常
     */
    private void generateEvent() throws Exception {
        VideoCapture camera = new VideoCapture();
        camera.open(filePath);
        if (!camera.isOpened()) {
            Thread.sleep(5000);
            if (!camera.isOpened()) {
                throw new RuntimeException("打开视频文件失败...");
            }
        }

        Mat mat = new Mat();
        int cnt = 0;
        int cnt2 = -1;
        // 获取fps
        int fps = (int) camera.get(Videoio.CAP_PROP_FPS);
        long start = System.currentTimeMillis();

        log.warn("当前视频的fps为 ====> {}", fps);

        while (camera.read(mat)) {
            cnt2++;
            //  每秒只取一帧
            if (cnt2 % fps != 0) {
                continue;
            }
            // 统一将所有的分辨率变为640 * 480
            // Imgproc.resize(mat, mat, new Size(640, 480), 0, 0, Imgproc.INTER_CUBIC);
            int cols = mat.cols();
            int rows = mat.rows();
            int type = mat.type();
            byte[] data = new byte[(int) (mat.total() * mat.channels())];
            mat.get(0, 0, data);
            double millSec = camera.get(0);
            VideoFrameData frameData = VideoFrameData.builder()
                    .taskId(task.getTaskId())
                    .millSec(millSec)
                    .rows(rows)
                    .cols(cols)
                    .type(type)
                    .data(Base64.getEncoder().encodeToString(data))
                    .build();
            String json = JSONObject.toJSONString(frameData);
            kafkaTemplate.send(topic, task.getTaskId(), json);
            cnt++;
        }
        long end = System.currentTimeMillis();

        log.warn("frame count ==== {}, 花费时间 ==> {}", cnt, (end - start) / 1000);
        camera.release();
        mat.release();

        log.warn("task id {} process complete", task.getTaskId());

        // 更新读取的帧数, 转入处理阶段
        task.setStatus(TaskConstant.TASK_STATUS_PROCESS);
        task.setCnt(cnt);
        taskService.updateOne(task);
    }

}
