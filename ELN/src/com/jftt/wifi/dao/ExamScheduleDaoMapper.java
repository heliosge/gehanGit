/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamScheduleDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.MarkExamListItemVo;
import com.jftt.wifi.bean.exam.vo.OfficialExamListItemVo;
import com.jftt.wifi.bean.exam.vo.SimExamListItemVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.PostExamVo;
import com.jftt.wifi.bean.vo.SaveMatchParamVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * @author JFTT)zhangchen class name:ExamScheduleDaoMapper <BR>
 *         class description: 考试信息DaoMapper <BR>
 *         Remark: <BR>
 * @version 1.00 2015-7-30
 */
public interface ExamScheduleDaoMapper {
	// wangyifeng begin
	/**
	 * Method name: addExam <BR>
	 * Description: 新增考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param newExam
	 * @return Integer<BR>
	 */
	Integer addExam(ExamScheduleBean newExam);

	/**
	 * Method name: deleteExam <BR>
	 * Description: 删除考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param id
	 *            void<BR>
	 */
	void deleteExam(Integer id);

	/**
	 * Method name: modifyExam <BR>
	 * Description: 修改考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedExam
	 *            void<BR>
	 */
	void modifyExam(ExamScheduleBean modifiedExam);

	/**
	 * Method name: publishExam <BR>
	 * Description: 发布考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 *            void<BR>
	 */
	void publishExam(Integer examId);

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
	 */
	ExamScheduleBean getExam(Integer examId);

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

	/**
	 * Method name: getMarkVoList <BR>
	 * Description: 获取考试成绩批阅摘要列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<MarkExamListItemVo><BR>
	 */
	List<MarkExamListItemVo> getMarkVoList(ExamSearchOptionVo searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getMarkTotalCount <BR>
	 * Description: 获取已结束的考试总数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	Integer getMarkTotalCount(ExamSearchOptionVo searchOption);

	/**
	 * Method name: publishScore <BR>
	 * Description: 发布成绩 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 *            void<BR>
	 */
	void publishScore(Integer examId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: updateIsMarkedState <BR>
	 * Description: 更新考试是否批阅完毕 <BR>
	 * Remark: <BR>
	 * @param examId  void<BR>
	 */
	void updateIsMarkedState(Integer examId);

	// wangyifeng end

	// zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamScheduleVo <BR>
	 * Description: 查询一场考试试卷内容 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamScheduleVo<BR>
	 */
	public ExamScheduleVo selectExamScheduleVo(@Param("id") int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamInfo <BR>
	 * Description: 查询我的考试-进入考试页面的信息  <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamScheduleVo<BR>
	 */
	public ExamScheduleVo selectExamInfo(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamScheduleInfo <BR>
	 * Description: 查询一场考试的基本信息，不包括试题-赛程考试专用 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamScheduleVo<BR>
	 */
	public ExamScheduleVo selectExamScheduleInfo(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamTitle <BR>
	 * Description: 获取一场考试的名称 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  String<BR>
	 */
	String selectExamTitle(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectTitleCount <BR>
	 * Description: 验证考试名称唯一性 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @param title
	 * @return  int<BR>
	 */
	int selectTitleCount(ExamScheduleBean bean);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectInfoById <BR>
	 * Description: 通过考试ID查询确大赛考试信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamQueryConditionBean<BR>
	 */
	ExamQueryConditionBean selectInfoById(@Param("id")int id,@Param("userId")int userId);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectNoticeInfo <BR>
	 * Description: selectNoticeInfo <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return  ExamScheduleBean<BR>
	 */
	ExamScheduleBean selectNoticeInfo(@Param("id")int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamInfoById <BR>
	 * Description: 发布考试时 查询考试基本信息  <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return  ExamQueryConditionBean<BR>
	 */
	ExamQueryConditionBean selectExamInfoById(@Param("id")int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: 通过考试ID查询试卷组卷类型 <BR>
	 * Description: selectOrganizingModeById <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	ExamScheduleVo selectOrganizingModeById(@Param("id")int id);
	
	ExamScheduleVo selectExamScheduleInfo2(@Param("id")int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamScheduleVoNew <BR>
	 * Description: 查询一场考试的基本信息，不带试题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamScheduleVo<BR>
	 */
	ExamScheduleVo selectExamScheduleVoNew(@Param("id")int id);

	// zhangchen end

	/** shenhaijun start */

	/**
	 * Method name: getExamCountByCompanyId <BR>
	 * Description: 获取公司的考试数量 <BR>
	 * Remark: <BR>
	 * 
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException
	 *             Integer<BR>
	 */
	public Integer getExamCountByCompanyId(
			@Param("companyId") Integer companyId,@Param("subCompanyId") Integer subCompanyId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getStageTest <BR>
	 * Description: 获取阶段测试 <BR>
	 * Remark: <BR>
	 * @param stageId
	 * @return
	 * @throws DataBaseException  ExamScheduleBean<BR>
	 */
	public PostExamVo getStageTest(@Param("stageId")Integer stageId,@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getIdByConditions <BR>
	 * Description: 根据条件查询id <BR>
	 * Remark: <BR>
	 * @param title
	 * @param examId
	 * @param duration
	 * @param allowTimes
	 * @param passScorePercent
	 * @param type
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	public Integer getIdByConditions(@Param("title")String title,@Param("examId")Integer examId,@Param("duration")Integer duration,@Param("allowTimes")Integer allowTimes,
			@Param("passScorePercent")Integer passScorePercent,@Param("type")Integer type,@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId) throws Exception;
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取考试信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ExamScheduleBean<BR>
	 */
	public ExamScheduleBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/** shenhaijun end */
	
	
	/**chenrui start*/
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: save2ExamByMatch <BR>
	 * Description: 新增赛程中的 新增考试 <BR>
	 * Remark: <BR>
	 * @param params  void<BR>
	 */
	public void save2ExamByMatch(SaveMatchParamVo params)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: update2ExamByMatch <BR>
	 * Description: 新增赛程中的 修改考试 <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  void<BR>
	 */
	public void update2ExamByMatch(SaveMatchParamVo params)throws Exception;

	/**chenrui end*/
}
