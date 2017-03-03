/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyAskService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年12月9日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.AskVoForMMGrid;
import com.jftt.wifi.bean.ask.SearchOptionBean;
import com.jftt.wifi.bean.thematicAsk.ThematicAskVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:MyAskService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年12月9日
 * @author JFTT)ShenHaijun
 */
public interface MyAskService {
	
	/**
	 * Method name: getAskCountByAskerId <BR>
	 * Description: 获取提问数量 <BR>
	 * Remark: <BR>
	 * @param askerId 提问人id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getAskCountByAskerId(Integer askerId) throws Exception;
	
	/**
	 * Method name: getAnswerCountByReplyerId <BR>
	 * Description: 获取回答数量 <BR>
	 * Remark: <BR>
	 * @param replyerId 回答人id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getAnswerCountByReplyerId(Integer replyerId) throws Exception;
	
	/**
	 * Method name: getRootTypes <BR>
	 * Description: 查询根问题分类 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<Map<String,String>>
	 * @throws Exception <BR>
	 */
	List<Map<String, String>> getRootTypes(SearchOptionBean searchOption) throws Exception;
	
	/**
	 * Method name: getToDealAskCount <BR>
	 * Description: 获取待处理问题数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getToDealAskCount(String companyId,String subCompanyId) throws Exception;
	
	/**
	 * Method name: getDealedAskCount <BR>
	 * Description: 获取已处理问题数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getDealedAskCount(String companyId,String subCompanyId) throws Exception;
	
	/**
	 * Method name: getThematicAskList <BR>
	 * Description: 获取专题问答 <BR>
	 * Remark: <BR>
	 * @return List<ThematicAskVo>
	 * @throws Exception <BR>
	 */
	List<ThematicAskVo> getThematicAskList() throws Exception;
	
	/**
	 * Method name: getAsksCount <BR>
	 * Description: 获取问问数量 <BR>
	 * Remark: <BR>
	 * @param askShowType 提问类型
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getAsksCount(String askShowType,String userId,String companyId,String subCompanyId) throws Exception;
	
	/**
	 * Method name: getAskList <BR>
	 * Description: 获取问问列表 <BR>
	 * Remark: <BR>
	 * @param askShowType 提问类型
	 * @param userId 用户id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<AskVoForMMGrid>
	 * @throws Exception <BR>
	 */
	List<AskVoForMMGrid> getAskList(String askShowType, String userId, Integer fromNum,
			String pageSize,String companyId,String subCompanyId) throws Exception;
	
	/**
	 * Method name: getTypeById <BR>
	 * Description: 根据id获取分类  <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @return AskTypeBean
	 * @throws Exception <BR>
	 */
	AskTypeBean getTypeById(Integer typeId) throws Exception;
	
	/**
	 * Method name: getTypeAskCount <BR>
	 * Description: 获取该分类下的问问数量 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param searchContent 查询内容
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getTypeAskCount(Integer typeId, String searchContent) throws Exception;
	
	/**
	 * Method name: getTypeAskList <BR>
	 * Description: 获取该分类下的问问列表 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<AskVoForMMGrid>
	 * @throws Exception <BR>
	 */
	List<AskVoForMMGrid> getTypeAskList(Integer typeId, String searchContent,
			String sortName, String sortOrder, Integer fromNum, String pageSize) throws Exception;

	/**
	 * Method name: getAskDetail <BR>
	 * Description: 获取问问详情-基本信息 <BR>
	 * Remark: <BR>
	 * @param askId 问问id
	 * @return AskDetailBean
	 * @throws Exception <BR>
	 */
	AskDetailBean getAskDetail(Integer askId) throws Exception;

	/**
	 * Method name: getAnswerList <BR>
	 * Description: 获取问问回答列表 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return  List<AskAnswerBean><BR>
	 */
	List<AskAnswerBean> getAnswerList(SearchOptionBean searchOption);

