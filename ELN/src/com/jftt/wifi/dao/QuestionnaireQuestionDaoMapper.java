/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireQuestionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-20        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireQuestionDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-20
 */
public interface QuestionnaireQuestionDaoMapper {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionnaireQuestion <BR>
	 * Description: 骑过ID查询问题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionnaireQuestionBean<BR>
	 */
	QuestionnaireQuestionBean getQuestionnaireQuestion(Integer id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionnaireQuestionList <BR>
	 * Description: 通过IDListStr查询问题列表 <BR>
	 * Remark: <BR>
	 * @param idListStr
	 * @return  questionnaireId<BR>
	 */
	List<QuestionnaireQuestionBean> getQuestionnaireQuestionList(Integer questionnaireId);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addQuestionnaireQuestion <BR>
	 * Description: 新增问题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	Integer addQuestionnaireQuestion(QuestionnaireQuestionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyQuestionnaireQuestion <BR>
	 * Description: 更新问题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void modifyQuestionnaireQuestion(QuestionnaireQuestionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteQuestionnaireQuestion <BR>
	 * Description: 删除问题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteQuestionnaireQuestion(Integer id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteQuestionByQuestionnaireId <BR>
	 * Description: 通过问卷ID删除问题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteQuestionByQuestionnaireId(@Param("id")Integer id);
}
