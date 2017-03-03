/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseDetailServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-28        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CourseCollectionBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.vo.CourseWareProgressVo;
import com.jftt.wifi.bean.vo.SectionExamVo;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.dao.CourseCollectionDaoMapper;
import com.jftt.wifi.dao.CourseExamRecordDaoMapper;
import com.jftt.wifi.dao.CourseStudyRecordDaoMapper;
import com.jftt.wifi.dao.CourseWareRecordDaoMapper;
import com.jftt.wifi.dao.ResCourseCategoryDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.dao.ResCoursewareDaoMapper;
import com.jftt.wifi.dao.ResSectionCoursewareDaoMapper;
import com.jftt.wifi.dao.ResSectionDaoMapper;
import com.jftt.wifi.dao.ResSectionExamDaoMapper;
import com.jftt.wifi.dao.ResTeacherDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseDetailService;
import com.jftt.wifi.util.CourseDurationUtil;

/**
 * class name:CourseDetailServiceImpl <BR>
 * class description: 课程详情service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-28
 * @author JFTT)ShenHaijun
 */
@Service("courseDetailService")
public class CourseDetailServiceImpl implements CourseDetailService{
	
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	@Resource(name="resSectionDaoMapper")
	private ResSectionDaoMapper resSectionDaoMapper;
	@Resource(name="resSectionCoursewareDaoMapper")
	private ResSectionCoursewareDaoMapper resSectionCoursewareDaoMapper;
	@Resource(name="resSectionExamDaoMapper")
	private ResSectionExamDaoMapper resSectionExamDaoMapper;
	@Resource(name="courseWareRecordDaoMapper")
	private CourseWareRecordDaoMapper courseWareRecordDaoMapper;
	@Resource(name="courseExamRecordDaoMapper")
	private CourseExamRecordDaoMapper courseExamRecordDaoMapper;
	@Resource(name="courseStudyRecordDaoMapper")
	private CourseStudyRecordDaoMapper courseStudyRecordDaoMapper;
	@Resource(name="resCourseCategoryDaoMapper")
	private ResCourseCategoryDaoMapper resCourseCategoryDaoMapper;
	@Resource(name="courseCollectionDaoMapper")
	private CourseCollectionDaoMapper courseCollectionDaoMapper;
	@Resource(name="resTeacherDaoMapper")
	private ResTeacherDaoMapper resTeacherDaoMapper;
	@Resource(name="resCoursewareDaoMapper")
	private ResCoursewareDaoMapper resCoursewareDaoMapper;
	
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getThisCourseCategory(java.lang.Integer) <BR>
	 * Method name: getThisCourseCategory <BR>
	 * Description: 找出该课程的课程体系结构列表 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseCategoryBean> getThisCourseCategory(Integer courseId)
			throws DataBaseException {
		List<ResCourseCategoryBean> categorys = new ArrayList<ResCourseCategoryBean>();
		//找出该课程的最近课程体系
		ResCourseCategoryBean categoryBean = resCourseCategoryDaoMapper.getCategoryByCourseId(courseId);
		if(categoryBean != null){
			//将课程体系加入集合
			categorys.add(categoryBean);
			//如果该课程体系有父级课程体系，就找出该父级课程体系
			if(categoryBean.getParentId() != null){
				//继续向上寻找，直到没有父级课程体系为止
				categorys = getCategorys(categorys,categoryBean.getParentId());
			}
		}
		//将这些课程体系按照父-子关系逐级向下排列，组成一个list，并返回
		Collections.reverse(categorys);
		return categorys;
	}
	
