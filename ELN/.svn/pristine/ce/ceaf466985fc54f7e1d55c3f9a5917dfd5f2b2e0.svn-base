/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: StudentGroupAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-30        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.ManageGroupVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.StudentGroupService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:StudentGroupAction <BR>
 * class description: 学员群组action <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("studentGroupAction")
public class StudentGroupAction {
	private static Logger logger = Logger.getLogger(StudentGroupAction.class);
	
	@Resource(name="studentGroupService")
	private StudentGroupService studentGroupService;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: toJoinGroup <BR>
	 * Description: 跳往加入群组页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toJoinGroup")
	public String toJoinGroup(HttpServletRequest request){
		return "studentGroup/joinGroup";
	}
	
	/**
	 * Method name: getGroupsForMMGrid <BR>
	 * Description: 获取群组（mmGrid展现） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId
	 * @param subCompanyId
	 * @param groupName
	 * @param createUser
	 * @return  Object<BR>
	 */
	@RequestMapping("getGroupsForMMGrid")
	@ResponseBody
	public Object getGroupsForMMGrid(HttpServletRequest request,Integer companyId,Integer subCompanyId,
			Integer userId,String groupName,String createUserName,Integer page,Integer pageSize){
		MMGridPageVoBean<ManageGroupVo> mmGridVo = new MMGridPageVoBean<ManageGroupVo>();
		try {
			//查询出总数目
			logger.info("查询子公司"+subCompanyId+"的群组数量");
			Integer count = studentGroupService.getGroupsCount(companyId,subCompanyId,groupName,createUserName);
			logger.info("子公司"+subCompanyId+"群组数量为"+count);
			//获取fromNum
			Integer fromNum = (int)CommonUtil.getFromNum(String.valueOf(pageSize),String.valueOf(page),count);
			//查询群组
			logger.info("查询子公司"+subCompanyId+"群组");
			List<ManageGroupVo> groups = studentGroupService.getGroupsByConditions(
					companyId,subCompanyId,userId,groupName,createUserName,fromNum,pageSize);
			logger.info("查询子公司"+subCompanyId+"群组完毕");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(groups);
		} catch (Exception e) {
			logger.warn(StudentGroupAction.class,e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: addGroupMember <BR>
	 * Description: 添加群组成员 <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @param groupType
	 * @param userId
	 * @return  Object<BR>
	 */
	@RequestMapping("addGroupMember")
	@ResponseBody
	public Object addGroupMember(HttpServletRequest request,Integer groupId, Integer groupType, Integer userId){
		AjaxReturnVo<Object> arv = new AjaxReturnVo<Object>();
		try {
			logger.info("开始向类型为"+groupType+"群组"+groupId+"添加学员"+userId);
			studentGroupService.addGroupMember(groupId, groupType, userId);
			logger.info("向类型为"+groupType+"群组"+groupId+"添加学员"+userId+"结束");
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.warn(StudentGroupAction.class,e);
		}
		return arv;
	}
	
	/**
	 * Method name: toGroupMembers <BR>
	 * Description: 跳往群组成员页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param groupId
	 * @return  String<BR>
	 */
	@RequestMapping("toGroupMembers")
	public String toGroupMembers(HttpServletRequest request,Integer groupId){
		request.setAttribute("groupId", groupId);
		return "studentGroup/groupMembers";
	}
	
	/**shenhaijun end*/
	
}
