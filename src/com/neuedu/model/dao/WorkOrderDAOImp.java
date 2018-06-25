package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

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
				o.setWarehouseName(rs.getString("warehouseName"));
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
				ps = conn.prepareStatement(" update neworder set orderState = 6 where newOrderId=?");
				ps.setInt(1, orderId);
				
				ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(" update returnorder set orderState = 6 where returnOrderId=?");
				ps.setInt(1, orderId);
				
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
	}

}
