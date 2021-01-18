package com.open.device.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Data
@ApiModel("device_metric")
public class DeviceMetric implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @ApiModelProperty("设备类型")
    private String equipmentType;
    @ApiModelProperty("设备类型id")
    private Integer typeId;
    @ApiModelProperty("设备类型说明")
    private String description;
    @ApiModelProperty("度量标准")
    private String metric;
    @ApiModelProperty("创建时间")
    private Date createTime;
}
