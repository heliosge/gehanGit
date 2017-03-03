/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageGroupStudentDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ManageGroupStudentDaoMapper <BR>
 * class description: 学员群组关系dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)ShenHaijun
 */
public interface ManageGroupStudentDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取群组学员关系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ManageGroupStudentBean<BR>
	 */
	public ManageGroupStudentBean getById(@Param("id")Integer id) throws DataBaseException;
	
	//zhangchen start
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectUserIdByGroupId <BR>
	 * Description: 根据群组ID查询用户ID <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @return  String<BR>
	 */
	public String selectUserIdByGroupId(@Param("groupId")Integer groupId);
	//zhangchen end
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getUnpassedMember <BR>
	 * Description: 查询审核未通过的群组成员 <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @param studentId
	 * @return  ManageGroupStudentBean<BR>
	 */
	public ManageGroupStudentBean getUnpassedMember(@Param("groupId")Integer groupId,@Param("studentId")Integer studentId) throws DataBaseException;
	
	/**
	 * Method name: updateMember <BR>
	 * Description: 更新用户的群组加入信息 <BR>
	 * Remark: <BR>
	 * @param manageGroupStudentBean
	 * @throws DataBaseException  void<BR>
	 */
	public void updateMember(ManageGroupStudentBean manageGroupStudentBean) throws DataBaseException;
	
	/**
	 * Method name: getGroupIdsByUserId <BR>
	 * Description: 查询用户所在群组id列表 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<Integer><BR>
	 */
	public List<Integer> getGroupIdsByUserId(@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getCountByGroupIdUserId <BR>
	 * Description: 根据群组id和用户id查询数量 <BR>
	 * Remark: <BR>
	 * @param openCrowId
	 * @param userId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCountByGroupIdUserId(
			@Param("groupId")Integer groupId, 
			@Param("userId")Integer userId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
