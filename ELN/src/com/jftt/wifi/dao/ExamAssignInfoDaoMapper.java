/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamAssignInfoDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.bean.vo.ExamAssignDetailVo;
import com.jftt.wifi.bean.vo.ExamGradeVo;
import com.jftt.wifi.bean.vo.ExamRecorderVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * @author JFTT)zhangchen 
 * class name:ExamAssignInfoDaoMapper <BR>
 * class description: 考试分配情况DaoMapper <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 */
public interface ExamAssignInfoDaoMapper {
	// wangyifeng begin
	/**
	 * Method name: addAssignInfo <BR>
	 * Description: 新增考试分配信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param newAssign
	 * @return Integer<BR>
	 */
	Integer addAssignInfo(ExamAssignBean newAssign);

	/**
	 * Method name: realDeleteAssignInfo <BR>
	 * Description: 物理删除旧的考试分配信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param relationType
	 * @param relationId
	 * @return Integer<BR>
	 */
	Integer realDeleteAssignInfo(@Param("relationType") Integer relationType,
			@Param("relationId") Integer relationId);

	// For ExamMarkScoreService

	/**
	 * Method name: getExamResultList <BR>
	 * Description: 获取成绩一览 <BR>
	 * Remark: <BR>
	 * 
	 * @return List<ExamAssignBean><BR>
	 */
	List<ExamAssignBean> getExamResultList(cjylSearchVo paramVo);

	/**
	 * Method name: getAttendUserIdList <BR>
	 * Description: 获取参加考试的考生摘要信息列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param exam_assign_id
	 * @return List<Integer><BR>
	 */
	List<ExamAssignBean> getAttendUserInfoList(ExamQueryConditionBean bean);

	/**
	 * Method name: modifyScore <BR>
	 * Description: 修改得分 <BR>
	 * Remark: <BR>
	 * 
	 * @param examAssign
	 *            void<BR>
	 */
	void modifyScore(ExamAssignBean examAssign);

	// wangyifeng end

