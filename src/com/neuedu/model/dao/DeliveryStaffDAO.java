package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.DeliveryStaff;
import com.neuedu.model.po.WorkOrder;

public interface DeliveryStaffDAO {
	public List<DeliveryStaff> getDeliveryStaff(int employeeId);
}
