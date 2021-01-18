package com.jecinfo.kelamqtt.service;


import java.util.List;

/**
 * @author lkq
 */
public interface DeviceService {

    /**
     * 获取rut设备编号
     * @return
     */
    List<String> getByModel();
}
