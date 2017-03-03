/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyExamManageService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.ExamWrongCardBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.ExamOrganizingRuleVo;
import com.jftt.wifi.bean.vo.ExamAssignDetailVo;
import com.jftt.wifi.bean.vo.ExamGetScoreVo;
import com.jftt.wifi.bean.vo.ExamGradeVo;
import com.jftt.wifi.bean.vo.ExamRecorderVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.ExamWrongCardVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * @author JFTT)zhangchen
 * class name:MyExamManageService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 */
public interface MyExamManageService {

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamScheduleVo <BR>
	 * Description: 查询我的考试-参与考试记录 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamScheduleVo><BR>
	 */
	public List<ExamRecorderVo> getMyExamRecorderVo(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamInfo <BR>
	 * Description: 查询我的考试-进入考试页面信息 <BR>
	 * Remark: <BR>
	 * @param int id
	 * @return  Object<BR>
	 */
	public ExamScheduleVo getExamInfo(int id);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamScheduleVo <BR>
	 * Description: 查询一场考试试卷内容 <BR>
	 * Remark: <BR>
	 * @param int id
	 * @return  Object<BR>
	 */
	public ExamScheduleVo getExamScheduleVo(int id);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getWrongCard <BR>
	 * Description: 查看错题集列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamWrongCardVo><BR>
	 */
	public List<ExamWrongCardVo> getWrongCard(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getWrongQuestionDetail <BR>
	 * Description: 查看错题详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  Object<BR>
	 */
	public ExamWrongCardVo getWrongQuestionDetail(int id);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamSimulate <BR>
	 * Description: 获取模拟试题列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamRecorderVo><BR>
	 */
	public List<ExamRecorderVo> getExamSimulate(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getAllGrade <BR>
	 * Description: 成绩总览 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamGradeVo><BR>
	 * @throws Exception 
	 */
	public List<ExamGradeVo> getAllGrade(ExamQueryConditionBean bean) throws Exception;

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamAssignDetail <BR>
	 * Description: 查询考试详情 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public List<ExamUserAnswerBean> getExamAssignDetail(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen
	 * Method name: deleteWrongCard <BR>
	 * Description: 移出错题集 <BR>
	 * Remark: <BR>
	 * @param id  answer_id<BR>
	 */
	public void deleteWrongCard(int id,int answer_id);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamRecorderCount <BR>
	 * Description: 获取我的考试记录条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getExamRecorderCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamWrongCount <BR>
	 * Description: 获得错题条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getExamWrongCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen
	 * Method name: getSimulateCount <BR>
	 * Description: 获取模拟试题条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getSimulateCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen
	 * Method name: calculateScore <BR>
	 * Description: 提交试卷时计算分数 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 * @throws Exception 
	 */
	public int calculateScore(ExamUserAnswerVo vo,int userId) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: updateExamAssignInfo <BR>
	 * Description: 更新考试信息 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateExamAssignInfo(ExamAssignBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: initialExamPaper <BR>
	 * Description: 初始化一份试卷 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 */
	public Object initialExamPaper(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectInfoByAssignId <BR>
	 * Description: 通过ID查询一场考试所需要的一些重要参数 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamQueryConditionBean<BR>
	 */
	public ExamQueryConditionBean selectInfoByAssignId(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOneExamQuestionDetail <BR>
	 * Description: 通过 assign_id与question_id,查询单个试题详情 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  ExamUserAnswerBean<BR>
	 */
	public ExamUserAnswerBean getOneExamQuestionDetail(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamQuestionDetail <BR>
	 * Description: 根据assign_id查询答题情况 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public List<ExamUserAnswerBean> getExamQuestionDetail(int assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyGetScore <BR>
	 * Description: 更新得分 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void modifyGetScore(ExamUserAnswerBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamGetScore <BR>
	 * Description: 查询答卷详情页面的得分总览  <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  ExamGetScoreVo<BR>
	 */
	public ExamGetScoreVo getExamGetScore(int assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectGradeOtherInfo <BR>
	 * Description: 查询成绩总览页面的其它信息，如考试时长、及格分、总分 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamRecorderVo<BR>
	 */
	public ExamRecorderVo getGradeOtherInfo(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addWrongCard <BR>
	 * Description: 加入错题集 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void addWrongCard(ExamWrongCardBean bean,int answer_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: initialNoAnswerExamDetail <BR>
	 * Description: 在无答题的情况下查看试题详情  <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  ExamAssignDetailVo<BR>
	 */
	public ExamAssignDetailVo getNoAnswerExamDetail(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInfoByAssignId <BR>
	 * Description: 通过assign_id获取一些需要的参数 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  ExamQueryConditionBean<BR>
	 */
	public ExamQueryConditionBean getInfoByAssignId(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCountByAssignId <BR>
	 * Description: 通过assign_id查询有无答案记录 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  boolean<BR>
	 */
	public boolean getCountByAssignId(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getRuleList <BR>
	 * Description: 获取抽题规则 <BR>
	 * Remark: <BR>
	 * @param paper_id
	 * @return  List<ExamOrganizingRuleVo><BR>
	 */
	List<ExamOrganizingRuleVo> getRuleList(int paper_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getLeaveTimes <BR>
	 * Description: 获取考试离开次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  int<BR>
	 */
	public int getLeaveTimes(int assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectOrganizingMode <BR>
	 * Description: 通过assign_id查询组卷方式  <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  int<BR>
	 */
	public ExamQueryConditionBean getTestDetailParam(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAllGradeCount <BR>
	 * Description: 成绩总览条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  int<BR>
	 */
	int getAllGradeCount(ExamQueryConditionBean bean) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamTitle <BR>
	 * Description: 获取一场考试的名称  <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  String<BR>
	 */
	public String getExamTitle(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectInfoById <BR>
	 * Description: 通过考试ID查询确大赛考试信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamQueryConditionBean<BR>
	 */
	public ExamQueryConditionBean selectInfoById(int id,int userId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOrganizingMode <BR>
	 * Description: 通过考试ID查询试卷组卷类型 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamScheduleVo<BR>
	 */
	ExamScheduleVo getOrganizingMode(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyExamAssignInfo <BR>
	 * Description: 更新考试记录表中的信息 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id  void<BR>
	 */
	void modifyExamAssignInfo(int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTestTimes <BR>
	 * Description: 获得考试次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  int<BR>
	 */
	ExamAssignBean getTestTimes(int assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamScheduleInfo <BR>
	 * Description: getExamScheduleInfo <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 */
	public ExamScheduleVo getExamScheduleInfo(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionGetScore <BR>
	 * Description: 查询答案的获取分数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	List<ExamUserAnswerBean> getQuestionGetScore(int assign_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseCard <BR>
	 * Description: 查询练习的错题集 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamWrongCardVo><BR>
	 */
	public List<ExamWrongCardVo> getExerciseWrongCard(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseWrongCount <BR>
	 * Description: 查询练习的错题集条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getExerciseWrongCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseWrongDetail <BR>
	 * Description: 获取练习中的错题详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamWrongCardVo<BR>
	 */
	ExamWrongCardVo getExerciseWrongDetail(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteWrongCard <BR>
	 * Description: 移出错题集 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteWrongCard(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isAnswer <BR>
	 * Description: 判断答案是否正确 ，不包含组合题 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @param questionType
	 * @param userAnswer
	 * @return  boolean<BR>
	 */
	boolean isAnswer(String questionId, String questionType, String userAnswer);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getSimExamWrongCount <BR>
	 * Description: 获取模拟考试错题集列表条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getSimExamWrongCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getSimWrongCard <BR>
	 * Description: 获取模拟考试错题集列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamWrongCardVo><BR>
	 */
	public List<ExamWrongCardVo> getSimWrongCard(ExamQueryConditionBean bean);

	
	
	/** zhangbocheng start*/
	/**
	 * Method name: getMyExamRecorderVoForMobile <BR>
	 * Description: 查询我的考试-参与考试记录 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamScheduleVo><BR>
	 */
	public List<ExamRecorderVo> getMyExamRecorderVoForMobile(ExamQueryConditionBean bean);
	/**
	 * Method name: getExamAssignById <BR>
	 * Description: 根据id查询考试分配信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ExamAssignBean<BR>
	 */
	ExamAssignBean getExamAssignById(Integer id) throws Exception;
	
	/**
	 * Method name: getExamRecorderCountForMobile <BR>
	 * Description: 获取我的考试记录条数-移动端 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getExamRecorderCountForMobile(ExamQueryConditionBean bean);
	/** zhangbocheng end*/

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCorrectAnswerByQuestionId <BR>
	 * Description: 通过questionId查询题目的正确答案 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionBean<BR>
	 */
	QuestionBean getCorrectAnswerByQuestionId(int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCorrectAnswerByQuestionId <BR>
	 * Description: 通过试题ID查询试题的正确答案 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return  String<BR>
	 */
	String selectCorrectAnswerByQuestionId(int questionId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCorrectAnswerByQuestionIdAndType <BR>
	 * Description: 通过试题ID与类型查询试题的正确答案 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @param type
	 * @return  String<BR>
	 */
	String selectCorrectAnswerByQuestionIdAndType(int questionId, int type);

}
