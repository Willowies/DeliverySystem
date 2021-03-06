package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Sign;
import com.neuedu.model.po.WorkOrder;

public interface SignDAO {
	
	public List<Sign> selectSign(String substation,Date selectDate,String product);

	public void clearSubstation(int[] ids, String employeeName);

	public List<Sign> selectClearedSub(String substation, Date date, String product);
	
	public Sign getSign(WorkOrder workOrder);
}
