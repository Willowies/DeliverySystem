package com.neuedu.model.service;

import java.sql.Connection;

import com.neuedu.model.dao.PurchaseInOrderDAO;
import com.neuedu.model.dao.PurchaseInOrderDAOImp;
import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.utils.DBUtil;

public class WarehouseService {
	private WarehouseService(){}
	private static WarehouseService service = new WarehouseService();
	public static WarehouseService getInstance(){
		return service;
	}
	
	public PurchaseInOrder selectPurchaseInOrder(int purchaseId){
		Connection conn = DBUtil.getConn();
		PurchaseInOrderDAO purOrderDao = new PurchaseInOrderDAOImp(conn);
		return purOrderDao.selectPurchaseInOrder(purchaseId);
		
	}

}
