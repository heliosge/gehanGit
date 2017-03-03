/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentCourseService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-21        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;







import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.vo.CourseNextTypeVo;
import com.jftt.wifi.bean.vo.CourseTypeVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:StudentCourseService <BR>
 * class description: 学员课程service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-21
 * @author JFTT)ShenHaijun
 */
public interface StudentCourseService {
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCourseCategorysByCompanyId <BR>
	 * Description: 根据公司id查询所有课程体系 （根节点课程体系） <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException <BR>
	 */
	List<ResCourseCategoryBean> getCourseCategorysByCompanyId(Integer companyId,Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取课程体系的子课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId 课程体系id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException <BR>
	 */
	List<ResCourseCategoryBean> getChildCategorys(Integer categoryId) throws DataBaseException;
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId 课程体系id
	 * @return List<ResCourseCategoryBean>
	 * @throws DataBaseException <BR>
	 */
	List<ResCourseCategoryBean> getUpCategorys(Integer categoryId) throws DataBaseException;
	
	/**
	 * Method name: getCoursesByConditions <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @param resCourseVo 查询条件vo
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
	 * @throws Exception <BR>
	 */
	List<ResCourseVo> getCoursesByConditions(ResCourseVo resCourseVo,
			Integer companyId,Integer subCompanyId,Integer userId,Integer postId,
			String sortName,String sortOrder,
			Integer fromNum,Integer pageSize,String deptId) throws Exception;
	
	/**
	 * Method name: getCoursesCount <BR>
	 * Description: 根据条件查询课程总条数 <BR>
	 * Remark: <BR>
	 * @param resCourseVo 查询条件vo
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param deptId 部门id
	 * @return Integer
	 * @throws Exception <BR>
	 */
	Integer getCoursesCount(ResCourseVo resCourseVo, Integer companyId,
			Integer subCompanyId,Integer userId,Integer postId,String deptId) throws Exception;
	
	/**
	 * Method name: getMyCoursesCount <BR>
	 * Description: 根据条件查询我的课程数目 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询内容
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getMyCoursesCount(Integer userId, Integer learnProcess,
			String searchContent) throws DataBaseException;
	
	/**
	 * Method name: getMyCourses <BR>
	 * Description: 根据条件获取我的课程 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询内容
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException <BR>
	 */
	List<ResCourseVo> getMyCourses(Integer userId, Integer learnProcess,
			String searchContent, Integer fromNum, Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getPulianCourseCategorysByCompanyId <BR>
	 * Description: 获取普联的课程体系  <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @return  List<ResCourseCategoryBean><BR>
	 */
	List<ResCourseCategoryBean> getPulianCourseCategorysByCompanyId(
			Integer companyId) throws DataBaseException;
	
	/**
	 * Method name: getPulianCoursesCount <BR>
	 * Description: 获取普连课程数量 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询内容
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getPulianCoursesCount(Integer companyId, Integer searchCategoryId,
			String searchContent) throws DataBaseException;
	
	/**
	 * Method name: getPulianCourses <BR>
	 * Description: 获取普连课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException <BR>
	 */
	List<ResCourseVo> getPulianCourses(Integer companyId,Integer searchCategoryId, String searchContent, 
			String sortName, String sortOrder, Integer fromNum, Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getFirstTypes <BR>
	 * Description: 获取一级课程体系和分类 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return List<CourseTypeVo>
	 * @throws Exception <BR>
	 */
	List<CourseTypeVo> getFirstTypes(Integer companyId, Integer subCompanyId) throws Exception;
	
	/**
	 * Method name: getNextTypes <BR>
	 * Description: 获取二级课程体系（分类）和三级课程体系（分类）列表 <BR>
	 * Remark: <BR>
	 * @param typeId 分类id
	 * @param categoryOrType 分类还是体系
	 * @return List<CourseNextTypeVo>
	 * @throws Exception <BR>
	 */
	List<CourseNextTypeVo> getNextTypes(Integer typeId, Integer categoryOrType) throws Exception;
	
	/**
	 * Method name: cancelStudy <BR>
	 * Description: 取消学习课程 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @throws Exception  void<BR>
	 */
	void cancelStudy(String courseId, String userId) throws Exception;
	
	/**
	 * Method name: getMyCoursesForList <BR>
	 * Description: 获取我的课程（列表展现） <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询内容
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws Exception <BR>
	 */
	List<ResCourseVo> getMyCoursesForList(Integer userId, Integer learnProcess,String searchContent,
			 Integer fromNum, Integer pageSize) throws Exception;
	
	/**shenhaijun end*/
	
	
	/**zhangbocheng mobile start*/
	/** Method name: getRecommendCourse <BR>
	 * Description: 获取推荐课程 <BR>
	 * @param companyId
	 * @param uid 用户id
	 * @param top 显示几条
	 * @param type 为1时展示最新，2时展示最热
	 * Remark: <BR>
	 * @return List<ResCourseVo>
	 * @throws DataBaseException  <BR>
	 */
	public List<ResCourseVo> getRecommendCourse(Integer companyId,
			Integer uid, Integer top,Integer type,Integer postId,Integer deptId,Integer subCompanyId)
			throws Exception;
	
	
	/**
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
	 * @throws DataBaseException <BR>
	 */
	public Integer getNewCoursesCount(
			Integer companyId,Integer subCompanyId,String lastDate,Integer userId,Integer postId,Integer deptId) throws DataBaseException;
	
	
	/**
	 * Method name: getSearchCourseList <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param cateid
	 * @param name
	 * @param pageIndex
	 * @param pageSize 每页大小
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param deptId 部门id
	 * @return List<ResCourseVo>
	 * @throws DataBaseException <BR>
	 */
	public List<ResCourseVo> getSearchCourseList(Integer companyId,Integer subCompanyId,Integer cateid,
			String name,Integer pageIndex,Integer pageSize,Integer userId,Integer postId,Integer deptId)throws DataBaseException ;
	
	
	/**
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
	 * @return
	 * @throws DataBaseException  List<ResCourseVo><BR>
	 */
	public List<ResCourseVo> getSearchCourseName(Integer companyId,Integer subCompanyId,Integer cateid,
			String name,Integer top,Integer userId,Integer postId,Integer deptId)throws DataBaseException ;
	
	/**
	 * Method name: selectCourseByIdForMobile <BR>
	 * Description: 根据id查询课程 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param userId 用户id
	 * @return ResCourseVo
	 * @throws Exception <BR>
	 */
	public ResCourseVo selectCourseByIdForMobile(Integer id,Integer userId) throws Exception;
	
	
	
	/**
	 * Method name: getAllCourseWares <BR>
	 * Description: 查询课程 包含所有的课件 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return List<SectionWareVo>
	 * @throws Exception <BR>
	 */
	public List<SectionWareVo> getAllCourseWares(Integer courseId,Integer userId) throws Exception;
	
	
	
	/**
	 * Method name: getMyCoursesForMobile <BR>
	 * Description: 根据条件获取我的课程 （按照开始学习时间降序排序） <BR>
	 * Remark: <BR>
	 * @param name 课程名称
	 * @param categoryId 课程体系
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException <BR>
	 */
	public List<ResCourseVo> getMyCoursesForMobile(String name,Integer categoryId,
			Integer userId,Integer learnProcess,
			Integer fromNum,Integer pageSize) throws DataBaseException;
	
	
	
	/**
	 * Method name: getCollectCoursesForMobile <BR>
	 * Description: 根据条件获取我收藏的课程 <BR>
	 * Remark: <BR>
	 * @param name 课程名称
	 * @param userId 用户id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ResCourseVo>
	 * @throws DataBaseException <BR>
	 */
	public List<ResCourseVo> getCollectCoursesForMobile(String name,Integer userId,
			Integer fromNum,Integer pageSize) throws DataBaseException;
	
	/**zhangbocheng mobile end*/
	
}
