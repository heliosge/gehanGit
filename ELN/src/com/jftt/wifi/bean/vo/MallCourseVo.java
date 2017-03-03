package com.jftt.wifi.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.jftt.wifi.bean.MallCourseBean;
import com.jftt.wifi.bean.ResCourseBean;

public class MallCourseVo {
	
	    private Integer id;

	    private Integer courseId;

	    private Integer categoryId;//分类ID
	    
	    private String categoryName;//分类名称

	    private BigDecimal price;//原价

	    private BigDecimal cheapPrice;//打折后价格

	    private Integer discount;//折扣

	    private String putawayTime;//上架时间

	    private Integer putawayUserId;

	    private Integer status;

	    private Integer isCopy;

	    private Integer copyCourseId;

	    private Integer isDelete;
	    
	    //course
	    private String code;//编码
		private String name;//名称
		private Integer learnTime;//学时
		private Integer learnScore;//学分
		private String frontImage;//封面图片
		private String outline;//大纲
		private Integer createUserId;//创建人id
		private String createTime;//创建时间
		private Integer companyId;//公司id
		private Integer subCompanyId;//子公司id
		private String companyName;//公司名称
	    
		
		private Integer selectCourseId;//选择的课程id
	    private Integer topicId;//专题id
	    private Integer sellCount;//销售数量
	    
	    
	    
	    //查询
	    private String [] categoryIds;
	    private Integer pageIndex;
	    private Integer pageSize;
	    private String beginTime;
	    private String endTime;
	    private Integer isAll; //0或者为空时 只查询本公司的，1时查询所有上架课程

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

		public Integer getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Integer categoryId) {
			this.categoryId = categoryId;
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

		public Integer getDiscount() {
			return discount;
		}

		public void setDiscount(Integer discount) {
			this.discount = discount;
		}

		public String getPutawayTime() {
			return putawayTime;
		}

		public void setPutawayTime(String putawayTime) {
			this.putawayTime = putawayTime;
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

		public Integer getIsCopy() {
			return isCopy;
		}

		public void setIsCopy(Integer isCopy) {
			this.isCopy = isCopy;
		}

		public Integer getCopyCourseId() {
			return copyCourseId;
		}

		public void setCopyCourseId(Integer copyCourseId) {
			this.copyCourseId = copyCourseId;
		}

		public Integer getIsDelete() {
			return isDelete;
		}

		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
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

		public String[] getCategoryIds() {
			return categoryIds;
		}

		public void setCategoryIds(String[] categoryIds) {
			this.categoryIds = categoryIds;
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


		public Integer getLearnTime() {
			return learnTime;
		}

		public void setLearnTime(Integer learnTime) {
			this.learnTime = learnTime;
		}

		public Integer getLearnScore() {
			return learnScore;
		}

		public void setLearnScore(Integer learnScore) {
			this.learnScore = learnScore;
		}

		public String getFrontImage() {
			return frontImage;
		}

		public void setFrontImage(String frontImage) {
			this.frontImage = frontImage;
		}

		public String getOutline() {
			return outline;
		}

		public void setOutline(String outline) {
			this.outline = outline;
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
		
		

		public Integer getSelectCourseId() {
			return selectCourseId;
		}

		public void setSelectCourseId(Integer selectCourseId) {
			this.selectCourseId = selectCourseId;
		}
		
		

		public Integer getTopicId() {
			return topicId;
		}

		public void setTopicId(Integer topicId) {
			this.topicId = topicId;
		}

		public Integer getSellCount() {
			return sellCount;
		}

		public void setSellCount(Integer sellCount) {
			this.sellCount = sellCount;
		}
		
		

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
		

		public Integer getIsAll() {
			return isAll;
		}

		public void setIsAll(Integer isAll) {
			this.isAll = isAll;
		}

		public MallCourseBean getMallCourseBean(MallCourseBean bean ){
			bean.setId(id);
			bean.setPrice(price);
			bean.setCourseId(courseId);
			bean.setCheapPrice(cheapPrice);
			bean.setCategoryId(categoryId);
			bean.setCopyCourseId(copyCourseId);
			bean.setDiscount(discount);
			bean.setIsCopy(isCopy);
			bean.setPutawayUserId(putawayUserId);
			bean.setStatus(status);
			return bean;
		}
		public ResCourseBean getResCourseBean(ResCourseBean bean){
			bean.setId(courseId);
		    bean.setName(name);
		    bean.setCode(code);
		    bean.setCompanyId(companyId);
		    bean.setSubCompanyId(subCompanyId);
		    bean.setCreateUserId(createUserId);
		    bean.setFrontImage(frontImage);
		    bean.setLearnScore(learnScore);
		    bean.setOutline(outline);
		    bean.setLearnTime(learnTime);
		    bean.setType(1);
			bean.setStatus(1);
			bean.setMallStatus(2);
			return bean;
		}
	    
		public void resCourseToVo(ResCourseBean bean){
			this.name=bean.getName();
			this.code=bean.getCode();
			this.frontImage= bean.getFrontImage();
			this.outline =bean.getOutline();
			this.selectCourseId = bean.getId();
		}

}
