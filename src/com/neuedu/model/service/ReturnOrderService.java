package com.neuedu.model.service;

import java.sql.Connection;
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
	public void creatReturnOrder(ReturnOrder returnOrder){
		Connection conn = DBUtil.getConn();
		NewOrder newOrder = returnOrder.getNewOrder();
		int returnQuantity = returnOrder.getReturnQuantity();
		int newQuantity = newOrder.getProductQuantity();
		NewOrderDAO newdao = new NewOrderDAOImp(conn);
		int newOrderId = newOrder.getOrderId();
		int orderState = 0;
		String operator = returnOrder.getOperator();
		Date operatorDate = returnOrder.getOperateDate();
		
		if(returnQuantity > newQuantity){//退货数量错误
			return;
		}else if(returnQuantity < newQuantity){//部分退货
			orderState = 5;
		}else if (returnQuantity == newQuantity){
			orderState = 4;
		}
		
		ReturnOrderDAO returndao = new ReturnOrderDAOImp(conn);
		returndao.creatReturnOrder(returnOrder);
		newdao.setNewOrderState(newOrderId,orderState, operator, operatorDate);
		
		DBUtil.closeConn(conn);
	}
	public void deleteReturnOrder(ReturnOrder returnOrder){
		//Connection conn = DBUtil.getConn();
		//ReturnOrderDAO returndao = new ReturnOrderDAOImp(conn);
	}
	public void updateReturnOrder(ReturnOrder returnOrder){}
	public List<ReturnOrder> selectReturnOrder(ReturnOrder returnOrder){
		Connection conn = DBUtil.getConn();
		ReturnOrderDAO dao = new ReturnOrderDAOImp(conn);
		return dao.selectReturnOrder(returnOrder);
	}
	public List<ReturnOrder> selectReturnOrder(ReturnOrder returnOrder,int pageSize,int pageNum){
		return null;
	}
	public int selectReturnPageCount(ReturnOrder returnOrder,int pageSize,int pageNum){
		return 0;
	}
}
