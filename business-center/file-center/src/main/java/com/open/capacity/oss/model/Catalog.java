package com.open.capacity.oss.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("caLog_info")
public class Catalog {

    @ApiModelProperty("文档目录id")
    private String id;
    @ApiModelProperty("目录")
    private String catalog;
}
