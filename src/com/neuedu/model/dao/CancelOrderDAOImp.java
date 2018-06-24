package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.CancelOrder;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.service.NewOrderService;
import com.neuedu.utils.DBUtil;



public class CancelOrderDAOImp implements CancelOrderDAO{
	private Connection conn;
	public  CancelOrderDAOImp(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void creatCancelOrder(CancelOrder cancelOrder) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into cancelorder values (null,?,?,?,?)");
			ps.setInt(1,cancelOrder.getNewOrder().getOrderId());
			ps.setInt(2,cancelOrder.getNewOrder().getEmployeeId());
			ps.setString(3,cancelOrder.getCancelReason());
			ps.setDate(4, new java.sql.Date(cancelOrder.getCancelDate().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<CancelOrder> selectCancelOrder(CancelOrder c) {
		List<CancelOrder> cancelOrders = new ArrayList<CancelOrder>();
		int cancelOrderId = c.getCancelOrderId();
		int newOrderId = c.getNewOrder().getOrderId();
		int employeeId = c.getEmployeeId();
		String cancelReason = c.getCancelReason();
		Date cancelDate = c.getCancelDate();
		
		StringBuffer sbf = new StringBuffer("");
		if(cancelOrderId != 0){
			sbf.append(" and cancelOrderId=?");
		}
		if(newOrderId != 0){
			sbf.append(" and newOrderId=?");
		}
		if(employeeId != 0){
			sbf.append(" and employeeId=?");
		}
		if(cancelReason != null && !"".equals(cancelReason)){
			sbf.append(" and cancelReason=?");
		}
		if(cancelDate != null){
			sbf.append(" and cancelDate=?");
		}
		PreparedStatement ps = null;
		sbf.append("select *  from  cancelorder where 1=1");
		try {
			ps = conn.prepareStatement(sbf.toString());
			if(cancelOrderId != 0){
				ps.setInt(1,cancelOrderId);
			}
			if(newOrderId != 0){
				ps.setInt(2,newOrderId);
			}
			if(employeeId != 0){
				ps.setInt(3,employeeId);
			}
			if(cancelReason != null && !"".equals(cancelReason)){
				ps.setString(4,cancelReason);
			}
			if(cancelDate != null){
				ps.setDate(5, new java.sql.Date(c.getCancelDate().getTime()));
			}
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CancelOrder cancelOrder = new CancelOrder();
				cancelOrder.setCancelOrderId(rs.getInt("cancelOrderId"));
				
				NewOrder n1 = new NewOrder();
				n1.setOrderId(rs.getInt("newOrderId"));
				NewOrder n2 = NewOrderService.getInstance().selectNewOrder(n1).get(0);
				cancelOrder.setNewOrder(n2);
				
				cancelOrder.setEmployeeId(rs.getInt("employeeId"));
				cancelOrder.setCancelReason(rs.getString("cancelReason"));
				cancelOrder.setCancelDate(rs.getDate("cancelDate"));
				cancelOrders.add(cancelOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return cancelOrders;
	}
	@Override
	public List<CancelOrder> selectCancelOrderByPage(CancelOrder cancelOrder, int pageSize, int pageNum) {
		return null;
	}
	@Override
	public int selectCancelOrderPageCount(CancelOrder cancelOrder, int pageSize, int pageNum) {
		return 0;
	}
	
}
