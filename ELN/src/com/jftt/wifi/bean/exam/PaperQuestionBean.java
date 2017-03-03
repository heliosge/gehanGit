/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: PaperQuestionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/29        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;

/**
 * class name:PaperQuestionBean <BR>
 * class description: 试卷内试题Bean <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/29
 * @author JFTT)wangyifeng
 */
public class PaperQuestionBean {
	/**
	 * 关联主键ID
	 */
	private Integer id;
	/**
	 * 试卷ID
	 */
	private Integer paperId;
	/**
	 * 试题ID
	 */
	private Integer questionId;
	/**
	 * 试题分值
	 */
	private Integer score;
	/**  
	 * 试题类型为组合题时，记录各个子试题的分值（正整数），用逗号隔开；
	 * 非组合题时，该字段留空(null)
	 */
	private String scoreDetail;
	/**
	 * 试题序号
	 */
	private Integer orderNum;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 题目Bean
	 */
	private QuestionBean questionBean;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: PaperQuestionBean<BR>
	 * Description: 空构造函数<BR>
	 * Remark: <BR> <BR>
	 */
	public PaperQuestionBean() {
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: PaperQuestionBean<BR>
	 * Description: 构造函数，用于随机试卷生成的试题<BR>
	 * Remark: <BR>
	 * @param paperId
	 * @param questionId
	 * @param score
	 * @param orderNum
	 * @param questionBean <BR>
	 */
	public PaperQuestionBean(Integer paperId, Integer questionId,
			Integer score, Integer orderNum, QuestionBean questionBean) {
		this.paperId = paperId;
		this.questionId = questionId;
		this.score = score;
		this.orderNum = orderNum;
		this.questionBean = questionBean;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: calculateAndSetSubQuestionScoreForGroupQuestion <BR>
	 * Description: 针对未给子试题分配分数的组合题，计算并分配分数 <BR>
	 * Remark: <BR>  void<BR>
	 */
	public void calculateAndSetSubQuestionScoreForGroupQuestion() {
		// 是组合题，且没有给子试题分配分数
		if (getQuestionBean() != null
				&& getQuestionBean().getQuestionDisplayType() == QuestionDisplayType.GROUP
				&& StringUtils.isBlank(getScoreDetail())) {

			int totalScore = getScore();
			int leftScore = totalScore;
			List<Integer> scoreList = new ArrayList<Integer>();
			for (int oIndex = 0; oIndex < getQuestionBean().getSubQuestions()
					.size(); oIndex++) {
				int score = 0;
				if (oIndex + 1 < getQuestionBean().getSubQuestions().size()) {
					score = totalScore
							/ getQuestionBean().getSubQuestions().size();
					leftScore -= score;
				} else {
					score = leftScore;
				}
				scoreList.add(score);
			}
			setScoreDetail(StringUtils.join(scoreList, ','));
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public QuestionBean getQuestionBean() {
		return questionBean;
	}

	public void setQuestionBean(QuestionBean questionBean) {
		this.questionBean = questionBean;
	}

	public String getScoreDetail() {
		return scoreDetail;
	}

	public void setScoreDetail(String scoreDetail) {
		this.scoreDetail = scoreDetail;
	}
}
