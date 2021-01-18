package com.open.device.dao;

import com.open.device.model.DeviceState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface DeviceStateDao {

    int save(DeviceState deviceState);

    int update(DeviceState deviceState);

    int delete(Long id);

    List<DeviceState> findAll(Map<String, Object> params);

    @Select("select d.* from device_state d where d.type_id = #{typeId}")
    DeviceState getByTypeId(Integer typeId);
}
