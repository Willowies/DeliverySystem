package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.*;
import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;

public class DispatchService {

		//单例模式
		private static DispatchService service = new DispatchService();
		private DispatchService(){}
		public static DispatchService getInstance(){
			return service;
		}
		
		//查询新订订单
		public List<NewOrder> searchNewOrder(NewOrder newOrder) {
			Connection conn = DBUtil.getConn();
			NewOrderDAO dao = new NewOrderDAOImp(conn);
			List<NewOrder> l =  dao.selectNewOrder(newOrder);
			DBUtil.closeConn(conn);
			return l;
		}
		
		
		
		//查询退货订单
		public List<ReturnOrder> searchReturnOrder(ReturnOrder returnOrder) {
			Connection conn = DBUtil.getConn();
			ReturnOrderDAO dao = new ReturnOrderDAOImp(conn);
			List<ReturnOrder> l =  dao.selectReturnOrder(returnOrder);
			DBUtil.closeConn(conn);
			return l;
		}
		
		
		//查询任务单
		public List<WorkOrder> searchWorkOrder(WorkOrder order){
			Connection conn = DBUtil.getConn();
			WorkOrderDAO dao = new WorkOrderDAOImp(conn);
			List<WorkOrder> l =  dao.searchWorkOrder(order);
			DBUtil.closeConn(conn);
			return l;
		}
		
		//调度订单
		public void dispatchOrder(int orderId,int orderType,String warehouseName,String remark,String operator) {
			Connection conn = DBUtil.getConn();
			DBUtil.beginTransaction(conn);
			try {
				WorkOrderDAO dao = new WorkOrderDAOImp(conn);
				dao.createWorkOrder(orderId, orderType, warehouseName, remark, operator);
				DBUtil.commit(conn);
			} catch (Exception e) {
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}

		}
		
		//查询缺货订单
		public List<NewOrder> searchLackOrder(NewOrder newOrder) {
			Connection conn = DBUtil.getConn();
			WorkOrderDAO dao = new WorkOrderDAOImp(conn);
			List<NewOrder> l =  dao.searchLackOrder(newOrder);
			DBUtil.closeConn(conn);
			return l;
		}
		
		//修改订单状态
		public void modify(int orderId,String operator) {
			Connection conn = DBUtil.getConn();
			DBUtil.beginTransaction(conn);
			try {
				WorkOrderDAO dao = new WorkOrderDAOImp(conn);
				dao.modifyLackStatus(orderId, operator);
				DBUtil.commit(conn);
			} catch (Exception e) {
				DBUtil.rollback(conn);
			}finally{
				DBUtil.closeConn(conn);
			}
		}
}
