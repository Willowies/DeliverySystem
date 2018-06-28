package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.neuedu.model.dao.NewOrderDAO;
import com.neuedu.model.dao.NewOrderDAOImp;
import com.neuedu.model.dao.ReturnOrderDAO;
import com.neuedu.model.dao.ReturnOrderDAOImp;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.ReturnOrder;
import com.neuedu.utils.DBUtil;

public class ReturnOrderService {
	private ReturnOrderService(){
	}
	
	private static ReturnOrderService service = new ReturnOrderService();
	
	public static ReturnOrderService getInstance(){
		return service;
	}
	public void creatReturnOrder(ReturnOrder returnOrder){
		Connection conn = DBUtil.getConn();
		NewOrder n = returnOrder.getNewOrder();
		NewOrder newOrder = NewOrderService.getInstance().selectNewOrder(n).get(0);
		int returnQuantity = returnOrder.getReturnQuantity();
		int newQuantity = newOrder.getProductQuantity();
		
		int newOrderId = newOrder.getOrderId();
		int orderState = 0;
		String operator = returnOrder.getOperator();
		Date operatorDate = returnOrder.getOperateDate();
		//判断是否超出退货数量
		if(returnQuantity > newQuantity){//退货数量错误
			return;
		}else if(returnQuantity < newQuantity){//部分退货
			orderState = 5;
		}else if (returnQuantity == newQuantity){
			orderState = 4;
		}
		//判断是否可退货
		if(newOrder.getProduct().getReturnAble()==0) return;
		
		float newTotal = newOrder.getTotal();
		float returnTotal = newTotal/newQuantity * returnOrder.getReturnQuantity();
		returnOrder.setReturnTotal(returnTotal);
		ReturnOrderDAO returndao = new ReturnOrderDAOImp(conn);
		returndao.creatReturnOrder(returnOrder);
		//设置订单状态
		NewOrderDAO newdao = new NewOrderDAOImp(conn);
		newdao.setNewOrderState(newOrderId,orderState, operator, operatorDate);
		
		DBUtil.closeConn(conn);
	}
	public void deleteReturnOrder(ReturnOrder returnOrder){
		Connection conn = DBUtil.getConn();
		ReturnOrderDAO returndao = new ReturnOrderDAOImp(conn);
		returndao.deleteReturnOrderById(returnOrder.getReturnOrderId(),returnOrder.getOperator(),returnOrder.getOperateDate());
		DBUtil.closeConn(conn);
	}
	public void updateReturnOrder(ReturnOrder returnOrder){}
	public List<ReturnOrder> selectReturnOrder(ReturnOrder returnOrder){
		Connection conn = DBUtil.getConn();
		ReturnOrderDAO dao = new ReturnOrderDAOImp(conn);
		List<ReturnOrder> returnOrders = dao.selectReturnOrder(returnOrder);
		DBUtil.closeConn(conn);
		return returnOrders;
	}
	public List<ReturnOrder> selectReturnOrderByPage(ReturnOrder returnOrder,int pageSize,int pageNum){
		Connection conn = DBUtil.getConn();
		ReturnOrderDAO dao = new ReturnOrderDAOImp(conn);
		List<ReturnOrder> returnOrders = dao.selectReturnOrderByPage(returnOrder,pageSize,pageNum);
		DBUtil.closeConn(conn);
		return returnOrders;
	}
	public int selectReturnPageCount(ReturnOrder returnOrder,int pageSize){
		Connection conn = DBUtil.getConn();
		ReturnOrderDAO dao = new ReturnOrderDAOImp(conn);
		int returnOrderPageCount=0;
		try {
			returnOrderPageCount = dao.selectReturnOrderPageCount(returnOrder, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConn(conn);
		return returnOrderPageCount;
	}
}
