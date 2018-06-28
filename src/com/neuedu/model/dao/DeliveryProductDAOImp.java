package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.neuedu.model.po.DeliveryReturnProduct;
import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.DeliveryWarehouseOrder;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.utils.DBUtil;

public class DeliveryProductDAOImp implements DeliveryProductDAO {
	
	Connection conn;
	
	public DeliveryProductDAOImp(Connection conn){
		this.conn = conn;
	}

	@Override
	public List<DeliveryStockProduct> searchStockProduct(String productName) {
		List<DeliveryStockProduct> orders = new ArrayList<DeliveryStockProduct>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.*,sum(c.productQuantity) q,f.firstClassName,s.secondClassName "
				+ " from firstclass f,secondclass s,warehouseproduct a,product b left join neworder as c  on c.productId = b.productId and c.orderState=3"
				+ " where b.productName like ? and a.productId=b.productId and warehouseId in (select warehouseId from warehouse where warehouseRank=1)"
				+ "  and f.firstClassId=b.firstClassId and s.secondClassId=b.secondClassId;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			if (productName == null) {
				productName = "%";
			}else {
				productName = "%"+productName+"%";
			}
			ps.setString(1, productName);
			
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryStockProduct o = new DeliveryStockProduct();
				o.setProductId(rs.getInt("productId"));
				o.setFirstClass(rs.getString("firstClassName"));
				o.setSecondClass(rs.getString("secondClassName"));
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setProductUnit(rs.getString("productUnit"));
				o.setNow(rs.getInt("productQuantity"));
				o.setWarning(rs.getInt("warningValue"));
				int lack = rs.getInt("q");
				int w = rs.getInt("warningValue");
				int n = rs.getInt("productQuantity");
				o.setStockDate(new Date());
				if (n<w) {
					lack += w-n;
				}
				o.setLack(lack);
				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public void stock(int productId, int productQuantity, String operator) {
		
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("select productPrice from product where productId=?;");
			ps.setInt(1, productId);
			
			ResultSet rs = ps.executeQuery();

			float price = 0;
			while (rs.next()) {
				price = rs.getFloat("productPrice");
			}
			
			java.sql.Date date =  new java.sql.Date(new Date().getTime());
			ps = conn.prepareStatement("insert into purchaseinorder "
					+ "(productId,productQuantity,productAmount,clearingStatus,status,operator,operateDate,createDate) VALUES (?,?,?,?,?,?,?,?);");
			ps.setInt(1, productId);
			ps.setInt(2, productQuantity);
			ps.setFloat(3, price*productQuantity);
			ps.setInt(4, 1);
			ps.setInt(5, 1);
			ps.setString(6, operator);
			ps.setDate(7, date);
			ps.setDate(8, date);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<DeliveryReturnProduct> searchReturnProduct(String supName, String productCode, Date start, Date end) {
		List<DeliveryReturnProduct> orders = new ArrayList<DeliveryReturnProduct>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select b.*,c.*,sum(a.productQuantity) sum,d.supName "
				+ " from warehouseproduct b,product as c,supplierinfo d"
				+ " left join purchaseinorder as a"
				+ " on a.productId in (select productId from product where productCode like ?)"
				+ " and a.createDate BETWEEN ? and ? "
				+ " where c.productId=a.productId  and c.supId=d.supId and b.productId=c.productId"
				+ " and b.warehouseId in (select warehouseId from warehouse where warehouseRank=1)"
				+ " and d.supName like ? ;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			if (supName == null) {
				supName = "%";
			}else {
				supName = "%"+supName+"%";
			}
			if (productCode == null) {
				productCode = "%";
			}else {
				productCode = "%"+productCode+"%";
			}
			ps.setString(1, productCode);
			ps.setDate(2, new java.sql.Date(start.getTime()));
			ps.setDate(3, new java.sql.Date(end.getTime()));
			ps.setString(4, supName);
			
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("id:"+rs.getInt("productId"));
				DeliveryReturnProduct o = new DeliveryReturnProduct();
				o.setProductId(rs.getInt("productId"));
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setStock(rs.getInt("sum"));
				o.setSupName(rs.getString("supName"));
				o.setNow(rs.getInt("productQuantity"));
				o.setReturnQ(rs.getInt("returnQuantity"));

				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public void returnProduct(int productId, int productQuantity, String operator) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("select supId from product where productId=?;");
			ps.setInt(1, productId);
			
			ResultSet rs = ps.executeQuery();

			int supId = 0;
			while (rs.next()) {
				supId = rs.getInt("supId");
			}
			
			java.sql.Date date =  new java.sql.Date(new Date().getTime());
			ps = conn.prepareStatement("insert into centerreturnorder "
					+ "(productId,productQuantity,supId,clearingStatus,status,operator,operateDate,returnDate) VALUES (?,?,?,?,?,?,?,?);");
			ps.setInt(1, productId);
			ps.setInt(2, productQuantity);
			ps.setInt(3, supId);
			ps.setInt(4, 1);
			ps.setInt(5, 1);
			ps.setString(6, operator);
			ps.setDate(7, date);
			ps.setDate(8, date);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
	}

