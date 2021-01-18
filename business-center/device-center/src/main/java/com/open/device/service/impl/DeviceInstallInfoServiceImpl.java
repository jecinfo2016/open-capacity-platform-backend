package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceAppDao;
import com.open.device.dao.DeviceInstallInfoDao;
import com.open.device.model.DeviceApp;
import com.open.device.model.DeviceInstallInfo;
import com.open.device.service.DeviceInstallInfoService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class DeviceInstallInfoServiceImpl  implements DeviceInstallInfoService {

    @Autowired
    private DeviceInstallInfoDao deviceInstallInfoDao;

    @Autowired
    private DeviceAppDao deviceAppDao;

    /**
     * 添加
     * @param deviceInstallInfo
     */
    public int save(DeviceInstallInfo deviceInstallInfo){
        return deviceInstallInfoDao.save(deviceInstallInfo);
    }

    /**
     * 修改
     * @param deviceInstallInfo
     */
    public int update(DeviceInstallInfo deviceInstallInfo){
        return deviceInstallInfoDao.update(deviceInstallInfo);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceInstallInfoDao.delete(id);
    }


    /**
     * 设备安装信息列表
     * @param params
     * @return
     */
    public PageResult<DeviceInstallInfo> findAll(Map<String, Object> params){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceInstallInfo> list  =  deviceInstallInfoDao.findAll(params);
        PageInfo<DeviceInstallInfo> pageInfo = new PageInfo(list);

        return PageResult.<DeviceInstallInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    /**
     * 更新设备安装信息
     * @param deviceInstallInfo
     * @param
     * @return
     */
    @Override
    public int saveOrUpdate(DeviceInstallInfo deviceInstallInfo) {
        //根据DeviceSn查询
        DeviceApp deviceApp = deviceAppDao.selectByDeviceSn(deviceInstallInfo.getDeviceSn());
        //DeviceSn存在插入更新数据
        if (deviceApp!=null){
            if (deviceInstallInfo.getId()==null){
                return deviceInstallInfoDao.save(deviceInstallInfo);
            }else {
                return deviceInstallInfoDao.update(deviceInstallInfo);
            }
        }else{
            return 0;
        }

    }


}
