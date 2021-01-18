package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/** 任务调度实体类
 * @author DUJIN*/
@ApiModel
public class EquipmentTsdbTask {
  /** 主键 */
  private Integer id;
  /** 应用ID */
  @NotNull(message = "应用ID不能为空")
  @ApiModelProperty(value = "应用ID",required = true)
  private Integer clientId;
  /** cron表达式 */
  @NotBlank(message = "cron不能为空")
  @ApiModelProperty(value = "cron表达式",required = true)
  private String cronStr;

  /** 聚合器 */
  @NotEmpty(message = "聚合方式不能为空")
  @ApiModelProperty(value = "聚合方式",required = true)
  private String aggregator;
  /** 统计开始时间 */
  @NotEmpty(message = "统计开始时间不能为空")
  @ApiModelProperty(value = "统计开始时间",required = true)
  private String startTime;
  /** 设备类型 */
  @NotNull(message = "设备类型不能为空")
  @ApiModelProperty(value = "设备类型",required = true)
  private Integer deviceType;
  /** 统计字段 */
  @NotEmpty(message = "统计字段不能为空")
  @ApiModelProperty(value = "统计字段",required = true)
  private String equipmentTag;
  /** 调度状态 */
  private String flag;

  /** 统计结束时间 */
  private String endTime;

  /** 任务名称 */
  @NotEmpty(message = "任务名称不能为空")
  @ApiModelProperty(value = "任务名称",required = true)
  private String taskName;
  /** 任务描述 */
  @NotEmpty(message = "任务描述不能为空")
  @ApiModelProperty(value = "任务描述",required = true)
  private String taskDesc;

  /** 设备列表，以|分隔 */
  @ApiModelProperty(value = "设备列表")
  private String equipmentSns;

  /** 下采样单位 */
  @ApiModelProperty(value = "聚合单位")
  private String downsampleUnit;

  /** 下采样时间段 */
  @ApiModelProperty(value = "聚合周期")
  private String downsampleTime;

  /** 是否分区分析 */
  private String dmaAnalyse="0";

  /**
   * 任务调度扩展id
   */
  private Integer jobInfoId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  public String getCronStr() {
    return cronStr;
  }

  public void setCronStr(String cronStr) {
    this.cronStr = cronStr;
  }

  public String getAggregator() {
    return aggregator;
  }

  public void setAggregator(String aggregator) {
    this.aggregator = aggregator;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public Integer getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(Integer deviceType) {
    this.deviceType = deviceType;
  }

  public String getEquipmentTag() {
    return equipmentTag;
  }

  public void setEquipmentTag(String equipmentTag) {
    this.equipmentTag = equipmentTag;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDesc() {
    return taskDesc;
  }

  public void setTaskDesc(String taskDesc) {
    this.taskDesc = taskDesc;
  }

  public String getEquipmentSns() {
    return equipmentSns;
  }

  public void setEquipmentSns(String equipmentSns) {
    this.equipmentSns = equipmentSns;
  }

  public String getDownsampleUnit() {
    return downsampleUnit;
  }

  public void setDownsampleUnit(String downsampleUnit) {
    this.downsampleUnit = downsampleUnit;
  }

  public String getDownsampleTime() {
    return downsampleTime;
  }

  public void setDownsampleTime(String downsampleTime) {
    this.downsampleTime = downsampleTime;
  }

  public String getDmaAnalyse() {
    return dmaAnalyse;
  }

  public void setDmaAnalyse(String dmaAnalyse) {
    this.dmaAnalyse = dmaAnalyse;
  }

  public Integer getJobInfoId() {
    return jobInfoId;
  }

  public void setJobInfoId(Integer jobInfoId) {
    this.jobInfoId = jobInfoId;
  }
}
