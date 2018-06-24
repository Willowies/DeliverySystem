package com.neuedu.model.po;

import java.util.Date;

public class ProductTransferOrder {
	private int productTransferId;
	private int workId;
	private int productId;
	private String produtName;
	private int productQuantity;
	private String productUnit;
	private Date planOutDate;
	private int outWarehouseId;
	private int inWarehouseId;
	private int orderId;
	
	public String getProdutName() {
		return produtName;
	}
	public void setProdutName(String produtName) {
		this.produtName = produtName;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public int getOutWarehouseId() {
		return outWarehouseId;
	}
	public void setOutWarehouse(int outWarehouseId) {
		this.outWarehouseId = outWarehouseId;
	}
	public int getInWarehouseId() {
		return inWarehouseId;
	}
	public void setInWarehouseId(int inWarehouseId) {
		this.inWarehouseId = inWarehouseId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductTransferId() {
		return productTransferId;
	}
	public void setProductTransferId(int productTransferId) {
		this.productTransferId = productTransferId;
	}
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
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
	public Date getPlanOutDate() {
		return planOutDate;
	}
	public void setPlanOutDate(Date planOutDate) {
		this.planOutDate = planOutDate;
	}

}
