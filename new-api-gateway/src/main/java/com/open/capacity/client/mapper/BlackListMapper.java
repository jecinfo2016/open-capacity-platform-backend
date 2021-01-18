package com.open.capacity.client.mapper;

import com.open.capacity.client.entity.BlackList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlackList record);

    BlackList selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BlackList record);

    List<BlackList> findAll();
}