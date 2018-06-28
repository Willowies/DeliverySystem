package com.neuedu.model.service;

import java.sql.Connection;

import com.neuedu.model.dao.EmployeeDAO;
import com.neuedu.model.dao.EmployeeDAOImp;
import com.neuedu.model.po.Employee;
import com.neuedu.utils.DBUtil;

public class EmployeeManageService {
	
	//单例模式
	private static EmployeeManageService service = new EmployeeManageService();
	private EmployeeManageService(){}
	public static EmployeeManageService getInstance(){
		return service;
	}
	
	//查询账户信息
	public Employee login(String account,String password) {
		Connection conn = DBUtil.getConn();
		EmployeeDAO dao = new EmployeeDAOImp(conn);
		Employee e = dao.login(account,password);
		return e;
	}
	
	
}
