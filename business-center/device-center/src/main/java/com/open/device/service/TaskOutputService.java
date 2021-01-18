package com.open.device.service;

import com.open.capacity.common.web.PageResult;
import com.open.device.model.TaskOutput;

import java.util.Map;

public interface TaskOutputService {

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
     * 根据任务编号查询
     * @param
     * @return
     */
    PageResult<TaskOutput> selectByStack(Map<String, Object> params);
}
