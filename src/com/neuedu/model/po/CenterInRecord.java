package com.neuedu.model.po;

import java.util.Date;

public class CenterInRecord {
	private int centerInId;
	private int purchaseId;
	private String productName;
	private int expectedQuantity;
	private int acturalQuantity;
	private Date centerInDate;
	private String remark;
	
	public int getCenterInId() {
		return centerInId;
	}
	public void setCenterInId(int centerInId) {
		this.centerInId = centerInId;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getExpectedQuantity() {
		return expectedQuantity;
	}
	public void setExpectedQuantity(int expectedQuantity) {
		this.expectedQuantity = expectedQuantity;
	}
	public int getActuralQuantity() {
		return acturalQuantity;
	}
	public void setActuralQuantity(int acturalQuantity) {
		this.acturalQuantity = acturalQuantity;
	}
	public Date getCenterInDate() {
		return centerInDate;
	}
	public void setCenterInDate(Date centerInDate) {
		this.centerInDate = centerInDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
