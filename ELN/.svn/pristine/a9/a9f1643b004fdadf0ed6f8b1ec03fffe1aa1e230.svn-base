/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ReportFormsServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-11        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseCategoryBean;
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
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ReportFormsMapper;
import com.jftt.wifi.dao.ResCourseCategoryDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ReportFormsService;

/**
 * class name:ReportFormsServiceImpl <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-11
 * @author JFTT)chenrui
 */
@Service
@SuppressWarnings("all")
public class ReportFormsServiceImpl implements ReportFormsService {

	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageCompanyService companyService;
	@Autowired
	private ReportFormsMapper reportFormsMapper;
	@Autowired
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	@Autowired
	private ManageParamService manageParamService;
	@Autowired
	private ResCourseDaoMapper resCourseDaoMapper;
	@Autowired
	private ResCourseCategoryDaoMapper resCourseCategoryDaoMapper;
	@Autowired
	private ManageDepartmentService manageDepartmentService;
	
	/** chenrui start */

	/**
	 *  获取当前学员总体概况统计表 数据源
	 * @Override
	 * @see com.jftt.wifi.service.ReportFormsService#getStuTotalityInfoForm(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getStuTotalityInfoForm <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<StuTotalityInfoFormVo> getStuTotalityInfoForm(ReportFormsParam params) throws Exception {
		
		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		List<StuTotalityInfoFormVo> list = new ArrayList<StuTotalityInfoFormVo>();
		if(uList.size()>0){
			list = reportFormsMapper.getStuTotalityInfoForm(params,uList);
		}
		
		return list;
	}
	@Override
	public int getStuTotalityInfoFormCount(ReportFormsParam params) throws Exception {
		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		return uList.size();
	}

	/**
	 * 获取当前公司所有用户
	 * Method name: getAllUserByCompanyId <BR>
	 * Description: getAllUserByCompanyId <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<ManageUserBean><BR>
	 * @throws Exception 
	 */
	@Override
	public List<ManageUserBean> getUsers(ReportFormsParam params) throws Exception{
		int companyId = params.getCompanyId();
		Integer subCompanyId = params.getSubCompanyId();
		String userName = params.getUserName();
		String name = params.getName();
		String deptName = params.getDeptName();
		String postName = params.getPostName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		if(params.getIsGetAllWithSub() == null){
			
			if(subCompanyId !=null && companyId != subCompanyId){
				//获得子公司，下面所有的子公司
				List<Long> subCompanyIdList = manageDepartmentService.getChildren(companyId, subCompanyId);
				
				map.put("subCompanyId", subCompanyIdList);
			}
		}
		if(userName !=null && !userName.isEmpty()){
			map.put("userName", userName);
		}
		if(name !=null && !name.isEmpty()){
			map.put("name", name);
		}
		if(deptName !=null && !deptName.isEmpty()){
			map.put("deptId", deptName);
		}
		if(params.getDeptId() !=null && !params.getDeptId().isEmpty()){
			
			String[] ids = params.getDeptId().split(",");
			List<Integer> list = new ArrayList<Integer>();
			
			for(int i=0; i<ids.length; i++){
				list.add(Integer.parseInt(ids[i]));
			}
			
			map.put("deptId", list);
		}
		if(postName !=null && !postName.isEmpty()){
			map.put("postId", postName);
		}
		if(params.getPostId() !=null && !params.getPostId().isEmpty()){
			
			String[] ids = params.getPostId().split(",");
			List<Integer> list = new ArrayList<Integer>();
			
			for(int i=0; i<ids.length; i++){
				list.add(Integer.parseInt(ids[i]));
			}
			
			map.put("postId", list);
		}
		
		List<ManageUserBean> list = manageUserService.findUserByListCondition_(map);
		
		if(params.getWithManager() !=null && params.getWithManager().equals("no") && list !=null && !list.isEmpty()){
			//需要 过滤掉 集团管理员
			ManageCompanyBean companyBean = companyService.selectCompanyById(companyId);
			
			if(companyBean.getInitUserId() != null){
				for (int i=0; i<list.size(); i++) {
					
					if(list.get(i).getId().equals(companyBean.getInitUserId()+"")){
						list.remove(i);
						
						break;
					}
				}
			}
		}
		
		return list;
	}
	
	@Override
	public int getStuStudyInfoFormCount(ReportFormsParam params) throws Exception {

		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		
		return uList.size();
	}
	
