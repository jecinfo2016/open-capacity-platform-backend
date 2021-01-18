package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备类型
 *
 * @author
 * @email
 * @date 2020-05-08 10:42:41
 */

@Data
@ApiModel("device_type")
public class DeviceType implements Serializable {
	private static final long serialVersionUID = 1L;

		private Integer id;
		@ApiModelProperty("类型名称")
		private String name;
		@ApiModelProperty("类型")
		private Integer type;
		@ApiModelProperty("创建时间")
		private Date createTime;

		@ApiModelProperty("设备点位类型图")
		private String image;

		private String tags;

		private String metric;
}
