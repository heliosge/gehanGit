/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseDetailAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-16        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.CourseCollectionBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.SectionExamVo;
import com.jftt.wifi.bean.vo.SectionWareVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseDetailService;
import com.jftt.wifi.service.CourseRecordService;
import com.jftt.wifi.service.IntegralManageService;

/**
 * class name:CourseDetailAction <BR>
 * class description: 课程详情action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-16
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("courseDetailAction")
public class CourseDetailAction {
	private static Logger logger = Logger.getLogger(CourseDetailAction.class);
	
	@Resource(name="courseDetailService")
	private CourseDetailService courseDetailService;
	@Resource(name="courseRecordService")
	private CourseRecordService courseRecordService;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: toCourseDetail <BR>
	 * Description: 跳转到课程详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程名称
	 * @param backFlag 返回标记
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseDetail")
	public String toCourseDetail(HttpServletRequest request,Integer courseId,Integer backFlag){
		try {
			logger.info("查询课程"+courseId+"详情");
			ResCourseBean courseBean = courseDetailService.getCourseById(courseId);
			logger.info("查询课程"+courseId+"详情结束");
			//将课程体系名称放入bean
			String nearestCategory = courseDetailService.getNearestCategory(courseId);
			if(nearestCategory != null){
				courseBean.setCategoryName(nearestCategory);
			}
			request.setAttribute("courseBean", JSONObject.fromObject(courseBean));//课程bean
			request.getSession().setAttribute("backFlag", backFlag);//返回页面标志
		} catch (DataBaseException e) {
			logger.warn(CourseDetailAction.class, e);
		}
		return "studentCourse/courseDetail";
	}
	
	/**
	 * Method name: getCourseCollection <BR>
	 * Description: 判断该课程是否被收藏 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  boolean<BR>
	 */
	@RequestMapping("getCourseCollection")
	@ResponseBody
	public boolean getCourseCollection(HttpServletRequest request,Integer courseId,Integer userId){
		boolean isCollectted = false;
		try {
			logger.info("查询"+courseId+"是否被收藏");
			isCollectted = courseDetailService.getCourseCollection(courseId,userId);
			logger.info("查询"+courseId+"是否被收藏结果为"+isCollectted);
		} catch (DataBaseException e) {
			logger.warn(CourseDetailAction.class,e);
		}
		return isCollectted;
	}
	
	/**
	 * Method name: collectThisCourse <BR>
	 * Description: 收藏该门课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param collection 课程收藏Bean
	 * @return  Object<BR>
	 */
	@RequestMapping("collectThisCourse")
	@ResponseBody
	public Object collectThisCourse(HttpServletRequest request,CourseCollectionBean collection){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("收藏课程"+collection.getCourseId()+"");
			courseDetailService.collectThisCourse(collection);
			//设置积分（收藏课程）
			integralManageService.handleIntegral(collection.getUserId(), 7004);
			logger.info("收藏课程"+collection.getCourseId()+"完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseDetailAction.class,e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: getThisCourseCategory <BR>
	 * Description: 获取该课程的课程体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  Object<BR>
	 */
	@RequestMapping("getThisCourseCategory")
	@ResponseBody
	public Object getThisCourseCategory(HttpServletRequest request,Integer courseId){
		try {
			logger.info("根据课程"+courseId+"查询课程体系");
			List<ResCourseCategoryBean> categorys = courseDetailService.getThisCourseCategory(courseId);
			logger.info("查询课程"+courseId+"课程体系完毕");
			return categorys;
		} catch (DataBaseException e) {
			logger.warn(CourseDetailAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getCourseSections <BR>
	 * Description: 查询该课程所有章节 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseSections")
	@ResponseBody
	public Object getCourseSections(HttpServletRequest request,Integer courseId){
		try {
			logger.warn("开始查询课程"+courseId+"所有章节");
			List<ResSectionBean> sections = courseDetailService.getCourseSections(courseId);
			logger.warn("查询完毕课程"+courseId+"所有章节");
			return sections;
		} catch (DataBaseException e) {
			logger.warn(CourseDetailAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getSectionWares <BR>
	 * Description: 根据章节查询所有课件及该课件的学习记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getSectionWares")
	@ResponseBody
	public Object getSectionWares(HttpServletRequest request,Integer sectionId,Integer userId){
		try {
			logger.warn("开始查询章节"+sectionId+"用户"+userId+"学习记录");
			List<SectionWareVo> wares = courseDetailService.getSectionWares(sectionId,userId);
			logger.warn("查询结束章节"+sectionId+"用户"+userId+"学习记录");
			return wares;
		} catch (DataBaseException e) {
			logger.warn(CourseDetailAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getSectionTests <BR>
	 * Description: 根据章节查询所有测试情况 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId 章节id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getSectionTests")
	@ResponseBody
	public Object getSectionTests(HttpServletRequest request,Integer sectionId,Integer userId){
		try {
			logger.info("查询学员"+userId+"章节"+sectionId+"测试记录");
			List<SectionExamVo> exams = courseDetailService.getSectionTests(sectionId,userId);
			logger.info("查询学员"+userId+"章节"+sectionId+"测试记录完毕");
			return exams;
		} catch (Exception e) {
			logger.warn(CourseDetailAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: getCourseTest <BR>
	 * Description: 查询课程测试及测试记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseTest")
	@ResponseBody
	public Object getCourseTest(HttpServletRequest request,Integer courseId,Integer userId){
		try {
			logger.info("查询学员"+userId+"课程"+courseId+"测试记录");
			List<SectionExamVo> exams = courseDetailService.getCourseTest(courseId,userId);
			logger.info("查询学员"+userId+"课程"+courseId+"测试记录完毕");
			return exams;
		} catch (DataBaseException e) {
			logger.warn(CourseDetailAction.class,e);
		}
		return null;
	}
	
	/**
	 * Method name: recordCourseAndWare <BR>
	 * Description: 记录课程和课件学习情况 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseWareId 课件id
	 * @param courseWareType 课件类型
	 * @param userId 用户id
	 * @param sectionId 章节id
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("recordCourseAndWare")
	@ResponseBody
	public Object recordCourseAndWare(HttpServletRequest request,Integer courseWareId,Integer courseWareType,
			Integer userId,Integer sectionId,Integer courseId,Integer companyId,Integer subCompanyId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("开始记录课程"+courseId+"和课件"+courseWareId+"的学习情况");
			courseRecordService.recordCourseAndWare(courseWareId,courseWareType,userId,sectionId,courseId,companyId,subCompanyId);
			logger.info("记录课程"+courseId+"和课件"+courseWareId+"的学习情况完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseDetailAction.class, e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: recordExamAndCourseBeforeTest <BR>
	 * Description: 记录测试及课程学习情况 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId 章节id
	 * @param examId 试卷id
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param passTimes 允许通过次数
	 * @param passScorePercent 允许通过分数
	 * @return  Object<BR>
	 */
	@RequestMapping("recordExamAndCourseBeforeTest")
	@ResponseBody
	public Object recordExamAndCourseBeforeTest(HttpServletRequest request,Integer sectionId,Integer examId,Integer userId,
			Integer courseId,Integer companyId,Integer subCompanyId,Integer passTimes,Integer passScorePercent){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("开始记录测试"+examId+"和课程"+courseId+"学习情况");
			Integer examRecordId = courseRecordService.recordExamAndCourseBeforeTest(sectionId,examId,userId,courseId,companyId,subCompanyId,passTimes,passScorePercent);
			returnVo.setRtnData(examRecordId);//课程测试记录的id
			logger.info("记录测试"+examId+"和课程"+courseId+"学习情况完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseDetailAction.class, e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: saveExamAndAssignInfo <BR>
	 * Description: 保存考试和考试分配信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId 试卷id
	 * @param duration 考试时长
	 * @param allowTimes 允许此时
	 * @param passScorePercent 通过分数比例
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("saveExamAndAssignInfo")
	@ResponseBody
	public Object saveExamAndAssignInfo(HttpServletRequest request,Integer examId,Integer duration,Integer allowTimes,Integer passScorePercent,
			Integer userId,Integer companyId,Integer subCompanyId){
		AjaxReturnVo<Object> returnVo = new AjaxReturnVo<Object>();
		try {
			logger.info("开始保存用户"+userId+"的考试和考试记录信息");
			Map<String, Integer> resultMap = courseRecordService.saveExamAndAssignInfo(examId,duration,allowTimes,passScorePercent,userId,companyId,subCompanyId);
			returnVo.setRtnData(resultMap);
			logger.info("保存用户"+userId+"的考试和考试记录信息完毕");
		} catch (Exception e) {
			returnVo.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(CourseDetailAction.class, e);
		}
		return returnVo;
	}
	
	/**
	 * Method name: getTeacherNameById <BR>
	 * Description: 根据教师id查询教师姓名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param teacherId 教师id
	 * @return  Object<BR>
	 */
	@RequestMapping("getTeacherNameById")
	@ResponseBody
	public Object getTeacherNameById(HttpServletRequest request, Integer teacherId){
		try {
			logger.info("根据讲师id"+teacherId+"查询讲师姓名");
			String teacherName = courseDetailService.getTeacherNameById(teacherId);
			logger.info("讲师"+teacherId+"姓名为"+teacherName);
			return teacherName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getStudentNumById <BR>
	 * Description: 根据课程id查询学习人数  <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  Object<BR>
	 */
	@RequestMapping("getStudentNumById")
	@ResponseBody
	public Object getStudentNumById(HttpServletRequest request, Integer courseId){
		logger.info("----getStudentNumById----");
		try {
			Integer studentNum = courseDetailService.getStudentNumById(courseId);
			return studentNum;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getCourseTotalDuration <BR>
	 * Description: 获取课程总时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseTotalDuration")
	@ResponseBody
	public Object getCourseTotalDuration(HttpServletRequest request, Integer courseId){
		logger.info("----getCourseTotalDuration----");
		try {
			String[] durationArr = courseDetailService.getCourseTotalDuration(courseId);
			return durationArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getCourseStudyedDuration <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseStudyedDuration")
	@ResponseBody
	public Object getCourseStudyedDuration(HttpServletRequest request, Integer courseId,Integer userId){
		logger.info("----getCourseStudyedDuration----");
		try {
			String[] durationArr = courseDetailService.getCourseStudyedDuration(courseId,userId);
			return durationArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getCourseStudyedDurationNew <BR>
	 * Description: 获取课程已学时长（时、分、秒依次存储） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return  Object<BR>
	 */
	@RequestMapping("getCourseStudyedDurationNew")
	@ResponseBody
	public Object getCourseStudyedDurationNew(HttpServletRequest request, Integer courseId,Integer userId){
		String[] durationArr = new String[3];
		logger.info("--开始查询课程id"+courseId+"用户id"+userId+"已学时长--");
		try {
			durationArr = courseDetailService.getCourseStudyedDurationNew(courseId,userId);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		logger.info("--结束查询课程id"+courseId+"用户id"+userId+"已学时长--");
		return durationArr;
	}
	
	/**shenhaijun end*/
	
}
