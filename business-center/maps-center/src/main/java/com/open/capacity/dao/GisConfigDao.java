package com.open.capacity.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GisConfigDao {

    String getAmapKey(Integer appId);
}
