package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_power")
public class DevicePower implements Serializable {
	private static final long serialVersionUID = 1L;


		private Integer id;

		@ApiModelProperty("設備id")
		private Integer deviceId;

		@ApiModelProperty("剩余电量")
		private Float stateOfCharge;

		@ApiModelProperty("当前电压")
		private Integer voltage;
		@ApiModelProperty("电池额定电压")
		private Integer standardVoltage;
		@ApiModelProperty("电池容量")
		private Integer fullCharge;
		@ApiModelProperty("更新时间")
		private Date updateTime;

}
