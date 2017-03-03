/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyQuestionnaireService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAnswerVo;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:MyQuestionnaireService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
public interface MyQuestionnaireService {

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getVoList <BR>
	 * Description: 获取我的问卷列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionnaireBean><BR>
	 */
	List<QuestionnaireBean> getVoList(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTotalCount <BR>
	 * Description: 我的问卷列表数量 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getTotalCount(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: saveAnswer <BR>
	 * Description: 保存问卷问题答案 <BR>
	 * Remark: <BR>
	 * @param vo  void<BR>
	 * @throws Exception 
	 */
	void saveAnswer(InvestigationAnswerVo vo) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exportDoc <BR>
	 * Description: 员工答卷详情 导出word <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  XWPFDocument<BR>
	 */
	XWPFDocument exportDoc(int id) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: generateFileName <BR>
	 * Description: 生成word文件名 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @return  String<BR>
	 * @throws Exception 
	 */
	String generateFileName(int assignId) throws Exception;

}
