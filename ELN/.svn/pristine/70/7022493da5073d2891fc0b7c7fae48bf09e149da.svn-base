/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamPaperAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/03        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo;
import com.jftt.wifi.bean.exam.vo.DifficultyLevelCountVo;
import com.jftt.wifi.bean.exam.vo.PaperListItemVo;
import com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo;
import com.jftt.wifi.bean.knowledge_contest.CommonConstants;
import com.jftt.wifi.bean.knowledge_contest.SetUserInfoUtils;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.common.IntegralConstant;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:ExamPaperAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/03
 * @author JFTT)wangyifeng
 */
@Controller
@RequestMapping(value = "exam/paper")
public class ExamPaperAction {

	private static final Logger LOG = Logger.getLogger(ExamPaperAction.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ExamPaperService paperService;
	@Autowired
	private ExamQuestionService questionService;
	@Autowired
	private IntegralManageService integralManageService;

	/**
	 * Method name: page <BR>
	 * Description: 跳转到试卷管理页面 <BR>
	 */
	@RequestMapping(value = "page")
	public String page() {
		
		return "manage_exam/paper_list";
	}
	
	/**
	 * Method name: page <BR>
	 * Description: 跳转到增加试卷页面 <BR>
	 */
	@RequestMapping(value = "pageAdd")
	public String pageAdd(HttpServletRequest request) {
		
		request.setAttribute("paperBean", JsonUtil.getJson4JavaObject(null));
		request.setAttribute("paperBean_questionList", JsonUtil.getJson4JavaList(null));
		request.setAttribute("paperBean_ruleList", JSONArray.fromObject(null));
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("subCompanyId", userInfo.getSubCompanyId());
		
		return "manage_exam/paper_add";
	}
	
	/**
	 * Method name: page <BR>
	 * Description: 跳转到增加试卷页面 <BR>
	 */
	@RequestMapping(value = "pageModify")
	public String pageModify(HttpServletRequest request, int id) {
		
		PaperBean paperBean = paperService.getPaper(id);
		
		List<QuestionBean> list = null;
		
		List<ExamOrganizingRuleBean> ruleList = null;
		
		if(paperBean.getOrganizingMode() == 1){
			//普通组卷
			
			if(paperBean.getPaperQuestionList() !=null && !paperBean.getPaperQuestionList().isEmpty()){
				
				list = new ArrayList<QuestionBean>();
				
				for (PaperQuestionBean ques : paperBean.getPaperQuestionList()) {
					
					QuestionBean bean = questionService.getQuestion(ques.getQuestionId());
					
					list.add(bean);
				}
			}
			
		}else if(paperBean.getOrganizingMode() == 2){
			//随机组卷
			
			ruleList = paperService.getExamOrganizingRuleBeanByPaperId(id);
		}
		
		request.setAttribute("paperBean", JSONObject.fromObject(paperBean));
		request.setAttribute("paperBean_questionList", JSONArray.fromObject(list));
		request.setAttribute("paperBean_ruleList", JSONArray.fromObject(ruleList));
		
		return "manage_exam/paper_add";
	}
	
	/**
	 * Method name: page <BR>
	 * Description: 跳转到试卷管理页面 插入试题 <BR>
	 */
	@RequestMapping(value = "pageLoadQuestion")
	public String pageLoadQuestion(HttpServletRequest request) {
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("subCompanyId", userInfo.getSubCompanyId());
		
		return "manage_exam/paper_load_question";
	}
	
	/**
	 * 更具 用户 选择的试题, 得到试题详情,返回页面
	 */
	@RequestMapping(value = "/pageLoadQuestionDate")
	@ResponseBody
	public Object pageLoadQuestionDate(Integer[] questionIdList) {
		
		List<QuestionBean> list = new ArrayList<QuestionBean>();
		
		if(questionIdList !=null && (questionIdList.length > 0)){
			
			for (Integer id : questionIdList) {
				
				QuestionBean bean = questionService.getQuestion(id);
				
				list.add(bean);
			}
		}
		
		return list;
	}

	// wangyifeng begin
	
	/**
	 * @author JFTT)wangyifeng
	 * Method name: exportToWord <BR>
	 * Description: 导出试卷到Word <BR>
	 * Remark: <BR>
	 * @param response
	 * @param paperId  void<BR>
	 */
	@RequestMapping(value = "exportToWord")
	public void exportToWord(HttpServletResponse response, Integer paperId) {
		LOG.info("exportToWord");
		try {
			String fileName = "exam.docx";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/x");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename*=UTF-8''%s",
					fileName);
			response.setHeader(headerKey, headerValue);
			XWPFDocument doc = paperService.exportToWord(paperId);
			OutputStream out = response.getOutputStream();
			doc.write(out);
			out.close();
		} catch (IOException e) {
			LOG.warn("Failed to export to word.", e);
		}
	}
	
