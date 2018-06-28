package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.ReturnOrder;

public interface ReturnOrderDAO {
	public void creatReturnOrder(ReturnOrder returnOrder);
	public void deleteReturnOrderById(int newOrderId,String operator,Date operatorDate);
	public void updateReturnOrder(ReturnOrder returnOrder);
	public List<ReturnOrder> selectReturnOrder(ReturnOrder returnOrder);
	public List<ReturnOrder> selectReturnOrderByPage(ReturnOrder returnOrder,int pageSize,int pageNum);
	public int selectReturnOrderPageCount(ReturnOrder returnOrder,int pageSize) throws SQLException;
}
