package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.CenterOutRecord;

public interface CenterOutRecordDAO {

	boolean outCenter(int transferId,String employee);
	boolean testOut(int transferId);
	List<CenterOutRecord> selectCenterOut(Date outDate, String productName);
	List<CenterOutRecord> selectCenterOut(Date outDate);

}
