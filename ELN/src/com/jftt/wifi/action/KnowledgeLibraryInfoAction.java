/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeLibraryAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.awt.Insets;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.jftt.elasticsearch.bean.BackArray;
import com.jftt.elasticsearch.bean.PageSort;
import com.jftt.elasticsearch.bean.TermQuery;
import com.jftt.elasticsearch.common.ElasticConstant.QueryType;
import com.jftt.elasticsearch.common.ElasticConstant.SortType;
import com.jftt.elasticsearch.util.ElastisearchUtil;
import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KlCollectDownloadBean;
import com.jftt.wifi.bean.KlEvaluateBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.KLCategoryVo;
import com.jftt.wifi.bean.vo.KlByCatParamsVo;
import com.jftt.wifi.bean.vo.KlContributorVo;
import com.jftt.wifi.bean.vo.KnowledgeOtherVo;
import com.jftt.wifi.bean.vo.SearchKlByNameVo;
import com.jftt.wifi.bean.vo.SearchKlVo;
import com.jftt.wifi.bean.vo.judgeColDownReturnVo;
import com.jftt.wifi.bean.vo.klCountsVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.common.IntegralConstant;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.KnowledgeLibraryInfoService;
import com.jftt.wifi.service.KnowledgeManageService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:KnowledgeLibraryAction <BR>
 * class description: 我的知识库 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015-7-15
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("knowledgeLibraryInfo")
public class KnowledgeLibraryInfoAction {
	@Resource(name = "knowledgeLibraryInfoService")
	private KnowledgeLibraryInfoService knowledgeLibraryInfoService;
	
	@Autowired
	private IntegralManageService integralManageService;
	@Autowired
	private KnowledgeManageService knowledgeManageService;
	@Autowired
	private ManageUserService manageUserService;
	private static Logger logger = Logger.getLogger(KnowledgeLibraryInfoAction.class);

