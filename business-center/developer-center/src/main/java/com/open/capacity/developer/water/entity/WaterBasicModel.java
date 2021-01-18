package com.open.capacity.developer.water.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/** @author DUJIN */
@ApiModel
public class WaterBasicModel {
  private Integer id;
  /** 应用ID */
  @NotNull(message = "应用ID不能为空")
  @ApiModelProperty(value = "应用id",required = true)
  private Integer clientId;
  /** 分区ID */
  @NotNull(message = "分区ID不能为空")
  @ApiModelProperty(value = "分区id",required = true)
  private Integer dmaId;
  /** 模型地址 */
  @NotBlank(message="水利模型地址不能为空")
  @ApiModelProperty(value = "水利模型地址",required = true)
  private String epnaetAddress;
  /** 模型基准点1 */
  @NotBlank(message = "基准点1坐标不能为空")
  @ApiModelProperty(value = "基准点1坐标",required = true)
  private String node1;
  /** 模型基准点2 */
  @NotBlank(message = "基准点2坐标不能为空")
  @ApiModelProperty(value = "基准点2坐标",required = true)
  private String node2;
  /** 高德坐标1 */
  @NotBlank(message = "高德坐标1不能为空")
  @ApiModelProperty(value = "高德坐标1",required = true)
  private String gaude1;
  /** 高德坐标2 */
  @NotBlank(message = "高德坐标2不能为空")
  @ApiModelProperty(value = "高德坐标2",required = true)
  private String gaude2;

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

  public Integer getDmaId() {
    return dmaId;
  }

  public void setDmaId(Integer dmaId) {
    this.dmaId = dmaId;
  }

  public String getEpnaetAddress() {
    return epnaetAddress;
  }

  public void setEpnaetAddress(String epnaetAddress) {
    this.epnaetAddress = epnaetAddress == null ? null : epnaetAddress.trim();
  }

  public String getNode1() {
    return node1;
  }

  public void setNode1(String node1) {
    this.node1 = node1 == null ? null : node1.trim();
  }

  public String getNode2() {
    return node2;
  }

  public void setNode2(String node2) {
    this.node2 = node2 == null ? null : node2.trim();
  }

  public String getGaude1() {
    return gaude1;
  }

  public void setGaude1(String gaude1) {
    this.gaude1 = gaude1 == null ? null : gaude1.trim();
  }

  public String getGaude2() {
    return gaude2;
  }

  public void setGaude2(String gaude2) {
    this.gaude2 = gaude2 == null ? null : gaude2.trim();
  }
}
