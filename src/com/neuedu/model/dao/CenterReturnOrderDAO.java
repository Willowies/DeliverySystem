package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.CenterReturnOrder;

public interface CenterReturnOrderDAO {
	
	public List<CenterReturnOrder> selectCROrder(String supplier,Date date,String product);
	public void clearSuppiler(int[] ids,String employeeName);
	public List<CenterReturnOrder> selectClearedSuppiler(String supplier, Date date, String product);
}
