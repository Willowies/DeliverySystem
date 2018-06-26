package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Product;



public interface ProductDAO {
	public void createProduct(Product p );
	public List<Product> selectProduct(Product p);
	public void updateProduct(Product p);
	public void deleteProduct(Product p);
}
