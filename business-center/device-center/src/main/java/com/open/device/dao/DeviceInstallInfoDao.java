package com.open.device.dao;

import com.open.device.model.DeviceInstallInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 设备安装信息
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DeviceInstallInfoDao  {

    int save(DeviceInstallInfo deviceInstallInfo);

    int update(DeviceInstallInfo deviceInstallInfo);

    int delete(Long id);

    List<DeviceInstallInfo> findAll(Map<String, Object> params);



}
