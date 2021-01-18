package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceManufacturerDao;
import com.open.device.model.DeviceManufacturer;
import com.open.device.service.DeviceManufacturerService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DeviceManufacturerServiceImpl  implements DeviceManufacturerService {

    @Autowired
    private DeviceManufacturerDao deviceManufacturerDao;

    /**
     * 添加
     * @param deviceManufacturer
     */
    public int save(DeviceManufacturer deviceManufacturer){
        return deviceManufacturerDao.save(deviceManufacturer);
    }

    /**
     * 修改
     * @param deviceManufacturer
     */
    public int update(DeviceManufacturer deviceManufacturer){
        return deviceManufacturerDao.update(deviceManufacturer);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceManufacturerDao.delete(id);
    }


    /**
     * 设备出厂信息列表
     * @param params
     * @return
     */
    public PageResult<DeviceManufacturer> findAll(Map<String, Object> params){

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceManufacturer> list  =  deviceManufacturerDao.findAll(params);
        PageInfo<DeviceManufacturer> pageInfo = new PageInfo(list);

        return PageResult.<DeviceManufacturer>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
