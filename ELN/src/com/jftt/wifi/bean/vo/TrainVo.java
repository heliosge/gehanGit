package com.jftt.wifi.bean.vo;

public class TrainVo {
	private int page;
	private int pageSize;
	private int fromNum;
	private String timeType;
	private String timeValue;
	private String status;
	private String createUserName;
	private String startTime;
	private String endTime;
	private String submitUserName;
	private String submitDept;
	private String type;
	private String companyId;
	private String subCompanyId;
	private String checkUserId;
	private String trainId;
	
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFromNum() {
		return (page-1)*pageSize;
	}
	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSubmitUserName() {
		return submitUserName;
	}
	public void setSubmitUserName(String submitUserName) {
		this.submitUserName = submitUserName;
	}
	public String getSubmitDept() {
		return submitDept;
	}
	public void setSubmitDept(String submitDept) {
		this.submitDept = submitDept;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(String subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	
	
}
