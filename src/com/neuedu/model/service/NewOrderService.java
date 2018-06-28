package com.neuedu.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neuedu.model.dao.NewOrderDAO;
import com.neuedu.model.dao.NewOrderDAOImp;
import com.neuedu.model.po.NewOrder;
import com.neuedu.utils.DBUtil;

public class NewOrderService {
	private NewOrderService(){
	}
	
	private static NewOrderService service = new NewOrderService();
	
	public static NewOrderService getInstance(){
		return service;
	}
	public void creatNewOrder(NewOrder newOrder){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		dao.creatNewOrder(newOrder);
		DBUtil.closeConn(conn);
	}
	public void deleteNewOrderById(int newOrderId,String operator,Date operatorDate){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		dao.deleteNewOrderById(newOrderId, operator, operatorDate);
		DBUtil.closeConn(conn);
	}
	public void setNewOrderState(int orderId,int orderState,String operator,Date operatorDate){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		dao.setNewOrderState(orderId,orderState,operator,operatorDate);
		DBUtil.closeConn(conn);
	}
	public void updateNewOrder(NewOrder newOrder){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		dao.updateNewOrder(newOrder);
		DBUtil.closeConn(conn);
	}
	public List<NewOrder> selectNewOrder(NewOrder newOrder){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		List<NewOrder> newOrders = dao.selectNewOrder(newOrder);
		DBUtil.closeConn(conn);
		return newOrders;
	}
	public List<NewOrder> selectNewOrderByPage(NewOrder newOrder,int pageSize,int pageNum){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		List<NewOrder> newOrders = dao.selectNewOrderByPage(newOrder,pageSize,pageNum);
		DBUtil.closeConn(conn);
		return newOrders;
	}
	public int selectNewOrderPageCount(NewOrder newOrder,int pageSize){
		Connection conn = DBUtil.getConn();
		NewOrderDAO dao = new NewOrderDAOImp(conn);
		int newOrderPageCount = dao.selectNewOrderPageCount(newOrder, pageSize);
		DBUtil.closeConn(conn);
		return newOrderPageCount;
	}
}
