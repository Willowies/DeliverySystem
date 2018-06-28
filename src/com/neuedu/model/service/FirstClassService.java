package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.FirstClassDAO;
import com.neuedu.model.dao.FirstClassDAOImp;
import com.neuedu.model.po.FirstClass;
import com.neuedu.model.po.Product;
import com.neuedu.utils.DBUtil;

public class FirstClassService {
	private FirstClassService(){
	}
	
	private static FirstClassService service = new FirstClassService();
	
	public static FirstClassService getInstance(){
		return service;
	}
	public void addFirstClass(FirstClass firstclass) throws SQLException {
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
	    dao.addFirstClass(firstclass);
		}
	public void editFirstClass(FirstClass firstclass) throws SQLException {
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
		dao.editFirstClass(firstclass);
	}
	public void deleteFirstClass(int firstClassId,String  operator,Date operateDate ) {
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
		try {
			dao.deleteFirstClass(firstClassId, operator, operateDate);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public List<FirstClass> selectFirstClass(String firstclassname,int firstclassid){
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
		return dao.selectFirstClass(firstclassname,firstclassid);
	}
	
	public boolean validatefirstClassName(String firstclassname) {
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
		FirstClass fc =dao.validatefirstClassName(firstclassname);
		DBUtil.closeConn(conn);
		
		if(fc==null){
			return false;
		}else{
			return true;
		}
	}
	public List<Product> selectProduct(int firstclassid){
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
		return dao.selectProduct(firstclassid);
		
	}
	public int selectProductPageCount(int firstclassid) {
		Connection conn = DBUtil.getConn();
		FirstClassDAO dao = new FirstClassDAOImp(conn);
		return dao.selectProductPageCount(firstclassid);
	}
	 public int selectfirstClassIdByfirstClassName(String firstclassname) {
		 Connection conn = DBUtil.getConn();
			FirstClassDAO dao = new FirstClassDAOImp(conn);
			return dao.selectfirstClassIdByfirstClassName(firstclassname);
	 }

}
