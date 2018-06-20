package com.neuedu.model.po;

import java.util.Date;

public class DistributionOrder {
	private int distributionId;
	private int productTransferId;
	private String warehouseName;
	private int productId;
	private String productName;
	private float price;
	private String manufacturer;
	private int productQuantity;
	private Date centerOutDate;
	private String operator;
	private int totalQuantity;
	private float totalAmount;
	
	public int getDistributionId() {
		return distributionId;
	}
	public void setDistributionId(int distributionId) {
		this.distributionId = distributionId;
	}
	public int getProductTransferId() {
		return productTransferId;
	}
	public void setProductTransferId(int productTransferId) {
		this.productTransferId = productTransferId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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

}
