package com.open.capacity.dao;

import com.open.capacity.entity.PositionData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 
 * 
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
@Mapper
public interface PositionDataDao  {

    int save(PositionData positionData);

    int update(PositionData positionData);

    int delete(Long id);

    List<PositionData> findAll(Map<String, Object> params);

    List<PositionData> findByDataAndType(Map<String, Object> params);



}
