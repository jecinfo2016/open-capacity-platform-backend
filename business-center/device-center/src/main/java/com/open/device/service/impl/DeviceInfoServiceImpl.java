package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.DeviceAppDao;
import com.open.device.dao.DeviceInfoDao;
import com.open.device.model.DeviceInfo;
import com.open.device.service.DeviceInfoService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Autowired
    private DeviceAppDao deviceAppDao;

    private static final String PAGE = "page";
    private static final String LIMIT = "limit";

    /**
     * 添加
     *
     * @param deviceInfo
     */
    public int save(DeviceInfo deviceInfo) {
        return deviceInfoDao.save(deviceInfo);
    }

    /**
     * 修改
     *
     * @param deviceInfo
     */
    public int update(DeviceInfo deviceInfo) {
        return deviceInfoDao.update(deviceInfo);
    }


    /**
     * 根据id删除
     *
     * @param id
     */
    public int delete(Long id) {
        return deviceInfoDao.delete(id);
    }


    /**
     * 设备信息列表
     *
     * @param params
     * @return
     */
    public PageResult<DeviceInfo> findAll(Map<String, Object> params) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, PAGE) != null && MapUtils.getInteger(params, LIMIT) != null)
            PageHelper.startPage(MapUtils.getInteger(params, PAGE), MapUtils.getInteger(params, LIMIT), true);
        List<DeviceInfo> list = deviceInfoDao.findAll(params);
        PageInfo<DeviceInfo> pageInfo = new PageInfo(list);


        return PageResult.<DeviceInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }


    /**
     * 查询设备基础信息
     *
     * @param appid
     * @param params
     * @return
     */
    @Override
    public PageResult findByDeviceBasic(String appid, Map<String, Object> params) {


        int appId = deviceAppDao.selectByAppId(appid);
        if (appId != 0) {//如果appid可查证明有权查询
            if (MapUtils.getInteger(params, PAGE) != null && MapUtils.getInteger(params, LIMIT) != null)
                PageHelper.startPage(MapUtils.getInteger(params, PAGE), MapUtils.getInteger(params, LIMIT), true);

            List<Map> list = deviceInfoDao.findByDeviceBasic(appid, params);
            PageInfo<DeviceInfo> pageInfo = new PageInfo(list);

            return PageResult.<DeviceInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
        } else {
            return null;
        }


    }

    /**
     * 通过sn查询
     *
     * @param deviceSn
     * @return
     */
    @Override
    public DeviceInfo findDeviceBySn(String appid, String deviceSn) {
        int appId = deviceAppDao.selectByAppId(appid);
        if (appId != 0) {
            //如果appid可查证明有权查询
            return deviceInfoDao.findDeviceBySn(deviceSn);
        } else {
            return null;
        }
    }

    /**
     * 查询设备状态信息
     *
     * @param appid
     * @param params
     * @return
     */
    @Override
    public PageResult findByDeviceStatus(String appid, Map<String, Object> params) {

        int appId = deviceAppDao.selectByAppId(appid);
        if (appId != 0) {//如果appid可查证明有权查询
            if (MapUtils.getInteger(params, PAGE) != null && MapUtils.getInteger(params, LIMIT) != null)
                PageHelper.startPage(MapUtils.getInteger(params, PAGE), MapUtils.getInteger(params, LIMIT), true);

            List<Map> list = deviceInfoDao.findByDeviceStatus(appid, params);
            PageInfo<DeviceInfo> pageInfo = new PageInfo(list);

            return PageResult.<DeviceInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
        } else {
            return null;
        }


    }

    @Override
    public DeviceInfo findDeviceStatusBySn(String appid, String deviceSn) {
        int appId = deviceAppDao.selectByAppId(appid);
        //如果appid可查证明有权查询
        if (appId != 0) {
            return deviceInfoDao.findDeviceStatusBySn(deviceSn);
        } else {
            return null;
        }
    }

    @Override
    public DeviceInfo selectById(Integer id) {
        return deviceInfoDao.findById(id);
    }


    /**
     * 点位信息
     *
     * @return
     */
    @Override
    public List<DeviceInfo> findPointData() {
        return deviceInfoDao.findPointData();
    }

    @Override
    public DeviceInfo findBySn(String deviceSn) {
        return deviceInfoDao.findBySn(deviceSn);
    }

    @Override
    public int modifyOnline(List<Integer> list) {
        return deviceInfoDao.modifyOnline(list);
    }


}
