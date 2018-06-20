package com.neuedu.model.po;

import java.util.Date;

public class ReturnOrder {
	private int returnOrderId;
	private NewOrder newOrder;
	private int returnQuantity;
	private String receiverName;
	private String returnReason;
	private Date returnDate;
	private float returnTotal;
	private String deliverRequest;
	private int status;
	private String operator;
	private String operateDate;
	public int getReturnOrderId() {
		return returnOrderId;
	}
	public void setReturnOrderId(int returnOrderId) {
		this.returnOrderId = returnOrderId;
	}
	public NewOrder getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(NewOrder newOrder) {
		this.newOrder = newOrder;
	}
	public int getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(int returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public float getReturnTotal() {
		return returnTotal;
	}
	public void setReturnTotal(float returnTotal) {
		this.returnTotal = returnTotal;
	}
	public String getDeliverRequest() {
		return deliverRequest;
	}
	public void setDeliverRequest(String deliverRequest) {
		this.deliverRequest = deliverRequest;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	
}
