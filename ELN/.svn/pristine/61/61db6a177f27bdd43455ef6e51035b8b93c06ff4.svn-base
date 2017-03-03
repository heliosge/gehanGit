/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-20        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireAnswerBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireRelationBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-20
 */
public interface QuestionnaireService {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addQuestionnaire <BR>
	 * Description: 新增问卷 <BR>
	 * Remark: <BR>
	 * @param qBean  void<BR>
	 * @throws Exception 
	 */
	void addQuestionnaire(QuestionnaireBean qBean) throws Exception;
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteQuestionnaires <BR>
	 * Description: 删除问卷 <BR>
	 * Remark: <BR>
	 * @param idList  void<BR>
	 */
	void deleteQuestionnaires(List<Integer> idList);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyQuestionnaire <BR>
	 * Description: modifyQuestionnaire <BR>
	 * Remark: <BR>
	 * @param qBean  void<BR>
	 * @throws Exception 
	 */
	void modifyQuestionnaire(QuestionnaireBean qBean) throws Exception;

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
	 * Method name: getQuestionnaireRelations <BR>
	 * Description: 获取问卷 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<QuestionnaireRelationBean><BR>
	 */
	List<QuestionnaireRelationBean> getQuestionnaireRelations(Integer id);
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
	 * Method name: getInvestQuestions <BR>
	 * Description: 获取调查的问卷问题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<QuestionnaireQuestionBean><BR>
	 */
	List<QuestionnaireQuestionBean> getInvestQuestions(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAnswerDetail <BR>
	 * Description: 查询用户答卷详情 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @return  List<QuestionnaireQuestionBean><BR>
	 */
	List<QuestionnaireQuestionBean> getAnswerDetail(int assignId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: wordImport <BR>
	 * Description: wordImport <BR>
	 * Remark: <BR>
	 * @param filePath
	 * @param companyId
	 * @param subCompanyId
	 * @param userId
	 * @return  Map<String,Object><BR>
	 */
	Map<String, Object> wordImport(String filePath, Integer companyId,
			Integer subCompanyId, Integer userId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: excelImport <BR>
	 * Description: 导入excel <BR>
	 * Remark: <BR>
	 * @param filePath
	 * @param companyId
	 * @param subCompanyId
	 * @param userId
	 * @return  Map<String,Object><BR>
	 */
	Map<String, Object> excelImport(String filePath, Integer companyId,
			Integer subCompanyId, Integer userId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exportDoc <BR>
	 * Description: 查看调查 导出word <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  XWPFDocument<BR>
	 * @throws Exception 
	 */
	XWPFDocument exportDoc(int id) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultDetail <BR>
	 * Description: 获取汇总数据  <BR>
	 * Remark: <BR>
	 * @param id  List<QuestionnaireQuestionBean><BR>
	 * @throws Exception 
	 */
	List<QuestionnaireQuestionBean> getResultDetail(int id) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exportResultDoc <BR>
	 * Description: 调查结果统计导出word <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  XWPFDocument<BR>
	 * @throws Exception 
	 */
	XWPFDocument exportResultDoc(int id) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: validateTitle <BR>
	 * Description: 验证调查标题是否唯一 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param title
	 * @return  int<BR>
	 */
	int validateTitle(InvestigationBean bean);


}
