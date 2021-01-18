package com.open.capacity.monitor.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.monitor.entity.ServiceMonitor;

import java.util.List;

/**
 * 服务请求
 * @author DUJIN
 */
public interface IMonitorService {

    /**
     * 保存服务监控信息
     * @param serviceMonitor
     * @return
     */
    int saveMonitorInfo(ServiceMonitor serviceMonitor);

    /**
     * 查询服务监控信息
     * @param serviceMonitor
     * @param page
     * @param size
     * @return
     */
    PageResult<ServiceMonitor> queryMonitorInfo(ServiceMonitor serviceMonitor, int page, int size);

    /**
     * 查询所有的服务监控信息
     * @return
     */
    List<ServiceMonitor> findMonitorInfoAll();

    /**
     * 根据服务名称和ID 查询服务信息
     * @param serviceMonitor
     * @return
     */
    ServiceMonitor findMonitorInfoByIdAndName(ServiceMonitor serviceMonitor);

    /**
     * 根据id 删除服务监控信息
     * @param serviceId
     * @return
     */
    int deleteMonitorInfo(Integer serviceId);

    /**
     * 更新服务监控信息
     * @param serviceMonitor
     * @return
     */
    int updateMonitorInfo(ServiceMonitor serviceMonitor);
}
