package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.model.po.Warehouse;
import com.neuedu.utils.DBUtil;



public class WarehouseDAOImp implements WarehouseDAO {

	Connection conn;
	public WarehouseDAOImp(Connection conn) {
		this.conn=conn;
	}
	
	//增加库房	
	public void addWarehouse(Warehouse warehouse) {
		Date date = new Date(new java.util.Date().getTime());
		PreparedStatement ps=null;
		try {
			ps = conn.prepareStatement("select count(warehouseId) c from warehouse where warehouseRank=1 ;");
			ResultSet rs = ps.executeQuery();
			int c = 0;
			if(rs.next()) {
				c= rs.getInt("c");
			}
			
			System.out.print("c"+c +"  rank:"+ warehouse.getWarehouseRank());
			if(c==0) {
				ps=conn.prepareStatement("insert into warehouse values(null,?,?,?,?,?,?,?)");
				ps.setString(1,warehouse.getWarehouseName());
				ps.setString(2, warehouse.getWarehouseAddress());
				ps.setString(3, warehouse.getWarehouseKeeper());
				ps.setInt(4, warehouse.getWarehouseRank());
				ps.setInt(5, warehouse.getStatus());
				ps.setString(6, warehouse.getOperator());
				ps.setDate(7,date);
				System.out.println(ps);
				ps.executeUpdate();
			}else if(c!=0  && warehouse.getWarehouseRank()!=1) {
				ps=conn.prepareStatement("insert into warehouse values(null,?,?,?,?,?,?,?)");
				ps.setString(1,warehouse.getWarehouseName());
				ps.setString(2, warehouse.getWarehouseAddress());
				ps.setString(3, warehouse.getWarehouseKeeper());
				ps.setInt(4, warehouse.getWarehouseRank());
				ps.setInt(5, warehouse.getStatus());
				ps.setString(6, warehouse.getOperator());
				ps.setDate(7,date);
				System.out.println(ps);
				ps.executeUpdate();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 

	//查询库房
	public List<Warehouse> selectWarehouse(String warehouseName) {
		System.out.println("warehouseName+"+warehouseName);
		List<Warehouse> list =new ArrayList<Warehouse>();
		StringBuffer sbf=new StringBuffer(" ");
		sbf.append("SELECT `warehouseId`,`warehouseName`,`warehouseAddress`,`warehouseKeeper`,`warehouseRank` FROM warehouse WHERE 1=1 ");
		if(warehouseName!=null&&!" ".equals(warehouseName)) {
			sbf.append("and warehouseName like ? ");
		}
		try {
			System.out.println(sbf);
			PreparedStatement ps=conn.prepareStatement(sbf.toString());
			if(warehouseName!=null&&!" ".equals(warehouseName)) {
				ps.setString(1, "%"+warehouseName+"%");
			}
			//执行
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Warehouse ware=new Warehouse();
				ware.setWarehouseId(rs.getInt("warehouseId"));
				ware.setWarehouseName(rs.getString("warehouseName"));
				ware.setWarehouseAddress(rs.getString("warehouseAddress"));
				ware.setWarehouseKeeper(rs.getString("warehouseKeeper"));
				ware.setWarehouseRank(rs.getInt("warehouseRank"));
				list.add(ware);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	

	//更改库房
	public void updateWarehouse(Warehouse warehouse) {
		PreparedStatement ps = null;
		try {
			ps=conn.prepareStatement("update warehouse set warehouseName=?,warehouseAddress=?,warehouseKeeper=?,warehouseRank=?");
			ps.setString(1, warehouse.getWarehouseName());
			ps.setString(2, warehouse.getWarehouseAddress());
			ps.setString(3, warehouse.getWarehouseKeeper());
			ps.setInt(4, warehouse.getWarehouseRank());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closePS(ps);
		}
		
//		int warehouseId=warehouse.getWarehouseId();
//		String warehouseName=warehouse.getWarehouseName();
//		String warehouseAddress=warehouse.getWarehouseAddress();
//		String warehouseKeeper=warehouse.getWarehouseKeeper();
//		int warehouseRank=warehouse.getWarehouseRank();
//		int status=warehouse.getStatus();
//		String operator=warehouse.getOperator();
//		java.util.Date operateDate=warehouse.getOperateDate();
//		
//		StringBuffer sbf = new StringBuffer("");
//		sbf.append("update warehouse  set  ");
//		if(warehouseName!=null&&!" ".equals(warehouseName)) {
//			sbf.append(" ,warehouseName=?");	
//		}
//		if(warehouseAddress!=null&&!" ".equals(warehouseAddress)) {
//			sbf.append(" ,warehouseAddress=?");	
//		}
//		if(warehouseKeeper!=null&&!" ".equals(warehouseKeeper)) {
//			sbf.append(" ,warehouseKeeper=?");	
//		}
//		if(warehouseRank>=0&&warehouseRank<=1) {
//			sbf.append(" ,warehouseRank=?");	
//		}
//		
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sbf.toString());
//			int index=1;
//			if(warehouseId !=0) {
//				ps.setInt(index, warehouseId);
//				index++;
//			}
//			if(warehouseName!= null && !"".equals(warehouseName)) {
//				ps.setString(index,warehouseName);
//				index++;
//			}
//			if(warehouseAddress!= null && !"".equals(warehouseAddress)) {
//				ps.setString(index,warehouseAddress);
//				index++;
//			}
//			if(warehouseKeeper!= null && !"".equals(warehouseKeeper)) {
//				ps.setString(index,warehouseKeeper);
//				index++;
//			}
//			if(warehouseRank>=0&&warehouseRank<=1) {
//				ps.setInt(index,warehouseRank);
//				index++;
//			}
//			if(status != 0){
//				ps.setInt(index, status);
//				index++;
//			}
//			if(operator != null && !"".equals(operator)){
//				ps.setString(index, operator);
//				index++;
//			}
//			if(operateDate != null){
//				ps.setDate(index, new java.sql.Date(operateDate.getTime()));
//				index++;
//			}
//			//执行
//			ps.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
	}

	//删除库房
	public void deleteWarehouse(String warehouseId) {
		PreparedStatement ps=null;
		try {
			ps = conn.prepareStatement("delete from warehouse where warehouseId=?");
			ps.setString(1, warehouseId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Warehouse getWarehouseById(int warehouseId) {
		Warehouse warehouse=new Warehouse();
		PreparedStatement ps;
		try {
			ps=conn.prepareStatement("select * from warehouse where warehouseId=? ");
			ps.setInt(1, warehouseId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				warehouse.setWarehouseId(rs.getInt("warehouseId"));
				warehouse.setWarehouseName(rs.getString("WarehouseName"));
				warehouse.setWarehouseAddress(rs.getString("WarehouseAddress"));
				warehouse.setWarehouseKeeper(rs.getString("WarehouseKeeper"));
				warehouse.setWarehouseRank(rs.getInt("WarehouseRank"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return warehouse;
	}
	
	
	
}
