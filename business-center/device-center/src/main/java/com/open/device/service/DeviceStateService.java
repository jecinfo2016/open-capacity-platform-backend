package com.open.device.service;


import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceState;

import java.util.Map;

/**
 * 设备状态
 *
 * @author
 * @email
 * @date 2020-05-08 10:42:41
 */
public interface DeviceStateService {
    /**
     * 添加
     *
     * @param deviceState
     */
    int save(DeviceState deviceState);

    /**
     * 修改
     *
     * @param deviceState
     */
    int update(DeviceState deviceState);

    /**
     * 根据id删除
     *
     * @param id
     */
    int delete(Long id);

    /**
     * 设备状态信息列表
     *
     * @param params
     * @return
     */
    PageResult<DeviceState> findAll(Map<String, Object> params);

    DeviceState getByTypeId(Integer typeId);
}

