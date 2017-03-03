/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamSearchOptionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jftt.wifi.bean.exam.enumtype.ExamState;
import com.jftt.wifi.bean.knowledge_contest.SearchOptionBaseWithPagination;

/**
 * class name:ExamSearchOptionBean <BR>
 * class description: 考试查询条件Vo <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public class ExamSearchOptionVo extends SearchOptionBaseWithPagination {
	private String userId;
	/**
	 * 子公司ID
	 */
	private Integer subCompanyId;
	
	private List<Long> subCompanyIdList;
	
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 考试名称
	 */
	private String title;
	/**
	 * 考试开始时间 的 起始时间
	 */
	private String beginTimeBegin;
	/**
	 * 考试开始时间 的 结束时间
	 */
	private String beginTimeEnd;
	/**
	 * 考试创建时间 的 起始时间
	 */
	private String createTimeBegin;
	/**
	 * 考试创建时间 的 结束时间
	 */
	private String createTimeEnd;
	/**
	 * 考试状态
	 */
	private ExamState examState;
	/**
	 * 考试ID列表
	 */
	private List<Integer> idList = new ArrayList<Integer>();
	/**  
	 * 考试ID
	 */
	private Integer examId;
	/**
	 * 考试类型
	 */
	private Integer type;
	
	
	/**
	 * wj add
	 * 额外参数
	 * 是否统计初始管理员 no:不统计
	 */
	private String withManager;

	public Integer getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getBeginTimeBegin() {
		return beginTimeBegin;
	}

	public void setBeginTimeBegin(String beginTimeBegin) {
		this.beginTimeBegin = beginTimeBegin;
	}

	public String getBeginTimeEnd() {
		return beginTimeEnd;
	}

	public void setBeginTimeEnd(String beginTimeEnd) {
		this.beginTimeEnd = beginTimeEnd;
	}

	public String getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public ExamState getExamState() {
		return examState;
	}

	public void setExamState(ExamState examState) {
		this.examState = examState;
	}

	/**
	 * Method name: getIdListStr <BR>
	 * Description: 获取用逗号分隔的ID列表字符串，用于sql查询 <BR>
	 * Remark: <BR>
	 * 
	 * @return String<BR>
	 */
	public String getIdListStr() {
		if (idList.size() > 0) {
			return StringUtils.join(idList, ",");
		}
		return null;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWithManager() {
		return withManager;
	}

	public void setWithManager(String withManager) {
		this.withManager = withManager;
	}

	public List<Long> getSubCompanyIdList() {
		return subCompanyIdList;
	}

	public void setSubCompanyIdList(List<Long> subCompanyIdList) {
		this.subCompanyIdList = subCompanyIdList;
	}

}
