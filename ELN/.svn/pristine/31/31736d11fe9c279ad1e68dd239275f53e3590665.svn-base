/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName: TagAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |	2013-01-07		|	JFTT)wangjian	|	original version
 */
package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.CertificateBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserRoleBean;
import com.jftt.wifi.bean.vo.ManageNoticeVo;
import com.jftt.wifi.bean.vo.ManageUserVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.CertificateService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageRoleService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.IdcardValidator;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.SendMessageUtil;
import com.jftt.wifi.util.XlsParserUtil;

/**
 * class name: ManageAction <BR>
 * class description: 管理  <BR>
 * Remark: <BR>
 * @version 1.00 2014-01-07
 * @author JFTT)LU YUNLONG
 */
@Controller
@RequestMapping(value="manageUser")
public class ManageUserAction {
	private static Logger logger = Logger.getLogger(ManageUserAction.class);  
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="manageRoleService")
	private ManageRoleService manageRoleService;
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	
	@Resource(name="certificateService")
	private CertificateService certificateService;
	
	@Resource(name="manageNoticeService")
	private ManageNoticeService manageNoticeService;
	
	@Resource(name="manageParamService")
	private ManageParamService manageParamService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	
	
	/**
	 * 根据企业获取用户数据
	 */
	@RequestMapping(value="getAllManageUserByCompanyId")
	@ResponseBody
	public Object getAllManageUserByCompanyId(HttpServletRequest request, String userName, String companyId){
		
		//组织参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("companyId", Integer.parseInt(companyId));
		try {
			return manageUserService.findUserByCondition(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取用户数据
	 */
	@RequestMapping(value="getAllManageUser")
	@ResponseBody
	public Object getAllManageUser(HttpServletRequest request, String userName){
		
		ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		//组织参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("companyId", user.getCompanyId());
		try {
			return manageUserService.findUserByCondition(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value="delManageUser")
	@ResponseBody
	public String delManageUser(HttpServletRequest request){
		
		try {
			String[] ids = request.getParameterValues("ids[]");
			for (String id : ids) {
				manageUserService.removeOne(id);
				//删除该用户和角色关系
				manageUserService.deleteUserRoleByUserId(id);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="delStudent")
	@ResponseBody
	public String delStudent(HttpServletRequest request){
		
		try {
			String[] ids = request.getParameterValues("ids[]");
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				//判断该学生是否有课程、试卷、学习计划、培训安排等记录，如果有的话，不能删除该学生。
				if(manageUserService.checkStudentStudyRecord(Integer.parseInt(id)) > 0){
					str.append((i+1)+",");
				};
				manageUserService.removeOne(id);
				//删除该用户和角色关系
				manageUserService.deleteUserRoleByUserId(id);
			}
			if(!str.toString().isEmpty()){
				return "第"+str.toString().substring(0, str.length()-1)+"条数据存在学习记录，不能删除。";
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * 跳转到增加用户页面
	 */
	@RequestMapping(value="userAdd")
	public String toUserAddPage(HttpServletRequest request, String id){
		
		if(id != null && !id.equals("")){
			ManageUserBean manageUser = new ManageUserBean();
			try {
				manageUser = manageUserService.findUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("manageUser", JSONObject.fromObject(manageUser));
		}else{
			request.setAttribute("manageUser", JSONObject.fromObject(null));
		}
		
		//部门
		Map<String, String> map = new HashMap<String, String>();
		map.put("deleteFlag", "0");
		//已启用
		map.put("status", "0");
//		List<ManageDepartmentBean> departmentList = manageDepartmentService.getManageDepartmentByMap(map);
//		request.setAttribute("departmentList", JsonUtil.getJson4JavaList(departmentList));
		
		return "manage/userAdd";
	}
	
	/**
	 * 增加用户
	 */
	@RequestMapping(value="addManageUser")
	@ResponseBody
	public String addManageUser(HttpServletRequest request, ManageUserBean manageUserBean){
		
		try {
			
			manageUserBean.setPassword(CommonUtil.getMD5(manageUserBean.getPassword()));
			
			manageUserService.insert(manageUserBean);
			
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
	
	/**
	 * 升级用户
	 */
	@RequestMapping(value="updateManageUser")
	@ResponseBody
	public String updateManageUser(HttpServletRequest request, ManageUserBean manageUserBean){
		try {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			String userName = (String) session.getAttribute(Constant.SESSION_USERNAME_STRING);
			manageUserBean.setId(id);
			manageUserBean.setUserName(userName);
			manageUserService.update(manageUserBean);
			//重置session
			request.getSession().setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: updateManageUserRows <BR>
	 * Description: 批量修改用户部门 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param manageUserBean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateManageUserRows")
	@ResponseBody
	public String updateManageUserRows(HttpServletRequest request, Integer deptId){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for (String id : ids) {
				ManageUserBean user = new ManageUserBean();
				user.setId(id);
				ManageDepartmentBean dept= manageDepartmentService.getManageDepartmentById(deptId);
				user.setSubCompanyId(dept.getSubCompanyId());
				user.setDeptId(deptId);
				System.out.println(dept.getSubCompanyId());
				manageUserService.update(user);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	

	/**
	 * 修改密码
	 */
	@RequestMapping(value="updatePassword")
	@ResponseBody
	public String updatePassword(HttpServletRequest request, String password, String newPassword){
		try {
			HttpSession session = request.getSession();
			
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			//验证原密码是否正确
			ManageUserBean userBean = manageUserService.findUserById(id);
			if(!userBean.getPassword().equalsIgnoreCase(CommonUtil.getMD5(password))){
				return Constant.AJAX_WRONG_PASS;
			}
			
			manageUserService.updateManageUserPassword(id, userBean.getUserName(), CommonUtil.getMD5(newPassword));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * 判断账号是否存在
	 */
	@RequestMapping(value="checkUserId")
	@ResponseBody
	public String checkUserId(HttpServletRequest request, String userId){
		
		//组织参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userId);
		//map.put("deleteFlag", "0");
		
		int size = 0;
		try {
			size = manageUserService.findUserCountByCondition(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size+"";
	}
	
	/**
	 * 判断密码是否正确
	 */
	/**
	 * Method name: checkUserPassword <BR>
	 * Description: checkUserPassword <BR>
	 * Remark: <BR>
	 * @param request
	 * @param password
	 * @return  boolean<BR>
	 */
	@RequestMapping(value="checkUserPassword")
	@ResponseBody
	public boolean checkUserPassword(HttpServletRequest request, String password){
		
		//组织参数
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
	
		ManageUserBean userBean = new ManageUserBean();
		try {
			userBean = manageUserService.findUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if(userBean.getPassword().equalsIgnoreCase(CommonUtil.getMD5(password))){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method name: toPersonnalInfo <BR>
	 * Description: 用户基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toPersonnalBaseInfo")
	public String toPersonnalBaseInfo(HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			session.setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/personnalBaseInfo";
	}
	
	/**
	 * Method name: toChangePassword <BR>
	 * Description: 修改密码页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toChangePassword")
	public String toChangePassword(HttpServletRequest request){
		
		//组织参数
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
	
		ManageUserBean user = new ManageUserBean();
		try {
			user = manageUserService.findUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "manage/changePassword";
	}
	
	/**
	 * Method name: toPersonnalInfo <BR>
	 * Description: 个人信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toPersonnalInfo")
	public String toPersonnalInfo(HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			session.setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/personnalInfo";
	}
	
	/**
	 * Method name: toMyCertificate <BR>
	 * Description: 我的证书 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toMyCertificate")
	public String toMyCertificate(HttpServletRequest request){
		
		//组织参数
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
	
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("receiveUserId", Integer.parseInt(id));
			List<CertificateBean> cerList = certificateService.selectCertificate(param);
			request.setAttribute("cerList", JsonUtil.getJsonString4JavaList(cerList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/myCertificate";
	}
	
	/**
	 * Method name: toMyReceiveNotice <BR>
	 * Description: 收件箱 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value="toMyReceiveNotice")
	public String toMyReceiveNotice(HttpServletRequest request) throws Exception{
		return "manage/myReceiveNotice";
	}
	
	/**
	 * Method name: selectMyRecNoticeList <BR>
	 * Description: 查询收件箱 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectMyRecNoticeList")
	@ResponseBody
	public Object selectMyRecNoticeList(HttpServletRequest request, ManageNoticeVo vo){
		MMGridPageVoBean<ManageNoticeBean> re = new MMGridPageVoBean<ManageNoticeBean>();
		try {
			int size = manageNoticeService.selectCount(vo);
			vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
			List<ManageNoticeBean> rows = manageNoticeService.select(vo);
			for(ManageNoticeBean row : rows){
				row.setSendUserName(manageUserService.findUserById(row.getSendUserId().toString()).getName());
			}
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: deleteRecNotice <BR>
	 * Description: 删除收件箱 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  String<BR>
	 */
	@RequestMapping(value="deleteRecNotice")
	@ResponseBody
	public String deleteRecNotice(HttpServletRequest request, ManageNoticeVo vo){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				vo.setId(Integer.parseInt(id));
				manageNoticeService.deleteNotice(vo);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toMySendNotice <BR>
	 * Description: 发件箱页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping(value="toMySendNotice")
	public String toMySendNotice(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		return "manage/mySendNotice";
	}
	
	/**
	 * Method name: selectMySendNoticeList <BR>
	 * Description: 查询发件箱 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectMySendNoticeList")
	@ResponseBody
	public Object selectMySendNoticeList(HttpServletRequest request, ManageNoticeVo vo){
		MMGridPageVoBean<ManageNoticeBean> re = new MMGridPageVoBean<ManageNoticeBean>();
		try {
			int size = manageNoticeService.selectSendNoticeCount(vo);
			vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
			List<ManageNoticeBean> rows = manageNoticeService.selectSendNotice(vo);
			for(ManageNoticeBean row : rows){
				String recUserName = "";
				for(String recUserId : row.getRecUserName().split(",")){
					ManageUserBean receiver = manageUserService.findUserById(recUserId);
					recUserName += receiver==null?"":receiver.getName()+" ";
				}
				row.setRecUserName(recUserName);
				row.setId(Integer.parseInt(row.getSendUserName().split(",")[0]));
			}
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toMySystemNotice <BR>
	 * Description: 系统信息页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping(value="toMySystemNotice")
	public String toMySystemNotice(HttpServletRequest request) throws Exception{
		return "manage/mySystemNotice";
	}
	
	/**
	 * Method name: selectMySystemNoticeList <BR>
	 * Description: 查询系统信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectMySystemNoticeList")
	@ResponseBody
	public Object selectMySystemNoticeList(HttpServletRequest request, ManageNoticeVo vo){
		MMGridPageVoBean<ManageNoticeBean> re = new MMGridPageVoBean<ManageNoticeBean>();
		try {
			int size = manageNoticeService.selectCount(vo);
			vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
			List<ManageNoticeBean> rows = manageNoticeService.select(vo);
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toPulianUserListPage <BR>
	 * Description: 普连用户页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toPulianUserListPage")
	public String toPulianUserListPage(HttpServletRequest request){
		return "manage/pulianUserList";
	}
	
	/**
	 * Method name: selectPulianUserList <BR>
	 * Description: 查询普连用户 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectPulianUserList")
	@ResponseBody
	public Object selectPulianUserList(HttpServletRequest request, ManageUserVo vo){
		MMGridPageVoBean<ManageUserBean> re = new MMGridPageVoBean<ManageUserBean>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			if(vo.getRoleName() != null && !"".equals(vo.getRoleName())){
				List<String> userIdList = manageRoleService.getManageUserByRoleName(vo.getRoleName(), userBean.getCompanyId());
				param.put("id", userIdList);
			}else{
				param.put("companyId", Constant.PULIAN_COMPANY_ID);
			}
			if(vo.getName() != null && !"".equals(vo.getName())){
				param.put("name", vo.getName());
			}
			if(vo.getUserName() != null && !"".equals(vo.getUserName())){
				param.put("userName", vo.getUserName());
			}
			if(vo.getStatus() != null && !"".equals(vo.getStatus())){
				param.put("status", vo.getStatus());
			}
			
			List<ManageUserBean> list = manageUserService.findUserByListCondition(param);
			int size = list.size();
			re.setTotal(size);
			int fromIndex = (vo.getPage()-1)*vo.getPageSize();
			int toIndex = fromIndex + vo.getPageSize();
			if(fromIndex >= list.size()){
				fromIndex = list.size();
			}
			if(toIndex >= list.size()){
				toIndex = list.size();
			}
			if(list.size() > 0){
				list = list.subList(fromIndex, toIndex);
				for(ManageUserBean user : list){
					user.setRoleList(manageUserService.getRoleByUserId(user.getId().toString()));
				}
				re.setRows(list);
			}else{
				re.setRows(new ArrayList<ManageUserBean>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toInsertPulianUserPage <BR>
	 * Description: 新增普连用户页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toInsertPulianUserPage")
	public String toInsertPulianUserPage(HttpServletRequest request){
		return "manage/pulianUserAdd";
	}
	
	/**
	 * Method name: resetPassword <BR>
	 * Description: 重置密码 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="resetPassword")
	@ResponseBody
	public String resetPassword(HttpServletRequest request){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				manageUserService.resetPassword(id, CommonUtil.getMD5(Constant.MANAGE_USER_PASSWORD));
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * 增加普连用户
	 */
	@RequestMapping(value="insertPulianUser")
	@ResponseBody
	public String insertPulianUser(HttpServletRequest request, ManageUserBean manageUserBean){
		
		try {
			
			
			//新建用户
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			//默认密码
			manageUserBean.setPassword(CommonUtil.getMD5(Constant.MANAGE_USER_PASSWORD));
			manageUserBean.setCompanyId(userBean.getCompanyId());
			manageUserBean.setSubCompanyId(userBean.getSubCompanyId());
			manageUserBean.setStatus(1);
			
			manageUserService.insert(manageUserBean);
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
			return Constant.AJAX_FAIL;
		}
	}
	
	@RequestMapping(value="toPulianUserDetailPage")
	public String toPulianUserDetailPage(HttpServletRequest request, String id){
		try {
			ManageUserBean user = manageUserService.findUserById(id);
			user.setRoleList(manageUserService.getRoleByUserId(id));
			request.setAttribute("user", JsonUtil.getJson4JavaObject(user));
		} catch (Exception e) {
		}
		return "manage/pulianUserDetail";
	}
	
	/**
	 * Method name: toInsertPulianUserPage <BR>
	 * Description: 修改普连用户页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdatePulianUserPage")
	public String toUpdatePulianUserPage(HttpServletRequest request, String id){
		try {
			ManageUserBean user = manageUserService.findUserById(id);
			user.setRoleList(manageUserService.getRoleByUserId(id));
			request.setAttribute("user", JsonUtil.getJson4JavaObject(user));
		} catch (Exception e) {
		}
		return "manage/pulianUserUpdate";
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping(value="updatePulianUser")
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
	
	/**
	 * Method name: freezeAndUnfreezeUser <BR>
	 * Description: 冻结、解冻用户 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param status
	 * @return  String<BR>
	 */
	@RequestMapping(value="freezeAndUnfreezeUser")
	@ResponseBody
	public String freezeAndUnfreezeUser(HttpServletRequest request, String status){
		
		try {
			String[] ids = request.getParameterValues("ids[]");
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			for(String id : ids){
				 ManageUserBean user = new  ManageUserBean();
				 user.setId(id);
				 user.setStatus(Integer.parseInt(status));
				 //判断公司正常用户数量是否达到上限
				 if(status.equals("1")){
					 if(!checkCompanyCount(userBean.getCompanyId())){
						return "ACCOUNT_OVER"; 
					 }
				 }
				 //更新用户状态
				 manageUserService.update(user);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			return Constant.AJAX_FAIL;
		}
	}
	
	
	@RequestMapping(value="toCompanyUserListPage")
	public String toCompanyUserListPage(HttpServletRequest request, String id){
		return "manage/companyUserList";
	}
	
	/**
	 * Method name: selectCompanyUserList <BR>
	 * Description: 查询企业人员列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectCompanyUserList")
	@ResponseBody
	public Object selectCompanyUserList(HttpServletRequest request, ManageUserVo vo){
		MMGridPageVoBean<ManageUserBean> re = new MMGridPageVoBean<ManageUserBean>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			String[] deptIds = request.getParameterValues("deptIds[]");
			if(deptIds != null && deptIds.length > 0){
				List<Integer> deptList = new ArrayList<Integer>();
				for(String deptId : deptIds){
					deptList.add(Integer.parseInt(deptId));
				}
				param.put("deptId",deptList);
			}else{
				param.put("companyId", vo.getCompanyId());
			}
			if(vo.getName() != null && !"".equals(vo.getName())){
				param.put("name", vo.getName());
			}
			if(vo.getUserName() != null && !"".equals(vo.getUserName())){
				param.put("userName", vo.getUserName());
			}
			if(vo.getStatus() != null && !"".equals(vo.getStatus())){
				param.put("status", vo.getStatus());
			}
			if(vo.getSex() != null && !"".equals(vo.getSex())){
				param.put("sex", vo.getSex());
			}
			if(vo.getMobile() != null && !"".equals(vo.getMobile())){
				param.put("mobile", vo.getMobile());
			}
			
			List<ManageUserBean> list = manageUserService.findUserByListCondition(param, vo.getPage(), vo.getPageSize());
			re.setTotal(manageUserService.findUserByListConditionCount(param));
			re.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toStudentListPage <BR>
	 * Description: 学员账号管理页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toStudentListPage")
	public String toStudentListPage(HttpServletRequest request){
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		try {
			ManageCompanyBean company = manageCompanyService.selectCompanyById(userBean.getCompanyId());
			request.setAttribute("initUserId", company.getInitUserId());
			request.setAttribute("isCompany", userBean.getCompanyId()-userBean.getSubCompanyId()==0);
		} catch (Exception e) {
			request.setAttribute("initUserId", 0);
			request.setAttribute("isCompany", false);
			e.printStackTrace();
		}
		return "manage/studentList";
	}
	
	/**
	 * Method name: selectStudentList <BR>
	 * Description: 查看学员账号 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectStudentList")
	@ResponseBody
	public Object selectStudentList(HttpServletRequest request, ManageUserVo vo, String roleName){
		MMGridPageVoBean<ManageUserBean> re = new MMGridPageVoBean<ManageUserBean>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			String[] deptIds = request.getParameterValues("deptIds[]");
			if(deptIds != null && deptIds.length > 0){
				List<Integer> deptList = new ArrayList<Integer>();
				for(String deptId : deptIds){
					deptList.add(Integer.parseInt(deptId));
				}
				param.put("deptId",deptList);
			}
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			param.put("companyId", userBean.getCompanyId());
//			if(userBean.getCompanyId() - userBean.getSubCompanyId() != 0){
//				param.put("subCompanyId", userBean.getSubCompanyId());
//			}
			if(vo.getName() != null && !"".equals(vo.getName())){
				param.put("name", vo.getName());
			}
			if(vo.getUserName() != null && !"".equals(vo.getUserName())){
				param.put("userName", vo.getUserName());
			}
			if(vo.getStatus() != null && !"".equals(vo.getStatus())){
				param.put("status", vo.getStatus());
			}
			if(vo.getSex() != null && !"".equals(vo.getSex())){
				param.put("sex", vo.getSex());
			}
			if(vo.getDeptId() != null && !"".equals(vo.getDeptId())){
				param.put("deptId", vo.getDeptId());
			}
			if(vo.getPostId() != null && !"".equals(vo.getPostId())){
				param.put("postId", vo.getPostId());
			}
			if(vo.getPhoto() != null && !vo.getPhoto().isEmpty()){
				param.put("photo", vo.getPhoto());
			}
			if(roleName != null && !roleName.isEmpty()){
				//根据角色名称获取人员id
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("roleName", roleName);
				map.put("companyId", userBean.getCompanyId());
				List<String> ids = manageUserService.findUserIdByRoleName(map);
				param.put("id", ids);
			}
			
			List<ManageUserBean> list = manageUserService.findUserByListCondition(param,vo.getPage(),vo.getPageSize());
			re.setTotal(manageUserService.findUserByListConditionCount(param));
			for(ManageUserBean user : list){
				user.setRoleList(manageUserService.getRoleByUserId(user.getId().toString()));
			}
			re.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toStudentListPage <BR>
	 * Description: 新增学员账号页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toInsertStudentPage")
	public String toInsertStudentPage(HttpServletRequest request, String id){
		return "manage/studentAdd";
	}
	
	/**
	 * 增加学员用户
	 */
	@RequestMapping(value="inserStudent")
	@ResponseBody
	public String inserStudent(HttpServletRequest request, ManageUserBean manageUserBean){
		
		try {
			//新建用户
			HttpSession session = request.getSession();
			ManageUserBean userBean = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			if(!checkCompanyCount(userBean.getCompanyId())){
				return "ACCOUNT_OVER";
			}
			//默认密码
			manageUserBean.setPassword(CommonUtil.getMD5(Constant.MANAGE_USER_PASSWORD));
			ManageDepartmentBean dept = manageDepartmentService.getManageDepartmentById(manageUserBean.getDeptId());
			/*manageUserBean.setCompanyId(userBean.getCompanyId());
			//判断是否新增子公司的用户
			if("1".equals(dept.getIsSubCompany().toString())){
				manageUserBean.setSubCompanyId(manageUserBean.getDeptId());
			}else{
				manageUserBean.setSubCompanyId(userBean.getSubCompanyId());
			}*/
			manageUserBean.setCompanyId(dept.getCompanyId());
			manageUserBean.setSubCompanyId(dept.getSubCompanyId());
			manageUserBean.setStatus(1);
			manageUserService.insert(manageUserBean);
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
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toUpdateStudentPage <BR>
	 * Description: 修改学员信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateStudentPage")
	public String toUpdateStudentPage(HttpServletRequest request, String id){
		try {
			ManageUserBean user = manageUserService.findUserByIdCondition(id);
			user.setRoleList(manageUserService.getRoleByUserId(id));
			request.setAttribute("user", JsonUtil.getJson4JavaObject(user));
		} catch (Exception e) {
		}
		return "manage/studentUpdate";
	}
	
	/**
	 * Method name: toUpdateStudentPage <BR>
	 * Description: 修改学员信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toStudentDetailtPage")
	public String toStudentDetailtPage(HttpServletRequest request, String id){
		try {
			ManageUserBean user = manageUserService.findUserByIdCondition(id);
			user.setRoleList(manageUserService.getRoleByUserId(id));
			request.setAttribute("user", JsonUtil.getJson4JavaObject(user));
		} catch (Exception e) {
		}
		return "manage/studentDetail";
	}
	
	/**
	 * Method name: toSendMessagePage <BR>
	 * Description: 发送站内信 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toSendMessagePage")
	public String toSendMessagePage(HttpServletRequest request){
		return "manage/sendMessage";
	}
	
	/**
	 * Method name: chooseReceiver <BR>
	 * Description: 选择收件人 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="chooseReceiver")
	public String chooseReceiver(HttpServletRequest request){
		return "manage/chooseReceiver";
	}
	
	/**
	 * Method name: chooseDept <BR>
	 * Description: 选择部门 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="chooseDept")
	public String chooseDept(HttpServletRequest request){
		return "manage/chooseDept";
	}
	
	/**
	 * Method name: sendNotice <BR>
	 * Description: 发送站内信 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param title
	 * @param content
	 * @return  String<BR>
	 */
	@RequestMapping(value="sendNotice")
	@ResponseBody
	public String sendNotice(HttpServletRequest request, String title, String content){
		try{
			HttpSession session = request.getSession();
			ManageUserBean userBean = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			String[] deptIds = request.getParameterValues("deptIds[]");
			String[] receiverIds = request.getParameterValues("receiverIds[]");
			if(deptIds != null){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptId", toIntegerList(deptIds));
				List<ManageUserBean> list = manageUserService.findUserByListCondition(map);
				List<String> userIdList = toStringList(receiverIds==null?(new String[0]):receiverIds);
				for(int i=list.size()-1;i>=0;i--){
					ManageUserBean user = list.get(i);
					if(!userIdList.contains(user.getId())){
						manageNoticeService.insertNotice(new ManageNoticeBean(title, content, Integer.parseInt(userBean.getId()), 
								Integer.parseInt(user.getId()), 2));
					}
				}
			}
			if(receiverIds != null){
				for(String receiverId : receiverIds){
					manageNoticeService.insertNotice(new ManageNoticeBean(title, content, Integer.parseInt(userBean.getId()), 
							Integer.parseInt(receiverId), 2));
				}
			}
			
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: sendNoticeDetail <BR>
	 * Description: 站内信详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="sendNoticeDetail")
	public Object sendNoticeDetail(HttpServletRequest request, String id){
		try{
			request.setAttribute("notice", JsonUtil.getJson4JavaObject(manageNoticeService.selectById(Integer.parseInt(id))));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "manage/noticeDetail";
	}
	
	/**
	 * Method name: noticeDetail <BR>
	 * Description: 专题详细页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="noticeDetail")
	public Object noticeDetail(HttpServletRequest request, String id){
		try{
			//将该站内信改为已读
			manageNoticeService.updateNoticeReadStatus(Integer.parseInt(id));
			request.setAttribute("notice", JsonUtil.getJson4JavaObject(manageNoticeService.selectById(Integer.parseInt(id))));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "manage/noticeDetail";
	}
	
	public boolean checkCompanyCountAndSendMail(Integer companyId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		try {
			int userCount = manageUserService.findUserByCondition(param).size();
			ManageCompanyBean company =  manageCompanyService.selectCompanyById(companyId);
			int acountCount = company.getAccountCount();
			if(acountCount > userCount){
				return true;
			}
			if(userCount/acountCount*100 > 95){
				int count = 1;
		    	if(company.getEmail() != null && !"".equals(company.getEmail())){
		    		String content = "尊敬的用户：您的账号使用人数已达"+userCount+"个，可用账户仅剩"+(acountCount-userCount)+"个，为保证您的使用不受影响，请及时联系我们进行续费调整，感谢您的长期支持和信赖。<br/>安培在线，互联网+安全培训服务专家，为您提供随时随地的安全培训。<br/>Powered by 安培在线<br/>咨询热线：4008-430-400<br/>";
		    		while(!SendMessageUtil.sendEmail(company.getEmail(), "安培在线系统会员账号可用数量不足5%", content) && count++ < 3);
		    	}
		    	if(company.getPhoneNum() != null && !"".equals(company.getPhoneNum())){
		    		String messageContent = "尊敬的用户：您的账号使用人数已达"+userCount+"个，可用账户仅剩"+(acountCount-userCount)+"个，为保证您的使用不受影响，请及时联系我们进行续费调整，感谢您的长期支持和信赖。";
		    		while(!SendMessageUtil.sendSMS(messageContent, company.getPhoneNum()) && count++ < 3);
		    	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkCompanyCount(Integer companyId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("status", 1);
		try {
			int count = manageUserService.findUserByCondition(param).size();
			int acountCount = manageCompanyService.selectCompanyById(companyId).getAccountCount();
			if(acountCount > count){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Method name: toUploadPhoto <BR>
	 * Description: 上传头像页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUploadPhoto")
	public String toUploadPhoto(HttpServletRequest request){
		return "manage/uploadPhoto";
	}
	
	/**
	 * Method name: toUploadPhoto_ <BR>
	 * Description: 上传照片页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUploadPhoto_")
	public String toUploadPhoto_(HttpServletRequest request, String userId, String photo){
		request.setAttribute("userId", userId);
		request.setAttribute("photo", photo);
		return "manage/uploadPhoto_";
	}
	
	/**
	 * Method name: uploadPhoto <BR>
	 * Description: 上传头像 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param user
	 * @return  String<BR>
	 */
	@ResponseBody
	@RequestMapping(value="uploadPhoto")
	public String uploadPhoto(HttpServletRequest request, ManageUserBean user){
		try{
			manageUserService.update(user);
			request.getSession().setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(user.getId()));
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: uploadPhoto_ <BR>
	 * Description: 上传用户照片 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param user
	 * @return  String<BR>
	 */
	@ResponseBody
	@RequestMapping(value="uploadPhoto_")
	public String uploadPhoto_(HttpServletRequest request, ManageUserBean user){
		try{
			manageUserService.update(user);
			return Constant.AJAX_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	public List<Integer> toIntegerList(String[] deptIds){
		List<Integer> deptIdList = new ArrayList<Integer>();
		for(String deptId : deptIds){
			deptIdList.add(Integer.parseInt(deptId));
		}
		return deptIdList;
	}
	
	public List<String> toStringList(String[] userIds){
		List<String> userIdList = new ArrayList<String>();
		for(String deptId : userIds){
			userIdList.add(deptId);
		}
		return userIdList;
	}
	
	@RequestMapping(value="importFile")
	public String importFile(HttpServletRequest request){
		return "manage/importStudentFile";
	}
	
	/**
	 * Method name: importDeptFile <BR>
	 * Description: 导入部门文件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="importDeptFile")
	public String importDeptFile(HttpServletRequest request){
		return "manage/importDeptFile";
	}
	
	/**
	 * Method name: 解析部门文件数据
	 * Description: importXlsFileData <BR>
	 * Remark: <BR>  void<BR>
	 */
	@RequestMapping(value="importDeptXlsFileData")
	@ResponseBody
	public Object  importDeptXlsFileData(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){
		
		boolean flag = true;
		//1、获取文件储存的地址。
		String pageFileName= imgFile.getOriginalFilename();//.getName() ;
		String filetype = pageFileName.split("\\.")[1];
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/

		//获取拼接地址
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extendUrl = "student/template/"+df.format(new Date());
		
		JSONObject reObj = new JSONObject();
		try {
			HttpSession session = request.getSession();
			ManageUserBean bean = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			//将上传的文件写到new出来的文件中
			String 	filePath =saveUrl +extendUrl+"/"+getUUID()+pageFileName;
			FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);
			
			int maxNum = XlsParserUtil.getXLSNum(filePath,filetype);
			if(maxNum>maxNum){
				reObj.put("errCode", 10);
			}
			else{
			
			JSONArray array = new JSONArray();
		
			//根据文件类型，去解析
			 if("xls".equals(filetype)){
				array = XlsParserUtil.importXlsParser(filePath,"utf-8",1,1,0);
			}else if("xlsx".equals(filetype)){
				array = XlsParserUtil.importXlsxParser(filePath,"utf-8",1,1,0);
			}else{
				
			}
			 StringBuffer errCode = new StringBuffer();
			 //此处缺少校验和异常处理
			 for(int i=1;i<array.size();i++){
				 JSONArray sArr = array.getJSONArray(i);
				 if(sArr.size() == 0){
					 continue; 
				 }
				 if(!checkSAARDeptNull(sArr)){
					 errCode.append("第"+i+"行数据不能为空；");
					 continue;						
				 }
				 if(sArr.getString(0).length() > 30){
					 errCode.append("第"+i+"行部门编码长度大于30，不能导入；");
					 continue;
				 }
				 ManageDepartmentBean dept = new ManageDepartmentBean();
				 dept.setCompanyId(bean.getCompanyId());
				 dept.setSubCompanyId(bean.getSubCompanyId());
				 //判断部门编码是否重复
				 Map<String, String> map = new HashMap<String, String>();
				 map.put("code", sArr.getString(0));
				 map.put("companyId", bean.getCompanyId().toString());
				 List<ManageDepartmentBean> deptL = manageDepartmentService.getManageDepartmentByMap(map);
				 if(deptL.size() > 0){
					 errCode.append("第"+i+"行部门编码已存在，不能导入；");
					 continue;	
				 }
				 dept.setCode(sArr.getString(0));
				 //判断部门名称的长度
				 if(sArr.getString(1).length() > 30){
					 errCode.append("第"+i+"行部门名称长度大于30，不能导入；");
					 continue;
				 }
				 dept.setName(sArr.getString(1));
				 dept.setDescr(sArr.getString(2));
				 //判断上层节点是否存在
				 if(sArr.getString(3) != null && !sArr.getString(3).isEmpty()){
					 map.put("code", sArr.getString(3));
					 List<ManageDepartmentBean> _deptL = manageDepartmentService.getManageDepartmentByMap(map);
					 if(_deptL.size() == 0){
						 errCode.append("第"+i+"行上层节点部门编码不存在，不能导入；");
						 continue;
					 }else{
						 dept.setSubCompanyId(_deptL.get(0).getSubCompanyId());
						 dept.setParentId((int)_deptL.get(0).getId());
					 }
				 }
				 if("部门".equals(sArr.getString(4))){
					 dept.setIsSubCompany(2);
				 }else{
					 dept.setIsSubCompany(1);
				 }
				 flag = false;
				 manageDepartmentService.addManageDepartment(dept);
				 if("1".equals(dept.getIsSubCompany().toString())){
						manageDepartmentService.setDeptToSubCom(dept);
				}
			 }
			 if(flag && errCode.toString().isEmpty()){
				 reObj.put("errCode", "Excel中没有数据。");
				 return reObj;
			 }
			 if("".equals(errCode.toString())){
				 reObj.put("errCode", "上传成功");
			 }else{
				 reObj.put("errCode", errCode.toString());
			 }
			}
		}catch(Exception e){
			System.out.println(e);
			reObj.put("errCode", "解析文件失败，请重试。");
			return reObj;
		}
		return reObj;
	}
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    } 
	 
	 /**
		 * Method name: 解析文件数据
		 * Description: importXlsFileData <BR>
		 * Remark: <BR>  void<BR>
		 */
		@RequestMapping(value="importXlsFileData")
		@ResponseBody
		public Object  importXlsFileData(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){
			
			boolean flag = true;
			//1、获取文件储存的地址。
			String pageFileName= imgFile.getOriginalFilename();//.getName() ;
			String filetype = pageFileName.split("\\.")[1];
			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/

			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "student/template/"+df.format(new Date());
			
			JSONObject reObj = new JSONObject();
			try {
				HttpSession session = request.getSession();
				ManageUserBean bean = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
				
				File file = new File(saveUrl+extendUrl);
				if(!file.exists()){
					file.mkdirs();
				}
				//将上传的文件写到new出来的文件中
				String 	filePath =saveUrl +extendUrl+"/"+getUUID()+pageFileName;
				FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);
				
				int maxNum = XlsParserUtil.getXLSNum(filePath,filetype);
				if(maxNum>maxNum){
					reObj.put("errCode", 10);
				}
				else{
				
				JSONArray array = new JSONArray();
			
					//根据文件类型，去解析
					 if("xls".equals(filetype)){
						array = XlsParserUtil.importXlsParser(filePath,"utf-8",1,1,0);
					}else if("xlsx".equals(filetype)){
						array = XlsParserUtil.importXlsxParser(filePath,"utf-8",1,1,0);
					}else{
						
					}
					 StringBuffer errCode = new StringBuffer();
					 //此处缺少校验和异常处理
					 for(int i=1;i<array.size();i++){
						 if(!checkCompanyCount(bean.getCompanyId())){
							 errCode.append("学员数量已达上限，第"+(i-1)+"行数据以后人员不能导入。");
							 reObj.put("errCode", errCode.toString());
							return reObj;
							}
						 JSONArray sArr = array.getJSONArray(i);
						 if(sArr.size() == 0){
							 continue; 
						 }
						 if(sArr.size() < 10){
							 errCode.append("第"+i+"行数据不完整；");
							 continue;
						 }
						 if(!checkSAARNull(sArr)){
								errCode.append("第"+i+"行数据不能为空；");
								continue;						
						 }
						 if(!sArr.isEmpty()){
							 ManageUserBean user = new ManageUserBean();
							 bean.setStatus(1);
							 user.setCompanyId(bean.getCompanyId());
							 //判断用户名是否重复
							 Map<String, Object> userParam = new HashMap<String, Object>();
							 userParam.put("userName", sArr.getString(0));
							 userParam.put("companyId", bean.getCompanyId());
							 List<ManageUserBean> userList = manageUserService.findUserByCondition(userParam);
							 if(userList != null && userList.size() > 0){
								 errCode.append("第"+i+"行数据，用户名重复，不能导入；");
								 continue;
							 }
							 user.setUserName(sArr.getString(0));
							 user.setName(sArr.getString(1));
							 user.setSex("男".equals(sArr.getString(2))?1:2);
							 user.setBirthDay(sArr.getString(3));
							 //验证身份证合法性
							 IdcardValidator validator = new IdcardValidator();
							 if(!validator.isValidatedAllIdcard(sArr.getString(4))){
								 errCode.append("第"+i+"行数据，身份证不合法，不能导入；");
								 continue;
							 }
							 user.setIdCard(sArr.getString(4));
							 //根据部门编码获取id
							 Map<String, String> map = new HashMap<String, String>();
							 map.put("code", sArr.getString(5));
							 map.put("companyId", bean.getCompanyId().toString());
							 List<ManageDepartmentBean> deptL = manageDepartmentService.getManageDepartmentByMap(map);
							 if(deptL != null && deptL.size() > 0 ){
								 if(deptL.get(0).getIsSubCompany()-1==0){
									 errCode.append("第"+i+"行数据，部门编码是分公司，不能导入；");
									 continue;
								 }
								 user.setDeptId((int)deptL.get(0).getId());
								 user.setSubCompanyId(deptL.get(0).getSubCompanyId());
							 }else{
								 errCode.append("第"+i+"行数据，部门编码不存在，不能导入；");
								 continue;
							 }
							 //根据岗位编码获取id
							 Map<String, Object> param = new HashMap<String, Object>();
							 param.put("code", sArr.getString(6));
							 param.put("companyId", bean.getCompanyId());
							 List<ManagePostBean> postL = manageParamService.selectManagePost(param);
							 if(postL != null && postL.size() > 0 ){
								 user.setPostId((int)postL.get(0).getId());
							 }else{
								 errCode.append("第"+i+"行数据，岗位编码不存在，不能导入；");
								 continue;
							 }
							 user.setMobile(sArr.getString(7));
							 user.setEmail(sArr.getString(8));
							 user.setStatus(1);
							 user.setPassword(CommonUtil.getMD5("123456"));
							 int userId = -1;
							 //判断角色是否存在
							 if(sArr.getString(9) == null|| "".equals(sArr.getString(9))){
								 errCode.append("第"+i+"行数据角色不能为空。");
								 	continue;
							 }
							 Map<String, String> map_ = new HashMap<String, String>();
							 map_.put("name", sArr.getString(9));
							 map_.put("companyId", user.getCompanyId().toString());
							 /*modify by luyl at 2016年1月25日14:47:10 角色不局限于分公司角色*/
							// map_.put("subCompanyId", user.getSubCompanyId().toString());
							 List<ManageRoleBean> list = manageRoleService.getManageRoleByMap(map_);
							 if(list != null && list.size() > 0){
								 try{
										userId = manageUserService.insert(user);
										flag = false;
									 }catch(Exception e){
									 	errCode.append("第"+i+"行数据导入错误。");
									 	continue;
									 }
								ManageUserRoleBean manageUserRole = new ManageUserRoleBean();
								manageUserRole.setRoleId((int)list.get(0).getId());
								manageUserRole.setUserId(userId);
								try{
									manageUserService.addUserRole(manageUserRole);
								}catch(Exception e){
								 	errCode.append("第"+i+"行数据角色导入错误。");
								 	continue;
								}
							 }else{
								 errCode.append("第"+i+"行数据，"+sArr.getString(9)+"角色不存在，请在人员导入后重新设置；");
								 continue;
							 }
						 }
					 }
					 if(flag && errCode.toString().isEmpty()){
						 reObj.put("errCode", "Excel中没有数据。");
						 return reObj;
					 }
					 if("".equals(errCode.toString())){
						 reObj.put("errCode", "上传成功");
					 }else{
						 reObj.put("errCode", errCode.toString());
					 }
				}
			}catch(Exception e){
				System.out.println(e);
				reObj.put("errCode", "解析文件失败，请重试。");
				return reObj;
			}
			return reObj;
		}
		
		public boolean checkSAARNull(JSONArray sArr){
			for(int i = 0; i < 7; i++){
				if(sArr.getString(i) == null || "".equals(sArr.getString(i))){
					return false;
				}
			}
			return true;
		}
		
		public boolean checkSAARDeptNull(JSONArray sArr){
			if(sArr.getString(0) == null || "".equals(sArr.getString(0))){
				return false;
			}
			if(sArr.getString(1) == null || "".equals(sArr.getString(1))){
				return false;
			}
			if(sArr.getString(4) == null || "".equals(sArr.getString(4))){
				return false;
			}
			return true;
		}
	
		/**
		 * Method name: exportStudent <BR>
		 * Description: 导出学生excel <BR>
		 * Remark: <BR>
		 * @param request
		 * @param response
		 * @param vo
		 * @param roleName  void<BR>
		 */
		@RequestMapping("exportStudent")
		public void exportStudent(HttpServletRequest request, HttpServletResponse response, ManageUserVo vo, String roleName, String deptIds){
			try {
				Map<String, Object> param = new HashMap<String, Object>();
				String[] deptIdArray = deptIds.split(",");
				if(deptIdArray != null && deptIdArray.length > 0){
					List<Integer> deptList = new ArrayList<Integer>();
					for(String deptId : deptIdArray){
						deptList.add(Integer.parseInt(deptId));
					}
					param.put("deptId",deptList);
				}
				ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
				param.put("companyId", userBean.getCompanyId());
				if(vo.getName() != null && !"".equals(vo.getName())){
					param.put("name", vo.getName());
				}
				if(vo.getUserName() != null && !"".equals(vo.getUserName())){
					param.put("userName", vo.getUserName());
				}
				if(vo.getStatus() != null && !"".equals(vo.getStatus())){
					param.put("status", vo.getStatus());
				}
				if(vo.getSex() != null && !"".equals(vo.getSex())){
					param.put("sex", vo.getSex());
				}
				if(vo.getDeptId() != null && !"".equals(vo.getDeptId())){
					param.put("deptId", vo.getDeptId());
				}
				if(vo.getPostId() != null && !"".equals(vo.getPostId())){
					param.put("postId", vo.getPostId());
				}
				if(vo.getPhoto() != null && !vo.getPhoto().isEmpty()){
					param.put("photo", vo.getPhoto());
				}
//				if(roleName != null && !roleName.isEmpty()){
//					//根据角色名称获取人员id
//					Map<String,Object> map = new HashMap<String,Object>();
//					map.put("roleName", roleName);
//					map.put("companyId", userBean.getCompanyId());
//					List<String> ids = manageUserService.findUserIdByRoleName(map);
//					param.put("id", ids);
//				}
				
				List<ManageUserBean> list = manageUserService.findUserByListCondition(param);
				for(ManageUserBean user : list){
					user.setRoleList(manageUserService.getRoleByUserId(user.getId().toString()));
				}
				HSSFWorkbook workbook = manageUserService.exportStudent(list);
				
				response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
				response.addHeader("Content-Disposition", "attachment;filename="+ new String("学生列表.xls".getBytes(),"iso8859-1"));
				//得到向客户端输出二进制数据的对象 
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				workbook.write(toClient);
				toClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	
}
