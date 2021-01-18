package com.open.device.dao;


import com.open.device.model.DeviceMetric;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface DeviceMetricDao {

    int save(DeviceMetric DeviceMetric);

    int update(DeviceMetric DeviceMetric);

    int delete(Long id);

    List<DeviceMetric> findAll(Map<String, Object> params);

    @Select("select dm.* from device_metric dm where dm.type_id = #{typeId}")
    DeviceMetric selectByType(Long typeId);
}
