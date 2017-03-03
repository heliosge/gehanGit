/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentLearnMapServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月10日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.vo.CourseVoForPost;
import com.jftt.wifi.bean.vo.MyPostPromotionPathVo;
import com.jftt.wifi.bean.vo.PostExamVo;
import com.jftt.wifi.bean.vo.PromotionPathDetailVo;
import com.jftt.wifi.bean.vo.PromotionPathStageVo;
import com.jftt.wifi.common.DataBaseConstant;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.dao.PostPromotionPathDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.StudentLearnMapService;

/**
 * class name:StudentLearnMapServiceImpl <BR>
 * class description: 学员学习地图service <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月10日
 * @author JFTT)ShenHaijun
 */
@Service("studentLearnMapService")
public class StudentLearnMapServiceImpl implements StudentLearnMapService{
	
	@Resource(name="postPromotionPathDaoMapper")
	private PostPromotionPathDaoMapper postPromotionPathDaoMapper;
	@Resource(name="examScheduleDaoMapper")
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	@Resource(name="examAssignInfoDaoMapper")
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Resource(name="examPaperDaoMapper")
	private ExamPaperDaoMapper examPaperDaoMapper;
	@Resource(name="managePostDaoMapper")
	private ManagePostDaoMapper managePostDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getMyPromotionPathCount(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getMyPromotionPathCount <BR>
	 * Description: 获取我的所有晋升路径的数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getMyPromotionPathCount(Integer userId,Integer companyId,Integer subCompanyId) throws DataBaseException {
		return postPromotionPathDaoMapper.getMyPromotionPathCount(userId,companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getMyPromotionPath(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getMyPromotionPath <BR>
	 * Description: 获取我的所有晋升路径 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyPostPromotionPathVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<MyPostPromotionPathVo> getMyPromotionPath(Integer userId,Integer companyId, Integer subCompanyId, 
			Integer fromNum,Integer pageSize) throws DataBaseException {
		return postPromotionPathDaoMapper.getMyPromotionPath(userId,companyId,subCompanyId,fromNum,pageSize);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getPathStages(java.lang.Integer) <BR>
	 * Method name: getPathStages <BR>
	 * Description: 获取该路径所有阶段 <BR>
	 * Remark: <BR>
	 * @param promotionPathId 路径id
	 * @return List<PromotionPathStageVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<PromotionPathStageVo> getPathStages(Integer promotionPathId) throws DataBaseException {
		return postPromotionPathDaoMapper.getPathStages(promotionPathId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getStageTest(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getStageTest <BR>
	 * Description: 获取阶段测试 <BR>
	 * Remark: <BR>
	 * @param stageId 阶段id
	 * @param userId 用户id
	 * @return PostExamVo
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public PostExamVo getStageTest(Integer stageId,Integer userId) throws DataBaseException {
		return examScheduleDaoMapper.getStageTest(stageId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getPostCoursesCount(java.lang.Integer) <BR>
	 * Method name: getPostCoursesCount <BR>
	 * Description: 获取岗位课程数量 <BR>
	 * Remark: <BR>
	 * @param postId 岗位id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getPostCoursesCount(Integer postId) throws DataBaseException {
		return resCourseDaoMapper.getPostCoursesCount(postId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getPostCourses(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getPostCourses <BR>
	 * Description: 获取岗位所有课程 <BR>
	 * Remark: <BR>
	 * @param postId 岗位id
	 * @param userId 用户id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return List<CourseVoForPost>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<CourseVoForPost> getPostCourses(Integer postId, Integer userId,
			Integer page, Integer pageSize) throws DataBaseException {
		Integer fromNum = page * pageSize;//此处page从0开始
		List<CourseVoForPost> postCourses = resCourseDaoMapper.getPostCourses(postId,userId,fromNum,pageSize);
		//将没有学习进度的课程默认为进行中
		Integer learnProcess = null;
		if(postCourses != null && postCourses.size() > 0){
			for (int i = 0; i < postCourses.size(); i++) {
				learnProcess = postCourses.get(i).getLearnProcess();
				if(learnProcess == null){
					postCourses.get(i).setLearnProcess(1);//进行中
				}
			}
		}
		return postCourses;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getRoadDetailsCount(java.lang.Integer, java.lang.String) <BR>
	 * Method name: getRoadDetailsCount <BR>
	 * Description: 获取晋升路径详细数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param searchContent 查询条件
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getRoadDetailsCount(Integer userId,String searchContent) throws DataBaseException {
		Integer promotionStatusContinue = DataBaseConstant.PROMOTION_STATUS_CONTINUE;//阶段晋升状态为进行中
		return postPromotionPathDaoMapper.getRoadDetailsCount(userId,searchContent,promotionStatusContinue);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getRoadDetails(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getRoadDetails <BR>
	 * Description: 获取晋升路径详细 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param searchContent 查询条件
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return List<PromotionPathDetailVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<PromotionPathDetailVo> getRoadDetails(Integer userId,String searchContent, 
			Integer page, Integer pageSize) throws DataBaseException {
		Integer fromNum = page * pageSize;//此处page从0开始
		List<PromotionPathDetailVo> roadDetails = postPromotionPathDaoMapper.getRoadDetails(
				userId,searchContent,fromNum,pageSize);
		Integer promotionPercent = null;
		//遍历集合，将晋升状态放入集合
		if(roadDetails != null && roadDetails.size() > 0){
			for (int i = 0; i < roadDetails.size(); i++) {
				promotionPercent = roadDetails.get(i).getPromotionPercent();
				if(promotionPercent == 100){
					roadDetails.get(i).setPromotionState(1);
				}else{
					roadDetails.get(i).setPromotionState(2);
				}
			}
		}
		return roadDetails;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#saveAssignInfo(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: saveAssignInfo <BR>
	 * Description: 保存试卷分配信息 <BR>
	 * Remark: <BR>
	 * @param examId 试卷id
	 * @param userId 用户id
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveAssignInfo(Integer examId, Integer userId)
			throws DataBaseException {
		Integer assignId = examAssignInfoDaoMapper.getIdByExamIdUserId(examId,userId);
		if(assignId == null){
			//如果没有分配信息，就添加一条
			ExamAssignBean assignInfo = new ExamAssignBean();
			assignInfo.setRelationType(1);
			assignInfo.setRelationId(examId);
			assignInfo.setUserId(userId);
			assignInfo.setTestTimes(0);
			ExamScheduleBean examSchedule = examScheduleDaoMapper.getById(examId);//考试信息
			if(examSchedule != null){
				if(examSchedule.getPaperId() != null){
					assignInfo.setPaperId(examSchedule.getPaperId());
					Integer totalScore =  examPaperDaoMapper.getTotalScoreById(examSchedule.getPaperId());
					Integer passScorePercent = examSchedule.getPassScorePercent();
					if(totalScore != null && passScorePercent != null){
						assignInfo.setPassScore((totalScore * passScorePercent)/100);
					}
				}
			}
			examAssignInfoDaoMapper.addAssignInfo(assignInfo);
			assignId = assignInfo.getId();
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getPostIdByStageId(java.lang.Integer) <BR>
	 * Method name: getPostIdByStageId <BR>
	 * Description: 查出当前阶段对应的岗位id <BR>
	 * Remark: <BR>
	 * @param stageId 阶段id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getPostIdByStageId(Integer stageId) throws DataBaseException {
		return postPromotionPathDaoMapper.getPostIdByStageId(stageId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentLearnMapService#getPostById(java.lang.Integer) <BR>
	 * Method name: getPostById <BR>
	 * Description: 根据id查询岗位 <BR>
	 * Remark: <BR>
	 * @param postId 岗位id
	 * @return ManagePostBean
	 * @throws Exception  <BR>
	 */
	@Override
	public ManagePostBean getPostById(Integer postId) throws Exception {
		return managePostDaoMapper.getById(postId);
	}
	
}
