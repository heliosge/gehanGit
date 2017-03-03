/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InvestigationServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-30        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.InvestigationVoBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.bean.vo.ExamGradeVo;
import com.jftt.wifi.dao.InvestigationAssignDaoMapper;
import com.jftt.wifi.dao.InvestigationDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.service.InvestigationService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.QuestionnaireService;

/**
 * @author JFTT)zhangchen<BR>
 * class name:InvestigationServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-30
 */
@Service
public class InvestigationServiceImpl implements InvestigationService{
	
	@Autowired
	private InvestigationDaoMapper investigationDaoMapper;
	@Autowired
	private InvestigationAssignDaoMapper investigationAssignDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Autowired
	private QuestionnaireService questionnaireService;
	@Resource(name="manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#getList(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getList <BR>
	 * Description: 获取调查管理列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public List<InvestigationBean> getList(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		return investigationDaoMapper.getVoList(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#getListCount(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getListCount <BR>
	 * Description: 获取调查管理列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public int getTotalCount(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		return investigationDaoMapper.getTotalCount(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#add(com.jftt.wifi.bean.questionnaire.InvestigationBean) <BR>
	 * Method name: add <BR>
	 * Description: 新增调查 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public void add(InvestigationBean newBean) throws Exception {
		// TODO Auto-generated method stub
		newBean.setIsDeleted(0);
		if("".equals(newBean.getEndTime())){
			newBean.setEndTime(null);
		}
		investigationDaoMapper.addInvestigation(newBean);
		addAssignInfo(newBean);
			
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addAssignInfo <BR>
	 * Description: 添加调查人员信息 <BR>
	 * Remark: <BR>
	 * @param newBean  void<BR>
	 * @throws Exception 
	 */
	private void addAssignInfo(InvestigationBean newBean) throws Exception {
		Assert.notNull(newBean.getId());
		if(newBean.getIntendType() == 2){//按组织架构
			if(!newBean.getUserList().isEmpty() && newBean.getUserList().get(0) != null){
				for (ManageUserBean userBean : newBean.getUserList()) {
					// 用于保存调查人员信息
					InvestigationAssignBean newAssignInfo = InvestigationAssignBean.generateAssignInfo(newBean, Integer.parseInt(userBean.getId()));
					investigationAssignDaoMapper.add(newAssignInfo);
				}
			}
		}else{//全员
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyId", newBean.getCompanyId());
			map.put("subCompanyId", newBean.getSubCompanyId());
			List<ManageUserBean> userList = manageUserService.findUserByListCondition(map);
			for (ManageUserBean userBean : userList) {
				// 用于保存调查人员信息
				InvestigationAssignBean newAssignInfo = InvestigationAssignBean.generateAssignInfo(newBean, Integer.parseInt(userBean.getId()));
				investigationAssignDaoMapper.add(newAssignInfo);
			}
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: delete <BR>
	 * Description: 删除调查 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	@Transactional
	public void delete(List<Integer> idList){
		if (idList != null) {
			for (Integer id : idList) {
				//删除调查基本信息
				investigationDaoMapper.deleteInvestigation(id);
				//删除调查参与人员,1表示删除参与调查问卷人员
				investigationAssignDaoMapper.delete(id,1);
			}
		}
		
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#getUserByInvestigationId(int) <BR>
	 * Method name: getUserByInvestigationId <BR>
	 * Description: 判断发布调查时，是否有添加参与人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public boolean getUserByInvestigationId(int id) {
		// TODO Auto-generated method stub
		int count = investigationAssignDaoMapper.getUser(id,1);
		if(count > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: publish <BR>
	 * Description: 调查发布 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void publish(int id){
		investigationDaoMapper.publishInvestigation(id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: get <BR>
	 * Description: 获取调查基本信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  InvestigationBean<BR>
	 */
	@Override
	public InvestigationBean get(int id){
		InvestigationBean bean = investigationDaoMapper.getInvestigation(id);
		if(bean != null){
			Integer qId = bean.getQuestionnaireId();
			QuestionnaireBean qBean = questionnaireService.getQuestionnaire(qId);
			bean.setQuestionnaireName(qBean.getName());
		}
		return bean;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUser <BR>
	 * Description: 获取调查参与人员 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManageUserBean><BR>
	 * @throws Exception 
	 */
	@Override
	public List<ManageUserBean> getUser(int id) throws Exception{
		List<InvestigationAssignBean> assignList= investigationAssignDaoMapper.getUsers(id,1);
		List<ManageUserBean> list = new ArrayList<ManageUserBean>();
		if(!assignList.isEmpty()){
			List<String> idList = new ArrayList<String>();
			for(int i=0;i<assignList.size();i++){
				idList.add(assignList.get(i).getUserId().toString());
			}
			list = manageUserService.findUserByIds(idList);
		}
		return list;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#modifyInvestigation(com.jftt.wifi.bean.questionnaire.InvestigationBean) <BR>
	 * Method name: modifyInvestigation <BR>
	 * Description: 修改调查 <BR>
	 * Remark: <BR>
	 * @param iBean  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public void modifyInvestigation(InvestigationBean iBean) throws Exception {
		// TODO Auto-generated method stub
		if("".equals(iBean.getEndTime())){
			iBean.setEndTime(null);
		}
		investigationDaoMapper.modifyInvestigation(iBean);
		Assert.notNull(iBean.getId());
		if(iBean.getModifyType() == 1){//保存并发布
			//先删除调查参与人员
			investigationAssignDaoMapper.delete(iBean.getId(),1);
			//再添加人员
			addAssignInfo(iBean);
		}
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#getResultList(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getResultList <BR>
	 * Description: 获取结果统计列表数据 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public List<InvestigationVoBean> getResultList(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		List<InvestigationVoBean> list = investigationDaoMapper.getResultVoList(searchOption);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getVoTotalCount <BR>
	 * Description: 获取结果统计列表条数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  int<BR>
	 */
	@Override
	public int getVoTotalCount(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		return investigationDaoMapper.getVoTotalCount(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#getResultVo(int) <BR>
	 * Method name: getResultVo <BR>
	 * Description: 获取取结果汇总vo <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public InvestigationVoBean getResultVo(int id) {
		// TODO Auto-generated method stub
		InvestigationVoBean bean = investigationDaoMapper.getResultVo(id);
		return bean;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignList <BR>
	 * Description: 获取调查答卷人员列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  List<InvestigationAssignBean><BR>
	 */
	@Override
	public List<InvestigationAssignBean> getAssignList(SearchOptionBean searchOption) throws Exception{
		String name = searchOption.getUsername();
		String depName = searchOption.getDepartment();
		Map<String, Object> map =new HashMap<String, Object>();
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(depName!=null && !"".equals(depName)){
			map.put("deptId", depName);
		}
		List<ManageUserBean> userList = null;
		if(map.size() > 0){
			userList = manageUserService.findUserByListCondition(map);
			if (userList == null || userList.size() == 0) {
				return new ArrayList<InvestigationAssignBean>();
			}
		}
		searchOption.setUserList(userList);
		List<InvestigationAssignBean> assignList = investigationAssignDaoMapper.getUserAnswerList(searchOption);
		for(InvestigationAssignBean aBean : assignList){
			int userId = aBean.getUserId();
			ManageUserBean userBean = manageUserService.findUserById(userId+"");
			String username = "暂无";
			String deptName = "暂无";
			if(userBean != null){
				username = userBean.getName();
				Integer depId = userBean.getDeptId();
				//获取部门名称
				if(depId != null){
					ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
					if(dept != null){
						deptName = dept.getName();
					}
				}
				aBean.setName(username);
				aBean.setDepartment(deptName);
			}
		}
		return assignList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignListCount <BR>
	 * Description: 获取调查答卷人员列表条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int getAssignListCount(SearchOptionBean searchOption) throws Exception{
		String name = searchOption.getUsername();
		String depName = searchOption.getDepartment();
		Map<String, Object> map =new HashMap<String, Object>();
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(depName!=null && !"".equals(depName)){
			map.put("deptId", depName);
		}
		List<ManageUserBean> userList = null;
		if(map.size() > 0){
			userList = manageUserService.findUserByListCondition(map);
			if (userList == null || userList.size() == 0) {
				return 0;
			}
		}
		searchOption.setUserList(userList);
		return investigationAssignDaoMapper.getUserAnswerListCount(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.InvestigationService#getInvestigationName(int) <BR>
	 * Method name: getInvestigationName <BR>
	 * Description: 根据ID获取调查标题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public String getInvestigationName(int id) {
		// TODO Auto-generated method stub
		return investigationDaoMapper.getInvestigationName(id);
	}
}
