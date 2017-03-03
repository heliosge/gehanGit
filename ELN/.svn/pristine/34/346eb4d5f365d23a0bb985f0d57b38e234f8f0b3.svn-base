/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ApproveManageDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月30日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ApproveBean;
import com.jftt.wifi.bean.ApproveRecordBean;
import com.jftt.wifi.bean.ApproveStepBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ShareRecordBean;

/**
 * class name:ApproveManageDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月30日
 * @author JFTT)liucaibao
 */
public interface ApproveManageDaoMapper {
	
	
	
	public List<Map> querySubCompanyList(@Param("companyId")int companyId);
	public List<Map> queryCompanyList();

	
	/**
	 * Method name: 查询审批路线数据
	 * Description: queryApproveWay <BR>
	 * Remark: <BR>
	 * @param approveBean
	 * @return  ApproveBean<BR>
	 */
	public ApproveBean queryApproveWay(ApproveBean approveBean);
	public List<ApproveBean> queryApproveWayList(ApproveBean approveBean);
	
	
	/**
	 * Method name: 查询审核步骤信息
	 * Description: queryApproveStep <BR>
	 * Remark: <BR>
	 * @param wayId
	 * @return  List<ApproveStepBean><BR>
	 */
	public List<ApproveStepBean> queryApproveStep(@Param("wayId")int wayId);
	
	/**
	 * Method name: 查询审核应用的部门id
	 * Description: queryApproveDept <BR>
	 * Remark: <BR>
	 * @param wayId
	 * @return  List<Integer><BR>
	 */
	public List<Integer> queryApproveDept(@Param("wayId")int wayId);
	
	/**
	 * Method name: insertApproveWay <BR>
	 * Description: insertApproveWay <BR>
	 * Remark: <BR>
	 * @param approveBean
	 * @return  int<BR>
	 */
	public void insertApproveWay(ApproveBean approveBean);

	/**
	 * Method name: insertApproveStep <BR>
	 * Description: insertApproveStep <BR>
	 * Remark: <BR>
	 * @param approveStepBean  void<BR>
	 */
	public void insertApproveStep(ApproveStepBean approveStepBean);
	
	/**
	 * Method name: insertApprovDept <BR>
	 * Description: insertApprovDept <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void  insertApprovDept(Map map);
	
	
	/**
	 * Method name: 更新审批步骤表
	 * Description: updateApproveWay <BR>
	 * Remark: <BR>
	 * @param approveBean  void<BR>
	 */
	public void updateApproveWay(ApproveBean approveBean);
	
	
	
	/**
	 * Method name: 删除审批步骤表
	 * Description: deleteApproveInfo <BR>
	 * Remark: <BR>
	 * @param wayId  void<BR>
	 */
	public void deleteApproveStep(@Param("wayId")int wayId);
	
	/**
	 * Method name: 删除审批部门
	 * Description: deleteApproveDept <BR>
	 * Remark: <BR>
	 * @param wayId  void<BR>
	 */
	public void deleteApproveDept(@Param("wayId")int wayId);
	
	
	/**
	 * Method name: 查询知识审批列表
	 * Description: queryKnowledge <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  List<KnowledgeBean><BR>
	 */
	public List<KnowledgeBean> queryKnowledge(Map<String,Object> param);
	public int queryKnowledgeCount(Map<String,Object> param);
	
	public List<ResCourseBean> queryApproveCourseList(Map<String,Object> param);
	public int queryApproveCourseCount(Map<String,Object> param);
	
	public List<OamTopicBean> queryApproveTopicList(Map<String,Object> param);
	public int queryApproveTopicCount(Map<String,Object> param);
	
	
	/**
	 * Method name: queryApproverecordId <BR>
	 * Description: 查找记录id <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return  int<BR>
	 */
	public int queryApproverecordId(ApproveRecordBean approveRecordBean);
	
	/**
	 * Method name: queryParentPostId <BR>
	 * Description: queryParentPostId <BR>
	 * Remark: <BR>
	 * @param postId
	 * @return  int<BR>
	 */
	public String queryParentPostId(@Param("postId") int postId);
	/**
	 * Method name: 更新审核记录表中的数据
	 * Description: updateRecordStatus <BR>
	 * Remark: <BR>
	 * @param approveRecordBean  void<BR>
	 */
	public void updateRecordStatus(ApproveRecordBean approveRecordBean);
	
