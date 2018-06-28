package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.PurchaseInOrder;

public interface PurchaseInOrderDAO {
	public PurchaseInOrder selectPurchaseInOrder(int purchaseId);
	
	public  List<PurchaseInOrder> selectPIOrder(String supplier,Date date,String product);
	
	public void clearSuppiler(int[] ids,String employeeName);

	public List<PurchaseInOrder> selectClearedSuppiler(String supplier, Date date, String product);

}
