package com.neuedu.model.po;

public class Product {
	private int productId;
	private String productName;
	private int firstClassId=0;
	private int secondClassId=0;
	private float productPrice = 1;
	private float productDiscount = 1;
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
	public int getFirstClassId() {
		return firstClassId;
	}
	public void setFirstClassId(int firstClassId) {
		this.firstClassId = firstClassId;
	}
	public int getSecondClassId() {
		return secondClassId;
	}
	public void setSecondClassId(int secondClassId) {
		this.secondClassId = secondClassId;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public float getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(float productDiscount) {
		this.productDiscount = productDiscount;
	}
	
}
