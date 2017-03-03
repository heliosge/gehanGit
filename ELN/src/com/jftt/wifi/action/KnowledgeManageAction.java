/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeManagerAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月14日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.elasticsearch.util.ElastisearchUtil;
import com.jftt.elasticsearch.util.FileUtil;
import com.jftt.wifi.bean.KLCategoryBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.KnowledgeLibraryInfoService;
import com.jftt.wifi.service.KnowledgeManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.MyElastisearchUtil;
import com.jftt.wifi.util.PropertyUtil;


/**
 * class name:知识管理action类 <BR>
 * class description: 主要为针对知识的增删改查
 * Remark: <BR>
 * @version 1.00 2015年7月14日
 * @author JFTT)liucaibao
 */
@Controller
@RequestMapping(value="knowledge")
public class KnowledgeManageAction  {

	
	//获取日志接口
	private static Logger log = Logger.getLogger(KnowledgeManageAction.class);
	
	@Resource(name="knowledgeManageService")
	private KnowledgeManageService knowledgeManageService;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private KnowledgeLibraryInfoService knowledgeLibraryInfoService;
	
	@Autowired
	private ManageCompanyService manageCompanyService;
	/**
	 * Method name: 知识管理主页。
	 * Description: QueryKnowledgeList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="list")
	public String  queryKnowledgeList(HttpServletRequest request ,ModelMap model){
		//入口日志。
		log.debug("进入知识审核页面查询");
		try {
			//查询该公司的知识分类列表。
			KLCategoryBean klCategoryBean = new KLCategoryBean();
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getCompanyId(request));
			List<KLCategoryBean>  listKL = 	knowledgeManageService.getKLCategoryTree(klCategoryBean);
			//拼接知识分类树的顶级目录 
			listKL.add(getZtreeHead(request));
			model.addAttribute("categoryList",JsonUtil.getJson4JavaList(listKL));

			if(getCompanyId(request) == getSubCompanyId(request)){
				model.addAttribute("isSub", "2");
			}else{
				model.addAttribute("isSub", "1");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询知识分类列表失败。"+e);
		}
		return "knowledge/list";
	}
	
	/**
	 * Method name: getKnowlegeListByMap <BR>
	 * Description: 获取知识列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getKLListByMap")
	@ResponseBody
	public Object getKLListByMap(HttpServletRequest request, int page, int pageSize,KnowledgeBean knowledgeBean){
			
			//入口日志
			log.debug("Enter Class:KnowledgeManageAction Method:getKLListByMap param:"+knowledgeBean.toString() );
			//int userId = getUserId(request);//request.getParameter("userId");
			//组织参数
			int size = 0;
			List<KnowledgeBean> rows = null;
			MMGridPageVoBean<KnowledgeBean> vo = new MMGridPageVoBean<KnowledgeBean>();
			try {
				//获得按照用户进行查询
				if(null!=knowledgeBean.getUserName()&&knowledgeBean.getUserName()!=""){
					Map<String,Object> param = new HashMap<String, Object>();
					param.put("name", knowledgeBean.getUserName().trim());
					param.put("companyId",getCompanyId(request));
					
					List<ManageUserBean> userBeans = manageUserService.findUserByCondition(param);
					List<String> list =new ArrayList<String>();
					for(ManageUserBean bean:userBeans ){
						list.add(bean.getId());
					}
					if(StringUtils.isNotBlank(knowledgeBean.getUserName().trim())){
						if(!list.isEmpty()){
							knowledgeBean.setUserIds(StringUtils.join(list,","));
						}else{
							knowledgeBean.setUserIds("-1");//如果查找不到，则给予-1,后台就查找不到数据
						}
					}
				}
				//queryKnowledgeList
				knowledgeBean.setCompanyId(getCompanyId(request));
				knowledgeBean.setSubCompanyId(getSubCompanyId(request));
				knowledgeBean.setCreateUserId(getUserId(request));
				//查询总数
				size = knowledgeManageService.queryKnowledgeCount(knowledgeBean);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e+"查询列表总数失败。param:"+knowledgeBean.toString());
			}
			
			
			try {
				knowledgeBean.setFromNum((page-1)*pageSize);
				
				knowledgeBean.setPage(pageSize);
				rows = knowledgeManageService.queryKnowledgeList(knowledgeBean);
				//此处需要处理，将userId转成userName
				for(KnowledgeBean kl:rows){
					int userId = kl.getCreateUserId();
					kl.setUserName(getUserBean(userId)==null?null:getUserBean(userId).getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e+"查询列表失败。param:"+knowledgeBean.toString());
				return vo;
			}
			vo.setTotal(size);
			vo.setRows(rows);
			return vo;
		}
	
	/**
	 * Method name: uploadKnowledge <BR>
	 * Description: 上传页面跳转 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="upload")
	public String uploadKnowledge(HttpServletRequest request,ModelMap model){
		
		boolean isPuLian = false;
		try {
			int companyId =  getCompanyId(request);
			if(companyId == Constant.PULIAN_COMPANY_ID){
				isPuLian = true;
			}
		} catch (Exception e) {
			log.debug("获取公司id失败。");
		}
		model.addAttribute("isPuLian",isPuLian);
		return "knowledge/upload";
	}
	/**
	 * Method name: uploadKnowledge <BR>
	 * Description: 创建知识页面跳转 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="create")
	public String createKnowledge(HttpServletRequest request,ModelMap model){
		boolean isPuLian = false;
		try {
			int companyId =  getCompanyId(request);
			if(companyId == Constant.PULIAN_COMPANY_ID){
				isPuLian = true;
			}
		} catch (Exception e) {
			log.debug("获取公司id失败。");
		}
		model.addAttribute("isPuLian",isPuLian);
		return "knowledge/create";
	}
	
	/**
	 * Method name:查询知识详情.
	 * Description: 此处，只需要查询文件信息。如果是上传的，只是
	 * 上传的文件路径，如果是富文本，则返回富文本
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="detail")
	public String queryKnowledgeDetail(HttpServletRequest request,int knowledgeId,String backUrl,ModelMap model){
		
		 KnowledgeBean   knowledgeBean =knowledgeManageService.queryKnowledgeDetail(knowledgeId);
		 model.addAttribute("klJSON",JsonUtil.getJson4JavaObject(knowledgeBean));
		 model.addAttribute("backUrl", backUrl);
		return "knowledge/detail";
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
		request.setAttribute("knowledgeId", knowledgeId);
		//查询此分类的相关信息，并返回到前台
		try {
			KnowledgeBean   knowledgeBean =knowledgeManageService.queryKnowledgeDetail(Integer.valueOf(knowledgeId));
			request.setAttribute("klJSON",JsonUtil.getJson4JavaObject(knowledgeBean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "knowledge/detail";
	}
	
	/**
	 * Method name: 保存上传知识
	 * Description: saveKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledge  void<BR>
	 */
	@RequestMapping(value="save",consumes = "application/json")
	@ResponseBody
	public String  saveKnowledge(HttpServletRequest request,@RequestBody KnowledgeBean[] knowledgeBeans){
		
		try {
			for(KnowledgeBean knowledgeBean : knowledgeBeans){   
				//组装公共的参数
	            knowledgeBean.setUserId(getUserId(request));//设置用户名
				knowledgeBean.setResourceType(1);//上传的目前只有文档。
				knowledgeBean.setStatus(1);//知识的状态，1未未审批，2为通过，3拒绝
	            knowledgeBean.setCompanyId(getCompanyId(request));
	            knowledgeBean.setSubCompanyId(getSubCompanyId(request));
	            //循环遍历。进行数据的上传
	            knowledgeManageService.saveKnowledge(knowledgeBean);
	            
	            String newId = knowledgeBean.getKnowledgeId() + "";
	            KnowledgeBean returnObj = new KnowledgeBean();
	            if(newId != null){
	            	returnObj = knowledgeManageService.queryKnowledgeDetail(Integer.parseInt(newId));
	            }
	            
	            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getCreateTime());
				String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getUpdateTime());
				String shareTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getShareTime());
				
