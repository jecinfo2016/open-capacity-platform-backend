package com.open.capacity.developer.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.developer.device.dao.DevDeviceRelationMapper;
import com.open.capacity.developer.device.dao.DeviceInfoMapper;
import com.open.capacity.developer.device.entity.DevDeviceRelation;
import com.open.capacity.developer.device.entity.DeviceInfo;
import com.open.capacity.developer.device.entity.DeviceType;
import com.open.capacity.developer.device.service.IDevDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
@Service
@Slf4j
public class DevDeviceServiceImpl implements IDevDeviceService {

    @Autowired
    DevDeviceRelationMapper deviceRelationMapper;


    @Autowired
    DeviceInfoMapper deviceInfoMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveDevDeviceInfo(DevDeviceRelation deviceRelation) {
        deviceRelation.setCreateTime(new Date());
        return deviceRelationMapper.insertSelective(deviceRelation);
    }

    @Override
    public List<DeviceType> queryDeviceTypeAll(){
        return deviceInfoMapper.queryDeviceTypeList();
    }

    @Override
    public PageResult queryDeviceInfoByParams(Map<String,Object> params) {
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        PageHelper.startPage(page,limit,true);
        List<Map<String, Object>> list = deviceRelationMapper.queryDeviceInfoByParams(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo(list);
        return PageResult.<Map<String,Object>>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public int deleteDeviceRelationInfo(Integer relationId) {
        return deviceRelationMapper.deleteDevDeviceRelation(relationId);
    }

    @Override
    public int updateDevDeviceRelation(DevDeviceRelation deviceRelation) {
        return deviceRelationMapper.updateDevDeviceRelation(deviceRelation);
    }

    @Override
    public List<DeviceInfo> queryDeviceInfoList(Map<String,Object> map) {
        return deviceInfoMapper.queryDeviceInfoList(map);
    }

    @Override
    public DevDeviceRelation queryDeviceInfoById(Integer clientId,Integer deviceId) {
        return deviceRelationMapper.queryDeviceInfoById(clientId,deviceId);
    }
}
