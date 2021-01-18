package com.open.device.service;



import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceWarning;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceWarningService {
    /**
     * 添加
     * @param deviceWarning
     */
    int save(DeviceWarning deviceWarning);

    /**
     * 修改
     * @param deviceWarning
     */
    int update(DeviceWarning deviceWarning);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备告警信息列表
     * @param params
     * @return
     */
    PageResult<DeviceWarning> findAll(Map<String, Object> params);

}

