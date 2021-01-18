package com.open.capacity.developer.device.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author DUJIN
 */
@Data
public class DeviceType {
  private Integer id;
  private String name;
  private Integer type;
  private Date createTime;
  private String image;
  private String tags;
  private String metric;
}
