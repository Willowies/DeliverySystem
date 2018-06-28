package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.SubInRecord;
import com.neuedu.utils.DBUtil;

public class SubInRecordDAOImp implements SubInRecordDAO {

	Connection conn;
	public SubInRecordDAOImp(Connection conn) {
		this.conn = conn;
	}

	public boolean inSubWarehouse(SubInRecord sir,String employee) {
		boolean isSuccess = false;
		PreparedStatement ps = null;
		if(!testInto(sir.getDistributionId())){ //�жϷ�վ�Ƿ��Ѿ�����		
			try {
				int warehouseId = 0;
				//��ȡ���������Ϣ
				String str1 = " select * from distributionorder d,producttransferorder p,workorder w "
						+ "where d.distributionId=? and d.productTransferId=p.productTransferId and p.workId=w.workId";
				ps = conn.prepareStatement(str1);
				ps.setInt(1, sir.getDistributionId());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					warehouseId = rs.getInt("warehouseId");
				}
				//����һ����վ�ⷿ����¼
				String str2 = " insert into subinrecord(warehouseId,productId,expectedQuantity,acturalQuantity,"
						+ "inDate,remark) values(?,?,?,?,?,?)";
				ps = conn.prepareStatement(str2);
				ps.setInt(1, warehouseId);
				ps.setInt(2, sir.getProductId());
				ps.setInt(3, sir.getExpectedQuantity());
				ps.setInt(4, sir.getActuralQuantity());
			    ps.setDate(5, new java.sql.Date(sir.getInDate().getTime()));
			    ps.setString(6, sir.getRemark());
			    ps.executeUpdate();
			    isSuccess = true;
			    //�޸Ŀ����
			    String str3 = " update warehouseproduct set productQuantity=productQuantity+?,allocatableQuantity=allocatableQuantity+?,"
			    		+ "operator=?,operateDate=? "
			    		+ " where warehouseId=?";
			    ps = conn.prepareStatement(str3);
			    ps.setInt(1, sir.getActuralQuantity());
			    ps.setInt(2, sir.getActuralQuantity());
			    ps.setString(3, employee);
			    ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			    ps.setInt(5, warehouseId);
			    ps.executeUpdate();
			    //�޸����񵥺Ͷ���״̬
			    String str4 = " update distributionorder d,producttransferorder p,workorder w,neworder n set w.workStatus=3,n.orderState=8,"
			    		+ "w.operator=?,w.operateDate=?,n.operator=?,n.operateDate=? "
			    		+ " where distributionId=? and d.productTransferId=p.productTransferId and p.workId=w.workId and w.orderId=n.newOrderId";
			    ps = conn.prepareStatement(str4);
			    ps.setString(1, employee);
			    ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			    ps.setString(3, employee);
			    ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			    ps.setInt(5, sir.getDistributionId());
			    ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
			
		}		
		return isSuccess;
	}

	public boolean testInto(int distributionId) {
		boolean isInto = true;
		String str = " select * from distributionorder d,producttransferorder p,workorder w,neworder n "
				+ " where distributionId=? and d.productTransferId=p.productTransferId and p.workId=w.workId and w.orderId=n.newOrderId";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(str);
			ps.setInt(1, distributionId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//������״̬Ϊ"���Ŀⷿ����" ����״̬Ϊ"�ɷ���"ʱ���ж�Ϊ�����
				if(rs.getInt("workStatus") == 2 && rs.getInt("orderState") == 7){
					isInto = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isInto;
	}

}
