package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 设备安装信息
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_install_info")
public class DeviceInstallInfo  implements Serializable {
	private static final long serialVersionUID = 1L;



    private Integer id;
    @ApiModelProperty("设备ID")
    private Integer deviceId;//设备ID
    @ApiModelProperty("设备编码")
    private String deviceSn;//设备编码
    @ApiModelProperty("安装日期")
    private String installDate;//安装日期
    @ApiModelProperty("纬度")
    private BigDecimal latitude;//纬度
    @ApiModelProperty("经度")
    private BigDecimal longitude;//经度
    @ApiModelProperty("安装地址")
    private String installAddress;//安装地址
    @ApiModelProperty("创建时间")
    private String createTime;//创建时间
    @ApiModelProperty("更新时间")
    private String updateTime;//更新时间
    @ApiModelProperty("图片")
    private String picUrl;//图片



}
