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
 * 警告
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_warning")
public class DeviceWarning implements Serializable {
	private static final long serialVersionUID = 1L;


			private Integer id;

		@ApiModelProperty("类型id")
		private Integer deviceId;
		@ApiModelProperty("告警类型")
		private Integer warningType;
		@ApiModelProperty("告警详情")
		private String content;
		@ApiModelProperty("创建时间")
		private Date createTime;

}
