package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.neuedu.model.po.CenterReturnOrder;
import com.neuedu.model.po.PurchaseInOrder;
import com.neuedu.utils.DBUtil;

public class CenterReturnOrderDAOImp implements CenterReturnOrderDAO {
	
	Connection conn;
	
	public CenterReturnOrderDAOImp(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public List<CenterReturnOrder> selectCROrder(String supplier, Date date, String product) {
		List<CenterReturnOrder> list = new ArrayList<CenterReturnOrder>();
		//sql语句
		//查询未结算普通送货单据
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.productName,b.productPrice "
				+"from centerreturnorder a,product b,supplierinfo c "
				+"where a.productId = b.productId and "
				+ "a.supId = c.supId and "
				+"a.clearingstatus=0 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.returnDate = ? ");
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
				CenterReturnOrder c = new CenterReturnOrder();
				c.setReturnOrderId(rs.getInt("returnOrderId"));
				c.setProductId(rs.getInt("productId"));
				c.setProductQuantity(rs.getInt("productQuantity"));
				c.setReturnDate(rs.getDate("returnDate"));
				c.setSupId(rs.getInt("supId"));
				c.setProductName(rs.getString("productName"));
				c.setProductPrice(rs.getFloat("productPrice"));
				float amount = rs.getFloat("productPrice")*rs.getInt("productQuantity");
				c.setOrderAmount(amount);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}

	//结算退货单。
	public void clearSuppiler(int[] ids, String employeeName) {
		// TODO Auto-generated method stub
		String id = Arrays.toString(ids).replace('[','(').replace(']', ')');
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update centerreturnorder set clearingStatus = 1,operator=?,operateDate=? where returnOrderId in "+id);
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

	@Override
	public List<CenterReturnOrder> selectClearedSuppiler(String supplier, Date date, String product) {
		List<CenterReturnOrder> list = new ArrayList<CenterReturnOrder>();
		//sql语句
		//查询未结算普通送货单据
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.productName,b.productPrice "
				+"from centerreturnorder a,product b,supplierinfo c "
				+"where a.productId = b.productId and "
				+ "a.supId = c.supId and "
				+"a.clearingstatus=1 ");
		if(date!=null&&!"".equals(date)){
			sbf.append("and a.returnDate = ? ");
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
				CenterReturnOrder c = new CenterReturnOrder();
				c.setReturnOrderId(rs.getInt("returnOrderId"));
				c.setProductId(rs.getInt("productId"));
				c.setProductQuantity(rs.getInt("productQuantity"));
				c.setReturnDate(rs.getDate("returnDate"));
				c.setSupId(rs.getInt("supId"));
				c.setProductName(rs.getString("productName"));
				c.setProductPrice(rs.getFloat("productPrice"));
				float amount = rs.getFloat("productPrice")*rs.getInt("productQuantity");
				c.setOrderAmount(amount);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
		
	}

}
