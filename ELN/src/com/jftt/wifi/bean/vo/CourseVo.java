/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月27日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:CourseVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月27日
 * @author JFTT)Lu Yunlong
 */
public class CourseVo {

	private Integer id;
	private String type;
	private String name;
	private String likeName;
	private String code;
	private Integer status;
	private int page;
	private int pageSize;
	private int fromNum;
	private String shareStatus;
	private String[] categoryIds;
	private String[] typeIds;
	private Integer companyId;
	private Integer subCompanyId;
	private String createUserId;
	private String queryType;
	private Integer postId;
	private Integer courseType;//岗位关联课程类型：1 选修;2 必修
	private Integer mallStatus;//1普通课程2商城课程3购买后的未加入课程库的课程4已加入课程库的课程
	private String sonCompanyId;
	
	
	public String getSonCompanyId() {
		return sonCompanyId;
	}
	public void setSonCompanyId(String sonCompanyId) {
		this.sonCompanyId = sonCompanyId;
	}
	public Integer getMallStatus() {
		return mallStatus;
	}
	public void setMallStatus(Integer mallStatus) {
		this.mallStatus = mallStatus;
	}
	public String getLikeName() {
		return likeName;
	}
	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String[] getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(String[] typeIds) {
		this.typeIds = typeIds;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public String[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return fromNum;
	}
	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
	}
	public String getShareStatus() {
		return shareStatus;
	}
	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	
}
