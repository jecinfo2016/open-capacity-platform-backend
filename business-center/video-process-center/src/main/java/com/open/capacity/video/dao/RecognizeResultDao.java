package com.open.capacity.video.dao;

import com.open.capacity.video.entity.RecognizeResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 任务处理明细识别
 *
 * @author donglh
 * @date 2020/7/20
 */
@Mapper
public interface RecognizeResultDao {

    /**
     * 插入一条数据
     *
     * @param result 识别结果
     * @return change row
     */
    int saveOne(RecognizeResult result);

    /**
     * 根据task id 查询
     *
     * @param taskId task id
     * @return cnt
     */
    @Select("select count(*) from recognize_result where task_id = #{taskId} ")
    int countByTaskId(@Param("taskId") String taskId);

    /**
     * 查询
     *
     * @param taskId 任务id
     * @param page   page 分页参数
     * @param limit  limit 分页参数
     * @return result
     */
    List<RecognizeResult> findByTaskId(@Param("taskId") String taskId, @Param("page") int page, @Param("limit") int limit);
}
