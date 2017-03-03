/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseGroupServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.vo.ManageGroupVo;
import com.jftt.wifi.common.DataBaseConstant;
import com.jftt.wifi.dao.ManageGroupDaoMapper;
import com.jftt.wifi.dao.ManageGroupStudentDaoMapper;
import com.jftt.wifi.dao.ManageNoticeDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.StudentGroupService;

/**
 * class name:CourseGroupServiceImpl <BR>
 * class description: 学员群组service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-20
 * @author JFTT)ShenHaijun
 */
@Service("studentGroupService")
public class StudentGroupServiceImpl implements StudentGroupService {
	@Resource(name="manageGroupDaoMapper")
	private ManageGroupDaoMapper manageGroupDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="manageNoticeDaoMapper")
	private ManageNoticeDaoMapper manageNoticeDaoMapper;
	@Resource(name="manageGroupStudentDaoMapper")
	private ManageGroupStudentDaoMapper manageGroupStudentDaoMapper;
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentGroupService#getGroupsCount(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String) <BR>
	 * Method name: getGroupsCount <BR>
	 * Description: 查询群组数目 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param groupName 群组名称
	 * @param createUser 创建人
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getGroupsCount(Integer companyId, Integer subCompanyId,
			String groupName, String createUser) throws Exception {
		//设置查询参数
		ManageGroupVo manageGroupVo = new ManageGroupVo();
		manageGroupVo.setCompanyId(companyId);
		manageGroupVo.setSubCompanyId(subCompanyId);
		manageGroupVo.setName(groupName);
		manageGroupVo.setCreateUserName(createUser);
		//查询数目
		return manageGroupDaoMapper.getGroupsCount(manageGroupVo);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentGroupService#getGroupsByConditions(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getGroupsByConditions <BR>
	 * Description: 根据条件查询群组列表 <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param studentId 学生id
	 * @param groupName 群组名称
	 * @param createUser 创建人
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<ManageGroupVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ManageGroupVo> getGroupsByConditions(Integer companyId,Integer subCompanyId,
			Integer studentId,String groupName,String createUser,
			Integer fromNum,Integer pageSize) throws Exception {
		//设置查询参数
		ManageGroupVo manageGroupVo = new ManageGroupVo();
		manageGroupVo.setCompanyId(companyId);
		manageGroupVo.setSubCompanyId(subCompanyId);
		manageGroupVo.setName(groupName);
		manageGroupVo.setCreateUserName(createUser);
		//根据条件查询出群组列表
		List<ManageGroupVo> groups = manageGroupDaoMapper.getGroupsByConditions(manageGroupVo,studentId,fromNum,pageSize);
		return groups;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.StudentGroupService#addGroupMember(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: addGroupMember <BR>
	 * Description: 添加群组成员 <BR>
	 * Remark: <BR>
	 * @param groupId 群组id
	 * @param groupType 群组类型
	 * @param studentId 学生id
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addGroupMember(Integer groupId, Integer groupType,
			Integer studentId) throws Exception {
		if(groupType == DataBaseConstant.MANAGEGROUP_TYPE_FREEJOIN){
			//自由加入
			ManageGroupStudentBean manageGroupStudentBean = new ManageGroupStudentBean();
			manageGroupStudentBean.setGroupId(groupId);
			manageGroupStudentBean.setStudentId(studentId);
			manageGroupStudentBean.setStatus(DataBaseConstant.MANAGEGROUPSTUDENT_STATUS_NORMAL);
			manageGroupDaoMapper.addGroupMember(manageGroupStudentBean);
		}else if(groupType == DataBaseConstant.MANAGEGROUP_TYPE_VALIDATED){
			//通过验证
			ManageGroupStudentBean manageGroupStudentBean = manageGroupStudentDaoMapper.getUnpassedMember(groupId,studentId);
			if(manageGroupStudentBean != null){
				manageGroupStudentBean.setStatus(DataBaseConstant.MANAGEGROUPSTUDENT_STATUS_WAIT);//修改审核不通过状态为待审核
				manageGroupStudentDaoMapper.updateMember(manageGroupStudentBean);
			}else{
				manageGroupStudentBean = new ManageGroupStudentBean();
				manageGroupStudentBean.setGroupId(groupId);
				manageGroupStudentBean.setStudentId(studentId);
				manageGroupStudentBean.setStatus(DataBaseConstant.MANAGEGROUPSTUDENT_STATUS_WAIT);
				manageGroupDaoMapper.addGroupMember(manageGroupStudentBean);
			}
			
			//添加消息通知管理员
			ManageGroupBean group = manageGroupDaoMapper.getById(groupId);
			String userName = manageUserService.findUserById(String.valueOf(studentId)).getUserName();
			List<Integer> recUserIds = manageGroupDaoMapper.getManageUserIds(group.getCompanyId(),group.getSubCompanyId());
			//向所有群组管理员发送站内信
			for (int i = 0; i < recUserIds.size(); i++) {
				ManageNoticeBean noticeBean = new ManageNoticeBean();
				noticeBean.setTitle("加入群组验证");
				noticeBean.setContent("尊敬的管理员，有新的学员申请加入群组，请及时验证处理。");
				noticeBean.setSendUserId(studentId);
				noticeBean.setSendUserName(userName);
				noticeBean.setSendTime(new Date());
				noticeBean.setRecUserId(recUserIds.get(i));
				noticeBean.setRecUserName("管理员");
				noticeBean.setIsRead(2);//未读
				noticeBean.setIsSystem(1);//系统消息
				noticeBean.setSendDeleteFlag(1);//未删除
				noticeBean.setRecDeleteFlag(1);//未删除
				manageNoticeDaoMapper.insert(noticeBean);
			}
		}else if(groupType == DataBaseConstant.MANAGEGROUP_TYPE_SPECIFESTUDENT){
			//指定学员
		}else if(groupType == DataBaseConstant.MANAGEGROUP_TYPE_MEETCONDITIONS){
			//满足条件
		}else{
			System.out.println("----群组类型不存在----");
		}
	}
	
	/**shenhaijun end*/
	
}
