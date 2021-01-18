package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备厂家信息
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_manufacturer")
public class DeviceManufacturer implements Serializable {
	private static final long serialVersionUID = 1L;



    private Integer  id;
    @ApiModelProperty("厂家名称")
    private String name;//厂家名称
    @ApiModelProperty("地址")
    private String address;//地址
    @ApiModelProperty("联系人")
    private String contacts;//联系人
    @ApiModelProperty("联系电话")
    private String phone;//联系电话
    @ApiModelProperty("网站")
    private String website;//网站
    @ApiModelProperty("厂家介绍")
    private String description;//厂家介绍
    @ApiModelProperty("创建者")
    private String createBy;//创建者
    @ApiModelProperty("创建时间")
    private String createTime;//创建时间
    @ApiModelProperty("更新者")
    private String updateBy;//更新者
    @ApiModelProperty("更新时间")
    private String updateTime;//更新时间
    @ApiModelProperty("备注信息")
    private String remark;//备注信息

}
