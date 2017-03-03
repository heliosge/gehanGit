/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ApproveManageService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月30日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.jftt.wifi.bean.ApproveBean;
import com.jftt.wifi.bean.ApproveRecordBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ShareRecordBean;

/**
 * class name:ApproveManageService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月30日
 * @author JFTT)liucaibao
 */
public interface ApproveManageService {

	/**
	 * Method name:保存审批流程的方法
	 * Description: saveApproveInfo <BR>
	 * Remark: <BR>
	 * @param approveBean
	 * @throws Exception  void<BR>
	 */
	public void  saveApproveInfo(ApproveBean approveBean) throws Exception;

	public JSONArray	querySubCompanyList(int companyId)  throws Exception;
	
	public JSONArray queryCompanyList()throws Exception;
	/**
	 * Method name: 查询公司的审批流程。
	 * Description: queryApproveInfo <BR>
	 * Remark: <BR>
	 * @param approveBean
	 * @return  ApproveBean<BR>
	 */
	public List<ApproveBean> queryApproveInfo(ApproveBean approveBean);
	
	
	/**
	 * Method name: 查询共享的知识列表和总数
	 * Description: queryKnowledgeCount <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  int<BR>
	 */
	public List<KnowledgeBean> queryKnowledge(Map<String,Object> param);
	public int queryKnowledgeCount(Map<String,Object> param);
	
	public List<ResCourseBean> queryApproveCourseList(Map<String,Object> param);
	public int queryApproveCourseCount(Map<String,Object> param);
	
	public List<OamTopicBean> queryApproveTopicList(Map<String,Object> param);
	public int queryApproveTopicCount(Map<String,Object> param);
	
	
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
	 * Method name: 进行对象的审批
	 * Description: approveObj <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @throws Exception  void<BR>
	 */
	public void approveObj(ApproveRecordBean approveRecordBean) throws Exception;
	/**
	 * Method name: 普连用户进行对象的审批
	 * Description: approveObjByPL <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @throws Exception  void<BR>
	 */
	public void approveObjByPL(ApproveRecordBean approveRecordBean) throws Exception;
	
	
	/**
	 * Method name:进行提交对象的共享
	 * Description: shareObj <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @throws Exception  void<BR>
	 */
	public void shareObj(ApproveRecordBean approveRecordBean) throws Exception;
	
	
	/**
	 * Method name: startWorkFlow <BR>
	 * Description: 对外提供共享接口 <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @throws Exception  void<BR>
	 */
	public void startWorkFlow(KnowledgeBean knowledgeBean)throws Exception;
	public  void  startWorkFlow(ResCourseBean resCourseBean) throws Exception;
	/**
	 * Method name: 查询待共享的知识总和和共享列表
	 * Description: queryShareKLCount <BR>
	 * Remark: <BR>
	 * @param knowledgeBean
	 * @return  int<BR>
	 */
	public int queryShareKLCount(KnowledgeBean knowledgeBean) throws Exception;
	public List<KnowledgeBean> queryShareKL(KnowledgeBean knowledgeBean) throws Exception;
	
	
	/**
	 * Method name: 查询待共享的课程总和和课程列表
	 * Description: queryShareCourseCount <BR>
	 * Remark: <BR>
	 * @param resCourseBean
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int queryShareCourseCount(ResCourseBean resCourseBean) throws Exception;
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
	 * Method name: queryShareRecordCount <BR>
	 * Description: 查询共享记录信息 <BR>
	 * Remark: <BR>
	 * @param approveRecordBean
	 * @return  int<BR>
	 */
	int queryShareRecordCount(ApproveRecordBean approveRecordBean);
	List<ShareRecordBean> queryShareRecord(ApproveRecordBean approveRecordBean);
	
	/**
	 * Method name: getUserCompanyName <BR>
	 * Description: 查询一个用户的公司名称 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  String<BR>
	 */
	public String getUserCompanyName(Map<String,String> param);
}