	            JSONObject obj = MyElastisearchUtil.transKlObj(returnObj);
	            
	            obj.put("createTime", createTime);
	            obj.put("updateTime", updateTime);
	            obj.put("shareTime", shareTime);
	            
	            ElastisearchUtil.dataAdd(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", knowledgeBean.getKnowledgeId()+"", obj);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 保存创建知识
	 * Description: saveKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledge  void<BR>
	 */
	@RequestMapping("saveCreate")
	@ResponseBody
	public String  saveKnowledge(HttpServletRequest request,KnowledgeBean knowledgeBean){
		
		try {
			//组装公共的参数
            knowledgeBean.setUserId(getUserId(request));//设置用户名
			knowledgeBean.setResourceType(3);//创建的为文章。
			knowledgeBean.setStatus(1);//知识的状态，1未未审批，2为通过，3拒绝
            knowledgeBean.setCompanyId(getCompanyId(request));
            knowledgeBean.setSubCompanyId(getSubCompanyId(request));
            knowledgeManageService.saveKnowledge(knowledgeBean);
            String newId = knowledgeBean.getKnowledgeId() + "";
            KnowledgeBean returnObj = null;
            if(newId != null){
            	returnObj = knowledgeManageService.queryKnowledgeDetail(Integer.parseInt(newId));
            }
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getCreateTime());
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getUpdateTime());
			String shareTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getShareTime());
            
