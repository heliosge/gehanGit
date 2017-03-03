/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName: TagAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |	2013-01-07		|	JFTT)wangjian	|	original version
 */
package com.jftt.wifi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageRolePageBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserRoleBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManagePageService;
import com.jftt.wifi.service.ManageRoleService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name: ManageAction <BR>
 * class description: 角色管理  <BR>
 * Remark: <BR>
 * @version 1.00 2014-01-07
 * @author JFTT)WANGJIAN
 */
@Controller
@RequestMapping(value="manageRole")
public class ManageRoleAction {
	private static Logger logger = Logger.getLogger(ManageRoleAction.class);  
	
	@Resource(name="manageRoleService")
	private ManageRoleService manageRoleService;
	
	@Resource(name="managePageService")
	private ManagePageService managePageService;
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	/**
	 * Method name: toApPage <BR>
	 * Description: 跳转到AP管理页面 <BR>
	 * Remark: <BR>
	 * @throws Exception 
	 */
	@RequestMapping(value="roleList")
	public String roleList(HttpServletRequest request) throws Exception{
		return "manage/manageRoleList";
	}
	
	/**
	 * 获得所有角色
	 * @throws Exception 
	 */
	@RequestMapping(value="getAllManageRole")
	@ResponseBody
	public Object getAllManageRole(HttpServletRequest request, String name, int page, int pageSize, String status) throws Exception{
		MMGridPageVoBean<ManageRoleBean> re = new MMGridPageVoBean<ManageRoleBean>();
		//所有角色
		Map<String, String> map = new HashMap<String, String>();
		ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		map.put("companyId", user.getCompanyId().toString());
		map.put("subCompanyId", user.getSubCompanyId().toString());
		map.put("name", name);
		map.put("status", status);
		map.put("fromNum", (page-1)*pageSize+"");
		map.put("pageSize", pageSize+"");
		List<ManageRoleBean> roleList = manageRoleService.getManageRoleByMap(map);
		int count = manageRoleService.getManageRoleCountByMap(map);
		re.setRows(roleList);
		re.setTotal(count);
		return re;
	}
	
	/**
	 * 获得公司所有角色
	 * @throws Exception 
	 */
	@RequestMapping(value="getCompanyRole")
	@ResponseBody
	public Object getCompanyRole(HttpServletRequest request) throws Exception{
		//所有角色
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
		ManageUserBean user = manageUserService.findUserById(id);
		map.put("companyId", user.getCompanyId().toString());
		map.put("subCompanyId", user.getSubCompanyId().toString());
		return manageRoleService.getManageRoleByMap(map);
	}
	
