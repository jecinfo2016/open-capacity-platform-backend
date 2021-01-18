package com.open.device.dao;

import com.open.device.model.DeviceApp;
import com.open.device.model.DeviceUpgrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeviceUpgradeDao {

    int save(DeviceUpgrade deviceUpgrade);

    List<DeviceUpgrade> findAll();
}
