/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamQuestionOptionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.exam.QuestionOptionBean;

/**
 * @author JFTT)zhangchen class name:ExamQuestionOptionDaoMapper <BR>
 *         class description: please write your description <BR>
 *         Remark: <BR>
 * @version 1.00 2015-7-30
 */
public interface ExamQuestionOptionDaoMapper {
	// wangyifeng begin
	/**
	 * Method name: addQuestionOption <BR>
	 * Description: 增加试题选项 <BR>
	 * Remark: <BR>
	 * 
	 * @param newQuestionOption
	 *            void<BR>
	 */
	void addQuestionOption(QuestionOptionBean newQuestionOption);

	/**
	 * Method name: deleteQuestionOption <BR>
	 * Description: 物理删除试题选项 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 * @return int<BR>
	 */
	int deleteQuestionOptions(Integer questionId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getOptionList <BR>
	 * Description: 获取试题选项集合，选项按照试题ID，选项顺序排序 <BR>
	 * Remark: <BR>
	 * @param questionIdListStr 逗号分隔的试题ID字符串
	 * @return  List<QuestionOptionBean><BR>
	 */
	List<QuestionOptionBean> getOptionList(
			@Param("questionIdListStr") String questionIdListStr);

	// wangyifeng end

	// zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectOptionsByQuestionId <BR>
	 * Description: 通过questionId查询题目选项 <BR>
	 * Remark: <BR>
	 * @param question_id
	 * @return  List<QuestionOptionBean><BR>
	 */
	public List<QuestionOptionBean> selectOptionsByQuestionId(@Param("question_id") Integer question_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectAnswerKeyCount <BR>
	 * Description: 查询主观题中用户答案与正确答案关键字的匹配数 <BR>
	 * Remark: <BR>
	 * @param content
	 * @param question_id
	 * @return  int<BR>
	 */
	public int selectAnswerKeyCount(@Param("content") String content,@Param("question_id") Integer question_id);

	// zhangchen end

}
