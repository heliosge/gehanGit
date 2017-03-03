/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyAskAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年12月7日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ask.AskAnswerBean;
import com.jftt.wifi.bean.ask.AskDetailBean;
import com.jftt.wifi.bean.ask.AskTypeBean;
import com.jftt.wifi.bean.ask.AskVoForMMGrid;
import com.jftt.wifi.bean.ask.SearchOptionBean;
import com.jftt.wifi.bean.thematicAsk.ThematicAskVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyAskService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:MyAskAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年12月7日
 * @author JFTT)ShenHaijun
 */
@Controller
@RequestMapping("myAsk")
public class MyAskAction {
	private Logger logger = Logger.getLogger(MyAskAction.class);
	
	@Resource(name="myAskService")
	private MyAskService myAskService;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	/**
	 * Method name: toMyAsk <BR>
	 * Description: 跳转到我的问问页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyAsk")
	public String toMyAsk(HttpServletRequest request){
		return "ask/myAsk";
	}
	
	/**
	 * Method name: getAskCountByAskerId <BR>
	 * Description: 获取提问数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askerId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("getAskCountByAskerId")
	@ResponseBody
	public Object getAskCountByAskerId(HttpServletRequest request,Integer askerId){
		try {
			logger.info("开始查询用户"+askerId+"提问的数量");
			Integer count = myAskService.getAskCountByAskerId(askerId);
			logger.info("查询完毕用户"+askerId+"提问的数量，数量为"+count);
			return count;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getAnswerCountByReplyerId <BR>
	 * Description: 根据回答人id查询回答数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param replyerId 回答人id
	 * @return  Object<BR>
	 */
	@RequestMapping("getAnswerCountByReplyerId")
	@ResponseBody
	public Object getAnswerCountByReplyerId(HttpServletRequest request,Integer replyerId){
		try {
			logger.info("开始查询用户"+replyerId+"回答的数量");
			Integer count = myAskService.getAnswerCountByReplyerId(replyerId);
			logger.info("查询完毕用户"+replyerId+"回答的数量，数量为"+count);
			return count;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getRootTypes <BR>
	 * Description: 查询根问题分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption 查询选项
	 * @return  Object<BR>
	 */
	@RequestMapping("getRootTypes")
	@ResponseBody
	public Object getRootTypes(HttpServletRequest request,SearchOptionBean searchOption){
		try {
			logger.info("开始查询根问题分类");
			//session中的人员信息
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			searchOption.setCompanyId(userBean.getCompanyId());
			searchOption.setSubCompanyId(userBean.getSubCompanyId());
			List<Map<String, String>> rootTypes = myAskService.getRootTypes(searchOption);
			logger.info("查询完毕根问题分类");
			return rootTypes;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getRootTypesUnCount <BR>
	 * Description: 获取分类树，不带数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption 查询选项
	 * @return  Object<BR>
	 */
	@RequestMapping("getRootTypesUnCount")
	@ResponseBody
	public Object getRootTypesUnCount(HttpServletRequest request,SearchOptionBean searchOption){
		try {
			logger.info("开始查询根问题分类");
			//session中的人员信息
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			searchOption.setCompanyId(userBean.getCompanyId());
			searchOption.setSubCompanyId(userBean.getSubCompanyId());
			List<AskTypeBean> rootTypes = myAskService.getRootTypesUnCount(searchOption);
			logger.info("查询完毕根问题分类");
			return rootTypes;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getToDealAskCount <BR>
	 * Description: 获取待处理问题数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getToDealAskCount")
	@ResponseBody
	public Object getToDealAskCount(HttpServletRequest request,String companyId,String subCompanyId){
		try {
			logger.info("开始查询待处理问题数量");
			Integer count = myAskService.getToDealAskCount(companyId,subCompanyId);
			logger.info("查询完毕待处理问题数量，数量为"+count);
			return count;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getDealedAskCount <BR>
	 * Description: 获取已处理问题数量 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getDealedAskCount")
	@ResponseBody
	public Object getDealedAskCount(HttpServletRequest request,String companyId,String subCompanyId){
		try {
			logger.info("开始查询已处理问题数量");
			Integer count = myAskService.getDealedAskCount(companyId,subCompanyId);
			logger.info("查询完毕已处理问题数量，数量为"+count);
			return count;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getThematicAskList <BR>
	 * Description: 获取专题问答 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("getThematicAskList")
	@ResponseBody
	public Object getThematicAskList(HttpServletRequest request){
		try {
			logger.info("开始查询问问首页专题回答");
			List<ThematicAskVo> thematicAsks = myAskService.getThematicAskList();
			logger.info("查询完毕问问首页专题回答");
			return thematicAsks;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getAsksForMMGrid <BR>
	 * Description: 获取问问列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askShowType 问问显示类型
	 * @param userId 用户ud
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getAsksForMMGrid")
	@ResponseBody
	public Object getAsksForMMGrid(HttpServletRequest request,String askShowType,String userId,String page,String pageSize,
			String companyId,String subCompanyId){
		MMGridPageVoBean<AskVoForMMGrid> mmGridVo = new MMGridPageVoBean<AskVoForMMGrid>();
		try {
			logger.info("开始查询前台问问数量");
			Integer count = myAskService.getAsksCount(askShowType,userId,companyId,subCompanyId);
			logger.info("结束查询前台问问数量，数量为"+count);
			Integer fromNum = (int)CommonUtil.getFromNum(pageSize, page, count);
			logger.info("开始查询前台问问列表");
			List<AskVoForMMGrid> askList = myAskService.getAskList(askShowType,userId,fromNum,pageSize,companyId,subCompanyId);
			logger.info("结束查询前台问问列表");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(askList);
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: toAskTypeDetail <BR>
	 * Description: 跳转到分类详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param typeId 显示类型
	 * @return  String<BR>
	 */
	@RequestMapping("toAskTypeDetail")
	public String toAskTypeDetail(HttpServletRequest request,Integer typeId){
		request.setAttribute("typeId", typeId);
		return "ask/askTypeDetail";
	}
	
	/**
	 * Method name: getTypeById <BR>
	 * Description: 根据id获取分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param typeId 显示类型
	 * @return  Object<BR>
	 */
	@RequestMapping("getTypeById")
	@ResponseBody
	public Object getTypeById(HttpServletRequest request,Integer typeId){
	 	try {
			logger.info("开始根据id"+typeId+"查询分类");
			AskTypeBean type = myAskService.getTypeById(typeId);
			logger.info("完成根据id"+typeId+"查询分类");
			return type;
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return null;
	}
	
	/**
	 * Method name: getTypeAsksForMMGrid <BR>
	 * Description: 获取该分类下的问问列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param typeId 显示类型
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping("getTypeAsksForMMGrid")
	@ResponseBody
	public Object getTypeAsksForMMGrid(HttpServletRequest request,Integer typeId,String searchContent,
			String sortName,String sortOrder,String page,String pageSize){
		MMGridPageVoBean<AskVoForMMGrid> mmGridVo = new MMGridPageVoBean<AskVoForMMGrid>();
		try {
			logger.info("开始查询分类"+typeId+"问问数量");
			Integer count = myAskService.getTypeAskCount(typeId,searchContent);
			logger.info("结束查询分类"+typeId+"问问数量，数量为"+count);
			Integer fromNum = (int)CommonUtil.getFromNum(pageSize, page, count);
			logger.info("开始查询分类"+typeId+"问问列表");
			List<AskVoForMMGrid> askList = myAskService.getTypeAskList(typeId,searchContent,sortName,sortOrder,fromNum,pageSize);
			logger.info("结束查询分类"+typeId+"问问列表");
			mmGridVo.setTotal(count);
			mmGridVo.setRows(askList);
		} catch (Exception e) {
			logger.warn(MyAskAction.class, e);
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: toAskDetail <BR>
	 * Description: 跳转到问问详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  String<BR>
	 */
	@RequestMapping("toAskDetail")
	public String toAskDetail(HttpServletRequest request,Integer askId){
		logger.info("toAskDetail");
		request.setAttribute("askId", askId);
		return "ask/askDetail";
	}
	
	/**
	 * Method name: getAskDetail <BR>
	 * Description: 获取问问详情-基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("getAskDetail")
	@ResponseBody
	public Object getAskDetail(HttpServletRequest request,Integer askId){
		logger.info("getAskDetail");
		AskDetailBean bean = new AskDetailBean();
		try {
			bean = myAskService.getAskDetail(askId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * Method name: getAnswerList <BR>
	 * Description: 获取问问回答列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption 查询条件
	 * @return  Object<BR>
	 */
	@RequestMapping("getAnswerList")
	@ResponseBody
	public Object getAnswerList(HttpServletRequest request,SearchOptionBean searchOption){
		logger.info("getAnswerList");
		List<AskAnswerBean> list = new ArrayList<AskAnswerBean>();
		try {
			list = myAskService.getAnswerList(searchOption);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Method name: getAnswerListIncludeShield <BR>
	 * Description: 获取问问回答列表 （包含已屏蔽） <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption 查询条件
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getAnswerListIncludeShield")
	@ResponseBody
	public Object getAnswerListIncludeShield(HttpServletRequest request,SearchOptionBean searchOption){
		logger.info("getAnswerList");
		List<AskAnswerBean> list = new ArrayList<AskAnswerBean>();
		try {
			list = myAskService.getAnswerListIncludeShield(searchOption);
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Method name: addAnswer <BR>
	 * Description: 插入回答内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean 回答Bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "addAnswer", consumes = "application/json")
	@ResponseBody
	public Object addAnswer(HttpServletRequest request,@RequestBody AskAnswerBean bean){
		logger.info("addAnswer");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			bean.setCompanyId(userBean.getCompanyId());
			bean.setSubCompanyId(userBean.getSubCompanyId());
			if(userBean.getCompanyId() == 1){
				bean.setReplyerType(1);
			}else{
				bean.setReplyerType(2);
			}
			bean.setReplyerId(Integer.parseInt(userBean.getId()));
			bean.setReplyerName(userBean.getName());
			myAskService.add(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toMyQuestion <BR>
	 * Description: 跳转至我的提问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param showType 显示类型
	 * @return  String<BR>
	 */
	@RequestMapping("toMyQuestion")
	public String toMyQuestion(HttpServletRequest request,String showType){
		logger.info("toMyQuestion");
		request.setAttribute("showType", showType);
		return "ask/my_question";
	}
	
	/**
	 * Method name: toAsk <BR>
	 * Description: 跳转至我要提问页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toAsk")
	public String toAsk(HttpServletRequest request){
		logger.info("toAsk");
		return "ask/ask";
	}
	
	/**
	 * Method name: addAskDetail <BR>
	 * Description: 添加问题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean 问题详情Bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "addAskDetail", consumes = "application/json")
	@ResponseBody
	public Object addAskDetail(HttpServletRequest request,@RequestBody AskDetailBean bean){
		logger.info("addAskDetail");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			bean.setCompanyId(userBean.getCompanyId());
			bean.setSubCompanyId(userBean.getSubCompanyId());
			bean.setAskerId(Integer.parseInt(userBean.getId()));
			bean.setAskerName(userBean.getName());
			myAskService.addAskDetail(bean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: getMyAskList <BR>
	 * Description: 获取我的提问列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption 查询条件
	 * @param type 显示类型
	 * @return  Object<BR>
	 */
	@RequestMapping("getMyAskList")
	@ResponseBody
	public Object getMyAskList(HttpServletRequest request,SearchOptionBean searchOption,String type){
		logger.info("getMyAskList");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			searchOption.setUserId(Integer.parseInt(userBean.getId()));
			searchOption.setCompanyId(userBean.getCompanyId());
			searchOption.setSubCompanyId(userBean.getSubCompanyId());
			if("1".equals(type)){
				//我的提问
				searchOption.setAskerId(Integer.parseInt(userBean.getId()));
			}else if("2".equals(type)){
				//我的回答
				searchOption.setReplyerId(Integer.parseInt(userBean.getId()));
			}
			List<AskDetailBean> list = myAskService.getMyAskList(searchOption);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getRecommendedToMeQuestions <BR>
	 * Description: 获取推荐给我的问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption 查询条件
	 * @param toWhom 推荐给谁
	 * @return  Object<BR>
	 */
	@RequestMapping("getRecommendedToMeQuestions")
	@ResponseBody
	public Object getRecommendedToMeQuestions(HttpServletRequest request,SearchOptionBean searchOption,Integer toWhom){
		logger.info("getRecommendedToMeQuestions");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			searchOption.setCompanyId(userBean.getCompanyId());
			searchOption.setSubCompanyId(userBean.getSubCompanyId());
			searchOption.setToWhom(toWhom);
			List<AskDetailBean> list = myAskService.getMyAskList(searchOption);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: delAsk <BR>
	 * Description: 删除提问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id 提问id
	 * @return  Object<BR>
	 */
	@RequestMapping("delAsk")
	@ResponseBody
	public Object delAsk(HttpServletRequest request,Integer id){
		logger.info("delAsk");
		try {
			myAskService.deleteAsk(id);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: toSearchAsks <BR>
	 * Description: 跳转到搜索问题页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchInput 搜索内容
	 * @return  String<BR>
	 */
	@RequestMapping(value="toSearchAsks")
	public String toSearchAsks(HttpServletRequest request, String searchInput){
		logger.info("toSearchAsks");
		try {
			searchInput = URLDecoder.decode(searchInput);
			searchInput = new String(searchInput.getBytes(),"utf-8");
			request.setAttribute("searchInput", searchInput);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "ask/searchAsks";
	}
	
	/**
	 * Method name: getAsksForSearch <BR>
	 * Description: 根据搜索条件查询问问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchContent 查询内容
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param page 当前页
	 * @param pageSize 每页大小
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getAsksForSearch")
	@ResponseBody
	public Object getAsksForSearch(HttpServletRequest request, String searchContent, String sortName,
			String sortOrder, String page, String pageSize){
		logger.info("getAsksForSearch");
		MMGridPageVoBean<AskVoForMMGrid> mmGridVo = new MMGridPageVoBean<AskVoForMMGrid>();
		try {
			Integer count = myAskService.getAskCountForSearch(searchContent);
			Integer fromNum = (int)CommonUtil.getFromNum(pageSize, page, count);
			List<AskVoForMMGrid> askList = myAskService.getAskListForSearch(searchContent,sortName,sortOrder,fromNum,pageSize);
			mmGridVo.setTotal(count);
			mmGridVo.setRows(askList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mmGridVo;
	}
	
	/**
	 * Method name: getAsksByTitle <BR>
	 * Description: 根据标题查询问问 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param title 标题
	 * @return  Object<BR>
	 */
	@RequestMapping("getAsksByTitle")
	@ResponseBody
	public Object getAsksByTitle(HttpServletRequest request, String title){
		logger.info("getAsksByTitle");
		try {
			List<AskDetailBean> asks = myAskService.getAsksByTitle(title);
			return asks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getUserByName <BR>
	 * Description: 根据姓名查询用户 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param toWhomName 姓名
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getUserByName")
	@ResponseBody
	public Object getUserByName(HttpServletRequest request, String toWhomName, Integer companyId, Integer subCompanyId){
		logger.info("getUserByName");
		try {
			List<ManageUserBean> userList = manageUserService.findUserByAccurateName(toWhomName, companyId, subCompanyId);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getManageThematicAskRoles <BR>
	 * Description: 获取专题问答管理员用户id列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param limitsUrl 专题问答管理URL
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getManageThematicAskRoles")
	@ResponseBody
	public Object getManageThematicAskRoles(HttpServletRequest request, String limitsUrl, 
			String companyId, String subCompanyId){
		logger.info("getManageThematicAskRoles");
		try {
			List<Integer> userIds = myAskService.getManageThematicAskRoles(limitsUrl,companyId,subCompanyId);
			return userIds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: toThematicAskDetail <BR>
	 * Description: 跳转到专题问答详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  String<BR>
	 */
	@RequestMapping("toThematicAskDetail")
	public String toThematicAskDetail(HttpServletRequest request, String askId){
		request.setAttribute("askId", askId);
		return "thematicAsk/thematicAskDetail";
	}
	
	/**
	 * Method name: getSatisfactAnswerForThematicAsk <BR>
	 * Description: 获取专题问答的满意答案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("getSatisfactAnswerForThematicAsk")
	@ResponseBody
	public Object getSatisfactAnswerForThematicAsk(HttpServletRequest request, String askId){
		logger.warn("----getSatisfactAnswerForThematicAsk----");
		try {
			AskAnswerBean satisfactAnswer = myAskService.getSatisfactAnswerForThematicAsk(askId);
			return satisfactAnswer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getOtherAnswers <BR>
	 * Description: 获取满意答案之外的其他回答列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param askId 问问id
	 * @return  Object<BR>
	 */
	@RequestMapping("getOtherAnswers")
	@ResponseBody
	public Object getOtherAnswers(HttpServletRequest request, String askId){
		logger.warn("----getOtherAnswers----");
		try {
			List<AskAnswerBean> otherAnswers = myAskService.getOtherAnswers(askId);
			return otherAnswers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method name: getSearchTypes <BR>
	 * Description: 获取该分类下的所有直接子分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param typeId 分类id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return  Object<BR>
	 */
	@RequestMapping("getSearchTypes")
	@ResponseBody
	public Object getSearchTypes(HttpServletRequest request, String typeId, String companyId, String subCompanyId){
		try {
			logger.info("开始查询分类id"+typeId+"下的所有直接子分类");
			List<AskTypeBean> types = myAskService.getSearchTypes(typeId,companyId,subCompanyId);
			logger.info("结束查询分类id"+typeId+"下的所有直接子分类");
			return types;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
