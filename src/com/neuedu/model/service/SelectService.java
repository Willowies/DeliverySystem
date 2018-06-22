package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.SelectServiceDAO;
import com.neuedu.model.dao.SelectServiceDAOImp;
import com.neuedu.model.po.Client;
import com.neuedu.model.po.Product;
import com.neuedu.utils.DBUtil;


public class SelectService {
	private SelectService(){
	}
	
	private static SelectService service = new SelectService();
	
	public static SelectService getInstance(){
		return service;
	}
	public Client selectClient(Client c){
		Connection conn = DBUtil.getConn();
		SelectServiceDAO dao = new SelectServiceDAOImp(conn);
		Client client = dao.selectClient(c);
		return client;
	}
	public List<Product> selectProduct(Product p){
		List<Product> products;
		Connection conn = DBUtil.getConn();
		SelectServiceDAO dao = new SelectServiceDAOImp(conn);
		products = dao.selectProduct(p);
		return products;
	}
}
