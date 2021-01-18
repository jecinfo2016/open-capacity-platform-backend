package com.open.capacity.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.monitor.dao.IMonitorDao;
import com.open.capacity.monitor.entity.ServiceMonitor;
import com.open.capacity.monitor.service.IMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MonitorServiceImpl implements IMonitorService {

    @Autowired
    IMonitorDao monitorDao;

    @Override
    public int saveMonitorInfo(ServiceMonitor serviceMonitor) {
        int row = 0;
        try {
            row = monitorDao.saveMonitorInfo(serviceMonitor);
        } catch (Exception e) {
            log.info("保存服务监控信息出现异常...");
            log.error(e.getMessage());
        }
        return row;
    }

    @Override
    public PageResult<ServiceMonitor> queryMonitorInfo(ServiceMonitor serviceMonitor, int page, int size) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(page,size,true);
        List<ServiceMonitor> list = monitorDao.queryMonitorInfo(serviceMonitor);
        PageInfo<ServiceMonitor> pageInfo = new PageInfo<>(list);
        return PageResult.<ServiceMonitor>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public List<ServiceMonitor> findMonitorInfoAll() {

        List<ServiceMonitor> list=null;
        try {
            list= monitorDao.queryMonitorInfoAll();
        } catch (Exception e) {
            log.info("查询【全部】服务监控信息出现异常...");
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public ServiceMonitor findMonitorInfoByIdAndName(ServiceMonitor serviceMonitor) {
        ServiceMonitor monitor= null;
        try {
            monitor= monitorDao.queryMonitorByIdAndName(serviceMonitor);
        } catch (Exception e) {
            log.info("查询服务监控信息出现异常...");
            log.error(e.getMessage());
        }
        return monitor;
    }

    @Override
    public int deleteMonitorInfo(Integer serviceId) {
        int row = 0;
        try {
            row = monitorDao.deleteMnoitorInfoById(serviceId);
        } catch (Exception e) {
            log.info("根据serviceId=[{}]删除服务监控信息出现异常...",serviceId);
            log.error(e.getMessage());
        }
        return row;
    }

    @Override
    public int updateMonitorInfo(ServiceMonitor serviceMonitor) {
        int row = 0;
        try {
            row = monitorDao.updateMonitorInfo(serviceMonitor);
            if (row==0){
                log.info("此条记录不存在-serviceId:[{}],更新失败！",serviceMonitor.getServiceId());
            }
        } catch (Exception e) {
            log.info("更新服务监控信息出现异常...");
            log.error(e.getMessage());
        }
        return row;
    }
}
