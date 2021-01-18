package com.open.capacity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 管线显示
 * 
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */

@Getter
@Setter
@Data
@ApiModel("n_pipeline_view")
public class NPipelineView implements Serializable {
	private static final long serialVersionUID = 1L;


			private Integer id;

			//分区编码
			@ApiModelProperty("分区编码")
		private Integer dmaId;
    //ID
	@ApiModelProperty("ID")
		private Integer pipeid;
    //节点编码
	@ApiModelProperty("节点编码")
		private String nodeNumber;
    //显示层级
	@ApiModelProperty("显示层级")
		private Integer level;
    //管线材质
	@ApiModelProperty("管线材质")
		private String material;
    //直径
	@ApiModelProperty("直径")
		private Integer diameter;
    //经度
	@ApiModelProperty("经度")
		private BigDecimal longitude;
    //纬度
	@ApiModelProperty("纬度")
		private BigDecimal latitude;
    //上一节点ID
	@ApiModelProperty("上一节点ID")
		private Integer parentNode;
    //创建时间
	@ApiModelProperty("创建时间")
		private Date ctreateTime;

}
