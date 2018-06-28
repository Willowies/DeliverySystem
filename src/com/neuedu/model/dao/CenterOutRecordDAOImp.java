package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.CenterInRecord;
import com.neuedu.model.po.CenterOutRecord;
import com.neuedu.utils.DBUtil;

public class CenterOutRecordDAOImp implements CenterOutRecordDAO {
	Connection conn;
	public CenterOutRecordDAOImp(Connection conn) {
		this.conn = conn;
	}
	
	public boolean outCenter(int transferId,String employee) {
		int productId = 0;
		int productQuantity = 0;
		//Date centerOutDate = null;
		boolean isSuccess = false;
		PreparedStatement ps = null;
		//检验是否出库过
		if(!testOut(transferId)){
			String str = " select n.productId,n.productQuantity from producttransferorder p,workorder w,neworder n "
					+ "where p.productTransferId=? and p.workId=w.workId and w.orderId=n.newOrderId";
			try {
				ps = conn.prepareStatement(str);
				ps.setInt(1, transferId);			
				//执行查询
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					productId = rs.getInt(1);
					productQuantity = rs.getInt(2);
				}
				//写入出库记录
				String str1 = " insert into centeroutrecord(productId,productQuantity,centerOutDate) values(?,?,?)";
				ps = conn.prepareStatement(str1);
				ps.setInt(1, productId);
				ps.setInt(2, productQuantity);
				ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps.executeUpdate();	
				//写入分发记录
				String str2 = " insert into distributionorder(productTransferId,centerOutDate) values(?,?)";
				ps = conn.prepareStatement(str2);
				ps.setInt(1, transferId);
				ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				ps.executeUpdate();
				isSuccess = true;
				//修改库存量
				String str3 = " update warehouseproduct set productQuantity=productQuantity-?,operator=?,operateDate=? "
						+ " where productId=? and warehouseId=1";
				ps = conn.prepareStatement(str3);
				ps.setInt(1, productQuantity);
				ps.setString(2, employee);
				ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(4, productId);			
				ps.executeUpdate();
				//修改任务单、订单状态
				String str4 = " update producttransferorder p,workorder w,neworder n set w.workStatus=2,n.orderState=7,"
						+ "w.operator=?,w.operateDate=?,n.operator=?,n.operateDate=? "
						+ " where p.productTransferId=? and p.workId=w.workId and w.orderId=n.newOrderId";
				ps = conn.prepareStatement(str4);
				ps.setString(1, employee);
				ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				ps.setString(3, employee);
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(5, transferId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
		}		
		
		return isSuccess;
	}

	public boolean testOut(int transferId) {
		PreparedStatement ps = null;
		boolean isOut = true;
		String str = " select w.workStatus,n.orderState from producttransferorder p,workorder w,neworder n "
				+ " where p.productTransferId=? and p.workId=w.workId and w.orderId=n.newOrderId";
		try {
			ps = conn.prepareStatement(str);
			ps.setInt(1, transferId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//当订单状态为"中心库房 出库" 任务单状态为"可分配"时，判断为已出库
				if(rs.getInt("workStatus") == 1 && rs.getInt("orderState") == 6){
					isOut = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return isOut;
	}

	//根据出库日期、商品名查询出库信息
	public List<CenterOutRecord> selectCenterOut(Date outDate, String productName) {
		List<CenterOutRecord> corList = new ArrayList<CenterOutRecord>();
		String str = " select * from centeroutrecord c,product p where c.centerOutDate=? and p.productName=?"
				+ " and c.productId=p.productId";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(outDate.getTime()));
			ps.setString(2, productName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CenterOutRecord cor = new CenterOutRecord();
				cor.setCenterOutId(rs.getInt("centerOutId"));
				cor.setCenterOutDate(outDate);
				cor.setProductId(rs.getInt("productId"));
				cor.setProductName(productName);
				cor.setProductQuantity(rs.getInt("productQuantity"));
				cor.setPrice(rs.getFloat("productPrice"));
				cor.setManufacturer(rs.getString("manufacturer"));
				cor.setRemark(rs.getString("remark"));
				
				corList.add(cor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		return corList;
	}

	//根据出库日期查询出库信息
	public List<CenterOutRecord> selectCenterOut(Date outDate) {
		List<CenterOutRecord> corList = new ArrayList<CenterOutRecord>();
		String str = " select * from centeroutrecord c,product p where c.centerOutDate=?"
				+ " and c.productId=p.productId";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(outDate.getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CenterOutRecord cor = new CenterOutRecord();
				cor.setCenterOutId(rs.getInt("centerOutId"));
				cor.setCenterOutDate(rs.getDate("centerOutDate"));
				cor.setProductId(rs.getInt("productId"));
				cor.setProductName(rs.getString("productName"));
				cor.setProductQuantity(rs.getInt("productQuantity"));
				cor.setPrice(rs.getFloat("productPrice"));
				cor.setManufacturer(rs.getString("manufacturer"));
				cor.setRemark(rs.getString("remark"));
				corList.add(cor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return corList;
	}

}
