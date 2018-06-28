package com.neuedu.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.neuedu.model.po.DeliveryStaff;
import com.neuedu.model.po.WorkOrder;

public class DeliveryStaffDAOImp implements DeliveryStaffDAO {
	Connection conn = null;
	
	public DeliveryStaffDAOImp(Connection conn) {
		this.conn = conn;
	}
	//获取配送员信息
	@Override
	public List<DeliveryStaff> getDeliveryStaff(int employeeId){
		List<DeliveryStaff> list = new ArrayList<DeliveryStaff>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append(" select a.* from ( "
				+ " select c.warehouseId "
				+ " from employee as b, warehousemanager as c "
				+ " where b.employeeId=? and b.employeeId=c.employeeId "
				+ " ) as d, deliverystaff as a "
				+ " where a.warehouseId=d.warehouseId");
		try {
			PreparedStatement ps = conn.prepareStatement(sbf.toString());
			if(employeeId!=0){
				ps.setInt(1, employeeId);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DeliveryStaff temp = new DeliveryStaff();
				temp.setDeliveryStaffId(rs.getInt("deliveryStaffId"));
				temp.setDeliveryStaffName(rs.getString("deliveryStaffName"));
				temp.setDeliveryStaffPhone(rs.getString("deliveryStaffPhone"));
				temp.setWarehouseId(rs.getInt("warehouseId"));
				list.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
