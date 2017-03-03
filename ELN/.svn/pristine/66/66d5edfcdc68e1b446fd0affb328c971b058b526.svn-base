/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PoliciesRulesAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月13日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.elasticsearch.util.ElastisearchUtil;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.bean.PoliciesRulesCategoryBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.PoliciesRulesService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.MyElastisearchUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:PoliciesRulesAction <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月13日
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("policiesRules")
public class PoliciesRulesAction {
	@Autowired
	private PoliciesRulesService policiesRulesService;
	private static Logger logger = Logger.getLogger(PoliciesRulesAction.class);
	/**
	 * 管理员--列表页
	 * Method name: toManageRulesList <BR>
	 * Description: toManageRulesList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toManageRulesList")
	public String toManageRulesList(HttpServletRequest request){
		return "policies_rules/manage_rulesList";
	}
	/**
	 * 管理--新增/修改页
	 * Method name: toManageRulesAdd <BR>
	 * Description: toManageRulesAdd <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toManageRulesAdd")
	public String toManageRulesAdd(HttpServletRequest request,String id){
		request.setAttribute("id", id);
		return "policies_rules/manage_rulesAdd";
	}
	/**
	 * 跳转 学员端列表页
	 * Method name: toRulesList <BR>
	 * Description: toRulesList <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toRulesList")
	public String toRulesList(HttpServletRequest request){
		return "policies_rules/rulesList";
	}
	/**
	 * 查看详情
	 * Method name: toDetail <BR>
	 * Description: toDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toDetail")
	public String toDetail(HttpServletRequest request,String id){
		request.setAttribute("curid", id);
		return "policies_rules/rulesDetail";
	}
	/**
	 * 根据id获取政策法规
	 * Method name: getByid <BR>
	 * Description: getByid <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getById")
	public Object getById(HttpServletRequest request,String id) {
		logger.debug("PoliciesRulesAction执行getById方法");
		AjaxReturnVo<PoliciesRulesBean> arv = new AjaxReturnVo<PoliciesRulesBean>();
		try {
				PoliciesRulesBean bean  = policiesRulesService.getById(id);
				arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
		return arv;
	}
	/**
	 * 新增政策法规
	 * Method name: add <BR>
	 * Description: add <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("add")
	public Object add(HttpServletRequest request,PoliciesRulesBean paramVo) {
		logger.debug("PoliciesRulesAction执行add方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			policiesRulesService.add(paramVo);
			String newId = paramVo.getId() + "";
			PoliciesRulesBean returnObj = policiesRulesService.getById(newId);

			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getCreateTime());
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getUpdateTime());
			String publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getPublishTime());
			String executeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getExecuteTime());
			// 全文检索库新增
			JSONObject obj = MyElastisearchUtil.transRuleObj(returnObj);
			obj.put("createTime", createTime);
			obj.put("updateTime", updateTime);
			obj.put("publishTime", publishTime);
			obj.put("executeTime", executeTime);
			ElastisearchUtil.dataAdd(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules", newId, obj);
			
			
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * 修改
	 * Method name: updateById <BR>
	 * Description: updateById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("updateById")
	public Object updateById(HttpServletRequest request,PoliciesRulesBean paramVo) {
		logger.debug("PoliciesRulesAction执行updateById方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			policiesRulesService.updateById(paramVo);
			PoliciesRulesBean returnObj = policiesRulesService.getById(paramVo.getId()+"");
			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getCreateTime());
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getUpdateTime());
			String publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getPublishTime());
			String executeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(returnObj.getExecuteTime());
			// 全文检索库更新
			JSONObject obj = MyElastisearchUtil.transRuleObj(returnObj);
			obj.put("createTime", createTime);
			obj.put("updateTime", updateTime);
			obj.put("publishTime", publishTime);
			obj.put("executeTime", executeTime);
			ElastisearchUtil.dataAdd(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules", returnObj.getId()+"", obj);
			
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 获取政策法规列表
	 * Method name: getPoliciesRulesList <BR>
	 * Description: getPoliciesRulesList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getPoliciesRulesList")
	public Object getPoliciesRulesList(HttpServletRequest request,PoliciesRulesSearchVo params) {
		logger.debug("PoliciesRulesAction执行getPoliciesRulesList方法");
		MMGridPageVoBean<PoliciesRulesBean> mmVo = new MMGridPageVoBean<PoliciesRulesBean>();
		try {
				int count = policiesRulesService.getPoliciesRulesListCount(params);
				String pageSize = params.getPageSize();
				String page = params.getPage();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				params.setFromNum(fromNum);
				List<PoliciesRulesBean> list  = policiesRulesService.getPoliciesRulesList(params);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 发布
	 * Method name: doPublish <BR>
	 * Description: doPublish <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("doPublish")
	public void doPublish(HttpServletRequest request,String id) {
		logger.debug("PoliciesRulesAction执行doPublish方法");
		try {	
				policiesRulesService.doPublish(id);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
	}
	/**
	 * 废止
	 * Method name: doAbolish <BR>
	 * Description: doAbolish <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("doAbolish")
	public void doAbolish(HttpServletRequest request,String id) {
		logger.debug("PoliciesRulesAction执行doAbolish方法");
		try {	
				policiesRulesService.doAbolish(id);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
	}
	/**
	 * 删除
	 * Method name: deleteByIds <BR>
	 * Description: deleteByIds <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("deleteByIds")
	public void deleteByIds(HttpServletRequest request) {
		logger.debug("PoliciesRulesAction执行deleteByIds方法");
		try {	
				String[] ids =  request.getParameterValues("ids[]");
				policiesRulesService.deleteByIds(ids);
				//处理全文检索库
				for(String id : ids){
					ElastisearchUtil.dataDelete(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules", id);
				}
				
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
	}
	/**
	 * 学员端 --政策法规列表获取
	 * Method name: stu_getPoliciesRulesList <BR>
	 * Description: stu_getPoliciesRulesList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("stu_getPoliciesRulesList")
	public Object stu_getPoliciesRulesList(HttpServletRequest request,PoliciesRulesSearchVo params) {
		logger.debug("PoliciesRulesAction执行stu_getPoliciesRulesList方法");
		AjaxReturnVo<PoliciesRulesBean> arv = new AjaxReturnVo<PoliciesRulesBean>();
		try {
				List<Object> ids = null;
				if(params.getSearchKey() != null && !params.getSearchKey().isEmpty()){
					ids = ElastisearchUtil.searchId(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules",params.getSearchKey(), "file_content","id");
					if(ids.size() == 0){
						return arv;
					}
				}
				String idsStr = "";
				if(ids!=null && ids.size()>0){
					for(Object tmp : ids){
						String id = tmp.toString();
						idsStr += id + ",";
					}
					idsStr = idsStr.substring(0,idsStr.length()-1);
					params.setIdsStr(idsStr);
				}
				String pageSize = params.getPageSize();
				String page = params.getPage();
				long fromNum = Integer.parseInt(pageSize) * (Integer.parseInt(page) - 1);
				params.setFromNum(fromNum);
				List<PoliciesRulesBean> list  = policiesRulesService.stu_getPoliciesRulesList(params);
				arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
		return arv;
	}
	@ResponseBody
	@RequestMapping("stu_getPoliciesRulesListCount")
	public int stu_getPoliciesRulesListCount(HttpServletRequest request,PoliciesRulesSearchVo params) {
		logger.debug("PoliciesRulesAction执行stu_getPoliciesRulesListCount方法");
		try {
			List<Object> ids = null;
			if(params.getSearchKey() != null && !params.getSearchKey().isEmpty()){
				ids = ElastisearchUtil.searchId(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules",params.getSearchKey(), "file_content", "id");
				if(ids.size() == 0){
					return 0;
				}
			}
			String idsStr = "";
			if(ids!=null && ids.size()>0){
				for(Object tmp : ids){
					String id = tmp.toString();
					idsStr += id + ",";
				}
				idsStr = idsStr.substring(0,idsStr.length()-1);
				params.setIdsStr(idsStr);
			}
			
			return policiesRulesService.stu_getPoliciesRulesListCount(params);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
		return 0 ;
	}
	
	/**
	 * 下载政策法规
	 * Method name: downloadById <BR>
	 * Description: downloadById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception  void<BR>
	 */
	@RequestMapping("downloadById")
	public void downloadById(HttpServletRequest request,HttpServletResponse response,String id) throws Exception {
		logger.debug("PoliciesRulesAction执行downloadById方法");
		try {
			PoliciesRulesBean bean = policiesRulesService.getById(id);
			String name = bean.getName();
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
			logger.debug(PoliciesRulesAction.class,e);
		}
		
	}
	/**
	 * 下载
	 * Method name: downUploads <BR>
	 * Description: downUploads <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception  void<BR>
	 */
	@RequestMapping("downUploads")
	public void downUploads(HttpServletRequest request,HttpServletResponse response,String downId) throws Exception {
		logger.debug("PoliciesRulesAction执行downUploads方法");
		try {
			PoliciesRulesBean bean = policiesRulesService.getById(downId);
			String name = bean.getName();
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
	 * 获取分类信息根据id
	 * Method name: getCategoryById <BR>
	 * Description: getCategoryById <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCategoryById")
	public Object getCategoryById(HttpServletRequest request,String categoryId) {
		logger.debug("PoliciesRulesAction执行getCategoryById方法");
		AjaxReturnVo<PoliciesRulesCategoryBean> arv = new AjaxReturnVo<PoliciesRulesCategoryBean>();
		try {
				PoliciesRulesCategoryBean be  = policiesRulesService.getCategoryById(categoryId);
				arv.setRtnData(be);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 获取当前政策法规的分类
	 * Method name: getCategorys <BR>
	 * Description: getCategorys <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCategorys")
	public Object getCategorys(HttpServletRequest request) {
		logger.debug("PoliciesRulesAction执行getCategorys方法");
		AjaxReturnVo<PoliciesRulesCategoryBean> arv = new AjaxReturnVo<PoliciesRulesCategoryBean>();
		try {
				List<PoliciesRulesCategoryBean> list  = policiesRulesService.getCategorys();
				arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
		return arv;
	}
	/**
	 * 新增政策法规分类
	 * Method name: addCategory <BR>
	 * Description: addCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("addCategory")
	public void addCategory(HttpServletRequest request,PoliciesRulesCategoryBean param) {
		logger.debug("PoliciesRulesAction执行addCategory方法");
		try {
				policiesRulesService.addCategory(param);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
	}
	/**
	 * 修改分类
	 * Method name: modifyCategory <BR>
	 * Description: modifyCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @param param  void<BR>
	 */
	@ResponseBody
	@RequestMapping("modifyCategory")
	public void modifyCategory(HttpServletRequest request,PoliciesRulesCategoryBean param) {
		logger.debug("PoliciesRulesAction执行modifyCategory方法");
		try {
				policiesRulesService.modifyCategory(param);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
	}
	/**
	 * 删除分类
	 * Method name: delCategory <BR>
	 * Description: delCategory <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("delCategory")
	public void delCategory(HttpServletRequest request,String categoryId) {
		logger.debug("PoliciesRulesAction执行delCategory方法");
		try {
				policiesRulesService.delCategory(categoryId);
		} catch (Exception e) {
			logger.debug(PoliciesRulesAction.class,e);
		}
	}
	
}
