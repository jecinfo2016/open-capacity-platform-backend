package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备状态
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_status")
public class DeviceStatus implements Serializable {
	private static final long serialVersionUID = 1L;


			private Integer id;
		@ApiModelProperty("設備id")
		private Integer deviceId;
		@ApiModelProperty("设备状态")
		private Integer status;
		@ApiModelProperty("更新时间")
		private Date updateTime;
		@ApiModelProperty("备注")
		private String remarks;

}