	/**
	 * Method name: 查找当前节点信息
	 * Description: queryNextStepInfo <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return  ApproveStepBean<BR>
	 */
	public ApproveStepBean queryCurrStepInfo(ApproveRecordBean approveRecordBean);
	/**
	 * Method name: 查找下一个审核节点
	 * Description: queryNextStepInfo <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return  ApproveStepBean<BR>
	 */
	public ApproveStepBean queryNextStepInfo(ApproveRecordBean approveRecordBean);
	
	/**
	 * Method name: 插入审核记录表数据
	 * Description: insertApproveRecord <BR>
	 * Remark: <BR>
	 * @param approveRecordBean  void<BR>
	 */
	public void insertApproveRecord(ApproveRecordBean approveRecordBean);
	
	/**
	 * Method name: 以下三个方法为更新课程知识专题的状态
	 * Description: updateCourseStatus <BR>
	 * Remark: <BR>
	 * @param approveRecordBean  void<BR>
	 */
	public void updateCourseStatus(ApproveRecordBean approveRecordBean);
	public void updateKnowledgeStatus(ApproveRecordBean approveRecordBean);
	public void updateTopicStatus(ApproveRecordBean approveRecordBean);
	
	/**
	 * Method name: 查询待共享的知识列表
	 * Description: queryShareKLCount <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  Integer<BR>
	 */
	public Integer queryShareKLCount(KnowledgeBean knowledgeBean)throws Exception;;
	public List<KnowledgeBean> queryShareKL(KnowledgeBean knowledgeBean)throws Exception;;
	
	
	/**
	 * Method name:查询待共享的课程列表
	 * Description: queryShareCourseCount <BR>
	 * Remark: <BR>
	 * @param resCourseBean
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	public Integer queryShareCourseCount(ResCourseBean resCourseBean)throws Exception;;
	public List<ResCourseBean> queryShareCourse(ResCourseBean resCourseBean) throws Exception;
	
	/**
	 * Method name: queryShareTopicCount <BR>
	 * Description: 查找共享列表中的专题 <BR>
	 * Remark: <BR>
	 * @param oamTopicBean
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int queryShareTopicCount(OamTopicBean oamTopicBean) throws Exception;
	public List<OamTopicBean>   queryShareTopic(OamTopicBean oamTopicBean)throws Exception;
	
	
	/**
	 * Method name: 查询共享给普连的知识列表和总数
	 * Description: queryKnowledgeCount <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  int<BR>
	 */
	public List<KnowledgeBean> queryKnowledgeByPL(Map<String,Object> param);
	public int queryKnowledgeCountByPL(Map<String,Object> param);
	
	public List<ResCourseBean> queryApproveCourseListByPL(Map<String,Object> param);
	public int queryApproveCourseCountByPL(Map<String,Object> param);
	
	public List<OamTopicBean> queryApproveTopicListByPL(Map<String,Object> param);
	public int queryApproveTopicCountByPL(Map<String,Object> param);
	
	
	
	/**
	 * Method name: queryComapnyName <BR>
	 * Description: 查询公司和子公司的名称 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  String<BR>
	 */
	public String queryComapnyName(Map<String, String> param);
	public String querySubComapnyName(Map<String, String> param);
	
	
	
	/**
	 * Method name: queryShareRecordCount <BR>
	 * Description: 查询共享记录信息 <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return  int<BR>
	 */
	public int	queryShareRecordCourseCount(ApproveRecordBean approveRecordBean);
	public int	queryShareRecordKLCount(ApproveRecordBean approveRecordBean);
	public int	queryShareRecordTopicCount(ApproveRecordBean approveRecordBean);
	public List<ShareRecordBean> queryShareRecordCourseList(ApproveRecordBean approveRecordBean);
	public List<ShareRecordBean> queryShareRecordKLList(ApproveRecordBean approveRecordBean);
	public List<ShareRecordBean> queryShareRecordTopicList(ApproveRecordBean approveRecordBean);
	
}
