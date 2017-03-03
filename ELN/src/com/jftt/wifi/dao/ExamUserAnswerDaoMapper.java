/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamUserAnswerDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.vo.ExamGetScoreVo;

/**
 * @author JFTT)zhangchen
 * class name:ExamUserAnswerDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 */
public interface ExamUserAnswerDaoMapper {
	// wangyifeng begin

	/**
	 * Method name: updateScore <BR>
	 * Description: 更新单题的分数 <BR>
	 * Remark: <BR>
	 * 
	 * @param scoreInfo
	 *            void<BR>
	 */
	void updateScore(ExamUserAnswerBean markInfo);

	/**
	 * Method name: getAnswerList <BR>
	 * Description: 获取指定考生在指定考试中(以examAssignId表示)的答题详情列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param examAssignId
	 * @return List<ExamUserAnswerBean><BR>
	 */
	List<ExamUserAnswerBean> getAnswerList(Integer examAssignId);

	// wangyifeng end

	// zhangchen start
	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectChoiceAnswer <BR>
	 * Description: 查询我的答案  <BR>
	 * Remark: <BR>
	 * @param ExamQueryConditionBean bean
	 * @return  String<BR>
	 */
	public String selectChoiceAnswer(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectExamCorrectAnswer <BR>
	 * Description: 查询题目正确答案，不包含组合题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String selectExamCorrectAnswer(@Param("id") int id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamCorrectAnswer2 <BR>
	 * Description: 查询题目正确答案，不包含组合题   若是多选题与单选题 ，直接取ID <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String selectExamCorrectAnswer2(@Param("id") int id);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: insertUserAnswer <BR>
	 * Description: 插入用户答案  <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void insertUserAnswer(ExamUserAnswerBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteUserAnswer <BR>
	 * Description: 删除一次考试记录的用户答案 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id  void<BR>
	 */
	public void deleteUserAnswer(@Param("exam_assign_id") int exam_assign_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamQuestionDetail <BR>
	 * Description: 查询试卷的试题详情 ， 随机试卷每进一次都会变化，所以要从用户答案表入手 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public List<ExamUserAnswerBean> selectExamQuestionDetail(@Param("exam_assign_id") int exam_assign_id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionGetScore <BR>
	 * Description: 查询试卷的试题详情 ， 随机试卷每进一次都会变化，所以要从用户答案表入手  2015-10-13 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public List<ExamUserAnswerBean> selectQuestionGetScore(@Param("exam_assign_id") int exam_assign_id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionDetailByOrderNum <BR>
	 * Description: 通过assign_id与order_num查询子试题的答题详情   <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public List<ExamUserAnswerBean> selectQuestionDetailByOrderNum(@Param("exam_assign_id") int exam_assign_id,@Param("order_num") int order_num);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionGetScoreByOrderNum <BR>
	 * Description: 通过assign_id与order_num查询子试题的答题详情  2015-10-13 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @param order_num
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public List<ExamUserAnswerBean> selectQuestionGetScoreByOrderNum(@Param("exam_assign_id") int exam_assign_id,@Param("order_num") int order_num);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionGetScoreByQuestionId <BR>
	 * Description: 通过assign_id与QuestionId查询子试题的答题详情  2015-10-29 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @param qustion_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public ExamUserAnswerBean selectQuestionGetScoreByQuestionId(@Param("exam_assign_id") int exam_assign_id,@Param("qustion_id") int qustion_id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectQuestionDetailByQuestionId <BR>
	 * Description: 通过assign_id与question_id查询子试题的答题详情 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @param qustion_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public ExamUserAnswerBean selectQuestionDetailByQuestionId(@Param("exam_assign_id") int exam_assign_id,@Param("qustion_id") int qustion_id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectOneExamQuestionDetail <BR>
	 * Description: 通过 assign_id与question_id,查询单个试题详情 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	public ExamUserAnswerBean selectOneExamQuestionDetail(ExamQueryConditionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamGetScore <BR>
	 * Description: 查询答卷详情页面的得分总览 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  ExamGetScoreVo<BR>
	 */
	public ExamGetScoreVo selectExamGetScore(@Param("exam_assign_id") int exam_assign_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: updateFavorated <BR>
	 * Description: 更新答案是否收藏在错题集 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param isFavorated  void<BR>
	 */
	public void updateFavorated(@Param("id") int id,@Param("isFavorated") int isFavorated);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectAnswerByAssignIdAndQuestionId <BR>
	 * Description: 通过 assign_id与question_id,查询单个试题答案 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @param question_id
	 * @return  String<BR>
	 */
	public String selectAnswerByAssignIdAndQuestionId(@Param("exam_assign_id") int exam_assign_id,@Param("question_id") int question_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectAnswerIdByAssignIdAndQuestionId <BR>
	 * Description: 通过 assign_id与question_id用户答案ID <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @param question_id
	 * @return  int<BR>
	 */
	public int selectAnswerIdByAssignIdAndQuestionId(@Param("exam_assign_id") int exam_assign_id,@Param("question_id") int question_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCountByassignId <BR>
	 * Description: 通过assign_id查询有无答案记录 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  int<BR>
	 */
	public int selectCountByassignId(@Param("exam_assign_id") int exam_assign_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCorrectAnswer <BR>
	 * Description: 通过ID与类型查询正确答案 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param type
	 * @return  String<BR>
	 */
	public String selectCorrectAnswer(@Param("id") int id,@Param("type") int type);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCorrectAnswer2 <BR>
	 * Description: 通过ID与类型查询正确答案ID <BR>
	 * Remark: <BR>
	 * @param id
	 * @param type
	 * @return  String<BR>
	 */
	public String selectCorrectAnswer2(@Param("id") int id,@Param("type") int type);
	// zhangchen end

}
