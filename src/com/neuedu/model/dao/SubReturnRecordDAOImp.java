package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class SubReturnRecordDAOImp implements SubReturnRecordDAO {
	
	Connection conn;
	
	public SubReturnRecordDAOImp(Connection conn){
		this.conn = conn;
	}

	//ÍË»õµÇ¼Ç
	public void returnRecord(int workId, int q,String operator) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select c.productId,a.warehouseId from workorder a,returnorder b,neworder c where workId=? and a.orderId = b.returnOrderId and b.newOrderId=c.newOrderId;");			
			ps.setInt(1, workId);
			ResultSet rs = ps.executeQuery();
			int productId = 0;
			int warehouseId = 0;
			if (rs.next()) {
				productId = rs.getInt("productId");
				warehouseId = rs.getInt("warehouseId");
			}
			
			
			Date date = new Date(new java.util.Date().getTime());
			ps = conn.prepareStatement("insert into subreturnrecord "
					+ "(productId,actualQuantity,recordStatus,returnDate,operator,operateDate,workId) VALUES (?,?,?,?,?,?,?);");
			ps.setInt(1, productId);
			ps.setInt(2, q);
			ps.setInt(3, 1);
			ps.setDate(4, date);
			ps.setString(5, operator);
			ps.setDate(6, date);
			ps.setInt(7, workId);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update warehouseproduct set returnQuantity=returnQuantity+?"
					+ " where productId=? and warehouseId=? ");
			ps.setInt(1, q);
			ps.setInt(2, productId);
			ps.setInt(3, warehouseId);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}


	public List<SubReturnRecord> searchReturnProduct(java.util.Date start, java.util.Date end, int warehouseId) {
		List<SubReturnRecord> orders = new ArrayList<SubReturnRecord>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.*,sum(a.actualQuantity) sum from subreturnrecord a,product b"
				+ " where recordStatus=1 and workId in (select workId from workorder where warehouseId = ?) "
				+ " and a.productId=b.productId and returnDate BETWEEN ? and ? ");
		
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, warehouseId);
			ps.setDate(2, new Date(start.getTime()));
			ps.setDate(3, new Date(end.getTime()));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				SubReturnRecord o = new SubReturnRecord();
				o.setProductId(rs.getInt("productId"));
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setProductQuantity(rs.getInt("sum"));
				o.setProductUnit(rs.getString("productUnit"));
				o.setEnd(end);
				o.setStart(start);
				String string= new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
				try {
					o.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse(string));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (rs.getInt("productId")!=0) {
					orders.add(o);
				}
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}


	public void subReturnOut(int productId, int productQuantity, java.util.Date start, java.util.Date end,
			int warehouseId) {
		PreparedStatement ps = null;
		try {

			Date date = new Date(new java.util.Date().getTime());
			ps = conn.prepareStatement("insert into subreturnorder "
					+ "(productId,productQuantity,orderStatus,returnDate,warehouseId) VALUES (?,?,?,?,?);");
			ps.setInt(1, productId);
			ps.setInt(2, productQuantity);
			ps.setInt(3, 1);
			ps.setDate(4, date);
			ps.setInt(5, warehouseId);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update subreturnrecord set recordStatus=0"
					+ " where workId in (select workId from workorder where warehouseId = ?) and productId=1"
					+ " and returnDate BETWEEN ? and ? ;");
			ps.setInt(1, warehouseId);
			ps.setDate(2, new Date(start.getTime()));
			ps.setDate(3, new Date(end.getTime()));
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update warehouseproduct set returnQuantity=returnQuantity-?"
					+ " where productId=? and warehouseId=? ");
			ps.setInt(1, productQuantity);
			ps.setInt(2, productId);
			ps.setInt(3, warehouseId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}


	public List<SubReturnRecord> searchSubReturnProduct(int id) {
		List<SubReturnRecord> orders = new ArrayList<SubReturnRecord>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.* from subreturnorder a,product b"
				+ " where orderStatus=1 and a.productId=b.productId and returnOrderId=? ");
		
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("id"+rs.getInt("productId"));
				SubReturnRecord o = new SubReturnRecord();
				o.setProductId(rs.getInt("productId"));
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setProductQuantity(rs.getInt("productQuantity"));
				o.setProductUnit(rs.getString("productUnit"));
				o.setOrderId(rs.getInt("returnOrderId"));

				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	
	public void centerReturnIn(int productId, int productQuantity, int orderId) {
		PreparedStatement ps = null;
		try {
			Date date = new Date(new java.util.Date().getTime());
			ps = conn.prepareStatement("update subreturnorder set orderStatus=2,returnDate=?"
					+ " where returnOrderId=? ;");
			
			ps.setDate(1, date);
			ps.setInt(2, orderId);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update warehouseproduct set returnQuantity=returnQuantity+?"
					+ " where productId=? and warehouseId in (select warehouseId from warehouse where warehouseRank=1) ");
			ps.setInt(1, productQuantity);
			ps.setInt(2, productId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}

	@Override
	public List<SubReturnRecord> searchCenterReturnProduct(int id) {
		List<SubReturnRecord> orders = new ArrayList<SubReturnRecord>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.* from centerreturnorder a,product b"
				+ " where a.status=1 and a.productId=b.productId and returnOrderId=? ");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SubReturnRecord o = new SubReturnRecord();
				o.setProductId(rs.getInt("productId"));
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setProductQuantity(rs.getInt("productQuantity"));
				o.setProductUnit(rs.getString("productUnit"));
				o.setOrderId(rs.getInt("returnOrderId"));

				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public void centerReturnOut(int productId, int productQuantity, int orderId) {
		PreparedStatement ps = null;
		try {
			Date date = new Date(new java.util.Date().getTime());
			ps = conn.prepareStatement("update centerreturnorder set status=0"
					+ " where returnOrderId=? ;");
			
			ps.setInt(1, orderId);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("update warehouseproduct set returnQuantity=returnQuantity-?"
					+ " where productId=? and warehouseId in (select warehouseId from warehouse where warehouseRank=1) ");
			ps.setInt(1, productQuantity);
			ps.setInt(2, productId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
		
	}

	@Override
	public int getWarehouseId(int employeeId) {
		int warehouseId = 0;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from warehousemanager where employeeId = ? ;");
			ps.setInt(1, employeeId);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				warehouseId = rs.getInt("warehouseId");
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return warehouseId;
	}
	
	
}
