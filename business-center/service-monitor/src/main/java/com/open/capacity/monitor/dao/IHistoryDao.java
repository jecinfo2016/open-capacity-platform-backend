package com.open.capacity.monitor.dao;

import com.open.capacity.monitor.entity.ServiceHistory;
import java.util.List;

public interface IHistoryDao {

    int saveHistoryInfo(ServiceHistory monitor);

    List<ServiceHistory> queryHistoryInfo(ServiceHistory monitor);

    int deleteHistoryInfoById(Integer historyId);

    int deleteHistoryInfoByServiceId(Integer serviceId);
}
