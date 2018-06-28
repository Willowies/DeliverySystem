package com.neuedu.model.dao;

import com.neuedu.model.po.CenterInRecord;

public interface CenterInRecordDAO {

	boolean intoCenter(CenterInRecord cInRecord, String employee);
	boolean testInto(int purchaseId);

}
