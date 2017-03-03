/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentLearnMapService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月10日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.vo.CourseVoForPost;
import com.jftt.wifi.bean.vo.MyPostPromotionPathVo;
import com.jftt.wifi.bean.vo.PostExamVo;
import com.jftt.wifi.bean.vo.PromotionPathDetailVo;
import com.jftt.wifi.bean.vo.PromotionPathStageVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:StudentLearnMapService <BR>
 * class description: 学员学习地图service <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月10日
 * @author JFTT)ShenHaijun
 */
public interface StudentLearnMapService {
	
	/**
	 * Method name: getMyPromotionPathCount <BR>
	 * Description: 获取我的所有晋升路径的数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getMyPromotionPathCount(Integer userId,Integer companyId,Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getMyPromotionPath <BR>
	 * Description: 获取我的所有晋升路径 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyPostPromotionPathVo>
	 * @throws DataBaseException <BR>
	 */
	List<MyPostPromotionPathVo> getMyPromotionPath(Integer userId,Integer companyId, Integer subCompanyId, 
			Integer fromNum,Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getPathStages <BR>
	 * Description: 获取该路径所有阶段 <BR>
	 * Remark: <BR>
	 * @param promotionPathId 路径id
	 * @return List<PromotionPathStageVo>
	 * @throws DataBaseException <BR>
	 */
	List<PromotionPathStageVo> getPathStages(Integer promotionPathId) throws DataBaseException;
	
	/**
	 * Method name: getStageTest <BR>
	 * Description: 获取阶段测试 <BR>
	 * Remark: <BR>
	 * @param stageId 阶段id
	 * @param userId 用户id
	 * @return PostExamVo
	 * @throws DataBaseException <BR>
	 */
	PostExamVo getStageTest(Integer stageId,Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getPostCoursesCount <BR>
	 * Description: 获取岗位课程数量 <BR>
	 * Remark: <BR>
	 * @param postId 岗位id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getPostCoursesCount(Integer postId) throws DataBaseException;
	
	/**
	 * Method name: getPostCourses <BR>
	 * Description: 获取岗位所有课程 <BR>
	 * Remark: <BR>
	 * @param postId 岗位id
	 * @param userId 用户id
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return List<CourseVoForPost>
	 * @throws DataBaseException <BR>
	 */
	List<CourseVoForPost> getPostCourses(Integer postId, Integer userId,
			Integer page, Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getRoadDetailsCount <BR>
	 * Description: 获取晋升路径详细数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param searchContent 查询内容
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getRoadDetailsCount(Integer userId,String searchContent) throws DataBaseException;
	
	/**
	 * Method name: getRoadDetails <BR>
	 * Description: 获取晋升路径详细 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param searchContent 查询内容
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return List<PromotionPathDetailVo>
	 * @throws DataBaseException <BR>
	 */
	List<PromotionPathDetailVo> getRoadDetails(Integer userId,String searchContent, 
			Integer page, Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: saveAssignInfo <BR>
	 * Description: 保存试卷分配信息 <BR>
	 * Remark: <BR>
	 * @param examId 试卷id
	 * @param userId 用户id
	 * @throws DataBaseException  void<BR>
	 */
	void saveAssignInfo(Integer examId, Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getPostIdByStageId <BR>
	 * Description: 查出当前阶段对应的岗位id <BR>
	 * Remark: <BR>
	 * @param stageId 阶段id
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	Integer getPostIdByStageId(Integer stageId) throws DataBaseException;
	
	/**
	 * Method name: getPostById <BR>
	 * Description: 根据id查询岗位 <BR>
	 * Remark: <BR>
	 * @param postId 岗位id
	 * @return ManagePostBean
	 * @throws Exception <BR>
	 */
	ManagePostBean getPostById(Integer postId) throws Exception;
	
}
