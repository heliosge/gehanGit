/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageParamAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月31日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jftt.wifi.bean.ExpandParamBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageExpandFieldBean;
import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageIndustryCategoryService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name:ManageParamAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月31日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="manageParam")
public class ManageParamAction {
	
	@Autowired
	private ManageParamService manageParamService;
	
	@Autowired
	private ManageUserService manageUserService;
	
	@Resource(name="manageIndustryCategoryService")
	private ManageIndustryCategoryService manageIndustryCategoryService;
	
	
	private static Logger log = Logger.getLogger(ManageParamAction.class);

	/**
	 * Method name: queryParam <BR>
	 * Description: 查询公司的用户字段配置 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="queryParam")
	public String queryParam(HttpServletRequest request,ModelMap model){
		//入口日志
		log.debug("查询公司配置的用户参数页面");
		//1、获取语言类型
		
		String language= String.valueOf(RequestContextUtils.getLocale(request));

		//2、查找该公司所有的选填和必填字段。
		int companyId =0;
		try {
			companyId = getCompanyId(request);
			List<ExpandParamBean> companyParamList = manageParamService.queryCompanyParamList(companyId,language);
			
			//3、查找所有的可以配置的字段
			List<ManageExpandFieldBean> paramList= manageParamService.queryParamList(language);
			
			model.addAttribute("companyParamList", companyParamList);
			model.addAttribute("paramList", paramList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询公司配置的用户参数失败.公司id为："+companyId);
		}
		
		return "manage/setParam";
	}
	
	@RequestMapping(value="selectField")
	public String selectField(HttpServletRequest request,int isEmpty,ModelMap model){
		model.put("isEmpty", isEmpty);
		try {
			int companyId = getCompanyId(request);
			List<ExpandParamBean> companyParamList = new ArrayList<ExpandParamBean>();
			companyParamList = manageParamService.queryCompanyParamList(companyId,null);
			model.put("fieldList", JsonUtil.getJson4JavaList(companyParamList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "manage/selectField";
		//入口日志
	}
	/**
	 * Method name: saveUserParam <BR>
	 * Description: 保存用户配置信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="saveUserParam")
	@ResponseBody
	public String saveUserParam(HttpServletRequest request,String ids,int isEmpty){
		//入口日志
		log.debug("保存配置的用户参数");
		try {
			int	companyId = getCompanyId(request);
			List<ExpandParamBean>  paramList = new ArrayList<ExpandParamBean>();
			if(!ids.isEmpty()){
				List<String> idArr =  Arrays.asList(ids.split("\\|"));
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
					ExpandParamBean bean = new ExpandParamBean();
					bean.setCompanyId(companyId);
					bean.setPropertyId(Integer.valueOf(idArr.get(i)));
					bean.setIsEmpty(isEmpty);
					paramList.add(bean);
				}
			}else{
				ExpandParamBean bean = new ExpandParamBean();
				bean.setCompanyId(companyId);
				bean.setIsEmpty(isEmpty);
				paramList.add(bean);
			}
			manageParamService.saveUserParam(paramList);
		}catch(Exception e){
			log.error("保存配置的用户参数失败."+e);
			e.printStackTrace();
			return 	Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: 获取集团公司id
	 * Description: getCompanyId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  int<BR>
	 */
	private int getCompanyId (HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String userId = String.valueOf(session.getAttribute(Constant.SESSION_USERID_LONG));
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
		return manageUserBean.getCompanyId();
	}
	
	/*luyunlong begin*/
	
