package com.open.capacity.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@ApiModel("gis_config")
public class GisConfig {

    private Integer id;
    @ApiModelProperty("appID")
    private Integer appId;
    @ApiModelProperty("高德地图key")
    private Integer AmapKey;
}