	/** chenrui start */
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledge <BR>
	 * Description: 跳转知识库首页 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toKnowledge")
	public String toKnowledge(HttpServletRequest request){
		return "my_knowledge/knowledge";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toUpload <BR>
	 * Description: 跳转-上传知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toUpload")
	public String toUpload(HttpServletRequest request){
		return "my_knowledge/toUpload";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toCreate <BR>
	 * Description: 跳转-创建知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCreate")
	public String toCreate(HttpServletRequest request){
		return "my_knowledge/toCreate";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledgeFl <BR>
	 * Description: 跳转-按分类查看知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toKnowledgeFl")
	public String toKnowledgeFl(HttpServletRequest request,String categoryId,String isCloud){
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("isCloud", isCloud);
		return "my_knowledge/knowledge_fl";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledgeSearch <BR>
	 * Description: 跳转-知识搜索页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param extendName
	 * @param knowledgeName
	 * @return  String<BR>
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("toKnowledgeSearch")
	public String toKnowledgeSearch(HttpServletRequest request,String extendName,String knowledgeName) throws UnsupportedEncodingException{
			knowledgeName =URLDecoder.decode(knowledgeName);
			knowledgeName =  new String(knowledgeName.getBytes(),"utf-8");
			request.setAttribute("extendName", extendName);
			request.setAttribute("knowledgeName", knowledgeName);
		return "my_knowledge/knowledge_search";
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledgeLook <BR>
	 * Description: 跳转-查看上传知识页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping("toKnowledgeLookUp")
	public String toKnowledgeLookUp(HttpServletRequest request,String knowledgeId){
		
		try {
			String userId =(String) request.getSession().getAttribute("USER_ID_LONG");//当前用户
			int uid = Integer.parseInt(userId);
			int createUserId = knowledgeLibraryInfoService.getUploadingKnowledge(knowledgeId).getCreateUserId();//当前资源的创建者
			if(createUserId!=uid){//非自己操作自己资源
				//增加积分--浏览别人知识 7020
				integralManageService.handleIntegral(uid, IntegralConstant.Num_kl_toBrower);
			}
			//添加浏览记录表
			knowledgeLibraryInfoService.addBrowerInfo(userId,knowledgeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("knowledgeId", knowledgeId);
		return "my_knowledge/knowledge_look_up";
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledgeCenter <BR>
	 * Description: 跳转我的知识中心 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId
	 * @return  String<BR>
	 */
	@RequestMapping("toKnowledgeCenter")
	public String toKnowledgeCenter(HttpServletRequest request,ModelMap model){
		KLCategoryBean klCategoryBean= new KLCategoryBean();
		try {
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getSubCompanyId(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<KLCategoryBean>  listKL = 	knowledgeManageService.getKLCategoryTree(klCategoryBean);
		//拼接知识分类树的顶级目录 
		model.addAttribute("categoryList",JsonUtil.getJson4JavaList(listKL));
		return "my_knowledge/knowledge_center";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toKnowledgeOthers <BR>
	 * Description: 跳转页面-他人的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param othersId
	 * @return  String<BR>
	 */
	@RequestMapping("toKnowledgeOthers")
	public String toKnowledgeOthers(HttpServletRequest request,String othersId){
		request.setAttribute("othersId", othersId);
		return "my_knowledge/knwoledge_others";
	}
	
	/**
	 * 
	 * Method name: giveEvaluate <BR>
	 * Description: 知识评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("giveEvaluate")
	public Object giveEvaluate(HttpServletRequest request, KlEvaluateBean klEvaluateBean) {
		logger.debug("KnowledgeLibraryAction执行giveEvaluate方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			knowledgeLibraryInfoService.giveEvaluate(klEvaluateBean);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: judgeEvaluate <BR>
	 * Description: 判断当前资源是否已评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param knowledgeId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("judgeEvaluate")
	public int judgeEvaluate(HttpServletRequest request,String userId,String knowledgeId) {
		logger.debug("KnowledgeLibraryAction执行judgeEvaluate方法");
		int count=0;
		try {
			count = knowledgeLibraryInfoService.judgeEvaluate(userId,knowledgeId);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: judgeEvaluate <BR>
	 * Description: 判断当前资源的是否收藏、下载 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param knowledgeId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("judgeCollecDownload")
	public Object judgeCollecDownload(HttpServletRequest request,String userId,String knowledgeId) {
		logger.debug("KnowledgeLibraryAction执行judgeCollecDownload方法");
		judgeColDownReturnVo vo = new judgeColDownReturnVo();
		try {
			vo = knowledgeLibraryInfoService.judgeCollecDownload(userId,knowledgeId);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return vo;
	}
	
	/**
	 * 
	 * Method name: addCollectAndDownload <BR>
	 * Description: 收藏下载操作 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @param klCollectDownloadBean
	 * @return String<BR>
	 */
	@ResponseBody
	@RequestMapping("addCollectAndDownload")
	public Object addCollectAndDownload(HttpServletRequest request, KlCollectDownloadBean klCollectDownloadBean) {
		logger.debug("KnowledgeLibraryAction执行collectAndDownload方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			knowledgeLibraryInfoService.addCollectAndDownload(klCollectDownloadBean);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toDeleteMoreWithMy <BR>
	 * Description: 批量删除-我上传的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("toDeleteMoreWithMy")
	public Object toDeleteMoreWithMy(HttpServletRequest request) {
		logger.debug("KnowledgeLibraryAction执行toDeleteMoreWithMy方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			String[] ids = request.getParameterValues("ids[]");
			knowledgeLibraryInfoService.toDeleteMoreWithMy(ids);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toDelColDown <BR>
	 * Description: 批量删除-我收藏下载的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("toDelColDown")
	public Object toDelColDown(HttpServletRequest request) {
		logger.debug("KnowledgeLibraryAction执行toDelColDown方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			String[] ids = request.getParameterValues("ids[]");
			knowledgeLibraryInfoService.toDelColDown(ids);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doDelColDow <BR>
	 * Description: 单个删除 知识收藏下载的记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId
	 * @param userId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("doDelColDow")
	public Object doDelColDow(HttpServletRequest request,String id) {
		logger.debug("KnowledgeLibraryAction执行doDelColDow方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			knowledgeLibraryInfoService.doDelColDow(id);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * 
	 * Method name: getKnowledgeCategory <BR>
	 * Description: 获取知识分类基础信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getKnowledgeCategory")
	public Object getKnowledgeCategory(HttpServletRequest request,String userId,String isCloud) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeCategory方法");
		AjaxReturnVo<KLCategoryVo> arv = new AjaxReturnVo<KLCategoryVo>();
		try {
			List<KLCategoryVo> list = knowledgeLibraryInfoService.getKnowledgeCategory(userId,isCloud);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getCategoryInfoById <BR>
	 * Description: 根据知识类别id获取类别信息(附带父类别信息、下级子分类也会查出) <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCategoryInfoById")
	public Object getCategoryInfoById(HttpServletRequest request,String categoryId) {
		logger.debug("KnowledgeLibraryAction执行getCategoryInfoById方法");
		AjaxReturnVo<KLCategoryVo> arv = new AjaxReturnVo<KLCategoryVo>();
		try {
			KLCategoryVo vo = knowledgeLibraryInfoService.getCategoryInfoById(categoryId);
			arv.setRtnData(vo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * Method name: getKnowledgeContributor <BR>
	 * Description: 获取突出贡献者排行信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getKnowledgeContributor")
	public Object getKnowledgeContributor(HttpServletRequest request,String userId) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeContributor方法");
		AjaxReturnVo<KlContributorVo> arv = new AjaxReturnVo<KlContributorVo>();
		try {
			List<KlContributorVo> list = knowledgeLibraryInfoService.getKnowledgeContributor(userId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * Method name: getKnowledgeCount <BR>
	 * Description: 获取当前知识资源数量统计 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getKnowledgeCount")
	public Object getKnowledgeCount(HttpServletRequest request,String userId) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeCount方法");
		AjaxReturnVo<klCountsVo> arv = new AjaxReturnVo<klCountsVo>();
		try {
			List<klCountsVo> list = knowledgeLibraryInfoService.getKnowledgeCount(userId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}

	/**
	 * 
	 * Method name: getMyKnowledgeCount <BR>
	 * Description: 获取我贡献的知识数目 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMyKnowledgeCount")
	public Object getMyKnowledgeCount(HttpServletRequest request, String userId) {
		logger.debug("KnowledgeLibraryAction执行getMyKnowledgeCount方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			int count = knowledgeLibraryInfoService.getMyKnowledgeCount(userId);
			arv.setCount(count);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}

	/**
	 * 
	 * Method name: getDepartmentKnowledge <BR>
	 * Description: 获取部门知识 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @param userId
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getDepartmentKnowledge")
	public Object getDepartmentKnowledge(HttpServletRequest request, String userId) {
		logger.debug("KnowledgeLibraryAction执行getDepartmentKnowledge方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			List<KnowledgeOtherVo> list = knowledgeLibraryInfoService.getDepartmentKnowledge(userId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * 
	 * Method name: getRecommendKnowledge <BR>
	 * Description: 获取推荐知识 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getRecommendKnowledge")
	public Object getRecommendKnowledge(HttpServletRequest request,String userId) {
		logger.debug("KnowledgeLibraryAction执行getDepartmentKnowledge方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			List<KnowledgeOtherVo> list = knowledgeLibraryInfoService.getRecommendKnowledge(userId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}

	/**
	 * 
	 * Method name: getKnowledgeByCategory <BR>
	 * Description: 根据类别获取知识(默认按时间排序) <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getKnowledgeByCategory")
	public Object getKnowledgeByCategory(HttpServletRequest request,KlByCatParamsVo paramsVo) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeByCategory方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			int count = knowledgeLibraryInfoService.getKnowledgeByCategoryCount(paramsVo);
			String page = paramsVo.getPage();
			String pageSize = paramsVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramsVo.setFromNum(fromNum);
			List<KnowledgeOtherVo> vo = knowledgeLibraryInfoService.getKnowledgeByCategory(paramsVo);
			arv.setRtnDataList(vo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getKnowledgeByCategoryCount")
	public int getKnowledgeByCategoryCount(HttpServletRequest request,KlByCatParamsVo paramsVo) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeByCategoryCount方法");
		int count =0;
		try {
			count = knowledgeLibraryInfoService.getKnowledgeByCategoryCount(paramsVo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}

	/**
	 * 
	 * Method name: getKnowledgeByCategoryOrderByEvaluate <BR>
	 * Description: 根据类型获取知识并按评价排序 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @param categoryId
	 * @param orderType
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getKnowledgeOrderByEvaluate")
	public Object getKnowledgeOrderByEvaluate(HttpServletRequest request, KlByCatParamsVo paramsVo) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeOrderByEvaluate方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			int count = knowledgeLibraryInfoService.getKnowledgeOrderByEvaluateCount(paramsVo);
			String page = paramsVo.getPage();
			String pageSize = paramsVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramsVo.setFromNum(fromNum);
			List<KnowledgeOtherVo> map = knowledgeLibraryInfoService.getKnowledgeOrderByEvaluate(paramsVo);
			arv.setRtnDataList(map);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}

	@ResponseBody
	@RequestMapping("getKnowledgeOrderByEvaluateCount")
	public int getKnowledgeOrderByEvaluateCount(HttpServletRequest request, KlByCatParamsVo paramsVo) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeOrderByEvaluateCount方法");
		int count = 0;
		try {
			count = knowledgeLibraryInfoService.getKnowledgeOrderByEvaluateCount(paramsVo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * Method name: getKnowledgeOrderByHot <BR>
	 * Description: 根据类型获取知识并按热门排序 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @param categoryId
	 * @param orderType
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getKnowledgeOrderByHot")
	public Object getKnowledgeOrderByHot(HttpServletRequest request,KlByCatParamsVo paramsVo) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeOrderByHot方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			int count = knowledgeLibraryInfoService.getKnowledgeOrderByHotCount(paramsVo);
			String page = paramsVo.getPage();
			String pageSize = paramsVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramsVo.setFromNum(fromNum);
			List<KnowledgeOtherVo> map = knowledgeLibraryInfoService.getKnowledgeOrderByHot(paramsVo);
			arv.setRtnDataList(map);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getKnowledgeOrderByHotCount")
	public int getKnowledgeOrderByHotCount(HttpServletRequest request, KlByCatParamsVo paramsVo) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeOrderByHotCount方法");
		int count = 0;
		try {
			count = knowledgeLibraryInfoService.getKnowledgeOrderByHotCount(paramsVo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}

	/**
	 * 
	 * Method name: getUploadingKnowledge <BR>
	 * Description: 获取查看上传知识页面数据信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getUploadingKnowledge")
	public Object getUploadingKnowledge(HttpServletRequest request, String knowledgeId) {
		logger.debug("KnowledgeLibraryAction执行getUploadingKnowledge方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			KnowledgeOtherVo data = knowledgeLibraryInfoService.getUploadingKnowledge(knowledgeId);
			arv.setRtnData(data);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}

	/**
	 * 
	 * Method name: getEvaluates <BR>
	 * Description: 获取知识评价信息 <BR>
	 * Remark: <BR>
	 * 
	 * @param request
	 * @param knowledgeId
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getEvaluates")
	public Object getEvaluates(HttpServletRequest request, String knowledgeId) {
		logger.debug("KnowledgeLibraryAction执行getEvaluateContent方法");
		AjaxReturnVo<KlEvaluateBean> arv = new AjaxReturnVo<KlEvaluateBean>();
		try {
			List<KlEvaluateBean> list = knowledgeLibraryInfoService.getEvaluates(knowledgeId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * Method name: getRecommends <BR>
	 * Description: 获取相关推荐知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param knowledgeId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getRecommends")
	public Object getRecommends(HttpServletRequest request,String userId,String knowledgeId) {
		logger.debug("KnowledgeLibraryAction执行getRecommends方法");
		AjaxReturnVo<KnowledgeBean> arv = new AjaxReturnVo<KnowledgeBean>();
		try {
			List<KnowledgeBean> list = knowledgeLibraryInfoService.getRecommends(userId,knowledgeId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * Method name: getKnowledgeByName <BR>
	 * Description: 根据条件筛选知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("searchKnowledge")
	public Object searchKnowledge(HttpServletRequest request,String knowledgeName,String fileType) {
		logger.debug("KnowledgeLibraryAction执行searchKnowledge方法");
		AjaxReturnVo<KnowledgeBean> arv = new AjaxReturnVo<KnowledgeBean>();
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			List<KnowledgeBean> list = knowledgeLibraryInfoService.searchKnowledge(knowledgeName,fileType,userId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * Method name: getGuessLike <BR>
	 * Description: 获取查看上传知识->猜你喜欢的数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getGuessLike")
	public Object getGuessLike(HttpServletRequest request,String knowledgeId,String userId) {
		logger.debug("KnowledgeLibraryAction执行getGuessLike方法");
		AjaxReturnVo<KnowledgeBean> arv = new AjaxReturnVo<KnowledgeBean>();
		try {
			List<KnowledgeBean> list = knowledgeLibraryInfoService.getGuessLike(knowledgeId,userId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMyUploadKnowledge <BR>
	 * Description: 我的知识中心-获取我上传的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchKlVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMyUploadKnowledge")
	public Object getMyUploadKnowledge(HttpServletRequest request,SearchKlVo searchKlVo) {
		logger.debug("KnowledgeLibraryAction执行getMyUploadKnowledge方法");
		
		MMGridPageVoBean<KnowledgeOtherVo> mmVo = new MMGridPageVoBean<KnowledgeOtherVo>();
		try {
				int count = knowledgeLibraryInfoService.getMyUploadKnowledgeCount(searchKlVo);
				String pageSize = searchKlVo.getPageSize();
				String page = searchKlVo.getPage();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				searchKlVo.setFromNum(fromNum);
				List<KnowledgeOtherVo> list  = knowledgeLibraryInfoService.getMyUploadKnowledge(searchKlVo);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getOthersKnowledge <BR>
	 * Description: 他人的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchKlVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getOthersKnowledge")
	public Object getOthersKnowledge(HttpServletRequest request,SearchKlVo searchKlVo) {
		logger.debug("KnowledgeLibraryAction执行getOthersKnowledge方法");
		
		MMGridPageVoBean<KnowledgeOtherVo> mmVo = new MMGridPageVoBean<KnowledgeOtherVo>();
		try {
				int count = knowledgeLibraryInfoService.getOthersKnowledgeCount(searchKlVo);
				String pageSize = searchKlVo.getPageSize();
				String page = searchKlVo.getPage();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				searchKlVo.setFromNum(fromNum);
				List<KnowledgeOtherVo> list  = knowledgeLibraryInfoService.getOthersKnowledge(searchKlVo);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * 
	 * Method name: getMyKnowledgeForCollectDownload <BR>
	 * Description: 我的知识中心-获取我收藏下载的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMyKnowledgeForCollectDownload")
	public Object getMyKnowledgeForCollectDownload(HttpServletRequest request,SearchKlVo searchKlVo) {
		logger.debug("KnowledgeLibraryAction执行getMyKnowledgeForCollectDownload方法");
		MMGridPageVoBean<KnowledgeOtherVo> mmVo = new MMGridPageVoBean<KnowledgeOtherVo>();
		try {
				int count = knowledgeLibraryInfoService.getMyKnowledgeForCollectDownloadCount(searchKlVo);
				String pageSize = searchKlVo.getPageSize();
				String page = searchKlVo.getPage();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				searchKlVo.setFromNum(fromNum);
				List<KnowledgeOtherVo> list  = knowledgeLibraryInfoService.getMyKnowledgeForCollectDownload(searchKlVo);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * Method name: delKlById <BR>
	 * Description: 根据id删除对应知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("delKlById")
	public void delKlById(HttpServletRequest request,String knowledgeId){
		logger.debug("KnowledgeLibraryAction执行delKlById方法");
		try {
			
			knowledgeLibraryInfoService.delKlById(knowledgeId);
			
			// 全文检索库删除
			ElastisearchUtil.dataDelete(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", knowledgeId);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
	}
	/**
	 * 
	 * Method name: toPublic <BR>
	 * Description: 公开知识操作 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("toPublic")
	public void toPublic(HttpServletRequest request,String knowledgeId){
		logger.debug("KnowledgeLibraryAction执行toPublic方法");
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		try {
			knowledgeLibraryInfoService.toPublic(knowledgeId,userId);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
	}
	/**
	 * 
	 * Method name: updateKl <BR>
	 * Description: 修改知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeBean  void<BR>
	 */
	@ResponseBody
	@RequestMapping("updateKl")
	public void updateKl(HttpServletRequest request,KnowledgeBean knowledgeBean){
		logger.debug("KnowledgeLibraryAction执行updateKl方法");
		try {
			knowledgeLibraryInfoService.updateKl(knowledgeBean);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
	}
	/**
	 * 全文检索过滤  
	 * Method name: getFullSearchIds <BR>
	 * Description: getFullSearchIds <BR>
	 * Remark: <BR>
	 * @return String  返回过滤后的资源ids<BR>
	 */
	/*private String getFullSearchIds(SearchKlByNameVo paramVo){
		String page = paramVo.getPage();
		String pageSize = paramVo.getPageSize();
		Integer n_page = Integer.parseInt(page);
		Integer n_pageSize = Integer.parseInt(pageSize);
		Integer fromNum = n_pageSize * (n_page - 1);
		String result = "";
		
		String searchName = paramVo.getKnowledgeName();
		List<TermQuery> termQueryList = new ArrayList<TermQuery>();
		
    	TermQuery query = new TermQuery(QueryType.must.getValue(), null, searchName, 1F);
    	
    	termQueryList.add(query);
    	String sortType = paramVo.getOrderType();
    	PageSort pageSort = new PageSort(fromNum, 10, "createTime.time", sortType);
    	BackArray backArray = ElastisearchUtil.search(Constant.ELASTICSEARCH_INDEX_knowledge,"kl_knowledge", null, termQueryList, pageSort);
    	JSONArray jArr = backArray.getList();
    	Object[] objs = jArr.toArray();
    	
    	for(int i=0;i<objs.length;i++){
    		Map<String, Object> map = new HashMap<String, Object>();
    		map = (Map<String, Object>) objs[i];
    		String id = map.get("knowledgeId") + "";
    		result += id + ",";
    	}
    	return result.substring(0,result.length()-1);
	}*/
	/**
	 * 全文检索查询
	 * Method name: getFullSearchKnowledge <BR>
	 * Description: getFullSearchKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo  void<BR>
	 */
	private String returnSearchIds(String searchKey){
		List<Object> list = ElastisearchUtil.searchId(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge",searchKey, "file_content,knowledgeName,knowledgeText","knowledgeId");
		if(list.size() == 0){
			return null;
		}
		String idsStr = "";
		if(list!=null && list.size()>0){
			for(Object tmp : list){
				String id = tmp.toString();
				idsStr += id + ",";
			}
			idsStr = idsStr.substring(0,idsStr.length()-1);
		}
		
		return idsStr;
	}
	
	/*@ResponseBody
	@RequestMapping("getFullSearchKnowledge")
	public Object getFullSearchKnowledge(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getFullSearchKnowledge方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			List<KnowledgeOtherVo> list = knowledgeLibraryInfoService.getFullSearchKnowledge(paramVo);
			arv.setRtnDataList(list);
	    	return arv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@ResponseBody
	@RequestMapping("getFullSearchKnowledgeCount")
	public int getFullSearchKnowledgeCount(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getFullSearchKnowledge方法");
		
		try {
			return knowledgeLibraryInfoService.getFullSearchKnowledgeCount(paramVo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}*/
	
	/**
	 * 
	 * Method name: getKnowledgeByCategory <BR>
	 * Description: 获取搜索的知识（默认按时间排序） <BR>
	 * Remark:  <BR>
	 * 
	 * @param request
	 * @param categoryId
	 * @return Object<BR>
	 */ 
	@ResponseBody
	@RequestMapping("getSearchKnowledge")
	public Object getSearchKnowledge(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getSearchKnowledge方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			String searchKey = paramVo.getKnowledgeName();
			String ids = "";
			if(searchKey!=null && !searchKey.isEmpty()){
				ids = returnSearchIds(searchKey);
				if(ids ==null){// 未搜索到结果
					return arv;
				}
				paramVo.setIdsStr(ids);
			}
			int count =  knowledgeLibraryInfoService.getSearchKnowledgeCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			List<KnowledgeOtherVo> vo = knowledgeLibraryInfoService.getSearchKnowledge(paramVo);
			arv.setRtnDataList(vo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getSearchKnowledgeCount")
	public int getSearchKnowledgeCount(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getSearchKnowledgeCount方法");
		String searchKey = paramVo.getKnowledgeName();
		String ids = "";
		if(searchKey!=null && !searchKey.isEmpty()){
			ids = returnSearchIds(searchKey);
			if(ids ==null){// 未搜索到结果
				return 0;
			}
			paramVo.setIdsStr(ids);
			
		}
		int count = 0;
		try {
			count = knowledgeLibraryInfoService.getSearchKnowledgeCount(paramVo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getSearchKnowledgeByEvaluate <BR>
	 * Description: 获取搜索的知识- 按评价排序 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getSearchKnowledgeByEvaluate")
	public Object getSearchKnowledgeByEvaluate(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getSearchKnowledgeByEvaluate方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			String searchKey = paramVo.getKnowledgeName();
			String ids = "";
			if(searchKey!=null && !searchKey.isEmpty()){
				ids = returnSearchIds(searchKey);
				if(ids ==null){// 未搜索到结果
					return arv;
				}
				paramVo.setIdsStr(ids);
				
			}
			int count = knowledgeLibraryInfoService.getSearchKnowledgeByEvaluateCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			List<KnowledgeOtherVo> vo = knowledgeLibraryInfoService.getSearchKnowledgeByEvaluate(paramVo);
			arv.setRtnDataList(vo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getSearchKnowledgeByEvaluateCount")
	public Object getSearchKnowledgeByEvaluateCount(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getSearchKnowledgeByEvaluateCount方法");
		int count =0;
		try {
			String searchKey = paramVo.getKnowledgeName();
			String ids = "";
			if(searchKey!=null && !searchKey.isEmpty()){
				ids = returnSearchIds(searchKey);
				if(ids ==null){// 未搜索到结果
					return 0;
				}
				paramVo.setIdsStr(ids);
				
			}
			count = knowledgeLibraryInfoService.getSearchKnowledgeByEvaluateCount(paramVo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getSearchKnowledgeByHot <BR>
	 * Description: 获取搜索的知识- 按热门排序 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getSearchKnowledgeByHot")
	public Object getSearchKnowledgeByHot(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getSearchKnowledgeByHot方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		try {
			String searchKey = paramVo.getKnowledgeName();
			String ids = "";
			if(searchKey!=null && !searchKey.isEmpty()){
				ids = returnSearchIds(searchKey);
				if(ids ==null){// 未搜索到结果
					return arv;
				}
				paramVo.setIdsStr(ids);
				
			}
			int count = knowledgeLibraryInfoService.getSearchKnowledgeByHotCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			List<KnowledgeOtherVo> vo = knowledgeLibraryInfoService.getSearchKnowledgeByHot(paramVo);
			arv.setRtnDataList(vo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	@ResponseBody
	@RequestMapping("getSearchKnowledgeByHotCount")
	public Object getSearchKnowledgeByHotCount(HttpServletRequest request,SearchKlByNameVo paramVo) {
		logger.debug("KnowledgeLibraryAction执行getSearchKnowledgeByHotCount方法");
		AjaxReturnVo<KnowledgeOtherVo> arv = new AjaxReturnVo<KnowledgeOtherVo>();
		String searchKey = paramVo.getKnowledgeName();
		String ids = "";
		if(searchKey!=null && !searchKey.isEmpty()){
			ids = returnSearchIds(searchKey);
			if(ids ==null){// 未搜索到结果
				return 0;
			}
			paramVo.setIdsStr(ids);
			
		}
		int count=0;
		try {
			count = knowledgeLibraryInfoService.getSearchKnowledgeByHotCount(paramVo);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: downCreateKl <BR>
	 * Description: 下载创建的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeText  void<BR>
	 */
	@RequestMapping("downCreateKl")
	public void downCreateKl(HttpServletRequest request,HttpServletResponse response,String klId) {
		logger.debug("KnowledgeLibraryAction执行downCreateKl方法");
		try {
			KnowledgeOtherVo bean = knowledgeLibraryInfoService.downCreateKl(klId);
			String name = bean.getKnowledgeName();
			if(name==null){
				name = UUID.randomUUID().toString();
			}
			String text = bean.getKnowledgeText();
			StringBuffer html = new StringBuffer();  
			// 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" +new String((name+".pdf").getBytes(),"iso8859-1"));
            OutputStream clientOutput =  new BufferedOutputStream(response.getOutputStream());
            html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");  
            html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("<head>")  
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
                .append("<style type=\"text/css\">*{font-family:KaiTi_GB2312;}</style>")
                .append("</head>")  
                .append("<body>");  
            html.append("<div>"+text+"</div>");  
            html.append("</body></html>"); 
            
            StringReader strReader = new StringReader(html.toString());
            
            PD4ML pd4ml = new PD4ML();  
            pd4ml.setPageInsets(new Insets(20, 10, 10, 10));  
            pd4ml.setHtmlWidth(950);  
            pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));  
            pd4ml.useTTF("java:fonts", true);  
            pd4ml.enableDebugInfo();  
            pd4ml.render(strReader, clientOutput);  
            clientOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: downUploadKl <BR>
	 * Description: 下载上传的知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeText  void<BR>
	 */
	@RequestMapping("downUploadKl")
	public void downUploadKl(HttpServletRequest request,HttpServletResponse response,String klId) throws Exception {
		logger.debug("KnowledgeLibraryAction执行downUploadKl方法");
		try {
			KnowledgeOtherVo bean = knowledgeLibraryInfoService.downCreateKl(klId);
			String name = bean.getKnowledgeName();
			String path = bean.getFilePath();
			String extendName = bean.getExtendName();
			if(name==null){
				name = UUID.randomUUID().toString();
			}
			name = name+"."+extendName;
			// 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" +new String(name.getBytes(),"iso8859-1"));
            path = path.replace(".swf", "."+extendName);
            path = PropertyUtil.getConfigureProperties("UPLOAD_PATH") + path.substring(8, path.length());
            FileInputStream fis = new FileInputStream(new File(path));
            OutputStream clientOutput =  new BufferedOutputStream(response.getOutputStream());
            int b = 0;
            while((b = fis.read())!= -1){
            	clientOutput.write(b);
            }
            clientOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPeopleInfo <BR>
	 * Description: 根据id获取人员基础信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getPeopleInfo")
	public Object getPeopleInfo(HttpServletRequest request,String userId) {
		logger.debug("KnowledgeLibraryAction执行getPeopleInfo方法");
		AjaxReturnVo<ManageUserBean> arv = new AjaxReturnVo<ManageUserBean>();
		try {
			//利用现有方法获取 他人贡献的知识数目
			String currentUser = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			SearchKlVo searchKlVo =  new SearchKlVo();
			searchKlVo.setUserId(currentUser);
			searchKlVo.setOthersId(userId);
			int count = knowledgeLibraryInfoService.getOthersKnowledgeCount(searchKlVo);
			ManageUserBean bean = knowledgeLibraryInfoService.getPeopleInfo(userId);
			arv.setRtnData(bean);
			arv.setCount(count);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
		}
		return arv;
	}
	/** chenrui end */
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
	
	/**
	 * Method name: 获取子公司id
	 * Description: getSubCompanyId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  int<BR>
	 */
	private int getSubCompanyId (HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String userId = String.valueOf(session.getAttribute(Constant.SESSION_USERID_LONG));
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
		return manageUserBean.getSubCompanyId();
	}
	
	/*luyunlong start*/
	
	/**
	 * Method name: toAccident <BR>
	 * Description: 事故分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toAccident")
	public String toAccident(HttpServletRequest request){
		return "my_knowledge/accident_fl";
	}
	
	/**
	 * 
	 * Method name: getKnowledgeCategory <BR>
	 * Description: 获取事故分类基础信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getAccidentCategory")
	public Object getAccidentCategory(HttpServletRequest request, String name) {
		logger.debug("KnowledgeLibraryAction执行getKnowledgeCategory方法");
		AjaxReturnVo<KLCategoryBean> arv = new AjaxReturnVo<KLCategoryBean>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			HttpSession session = request.getSession();
			ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
			param.put("companyId", user.getCompanyId());
			param.put("subCompanyId", user.getSubCompanyId());
			param.put("name", name);
			List<KLCategoryBean> list = knowledgeLibraryInfoService.getAccidentCategory(param);
			//添加跟节点
			KLCategoryBean bean = new KLCategoryBean();
			bean.setCategoryId(-2);
			bean.setCategoryName("事故案例分析");
			list.add(bean);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(KnowledgeLibraryInfoAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/*luyunlong end*/
	
}
