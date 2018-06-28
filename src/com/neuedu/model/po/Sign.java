package com.neuedu.model.po;

import java.util.Date;

public class Sign {
	private int signId;//绛炬敹鍗曞彿
	private int workId;//浠诲姟鍗曞彿
	private Date deliveryDate;//閫佽揣鏃ユ湡
	private int receiptNeedOrNot;//鏄惁瑕佸彂绁�
	private int clearingStatus;//缁撶畻鐘舵��
	private String remark;//澶囨敞
	private int customerFeedback;//瀹㈡埛鍙嶉
	private String customerSignature;//瀹㈡埛绛惧悕
	private int orderId;//璁㈠崟鍙�
	private int workType;//浠诲姟绫诲瀷
	private String productName;//鍟嗗搧鍚嶇О
	private float productPrice;//鍗曚环
	private int productQuantity;//鍟嗗搧鏁伴噺
	private float total;//鍟嗗搧鎬讳环
	private String customerName;//瀹㈡埛濮撳悕
	private String customerPhone;//瀹㈡埛鑱旂郴鐢佃瘽
	private int postCode;//閭紪
	private String deliveryAddress;//閫佽揣鍦板潃
	private String deliveryRequirement;//閫佽揣瑕佹眰
	private int deliverySubstation;//閫佽揣鍒嗙珯
	private String substationPhone;//鍒嗙珯鐢佃瘽
	private int status;
	private String operator;
	private Date operateDate;
	private String substation;
	
	public String getSubstation() {
		return substation;
	}
	public void setSubstation(String substation) {
		this.substation = substation;
	}
	
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
