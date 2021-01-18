package org.opentsdb.device.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用设备关系
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Data
public class DeviceApp implements Serializable {
	private static final long serialVersionUID = 1L;

    	private Integer id;

    	private String appId;//应用id

    	private String deviceSn;//设备编码

		private String installAddress;//设备安装地址

		private String typeId;//设备类型

}