	/**
	 * Method name: toInsertRole <BR>
	 * Description: 新增角色 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping(value="toInsertRolePage")
	public String toInsertRolePage(HttpServletRequest request) throws Exception{
		return "manage/manageRoleAdd";
	}
	
	/**
	 * 增加角色
	 */
	@RequestMapping(value="addManageRole")
	@ResponseBody
	public String addManageRole(HttpServletRequest request ,ManageRoleBean role){
		
		try {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = manageUserService.findUserById(id);
			role.setCompanyId(user.getCompanyId());
			role.setSubCompanyId(user.getSubCompanyId());
			manageRoleService.addManageRole(role);
			
			//新增角色权限
			List<ManageRolePageBean> rolePageList = new ArrayList<ManageRolePageBean>();
			String[] pageIds = request.getParameterValues("pageIds[]");
			for(String pageId : pageIds){
				ManageRolePageBean rolePage = new ManageRolePageBean();
				rolePage.setRoleId(role.getId());
				rolePage.setPageId(Integer.parseInt(pageId));
				rolePageList.add(rolePage);
			}
			managePageService.addRolesManagePage(rolePageList);
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
		
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping(value="delManageRole")
	@ResponseBody
	public String delManageRole(HttpServletRequest request){
		
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				manageRoleService.delManageRoleById(Integer.parseInt(id));
				//删除角色的页面
				managePageService.deleteManagePageByUserId(Integer.parseInt(id));
				//删除某个角色的所有用户
				manageUserService.deleteUserRoleByRole(id);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toUpdateRolePage <BR>
	 * Description: 修改角色页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping(value="toUpdateRolePage")
	public String toUpdateRolePage(HttpServletRequest request, String id) throws Exception{
		request.setAttribute("role", JsonUtil.getJson4JavaObject(manageRoleService.getManageRoleById(id)));
		return "manage/manageRoleUpdate";
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping(value="updateManageRole")
	@ResponseBody
	public String updateManageRole(HttpServletRequest request,ManageRoleBean role){
		try {
			manageRoleService.updateManageRole(role);
			
			//删除角色原来权限
			managePageService.deleteManagePageByUserId(role.getId());
			//增加新资源
			List<ManageRolePageBean> rolePageList = new ArrayList<ManageRolePageBean>();
			String[] pageIds = request.getParameterValues("pageIds[]");
			for(String pageId : pageIds){
				ManageRolePageBean rolePage = new ManageRolePageBean();
				rolePage.setRoleId(role.getId());
				rolePage.setPageId(Integer.parseInt(pageId));
				rolePageList.add(rolePage);
			}
			managePageService.addRolesManagePage(rolePageList);
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	@RequestMapping(value="toRoleDetailPage")
	public String toRoleDetailPage(HttpServletRequest request, String id) throws Exception{
		request.setAttribute("role", JsonUtil.getJson4JavaObject(manageRoleService.getManageRoleById(id)));
		return "manage/manageRoleDetail";
	}
	
	/**
	 * Method name: freezeAndUnfreezeRole <BR>
	 * Description: 冻结、解冻角色 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param status
	 * @return  String<BR>
	 */
	@RequestMapping(value="freezeAndUnfreezeRole")
	@ResponseBody
	public String freezeAndUnfreezeRole(HttpServletRequest request, String status){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				ManageRoleBean role = new ManageRoleBean();
				role.setStatus(Integer.parseInt(status));
				role.setId(Integer.parseInt(id));
				manageRoleService.updateManageRole(role);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * 获取所有页面权限
	 */
	@RequestMapping(value="getAllManagePage")
	@ResponseBody
	public Object getAllManagePage(){
		
		return managePageService.getAllManagePage(new HashMap<String, Object>());
	}
	
	/**
	 * 获取某个角色的权限页面
	 */
	@RequestMapping(value="getManagePageByRole")
	@ResponseBody
	public Object getManagePageByRole(long roleId){
		
		return managePageService.getManagePageByUserId(roleId);
	}
	
	/**
	 * 删除某个角色的权限页面
	 */
	@RequestMapping(value="delManagePageByRole")
	@ResponseBody
	public String delManagePageByRole(long roleId){
		
		try {
			managePageService.deleteManagePageByUserId(roleId);
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * 增加某个角色的权限页面
	 */
	@RequestMapping(value="addManagePageByRole")
	@ResponseBody
	public String addManagePageByRole(long roleId, long[] pageIds){
		
		try {
			if(pageIds != null && pageIds.length > 0){
				List<ManageRolePageBean> rolePageList = new ArrayList<ManageRolePageBean>();
				
				for (long pageId : pageIds) {
					ManageRolePageBean rolePage = new ManageRolePageBean();
					rolePage.setRoleId(roleId);
					rolePage.setPageId(pageId);
					rolePageList.add(rolePage);
				}
				
				managePageService.addRolesManagePage(rolePageList);
			}
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * Method name: toApPage <BR>
	 * Description: 跳转到选择用户页面 <BR>
	 * Remark: <BR>
	 */
	@RequestMapping(value="selectUser")
	public String selectUser(HttpServletRequest request){
		
		return "manage/selectUser";
	}
	
	/**
	 * 增加用户的角色
	 */
	@RequestMapping(value="addUserRole")
	@ResponseBody
	public String addUserRole(int roleId, int[] userIds){
		
		try {
			if(userIds != null && userIds.length > 0){
				for (int userId : userIds) {
					
					ManageUserRoleBean manageUserRole = new ManageUserRoleBean();
					manageUserRole.setRoleId(roleId);
					manageUserRole.setUserId(userId);
					
					manageUserService.addUserRole(manageUserRole);
					
				}
			}
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * 删除用户的角色
	 */
	@RequestMapping(value="deleteUserRole")
	@ResponseBody
	public String deleteUserRole(int roleId, int userId){
		
		try {
			manageUserService.deleteUserRole(userId+"", roleId+"");
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * 获得所有角色
	 * @throws Exception 
	 */
	@RequestMapping(value="getManageRoleByName")
	@ResponseBody
	public Object getManageRoleByName(HttpServletRequest request, String name) throws Exception{
		//所有角色
		Map<String, String> map = new HashMap<String, String>();
		ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		map.put("companyId", user.getCompanyId().toString());
		map.put("subCompanyId", user.getSubCompanyId().toString());
		map.put("nameEqule", name);
		return manageRoleService.getManageRoleByMap(map);
	}
}
