package com.open.capacity.video.processor;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.video.constant.TaskConstant;
import com.open.capacity.video.entity.Pedestrian;
import com.open.capacity.video.entity.RecognizeResult;
import com.open.capacity.video.entity.Task;
import com.open.capacity.video.entity.VideoFrameData;
import com.open.capacity.video.utils.MatUtils;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

import java.io.Serializable;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 行人检测实现类, 可使用其他算法替代
 *
 * @author donglh
 * @date 2020/7/15
 */
@Slf4j
public class PedestrianDetector implements Serializable {
    private static Connection con;

    /*
     * 加载 opencv lib 数据库连接
     *
     * 由于spark中无法使用mybatis直接调用数据库, 因此这里使用原始的JDBC连接方式
     */
    static {
        // 加载opencv 动态库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 连接数据库
        try {
            // TODO 不要写死
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://61.164.218.155:62306/video-process?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai";
            con = DriverManager.getConnection(url, "root", "qazpl,123!");
        } catch (ClassNotFoundException | SQLException e) {
            log.error("数据库配置错误", e);
        }
    }

    /**
     * 行人检测 实现
     *
     * @param key                        task id
     * @param frames                     一系列帧
     * @param previousProcessedEventData 前一次处理的帧
     * @return 该序列处理的最后一帧
     */
    public static VideoFrameData pedestrian(String key,
                                            Iterator<VideoFrameData> frames,
                                            VideoFrameData previousProcessedEventData) {
        // 当期处理的数据帧
        VideoFrameData currentProcessedEventData = new VideoFrameData();
        // 帧
        Mat frame;
        // 拷贝帧
        Mat copyFrame;
        // 灰度化
        Mat grayFrame;
        Mat firstFrame = null;
        Mat deltaFrame = new Mat();
        Mat thresholdFrame = new Mat();
        List<Rect> rectArray;

        HOGDescriptor hog = new HOGDescriptor();
        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());

        if (previousProcessedEventData != null) {
            Mat preFrame = MatUtils.getMat(previousProcessedEventData);
            Mat preGrayFrame = new Mat(preFrame.size(), CvType.CV_8UC1);
            Imgproc.cvtColor(preFrame, preGrayFrame, Imgproc.COLOR_BGR2GRAY);
            Imgproc.GaussianBlur(preGrayFrame, preGrayFrame, new Size(3, 3), 0);
            firstFrame = preGrayFrame;
        }
        // 将该一组帧按照时间进行排序
        ArrayList<VideoFrameData> sortedList = new ArrayList<>();
        while (frames.hasNext()) {
            sortedList.add(frames.next());
        }
        sortedList.sort(Comparator.comparing(VideoFrameData::getMillSec));
        log.warn("task id ++++++++++++ {}, 帧数 ==> {}", key, sortedList.size());
        Statement stat = null;
        try {
            stat = con.createStatement();
            // todo 设置为事务提交
        } catch (SQLException e) {
            log.error("open statement error ", e);
            return null;
        }

