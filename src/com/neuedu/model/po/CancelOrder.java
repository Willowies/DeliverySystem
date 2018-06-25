package com.neuedu.model.po;

import java.util.Date;

public class CancelOrder {
	private int cancelOrderId;
	private NewOrder newOrder = new NewOrder();
	private int employeeId;
	private String cancelReason;
	private Date cancelDate;
	//虽然订单中不存在删改，但是存在修改新订订单的行为，所以封装操作者名字和时间用于数据维护
	private String operator;
	private Date operateDate;
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
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operatorDate) {
		this.operateDate = operatorDate;
	}
	
	
}
