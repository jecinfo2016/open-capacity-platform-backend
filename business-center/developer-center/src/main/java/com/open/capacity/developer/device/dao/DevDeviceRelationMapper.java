package com.open.capacity.developer.device.dao;

import com.open.capacity.developer.device.entity.DevDeviceRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
public interface DevDeviceRelationMapper {

    /**
     * 新增开发者平台-设备管理-实体
     * @param record
     * @return
     */
    int insertSelective(DevDeviceRelation record);

    /**
     * 根据条件查询开发者平台下所有的设备信息
     * @param params
     * @return
     */
    List<Map<String,Object>> queryDeviceInfoByParams(Map<String,Object> params);

    /**
     * 根据主键删除设备管理信息
     * @param relationId
     * @return
     */
    int deleteDevDeviceRelation(Integer relationId);

    /**
     * 更新设备关联信息
     * @param deviceRelation
     * @return
     */
    int updateDevDeviceRelation(DevDeviceRelation deviceRelation);

    /**
     * 查询设备关联信息
     * @param clientId ：应用ID
     * @param deviceId ：设备ID
     * @return
     */
    @Select("SELECT * FROM dev_device_relation WHERE device_id=#{deviceId} AND client_id=#{clientId}")
    DevDeviceRelation queryDeviceInfoById(@Param("clientId") Integer clientId,  @Param("deviceId") Integer deviceId);
}
