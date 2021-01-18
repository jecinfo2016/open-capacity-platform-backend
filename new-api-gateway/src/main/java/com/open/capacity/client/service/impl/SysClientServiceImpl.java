package com.open.capacity.client.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.client.dao.SysClientDao;
import com.open.capacity.client.service.SysClientService;
import com.open.capacity.common.constant.UaaConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 作者 owen
 * @version 创建时间：2018年4月5日 下午19:52:21 类说明
 * 查询应用绑定的资源权限
 */
@Slf4j
@SuppressWarnings("all")
@Service("sysClientService")
public class SysClientServiceImpl implements SysClientService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SysClientDao sysClientDao;

    public Map getClient(String clientId) {
        // 先从redis获取
        Map client = null;
        String value = (String) redisTemplate.boundHashOps(UaaConstant.CACHE_CLIENT_KEY).get(clientId);
        if (StringUtils.isBlank(value)) {
            // 没有从数据库读取
            client = sysClientDao.getClient(clientId);
        } else {
            client = JSONObject.parseObject(value, Map.class);
        }
        return client;
    }


}
