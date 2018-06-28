package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.CancelOrder;

public interface CancelOrderDAO {
	public void creatCancelOrder(CancelOrder cancelOrder);
	public List<CancelOrder> selectCancelOrder(CancelOrder c);
	public List<CancelOrder> selectCancelOrderByPage(CancelOrder cancelOrder,int pageSize,int pageNum);
	public int selectCancelOrderPageCount(CancelOrder cancelOrder,int pageSize);
}
