package com.open.capacity.developer.device.dao;

import com.open.capacity.developer.device.entity.DevDeviceRelation;
import com.open.capacity.developer.device.entity.DeviceInfo;
import com.open.capacity.developer.device.entity.DeviceType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
public interface DeviceInfoMapper {
    /**
     * 根据条件搜索全部的设备信息
     * @param map
     * @return
     */
    List<DeviceInfo> queryDeviceInfoList(Map<String,Object> map);

    /**
     * 搜索所有的设备类型
     * @return
     */
    List<DeviceType> queryDeviceTypeList();
}
