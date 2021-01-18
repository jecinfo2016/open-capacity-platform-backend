package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("水利模型配置节点管网设备")
public class EpnaetPointDevice {

    private Integer id;

    @ApiModelProperty("模型ID")
    private Integer modelId;

    @ApiModelProperty("节点ID")
    private Integer nodeId;

    @ApiModelProperty("设备编码")
    private String deviceSn;


    private String dmaName;
    private String deviceName;
    private String typeName;
}
