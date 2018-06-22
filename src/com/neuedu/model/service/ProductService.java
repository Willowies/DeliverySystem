package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.ProductDAO;
import com.neuedu.model.dao.ProductDAOImp;
import com.neuedu.model.po.Product;
import com.neuedu.utils.DBUtil;



public class ProductService {
	private ProductService(){
	}
	
	private static ProductService service = new ProductService();
	
	public static ProductService getInstance(){
		return service;
	}
	public List<Product> selectProduct(Product p){
		List<Product> products;
		Connection conn = DBUtil.getConn();
		ProductDAO dao = new ProductDAOImp(conn);
		products = dao.selectProduct(p);
		return products;
	}
}
