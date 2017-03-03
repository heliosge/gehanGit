/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagService.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.dao.ManageRoleDaoMapper;
import com.jftt.wifi.service.ManageRoleService;
import com.mongodb.BasicDBList;

@Service("manageRoleService")
public class ManageRoleServiceImpl implements ManageRoleService{
	
	@Resource(name="manageRoleDaoMapper")
	private ManageRoleDaoMapper manageRoleDaoMapper;
	
	/**
	 * 根据条件获得权限
	 */
	public List<ManageRoleBean> getManageRoleByMap(Map<String, String> map){
		try {
			return manageRoleDaoMapper.getManageRoleByMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 增加角色
	 */
	public void addManageRole(ManageRoleBean role){
		try {
			manageRoleDaoMapper.addManageRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改角色
	 */
	public void updateManageRoleById(long id, String name){
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id+"");
			map.put("name", name);
			manageRoleDaoMapper.updateManageRoleById(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除角色
	 */
	@Override
	public void delManageRoleById(Integer id){
		try {
			manageRoleDaoMapper.deleteRolebyId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: getManageUserByRoleName <BR>
	 * Description: 根据角色名称获取用户id <BR>
	 * Remark: <BR>
	 * @param roleName
	 * @return  List<ManageUserBean><BR>
	 */
	public List<String> getManageUserByRoleName(String roleName, int companyId){
		return manageRoleDaoMapper.getManageUserByRoleName(roleName, companyId);
	}

	@Override
	public void updateManageRole(ManageRoleBean role) {
		manageRoleDaoMapper.updateManageRole(role);
	}

	@Override
	public ManageRoleBean getManageRoleById(String id) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return manageRoleDaoMapper.getManageRoleByMap(map).get(0);
	}

	@Override
	public int getManageRoleCountByMap(Map<String, String> map) {
		return manageRoleDaoMapper.getManageRoleCountByMap(map);
	}
}
