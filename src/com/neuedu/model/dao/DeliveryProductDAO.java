package com.neuedu.model.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.neuedu.model.po.DeliveryReturnProduct;
import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.DeliveryWarehouseOrder;

public interface DeliveryProductDAO {

	List<DeliveryStockProduct> searchStockProduct(String productName);

	void stock(int productId, int productQuantity, String operator);

	List<DeliveryReturnProduct> searchReturnProduct(String supName, String productCode, Date start, Date end);

	void returnProduct(int productId, int productQuantity, String operator);

	List<DeliveryStockProduct> searchWarehouseProduct(String productName);

	void modify(int productId, int warning, int max, String operator);

	List<DeliveryStockProduct> searchWarehouseValue(String productName, String warehouseName);

	List<DeliveryWarehouseOrder> searchOrder(String productName, Date start, Date end);

	List<DeliveryWarehouseOrder> orderStatistics(Date start, Date end);

	List<DeliveryWarehouseOrder> substationStatistics(Date start, Date end);

	HashMap<String, String> satisfyStatistics(Date start, Date end);

}
