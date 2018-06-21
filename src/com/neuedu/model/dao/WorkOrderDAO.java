package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.WorkOrder;

public  interface WorkOrderDAO {
	
	public List<WorkOrder> searchWorkOrder(WorkOrder order);
	
}
