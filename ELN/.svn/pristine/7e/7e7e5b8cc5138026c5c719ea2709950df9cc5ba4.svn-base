/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CoursePromotionPath.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-31        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.Date;
import java.util.List;

import com.jftt.wifi.common.Constant;

/**
 * class name:CoursePromotionPath <BR>
 * class description: 岗位体系--晋升路径<BR>
 * Remark: <BR>
 * @version 1.00 2015-7-31
 * @author JFTT)HeTianrui
 */
public class CoursePromotionPath {

	private Integer id;			//晋升路径ID，自增ID
	private String name;		//晋升路径名称
	private String description;	//晋升路径描述
	private int isDisabled;	 	//是否停用：1停用，0启用
	private int isDeleted;		//是否删除（逻辑删除）：1逻辑删除，0非删除
	private Date updateTime;	//最后更新时间
	private Integer companyId;	//公司ID
	
	private List<CoursePromotionPathStage> stageList;//阶段list
	
	private Integer userId;//用户ID
	private String postName;//岗位名称
	
	//分页
	private Integer pageStartSize;//limit pageStartSize,pageSize
	private Integer pageIndex;//当前页
	private Integer pageSize;//每页显示记录数
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getPageStartSize() {
		return pageStartSize;
	}
	public void setPageStartSize(Integer pageStartSize) {
		this.pageStartSize = pageStartSize;
	}
	public Integer getPageIndex() {
		return pageIndex==null?1:pageIndex<0?1:pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex==null?1:pageIndex<0?1:pageIndex;
	}
	public Integer getPageSize() {
		return pageSize==null?Constant.PAGE_SIZE:pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize==null?Constant.PAGE_SIZE:pageSize;
	}
	public List<CoursePromotionPathStage> getStageList() {
		return stageList;
	}
	public void setStageList(List<CoursePromotionPathStage> stageList) {
		this.stageList = stageList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
}
