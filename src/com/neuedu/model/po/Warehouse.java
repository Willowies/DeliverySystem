package com.neuedu.model.po;

import java.util.Date;

public class Warehouse {
	private int warehouseId;//�ⷿId
	private String warehouseName;//�ⷿ����
	private String warehouseAddress;//�ⷿ��ַ
	private String warehouseKeeper;//���Ա
	private int warehouseRank;//�ⷿ����(����or��վ)
	private int status;//״̬
	private String operator;//������Ա
	private Date operateDate;//��������
	
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
