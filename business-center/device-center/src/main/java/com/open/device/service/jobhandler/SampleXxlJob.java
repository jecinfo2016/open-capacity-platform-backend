package com.open.device.service.jobhandler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.open.device.model.*;
import com.open.device.service.TsdbTaskService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
@Component
@Slf4j
public class SampleXxlJob {

    @Autowired
    TsdbTaskService taskService;

    /**
     * 任务执行逻辑
     *
     * @param param:任务执行参数
     * @return ：
     * @throws Exception
     */
    @XxlJob("tsdbJobHandler")
    public ReturnT<String> tsdbJobHandler(String param) {
        log.info("进入执行器.....,开始执行任务。");
        JobParam jobParam = parseParam(param);
        if (jobParam == null) {
            log.info("执行参数不符合指定格式，此次任务结束。");
            return ReturnT.FAIL;
        }
        // 任务ID
        Integer taskId = jobParam.getTaskId();
        //调取openTsdb所需参数
        RequestModel requestModel = jobParam.getRequestModel();
        //根据任务ID去数据库查询最后一条记录
        Long lastTiemstamp = taskService.queryEndTimestamp(taskId);
        if (lastTiemstamp != null) {
            requestModel.setStart(lastTiemstamp.toString());
        }
        // 任务执行结果
        List<ResposeModel> list = taskService.analysisTsdbInfo(requestModel);
        //异步保存任务执行结果
        executeResult(list, taskId);
        return ReturnT.SUCCESS;
    }


    /**
     * 执行参数转换
     *
     * @param param
     */
    public JobParam parseParam(String param) {
        JobParam jobParam = null;
        if (StringUtils.isNotEmpty(param)) {
            jobParam = JSONObject.parseObject(param, JobParam.class);
        } else {
            log.info("执行参数未传，此次任务结束。");
        }
        return jobParam;
    }

    /**
     * 保存任务执行结果
     *
     * @param list
     * @param taskId
     */
    public void executeResult(List<ResposeModel> list, Integer taskId) {
        List<TaskOutput> items = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            list.stream()
                    .forEach(
                            model -> {
                                Map<Long, Double> map = model.getDps();
                                Tags tags = model.getTags();
                                if (map != null) {
                                    // 对map中time排序
                                    Map<Long, Double> sortMap = Maps.newLinkedHashMap();
                                    map.entrySet().stream()
                                            .sorted(Map.Entry.<Long, Double>comparingByKey().reversed())
                                            .forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
                                    for (Map.Entry<Long, Double> entry : sortMap.entrySet()) {
                                        TaskOutput output = new TaskOutput();
                                        //任务ID
                                        output.setTaskId(taskId);
                                        //统计字段
                                        output.setEquipmentTag(tags.getIndex());
                                        //设备编码-单个
                                        output.setEquipmentSns(tags.getHost());
                                        //时间戳
                                        output.setTimesTamp(entry.getKey());
                                        //值
                                        output.setEquipmentTagValue(entry.getValue());
                                        items.add(output);
                                    }
                                }
                            });
        }
        if (!items.isEmpty() && taskService.saveTaskOutputInfo(items) > 0) {
            log.info("保存任务调度记录成功！");
        }
    }
}
