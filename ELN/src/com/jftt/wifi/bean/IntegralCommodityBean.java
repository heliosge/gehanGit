package com.jftt.wifi.bean;

import java.util.Date;

/**
 * 积分商品
 * @author zhangbocheng
 *
 */
public class IntegralCommodityBean {
	
	private Integer id;
	private String code;//编码
    private String name;//名称
    private Integer categoryId;//分类
    private Integer integral;//兑换积分
    private String coverImage;//封面图片
    private String showImage;//展示图片
    private Integer stock;//库存
    private Integer sellCount;//销售数量
    private String descr;//描述
    private Integer updateUserId;//修改人
    private Date createTime;
    private Date updateTime;
    private Integer createUserId;//创建人
    private Integer putawayUserId;//上架人
    private Date putawayTime;//上架时间
    private Integer status;//状态
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getShowImage() {
		return showImage;
	}
	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getPutawayUserId() {
		return putawayUserId;
	}
	public void setPutawayUserId(Integer putawayUserId) {
		this.putawayUserId = putawayUserId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getPutawayTime() {
		return putawayTime;
	}
	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}
	public Integer getSellCount() {
		return sellCount;
	}
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

    


}
