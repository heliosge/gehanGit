package com.jftt.wifi.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MallSellRecordVo {

	
	private Integer id;
	private Integer courseId;
	private Integer orderId;
	private BigDecimal price;
	private String courseName;
	private String payUserName;
	private Integer payUserId;
	private Integer status;
	private String gatheringUserName;
	private Integer gatheringUserId;
	private String createTime;
	private String updateTime;
	private Integer companyId;
	private Integer subCompanyId;
	private Integer isDelete;
	
	private String orderCode;
	private String companyName;
	private String statusStr;
	private String buyCompanyName;//购买企业
	
	//查询条件
	private String [] categoryIds;
	private String beginPrice;
	private String endPrice;
	private String beginTime;
	private String endTime;
	private Integer pageIndex;
	private Integer pageSize;
	private Integer type;//type不为空时，批量查询第三方的销售记录
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getPayUserName() {
		return payUserName;
	}
	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}
	public Integer getPayUserId() {
		return payUserId;
	}
	public void setPayUserId(Integer payUserId) {
		this.payUserId = payUserId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
		if(status!=null){
			if(status==1){
				this.statusStr="已付款";
			}else if(status==2){
				this.statusStr="已收款";
			}else{
				this.statusStr="未付款";
			}
		}
	}
	public String getGatheringUserName() {
		return gatheringUserName;
	}
	public void setGatheringUserName(String gatheringUserName) {
		this.gatheringUserName = gatheringUserName;
	}
	public Integer getGatheringUserId() {
		return gatheringUserId;
	}
	public void setGatheringUserId(Integer gatheringUserId) {
		this.gatheringUserId = gatheringUserId;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBeginPrice() {
		return beginPrice;
	}
	public void setBeginPrice(String beginPrice) {
		this.beginPrice = beginPrice;
	}
	public String getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getBuyCompanyName() {
		return buyCompanyName;
	}
	public void setBuyCompanyName(String buyCompanyName) {
		this.buyCompanyName = buyCompanyName;
	}
	
	
	
	
	
	
}
