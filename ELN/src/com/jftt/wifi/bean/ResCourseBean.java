/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResCourseBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;
import java.util.List;

/**
 * class name:ResCourseBean <BR>
 * class description: 课程bean <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-20
 * @author JFTT)ShenHaijun
 */
/**
 * @author JFTT)zhangchen<BR>
 * class name:ResCourseBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-14
 */
public class ResCourseBean {
	private Integer id;//课程id
	private Integer categoryId;//课程体系id
	private String categoryName;//课程体系名称
	private String code;//编码
	private String name;//名称
	private Integer type;//类型（1：线上课程 2：线下课程）
	private Integer learnTime;//学时
	private Integer learnScore;//学分
	private String tags;//标签
	private String crowd;//适合人群
	private String frontImage;//封面图片
	private String outline;//大纲
	private Integer createUserId;//创建人id
	private Date createTime;//创建时间
	private Integer companyId;//公司id
	private String companyName;//公司名称
	private Integer subCompanyId;//子公司id
	private Integer isSpread;//是否推广1：推广；2：不推广；
	private Integer status;//1：未发布；2：发布；
	private Date shareTime;//共享时间
	private Integer shareStatus;//共享状态。1为未共享，2为提交共享，3为共享驳回，4为共享通过，5为提交普连共享，6为普连驳回。7为普连通过
	private Integer isFeatured;//是否精选，1：不是精选（默认） 2：精选
	private Integer teacherId;//讲师
	private String teacherName;//讲师名称
	private List<ManageDepartmentBean> deptList;//部门列表
	private List<ManagePostBean> postList;//岗位列表
	private List<ManageGroupBean> groupList;//群组列表
	private String typeName;//课程分类
	private Integer fromNum;
	private Integer page;
	private String checkReason;
	private int mallStatus;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	//zhangchen add
	private Integer userId;//用户ID
    
    
    public List<ManageDepartmentBean> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<ManageDepartmentBean> deptList) {
		this.deptList = deptList;
	}
	public String getCheckReason() {
		return checkReason;
	}
	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	public Integer getFromNum() {
		return fromNum;
	}
	public void setFromNum(Integer fromNum) {
		this.fromNum = fromNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<ManagePostBean> getPostList() {
		return postList;
	}
	public void setPostList(List<ManagePostBean> postList) {
		this.postList = postList;
	}
	public List<ManageGroupBean> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<ManageGroupBean> groupList) {
		this.groupList = groupList;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getIsFeatured() {
		return isFeatured;
	}
	public void setIsFeatured(Integer isFeatured) {
		this.isFeatured = isFeatured;
	}
	public Date getShareTime() {
		return shareTime;
	}
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}
	public Integer getShareStatus() {
		return shareStatus;
	}
	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsSpread() {
		return isSpread;
	}
	public void setIsSpread(Integer isSpread) {
		this.isSpread = isSpread;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCrowd() {
		return crowd;
	}
	public void setCrowd(String crowd) {
		this.crowd = crowd;
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public int getMallStatus() {
		return mallStatus;
	}
	public void setMallStatus(int mallStatus) {
		this.mallStatus = mallStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
