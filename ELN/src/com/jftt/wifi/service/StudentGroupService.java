/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseGroupService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.vo.ManageGroupVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseGroupService <BR>
 * class description: 学员加入群组service <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-20
 * @author JFTT)ShenHaijun
 */
public interface StudentGroupService {
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getGroupsCount <BR>
	 * Description: 查询群组数目  <BR>
	 * Remark: <BR>
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param groupName 群组名称
	 * @param createUser 创建人
	 * @return Integer
	 * @throws DataBaseException <BR>
	 */
	public Integer getGroupsCount(Integer companyId,Integer subCompanyId,
			String groupName,String createUser) throws Exception;
	
	/**
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
	 * @throws Exception <BR>
	 */
	List<ManageGroupVo> getGroupsByConditions(Integer companyId,Integer subCompanyId,
			Integer studentId,String groupName,String createUser,
			Integer fromNum,Integer pageSize) throws Exception;
	
	/**
	 * Method name: addGroupMember <BR>
	 * Description: 添加群组成员 <BR>
	 * Remark: <BR>
	 * @param groupId 群组id
	 * @param groupType 群组类型
	 * @param studentId 学生id
	 * @throws DataBaseException  void<BR>
	 */
	void addGroupMember(Integer groupId, Integer groupType, Integer studentId) throws Exception;
	
	/**shenhaijun end*/
	
}
