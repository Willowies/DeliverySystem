package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.*;
import com.neuedu.model.po.*;
import com.neuedu.utils.DBUtil;

public class DispatchService {

		//����ģʽ
		private static DispatchService service = new DispatchService();
		private DispatchService(){}
		public static DispatchService getInstance(){
			return service;
		}
		
		//��ѯ�¶�����
		public List<NewOrder> searchNewOrder(NewOrder newOrder) {
			Connection conn = DBUtil.getConn();
			NewOrderDAO dao = new NewOrderDAOImp(conn);
			List<NewOrder> l =  dao.selectNewOrder(newOrder);
			DBUtil.closeConn(conn);
			return l;
		}
		
		//��ҳ��ѯ�¶�����
		public List<NewOrder> searchNewOrderByPage(NewOrder newOrder,int page,int size) {
			Connection conn = DBUtil.getConn();
			NewOrderDAO dao = new NewOrderDAOImp(conn);
			List<NewOrder> l =  dao.selectNewOrder(newOrder);
			DBUtil.closeConn(conn);
			return l;
		}
		
		//��ѯ�˻�����
		public List<ReturnOrder> searchReturnOrder(ReturnOrder returnOrder) {
			Connection conn = DBUtil.getConn();
			ReturnOrderDAO dao = new ReturnOrderDAOImp(conn);
			return dao.selectReturnOrderByPage(returnOrder, 6, 1);
		}
		
		
		//��ѯ����
		public List<WorkOrder> searchWorkOrder(WorkOrder order){
			Connection conn = DBUtil.getConn();
			WorkOrderDAO dao = new WorkOrderDAOImp(conn);
			List<WorkOrder> l =  dao.searchWorkOrder(order);
			DBUtil.closeConn(conn);
			return l;
		}
		
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
}
