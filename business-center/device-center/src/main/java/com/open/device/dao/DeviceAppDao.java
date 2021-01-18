package com.open.device.dao;


import com.open.device.model.DeviceApp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 应用设备关系
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@Mapper
public interface DeviceAppDao  {

    int save(DeviceApp deviceApp);

    int update(DeviceApp deviceApp);

    int delete(Long id);

    List<DeviceApp> findAll(Map<String, Object> params);

    int selectByAppId(String appId);


    DeviceApp selectByDeviceSn(String deviceSn);

    /**
     * 根据appid查询设备列表
     * @param appId
     * @return
     */
    int findAppsn(@Param("appId") String appId,@Param("deviceSn") String deviceSn);
}
