/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageRolePageBean;


/**
 * ManageRoleDaoMapper.java
 */
public interface ManagePageService {
	
	/**
	 * 获取全部权限
	 */
	public List<ManagePageBean> getAllManagePage(Map<String, Object> param);
	
	/**
	 * 获取某个角色的权限
	 */
	public List<ManagePageBean> getManagePageByUserId(long roleId);
	
	/**
	 * 删除某个角色的权限页面
	 */
	public void deleteManagePageByUserId(long roleId);
	
	/**
	 * 给角色加页面 
	 */
	public void addRolesManagePage(List<ManageRolePageBean> rolePageList);
	
	/**
	 * 获取某个用户可以查看的所有页面
	 */
	public List<ManagePageBean> getManagePageByUser(long userId);
	
	/**
	 * 增加页面
	 */
	public void addManagePage(ManagePageBean managePageBean);
	
	/**
	 * 删除页面
	 */
	public void delManagePageById(long id);
	
	/**
	 * 升级页面, name, url, levelType
	 */
	public void updateManagePageById(ManagePageBean managePageBean);
	
	/**
	 * 根据页面url获取当前页面对象
	 */
	public ManagePageBean getManagePageByMap(Map<String, Object> param);
	
	/**
	 * Method name: checkManagePageByUser <BR>
	 * Description: 验证该页面、用户是否匹配 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  ManagePageBean<BR>
	 */
	public ManagePageBean checkManagePageByUser(Map<String, Object> param);


} 
