package com.open.capacity.client.service.impl;

import com.open.capacity.client.entity.IpFilter;
import com.open.capacity.client.mapper.IpFilterMapper;
import com.open.capacity.client.service.IpFilterService;
import com.open.capacity.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("all")
public class IpFilterServiceImpl implements IpFilterService {

    private static final String IPWHITE = "ip_white";

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private IpFilterMapper ipFilterMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return ipFilterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(IpFilter record) {
        if (null != record.getIp()) {
            List<Object> objects = redisUtil.lGet(IPWHITE, 0, -1);
            objects.add(record.getIp());
            if (null != redisUtil.lGet(IPWHITE, 0, -1) && redisUtil.lGet(IPWHITE, 0, -1).size() > 0) {
                redisUtil.del(IPWHITE);
            }
            redisUtil.lSet(IPWHITE, objects);
        }
        record.setCreateTime(new Date());
        ipFilterMapper.insert(record);
    }

    @Override
    public IpFilter selectByPrimaryKey(Long id) {
        return ipFilterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(IpFilter record) {
        return ipFilterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<IpFilter> findAll() {
        //缓存ip白名单
        getIpFilterIp();
        return ipFilterMapper.findAll();
    }

    @Override
    public List<Object> getIpFilterIp() {

        List<Object> result = new ArrayList<>();
        List<IpFilter> all = ipFilterMapper.findAll();
        for (IpFilter ipFilter : all) {
            if (null != ipFilter.getIp() && !"".equals(ipFilter.getIp())) {
                result.add(ipFilter.getIp());
            }
        }
        if (null != redisUtil.lGet(IPWHITE, 0, -1) && redisUtil.lGet(IPWHITE, 0, -1).size() > 0) {
            redisUtil.del(IPWHITE);
        }
        redisUtil.lSet(IPWHITE, result);
        return result;
    }

    @Override
    public List<Object> test() {
        return redisUtil.lGet(IPWHITE, 0, -1);
    }
}
