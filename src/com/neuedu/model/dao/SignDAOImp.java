package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.Sign;
import com.neuedu.utils.DBUtil;

public class SignDAOImp implements SignDAO {
	
	Connection conn;
	
	public SignDAOImp(Connection conn){
		this.conn = conn;
	}

	//查询未结算签收单
	public List<Sign> selectSign(String substation, Date date, String product) {
		List<Sign> list = new ArrayList<Sign>();
		//sql语句
		//查询未结算普通送货单据
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,d.productQuantity,d.total,e.productName,e.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,product e "
				+"where a.workId = b.workId and "
				+ "b.warehouseId = c.warehouseId and "
				+"b.orderId = d.neworderId and "
				+"d.productId = e.productId and "
				+"b.workType = 1 and "
				+"a.clearingStatus = 0 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and e.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("productQuantity"));
				s.setTotal(rs.getFloat("total"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//查询退货单
		sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,e.returnQuantity,e.returnTotal,f.productName,f.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,returnorder e,product f "
				+"where a.workId = b.workId and "
				+"b.orderId = e.returnOrderId and "
				+ "b.warehouseId = c.warehouseId and "
				+"e.neworderId = d.neworderId and "
				+"d.productId = f.productId and "
				+"b.workType = 2 and "
				+"a.clearingStatus = 0 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and f.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("returnQuantity"));
				s.setTotal(rs.getFloat("returnTotal"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//结算签收单
	public void clearSubstation(int[] ids,String employeeName){
		String id = Arrays.toString(ids).replace('[','(').replace(']', ')');
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update sign set clearingStatus = 1,operator=?,operateDate=? where signId in "+id);
			ps.setString(1,employeeName);
			ps.setDate(2,new java.sql.Date(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}

	//查询已结算订单
	public List<Sign> selectClearedSub(String substation, Date date, String product) {
		List<Sign> list = new ArrayList<Sign>();
		//sql语句
		//查询未结算普通送货单据
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,d.productQuantity,d.total,e.productName,e.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,product e "
				+"where a.workId = b.workId and "
				+ "b.warehouseId = c.warehouseId and "
				+"b.orderId = d.neworderId and "
				+"d.productId = e.productId and "
				+"b.workType = 1 and "
				+"a.clearingStatus = 1 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and e.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("productQuantity"));
				s.setTotal(rs.getFloat("total"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//查询退货单
		sbf = new StringBuffer("");
		sbf.append("select a.*,b.orderId,b.workType,e.returnQuantity,e.returnTotal,f.productName,f.productPrice "
				+"from sign a,workorder b,warehouse c,neworder d,returnorder e,product f "
				+"where a.workId = b.workId and "
				+"b.orderId = e.returnOrderId and "
				+ "b.warehouseId = c.warehouseId and "
				+"e.neworderId = d.neworderId and "
				+"d.productId = f.productId and "
				+"b.workType = 2 and "
				+"a.clearingStatus = 1 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.deliveryDate = ?");
		}
		if(substation!=null&&!"".equals(substation)){
			sbf.append("and c.warehouseName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and f.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(substation!=null&&!"".equals(substation)){
				ps.setString(index, substation);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Sign s = new Sign();
				s.setSignId(rs.getInt("signId"));
				s.setWorkId(rs.getInt("workId"));
				s.setDeliveryDate(rs.getDate("deliveryDate"));
				s.setReceiptNeedOrNot(rs.getInt("receiptNeedOrNot"));
				s.setClearingStatus(rs.getInt("clearingStatus"));
				s.setRemark(rs.getString("remark"));
				s.setCustomerFeedback(rs.getInt("customerFeedback"));
				s.setCustomerSignature(rs.getString("customerSignature"));
				s.setOrderId(rs.getInt("orderId"));
				s.setWorkType(rs.getInt("workType"));
				s.setProductQuantity(rs.getInt("returnQuantity"));
				s.setTotal(rs.getFloat("returnTotal"));
				s.setProductName(rs.getString("productName"));
				s.setProductPrice(rs.getFloat("productPrice"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
