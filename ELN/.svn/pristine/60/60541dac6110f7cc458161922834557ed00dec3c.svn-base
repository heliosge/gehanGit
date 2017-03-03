/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InvestigationDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-26        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.InvestigationVoBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-26
 */
public interface InvestigationDaoMapper {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInvestigation <BR>
	 * Description: 查询调查报告信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  InvestigationBean<BR>
	 */
	InvestigationBean getInvestigation(@Param("id") Integer id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addInvestigation <BR>
	 * Description: 查报告的插入 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	Integer addInvestigation(InvestigationBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteInvestigation <BR>
	 * Description: 删除调查报告 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteInvestigation(@Param("id") Integer id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: publishInvestigation <BR>
	 * Description: 发布调查报告 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void publishInvestigation(@Param("id") Integer id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getVoList <BR>
	 * Description: 获取调查报告列表  <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<InvestigationBean><BR>
	 */
	List<InvestigationBean> getVoList(SearchOptionBean searchOption);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultVoList <BR>
	 * Description: 获取结果统计列表数据 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<InvestigationVoBean><BR>
	 */
	List<InvestigationVoBean> getResultVoList(SearchOptionBean searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultVo <BR>
	 * Description: 获取取结果汇总vo <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  InvestigationVoBean<BR>
	 */
	InvestigationVoBean getResultVo(@Param("id") Integer id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTotalCount <BR>
	 * Description: 获取调查报告列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getTotalCount(SearchOptionBean searchOption);
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
	 * Method name: modifyInvestigation <BR>
	 * Description: 修改调查 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void modifyInvestigation(InvestigationBean bean);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInvestigationName <BR>
	 * Description: 根据ID获取调查标题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	String getInvestigationName(@Param("id") Integer id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTitleCount <BR>
	 * Description: 获取相同标题的数量 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	int getTitleCount(InvestigationBean bean);

}
