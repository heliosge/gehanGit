/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InvestigationService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.InvestigationVoBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-30
 */
public interface InvestigationService {
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getList <BR>
	 * Description: 获取调查管理列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<InvestigationBean><BR>
	 */
	List<InvestigationBean> getList(SearchOptionBean searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getListCount <BR>
	 * Description: 获取调查报告列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getTotalCount(SearchOptionBean searchOption);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: add <BR>
	 * Description: 新增调查 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 * @throws Exception 
	 */
	void add(InvestigationBean bean) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: delete <BR>
	 * Description: 删除调查 <BR>
	 * Remark: <BR>
	 * @param idList  void<BR>
	 */
	void delete(List<Integer> idList);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserByInvestigationId <BR>
	 * Description: 判断发布调查时，是否有添加参与人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  boolean<BR>
	 */
	boolean getUserByInvestigationId(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: publish <BR>
	 * Description: 调查发布 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void publish(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: get <BR>
	 * Description: 获取调查基本信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  InvestigationBean<BR>
	 */
	InvestigationBean get(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUser <BR>
	 * Description: 获取调查参与人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManageUserBean><BR>
	 * @throws Exception 
	 */
	List<ManageUserBean> getUser(int id) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyInvestigation <BR>
	 * Description: 修改调查 <BR>
	 * Remark: <BR>
	 * @param iBean  void<BR>
	 * @throws Exception 
	 */
	void modifyInvestigation(InvestigationBean iBean) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultList <BR>
	 * Description: 获取结果统计列表数据 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<InvestigationVoBean><BR>
	 */
	List<InvestigationVoBean> getResultList(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getVoTotalCount <BR>
	 * Description: 获取结果统计列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getVoTotalCount(SearchOptionBean searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultVo <BR>
	 * Description: 获取取结果汇总vo <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  InvestigationVoBean<BR>
	 */
	InvestigationVoBean getResultVo(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignList <BR>
	 * Description: 获取调查答卷人员列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return
	 * @throws Exception  List<InvestigationAssignBean><BR>
	 */
	List<InvestigationAssignBean> getAssignList(SearchOptionBean searchOption)
			throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignListCount <BR>
	 * Description: 获取调查答卷人员列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return
	 * @throws Exception  int<BR>
	 */
	int getAssignListCount(SearchOptionBean searchOption) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInvestigationName <BR>
	 * Description: 根据ID获取调查标题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	String getInvestigationName(int id);

}
