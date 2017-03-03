/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseUserAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.CourseNextTypeVo;
import com.jftt.wifi.bean.vo.CourseTypeVo;
import com.jftt.wifi.bean.vo.ResCourseVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.StudentCourseService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:CourseUserAction <BR>
 * class description: 课程用户action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("courseUserAction")
public class CourseUserAction {
	private static Logger logger = Logger.getLogger(CourseUserAction.class);
	
	@Resource(name="studentCourseService")
	private StudentCourseService studentCourseService;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: toAllCourses <BR>
	 * Description: 跳转到全部课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toAllCourses")
	public String toAllCourses(HttpServletRequest request){
		return "studentCourse/allCourses";
	}
	
	/**
	 * Method name: getCourseCategorys <BR>
	 * Description: 查询该公司的所有课程体系 （根节点课程体系） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseCategorys")
	@ResponseBody
	public Object getCourseCategorys(HttpServletRequest request,Integer companyId,Integer subCompanyId){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		if(companyId != null){
			try {
				logger.info("开始查询公司id为"+companyId+"的所有课程体系");
				List<ResCourseCategoryBean> courseCategorys = studentCourseService.getCourseCategorysByCompanyId(companyId,subCompanyId);
				arv.setRtnData(courseCategorys);
				logger.info("查询公司id为"+companyId+"所有课程体系结束");
			} catch (DataBaseException e) {
				arv.setRtnResult(Constant.AJAX_FAIL);
				logger.warn(CourseUserAction.class,e);
			}
		}
		return arv;
	}
	
	/**
	 * Method name: getCoursePulianCategorys <BR>
	 * Description: 获取普联的课程体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCoursePulianCategorys")
	@ResponseBody
	public Object getCoursePulianCategorys(HttpServletRequest request,Integer companyId){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		if(companyId != null){
			try {
				logger.info("开始查询公司id为"+companyId+"的所有课程体系");
				List<ResCourseCategoryBean> courseCategorys = studentCourseService.getPulianCourseCategorysByCompanyId(companyId);
				arv.setRtnData(courseCategorys);
				logger.info("查询公司id为"+companyId+"所有课程体系结束");
			} catch (DataBaseException e) {
				arv.setRtnResult(Constant.AJAX_FAIL);
				logger.warn(CourseUserAction.class,e);
			}
		}
		return arv;
	}
	
	/**
	 * Method name: getChildCategorys <BR>
	 * Description: 获取课程体系的子课程体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId 课程体系id
	 * @return  Object<BR>
	 */
	@RequestMapping("getChildCategorys")
	@ResponseBody
	public Object getChildCategorys(HttpServletRequest request,Integer categoryId){
		try {
			logger.info("查询课程体系"+categoryId+"的子课程体系");
			List<ResCourseCategoryBean> childCategorys = studentCourseService.getChildCategorys(categoryId);
			logger.info("查询课程体系"+categoryId+"的子课程体系完毕");
			return childCategorys;
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有课程体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId 课程体系id
	 * @return  Object<BR>
	 */
	@RequestMapping("getUpCategorys")
	@ResponseBody
	public Object getUpCategorys(HttpServletRequest request,Integer categoryId){
		try {
			logger.info("查询课程体系"+categoryId+"的上一级所有课程体系");
			List<ResCourseCategoryBean> upCategorys = studentCourseService.getUpCategorys(categoryId);
			logger.info("查询课程体系"+categoryId+"的上一级所有课程体系完毕");
			return upCategorys;
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class,e);
		}
		return null;
	} 
	
	/**
	 * Method name: getCoursesByConditions <BR>
	 * Description: 根据条件查询课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param searchCategoryId 课程体系id
	 * @param searchCategoryType 课程体系还是课程分类
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @param deptId 部门id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCoursesByConditions")
	@ResponseBody
	public Object getCoursesByConditions(HttpServletRequest request,
			String companyId,String subCompanyId,Integer userId,Integer postId,
			String searchCategoryId, String searchCategoryType, String searchContent,
			String sortName,String sortOrder,
			String page,String pageSize,String deptId){
		//设置查询参数
		ResCourseVo resCourseVo = new ResCourseVo();
		if(searchCategoryId != null && !("".equals(searchCategoryId))){
			resCourseVo.setCategoryId(Integer.parseInt(searchCategoryId));
		}
		if(searchCategoryType != null && !("".equals(searchCategoryType))){
			resCourseVo.setCategoryOrType(Integer.parseInt(searchCategoryType));
		}
		if(searchContent != null && !("".equals(searchContent))){
			resCourseVo.setName(searchContent);
		}
		//注意page是从0开始的
		Integer fromNum = Integer.parseInt(page)*Integer.parseInt(pageSize);
		//根据条件查询
		try {
			logger.info("根据条件查询公司"+companyId+"子公司"+subCompanyId+"课程");
			List<ResCourseVo> courses = studentCourseService.getCoursesByConditions(
					resCourseVo,Integer.parseInt(companyId),Integer.parseInt(subCompanyId),
					userId,postId,sortName,sortOrder,fromNum,Integer.parseInt(pageSize),deptId);
			logger.info("根据条件查询课程结束");
			return courses;
		} catch (Exception e) {
			logger.warn(CourseUserAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getPulianCoursesForPics <BR>
	 * Description: 获取普连课程（图标展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getPulianCoursesForPics")
	@ResponseBody
	public Object getPulianCoursesForPics(HttpServletRequest request,Integer companyId,
			Integer searchCategoryId,String searchContent,
			String sortName,String sortOrder,Integer page,Integer pageSize){
		//注意page是从0开始的
		Integer fromNum = page * pageSize;
		//根据条件查询
		try {
			logger.info("根据条件查询公司"+companyId+"课程");
			List<ResCourseVo> courses = studentCourseService.getPulianCourses(
					companyId,searchCategoryId,searchContent,sortName,sortOrder,fromNum,pageSize);
			logger.info("根据条件查询课程结束");
			return courses;
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getSearchResulltsCount <BR>
	 * Description: 根据条件查询课程数目 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param searchCategoryId 课程体系id
	 * @param searchCategoryType 课程体系还是课程分类
	 * @param searchContent 查询内容
	 * @param deptId 部门id
	 * @return  Integer<BR>
	 */
	@RequestMapping("getSearchResulltsCount")
	@ResponseBody
	public Integer getSearchResulltsCount(HttpServletRequest request,
			Integer companyId,Integer subCompanyId,Integer userId,Integer postId,
			String searchCategoryId,String searchCategoryType,String searchContent,String deptId){
		//设置查询参数
		ResCourseVo resCourseVo = new ResCourseVo();
		if(searchCategoryId != null && !("".equals(searchCategoryId))){
			resCourseVo.setCategoryId(Integer.parseInt(searchCategoryId));
		}
		if(searchCategoryType != null && !("".equals(searchCategoryType))){
			resCourseVo.setCategoryOrType(Integer.parseInt(searchCategoryType));
		}
		if(searchContent != null && !("".equals(searchContent))){
			resCourseVo.setName(searchContent);
		}
		//查询总条数
		Integer coursesCount = 0;
		try {
			logger.info("查询公司"+companyId+"子公司"+subCompanyId+"课程总数目");
			coursesCount = studentCourseService.getCoursesCount(
					resCourseVo,companyId,subCompanyId,userId,postId,deptId);
			logger.info("查询结果总数目为"+coursesCount);
		} catch (Exception e) {
			logger.warn(CourseUserAction.class,e);
		}
		return coursesCount;
	}
	
	/**
	 * Method name: getPulianCoursesCount <BR>
	 * Description: 根据条件获取普连课程数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询内容
	 * @return  Integer<BR>
	 */
	@RequestMapping("getPulianCoursesCount")
	@ResponseBody
	public Integer getPulianCoursesCount(HttpServletRequest request,
			Integer companyId,Integer searchCategoryId,String searchContent){
		//查询总条数
		Integer coursesCount = 0;
		try {
			logger.info("查询公司"+companyId+"课程总数目");
			coursesCount = studentCourseService.getPulianCoursesCount(companyId,searchCategoryId,searchContent);
			logger.info("查询结果总数目为"+coursesCount);
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class,e);
		}
		return coursesCount;
	} 
	
