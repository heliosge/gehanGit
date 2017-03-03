/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-20        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.action;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.InvestigationVoBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireAnswerBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.InvestigationService;
import com.jftt.wifi.service.QuestionnaireService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-20
 */
@Controller
@RequestMapping(value = "questionnaire")
public class QuestionnaireAction {
	private static final Logger LOG = Logger.getLogger(ExamQuestionAction.class);
	
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private InvestigationService investigationService;
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoManageList <BR>
	 * Description: gotoManageList <BR>
	 * Remark: <BR>
	 * @param model
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "gotoManageList")
	public String gotoManageList(Model model, HttpServletRequest request) {
		LOG.info("QuestionnaireAction_gotoManageList start");
		model.addAttribute("containZTreeContextMenu", true);
		LOG.info("QuestionnaireAction_gotoManageList end");
		return "manage_questionnaire/questionnaire_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: voList <BR>
	 * Description: voList <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "voList")
	@ResponseBody
	public Object voList(HttpServletRequest request,SearchOptionBean searchOption){
		LOG.info("QuestionnaireAction_voList start");
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCategoryIdListStr(null);
		//searchOption.setCreateUserId(Integer.parseInt(userBean.getId()));
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		MMGridPageVoBean<QuestionnaireBean> re = new MMGridPageVoBean<QuestionnaireBean>();
		List<QuestionnaireBean> list =  questionnaireService.getVoList(searchOption);
		int count = questionnaireService.getTotalCount(searchOption);
		//resolveFamilyTree(searchOption);
		re.setTotal(count);
		re.setRows(list);
		LOG.info("QuestionnaireAction_voList end");
		return re;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: pageAdd <BR>
	 * Description: 跳转到问卷新增页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "pageAdd")
	public String pageAdd(HttpServletRequest request) {
		LOG.info("QuestionnaireAction_pageAdd start");
		request.setAttribute("qBean", JsonUtil.getJson4JavaObject(null));
		request.setAttribute("qBean_questionList", JsonUtil.getJson4JavaList(null));
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("subCompanyId", userInfo.getSubCompanyId());
		
		LOG.info("QuestionnaireAction_pageAdd end");
		return "manage_questionnaire/questionnaire_add";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toAdd <BR>
	 * Description: 加载新增问题页面 <BR>
	 * Remark: <BR>
	 * @param fromPaper
	 * @param categoryId
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toAdd")
	public String toAdd(HttpServletRequest request,Integer fromPaper, Model model) {
		LOG.info("QuestionnaireAction_toAdd start");
		model.addAttribute("fromPaper", fromPaper);
		LOG.info("QuestionnaireAction_toAdd end");
		return "manage_questionnaire/question_edit";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: paperFileInput <BR>
	 * Description: 导入问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "questionnaireFileInput")
	public String questionnaireFileInput() {
		LOG.info("QuestionnaireAction_questionnaireFileInput");
		return "manage_questionnaire/questionnaire_file_input";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: AddQuestionnaire <BR>
	 * Description: AddQuestionnaire <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "/add", consumes = "application/json")
	@ResponseBody
	public Object addQuestionnaire(HttpServletRequest request,@RequestBody QuestionnaireBean newBean) throws Exception{
		LOG.info("QuestionnaireAction_addQuestionnaire start");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			newBean.setCreateUserId(Integer.parseInt(userBean.getId()));
			newBean.setCompanyId(userBean.getCompanyId());
			newBean.setSubCompanyId(userBean.getSubCompanyId());
			questionnaireService.addQuestionnaire(newBean);
			LOG.info("QuestionnaireAction_addQuestionnaire end");
			return Constant.AJAX_SUCCESS;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: delete <BR>
	 * Description: 删除问卷 <BR>
	 * Remark: <BR>
	 * @param idList
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/delete", consumes = "application/json")
	@ResponseBody
	public String delete(@RequestBody List<Integer> idList) {
		LOG.info("QuestionnaireAction_delete start" );
		try {
			questionnaireService.deleteQuestionnaires(idList);
			LOG.info("QuestionnaireAction_delete end" );
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("delete failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: pageModify <BR>
	 * Description: 跳转到问卷修改页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value = "pageModify")
	public String pageModify(HttpServletRequest request, int id) {
		LOG.info("QuestionnaireAction_pageModify start" );
		//根据ID获取问卷基本信息
		QuestionnaireBean qBean = questionnaireService.getQuestionnaire(id);
		//以下是查询问卷问题列表
		List<QuestionnaireQuestionBean> list = null;
		list = qBean.getQuestionList();
		request.setAttribute("qBean", JSONObject.fromObject(qBean));
		request.setAttribute("qBean_questionList", JSONArray.fromObject(list));
		LOG.info("QuestionnaireAction_pageModify end" );
		return "manage_questionnaire/questionnaire_add";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modify <BR>
	 * Description: 修改问卷 <BR>
	 * Remark: <BR>
	 * @param qBean
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/modify", consumes = "application/json")
	@ResponseBody
	public String modify(HttpServletRequest request,@RequestBody QuestionnaireBean qBean) {
		LOG.info("QuestionnaireAction_modify start");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			qBean.setCreateUserId(Integer.parseInt(userBean.getId()));
			qBean.setCompanyId(userBean.getCompanyId());
			qBean.setSubCompanyId(userBean.getSubCompanyId());
			questionnaireService.modifyQuestionnaire(qBean);
			LOG.info("QuestionnaireAction_modify end");
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("modify failed", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: pageDetail <BR>
	 * Description: 问卷详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value = "pageDetail")
	public String pageDetail(HttpServletRequest request, int id) {
		LOG.info("QuestionnaireAction_pageDetail start");
		//根据ID获取问卷基本信息
		QuestionnaireBean qBean = questionnaireService.getQuestionnaire(id);
		//以下是查询问卷问题列表
		List<QuestionnaireQuestionBean> list = null;
		list = qBean.getQuestionList();
		request.setAttribute("qBean", JSONObject.fromObject(qBean));
		request.setAttribute("qBean_questionList", JSONArray.fromObject(list));
		LOG.info("QuestionnaireAction_pageDetail end");
		return "manage_questionnaire/questionnaire_detail";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoInvestigationList <BR>
	 * Description: 跳转至调查管理列表 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value = "gotoInvestigationList")
	public String gotoInvestigationList(){
		LOG.info("QuestionnaireAction_gotoInvestigationList");
		return "manage_questionnaire/investigation_list";
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInvestigationList <BR>
	 * Description: 获取调查管理列表数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "investigationList")
	@ResponseBody
	public Object getInvestigationList(HttpServletRequest request,SearchOptionBean searchOption){
		LOG.info("QuestionnaireAction_getInvestigationList start");
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCategoryIdListStr(null);
		//searchOption.setCreateUserId(Integer.parseInt(userBean.getId()));
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		MMGridPageVoBean<InvestigationBean> re = new MMGridPageVoBean<InvestigationBean>();
		List<InvestigationBean> list =  investigationService.getList(searchOption);
		int count = investigationService.getTotalCount(searchOption);
		//resolveFamilyTree(searchOption);
		re.setTotal(count);
		re.setRows(list);
		LOG.info("QuestionnaireAction_getInvestigationList end");
		return re;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toAddInvestigation <BR>
	 * Description: 跳转至新增调查页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toAddInvestigation")
	public String toAddInvestigation(HttpServletRequest request) {
		LOG.info("QuestionnaireAction_toAddInvestigation start");
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(null));
		request.setAttribute("iBean_userList", JsonUtil.getJson4JavaList(null));
		LOG.info("QuestionnaireAction_toAddInvestigation end");
		return "manage_questionnaire/investigation_add";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addInvestigation <BR>
	 * Description: 新增调查 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param newBean
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "/addInvestigation", consumes = "application/json")
	@ResponseBody
	public Object addInvestigation(HttpServletRequest request,@RequestBody InvestigationBean newBean){
		LOG.info("QuestionnaireAction_addQuestionnaire start");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			newBean.setCreateUserId(Integer.parseInt(userBean.getId()));
			newBean.setCompanyId(userBean.getCompanyId());
			newBean.setSubCompanyId(userBean.getSubCompanyId());
			investigationService.add(newBean);
			LOG.info("QuestionnaireAction_addQuestionnaire end");
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteInvestigation <BR>
	 * Description: 删除调查 <BR>
	 * Remark: <BR>
	 * @param idList
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/deleteInvestigation", consumes = "application/json")
	@ResponseBody
	public String deleteInvestigation(@RequestBody List<Integer> idList) {
		LOG.info("QuestionnaireAction_deleteInvestigation start");
		try {
			investigationService.delete(idList);
			LOG.info("QuestionnaireAction_deleteInvestigation end");
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("delete failed", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isAddUser <BR>
	 * Description: 判断发布调查时，是否有添加参与人员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  boolean<BR>
	 */
	@RequestMapping("isAddUser")
	@ResponseBody
	public boolean isAddUser(HttpServletRequest request,int id){
		LOG.info("QuestionnaireAction_isAddUser");
		return investigationService.getUserByInvestigationId(id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: publish <BR>
	 * Description: 发布调查 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/publish")
	@ResponseBody
	public String publish(HttpServletRequest request,int id){
		LOG.info("QuestionnaireAction_publish start");
		try {
			investigationService.publish(id);
			LOG.info("QuestionnaireAction_publish end");
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("publish failed", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toModifyInvestigation <BR>
	 * Description: 跳转至调查编辑页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toModifyInvestigation")
	public String toModifyInvestigation(HttpServletRequest request,int id) throws Exception{
		LOG.info("QuestionnaireAction_toModifyInvestigation start");
		InvestigationBean iBean = investigationService.get(id);
		List<ManageUserBean> userList = investigationService.getUser(id);
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(iBean));
		request.setAttribute("iBean_userList", JsonUtil.getJson4JavaList(userList));
		LOG.info("QuestionnaireAction_toModifyInvestigation end");
		return "manage_questionnaire/investigation_add";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyInvestigation <BR>
	 * Description: 修改调查 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param iBean
	 * @return  String<BR>
	 */
	@RequestMapping(value = "/modifyInvestigation", consumes = "application/json")
	@ResponseBody
	public String modifyInvestigation(HttpServletRequest request,@RequestBody InvestigationBean iBean) {
		LOG.info("QuestionnaireAction_modifyInvestigation start");
		try {
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			iBean.setCreateUserId(Integer.parseInt(userBean.getId()));
			iBean.setCompanyId(userBean.getCompanyId());
			iBean.setSubCompanyId(userBean.getSubCompanyId());
			investigationService.modifyInvestigation(iBean);
			LOG.info("QuestionnaireAction_modifyInvestigation end");
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("modify failed", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toViewInvestigation <BR>
	 * Description: 查看调查 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "toViewInvestigation")
	public String toViewInvestigation(HttpServletRequest request,int id) throws Exception {
		LOG.info("QuestionnaireAction_toViewInvestigation start");
		//根据ID获取问卷基本信息
		/*QuestionnaireBean qBean = questionnaireService.getQuestionnaire(id);
		//以下是查询问卷问题列表
		List<QuestionnaireQuestionBean> list = null;
		list = qBean.getQuestionList();
		request.setAttribute("qBean", JSONObject.fromObject(qBean));
		request.setAttribute("qBean_questionList", JSONArray.fromObject(list));*/
		InvestigationBean iBean = investigationService.get(id);
		List<ManageUserBean> userList = investigationService.getUser(id);
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(iBean));
		request.setAttribute("iBean_userList", JsonUtil.getJson4JavaList(userList));
		LOG.info("QuestionnaireAction_toViewInvestigation end");
		return "manage_questionnaire/investigation_view";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestions <BR>
	 * Description: getQuestions <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getQuestions")
	@ResponseBody
	public Object getQuestions(HttpServletRequest request,int id){
		LOG.info("QuestionnaireAction_getQuestions start");
		List<QuestionnaireQuestionBean> list = questionnaireService.getInvestQuestions(id);
		LOG.info("QuestionnaireAction_getQuestions end");
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toResultList <BR>
	 * Description: 跳转至结果统计页面 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toResultList")
	public String toResultList(){
		LOG.info("QuestionnaireAction_toResultList");
		return "manage_questionnaire/result_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultList <BR>
	 * Description: 获取结果统计列表数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "getResultList")
	@ResponseBody
	public Object getResultList(HttpServletRequest request,SearchOptionBean searchOption){
		LOG.info("QuestionnaireAction_getResultList start");
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCategoryIdListStr(null);
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		//searchOption.setCreateUserId(Integer.parseInt(userBean.getId()));
		MMGridPageVoBean<InvestigationVoBean> re = new MMGridPageVoBean<InvestigationVoBean>();
		List<InvestigationVoBean> list =  investigationService.getResultList(searchOption);
		int count = investigationService.getVoTotalCount(searchOption);
		//resolveFamilyTree(searchOption);
		re.setTotal(count);
		re.setRows(list);
		LOG.info("QuestionnaireAction_getResultList end");
		return re;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: uploadFile <BR>
	 * Description: 导入问题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param quesfile
	 * @param filetype
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping(value="/uploadFile")
	public Object uploadFile(HttpServletRequest request, MultipartFile quesfile, Integer filetype){
		LOG.debug("QuestionnaireAction_uploadFile start");
		try {
			//1、获取文件储存的地址。
			String pageFileName= quesfile.getOriginalFilename();//.getName() ;
			
			String nameSuff=".jpg";
			
            int pos = pageFileName.lastIndexOf(".");
            if(pos != -1){
            	nameSuff = "." + pageFileName.substring(pos + 1);
            }
			
			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
			
			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "exam_paper/upload/"+df.format(new Date());
			
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			
			//2、获取文件的新的名称。以时间戳+四位随机数组成
			String fileName = getUUID()+nameSuff;
			String filePath = saveUrl+extendUrl+"/"+fileName;
			File sourceFile= new File(filePath);
			
			//先删除此文件
			FileUtil.deleteFile(sourceFile);
			
			//将上传的文件写到new出来的文件中
			FileUtil.SaveFileFromInputStream(quesfile.getInputStream(), filePath);
			
			ManageUserBean user =  (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			
			if(filetype == 1){
				//word
				Map<String, Object> obj = questionnaireService.wordImport(filePath, user.getCompanyId(), user.getSubCompanyId(), Integer.parseInt(user.getId()));
				if(obj.containsKey("status") && obj.get("status").toString().equals("0")){
					//上传成功
					List<QuestionnaireQuestionBean> qList = (List<QuestionnaireQuestionBean>) obj.get("list");
					obj.put("listData", qList);
				}
				LOG.debug("QuestionnaireAction_uploadFile end");
				return obj;
			}else{
				//excel
				Map<String, Object> obj = questionnaireService.excelImport(filePath, user.getCompanyId(), user.getSubCompanyId(), Integer.parseInt(user.getId()));
				
				if(obj.containsKey("status") && obj.get("status").toString().equals("0")){
					//上传成功
					List<QuestionnaireQuestionBean> qList = (List<QuestionnaireQuestionBean>) obj.get("list");
					obj.put("listData", qList);
				}
				LOG.debug("QuestionnaireAction_uploadFile end");
				return obj;
			}
		} catch (Exception e) {
			LOG.debug(MegagameManageAction.class,e);
			return null;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUUID <BR>
	 * Description: getUUID <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	private  String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        return str;
	 }
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exportDoc <BR>
	 * Description: 查看调查 导出word <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param bean  void<BR>
	 */
	@ResponseBody
	@RequestMapping("exportDoc")
	public void exportDoc(HttpServletRequest request,HttpServletResponse response,int id) {
		LOG.info("QuestionnaireAction_exportDoc start");
		try {
			String fileName = "investigation.docx";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/x");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename*=UTF-8''%s",fileName);
			response.setHeader(headerKey, headerValue);
			XWPFDocument doc = questionnaireService.exportDoc(id);
			OutputStream out = response.getOutputStream();
			doc.write(out);
			out.close();
			LOG.info("QuestionnaireAction_exportDoc end");
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toResultToal <BR>
	 * Description: 跳转至汇总统计页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping(value = "toResultToal")
	public String toResultToal(HttpServletRequest request,int id,String backType) {
		LOG.info("QuestionnaireAction_toResultToal start");
		InvestigationVoBean iBean = investigationService.getResultVo(id);
		request.setAttribute("iBean", JsonUtil.getJson4JavaObject(iBean));
		request.setAttribute("backType", backType);
		LOG.info("QuestionnaireAction_toResultToal end");
		return "manage_questionnaire/result_total";
	} 
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultDetail <BR>
	 * Description: 获取汇总数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "getResultDetail")
	@ResponseBody
	public Object getResultDetail(HttpServletRequest request,int id) throws Exception{
		LOG.info("QuestionnaireAction_getResultDetail start");
		List<QuestionnaireQuestionBean> answerList = questionnaireService.getResultDetail(id);
		LOG.info("QuestionnaireAction_getResultDetail end");
		return answerList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getUserAnswerList <BR>
	 * Description: 获取员工答卷明细列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping(value = "getUserAnswerList")
	@ResponseBody
	public Object getUserAnswerList(HttpServletRequest request,SearchOptionBean searchOption) throws Exception{
		LOG.info("QuestionnaireAction_getUserAnswerList start");
		MMGridPageVoBean<InvestigationAssignBean> re = new MMGridPageVoBean<InvestigationAssignBean>();
		List<InvestigationAssignBean> list =  investigationService.getAssignList(searchOption);
		int count = investigationService.getAssignListCount(searchOption);
		re.setTotal(count);
		re.setRows(list);
		LOG.info("QuestionnaireAction_getUserAnswerList end");
		return re;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exportResultDoc <BR>
	 * Description: 调查结果统计导出word <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("exportResultDoc")
	public void exportResultDoc(HttpServletRequest request,HttpServletResponse response,int id) {
		LOG.info("QuestionnaireAction_exportResultDoc start");
		try {
			String title = investigationService.getInvestigationName(id);
			String fileName = title + ".docx";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/x");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename*=UTF-8''%s",fileName);
			response.setHeader(headerKey, headerValue);
			XWPFDocument doc = questionnaireService.exportResultDoc(id);
			OutputStream out = response.getOutputStream();
			doc.write(out);
			out.close();
			LOG.info("QuestionnaireAction_exportResultDoc end");
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: validateTitle <BR>
	 * Description: 验证调查标题是否唯一 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param title
	 * @param id
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("validateTitle")
	public int validateTitle(HttpServletRequest request,String title,int id){
		LOG.info("QuestionnaireAction_validateTitle");
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		InvestigationBean bean = new InvestigationBean();
		bean.setTitle(title);
		if(id != 0){
			bean.setId(id);
		}
		bean.setCompanyId(userBean.getCompanyId());
		bean.setSubCompanyId(userBean.getSubCompanyId());
		return questionnaireService.validateTitle(bean);
	}

}
