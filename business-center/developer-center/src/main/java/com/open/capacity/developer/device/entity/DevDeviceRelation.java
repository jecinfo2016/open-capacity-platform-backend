package com.open.capacity.developer.device.entity;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 开发者平台-设备管理实体类
 *
 * @author DUJIN
 */
@Data
public class DevDeviceRelation {

    private Integer id;

    @NotNull(message = "应用ID不能为空")
    private Integer clientId;

    private String appId;

    private Integer dmaId;

    private Date createTime;

    @NotNull(message = "设备ID不能为空")
    private Integer deviceId;

    private String deviceSn;

    @NotEmpty(message = "安装地点不能为空")
    private String installAddress;
}
