package com.open.capacity.client.service;

import com.open.capacity.client.entity.BlackList;

import java.util.List;

public interface BlackListService {

    int deleteByPrimaryKey(Long id);

    void insert(BlackList record);

    BlackList selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BlackList record);

    List<BlackList> findAll();

    List<String> getBlackListIp();
}
