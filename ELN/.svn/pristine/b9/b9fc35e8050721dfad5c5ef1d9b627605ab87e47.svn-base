/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamPaperQuestionDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.exam.PaperQuestionBean;

/**
 * @author JFTT)zhangchen class name:ExamPaperQuestionDaoMapper <BR>
 *         class description: please write your description <BR>
 *         Remark: <BR>
 * @version 1.00 2015-7-30
 */
public interface ExamPaperQuestionDaoMapper {
	// wangyifeng begin
	/**
	 * Method name: addPaperQuestion <BR>
	 * Description: 新增所属试卷的试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param paperQuestion
	 * @return Integer<BR>
	 */
	Integer addPaperQuestion(PaperQuestionBean paperQuestion);

	/**
	 * Method name: getPaperQuestionList <BR>
	 * Description: 获取所属试卷的试题列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param paperId
	 * @return List<PaperQuestionBean><BR>
	 */
	List<PaperQuestionBean> getPaperQuestionList(Integer paperId);

	/**
	 * Method name: realDeleteRecords <BR>
	 * Description: 实际删除记录 <BR>
	 * Remark: <BR>
	 * 
	 * @param paperId
	 * @return Integer<BR>
	 */
	Integer realDeleteRecords(Integer paperId);

	// wangyifeng end

	// zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectPaperQuestionNum <BR>
	 * Description: 通过paperId查询一份试卷题目总数 <BR>
	 * Remark: <BR>
	 * @param paper_id
	 * @return  int<BR>
	 */
	public int selectPaperQuestionNum(@Param("paper_id") int paper_id);

	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamQuestionByPaperId <BR>
	 * Description: 通过试卷ID 查询试卷题目、题干等内容 <BR>
	 * Remark: <BR>
	 * @param paper_id
	 * @return  List<PaperQuestionBean><BR>
	 */
	public List<PaperQuestionBean> selectExamQuestionByPaperId(
			@Param("paper_id") int paper_id);

	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectByPaperIdAndQuestionId <BR>
	 * Description: 根据试卷ID与题目ID查询题目的基本信息 <BR>
	 * Remark: <BR>
	 * @param paper_id
	 * @param question_id
	 * @return  PaperQuestionBean<BR>
	 */
	public PaperQuestionBean selectByPaperIdAndQuestionId(
			@Param("paper_id") int paper_id,
			@Param("question_id") int question_id);

	// zhangchen end

}
