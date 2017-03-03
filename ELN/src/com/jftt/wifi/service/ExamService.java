/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.text.ParseException;
import java.util.List;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.OfficialExamListItemVo;
import com.jftt.wifi.bean.exam.vo.SimExamListItemVo;

/**
 * class name:ExamService <BR>
 * class description: 考试Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public interface ExamService {
	// wangyifeng begin

	/**
	 * Method name: addExam <BR>
	 * Description: 新增考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param newExam
	 * @return Integer<BR>
	 * @throws ParseException 
	 */
	Integer addExam(ExamScheduleBean newExam,ManageUserBean userBean) throws Exception;

	/**
	 * Method name: deleteExams <BR>
	 * Description: 删除考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param idList
	 *            void<BR>
	 */
	void deleteExams(Integer... idList);

	/**
	 * Method name: modifyExam <BR>
	 * Description: 修改考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedExam
	 *            void<BR>
	 * @throws ParseException 
	 */
	void modifyExam(ExamScheduleBean modifiedExam) throws ParseException;

	/**
	 * Method name: publishExam <BR>
	 * Description: 发布考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 *            void<BR>
	 * @param userBean 
	 * @throws Exception 
	 */
	void publishExam(Integer examId, ManageUserBean userBean) throws Exception;

	/**
	 * Method name: cancelExam <BR>
	 * Description: 取消考试的发布 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 *            void<BR>
	 */
	void cancelExam(Integer examId);

	/**
	 * Method name: getExam <BR>
	 * Description: 获取考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 * @return ExamScheduleBean<BR>
	 * @throws Exception 
	 */
	ExamScheduleBean getExam(Integer examId) throws Exception;

	/**
	 * Method name: getOfficialVoList <BR>
	 * Description: 获取正式考试摘要列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<OfficialExamListItemVo><BR>
	 */
	List<OfficialExamListItemVo> getOfficialVoList(
			ExamSearchOptionVo searchOption);

	/**
	 * Method name: getSimVoList <BR>
	 * Description: 获取模拟考试摘要列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<SimExamListItemVo><BR>
	 */
	List<SimExamListItemVo> getSimVoList(ExamSearchOptionVo searchOption);

	/**
	 * Method name: getTotalCount <BR>
	 * Description: 获取考试数目 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return Integer<BR>
	 */
	Integer getTotalCount(ExamSearchOptionVo searchOption);
	// wangyifeng end

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserIdByGroupId <BR>
	 * Description: 根据群组ID查询用户ID <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @return  String<BR>
	 */
	String getUserIdByGroupId(int groupId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignInfoByRelationId <BR>
	 * Description: 根据考试ID查询用户ID <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  ManageUserBean<BR>
	 * @throws Exception 
	 */
	List<ManageUserBean> getAssignInfoByRelationId(int relation_id,String examState) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTitleCount <BR>
	 * Description: 验证考试名称唯一性 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @param title
	 * @return  int<BR>
	 */
	int getTitleCount(ExamScheduleBean bean);

}
