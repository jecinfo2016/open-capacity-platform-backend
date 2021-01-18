package com.open.device.dao;


import com.open.device.model.DeviceManufacturer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 设备厂家信息
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DeviceManufacturerDao  {

    int save(DeviceManufacturer deviceManufacturer);

    int update(DeviceManufacturer deviceManufacturer);

    int delete(Long id);

    List<DeviceManufacturer> findAll(Map<String, Object> params);



}
