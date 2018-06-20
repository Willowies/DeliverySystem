package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.PurchaseInOrder;

public class PurchaseInOrderDAOImp implements PurchaseInOrderDAO {
	Connection conn;
	public PurchaseInOrderDAOImp(Connection conn){
		this.conn = conn;
	}

	public PurchaseInOrder selectPurchaseInOrder(int purchaseId) {
		PurchaseInOrder purOrder = new PurchaseInOrder();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select * from purchaseinorder where purchaseId=? ");
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(purchaseId != 0){
				ps.setInt(index, purchaseId);
				index++;
			}
			//÷¥––≤È—Ø
			ResultSet rs = ps.executeQuery();			
			while(rs.next()){
				purOrder.setPurchaseId(rs.getInt("purchaseId"));
				purOrder.setProductId(rs.getInt("productId"));
				purOrder.setProductName(rs.getString("productName"));
				purOrder.setProductQuantity(rs.getInt("productQuantity"));
				purOrder.setProductAmount(rs.getInt("productAmount"));
				
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return purOrder;
	}

}
