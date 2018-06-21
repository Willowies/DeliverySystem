package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.WorkOrder;

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
				+ "where a.orderId=c.newOrderId and b.clientId=c.clientId abd d.warehouseId=a.warehouseId ");
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
			sbf.append(" and workType = ? ");
		}
		if (order.getWorkStatus() != 0) {
			sbf.append(" and workStatus = ? ");
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
				ps.setInt(index, order.getWorkId());
				index++;
			}
			if (order.getWorkStatus() != 0) {
				ps.setInt(index, order.getWorkId());
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
				WorkOrder o = new WorkOrder();
				o.setWorkId(rs.getInt("workId"));
				o.setWarehouseName(rs.getString("warehouseName"));
				o.setClientName(rs.getString("clientName"));
				o.setClientPhone(rs.getString("clientPhone"));
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

}
