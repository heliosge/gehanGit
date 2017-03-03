/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ReportFormsMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-1-11        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCourseOpenCrowdBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.vo.DeptExamDetailVo;
import com.jftt.wifi.bean.exam.vo.DeptExamVo;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.MarkExamListItemVo;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.bean.vo.CourseDetailByCourseVo;
import com.jftt.wifi.bean.vo.CourseDetailByUserVo;
import com.jftt.wifi.bean.vo.ExamUserVo;
import com.jftt.wifi.bean.vo.ReportFormsParam;
import com.jftt.wifi.bean.vo.StuStudyInfoFormVo;
import com.jftt.wifi.bean.vo.StuTotalityInfoFormVo;

/**
 * class name:ReportFormsMapper <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-1-11
 * @author JFTT)chenrui
 */
/**
 * class name:ReportFormsMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2016-4-22
 * @author JFTT)wangjian
 */
public interface ReportFormsMapper {

	/** chenrui start */
	
	/**
	 * 
	 * Method name: getStuTotalityInfoFormCount <BR>
	 * Description: getStuTotalityInfoFormCount <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  void<BR>
	 */
	List<StuTotalityInfoFormVo> getStuTotalityInfoForm(@Param("params")ReportFormsParam params,@Param("users")List<ManageUserBean> users) throws Exception;

	/**
	 * 获取员工学习情况统计表
	 * Method name: getStuStudyInfoForm <BR>
	 * Description: getStuStudyInfoForm <BR>
	 * Remark: <BR>
	 * @param params
	 * @param users
	 * @return
	 * @throws Exception  List<StuStudyInfoFormVo><BR>
	 */
	List<StuStudyInfoFormVo> getStuStudyInfoForm(@Param("params")ReportFormsParam params,
			@Param("users")List<ManageUserBean> users,@Param("isSearchAll")int isSearchAll) throws Exception;
	/** chenrui end */
	
