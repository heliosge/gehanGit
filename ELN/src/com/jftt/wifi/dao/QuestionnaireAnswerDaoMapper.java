/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireAnswerDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.questionnaire.QuestionnaireAnswerBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireAnswerDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
public interface QuestionnaireAnswerDaoMapper {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: get <BR>
	 * Description: 查询答案 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @return  List<QuestionnaireAnswerBean><BR>
	 */
	List<QuestionnaireAnswerBean> get(@Param("assignId") Integer assignId);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: add <BR>
	 * Description: 保存答案 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void add(QuestionnaireAnswerBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAnswer <BR>
	 * Description: 获取问题答案 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @param assignId
	 * @return  String<BR>
	 */
	String getAnswer(@Param("questionId") Integer questionId,@Param("assignId") Integer assignId);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTotalAnswerNum <BR>
	 * Description: 查询某个问题被回答的次数 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return  int<BR>
	 */
	int getTotalAnswerNum(@Param("assignIds") String assignIds,@Param("questionId") Integer questionId);
	
	

}
