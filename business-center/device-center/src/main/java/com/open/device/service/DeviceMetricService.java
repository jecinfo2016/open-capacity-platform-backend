package com.open.device.service;

import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceMetric;

import java.util.Map;


public interface DeviceMetricService {
    /**
     * 添加
     * @param DeviceMetric
     */
    int save(DeviceMetric DeviceMetric);

    /**
     * 修改
     * @param DeviceMetric
     */
    int update(DeviceMetric DeviceMetric);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * App列表
     * @param params
     * @return
     */
    PageResult findAll(Map<String, Object> params);

    /**
     * 根据typeId获取对象
     */
    DeviceMetric selectByType(Long typeId);
}

