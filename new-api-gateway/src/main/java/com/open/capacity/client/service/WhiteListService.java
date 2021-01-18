package com.open.capacity.client.service;

import com.open.capacity.client.entity.GatewayRoutes;
import com.open.capacity.client.entity.WhiteList;

import java.util.List;
import java.util.Map;

public interface WhiteListService {

    int deleteByPrimaryKey(Long id);

    void insert(WhiteList record);

    WhiteList selectByPrimaryKey(Long id);

    int updateByPrimaryKey(WhiteList record);

    List<WhiteList> findAll();

    String[] getIgnore();

    /**
     *  同步redis数据 从mysql中同步过去
     *
     * @return
     */
    String synchronization();
}
