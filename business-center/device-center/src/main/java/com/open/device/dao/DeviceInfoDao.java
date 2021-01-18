package com.open.device.dao;

import com.open.device.model.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 设备信息
 *
 * @author
 * @email
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DeviceInfoDao {

    int save(DeviceInfo deviceInfo);

    int update(DeviceInfo deviceInfo);

    int delete(Long id);

    List<DeviceInfo> findAll(Map<String, Object> params);

    List<Map> findByDeviceBasic(String appid, Map<String, Object> params);

    DeviceInfo findDeviceBySn(String deviceSn);

    List<Map> findByDeviceStatus(String appid, Map<String, Object> params);

    DeviceInfo findDeviceStatusBySn(String deviceSn);

    DeviceInfo findById(Integer id);

    List<DeviceInfo> findPointData();

    DeviceInfo findBySn(String deviceSn);

    int modifyOffline(@Param(value = "list") List<Integer> list);

    int modifyOnline(@Param(value = "list") List<Integer> list);
}
