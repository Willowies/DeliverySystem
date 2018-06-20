package com.neuedu.model.po;

import java.util.Date;

public class ProductTransferOrder {
	private int productTransferId;
	private int workId;
	private int productId;
	private String produtName;
	private int productQuantity;
	private float productAmount;
	
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
	public String getProdutName() {
		return produtName;
	}
	public void setProdutName(String produtName) {
		this.produtName = produtName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public float getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(float productAmount) {
		this.productAmount = productAmount;
	}
	public Date getPlanOutDate() {
		return planOutDate;
	}
	public void setPlanOutDate(Date planOutDate) {
		this.planOutDate = planOutDate;
	}
	private Date planOutDate;

}
