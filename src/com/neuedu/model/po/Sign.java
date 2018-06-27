package com.neuedu.model.po;

import java.util.Date;

public class Sign {
	private int signId;//签收单号
	private int workId;//任务单号
	private Date deliveryDate;//送货日期
	private int receiptNeedOrNot;//是否要发票
	private int clearingStatus;//结算状态
	private String remark;//备注
	private int customerFeedback;//客户反馈
	private String customerSignature;//客户签名
	private int orderId;//订单号
	private int workType;//任务类型
	private String productName;//商品名称
	private float productPrice;//单价
	private int productQuantity;//商品数量
	private float total;//商品总价
	private String customerName;//客户姓名
	private String customerPhone;//客户联系电话
	private int postCode;//邮编
	private String deliveryAddress;//送货地址
	private String deliveryRequirement;//送货要求
	private int deliverySubstation;//送货分站
	private String substationPhone;//分站电话
	private int status;
	private String operator;
	private Date operateDate;
	
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
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryRequirement() {
		return deliveryRequirement;
	}
	public void setDeliveryRequirement(String deliveryRequirement) {
		this.deliveryRequirement = deliveryRequirement;
	}
	public int getDeliverySubstation() {
		return deliverySubstation;
	}
	public void setDeliverySubstation(int deliverySubstation) {
		this.deliverySubstation = deliverySubstation;
	}
	public String getSubstationPhone() {
		return substationPhone;
	}
	public void setSubstationPhone(String substationPhone) {
		this.substationPhone = substationPhone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
}
