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
		PreparedStatement ps2 = null;
		try {
			ps = conn.prepareStatement(" insert into cancelorder values (null,?,?,?,?)");
			ps.setInt(1,cancelOrder.getNewOrder().getOrderId());
			ps.setInt(2,cancelOrder.getEmployeeId());
			ps.setString(3,cancelOrder.getCancelReason());
			ps.setDate(4, new java.sql.Date(cancelOrder.getCancelDate().getTime()));
			//更新库存可用量
			//恢复可用量
			NewOrder newOrder = NewOrderService.getInstance().selectNewOrder(cancelOrder.getNewOrder()).get(0);
			ps2 = conn.prepareStatement(" update warehouseProduct set allocatableQuantity = (allocatableQuantity+?) where productId = ?;");
			ps2.setInt(1, newOrder.getProductQuantity());
			ps2.setInt(2, newOrder.getProduct().getProductId());
			ps2.executeUpdate();
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
			DBUtil.closePS(ps2);
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
		sbf.append("select * from cancelorder LEFT JOIN neworder USING(newOrderId) WHERE 1=1");
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
	public List<CancelOrder> selectCancelOrderByPage(CancelOrder c, int pageSize, int pageNum) {
		List<CancelOrder> cancelOrders = new ArrayList<CancelOrder>();
		int cancelOrderId = c.getCancelOrderId();
		int newOrderId = c.getNewOrder().getOrderId();
		int employeeId = c.getEmployeeId();
		String cancelReason = c.getCancelReason();
		Date cancelDate = c.getCancelDate();
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from cancelorder LEFT JOIN neworder USING(newOrderId) WHERE 1=1");
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
		if(pageSize !=0 && pageNum>=0){
			sbf.append(" limit ?,?");
		}
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(cancelOrderId != 0){
				ps.setInt(index,cancelOrderId);
				index++;
			}
			if(newOrderId != 0){
				ps.setInt(index,newOrderId);
				index++;
			}
			if(employeeId != 0){
				ps.setInt(index,employeeId);
				index++;
			}
			if(cancelReason != null && !"".equals(cancelReason)){
				ps.setString(index,cancelReason);
				index++;
			}
			if(cancelDate != null){
				ps.setDate(index, new java.sql.Date(c.getCancelDate().getTime()));
				index++;
			}
			if(pageSize !=0 && pageNum>=0){
				ps.setInt(index, pageNum-1);
				index++;
				ps.setInt(index, pageSize);
				index++;
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
	public int selectCancelOrderPageCount(CancelOrder c, int pageSize) {
		int cancelOrderId = c.getCancelOrderId();
		int newOrderId = c.getNewOrder().getOrderId();
		int employeeId = c.getEmployeeId();
		String cancelReason = c.getCancelReason();
		Date cancelDate = c.getCancelDate();
		int count = 0;
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select COUNT(*) from cancelorder WHERE 1=1");
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
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(cancelOrderId != 0){
				ps.setInt(index,cancelOrderId);
				index++;
			}
			if(newOrderId != 0){
				ps.setInt(index,newOrderId);
				index++;
			}
			if(employeeId != 0){
				ps.setInt(index,employeeId);
				index++;
			}
			if(cancelReason != null && !"".equals(cancelReason)){
				ps.setString(index,cancelReason);
				index++;
			}
			if(cancelDate != null){
				ps.setDate(index, new java.sql.Date(c.getCancelDate().getTime()));
				index++;
			}
			if(pageSize !=0 ){
				ps.setInt(index, pageSize);
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		int pagecount = 0;
		if(count%pageSize==0){
			pagecount = count/pageSize;
		}else{
			pagecount = count/pageSize+1;
		}
		return pagecount;
	}
	
}
