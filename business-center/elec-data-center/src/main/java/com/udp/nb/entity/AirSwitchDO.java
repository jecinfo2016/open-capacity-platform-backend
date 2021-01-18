package com.udp.nb.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author zql
 * @email zql3315@163.com
 * @date 2019-08-19 23:31:53
 */
public class AirSwitchDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//空开报警id
	private Integer alarmId;
	//报警类型
	private Integer type;
	//设备id
	private Integer deviceId;
	//空开报警类型：1、漏电报警；2、漏电预警；3、温度报警；4、短路报警；5、打火报警；6、三项报警；7、窃电报警
	private Integer alarmType;
	//报警时间
	private Date alarmTime;
	private Integer hostid;
	private String nno;
	private String mac;
	/**
	 * 设置：空开报警id
	 */
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}
	/**
	 * 获取：空开报警id
	 */
	public Integer getAlarmId() {
		return alarmId;
	}
	/**
	 * 设置：报警类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：报警类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：设备id
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备id
	 */
	public Integer getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：空开报警类型：1、漏电报警；2、漏电预警；3、温度报警；4、短路报警；5、打火报警；6、三项报警；7、窃电报警
	 */
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	/**
	 * 获取：空开报警类型：1、漏电报警；2、漏电预警；3、温度报警；4、短路报警；5、打火报警；6、三项报警；7、窃电报警
	 */
	public Integer getAlarmType() {
		return alarmType;
	}
	/**
	 * 设置：报警时间
	 */
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	/**
	 * 获取：报警时间
	 */
	public Date getAlarmTime() {
		return alarmTime;
	}

	public Integer getHostid() {
		return hostid;
	}

	public void setHostid(Integer hostid) {
		this.hostid = hostid;
	}

	public String getNno() {
		return nno;
	}

	public void setNno(String nno) {
		this.nno = nno;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}
