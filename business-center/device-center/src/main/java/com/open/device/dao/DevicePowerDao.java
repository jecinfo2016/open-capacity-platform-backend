package com.open.device.dao;

import com.open.device.model.DevicePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DevicePowerDao  {

    int save(DevicePower devicePower);

    int update(DevicePower devicePower);

    int delete(Long id);

    List<DevicePower> findAll(Map<String, Object> params);



}
