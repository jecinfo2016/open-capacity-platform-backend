package com.open.capacity.monitor.service;

import com.open.capacity.monitor.entity.ServiceHistory;

import java.util.List;

public interface IHistoryService {

    int saveHistoryInfo(ServiceHistory history);

    List<ServiceHistory> queryHistoryInfo(ServiceHistory history, int page, int size);

    int deleteHistoryInfoById(Integer historyId);

    int deleteHistoryInfoByServiceId(Integer historyId);
}
