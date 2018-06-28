package com.neuedu.model.po;

import java.sql.Date;

public class FirstClass {
	
	
	private int firstClassId;
	private String firstClassName;
	private String firstClassDescription;
	private int status;
	private String operator;
	private Date operateDate;
	
	public int getFirstClassId() {
		return firstClassId;
	}
	public void setFirstClassId(int firstClassId) {
		this.firstClassId = firstClassId;
	}
	public String getFirstClassName() {
		return firstClassName;
	}
	public void setFirstClassName(String firstClassName) {
		this.firstClassName = firstClassName;
	}
	public String getFirstClassDescription() {
		return firstClassDescription;
	}
	public void setFirstClassDescription(String firstClassDescription) {
		this.firstClassDescription = firstClassDescription;
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
	

}
