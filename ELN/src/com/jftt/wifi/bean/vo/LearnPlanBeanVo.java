package com.jftt.wifi.bean.vo;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jftt.wifi.bean.LearnPlanBean;
import com.jftt.wifi.util.TimeUtil;
/**
 * class LearnPlanBeanVo <BR>
 * class description: 学习计划展示bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-10
 * @author zhangbocheng
 * 
 */
public class LearnPlanBeanVo {
      private Integer id; //学习计划id
      private String name;//计划名称
      private String type; //学习计划类型
      private String beginTime;//计划开始时间（年月日）
      private String endTime;//计划结束时间（年月日）
      private Integer isPublished;//是否已发布：1已发布，0未发布
      private String createTime;//创建时间
      private Integer createUserId;//创建人id
      private Integer createUser;//创建人
      private String updateTime;//最后更新时间 
      private Integer companyId;//公司ID
      private Integer subCompanyId;//子公司
      private Integer learnTime;//总学时
      private Integer learnScore;//总学分
      private Integer status;//状态
      private String description;//计划描述
      private String coverImage;//计划封面（路径）
      private Integer isDeleted;//是否删除（逻辑删除）：1逻辑删除，0非删除

      
      
      
      
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



	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	private Date getDate4String(String date,String pattern){
		try{
			return TimeUtil.parseString2Date(date, pattern);
		}catch(Exception e){
			return null;
		}
	}
	
	
	public LearnPlanBean vo2Bean(){
		LearnPlanBean bean = new LearnPlanBean();
		bean.setCompanyId(companyId);
		bean.setSubCompanyId(subCompanyId);
		bean.setCoverImage(coverImage);
		bean.setCreateUserId(createUserId);
		bean.setDescription(description);
		bean.setId(id);
		bean.setIsPublished(isPublished);
		bean.setName(name);
		bean.setType(type);
		bean.setBeginTime(getDate4String(beginTime, "yyyy-MM-dd HH:mm"));
		bean.setEndTime(getDate4String(endTime, "yyyy-MM-dd HH:mm"));
		return bean;
	}
	
	
      
      
}
