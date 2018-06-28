package com.neuedu.model.po;

import java.util.Date;

public class Invoice {
	private int invoiceId;
	private float invoiceAmount;
	private Date registerDate;
	private Date receiveDate;
	private String receivedPerson;
	private String substationName;
	private int state;
	private String stateInfo;
	private Date voidDate;
	private int workId;
	private int orderId;
	private int Status;
	private String Operator;
	private Date OperateDate;
	private String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public float getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceivedPerson() {
		return receivedPerson;
	}
	public void setReceivedPerson(String receivedPerson) {
		this.receivedPerson = receivedPerson;
	}
	public String getSubstationName() {
		return substationName;
	}
	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getVoidDate() {
		return voidDate;
	}
	public void setVoidDate(Date voidDate) {
		this.voidDate = voidDate;
	}
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public Date getOperateDate() {
		return OperateDate;
	}
	public void setOperateDate(Date operateDate) {
		OperateDate = operateDate;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	
}
