/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AskAnswerDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.SearchOptionBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:AskAnswerDaoMapper <BR>
 * class description: 问问回答dao <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public interface AskAnswerDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取问问回答 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  AskAnswerBean<BR>
	 */
	public AskAnswerBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getByQuestionId <BR>
	 * Description: 根据问题id查询所有回答 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  List<AskAnswerBean><BR>
	 */
	public List<AskAnswerBean> getByQuestionId(@Param("questionId")Integer questionId) throws DataBaseException;
	
	/**
	 * Method name: getAnswerCount <BR>
	 * Description: 获取问答数量 <BR>
	 * Remark: <BR>
	 * @param thematicAskId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getAnswerCount(@Param("thematicAskId")Integer thematicAskId) throws DataBaseException;
	
	/**
	 * Method name: getSatisfactoryAnswer <BR>
	 * Description: 获取问题满意回答 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return
	 * @throws DataBaseException  AskAnswerBean<BR>
	 */
	public AskAnswerBean getSatisfactoryAnswer(@Param("questionId")Integer questionId) throws DataBaseException;
	
	/**
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取其他问答 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return
	 * @throws DataBaseException  List<AskAnswerBean><BR>
	 */
	public List<AskAnswerBean> getOtherAnswers(@Param("questionId")Integer questionId) throws DataBaseException;
	
	/**
	 * Method name: getAllAnswers <BR>
	 * Description: 获取该专题问答的所有回答 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return
	 * @throws DataBaseException  List<AskAnswerBean><BR>
	 */
	public List<AskAnswerBean> getAllAnswers(@Param("questionId")Integer questionId) throws DataBaseException;
	
	/**
	 * Method name: shielAnswer <BR>
	 * Description: 屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws DataBaseException  void<BR>
	 */
	public void shielAnswer(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: showAnswer <BR>
	 * Description: 解除屏蔽回答 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws DataBaseException  void<BR>
	 */
	public void showAnswer(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: setSatisfactoryAnswer <BR>
	 * Description: 采为满意答案 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws DataBaseException  void<BR>
	 */
	public void setSatisfactoryAnswer(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: getAnswerCountByReplyerId <BR>
	 * Description: 获取回答数量 <BR>
	 * Remark: <BR>
	 * @param replyerId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getAnswerCountByReplyerId(@Param("replyerId")Integer replyerId) throws DataBaseException;
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAskAnswerCount <BR>
	 * Description: 获取回答数量 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getAskAnswerCount(SearchOptionBean searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAskAnswerList <BR>
	 * Description: 获取问问答案列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<AskAnswerBean><BR>
	 */
	List<AskAnswerBean> getAskAnswerList(SearchOptionBean searchOption);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: add <BR>
	 * Description: 插入回答内容  <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	void add(AskAnswerBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteAnswer <BR>
	 * Description: 删除问题的回答 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteAnswer(@Param("questionId") Integer questionId);
	
	/**
	 * Method name: getAnswerListIncludeShield <BR>
	 * Description: 获取问问回答列表 （包含已屏蔽） <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return
	 * @throws DataBaseException  List<AskAnswerBean><BR>
	 */
	public List<AskAnswerBean> getAnswerListIncludeShield(
			SearchOptionBean searchOption) throws DataBaseException;
	
}
