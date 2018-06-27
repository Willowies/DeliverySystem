package com.neuedu.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neuedu.model.dao.NewOrderDAO;
import com.neuedu.model.dao.NewOrderDAOImp;
import com.neuedu.model.dao.SubReturnRecordDAO;
import com.neuedu.model.dao.SubReturnRecordDAOImp;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

public class WarehouseReturnService {
	// 单例模式
	private static WarehouseReturnService service = new WarehouseReturnService();

	private WarehouseReturnService(){}

	public static WarehouseReturnService getInstance() {
		return service;
	}
	
	//搜索任务单
	public List<WorkOrder> searchWorkOrder(int workId){
		Connection conn = DBUtil.getConn();
		WorkOrderDAO dao = new WorkOrderDAOImp(conn);
		List<WorkOrder> l =  dao.searchWorkOrderWithProduct(workId);
		DBUtil.closeConn(conn);
		return l;
	}

	//退货登记
	public void returnRecord(int workId, int q,String operator) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
			dao.returnRecord(workId,q, operator);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}

	public List<SubReturnRecord> searchReturnProduct(Date start, Date end, int warehouseId) {
		Connection conn = DBUtil.getConn();
		SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
		List<SubReturnRecord> l =  dao.searchReturnProduct(start,end,warehouseId);
		DBUtil.closeConn(conn);
		return l;
	}

	public void subReturnOut(int productId, int productQuantity, Date start, Date end, int warehouseId) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
			dao.subReturnOut(productId,productQuantity,start,end,warehouseId);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		
	}

	public List<SubReturnRecord> searchSubReturnProduct(int id) {
		Connection conn = DBUtil.getConn();
		SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
		List<SubReturnRecord> l =  dao.searchSubReturnProduct(id);
		DBUtil.closeConn(conn);
		return l;
	}

	public void centerReturnIn(int orderId, int productId, int productQuantity) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
			dao.centerReturnIn(productId,productQuantity,orderId);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
	}

	public List<SubReturnRecord> searchCenterReturnProduct(int id) {
		Connection conn = DBUtil.getConn();
		SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
		List<SubReturnRecord> l =  dao.searchCenterReturnProduct(id);
		DBUtil.closeConn(conn);
		return l;
	}

	public void centerReturnOut(int orderId, int productId, int productQuantity) {
		Connection conn = DBUtil.getConn();
		DBUtil.beginTransaction(conn);
		try {
			SubReturnRecordDAO dao = new SubReturnRecordDAOImp(conn);
			dao.centerReturnOut(productId,productQuantity,orderId);
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
		}finally{
			DBUtil.closeConn(conn);
		}
		
	}
	
	
	
	
}