	// zhangchen start

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectSimulateRecorder <BR>
	 * Description: 查询我的考试-模拟考试 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return List<ExamRecorderVo><BR>
	 */
	public List<ExamRecorderVo> selectSimulateRecorder(
			ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectExamAssignInfoByUserId <BR>
	 * Description: 查询我的考试-参与考试记录 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return List<ExamRecorderVo><BR>
	 */
	public List<ExamRecorderVo> selectExamAssignInfoByUserId(
			ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen 
	 * Method name: selectExamTotalNum <BR>
	 * Description: 查询一场考试总人数 <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return int<BR>
	 */
	public int selectExamTotalNum(@Param("relation_id") int relation_id);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectExamRowNum <BR>
	 * Description: 查询个人在考试中的排名 <BR>
	 * Remark: <BR>
	 * @param score
	 * @param relation_id
	 * @return int<BR>
	 */
	public String selectExamRowNum(@Param("score") int score,
			@Param("relation_id") int relation_id);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectAllGrade <BR>
	 * Description: 查询成绩总览 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return List<ExamGradeVo><BR>
	 */
	public List<ExamGradeVo> selectAllGrade(ExamQueryConditionBean bean);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectAllGradeCount <BR>
	 * Description: 查询成绩总览条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int selectAllGradeCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen<BR> 
	 * Method name: selectExamAssignDetail <BR>
	 * Description: 查询考试详情 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return ExamAssignDetailVo<BR>
	 */
	public ExamAssignDetailVo selectExamAssignDetail(@Param("exam_assign_id") int exam_assign_id);

	/**
	 * @author JFTT)zhangchen<BR> 
	 * Method name: updateExamAssignInfo <BR>
	 * Description: 更新我的考试记录 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return void<BR>
	 */
	public void updateExamAssignInfo(ExamAssignBean bean);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectSimulateCount <BR>
	 * Description: 获取模拟试题条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return int<BR>
	 */
	public int selectSimulateCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectExamRecorderCount <BR>
	 * Description: 获取我的考试记录条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return int<BR>
	 */
	public int selectExamRecorderCount(ExamQueryConditionBean bean);

	/**
	 * @author JFTT)zhangchen <BR>
	 * Method name: selectInfoByAssignId <BR>
	 * Description: 通过ID查询一场考试所需要的一些重要参数 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return ExamQueryConditionBean<BR>
	 */
	public ExamQueryConditionBean selectInfoByAssignId(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectGradeOtherInfo <BR>
	 * Description: 查询成绩总览页面的其它信息，如考试时长、及格分、总分 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamRecorderVo<BR>
	 */
	public ExamRecorderVo selectGradeOtherInfo(@Param("id") int id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectLeaveTimes <BR>
	 * Description: selectLeaveTimes <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  int<BR>
	 */
	public int selectLeaveTimes(@Param("id") int id);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectOrganizingMode <BR>
	 * Description: 通过assign_id查询组卷方式 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  int<BR>
	 */
	public ExamQueryConditionBean selectTestDetailParam(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectPassScore <BR>
	 * Description: 查询及格分 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	public int selectPassScore(@Param("id") int id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectUserByRelationId <BR>
	 * Description: 根据考试ID查询用户ID <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  List<ExamAssignBean><BR>
	 */
	public List<ExamAssignBean> selectUserByRelationId(@Param("relation_id") int relation_id);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectUserIdsByRelationId <BR>
	 * Description: 根据考试ID查询用户IDs <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  String<BR>
	 */
	public List<String> selectUserIdsByRelationId(@Param("relation_id") int relation_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteByRelationId <BR>
	 * Description: 根据考试ID删除 <BR>
	 * Remark: <BR>  void<BR>
	 */
	public void deleteByRelationId(@Param("relation_id") int relation_id);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectTestTimes <BR>
	 * Description: 通过assignId查询考试次数 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamAssignBean<BR>
	 */
	ExamAssignBean selectTestTimes(@Param("id") int id);
	// zhangchen end

	/** shenhaijun start */

	/**
	 * Method name: getMyExamCount <BR>
	 * Description: 获取我的考试数量 <BR>
	 * Remark: <BR>
	 * 
	 * @param userId
	 * @param examStatus
	 * @return
	 * @throws DataBaseException
	 *             Integer<BR>
	 */
	public Integer getMyExamCount(@Param("userId") Integer userId,
			@Param("examStatus") Integer examStatus) throws DataBaseException;
	
	/**
	 * Method name: getIdByConditions <BR>
	 * Description: 根据条件查询id <BR>
	 * Remark: <BR>
	 * @param relationType
	 * @param relationId
	 * @param paperId
	 * @param userId
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	Integer getIdByConditions(@Param("relationType")Integer relationType, @Param("relationId")Integer relationId, @Param("paperId")Integer paperId,
			@Param("userId")Integer userId) throws Exception;
	
	/**
	 * Method name: getIdByExamIdUserId <BR>
	 * Description: 根据考试id和用户id查询学习地图的考试分配信息 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @param userId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	Integer getIdByExamIdUserId(@Param("examId")Integer examId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询考试分配信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ExamAssignBean<BR>
	 */
	ExamAssignBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/** shenhaijun end */
	
	/**chenrui start*/
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getExamResultListCount <BR>
	 * Description: 获取成绩预览列表记录 数 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  int<BR>
	 */
	public int getExamResultListCount(cjylSearchVo paramVo) throws DataBaseException;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAssignInfoByUserExamId <BR>
	 * Description: 根据用户 和考试id获取考试记录信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param examId
	 * @return  ExamAssignBean<BR>
	 */
	public ExamAssignBean getAssignInfoByUserExamId(@Param("userId")String userId, @Param("examId")String examId) throws DataBaseException;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getInfoByPromoteNum <BR>
	 * Description:  获取晋级者的考试信息<BR>
	 * Remark: <BR>
	 * @param examId
	 * @param promoteNum
	 * @throws DataBaseException  void<BR>
	 */
	public List<ExamAssignBean> getInfoByPromoteNum( @Param("examId")int examId,  @Param("promoteNum")int promoteNum)throws DataBaseException;

	public List<ExamAssignBean> getCjYlListAll(cjylSearchVo paramVo) throws DataBaseException;
	
	/**chenrui end*/

	/** zhangbocheng start */
	/**
	 * Method name: selectExamAssignInfoByUserIdForMobile <BR>
	 * Description: 查询我的考试-参与考试记录-移动端 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return List<ExamRecorderVo><BR>
	 */
	public List<ExamRecorderVo> selectExamAssignInfoByUserIdForMobiel(
			ExamQueryConditionBean bean);

	/**
	 * Method name: getExamRecorderCountForMobile <BR>
	 * Description: 获取我的考试记录条数-移动端 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int selectExamRecorderCountForMobile(ExamQueryConditionBean bean);
	/** zhangbocheng end */
	
	/**
	 * wangjian add
	 * 获取 某次考试的 参加次数
	 */
	public Integer getUserTestNum(@Param("relation_id")long relation_id, @Param("user_id")long user_id);
}
