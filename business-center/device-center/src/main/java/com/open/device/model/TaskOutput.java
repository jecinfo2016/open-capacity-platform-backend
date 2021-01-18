package com.open.device.model;

/** @author DUJIN @Des: 任务执行输出实体类 */
public class TaskOutput {
  /**
   * 主键
   */
  private Integer id;
  /**
   * 统计字段
   */
  private String equipmentTag;
  /**
   * 统计值
   */
  private Double equipmentTagValue;
  /**
   * 时间戳
   */
  private Long timesTamp;
  /**
   * 任务ID
   */
  private Integer taskId;
  /**
   * 设备编码
   */
  private String equipmentSns;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEquipmentTag() {
    return equipmentTag;
  }

  public void setEquipmentTag(String equipmentTag) {
    this.equipmentTag = equipmentTag;
  }

  public Double getEquipmentTagValue() {
    return equipmentTagValue;
  }

  public void setEquipmentTagValue(Double equipmentTagValue) {
    this.equipmentTagValue = equipmentTagValue;
  }

  public Long getTimesTamp() {
    return timesTamp;
  }

  public void setTimesTamp(Long timesTamp) {
    this.timesTamp = timesTamp;
  }

  public Integer getTaskId() {
    return taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }

  public String getEquipmentSns() {
    return equipmentSns;
  }

  public void setEquipmentSns(String equipmentSns) {
    this.equipmentSns = equipmentSns;
  }

  @Override
  public String toString() {
    return "TaskOutput{" +
            "id=" + id +
            ", equipmentTag='" + equipmentTag + '\'' +
            ", equipmentTagValue=" + equipmentTagValue +
            ", timesTamp=" + timesTamp +
            ", taskId=" + taskId +
            ", equipmentSns='" + equipmentSns + '\'' +
            '}';
  }
}
