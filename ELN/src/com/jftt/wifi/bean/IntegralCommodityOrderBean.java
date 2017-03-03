package com.jftt.wifi.bean;

import java.util.Date;

/**
 * 积分商品兑换
 * @author zhangbocheng
 *
 */
public class IntegralCommodityOrderBean {
	private Integer id;
	private Integer commodityId;//商品ID
	private Integer commodityCount;//商品数量
	private Integer allIntegral;//总积分
	private Integer userId;
	private Date createTime;
	private String descr;
	private String userRealName;
	private String phoneNum;//联系电话
	private String address;//地址
	private String postcode;//邮编
	private Integer logisticsCompanyId;//物流公司ID
	private String logisticsNumber;//物流单号
	private Integer logisticsUserId;//发货人
	private String logisticsTime;//发货时间
	private Integer status;//状态，0未发货，1已发货
	
	private String commodityName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	public String getLogisticsTime() {
		return logisticsTime;
	}
	public void setLogisticsTime(String logisticsTime) {
		this.logisticsTime = logisticsTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(Integer commodityCount) {
		this.commodityCount = commodityCount;
	}
	public Integer getAllIntegral() {
		return allIntegral;
	}
	public void setAllIntegral(Integer allIntegral) {
		this.allIntegral = allIntegral;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	

	
}
