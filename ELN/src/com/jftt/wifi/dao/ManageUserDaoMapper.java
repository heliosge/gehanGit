/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserIdSeqBean;
import com.jftt.wifi.bean.ManageUserRoleBean;

/**
 * ManageUserDaoMapper.java
 */
public interface ManageUserDaoMapper {
	
	/*弃用 begin*/
	
	/**
	 * 根据条件获得用户的数量
	 */
	public int getManageUserCountByMap(Map<String, String> map);
	
	/**
	 * 根据条件获得用户
	 */
	public List<ManageUserBean> getManageUserByMap(Map<String, String> map);
	
	/**
	 * 升级用户
	 */
	public void updateManageUserById(Map<String, String> map);
	
	/**
	 * 新增用户
	 */
	public void addManageUser(ManageUserBean manageUserBean);
	
	/**
	 * 根据Id获得用户
	 */
	public ManageUserBean getManageUserById(@Param("id")long id);
	
	/**
	 * Method name: getUserByUserId <BR>
	 * Description: 根据用户UserId查询用户信息 <BR>
	 * Remark: <BR>
	 * @param map user_id
	 * @return
	 * @throws DataBaseException  ManageUserBean<BR>
	 */
	public ManageUserBean getUserByUserId(Map<String, String> map);
	
	/*弃用 end*/
	
	
	
	/**
	 * Method name: getManageUserIdSeq <BR>
	 * Description: 获取用户主键 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void getManageUserIdSeq(ManageUserIdSeqBean bean);

	/**
	 * Method name: getRoleByUserId <BR>
	 * Description: 获取用户的权限id <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<Integer><BR>
	 */
	public List<Integer> getRoleIdByUserId(@Param("userId")String userId);
	
	/**
	 * Method name: addUserRole <BR>
	 * Description: 增加用户角色 <BR>
	 * Remark: <BR>
	 * @param manageUserRole  void<BR>
	 */
	public void addUserRole(ManageUserRoleBean manageUserRole);
	
	/**
	 * Method name: deleteUserRoleByUserId <BR>
	 * Description: 删除某个用户的所有角色 <BR>
	 * Remark: <BR>
	 * @param userId  void<BR>
	 */
	public void deleteUserRoleByUserId (@Param("userId")String userId);
	
	/**
	 * Method name: deleteUserRole <BR>
	 * Description:  删除某个用户的某个角色 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param roleId  void<BR>
	 */
	public void deleteUserRole(@Param("userId")String userId, @Param("roleId")String roleId);
	
	/**
	 * Method name: deleteUserRoleByRole <BR>
	 * Description: 删除某角色的所有用户  <BR>
	 * Remark: <BR>
	 * @param roleId  void<BR>
	 */
	public void deleteUserRoleByRole(@Param("roleId")String roleId);

	/**
	 * Method name: deleteSubManagePageByUserId <BR>
	 * Description: 修改公司权限，删除该公司子用户缺少的权限 <BR>
	 * Remark: <BR>
	 * @param roleId
	 * @throws Exception  void<BR>
	 */
	public void deleteSubManagePageByUserId(@Param("roleId")long roleId) throws Exception;

	/**
	 * Method name: getRoleByUserId <BR>
	 * Description: 根据用户获取角色 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<ManageRoleBean><BR>
	 */
	public List<ManageRoleBean> getRoleByUserId(@Param("userId")String userId);

	/**
	 * Method name: findUserIdByRoleName <BR>
	 * Description: 根据角色名称获取人员id  <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  List<String><BR>
	 */
	public List<String> findUserIdByRoleName(Map<String, Object> map);

	/**
	 * Method name: checkStudentStudyRecord <BR>
	 * Description: 判断学生是否存在学习记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	public int checkStudentStudyRecord(@Param("id")int id);
}
