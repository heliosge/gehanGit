/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: AutoQuestionGroupBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;

/**
 * class name:AutoQuestionGroupVo <BR>
 * class description: 自由组卷Vo <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public class AutoQuestionGroupVo {
	/**
	 * 试题显示类型
	 */
	private QuestionDisplayType displayType;
	/**
	 * 试题分类ID
	 */
	private Integer categoryId;
	/**  
	 * 分类ID列表，逗号分隔
	 */
	private String categoryIdListStr;
	/**
	 * 试题难度数量对应关系列表
	 */
	private List<DifficultyLevelCountVo> difficultyCountList = new ArrayList<DifficultyLevelCountVo>();
	/**  
	 * 临时map，用于缓存难度分数映射表
	 */
	private Map<Integer, Integer> difficultyScoreMap = null;
	/**
	 * 当前难度级别，用于查询指定难度级别下的ID列表
	 */
	private Integer curDifficultyLevel;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getInstance <BR>
	 * Description: 根据ExamOrganizingRuleBean实例生成对应的AutoQuestionGroupVo实例 <BR>
	 * Remark: <BR>
	 * @param organizingRule
	 * @return  AutoQuestionGroupVo<BR>
	 */
	public static AutoQuestionGroupVo getInstance(
			ExamOrganizingRuleBean organizingRule) {
		AutoQuestionGroupVo result = new AutoQuestionGroupVo();
		result.setCategoryId(organizingRule.getQuestionCategoryId());
		for (String ruleItem : StringUtils.split(organizingRule.getRule(), ';')) {
			result.getDifficultyCountList().add(
					DifficultyLevelCountVo.getInstance(ruleItem));
		}
		result.setDisplayType(QuestionDisplayType
				.getDisplayQuestionType(organizingRule.getQuestionDisplayType()));
		return result;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getScoreByDifficultyLevel <BR>
	 * Description: 根据难度级别获得分数 <BR>
	 * Remark: <BR>
	 * @param difficultyLevel
	 * @return  Integer<BR>
	 */
	public Integer getScoreByDifficultyLevel(Integer difficultyLevel) {
		if (difficultyScoreMap == null) {
			difficultyScoreMap = new HashMap<Integer, Integer>();
			for (DifficultyLevelCountVo dlc : getDifficultyCountList()) {
				difficultyScoreMap
						.put(dlc.getDifficultyLevel(), dlc.getScore());
			}
		}
		return difficultyScoreMap.get(difficultyLevel);
	}

	public QuestionDisplayType getDisplayType() {
		return displayType;
	}

	public void setDisplayType(QuestionDisplayType displayType) {
		this.displayType = displayType;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<DifficultyLevelCountVo> getDifficultyCountList() {
		return difficultyCountList;
	}

	public void setDifficultyCountList(
			List<DifficultyLevelCountVo> difficultyCountList) {
		this.difficultyCountList = difficultyCountList;
	}

	public Integer getCurDifficultyLevel() {
		return curDifficultyLevel;
	}

	public void setCurDifficultyLevel(Integer curDifficultyLevel) {
		this.curDifficultyLevel = curDifficultyLevel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getCategoryIdListStr() {
		return categoryIdListStr;
	}

	public void setCategoryIdListStr(String categoryIdListStr) {
		this.categoryIdListStr = categoryIdListStr;
	}
}
