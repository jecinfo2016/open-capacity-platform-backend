package com.open.device.dao;

import com.open.device.model.DeviceStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 设备状态
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DeviceStatusDao  {

    int save(DeviceStatus deviceStatus);

    int update(DeviceStatus deviceStatus);

    int delete(Long id);

    List<DeviceStatus> findAll(Map<String, Object> params);



}
