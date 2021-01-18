package com.jecinfo.kelamqtt.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lkq
 */
@Mapper
public interface DeviceDao {

    /**
     * 获取rut设备编号
     * @return
     */
    List<String> findByModel();
}
