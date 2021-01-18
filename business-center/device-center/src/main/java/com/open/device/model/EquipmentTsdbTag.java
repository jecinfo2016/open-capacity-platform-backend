package com.open.device.model;

/** 统计字段实体类 */
public class EquipmentTsdbTag {

  /** 主键 */
  private Integer id;
  /** 设备类型 */
  private Integer deviceType;
  /** 统计字段index */
  private String tagIndex;
  /** 统计字段content */
  private String tagContent;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(Integer deviceType) {
    this.deviceType = deviceType;
  }

  public String getTagIndex() {
    return tagIndex;
  }

  public void setTagIndex(String tagIndex) {
    this.tagIndex = tagIndex;
  }

  public String getTagContent() {
    return tagContent;
  }

  public void setTagContent(String tagContent) {
    this.tagContent = tagContent;
  }
}
