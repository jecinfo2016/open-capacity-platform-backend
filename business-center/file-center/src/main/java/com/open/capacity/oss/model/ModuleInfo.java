package com.open.capacity.oss.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
@ApiModel("module_info")
public class ModuleInfo {

    @ApiModelProperty("模块id")
    private Integer id;
    @ApiModelProperty("模块名称")
    private String name;//模块名称
    @ApiModelProperty("模块图标")
    private String icon; //模块图标
    @ApiModelProperty("创建人")
    private String creater; //创建人
    @ApiModelProperty("创建时间")
    private Date createTime; //创建时间
    @ApiModelProperty("模块文档地址")
    private String documentAddress; //模块文档地址
    @ApiModelProperty("模块文档展示静态文档地址")
    private String showUrl; //模块文档展示静态文档地址

    @Transient
    private Integer page;
    @Transient
    private Integer limit;
}
