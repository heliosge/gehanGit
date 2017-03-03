/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InvestigationAssignDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-1        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.InvestigationVoBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationAssignDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-1
 */
public interface InvestigationAssignDaoMapper {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getByInvestigationId <BR>
	 * Description: 查询调查报告人员 <BR>
	 * Remark: <BR>
	 * @param investigationId
	 * @return  List<InvestigationAssignBean><BR>
	 */
	List<InvestigationAssignBean> getByInvestigationId(@Param("investigationId") Integer investigationId,@Param("type") Integer type);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: add <BR>
	 * Description: 调查报告人员的插入 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void add(InvestigationAssignBean bean);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: delete <BR>
	 * Description: 删除调查报告人员 <BR>
	 * Remark: <BR>
	 * @param investigationId  void<BR>
	 */
	void delete(@Param("investigationId") Integer investigationId,@Param("type") Integer type);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUser <BR>
	 * Description: 查询调查的参与人员数量 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  boolean<BR>
	 */
	int getUser(@Param("id")int id,@Param("type") Integer type);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUsers <BR>
	 * Description: 查询调查的参与人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<InvestigationAssignBean><BR>
	 */
	List<InvestigationAssignBean> getUsers(@Param("id")int id,@Param("type") Integer type);
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
	 * Method name: modifyStatus <BR>
	 * Description: 更新问卷提交状态  <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void modifyStatus(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInvestigationId <BR>
	 * Description: 通过ID查询调查ID <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	int getInvestigationId(@Param("id") int id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTotalNum <BR>
	 * Description: 查询某个调查的参与总人数 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	int getTotalNum(@Param("id") int id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getIntendNum <BR>
	 * Description: 查询某个调查的实际参与人数 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	int getIntendNum(@Param("id") int id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getIntendList <BR>
	 * Description: 查询参与某个调查的所有人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<InvestigationAssignBean><BR>
	 */
	List<InvestigationAssignBean> getIntendList(@Param("id") int id,@Param("type") Integer type);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserAnswerList <BR>
	 * Description: 获取某个调查的员工答卷列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<InvestigationAssignBean><BR>
	 */
	List<InvestigationAssignBean> getUserAnswerList(SearchOptionBean searchOption);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserAnswerListCount <BR>
	 * Description: 获取某个调查的员工答卷列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getUserAnswerListCount(SearchOptionBean searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getById <BR>
	 * Description: 通过ID查询调查标题  <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	InvestigationVoBean getById(@Param("id") int id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignIdByInvestigationId <BR>
	 * Description: 根据调查ID查询分配IDs <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	String getAssignIdByInvestigationId(@Param("id") int id);

}
