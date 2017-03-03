package com.jftt.wifi.bean.vo;

import java.math.BigDecimal;

public class MallOrderVo {
	 private Integer id;

	    private String code;

	    private Integer userId;

	    private Integer commodityType;

	    private BigDecimal price;

	    private String userRealName;

	    private String invoiceTitle;

	    private String invoicePhone;

	    private String invoiceAddress;

	    private String invoicePostcode;

	    private Integer type;

	    private Integer status;

	    private Integer invoiceStatus;

	    private Integer payStatus;

	    private Integer gatheringStatus;

	    private String postUserName;

	    private Integer postUserId;

	    private Integer invoiceUserId;

	    private Integer logisticsCompanyId;

	    private String logisticsNumber;

	    private Integer logisticsUserId;
	    private String logisticsTime;

	    private String payUserName;

	    private Integer payUserId;
	    
	    private String payTime;

	    private String companyName;

	    private String contactWay;

	    private Integer isNeedInvoice;

	    private String evaluation;

	    private String gatheringUserName;

	    private Integer gatheringUserId;
	    
	    private String remarks;

	    private String createTime;

	    private String updateTime;

	    private Integer isDelete;
	    
	    private String names;//订单内商品名称集合
	    private String relateIds;//订单内商品ID集合
	    
	    private String logisticsCompanyName;//物流公司名称
	    private String logisticsCode;//物流公司编号
	    private String logisticsPort;//物流公司接口
	    
	    
	    
	    

		private String statusStr;
		private String invoiceStatusStr;
		private String sellCompanyName;//卖家企业
	    
