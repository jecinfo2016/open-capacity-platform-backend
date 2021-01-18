package com.open.capacity.client.service;

import com.open.capacity.client.entity.IpFilter;

import java.util.List;

public interface IpFilterService {

    int deleteByPrimaryKey(Long id);

    void insert(IpFilter record);

    IpFilter selectByPrimaryKey(Long id);

    int updateByPrimaryKey(IpFilter record);

    List<IpFilter> findAll();

    List<Object> getIpFilterIp();

    List<Object> test();
}
