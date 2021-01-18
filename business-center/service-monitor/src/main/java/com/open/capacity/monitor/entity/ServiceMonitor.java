package com.open.capacity.monitor.entity;

/**
 * 服务监控实体
 * @author DUJIN
 */
public class ServiceMonitor {
    /**
     * 主键
     */
    private Integer serviceId;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 请求地址
     */
    private String serviceAddr;
    /**
     * 请求方式
     */
    private String serviceMethod;
    /**
     * 请求参数
     */
    private String serviceParams;
    /**
     * 状态
     */
    private Integer state;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddr() {
        return serviceAddr;
    }

    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getServiceParams() {
        return serviceParams;
    }

    public void setServiceParams(String serviceParams) {
        this.serviceParams = serviceParams;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ServiceMonitor{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceAddr='" + serviceAddr + '\'' +
                ", serviceMethod='" + serviceMethod + '\'' +
                ", serviceParams='" + serviceParams + '\'' +
                ", state=" + state +
                '}';
    }
}
