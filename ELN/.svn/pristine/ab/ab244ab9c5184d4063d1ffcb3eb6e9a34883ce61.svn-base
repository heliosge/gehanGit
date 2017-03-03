/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamWrongCardDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-14        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamWrongCardBean;
import com.jftt.wifi.bean.vo.ExamRecorderVo;
import com.jftt.wifi.bean.vo.ExamWrongCardVo;

/**
 * 
 * class name:ExamWrongCardDaoMapper <BR>
 * class description: 错题集记录表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-14
 * @author JFTT)chenrui
 */
public interface ExamWrongCardDaoMapper {
	public ExamWrongCardBean getById(@Param("id")long id) throws Exception;
	
	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectWrongRecorder <BR>
	 * Description: 查询我的考试-错题集 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamRecorderVo><BR>
	 */
	public List<ExamWrongCardVo> selectWrongCardByUserId(ExamQueryConditionBean bean);
	
	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectWrongQuestionDetail <BR>
	 * Description: 查看错题详情  <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamWrongCardBean<BR>
	 */
	public ExamWrongCardVo selectWrongQuestionDetail(@Param("id")long id);
	
	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: deleteWrongCardById <BR>
	 * Description: 移出错题集 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteWrongCardById(@Param("id")long id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteWrongCardByAssignId <BR>
	 * Description: 根据assign_id删除错题 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteWrongCardByAssignId(@Param("id")long id);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectWrongCount <BR>
	 * Description: 获得错题条数  <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int selectWrongCount(ExamQueryConditionBean bean);
	
	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: insertWrongCard <BR>
	 * Description: 插入错题 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void insertWrongCard(ExamWrongCardBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExerciseWrongByUserId <BR>
	 * Description: 查询练习的错题集 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamWrongCardVo><BR>
	 */
	public List<ExamWrongCardVo> selectExerciseWrongByUserId(ExamQueryConditionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExerciseWrongCount <BR>
	 * Description: 查询练习的错题集条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int selectExerciseWrongCount(ExamQueryConditionBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExerciseWrongDetail <BR>
	 * Description: 查询练习中的错题详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamWrongCardVo<BR>
	 */
	public ExamWrongCardVo  selectExerciseWrongDetail(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectChoiceAnswer2 <BR>
	 * Description: 查询选择题答案 order_num <BR>
	 * Remark: <BR>
	 * @param id
	 * @param option_id
	 * @return  String<BR>
	 */
	public String selectChoiceAnswer2(@Param("id") int id,@Param("option_id") String option_id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectSimExamWrongCount <BR>
	 * Description: 获取模拟考试错题集列表条数  <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int selectSimExamWrongCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectSimWrongCard <BR>
	 * Description: 获取模拟考试错题集列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  List<ExamWrongCardVo><BR>
	 */
	public List<ExamWrongCardVo> selectSimWrongCard(ExamQueryConditionBean bean);
}