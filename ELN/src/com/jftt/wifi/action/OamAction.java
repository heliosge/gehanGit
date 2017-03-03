/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: OamAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月3日        | JFTT)luyunlong    | original version
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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.OamBarBean;
import com.jftt.wifi.bean.OamTopicBean;
import com.jftt.wifi.bean.OamTopicCategoryBean;
import com.jftt.wifi.bean.vo.ManageGroupVo;
import com.jftt.wifi.bean.vo.OamTopicVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.OamService;
import com.jftt.wifi.service.ResService;
import com.jftt.wifi.util.JsonUtil;

/**
 * class name:OamAction <BR>
 * class description: 运维action <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月3日
 * @author JFTT)Lu Yunlong
 */
@Controller
@RequestMapping(value="oam")
public class OamAction {
	
	@Resource(name="oamService")
	private OamService oamService;
	
	@Resource(name="resService")
	private ResService resService;
	
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@RequestMapping(value="toOamTopicListPage")
	public String toOamTopicListPage(HttpServletRequest request){
		return "oam/topicList";
	}

	/**
	 * Method name: selectOamTopicList <BR>
	 * Description: 查询专题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo 
	 * @return  Object<BR>
	 */
	@RequestMapping("selectOamTopicList")
	@ResponseBody
	public Object selectOamTopicList(HttpServletRequest request, OamTopicVo vo){
		MMGridPageVoBean<OamTopicBean> re = new MMGridPageVoBean<OamTopicBean>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			vo.setCategoryIds(request.getParameterValues("categoryIdList[]"));
			vo.setCompanyId(user.getCompanyId());
			vo.setSubCompanyId(user.getSubCompanyId());
			vo.setFromNum((vo.getPage()-1)*vo.getPageSize());
			List<OamTopicBean> result = oamService.selectOamTopicList(vo);
			int size = oamService.selectOamTopicCount(vo);
			re.setRows(result);
			re.setTotal(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * Method name: toInsertOamTopicPage <BR>
	 * Description: 新增专题页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toInsertOamTopicPage")
	public String toInsertOamTopicPage(HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			if(Constant.PULIAN_COMPANY_ID - user.getCompanyId() == 0){
				return "oam/pulianTopicAdd";
			}else{
				return "oam/topicAdd";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Method name: inserOamTopic <BR>
	 * Description: 新增专题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean 专题bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="insertOamTopic")
	@ResponseBody
	public String insetrOamTopic(HttpServletRequest request, OamTopicBean bean, OamBarBean bar){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			bean.setCompanyId(user.getCompanyId());
			bean.setSubCompanyId(user.getSubCompanyId());
			bean.setCreateUserId(Integer.parseInt(user.getId()));
			String[] courseIds = request.getParameterValues("courseIds[]");
			String[] industryIds = request.getParameterValues("industryIds[]");
			//判断共享状态
			if(Constant.PULIAN_COMPANY_ID - user.getCompanyId() == 0){
				bean.setShareStatus(7);
			}else{
				if(user.getCompanyId().equals(user.getSubCompanyId())){
					bean.setShareStatus(4);
				}else{
					bean.setShareStatus(1);
				}
			}
			//新增专题基本属性
			Integer topicId = oamService.insertTopic(bean);
			//新增专题和课程关系
			if(courseIds != null){
				for(String courseId : courseIds){
					oamService.insertTopicAndCourse(topicId,courseId);
				}
			}
			//新增专题和推广企业的关系
			if(industryIds != null){
				for(String industryId : industryIds){
					oamService.insertTopicAndIndustry(topicId,industryId);
				}
			}
			if(bar.getOrder() != null && !"".equals(bar.getOrder())){
				bar.setCompanyId(user.getCompanyId());
				bar.setSubCompanyId(user.getSubCompanyId());
				bar.setSpreadId(topicId);
				bar.setType(1);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("companyId", bean.getCompanyId());
				//获取运维风格
				List<OamBarBean> list = oamService.selectOamBar(param);
				if(list != null && list.size() > 0 ){
					bar.setStyle(list.get(0).getStyle());
				}else{
					bar.setStyle(1);
				}
				//删除原来推广栏位置
				oamService.deleteOamBarSpread(bar);
				//新建该专题推广位子
				oamService.insertOamBarSpread(bar);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	
	
	/**
	 * Method name: updateOamTopic <BR>
	 * Description: 跳转到修改专题页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean 专题bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateOamTopicPage")
	public String toUpdateOamTopicPage(HttpServletRequest request, String id){
		try {
			OamTopicBean bean = oamService.selectTopicById(id);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("spreadId", bean.getId());
			param.put("type", 1);
			param.put("companyId", bean.getCompanyId());
			param.put("subCompanyId", bean.getSubCompanyId());
			List<OamBarBean> barList = oamService.selectOamBar(param);
			if(barList != null && barList.size() > 0){
				request.setAttribute("bar", JsonUtil.getJson4JavaObject(barList.get(0)));
			}else{
				request.setAttribute("bar", "{}");
			}
			request.setAttribute("topic", JsonUtil.getJson4JavaObject(bean));
			
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			if(Constant.PULIAN_COMPANY_ID - user.getCompanyId() == 0){
				return "oam/pulianTopicUpdate";
			}else{
				return "oam/topicUpdate";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "oam/topicUpdate";
	}
	
	/**
	 * Method name: toOamTopicDetailPage <BR>
	 * Description: 专题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOamTopicDetail")
	public String toOamTopicDetail(HttpServletRequest request, String id){
		try {
			OamTopicBean bean = oamService.selectTopicById(id);
			request.setAttribute("topic", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "oam/topicDetail";
	}
	
	/**
	 * Method name: toOamTopicDetailPage <BR>
	 * Description: 专题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOamTopicDetailPage")
	public String toOamTopicDetailPage(HttpServletRequest request, String id){
		try {
			OamTopicBean bean = oamService.selectTopicById(id);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("spreadId", bean.getId());
			param.put("companyId", bean.getCompanyId());
			param.put("subCompanyId", bean.getSubCompanyId());
			List<OamBarBean> barList = oamService.selectOamBar(param);
			if(barList != null && barList.size() > 0){
				request.setAttribute("bar", JsonUtil.getJson4JavaObject(barList.get(0)));
			}else{
				request.setAttribute("bar", "{}");
			}
			request.setAttribute("topic", JsonUtil.getJson4JavaObject(bean));
			
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			if(Constant.PULIAN_COMPANY_ID - user.getCompanyId() == 0){
				return "oam/pulianTopicDetail";
			}else{
				return "oam/topicDetailOld";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "oam/topicDetailOld";
	}
	
	
	/**
	 * Method name: updateOamTopic <BR>
	 * Description: 修改专题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean 专题bean
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateOamTopic")
	@ResponseBody
	public String updateOamTopic(HttpServletRequest request, OamTopicBean bean, Integer barId, Integer order){
		try {
			String[] courseIds = request.getParameterValues("courseIds[]");
			String[] industryIds = request.getParameterValues("industryIds[]");
			//删除专题和课程、企业关系
			oamService.deleteTopicAndCourseAndIndustry(bean);
			//新增专题基本属性
			oamService.updateTopic(bean);
			//新增专题和课程关系
			if(courseIds != null){
				for(String courseId : courseIds){
					oamService.insertTopicAndCourse(bean.getId(),courseId);
				}
			}
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			if(user.getCompanyId()-user.getSubCompanyId() == 0){
				//新增专题和推广企业的关系
				if(industryIds != null){
					for(String industryId : industryIds){
						oamService.insertTopicAndIndustry(bean.getId(),industryId);
					}
				}
			}
			if(order != null && !"".equals(order)){
				//删除该专题的原推荐
				oamService.deleteOamBarSpreadById(barId);
				//修改该专题的推广位置
				OamBarBean bar = new OamBarBean();
				bar.setCompanyId(user.getCompanyId());
				bar.setSubCompanyId(user.getSubCompanyId());
				bar.setSpreadId(bean.getId());
				bar.setOrder(order);
				bar.setType(1);
				//获取运维风格
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("companyId", user.getCompanyId());
				List<OamBarBean> list = oamService.selectOamBar(param);
				if(list != null && list.size() > 0 ){
					bar.setStyle(list.get(0).getStyle());
				}else{
					bar.setStyle(1);
				}
				oamService.insertOamBarSpread(bar);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: deleteOamTopic <BR>
	 * Description: deleteOamTopic <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 专题id
	 * @return  String<BR>
	 */
	@RequestMapping(value="deleteOamTopic")
	@ResponseBody
	public String deleteOamTopic(HttpServletRequest request){
		try {
			String[] ids = request.getParameterValues("ids[]");
			for(String id : ids){
				oamService.deleteOamTopic(id);
			}
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: spreadOamTopic <BR>
	 * Description: 推广专题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 专题id
	 * @return  String<BR>
	 */
	@RequestMapping(value="spreadOamTopic")
	@ResponseBody
	public String spreadOamTopic(HttpServletRequest request, String id){
		try {
			oamService.spreadOamTopic(id);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: cancelSpreadOamTopic <BR>
	 * Description: 取消推广 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 专题id
	 * @return  String<BR>
	 */
	@RequestMapping(value="cancelSpreadOamTopic")
	@ResponseBody
	public String cancelSpreadOamTopic(HttpServletRequest request, String id){
		try {
			oamService.cancelSpreadOamTopic(id);
			//删除该专题的原推荐
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", 1);
			param.put("spreadId", id);
			param.put("companyId", user.getCompanyId());
			oamService.deleteOamBarSpreadByTopicId(param);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	@RequestMapping(value="toOamSpreadCourseListPage")
	public String toOamSpreadCourseListPage(HttpServletRequest request){
		return "oam/spreadCourseList";
	}
	
	
	/**
	 * Method name: spreadResCourse <BR>
	 * Description: 推广课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="spreadResCourse")
	@ResponseBody
	public String spreadResCourse(HttpServletRequest request, String id){
		try {
			resService.spreadResCourse(id);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: cancelSpreadResCourse <BR>
	 * Description: 取消推广课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="cancelSpreadResCourse")
	@ResponseBody
	public String cancelSpreadResCourse(HttpServletRequest request, String id){
		try {
			resService.cancelSpreadResCourse(id);
			//删除该课程的原推荐
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", 2);
			param.put("spreadId", id);
			param.put("companyId", user.getCompanyId());
			oamService.deleteOamBarSpreadByTopicId(param);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: spreadGame <BR>
	 * Description: 推广大赛 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="spreadGame")
	@ResponseBody
	public String spreadGame(HttpServletRequest request, String id){
		try {
			oamService.spreadGame(id);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: cancelSpreadGame <BR>
	 * Description: 取消推广大赛 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="cancelSpreadGame")
	@ResponseBody
	public String cancelSpreadGame(HttpServletRequest request, String id){
		try {
			oamService.cancelSpreadGame(id);
			//删除该专题的原推荐
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", 3);
			param.put("spreadId", id);
			param.put("companyId", user.getCompanyId());
			oamService.deleteOamBarSpreadByTopicId(param);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toOamSpreadGameListPage <BR>
	 * Description: 推广大赛页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOamSpreadGameListPage")
	public String toOamSpreadGameListPage(HttpServletRequest request){
		return "oam/spreadGameList";
	}
	
	/**
	 * Method name: selectOamBar <BR>
	 * Description: 查询运维栏 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectOamBar")
	@ResponseBody
	public Object selectOamBar(HttpServletRequest request){
		List<OamBarBean> list = new ArrayList<OamBarBean>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			list = oamService.selectOamBar(param);
			if(list != null && list.size() > 0){
				data.put("data", list);
				if(list != null && list.size() > 0){
					data.put("style", list.get(0).getStyle());
				}else{
					data.put("style", null);
				}
			}else{
				data.put("data", null);
				data.put("style", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * Method name: toUpdateSpread <BR>
	 * Description: 修改运维栏推广页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toUpdateSpread")
	public String toUpdateSpread(HttpServletRequest request){
		return "oam/updateChooseSpread";
	}
	
	
	/**
	 * Method name: updateOamBarSpread <BR>
	 * Description: 修改运维栏推广 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateOamBarSpread")
	@ResponseBody
	public String updateOamBarSpread(HttpServletRequest request,OamBarBean vo){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setSubCompanyId(user.getSubCompanyId());
			oamService.updateOamBarSpread(vo);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: updateBarSpreadOrder <BR>
	 * Description: 修改运维栏推广位置 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateBarSpreadOrder")
	@ResponseBody
	public String updateBarSpreadOrder(HttpServletRequest request,OamBarBean vo){
		try {
			oamService.updateBarSpreadOrder(vo);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: updateBarStyle <BR>
	 * Description: 修改运维风格 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateBarStyle")
	@ResponseBody
	public String updateBarStyle(HttpServletRequest request,OamBarBean vo){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			List<OamBarBean> list = oamService.selectOamBar(param);
			if(list == null || list.size() == 0){
				return "NO_SPREAD";
			}
			vo.setSubCompanyId(user.getSubCompanyId());
			oamService.updateBarStyle(vo);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: insertOamBarSpread <BR>
	 * Description: 新增运维栏推广 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  String<BR>
	 */
	@RequestMapping(value="insertOamBarSpread")
	@ResponseBody
	public String insertOamBarSpread(HttpServletRequest request,OamBarBean vo){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
			vo.setSubCompanyId(user.getSubCompanyId());
			vo.setCompanyId(user.getCompanyId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			//获取运维风格
			List<OamBarBean> list = oamService.selectOamBar(param);
			if(list != null && list.size() > 0 ){
				vo.setStyle(list.get(0).getStyle());
			}else{
				vo.setStyle(1);
			}
			//删除原先的运维推广
			oamService.deleteOamBarSpread(vo);
			//新增一个运维推广
			oamService.insertOamBarSpread(vo);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/**
	 * Method name: selectOamTopicCategory <BR>
	 * Description: 查询专题体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectOamTopicCategory")
	@ResponseBody
	public Object selectOamTopicCategory(HttpServletRequest request, String categoryId, String name, String parentId){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			Map<String ,Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			param.put("categoryId", categoryId);
			param.put("name", name);
			param.put("parentId", parentId);
			return oamService.selectOamTopicCategory(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: insertOamTopicCategory <BR>
	 * Description: 新增专题体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="insertOamTopicCategory")
	@ResponseBody
	public Object insertOamTopicCategory(HttpServletRequest request, OamTopicCategoryBean bean){
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			bean.setCompanyId( user.getCompanyId());
			bean.setSubCompanyId(user.getSubCompanyId());
			oamService.insertOamTopicCategory(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: deleteOamTopicCategory <BR>
	 * Description: 删除专题体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteOamTopicCategory")
	@ResponseBody
	public Object deleteOamTopicCategory(HttpServletRequest request, String id){
		try {
			oamService.deleteOamTopicCategoryById(Integer.parseInt(id));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateOamTopicCategory <BR>
	 * Description: 修改专题体系 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="updateOamTopicCategory")
	@ResponseBody
	public Object updateOamTopicCategory(HttpServletRequest request, OamTopicCategoryBean bean){
		try {
			oamService.updateOamTopicCategory(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: selectOamCourseByTopic <BR>
	 * Description: 获取专题课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param topicId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectOamCourseByTopic")
	@ResponseBody
	public Object selectOamCourseByTopic(HttpServletRequest request, String topicId){
		try {
			return oamService.selectOamCourseByTopic(topicId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: selectOamIndustryByTopic <BR>
	 * Description: 获取推广企业 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param topicId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectOamIndustryByTopic")
	@ResponseBody
	public Object selectOamIndustryByTopic(HttpServletRequest request, String topicId){
		try {
			return oamService.selectOamIndustryByTopic(topicId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: toOamBarSettingPage <BR>
	 * Description: 运维栏页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toOamBarSettingPage")
	public String toOamBarSettingPage(HttpServletRequest request){
		return "oam/barSetting";
	}
	
	
	/**
	 * Method name: toChooseSpread <BR>
	 * Description: 选择推广资源 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toChooseSpread")
	public String toChooseSpread(HttpServletRequest request){
		return "oam/chooseSpread";
	}
	
	
	/**
	 * Method name: toBarIndex <BR>
	 * Description: 首页 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="toBarIndex")
	public String toBarIndex(HttpServletRequest request){
		return "oam/barIndex";
	}
	
	
	/**
	 * Method name: selectChooseSpread <BR>
	 * Description: 获取推广资源 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectChooseSpread")
	@ResponseBody
	public Object selectChooseSpread(HttpServletRequest request, String name, int page, int pageSize){
		MMGridPageVoBean<Map> re = new MMGridPageVoBean<Map>();
		try {
			HttpSession session = request.getSession();
			ManageUserBean user = manageUserService.findUserById((String) session.getAttribute(Constant.SESSION_USERID_LONG));
			Map<String ,Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			param.put("name", name);
			param.put("fromNum", (page-1)*pageSize);
			param.put("pageSize", pageSize);
			List<Map> list = oamService.selectChooseSpread(param);
			int size = oamService.selectChooseSpreadCount(param);
			re.setRows(list);
			re.setTotal(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	
	
}