	@Override
	public List<StuStudyInfoFormVo> getStuStudyInfoForm(ReportFormsParam params) throws Exception {

		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		List<StuStudyInfoFormVo> list = new ArrayList<StuStudyInfoFormVo>();

		int len = uList.size();
		if(len > 0){
			list = reportFormsMapper.getStuStudyInfoForm(params,uList,2);
			for(StuStudyInfoFormVo vo : list){
				int totalTime = 0;// 总学时 
				int totalCredit = 0;// 总积分
				if(vo.getFinishCourseBySelf()!=null && !vo.getFinishCourseBySelf().isEmpty()){
					totalTime += Integer.parseInt(vo.getFinishCourseTimeBySelf());
				}
				if(vo.getGetCreditBySelf()!=null && !vo.getGetCreditBySelf().isEmpty()){
					totalCredit += Integer.parseInt(vo.getGetCreditBySelf());
				}
				if(vo.getFinishCourseTimeByAppoint()!=null && !vo.getFinishCourseTimeByAppoint().isEmpty()){
					totalTime += Integer.parseInt(vo.getFinishCourseTimeByAppoint());
				}
				if(vo.getGetCreditByAppoint()!=null && !vo.getGetCreditByAppoint().isEmpty()){
					totalCredit += Integer.parseInt(vo.getGetCreditByAppoint());
				}
				
				vo.setTotalTime(totalTime+"");
				vo.setTotalCredit(totalCredit+"");
			}
		}
		// 获取平均统计
		StuStudyInfoFormVo avgVo = getAvgData(params);
		list.add(avgVo);
		return list;
	}
	
	private StuStudyInfoFormVo getAvgData(ReportFormsParam params) throws Exception{
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		params.setDeptName("");
		params.setName("");
		params.setUserName("");
		params.setPostName("");
		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		List<StuStudyInfoFormVo> list = reportFormsMapper.getStuStudyInfoForm(params,uList,1);
		StuStudyInfoFormVo avgVo = new StuStudyInfoFormVo();
		double totalTimes = 0; // 所有学员总学时汇总
		double totalCredits = 0; // 所有学员总学分汇总
		int totalFinishCourseBySelf = 0; // 所有学员完成自学总课程数 
		double totalFinishCourseTimeBySelf = 0; // 所有学员完成自学总课程学时
		double totalGetCreditBySelf = 0; // 所有学员完成自学总课程学分
		int totalFinishCourseByAppoint = 0; // 所有学员完成指派总课程数 
		double totalFinishCourseTimeByAppoint = 0; // 所有学员完成指派总课程学时
		double totalGetCreditByAppoint = 0; // 所有学员完成指派总课程学分
		int len = uList.size();
		if(len > 0){
			for(StuStudyInfoFormVo vo : list){
				int totalTime = 0;// 总学时 
				int totalCredit = 0;// 总积分
				if(vo.getFinishCourseBySelf()!=null && !vo.getFinishCourseBySelf().isEmpty()){
					// 自学课程数
					totalFinishCourseBySelf += Integer.parseInt(vo.getFinishCourseBySelf());
				}
				if(vo.getFinishCourseBySelf()!=null && !vo.getFinishCourseBySelf().isEmpty()){
					//完成自学学习课程学时
					totalFinishCourseTimeBySelf += Integer.parseInt(vo.getFinishCourseTimeBySelf());
					totalTime += Integer.parseInt(vo.getFinishCourseTimeBySelf());
				}
				if(vo.getGetCreditBySelf()!=null && !vo.getGetCreditBySelf().isEmpty()){
					// 获得自学学分
					totalGetCreditBySelf += Integer.parseInt(vo.getGetCreditBySelf());
					totalCredit += Integer.parseInt(vo.getGetCreditBySelf());
				}
				if(vo.getFinishCourseByAppoint()!=null && !vo.getFinishCourseByAppoint().isEmpty()){
					// 指派课程数
					totalFinishCourseByAppoint += Integer.parseInt(vo.getFinishCourseByAppoint());
				}
				if(vo.getFinishCourseTimeByAppoint()!=null && !vo.getFinishCourseTimeByAppoint().isEmpty()){
					// 完成指派学习课程学时
					totalFinishCourseTimeByAppoint += Integer.parseInt(vo.getFinishCourseTimeByAppoint());
					totalTime += Integer.parseInt(vo.getFinishCourseTimeByAppoint());
				}
				if(vo.getGetCreditByAppoint()!=null && !vo.getGetCreditByAppoint().isEmpty()){
					// 获得指派学分
					totalGetCreditByAppoint += Integer.parseInt(vo.getGetCreditByAppoint());
					totalCredit += Integer.parseInt(vo.getGetCreditByAppoint());
				}
				totalTimes += totalTime;
				totalCredits += totalCredit;
				vo.setTotalTime(totalTime+"");
				vo.setTotalCredit(totalCredit+"");
			}
		}
		
		avgVo.setName("平均");
		avgVo.setFinishCourseBySelf(totalFinishCourseBySelf/len +"");
		avgVo.setFinishCourseTimeBySelf(totalFinishCourseTimeBySelf/len==0?"0":new DecimalFormat("0.00").format(totalFinishCourseTimeBySelf/len));
		avgVo.setGetCreditBySelf(totalGetCreditBySelf/len==0?"0":new DecimalFormat("0.00").format(totalGetCreditBySelf/len));
		avgVo.setFinishCourseByAppoint(totalFinishCourseByAppoint/len + "");
		avgVo.setFinishCourseTimeByAppoint(totalFinishCourseTimeByAppoint/len==0?"0":new DecimalFormat("0.00").format(totalFinishCourseTimeByAppoint/len));
		avgVo.setGetCreditByAppoint(totalGetCreditByAppoint/len==0?"0":new DecimalFormat("0.00").format(totalGetCreditByAppoint/len));
		avgVo.setTotalTime(totalTimes/len==0?"0":new DecimalFormat("0.00").format(totalTimes/len));
		avgVo.setTotalCredit(totalCredits/len==0?"0":new DecimalFormat("0.00").format(totalCredits/len));
		avgVo.setStatisticsDate(nowDate);
		return avgVo;
	}
	
