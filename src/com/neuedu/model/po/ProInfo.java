package com.neuedu.model.po;

public class ProInfo {
	private String productName;
	private String collectionStatus;
	private int deliveryNum;
	private float totalCollection;
	private float totalRefund;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCollectionStatus() {
		return collectionStatus;
	}
	public void setCollectionStatus(String collectionStatus) {
		this.collectionStatus = collectionStatus;
	}
	public int getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(int deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public float getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(float totalCollection) {
		this.totalCollection = totalCollection;
	}
	public float getTotalRefund() {
		return totalRefund;
	}
	public void setTotalRefund(float totalRefund) {
		this.totalRefund = totalRefund;
	}
}
