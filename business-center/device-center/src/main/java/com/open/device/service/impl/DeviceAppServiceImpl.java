package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceAppDao;
import com.open.device.model.DeviceApp;
import com.open.device.service.DeviceAppService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class DeviceAppServiceImpl implements DeviceAppService {

    @Autowired
    private DeviceAppDao deviceAppDao;

    /**
     * 添加
     * @param deviceApp
     */
    public int save(DeviceApp deviceApp){
       /* if (deviceApp.getUpdateTime()==""&&deviceApp.getCreateTime()==""){
            return 0;
        }*/
        return deviceAppDao.save(deviceApp);
    }

    /**
     * 修改
     * @param deviceApp
     */
    public int update(DeviceApp deviceApp){
        return deviceAppDao.update(deviceApp);
    }


    /**
     * 根据id删除
     * @param id
     */
    public int delete(Long id){
        return deviceAppDao.delete(id);
    }


    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult findAll(Map<String, Object> params){

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<DeviceApp> list  =  deviceAppDao.findAll(params);
        PageInfo<DeviceApp> pageInfo = new PageInfo(list);

        return PageResult.<DeviceApp>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    /**
     * 根据appId查询
     * @param appId
     * @return
     */
    @Override
    public int selectByAppId(String appId) {

        return deviceAppDao.selectByAppId(appId);
    }
    /**
     * 根据deviceSn查询
     * @param deviceSn
     * @return
     */
    @Override
    public DeviceApp selectByDeviceSn(String deviceSn) {
        return deviceAppDao.selectByDeviceSn(deviceSn);
    }

    @Override
    public int findAppsn(String appId, String deviceSn) {
        return deviceAppDao.findAppsn(appId,deviceSn);
    }

}