        // 迭代处理
        for (VideoFrameData eventData : sortedList) {
            frame = MatUtils.getMat(eventData);
            copyFrame = frame.clone();
            grayFrame = new Mat(frame.size(), CvType.CV_8UC1);
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

            // first
            if (firstFrame != null) {
                Core.absdiff(firstFrame, grayFrame, deltaFrame);
                Imgproc.threshold(deltaFrame, thresholdFrame, 20, 255, Imgproc.THRESH_BINARY);
                rectArray = getPedestrian(hog, grayFrame);
                log.warn("识别人数 =========> {}", rectArray.size());

                // 保存到数据库
                RecognizeResult result = RecognizeResult.builder()
                        .id(UUID.randomUUID().toString().replace("-", ""))
                        .taskId(key)
                        .millSec(eventData.getMillSec())
                        .cnt(rectArray.size())
                        .build();
                if (rectArray.size() > 0) {
                    Iterator<Rect> it2 = rectArray.iterator();
                    // 行人列表
                    List<Pedestrian> pedestrians = new ArrayList<>();
                    while (it2.hasNext()) {
                        Rect obj = it2.next();
                        Imgproc.rectangle(copyFrame, obj.br(), obj.tl(), new Scalar(0, 255, 0), 1);

                        Pedestrian area = Pedestrian.builder()
                                .start(obj.br())
                                .end(obj.tl())
                                .build();
                        pedestrians.add(area);
                    }
                    byte[] data = new byte[(int) (copyFrame.total() * copyFrame.channels())];
                    copyFrame.get(0, 0, data);
                    // 行人识别数据
                    result.setPedestriansJson(JSONObject.toJSONString(pedestrians));
                    // 保存识别后的图片数据
                    // result.setBase64(Base64.getEncoder().encodeToString(data));
                }

                // 处理后的结果
                log.warn("数据处理结果 识别人数 ++++++++++++++++++++++++ {}", result.getCnt());
                try {
                    // 将一帧的分析插入到数据库中
                    String sql = String.format("insert into recognize_result (id, task_id, pedestrians_json, base64, mill_sec, cnt) " +
                                    " VALUES ('%s', '%s', '%s', '%s', %.2f, %d)", result.getId(), result.getTaskId(), result.getPedestriansJson(),
                            result.getBase64(), result.getMillSec(), result.getCnt());
                    stat.executeUpdate(sql);
                } catch (SQLException e) {
                    log.error("option db error", e);
                }
            }
            firstFrame = grayFrame;
            currentProcessedEventData = eventData;
        }

        // 一次处理完成后判断更新一下该task的状态
        try {
            updateTask(key, stat);
        } catch (SQLException e) {
            log.error("option db error", e);
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                log.error("close ");
            }
        }

        return currentProcessedEventData;
    }

    /**
     * 判断是否需要更新任务状态
     *
     * @param taskId 任务 id
     * @param stat   stat
     * @throws SQLException sql执行异常
     */
    private static void updateTask(String taskId, Statement stat) throws SQLException {
        // 根据taskId查询
        String queryTaskSql = String.format("select * from task where task_id = '%s' limit 1", taskId);
        // 统计当前的任务的识别数量
        String resultCntSql = String.format("select count(*) as cnt from recognize_result where task_id = '%s'", taskId);
        try (ResultSet rsTask = stat.executeQuery(queryTaskSql);
             ResultSet rsCnt = stat.executeQuery(resultCntSql);) {
            Task task = null;
            if (rsTask.next()) {
                task = Task.builder()
                        .taskId(rsTask.getString("task_id"))
                        .cnt(rsTask.getInt("cnt"))
                        .startTime(rsTask.getTimestamp("start_time").toLocalDateTime())
                        .build();
                log.warn("task  ==> {}", JSONObject.toJSON(task));
            }
            if (task == null) {
                return;
            }
            int cnt = 0;
            if (rsCnt.next()) {
                cnt = rsCnt.getInt("cnt");
            }
            // 满足条件将任务更新为完成状态
            if (cnt != 0 && task.getCnt() != 0 && cnt + task.getCnt() / 10 >= task.getCnt()) {
                task.setFinishedTime(LocalDateTime.now());
                task.setStatus(TaskConstant.TASK_STATUS_FINISHED);
                task.setDuration(Duration.between(task.getStartTime(), task.getFinishedTime()).toMillis());

                String updateTaskSql = String.format("update task set status = '%s', finished_time = '%s', duration = %d where task_id = '%s'",
                        task.getStatus(), task.getFinishedTime().toString(), task.getDuration(), task.getTaskId());
                stat.executeUpdate(updateTaskSql);

                log.warn("task [{}] status update finished", task.getTaskId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error("updateTask()", throwables);
        }
    }

    private static List<Rect> getPedestrian(HOGDescriptor hog, Mat frame) {
        MatOfRect rect = new MatOfRect();
        hog.detectMultiScale(frame, rect, new MatOfDouble(), 0, new Size(4, 4), new Size(8, 8), 1.05, 2, false);
        return rect.toList();
    }

}
