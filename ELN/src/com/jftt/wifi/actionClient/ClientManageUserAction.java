package com.jftt.wifi.actionClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zefer.html.doc.r;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserRoleBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name: ManageAction <BR>
 * class description: PC用户管理  <BR>
 * Remark: <BR>
 * @version 1.00 2017-03-06
 * @author gehan
 */
@Controller
@RequestMapping(value="pcManageUser")
public class ClientManageUserAction {
	@Resource(name = "manageUserService")
	private ManageUserService manageUserService;

	@Resource(name = "manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;

	@Resource(name = "manageCompanyService")
	private ManageCompanyService manageCompanyService;

	/**
	 * 增加学员用户
	 */
	@RequestMapping(value = "insertStudent",method=RequestMethod.POST)
	@ResponseBody
	public Object inserStudent(HttpServletRequest request, @RequestBody ManageUserBean manageUserBean) {
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			// 新建用户
			HttpSession session = request.getSession();
			ManageUserBean userBean = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			if (!checkCompanyCount(userBean.getCompanyId())) {
				resultMap.put("result", "ACCOUNT_OVER");
				return resultMap;
			}
			// 默认密码
			manageUserBean.setPassword(CommonUtil.getMD5(Constant.MANAGE_USER_PASSWORD));
			ManageDepartmentBean dept = manageDepartmentService.getManageDepartmentById(manageUserBean.getDeptId());
			manageUserBean.setCompanyId(dept.getCompanyId());
			manageUserBean.setSubCompanyId(dept.getSubCompanyId());
			manageUserBean.setStatus(1);
			manageUserService.insert(manageUserBean);
			// 增加用户角色
			String[] roleIds = request.getParameterValues("roleIds[]");
			for (String roleId : roleIds) {
				ManageUserRoleBean manageUserRole = new ManageUserRoleBean();
				manageUserRole.setRoleId(Integer.parseInt(roleId));
				manageUserRole.setUserId(Integer.parseInt(manageUserBean.getId()));
				manageUserService.addUserRole(manageUserRole);
			}
			resultMap.put("result", Constant.AJAX_SUCCESS);
			return resultMap;
		} catch (Exception e) {
			resultMap.put("result", Constant.AJAX_FAIL);
			return resultMap;
		}
	}

	public boolean checkCompanyCount(Integer companyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("status", 1);
		try {
			int count = manageUserService.findUserByCondition(param).size();
			int acountCount = manageCompanyService.selectCompanyById(companyId).getAccountCount();
			if (acountCount > count) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * 修改用户
	 */
	@RequestMapping(value="updateUser")
	@ResponseBody
	public String updatePulianUser(HttpServletRequest request, ManageUserBean manageUserBean){
		
		try {
			//获取部门的信息
			ManageDepartmentBean dept = manageDepartmentService.getManageDepartmentById(manageUserBean.getDeptId());
			manageUserBean.setSubCompanyId(dept.getSubCompanyId());
			//修改用户
			manageUserService.update(manageUserBean);
			//删除该用户原来角色
			manageUserService.deleteUserRoleByUserId(manageUserBean.getId());
			//增加用户角色
			String[] roleIds = request.getParameterValues("roleIds[]");
			for(String roleId : roleIds){
				ManageUserRoleBean manageUserRole = new ManageUserRoleBean();
				manageUserRole.setRoleId(Integer.parseInt(roleId));
				manageUserRole.setUserId(Integer.parseInt(manageUserBean.getId()));
				manageUserService.addUserRole(manageUserRole);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
}
