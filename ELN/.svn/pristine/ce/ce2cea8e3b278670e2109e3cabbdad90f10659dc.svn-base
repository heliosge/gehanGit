/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentCourseServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-21        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResCourseOpenCrowdBean;
import com.jftt.wifi.bean.ResCourseTypeBean;
import com.jftt.wifi.bean.vo.CourseNextTypeVo;
import com.jftt.wifi.bean.vo.CourseTypeVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.dao.CourseEvaluationDaoMapper;
import com.jftt.wifi.dao.CourseExamRecordDaoMapper;
import com.jftt.wifi.dao.CourseNoteDaoMapper;
import com.jftt.wifi.dao.CourseQuestionTopicDaoMapper;
import com.jftt.wifi.dao.CourseStudyRecordDaoMapper;
import com.jftt.wifi.dao.CourseWareRecordDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ManageGroupStudentDaoMapper;
import com.jftt.wifi.dao.ResCourseCategoryDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.dao.ResCoursewareDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseDetailService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.StudentCourseService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.CourseDurationUtil;

/**
 * class name:StudentCourseServiceImpl <BR>
 * class description: 学员课程service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-21
 * @author JFTT)ShenHaijun
 */
@Service("studentCourseService")
public class StudentCourseServiceImpl implements StudentCourseService {
	@Resource(name="resCourseCategoryDaoMapper")
	private ResCourseCategoryDaoMapper resCourseCategoryDaoMapper;
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	@Resource(name="manageGroupStudentDaoMapper")
	private ManageGroupStudentDaoMapper manageGroupStudentDaoMapper;
	@Resource(name="courseStudyRecordDaoMapper")
	private CourseStudyRecordDaoMapper courseStudyRecordDaoMapper;
	@Resource(name="courseWareRecordDaoMapper")
	private CourseWareRecordDaoMapper courseWareRecordDaoMapper;
	@Resource(name="courseExamRecordDaoMapper")
	private CourseExamRecordDaoMapper courseExamRecordDaoMapper;
	@Resource(name="courseNoteDaoMapper")
	private CourseNoteDaoMapper courseNoteDaoMapper;
	@Resource(name="courseQuestionTopicDaoMapper")
	private CourseQuestionTopicDaoMapper courseQuestionTopicDaoMapper;
	@Resource(name="courseEvaluationDaoMapper")
	private CourseEvaluationDaoMapper courseEvaluationDaoMapper;
	@Resource(name="resCoursewareDaoMapper")
	private ResCoursewareDaoMapper resCoursewareDaoMapper;
	@Resource(name="courseDetailService")
	private CourseDetailService courseDetailService;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getCourseCategorysByCompanyId(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseCategorysByCompanyId <BR>
	 * Description: 根据公司id查询课程体系 （根节点课程体系） <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseCategoryBean> getCourseCategorysByCompanyId(
			Integer companyId,Integer subCompanyId) throws DataBaseException {
		return resCourseCategoryDaoMapper.getCourseCategorysByCompanyId(companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getChildCategory(java.lang.Integer) <BR>
	 * Method name: getChildCategory <BR>
	 * Description: 获取课程体系的子课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId 课程体系id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseCategoryBean> getChildCategorys(Integer categoryId)
			throws DataBaseException {
		return resCourseCategoryDaoMapper.getChildCategorys(categoryId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getUpCategorys(java.lang.Integer) <BR>
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId 课程体系id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseCategoryBean> getUpCategorys(Integer categoryId)
			throws DataBaseException {
		return resCourseCategoryDaoMapper.getUpCategorys(categoryId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getCoursesByConditions(com.jftt.wifi.bean.vo.ResCourseVo, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getCoursesByConditions <BR>
	 * Description: 根据课程来源查询课程 <BR>
	 * Remark: <BR>
	 * @param resCourseVo 课程vo
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @param deptId 部门id
	 * @return List<ResCourseVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ResCourseVo> getCoursesByConditions(ResCourseVo resCourseVo,Integer companyId,
			Integer subCompanyId,Integer userId,Integer postId,String sortName,String sortOrder,
			Integer fromNum,Integer pageSize,String deptId)throws Exception {
		//return resCourseDaoMapper.getCoursesByConditions(resCourseVo,companyId,subCompanyId,sortName,sortOrder,fromNum,pageSize);
		//查询出该体系（分类）下的所有子体系（子分类）
		if(resCourseVo.getCategoryId() != null && resCourseVo.getCategoryOrType() != null){
			List<Integer> childCategoryIdList = null;
			if(resCourseVo.getCategoryOrType() == 1){
				//子课程体系
				List<Integer> nodeIds = new ArrayList<Integer>();
				childCategoryIdList = getChildCategoryIds(resCourseVo.getCategoryId(),nodeIds);
			}else if(resCourseVo.getCategoryOrType() == 2){
				//子课程分类
				List<Integer> nodeIds = new ArrayList<Integer>();
				childCategoryIdList = getChildTypeIds(resCourseVo.getCategoryId(),nodeIds);
			}
			resCourseVo.setChildCategoryIdList(childCategoryIdList);
		}
		//查询用户所在群组id列表
		List<Integer> groupIds = manageGroupStudentDaoMapper.getGroupIdsByUserId(userId);
		//查询出父级（子公司id）列表
		List<Integer> subCompanyIds = getParentSubCompanyIds(companyId,subCompanyId);
		//根据开放人群查询全部课程
		List<ResCourseVo> courses = resCourseDaoMapper.getCoursesByOpenCrowd(resCourseVo,companyId,subCompanyId,postId,groupIds,sortName,sortOrder,fromNum,pageSize,deptId,subCompanyIds);
		//遍历结果，将评分改为保留一位小数
		for (int i = 0; i < courses.size(); i++) {
			courses.get(i).setAverageScore(CommonUtil.getOneDecimalNumber(courses.get(i).getAverageScore()));
		}
		return courses;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getCoursesCount(com.jftt.wifi.bean.vo.ResCourseVo, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getCoursesCount <BR>
	 * Description: 根据课程来源查询课程数目 <BR>
	 * Remark: <BR>
	 * @param resCourseVo 课程vo
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param deptId 部门id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getCoursesCount(ResCourseVo resCourseVo,
			Integer companyId,Integer subCompanyId,Integer userId,Integer postId,String deptId) 
			throws Exception{
		//return resCourseDaoMapper.getCoursesCount(resCourseVo,companyId,subCompanyId);
		//查询出该体系（分类）下的所有子体系（子分类）
		if(resCourseVo.getCategoryId() != null && resCourseVo.getCategoryOrType() != null){
			List<Integer> childCategoryIdList = null;
			if(resCourseVo.getCategoryOrType() == 1){
				//子课程体系
				List<Integer> nodeIds = new ArrayList<Integer>();
				childCategoryIdList = getChildCategoryIds(resCourseVo.getCategoryId(),nodeIds);
			}else if(resCourseVo.getCategoryOrType() == 2){
				//子课程分类
				List<Integer> nodeIds = new ArrayList<Integer>();
				childCategoryIdList = getChildTypeIds(resCourseVo.getCategoryId(),nodeIds);
			}
			resCourseVo.setChildCategoryIdList(childCategoryIdList);
		}
		//查询用户所在群组id列表
		List<Integer> groupIds = manageGroupStudentDaoMapper.getGroupIdsByUserId(userId);
		//查询出父级（子公司id）列表
		List<Integer> subCompanyIds = getParentSubCompanyIds(companyId,subCompanyId);
		//根据开放人群查询课程数量
		return resCourseDaoMapper.getCourseCountByOpenCrowd(resCourseVo,companyId,subCompanyId,postId,groupIds,deptId,subCompanyIds);
	}
	
	/**
	 * Method name: getParentSubCompanyIds <BR>
	 * Description: 查询该子公司的所有父公司子id（包含本公司） <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<Integer>
	 * @throws DataBaseException  List<Integer><BR>
	 */
	private List<Integer> getParentSubCompanyIds(Integer companyId,Integer subCompanyId) throws DataBaseException {
		if(companyId != null && subCompanyId != null){
			List<Integer> rtnSubCompanyIds = new ArrayList<Integer>();
			if(companyId == subCompanyId){
				//如果是集团公司
				rtnSubCompanyIds.add(companyId);
				return rtnSubCompanyIds;
			}else{
				//如果是子公司
				List<ManageDepartmentBean> companys = manageDepartmentDaoMapper.getCompanysByCompanyId(companyId);
				if(companys != null && companys.size() > 0){
					//如果集团公司有子公司
					ManageDepartmentBean department = new ManageDepartmentBean();
					for (int i = 0; i < companys.size(); i++) {
						if(companys.get(i).getSubCompanyId().equals(subCompanyId)){
							department = companys.get(i);
						}
					}
					rtnSubCompanyIds = getParentSubCompanyIdsOfDepart(rtnSubCompanyIds,department,companys);
					rtnSubCompanyIds.add(companyId);
				}else{
					//如果没有子公司
					rtnSubCompanyIds.add(subCompanyId);
					rtnSubCompanyIds.add(companyId);
				}
				return rtnSubCompanyIds;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * Method name: getParentSubCompanyIdsOfDepart <BR>
	 * Description: 获取所有父公司id <BR>
	 * Remark: <BR>
	 * @param rtnSubCompanyIds 返回的子公司id
	 * @param department 部门
	 * @param companys 公司集合
	 * @return  List<Integer><BR>
	 */
	private List<Integer> getParentSubCompanyIdsOfDepart(List<Integer> rtnSubCompanyIds, ManageDepartmentBean department,List<ManageDepartmentBean> companys) {
		//判断是子公司还是部门
		if(department != null && department.getIsSubCompany() == 1){
			rtnSubCompanyIds.add(department.getSubCompanyId());
		}
		//查询父级
		ManageDepartmentBean company = getParentCompany(department,companys);
		if(company != null){
			//继续向上查找
			getParentSubCompanyIdsOfDepart(rtnSubCompanyIds,company,companys);
		}else{
            return rtnSubCompanyIds;
        }
		return rtnSubCompanyIds;
	}
	
	/**
	 * Method name: getParentCompany <BR>
	 * Description: 查询该子公司（部门）的直接父子公司（部门） <BR>
	 * Remark: <BR>
	 * @param department 部门
	 * @param companys 公司集合
	 * @return  ManageDepartmentBean<BR>
	 */
	private ManageDepartmentBean getParentCompany(ManageDepartmentBean department,List<ManageDepartmentBean> companys) {
		if(department != null && department.getParentId() != null && companys != null && companys.size() > 0){
			for (int i = 0; i < companys.size(); i++) {
				if(companys.get(i).getId() == department.getParentId()){
					return companys.get(i);
				}
			}
		}
		return null;
	}

	/**
	 * Method name: getChildCategoryIds <BR>
	 * Description: 获取该课程体系的所有叶子节点课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId 课程体系id
	 * @param nodeIds 节点id集合
	 * @return List<Integer>
	 * @throws Exception  <BR>
	 */
	private List<Integer> getChildCategoryIds(Integer categoryId,List<Integer> nodeIds) throws Exception {
		//获取该课程体系的所有叶子节点课程体系
		List<ResCourseCategoryBean> childCategorys = getChildCategorys(categoryId);
		if(childCategorys != null && childCategorys.size() > 0){
			nodeIds.add(categoryId);//包含该语句表示所有节点，不包含表示所有叶子节点
			for (int i = 0; i < childCategorys.size(); i++) {
				getChildCategoryIds(childCategorys.get(i).getId(),nodeIds);
			}
		}else{
			nodeIds.add(categoryId);
		}
		return nodeIds;
	}
	
	/**
	 * Method name: getChildTypeIds <BR>
	 * Description: 获取该课程分类的所有节点课程分类 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param nodeIds 节点id集合
	 * @return List<Integer>
	 * @throws Exception  <BR>
	 */
	private List<Integer> getChildTypeIds(Integer typeId,List<Integer> nodeIds) throws Exception{
		//获取该课程分类的所有节点课程分类
		List<ResCourseTypeBean> childTypes = getChildTypes(typeId);
		if(childTypes != null && childTypes.size() > 0){
			nodeIds.add(typeId);//自身节点也添加到列表中
			for (int i = 0; i < childTypes.size(); i++) {
				getChildTypeIds(childTypes.get(i).getId(),nodeIds);
			}
		}else{
			nodeIds.add(typeId);
		}
		return nodeIds;
	} 
	
	/**
	 * Method name: getChildTypes <BR>
	 * Description: 获取该分类下的直接子分类 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @return  List<ResCourseCategoryBean><BR>
	 */
	private List<ResCourseTypeBean> getChildTypes(Integer typeId) throws DataBaseException {
		return resCourseCategoryDaoMapper.getChildTypes(typeId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getMyCoursesCount(java.lang.Integer, java.lang.Integer, java.lang.String) <BR>
	 * Method name: getMyCoursesCount <BR>
	 * Description: 根据条件查询我的课程数目 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询条件
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getMyCoursesCount(Integer userId, Integer learnProcess,
			String searchContent) throws DataBaseException {
		//设置查询参数
		ResCourseVo resCourseVo = new ResCourseVo();
		if(searchContent != null && searchContent.trim() != ""){
			resCourseVo.setName(searchContent);
		}
		//总条数
		return resCourseDaoMapper.getMyCoursesCount(resCourseVo,userId,learnProcess);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getMyCourses(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getMyCourses <BR>
	 * Description: 根据条件获取我的课程（图片展现） <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询条件
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseVo> getMyCourses(Integer userId,
			Integer learnProcess, String searchContent, Integer fromNum,
			Integer pageSize) throws DataBaseException {
		//设置查询参数
		ResCourseVo resCourseVo = new ResCourseVo();
		if(searchContent != null && searchContent.trim() != ""){
			resCourseVo.setName(searchContent);
		}
		//查询我的课程
		List<ResCourseVo> courses = resCourseDaoMapper.getMyCourses(resCourseVo,userId,learnProcess,fromNum,pageSize);
		//遍历courses，将课程学习进度放入集合
		if(courses != null && courses.size() > 0){
			for (int i = 0; i < courses.size(); i++) {
				Integer courseId = courses.get(i).getId();
				//添加课程学习进度
				List<Integer> progresses = resCourseDaoMapper.getProgresses(courseId,userId);
				Integer courseWareSize = resCourseDaoMapper.getCourseWareSize(courseId);
				if(progresses != null && progresses.size() > 0){
					Integer totalProgress = 0;
					for (int j = 0; j < progresses.size(); j++) {
						totalProgress += progresses.get(j);
					}
					if(courseWareSize != 0){
						courses.get(i).setLearnProcessPercent(totalProgress/courseWareSize);
					}else{
						courses.get(i).setLearnProcessPercent(0);
					}
				}else{
					courses.get(i).setLearnProcessPercent(0);
				}
			}
		}
		//遍历结果，将评分改为保留一位小数
		/*for (int i = 0; i < courses.size(); i++) {
			courses.get(i).setAverageScore(CommonUtil.getOneDecimalNumber(courses.get(i).getAverageScore()));
		}*/
		return courses;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getPulianCourseCategorysByCompanyId(java.lang.Integer) <BR>
	 * Method name: getPulianCourseCategorysByCompanyId <BR>
	 * Description: 获取普联的课程体系 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseCategoryBean> getPulianCourseCategorysByCompanyId(
			Integer companyId) throws DataBaseException {
		return resCourseCategoryDaoMapper.getPulianCategorys(companyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getPulianCoursesCount(java.lang.Integer, java.lang.String, java.lang.String) <BR>
	 * Method name: getPulianCoursesCount <BR>
	 * Description: 获取普连课程数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询条件
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getPulianCoursesCount(Integer companyId,
			Integer searchCategoryId, String searchContent)throws DataBaseException {
		return resCourseDaoMapper.getPulianCoursesCount(companyId,searchCategoryId,searchContent);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getPulianCourses(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getPulianCourses <BR>
	 * Description: 获取普连课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询条件
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseVo> getPulianCourses(Integer companyId,
			Integer searchCategoryId, String searchContent, String sortName,
			String sortOrder, Integer fromNum, Integer pageSize)
			throws DataBaseException {
		List<ResCourseVo> courses = resCourseDaoMapper.getPulianCourses(companyId,searchCategoryId,searchContent,
				sortName,sortOrder,fromNum,pageSize);
		//遍历结果，将评分改为保留一位小数
		for (int i = 0; i < courses.size(); i++) {
			courses.get(i).setAverageScore(CommonUtil.getOneDecimalNumber(courses.get(i).getAverageScore()));
		}
		return courses;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getFirstTypes(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getFirstTypes <BR>
	 * Description: 获取一级课程体系和分类 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<CourseTypeVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CourseTypeVo> getFirstTypes(Integer companyId,Integer subCompanyId) throws Exception {
		return resCourseCategoryDaoMapper.getFirstTypes(companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getNextTypes(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getNextTypes <BR>
	 * Description: 获取二级课程体系（分类）和三级课程体系（分类）列表 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param categoryOrType 分类还是体系
	 * @return List<CourseNextTypeVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<CourseNextTypeVo> getNextTypes(Integer typeId,Integer categoryOrType) throws Exception {
		List<CourseNextTypeVo> nextTypes = null;
		if(categoryOrType == 1){
			//查询课程体系
			nextTypes = resCourseCategoryDaoMapper.getNextCategorys(typeId);
		}else if(categoryOrType == 2){
			//查询课程分类
			nextTypes = resCourseCategoryDaoMapper.getNextTypes(typeId);
		}
		return nextTypes;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#cancelStudy(java.lang.String, java.lang.String) <BR>
	 * Method name: cancelStudy <BR>
	 * Description: 取消学习课程 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelStudy(String courseId, String userId) throws Exception {
		//删除课程的学习进度
		courseStudyRecordDaoMapper.deleteRecord(courseId,userId);
		//删除课件和章节测试的学习进度
		courseWareRecordDaoMapper.deleteRecord(courseId,userId);
		courseExamRecordDaoMapper.deleteRecord(courseId,userId);
		//删除发布的笔记和问题
		courseNoteDaoMapper.deleteRecord(courseId,userId);
		List<Integer> questionIds = courseQuestionTopicDaoMapper.getNotAnsweredQuestionIds(courseId,userId);
		if(questionIds != null && questionIds.size() > 0){
			courseQuestionTopicDaoMapper.deleteRecord(questionIds);
		}
		//删除评分和评论
		courseEvaluationDaoMapper.deleteRecord(courseId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getMyCoursesForList(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getMyCoursesForList <BR>
	 * Description: 获取我的课程（列表展现） <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询条件
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws Exception 
	 */
	@Override
	public List<ResCourseVo> getMyCoursesForList(Integer userId,Integer learnProcess, String searchContent,
			 Integer fromNum,Integer pageSize) throws Exception {
		List<ResCourseVo> courses = resCourseDaoMapper.getMyCoursesNew(userId,learnProcess,searchContent,fromNum,pageSize);
		if(courses != null && courses.size() > 0){
			//遍历courses，将学习时长、课程学习进度和任务类型（课程来源）放入列表
			for (int i = 0; i < courses.size(); i++) {
				Integer courseId = courses.get(i).getId();
				//添加学习时长
				String[] studiedDurationArr = courseDetailService.getCourseStudyedDuration(courseId, userId);
				courses.get(i).setLearnDuration(CourseDurationUtil.getDurationStrFromArr(studiedDurationArr));
				//添加课程学习进度
				List<Integer> progresses = resCourseDaoMapper.getProgresses(courseId,userId);
				Integer courseWareSize = resCourseDaoMapper.getCourseWareSize(courseId);
				if(progresses != null && progresses.size() > 0){
					Integer totalProgress = 0;
					for (int j = 0; j < progresses.size(); j++) {
						totalProgress += progresses.get(j);
					}
					if(courseWareSize != 0){
						courses.get(i).setLearnProcessPercent(totalProgress/courseWareSize);
					}else{
						courses.get(i).setLearnProcessPercent(0);
					}
				}else{
					courses.get(i).setLearnProcessPercent(0);
				}
				//添加任务类型
				String sourceStr = getSourceStr(courseId,userId);
				courses.get(i).setCourseSource(sourceStr);
			}
		}
		return courses;
	}
	
	/**
	 * Method name: getSourceStr <BR>
	 * Description: 获取任务类型字符串 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @Param userId 用户id
	 * @return  String<BR>
	 * @throws Exception
	 */
	private String getSourceStr(Integer courseId,Integer userId) throws Exception {
		String returnStr = "";
		List<ResCourseOpenCrowdBean> openCrowds = resCourseDaoMapper.getOpenCrowdsByCourseId(courseId);
		if(openCrowds != null && openCrowds.size() > 0){
			StringBuffer buffer = new StringBuffer();
			boolean inPosts = false;
			boolean inGroups = false;
			boolean inDeparts = false;
			boolean selfChoose = false;
			for (int k = 0; k < openCrowds.size(); k++) {
				if(openCrowds.get(k).getOpenType() == 1){
					//自选课程
					selfChoose =true;
				}
				if(openCrowds.get(k).getOpenType() == 2){
					//岗位课程
					Integer postId = manageUserService.findUserById(String.valueOf(userId)).getPostId();
					if(openCrowds.get(k).getOpenCrowId() == postId){
						inPosts = true;
					}
				}
				if(openCrowds.get(k).getOpenType() == 3){
					//群组课程
					Integer count = manageGroupStudentDaoMapper.getCountByGroupIdUserId(openCrowds.get(k).getOpenCrowId(),userId);
					if(count > 0){
						inGroups = true;
					}
				}
				if(openCrowds.get(k).getOpenType() == 4){
					//部门课程
					Integer deptId = manageUserService.findUserById(String.valueOf(userId)).getDeptId();
					if(openCrowds.get(k).getOpenCrowId().equals(deptId)){
						inDeparts = true;
					}
				}
			}
			if(selfChoose){
				buffer.append(",1");
			}
			if(inPosts){
				buffer.append(",2");
			}
			if(inGroups){
				buffer.append(",3");
			}
			if(inDeparts){
				buffer.append(",4");
			}
			if(buffer.length() > 0){
				returnStr = buffer.toString().substring(1);
			}else{
				returnStr = "1";
			}
		}else{
			returnStr = "1";
		}
		return returnStr;
	}

	/**shenhaijun end*/
	
	
	/**zhangbocheng mobile start*/
	
	/** Method name: getRecommendCourse <BR>
	 * Description: 获取推荐课程 <BR>
	 * @param companyId
	 * @param uid 用户id
	 * @param top 显示几条
	 * @param type 为1时展示最新，2时展示最热
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseVo> getRecommendCourse(Integer companyId,
			Integer uid, Integer top,Integer type,Integer postId,Integer deptId,Integer subCompanyId)
			throws Exception {
		//查询用户所在群组id列表
				List<Integer> groupIds = manageGroupStudentDaoMapper.getGroupIdsByUserId(uid);
        //查询出父级（子公司id）列表
        List<Integer> subCompanyIds = getParentSubCompanyIds(companyId,subCompanyId);
		return resCourseDaoMapper.getRecommendCourse(companyId,uid,top,type,postId,deptId,groupIds,subCompanyId,subCompanyIds);
	}
	
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getNewCoursesCount(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getNewCoursesCount <BR>
	 * Description: 查询出最新创建的课程数目 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param lastDate 
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param deptId 部门id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getNewCoursesCount(
			Integer companyId,Integer subCompanyId,String lastDate,Integer userId,Integer postId,Integer deptId) throws DataBaseException{
		 //查询用户所在群组id列表
		List<Integer> groupIds = manageGroupStudentDaoMapper.getGroupIdsByUserId(userId);
        //查询出父级（子公司id）列表
        List<Integer> subCompanyIds = getParentSubCompanyIds(companyId,subCompanyId);
		return resCourseDaoMapper.getNewCoursesCount(companyId,subCompanyId,lastDate,postId,deptId,groupIds,subCompanyIds);
	}
	
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getSearchCourseList(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSearchCourseList <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param cateid
	 * @param name
	 * @param pageIndex
	 * @param pageSize
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param deptId 部门id
	 * @return
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseVo> getSearchCourseList(Integer companyId,Integer subCompanyId,Integer cateid,
			String name,Integer pageIndex,Integer pageSize,Integer userId,Integer postId,Integer deptId)throws DataBaseException {
		      //查询用户所在群组id列表
				List<Integer> groupIds = manageGroupStudentDaoMapper.getGroupIdsByUserId(userId);
        //查询出父级（子公司id）列表
        List<Integer> subCompanyIds = getParentSubCompanyIds(companyId,subCompanyId);
				//根据开放人群查询全部课程
		return resCourseDaoMapper.getSearchCourseList(companyId,subCompanyId,cateid,name,postId,groupIds,pageIndex,pageSize,1, deptId,subCompanyIds);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getSearchCourseName(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getSearchCourseName <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param cateid
	 * @param name
	 * @param top
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param deptId 部门id
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<ResCourseVo> getSearchCourseName(Integer companyId,Integer subCompanyId,Integer cateid,
			String name,Integer top,Integer userId,Integer postId,Integer deptId)throws DataBaseException {
		 //查询用户所在群组id列表
		List<Integer> groupIds = manageGroupStudentDaoMapper.getGroupIdsByUserId(userId);
        //查询出父级（子公司id）列表
        List<Integer> subCompanyIds = getParentSubCompanyIds(companyId,subCompanyId);
		//根据开放人群查询全部课程
		return resCourseDaoMapper.getSearchCourseList(companyId,subCompanyId,cateid,name,postId,groupIds,0,top,2,deptId,subCompanyIds);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#selectCourseByIdForMobile(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: selectCourseByIdForMobile <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param id 
	 * @param userId 用户id
	 * @return ResCourseVo
	 * @throws Exception  <BR>
	 */
	@Override
	public ResCourseVo selectCourseByIdForMobile(Integer id,Integer userId) throws Exception{
		return resCourseDaoMapper.selectCourseByIdForMobile(id,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getAllCourseWares(java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getAllCourseWares <BR>
	 * Description: 查询课程 包含所有的课件 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return List<SectionWareVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<SectionWareVo> getAllCourseWares(Integer sectionId,Integer userId) throws Exception{
		return resCourseDaoMapper.getAllCourseWares(sectionId, userId);
	}
	
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getMyCoursesForMobile(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getMyCoursesForMobile <BR>
	 * Description: 根据条件获取我的课程 （按照开始学习时间降序排序） <BR>
	 * Remark: <BR>
	 * @param name 课程名称
	 * @param categoryId 课程体系id
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	public List<ResCourseVo> getMyCoursesForMobile(String name,Integer categoryId,
			Integer userId,Integer learnProcess,
			Integer fromNum,Integer pageSize) throws DataBaseException{
		return resCourseDaoMapper.getMyCoursesForMobile(name, categoryId,userId, learnProcess, fromNum, pageSize);
	}
	
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentCourseService#getCollectCoursesForMobile(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCollectCoursesForMobile <BR>
	 * Description: 根据条件获取我收藏的课程 <BR>
	 * Remark: <BR>
	 * @param name 课程名称
	 * @param userId 用户id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	public List<ResCourseVo> getCollectCoursesForMobile(String name,Integer userId,
			Integer fromNum,Integer pageSize) throws DataBaseException{
		return resCourseDaoMapper.getCollectCoursesForMobile(name, userId, fromNum, pageSize);
	}
	/**zhangbocheng mobile end*/
	
}
