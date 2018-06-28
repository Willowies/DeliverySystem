package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.ReceiveRecord;
import com.neuedu.utils.DBUtil;

public class ReceiveRecordDAOImp implements ReceiveRecordDAO {
	Connection conn;

	public ReceiveRecordDAOImp(Connection conn) {
		this.conn = conn;
	}

	public boolean receiveProduct(ReceiveRecord rr, String employee) {
		boolean isSuccess = false;
		//判断该任务单是否已领货
		if(!testReceive(rr.getWorkId())){ 
			PreparedStatement ps = null;
			try {
				isSuccess = true;
				//写入领货记录
				String str1 = " insert into receiverecord(workId,receivePerson,receiveDate,remark) values(?,?,?,?)";
				ps = conn.prepareStatement(str1);
				ps.setInt(1, rr.getWorkId());
				ps.setString(2, rr.getReceivePerson());
				ps.setDate(3, new java.sql.Date(rr.getReceiveDate().getTime()));
				ps.setString(4, rr.getRemark());
				ps.executeUpdate();
				//获取订单商品数量
				int quantity = 0;
				String str2 = " select * from workorder w,neworder n where w.workId=? and w.orderId=n.newOrderId";
				ps = conn.prepareStatement(str2);
				ps.setInt(1, rr.getWorkId());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					quantity = rs.getInt("productQuantity");
				}
				//修改库存量
				String str3 = " update workorder w,warehouseproduct wp,neworder n "
						+ "set wp.productQuantity=wp.productQuantity-?,wp.operator=?,wp.operateDate=? "
						+ " where w.workId=? and w.warehouseId=wp.warehouseId and w.orderId=n.newOrderId and n.productId=wp.productId";
				ps = conn.prepareStatement(str3);
				ps.setInt(1, quantity);
				ps.setString(2, employee);
				ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(4, rr.getWorkId());
				ps.executeUpdate();
				//修改订单、任务单状态
				String str4 = " update workorder w,neworder n "
						+ "set w.workStatus=4,n.orderState=10,w.operator=?,w.operateDate=?,n.operator=?,n.operateDate=? "
						+ " where w.orderId=n.newOrderId and w.workId=?";
				ps = conn.prepareStatement(str4);
				ps.setString(1, employee);
				ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				ps.setString(3, employee);
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(5, rr.getWorkId());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
		}
		return isSuccess;
	}

	public boolean testReceive(int workId) {
		boolean isReceive = true;
		PreparedStatement ps = null;
		String str = " select * from workorder w, neworder n where w.workId=? and w.orderId=n.newOrderId";
		try {
			ps = conn.prepareStatement(str);
			ps.setInt(1, workId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("workStatus") == 3 && rs.getInt("orderState") == 9){
					isReceive = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}		
		return isReceive;
	}

}
