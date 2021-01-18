package com.open.capacity.video.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.video.entity.RecognizeResult;
import com.open.capacity.video.entity.Task;

import java.util.Map;

/**
 * 任务 service
 *
 * @author donglh
 * @date 2020/7/14
 */
public interface TaskService {
    /**
     * 保存一个数据
     *
     * @param task task
     */
    void saveOne(Task task);

    /**
     * 更新一条数据
     *
     * @param task task
     */
    void updateOne(Task task);


    /**
     * 查询task
     *
     * @param taskId 任务id
     * @return task
     */
    Task findByTaskId(String taskId);

    /**
     * 根据taskId 更新task 状态
     *
     * @param key key
     */
    void updateTaskStatus(String key);

    /**
     * 根据 task id 查询
     *
     * @param taskId task id
     * @param params 分页参数
     * @return 结果
     */
    PageResult<RecognizeResult> findRecognizeResult(String taskId, Map<String, Object> params);
}
