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
import com.mongodb.BasicDBList;

/**
 * ManageRoleDaoMapper.java
 * 角色管理
 */
public interface ManageRoleDaoMapper {
	
	/**
	 * 根据条件获得权限
	 */
	public List<ManageRoleBean> getManageRoleByMap(Map<String, String> map);
	
	/**
	 * 增加角色
	 */
	public void addManageRole(ManageRoleBean role);
	
	/**
	 * 修改角色
	 */
	public void updateManageRoleById(Map<String, String> map);
	
	/**
	 * Method name: getManageUserByRoleName <BR>
	 * Description: 根据角色名称获取用户id <BR>
	 * Remark: <BR>
	 * @param roleName
	 * @return  List<ManageUserBean><BR>
	 */
	public List<String> getManageUserByRoleName(@Param("roleName")String roleName, @Param("companyId")int companyId);

	/**
	 * Method name: updateManageRole <BR>
	 * Description: 修改角色 <BR>
	 * Remark: <BR>
	 * @param role  void<BR>
	 */
	public void updateManageRole(ManageRoleBean role);

	public void deleteRolebyId(@Param("id")Integer id);

	/**
	 * Method name: getManageRoleCountByMap <BR>
	 * Description: 获取角色数量 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  int<BR>
	 */
	public int getManageRoleCountByMap(Map<String, String> map);
}
