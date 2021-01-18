package com.open.capacity.monitor.entity;

import java.util.Date;

/**
 * 服务请求历史记录实体
 * @author DUJIN
 */
public class ServiceHistory {
    /**
     * 主键
     */
    private Integer historyId;
    /**
     * 外键
     */
    private Integer serviceId;
    /**
     * 请求开始时间
     */
    private Date startTime;
    /**
     * 请求结束时间
     */
    private Date endTime;
    /**
     * 请求状态
     */
    private Integer state;
    /**
     * 请求结果
     */
    private String historyResult;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getHistoryResult() {
        return historyResult;
    }

    public void setHistoryResult(String historyResult) {
        this.historyResult = historyResult;
    }

    @Override
    public String toString() {
        return "ServiceHistory{" +
                "historyId=" + historyId +
                ", serviceId=" + serviceId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state=" + state +
                ", historyResult='" + historyResult + '\'' +
                '}';
    }
}
