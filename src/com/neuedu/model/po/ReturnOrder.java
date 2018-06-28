package com.neuedu.model.po;

import java.util.Date;

public class ReturnOrder {
	private int returnOrderId;
	private NewOrder newOrder = new NewOrder();
	private int returnQuantity;
	private String returnReason;
	private Date returnDate;
	private String deliverRequest;
	private int employeeId;
	private int orderState;
	private float returnTotal;
	private int status;
	private String operator;
	private Date operateDate;
	private Date generateDate;
	private String productName;
	private String productUnit;
	private int productQuantity;
	private float total;
	private int newOrderId;
	
	public int getNewOrderId() {
		return newOrderId;
	}
	public void setNewOrderId(int newOrderId) {
		this.newOrderId = newOrderId;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
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
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public Date getGenerateDate() {
		return generateDate;
	}
	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}
	
	
}