	/** zhangchen start */
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResCourse <BR>
	 * Description: 获取课程基本信息 <BR>
	 * Remark: <BR>
	 * @return  List<ResCourseBean><BR>
	 */
	List<ResCourseBean> getResCourse(ReportFormsParam params);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResCourseCount <BR>
	 * Description: 获取课程基本信息条数 <BR>
	 * Remark: <BR>
	 * @return  int<BR>
	 */
	int getResCourseCount(ReportFormsParam params);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getLearnNum <BR>
	 * Description: 获取正在学习人数或已完成学习人数 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param learnProcess
	 * @return  int<BR>
	 * 
	 * wj edit
	 * 增加参数 withManager：是否包括初始化的管理员, no:不包括
	 */
	int getLearnNum(@Param("courseId") Integer courseId,@Param("learnProcess") Integer learnProcess, @Param("withManager")String withManager);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: 获取课件学习总时长 <BR>
	 * Description:  <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return  int<BR>
	 */
	Integer getCourseTotalDuration(ReportFormsParam params);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOpenCrowd <BR>
	 * Description: 根据课程ID获取课程开放人群 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return  List<ResCourseOpenCrowdBean><BR>
	 */
	List<ResCourseOpenCrowdBean> getOpenCrowd(@Param("courseId") Integer courseId);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPlanUserId <BR>
	 * Description: 根据课程ID获取学习计划中安排的人员 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return  String<BR>
	 */
	String getPlanUserId(@Param("courseId") Integer courseId);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectValues <BR>
	 * Description: 查询课程名 <BR>
	 * Remark: <BR>
	 * @return  List<String><BR>
	 */
	List<String> selectValues();
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseInfo <BR>
	 * Description: 通过课程名查询课程信息 <BR>
	 * Remark: <BR>
	 * @param courseName
	 * @return  ResCourseBean<BR>
	 */
	ResCourseBean getCourseInfo(@Param("courseId") Integer courseId);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseStudyRecordByCourse <BR>
	 * Description: 通过课程名查询学习记录 <BR>
	 * Remark: <BR>
	 * @param courseName
	 * @return  CourseDetailByCourseVo<BR>
	 */
	List<CourseDetailByCourseVo> getCourseStudyRecordByCourse(ReportFormsParam params);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseStudyRecordByCourseCount <BR>
	 * Description: 通过课程名查询学习记录条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  int<BR>
	 */
	int getCourseStudyRecordByCourseCount(ReportFormsParam params);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseStudyRecordByUser <BR>
	 * Description: 通过用户ID查询学习记录 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  CourseDetailByUserVo<BR>
	 */
	List<CourseDetailByUserVo> getCourseStudyRecordByUser(ReportFormsParam params);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCourseStudyRecordByUserCount <BR>
	 * Description: 通过用户ID查询学习记录条数 <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  int<BR>
	 */
	int getCourseStudyRecordByUserCount(ReportFormsParam params);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamList <BR>
	 * Description: 获取考试统计列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  MarkExamListItemVo<BR>
	 */
	List<MarkExamListItemVo> getExamList(ExamSearchOptionVo searchOption);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamListCount <BR>
	 * Description: 获取考试统计列表条数 <BR>
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
	 * @return  List<ExamAssignBean><BR>
	 */
	List<ExamAssignBean> getGradeDetailList(cjylSearchVo paramVo);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeDetailCount <BR>
	 * Description: 获取成绩预览列表条数 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return  int<BR>
	 */
	int getGradeDetailCount(cjylSearchVo paramVo);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptList <BR>
	 * Description: 获取部门列表 <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> getDeptList(cjylSearchVo searchVo);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptListCount <BR>
	 * Description: 获取部门列表条数 <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  List<ManageDepartmentBean><BR>
	 */
	public int getDeptListCount(cjylSearchVo searchVo);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptExamPersonNum <BR>
	 * Description: 考试统计（按部门） 查询部门参与考试人数  <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  int<BR>
	 */
	int getDeptExamPersonNum(cjylSearchVo searchVo);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptExamNum <BR>
	 * Description: 考试统计（按部门） 查询部门参与考试数 <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  int<BR>
	 */
	int getDeptExamNum(cjylSearchVo searchVo);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptExamDetail <BR>
	 * Description: 考试统计（按部门） 部门详情 <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  List<DeptExamDetailVo><BR>
	 */
	List<DeptExamDetailVo> getDeptExamDetail(cjylSearchVo searchVo);
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptExamDetailCount <BR>
	 * Description: 考试统计（按部门） 部门详情条数 <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  int<BR>
	 */
	int getDeptExamDetailCount(cjylSearchVo searchVo);
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDeptExamVoList <BR>
	 * Description: getDeptExamVoList <BR>
	 * Remark: <BR>
	 * @param searchVo
	 * @return  List<DeptExamVo><BR>
	 */
	List<DeptExamVo> getDeptExamVoList(cjylSearchVo searchVo);
	/** zhangchen end */
	
	
	/**
	 * Method name: getLearnALL <BR>
	 * Description: getLearnALL <BR>
	 * Remark: 获得 总的学习情况 <BR>
	 * @param searchVo
	 * @return  List<Map<String,Object>><BR>
	 */
	List<StuStudyInfoFormVo> getLearnALL(@Param("companyId")long companyId, @Param("userList")List<Integer> userList);
	
	/**
	 * Method name: getLearnPoint <BR>
	 * Description: getLearnPoint <BR>
	 * Remark: 获得总的指定学习的情况<BR>
	 * @param companyId
	 * @param userList
	 * @return  List<StuStudyInfoFormVo><BR>
	 */
	List<StuStudyInfoFormVo> getLearnPoint(@Param("companyId")long companyId, @Param("userList")List<Integer> userList);
	
	/**
	 * 获得有指派课程的人
	 */
	List<Integer> selectUserIdHavePoint(@Param("companyId")long companyId);
	
	/**
	 * 考试 统计 获取数量
	 */
	int getExamUserCount(ExamUserVo examUserVo);
	
	/**
	 * 考试 统计 获取数居
	 */
	List<ExamUserVo> getExamUser(ExamUserVo examUserVo);
}	
