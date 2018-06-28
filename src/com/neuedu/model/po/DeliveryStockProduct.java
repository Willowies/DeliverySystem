package com.neuedu.model.po;

import java.util.Date;

public class DeliveryStockProduct {
	
	private int productId;
	private String firstClass;
	private String secondClass;
	private String productName;
	private String productCode;
	private String productUnit;
	private int now;
	private int warning;
	private int lack;
	private int stock;
	private Date stockDate;
	private int max;
	private String warehouseName;
	private int total;
	private int allocate;
	private int allocated;
	private int returnQ;
	
	
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAllocate() {
		return allocate;
	}
	public void setAllocate(int allocate) {
		this.allocate = allocate;
	}
	public int getAllocated() {
		return allocated;
	}
	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}
	public int getReturnQ() {
		return returnQ;
	}
	public void setReturnQ(int returnQ) {
		this.returnQ = returnQ;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public int getWarning() {
		return warning;
	}
	public void setWarning(int warning) {
		this.warning = warning;
	}
	public int getLack() {
		return lack;
	}
	public void setLack(int lack) {
		this.lack = lack;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	
	
}
