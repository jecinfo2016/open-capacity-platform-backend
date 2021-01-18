package com.open.device.model;

import lombok.Data;

/**
 * 任务执行参数实体类
 * @author DUJIN
 */
@Data
public class JobParam {

    private RequestModel requestModel;
    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 统计字段
     */
    private String equipmentTag;
}
