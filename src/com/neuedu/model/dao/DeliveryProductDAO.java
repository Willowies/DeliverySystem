package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.DeliveryStockProduct;

public interface DeliveryProductDAO {

	List<DeliveryStockProduct> searchStockProduct(String productName);

}
