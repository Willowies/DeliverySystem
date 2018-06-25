package com.neuedu.model.po;

import java.util.Date;

public class Sign {
	private int signId;
	private int workId;
	private Date deliveryDate;
	private int receiptNeedOrNot;
	private int clearingStatus;
	private String remark;
	private int customerFeedback;
	private String customerSignature;
	private int orderId;//订单号
	private int signType;//送货退货
	private String productName;//商品名称
	private float productPrice;//商品单价
	private int productQuantity;//商品数量
	private float total;//商品总价
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getReceiptNeedOrNot() {
		return receiptNeedOrNot;
	}
	public void setReceiptNeedOrNot(int receiptNeedOrNot) {
		this.receiptNeedOrNot = receiptNeedOrNot;
	}
	public int getClearingStatus() {
		return clearingStatus;
	}
	public void setClearingStatus(int clearingStatus) {
		this.clearingStatus = clearingStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCustomerFeedback() {
		return customerFeedback;
	}
	public void setCustomerFeedback(int customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public String getCustomerSignature() {
		return customerSignature;
	}
	public void setCustomerSignature(String customerSignature) {
		this.customerSignature = customerSignature;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSignType() {
		return signType;
	}
	public void setSignType(int signType) {
		this.signType = signType;
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
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
