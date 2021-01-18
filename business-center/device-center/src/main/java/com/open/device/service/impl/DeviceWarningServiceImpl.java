package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceWarningDao;
import com.open.device.model.DeviceWarning;
import com.open.device.service.DeviceWarningService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class DeviceWarningServiceImpl  implements DeviceWarningService {

    @Autowired
    private DeviceWarningDao deviceWarningDao;

    /**
     * 添加
     * @param deviceWarning
     */
    public int save(DeviceWarning deviceWarning){
        return deviceWarningDao.save(deviceWarning);
    }

    /**
     * 修改
     * @param deviceWarning
     */
    public int update(DeviceWarning deviceWarning){
        return deviceWarningDao.update(deviceWarning);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceWarningDao.delete(id);
    }


    /**
     * 设备告警信息列表
     * @param params
     * @return
     */
    public PageResult<DeviceWarning> findAll(Map<String, Object> params){

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceWarning> list  =  deviceWarningDao.findAll(params);
        PageInfo<DeviceWarning> pageInfo = new PageInfo(list);

        return PageResult.<DeviceWarning>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
