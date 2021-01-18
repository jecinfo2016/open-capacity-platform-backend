package com.open.device.service;



import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceType;

import java.util.Map;

/**
 * 设备类型
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceTypeService {
    /**
     * 添加
     * @param deviceType
     */
    int save(DeviceType deviceType);

    /**
     * 修改
     * @param deviceType
     */
    int update(DeviceType deviceType);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备类型信息列表
     * @param params
     * @return
     */
    PageResult<DeviceType> findAll(Map<String, Object> params);

}