            JSONObject obj = JSONObject.fromObject(returnObj);
            
            obj.put("createTime", createTime);
            obj.put("updateTime", updateTime);
            obj.put("shareTime", shareTime);
            
            ElastisearchUtil.dataAdd(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", newId,obj);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: uploadFile <BR>
	 * Description: 上传文件，主要为调用第三方的插件进行文件的上传，并返回文件的路径 <BR>
	 * Remark: <BR>
	 * @param file
	 * @return  String<BR>
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file){
		String uploadResult = null;
		try {
			ManageCompanyBean company = manageCompanyService.selectCompanyById(((ManageUserBean)(request.getSession().getAttribute(Constant.SESSION_USERBEAN))).getCompanyId());
			if(company.getTotalCapacity() <= company.getUsedCapacity()){
				return "{\"result\":\"OVER_CAPACITY\"}";
			}
			String commonPath = request.getParameter("path").toString();
			uploadResult =knowledgeManageService.uploadFile(file,commonPath);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("recordCode", -1);
			return param.toString();
		}
		
		return uploadResult;//d.getswfPath();
	}
	
	
	
	
	/**
	 * Method name: 保存知识
	 * Description: saveKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledge  void<BR>
	 */
	@RequestMapping("update")
	@ResponseBody
	public String  updateKnowledge(HttpServletRequest request,KnowledgeBean knowledgeBean){
		
		try {
			knowledgeManageService.updateKnowledge(knowledgeBean);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	
	/**
	 * Method name: publishKL <BR>
	 * Description: 发布知识 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId
	 * @return  String<BR>
	 */
	@RequestMapping("publish")
	@ResponseBody
	public String publishKL(HttpServletRequest request,int knowledgeId){
		
		log.debug("进入发布知识方法。知识id为:"+knowledgeId);
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			
			param.put("userId",getUserId(request));
			param.put("knowledgeId", knowledgeId);
			knowledgeManageService.publishKL(param);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("发布知识失败。知识id为:"+knowledgeId);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	
	/**
	 * Method name: deleteKnowledge <BR>
	 * Description: deleteKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId  void<BR>
	 */
	@RequestMapping("deleteKL")
	@ResponseBody
	public String deleteKnowledge(HttpServletRequest request,int knowledgeId){
		log.debug("进入删除知识方法。知识id为:"+knowledgeId);
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("userId",getUserId(request));
			param.put("knowledgeId", knowledgeId);
			knowledgeManageService.deleteKnowledge(param);
			
			// 全文检索库删除
			ElastisearchUtil.dataDelete(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", knowledgeId +"");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("删除知识失败。知识id为:"+knowledgeId+"失败原因为："+e);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 批量删除知识
	 * Description: deleteBatchKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids   知识id组织的array
	 */
	@RequestMapping("deleteBatch")
	@ResponseBody
	public String deleteBatchKnowledge(HttpServletRequest request,String ids){
		//入口日志
		log.debug("进入批量删除知识方法。知识id为:"+ids.toString());
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("userId",getUserId(request));
			if(!ids.isEmpty()){
				List<String> idArr =  Arrays.asList(ids.split("\\|"));
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
					param.put("knowledgeId", idArr.get(i));
					knowledgeManageService.deleteKnowledge(param);
					// 全文检索库删除
					ElastisearchUtil.dataDelete(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", idArr.get(i) +"");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.debug("删除知识失败。知识id为:"+ids.toString()+"失败原因为："+e);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
		
	}
	
	
	/**
	 * Method name: 批量内容进行分类的跟新
	 * Description: updateBatchKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids	
	 * @param categoryId  需要更新的分类id
	 */
	@RequestMapping("updateBatchKL")
	@ResponseBody
	public String updateBatchKnowledge(HttpServletRequest request,JSONArray ids,int categoryId){
		
		
		//获取当前操作的用户id
		String userId = String.valueOf(request.getSession().getAttribute("userId"));
		KnowledgeBean knowledgeBean = new KnowledgeBean();
		knowledgeBean.setCategoryId(categoryId);
		knowledgeBean.setUserId(Integer.valueOf(userId));
		try {
			if(!ids.isEmpty()){
				for(int i=0;i<ids.size();i++){
			
					try {
						if(StringUtils.isNotBlank(ids.getString(i))){
							
							knowledgeBean.setKnowledgeId(Integer.valueOf(ids.getString(i)));
							knowledgeManageService.updateOneKnowledgeCategroy(knowledgeBean);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: 修改知识信息
	 * Description: modifyKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId  void<BR>
	 */
	@RequestMapping("edit")
	public String modifyKnowledge(HttpServletRequest request,int knowledgeId,String type,ModelMap model,String roleType){
		
		try {
			//调用查询接口，返回查询列表
			KnowledgeBean knowledgeBean =knowledgeManageService.queryKnowledgeDetail(knowledgeId);
			model.put("klJSON",JsonUtil.getJson4JavaObject(knowledgeBean));
			KLCategoryBean klCategoryBean= new KLCategoryBean();
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getCompanyId(request));
			List<KLCategoryBean>  listKL = 	knowledgeManageService.getKLCategoryTree(klCategoryBean);
			//拼接知识分类树的顶级目录 
			listKL.add(getZtreeHead(request));
			model.addAttribute("categoryList",JsonUtil.getJson4JavaList(listKL));
			model.addAttribute("roleType", roleType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		if(Constant.CONSTANT_EDITUPLOAD.equals(type)){
			return "knowledge/editUpload";
		}
		
		return "knowledge/editCreate";
		
	}
	
	
	
	
	//以下为知识分类的处理部分
	
	/**
	 * Method name: 修改知识分类页面
	 * Description: editKLCategory <BR>
	 * Remark: <BR>
	 * @param categroyId
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping("editKLCategory")
	public String  editKLCategory(HttpServletRequest request,int categoryId,ModelMap model){
		log.debug("查询知识分类信息。");
		try {
			//查询此分类的相关信息，并返回到前台
			KLCategoryBean bean=knowledgeManageService.detailCategory(categoryId);
			model.addAttribute("categoryBean", bean);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("查询知识分类信息失败。"+e);
		}
		//跳转子页面
		return "knowledge/editKLCategory";
		
	}
	
	
	/**
	 * Method name: getKLCategoryTree <BR>
	 * Description: 获取知识分类树 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping("getKLCategoryTree")
	public String getKLCategoryTree(HttpServletRequest request,int categoryId,Integer roleType,ModelMap model){
		log.debug("获取知识分类树。当前选中的知识节点categoryId:"+categoryId);
		try {
			KLCategoryBean klCategoryBean= new KLCategoryBean();
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getCompanyId(request));
			klCategoryBean.setRoleType(roleType);
			List<KLCategoryBean>  listKL = 	knowledgeManageService.getKLCategoryTree(klCategoryBean);
			model.addAttribute("categoryList",JsonUtil.getJson4JavaList(listKL));
			model.addAttribute("categoryId", categoryId);
		} catch (Exception e) {
			log.error("查询知识分类树失败。错误原因为："+e);
 			e.printStackTrace();
		}
		return "knowledge/categoryTree";
	}
	
	
	/**
	 * Method name: asyncCategoryTree <BR>
	 * Description: asyncCategoryTree <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("asyncCategoryTree")
	@ResponseBody
	public Object asyncCategoryTree(HttpServletRequest request){
		
		log.debug("异步加载知识分类树");
		List<KLCategoryBean>  listKL = new ArrayList<KLCategoryBean>();
		try {
			KLCategoryBean klCategoryBean= new KLCategoryBean();
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getCompanyId(request));
			
			listKL = 	knowledgeManageService.getKLCategoryTree(klCategoryBean);
			listKL.add(getZtreeHead(request));
		} catch (Exception e) {
			log.error("查询知识分类树失败。错误原因为："+e);
 			e.printStackTrace();
		}
		return JsonUtil.getJson4JavaList(listKL);
	}
	/**
	 * Method name: 更新一个知识分类
	 * Description: updateKnowledgeCategroy <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledgeId
	 * @param categroyId  void<BR>
	 */
	@RequestMapping("updateOneKLCategory")
	@ResponseBody
	public String updateOneKnowledgeCategroy(HttpServletRequest request,KnowledgeBean knowledgeBean){
		
		//入口日志
		log.debug("更新单个知识的分类。传入参数为："+knowledgeBean.toString());
		try {
			knowledgeBean.setUserId(getCompanyId(request));//塞入参数
			knowledgeManageService.updateOneKnowledgeCategroy(knowledgeBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新单个知识的分类失败。");
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;		
	}
	
	
	/**
	 * Method name: updateBatchKLCategory <BR>
	 * Description: updateBatchKLCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids
	 * @param categoryId
	 * @return  String<BR>
	 */
	@RequestMapping("updateBatch")
	@ResponseBody
	public String updateBatchKLCategory(HttpServletRequest request,String ids,int categoryId){
		
		//入口日志
		log.debug("批量更新知识分类！参数为：knowledgeId"+ids.toString()+".categoryId:"+categoryId);
		try {
			KnowledgeBean knowledgeBean = new KnowledgeBean();
			knowledgeBean.setUserId(getUserId(request));
			knowledgeBean.setCategoryId(categoryId);
			if(!ids.isEmpty()){
				List<String> idArr =  Arrays.asList(ids.split("\\|"));
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
				int knowledgeId = Integer.valueOf(idArr.get(i));
				knowledgeBean.setKnowledgeId(knowledgeId);
				knowledgeManageService.updateOneKnowledgeCategroy(knowledgeBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("批量更新知识分类失败！参数为：knowledgeId"+ids.toString()+".categoryId:"+categoryId);
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	
	/**
	 * Method name:  更新知识分类
	 * Description: updateKnowledgeCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categroyId
	 * @param changeId  void<BR>
	 */
	@RequestMapping("updateKLCategory")
	@ResponseBody
	public String updateKnowledgeCategory(HttpServletRequest request,String categoryId,String oldCategoryId){
		try {
			int userId = getUserId(request);
			int companyId = getCompanyId(request);
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("oldCategoryId", oldCategoryId);
			param.put("categoryId", categoryId);
			param.put("userId", userId);
			param.put("subCompanyId",getSubCompanyId(request));
			param.put("companyId",companyId);
			
			knowledgeManageService.updateKnowledgeCategory(param);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;	
	}
	
	/**
	 * Method name: 保存知识分类（有则更新，没有则添加）
	 * Description: saveKLCategory <BR>
	 * Remark: <BR>  void<BR>
	 */
	@RequestMapping("saveKLCategory")
	@ResponseBody
	public String saveKLCategory(HttpServletRequest request,KLCategoryBean klCategoryBean){
		log.debug("保存知识分类。");
		try {
			int userId = getUserId(request);
			klCategoryBean.setUserId(userId);
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getSubCompanyId(request));
			knowledgeManageService.saveKLCategory(klCategoryBean);
		} catch (Exception e) {
			log.error("保存知识分类失败。错误为："+e);
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: updateKLCategoryName <BR>
	 * Description: updateKLCategoryName <BR>
	 * Remark: <BR>
	 * @param request
	 * @param klCategoryBean
	 * @return  String<BR>
	 */
	@RequestMapping("updateKLCategoryName")
	@ResponseBody
	public String updateKLCategoryName(HttpServletRequest request,KLCategoryBean klCategoryBean){
		
		log.debug("更新知识分类的名称");
		try {
			int userId = getUserId(request);
			klCategoryBean.setUserId(userId);
			knowledgeManageService.updateKLCategoryName(klCategoryBean);
		} catch (Exception e) {
			log.error("更新知识分类的名称失败。错误为："+e);
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
		
	}
	/**
	 * Method name: checkKLCategoryName <BR>
	 * Description: 校验是否重名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param klCategoryBean
	 * @return  String<BR>
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public String checkKLCategoryName(HttpServletRequest request,KLCategoryBean klCategoryBean){
		
		//入口日志
		log.debug("检查知识分类是否存在名称重复。传入参数为："+klCategoryBean.toString());
		int size =0;
		klCategoryBean.setUserId(getUserId(request));
		
		try {
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getCompanyId(request));
			size = knowledgeManageService.queryKLCategoryCount(klCategoryBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("检查知识分类名称是否存在重复失败。传入参数为："+klCategoryBean.toString());
			return Constant.AJAX_FAIL;
		}
		if(size==0){
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 删除知识分类
	 * Description: deleteKLCategory <BR>
	 * Remark: <BR>  void<BR>
	 */
	@RequestMapping("deleteCategory")
	@ResponseBody
	public String deleteKLCategory(HttpServletRequest request,int categoryId){
		
		//入口日志
		log.debug("删除某个知识分类。参数categoryId为:"+categoryId);
		try {
			knowledgeManageService.deleteKLCategory(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除某个知识分类失败。参数categoryId为:"+categoryId);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: isAllowDeleteCategory <BR>
	 * Description:知识分类节点是否允许删除。返回为success为允许删除，否则不允许删除 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categroyId
	 * @return  String<BR>
	 */
	@RequestMapping("isAllowDelete")
	@ResponseBody
	public String isAllowDeleteCategory(HttpServletRequest request,int categoryId){
		
		log.debug("检查某个知识分类节点是否有子集或者知识关联。参数categoryId："+categoryId);
		//主要为查询该节点下是否有知识或者子类
		boolean isAllow = false;
		
		try {
			isAllow = knowledgeManageService.isAllowDeleteCategory(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("检查某个知识分类节点是否有子集或者知识关联失败。categoryId："+categoryId);
			return Constant.AJAX_FAIL;
		}

		if(!isAllow){
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: 审核知识列表
	 * Description: auditKnowledgeList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("auditList")
	public String  auditKnowledgeList(HttpServletRequest request,ModelMap model){
		
		//此处组装数据，查询发布了的数据即可
		//入口日志。
		log.debug("审核知识列表。");
		try {
			//查询该公司的知识分类列表。
			KLCategoryBean klCategoryBean= new KLCategoryBean();
			klCategoryBean.setCompanyId(getCompanyId(request));
			klCategoryBean.setSubCompanyId(getCompanyId(request));
			List<KLCategoryBean>  listKL = 	knowledgeManageService.getKLCategoryTree(klCategoryBean);
			
			//拼接知识分类树的顶级目录 
			listKL.add(getZtreeHead(request));
			model.addAttribute("categoryList",JsonUtil.getJson4JavaList(listKL));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询知识分类列表失败。"+e);
		}
		
		return "knowledge/auditList";
	}
	
	
	/**
	 * Method name: getKnowlegeListByMap <BR>
	 * Description: 获取知识列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @param knowledgeBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getAuditKLListByMap")
	@ResponseBody
	public Object getAuditKLListByMap(HttpServletRequest request, int page, int pageSize,KnowledgeBean knowledgeBean){
			
			//入口日志
			log.debug("Enter Class:KnowledgeManageAction Method:getAuditKLListByMap param:"+knowledgeBean.toString() );
			//int userId = getUserId(request);//request.getParameter("userId");
			//组织参数
			int size = 0;
			List<KnowledgeBean> rows = null;
			MMGridPageVoBean<KnowledgeBean> vo = new MMGridPageVoBean<KnowledgeBean>();
			try {
				//获得按照用户进行查询
				if(null!=knowledgeBean.getUserName()&&knowledgeBean.getUserName()!=""){
					Map<String,Object> param = new HashMap<String, Object>();
					param.put("name", knowledgeBean.getUserName().trim());
					param.put("companyId",getCompanyId(request));
					param.put("subCompanyId",getSubCompanyId(request));

					List<ManageUserBean> userBeans = manageUserService.findUserByCondition(param);
					List<String> list =new ArrayList<String>();
					for(ManageUserBean bean:userBeans ){
						list.add(bean.getId());
					}
					if(StringUtils.isNotBlank(knowledgeBean.getUserName().trim())){
						if(!list.isEmpty()){
							knowledgeBean.setUserIds(StringUtils.join(list,","));
						}else{
							knowledgeBean.setUserIds("-1");//如果查找不到，则给予-1,后台就查找不到数据
						}
					}
				}
				//queryKnowledgeList
				knowledgeBean.setCompanyId(getCompanyId(request));
				knowledgeBean.setSubCompanyId(getSubCompanyId(request));
				//查询总数
				size = knowledgeManageService.queryAuditKnowledgeCount(knowledgeBean);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e+"查询列表总数失败。param:"+knowledgeBean.toString());
			}
			
			
			try {
				knowledgeBean.setFromNum((page-1)*pageSize);
				
				knowledgeBean.setPage(pageSize);
				rows = knowledgeManageService.queryAuditKnowledgeList(knowledgeBean);
				//此处需要处理，将userId转成userName
				for(KnowledgeBean kl:rows){
					int userId = kl.getCreateUserId();
					kl.setUserName(getUserBean(userId).getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e+"查询列表失败。param:"+knowledgeBean.toString());
				return vo;
			}
			vo.setTotal(size);
			vo.setRows(rows);
			return vo;
		}
	
	/**
	 * Method name:进行知识的审核
	 * Description: auditKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledge
	 * @param status
	 * @param refuse_memo  void<BR>
	 */
	@RequestMapping("audit")
	@ResponseBody
	public String auditKnowledge(HttpServletRequest request,KnowledgeBean knowledgeBean){
		
		try {
			knowledgeBean.setUserId(getUserId(request));
			knowledgeManageService.auditKnowledge(knowledgeBean);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name:进行知识的审核
	 * Description: auditKnowledge <BR>
	 * Remark: <BR>
	 * @param request
	 * @param knowledge
	 * @param status
	 * @param refuse_memo  void<BR>
	 */
	@RequestMapping("batchAudit")
	@ResponseBody
	public String batchAuditKnowledge(HttpServletRequest request,String ids,int status,String refuseMemo){
		
		try {
			KnowledgeBean param = new KnowledgeBean();
			param.setStatus(status);
			param.setUserId(getUserId(request));
			param.setRefuseMemo(refuseMemo);
			if(!ids.isEmpty()){
				List<String> idArr =  Arrays.asList(ids.split("\\|"));
				
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
					param.setKnowledgeId((Integer.valueOf(idArr.get(i))));
					knowledgeManageService.auditKnowledge(param);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}

	/**
	 * Method name: 获取用户Id
	 * Description: getUserId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  int<BR>
	 */
	private int getUserId (HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute(Constant.SESSION_USERID_LONG);
		if(null!=userId){
			return Integer.valueOf(String.valueOf(userId));
		}
		return 0;
	}
	
	/**
	 * Method name: getUserBean <BR>
	 * Description: 获取userBean <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  ManageUserBean<BR>
	 */
	private ManageUserBean getUserBean(int userId) throws Exception{
		
		return 	 manageUserService.findUserByIdCondition(String.valueOf(userId));
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
	
	/**
	 * Method name: getZtreeHead <BR>
	 * Description: 获取ztree的顶级目录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  KLCategoryBean<BR>
	 */
	private KLCategoryBean getZtreeHead(HttpServletRequest request) throws Exception{
		
		KLCategoryBean klbean = new KLCategoryBean();
		klbean.setCategoryId(0);
		klbean.setParentId(-1);
		ManageUserBean userBean = getUserBean(getUserId(request));
		klbean.setCategoryName(userBean.getCompanyName());
		return klbean;
	}
	/**
	 * 推荐 、取消推荐
	 * @param request
	 * @param id
	 * @param status
	 * @param refuseMemo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doRecommend")
	public void doRecommend(HttpServletRequest request,String id,String type,String createUserId){
		try {
			knowledgeManageService.doRecommend(id,type,createUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
