package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceStatusDao;
import com.open.device.model.DeviceStatus;
import com.open.device.service.DeviceStatusService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DeviceStatusServiceImpl  implements DeviceStatusService {

    @Autowired
    private DeviceStatusDao deviceStatusDao;

    /**
     * 添加
     * @param deviceStatus
     */
    public int save(DeviceStatus deviceStatus){
        return deviceStatusDao.save(deviceStatus);
    }

    /**
     * 修改
     * @param deviceStatus
     */
    public int update(DeviceStatus deviceStatus){
        return deviceStatusDao.update(deviceStatus);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceStatusDao.delete(id);
    }


    /**
     * 设备状态列表
     * @param params
     * @return
     */
    public PageResult<DeviceStatus> findAll(Map<String, Object> params){

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceStatus> list  =  deviceStatusDao.findAll(params);
        PageInfo<DeviceStatus> pageInfo = new PageInfo(list);

        return PageResult.<DeviceStatus>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