	@Override
	public List<DeliveryStockProduct> searchWarehouseProduct(String productName) {
		List<DeliveryStockProduct> orders = new ArrayList<DeliveryStockProduct>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select *,b.productName from warehouseproduct a,product b "
				+ " where a.productId in (SELECT productId from product where productName like ? )"
				+ " and a.productId = b.productId and warehouseId in (select warehouseId from warehouse where warehouseRank=1);");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			if (productName == null) {
				productName = "%";
			}else {
				productName = "%"+productName+"%";
			}
			ps.setString(1, productName);
			
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryStockProduct o = new DeliveryStockProduct();
				o.setProductId(rs.getInt("productId"));
				o.setProductName(rs.getString("productName"));
				o.setMax(rs.getInt("maxValue"));
				o.setWarning(rs.getInt("warningValue"));
				
				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public void modify(int productId, int warning, int max, String operator) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("update warehouseproduct set warningValue=?,`maxValue`=? "
					+ "  where productId=? and warehouseId in (select warehouseId from warehouse where warehouseRank=1); ");
			ps.setInt(1, warning);
			ps.setInt(2, max);
			ps.setInt(3, productId);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<DeliveryStockProduct> searchWarehouseValue(String productName, String warehouseName) {
		List<DeliveryStockProduct> orders = new ArrayList<DeliveryStockProduct>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from warehouseproduct a,warehouse b,product c"
				+ " where a.warehouseId=b.warehouseId"
				+ " and a.productId=c.productId"
				+ " and a.warehouseId in (SELECT warehouseId from warehouse where warehouseName like ? )"
				+ " and a.productId in (SELECT productId from product where productName like ? ) ;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			if (productName == null) {
				productName = "%";
			}else {
				productName = "%"+productName+"%";
			}
			if (warehouseName == null) {
				warehouseName = "%";
			}else {
				warehouseName = "%"+warehouseName+"%";
			}
			ps.setString(1, productName);
			ps.setString(2, warehouseName);
			
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryStockProduct o = new DeliveryStockProduct();
				o.setProductId(rs.getInt("productId"));
				o.setProductName(rs.getString("productName"));
				o.setWarehouseName(rs.getString("warehouseName"));
				int qu = rs.getInt("productQuantity");
				int al = rs.getInt("allocatableQuantity");
				int re = rs.getInt("returnQuantity");
				o.setAllocate(al);
				o.setReturnQ(re);
				o.setAllocated(qu-al);
				o.setTotal(qu+re);
				
				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public List<DeliveryWarehouseOrder> searchOrder(String productName, Date start, Date end) {
		List<DeliveryWarehouseOrder> orders = new ArrayList<DeliveryWarehouseOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.*,c.warehouseName from centerinrecord a,product b,warehouse c"
				+ " where c.warehouseId in (select warehouseId from warehouse where warehouseRank=1)"
				+ " and a.productId=b.productId"
				+ " and b.productId in (select productId from product where productName like ? )"
				+ " and centerInDate BETWEEN ? and ? ;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			if (productName == null) {
				productName = "%";
			}else {
				productName = "%"+productName+"%";
			}
			
			ps.setString(1, productName);
			ps.setDate(2, new java.sql.Date(start.getTime()));
			ps.setDate(3, new java.sql.Date(end.getTime()));
			
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryWarehouseOrder o = new DeliveryWarehouseOrder();
				o.setProductName(rs.getString("productName"));
				o.setWarehouseName(rs.getString("warehouseName"));
				o.setOrderId(rs.getInt("centerInId"));
				o.setDate(rs.getDate("centerInDate"));
				o.setProductCode(rs.getString("productCode"));
				float price = rs.getFloat("productPrice");
				int qu = rs.getInt("actualQuantity");
				o.setPrice(price);
				o.setQuantity(qu);
				o.setTotal(price*qu);
				o.setType("入库");
				
