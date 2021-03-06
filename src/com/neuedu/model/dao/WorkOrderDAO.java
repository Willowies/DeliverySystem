package com.neuedu.model.dao;

import java.sql.Date;
import java.util.List;

import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.WarehouseNameInfo;
import com.neuedu.model.po.WorkOrder;

public  interface WorkOrderDAO {
	
	public List<WorkOrder> searchWorkOrder(WorkOrder order);
	
	public void createWorkOrder(int orderId,int orderType,String warehouseName,String remark,String operator);
	
	public List<NewOrder> searchLackOrder(NewOrder order);
	
	public void modifyLackStatus(int orderId,String operator);
	
	public List<WorkOrder> selectPageWork(int warehouseId, java.util.Date requireDate, int workStatus, int workType, int pageNum);
	
	public List<WorkOrder> selectPageWork(int warehouseId, int deliveryStaffId, java.util.Date requireDate, int workStatus, int workType, int pageNum);
	
	public int selectPageCount(int warehouseId, java.util.Date requireDate, int workStatus, int workType);

	public int selectPageCount(int warehouseId, int deliveryStaffId, java.util.Date requireDate, int workStatus, int workType);
	
	List<WorkOrder> searchWorkOrderByPage(WorkOrder order, int pageSize, int page);

	List<WorkOrder> searchWorkOrderWithProduct(int workId);

	public List<WarehouseNameInfo> getWarehosueInfo();
	
	public boolean selectWorkOrder(int workId);
	
	public void workAssign(int workId, int workStatus, int deliveryStaffId);
}
