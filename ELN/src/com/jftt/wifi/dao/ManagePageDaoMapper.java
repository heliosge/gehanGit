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

import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageRolePageBean;
import com.jftt.wifi.exception.database.DataBaseException;


/**
 * ManageRoleDaoMapper.java
 */
public interface ManagePageDaoMapper {
	
	/**
	 * 获取全部权限
	 */
	public List<ManagePageBean> getAllManagePage(Map<String, Object> param);
	
	/**
	 * 获取某个角色的权限
	 */
	public List<ManagePageBean> getManagePageByUserId(@Param("roleId")long roleId);
	
	/**
	 * 删除某个角色的权限页面
	 */
	public void deleteManagePageByUserId(@Param("roleId")long roleId);
	
	/**
	 * 给角色加页面 
	 */
	public void addRolesManagePage(List<ManageRolePageBean> rolePageList);
	
	/**
	 * 获取某个用户可以查看的所有页面
	 */
	public List<ManagePageBean> getManagePageByUser(@Param("userId")long userId);
	
	/**
	 * 增加页面
	 */
	public void addManagePage(ManagePageBean managePageBean);
	
	/**
	 * 删除页面
	 */
	public void delManagePageById(@Param("id")long id);
	
	/**
	 * 升级页面, name, url, levelType
	 */
	public void updateManagePageById(ManagePageBean managePageBean);
	
	/**
	 * Method name: getManagePageByMap <BR>
	 * Description: 据页面url获取当前页面对象  <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  ManagePageBean<BR>
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
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getManageThematicAskRoles <BR>
	 * Description: 获取专题问答管理员用户id列表 <BR>
	 * Remark: <BR>
	 * @param limitsUrl
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  List<Integer><BR>
	 */
	public List<Integer> getManageThematicAskRoles(@Param("limitsUrl")String limitsUrl,
			@Param("companyId")String companyId, @Param("subCompanyId")String subCompanyId) throws DataBaseException;

	/**shenhaijun end*/
	
} 
