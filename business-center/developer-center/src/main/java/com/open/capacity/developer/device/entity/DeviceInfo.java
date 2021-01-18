package com.open.capacity.developer.device.entity;

import lombok.Data;

/**
 * @author DUJIN
 */
@Data
public class DeviceInfo {

  private Integer id;

  private String deviceSn;

  private Integer manufacturerId;

  private String model;

  private String name;

  private String generationDate;

  private Float equipmentLife;

  private Integer devceTypeId;

  private String deviceTypeName;

  private Integer status;

  private Integer powerSupplyMode;

  private Integer isIotDevice;

  private String createBy;

  private String createTime;

  private String updateBy;

  private String updateTime;

  private String remark;
}
