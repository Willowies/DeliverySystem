package com.neuedu.model.po;

import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class NewOrder {
	private int orderId;
	private Client client;
	private Product product;
	private int productQuantity;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private int receiverPostCode;
	private float total;
	private int orderState;
	private int employeeId;
	private int whetherInvoice;
	private Date requireDate;
	private Date generaDate;
	private Data finishDate;
	private String newOrderRemark;
	private String deliverRequest;
	private int status;
	private String operator;
	private Date operateDate;
	
	
	public Data getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Data finishDate) {
		this.finishDate = finishDate;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public int getReceiverPostCode() {
		return receiverPostCode;
	}
	public void setReceiverPostCode(int receiverPostCode) {
		this.receiverPostCode = receiverPostCode;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getWhetherInvoice() {
		return whetherInvoice;
	}
	public void setWhetherInvoice(int whetherInvoice) {
		this.whetherInvoice = whetherInvoice;
	}
	public Date getRequireDate() {
		return requireDate;
	}
	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}
	public Date getGeneraDate() {
		return generaDate;
	}
	public void setGeneraDate(Date generaDate) {
		this.generaDate = generaDate;
	}
	public String getNewOrderRemark() {
		return newOrderRemark;
	}
	public void setNewOrderRemark(String newOrderRemark) {
		this.newOrderRemark = newOrderRemark;
	}
	public String getDeliverRequest() {
		return deliverRequest;
	}
	public void setDeliverRequest(String deliverRequest) {
		this.deliverRequest = deliverRequest;
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
