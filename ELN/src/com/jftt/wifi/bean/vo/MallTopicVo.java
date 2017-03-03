package com.jftt.wifi.bean.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MallTopicVo {

	 private Integer id;

	    private String name;

	    private String code;

	    private BigDecimal price;

	    private BigDecimal cheapPrice;

	    private String coverImage;

	    private String bannerImage;

	    private Integer createUserId;

	    private String createTime;

	    private Integer updateUserId;

	    private String updateTime;

	    private Integer putawayUserId;

	    private String putawayTime;

	    private Integer status;

	    private Integer isDelete;

	    private String descr;
	    
	    private Integer courseCount;//专题绑定的课程数量
	    
	    private Integer sellCount;
	    
	    private List<MallCourseVo> courseList;
	    
	    
	    private String beginTime;
	    private String endTime;
	    private Integer pageIndex;
	    private Integer pageSize;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public BigDecimal getCheapPrice() {
			return cheapPrice;
		}

		public void setCheapPrice(BigDecimal cheapPrice) {
			this.cheapPrice = cheapPrice;
		}

		public String getCoverImage() {
			return coverImage;
		}

		public void setCoverImage(String coverImage) {
			this.coverImage = coverImage;
		}

		public String getBannerImage() {
			return bannerImage;
		}

		public void setBannerImage(String bannerImage) {
			this.bannerImage = bannerImage;
		}

		public Integer getCreateUserId() {
			return createUserId;
		}

		public void setCreateUserId(Integer createUserId) {
			this.createUserId = createUserId;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public Integer getUpdateUserId() {
			return updateUserId;
		}

		public void setUpdateUserId(Integer updateUserId) {
			this.updateUserId = updateUserId;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public Integer getPutawayUserId() {
			return putawayUserId;
		}

		public void setPutawayUserId(Integer putawayUserId) {
			this.putawayUserId = putawayUserId;
		}

		public String getPutawayTime() {
			return putawayTime;
		}

		public void setPutawayTime(String putawayTime) {
			this.putawayTime = putawayTime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getIsDelete() {
			return isDelete;
		}

		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}

		public String getDescr() {
			return descr;
		}

		public void setDescr(String descr) {
			this.descr = descr;
		}

		public Integer getCourseCount() {
			return courseCount;
		}

		public void setCourseCount(Integer courseCount) {
			this.courseCount = courseCount;
		}
		

		public Integer getSellCount() {
			return sellCount;
		}

		public void setSellCount(Integer sellCount) {
			this.sellCount = sellCount;
		}

		public List<MallCourseVo> getCourseList() {
			return courseList;
		}

		public void setCourseList(List<MallCourseVo> courseList) {
			this.courseList = courseList;
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
	    
	    
}
