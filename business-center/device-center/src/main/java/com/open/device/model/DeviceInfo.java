package com.open.device.model;

import com.open.capacity.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Map;

/**
 * 设备信息
 * 
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */

@Getter
@Setter
@Data
@ApiModel("device_info")
public class DeviceInfo implements Serializable {
	private static final long serialVersionUID = 1L;


		@Excel(name = "设备id",cellType = Excel.ColumnType.NUMERIC)
    	private Integer id;

		@Excel(name = "设备编码")
		@ApiModelProperty("设备编码")
    	private String deviceSn;//设备编码

		@Excel(name = "设备厂家")
		@ApiModelProperty("设备厂家")
    	private Integer manufacturerId;//设备厂家

		@Excel(name = "设备型号")
		@ApiModelProperty("设备型号")
		private String model;//设备型号

		@Excel(name = "设备名称")
		@ApiModelProperty("设备名称")
    	private String name;//设备名称

		@Excel(name = "生产日期")
		@ApiModelProperty("生产日期")
    	private String generationDate;//生产日期

		@Excel(name = "使用年限")
		@ApiModelProperty("使用年限")
    	private Float equipmentLife;//使用年限

		@Excel(name = "设备类型")
		@ApiModelProperty("设备类型")
    	private Integer devceTypeId;//设备类型

		@Transient
		private String deviceTypeName;//设备类型名称

		private Integer status;

		@Excel(name = "供电方式")
		@ApiModelProperty("供电方式")
    	private Integer powerSupplyMode;//供电方式

		@Excel(name = "是否物联网设备")
		@ApiModelProperty("是否物联网设备")
  		private Integer isIotDevice;//是否物联网设备

		@Excel(name = "创建者")
		@ApiModelProperty("创建者")
		private String createBy;//创建者

		@Excel(name = "创建时间")
		@ApiModelProperty("创建时间")
    	private String createTime;//创建时间

		@Excel(name = "更新者")
		@ApiModelProperty("更新者")
    	private String updateBy;//更新者

		@Excel(name = "更新时间")
		@ApiModelProperty("更新时间")
    	private String updateTime;//更新时间

		@Excel(name = "备注信息")
		@ApiModelProperty("备注信息")
    	private String remark;//备注信息

		@ApiModelProperty("备注信息")
		private Map<String, String> DeviceValue;//设备数据

	@ApiModelProperty("备注類型")
	private DeviceType style;
	@ApiModelProperty("备注出場信息")
	private DeviceManufacturer deviceManufacturer;
	@ApiModelProperty("备注電池信息")
	private DevicePower devicePower;
	@ApiModelProperty("备注狀態")
	private DeviceStatus deviceStatus;
	@ApiModelProperty("备注安裝信息")
	private DeviceInstallInfo deviceInstallInfo;



}
