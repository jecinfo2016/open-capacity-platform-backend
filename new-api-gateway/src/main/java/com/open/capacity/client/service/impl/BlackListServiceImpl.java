package com.open.capacity.client.service.impl;

import com.open.capacity.client.entity.BlackList;
import com.open.capacity.client.mapper.BlackListMapper;
import com.open.capacity.client.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("all")
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    private BlackListMapper BlackListMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return BlackListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(BlackList record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        BlackListMapper.insert(record);
    }

    @Override
    public BlackList selectByPrimaryKey(Long id) {
        return BlackListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(BlackList record) {
        record.setUpdateTime(new Date());
        return BlackListMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BlackList> findAll() {
        return BlackListMapper.findAll();
    }

    @Override
    public List<String> getBlackListIp() {
        List<BlackList> all = BlackListMapper.findAll();
        List<String> ipList = new ArrayList<>();
        for (BlackList blackList : all) {
            ipList.add(blackList.getIp());
        }
        return ipList;
    }

}
