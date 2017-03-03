/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageThematicAskService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月25日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.thematicAsk.ManageThematicAskVo;

/**
 * class name:ManageThematicAskService <BR>
 * class description: 专题问答service <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月25日
 * @author JFTT)ShenHaijun
 */
public interface ManageThematicAskService {
	
	/**
	 * Method name: getThematicAskCount <BR>
	 * Description: 根据条件查询专题问答数量 <BR>
	 * Remark: <BR>
	 * @param searchAskState 专题状态
	 * @param searchTitle 专题标题
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getThematicAskCount(String searchAskState, String searchTitle) throws Exception;
	
	/**
	 * Method name: getThematicAsks <BR>
	 * Description: 根据条件查询专题问答列表 <BR>
	 * Remark: <BR>
	 * @param searchAskState 专题状态
	 * @param searchTitle 专题标题
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ManageThematicAskVo>
	 * @throws Exception  <BR>
	 */
	List<ManageThematicAskVo> getThematicAsks(String searchAskState,String searchTitle, 
			Integer fromNum, String pageSize) throws Exception;
	
	/**
	 * Method name: getThematicAsksByTitle <BR>
	 * Description: 根据标题查询专题问答 <BR>
	 * Remark: <BR>
	 * @param thematicTitle 专题标题
	 * @return List<AskDetailBean>
	 * @throws Exception  <BR>
	 */
	List<AskDetailBean> getThematicAsksByTitle(String thematicTitle) throws Exception;
	
	/**
	 * Method name: addThematicAsk <BR>
	 * Description: 添加专题问答 <BR>
	 * Remark: <BR>
	 * @param title 标题
	 * @param content 内容
	 * @param initialAnswer 初始答案
	 * @param addPics 添加图片
	 * @param coverPic 封面图片
	 * @param tags 标签
	 * @param effectiveTime 失效时间
	 * @param rewardPoints 绩点
	 * @param askerId 提问人id
	 * @param askerName 提问人姓名
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  void<BR>
	 */
	void addThematicAsk(String title, String content, String initialAnswer,
			String addPics, String coverPic, String tags, String effectiveTime,String rewardPoints,
			String askerId,String askerName,String companyId,String subCompanyId) throws Exception;
	
	/**
	 * Method name: getThematicAskById <BR>
	 * Description: 根据id查询专题问答 <BR>
	 * Remark: <BR>
	 * @param id 专题id
	 * @return AskDetailBean
	 * @throws Exception  <BR>
	 */
	AskDetailBean getThematicAskById(Integer id) throws Exception;
	
	/**
	 * Method name: getAnswerCount <BR>
	 * Description: 获取问答数量 <BR>
	 * Remark: <BR>
	 * @param thematicAskId 专题id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	Integer getAnswerCount(Integer thematicAskId) throws Exception;
	
	/**
	 * Method name: getSatisfactoryAnswer <BR>
	 * Description: 获取问题满意回答 <BR>
	 * Remark: <BR>
	 * @param questionId 问题id
	 * @return AskAnswerBean
	 * @throws Exception  <BR>
	 */
	AskAnswerBean getSatisfactoryAnswer(Integer questionId) throws Exception;
	
	/**
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取其他问答 <BR>
	 * Remark: <BR>
	 * @param questionId 问题id
	 * @return List<AskAnswerBean>
	 * @throws Exception  <BR>
	 */
	List<AskAnswerBean> getOtherAnswers(Integer questionId) throws Exception;
	
	/**
	 * Method name: getAllAnswers <BR>
	 * Description: 获取该专题问答的所有回答 <BR>
	 * Remark: <BR>
	 * @param questionId 问题id
	 * @return List<AskAnswerBean>
	 * @throws Exception  <BR>
	 */
	List<AskAnswerBean> getAllAnswers(Integer questionId) throws Exception;
	
	/**
	 * Method name: shielAnswer <BR>
	 * Description: 屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  void<BR>
	 */
	void shielAnswer(Integer id) throws Exception;
	
	/**
	 * Method name: showAnswer <BR>
	 * Description: 解除屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  void<BR>
	 */
	void showAnswer(Integer id) throws Exception;
	
	/**
	 * Method name: setSatisfactoryAnswer <BR>
	 * Description: 采为满意答案 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  void<BR>
	 */
	void setSatisfactoryAnswer(Integer id) throws Exception;
	
	/**
	 * Method name: deleteThematicAsk <BR>
	 * Description: 删除专题问答 <BR>
	 * Remark: <BR>
	 * @param id 回答id
	 * @throws Exception  void<BR>
	 */
	void deleteThematicAsk(Integer id) throws Exception;
	
	/**
	 * Method name: batchDeleteThematicAsks <BR>
	 * Description: 批量删除专题问答 <BR>
	 * Remark: <BR>
	 * @param ids 回答id集合
	 * @throws Exception  void<BR>
	 */
	void batchDeleteThematicAsks(String[] ids) throws Exception;
	
}
