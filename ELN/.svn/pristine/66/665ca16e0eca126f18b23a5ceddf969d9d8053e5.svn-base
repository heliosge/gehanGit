/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageRolePageBean;
import com.jftt.wifi.dao.ManagePageDaoMapper;
import com.jftt.wifi.service.ManagePageService;


/**
 * ManageRoleDaoMapper.java
 */
@Service("managePageService")
public class ManagePageServiceImpl implements ManagePageService{
	
	@Resource(name="managePageDaoMapper")
	private ManagePageDaoMapper managePageDaoMapper; 
	
	/**
	 * 获取全部权限
	 */
	public List<ManagePageBean> getAllManagePage(Map<String, Object> param){
		try {
			return managePageDaoMapper.getAllManagePage(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取某个角色的权限
	 */
	public List<ManagePageBean> getManagePageByUserId(long roleId){
		try {
			return managePageDaoMapper.getManagePageByUserId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除某个角色的权限页面
	 */
	public void deleteManagePageByUserId(long roleId){
		try {
			managePageDaoMapper.deleteManagePageByUserId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给角色加页面 
	 */
	public void addRolesManagePage(List<ManageRolePageBean> rolePageList){
		try {
			managePageDaoMapper.addRolesManagePage(rolePageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取某个用户可以查看的所有页面
	 */
	public List<ManagePageBean> getManagePageByUser(long userId){
		try {
			return managePageDaoMapper.getManagePageByUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 增加页面
	 */
	public void addManagePage(ManagePageBean managePageBean){
		
		managePageDaoMapper.addManagePage(managePageBean);
	}
	
	/**
	 * 删除页面
	 */
	public void delManagePageById(long id){
		
		managePageDaoMapper.delManagePageById(id);
	}
	
	/**
	 * 升级页面, name, url, levelType
	 */
	public void updateManagePageById(ManagePageBean managePageBean){
		
		managePageDaoMapper.updateManagePageById(managePageBean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManagePageService#getManagePageByMap(java.util.Map) <BR>
	 * Method name: getManagePageByMap <BR>
	 * Description: 根据页面url获取当前页面对象 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public ManagePageBean getManagePageByMap(Map<String, Object> param) {
		return managePageDaoMapper.getManagePageByMap(param);
	}
	
	/**
	 * Method name: checkManagePageByUser <BR>
	 * Description: 验证该页面、用户是否匹配 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  ManagePageBean<BR>
	 */
	@Override
	public ManagePageBean checkManagePageByUser(Map<String, Object> param) {
		return managePageDaoMapper.checkManagePageByUser(param);
	}
} 