	/**
	 * Method name: getCategorys <BR>
	 * Description: 递归调用直到找到全部父级课程体系 <BR>
	 * Remark: <BR>
	 * @param categorys 所有课程体系
	 * @param categoryId 当前课程体系id
	 * @return  List<ResCourseCategoryBean><BR>
	 */
	public List<ResCourseCategoryBean> getCategorys(List<ResCourseCategoryBean> categorys,Integer parentId) throws DataBaseException{
		//根据父级id查询课程体系
		ResCourseCategoryBean category = resCourseCategoryDaoMapper.getById(parentId);
		if(category != null){
			//将课程体系添加进集合中
			categorys.add(category);
			//如果父级id依然存在
			if(category.getParentId() != null){
				//递归调用
				getCategorys(categorys,category.getParentId());
			}
		}
		return categorys;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseById(java.lang.Integer) <BR>
	 * Method name: getCourseById <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return ResCourseBean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public ResCourseBean getCourseById(Integer courseId) throws DataBaseException {
		return resCourseDaoMapper.getById(courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseSections(java.lang.Integer) <BR>
	 * Method name: getCourseSections <BR>
	 * Description: 查询该课程所有章节 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return List<ResSectionBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResSectionBean> getCourseSections(Integer courseId)
			throws DataBaseException {
		return resSectionDaoMapper.getCourseSections(courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getSectionWares(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSectionWares <BR>
	 * Description: 根据章节查询所有课件及该课件的学习记录 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return List<SectionWareVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<SectionWareVo> getSectionWares(Integer sectionId,
			Integer userId) throws DataBaseException {
		return resSectionCoursewareDaoMapper.getSectionWares(sectionId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseTest(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseTest <BR>
	 * Description: 查询课程测试及测试记录 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return List<SectionExamVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<SectionExamVo> getCourseTest(Integer courseId,
			Integer userId) throws DataBaseException {
		return resSectionExamDaoMapper.getCourseTest(courseId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseCollection(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseCollection <BR>
	 * Description: 判断该课程是否被收藏 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return boolean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public boolean getCourseCollection(Integer courseId,Integer userId) throws DataBaseException {
		Integer isCollectCount = courseCollectionDaoMapper.getIsCourseCollection(courseId,userId);
		if(isCollectCount != null && isCollectCount > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#collectThisCourse(com.jftt.wifi.bean.CourseCollectionBean) <BR>
	 * Method name: collectThisCourse <BR>
	 * Description: 收藏该课程 <BR>
	 * Remark: <BR>
	 * @param collection 课程Bean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void collectThisCourse(CourseCollectionBean collection)
			throws DataBaseException {
		courseCollectionDaoMapper.addMyCollection(collection);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getSectionTests(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSectionTests <BR>
	 * Description: 根据章节查询所有测试情况 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return List<SectionExamVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<SectionExamVo> getSectionTests(Integer sectionId, Integer userId)
			throws DataBaseException {
		return resSectionExamDaoMapper.getSectionTests(sectionId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getNearestCategory(java.lang.Integer) <BR>
	 * Method name: getNearestCategory <BR>
	 * Description: 获取最近的课程体系名称 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return String
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public String getNearestCategory(Integer courseId) throws DataBaseException {
		ResCourseCategoryBean categoryBean = resCourseCategoryDaoMapper.getCategoryByCourseId(courseId);
		if(categoryBean != null){
			if(categoryBean.getName() != null){
				return categoryBean.getName();
			}
		}
		return null;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getTeacherNameById(java.lang.Integer) <BR>
	 * Method name: getTeacherNameById <BR>
	 * Description: 根据教师id查询教师姓名 <BR>
	 * Remark: <BR>
	 * @param teacherId 教师id
	 * @return String
	 * @throws Exception  <BR>
	 */
	@Override
	public String getTeacherNameById(Integer teacherId) throws Exception {
		if(teacherId != null){
			return resTeacherDaoMapper.getTeacherNameById(teacherId);
		}else{
			return null;
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseTotalDuration(java.lang.Integer) <BR>
	 * Method name: getCourseTotalDuration <BR>
	 * Description: 获取课程总时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return String[]
	 * @throws Exception  <BR>
	 */
	@Override
	public String[] getCourseTotalDuration(Integer courseId) throws Exception {
		//课程的所有时长（秒）
		List<Integer> durations = resCoursewareDaoMapper.getDurationByCourseId(courseId);
		//将获取的所有时长（以秒计）相加并转换为时分秒字符串数组的形式
		return CourseDurationUtil.getDurationStrArr(durations);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseStudyedDuration(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseStudyedDuration <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return String[]
	 * @throws Exception  <BR>
	 */
	@Override
	public String[] getCourseStudyedDuration(Integer courseId, Integer userId)
			throws Exception {
		//该课程已学时长
		List<Long> durations = courseWareRecordDaoMapper.getCourseStudyedDuration(courseId,userId);
		//将获取的所有时长（以秒计）相加并转换为时分秒字符串数组的形式
		return CourseDurationUtil.getDurationStrArrOfLong(durations);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseDetailService#getCourseStudyedDurationNew(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseStudyedDurationNew <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return String[]
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public String[] getCourseStudyedDurationNew(Integer courseId, Integer userId) throws DataBaseException {
		//定义课件已学时长列表
		List<Integer> studyedDurations = new ArrayList<Integer>();
		//获取该课程所有课件学习进度
		List<CourseWareProgressVo> studyedPercents = courseWareRecordDaoMapper.getStudyedPercents(courseId,userId);
		if(studyedPercents != null && studyedPercents.size() > 0){
			//遍历课件，计算已学时长（课件时长*课件学习进度）
			for (int i = 0; i < studyedPercents.size(); i++) {
				if(studyedPercents.get(i).getProgressPercent() != null){
					Integer duration = resCoursewareDaoMapper.getById(studyedPercents.get(i).getWareId()).getDuration();
					if(duration != null){
						studyedDurations.add((duration * studyedPercents.get(i).getProgressPercent())/100);
					}else{
						studyedDurations.add(0);
					}
				}else{
					studyedDurations.add(0);
				}
			}
			//将已学时长转换为需要的格式返回
			return CourseDurationUtil.getDurationStrArr(studyedDurations);
		}else{
			//将已学时长转换为需要的格式返回
			return CourseDurationUtil.getDurationStrArr(studyedDurations);
		}
	}
	
	/**shenhaijun end*/
	
	/**
	 * Method name: getStudentNumById <BR>
	 * Description: 根据课程id查询学习人数 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getStudentNumById(Integer courseId) throws Exception {
		if(courseId != null){
			return resCourseDaoMapper.getStudentNumById(courseId);
		}else{
			return null;
		}
	}
	
	/**zhangtbocheng mobile start*/
	/**
	 * 估计id查出章节测试
	 * getSectionExamById
	 * @param id
	 * @return ResSectionExamBean
	 * @throws Exception
	 */
	@Override
	public ResSectionExamBean getSectionExamById(Integer id)
			throws Exception {
		return resSectionExamDaoMapper.getById(id);
	}
	/**zhangtbocheng mobile end*/

}