	    //查询条件
	    private String courseName;
	    private String topicName;
	    private String name;
	    private String beginTime;
	    private String endTime;
	    private BigDecimal beginPrice;
	    private BigDecimal endPrice;
	    private Integer pageIndex;
	    private Integer pageSize;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public Integer getCommodityType() {
			return commodityType;
		}
		public void setCommodityType(Integer commodityType) {
			this.commodityType = commodityType;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getUserRealName() {
			return userRealName;
		}
		public void setUserRealName(String userRealName) {
			this.userRealName = userRealName;
		}
		public String getInvoiceTitle() {
			return invoiceTitle;
		}
		public void setInvoiceTitle(String invoiceTitle) {
			this.invoiceTitle = invoiceTitle;
		}
		public String getInvoicePhone() {
			return invoicePhone;
		}
		public void setInvoicePhone(String invoicePhone) {
			this.invoicePhone = invoicePhone;
		}
		public String getInvoiceAddress() {
			return invoiceAddress;
		}
		public void setInvoiceAddress(String invoiceAddress) {
			this.invoiceAddress = invoiceAddress;
		}
		public String getInvoicePostcode() {
			return invoicePostcode;
		}
		public void setInvoicePostcode(String invoicePostcode) {
			this.invoicePostcode = invoicePostcode;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
			if(status!=null){
				 if(status==0){
					 this.statusStr="待付款" ;
	 				 }else if(status==1){
	 					this.statusStr="已付款" ;
	 				 }else if(status==2){
	 					this.statusStr="已发货" ;
	 				 }else if(status==3){
	 					this.statusStr="已评价" ;
	 				 }else if(status==4){
	 					this.statusStr="已失效" ;
	 				 }else if(status==5){
	 					this.statusStr="已取消" ;
	 				 }
			}
		}
		public Integer getInvoiceStatus() {
			return invoiceStatus;
		}
		public void setInvoiceStatus(Integer invoiceStatus) {
			this.invoiceStatus = invoiceStatus;
			if(invoiceStatus!=null){
				 if(invoiceStatus==0){
					 this.invoiceStatusStr="待邮寄" ;
	 				 }else if(invoiceStatus==1){
	 					this.invoiceStatusStr="已邮寄" ;
	 				 }else if(invoiceStatus==2){
	 					this.invoiceStatusStr="已签收" ;
	 				 }
			}
		}
		public Integer getPayStatus() {
			return payStatus;
		}
		public void setPayStatus(Integer payStatus) {
			this.payStatus = payStatus;
		}
		public Integer getGatheringStatus() {
			return gatheringStatus;
		}
		public void setGatheringStatus(Integer gatheringStatus) {
			this.gatheringStatus = gatheringStatus;
		}
		public String getPostUserName() {
			return postUserName;
		}
		public void setPostUserName(String postUserName) {
			this.postUserName = postUserName;
		}
		public Integer getPostUserId() {
			return postUserId;
		}
		public void setPostUserId(Integer postUserId) {
			this.postUserId = postUserId;
		}
		public Integer getInvoiceUserId() {
			return invoiceUserId;
		}
		public void setInvoiceUserId(Integer invoiceUserId) {
			this.invoiceUserId = invoiceUserId;
		}
		public Integer getLogisticsCompanyId() {
			return logisticsCompanyId;
		}
		public void setLogisticsCompanyId(Integer logisticsCompanyId) {
			this.logisticsCompanyId = logisticsCompanyId;
		}
		public String getLogisticsNumber() {
			return logisticsNumber;
		}
		public void setLogisticsNumber(String logisticsNumber) {
			this.logisticsNumber = logisticsNumber;
		}
		public Integer getLogisticsUserId() {
			return logisticsUserId;
		}
		public void setLogisticsUserId(Integer logisticsUserId) {
			this.logisticsUserId = logisticsUserId;
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
		public String getPayTime() {
			return payTime;
		}
		public void setPayTime(String payTime) {
			this.payTime = payTime;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getContactWay() {
			return contactWay;
		}
		public void setContactWay(String contactWay) {
			this.contactWay = contactWay;
		}
		public Integer getIsNeedInvoice() {
			return isNeedInvoice;
		}
		public void setIsNeedInvoice(Integer isNeedInvoice) {
			this.isNeedInvoice = isNeedInvoice;
		}
		public String getEvaluation() {
			return evaluation;
		}
		public void setEvaluation(String evaluation) {
			this.evaluation = evaluation;
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
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
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
		public Integer getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}
		public String getNames() {
			return names;
		}
		public void setNames(String names) {
			this.names = names;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getTopicName() {
			return topicName;
		}
		public void setTopicName(String topicName) {
			this.topicName = topicName;
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
		public BigDecimal getBeginPrice() {
			return beginPrice;
		}
		public void setBeginPrice(BigDecimal beginPrice) {
			this.beginPrice = beginPrice;
		}
		public BigDecimal getEndPrice() {
			return endPrice;
		}
		public void setEndPrice(BigDecimal endPrice) {
			this.endPrice = endPrice;
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
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRelateIds() {
			return relateIds;
		}
		public void setRelateIds(String relateIds) {
			this.relateIds = relateIds;
		}
		public String getLogisticsTime() {
			return logisticsTime;
		}
		public void setLogisticsTime(String logisticsTime) {
			this.logisticsTime = logisticsTime;
		}
		public String getLogisticsCompanyName() {
			return logisticsCompanyName;
		}
		public void setLogisticsCompanyName(String logisticsCompanyName) {
			this.logisticsCompanyName = logisticsCompanyName;
		}
		public String getLogisticsCode() {
			return logisticsCode;
		}
		public void setLogisticsCode(String logisticsCode) {
			this.logisticsCode = logisticsCode;
		}
		public String getLogisticsPort() {
			return logisticsPort;
		}
		public void setLogisticsPort(String logisticsPort) {
			this.logisticsPort = logisticsPort;
		}
		public String getStatusStr() {
			return statusStr;
		}
		public void setStatusStr(String statusStr) {
			this.statusStr = statusStr;
		}
		public String getInvoiceStatusStr() {
			return invoiceStatusStr;
		}
		public void setInvoiceStatusStr(String invoiceStatusStr) {
			this.invoiceStatusStr = invoiceStatusStr;
		}
		public String getSellCompanyName() {
			return sellCompanyName;
		}
		public void setSellCompanyName(String sellCompanyName) {
			this.sellCompanyName = sellCompanyName;
		}
	    
	    

}
