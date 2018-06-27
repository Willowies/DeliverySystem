package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.SubReturnRecord;

public interface SubReturnRecordDAO {

	void returnRecord(int workId, int q,String operator);

	List<SubReturnRecord> searchReturnProduct(Date start, Date end, int warehouseId);

	void subReturnOut(int productId, int productQuantity, Date start, Date end, int warehouseId);

	List<SubReturnRecord> searchSubReturnProduct(int id);

	void centerReturnIn(int productId, int productQuantity, int orderId);

	List<SubReturnRecord> searchCenterReturnProduct(int id);

	void centerReturnOut(int productId, int productQuantity, int orderId);
	
	
	
}
