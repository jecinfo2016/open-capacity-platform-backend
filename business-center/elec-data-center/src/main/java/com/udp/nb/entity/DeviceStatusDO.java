package com.udp.nb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class DeviceStatusDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer deviceId;
	private Integer status;
	private BigDecimal temperature;
	private BigDecimal current;
	private BigDecimal voltage;
	private BigDecimal power;

	private Date recordtime;

	private Integer hostid;
	private String nno;
	private String mac;
	private String type;
	private Integer openstatus;
	private Integer alarmstatus;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public BigDecimal getCurrent() {
		return current;
	}

	public void setCurrent(BigDecimal current) {
		this.current = current;
	}

	public BigDecimal getVoltage() {
		return voltage;
	}

	public void setVoltage(BigDecimal voltage) {
		this.voltage = voltage;
	}

	public BigDecimal getPower() {
		return power;
	}

	public void setPower(BigDecimal power) {
		this.power = power;
	}

	public Date getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
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

	public Integer getOpenstatus() {
		return openstatus;
	}

	public void setOpenstatus(Integer openstatus) {
		this.openstatus = openstatus;
	}

	public Integer getAlarmstatus() {
		return alarmstatus;
	}

	public void setAlarmstatus(Integer alarmstatus) {
		this.alarmstatus = alarmstatus;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
