package com.open.device.dao;


import com.open.device.model.DeviceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 设备类型
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DeviceTypeDao  {

    int save(DeviceType deviceType);

    int update(DeviceType deviceType);

    int delete(Long id);

    List<DeviceType> findAll(Map<String, Object> params);



}
