/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageGroupAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月5日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageGroupCondition;
import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.bean.vo.ManageGroupVo;
import com.jftt.wifi.bean.vo.ManageUserVo;
import com.jftt.wifi.bean.vo.ManagerContestMatchVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageGroupService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name:ManageGroupAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月5日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="manageGroup")
public class ManageGroupAction {

	
	@Resource(name="manageGroupService")
	private ManageGroupService manageGroupService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="manageNoticeService")
	private ManageNoticeService manageNoticeService;
	
	
	/**
	 * Method name: toGroupListPage <BR>
	 * Description: 群组页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toGroupListPage")
	public String toGroupListPage(HttpServletRequest request){
		return "manage/groupList";
	}
	
	/**
	 * Method name: selectManageGroupList <BR>
	 * Description: 查询群组列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectManageGroupList")
	@ResponseBody
	public Object selectManageGroupList(HttpServletRequest request, ManageGroupBeanVo vo){

		MMGridPageVoBean<ManageGroupBean> re = new MMGridPageVoBean<ManageGroupBean>();
		vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
		try {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
			ManageUserBean user = manageUserService.findUserById(id);
			vo.setCompanyId(user.getCompanyId());
			vo.setSubCompanyId(user.getSubCompanyId());
			int size = manageGroupService.selectGroupCount(vo);
			List<ManageGroupBean> rows = manageGroupService.selectGroupList(vo);
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toInsertGroupPage <BR>
	 * Description: 新增群组页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toInsertGroupPage")
	public String toInsertGroupPage(HttpServletRequest request){
		return "manage/groupAdd";
	}
	
	/**
	 * Method name: insertManageGroup <BR>
	 * Description: 新增群组 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="insertManageGroup")
	@ResponseBody
	public String insertManageGroup(HttpServletRequest request, ManageGroupBean bean){
		try {
			HttpSession session = request.getSession();
			String[] studentIds = request.getParameterValues("studentIds[]");
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			bean.setCompanyId(user.getCompanyId());
			bean.setSubCompanyId(user.getSubCompanyId());
			bean.setCreateUserId(Integer.parseInt(user.getId()));
			bean.setCreateUserName(user.getName());
			//新增群组
			Integer id = manageGroupService.insertGroup(bean);
			//新增群组、学员的关系
			if(studentIds != null){
				for(String studentId : studentIds){
					ManageGroupStudentBean mgsBean = new ManageGroupStudentBean();
					mgsBean.setGroupId(id);
					mgsBean.setStudentId(Integer.parseInt(studentId));
					manageGroupService.insertGroupAndStudent(mgsBean);
				}
			}
			//保存“满足条件”群组的条件
			if("4".equals(bean.getType().toString())){
				String[] fields = request.getParameterValues("fields[]");
				String[] values = request.getParameterValues("values[]");
				manageGroupService.deleteGroupCondition(bean.getId());
				if(fields != null){
					for(int i = 0 ; i < fields.length; i++){
						ManageGroupCondition condition = new ManageGroupCondition();
						condition.setGroupId(bean.getId());
						condition.setCompareKey(fields[i]);
						condition.setCompareValue(values[i]);
						manageGroupService.insertGroupCondition(condition);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "";
	}
	
	/**
	 * Method name: toUpdateGroupPage <BR>
	 * Description: 修改群组页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateGroupPage")
	public String toUpdateGroupPage(HttpServletRequest request, String id){
		try {
			ManageGroupBean bean = manageGroupService.selectGroupById(Integer.parseInt(id));
			request.setAttribute("group", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "manage/groupUpdate";
	}
	
	@RequestMapping(value="chooseStu")
	public String chooseStu(HttpServletRequest request){
		return "manage/chooseStu";
	}
	
	/**
	 * Method name: selectGroupStudentByGroupId <BR>
	 * Description: 根据群组id获取学员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectGroupStudentByGroupId")
	@ResponseBody
	public Object selectGroupStudentByGroupId(HttpServletRequest request , String id){
		MMGridPageVoBean<ManageUserBean> re = new MMGridPageVoBean<ManageUserBean>();
		try {
			ManageGroupBeanVo group = new ManageGroupBeanVo();
			group.setId(Integer.parseInt(id));
			List<ManageGroupStudentBean> groupStu = manageGroupService.selectGroupStudentListByGroupId(group);
			List<String> studentIds = toList(groupStu);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", studentIds);
			List<ManageUserBean> rows = manageUserService.findUserByListCondition(param);
			re.setTotal(rows.size());
			for(ManageUserBean row : rows){
				for(ManageGroupStudentBean stu : groupStu){
					if(row.getId().equals(stu.getStudentId().toString())){
						row.setStatus(stu.getStatus());
					}
				}
			}
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: getStudentsByGroupId <BR>
	 * Description: 根据群组id查询学员（分页查询） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping("getStudentsByGroupId")
	@ResponseBody
	public Object getStudentsByGroupId(HttpServletRequest request , String id, Integer page, Integer pageSize){
		MMGridPageVoBean<ManageUserBean> re = new MMGridPageVoBean<ManageUserBean>();
		try {
			ManageGroupBeanVo group = new ManageGroupBeanVo();
			group.setId(Integer.parseInt(id));
			group.setFromNum((page-1)*pageSize);
			group.setPageSize(pageSize);
			List<ManageGroupStudentBean> groupStu = manageGroupService.selectGroupStudentListByGroupId(group);
			List<String> studentIds = toList(groupStu);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", studentIds);
			List<ManageUserBean> rows = manageUserService.findUserByListCondition(param,0,0);
			re.setTotal(manageGroupService.selectGroupStudentCountByGroupId(group));
			for(ManageUserBean row : rows){
				for(ManageGroupStudentBean stu : groupStu){
					if(row.getId().equals(stu.getStudentId().toString())){
						row.setStatus(stu.getStatus());
					}
				}
			}
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	public List<String> toList(List<ManageGroupStudentBean> studentList){
		List<String> list = new ArrayList<String>();
		for(ManageGroupStudentBean bean : studentList){
			list.add(bean.getStudentId().toString());
		}
		return list;
	}
	
	/**
	 * Method name: updateManageGroup <BR>
	 * Description: 修改群组信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateManageGroup")
	@ResponseBody
	public String updateManageGroup(HttpServletRequest request, ManageGroupBean bean){
		try {
			String[] studentIds = request.getParameterValues("studentIds[]");
			//修改群组
			manageGroupService.updateGroup(bean);
			/*//删除群组、学员关系
			ManageGroupStudentBean mgsVo = new ManageGroupStudentBean();
			mgsVo.setGroupId(bean.getId());
			manageGroupService.deleteGroupStudent(mgsVo);
			//新增群组、学员的关系
			if(studentIds != null){
				for(String studentId : studentIds){
					ManageGroupStudentBean mgsBean = new ManageGroupStudentBean();
					mgsBean.setGroupId(bean.getId());
					mgsBean.setStudentId(Integer.parseInt(studentId));
					manageGroupService.insertGroupAndStudent(mgsBean);
				}
			}*/
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: toGroupDetail <BR>
	 * Description: 群组详细信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toGroupDetail")
	public String toGroupDetail(HttpServletRequest request, String id){
		ManageGroupBean bean;
		try {
			bean = manageGroupService.selectGroupById(Integer.parseInt(id));
			request.setAttribute("group", JsonUtil.getJson4JavaObject(bean));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/groupDetail";
	}
	
	/**
	 * Method name: updateManageGroupStudent <BR>
	 * Description: 修改群组学员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateManageGroupStudent")
	@ResponseBody
	public String updateManageGroupStudent(HttpServletRequest request, ManageGroupBean bean){
		try {
			String[] studentIds = request.getParameterValues("studentIds[]");
			//修改群组
			//manageGroupService.updateGroup(bean);
			//删除群组、学员关系
			ManageGroupStudentBean mgsVo = new ManageGroupStudentBean();
			mgsVo.setGroupId(bean.getId());
			manageGroupService.deleteGroupStudent(mgsVo);
			//新增群组、学员的关系
			if(studentIds != null){
				for(String studentId : studentIds){
					ManageGroupStudentBean mgsBean = new ManageGroupStudentBean();
					mgsBean.setGroupId(bean.getId());
					mgsBean.setStudentId(Integer.parseInt(studentId));
					manageGroupService.insertGroupAndStudent(mgsBean);
				}
			}
			if("4".equals(bean.getType().toString())){
				String[] fields = request.getParameterValues("fields[]");
				String[] values = request.getParameterValues("values[]");
				manageGroupService.deleteGroupCondition(bean.getId());
				if(fields != null){
					for(int i = 0 ; i < fields.length; i++){
						ManageGroupCondition condition = new ManageGroupCondition();
						condition.setGroupId(bean.getId());
						condition.setCompareKey(fields[i]);
						condition.setCompareValue(values[i]);
						manageGroupService.insertGroupCondition(condition);
					}
				}
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	

	/**
	 * Method name: toUpdateGroupPage <BR>
	 * Description: 修改群组页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCheckGroupPage")
	public String toCheckGroupPage(HttpServletRequest request, String id){
		try {
			ManageGroupBean bean = manageGroupService.selectGroupById(Integer.parseInt(id));
			request.setAttribute("group", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "manage/groupCheck";
	}
	/**
	 * Method name: deleteGroupStudentByStudentId <BR>
	 * Description: 删除一个群组学员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="deleteGroupStudentByStudentId")
	@ResponseBody
	public String deleteGroupStudentByStudentId(HttpServletRequest request ,ManageGroupStudentBean bean){
		try{
			manageGroupService.deleteGroupStudent(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: deleteGroup <BR>
	 * Description: 批量删除群组 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="deleteGroup")
	@ResponseBody
	public String deleteGroup(HttpServletRequest request ){
		try{
			String[] groupIds = request.getParameterValues("ids[]");
			for(String id : groupIds){
				manageGroupService.deleteGroup(Integer.parseInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: checkGroup <BR>
	 * Description: 验证群组 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param status
	 * @return  String<BR>
	 */
	@RequestMapping(value="checkGroupOneStu")
	@ResponseBody
	public String checkGroupOneStu(HttpServletRequest request, String id, String status){
		try{
			HttpSession session = request.getSession();
			ManageGroupBean group = manageGroupService.selectGroupById(Integer.parseInt(id));
			DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			String content = "您加入群组已获通过。<br/>详情如下：群组名称："+group.getName()+"<br/> 通过时间："+date+"<br/>";
			if("4".equals(status)){
				content = "您加入群组的请求被管理员拒绝。详情如下：群组名称："+group.getName()+"<br/> 操作人："+session.getAttribute(Constant.SESSION_NAME)+"<br/>通过时间："+date+"<br/>";
			}
			String[] studentIds = request.getParameterValues("studentIds[]");
			for(String studentId : studentIds){
				ManageGroupStudentBean mgsBean = new ManageGroupStudentBean(Integer.parseInt(id), Integer.parseInt(studentId), Integer.parseInt(status));
				manageGroupService.checkGroup(mgsBean);
				//发送站内信通知学员
				ManageNoticeBean notice = new ManageNoticeBean("学员加入群组通知", content, null, Integer.parseInt(studentId), 1);
				manageNoticeService.insertNotice(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: selectGroupCondition <BR>
	 * Description: 获取满足条件群组的条件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param groupId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectGroupCondition")
	@ResponseBody
	public Object selectGroupCondition(HttpServletRequest request, String groupId){
		List<ManageGroupCondition> list = manageGroupService.selectGroupCondition(Long.parseLong(groupId));
		return list;
	}
	
}
