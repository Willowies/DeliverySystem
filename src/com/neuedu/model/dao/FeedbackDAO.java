package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.ProInfo;

public interface FeedbackDAO {
	
	public void feedbackRecord(int workId, int customerFeedback, String remark);
	
	public List<ProInfo> selectPageProduct(java.util.Date beginDate, java.util.Date endDate, String productName, int pageNum);
	
	public int selectPageProduct(java.util.Date beginDate, java.util.Date endDate, String productName);
}
