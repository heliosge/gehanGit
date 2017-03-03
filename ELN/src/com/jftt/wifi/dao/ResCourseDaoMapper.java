/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResCourseDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-21        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResCourseOpenCrowdBean;
import com.jftt.wifi.bean.ResCourseTypeBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionCoursewareBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.vo.CourseStudyVo;
import com.jftt.wifi.bean.vo.CourseVo;
import com.jftt.wifi.bean.vo.CourseVoForPost;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ResCourseDaoMapper <BR>
 * class description: 课程dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-21
 * @author JFTT)ShenHaijun
 */
public interface ResCourseDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ResCourseBean<BR>
	 */
	public ResCourseBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCoursesByConditions <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @param resCourseVo
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getCoursesByConditions(
			@Param("resCourseVo")ResCourseVo resCourseVo,
			@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId,
			@Param("sortName")String sortName,@Param("sortOrder")String sortOrder,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getCoursesByOpenCrowd <BR>
	 * Description: 根据开放人群查询全部课程 <BR>
	 * Remark: <BR>
	 * @param resCourseVo
	 * @param companyId
	 * @param subCompanyId
	 * @param userId
	 * @param postId
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getCoursesByOpenCrowd(
			@Param("resCourseVo")ResCourseVo resCourseVo,
			@Param("companyId")Integer companyId, 
			@Param("subCompanyId")Integer subCompanyId,
			@Param("postId")Integer postId,
			@Param("groupIds")List<Integer> groupIds,
			@Param("sortName")String sortName,
			@Param("sortOrder")String sortOrder,
			@Param("fromNum")Integer fromNum,
			@Param("pageSize")Integer pageSize,
			@Param("deptId")String deptId,
			@Param("subCompanyIds")List<Integer> subCompanyIds) throws DataBaseException;
	
	/**
	 * Method name: getCoursesCount <BR>
	 * Description: 根据条件查询课程数目 <BR>
	 * Remark: <BR>
	 * @param resCourseVo
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCoursesCount(
			@Param("resCourseVo")ResCourseVo resCourseVo, 
			@Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseCountByOpenCrowd <BR>
	 * Description: 根据开放人群查询课程数量 <BR>
	 * Remark: <BR>
	 * @param resCourseVo
	 * @param companyId
	 * @param subCompanyId
	 * @param userId
	 * @param postId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCourseCountByOpenCrowd(
			@Param("resCourseVo")ResCourseVo resCourseVo,
			@Param("companyId")Integer companyId, 
			@Param("subCompanyId")Integer subCompanyId,
			@Param("postId")Integer postId,
			@Param("groupIds")List<Integer> groupIds,
			@Param("deptId")String deptId,
			@Param("subCompanyIds")List<Integer> subCompanyIds) throws DataBaseException;
	
	/**
	 * Method name: getMyCoursesCount <BR>
	 * Description: 根据条件获取我的课程数目 <BR>
	 * Remark: <BR>
	 * @param resCourseVo
	 * @param userId
	 * @param learnProcess
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getMyCoursesCount(@Param("resCourseVo")ResCourseVo resCourseVo,
			@Param("userId")Integer userId,@Param("learnProcess")Integer learnProcess) throws DataBaseException;
	
	/**
	 * Method name: getMyCourses <BR>
	 * Description: 根据条件获取我的课程 （按照开始学习时间降序排序）<BR>
	 * Remark: <BR>
	 * @param ResCourseVo
	 * @param userId
	 * @param learnProcess
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getMyCourses(@Param("resCourseVo")ResCourseVo resCourseVo,
			@Param("userId")Integer userId,@Param("learnProcess")Integer learnProcess,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getMyCoursesNew <BR>
	 * Description: 获取我的课程（新） <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param learnProcess
	 * @param searchContent
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getMyCoursesNew(
			@Param("userId")Integer userId,
			@Param("learnProcess")Integer learnProcess,
			@Param("searchContent")String searchContent,
			@Param("fromNum")Integer fromNum,
			@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getProgresses <BR>
	 * Description: 获取该学员该课程的所有课件学习进度 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<Integer><BR>
	 */
	public List<Integer> getProgresses(
			@Param("courseId")Integer courseId,
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getCourseWareSize <BR>
	 * Description: 获取该课程课件的数量 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCourseWareSize(@Param("courseId")Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getOpenCrowdsByCourseId <BR>
	 * Description: 根据课程id查询开放类型 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  List<ResCourseOpenCrowdBean><BR>
	 */
	public List<ResCourseOpenCrowdBean> getOpenCrowdsByCourseId(
			@Param("courseId")Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getCourseCountByCompanyId <BR>
	 * Description: 获取公司的课程数量 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @return  Integer<BR>
	 */
	public Integer getCourseCountByCompanyId(@Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId,@Param("deleteStatus")Integer deleteStatus);
	
	/**
	 * Method name: getMyStudyHours <BR>
	 * Description: 获取我的学时 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getMyStudyHours(@Param("userId")Integer userId,
			@Param("learnProcess")Integer learnProcess) throws DataBaseException;
	
	/**
	 * Method name: getFeatureCourses <BR>
	 * Description: 获取精选课程 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @param isFeatured
	 * @param status
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getFeatureCourses(
			@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId, 
			@Param("isFeatured")Integer isFeatured,@Param("status")Integer status) throws DataBaseException;
	
	/**
	 * Method name: getCourseDetailForStudy <BR>
	 * Description: 获取课程学习页面课程详情 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  CourseStudyVo<BR>
	 */
	public CourseStudyVo getCourseDetailForStudy(@Param("courseId")Integer courseId,@Param("sectionId")Integer sectionId,@Param("courseWareId")Integer courseWareId) throws DataBaseException;
	
	/**
	 * Method name: getPostCoursesCount <BR>
	 * Description: 获取岗位课程数量 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getPostCoursesCount(@Param("postId")Integer postId) throws DataBaseException;
	
	/**
	 * Method name: getPostCourses <BR>
	 * Description: 获取岗位所有课程 <BR>
	 * Remark: <BR>
	 * @param postId
	 * @param userId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<CourseVoForPost><BR>
	 */
	public List<CourseVoForPost> getPostCourses(@Param("postId")Integer postId, @Param("userId")Integer userId,
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getPulianCoursesCount <BR>
	 * Description: 获取普连课程数量  <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param searchCategoryId
	 * @param searchContent
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getPulianCoursesCount(@Param("companyId")Integer companyId,
			@Param("searchCategoryId")Integer searchCategoryId, @Param("searchContent")String searchContent) throws DataBaseException;
	
	/**
	 * Method name: getPulianCourses <BR>
	 * Description: 获取普连课程 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param searchCategoryId
	 * @param searchContent
	 * @param sortName
	 * @param sortOrder
	 * @param fromNum
	 * @param pageSize
	 * @return  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getPulianCourses(@Param("companyId")Integer companyId,
			@Param("searchCategoryId")Integer searchCategoryId, @Param("searchContent")String searchContent, @Param("sortName")String sortName,
			@Param("sortOrder")String sortOrder, @Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**shenhaijun end*/
	
	
	/**luyunlong start*/
	
	
	/**
	 * Method name: selectCourseById <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public ResCourseBean selectCourseById(@Param("id")Integer id) throws Exception;
	
	/**
	 * Method name: spreadResCourse <BR>
	 * Description: 推广课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void spreadResCourse(@Param("id")String id) throws Exception;
	
	/**
	 * Method name: cancelSpreadResCourse <BR>
	 * Description: 取消推广课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void cancelSpreadResCourse(@Param("id")String id) throws Exception;

	/**
	 * Method name: insertCourse <BR>
	 * Description: 新增课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void insertCourse(ResCourseBean bean) throws Exception;

	/**
	 * Method name: updateCourse <BR>
	 * Description: 修改课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateCourse(ResCourseBean bean) throws Exception;

	/**
	 * Method name: deleteCourse <BR>
	 * Description: 删除课程 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteCourse(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: insertCourseAndOpenCrowd <BR>
	 * Description: 新增课程开放人群 <BR>
	 * Remark: <BR>
	 * @param param  void<BR>
	 */
	public void insertCourseAndOpenCrowd(Map<String, Object> param) throws Exception;

	/**
	 * Method name: deleteCourseAndOpenCrowd <BR>
	 * Description: 删除课程开放人群  <BR>
	 * Remark: <BR>
	 * @param courseId  void<BR>
	 */
	public void deleteCourseAndOpenCrowd(@Param("courseId")Integer courseId) throws Exception;

	/**
	 * Method name: releaseCourse <BR>
	 * Description: 发布课程 <BR>
	 * Remark: <BR>
	 * @param courseId  void<BR>
	 */
	public void releaseCourse(@Param("id")Integer courseId) throws Exception;

	/**
	 * Method name: cancelReleaseCourse <BR>
	 * Description: 取消发布课程 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @throws Exception  void<BR>
	 */
	public void cancelReleaseCourse(@Param("id")Integer courseId) throws Exception;
	/**
	 * Method name: shareCourse <BR>
	 * Description: 共享课程 <BR>
	 * Remark: <BR>
	 * @param param  void<BR>
	 */
	public void shareCourse(Map<String, Object> param) throws Exception;

	/**
	 * Method name: insertResSection <BR>
	 * Description: 新增章节 <BR>
	 * Remark: <BR>
	 * @param section  void<BR>
	 */
	public Integer insertSection(ResSectionBean section) throws Exception;

	/**
	 * Method name: insertSectionCourseware <BR>
	 * Description: 新增章节、课件的关系 <BR>
	 * Remark: <BR>
	 * @param voBean
	 * @throws Exception  void<BR>
	 */
	public void insertSectionCourseware(ResSectionCoursewareBean voBean) throws Exception;

	/**
	 * Method name: insertSectionExam <BR>
	 * Description: 新增章节、试卷的关系 <BR>
	 * Remark: <BR>
	 * @param voBean
	 * @throws Exception  void<BR>
	 */
	public void insertSectionExam(ResSectionExamBean voBean) throws Exception;

	/**
	 * Method name: deleteSection <BR>
	 * Description: 删除章节 <BR>
	 * Remark: <BR>
	 * @param sectionId  void<BR>
	 */
	public void deleteSection(ResSectionBean bean);

	/**
	 * Method name: deleteSectionCourseware <BR>
	 * Description: 删除章节、课件关系 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void deleteSectionCourseware(ResSectionCoursewareBean bean);

	/**
	 * Method name: deleteSectionExam <BR>
	 * Description: 删除章节、试卷关系 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void deleteSectionExam(ResSectionExamBean bean);
	
	/**
	 * Method name: deleteSectionExamByCourseId <BR>
	 * Description: 根据课程id删除章节、考试关系 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteSectionExamByCourseId(@Param("courseId")Integer id);

	/**
	 * Method name: deleteSectionCoursewareByCourseId <BR>
	 * Description: 根据课程id删除章节、课件关系 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteSectionCoursewareByCourseId(@Param("courseId")Integer id);
	
	/**
	 * Method name: selectCourseCategory <BR>
	 * Description: 查询【课程体系】 <BR>
	 * Remark: <BR>
	 * @param param 
	 * @return  List<ResCourseCategoryBean><BR>
	 */
	public List<ResCourseCategoryBean> selectCourseCategory(Map<String, Object> param) throws Exception;

	/**
	 * Method name: deleteCourseCategory <BR>
	 * Description: 删除【课程体系】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public void deleteCourseCategory(@Param("id")int parseInt) throws Exception;

	/**
	 * Method name: updateCourseCategory <BR>
	 * Description: 修改【课程体系】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateCourseCategory(ResCourseCategoryBean record) throws Exception;

	/**
	 * Method name: insertCourseCategory <BR>
	 * Description: 添加【课程体系】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void insertCourseCategory(ResCourseCategoryBean record) throws Exception;

	/**
	 * Method name: selectCourseType <BR>
	 * Description: 查询【课程分类】 <BR>
	 * Remark: <BR>
	 * @return  List<ResCourseTypeBean><BR>
	 */
	public List<ResCourseTypeBean> selectCourseType(Map<String, Object> param) throws Exception;

	/**
	 * Method name: deleteCourseType <BR>
	 * Description: 删除【课程分类】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public void deleteCourseType(@Param("id")int parseInt) throws Exception;

	/**
	 * Method name: updateCourseType <BR>
	 * Description: 修改【课程分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateCourseType(ResCourseTypeBean record) throws Exception;

	/**
	 * Method name: insertCourseType <BR>
	 * Description: 添加【课程分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void insertCourseType(ResCourseTypeBean record) throws Exception;

	/**
	 * Method name: selectCourseList <BR>
	 * Description: selectCourseList <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<ResCourseBean><BR>
	 */
	public List<ResCourseBean> selectCourseList(CourseVo vo) throws Exception;

	/**
	 * Method name: selectCourseListCount <BR>
	 * Description: selectCourseListCount <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectCourseListCount(CourseVo vo) throws Exception;

	/**
	 * Method name: changeCategory <BR>
	 * Description: 更改课程体系 <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void changeCategory(Map<String, Object> map) throws Exception;

	/**
	 * Method name: selectSectionByCourseId <BR>
	 * Description: 获得章节列表 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return  List<Integer><BR>
	 */
	public List<ResSectionBean> selectSectionByCourseId(@Param("courseId")String courseId) throws Exception;

	/**
	 * Method name: updateSection <BR>
	 * Description: 修改章节 <BR>
	 * Remark: <BR>
	 * @param section
	 * @throws Exception  void<BR>
	 */
	public void updateSection(ResSectionBean section) throws Exception;

	/**
	 * Method name: selectOamCourseByTopic <BR>
	 * Description: 获取专题推广课程 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws Exception  List<ResCourseBean><BR>
	 */
	public List<ResCourseBean> selectOamCourseByTopic(@Param("topicId")String topicId) throws Exception;

	/**
	 * Method name: insertCourseType <BR>
	 * Description: 修改课程分类 <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void changeCourseType(Map<String, Object> map);

	/**
	 * Method name: featurAndUnFeaturResCourse <BR>
	 * Description:  精选、取消精选课程 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void featurAndUnFeaturResCourse(ResCourseBean bean) throws Exception;

	/**
	 * Method name: selectShareResCourseList <BR>
	 * Description: 查询共享课程 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ResCourseBean><BR>
	 */
	public List<ResCourseBean> selectShareResCourseList(CourseVo vo) throws Exception;
	
	/**
	 * Method name: selectCourseTypeByCourseId <BR>
	 * Description: 根据课程id获取课程分类 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  List<Map<String,Object>><BR>
	 */
	public List<Map<String, Object>> selectCourseTypeByCourseId(
			Map<String, Object> map);

	/**
	 * Method name: deleteCourseType <BR>
	 * Description: 根据课程id删除课程分类 <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void deleteCourseTypeByCourseId(Map<String, Object> map);

	/**luyunlong end*/
	
	/**zhangbocheng post start*/

	/**
	 * Method name: getStudentNumById <BR>
	 * Description: 根据课程id查询学习人数 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws Exception  <BR>
	 */
	public Integer getStudentNumById(@Param("courseId")Integer courseId) throws Exception;
	/**
	 * Method name: selectCourseList <BR>
	 * Description: selectCourseList <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<ResCourseBean><BR>
	 */
	public List<ResCourseBean> selectCourseListByPostId(CourseVo vo) throws Exception;

	/**
	 * Method name: selectCourseListCount <BR>
	 * Description: selectCourseListCount <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  int<BR>
	 */
	public int selectCourseListCountByPostId(CourseVo vo) throws Exception;
	
	/**zhangbocheng post end*/
	
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
	public List<ResCourseVo> getRecommendCourse(@Param("companyId")Integer companyId,
			@Param("userId")Integer uid, @Param("top")Integer top,@Param("type")Integer type,
			@Param("postId")Integer postId,@Param("deptId")Integer deptId,@Param("groupIds")List<Integer> groupIds,@Param("subCompanyId")Integer subCompanyId,@Param("subCompanyIds")List<Integer> subCompanyIds)
			throws Exception;
	

	
	/**
	 * Method name: getNewCoursesCount <BR>
	 * Description: 查询出最新创建的课程数目 <BR>
	 * Remark: <BR>
	 * @param lastDate
	 * @param companyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getNewCoursesCount(
			@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId,@Param("lastDate")String lastDate,
			@Param("postId")Integer postId,@Param("deptId")Integer deptId,@Param("groupIds")List<Integer> groupIds,@Param("subCompanyIds")List<Integer> subCompanyIds) throws DataBaseException;
	
	
	/**
	 * Method name: getSearchCourseList <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws DataBaseException  <BR>
	 */
	public List<ResCourseVo> getSearchCourseList(@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId,@Param("categoryId")Integer cateid,
			@Param("name")String name,
			@Param("postId")Integer postId,@Param("groupIds")List<Integer> groupIds,@Param("page")Integer pageIndex,@Param("pageSize")Integer pageSize,@Param("type")Integer type,@Param("deptId")Integer deptId,@Param("subCompanyIds")List<Integer> subCompanyIds)throws DataBaseException ;
	
	
	/**
	 * Method name: getMyCoursesForMobile <BR>
	 * Description: 根据条件获取我的课程 （按照开始学习时间降序排序）<BR>
	 * Remark: <BR>
	 * @param ResCourseVo
	 * @param userId
	 * @param learnProcess
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getMyCoursesForMobile(@Param("name")String name,@Param("categoryId")Integer categoryId,
			@Param("userId")Integer userId,@Param("learnProcess")Integer learnProcess,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	
	/**
	 * Method name: getCollectCoursesForMobile <BR>
	 * Description: 根据条件获取我收藏的课程 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param userId
	 * @param learnProcess
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getCollectCoursesForMobile(@Param("courseName")String name,@Param("userId")Integer userId,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: selectCourseByIdForMobile <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	 */
	public ResCourseVo selectCourseByIdForMobile(@Param("id")Integer id,@Param("userId")Integer userId) throws Exception;
	
	
	/**
	 * Method name: getAllCourseWares <BR>
	 * Description: 查询课程 包含所有的课件<BR>
	 * Remark: <BR>
	 * @param courseId
	 * @throws Exception  <BR>
	 */
	public List<SectionWareVo> getAllCourseWares(@Param("courseId")Integer courseId,@Param("userId")Integer userId) throws Exception;



	/**zhangbocheng mobile end*/
	
	
	
	/**chenrui start**/
	/**
	 * 商城购买过后的课程同步
	 * Method name: courseSynchronization <BR>
	 * Description: courseSynchronization <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @throws Exception  void<BR>
	 */
	public void courseSynchronization(ResCourseBean courseBean) throws Exception;

	/**
	 *  判断当前课程该用户所在公司是否已购买
	 * Method name: judgeIsBuy <BR>
	 * Description: judgeIsBuy <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return  int<BR>
	 */
	public int judgeIsBuy(@Param("courseId")String courseId, @Param("companyId")int companyId, @Param("subCompanyId")int subCompanyId) throws Exception;

	/**chenrui end**/
}
