package com.neuedu.model.po;

public class Feedback {
	
	private int feedbackId;
	private int wordId;
	private int customerFeedback;
	private String remark;
	private int recordStatus; //判断是否已经录入
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	public int getCustomerFeedback() {
		return customerFeedback;
	}
	public void setCustomerFeedback(int customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}
	
	
	
}
