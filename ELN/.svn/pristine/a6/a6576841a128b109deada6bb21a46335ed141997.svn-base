/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo;
import com.jftt.wifi.bean.exam.vo.DifficultyLevelCountVo;
import com.jftt.wifi.bean.exam.vo.QuestionExportVo;
import com.jftt.wifi.bean.exam.vo.QuestionListItemVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;

/**
 * class name:ExamQuestionService <BR>
 * class description: 试题Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public interface ExamQuestionService {
	// wangyifeng begin
	/**
	 * Method name: getQuestionListItemVoList <BR>
	 * Description: 根据查询条件，获取用于列表展示的试题列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<QuestionListItemVo><BR>
	 */
	List<QuestionListItemVo> getQuestionListItemVoList(
			QuestionSearchOptionVo searchOption);

	/**
	 * Method name: getQuestionTotalCount <BR>
	 * Description: 获取试题总数 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return Integer<BR>
	 */
	Integer getQuestionTotalCount(QuestionSearchOptionVo searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getQuestionListForMMGrid <BR>
	 * Description: 获取试题列表，用于试题管理列表页面 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  MMGridPageVoBean<QuestionListItemVo><BR>
	 */
	MMGridPageVoBean<QuestionListItemVo> getQuestionListForMMGrid(
			QuestionSearchOptionVo searchOption);

	/**
	 * Method name: addQuestion <BR>
	 * Description: 增加试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param newQuestion
	 *            Integer<BR>
	 */
	Integer addQuestion(QuestionBean newQuestion);

	/**
	 * Method name: modifyQuestion <BR>
	 * Description: 修改试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param question
	 *            void<BR>
	 */
	void modifyQuestion(QuestionBean question);

	/**
	 * Method name: deleteQuestions <BR>
	 * Description: 删除试题列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionIdList
	 *            void<BR>
	 */
	void deleteQuestions(List<Integer> questionIdList);

	/**
	 * Method name: getQuestion <BR>
	 * Description: 获取试题详细信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 * @return QuestionBean<BR>
	 */
	QuestionBean getQuestion(Integer questionId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getQuestionList <BR>
	 * Description: 获取试题列表（带试题详情） <BR>
	 * Remark: <BR>
	 * @param questionIdList 试题ID列表
	 * @return  List<QuestionBean><BR>
	 */
	List<QuestionBean> getQuestionList(List<Integer> questionIdList);

	/**
	 * Method name: getDifficultyLevelCountList <BR>
	 * Description: 根据自由组卷规则，获取试题难度数量列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<DifficultyLevelCountVo><BR>
	 */
	List<DifficultyLevelCountVo> getDifficultyLevelCountList(
			AutoQuestionGroupVo autoQuestionGroup);

	/**
	 * Method name: autoGenerateQuestionIdList <BR>
	 * Description: 根据自由组卷规则，（随机选择）自动生成试题集 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<Integer><BR>
	 */
	List<Integer> autoGenerateQuestionIdList(
			AutoQuestionGroupVo autoQuestionGroup);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: uploadFile <BR>
	 * Description: 上传文件 <BR>
	 * Remark: <BR>
	 * @param imgFile
	 * @return
	 * @throws IOException  String<BR>
	 */
	String uploadFile(MultipartFile imgFile) throws IOException;
	// wangyifeng end
	
	/**
	 * Method name: excelImport <BR>
	 * Description: excel导入试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param filePath
	 * @return List<Integer><BR>
	 */
	Map<String,Object> excelImport(String filePath,Integer companyId,Integer subCompanyId,Integer userId,Integer categoryId);
	
	 /**
     * 导出的试题list
     * @param searchOption
     * @return
     */
    public List<QuestionExportVo> getExportList(QuestionSearchOptionVo searchOption);
	
	/**
	 * Method name: wordImport <BR>
	 * Description: word导入试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param filePath
	 * @return List<Integer><BR>
	 */
	Map<String,Object> wordImport(String filePath,Integer companyId,Integer subCompanyId,Integer userId,Integer categoryId,Integer difficultId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionList <BR>
	 * Description: 练习-生成试题 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionBean><BR>
	 */
	List<QuestionBean> getExerciseQuestionList(QuestionSearchOptionVo searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionIdList <BR>
	 * Description:  练习-生成试题 IDlist <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  String<BR>
	 */
	String getExerciseQuestionIdList(QuestionSearchOptionVo searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOneExerciseQuestion <BR>
	 * Description: 生成一道试题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionBean<BR>
	 */
	QuestionBean getOneExerciseQuestion(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isAnswerCorrect <BR>
	 * Description: 判断题目是否正确 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception  boolean<BR>
	 */
	boolean isAnswerCorrect(ExamUserAnswerVo vo,int userId,String isAddWrong) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionCount <BR>
	 * Description: 练习-生成试题 个数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  String<BR>
	 */
	String getExerciseQuestionCount(QuestionSearchOptionVo searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exerciseIsOrNotComplete <BR>
	 * Description: 判断一次练习中，一级题库是否全部做完，如果做完，需要清空已做试题数据 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  boolean<BR>
	 */
	boolean exerciseIsOrNotComplete(QuestionSearchOptionVo searchOption);
}
