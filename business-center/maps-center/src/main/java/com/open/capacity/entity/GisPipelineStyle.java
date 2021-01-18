package com.open.capacity.entity;

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
 * @date 2020-05-12 10:52:01
 */

@Getter
@Setter
@Data
@ApiModel("gis_pipeline_style")
public class GisPipelineStyle implements Serializable {
	private static final long serialVersionUID = 1L;

			private Integer id;
	@ApiModelProperty("管道颜色显示")
		private String pipeLineColor;
	@ApiModelProperty("管径是否体现")
		private Integer diameterShow;
	@ApiModelProperty("是否显示材质")
		private Integer materialShow;
	@ApiModelProperty("显示缩放级别")
		private Integer level;
	@ApiModelProperty("创建者")
		private String createBy;
	@ApiModelProperty("创建时间")
		private Date createTime;
	@ApiModelProperty("更新者")
		private String updateBy;
	@ApiModelProperty("更新时间")
		private Date updateTime;
	@ApiModelProperty("备注信息")
		private String remark;

}
