/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseEvaluationDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseEvaluationDaoMapper <BR>
 * class description: 课程评价dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public interface CourseEvaluationDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取课程评价 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  CourseEvaluationBean<BR>
	 */
	public CourseEvaluationBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getEvaluateCount <BR>
	 * Description: 获取该课程评价人数 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getEvaluateCount(@Param("courseId")Integer courseId, @Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseEvaluations <BR>
	 * Description: 查询课程所有评价 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @return
	 * @throws DataBaseException  List<CourseEvaluationBean><BR>
	 */
	public List<CourseEvaluationBean> getCourseEvaluations(@Param("courseId")Integer courseId,
			@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: addCourseEvaluation <BR>
	 * Description: 添加一条课程评价 <BR>
	 * Remark: <BR>
	 * @param courseEvaluationBean
	 * @throws DataBaseException  void<BR>
	 */
	public void addCourseEvaluation(CourseEvaluationBean courseEvaluationBean) throws DataBaseException;
	
	/**
	 * Method name: getMyEvaluate <BR>
	 * Description: 获取我的课程评价  <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @return
	 * @throws DataBaseException  CourseEvaluationBean<BR>
	 */
	public CourseEvaluationBean getMyEvaluate(@Param("userId")Integer userId, @Param("courseId")Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: updateCourseEvaluation <BR>
	 * Description: 更新评价信息 <BR>
	 * Remark: <BR>
	 * @param evaluation
	 * @throws DataBaseException  void<BR>
	 */
	public void updateCourseEvaluation(CourseEvaluationBean evaluation) throws DataBaseException;
	
	/**
	 * Method name: deleteRecord <BR>
	 * Description: 删除学员该课程的评价记录 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteRecord(@Param("courseId")String courseId, @Param("userId")String userId) throws DataBaseException;

	
	/**shenhaijun end*/
	
	/** chenrui start */
	/**
	 * 获取课程评价信息
	 * Method name: getMallEvaluationCount <BR>
	 * Description: getMallEvaluationCount <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @return  int<BR>
	 */
	public List<CourseEvaluationBean> getMallEvaluation(@Param("userId")String userId, @Param("courseId")String courseId,
			@Param("pageSize")String pageSize, @Param("fromNum")long fromNum) throws DataBaseException;
	public int getMallEvaluationCount(@Param("userId")String userId, @Param("courseId")String courseId) throws DataBaseException;

	/**
	 * 检测是否已评价过
	 * Method name: checkAlreadyEvaluate <BR>
	 * Description: checkAlreadyEvaluate <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param tcourseId
	 * @return  int<BR>
	 */
	public CourseEvaluationBean checkAlreadyEvaluate(@Param("userId")String userId, @Param("courseId")int tcourseId) throws DataBaseException;

	public void giveEvaluate(CourseEvaluationBean courseEvaluationBean) throws DataBaseException;

	
	/**chenrui end */

}