	/**
	 * Method name: add <BR>
	 * Description: 插入回答内容 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void add(AskAnswerBean bean);

	/**
	 * Method name: getRootTypesUnCount <BR>
	 * Description: 获取分类树，不带数量 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<AskTypeBean>
	 * @throws Exception <BR>
	 */
	List<AskTypeBean> getRootTypesUnCount(SearchOptionBean searchOption)
			throws Exception;

	/**
	 * Method name: addAskDetail <BR>
	 * Description: 添加问题详情 <BR>
	 * Remark: <BR>
	 * @param bean 问题Bean
	 * @throws DataBaseException  void<BR>
	 */
	void addAskDetail(AskDetailBean bean) throws DataBaseException;

	/**
	 * Method name: getMyAskList <BR>
	 * Description: 获取我的提问列表 <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<AskDetailBean>
	 * @throws Exception <BR>
	 */
	List<AskDetailBean> getMyAskList(SearchOptionBean searchOption) throws Exception;

	/**
	 * Method name: deleteAsk <BR>
	 * Description: 删除提问 <BR>
	 * Remark: <BR>
	 * @param id 问问id
	 * @throws Exception  void<BR>
	 */
	void deleteAsk(Integer id) throws Exception;
	
	/**
	 * Method name: getAskCountForSearch <BR>
	 * Description: 根据搜索条件查询问问数量 <BR>
	 * Remark: <BR>
	 * @param searchContent 查询条件
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getAskCountForSearch(String searchContent) throws Exception;
	
	/**
	 * Method name: getAskListForSearch <BR>
	 * Description: 根据搜索条件查询问问 <BR>
	 * Remark: <BR>
	 * @param searchContent 查询条件
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<AskVoForMMGrid>
	 * @throws Exception <BR>
	 */
	List<AskVoForMMGrid> getAskListForSearch(String searchContent,
			String sortName, String sortOrder, Integer fromNum, String pageSize) throws Exception;
	
	/**
	 * Method name: getAsksByTitle <BR>
	 * Description: 根据标题查询问问  <BR>
	 * Remark: <BR>
	 * @param title 标题
	 * @return List<AskDetailBean>
	 * @throws Exception <BR>
	 */
	List<AskDetailBean> getAsksByTitle(String title) throws Exception;
	
	/**
	 * Method name: getManageThematicAskRoles <BR>
	 * Description: 获取专题问答管理员用户id列表 <BR>
	 * Remark: <BR>
	 * @param limitsUrl 专题问答管理Url
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<Integer>
	 * @throws Exception <BR>
	 */
	List<Integer> getManageThematicAskRoles(String limitsUrl, String companyId,
			String subCompanyId) throws Exception;
	
	/**
	 * Method name: getSatisfactAnswerForThematicAsk <BR>
	 * Description: 获取专题问答的满意答案 <BR>
	 * Remark: <BR>
	 * @param askId 问题id
	 * @return AskAnswerBean
	 * @throws Exception <BR>
	 */
	AskAnswerBean getSatisfactAnswerForThematicAsk(String askId) throws Exception;
	
	/**
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取满意答案之外的其他回答列表 <BR>
	 * Remark: <BR>
	 * @param askId 问题id
	 * @return List<AskAnswerBean>
	 * @throws Exception <BR>
	 */
	List<AskAnswerBean> getOtherAnswers(String askId) throws Exception;
	
	/**
	 * Method name: getSearchTypes <BR>
	 * Description: 获取该分类下的所有直接子分类 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<AskTypeBean>
	 * @throws Exception <BR>
	 */
	List<AskTypeBean> getSearchTypes(String typeId,String companyId,String subCompanyId) throws Exception;
	
	/**
	 * Method name: getAnswerListIncludeShield <BR>
	 * Description: 获取问问回答列表 （包含已屏蔽） <BR>
	 * Remark: <BR>
	 * @param searchOption 查询条件
	 * @return List<AskAnswerBean>
	 * @throws DataBaseException <BR>
	 */
	List<AskAnswerBean> getAnswerListIncludeShield(SearchOptionBean searchOption) throws DataBaseException;
	
}
