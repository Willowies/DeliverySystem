package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.Product;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

public class WorkOrderDAOImp implements WorkOrderDAO {

	Connection conn;
	
	public WorkOrderDAOImp(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public List<WorkOrder> searchWorkOrder(WorkOrder order) {
		List<WorkOrder> orders = new ArrayList<WorkOrder>();
		PreparedStatement ps = null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select a.*,b.clientName,b.clientMobilephone,d.warehouseName "
				+ "from workorder a,userinfo b,neworder c,warehouse d "
				+ "where a.orderId=c.newOrderId and b.clientId=c.clientId and d.warehouseId=a.warehouseId ");
		int flag = 0;
		if (order.getWorkId() != 0) {
			sbf.append(" and workId = ? ");
		}
		if (order.getCreateDate() != null) {
			sbf.append(" and createDate = ? ");
		}
		if (order.getRequireDate() != null) {
			sbf.append(" and requireDate = ? ");
		}
		if (order.getWarehouseName() != null) {
			sbf.append(" and d.warehouseName = ? ");
		}
		if (order.getWorkType() != 0) {
			sbf.append(" and a.workType = ? ");
		}
		if (order.getWorkStatus() != 0) {
			sbf.append(" and a.workStatus = ? ");
		}
		if (order.getClientName() != null) {
			flag = 1;
			sbf.append(" and orderId in ( select orderId from neworder where clientId in (select clientId from userinfo where clientName = ? ");
		}
		if (order.getClientPhone() != null) {
			if (flag == 0) {
				sbf.append(" and orderId in ( select newOrderId from neworder where clientId in (select clientId from userinfo where clientMobilephone = ? ");
				flag = 1;
			}else if (flag == 1) {
				sbf.append(" and clientMobilephone = ? ");
			}
		}
		if (flag==1) {
			sbf.append(" ) )");
		}
		System.out.println("sql:"+sbf);
		try {
			ps = conn.prepareStatement(sbf.toString());
			int index = 1;
			if (order.getWorkId() != 0) {
				ps.setInt(index, order.getWorkId());
				index++;
			}
			if (order.getCreateDate() != null) {
				Date date = new Date(order.getCreateDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getRequireDate() != null) {
				Date date = new Date(order.getRequireDate().getTime());
				ps.setDate(index,  date);
				index++;
			}
			if (order.getWarehouseName() != null) {
				ps.setString(index, order.getWarehouseName());
				index++;
			}
			if (order.getWorkType() != 0) {
				ps.setInt(index, order.getWorkType());
				index++;
			}
			if (order.getWorkStatus() != 0) {
				ps.setInt(index, order.getWorkStatus());
				index++;
			}
			if (order.getClientName() != null) {
				ps.setString(index, order.getClientName());
				index++;
			}
			if (order.getClientPhone() != null) {
				ps.setString(index, order.getClientPhone());
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("exist");
				WorkOrder o = new WorkOrder();
				o.setWorkId(rs.getInt("workId"));
				o.setWarehouseId(rs.getString("warehouseId"));
				o.setClientName(rs.getString("clientName"));
				o.setClientPhone(rs.getString("clientMobilephone"));
				o.setWorkStatus(rs.getInt("workStatus"));
				o.setWorkType(rs.getInt("workType"));
				o.setCreateDate(rs.getDate("createDate"));
				o.setRequireDate(rs.getDate("requireDate"));
				o.setRemark(rs.getString("remark"));
				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return orders;
	}
	
	public void createWorkOrder(int orderId,int orderType,String warehouseName,String remark,String operator) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from warehouse where warehouseName = ? ;");
			ps.setString(1, warehouseName);
		
			int warehouseId = 0;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				warehouseId = rs.getInt("warehouseId");
			}
			
			Date date = new Date(new java.util.Date().getTime());
			Date date2 = new Date(new java.util.Date().getTime()+7*24*60*60*1000);
			Date date3 = new Date(new java.util.Date().getTime()+24*60*60*1000);
			ps = conn.prepareStatement(" insert into workorder (warehouseId,orderId,workStatus,workType,"
					+ "createDate,requireDate,operator,operateDate,remark,status) values (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, warehouseId);
			ps.setInt(2, orderId);
			ps.setInt(3, 1);
			ps.setInt(4, orderType);
			ps.setDate(5, date);
			ps.setDate(6, date2);
			ps.setString(7, operator);
			ps.setDate(8, date);
			ps.setString(9, remark);
			ps.setInt(10, 1);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("select * from workorder where orderId = ? and workType=? ;");
			ps.setInt(1, orderId);
			ps.setInt(2, orderType);
		
			int workId = 0;
			ResultSet rs2 = ps.executeQuery();
			while (rs2.next()) {
				workId = rs2.getInt("workId");
			}
			
			ps = conn.prepareStatement(" insert into producttransferorder (workId,planOutDate) values (?,?)");
			ps.setInt(1, workId);
			ps.setDate(2, date3);
			
			ps.executeUpdate();
			
			if (orderType == 1) {
				ps = conn.prepareStatement(" update neworder set orderState = 6 "
						+ "and operator=? and operateDate=?  where newOrderId=?");
				ps.setString(1, operator);
				ps.setDate(2, date);
				ps.setInt(3, orderId);
				
				ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(" update returnorder set orderState = 6 "
						+ "and operator=? and operateDate=?  where returnOrderId=?");
				ps.setString(1, operator);
				ps.setDate(2, date);
				ps.setInt(3, orderId);
				
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

	@Override
	public List<NewOrder> searchLackOrder(NewOrder order) {
		java.util.Date generateDate = order.getGenerateDate();
		java.util.Date requireDate = order.getRequireDate();
		boolean enough = order.isEnoughOrNot();
		
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select o.*,b.allocatableQuantity,product.productName "
				+ "FROM neworder o,warehouse a,warehouseproduct b LEFT JOIN product using(productId) "
				+ "WHERE a.warehouseRank = 1 and a.warehouseId = b.warehouseId and o.productId = b.productId  and o.orderState = 3");
		if (generateDate != null) {
			sbf.append(" and generateDate = ? ");
		}
		if (requireDate != null) {
			sbf.append(" and requireDate = ? ");
		}
		
		List<NewOrder> newOrders = new ArrayList<NewOrder>();
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			int index=1;
			if (generateDate != null) {
				ps.setDate(index,new Date(generateDate.getTime()));
				index++;
			}
			if (requireDate != null) {
				ps.setDate(index, new Date(requireDate.getTime()));
				index++;
			}
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NewOrder newOrder = new NewOrder();
				newOrder.setOrderId(rs.getInt("neworderId"));
				
				Product p = new Product();
				p.setProductName(rs.getString("productName"));
				newOrder.setProduct(p);
				
				newOrder.setProductQuantity(rs.getInt("productQuantity"));
				newOrder.setGenerateDate(rs.getDate("generateDate"));
				newOrder.setRequireDate(rs.getDate("requireDate"));
				if (rs.getInt("productQuantity")>rs.getInt("allocatableQUantity")) {
					newOrder.setEnoughOrNot(false);
					if (!enough) {
						newOrders.add(newOrder);
					}
				}else {
					newOrder.setEnoughOrNot(true);
					newOrders.add(newOrder);
				}

			}
			
		} catch (SQLException e) {
			
		}
		return newOrders;
	}

	//�޸Ķ���״̬
	public void modifyLackStatus(int orderId, String operator) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update neworder set orderState = 1 "
					+ "and operator=? and operateDate=? where newOrderId=?");
			ps.setString(1, operator);
			ps.setDate(2, new Date(new java.util.Date().getTime()));
			ps.setInt(3, orderId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}
	//查询指定页码的数据
	@Override
	public List<WorkOrder> selectPageWork(java.util.Date requireDate, int workStatus, int workType, int pageNum) {
		List<WorkOrder> list = new ArrayList<WorkOrder>();
		int pageSize = 5;//固定
		StringBuffer sbf = new StringBuffer("");//用“”开头
		sbf.append(" select * from workorder where 1=1 ");
		if(requireDate != null){
			sbf.append(" and requireDate=? ");
		}
		if(workStatus != 0){
			sbf.append(" and workStatus=? ");
		}
		if(workType != 0){
			sbf.append(" and workType=? ");
		}
		
		//查询，构建sql
		try {
			PreparedStatement ps = conn.prepareStatement(" select * from ( "
					+ " select @rownum:=@rownum+1 as rownum, a.* "
					+ " from (select @rownum:=0) t, ( "+ sbf.toString() +" ) as a) as c "
					+ " where c.rownum <= "+ pageSize*pageNum +" and c.rownum >"+ pageSize*(pageNum-1)
					);
			int index = 1;
			if(requireDate != null){
				Date newRequireDate = new Date(requireDate.getTime());
				ps.setDate(index, newRequireDate);
				index++;
			}
			if(workStatus != 0){
				ps.setInt(index, workStatus);
				index++;
			}
			if(workType != 0){
				ps.setInt(index, workType);
			}
			//执行
			ResultSet rs = ps.executeQuery();//执行查询，返回结果集
			while(rs.next()){
				WorkOrder workOrder = new WorkOrder();
				workOrder.setWorkId(rs.getInt("workId"));
				workOrder.setOrderId(rs.getInt("orderId"));
				workOrder.setWarehouseName(rs.getString("warehouseName"));
				workOrder.setWorkStatus(rs.getInt("workStatus"));
				workOrder.setWorkType(rs.getInt("workType"));
				workOrder.setClientName(rs.getString("clientName"));
				workOrder.setClientPhone(rs.getString("clientPhone"));
				workOrder.setCreateDate(rs.getDate("createDate"));//取出来的是sql的date，但是他是util的date的子类，所以不用转化
				workOrder.setRequireDate(rs.getDate("requireDate"));
				workOrder.setProductName(rs.getString("productName"));
				workOrder.setProductUnit(rs.getString("productUnit"));
				workOrder.setProductQuantity(rs.getInt("productQuantity"));
				workOrder.setProductCode(rs.getString("productCode"));
				workOrder.setRemark(rs.getString("remark"));
				list.add(workOrder);//添加查询到的数据
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//返回list
	}
	
	//数据的页数
	@Override
	public int selectPageCount(java.util.Date requireDate, int workStatus, int workType) {
		List<WorkOrder> list = new ArrayList<WorkOrder>();
		int pageSize = 5;//固定
		int count = 0;
		StringBuffer sbf = new StringBuffer("");//用“”开头
		sbf.append(" select count(*) cc from workorder where 1=1 ");
		if(requireDate != null){
			sbf.append(" and requireDate=? ");
		}
		if(workStatus != 0){
			sbf.append(" and workStatus=? ");
		}
		if(workType != 0){
			sbf.append(" and workType=? ");
		}
		//构建sql语句
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sbf.toString());
			
			int index = 1;
			if(requireDate != null){
				Date newRequireDate = new Date(requireDate.getTime());
				ps.setDate(index, newRequireDate);
				index++;
			}
			if(workStatus != 0){
				ps.setInt(index, workStatus);
				index++;
			}
			if(workType != 0){
				ps.setInt(index, workType);
			}
			//执行语句
			ResultSet rs = ps.executeQuery();
			if(rs.next()){//说明起码有一个
				count = rs.getInt("cc");//获得数据总数
			}
			System.out.println("count::"+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int pagecount = 0;
		if(count % pageSize == 0){
			pagecount = count / pageSize;
		}else{
			pagecount = count / pageSize + 1;
		}
		System.out.println("pagecount+++++:"+pagecount);
		return pagecount;
	}
	
}
