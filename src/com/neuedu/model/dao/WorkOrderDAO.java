package com.neuedu.model.dao;

import java.sql.Date;
import java.util.List;

import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.WorkOrder;

public  interface WorkOrderDAO {
	
	public List<WorkOrder> searchWorkOrder(WorkOrder order);
	
	public void createWorkOrder(int orderId,int orderType,String warehouseName,String remark,String operator);
	
	public List<NewOrder> searchLackOrder(NewOrder order);
	
	public void modifyLackStatus(int orderId,String operator);
}
