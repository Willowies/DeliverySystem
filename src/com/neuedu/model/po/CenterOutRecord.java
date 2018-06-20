package com.neuedu.model.po;

import java.util.Date;

public class CenterOutRecord {
	private int centerOutId;
	private int productId;
	private String productName;
	private float price;
	private String manufacturer;
	private int productQuantity;
	private Date centerOutDate;
	private int totalQuantity;
	private float totalAmount;
	private String remark;
	
	public int getCenterOutId() {
		return centerOutId;
	}
	public void setCenterOutId(int centerOutId) {
		this.centerOutId = centerOutId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Date getCenterOutDate() {
		return centerOutDate;
	}
	public void setCenterOutDate(Date centerOutDate) {
		this.centerOutDate = centerOutDate;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
