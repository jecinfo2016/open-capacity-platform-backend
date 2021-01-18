package com.open.capacity.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.client.dto.WhiteListDefinition;
import com.open.capacity.client.entity.WhiteList;
import com.open.capacity.client.mapper.WhiteListMapper;
import com.open.capacity.client.service.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("all")
public class WhiteListServiceImpl implements WhiteListService {

    public static final String WHITE_LIST = "WHITELIST";

    @Autowired
    private WhiteListMapper whiteListMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Long id) {
        redisTemplate.boundHashOps(WHITE_LIST).delete(id.toString());
        return whiteListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(WhiteList record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        whiteListMapper.insert(record);
        WhiteListDefinition whiteListDefinition = transToDefinition(record);
        redisTemplate.boundHashOps(WHITE_LIST).put(record.getId().toString(), JSONObject.toJSONString(whiteListDefinition));
    }

    @Override
    public WhiteList selectByPrimaryKey(Long id) {
        return whiteListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(WhiteList record) {
        record.setUpdateTime(new Date());
        WhiteListDefinition whiteListDefinition = transToDefinition(record);
        redisTemplate.boundHashOps(WHITE_LIST).delete(record.getId().toString());
        redisTemplate.boundHashOps(WHITE_LIST).put(record.getId().toString(), JSONObject.toJSONString(whiteListDefinition));
        return whiteListMapper.updateByPrimaryKey(record);
    }

    private WhiteListDefinition transToDefinition(WhiteList record) {
        WhiteListDefinition whiteListDefinition = new WhiteListDefinition();
        whiteListDefinition.setId(record.getId());
        whiteListDefinition.setPath(record.getPath());
        return whiteListDefinition;
    }

    @Override
    public List<WhiteList> findAll() {
        return whiteListMapper.findAll();
    }

    @Override
    public String[] getIgnore() {
        List<WhiteList> lists = whiteListMapper.findAll();
        List<String> pathList = new ArrayList<>();
        for (WhiteList list : lists) {
            pathList.add(list.getPath());
        }
        return pathList.toArray(new String[pathList.size()]);
    }

    @Override
    public String synchronization() {
        List<WhiteList> all = whiteListMapper.findAll();
        for (WhiteList whiteList : all) {
            WhiteListDefinition whiteListDefinition = WhiteListDefinition.builder()
                    .id(whiteList.getId())
                    .path(whiteList.getPath())
                    .build();

            redisTemplate.boundHashOps(WHITE_LIST).put(whiteList.getId().toString(), JSONObject.toJSONString(whiteListDefinition));
        }

        return "success";
    }
}
