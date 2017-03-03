/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-19        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;


/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-19
 */
public interface QuestionnaireDaoMapper {
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addQuestionnaire <BR>
	 * Description: 新增问卷 <BR>
	 * Remark: <BR>
	 * @param qBean
	 * @return  Integer<BR>
	 */
	Integer addQuestionnaire(QuestionnaireBean qBean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteQuestionnaire <BR>
	 * Description: 删除问卷 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteQuestionnaire(Integer id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyQuestionnaire <BR>
	 * Description: 更新问卷 <BR>
	 * Remark: <BR>
	 * @param qBean  void<BR>
	 */
	void modifyQuestionnaire(QuestionnaireBean qBean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionnaire <BR>
	 * Description: 获取问卷 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionnaireBean<BR>
	 */
	QuestionnaireBean getQuestionnaire(Integer id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getVoList <BR>
	 * Description: 获取问卷基本信息列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionnaireBean><BR>
	 */
	List<QuestionnaireBean> getVoList(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTotalCount <BR>
	 * Description: 获取问卷列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getTotalCount(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: checkQuestionnaireReference <BR>
	 * Description: check问卷是否被引用 <BR>
	 * Remark: <BR>
	 * @param questionnaireId
	 * @return  Boolean<BR>
	 */
	Boolean checkQuestionnaireReference(Integer questionnaireId);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: disableQuestionnaire <BR>
	 * Description: 禁用问卷 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void disableQuestionnaire(Integer id);

}
