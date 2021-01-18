package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 应用设备关系
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_app")
public class DeviceApp implements Serializable {
	private static final long serialVersionUID = 1L;

    	private Integer id;
		@ApiModelProperty("应用标识")
    	private String appId;//应用标识
		@ApiModelProperty("设备编码")
    	private String deviceSn;//设备编码
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
