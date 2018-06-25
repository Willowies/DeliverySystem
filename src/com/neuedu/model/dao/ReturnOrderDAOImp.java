package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  com.neuedu.model.po.NewOrder;
import  com.neuedu.model.po.ReturnOrder;
import  com.neuedu.model.service.NewOrderService;
import  com.neuedu.utils.DBUtil;

public class ReturnOrderDAOImp implements ReturnOrderDAO{
	private Connection conn;
	public  ReturnOrderDAOImp(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void creatReturnOrder(ReturnOrder returnOrder) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" insert into cancelorder values (null,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1,returnOrder.getNewOrder().getOrderId());
			ps.setInt(2,returnOrder.getReturnQuantity());
			ps.setString(3,returnOrder.getReturnReason());
			ps.setDate(4, new java.sql.Date(returnOrder.getReturnDate().getTime()));
			ps.setString(5, returnOrder.getDeliverRequest());
			ps.setInt(6, returnOrder.getEmployeeId());
			ps.setInt(7, returnOrder.getOrderState());
			ps.setFloat(8, returnOrder.getReturnTotal());
			ps.setInt(9, returnOrder.getStatus());
			ps.setString(10, returnOrder.getOperator());
			ps.setDate(11, new java.sql.Date(returnOrder.getOperateDate().getTime()));
			ps.setDate(12, new java.sql.Date(returnOrder.getGenerateDate().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}

	@Override
	public void deleteReturnOrderById(int returnOrderId, String operator, Date operatorDate) {
		try {
			PreparedStatement ps = conn.prepareStatement("update returnOrder set status =0,operator=?,operateDate=? where returnorderid = ?");
			ps.setString(1, operator);
			ps.setDate(2, new java.sql.Date(operatorDate.getTime()));
			ps.setInt(3, returnOrderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upDateReturnOrder(ReturnOrder returnOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReturnOrder> selectReturnOrder(ReturnOrder r) {
		StringBuffer sbf = new StringBuffer("");
		int returnOrderId = r.getReturnOrderId();
		int newOrderId = r.getNewOrder().getOrderId();
		int returnQuantity = r.getReturnQuantity();
		String returnReason = r.getReturnReason();
		Date returnDate= r.getReturnDate();
		String deliverRequest = r.getDeliverRequest();
		int employeeId = r.getEmployeeId();
		int orderState = r.getOrderState();
		float returnTotal = r.getReturnTotal();
		int status = r.getStatus();
		String operator = r.getOperator();
		Date operateDate = r.getOperateDate();
		Date generateDate = r.getGenerateDate();
		
		List<ReturnOrder> returnOrders = new ArrayList<ReturnOrder>();
		sbf.append("select *  from  returnorder where 1=1  ");
		if(returnOrderId != 0){
			sbf.append(" and returnOrderId=?");
		}
		if(newOrderId != 0){
			sbf.append(" and newOrderId=?");
		}
		if(returnQuantity != 0){
			sbf.append(" and returnQuantity=?");
		}
		if(returnReason != null && !"".equals(returnReason)){
			sbf.append(" and returnReason=?");
		}
		if(returnDate != null ){
			sbf.append(" and returnDate=?");
		}
		if(deliverRequest != null && !"".equals(deliverRequest)){
			sbf.append(" and deliverRequest=?");
		}
		if(employeeId != 0 ){
			sbf.append(" and employeeId=?");
		}
		if(orderState != 0){
			sbf.append(" and orderState=?");
		}
		if(returnTotal != 0){
			sbf.append(" and returnTotal=?");
		}
		if(status != 0){
			sbf.append(" and status=?");
		}
		if(operator != null && !"".equals(operator)){
			sbf.append(" and operator=?");
		}
		if(operateDate != null){
			sbf.append(" and operateDate=?");
		}
		if(generateDate != null) {
			sbf.append(" and generateDate = ? ");
		}
		sbf.append(";");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if(returnOrderId != 0){
				ps.setInt(index, returnOrderId);
				index++;
			}
			if(newOrderId != 0){
				ps.setInt(index, newOrderId);
				index++;
			}
			if(returnQuantity != 0){
				ps.setInt(index, returnQuantity);
				index++;
			}
			if(returnReason != null && !"".equals(returnReason)){
				ps.setString(index, returnReason);
				index++;
			}
			if(returnDate != null){
				ps.setDate(index, new java.sql.Date(returnDate.getTime()));
				index++;
			}
			if(deliverRequest != null && !"".equals(deliverRequest)){
				ps.setString(index, deliverRequest);
				index++;
			}
			if(employeeId != 0){
				ps.setInt(index, employeeId);
				index++;
			}
			if(orderState != 0){
				ps.setInt(index, orderState);
				index++;
			}
			if(returnTotal != 0){
				ps.setFloat(index, returnTotal);
				index++;
			}
			if(status != 0){
				ps.setInt(index, status);
				index++;
			}
			if(operator != null && !"".equals(operator)){
				ps.setString(index, operator);
				index++;
			}
			if(operateDate != null){
				ps.setDate(index, new java.sql.Date(operateDate.getTime()));
				index++;
			}
			if(generateDate != null){
				ps.setDate(index, new java.sql.Date(generateDate.getTime()));
				index++;
			}
			//System.out.println(ps);
			//执行
			//统计获得数据数
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while (rs.next()) {
				ReturnOrder returnOrder = new ReturnOrder();
				returnOrder.setReturnOrderId(rs.getInt("returnOrderId"));
				
				NewOrder n1 = new NewOrder();
				n1.setOrderId(rs.getInt("newOrderId"));
				NewOrder n2 = NewOrderService.getInstance().selectNewOrder(n1).get(0);
				returnOrder.setNewOrder(n2);
				
				returnOrder.setReturnQuantity(rs.getInt("returnQuantity"));
				returnOrder.setReturnReason(rs.getString("returnReason"));
				returnOrder.setReturnDate(rs.getDate("returnDate"));
				returnOrder.setDeliverRequest(rs.getString("deliverRequest"));
				returnOrder.setEmployeeId(rs.getInt("employeeId"));
				returnOrder.setOrderState(rs.getInt("orderState"));
				returnOrder.setReturnTotal(rs.getFloat("returnTotal"));
				returnOrder.setStatus(rs.getInt("status"));
				returnOrder.setOperator(rs.getString("operator"));
				returnOrder.setGenerateDate(rs.getDate("operateDate"));
				returnOrder.setGenerateDate(rs.getDate("generateDate"));

				returnOrders.add(r);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return returnOrders;
	}

	@Override
	public List<ReturnOrder> selectReturnOrderByPage(ReturnOrder returnOrder, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectReturnOrderPageCount(ReturnOrder returnOrder, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		return 0;
	}


}
