package com.neuedu.model.po;

import java.util.Date;

public class CancelOrder {
	private int cancelOrderId;
	private NewOrder newOrder;
	private int employeeId;
	private String cancelReason;
	private Date cancelDate;
	//��Ȼ�����в�����ɾ�ģ����Ǵ����޸��¶���������Ϊ�����Է�װ���������ֺ�ʱ����������ά��
	private String operator;
	private Date operatorDate;
	public int getCancelOrderId() {
		return cancelOrderId;
	}
	public void setCancelOrderId(int cancelOrderId) {
		this.cancelOrderId = cancelOrderId;
	}
	
	public NewOrder getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(NewOrder newOrder) {
		this.newOrder = newOrder;
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	
}
