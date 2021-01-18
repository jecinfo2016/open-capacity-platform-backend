package com.open.device.service;

import com.open.device.model.RequestModel;
import com.open.device.model.ResposeModel;
import com.open.device.model.TaskOutput;

import java.util.List;

/**
 *
 * @author DUJIN
 */
public interface TsdbTaskService {



    /**
     * 查询聚合opentsdb的数据
     * @param requestModel
     * @return
     */
    List<ResposeModel> analysisTsdbInfo(RequestModel requestModel);

    /**
     * 批量添加任务执行数据
     * @param list
     * @return
     */
    int saveTaskOutputInfo(List<TaskOutput> list);

    /**
     * 查询此任务的最后一次执行时间
     * @param taskId
     * @return
     */
    Long queryEndTimestamp(Integer taskId);
}
