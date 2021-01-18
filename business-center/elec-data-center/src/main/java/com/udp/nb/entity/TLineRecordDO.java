package com.udp.nb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class TLineRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer lineId;
	private String state;
	private BigDecimal power;
	private BigDecimal voltage;
	private BigDecimal current;
	private BigDecimal temperature;
	private BigDecimal leakageCurre;

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

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getPower() {
		return power;
	}

	public void setPower(BigDecimal power) {
		this.power = power;
	}

	public BigDecimal getVoltage() {
		return voltage;
	}

	public void setVoltage(BigDecimal voltage) {
		this.voltage = voltage;
	}

	public BigDecimal getCurrent() {
		return current;
	}

	public void setCurrent(BigDecimal current) {
		this.current = current;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public BigDecimal getLeakageCurre() {
		return leakageCurre;
	}

	public void setLeakageCurre(BigDecimal leakageCurre) {
		this.leakageCurre = leakageCurre;
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
}
