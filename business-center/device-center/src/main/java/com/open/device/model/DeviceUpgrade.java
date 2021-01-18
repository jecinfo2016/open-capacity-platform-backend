package com.open.device.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@ApiModel("device_upgrade")
public class DeviceUpgrade {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String ver;
    private String checkCode;

}
