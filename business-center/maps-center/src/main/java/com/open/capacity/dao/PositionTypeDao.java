package com.open.capacity.dao;

import com.open.capacity.entity.PositionType;
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
public interface PositionTypeDao  {

    int save(PositionType positionType);

    int update(PositionType positionType);

    int delete(Long id);

    List<PositionType> findAll(Map<String, Object> params);



}
