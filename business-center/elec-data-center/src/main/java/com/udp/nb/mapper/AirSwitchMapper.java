package com.udp.nb.mapper;

import com.udp.nb.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zql
 * @email zql3315@163.com
 * @date 2019-08-19 23:31:53
 */
@Mapper
public interface AirSwitchMapper {
	int save(AirSwitchDO airSwitch);

	int insertDeviceStatus(DeviceStatusDO  deviceStatusDO);

	int saveAirSwitchCurrent(AirSwitchDO airSwitch);

	int insertDeviceStatusCurrent(DeviceStatusDO  deviceStatusDO);

	int insertTTotalRecord(TTotalRecordDO tTotalRecord);

	void deleteAirSwitchCurrent(String mac);

	void deleteDeviceStatusCurrent(String mac);

	String getDeviceInfo(String mac);

	TAirSwitchLine getAirSwitchLine(TAirSwitchLine tAirSwitchLine);

	int insertTLineRecord(TLineRecordDO tLineRecordDO);
	List<DeviceStatusDO> getDeviceStatusList(Map<String, Object> map);
	List<DeviceStatusDO> getDeviceStatusCurrentList(Map<String, Object> map);
	List<AirSwitchDO> getAirSwitchCurrentList(Map<String, Object> map);

}
