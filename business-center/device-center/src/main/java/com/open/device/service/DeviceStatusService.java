package com.open.device.service;



import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceStatus;

import java.util.Map;

/**
 * 设备状态
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceStatusService {
    /**
     * 添加
     * @param deviceStatus
     */
    int save(DeviceStatus deviceStatus);

    /**
     * 修改
     * @param deviceStatus
     */
    int update(DeviceStatus deviceStatus);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备状态信息列表
     * @param params
     * @return
     */
    PageResult<DeviceStatus> findAll(Map<String, Object> params);

}

