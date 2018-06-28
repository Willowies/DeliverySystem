package com.neuedu.model.po;

import java.util.Date;

public class DeliveryWarehouseOrder {
	private String type;
	private int orderId;
	private String warehouseName;
	private String productCode;
	private String productName;
	private float price;
	private int quantity;
	private float total;
	private Date date;
	private String rank;
	private String firstClass;
	private String secondClass;
	private int orderQ;
	
	
	
	public int getOrderQ() {
		return orderQ;
	}
	public void setOrderQ(int orderQ) {
		this.orderQ = orderQ;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getFirstClass() {
		return firstClass;
	}
	public void setFirstClass(String firstClass) {
		this.firstClass = firstClass;
	}
	public String getSecondClass() {
		return secondClass;
	}
	public void setSecondClass(String secondClass) {
		this.secondClass = secondClass;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String procductCode) {
		this.productCode = procductCode;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
