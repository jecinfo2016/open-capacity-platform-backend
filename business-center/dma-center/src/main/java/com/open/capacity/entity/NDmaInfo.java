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
 * @date 2020-05-15 16:38:16
 */

@Getter
@Setter
@Data
@ApiModel("n_dma_info")
public class NDmaInfo implements Serializable {
	private static final long serialVersionUID = 1L;


			private Integer id;
    @ApiModelProperty("行政编码")
		private String areaId;
    @ApiModelProperty("分区名称")
		private String name;
    @ApiModelProperty("户数")
		private Integer households;
    @ApiModelProperty("中心点经纬度")
		private String centerLnglat;
    @ApiModelProperty("上一级分区id")
		private Integer parentId;
    @ApiModelProperty("当前节点全路径id集合，包含自己id，逗号分隔：例如(1,2,3)")
		private String fullIds;
    @ApiModelProperty("状态 0：启用；1：删除；")
		private Integer status;
    @ApiModelProperty("排序号")
		private Integer sortNo;
    @ApiModelProperty("创建时间")
		private Date createTime;
    @ApiModelProperty("更新时间")
		private Date updateTime;
    @ApiModelProperty("叶子结点")
		private Boolean leaf;
    @ApiModelProperty("坐标点")
		private String nodes;

}
