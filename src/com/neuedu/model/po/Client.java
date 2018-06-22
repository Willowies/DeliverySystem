package com.neuedu.model.po;

import java.util.Date;

public class Client {
	private int clientId;
	private String clientIc;
	private String clientName;
	private String clientWrokPlace;
	private String clientPhoneNumber;
	private String clientMobilePhone;
	private String clientContactAddress;
	private int clientPostCode;
	private String clientEmail;
	private int status;
	private String operator ;
	private Date operateDate;
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientIc() {
		return clientIc;
	}
	public void setClientIc(String clientIc) {
		this.clientIc = clientIc;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}
	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}
	public String getClientMobilePhone() {
		return clientMobilePhone;
	}
	public void setClientMobilePhone(String clientMobilePhone) {
		this.clientMobilePhone = clientMobilePhone;
	}
	public String getClientWrokPlace() {
		return clientWrokPlace;
	}
	public void setClientWrokPlace(String clientWrokPlace) {
		this.clientWrokPlace = clientWrokPlace;
	}
	public String getClientContactAddress() {
		return clientContactAddress;
	}
	public void setClientContactAddress(String clientContactAddress) {
		this.clientContactAddress = clientContactAddress;
	}
	public int getClientPostCode() {
		return clientPostCode;
	}
	public void setClientPostCode(int clientPostCode) {
		this.clientPostCode = clientPostCode;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
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
