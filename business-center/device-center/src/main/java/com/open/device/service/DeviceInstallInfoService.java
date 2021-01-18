package com.open.device.service;



import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceInstallInfo;

import java.util.Map;

/**
 * 设备安装信息
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceInstallInfoService {
    /**
     * 添加
     * @param deviceInstallInfo
     */
    int save(DeviceInstallInfo deviceInstallInfo);

    /**
     * 修改
     * @param deviceInstallInfo
     */
    int update(DeviceInstallInfo deviceInstallInfo);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备安装信息列表
     * @param params
     * @return
     */
    PageResult<DeviceInstallInfo> findAll(Map<String, Object> params);


    /**
     * 添加或修改
     * @param deviceInstallInfo
     * @return
     */
    int saveOrUpdate(DeviceInstallInfo deviceInstallInfo);

}

