/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageGroupDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-20        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageGroupCondition;
import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.bean.vo.ManageGroupVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ManageGroupDaoMapper <BR>
 * class description: 群组dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-20
 * @author JFTT)ShenHaijun
 */
public interface ManageGroupDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询群组 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ManageGroupBean<BR>
	 */
	public ManageGroupBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getGroupsCount <BR>
	 * Description: 查询群组数目 <BR>
	 * Remark: <BR>
	 * @param manageGroupVo
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getGroupsCount(@Param("manageGroupVo")ManageGroupVo manageGroupVo) throws DataBaseException;
	
	/**
	 * Method name: getGroupsByConditions <BR>
	 * Description: 根据条件查询群组列表 <BR>
	 * Remark: <BR>
	 * @param manageGroupVo
	 * @return groups
	 * @throws DataBaseException  List<ManageGroupVo><BR>
	 */
	public List<ManageGroupVo> getGroupsByConditions(@Param("manageGroupVo")ManageGroupVo manageGroupVo,@Param("studentId")Integer studentId,
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: addGroupMember <BR>
	 * Description: 添加群组成员 <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @param studentId
	 * @param managegroupstudentStatusPass
	 * @throws DataBaseException  void<BR>
	 */
	public void addGroupMember(ManageGroupStudentBean manageGroupStudentBean) throws DataBaseException;
	
	/**
	 * Method name: getManageUserIds <BR>
	 * Description: 获取公司的所有群组管理员 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  List<Integer><BR>
	 */
	public List<Integer> getManageUserIds(@Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**shenhaijun end*/
	
	
	/**luyunlong begin*/
	/**
	 * Method name: selectGroupCount <BR>
	 * Description: 查询群组数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	public int selectGroupCount(ManageGroupBeanVo vo) throws Exception ;

	/**
	 * Method name: selectGroupList <BR>
	 * Description: 查询群组数据 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ManageGroupBean><BR>
	 */
	public List<ManageGroupBean> selectGroupList(ManageGroupBeanVo vo) throws Exception ;

	/**
	 * Method name: insertGroup <BR>
	 * Description: 新增群组 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void insertGroup(ManageGroupBean bean) throws Exception ;

	/**
	 * Method name: insertGroupAndStudent <BR>
	 * Description: 新增群组、学员关系 <BR>
	 * Remark: <BR>
	 * @param mgsBean
	 * @throws Exception  void<BR>
	 */
	public void insertGroupAndStudent(ManageGroupStudentBean mgsBean) throws Exception ;

	/**
	 * Method name: selectGroupStudentCountByGroupId <BR>
	 * Description: 群组学员数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	public int selectGroupStudentCountByGroupId(ManageGroupBeanVo vo) throws Exception ;

	/**
	 * Method name: selectGroupStudentListByGroupId <BR>
	 * Description: 群组、学员列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ManageGroupBean><BR>
	 */
	public List<ManageGroupStudentBean> selectGroupStudentListByGroupId(
			ManageGroupBeanVo vo) throws Exception ;

	/**
	 * Method name: deleteGroupStudent <BR>
	 * Description: 删除群组学员 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void deleteGroupStudent(ManageGroupStudentBean bean) throws Exception ;

	/**
	 * Method name: deleteGroup <BR>
	 * Description: 删除群组 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void deleteGroup(@Param("id")Integer id) throws Exception ;

	/**
	 * Method name: checkGroup <BR>
	 * Description: 验证群组 <BR>
	 * Remark: <BR>
	 * @param mgsBean  void<BR>
	 */
	public void checkGroup(ManageGroupStudentBean mgsBean);

	/**
	 * Method name: updateGroup <BR>
	 * Description: 修改群组 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateGroup(ManageGroupBean bean);

	/**
	 * Method name: selectCourseOpenGroupByCourseId <BR>
	 * Description: 获取课程开放群组 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManageGroupBean><BR>
	 */
	public List<ManageGroupBean> selectCourseOpenGroupByCourseId(Integer id);

	/**
	 * Method name: selectGroupCondition <BR>
	 * Description: 获取满足条件群组的条件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param groupId
	 * @return  Object<BR>
	 */
	public List<ManageGroupCondition> selectGroupCondition(@Param("groupId")long groupId);

	/**
	 * Method name: deleteGroupCondition <BR>
	 * Description: 删除群组条件 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteGroupCondition(@Param("groupId")Integer id);

	/**
	 * Method name: insertGroupCondition <BR>
	 * Description: 新增群组条件 <BR>
	 * Remark: <BR>
	 * @param condition  void<BR>
	 */
	public void insertGroupCondition(ManageGroupCondition condition);

	/**luyunlong end*/
	
}
