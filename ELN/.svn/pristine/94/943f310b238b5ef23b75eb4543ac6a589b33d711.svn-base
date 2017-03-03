/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: PaperBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class name:PaperBean <BR>
 * class description: 试卷Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public class PaperBean {
	/**
	 * 试卷主键ID
	 */
	private Integer id;
	/**
	 * 试卷标题
	 */
	private String title;
	/**
	 * 组卷方式
	 */
	private Integer organizingMode;
	/**
	 * 试卷总分
	 */
	private Integer totalScore;
	/**
	 * 试卷描述
	 */
	private String description;
	/**
	 * 试卷分类ID
	 */
	private Integer categoryId;
	/**
	 * 试卷分类
	 */
	private PaperCategoryBean paperCategory;
	/**
	 * 是否启用
	 */
	private Boolean isEnabled;
	/**
	 * 创建者ID
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 试卷关联试题
	 */
	private List<PaperQuestionBean> paperQuestionList = new ArrayList<PaperQuestionBean>();
	/**  
	 * 自由组卷规则列表
	 */
	private List<ExamOrganizingRuleBean> organizingRuleList = new ArrayList<ExamOrganizingRuleBean>();
	/**chenrui start*/
	
	private int count1;//主观题
	private int count2;//单选题
	private int count3;//多选题
	private int count4;//判断
	private int count5;//填空
	private int count6;//组合
	private int count7;//多媒体
	private String questionsTypes;
	private String categoryName;
	
	
	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getCount4() {
		return count4;
	}

	public void setCount4(int count4) {
		this.count4 = count4;
	}

	public int getCount5() {
		return count5;
	}

	public void setCount5(int count5) {
		this.count5 = count5;
	}

	public int getCount6() {
		return count6;
	}

	public void setCount6(int count6) {
		this.count6 = count6;
	}

	public int getCount7() {
		return count7;
	}

	public void setCount7(int count7) {
		this.count7 = count7;
	}

	public String getQuestionsTypes() {
		return questionsTypes;
	}

	public void setQuestionsTypes(String questionsTypes) {
		this.questionsTypes = "主观"+this.count1+"单选"+this.count2+"多选"+this.count3+"判断"+this.count4+
				"填空"+this.count5+"组合"+this.count6+"多媒体"+this.count7;
	}
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**chenrui end*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getOrganizingMode() {
		return organizingMode;
	}

	public void setOrganizingMode(Integer organizingMode) {
		this.organizingMode = organizingMode;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

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

	public List<PaperQuestionBean> getPaperQuestionList() {
		return paperQuestionList;
	}

	public void setPaperQuestionList(List<PaperQuestionBean> paperQuestionList) {
		this.paperQuestionList = paperQuestionList;
	}

	public PaperCategoryBean getPaperCategory() {
		return paperCategory;
	}

	public void setPaperCategory(PaperCategoryBean paperCategory) {
		this.paperCategory = paperCategory;
	}

	public List<ExamOrganizingRuleBean> getOrganizingRuleList() {
		return organizingRuleList;
	}

	public void setOrganizingRuleList(
			List<ExamOrganizingRuleBean> organizingRuleList) {
		this.organizingRuleList = organizingRuleList;
	}

}
