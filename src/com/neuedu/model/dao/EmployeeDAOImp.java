package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.Employee;

public class EmployeeDAOImp implements EmployeeDAO {
	
	Connection conn;
	
	public EmployeeDAOImp(Connection conn){
		this.conn = conn;
	}
	
	//到数据库中查询该账户信息
	@Override
	public Employee login(String account,String password){
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Employee employee = null;
		try {
			ps = conn.prepareStatement(" select * from employee where employeeAccount=? and employeePassword=?");
			ps.setString(1, account);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				employee = new Employee();
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setEmployeeAccount(rs.getString("employeeAccount"));
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setEmployeePassword(rs.getString("employeePassword"));
				employee.setCenterType(rs.getInt("centerType"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
		
	}

}
