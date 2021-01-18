package com.open.device.service;




import com.open.capacity.common.web.PageResult;
import com.open.device.model.DeviceInfo;

import java.util.List;
import java.util.Map;

/**
 * 设备信息
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
public interface DeviceInfoService {
    /**
     * 添加
     * @param deviceInfo
     */
    int save(DeviceInfo deviceInfo);

    /**
     * 修改
     * @param deviceInfo
     */
    int update(DeviceInfo deviceInfo);

    /**
     * 根据id删除
     * @param id
     */
    int delete(Long id);


    /**
     * 设备信息列表
     * @param params
     * @return
     */
    PageResult<DeviceInfo> findAll(Map<String, Object> params);

    /**
     * 设备和设备基础信息
     * @param appid
     * @return
     */
    PageResult findByDeviceBasic(String appid, Map<String, Object> params);

    /**
     * 通过设备编码查询设备基础信息
     * @param deviceSn
     * @return
     */
    DeviceInfo findDeviceBySn(String appid, String deviceSn);


    /**
     * 设备位置和设备状态信息
     * @param appid
     * @return
     */
    PageResult findByDeviceStatus(String appid, Map<String, Object> params);

    /**
     * 通过设备编码查询设备状态信息
     * @param deviceSn
     * @return
     */
    DeviceInfo findDeviceStatusBySn(String appid, String deviceSn);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    DeviceInfo selectById(Integer id);


    /**
     * 点位信息
     * @return
     */
    List<DeviceInfo> findPointData();

    DeviceInfo findBySn(String deviceSn);

    /**
     * 批量修改离线
     */
    int modifyOnline(List<Integer> list);
}

