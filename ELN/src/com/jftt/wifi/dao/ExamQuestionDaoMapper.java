/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/27        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo;
import com.jftt.wifi.bean.exam.vo.DifficultyLevelCountVo;
import com.jftt.wifi.bean.exam.vo.QuestionListItemVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;

/**
 * class name:ExamQuestionDaoMapper <BR>
 * class description: 试题Mapper <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/27
 * @author JFTT)wangyifeng
 */
public interface ExamQuestionDaoMapper {
	// wangyifeng begin
	/**
	 * Method name: getQuestionListItemVoList <BR>
	 * Description: 获取试题列表，用于试题管理列表页面 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<QuestionListItemVo><BR>
	 */
	List<QuestionListItemVo> getQuestionListItemVoList(
			QuestionSearchOptionVo searchOption);

	/**
	 * Method name: getQuestionTotalCount <BR>
	 * Description: 获取试题总数 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return Integer<BR>
	 */
	Integer getQuestionTotalCount(QuestionSearchOptionVo searchOption);

	/**
	 * Method name: addQuestion <BR>
	 * Description: 增加试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param newQuestion
	 *            void<BR>
	 */
	void addQuestion(QuestionBean newQuestion);

	/**
	 * Method name: modifyQuestion <BR>
	 * Description: 修改试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param question
	 *            void<BR>
	 */
	void modifyQuestion(QuestionBean question);

	/**
	 * Method name: deleteQuestion <BR>
	 * Description: 删除试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 *            void<BR>
	 */
	void deleteQuestion(Integer questionId);

	/**
	 * Method name: realDeleteQuestion <BR>
	 * Description: 物理删除试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 *            void<BR>
	 */
	void realDeleteQuestion(Integer questionId);

	/**
	 * Method name: getQuestion <BR>
	 * Description: 获取试题详情 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 * @return QuestionBean<BR>
	 */
	QuestionBean getQuestion(Integer questionId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getQuestionList <BR>
	 * Description: 获取试题列表 <BR>
	 * Remark: <BR>
	 * @param idListStr
	 * @return  List<QuestionBean><BR>
	 */
	List<QuestionBean> getQuestionList(@Param("idListStr") String idListStr);

	/**
	 * Method name: getDifficultyLevelCountList <BR>
	 * Description: 根据自由组卷规则，获取试题难度数量列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<DifficultyLevelCountVo><BR>
	 */
	List<DifficultyLevelCountVo> getDifficultyLevelCountList(
			AutoQuestionGroupVo autoQuestionGroup);

	/**
	 * Method name: getQuestionIdListForAutoGroup <BR>
	 * Description: 查询指定难度级别及自由组卷规则下包含的试题ID列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<Integer><BR>
	 */
	List<Integer> getQuestionIdListForAutoGroup(
			AutoQuestionGroupVo autoQuestionGroup);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: checkQuestionReference <BR>
	 * Description: check试题是否被引用 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return  Boolean<BR>
	 */
	Boolean checkQuestionReference(Integer questionId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: disableQuestion <BR>
	 * Description: 将传入试题ID对应的试题设为禁用 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void disableQuestion(Integer id);

	// wangyifeng end

	// zhangchen start

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionById <BR>
	 * Description: 通过题目ID查询题目内容 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionBean<BR>
	 */
	public QuestionBean selectQuestionById(@Param("id") int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionListByIdList <BR>
	 * Description: 通过题目IDs查询子题目list <BR>
	 * Remark: <BR>
	 * @param question_id_list
	 * @return  List<QuestionBean><BR>
	 */
	public List<QuestionBean> selectQuestionListByIdList(
			@Param("question_id_list") String question_id_list);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectTypeByID <BR>
	 * Description: 通过试题ID查询试题类型 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	public int selectTypeByID(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionList <BR>
	 * Description: 练习-生成试题 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionBean><BR>
	 */
	public List<QuestionBean> getExerciseQuestionList(QuestionSearchOptionVo searchOption);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionIdList <BR>
	 * Description: 练习-生成试题ID <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  String<BR>
	 */
	public String getExerciseQuestionIdList(QuestionSearchOptionVo searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionCount <BR>
	 * Description: 练习-生成试题ID的个数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	public int getExerciseQuestionCount(QuestionSearchOptionVo searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionIdListById <BR>
	 * Description: 通过题目IDs查询子questionIdList <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String selectQuestionIdListById(@Param("id") int id);
	// zhangchen end
	
	//zhangbocheng start 
	/**
	 * Method name: getQuestionExportVoList <BR>
	 * Description: 查询出可以导出的试题列表 <BR>
	 * @param searchOption
	 * @return
	 */
	public List<QuestionBean> getQuestionExportVoList(QuestionSearchOptionVo searchOption);
	//zhangbocheng end

}
