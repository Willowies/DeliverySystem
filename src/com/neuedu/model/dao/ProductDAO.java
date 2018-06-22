package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Product;


public interface ProductDAO {
	List<Product> selectProduct(Product p);
}