	/**
	 * Method name: toExpandFieldPage <BR>
	 * Description: 扩展字段页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toExpandFieldPage")
	public String toExpandFieldPage(HttpServletRequest request){
		
		return "manage/expandField";
	}
	
	/**
	 * Method name: selectExpandField <BR>
	 * Description: 查询【扩展字段】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectExpandField")
	@ResponseBody
	public Object selectExpandField(HttpServletRequest request, String page, String pageSize, String name){

		MMGridPageVoBean<ManageExpandFieldBean> re = new MMGridPageVoBean<ManageExpandFieldBean>();
		try {
			int size = manageParamService.selectManageExpandFieldCount();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", name);
			if(page != null){
				param.put("pageSize", pageSize);
				param.put("fromNum", (Integer.parseInt(page)-1)*Integer.parseInt(pageSize));
			}
			//param.put("companyId", ((ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN)).getCompanyId());
			List<ManageExpandFieldBean> rows = manageParamService.selectManageExpandField(param);
			re.setTotal(size);
			re.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	@RequestMapping(value="selectExpandFieldByName")
	@ResponseBody
	public Object selectExpandFieldByName(HttpServletRequest request, String name, String id){

		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("name", name);
			param.put("id", id);
			return manageParamService.selectManageExpandField(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: saveUserParam <BR>
	 * Description: 删除【扩展字段】 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramList
	 * @return  String<BR>
	 */
	@RequestMapping(value="deleteExpandField")
	@ResponseBody
	public String deleteExpandField(HttpServletRequest request){
		//入口日志
		try {
			String[] ids = request.getParameterValues("ids");
			for(String id : ids){
				manageParamService.deleteManageExpandField(Integer.parseInt(id));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return 	Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: insertExpandField <BR>
	 * Description: 新增扩展字段 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="insertExpandField")
	@ResponseBody
	public String insertExpandField(HttpServletRequest request, ManageExpandFieldBean bean){
		try {
			manageParamService.insertManageExpandField(bean);
		}catch(Exception e){
			e.printStackTrace();
			return 	Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: updateExpandField <BR>
	 * Description: 修改人员扩展字段 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateExpandField")
	@ResponseBody
	public String updateExpandField(HttpServletRequest request, ManageExpandFieldBean bean){
		try {
			manageParamService.updateManageExpandField(bean);
		}catch(Exception e){
			e.printStackTrace();
			return 	Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: toManagePostPage <BR>
	 * Description: 岗位管理 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param isEmpty
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value="toManagePostPage")
	public String toManagePostPage(HttpServletRequest request){
		return "manage/postTree";
	}
	
	/**
	 * Method name: toManagePostPage <BR>
	 * Description: 企业分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param isEmpty
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value="toCompanyIndustryPage")
	public String toCompanyIndustryPage(HttpServletRequest request){
		return "manage/companyIndustryTree";
	}
	
	/**
	 * Method name: selectManagePost <BR>
	 * Description: 查询岗位 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectManagePost")
	@ResponseBody
	public Object selectManagePost(HttpServletRequest request, String id, String code){
		try {
			HttpSession session = request.getSession();
			ManageUserBean userBean = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", userBean.getCompanyId());
//			if(userBean.getCompanyId() - userBean.getSubCompanyId() != 0){
//				param.put("subCompanyId", userBean.getSubCompanyId());
//			}
			param.put("id", id);
			param.put("code", code);
			return manageParamService.selectManagePost(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: addManageIndustry <BR>
	 * Description: 新增企业分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addManageIndustry")
	@ResponseBody
	public Object addManageIndustry(HttpServletRequest request, ManageIndustryCategoryBean bean){
		try {
			manageIndustryCategoryService.insert(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateManageIndustry <BR>
	 * Description: 修改企业分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateManageIndustry")
	@ResponseBody
	public Object updateManageIndustry(HttpServletRequest request, ManageIndustryCategoryBean bean){
		try {
			manageIndustryCategoryService.update(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteManageIndustry <BR>
	 * Description: 删除企业分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteManageIndustry")
	@ResponseBody
	public Object deleteManageIndustry(HttpServletRequest request, String id){
		try {
			manageIndustryCategoryService.delete(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: addManagePost <BR>
	 * Description: 新增岗位 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param post
	 * @return  Object<BR>
	 */
	@RequestMapping(value="addManagePost")
	@ResponseBody
	public Object addManagePost(HttpServletRequest request, ManagePostBean post){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
			post.setCompanyId(user.getCompanyId());
			post.setSubCompanyId(user.getSubCompanyId());
			manageParamService.inserManagePost(post);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateManagePost <BR>
	 * Description: 修改岗位 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param post
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateManagePost")
	@ResponseBody
	public Object updateManagePost(HttpServletRequest request, ManagePostBean post){
		try {
			manageParamService.updateManagePost(post);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteManagePost <BR>
	 * Description: 删除岗位 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteManagePost")
	@ResponseBody
	public Object deleteManagePost(HttpServletRequest request, String id){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("postId", Integer.parseInt(id));
			List<ManageUserBean> list = manageUserService.findUserByCondition(map);
			if(list != null && list.size() > 0){
				return "HAVE_USER";
			}
			manageParamService.deleteManagePost(Integer.parseInt(id));;
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	@RequestMapping(value="queryCompanyParamList")
	@ResponseBody
	public Object queryCompanyParamList(HttpServletRequest request, String id){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String)session.getAttribute(Constant.SESSION_USERID_LONG));
			String language= String.valueOf(RequestContextUtils.getLocale(request));
			return manageParamService.queryCompanyParamList(user.getCompanyId(),language);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
