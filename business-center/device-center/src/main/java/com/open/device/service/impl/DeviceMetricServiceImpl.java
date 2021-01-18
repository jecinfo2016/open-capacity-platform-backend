package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceMetricDao;
import com.open.device.model.DeviceMetric;
import com.open.device.service.DeviceMetricService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@SuppressWarnings("all")
public class DeviceMetricServiceImpl implements DeviceMetricService {

    @Autowired
    private DeviceMetricDao deviceMetricDao;

    /**
     * 添加
     * @param DeviceMetric
     */
    public int save(DeviceMetric DeviceMetric){
        return deviceMetricDao.save(DeviceMetric);
    }

    /**
     * 修改
     * @param DeviceMetric
     */
    public int update(DeviceMetric DeviceMetric){
        return deviceMetricDao.update(DeviceMetric);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceMetricDao.delete(id);
    }


    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult findAll(Map<String, Object> params){
        params.put("page",1);
        params.put("limit",10);
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceMetric> list  =  deviceMetricDao.findAll(params);
        PageInfo<DeviceMetric> pageInfo = new PageInfo(list);

        return PageResult.<DeviceMetric>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public DeviceMetric selectByType(Long typeId) {
        return deviceMetricDao.selectByType(typeId);
    }


}
