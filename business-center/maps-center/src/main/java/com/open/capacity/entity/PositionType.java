package com.open.capacity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
@ApiModel("position_type")
public class PositionType implements Serializable {
	private static final long serialVersionUID = 1L;


			private Integer id;
	@ApiModelProperty("类型名称")
		private String name;

}
