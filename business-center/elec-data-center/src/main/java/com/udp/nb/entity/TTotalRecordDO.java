package com.udp.nb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class TTotalRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer deviceId;
	private Integer hostid;
	private String nno;
	private String mac;
	private Integer dn;

	private BigDecimal voltage;
	private BigDecimal leakageCurre;
	private BigDecimal temperature;
	private BigDecimal current;
	private BigDecimal ps;

	private Date recordTime;
	private String recordType;
	private Date createTime;
	private String createName;

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

	public Integer getDn() {
		return dn;
	}

	public void setDn(Integer dn) {
		this.dn = dn;
	}

	public BigDecimal getVoltage() {
		return voltage;
	}

	public void setVoltage(BigDecimal voltage) {
		this.voltage = voltage;
	}

	public BigDecimal getLeakageCurre() {
		return leakageCurre;
	}

	public void setLeakageCurre(BigDecimal leakageCurre) {
		this.leakageCurre = leakageCurre;
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

	public BigDecimal getPs() {
		return ps;
	}

	public void setPs(BigDecimal ps) {
		this.ps = ps;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}
