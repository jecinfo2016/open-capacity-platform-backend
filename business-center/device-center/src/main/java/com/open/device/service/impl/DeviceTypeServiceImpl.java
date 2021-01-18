package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceTypeDao;
import com.open.device.model.DeviceType;
import com.open.device.service.DeviceTypeService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class DeviceTypeServiceImpl  implements DeviceTypeService {

    @Autowired
    private DeviceTypeDao deviceTypeDao;

    /**
     * 添加
     * @param deviceType
     */
    public int save(DeviceType deviceType){
        return deviceTypeDao.save(deviceType);
    }

    /**
     * 修改
     * @param deviceType
     */
    public int update(DeviceType deviceType){
        return deviceTypeDao.update(deviceType);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceTypeDao.delete(id);
    }


    /**
     * 设备类型列表
     * @param params
     * @return
     */
    public PageResult<DeviceType> findAll(Map<String, Object> params){

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceType> list  =  deviceTypeDao.findAll(params);
        PageInfo<DeviceType> pageInfo = new PageInfo(list);

        return PageResult.<DeviceType>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
