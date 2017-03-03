/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseEvaluateService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.bean.vo.CourseEvaluationVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseEvaluateService <BR>
 * class description: 课程评价service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 * @author JFTT)ShenHaijun
 */
public interface CourseEvaluateService {
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getEvaluateCount <BR>
	 * Description: 获取该课程评价人数 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	Integer getEvaluateCount(Integer courseId, Integer companyId,
			Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseEvaluations <BR>
	 * Description: 查询课程所有评价 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return
	 * @throws Exception  List<CourseEvaluationVo><BR>
	 */
	List<CourseEvaluationVo> getCourseEvaluations(Integer courseId,
			Integer companyId,Integer subCompanyId,Integer fromNum,Integer pageSize) throws Exception;
	
	/**
	 * Method name: addCourseEvaluation <BR>
	 * Description: 添加一条课程评价 <BR>
	 * Remark: <BR>
	 * @param courseEvaluationBean 课程评价Bean
	 * @throws DataBaseException  void<BR>
	 */
	void addCourseEvaluation(CourseEvaluationBean courseEvaluationBean) throws DataBaseException;
	
	/**
	 * Method name: getMyEvaluate <BR>
	 * Description: 获取我的课程评价 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @return
	 * @throws Exception  CourseEvaluationBean<BR>
	 */
	CourseEvaluationBean getMyEvaluate(Integer userId, Integer courseId) throws Exception;
	
	/**
	 * Method name: giveMyScore <BR>
	 * Description: 给出我的评分 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param score 分数
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  void<BR>
	 */
	void giveMyScore(Integer userId, Integer courseId, Integer score, Integer companyId, Integer subCompanyId) throws Exception;
	
	/**
	 * Method name: giveMyEvaluation <BR>
	 * Description: 给出我的评价 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param content 内容
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  void<BR>
	 */
	void giveMyEvaluation(Integer userId, Integer courseId, String content,
			Integer companyId, Integer subCompanyId) throws Exception;
	
	/**shenhaijun end*/
	
}
