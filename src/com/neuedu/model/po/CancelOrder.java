package com.neuedu.model.po;

import java.util.Date;

public class CancelOrder {
	private int cancelOrderId;
	private int newOrderId;
	private String cancelReason;
	private Date cancelDate;
	public int getCancelOrderId() {
		return cancelOrderId;
	}
	public void setCancelOrderId(int cancelOrderId) {
		this.cancelOrderId = cancelOrderId;
	}
	public int getNewOrderId() {
		return newOrderId;
	}
	public void setNewOrderId(int newOrderId) {
		this.newOrderId = newOrderId;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	
}
