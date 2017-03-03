/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireRelationDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-20        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import com.jftt.wifi.bean.questionnaire.QuestionnaireRelationBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireRelationDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-20
 */
public interface QuestionnaireRelationDaoMapper {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addQuestionnaireRelation <BR>
	 * Description: 新增问题关联信息 <BR>
	 * Remark: <BR>
	 * @param qrBean
	 * @return  Integer<BR>
	 */
	Integer addQuestionnaireRelation(QuestionnaireRelationBean qrBean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionnaireRelationList <BR>
	 * Description: 获取问题关联信息列表 <BR>
	 * Remark: <BR>
	 * @param questionnaireId
	 * @return  List<QuestionnaireRelationBean><BR>
	 */
	List<QuestionnaireRelationBean> getQuestionnaireRelationList(Integer questionnaireId);

}
