package com.jftt.wifi.action;

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
import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManagePageService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.JsonUtil;

/**
 * 页面管理
 */
@Controller
@RequestMapping(value="managePage")
public class ManagePageAction {
	
	private static Logger logger = Logger.getLogger(ManagePageAction.class);  
	
	@Resource(name="managePageService")
	private ManagePageService managePageService;
	
	/**
	 * 跳转到页面管理页面
	 */
	@RequestMapping(value="pageList")
	public String pageList(HttpServletRequest request){
		
		List<ManagePageBean> pageList = managePageService.getAllManagePage(new HashMap<String, Object>());
		
		request.setAttribute("pageList", JsonUtil.getJson4JavaList(pageList));
				
		return "manage/pageList";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value="addManagePage")
	@ResponseBody
	public Object addManagePage(ManagePageBean managePageBean){
		
		managePageService.addManagePage(managePageBean);
		
		return managePageBean;
	}
	
	/**
	 * 删除页面
	 */
	@RequestMapping(value="delManagePageById")
	@ResponseBody
	public String delManagePageById(long id){
		
		try {
			managePageService.delManagePageById(id);
			
			return "OK";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}
	
	/**
	 * 升级页面, name, url, levelType
	 */
	@RequestMapping(value="updateManagePageById")
	@ResponseBody
	public String updateManagePageById(ManagePageBean managePageBean){
		
		try {
			managePageService.updateManagePageById(managePageBean);
			
			return "OK";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}
}
