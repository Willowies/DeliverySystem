package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.NewOrder;


public interface NewOrderDAO {
	public void creatNewOrder(NewOrder newOrder);
	public void deleteNewOrderById(int newOrderId,String operator,Date operatorDate);
	public void upDateNewOrder(NewOrder newOrder);
	public List<NewOrder> selectNewOrder(NewOrder n);
	public List<NewOrder> selectNewOrderByPage(NewOrder newOrder,int pageSize,int pageNum);
	public int selectNewOrderPageCount(NewOrder newOrder,int pageSize);
	public void setNewOrderState(int newOrderId, int orderState, String operator, Date operatorDate);
}
