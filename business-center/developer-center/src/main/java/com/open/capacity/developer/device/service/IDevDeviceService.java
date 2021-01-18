package com.open.capacity.developer.device.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.developer.device.entity.DevDeviceRelation;
import com.open.capacity.developer.device.entity.DeviceInfo;
import com.open.capacity.developer.device.entity.DeviceType;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
public interface IDevDeviceService {

    /**
     * 保存开发者平台-设备管理信息
     * @param deviceRelation
     * @return
     */
    int saveDevDeviceInfo(DevDeviceRelation deviceRelation);

    /**
     * 查询所有的设备类型
     * @return
     */
    List<DeviceType> queryDeviceTypeAll();

    /**
     * 根据条件查询开发者平台下所有的设备信息
     * @param params
     * @return
     */
    PageResult queryDeviceInfoByParams(Map<String,Object> params);

    /**
     * 删除开发者平台-设备管理关联信息
     * @param relationId 关联主键
     * @return
     */
    int deleteDeviceRelationInfo(Integer relationId);

    /**
     * 更新设备管理信息
     * @param deviceRelation
     * @return
     */
    int updateDevDeviceRelation(DevDeviceRelation deviceRelation);

    /**
     * 查询平台所有的设备
     * @param map
     * @return
     */
    List<DeviceInfo> queryDeviceInfoList(Map<String,Object> map);

    /**
     * 根据设备ID查询是否已经存在
     * @param deviceId
     * @param clientId
     * @return
     */
    DevDeviceRelation queryDeviceInfoById(Integer clientId,Integer deviceId);
}
