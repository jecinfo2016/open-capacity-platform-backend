package com.open.capacity.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.monitor.dao.IHistoryDao;
import com.open.capacity.monitor.entity.ServiceHistory;
import com.open.capacity.monitor.service.IHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    IHistoryDao historyDao;

    @Override
    public int saveHistoryInfo(ServiceHistory history) {
        int row = 0;
        try {
            row = historyDao.saveHistoryInfo(history);
        } catch (Exception e) {
            log.info("保存服务监控历史数据出现异常...");
            log.error(e.getMessage());
        }
        return row;
    }

    @Override
    public List<ServiceHistory> queryHistoryInfo(ServiceHistory history,int page, int size) {
        List<ServiceHistory> list=null;
        PageInfo<ServiceHistory> pageInfo;
        try {
            PageHelper.startPage(page, size);
            pageInfo = new PageInfo<>(historyDao.queryHistoryInfo(history));
            list=pageInfo.getList();
        } catch (Exception e) {
            log.info("查询服务监控历史信息出现异常...");
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public int deleteHistoryInfoById(Integer historyId) {
        int row = 0;
        try {
            row = historyDao.deleteHistoryInfoById(historyId);
        } catch (Exception e) {
            log.info("根据historyId=[{}]删除服务监控历史数据出现异常...",historyId);
            log.error(e.getMessage());
        }
        return row;
    }

    @Override
    public int deleteHistoryInfoByServiceId(Integer serviceId) {
        int row = 0;
        try {
            row = historyDao.deleteHistoryInfoByServiceId(serviceId);
        } catch (Exception e) {
            log.info("根据historyId=[{}]删除服务监控历史数据出现异常...",serviceId);
            log.error(e.getMessage());
        }
        return row;
    }
}
