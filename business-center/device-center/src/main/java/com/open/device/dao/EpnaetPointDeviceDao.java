package com.open.device.dao;


import com.open.device.model.EpnaetPointDevice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EpnaetPointDeviceDao {

    /**
     * 节点配置设备
     * @param epnaetPointDevice
     * @return
     */
    int configurationDevice(EpnaetPointDevice epnaetPointDevice);

    /**
     * 根据模型id查询模型设备列表
     * @param modelId
     * @return
     */
    @Select("SELECT n.`name`AS dmaName,i.`name`AS deviceName, t.`name`AS typeName,e.`id`, e.`node_id`AS id FROM  epnaet_point_device e \n" +
            "LEFT JOIN water_basic_model w ON e.`model_id`=w.`id` \n" +
            "LEFT JOIN n_dma_info n ON w.`dma_id`=n.`id`\n" +
            "LEFT JOIN epnaet_point p ON e.`node_id`= p.`node_id`\n" +
            "LEFT JOIN device_info i ON e.`device_sn` = i.`device_sn`\n" +
            "LEFT JOIN device_type t ON t.`type`= i.`devce_type_id` WHERE e.`model_id` = #{modelId}" )
    List<EpnaetPointDevice> modelDeviceList(@Param("modelId") Integer modelId);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
