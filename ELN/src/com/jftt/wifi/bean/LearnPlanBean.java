package com.jftt.wifi.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jftt.wifi.util.TimeUtil;
/**
 * class LearnPlanBean <BR>
 * class description: 学习计划bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-6
 * @author zhangbocheng
 * 
 */
public class LearnPlanBean {
      private Integer id; //学习计划id
      private String name;//计划名称
      private String type; //学习计划类型
//      @DateTimeFormat(pattern="yyyy-MM-dd")
      private Date beginTime;//计划开始时间（年月日）
//      @DateTimeFormat(pattern="yyyy-MM-dd")
      private Date endTime;//计划结束时间（年月日）
      private String description;//计划描述
      private String coverImage;//计划封面（路径）
      private Integer isPublished;//是否已发布：1已发布，0未发布
      private Date createTime;//创建时间
      private Integer createUserId;//创建人id
      private Date updateTime;//最后更新时间 
      private Integer isDeleted;//是否删除（逻辑删除）：1逻辑删除，0非删除
      private Integer companyId;//公司ID
      private Integer subCompanyId;//子公司
      
      
      
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

	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public Integer getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
      
      
}
