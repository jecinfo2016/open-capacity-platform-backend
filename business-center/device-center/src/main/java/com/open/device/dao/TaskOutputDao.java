package com.open.device.dao;

import com.open.device.model.TaskOutput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskOutputDao {

    /**
     * 新增
     * @param taskOutput
     * @return
     */
    int save(TaskOutput taskOutput);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 查询所有
     * @param params
     * @return
     */
    List<TaskOutput> findAll(Map<String, Object> params);

    /**
     * 根据任务id查询
     * @param
     * @return
     */
    List<TaskOutput> selectByStack(Map<String, Object> params);

}
