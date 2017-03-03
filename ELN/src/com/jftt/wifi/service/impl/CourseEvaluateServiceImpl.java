/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseEvaluateServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.bean.vo.CourseEvaluationVo;
import com.jftt.wifi.dao.CourseEvaluationDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseEvaluateService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:CourseEvaluateServiceImpl <BR>
 * class description: 课程评价service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 * @author JFTT)ShenHaijun
 */
@Service("courseEvaluateService")
public class CourseEvaluateServiceImpl implements CourseEvaluateService{
	
	@Resource(name="courseEvaluationDaoMapper")
	private CourseEvaluationDaoMapper courseEvaluationDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	/**shenhaijun start */
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseEvaluateService#getEvaluateCount(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getEvaluateCount <BR>
	 * Description: 获取该课程评价人数 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getEvaluateCount(Integer courseId, Integer companyId,
			Integer subCompanyId) throws DataBaseException {
		return courseEvaluationDaoMapper.getEvaluateCount(courseId,companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseEvaluateService#getCourseEvaluations(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseEvaluations <BR>
	 * Description: 查询课程所有评价 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<CourseEvaluationVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CourseEvaluationVo> getCourseEvaluations(Integer courseId,
			Integer companyId,Integer subCompanyId,Integer fromNum,Integer pageSize) throws Exception {
		List<CourseEvaluationVo> evaluationVos = new ArrayList<CourseEvaluationVo>();
		//查出所有评价
		List<CourseEvaluationBean> evaluations = courseEvaluationDaoMapper.getCourseEvaluations(
				courseId,companyId,subCompanyId,fromNum,pageSize);
		//遍历评价，将用户名和用户头像放进返回结果
		String userIdStr;//用户id（String）
		for (int i = 0; i < evaluations.size(); i++) {
			CourseEvaluationVo evaluationVo = new CourseEvaluationVo();
			evaluationVo.setId(evaluations.get(i).getId());//设置id
			evaluationVo.setCommentTime(evaluations.get(i).getCommentTime());//设置评价时间
			evaluationVo.setScore(evaluations.get(i).getScore());//设置评分
			evaluationVo.setContent(evaluations.get(i).getContent());//设置评价内容
			evaluationVo.setUserId(evaluations.get(i).getUserId());
			userIdStr = String.valueOf(evaluations.get(i).getUserId());//用户id（String）
			evaluationVo.setUserName(manageUserService.findUserById(userIdStr).getUserName());//设置评价人用户名
			evaluationVo.setUserPic(manageUserService.findUserById(userIdStr).getHeadPhoto());//设置评价人头像
			//将评价结果添加到集合中
			evaluationVos.add(evaluationVo);
		}
		return evaluationVos;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseEvaluateService#addCourseEvaluation(com.jftt.wifi.bean.CourseEvaluationBean) <BR>
	 * Method name: addCourseEvaluation <BR>
	 * Description: 添加一条课程评价 <BR>
	 * Remark: <BR>
	 * @param courseEvaluationBean 课程评价Bean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addCourseEvaluation(CourseEvaluationBean courseEvaluationBean)
			throws DataBaseException {
		courseEvaluationDaoMapper.addCourseEvaluation(courseEvaluationBean);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseEvaluateService#getMyEvaluate(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getMyEvaluate <BR>
	 * Description: 获取我的课程评价 <BR>
	 * Remark: <BR>
	 * @param userId 用户ud
	 * @param courseId 课程id
	 * @return CourseEvaluationBean
	 * @throws Exception  <BR>
	 */
	@Override
	public CourseEvaluationBean getMyEvaluate(Integer userId, Integer courseId)
			throws Exception {
		return courseEvaluationDaoMapper.getMyEvaluate(userId,courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseEvaluateService#giveMyScore(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: giveMyScore <BR>
	 * Description: 给出我的评分 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param score 分数
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void giveMyScore(Integer userId, Integer courseId, Integer score,
			Integer companyId, Integer subCompanyId) throws Exception {
		//查询是否已经评价过该项目
		CourseEvaluationBean evaluation = courseEvaluationDaoMapper.getMyEvaluate(userId,courseId);
		if(evaluation == null){
			//添加我的评分
			CourseEvaluationBean newEvaluation = new CourseEvaluationBean();
			newEvaluation.setCourseId(courseId);
			newEvaluation.setUserId(userId);
			newEvaluation.setScore(score);
			newEvaluation.setCommentTime(new Date());
			newEvaluation.setCompanyId(companyId);
			newEvaluation.setSubCompanyId(subCompanyId);
			courseEvaluationDaoMapper.addCourseEvaluation(newEvaluation);
		}else{
			//更新我的评分
			evaluation.setScore(score);
			courseEvaluationDaoMapper.updateCourseEvaluation(evaluation);
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseEvaluateService#giveMyEvaluation(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: giveMyEvaluation <BR>
	 * Description: 给出我的评价 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param content 内容
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void giveMyEvaluation(Integer userId, Integer courseId,
			String content, Integer companyId, Integer subCompanyId)
			throws Exception {
		//查询是否已经评价过该项目
		CourseEvaluationBean evaluation = courseEvaluationDaoMapper.getMyEvaluate(userId,courseId);
		if(evaluation == null){
			//添加我的评分
			CourseEvaluationBean newEvaluation = new CourseEvaluationBean();
			newEvaluation.setCourseId(courseId);
			newEvaluation.setUserId(userId);
			newEvaluation.setContent(content);
			newEvaluation.setCommentTime(new Date());
			newEvaluation.setCompanyId(companyId);
			newEvaluation.setSubCompanyId(subCompanyId);
			courseEvaluationDaoMapper.addCourseEvaluation(newEvaluation);
		}else{
			//更新我的评分
			evaluation.setContent(content);
			courseEvaluationDaoMapper.updateCourseEvaluation(evaluation);
		}
	}
	
	/**shenhaijun end*/
	
}
