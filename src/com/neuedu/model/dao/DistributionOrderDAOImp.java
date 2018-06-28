package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.DistributionOrder;
import com.neuedu.utils.DBUtil;

public class DistributionOrderDAOImp implements DistributionOrderDAO {
	Connection conn;
	public DistributionOrderDAOImp(Connection conn) {
		this.conn = conn;
	}
	public DistributionOrder selectDistributionOrder(int distributionId) {
		DistributionOrder dOrder = new DistributionOrder();
		String str = " select * from distributionorder d,producttransferorder p,workorder w,neworder n,product pr "
				+ "where d.distributionId=? and d.productTransferId=p.productTransferId and p.workId=w.workId and w.orderId=n.newOrderId and n.productId=pr.productId";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(str);
			ps.setInt(1, distributionId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				dOrder.setDistributionId(distributionId);
				dOrder.setProductId(rs.getInt("productId"));
				dOrder.setProductName(rs.getString("productName"));
				dOrder.setProductQuantity(rs.getInt("productQuantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dOrder;
	}
	//根据出库日期、商品名、仓库名称查询分发信息
	public List<DistributionOrder> selectDistribution(Date outDate, String productName, String warehouseName) {
		List<DistributionOrder> list = new ArrayList<DistributionOrder>();
		String str = " select * from distributionorder d,productTransferOrder p,workorder w,neworder n,product pr,warehouse wh"
				+ " where d.productTransferId=p.productTransferId and p.workId=w.workId and w.orderId=n.newOrderId"
				+ " and n.productId=pr.productId and w.warehouseId=wh.warehouseId"
				+ " and d.centerOutDate=? and pr.productName=? and wh.warehouseName=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(outDate.getTime()));
			ps.setString(2, productName);
			ps.setString(3, warehouseName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DistributionOrder disOrder = new DistributionOrder();
				disOrder.setWarehouseName(warehouseName);
				disOrder.setProductId(rs.getInt("productId"));
				disOrder.setProductName(productName);
				disOrder.setProductQuantity(rs.getInt("productQuantity"));
				disOrder.setPrice(rs.getFloat("productPrice"));
				disOrder.setManufacturer(rs.getString("manufacturer"));
				
				list.add(disOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
	}
	
	//根据出库日期、仓库名称查询分发信息
	public List<DistributionOrder> selectDistribution(Date outDate, String warehouseName) {
		List<DistributionOrder> list = new ArrayList<DistributionOrder>();
		String str = " select * from distributionorder d,productTransferOrder p,workorder w,neworder n,product pr,warehouse wh"
				+ " where d.productTransferId=p.productTransferId and p.workId=w.workId and w.orderId=n.newOrderId"
				+ " and n.productId=pr.productId and w.warehouseId=wh.warehouseId"
				+ " and d.centerOutDate=? and wh.warehouseName=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(outDate.getTime()));
			ps.setString(2, warehouseName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DistributionOrder disOrder = new DistributionOrder();
				disOrder.setWarehouseName(warehouseName);
				disOrder.setProductId(rs.getInt("productId"));
				disOrder.setProductName(rs.getString("productName"));
				disOrder.setProductQuantity(rs.getInt("productQuantity"));
				disOrder.setPrice(rs.getFloat("productPrice"));
				disOrder.setManufacturer(rs.getString("manufacturer"));
				
				list.add(disOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
	}

}
