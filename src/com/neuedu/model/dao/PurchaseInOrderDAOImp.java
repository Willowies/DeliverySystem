package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.utils.DBUtil;

public class PurchaseInOrderDAOImp implements PurchaseInOrderDAO {
	Connection conn;
	public PurchaseInOrderDAOImp(Connection conn){
		this.conn = conn;
	}

	public PurchaseInOrder selectPurchaseInOrder(int purchaseId) {
		PurchaseInOrder purOrder = new PurchaseInOrder();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("  select  pu.*,pr.productName from purchaseinorder pu, product pr where purchaseInId=? "
				+ "and pu.productId=pr.productId");
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString());
            ps.setInt(1, purchaseId);
							
			//执行查询
			ResultSet rs = ps.executeQuery();			
			if(rs.next()){
				purOrder.setPurchaseId(rs.getInt("purchaseInId"));
				purOrder.setProductId(rs.getInt("productId"));
				purOrder.setProductName(rs.getString("productName"));
				purOrder.setProductQuantity(rs.getInt("productQuantity"));
				purOrder.setProductAmount(rs.getInt("productAmount"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
			
		return purOrder;
	}
	
	//查询未结算的购货入库单
	public List<PurchaseInOrder> selectPIOrder(String supplier, Date date, String product) {
		List<PurchaseInOrder> list = new ArrayList<PurchaseInOrder>();
		//sql语句
		//查询未结算普通送货单据
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.productName,b.productPrice "
				+"from purchaseinorder a,product b,supplierinfo c "
				+"where a.productId = b.productId and "
				+ "b.supId = c.supId and "
				+"a.clearingstatus=0 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.createDate = ? ");
		}
		if(supplier!=null&&!"".equals(supplier)){
			sbf.append("and c.supName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and b.productName =? ");
		}
	//	System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(supplier!=null&&!"".equals(supplier)){
				ps.setString(index, supplier);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PurchaseInOrder p = new PurchaseInOrder();
				p.setPurchaseId(rs.getInt("purchaseInId"));
				p.setProductId(rs.getInt("productId"));
				p.setProductQuantity(rs.getInt("productQuantity"));
				p.setProductAmount(rs.getFloat("productAmount"));
				p.setCreateDate(rs.getDate("createDate"));
				p.setProductName(rs.getString("productName"));
				p.setProductPrice(rs.getFloat("productPrice"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}

	//结算购货入库单
	public void clearSuppiler(int[] ids, String employeeName) {
		String id = Arrays.toString(ids).replace('[','(').replace(']', ')');
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update purchaseinorder set clearingStatus = 1,operator=?,operateDate=? where purchaseInId in "+id);
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

	//查询已结算的单据
	public List<PurchaseInOrder> selectClearedSuppiler(String supplier, Date date, String product) {
		List<PurchaseInOrder> list = new ArrayList<PurchaseInOrder>();
		//sql语句
		//查询未结算普通送货单据
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.productName,b.productPrice "
				+"from purchaseinorder a,product b,supplierinfo c "
				+"where a.productId = b.productId and "
				+ "b.supId = c.supId and "
				+"a.clearingstatus=1 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.createDate = ? ");
		}
		if(supplier!=null&&!"".equals(supplier)){
			sbf.append("and c.supName =? ");
		}
		if(product!=null&&!"".equals(product)){
			sbf.append("and b.productName =? ");
		}
		//System.out.println("sql:"+sbf);
		
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if(date!=null&&!"".equals(date)){
				ps.setDate(index,new java.sql.Date(date.getTime()));
				index++;	
			}
			if(supplier!=null&&!"".equals(supplier)){
				ps.setString(index, supplier);
				index++;
			}
			if(product!=null&&!"".equals(product)){
				ps.setString(index, product);
			}
			
			//执行
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PurchaseInOrder p = new PurchaseInOrder();
				p.setPurchaseId(rs.getInt("purchaseInId"));
				p.setProductId(rs.getInt("productId"));
				p.setProductQuantity(rs.getInt("productQuantity"));
				p.setProductAmount(rs.getFloat("productAmount"));
				p.setCreateDate(rs.getDate("createDate"));
				p.setProductName(rs.getString("productName"));
				p.setProductPrice(rs.getFloat("productPrice"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
		
	}
}
