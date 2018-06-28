package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Warehouse;

public interface WarehouseDAO {
	public void addWarehouse(Warehouse warehouse);
	public List<Warehouse> selectWarehouse(String warehouseName);
	public void updateWarehouse(Warehouse warehouse);
	public void deleteWarehouse(String warehouseId);
	public Warehouse getWarehouseById(int warehouseId);
}
