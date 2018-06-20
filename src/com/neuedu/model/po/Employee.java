package com.neuedu.model.po;

public class Employee {
	private int employeeId;
	private String employeeAccount;
	private String employeeName;
	private String employeePassword;
	private int centerType;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeAccount() {
		return employeeAccount;
	}
	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public int getCenterType() {
		return centerType;
	}
	public void setCenterType(int centerType) {
		this.centerType = centerType;
	}
	
}
