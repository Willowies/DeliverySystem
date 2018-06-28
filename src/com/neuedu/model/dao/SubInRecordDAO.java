package com.neuedu.model.dao;

import com.neuedu.model.po.SubInRecord;

public interface SubInRecordDAO {

	boolean inSubWarehouse(SubInRecord sir, String employee);
	boolean testInto(int distributionId);

}
