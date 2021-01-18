package com.open.capacity.client.mapper;

import com.open.capacity.client.entity.IpFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IpFilterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IpFilter record);

    IpFilter selectByPrimaryKey(Long id);

    int updateByPrimaryKey(IpFilter record);

    List<IpFilter> findAll();
}