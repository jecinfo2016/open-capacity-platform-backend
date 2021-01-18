package com.open.device.service;

import com.open.device.model.DeviceInfo;
import com.open.device.model.DeviceType;
import com.open.device.model.EquipmentTsdbTag;
import com.open.device.model.EquipmentTsdbTask;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

public interface TsdbDeviceService {

  /**
   * 查询所有的聚合器
   *
   * @return
   */
  List<String> queryAggregators();

  /**
   * 查询设备上报的所有类型
   *
   * @return
   */
  List<DeviceType> queryEquipmentType();

  /**
   * 根据上报类型查询对应的字段
   *
   * @param deviceType
   * @return
   */
  List<EquipmentTsdbTag> queryEquipmentTsdbTag(Integer deviceType);

  /**
   * 根据上报类型查询设备列表
   *
   * @param deviceType
   * @return
   */
  List<DeviceInfo> queryDeviceListByType(Integer deviceType);

  /**
   * 新增任务调度
   *
   * @param task
   * @return
   */
  int insertDeviceTask(EquipmentTsdbTask task);

  /**
   * 查询任务列表
   * @param params
   * @return
   */
  List<Map<String, String>> queryTaskList(Map<String, String> params);

  /**
   * 删除任务调度记录
   * @param id 任务主键
   * @param jobInfoId ：job任务表主键
   * @return
   */
  int deleteTaskInfo(Integer id, Integer jobInfoId);

  /**
   * 更新任务调度记录
   * @param task
   * @return
   */
  int updateTaskInfo(EquipmentTsdbTask task);

  /**
   * 更新任务调度状态
   * @param taskId
   * @param flag
   * @return
   */
  int updateTaskFlag(Integer taskId,String flag);
}
