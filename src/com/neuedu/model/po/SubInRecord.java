package com.neuedu.model.po;

import java.util.Date;

public class SubInRecord {
	private int subWarehouseInId;
	private int distributionId;
	private int warehouseId;
	private int productId;
	private String productName;
	private int expectedQuantity;
	private int acturalQuantity;
	private Date inDate;
	private String remark;
	
	public int getSubWarehouseInId() {
		return subWarehouseInId;
	}
	public void setSubWarehouseInId(int subWarehouseInId) {
		this.subWarehouseInId = subWarehouseInId;
	}
	public int getDistributionId() {
		return distributionId;
	}
	public void setDistributionId(int distributionId) {
		this.distributionId = distributionId;
	}
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
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
	public int getExpectedQuantity() {
		return expectedQuantity;
	}
	public void setExpectedQuantity(int expectedQuantity) {
		this.expectedQuantity = expectedQuantity;
	}
	public int getActuralQuantity() {
		return acturalQuantity;
	}
	public void setActuralQuantity(int acturalQuantity) {
		this.acturalQuantity = acturalQuantity;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
