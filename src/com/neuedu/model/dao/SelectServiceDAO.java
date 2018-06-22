package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.Client;
import com.neuedu.model.po.Product;


public interface SelectServiceDAO {
	Client selectClient(Client c);
	List<Product> selectProduct(Product p);
}
