package com.neuedu.model.po;

import java.util.Date;

public class Product {
	private int productId = 0;
	private String productCode;
	private String productName;
	private int firstClassId =0;
	private int secondClassId=0;
	private String productUnit;
	private float productPrice = 1;
	private float productDiscount = 1;
	private float productCost;
	private String productModel;
	private Supplier supplier;
	private String manufacturer;
	private Date expirationDate;
	private int returnAble;
	private String remark;
	private int status;
	private String operator;
	private Date operateDate;
	
	private int allocatableQuantity;//可分配数量，由库房得出，放在商品里为了传数据方便

	
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
	public int getFirstClassId() {
		return firstClassId;
	}
	public void setFirstClassId(int firstClassId) {
		this.firstClassId = firstClassId;
	}
	public int getSecondClassId() {
		return secondClassId;
	}
	public void setSecondClassId(int secondClassId) {
		this.secondClassId = secondClassId;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public float getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(float productDiscount) {
		this.productDiscount = productDiscount;
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
	public float getProductCost() {
		return productCost;
	}
	public void setProductCost(float productCost) {
		this.productCost = productCost;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getReturnAble() {
		return returnAble;
	}
	public void setReturnAble(int returnAble) {
		this.returnAble = returnAble;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getAllocatableQuantity() {
		return allocatableQuantity;
	}
	public void setAllocatableQuantity(int allocatableQuantity) {
		this.allocatableQuantity = allocatableQuantity;
	}
	
}
