package com.neuedu.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.neuedu.model.dao.DeliveryProductDAO;
import com.neuedu.model.dao.DeliveryProductDAOImp;
import com.neuedu.model.dao.SubReturnRecordDAO;
import com.neuedu.model.dao.SubReturnRecordDAOImp;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
import com.neuedu.model.po.DeliveryReturnProduct;
import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.DeliveryWarehouseOrder;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

public class DeliveryPartService {
	//µ¥ÀýÄ£Ê½
	private static DeliveryPartService service = new DeliveryPartService();

	private DeliveryPartService(){}

	public static DeliveryPartService getInstance() {
		return service;
	}

	public List<DeliveryStockProduct> searchLackProduct(String productName) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryStockProduct> l =  dao.searchStockProduct(productName);
		DBUtil.closeConn(conn);
		return l;
	}

	public void stock(int productId, int productQuantity, String operator) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
			dao.stock(productId,productQuantity, operator);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}

	public List<DeliveryReturnProduct> searchReturnProduct(String supName, String productCode, Date start, Date end) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryReturnProduct> l =  dao.searchReturnProduct(supName,productCode,start,end);
		DBUtil.closeConn(conn);
		return l;
	}

	public void returnProduct(int productId, int productQuantity, String operator) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
			dao.returnProduct(productId,productQuantity, operator);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		
	}

	public List<DeliveryStockProduct> searchWarehouseProduct(String productName) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryStockProduct> l =  dao.searchWarehouseProduct(productName);
		DBUtil.closeConn(conn);
		return l;
	}

	public void modify(int productId, int warning, int max, String operator) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
			dao.modify(productId,warning,max, operator);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		
	}

	public List<DeliveryStockProduct> searchWarehouseValue(String productName, String warehouseName) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryStockProduct> l =  dao.searchWarehouseValue(productName,warehouseName);
		DBUtil.closeConn(conn);
		return l;
	}

	public List<DeliveryWarehouseOrder> searchOrder(String productName, Date start, Date end) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryWarehouseOrder> l =  dao.searchOrder(productName,start,end);
		DBUtil.closeConn(conn);
		return l;
	}

	public List<DeliveryWarehouseOrder> orderStatistics(Date start, Date end) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryWarehouseOrder> l =  dao.orderStatistics(start,end);
		DBUtil.closeConn(conn);
		return l;
	}

	public List<DeliveryWarehouseOrder> substationStatistics(Date start, Date end) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryWarehouseOrder> l =  dao.substationStatistics(start,end);
		DBUtil.closeConn(conn);
		return l;
	}

	public HashMap<String, String> satisfyStatistics(Date start, Date end) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		HashMap<String, String> l =  dao.satisfyStatistics(start,end);
		DBUtil.closeConn(conn);
		return l;
	}

}