	/** chenrui end */
	
	/** zhangchen start */
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseDetailReport(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getCourseDetailReport <BR>
	 * Description: 获取课程学习情况统计 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public List<CourseDetailReportVo> getCourseDetailReport(ReportFormsParam params) throws Exception {
		List<CourseDetailReportVo> voList = new ArrayList<CourseDetailReportVo>();
		List<ResCourseBean> courseList = new ArrayList<ResCourseBean>();
		// 获取当前公司的所有用户信息
		//List<ManageUserBean> uList = getUsers(params);
		//params.setUserList(uList);
		//if(uList.size() > 0){
			courseList = reportFormsMapper.getResCourse(params);
		//}
		CourseDetailReportVo vo = null;
		for(ResCourseBean course : courseList){
			vo = new CourseDetailReportVo();
			vo.setId(course.getId());
			vo.setCourseCode(course.getCode());
			vo.setCourseName(course.getName());
			/*if(course.getType() != null){
				vo.setCourseType(course.getType() == 1 ? "线上课程" : "直播课程");
			}*/
			ResCourseCategoryBean cbean = resCourseCategoryDaoMapper.getById(course.getCategoryId());
			if(cbean != null){
				vo.setCourseType(cbean.getName());
			}
			vo.setCourseTime(course.getLearnTime());
			int learningNum = reportFormsMapper.getLearnNum(course.getId(), 1, params.getWithManager());
			vo.setLearningNum(learningNum);
			int completeNum = reportFormsMapper.getLearnNum(course.getId(), 2, params.getWithManager());
			vo.setCompleteNum(completeNum);
			//总学习人数
			vo.setTotalNum(learningNum + completeNum);
			//总学习时长
			params.setCourseId(course.getId());
			Integer totalTime = reportFormsMapper.getCourseTotalDuration(params);
			if(totalTime != null){
				vo.setTotalTime(totalTime);
			}else{
				vo.setTotalTime(0);
			}
			
			//获得学分  学习完成得到全部学分course.getLearnScore()  否则0分
			//即完成的人*course.getLearnScore() 
			int learnScore = 0;
			if(course.getLearnScore() !=null){
				learnScore = course.getLearnScore();
			}
			
			vo.setGetScore(completeNum*learnScore);
			
			voList.add(vo);
		}
		return voList;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseDetailReportCount(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getCourseDetailReportCount <BR>
	 * Description: 获取课程学习情况统计条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public int getCourseDetailReportCount(ReportFormsParam params) throws Exception {
		// TODO Auto-generated method stub
		List<ManageUserBean> uList = getUsers(params);
		params.setUserList(uList);
		if(uList.size() > 0){
			return reportFormsMapper.getResCourseCount(params);
		}else{
			return 0;
		}
		
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getValues() <BR>
	 * Method name: getValues <BR>
	 * Description: 查询课程名 <BR>
	 * Remark: <BR>
	 * @return  <BR>
	*/
	
	@Override
	public List<String> getValues() {
		// TODO Auto-generated method stub
		return reportFormsMapper.selectValues();
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getUserNames() <BR>
	 * Method name: getUserNames <BR>
	 * Description: 获取用户姓名 <BR>
	 * Remark: <BR>
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public List<String> getUserNames() throws Exception {
		// TODO Auto-generated method stub
		List<ManageUserBean> list = manageUserService.findAll();
		List<String> nameList = new ArrayList<String>();
		for(ManageUserBean user : list){
			nameList.add(user.getName());
		}
		return nameList;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseDetailByCourseCount(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getCourseDetailByCourseCount <BR>
	 * Description: 获取学习明细表（按课程）条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public int getCourseDetailByCourseCount(ReportFormsParam params) throws Exception {
		// TODO Auto-generated method stub
		List<CourseDetailByCourseVo> voList = new ArrayList<CourseDetailByCourseVo>();
		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		params.setUserList(uList);
		if(uList.size() > 0){
			return reportFormsMapper.getCourseStudyRecordByCourseCount(params);
		}else{
			return 0;
		}
		
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseDetailByCourse(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getCourseDetailByCourse <BR>
	 * Description: 获取学习明细表（按课程） <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public List<CourseDetailByCourseVo> getCourseDetailByCourse(ReportFormsParam params) throws Exception {
		// TODO Auto-generated method stub
		List<CourseDetailByCourseVo> voList = new ArrayList<CourseDetailByCourseVo>();
		// 获取当前公司的所有用户信息
		List<ManageUserBean> uList = getUsers(params);
		params.setUserList(uList);
		if(uList.size() > 0){
			voList = reportFormsMapper.getCourseStudyRecordByCourse(params);
		}
		//List<CourseDetailByCourseVo> voList = reportFormsMapper.getCourseStudyRecordByCourse(params);
		if(!voList.isEmpty()){
			Integer courseId = voList.get(0).getCourseId();
			ResCourseBean course = resCourseDaoMapper.getById(courseId);
			for(CourseDetailByCourseVo vo : voList){
				if(vo.getLearnProcess() == 2){
					vo.setGetScore(course.getLearnScore());
				}else{
					vo.setGetScore(0);
				}
				params.setCourseId(courseId);
				params.setUserId(vo.getUserId());
				Integer durationTime = reportFormsMapper.getCourseTotalDuration(params);
				if(durationTime != null){
					vo.setDurationTime(durationTime);
				}
				ManageUserBean userBean = manageUserService.findUserByIdCondition(vo.getUserId().toString());
				/*Integer depId = 0;
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
						ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
						if(dept != null){
							departmentName = dept.getName();
						}
					}
					if(postId != null && postId > 0){
						ManagePostBean postBean = manageParamService.selectManagePostById(postId);
						if(postBean!=null){
							postName = postBean.getName();
						}
					}
				}*/
				
				if(userBean !=null){
					vo.setName(userBean.getName());
					vo.setUserName(userBean.getUserName());
					vo.setPost(userBean.getPostName());
					vo.setDepartment(userBean.getDeptName());
				}
				
			}
		}
		return voList;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseInfo(java.lang.String) <BR>
	 * Method name: getCourseInfo <BR>
	 * Description: 获取课程基本信息 <BR>
	 * Remark: <BR>
	 * @param courseName
	 * @return  <BR>
	*/
	
	@Override
	public Object getCourseInfo(Integer courseId) {
		// TODO Auto-generated method stub
		return reportFormsMapper.getCourseInfo(courseId);
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseDetailByUserCount(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getCourseDetailByUserCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  <BR>
	*/
	
	@Override
	public int getCourseDetailByUserCount(ReportFormsParam params) {
		// TODO Auto-generated method stub
		return reportFormsMapper.getCourseStudyRecordByUserCount(params);
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getCourseDetailByUser(com.jftt.wifi.bean.vo.ReportFormsParam) <BR>
	 * Method name: getCourseDetailByUser <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  <BR>
	 * @throws DataBaseException 
	*/
	
	@Override
	@Transactional
	public List<CourseDetailByUserVo> getCourseDetailByUser(ReportFormsParam params) throws DataBaseException {
		// TODO Auto-generated method stub
		List<CourseDetailByUserVo> voList = reportFormsMapper.getCourseStudyRecordByUser(params);
		for(CourseDetailByUserVo vo :  voList){
			Integer courseId = vo.getCourseId();
			Integer learnProcess = vo.getLearnProcess();
			ResCourseBean course = resCourseDaoMapper.getById(courseId);
			vo.setCourseCode(course.getCode());
			vo.setCourseId(course.getId());
			vo.setCourseName(course.getName());
			//vo.setCourseType(course.getType());
			ResCourseCategoryBean cbean = resCourseCategoryDaoMapper.getCategoryByCourseId(courseId);
			if(cbean != null){
				vo.setCourseType(cbean.getName());
			}
			vo.setLearnTime(course.getLearnTime());
			if(learnProcess == 2){
				vo.setGetScore(course.getLearnScore());
			}else{
				vo.setGetScore(0);
			}
			params.setCourseId(courseId);
			params.setUserId(params.getUserId());
			Integer totalTime = reportFormsMapper.getCourseTotalDuration(params);
			vo.setDurationTime(totalTime);
		}
		return voList;
	}
	
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getExmaList(com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo) <BR>
	 * Method name: 获取考试统计列表<BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	@Override
	public List<MarkExamListItemVo> getExamList(ExamSearchOptionVo searchOption) {
		List<MarkExamListItemVo> list = reportFormsMapper.getExamList(searchOption);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExmaListCount <BR>
	 * Description: 获取考试统计列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<MarkExamListItemVo><BR>
	 */
	@Override
	public int getExamListCount(ExamSearchOptionVo searchOption) {
		int count = reportFormsMapper.getExamListCount(searchOption);
		return count;
	}
	
	/**
	 * @author JFTT) wj
	 * 把 getGradeDetailList getGradeDetailCount 获取用户的方法合并
	 * @throws Exception 
	 */
	@Transactional
	public List<ManageUserBean> getGradeDetailUserList(cjylSearchVo paramVo) throws Exception{
		
		String name = paramVo.getName();
		String depName = paramVo.getDepName();
		String post = paramVo.getPostName();
		String userName = paramVo.getUserName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		if (StringUtils.isNotEmpty(userName)) {
			map.put("userName", userName);
		}
		if (StringUtils.isNotEmpty(depName)) {
			map.put("deptId", depName);
		}
		if (StringUtils.isNotEmpty(post)) {
			map.put("postId", post);
		}
		
		//公司 部门
		if(paramVo.getDeptIds() !=null && !paramVo.getDeptIds().isEmpty()){
			
			String[] ids = paramVo.getDeptIds().split(",");
			List<Integer> idList = new ArrayList<Integer>();
			
			for (String id : ids) {
				idList.add(Integer.parseInt(id));
			}
			
			map.put("deptId", idList);
		}
		
		//岗位
		if(paramVo.getPostIds() !=null && !paramVo.getPostIds().isEmpty()){
			
			String[] ids = paramVo.getPostIds().split(",");
			List<Integer> idList = new ArrayList<Integer>();
			
			for (String id : ids) {
				idList.add(Integer.parseInt(id));
			}
			
			map.put("postId", idList);
		}
		
		//null 表示所有人都满足条件，[]表示没有满足条件的人
		
		List<ManageUserBean> userList = null;
		if(map.size()>0){
			userList = manageUserService.findUserByListCondition(map);
		}
		
		return userList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailList <BR>
	 * Description: 获取成绩预览列表 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @param curUserIdStr
	 * @return
	 * @throws Exception  List<ExamAssignBean><BR>
	 */
	@Override
	@Transactional
	public List<ExamAssignBean> getGradeDetailList(cjylSearchVo paramVo, String curUserIdStr) throws Exception {

		List<ManageUserBean> userList = getGradeDetailUserList(paramVo);
		
		if(userList !=null && userList.isEmpty()){
			return new ArrayList<ExamAssignBean>();
		}
		
		paramVo.setUserList(userList);
		List<ExamAssignBean> resultList = reportFormsMapper.getGradeDetailList(paramVo);
		for (ExamAssignBean assignBean : resultList) {
			int userId  = assignBean.getUserId();
			//ManageUserBean userBean = manageUserService.findUserById(userId+"");
			ManageUserBean userBean = manageUserService.findUserByIdCondition(userId+"");
			/*Integer depId = 0;
			String name2 = "暂无";
			String userName = "暂无";
			String postName = "暂无";
			String departmentName = "暂无";
			Integer postId = 0;
			if(userBean != null){
				depId = userBean.getDeptId();
				name2 = userBean.getName();
				userName = userBean.getUserName();
				postId = userBean.getPostId();
				//获取部门名称
				if(depId != null){
					ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
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
			}*/
			
			if(userBean !=null){
				assignBean.setDepartmentName(userBean.getDeptName());
				assignBean.setPostName(userBean.getPostName());
				assignBean.setUserName(userBean.getUserName());
				assignBean.setName(userBean.getName());
			}
			
		}
		return resultList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailCount <BR>
	 * Description: 获取成绩预览列表条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int getGradeDetailCount(cjylSearchVo paramVo) throws Exception {
		
		List<ManageUserBean> userList = getGradeDetailUserList(paramVo);
		
		if(userList !=null && userList.isEmpty()){
			return 0;
		}
		
		paramVo.setUserList(userList);
		return reportFormsMapper.getGradeDetailCount(paramVo);
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getGradeDetailByDeptList(com.jftt.wifi.bean.exam.vo.cjylSearchVo) <BR>
	 * Method name: getGradeDetailByDeptList <BR>
	 * Description: 获取考试统计列表（按部门） <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public List<DeptExamVo> getExamDetailByDeptList(cjylSearchVo paramVo) throws Exception {
		// TODO Auto-generated method stub
		List<DeptExamVo> resultList = new ArrayList<DeptExamVo>();
		//先查询出部门列表
		/*Map<String,String> map = new HashMap<String,String>();
		map.put("companyId", String.valueOf(paramVo.getCompanyId()));
		map.put("subCompanyId", String.valueOf(paramVo.getSubCompanyId()));
		map.put("name",paramVo.getDepName());
		int companyId = paramVo.getCompanyId();
		int subCompanyId = paramVo.getSubCompanyId();
		long fromNum = paramVo.getFromNum();
		String pageSize = paramVo.getPageSize();*/
		List<ManageDepartmentBean> deptList = reportFormsMapper.getDeptList(paramVo);
		//List<ManageDepartmentBean> deptList = manageDepartmentDaoMapper.getDepInfo(companyId, subCompanyId, fromNum, pageSize);
		for(int i=0;i<deptList.size();i++){
			DeptExamVo vo = new DeptExamVo();
			int deptId = (int) deptList.get(i).getId();
			vo.setId(deptId);
			vo.setDeptName(deptList.get(i).getName());
			Map tempMap = new HashMap();
			tempMap.put("deptId", deptId);
			List<ManageUserBean> userList =  manageUserService.findUserByListCondition(tempMap);
			vo.setUserNum(userList.size());
			if(!userList.isEmpty()){
				paramVo.setUserList(userList);
				int attendNum = reportFormsMapper.getDeptExamPersonNum(paramVo);
				int attendExamNum = reportFormsMapper.getDeptExamNum(paramVo);
				vo.setAttendNum(attendNum);
				vo.setAttendExamNum(attendExamNum);
			}else{
				vo.setAttendNum(0);
				vo.setAttendExamNum(0);
			}
			if(userList.isEmpty()){
				vo.setAttendPercent(0);
			}else{
				Integer attendPercent = (int)Math.round((vo.getAttendNum()*1.0/userList.size())*100);
				vo.setAttendPercent(attendPercent);
			}
			resultList.add(vo);
		}
		//排名
		List<Integer> percentList = new ArrayList<Integer>();
		for(int i=0;i<resultList.size();i++){
			if(!percentList.contains(resultList.get(i).getAttendPercent())){
				percentList.add(resultList.get(i).getAttendPercent());
			}
		}
		Collections.sort(percentList,Collections.reverseOrder());
		for(DeptExamVo examVo : resultList){
			Integer per = examVo.getAttendPercent();
			for(int i=0;i<percentList.size();i++){
				if(per.equals(percentList.get(i))){
					examVo.setRank(i+1);
				}
			}
		}
		//排序
		paramVo.setDeptExamVoList(resultList);
		resultList = reportFormsMapper.getDeptExamVoList(paramVo);
		return resultList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailByDeptListCount <BR>
	 * Description: 获取考试统计列表（按部门）条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  List<DeptExamVo><BR>
	 */
	@Override
	public int getExamDetailByDeptListCount(cjylSearchVo paramVo) throws Exception {
		// TODO Auto-generated method stub
		/*Map<String,String> map = new HashMap<String,String>();
		map.put("companyId", String.valueOf(paramVo.getCompanyId()));
		map.put("subCompanyId", String.valueOf(paramVo.getSubCompanyId()));
		map.put("name",paramVo.getDepName());
		int companyId = paramVo.getCompanyId();
		int subCompanyId = paramVo.getSubCompanyId();*/
		int count =  reportFormsMapper.getDeptListCount(paramVo);
		return count;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getGradeDetailByDeptListCount(com.jftt.wifi.bean.exam.vo.cjylSearchVo) <BR>
	 * Method name: getGradeDetailByDeptListCount <BR>
	 * Description: 获取部门考试详情条数  <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public int getGradeDetailByDeptListCount(cjylSearchVo paramVo) throws Exception {

		List<ManageUserBean> userList = getGradeDetailUserList(paramVo);
		
		if(userList !=null && userList.isEmpty()){
			return 0;
		}

		paramVo.setUserList(userList);
		int count = reportFormsMapper.getDeptExamDetailCount(paramVo);
		return count;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getGradeDetailByDeptList(com.jftt.wifi.bean.exam.vo.cjylSearchVo) <BR>
	 * Method name: getGradeDetailByDeptList <BR>
	 * Description: 获取部门考试详情记录  <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public List<DeptExamDetailVo> getGradeDetailByDeptList(cjylSearchVo paramVo) throws Exception {

		List<DeptExamDetailVo> resultList = new ArrayList<DeptExamDetailVo>();
		
		List<ManageUserBean> userList = getGradeDetailUserList(paramVo);
		
		if(userList !=null && userList.isEmpty()){
			return resultList;
		}
		
		paramVo.setUserList(userList);
		
		resultList = reportFormsMapper.getDeptExamDetail(paramVo);
		for(DeptExamDetailVo vo : resultList){
			int userId  = vo.getUserId();
			//ManageUserBean userBean = manageUserService.findUserById(userId+"");
			ManageUserBean userBean = manageUserService.findUserByIdCondition(userId+"");
			
			/*Integer depId = 0;
			String name2 = "暂无";
			String userName2 = "暂无";
			String postName = "暂无";
			String departmentName = "暂无";
			Integer postId = 0;
			if(userBean != null){
				depId = userBean.getDeptId();
				name2 = userBean.getName();
				userName2 = userBean.getUserName();
				postId = userBean.getPostId();
				//获取部门名称
				if(depId != null){
					ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
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
			}*/
			
			if(userBean !=null){
				
				vo.setDeptName(userBean.getDeptName());
				vo.setPost(userBean.getPostName());
				vo.setUserName(userBean.getUserName());
				vo.setName(userBean.getName());
			}
		}
		return resultList;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getWrongDetailListCount(com.jftt.wifi.bean.exam.vo.cjylSearchVo) <BR>
	 * Method name: getWrongDetailListCount <BR>
	 * Description: 获取错题分析列表条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  <BR>
	*/
	
	@Override
	public int getWrongDetailListCount(cjylSearchVo paramVo) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.ReportFormsService#getWrongDetailList(com.jftt.wifi.bean.exam.vo.cjylSearchVo) <BR>
	 * Method name: getWrongDetailList <BR>
	 * Description: 获取错题分析列表 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  <BR>
	*/
	
	@Override
	public List<ReportWrongDetailVo> getWrongDetailList(cjylSearchVo paramVo) {
		// TODO Auto-generated method stub
		//先根据考试ID查询出试题id
		Integer examId = Integer.parseInt(paramVo.getExamId());
		return null;
	}

	
	/** zhangchen end */
	
	/**
	 * Method name: getLearnALL <BR>
	 * Description: getLearnALL <BR>
	 * Remark: 获得 总的学习情况 <BR>
	 * @param searchVo
	 * @return  List<Map<String,Object>><BR>
	 */
	@Override
	public List<StuStudyInfoFormVo> getLearnALL(long companyId, List<Integer> userList){
		
		return reportFormsMapper.getLearnALL(companyId, userList);
	}
	
	/**
	 * Method name: getLearnPoint <BR>
	 * Description: getLearnPoint <BR>
	 * Remark: 获得总的指定学习的情况<BR>
	 * @param companyId
	 * @param userList
	 * @return  List<StuStudyInfoFormVo><BR>
	 */
	@Override
	public List<StuStudyInfoFormVo> getLearnPoint(long companyId, List<Integer> userList){
		
		return reportFormsMapper.getLearnPoint(companyId, userList);
	}
	
	/**
	 * 获得平均
	 */
	public StuStudyInfoFormVo getLearn(long companyId){
		
		int size = 0;
		ManageCompanyBean companyBean = null;
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			//公司人数
			size = manageUserService.findUserCountByCondition(map);
			
			//需要 过滤掉 集团管理员
			companyBean = companyService.selectCompanyById(Integer.parseInt(companyId+""));
			if(companyBean !=null){
				size--;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//有指派课程的人
		List<Integer> userIdList = reportFormsMapper.selectUserIdHavePoint(companyId);
		
		/*StuStudyInfoFormVo all = reportFormsMapper.getLearnALL(companyId, null).get(0);
		StuStudyInfoFormVo point = reportFormsMapper.getLearnPoint(companyId, null).get(0);
		
		double toltalCourse = Double.parseDouble(all.getTotalCourse());
		all.setTotalCourse(toltalCourse/size + "");
		
		double totalTime = Double.parseDouble(all.getTotalTime());
		all.setTotalTime(totalTime/size + "");
		
		double totalCredit = Double.parseDouble(all.getTotalCredit());
		all.setTotalCredit(totalCredit/size + "");
		
		double pointCourse = Double.parseDouble(point.getFinishCourseByAppoint());
		all.setFinishCourseByAppoint(pointCourse/size + ""); 		  // 所有学员完成指派总课程数 
		
		double pointTime = Double.parseDouble(point.getFinishCourseTimeByAppoint());
		all.setFinishCourseTimeByAppoint(pointTime/size + ""); // 所有学员完成指派总课程学时
		
		double pointCredit = Double.parseDouble(point.getGetCreditByAppoint());
		all.setGetCreditByAppoint(pointCredit/size + ""); 			  // 所有学员完成指派总课程学分
		
		all.setFinishCourseBySelf((toltalCourse-pointCourse)/size + "");       // 所有学员完成自学总课程数 
		all.setFinishCourseTimeBySelf((totalTime-pointTime)/size + "");	  // 所有学员完成自学总课程学时
		all.setGetCreditBySelf((totalCredit-pointCredit)/size + "");		  // 所有学员完成自学总课程学分
		
		all.setName("平均");*/
		
		StuStudyInfoFormVo all = reportFormsMapper.getLearnALL(companyId, null).get(0);
		
		//指派课程
		List<StuStudyInfoFormVo> pointList = new ArrayList<StuStudyInfoFormVo>();
		
		for (Integer userId : userIdList) {
			
			if(userId !=null){
				
				if(companyBean !=null && companyBean.getInitUserId().intValue() == userId.intValue()){
					continue;
				}
				
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(userId);
				
				List<StuStudyInfoFormVo> userPointList = reportFormsMapper.getLearnPoint(companyId, idList);
				
				pointList.addAll(userPointList);
			}
		}

		double pointCourse = 0;
		double pointTime = 0;
		double pointCredit = 0;
		
		for (StuStudyInfoFormVo point : pointList) {
			pointCourse += Double.parseDouble(point.getFinishCourseByAppoint());
			pointTime += Double.parseDouble(point.getFinishCourseTimeByAppoint());
			pointCredit += Double.parseDouble(point.getGetCreditByAppoint());
		}
		
		double toltalCourse = Double.parseDouble(all.getTotalCourse());
		all.setTotalCourse(toltalCourse/size + "");
		
		double totalTime = Double.parseDouble(all.getTotalTime());
		all.setTotalTime(totalTime/size + "");
		
		double totalCredit = Double.parseDouble(all.getTotalCredit());
		all.setTotalCredit(totalCredit/size + "");
		
		all.setFinishCourseByAppoint(pointCourse/size + ""); 		  // 所有学员完成指派总课程数 
		
		all.setFinishCourseTimeByAppoint(pointTime/size + ""); // 所有学员完成指派总课程学时
		
		all.setGetCreditByAppoint(pointCredit/size + ""); 			  // 所有学员完成指派总课程学分
		
		all.setFinishCourseBySelf((toltalCourse-pointCourse)/size + "");       // 所有学员完成自学总课程数 
		all.setFinishCourseTimeBySelf((totalTime-pointTime)/size + "");	  // 所有学员完成自学总课程学时
		all.setGetCreditBySelf((totalCredit-pointCredit)/size + "");		  // 所有学员完成自学总课程学分
		
		all.setName("平均");
		
		return all;
	}
	
	/**
	 * 考试 统计 获取数量
	 */
	@Override
	public int getExamUserCount(ExamUserVo examUserVo){
		
		return reportFormsMapper.getExamUserCount(examUserVo);
	}
	
	/**
	 * 考试 统计 获取数居
	 */
	@Override
	public List<ExamUserVo> getExamUser(ExamUserVo examUserVo){
		
		return reportFormsMapper.getExamUser(examUserVo);
	}
}
