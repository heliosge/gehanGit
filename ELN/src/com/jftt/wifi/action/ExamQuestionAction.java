/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;
import com.jftt.wifi.bean.exam.vo.QuestionExportVo;
import com.jftt.wifi.bean.exam.vo.QuestionListItemVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;
import com.jftt.wifi.bean.knowledge_contest.CommonConstants;
import com.jftt.wifi.bean.knowledge_contest.SetUserInfoUtils;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.CompanyVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.util.ExamPaperExportUtils;
import com.jftt.wifi.util.ExcelExportUtils;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:ExamQuestionAction <BR>
 * class description: 试题Action <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
@Controller
@RequestMapping(value = "exam/question")
public class ExamQuestionAction {
	@Autowired
	private ExamQuestionService questionService;
	private ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger LOG = Logger
			.getLogger(ExamQuestionAction.class);
	
	@Autowired
	private ExamPaperService paperService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;

	// wangyifeng begin

	/**
	 * @author JFTT)wangyifeng
	 * Method name: toList <BR>
	 * Description: 管理员：试题列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toManageList")
	public String toManageList(Model model, HttpServletRequest request) {
		LOG.info("toManageList");
		model.addAttribute("listSource", "voList");
		model.addAttribute("containZTreeContextMenu", true);
		
		//公司ID 如不是1 说明是非普联  需要引入普联的试题库（云试题库）
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		/*request.setAttribute("subCompanyId", userInfo.getSubCompanyId());
		
		if(!userInfo.getSubCompanyId().equals(userInfo.getCompanyId()) && userInfo.getCompanyId() !=1){
			//非普联，设置集团公司ID
			request.setAttribute("companyId", userInfo.getCompanyId());
		}*/
		
		//需要引用的试题库
		List<CompanyVo> companyList = manageDepartmentService.getParents(userInfo);
		if(companyList.get(0).getFlag().intValue() == 1 && companyList.get(0).getId().intValue() !=1){
			//是集团公司，且不是普联  需要再引用一个云试题库, 即普联集团的试题库
			CompanyVo pulian = new CompanyVo(1L, "云", 0);
			
			companyList.add(0, pulian);
		}
		
		request.setAttribute("companyList", JSONArray.fromObject(companyList));
		
