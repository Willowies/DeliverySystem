package com.neuedu.model.po;

public class DeliveryReturnProduct {
	private int productId;
	private String productName;
	private String productCode;
	private String supName;
	private int now;
	private int stock;
	private int returnQ;
	
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getReturnQ() {
		return returnQ;
	}
	public void setReturnQ(int returnQ) {
		this.returnQ = returnQ;
	}
	
}
