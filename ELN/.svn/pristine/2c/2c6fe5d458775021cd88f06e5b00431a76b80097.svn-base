/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagService.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.ManageRoleBean;
import com.mongodb.BasicDBList;

/**
 * ManageService.java
 */
public interface ManageRoleService {

	/**
	 * 根据条件获得权限
	 */
	public List<ManageRoleBean> getManageRoleByMap(Map<String, String> map);
	
	/**
	 * 增加角色信息
	 */
	public void addManageRole(ManageRoleBean role);
	
	/**
	 * 修改角色信息
	 */
	public void updateManageRoleById(long id, String name);
	
	/**
	 * 删除角色信息
	 */
	public void delManageRoleById(Integer id);
	
	/**
	 * Method name: getManageUserByRoleName <BR>
	 * Description: 根据角色名称获取用户id <BR>
	 * Remark: <BR>
	 * @param roleName
	 * @return  List<ManageUserBean><BR>
	 */
	public List<String> getManageUserByRoleName(String roleName, int companyId);

	/**
	 * Method name: updateManageRole <BR>
	 * Description: 修改用户 <BR>
	 * Remark: <BR>
	 * @param role  void<BR>
	 */
	public void updateManageRole(ManageRoleBean role);

	/**
	 * Method name: getManageRoleById <BR>
	 * Description: 根据roleId得到角色 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  Object<BR>
	 */
	public ManageRoleBean getManageRoleById(String parseInt);

	/**
	 * Method name: getManageRoleCountByMap <BR>
	 * Description: 获取角色数量 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  int<BR>
	 */
	public int getManageRoleCountByMap(Map<String, String> map);

	
}
