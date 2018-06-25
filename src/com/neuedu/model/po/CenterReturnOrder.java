package com.neuedu.model.po;

import java.util.Date;

public class CenterReturnOrder {
	private int returnOrderId;
	private int productId;
	private int productQuantity;
	private Date returnDate;
	private int supId;
	private String productName;
	private float productPrice;
	private float orderAmount;//¶©µ¥½ð¶î
	public int getReturnOrderId() {
		return returnOrderId;
	}
	public void setReturnOrderId(int returnOrderId) {
		this.returnOrderId = returnOrderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public float getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}
}
