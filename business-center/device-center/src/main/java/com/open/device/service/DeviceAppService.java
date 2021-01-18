package com.open.device.service;

import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceApp;


import java.util.Map;

/**
 * 应用设备关系
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceAppService {
    /**
     * 添加
     * @param deviceApp
     */
    int save(DeviceApp deviceApp);

    /**
     * 修改
     * @param deviceApp
     */
    int update(DeviceApp deviceApp);

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
     * 根据appId查询
     * @param appId
     * @return
     */
    int selectByAppId(String appId);

    /**
     * 根据deviceSn查询
     * @param deviceSn
     * @return
     */
    DeviceApp selectByDeviceSn(String deviceSn);


    int findAppsn(String appId,String deviceSn);
}

