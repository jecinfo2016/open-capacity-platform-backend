package com.open.device.service;


import com.open.capacity.common.web.PageResult;
import com.open.device.model.DevicePower;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DevicePowerService {
    /**
     * 添加
     * @param devicePower
     */
    int save(DevicePower devicePower);

    /**
     * 修改
     * @param devicePower
     */
    int update(DevicePower devicePower);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备设备用电信息列表
     * @param params
     * @return
     */
    PageResult<DevicePower> findAll(Map<String, Object> params);

}

