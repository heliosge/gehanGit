/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentInfoServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-5        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.vo.IndexShowVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.common.DataBaseConstant;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.IntTotalintegralDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestContestDaoMapper;
import com.jftt.wifi.dao.OamBarDaoMapper;
import com.jftt.wifi.dao.OamTopicDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.StudentInfoService;

/**
 * class name:StudentInfoServiceImpl <BR>
 * class description: 学员信息service <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-5
 * @author JFTT)ShenHaijun
 */
@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {
	
	@Resource(name="intTotalintegralDaoMapper")
	private IntTotalintegralDaoMapper intTotalintegralDaoMapper;
	@Resource(name="examAssignInfoDaoMapper")
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	@Resource(name="examScheduleDaoMapper")
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Resource(name="oamTopicDaoMapper")
	private OamTopicDaoMapper oamTopicDaoMapper;
	@Resource(name="oamBarDaoMapper")
	private OamBarDaoMapper oamBarDaoMapper;
	@Resource(name="knowledgeContestContestDaoMapper")
	private KnowledgeContestContestDaoMapper knowledgeContestContestDaoMapper;
	
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getMyPoint(java.lang.String) <BR>
	 * Method name: getMyPoint <BR>
	 * Description: 获取我的积分 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getMyPoint(Integer userId) throws DataBaseException {
		return intTotalintegralDaoMapper.getMyPoint(userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getMyStudyHours(java.lang.Integer) <BR>
	 * Method name: getMyStudyHours <BR>
	 * Description: 获取我的学时（指我的已完成课程的学时） <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getMyStudyHours(Integer userId) throws DataBaseException {
		Integer learnProcess = DataBaseConstant.COURSESTUDYRECORD_LEARNPROCESS_COMPLETED;//已完成
		return resCourseDaoMapper.getMyStudyHours(userId,learnProcess);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getMyExamCount(java.lang.Integer) <BR>
	 * Method name: getMyExamCount <BR>
	 * Description: 获取我的待参与考试数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getMyExamCount(Integer userId) throws DataBaseException {
		Integer examStatus = DataBaseConstant.EXAMASSIGNINFO_STATUS_NOTSTARTED;//未开始
		return examAssignInfoDaoMapper.getMyExamCount(userId,examStatus);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getCourseCount(java.lang.String, java.lang.String) <BR>
	 * Method name: getCourseCount <BR>
	 * Description: 获取公司的课程数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getCourseCount(Integer companyId, Integer subCompanyId)
			throws DataBaseException {
		Integer deleteStatus = DataBaseConstant.RES_COURSE_STATUS_DELETE;//删除状态
		return resCourseDaoMapper.getCourseCountByCompanyId(companyId,subCompanyId,deleteStatus);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getExamCount(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getExamCount <BR>
	 * Description: 获取公司的考试数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getExamCount(Integer companyId, Integer subCompanyId,Integer userId)
			throws DataBaseException {
		return examScheduleDaoMapper.getExamCountByCompanyId(companyId,subCompanyId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getSpecialTopicCount(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSpecialTopicCount <BR>
	 * Description: 获取专题数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getSpecialTopicCount(Integer companyId, Integer subCompanyId)
			throws DataBaseException {
		Integer deleteStatus = DataBaseConstant.OAM_TOPIC_STATUS_DELETE;//删除
		return oamTopicDaoMapper.getSpecialTopicCount(companyId,subCompanyId,deleteStatus);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getSpreadBlocks(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSpreadBlocks <BR>
	 * Description: 获取学员首页推广模块 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<IndexShowVo>
	 * @throws Exception 
	 */
	@Override
	public List<IndexShowVo> getSpreadBlocks(Integer companyId,
			Integer subCompanyId) throws Exception {
		//获取该公司的8栏风格运维栏推广
		Integer style = DataBaseConstant.OAM_BAR_EIGHTCOLUMNS;
		List<IndexShowVo> blocks = oamBarDaoMapper.getBarsByCompanyId(companyId,subCompanyId,style);
		//遍历该集合将名称和封面填充进去
		if(blocks != null && blocks.size() > 0){
			for (int i = 0; i < blocks.size(); i++) {
				if(blocks.get(i).getType().equals(DataBaseConstant.OAM_BAR_TYPE_TOPIC)){
					//如果推广类型是专题
					blocks.get(i).setName(
							oamTopicDaoMapper.selectOamTopicById(blocks.get(i).getSpreadId()).getName());
					blocks.get(i).setCoverImg(
							oamTopicDaoMapper.selectOamTopicById(blocks.get(i).getSpreadId()).getCoverImage());
				}else if(blocks.get(i).getType().equals(DataBaseConstant.OAM_BAR_TYPE_COURSE)){
					//如果推广类型是课程
					blocks.get(i).setName(
							resCourseDaoMapper.getById(blocks.get(i).getSpreadId()).getName());
					blocks.get(i).setCoverImg(
							resCourseDaoMapper.getById(blocks.get(i).getSpreadId()).getFrontImage());
				}else if(blocks.get(i).getType().equals(DataBaseConstant.OAM_BAR_TYPE_COMPETITION)){
					//如果推广类型是大赛
					blocks.get(i).setName(
							knowledgeContestContestDaoMapper.getById(blocks.get(i).getSpreadId()).getName());
					blocks.get(i).setCoverImg(
							knowledgeContestContestDaoMapper.getById(blocks.get(i).getSpreadId()).getCoverImageForList());
				}else{
					throw new DataBaseException();
				}
			}
		}
		return blocks;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentInfoService#getFeatureCourses(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getFeatureCourses <BR>
	 * Description: 获取精选课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseVo> getFeatureCourses(Integer companyId,
			Integer subCompanyId) throws DataBaseException {
		Integer isFeatured = DataBaseConstant.RES_COURSE_FEATURED_IS;//精选
		Integer status = DataBaseConstant.RES_COURSE_STATUS_DELETE;//删除
		return resCourseDaoMapper.getFeatureCourses(companyId,subCompanyId,isFeatured,status);
	}
	
	/**shenhaijun end*/
	
}
