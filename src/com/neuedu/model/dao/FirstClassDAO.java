package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.FirstClass;
import com.neuedu.model.po.Product;

public interface FirstClassDAO {

	void addFirstClass(FirstClass firstclass) throws SQLException;

	void editFirstClass(FirstClass firstclass) throws SQLException;


	void deleteFirstClass(int firstClassId, String operator, java.util.Date operateDate) throws SQLException;

	FirstClass validatefirstClassName(String firstClassName);

	FirstClass getfistClassById(int firstclassid);

	int selectfirstClassPageCount(String firstclassname);

	List<Product> selectProduct(int firstclassid);

	int selectProductPageCount(int firstclassid);

	int selectfirstClassIdByfirstClassName(String firstclassname);

	List<FirstClass> selectFirstClass(String firstclassname, int firstclassid);

	

	
	
	



}
