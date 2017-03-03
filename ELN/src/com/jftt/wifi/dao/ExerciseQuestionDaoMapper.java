/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExerciseQuestionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-10        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import com.jftt.wifi.bean.ExerciseQuestionBean;

/**
 * @author JFTT)zhangchen<BR>
 * class name:ExerciseQuestionDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-10
 */
public interface ExerciseQuestionDaoMapper {
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCompleteQuestionId <BR>
	 * Description: 查询完成题目ID <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  String<BR>
	 */
	public String getCompleteQuestionId(ExerciseQuestionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: insertExerciseQuestion <BR>
	 * Description: 练习答题的插入 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void insertExerciseQuestion(ExerciseQuestionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCompleteQuestionNum <BR>
	 * Description: 查询完成题目总条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getCompleteQuestionNum(ExerciseQuestionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteCompleteQuestion <BR>
	 * Description: 清空已经做完的题目  <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void deleteCompleteQuestion(ExerciseQuestionBean bean);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: updateExerciseQuestion <BR>
	 * Description: 更新试题错误字段 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateExerciseQuestion(ExerciseQuestionBean bean);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isCompleted <BR>
	 * Description: 查询该题是否已经做过 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public Integer isCompleted(ExerciseQuestionBean bean);

}
