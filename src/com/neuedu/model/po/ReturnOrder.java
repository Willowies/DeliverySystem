package com.neuedu.model.po;

import java.util.Date;

public class ReturnOrder {
	private int returnOrderId;
	private NewOrder newOrder;
	private int returnQuantity;
	private String receiverName;
	private String returnReason;
	private Date generateDate;//生成日期
	private Date returnDate;//要求完成日期
	private float returnTotal;
	private int orderState;
	private int employeeId;
	private String deliverRequest;
	private int status;
	private String operator;
	private Date operateDate;
	
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public Date getGenerateDate() {
		return generateDate;
	}
	public void setGenerateDate(Date generaDate) {
		this.generateDate = generaDate;
	}
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	
}