	/**
	 * @author JFTT)wangyifeng
	 * Method name: pageDetail <BR>
	 * Description: 试卷详情（浏览试卷）页面 <BR>
	 * Remark: <BR>
	 * @param paperId
	 * @param model
	 * @return  String<BR>
	 */
	@RequestMapping(value = "pageDetail")
	public String pageDetail(Integer paperId, Model model) {
		LOG.info("pageDetail");
		try {
			PaperBean paperBean = paperService.getPaper(paperId);
			model.addAttribute("paperBean",
					objectMapper.writeValueAsString(paperBean));
		} catch (Exception e) {
			LOG.warn("获取试卷详情失败", e);
		}
		return "manage_exam/paper_detail";
	}

	/**
	 * Method name: add <BR>
	 * Description: 新增试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param newPaper
	 * @return String<BR>
	 */
	@RequestMapping(value = "/add", consumes = "application/json")
	@ResponseBody
	public String add(HttpServletRequest request, @RequestBody PaperBean newPaper) {
		LOG.info("add");
		try {
			
			SetUserInfoUtils.doWork(request, newPaper, CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
			SetUserInfoUtils.doWork(request, newPaper, CommonConstants.FIELD_NAME_COMPANY_ID, Integer.class);
			SetUserInfoUtils.doWork(request, newPaper, CommonConstants.FIELD_NAME_ID, CommonConstants.FIELD_NAME_CREATE_USER_ID, Integer.class);
			
			paperService.addPaper(newPaper);
			
			//积分
			try {
				Assert.notNull(newPaper.getCreateUserId());
				integralManageService.handleIntegral(newPaper.getCreateUserId(), IntegralConstant.Num_EXAM_ADD_PAPER);
			} catch (Exception e) {
				LOG.warn(String.format("Failed to add points for examPaper id: %d, user id: %d.", newPaper.getId(), newPaper.getCreateUserId()), e);
				throw new RuntimeException(e);
			}
			
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("add failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: delete <BR>
	 * Description: 删除试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param idList
	 * @return String<BR>
	 */
	@RequestMapping(value = "/delete", consumes = "application/json")
	@ResponseBody
	public String delete(@RequestBody List<Integer> idList) {
		LOG.info("delete: " + idList);
		try {
			paperService.deletePapers(idList);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("delete failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: modify <BR>
	 * Description: 修改试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param paper
	 * @return String<BR>
	 */
	@RequestMapping(value = "/modify", consumes = "application/json")
	@ResponseBody
	public String modify(@RequestBody PaperBean paper) {
		LOG.info("modify");
		try {
			paperService.modifyPaper(paper);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("modify failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: get <BR>
	 * Description: 获取试卷详情 <BR>
	 * Remark: <BR>
	 * 
	 * @param id
	 * @return PaperBean<BR>
	 */
	@RequestMapping(value = "/get")
	@ResponseBody
	public PaperBean get(Integer id) {
		LOG.info("get: " + id);
		return paperService.getPaper(id);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getQuestionList <BR>
	 * Description: 获取试题列表 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<PaperQuestionBean><BR>
	 */
	@RequestMapping(value = "/getQuestionList")
	@ResponseBody
	public List<PaperQuestionBean> getQuestionList(Integer id) {
		LOG.info("getQuestionList: " + id);
		LOG.debug("getQuestionList begin time: " + new Date());
		List<PaperQuestionBean> resultList = paperService.getPaperQuestions(id);
		LOG.debug("getQuestionList end time: " + new Date());
		return resultList;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getPaperListForMMGrid <BR>
	 * Description: 获取列表页面的试卷列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  MMGridPageVoBean<PaperListItemVo><BR>
	 */
	@RequestMapping(value = "/voList")
	@ResponseBody
	public MMGridPageVoBean<PaperListItemVo> getPaperListForMMGrid(
			HttpServletRequest request, PaperSearchOptionVo searchOption) {
		LOG.info("getPaperListForMMGrid");
		// Disable user input of property: CategoryIdListStr
		searchOption.setCategoryIdListStr(null);
		SetUserInfoUtils.doWork(request, searchOption,
				CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
		LOG.debug(searchOption);
		return paperService.getPaperListForMMGrid(searchOption);
	}

	/**
	 * Method name: getDifficultyLevelCountList <BR>
	 * Description: 根据自由组卷规则，获取试题难度数量列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<DifficultyLevelCountVo><BR>
	 */
	@RequestMapping(value = "/difficultyLevelCountList", consumes = "application/json")
	@ResponseBody
	public List<DifficultyLevelCountVo> getDifficultyLevelCountList(
			@RequestBody AutoQuestionGroupVo autoQuestionGroup) {
		LOG.info("getDifficultyLevelCountList");
		// Disable user input of property: categoryIdListStr
		autoQuestionGroup.setCategoryIdListStr(null);
		LOG.debug(autoQuestionGroup);
		return questionService.getDifficultyLevelCountList(autoQuestionGroup);
	}
	
	/**
	 * Method name: getDifficultyLevelCountList <BR>
	 * Description: 根据自由组卷规则，获取试题难度数量列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<DifficultyLevelCountVo><BR>
	 */
	@RequestMapping(value = "/difficultyLevelCountListAll")
	@ResponseBody
	public List<List<DifficultyLevelCountVo>> difficultyLevelCountListAll(@RequestBody List<AutoQuestionGroupVo> autoQuestionGroupList) {
		LOG.info("getDifficultyLevelCountList");
		
		List<List<DifficultyLevelCountVo>> aa = null;
 		
		if(autoQuestionGroupList != null && autoQuestionGroupList.size() > 0){
			
			aa = new ArrayList<List<DifficultyLevelCountVo>>();
			
			for (AutoQuestionGroupVo autoQuestionGroupVo : autoQuestionGroupList) {
				
				autoQuestionGroupVo.setCategoryIdListStr(null);
				
				List<DifficultyLevelCountVo> list = questionService.getDifficultyLevelCountList(autoQuestionGroupVo);
				
				aa.add(list);
			}
		}

		return aa;
	}

	/**
	 * Method name: autoGenerateQuestionList <BR>
	 * Description: 根据自由组卷规则及各个难易度下指定的试题数目，自动生成试题列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<QuestionBean><BR>
	 */
	@RequestMapping(value = "/autoGenerateQuestionList", consumes = "application/json")
	@ResponseBody
	public List<QuestionBean> autoGenerateQuestionList(
			@RequestBody AutoQuestionGroupVo autoQuestionGroup) {
		LOG.info("autoGenerateQuestionList");
		// Disable user input of property: categoryIdListStr
		autoQuestionGroup.setCategoryIdListStr(null);
		List<QuestionBean> resultList = new ArrayList<QuestionBean>();
		try {
			List<Integer> idList = questionService
					.autoGenerateQuestionIdList(autoQuestionGroup);
			LOG.debug("idList: ");
			LOG.debug(idList);
			for (Integer id : idList) {
				resultList.add(questionService.getQuestion(id));
			}
		} catch (Exception e) {
			LOG.warn("autoGenerateQuestionList failed", e);
		}
		return resultList;
	}
	
	/**
	 * Method name: autoGenerateQuestionListAll <BR>
	 * Description: 根据自由组卷规则及各个难易度下指定的试题数目，自动生成试题列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param autoQuestionGroup
	 * @return List<QuestionBean><BR>
	 */
	@RequestMapping(value = "/autoGenerateQuestionListAll")
	@ResponseBody
	public List<QuestionBean> autoGenerateQuestionListAll(@RequestBody List<AutoQuestionGroupVo> autoQuestionGroupList) {
		LOG.info("autoGenerateQuestionList");
		
		//返回值
		List<QuestionBean> resultList = null;
		
		if(autoQuestionGroupList !=null && !autoQuestionGroupList.isEmpty()){
			
			resultList = new ArrayList<QuestionBean>();
			
			for (AutoQuestionGroupVo autoQuestionGroup : autoQuestionGroupList) {
				
				autoQuestionGroup.setCategoryIdListStr(null);
				
				List<Integer> idList = questionService.autoGenerateQuestionIdList(autoQuestionGroup);
				
				if(idList !=null && !idList.isEmpty()){
					
					for (Integer id : idList) {
						
						resultList.add(questionService.getQuestion(id));
					}
				}
			}
		}
		
		return resultList;
	}

	// TODO wangyifeng
	// 在增加下列功能时，注意给试题的检索条件加上限制：isEnabled = true<br>
	// 新增试题（没直接关系？）：新增后直接加入试卷？有继续新增按钮，当从新增试卷跳过去时，禁止继续新增？<br>
	// 试题库导入：需要加限制<br>
	// 自由组卷：现在修正<br>
	// 导入试题：默认为可用<br>

	// wangyifeng end
	
	/**
	 * 上传word, excel 试题
	 */
	@ResponseBody
	@RequestMapping(value="/uploadFile")
	public Object uploadFile(HttpServletRequest request, MultipartFile quesfile, Integer categoryId, Integer filetype){
		LOG.debug("MegagameManageAction执行addContestUploadImg方法");
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
				Map<String, Object> obj = questionService.wordImport(filePath, user.getCompanyId(), user.getSubCompanyId(), Integer.parseInt(user.getId()), categoryId, 2);
				
				if(obj.containsKey("status") && obj.get("status").toString().equals("0")){
					//上传成功
					
					List<QuestionBean> list = new ArrayList<QuestionBean>();
					
					List<Integer> idList = (List<Integer>) obj.get("list");
					
					if(idList !=null && (idList.size() > 0)){
						
						for (Integer id : idList) {
							
							QuestionBean bean = questionService.getQuestion(id);
							
							list.add(bean);
						}
					}
					
					obj.put("listData", list);
				}
				
				return JsonUtil.getJson4JavaObject(obj).toString();
			}else{
				//excel
				Map<String, Object> obj = questionService.excelImport(filePath, user.getCompanyId(), user.getSubCompanyId(), Integer.parseInt(user.getId()),categoryId);
				
				if(obj.containsKey("status") && obj.get("status").toString().equals("0")){
					//上传成功
					
					List<QuestionBean> list = new ArrayList<QuestionBean>();
					
					List<Integer> idList = (List<Integer>) obj.get("list");
					
					if(idList !=null && (idList.size() > 0)){
						
						for (Integer id : idList) {
							
							QuestionBean bean = questionService.getQuestion(id);
							
							list.add(bean);
						}
					}
					
					obj.put("listData", list);
				}
				
				return JsonUtil.getJson4JavaObject(obj).toString();
			}

		} catch (Exception e) {
			LOG.debug(MegagameManageAction.class,e);
			return null;
		}
	}
	
	 private  String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        return str;
	 } 
	 
	/**
	 * 跳转到试题导入页面
	 */
	@RequestMapping(value = "paperFileInput")
	public String paperFileInput() {
		
		return "manage_exam/paper_file_input";
	}
	
	/**
	 * 跳转到自由组卷,随机组卷页面
	 */
	@RequestMapping(value = "pageLoadQuestionSJ")
	public String pageLoadQuestionZY(HttpServletRequest request) {
		
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("subCompanyId", userInfo.getSubCompanyId());
		
		return "manage_exam/paper_load_question_sj";
	}
}
