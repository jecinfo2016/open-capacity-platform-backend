package com.open.capacity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */

@Getter
@Setter
@Data
@ApiModel("position_data")
public class PositionData implements Serializable {
	private static final long serialVersionUID = 1L;


			private Integer id;
	@ApiModelProperty("类型")
		private Integer type;
	@ApiModelProperty("名称")
		private String name;
	@ApiModelProperty("经度")
		private BigDecimal longitude;
	@ApiModelProperty("纬度")
		private BigDecimal latitude;
	@ApiModelProperty("创建时间")
		private Date createTime;


	private PositionType positionType;

}
