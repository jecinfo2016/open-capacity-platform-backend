package com.open.capacity.video.dao;

import com.open.capacity.video.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author donglh
 * @date 2020/7/14
 */
@Mapper
public interface TaskDao {
    /**
     * 新增一条任务
     *
     * @param task task
     * @return change cnt
     */
    int saveOne(Task task);

    /**
     * 更新一条任务
     *
     * @param task task
     * @return change cnt
     */
    int updateOne(Task task);


    /**
     * 查询任务
     *
     * @param taskId 任务id
     * @return task
     */
    @Select("select * from task where task_id = #{taskId} limit 1")
    Task findByTaskId(String taskId);
}
