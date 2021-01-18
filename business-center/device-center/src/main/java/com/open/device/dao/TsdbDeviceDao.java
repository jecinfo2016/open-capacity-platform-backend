package com.open.device.dao;

import com.open.device.model.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/** 设备上报数据处理
 * @author DUJIN*/
public interface TsdbDeviceDao {

  /**
   * 查询所有的聚合器
   *
   * @return
   */
  @Select("SELECT aggregator FROM  aggregator_tsdb ORDER BY sort asc")
  List<String> queryAggregators();

  /**
   * 查询设备上报的所有类型
   *
   * @return
   */
  @Select("SELECT type,name FROM device_type WHERE metric is not null")
  List<DeviceType> queryEquipmentType();

  /**
   * 根据设备类型，查询设备上报主题
   *
   * @param type
   * @return
   */
  @Select("SELECT metric FROM device_type WHERE type=#{type}")
  String queryMetricByType(Integer type);
  /**
   * 根据上报类型查询对应的字段
   *
   * @param deviceType
   * @return
   */
  @Select("SELECT e.* FROM equipment_tsdb_tag e WHERE e.device_type=#{deviceType}")
  List<EquipmentTsdbTag> queryEquipmentTsdbTag(Integer deviceType);

  /**
   * 根据设备类型查询设备列表
   *
   * @param deviceType
   * @return
   */
  @Select("SELECT d.id,d.name,d.device_sn FROM device_info d WHERE d.devce_type_id=#{deviceType}")
  List<DeviceInfo> queryDeviceListByType(Integer deviceType);

  /**
   * 新增任务调度
   *
   * @param tsdbTask
   * @return
   */
  @Insert(
      "INSERT INTO equipment_tsdb_task (client_id,cron_str,aggregator,start_time,device_type,equipment_tag,flag,end_time,task_name,task_desc,equipment_sns,downsample_unit,downsample_time,dma_analyse) "
          + "VALUES(#{clientId},#{cronStr},#{aggregator},#{startTime},#{deviceType},#{equipmentTag},0,#{endTime,jdbcType=VARCHAR},#{taskName},#{taskDesc,jdbcType=VARCHAR},#{equipmentSns,jdbcType=VARCHAR},#{downsampleUnit},#{downsampleTime},#{dmaAnalyse,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
  int insertDeviceTask(EquipmentTsdbTask tsdbTask);

  /**
   * 回溯更新执行任务主键
   * @param id
   * @param jobInfoId
   * @return
   */
  @Update("update equipment_tsdb_task set job_info_id=#{jobInfoId} where id=#{id}")
  int updateJobInfoId(@Param("id") Integer id, @Param("jobInfoId") Integer jobInfoId);
  /**
   * 查询任务调度
   *
   * @param params:查询条件
   * @return
   */
  List<Map<String, String>> queryTaskList(Map<String, String> params);

  /**
   * 根据ID查询任务记录
   * @param id
   * @return
   */
  @Select("SELECT * FROM equipment_tsdb_task e WHERE e.id=#{id}")
  EquipmentTsdbTask queryTaskInfoById(Integer id);

  /** 删除任务调度信息 */
  int deleteTaskInfo(Integer id);

  /**
   * 更新任务调度记录
   *
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
  @Update("UPDATE equipment_tsdb_task set flag=#{flag,jdbcType=VARCHAR} where id=#{taskId}")
  int updateTaskFlag(@Param("taskId") Integer taskId,@Param("flag") String flag);

  /**
   * 批量添加任务执行数据
   * @param list
   * @return
   */
  int saveTaskOutputInfo(List<TaskOutput> list);

  /**
   * 查询此任务的最后一次执行时间
   * @param taskId
   * @return
   */
  Long queryEndTimestamp(Integer taskId);
}
