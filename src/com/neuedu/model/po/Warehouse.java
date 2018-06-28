package com.neuedu.model.po;

import java.util.Date;

public class Warehouse {
	private int warehouseId;//库房Id
	private String warehouseName;//库房名称
	private String warehouseAddress;//库房地址
	private String warehouseKeeper;//库管员
	private int warehouseRank;//库房级别(中心or分站)
	private int status;//状态
	private String operator;//操作人员
	private Date operateDate;//操作日期
	
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseAddress() {
		return warehouseAddress;
	}
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}
	public String getWarehouseKeeper() {
		return warehouseKeeper;
	}
	public void setWarehouseKeeper(String warehouseKeeper) {
		this.warehouseKeeper = warehouseKeeper;
	}
	public int getWarehouseRank() {
		return warehouseRank;
	}
	public void setWarehouseRank(int warehouseRank) {
		this.warehouseRank = warehouseRank;
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
	
	
	public Warehouse() {
		super();
	}
	
	
	
	
	
}
