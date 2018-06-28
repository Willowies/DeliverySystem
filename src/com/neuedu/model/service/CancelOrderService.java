package com.neuedu.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neuedu.model.dao.CancelOrderDAO;
import com.neuedu.model.dao.CancelOrderDAOImp;
import com.neuedu.model.dao.NewOrderDAO;
import com.neuedu.model.dao.NewOrderDAOImp;
import com.neuedu.model.po.CancelOrder;
import com.neuedu.utils.DBUtil;

public class CancelOrderService {
	private CancelOrderService(){
	}
	private static CancelOrderService service = new CancelOrderService();
	public static CancelOrderService getInstance(){
		return service;
	}
	
	public void cancelOrder(CancelOrder cancelOrder){
		Connection conn = DBUtil.getConn();
		CancelOrderDAO canceldao = new CancelOrderDAOImp(conn);
		canceldao.creatCancelOrder(cancelOrder);
		int newOrderId = cancelOrder.getNewOrder().getOrderId();
		int orderState = 2;
		/*1.��Ա��idȥ��Ա������
		 *2.ҳ�洫����Ա������
		String operator = cancelOrder.getEmployeeId();
		Date operatorDate = cancelOrder.getOperatorDate();
		*/
		//���¶�������״̬����Ϊ�˶�״̬
		Connection conn2 = DBUtil.getConn();
		NewOrderDAO newdao = new NewOrderDAOImp(conn2);
		String operator = cancelOrder.getOperator();
		Date operatorDate = cancelOrder.getOperateDate();
		newdao.setNewOrderState(newOrderId, orderState, operator, operatorDate);
		DBUtil.closeConn(conn2);
	}
	public List<CancelOrder> selectCancelOrder(CancelOrder c){
		Connection conn = DBUtil.getConn();
		CancelOrderDAO canceldao = new CancelOrderDAOImp(conn);
		List<CancelOrder> cancelOrders = canceldao.selectCancelOrder(c);
		DBUtil.closeConn(conn);
		return cancelOrders;
	}
	public List<CancelOrder> selectCancelOrderByPage(CancelOrder cancelOrder,int pageSize,int pageNum){
		Connection conn = DBUtil.getConn();
		CancelOrderDAO canceldao = new CancelOrderDAOImp(conn);
		List<CancelOrder> cancelOrders = canceldao.selectCancelOrderByPage(cancelOrder,pageSize,pageNum);
		DBUtil.closeConn(conn);
		return cancelOrders;
	}
	public int selectCancelOrderPageCount(CancelOrder cancelOrder,int pageSize){
		Connection conn = DBUtil.getConn();
		CancelOrderDAO canceldao = new CancelOrderDAOImp(conn);
		int cancelOrderPageCount = canceldao.selectCancelOrderPageCount(cancelOrder, pageSize);
		DBUtil.closeConn(conn);
		return cancelOrderPageCount;
	}
}
