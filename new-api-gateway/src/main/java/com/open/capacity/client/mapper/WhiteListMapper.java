package com.open.capacity.client.mapper;

import com.open.capacity.client.entity.WhiteList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WhiteListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WhiteList record);

    WhiteList selectByPrimaryKey(Long id);

    int updateByPrimaryKey(WhiteList record);

    List<WhiteList> findAll();
}