package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DevicePowerDao;
import com.open.device.model.DevicePower;
import com.open.device.service.DevicePowerService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DevicePowerServiceImpl implements DevicePowerService {

    @Autowired
    private DevicePowerDao devicePowerDao;

    /**
     * 添加
     *
     * @param devicePower
     */
    public int save(DevicePower devicePower) {
        return devicePowerDao.save(devicePower);
    }

    /**
     * 修改
     *
     * @param devicePower
     */
    public int update(DevicePower devicePower) {
        return devicePowerDao.update(devicePower);
    }


    /**
     * 根据id删除
     *
     * @param id
     */
    public int delete(Long id) {
        return devicePowerDao.delete(id);
    }


    /**
     * 设备用电信息列表
     *
     * @param params
     * @return
     */
    public PageResult<DevicePower> findAll(Map<String, Object> params) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page") != null && MapUtils.getInteger(params, "limit") != null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);

        List<DevicePower> list = devicePowerDao.findAll(params);
        PageInfo<DevicePower> pageInfo = new PageInfo(list);

        return PageResult.<DevicePower>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
