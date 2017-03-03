/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName: TagAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |	2013-01-07		|	JFTT)wangjian	|	original version
 */
package com.jftt.wifi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name: ManageDepartmentAction <BR>
 * class description: 部门管理  <BR>
 * Remark: <BR>
 * @version 1.00 2014-01-07
 * @author JFTT)WANGJIAN
 */
@Controller
@RequestMapping(value="manageDepartment")
public class ManageDepartmentAction {
	private static Logger logger = Logger.getLogger(ManageDepartmentAction.class);  
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	/**
	 * Method name: departmentList <BR>
	 * Description: 跳转到部门管理页面 <BR>
	 * Remark: <BR>
	 */
	@RequestMapping(value="departmentList")
	public String departmentList(HttpServletRequest request){
		
		//用户
		//Map<String, String> map = new HashMap<String, String>();
		//map.put("deleteFlag", "0");
		//List<ManageUserBean> userList = manageUserService.getManageUserByMap(map);
		//request.setAttribute("userList", JsonUtil.getJson4JavaList(userList));
		
		return "manage/departmentList";
	}
	
	/**
	 * 获得部门数据
	 * @param page 当前页
	 * @param pageSize 每页数量
	 */
	@RequestMapping(value="getManageDepartmentByMap")
	@ResponseBody
	public Object getManageDepartmentByMap(HttpServletRequest request, int page, int pageSize, String name, String code) throws Exception{

		//组织参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("code2", code);
		map.put("deleteFlag", "0");
		
//		int size = manageDepartmentService.getManageDepartmentCountByMap(map);
		
		//放入分页
		int from = (page-1) * pageSize;

		map.put("fromNum", from+"");
		map.put("pageSize", pageSize+"");
		
		List<ManageDepartmentBean> rows = manageDepartmentService.getManageDepartmentByMap(map);
		
		MMGridPageVoBean<ManageDepartmentBean> vo = new MMGridPageVoBean<ManageDepartmentBean>();
//		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	/**
	 * 获得部门数据
	 */
	@RequestMapping(value="getAllManageDepartment")
	@ResponseBody
	public Object getAllManageDepartment() throws Exception{
		
		//组织参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("deleteFlag", "0");
		//已启用
		map.put("status", "0");
				
		return manageDepartmentService.getManageDepartmentByMap(map);
	}
	
	
	/**
	 * 删除部门
	 */
	@RequestMapping(value="delManageDepartment")
	@ResponseBody
	public String delManageDepartment(HttpServletRequest request, long[] ids){
		
		try {
			for (long id : ids) {
				manageDepartmentService.delManageDepartmentById(id);
			}
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * 跳转到增加部门页面
	 */
	@RequestMapping(value="departmentAdd")
	public String departmentAdd(HttpServletRequest request, String id) throws Exception{
		
		//用户
		Map<String, String> map = new HashMap<String, String>();
		map.put("deleteFlag", "0");
		//List<ManageUserBean> userList = manageUserService.getManageUserByMap(map);
		//request.setAttribute("userList", JsonUtil.getJson4JavaList(userList));
		
		//部门
		//已启用
		map.put("status", "0");
		List<ManageDepartmentBean> departmentList = manageDepartmentService.getManageDepartmentByMap(map);
		request.setAttribute("departmentList", JsonUtil.getJson4JavaList(departmentList));
		
		if(id != null && !id.equals("")){
			ManageDepartmentBean departmentBean = manageDepartmentService.getManageDepartmentById(Long.parseLong(id));
			request.setAttribute("departmentBean", JSONObject.fromObject(departmentBean));
			
			//查看修改的部门是否是其他部门的上级部门
			map.put("departmentId", departmentBean.getId()+"");
//			int count = manageDepartmentService.getManageDepartmentCountByMap(map);
//			if(count > 0){
//				request.setAttribute("isParent", count);
//			}
			
		}else{
			request.setAttribute("departmentBean", JSONObject.fromObject(null));
		}
		
		return "manage/departmentAdd";
	}
	
	
	
	
	/**
	 * 验证部门编码唯一性
	 */
	@RequestMapping(value="checkCode")
	@ResponseBody
	public String checkCode(HttpServletRequest request, String code) throws Exception{

		//组织参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code.trim());
		map.put("deleteFlag", "0");

		long size = manageDepartmentService.getManageDepartmentCountByMap(map);
		
		return size+"";
	}
	
	/**
	 * 验证部门名称唯一性
	 */
	@RequestMapping(value="checkName")
	@ResponseBody
	public String checkName(HttpServletRequest request, String name) throws Exception{

		//组织参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("name2", name.trim());
		map.put("deleteFlag", "0");

		long size = manageDepartmentService.getManageDepartmentCountByMap(map);
		
		return size+"";
	}
	
	/**
	 * 验证部门是否是其他部门的  父部门
	 */
	@RequestMapping(value="checkParentDepartment")
	@ResponseBody
	public String checkParentDepartment(HttpServletRequest request, String ids[]) throws Exception{

		for (String id : ids) {
			//组织参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("departmentId", id);
			map.put("deleteFlag", "0");

			long size = manageDepartmentService.getManageDepartmentCountByMap(map);
			
			if(size > 0){
				return size+"";
			}
		}
		
		return "0";
	}
	
	/*=========================================================================================*/
	
	
	/**
	 * 删除部门
	 */
	@RequestMapping(value="deleteManageDepartment")
	@ResponseBody
	public String deleteManageDepartment(HttpServletRequest request, String id){
		
		try {
			manageDepartmentService.delManageDepartmentById(Long.parseLong(id));
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * 增加部门
	 */
	@RequestMapping(value="addManageDepartment")
	@ResponseBody
	public String addManageDepartment(HttpServletRequest request, ManageDepartmentBean departmentBean){
		
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			if(departmentBean.getParentId() != null && !"".equals(departmentBean.getParentId())){
				ManageDepartmentBean parentDept = manageDepartmentService.getManageDepartmentById(departmentBean.getParentId());
				departmentBean.setCompanyId(parentDept.getCompanyId());
				departmentBean.setSubCompanyId(parentDept.getSubCompanyId());
			}else{
				departmentBean.setCompanyId(user.getCompanyId());
				departmentBean.setSubCompanyId(user.getSubCompanyId());
			}
			//if("1".equals(departmentBean.getIsSubCompany().toString())){
				//departmentBean.setParentId(null);
			//}
			manageDepartmentService.addManageDepartment(departmentBean);
			if("1".equals(departmentBean.getIsSubCompany().toString())){
				manageDepartmentService.setDeptToSubCom(departmentBean);
			}
			
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	/**
	 * 升级部门
	 */
	@RequestMapping(value="updateManageDepartment")
	@ResponseBody
	public String updateManageDepartment(HttpServletRequest request, ManageDepartmentBean departmentBean) throws Exception{
		
		try {
			manageDepartmentService.updateManageDepartmentById(departmentBean);
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
	}
	
	/**
	 * 获得部门数据
	 */
	@RequestMapping(value="selectManageDept")
	@ResponseBody
	public Object selectManageDept(HttpServletRequest request, String deptId, String code) throws Exception{
		HttpSession session = request.getSession();
		ManageUserBean userBean = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		//组织参数
		Map<String, String> map = new HashMap<String, String>();
		if(deptId == null || deptId.isEmpty()){
			map.put("companyId", userBean.getCompanyId().toString());
			//map.put("subCompanyId", userBean.getSubCompanyId().toString());
		}
		map.put("id", deptId);
		map.put("code", code);	
		return manageDepartmentService.getManageDepartmentByMap(map);
	}
	
	/**
	 * Method name: upDept <BR>
	 * Description: 上移部门 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param type
	 * @return  Object<BR>
	 */
	@RequestMapping(value="upDept")
	@ResponseBody
	public Object upDept(HttpServletRequest request, String id){
		try {
			manageDepartmentService.upDept(Integer.parseInt(id));
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
	}
	
	/**
	 * Method name: downDept <BR>
	 * Description: 下移部门 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="downDept")
	@ResponseBody
	public Object downDept(HttpServletRequest request, String id){
		try {
			manageDepartmentService.downDept(Integer.parseInt(id));
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
}
