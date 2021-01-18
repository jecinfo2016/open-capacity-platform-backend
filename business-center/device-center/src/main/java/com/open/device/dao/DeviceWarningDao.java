package com.open.device.dao;

import com.open.device.model.DeviceWarning;
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
public interface DeviceWarningDao  {

    int save(DeviceWarning deviceWarning);

    int update(DeviceWarning deviceWarning);

    int delete(Long id);

    List<DeviceWarning> findAll(Map<String, Object> params);



}
