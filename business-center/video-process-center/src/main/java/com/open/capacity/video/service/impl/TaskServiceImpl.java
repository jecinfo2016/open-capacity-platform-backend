package com.open.capacity.video.service.impl;

import cn.hutool.core.util.StrUtil;
import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.video.constant.TaskConstant;
import com.open.capacity.video.dao.RecognizeResultDao;
import com.open.capacity.video.dao.TaskDao;
import com.open.capacity.video.entity.RecognizeResult;
import com.open.capacity.video.entity.Task;
import com.open.capacity.video.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author donglh
 * @date 2020/7/14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService, Serializable {

    private final TaskDao taskDao;
    private final RecognizeResultDao recognizeResultDao;

    @Override
    public void saveOne(Task task) {
        taskDao.saveOne(task);
    }

    @Override
    public void updateOne(Task task) {
        taskDao.updateOne(task);
    }

    @Override
    public Task findByTaskId(String taskId) {
        return taskDao.findByTaskId(taskId);
    }

    @Override
    public void updateTaskStatus(String key) {
        Task task = taskDao.findByTaskId(key);
        if (task == null) {
            log.error("task id ==> {} don't exist!", key);
            throw new ServiceException("task 不存在!");
        }

        int cnt = recognizeResultDao.countByTaskId(task.getTaskId());

        log.warn("task cnt ==> {}, cur cnt ==> {}", task.getCnt(), cnt);

        if (cnt + task.getCnt() / 10 >= task.getCnt()) {
            task.setStatus(TaskConstant.TASK_STATUS_FINISHED);
            task.setFinishedTime(LocalDateTime.now());
            task.setDuration(Duration.between(task.getFinishedTime(), task.getStartTime()).toMillis());
            updateOne(task);
        }
    }

    @Override
    public PageResult<RecognizeResult> findRecognizeResult(String taskId, Map<String, Object> params) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null || !StrUtil.equals(task.getStatus(), TaskConstant.TASK_STATUS_FINISHED)) {
            throw new ServiceException("task 不存在或者状态异常!");
        }
        int cnt = recognizeResultDao.countByTaskId(taskId);
        int page = MapUtils.getInteger(params, "page", 1);
        int limit = MapUtils.getInteger(params, "limit", 10);

        List<RecognizeResult> data = recognizeResultDao.findByTaskId(taskId, (page - 1) * limit, limit);

        return PageResult.<RecognizeResult>builder()
                .data(data)
                .code(0)
                .count((long) cnt)
                .current(page)
                .size(limit)
                .build();
    }

}
