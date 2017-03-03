/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ReportFormsAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-11        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.vo.DeptExamDetailVo;
import com.jftt.wifi.bean.exam.vo.DeptExamVo;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.MarkExamListItemVo;
import com.jftt.wifi.bean.exam.vo.ReportWrongDetailVo;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.bean.vo.CourseDetailByCourseVo;
import com.jftt.wifi.bean.vo.CourseDetailByUserVo;
import com.jftt.wifi.bean.vo.CourseDetailReportVo;
import com.jftt.wifi.bean.vo.ExamUserVo;
import com.jftt.wifi.bean.vo.ReportFormsParam;
import com.jftt.wifi.bean.vo.StuStudyInfoFormVo;
import com.jftt.wifi.bean.vo.StuTotalityInfoFormVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ReportFormsService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:ReportFormsAction <BR>
 * class description: <BR>
 * Remark: <BR>报表
 * @version 1.00 2016-1-11
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("reportForms")
public class ReportFormsAction {
	
	@Autowired
	private ReportFormsService reportFormsService;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageDepartmentService manageDepartmentService;
	@Autowired
	private ManageParamService manageParamService;
	
	private static Logger logger = Logger.getLogger(ReportFormsAction.class);
	/**  chenrui start  **/
	
	/**
	 * 跳转 学员总体概况统计表
	 * Method name: toStuTotalityInfoForm <BR>
	 * Description: toStuTotalityInfoForm <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toStuTotalityInfoForm")
	public String toStuTotalityInfoForm(HttpServletRequest request) {
		return "report_forms/stu_totalInfo_form";
	}
	
	/**
	 * 跳转 员工学习情况统计总表
	 * Method name: toStuTotalityInfoForm <BR>
	 * Description: toStuTotalityInfoForm <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toStuStudyInfoForm")
	public String toStuStudyInfoForm(HttpServletRequest request) {
		return "report_forms/stu_studyInfo_form";
	}
	/**
	 * 获取当前学员总体概况统计表 数据源
	 * Method name: getStuTotalityInfoForm <BR>
	 * Description: getStuTotalityInfoForm <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getStuTotalityInfoForm")
	public Object getStuTotalityInfoForm(HttpServletRequest request,ReportFormsParam params) {
		logger.debug("ReportFormsAction执行getStuTotalityInfoForm方法");
		MMGridPageVoBean<StuTotalityInfoFormVo> mmVo = new MMGridPageVoBean<StuTotalityInfoFormVo>();
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setUserId(Integer.parseInt(userBean.getId()));
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());
			}
			int count = reportFormsService.getStuTotalityInfoFormCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<StuTotalityInfoFormVo> list = reportFormsService.getStuTotalityInfoForm(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ReportFormsAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * 获取员工学习情况统计表
	 * Method name: getStuStudyInfoForm <BR>
	 * Description: getStuStudyInfoForm <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getStuStudyInfoForm")
	public Object getStuStudyInfoForm(HttpServletRequest request, ReportFormsParam params) {
		logger.debug("ReportFormsAction执行getStuStudyInfoForm方法");
		MMGridPageVoBean<StuStudyInfoFormVo> mmVo = new MMGridPageVoBean<StuStudyInfoFormVo>();
		try {
			/*ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setUserId(Integer.parseInt(userBean.getId()));
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());
			}
			int count = reportFormsService.getStuStudyInfoFormCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<StuStudyInfoFormVo> list = reportFormsService.getStuStudyInfoForm(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);*/
			
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setUserId(Integer.parseInt(userBean.getId()));
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());
			}
			
			List<ManageUserBean> userlList = reportFormsService.getUsers(params);
			List<Integer> idlist = new ArrayList<Integer>();
			
			for (ManageUserBean user : userlList) {
				
				idlist.add(Integer.parseInt(user.getId()));
			}
			
			int count = userlList.size();
			String pageSize = params.getPageSize();
			String page = params.getPage();
			int fromNum = Integer.parseInt(CommonUtil.getFromNum(pageSize, page, count)+"");
			
			int end = fromNum + Integer.parseInt(pageSize);
			if(end > count){
				end = count;
			}
			
			idlist = idlist.subList(fromNum, end);
			
			//总的学习情况
			List<StuStudyInfoFormVo> allList = reportFormsService.getLearnALL(userBean.getCompanyId(), idlist);
			
			List<StuStudyInfoFormVo> list = new ArrayList<StuStudyInfoFormVo>();
			
			for (Integer id : idlist) {
				
				StuStudyInfoFormVo vo = new StuStudyInfoFormVo();
				
				ManageUserBean user = manageUserService.findUserByIdCondition(id+"");
				//用户信息
				vo.setUserId(user.getId());
				vo.setName(user.getName());
				vo.setUserName(user.getUserName());
				//性别 1:男；2：女；
				vo.setSex(user.getSex()+"");
				vo.setDeptName(user.getDeptName());
				vo.setPostName(user.getPostName());
				
				for(int i=0; i<allList.size(); i++) {
					
					if(allList.get(i).getUserId().equals(id+"")){
						
						vo.setTotalCourse(allList.get(i).getTotalCourse());
						vo.setTotalTime(allList.get(i).getTotalTime());
						vo.setTotalCredit(allList.get(i).getTotalCredit());
						
						allList.remove(i);
						break;
					}
				}
				
				List<Integer> userIdList = new ArrayList<Integer>();
				userIdList.add(id);
				//指定学习情况
				List<StuStudyInfoFormVo> pointList = reportFormsService.getLearnPoint(userBean.getCompanyId(), userIdList);
				
				for(int i=0; i<pointList.size(); i++) {
					
					if(pointList.get(i).getUserId().equals(id+"")){
						
						vo.setFinishCourseByAppoint(pointList.get(i).getFinishCourseByAppoint());
						vo.setFinishCourseTimeByAppoint(pointList.get(i).getFinishCourseTimeByAppoint());
						vo.setGetCreditByAppoint(pointList.get(i).getGetCreditByAppoint());
						
						pointList.remove(i);
						break;
					}
				}
				
				list.add(vo);
			}
			
			//平均
			list.add(reportFormsService.getLearn(userBean.getCompanyId()));
			
			mmVo.setTotal(count);
			mmVo.setRows(list);
			
		} catch (Exception e) {
			logger.debug(ReportFormsAction.class,e);
		}
		return mmVo;
	}
	
	/** chenrui end */
	/** zhangchen start */
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: CourseDetailReport <BR>
	 * Description: 跳转至课程学习情况统计页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseDetailReport")
	public String CourseDetailReport(HttpServletRequest request) {
		logger.debug("ReportFormsAction执行toCourseDetailReport方法");
		return "report_forms/course_detail_form";
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailReport <BR>
	 * Description: 获取课程学习情况统计 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCourseDetailReport")
	public Object getCourseDetailReport(HttpServletRequest request,ReportFormsParam params) {
		logger.debug("ReportFormsAction执行getCourseDetailReport方法");
		MMGridPageVoBean<CourseDetailReportVo> mmVo = new MMGridPageVoBean<CourseDetailReportVo>();
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());
			}
			int count = reportFormsService.getCourseDetailReportCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<CourseDetailReportVo> list = reportFormsService.getCourseDetailReport(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ReportFormsAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toCourseDetailByCourse <BR>
	 * Description: 跳转至学习明细表（按课程） <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCourseDetailByCourse")
	public String toCourseDetailByCourse(HttpServletRequest request,Integer courseId) {
		logger.debug("ReportFormsAction执行toCourseDetailByCourse方法");
		request.setAttribute("courseId", courseId);
		return "report_forms/course_detail_by_course";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailByCourse <BR>
	 * Description: 获取学习明细表（按课程） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCourseDetailByCourse")
	public Object getCourseDetailByCourse(HttpServletRequest request,ReportFormsParam params) {
		logger.debug("ReportFormsAction执行getCourseDetailByCourse方法");
		MMGridPageVoBean<CourseDetailByCourseVo> mmVo = new MMGridPageVoBean<CourseDetailByCourseVo>();
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());

			}
			int count = reportFormsService.getCourseDetailByCourseCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<CourseDetailByCourseVo> list = reportFormsService.getCourseDetailByCourse(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ReportFormsAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseInfo <BR>
	 * Description: 获取课程基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseName
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCourseInfo")
	public Object getCourseInfo(HttpServletRequest request,Integer courseId) {
		logger.debug("ReportFormsAction执行getCourseInfo方法");
		return reportFormsService.getCourseInfo(courseId);
	}
	
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getSearchValue <BR>
	 * Description: 查询课程名 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getSearchValue")
	@ResponseBody
	public Object getSearchValue(){
		List<String> list = reportFormsService.getValues();
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserName <BR>
	 * Description: 获取用户姓名 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value="getUserName")
	@ResponseBody
	public Object getUserName() throws Exception{
		List<String> list = reportFormsService.getUserNames();
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toCourseDetailByUser <BR>
	 * Description: 跳转至学习明细表（按学员） <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping("toCourseDetailByUser")
	public String toCourseDetailByUser(HttpServletRequest request,String userId) throws Exception {
		logger.debug("ReportFormsAction执行toCourseDetailByUser方法");
		if(userId != null){
			ManageUserBean userBean = manageUserService.findUserById(userId);
			Integer depId = 0;
			String name = "暂无";
			String userName = "暂无";
			String postName = "暂无";
			String departmentName = "暂无";
			Integer postId = 0;
			if(userBean != null){
				depId = userBean.getDeptId();
				name = userBean.getName();
				userName = userBean.getUserName();
				postId = userBean.getPostId();
				//获取部门名称
				if(depId != null){
					ManageDepartmentBean dept= manageDepartmentService.getManageDepartmentById(depId);
					if(dept != null){
						departmentName = dept.getName();
					}
				}
				if(postId != null){
					ManagePostBean postBean = manageParamService.selectManagePostById(postId);
					if(postBean!=null){
						postName = postBean.getName();
					}
				}
			}
			request.setAttribute("name", name);
			request.setAttribute("deptName", departmentName);
			request.setAttribute("postName", postName);
		}
		request.setAttribute("userId", userId);
		return "report_forms/course_detail_by_user";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailByUser <BR>
	 * Description: 获取学习明细表（按学员） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCourseDetailByUser")
	public Object getCourseDetailByUser(HttpServletRequest request,ReportFormsParam params) {
		logger.debug("ReportFormsAction执行getCourseDetailByUser方法");
		MMGridPageVoBean<CourseDetailByUserVo> mmVo = new MMGridPageVoBean<CourseDetailByUserVo>();
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());
			}
			int count = reportFormsService.getCourseDetailByUserCount(params);
			String pageSize = params.getPageSize();
			String page = params.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			params.setFromNum(fromNum);
			List<CourseDetailByUserVo> list = reportFormsService.getCourseDetailByUser(params);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ReportFormsAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toExamDetailForm <BR>
	 * Description: 跳转至考试统计列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toExamDetailForm")
	public String toExamDetailForm(HttpServletRequest request) {
		logger.debug("ReportFormsAction执行toExamDetailForm方法");
		return "report_forms/exam_detail_form";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamList <BR>
	 * Description: 获取考试统计列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getExamList")
	public Object getExamList(HttpServletRequest request,ExamSearchOptionVo searchOption) {
		logger.debug("ReportFormsAction执行getExamList方法");
		MMGridPageVoBean<MarkExamListItemVo> mmVo = new MMGridPageVoBean<MarkExamListItemVo>();
		try {
				ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
				searchOption.setCompanyId(userBean.getCompanyId());
				if(userBean.getCompanyId().intValue() != userBean.getSubCompanyId().intValue()){
					//子公司的人
					List<Long> subCompanyIdList = manageDepartmentService.getChildren(userBean.getCompanyId(), userBean.getSubCompanyId());
					searchOption.setSubCompanyIdList(subCompanyIdList);
				}
				
				int count = reportFormsService.getExamListCount(searchOption);
				
				List<MarkExamListItemVo> list  = reportFormsService.getExamList(searchOption);
				
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ExamAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toGradeDetail <BR>
	 * Description: 跳转至成绩成都预览列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  String<BR>
	 */
	@RequestMapping("toGradeDetail")
	public String toGradeDetail(HttpServletRequest request,String examId){
		request.setAttribute("examId", examId);
		return "report_forms/grade_detail";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetail <BR>
	 * Description: 获取成绩预览列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getGradeDetail")
	public Object getGradeDetail(HttpServletRequest request,cjylSearchVo paramVo) {
		logger.debug("ReportFormsAction执行getGradeDetail方法");
		MMGridPageVoBean<ExamAssignBean> mmVo = new MMGridPageVoBean<ExamAssignBean>();
		try {	
				int count = reportFormsService.getGradeDetailCount(paramVo);
				String page = paramVo.getPage();
				String pageSize = paramVo.getPageSize();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				paramVo.setFromNum(fromNum);
				List<ExamAssignBean> list  = reportFormsService.getGradeDetailList(paramVo, paramVo.getUserId());
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ExamAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toWrongDetail <BR>
	 * Description: 跳转至错题分析 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  String<BR>
	 */
	@RequestMapping("toWrongDetailList")
	public String toWrongDetailList(HttpServletRequest request,String examId){
		request.setAttribute("examId", examId);
		return "report_forms/wrong_detail_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getWrongDetailList <BR>
	 * Description: 获取错题分析列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getWrongDetailList")
	public Object getWrongDetailList(HttpServletRequest request,cjylSearchVo paramVo){
		logger.debug("ReportFormsAction执行getWrongDetailList方法");
		MMGridPageVoBean<ReportWrongDetailVo> mmVo = new MMGridPageVoBean<ReportWrongDetailVo>();
		try {	
				int count = reportFormsService.getWrongDetailListCount(paramVo);
				String page = paramVo.getPage();
				String pageSize = paramVo.getPageSize();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				paramVo.setFromNum(fromNum);
				List<ReportWrongDetailVo> list  = reportFormsService.getWrongDetailList(paramVo);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ExamAction.class,e);
		}
		return mmVo;
	}	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toExamDetailByDept <BR>
	 * Description: 跳转至考试统计（按部门） <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toExamDetailByDept")
	public String toExamDetailByDept(HttpServletRequest request) {
		logger.debug("ReportFormsAction_toExamDetailByDept");
		return "report_forms/exam_detail_by_dept";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailByDept <BR>
	 * Description: 获取考试统计（按部门） 记录<BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getExamDetailByDept")
	public Object getExamDetailByDept(HttpServletRequest request,cjylSearchVo paramVo) {
		logger.debug("ExamAction执行getGradeDetailByDept方法");
		MMGridPageVoBean<DeptExamVo> mmVo = new MMGridPageVoBean<DeptExamVo>();
		try {	
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				paramVo.setCompanyId(userBean.getCompanyId());
				paramVo.setSubCompanyId(userBean.getSubCompanyId());
			}
			/*Map map = new HashMap();
			map.put("companyId", userBean.getCompanyId());
			map.put("subCompanyId", userBean.getSubCompanyId());
			List<ManageDepartmentBean> deptList = manageDepartmentService.getManageDepartmentByMap(map);*/
			//int count = 10;
			int count = reportFormsService.getExamDetailByDeptListCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			//List<DeptExamVo> list  = new ArrayList<DeptExamVo>();
			List<DeptExamVo> list  = reportFormsService.getExamDetailByDeptList(paramVo);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ExamAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toGradeDetailByDept <BR>
	 * Description: 跳转至考试统计（按部门）部门详情 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("toGradeDetailByDept")
	public String toGradeDetailByDept(HttpServletRequest request, Integer deptId){
		logger.debug("ReportFormsAction_toGradeDetailByDept");
		
		if(deptId !=null){
			request.setAttribute("deptId", deptId);
		}
		
		return "report_forms/grade_detail_by_dept";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailByDept <BR>
	 * Description: 获取考试统计（按部门）部门详情  <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getGradeDetailByDept")
	public Object getGradeDetailByDept(HttpServletRequest request,cjylSearchVo paramVo) {
		logger.debug("ExamAction执行getGradeDetailByDept方法");
		MMGridPageVoBean<DeptExamDetailVo> mmVo = new MMGridPageVoBean<DeptExamDetailVo>();
		try {	
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				paramVo.setCompanyId(userBean.getCompanyId());
				paramVo.setSubCompanyId(userBean.getSubCompanyId());
			}
			//int count = 10;
			int count = reportFormsService.getGradeDetailByDeptListCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			//List<DeptExamVo> list  = new ArrayList<DeptExamVo>();
			List<DeptExamDetailVo> list  = reportFormsService.getGradeDetailByDeptList(paramVo);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(ExamAction.class,e);
		}
		return mmVo;
	}
	/** zhangchen end */
	
	/**
	 * wj
	 * 考试统计按人员页面
	 */
	@RequestMapping("toGradeDetailByUser")
	public String toGradeDetailByUser(HttpServletRequest request){
		logger.debug("ReportFormsAction_toGradeDetailByUser");
		
		return "report_forms/grade_detail_by_user";
	}
	
	/**
	 * @author JFTT)wj<BR>
	 * Method name: getGradeDetailByUser <BR>
	 * Description: 获取考试统计（按人员） <BR>
	 */
	@ResponseBody
	@RequestMapping("getGradeDetailByUser")
	public Object getGradeDetailByUser(HttpServletRequest request, ReportFormsParam params, ExamUserVo examUserVo) {
		logger.debug("ExamAction执行getGradeDetailByUser方法");
		MMGridPageVoBean<ExamUserVo> mmVo = new MMGridPageVoBean<ExamUserVo>();
		
		try {	
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(userBean != null){
				params.setCompanyId(userBean.getCompanyId());
				params.setSubCompanyId(userBean.getSubCompanyId());
			}
			
			//参数
			List<Integer> userIdList = new ArrayList<Integer>();
			
			List<ManageUserBean> manageUserList = reportFormsService.getUsers(params);
			if(manageUserList !=null && !manageUserList.isEmpty()){
				for (ManageUserBean user : manageUserList) {
					userIdList.add(Integer.parseInt(user.getId()));
				}
			}
			
			examUserVo.setUserList(userIdList);
			//分页
			int fromNum = (examUserVo.getPage()-1)*examUserVo.getPageSize();
			examUserVo.setFromNum(fromNum);
			
			int count = reportFormsService.getExamUserCount(examUserVo);
			List<ExamUserVo> list = reportFormsService.getExamUser(examUserVo);
			
			if(list !=null && !list.isEmpty()){
				for (ExamUserVo vo : list) {
					
					ManageUserBean user = manageUserService.findUserByIdCondition(vo.getUserId()+"");
					
					vo.setName(user.getName());
					vo.setUserName(user.getUserName());
					vo.setDeptName(user.getDeptName());
					vo.setPostName(user.getPostName());
				}
			}
			
			mmVo.setTotal(count);
			mmVo.setRows(list);
			
		} catch (Exception e) {
			logger.debug(ExamAction.class,e);
		}
		
		return mmVo;
	}
}
