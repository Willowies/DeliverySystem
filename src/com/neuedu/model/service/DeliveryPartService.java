package com.neuedu.model.service;

import java.sql.Connection;
import java.util.List;

import com.neuedu.model.dao.DeliveryProductDAO;
import com.neuedu.model.dao.DeliveryProductDAOImp;
import com.neuedu.model.dao.WorkOrderDAO;
import com.neuedu.model.dao.WorkOrderDAOImp;
import com.neuedu.model.po.DeliveryStockProduct;
import com.neuedu.model.po.SubReturnRecord;
import com.neuedu.model.po.WorkOrder;
import com.neuedu.utils.DBUtil;

public class DeliveryPartService {
	//µ¥ÀýÄ£Ê½
	private static DeliveryPartService service = new DeliveryPartService();

	private DeliveryPartService(){}

	public static DeliveryPartService getInstance() {
		return service;
	}

	public List<DeliveryStockProduct> searchLackProduct(String productName) {
		Connection conn = DBUtil.getConn();
		DeliveryProductDAO dao = new DeliveryProductDAOImp(conn);
		List<DeliveryStockProduct> l =  dao.searchStockProduct(productName);
		DBUtil.closeConn(conn);
		return l;
	}

}
