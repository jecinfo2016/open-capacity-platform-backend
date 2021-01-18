package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceStateDao;
import com.open.device.model.DeviceState;
import com.open.device.model.DeviceStatus;
import com.open.device.service.DeviceStateService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@SuppressWarnings("all")
public class DeviceStateServiceImpl implements DeviceStateService {

    @Autowired
    private DeviceStateDao deviceStateDao;

    /**
     * 添加
     *
     * @param deviceState
     */
    public int save(DeviceState deviceState) {
        return deviceStateDao.save(deviceState);
    }

    /**
     * 修改
     *
     * @param deviceState
     */
    public int update(DeviceState deviceState) {
        return deviceStateDao.update(deviceState);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public int delete(Long id) {
        return deviceStateDao.delete(id);
    }

    /**
     * 设备状态列表
     *
     * @param params
     * @return
     */
    public PageResult<DeviceState> findAll(Map<String, Object> params) {
        params.put("page", 1);
        params.put("limit", 5);
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page") != null && MapUtils.getInteger(params, "limit") != null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        List<DeviceState> list = deviceStateDao.findAll(params);
        PageInfo<DeviceState> pageInfo = new PageInfo(list);
        return PageResult.<DeviceState>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public DeviceState getByTypeId(Integer typeId) {
        return deviceStateDao.getByTypeId(typeId);
    }

}
