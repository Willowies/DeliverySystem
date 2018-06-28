package com.neuedu.model.dao;

import com.neuedu.model.po.ReceiveRecord;

public interface ReceiveRecordDAO {

	boolean receiveProduct(ReceiveRecord rr, String employee);
	boolean testReceive(int workId);

}
