package com.open.device.service;



import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceManufacturer;

import java.util.Map;

/**
 * 设备厂家信息
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceManufacturerService {
    /**
     * 添加
     * @param deviceManufacturer
     */
    int save(DeviceManufacturer deviceManufacturer);

    /**
     * 修改
     * @param deviceManufacturer
     */
    int update(DeviceManufacturer deviceManufacturer);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备出厂信息列表
     * @param params
     * @return
     */
    PageResult<DeviceManufacturer> findAll(Map<String, Object> params);

}

