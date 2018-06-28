package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.neuedu.model.po.CenterInRecord;
import com.neuedu.utils.DBUtil;

public class CenterInRecordDAOImp implements CenterInRecordDAO {
	Connection conn;
	public CenterInRecordDAOImp(Connection conn){
		this.conn = conn;
	}

	public boolean intoCenter(CenterInRecord cInRecord, String employee) {
		PreparedStatement ps = null;
		boolean isSuccess = false;
		if(!testInto(cInRecord.getPurchaseId())){ //判断该购货单是否入库过
			try {
				//插入一条中心库房入库记录
				String sql = " insert into centerinrecord(productId,expectedQuantity,actualQuantity,centerInDate,"
						+ "remark) values(?,?,?,?,?)";
				ps = conn.prepareStatement(sql);			
				ps.setInt(1, cInRecord.getProductId());
				ps.setInt(2, cInRecord.getExpectedQuantity());
				ps.setInt(3, cInRecord.getActuralQuantity());
				ps.setDate(4, new java.sql.Date(cInRecord.getCenterInDate().getTime()));
				ps.setString(5, cInRecord.getRemark());
				ps.executeUpdate();
				isSuccess = true;				
				
				//修改购货单状态
				String str = " update purchaseinorder p set status=?,operator=?,operateDate=? where p.purchaseInId=?";
				ps = conn.prepareStatement(str);
				ps.setInt(1, 0);
				ps.setString(2, employee);
			    ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(4, cInRecord.getPurchaseId());
				ps.executeUpdate();
				
				//修改库存量
				String st = " update warehouseproduct set productQuantity=productQuantity+?,allocatableQuantity=allocatableQuantity+?,"
						+ "operator=?,operateDate=? "
						+ " where productId=? and warehouseId=1";
				ps = conn.prepareStatement(st);
				ps.setInt(1, cInRecord.getActuralQuantity());
				ps.setInt(2, cInRecord.getActuralQuantity());
				ps.setString(3, employee);
			    ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(5, cInRecord.getProductId());				
				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closePS(ps);
			}
		}
		return isSuccess;

	}

	public boolean testInto(int purchaseId) {
		PreparedStatement ps = null;
		boolean isInto = false;
		String s = " select p.status from purchaseinorder p where p.purchaseInId=?";
		try {
			ps = conn.prepareStatement(s);
			ps.setInt(1, purchaseId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("status") == 0) isInto = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		return isInto;
	}

}