				orders.add(o);
				
			}
			
			ps = conn.prepareStatement("select a.*,b.*,c.warehouseName from centeroutrecord a,product b,warehouse c"
					+ " where c.warehouseId in (select warehouseId from warehouse where warehouseRank=1)"
					+ " and a.productId=b.productId and b.productId in (select productId from product"
					+ " where productName like ? )"
					+ " and centerOutDate BETWEEN ? and ? ;");
			if (productName == null) {
				productName = "%";
			}else {
				productName = "%"+productName+"%";
			}
			
			ps.setString(1, productName);
			ps.setDate(2, new java.sql.Date(start.getTime()));
			ps.setDate(3, new java.sql.Date(end.getTime()));
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryWarehouseOrder o = new DeliveryWarehouseOrder();
				o.setProductName(rs.getString("productName"));
				o.setWarehouseName(rs.getString("warehouseName"));
				o.setOrderId(rs.getInt("centerOutId"));
				o.setDate(rs.getDate("centerOutDate"));
				o.setProductCode(rs.getString("productCode"));
				float price = rs.getFloat("productPrice");
				int qu = rs.getInt("productQuantity");
				o.setPrice(price);
				o.setQuantity(qu);
				o.setTotal(price*qu);
				o.setType("出库");
				
				orders.add(o);
				
			}

			ps = conn.prepareStatement("select a.*,b.*,c.warehouseName from subinrecord a,product b,warehouse c"
					+ " where c.warehouseId=a.warehouseId "
					+ " and a.productId=b.productId and b.productId in (select productId from product"
					+ " where productName like ? )"
					+ " and InDate BETWEEN ? and ? ;");
			if (productName == null) {
				productName = "%";
			}else {
				productName = "%"+productName+"%";
			}
			
			ps.setString(1, productName);
			ps.setDate(2, new java.sql.Date(start.getTime()));
			ps.setDate(3, new java.sql.Date(end.getTime()));
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				DeliveryWarehouseOrder o = new DeliveryWarehouseOrder();
				o.setProductName(rs.getString("productName"));
				o.setWarehouseName(rs.getString("warehouseName"));
				o.setOrderId(rs.getInt("subWarehouseInId"));
				o.setDate(rs.getDate("InDate"));
				o.setProductCode(rs.getString("productCode"));
				float price = rs.getFloat("productPrice");
				int qu = rs.getInt("actualQuantity");
				o.setPrice(price);
				o.setQuantity(qu);
				o.setTotal(price*qu);
				o.setType("入库");
				
				orders.add(o);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public List<DeliveryWarehouseOrder> orderStatistics(Date start, Date end) {
		List<DeliveryWarehouseOrder> orders = new ArrayList<DeliveryWarehouseOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,sum(b.productQuantity) sum,f.firstClassName,s.secondClassName "
				+ " from product a,firstclass f,secondclass s left join neworder as b "
				+ " on b.finishDate BETWEEN ? and ?"
				+ " where f.firstClassId=a.firstClassId and s.secondClassId=a.secondClassId and a.productId = b.productId "
				+ " order by sum desc limit 0,5 ;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setDate(1, new java.sql.Date(start.getTime()));
			ps.setDate(2, new java.sql.Date(end.getTime()));
			
			ResultSet rs = ps.executeQuery();

			int r = 1;
			while (rs.next()) {
				if (r==6) {
					break;
				}
				DeliveryWarehouseOrder o = new DeliveryWarehouseOrder();
				o.setProductName(rs.getString("productName"));
				o.setProductCode(rs.getString("productCode"));
				o.setFirstClass(rs.getString("firstClassName"));
				o.setSecondClass(rs.getString("secondClassName"));
				o.setQuantity(rs.getInt("sum"));
				o.setRank("第"+r+"名");
				r++;
				
				orders.add(o);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public List<DeliveryWarehouseOrder> substationStatistics(Date start, Date end) {
		List<DeliveryWarehouseOrder> orders = new ArrayList<DeliveryWarehouseOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("SELECT a.*,sum(b.total) total,count(b.total) count,sum(b.productQuantity) sum "
				+ " from warehouse a LEFT join neworder b"
				+ " on b.finishDate BETWEEN ? and ? "
				+ " where b.newOrderId in"
				+ " (select orderId from workorder where workType=1 and warehouseId=a.warehouseId ) and a.warehouseRank=0;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ps.setDate(1, new java.sql.Date(start.getTime()));
			ps.setDate(2, new java.sql.Date(end.getTime()));
			
			ResultSet rs = ps.executeQuery();

			int r = 1;
			while (rs.next()) {
				
				DeliveryWarehouseOrder o = new DeliveryWarehouseOrder();
				o.setWarehouseName(rs.getString("warehouseName"));
				o.setOrderQ(rs.getInt("count"));
				o.setQuantity(rs.getInt("sum"));
				o.setTotal(rs.getFloat("total"));
				
				if (rs.getString("warehouseName") != null) {
					orders.add(o);
				}
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public HashMap<String, String> satisfyStatistics(Date start, Date end) {
		HashMap<String, String> orders = new HashMap<String, String>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select  avg(customerFeedback) avg from feedback a,workorder b where a.workId=b.workId and workType=1 ;");
		
		System.out.println("sql: "+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				float f = rs.getFloat("avg");
				if (f>0.9) {
					orders.put("neworder", String.valueOf(f));
				}else {
					orders.put("neworder", "无数据");
				}
				
				break;
			}
			
			ps = conn.prepareStatement("select  avg(customerFeedback) avg from feedback a,workorder b where a.workId=b.workId and workType=2 ;");
			rs = ps.executeQuery();

			while (rs.next()) {
				float f = rs.getFloat("avg");
				if (f>0.9) {
					orders.put("returnorder", String.valueOf(f));
				}else {
					orders.put("returnorder", "无数据");
				}
				
				break;
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	
}
