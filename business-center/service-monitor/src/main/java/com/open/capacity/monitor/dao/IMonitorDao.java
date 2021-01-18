package com.open.capacity.monitor.dao;

import com.open.capacity.monitor.entity.ServiceMonitor;
import java.util.List;

/**
 * @author DUJIN
 */
public interface IMonitorDao {

    /**
     * 保存服务监控信息
     * @param monitor
     * @return
     */
    int saveMonitorInfo(ServiceMonitor monitor);

    /**
     * 更新服务监控信息
     * @param monitor
     * @return
     */
    int updateMonitorInfo(ServiceMonitor monitor);

    /**
     * 查询服务监控信息
     * @param monitor
     * @return
     */
    List<ServiceMonitor> queryMonitorInfo(ServiceMonitor monitor);

    /**
     * 删除服务监控信息
     * @param serviceId
     * @return
     */
    int deleteMnoitorInfoById(Integer serviceId);

    /**
     * 查询所有的服务监控信息
     * @return
     */
    List<ServiceMonitor> queryMonitorInfoAll();

    /**
     * 查询ID和Name查询服务信息
     * @param monitor
     * @return
     */
    ServiceMonitor queryMonitorByIdAndName(ServiceMonitor monitor);
}
