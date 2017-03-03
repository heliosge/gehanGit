/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ReportFormsService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-11        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ReportFormsService <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-11
 * @author JFTT)chenrui
 */
public interface ReportFormsService {

	/** chenrui start */
	
	/**
	 * 获取当前学员总体概况统计表 数据源
	 * Method name: getStuTotalityInfoFormCount <BR>
	 * Description: getStuTotalityInfoFormCount <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  int<BR>
	 */
	int getStuTotalityInfoFormCount(ReportFormsParam params) throws Exception;

	/**
	 * 获取当前学员总体概况统计表 数据源
	 * Method name: getStuTotalityInfoForm <BR>
	 * Description: getStuTotalityInfoForm <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<StuTotalityInfoFormVo><BR>
	 */
	List<StuTotalityInfoFormVo> getStuTotalityInfoForm(ReportFormsParam params) throws Exception;

	/**
	 * 获取员工学习情况统计表
	 * Method name: getStuStudyInfoForm <BR>
	 * Description: getStuStudyInfoForm <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws DataBaseException  List<StuStudyInfoFormVo><BR>
	 */
	
	int getStuStudyInfoFormCount(ReportFormsParam params) throws Exception;
	List<StuStudyInfoFormVo> getStuStudyInfoForm(ReportFormsParam params) throws Exception;
	
	/** chenrui end */
	
	/** zhangchen start */
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailReport <BR>
	 * Description: 获取课程学习情况统计 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<StuTotalityInfoFormVo><BR>
	 * @throws Exception 
	 */
	List<CourseDetailReportVo> getCourseDetailReport(ReportFormsParam params) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailReportCount <BR>
	 * Description: 获取课程学习情况统计条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  int<BR>
	 * @throws Exception 
	 */
	int getCourseDetailReportCount(ReportFormsParam params) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getValues <BR>
	 * Description: 查询课程名 <BR>
	 * Remark: <BR>
	 * @return  List<String><BR>
	 */
	List<String> getValues();
	/** zhangchen end */

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserNames <BR>
	 * Description: 获取用户姓名 <BR>
	 * Remark: <BR>
	 * @return  List<String><BR>
	 * @throws Exception 
	 */
	List<String> getUserNames() throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailByCourseCount <BR>
	 * Description: 获取学习明细表（按课程）记录条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  int<BR>
	 * @throws Exception 
	 */
	int getCourseDetailByCourseCount(ReportFormsParam params) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailByCourse <BR>
	 * Description: 获取学习明细表（按课程） <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<CourseDetailReportVo><BR>
	 * @throws Exception 
	 */
	List<CourseDetailByCourseVo> getCourseDetailByCourse(ReportFormsParam params) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseInfo <BR>
	 * Description: 获取课程基本信息 <BR>
	 * Remark: <BR>
	 * @param courseName
	 * @return  Object<BR>
	 */
	Object getCourseInfo(Integer courseId);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailByUserCount <BR>
	 * Description: 获取学习明细表（按学员）条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  int<BR>
	 */
	int getCourseDetailByUserCount(ReportFormsParam params);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseDetailByUser <BR>
	 * Description: 获取学习明细表（按学员） <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<CourseDetailByUserVo><BR>
	 * @throws DataBaseException 
	 */
	List<CourseDetailByUserVo> getCourseDetailByUser(ReportFormsParam params) throws DataBaseException;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExmaList <BR>
	 * Description: getExmaList <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<MarkExamListItemVo><BR>
	 */
	List<MarkExamListItemVo> getExamList(ExamSearchOptionVo searchOption);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExmaListCount <BR>
	 * Description: 获取考试统计列表条数  <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	int getExamListCount(ExamSearchOptionVo searchOption);

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
	List<ExamAssignBean> getGradeDetailList(cjylSearchVo paramVo,
			String curUserIdStr) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailCount <BR>
	 * Description: 获取成绩预览列表条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  int<BR>
	 */
	int getGradeDetailCount(cjylSearchVo paramVo) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamDetailByDeptList <BR>
	 * Description: 获取考试统计（按部门） <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  List<DeptExamVo><BR>
	 * @throws Exception 
	 */
	List<DeptExamVo> getExamDetailByDeptList(cjylSearchVo paramVo) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamDetailByDeptListCount <BR>
	 * Description: 获取考试统计（按部门）记录条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  List<DeptExamVo><BR>
	 */
	int getExamDetailByDeptListCount(cjylSearchVo paramVo)throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailByDeptListCount <BR>
	 * Description: 获取部门考试详情条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  int<BR>
	 * @throws Exception 
	 */
	int getGradeDetailByDeptListCount(cjylSearchVo paramVo) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailByDeptList <BR>
	 * Description: 获取部门考试详情记录 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  List<DeptExamDetailVo><BR>
	 * @throws Exception 
	 */
	List<DeptExamDetailVo> getGradeDetailByDeptList(cjylSearchVo paramVo) throws Exception;

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getWrongDetailListCount <BR>
	 * Description: 获取错题分析列表条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  int<BR>
	 */
	int getWrongDetailListCount(cjylSearchVo paramVo);

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getWrongDetailList <BR>
	 * Description: 获取错题分析列表 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  List<ReportWrongDetailVo><BR>
	 */
	List<ReportWrongDetailVo> getWrongDetailList(cjylSearchVo paramVo);
	
	/**
	 * Method name: getLearnALL <BR>
	 * Description: getLearnALL <BR>
	 * Remark: 获得 总的学习情况 <BR>
	 * @param searchVo
	 * @return  List<Map<String,Object>><BR>
	 */
	List<StuStudyInfoFormVo> getLearnALL(long companyId, List<Integer> userList);
	
	/**
	 * Method name: getLearnPoint <BR>
	 * Description: getLearnPoint <BR>
	 * Remark: 获得总的指定学习的情况<BR>
	 * @param companyId
	 * @param userList
	 * @return  List<StuStudyInfoFormVo><BR>
	 */
	List<StuStudyInfoFormVo> getLearnPoint(long companyId, List<Integer> userList);
	
	/**
	 * 获取当前公司所有用户
	 * Method name: getAllUserByCompanyId <BR>
	 * Description: getAllUserByCompanyId <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<ManageUserBean><BR>
	 * @throws Exception 
	 */
	public List<ManageUserBean> getUsers(ReportFormsParam params) throws Exception;
	
	/**
	 * 获得平均
	 */
	public StuStudyInfoFormVo getLearn(long companyId);
	
	/**
	 * 考试 统计 获取数量
	 */
	public int getExamUserCount(ExamUserVo examUserVo);
	
	/**
	 * 考试 统计 获取数居
	 */
	public List<ExamUserVo> getExamUser(ExamUserVo examUserVo);
}
