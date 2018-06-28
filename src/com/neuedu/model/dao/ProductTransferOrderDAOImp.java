package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.ProductTransferOrder;
import com.neuedu.utils.DBUtil;

public class ProductTransferOrderDAOImp implements ProductTransferOrderDAO {
    Connection conn;
	public ProductTransferOrderDAOImp(Connection conn) {
		this.conn = conn;
	}

	public List<ProductTransferOrder> selectProductTransferOrder(Date inputDate) {
		List<ProductTransferOrder> list = new ArrayList<ProductTransferOrder>();
		PreparedStatement ps = null;
		int centerId = 0;
		String st = "select warehouseId from warehouse where warehouseRank=1";
		try {
			ps = conn.prepareStatement(st);
			ResultSet r = ps.executeQuery();
			if(r.next()){
				centerId = r.getInt("warehouseId");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String str = " select p.*,w.orderId,w.warehouseId,n.productQuantity,pr.productId,pr.productName,pr.productUnit "
				+ " from producttransferorder p,workOrder w,neworder n,product pr where planOutDate=? and "
				+ "p.workId=w.workId and w.orderId=n.newOrderId and n.productId=pr.productId";
		try {
			ps = conn.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(inputDate.getTime()));
			//÷¥––≤È—Ø
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProductTransferOrder transferOrder = new ProductTransferOrder();
				transferOrder.setProductTransferId(rs.getInt("productTransferId"));
				transferOrder.setWorkId(rs.getInt("workId"));
				transferOrder.setPlanOutDate(rs.getDate("planOutDate"));
				transferOrder.setOrderId(rs.getInt("orderId"));
				transferOrder.setInWarehouseId(rs.getInt("warehouseId"));
				transferOrder.setOutWarehouse(centerId);
				transferOrder.setProductQuantity(rs.getInt("productQuantity"));
				transferOrder.setProductId(rs.getInt("productId"));
				transferOrder.setProdutName(rs.getString("productName"));			
				transferOrder.setProductUnit(rs.getString("productUnit"));
				
				list.add(transferOrder);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return list;
	}

}
