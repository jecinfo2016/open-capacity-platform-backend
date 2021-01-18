package com.udp.nb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class MessageLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String msg_id;

	private String msg;

	private Date receiveHttpTime;

	private Date sendTime;

	private Date receiptTime;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getReceiveHttpTime() {
		return receiveHttpTime;
	}

	public void setReceiveHttpTime(Date receiveHttpTime) {
		this.receiveHttpTime = receiveHttpTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}
}