		return "manage_exam/question_list";
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: toUserList <BR>
	 * Description: 学员：试题列表页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toUserList")
	public String toUserList(Model model, HttpServletRequest request) {
		LOG.info("toUserList");
		model.addAttribute("listSource", "voUserList");
		model.addAttribute("containZTreeContextMenu", false);
		
		//公司ID 如不是1 说明是非普联  需要引入普联的试题库（云试题库）
		/*ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("subCompanyId", userInfo.getSubCompanyId());*/
		
		return "manage_exam/question_list";
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: toModify <BR>
	 * Description: 修改试题页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toModify")
	public String toModify(HttpServletRequest request, Integer questionId,
			Model model) {
		LOG.info("toModify");
		try {
			QuestionBean oriQuestion = questionService.getQuestion(questionId);
			String oriQuestionStr = objectMapper
					.writeValueAsString(oriQuestion);

			Object subCompanyId = SetUserInfoUtils.getUserPropertyInfo(request,
					CommonConstants.FIELD_NAME_SUB_COMPANY_ID);
			if (subCompanyId != null
					&& subCompanyId.equals(oriQuestion.getSubCompanyId())) {
				model.addAttribute("oriQuestion", oriQuestionStr);
			} else {
				LOG.warn(String.format(
						"非法访问：非当前子公司用户试图修改试题资源，试题编号:%d，用户编号：%s",
						oriQuestion.getId(),
						request.getSession().getAttribute(
								Constant.SESSION_USERID_LONG)));
			}
		} catch (IOException e) {
			LOG.warn("toModify: failed to get question by id: " + questionId, e);
		}
		
		model.addAttribute("title", "修改试题");
		
		return "manage_exam/question_edit";
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: toAdd <BR>
	 * Description: 新增试题页面 <BR>
	 * Remark: <BR>
	 * @param fromPaper
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toAdd")
	public String toAdd(Integer fromPaper, Integer categoryId, Model model) {
		LOG.info("toAdd");
		model.addAttribute("fromPaper", fromPaper);
		model.addAttribute("categoryId", categoryId);
		
		model.addAttribute("title", "新增试题");
		
		return "manage_exam/question_edit";
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: toDetail <BR>
	 * Description: 试题详情页面 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value = "toDetail")
	public String toDetail(Integer questionId, Model model) {
		LOG.info("toDetail");
		try {
			model.addAttribute("questionBean",
					objectMapper.writeValueAsString(questionService
							.getQuestion(questionId)));
		} catch (JsonProcessingException e) {
			LOG.warn("toDetail: failed to get question detail by id: "
					+ questionId, e);
		}
		return "manage_exam/question_detail";
	}

	/**
	 * Method name: getQuestionListItemVoList <BR>
	 * Description: 获取试题列表，用于试题管理列表页面:管理员视角 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<QuestionListItemVo><BR>
	 */
	@RequestMapping(value = "/voList")
	@ResponseBody
	public MMGridPageVoBean<QuestionListItemVo> getQuestionListForMMGrid(HttpServletRequest request, QuestionSearchOptionVo searchOption) {
		LOG.info("getQuestionListItemVoList");
		// Disable user input of property: CategoryIdListStr
		searchOption.setCategoryIdListStr(null);
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		
		if(searchOption.getSubCompanyId() == null){
			
			searchOption.setSubCompanyId(userInfo.getSubCompanyId());
		}
		
		if(searchOption.getCompanyId() == null){
			
			searchOption.setCompanyId(userInfo.getCompanyId());
		}
		
		LOG.debug(searchOption);
		return questionService.getQuestionListForMMGrid(searchOption);
	}

	/**
	 * Method name: getQuestionListItemVoList <BR>
	 * Description: 获取试题列表，用于试题管理列表页面:学员视角 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<QuestionListItemVo><BR>
	 */
	@RequestMapping(value = "/voUserList")
	@ResponseBody
	public MMGridPageVoBean<QuestionListItemVo> getUserQuestionListForMMGrid(
			HttpServletRequest request, QuestionSearchOptionVo searchOption) {
		LOG.info("getQuestionListItemVoList");
		// Disable user input of property: CategoryIdListStr
		searchOption.setCategoryIdListStr(null);
		SetUserInfoUtils.doWork(request, searchOption,
				CommonConstants.FIELD_NAME_ID,
				CommonConstants.FIELD_NAME_CREATE_USER_ID, Integer.class);
		SetUserInfoUtils.doWork(request, searchOption,
				CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
		LOG.debug(searchOption);
		return questionService.getQuestionListForMMGrid(searchOption);
	}
	
	/**
	 * @author JFTT) wj
	 * 导出试题
	 */
	@RequestMapping(value = "exportToWord")
	public void exportToWord(HttpServletRequest request, HttpServletResponse response, QuestionSearchOptionVo searchOption) {
		LOG.info("exportToWord");
		try {
			String fileName = "question.docx";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/x");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename*=UTF-8''%s", fileName);
			response.setHeader(headerKey, headerValue);

			searchOption.setCategoryIdListStr(null);
			if(searchOption.getSubCompanyId() != null && searchOption.getSubCompanyId() == 1){
				//云试题库，即普联试题库
			}else{
				SetUserInfoUtils.doWork(request, searchOption,CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
			}
			
			LOG.debug(searchOption);
			List<QuestionListItemVo> list = questionService.getQuestionListItemVoList(searchOption);
			
			XWPFDocument doc = ExamPaperExportUtils.exporQuestiontAsWord(questionService, list);
			
			OutputStream out = response.getOutputStream();
			doc.write(out);
			out.close();
		} catch (IOException e) {
			LOG.warn("Failed to export to word.", e);
		}
	}

	/**
	 * Method name: getQuestion <BR>
	 * Description: 获取单个试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 * @return QuestionBean<BR>
	 */
	@RequestMapping(value = "/get")
	@ResponseBody
	public QuestionBean getQuestion(Integer questionId) {
		LOG.info("getQuestion: " + questionId);
		return questionService.getQuestion(questionId);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: addQuestion <BR>
	 * Description: 新增试题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param newQuestion
	 * @return  AjaxReturnVo<Integer><BR>
	 */
	@RequestMapping(value = "/add", consumes = "application/json")
	@ResponseBody
	public AjaxReturnVo<Integer> addQuestion(HttpServletRequest request,
			@RequestBody QuestionBean newQuestion) {
		LOG.info("addQuestion");
		AjaxReturnVo<Integer> rtn = new AjaxReturnVo<Integer>();
		try {
			Assert.notNull(newQuestion);
			setUserRelatedInfo(request, newQuestion);
			if (newQuestion.getQuestionDisplayType() == QuestionDisplayType.GROUP
					&& newQuestion.getSubQuestions() != null) {
				for (QuestionBean sub : newQuestion.getSubQuestions()) {
					Assert.notNull(sub);
					setUserRelatedInfo(request, sub);
				}
			}
			Integer questionId = questionService.addQuestion(newQuestion);
			rtn.setRtnData(questionId);
			rtn.setRtnResult(Constant.AJAX_SUCCESS);
		} catch (Exception e) {
			LOG.warn("addQuestion question failed.", e);
			rtn.setRtnResult(Constant.AJAX_FAIL);
		}
		return rtn;
	}
	
	/**
	 * @author JFTT)wangjian
	 * 云试题库的试题，加到自己的库中
	 */
	@RequestMapping(value = "/addByYun")
	@ResponseBody
	public String addByYun(HttpServletRequest request, int categoryId, int[] questionId) {
		LOG.info("addByYun");

		try {
			for(int i=0; i<questionId.length; i++){
				//捞取试题
				QuestionBean question = questionService.getQuestion(questionId[i]);
				question.setCategoryId(categoryId);
				
				//塞入创建人，公司，分公司等信息
				setUserRelatedInfo(request, question);
				
				if (question.getQuestionDisplayType() == QuestionDisplayType.GROUP && question.getSubQuestions() != null) {
					//组合题
					for (QuestionBean sub : question.getSubQuestions()) {
						//塞入创建人，公司，分公司等信息
						setUserRelatedInfo(request, sub);
					}
				}
				
				//保存
				questionService.addQuestion(question);
			}
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("addByYun addQuestion question failed.", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: setUserRelatedInfo <BR>
	 * Description: setUserRelatedInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @param newQuestion  void<BR>
	 */
	private void setUserRelatedInfo(HttpServletRequest request,
			QuestionBean newQuestion) {
		SetUserInfoUtils.doWork(request, newQuestion,
				CommonConstants.FIELD_NAME_ID,
				CommonConstants.FIELD_NAME_CREATE_USER_ID, Integer.class);
		SetUserInfoUtils.doWork(request, newQuestion,
				CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
		SetUserInfoUtils.doWork(request, newQuestion,
				CommonConstants.FIELD_NAME_COMPANY_ID, Integer.class);
	}

	/**
	 * Method name: modifyQuestion <BR>
	 * Description: 修改试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param question
	 * @return String<BR>
	 */
	@RequestMapping(value = "/modify", consumes = "application/json")
	@ResponseBody
	public String modifyQuestion(@RequestBody QuestionBean question) {
		LOG.info("modifyQuestion: " + question.getId());
		questionService.modifyQuestion(question);
		return Constant.AJAX_SUCCESS;
	}

	/**
	 * Method name: deleteQuestions <BR>
	 * Description: 逻辑删除指定ID集合对应的试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionIdList
	 * @return String<BR>
	 */
	@RequestMapping(value = "/delete", consumes = "application/json")
	@ResponseBody
	public String deleteQuestions(@RequestBody List<Integer> questionIdList) {
		LOG.info("deleteQuestions: " + questionIdList);
		try {
			questionService.deleteQuestions(questionIdList);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("delete question failed.", e);
			return Constant.AJAX_FAIL;
		}
	}

	@RequestMapping(value = "uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile imgFile) {
		LOG.info("uploadFile: " + imgFile.getOriginalFilename());
		
		try {
			
			ManageCompanyBean company = manageCompanyService.selectCompanyById(((ManageUserBean)(request.getSession().getAttribute(Constant.SESSION_USERBEAN))).getCompanyId());
			if(company.getTotalCapacity() <= company.getUsedCapacity()){
				return "{\"result\":\"OVER_CAPACITY\"}";
			}
			
			return questionService.uploadFile(imgFile);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return Constant.AJAX_FAIL;
		}
	}

	// wangyifeng end
	
	//zhangbocheng start

	
	
	/**
	 * Method name: excelImportQuestions <BR>
	 * Description: excel导入 <BR>
	 * Remark: <BR>
	 * 
	 * @param filePath
	 * @return String<BR>
	 */
	@RequestMapping(value = "/excelImport")
	@ResponseBody
	public Object excelImportQuestions(HttpServletRequest request,String filePath,Integer categoryId) {
		LOG.info("excelImportQuestions: " + filePath);
		ManageUserBean user =  (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		if(user==null||user.getId()==null||user.getCompanyId()==null){
			return Constant.AJAX_FAIL;
		}		
		if(filePath==null||filePath.length()==0){
			return Constant.AJAX_FAIL;
		}
		try { 
			return	questionService.excelImport(filePath, user.getCompanyId(), user.getSubCompanyId(), Integer.parseInt(user.getId()),categoryId);

		} catch (Exception e) {
			LOG.warn("import question failed.", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: wordImportQuestions <BR>
	 * Description: word导入 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionIdList
	 * @return String<BR>
	 */
	@RequestMapping(value = "/wordImport")
	@ResponseBody
	public Object wordImportQuestions(HttpServletRequest request,String filePath,Integer categoryId,Integer difficultyLevel) {
		LOG.info("wordImportQuestions: " + filePath);
		ManageUserBean user =  (ManageUserBean)request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		if(user==null||user.getId()==null||user.getCompanyId()==null){
			return Constant.AJAX_FAIL;
		}		
		if(filePath==null||filePath.length()==0){
			return Constant.AJAX_FAIL;
		}
		try { 
			return	questionService.wordImport(filePath, user.getCompanyId(), user.getSubCompanyId(), Integer.parseInt(user.getId()),categoryId,difficultyLevel);
		} catch (Exception e) {
			LOG.warn("import question failed.", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/**
	 * Method name: downloadErrorFile <BR>
	 * Description: 下载错误文件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param path
	 * @throws Exception  void<BR>
	 */
	@RequestMapping(value="downloadErrorFile")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");
		String path =saveUrl +request.getParameter("path").replaceFirst("/upload/", "");
		
		FileUtil.writeFile(response, String.valueOf("错误信息.txt"), path, false);
	}
	
	

	/**
	 * Method name: exportToExcel <BR>
	 * Description: 导出试题到excel <BR>
	 * Remark: <BR>
	 * @param response
	 * @param paperId  void<BR>
	 */
	@RequestMapping(value = "exportToExcel")
	public void exportToExcel(HttpServletRequest request,HttpServletResponse response, QuestionSearchOptionVo searchOption) {
		try {
			if(searchOption.getSubCompanyId() != null && searchOption.getSubCompanyId() == 1){
				//云试题库，即普联试题库
			}else{
				SetUserInfoUtils.doWork(request, searchOption,CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
			}

			LOG.debug(searchOption);
			List<QuestionExportVo> list = questionService.getExportList(searchOption);
			List<String> titleList = new ArrayList<String>();
			List<String> valueList = new ArrayList<String>();
			titleList.add("题干");
			titleList.add("题型");
			titleList.add("难度");
			titleList.add("试题解析");
			titleList.add("选项内容，多个选项内容之间以单竖线(|)分隔开");
			titleList.add("答案(单选/多选/判断题)当为单选/多选题时答案内容就为选项内容;多个答案之间以单竖线(|)分隔开;当为判断题时请填写“正确”与“错误”表示对错;当为填空题时，题干有多少个()，就有多少个答案,多个答案之间以单竖线(|)分隔开;");
			titleList.add("关键字（多个关键字以空格分隔，主观题和填空题关键字为空时，默认和答案相同）");
			valueList.add("content");
			valueList.add("typeName");
			valueList.add("difficulty");
			valueList.add("analysis");
			valueList.add("options");
			valueList.add("answer");
			valueList.add("key");
			String fileName = "试题.xlsx";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/x");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename*=UTF-8''%s",
					fileName);
			response.setHeader(headerKey, headerValue);
			Workbook doc = ExcelExportUtils.createObjectExcel(list, titleList, valueList);
			OutputStream out = response.getOutputStream();
			doc.write(out);
			out.close();
		} catch (Exception e) {
			LOG.warn("Failed to export to excel.", e);
		}
	}
	//zhangbocheng end
}
