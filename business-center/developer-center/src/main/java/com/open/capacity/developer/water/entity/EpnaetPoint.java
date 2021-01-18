package com.open.capacity.developer.water.entity;

import java.util.Date;

/**
 * 水利模型-点位信息实体类
 *
 * @author DUJIN
 */
public class EpnaetPoint {
  private Integer id;
  /** 模型ID */
  private Integer modelId;
  /** 点位ID */
  private Integer nodeId;
  /** 点位横坐标 */
  private Double nodeX;
  /** 点位纵坐标 */
  private Double nodeY;
  /** 高德经度 */
  private Double longitude;
  /** 高德纬度 */
  private Double latitude;
  /** 创建时间 */
  private Date createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getModelId() {
    return modelId;
  }

  public void setModelId(Integer modelId) {
    this.modelId = modelId;
  }

  public Integer getNodeId() {
    return nodeId;
  }

  public void setNodeId(Integer nodeId) {
    this.nodeId = nodeId;
  }

  public Double getNodeX() {
    return nodeX;
  }

  public void setNodeX(Double nodeX) {
    this.nodeX = nodeX;
  }

  public Double getNodeY() {
    return nodeY;
  }

  public void setNodeY(Double nodeY) {
    this.nodeY = nodeY;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "EpnaetPoint{" +
            "id=" + id +
            ", modelId=" + modelId +
            ", nodeId=" + nodeId +
            ", nodeX=" + nodeX +
            ", nodeY=" + nodeY +
            ", longitude=" + longitude +
            ", latitude=" + latitude +
            ", createTime=" + createTime +
            '}';
  }
}