	/**
	 * Method name: getCoursesForMMGrid <BR>
	 * Description: 获取课程mmGrid数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param userId 用户id
	 * @param postId 岗位id
	 * @param searchCategoryId 课程体系id
	 * @param searchCategoryType 课程体系还是课程分类
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @param deptId 部门id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCoursesForMMGrid")
	@ResponseBody
	public Object getCoursesForMMGrid(HttpServletRequest request,
			Integer companyId,Integer subCompanyId,Integer userId,Integer postId,
			String searchCategoryId,String searchCategoryType,String searchContent,
			String sortName,String sortOrder,Integer page,Integer pageSize,String deptId){
		MMGridPageVoBean<ResCourseVo> mmGridVo = new MMGridPageVoBean<ResCourseVo>();
		try {
			//设置查询参数
			ResCourseVo resCourseVo = new ResCourseVo();
			if(searchCategoryId != null && !("".equals(searchCategoryId))){
				resCourseVo.setCategoryId(Integer.parseInt(searchCategoryId));
			}
			if(searchCategoryType != null && !("".equals(searchCategoryType))){
				resCourseVo.setCategoryOrType(Integer.parseInt(searchCategoryType));
			}
			if(searchContent != null && !("".equals(searchContent))){
				resCourseVo.setName(searchContent);
			}
			logger.info("查询子公司"+subCompanyId+"课程数目");
			Integer count = studentCourseService.getCoursesCount(
					resCourseVo,companyId,subCompanyId,userId,postId,deptId);
			logger.info("子公司"+subCompanyId+"课程数目为");
			//获取fromNum
			Integer fromNum = (int)CommonUtil.getFromNum(String.valueOf(pageSize), String.valueOf(page), count);
			//查询课程
			logger.info("查询子公司"+subCompanyId+"课程");
			List<ResCourseVo> courses = studentCourseService.getCoursesByConditions(
					resCourseVo,companyId,subCompanyId,userId,postId,sortName,sortOrder,fromNum,pageSize,deptId);
			logger.info("查询子公司"+subCompanyId+"课程完毕");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(courses);
		} catch (Exception e) {
			logger.warn(CourseUserAction.class,e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: getPulianCoursesForMMGrid <BR>
	 * Description: 获取普连课程（mmGrid展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param searchCategoryId 课程体系id
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getPulianCoursesForMMGrid")
	@ResponseBody
	public Object getPulianCoursesForMMGrid(HttpServletRequest request,
			Integer companyId,Integer searchCategoryId,String searchContent,
			String sortName,String sortOrder,Integer page,Integer pageSize){
		MMGridPageVoBean<ResCourseVo> mmGridVo = new MMGridPageVoBean<ResCourseVo>();
		try {
			logger.info("查询公司"+companyId+"课程数目");
			Integer count = studentCourseService.getPulianCoursesCount(
					companyId,searchCategoryId,searchContent);
			logger.info("子公司"+companyId+"课程数目为"+count);
			//获取fromNum
			Integer fromNum = (int)CommonUtil.getFromNum(String.valueOf(pageSize), String.valueOf(page), count);
			//查询课程
			logger.info("查询公司"+companyId+"课程");
			List<ResCourseVo> courses = studentCourseService.getPulianCourses(
					companyId,searchCategoryId,searchContent,sortName,sortOrder,fromNum,pageSize);
			logger.info("查询公司"+companyId+"课程完毕");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(courses);
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class,e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: toMyCourses <BR>
	 * Description: 跳转到我的课程页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyCourses")
	public String toMyCourses(HttpServletRequest request){
		return "studentCourse/myCourses";
	}
	
	/**
	 * Method name: getMyCoursesCount <BR>
	 * Description: 根据条件获取我的课程数目 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询内容
	 * @return  Integer<BR>
	 */
	@RequestMapping("getMyCoursesCount")
	@ResponseBody
	public Integer getMyCoursesCount(HttpServletRequest request,
			Integer userId,Integer learnProcess,String searchContent){
		Integer count = 0;
		try {
			logger.info("查询学员"+userId+"的课程数目");
			count = studentCourseService.getMyCoursesCount(userId,learnProcess,searchContent);
			logger.info("学员"+userId+"课程数目为"+count);
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class, e);
		}
		return count;
	}
	
	/**
	 * Method name: getMyCoursesForPics <BR>
	 * Description: 根据条件获取我的课程（图片展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询内容
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyCoursesForPics")
	@ResponseBody
	public Object getMyCoursesForPics(HttpServletRequest request,
			Integer userId,Integer learnProcess,String searchContent,
			Integer page,Integer pageSize){
		//注意page是从0开始的
		Integer fromNum = page * pageSize;
		//查询我的课程
		try {
			logger.info("查询学员"+userId+"的课程");
			List<ResCourseVo> myCourses = studentCourseService.getMyCourses(
					userId,learnProcess,searchContent,fromNum,pageSize);
			logger.info("查询学员"+userId+"的课程完毕");
			return myCourses;
		} catch (DataBaseException e) {
			logger.warn(CourseUserAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getMyCoursesForMMGrid <BR>
	 * Description: 根据条件获取我的课程（mmGrid展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId 用户id
	 * @param learnProcess 学习进度
	 * @param searchContent 查询内容
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyCoursesForMMGrid")
	@ResponseBody
	public Object getMyCoursesForMMGrid(HttpServletRequest request,
			Integer userId,Integer learnProcess,String searchContent,
			Integer page,Integer pageSize){
		logger.debug("CourseUserAction_getMyCoursesForMMGrid: getMyCoursesForMMGrid START");
		MMGridPageVoBean<ResCourseVo> mmGridVo = new MMGridPageVoBean<ResCourseVo>();
		try {
			logger.debug("CourseUserAction_getMyCoursesForMMGrid: try START");
			//查询count和fromNum
			Integer count = studentCourseService.getMyCoursesCount(userId,learnProcess,searchContent);
			Integer fromNum = (int)CommonUtil.getFromNum(String.valueOf(pageSize),String.valueOf(page),count);
			//查询我的课程列表
			/*List<ResCourseVo> myCourses = studentCourseService.getMyCourses(
					userId, learnProcess, searchContent, fromNum, pageSize);*/
			List<ResCourseVo> myCourses = studentCourseService.getMyCoursesForList(
					userId, learnProcess, searchContent, fromNum, pageSize);
			mmGridVo.setTotal(count);
			mmGridVo.setRows(myCourses);
			logger.debug("CourseUserAction_getMyCoursesForMMGrid: try END");
		} catch (Exception e) {
			logger.debug("CourseUserAction_getMyCoursesForMMGrid: catch START");
			e.printStackTrace();
			logger.debug("CourseUserAction_getMyCoursesForMMGrid: catch END");
		}
		logger.debug("CourseUserAction_getMyCoursesForMMGrid: getMyCoursesForMMGrid END");
		return mmGridVo;
	}
	
	/**
	 * Method name: getFirstTypes <BR>
	 * Description: 获取一级课程体系和分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getFirstTypes")
	@ResponseBody
	public Object getFirstTypes(HttpServletRequest request,Integer companyId,Integer subCompanyId){
		try {
			logger.info("开始查询公司"+companyId+"的一级课程体系和分类");
			List<CourseTypeVo> types = studentCourseService.getFirstTypes(companyId,subCompanyId);
			logger.info("查询完毕公司"+companyId+"的一级课程体系和分类");
			return types;
		} catch (Exception e) {
			logger.warn(CourseUserAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getNextTypes <BR>
	 * Description: 获取二级课程体系和三级课程体系列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param typeId 体系id
	 * @param categoryOrType 体系还是分类
	 * @return  Object<BR>
	 */
	@RequestMapping("getNextTypes")
	@ResponseBody
	public Object getNextTypes(HttpServletRequest request,Integer typeId,Integer categoryOrType){
		try {
			logger.info("开始查询分类"+typeId+"的二级课程体系（分类）和三级课程体系（分类）列表");
			List<CourseNextTypeVo> nextTypes = studentCourseService.getNextTypes(typeId,categoryOrType);
			logger.info("查询完毕分类"+typeId+"的二级课程体系（分类）和三级课程体系（分类）列表");
			return nextTypes;
		} catch (Exception e) {
			logger.warn(CourseUserAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: cancelStudy <BR>
	 * Description: 取消学习课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("cancelStudy")
	@ResponseBody
	public Object cancelStudy(HttpServletRequest request, String courseId, String userId){
		AjaxReturnVo<Object> data = new AjaxReturnVo<Object>();
		try {
			logger.info("开始取消学员"+userId+"的课程"+courseId+"学习记录");
			studentCourseService.cancelStudy(courseId,userId);
			logger.info("取消完毕学员"+userId+"的课程"+courseId+"学习记录");
		} catch (Exception e) {
			data.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseUserAction.class, e);
		}
		return data;
	}
	
	/**shenhaijun end*/
	
}
