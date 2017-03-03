/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: QuestionnaireServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-11-20        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jftt.wifi.action.ExamQuestionAction;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.AnswerVoBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAssignBean;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireAnswerBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryWithFullNameVo;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionOptionBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireRelationBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.InvestigationAssignDaoMapper;
import com.jftt.wifi.dao.InvestigationDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.dao.QuestionnaireAnswerDaoMapper;
import com.jftt.wifi.dao.QuestionnaireCategoryDaoMapper;
import com.jftt.wifi.dao.QuestionnaireDaoMapper;
import com.jftt.wifi.dao.QuestionnaireQuestionDaoMapper;
import com.jftt.wifi.dao.QuestionnaireQuestionOptionDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.InvestigationService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.QuestionnaireCategoryService;
import com.jftt.wifi.service.QuestionnaireService;
import com.jftt.wifi.util.ExcelUtils;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.HtmlUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * @author JFTT)zhangchen<BR>
 * class name:QuestionnaireServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-11-20
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{
	
	@Autowired
	private QuestionnaireDaoMapper questionnaireDaoMapper;
	@Autowired
	private QuestionnaireCategoryDaoMapper questionnaireCategoryDaoMapper;
	@Autowired
	private QuestionnaireQuestionDaoMapper questionnaireQuestionDaoMapper;
	@Autowired
	private QuestionnaireQuestionOptionDaoMapper questionnaireQuestionOptionDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private QuestionnaireCategoryService questionnaireCategoryService;
	@Autowired
	private InvestigationDaoMapper investigationDaoMapper;
	@Autowired
	private InvestigationAssignDaoMapper investigationAssignDaoMapper;
	@Autowired
	private QuestionnaireAnswerDaoMapper questionnaireAnswerDaoMapper;
	@Autowired
	private InvestigationService investigationService;
	@Resource(name="manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	@Resource(name="managePostDaoMapper")
	private ManagePostDaoMapper managePostDaoMapper;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	private static final Logger LOG = Logger.getLogger(ExamQuestionAction.class);
	private static final String FONT_FAMILY = "微软雅黑";
	private static String[] optionName = new String[26];
	private static Map<Integer, String> typeStrMap = new HashMap<Integer, String>();
	static {
		for (int i = 0; i < 26; i++) {
			optionName[i] = Character.toString((char) (0x41 + i));
		}
		typeStrMap.put(1, "单选题");
		typeStrMap.put(2, "多选题");
		typeStrMap.put(3, "主观题");
		typeStrMap.put(4, "评星题");
	}
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#addQuestionnaire(com.jftt.wifi.bean.questionnaire.QuestionnaireBean) <BR>
	 * Method name: addQuestionnaire <BR>
	 * Description: 新增问卷 <BR>
	 * Remark: <BR>
	 * @param qBean  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public void addQuestionnaire(QuestionnaireBean qBean) throws Exception {
		// TODO Auto-generated method stub
		//积分管理
		integralManageService.handleIntegral(qBean.getCreateUserId(), 7023);
		//保存问卷基本信息，返回主键ID
		qBean.setIsEnabled(1);
		qBean.setIsDeleted(0);
		questionnaireDaoMapper.addQuestionnaire(qBean);
		Integer id = qBean.getId();
		//以下插入问题
		Assert.notEmpty(qBean.getQuestionList());
		for (int i = 0; i < qBean.getQuestionList().size(); i++) {
			QuestionnaireQuestionBean question = qBean.getQuestionList().get(i);
			question.setQuestionnaireId(id);
			question.setOrderNum(i + 1);
			//保存调查问卷问题基本信息，返回主键ID
			questionnaireQuestionDaoMapper.addQuestionnaireQuestion(question);
			Integer qId = question.getId();
			//以下插入问题选项
			for(int j=0;j<question.getOptions().size();j++){
				QuestionnaireQuestionOptionBean oBean = question.getOptions().get(j);
				oBean.setQuestionId(qId);
				oBean.setOrderNum(j+1);
				//保存问题选项
				questionnaireQuestionOptionDaoMapper.addQuestionOption(oBean);
			}
		}
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#deleteQuestionnaires(java.util.List) <BR>
	 * Method name: deleteQuestionnaires <BR>
	 * Description: 删除问卷 <BR>
	 * Remark: <BR>
	 * @param idList  <BR>
	*/
	
	@Override
	public void deleteQuestionnaires(List<Integer> idList) {
		// TODO Auto-generated method stub
		if (idList != null) {
			for (Integer id : idList) {
				questionnaireDaoMapper.deleteQuestionnaire(id);
			}
		}
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#modifyQuestionnaire(com.jftt.wifi.bean.questionnaire.QuestionnaireBean) <BR>
	 * Method name: modifyQuestionnaire <BR>
	 * Description: 更新问卷 <BR>
	 * Remark: <BR>
	 * @param qBean  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public void modifyQuestionnaire(QuestionnaireBean qBean) throws Exception {
		// TODO Auto-generated method stub
		if (questionnaireDaoMapper.checkQuestionnaireReference(qBean.getId())) {
			//禁用原来的问卷
			questionnaireDaoMapper.disableQuestionnaire(qBean.getId());
			//新增问卷
			addQuestionnaire(qBean);
			/*QuestionnaireBean questionnaire = questionnaireDaoMapper.getQuestionnaire(qBean.getId());
			qBean.setCreateUserId(questionnaire.getCreateUserId());
			qBean.setSubCompanyId(questionnaire.getSubCompanyId());
			qBean.setCompanyId(questionnaire.getCompanyId());
			addQuestionnaire(qBean)*/;
		} else {
			realModifyQuestionnaire(qBean);
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: realModifyQuestionnaire <BR>
	 * Description: 实际更新问卷 <BR>
	 * Remark: <BR>
	 * @param qBean  void<BR>
	 */
	private void realModifyQuestionnaire(QuestionnaireBean qBean) {
		Assert.notNull(qBean.getId());
		//更新问卷基本信息
		questionnaireDaoMapper.modifyQuestionnaire(qBean);
		Assert.notEmpty(qBean.getQuestionList());
		//问卷ID
		Integer id = qBean.getId();
		//先删除问题选项
		List<QuestionnaireQuestionBean> qList = questionnaireQuestionDaoMapper.getQuestionnaireQuestionList(id);
		for(int i=0;i<qList.size();i++){
			Integer qId = qList.get(i).getId();
			questionnaireQuestionOptionDaoMapper.deleteQuestionOption(qId);
		}
		//以下是先删除问卷所有问题基本信息
		questionnaireQuestionDaoMapper.deleteQuestionByQuestionnaireId(id);
		//以下是新增问题及其选项
		for (int i = 0; i < qBean.getQuestionList().size(); i++) {
			QuestionnaireQuestionBean question = qBean.getQuestionList().get(i);
			question.setQuestionnaireId(id);
			question.setOrderNum(i + 1);
			questionnaireQuestionDaoMapper.addQuestionnaireQuestion(question);
			Integer qId = question.getId();
			//以下新增问题选项
			for(int j=0;j<question.getOptions().size();j++){
				QuestionnaireQuestionOptionBean oBean = question.getOptions().get(j);
				oBean.setQuestionId(qId);
				oBean.setOrderNum(j+1);
				//保存问题选项
				questionnaireQuestionOptionDaoMapper.addQuestionOption(oBean);
			}
		}
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#getQuestionnaire(java.lang.Integer) <BR>
	 * Method name: getQuestionnaire <BR>
	 * Description: 获取问卷 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public QuestionnaireBean getQuestionnaire(Integer id) {
		// TODO Auto-generated method stub
		QuestionnaireBean questionnaire = questionnaireDaoMapper.getQuestionnaire(id);
		if (questionnaire == null) {
			return null;
		}
		if (questionnaire.getCategoryId() != null) {
			questionnaire.setCategoryBean(questionnaireCategoryService.getCategory(questionnaire.getCategoryId()));
		}
		List<QuestionnaireQuestionBean> questionList = questionnaireQuestionDaoMapper
				.getQuestionnaireQuestionList(questionnaire.getId());
		questionnaire.setQuestionList(questionList);
		return questionnaire;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#getQuestionnaireRelations(java.lang.Integer) <BR>
	 * Method name: getQuestionnaireRelations <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public List<QuestionnaireRelationBean> getQuestionnaireRelations(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#getVoList(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getVoList <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public List<QuestionnaireBean> getVoList(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		Map<Integer, QuestionnaireCategoryWithFullNameVo> paperCategoryWithFullNameMap = questionnaireCategoryService
				.getQuestionnaireCategoryWithFullNameMap(searchOption.getCompanyId());
		resolveFamilyTree(searchOption);
		List<QuestionnaireBean> resultList = questionnaireDaoMapper.getVoList(searchOption);
		for (QuestionnaireBean q : resultList) {
			String categoryFullName = null;
			if (paperCategoryWithFullNameMap.containsKey(q.getCategoryId())) {
				categoryFullName = paperCategoryWithFullNameMap.get(
						q.getCategoryId()).getFullName();
			}
			q.setCategoryFullName(categoryFullName);
		}
		return resultList;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#getTotalCount(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getTotalCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public Integer getTotalCount(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		resolveFamilyTree(searchOption);
		return questionnaireDaoMapper.getTotalCount(searchOption);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: resolveFamilyTree <BR>
	 * Description: resolveFamilyTree <BR>
	 * Remark: <BR>
	 * @param searchOption  void<BR>
	 */
	private void resolveFamilyTree(SearchOptionBean searchOption) {
		if (searchOption.getCategoryIdListStr() == null) {
			searchOption.setCategoryIdListStr(questionnaireCategoryDaoMapper
					.getCategoryFamilyTree(searchOption.getCategoryId(),
							searchOption.getSubCompanyId()));
			searchOption.setCategoryId(null);
		}
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#getInvestQuestions(int) <BR>
	 * Method name: getInvestQuestions <BR>
	 * Description: 获取调查问卷问题列表 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	@Transactional
	public List<QuestionnaireQuestionBean> getInvestQuestions(int id) {
		// TODO Auto-generated method stub
		InvestigationBean iBean = investigationDaoMapper.getInvestigation(id);
		List<QuestionnaireQuestionBean> list = new ArrayList<QuestionnaireQuestionBean>();
		if(iBean != null){
			Integer qId = iBean.getQuestionnaireId();
			list = questionnaireQuestionDaoMapper.getQuestionnaireQuestionList(qId);
		}
		return list;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#getAnswerDetail(int) <BR>
	 * Method name: getAnswerDetail <BR>
	 * Description: 查询用户答卷详情 <BR>
	 * Remark: <BR>
	 * @param assignId
	 * @return  <BR>
	*/
	
	@Override
	@Transactional
	public List<QuestionnaireQuestionBean> getAnswerDetail(int assignId) {
		// TODO Auto-generated method stub
		//先通过参与ID查询到调查ID
		Integer investigationId = investigationAssignDaoMapper.getInvestigationId(assignId);
		//再通过调查查询详情答案信息
		List<QuestionnaireQuestionBean> qList = getInvestQuestions(investigationId);
		for(QuestionnaireQuestionBean qBean:qList){
			String answer = questionnaireAnswerDaoMapper.getAnswer(qBean.getId(), assignId);
			qBean.setAnswer(answer);
		}
		return qList;
	}
	
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#wordImport(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: wordImport <BR>
	 * Description: 导入word <BR>
	 * Remark: <BR>
	 * @param filePath
	 * @param companyId
	 * @param subCompanyId
	 * @param userId
	 * @return  <BR>
	*/
	@Override
	public  Map<String,Object> wordImport(String filePath,Integer companyId,Integer subCompanyId,Integer userId){
		//临时存放问题list
		List<QuestionnaireQuestionBean> qList = new ArrayList<QuestionnaireQuestionBean>();
		//返回的map
		Map<String,Object> dbObject =new HashMap<String,Object>();
		 StringBuilder sb = new StringBuilder();
        if (filePath.length() == 0) {
            dbObject.put("status", 2);
            LOG.error("文件不存在");
            return dbObject;
        }
        File file = new File(filePath);
        //Long  size = FileUtil.getSize(file);
        String extend = FileUtil.getExt(file);
        /**
        if (size > 52428800) {
            dbObject.put("status", 3);
            LOG.error("文件过大");
            return dbObject;
        }*/
        if (!"doc".equals(extend) ) {
            dbObject.put("status", 4);
            LOG.error("文件类型错误");
            return dbObject;
        }
        if (!FileUtil.isFile(file)) {
            dbObject.put("status", 2);
            LOG.error("文件不存在");
            return dbObject;
        }
        try {
        	FileInputStream fis = new FileInputStream(file);
            //载入文档
            HWPFDocument hwpf = new HWPFDocument(fis);
            //读取文字,得到文档的读取范围
            Range range = hwpf.getRange();
            //问题Bean
            QuestionnaireQuestionBean question =null ;
            //存放每一题的选项内容
            Map<String,String> optionMap = new LinkedHashMap<String,String>();
            //以下是循环读取word行内容
            for(int k=0;k<range.numParagraphs();k++){
          	  	Paragraph paragraph = range.getParagraph(k);
                String s = paragraph.text();
                s=s.replace((char)12288, ' ').replace((char)160, ' ').trim().replaceAll("\t|\r|\n", "");
                //判读是否匹配题干规则
                if(s.matches("^\\d*(\\、|\\.)[\\w\\W]*(.|。)\\s*(\\(|（)[\\w\\W]+(\\)|）)[\\s]*$")){
                	//判断上一题是否存在
                	if(question != null && question.getType() != null && question.getContent() != null){
                		//question  临时保存
                		List<QuestionnaireQuestionOptionBean> optionList = new ArrayList<QuestionnaireQuestionOptionBean>();
                		int i=1;
						for(Map.Entry<String,String> entry:optionMap.entrySet()){
							QuestionnaireQuestionOptionBean option= new QuestionnaireQuestionOptionBean();
							String optionStr = entry.getValue();
							option.setContent(optionStr);
							option.setOrderNum(i);
							optionList.add(option);
							i++;
						}
						question.setOptions(optionList);
						qList.add(question);
						question = new QuestionnaireQuestionBean();
						optionMap.clear();
					  }else{
						  question = new QuestionnaireQuestionBean();//创建新的试题对象
					  }
                		//获得试题类型
					  Integer typeId = checkType(s); 
					  LOG.info(s+ typeId+"：试题类型");
					  if(typeId == null || typeId < 1){
						LOG.error(s+ "：不能得到具体的试题类型");
						sb.append(s+ "：不能得到具体的试题类型\r\n");
						question=null;
						continue; 
					  }
	              	  question.setType(typeId);
	              	  //是否必答
	              	  question.setIsRequired(1);
	              	//获得题干
	              	  String content=getContent(s);
	              	  question.setContent(content);
                	}else{
                		//不匹配题干规则，判断试题是否存在
	              	  if(question!=null&&question.getType()!=null&&question.getContent()!=null){
	              		  switch(question.getType())
	                  	  {
	                  	    
	                  	     case 1:
	                  	    	 if(s.matches("^\\s*[A-Z]\\s*(.|、)[\\w\\W]*$")){
	                                getOption(s,optionMap);
	                  	    	 }
	                  	    	 break;
	                  	     case 2:
	                  	    	if(s.matches("^\\s*[A-Z]\\s*(.|、)[\\w\\W]*$")){
	                  	    		getOption(s,optionMap);
	                      		 }
	                  	    	 break;
	                  	     default:
	                  	    	 break;
	                  	  }
	              	
	              	  }else{
	              		  continue;
	              	  }
                }//else end
                //读到最后，保存最后一题。因为每次都是读到第二题题干时才保存上一题
                if(k==(range.numParagraphs()-1)){
                	if(question != null && question.getType() != null && question.getContent() != null){
                		 //question  保存
                		qList.add(question);
                	}
                }
            }//for end
        }catch (Exception e) {
            e.printStackTrace();
        }
        dbObject.put("list", qList);
        if(sb.length() > 0){
            String errorFilePath=saveErrorFile(sb.toString()); //将错误信息写入文件
            dbObject.put("errorFilePath", errorFilePath);
        }
        dbObject.put("status", 0);
        return dbObject;
	}

	
	//获得试题类型
	 public  Integer checkType(String s){
         String danxuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*单\\s*(\\)|）)[\\s]*$";
         String duoxuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*多\\s*(\\)|）)[\\s]*$";
         //String zhuGuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*主\\s*(\\)|）)[\\s]*$";
         String zhuGuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*主\\s*(\\)|）)[\\s]*$";
         String pingxing="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*评\\s*(\\)|）)[\\s]*$";
         //String panDuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*(正确|错误)\\s*(\\)|）)[\\s]*$";
         //String tianKong="^[\\w\\W]+(\\(|（)\\s*(\\)|）)[\\w\\W]*(\\.|。)\\s*(\\(|（)[\\w\\W]+(\\)|）)[\\s]*$";
         if(s.matches(zhuGuan)){
        	 return 3;
         }else if(s.matches(danxuan)){
        	 return 1;
         }else if(s.matches(duoxuan)){
        	 return 2;
         }else if(s.matches(pingxing)){
        	 return 4;
         }
		 return 0;
	 }
		 
	 //获得题干
	 private String getContent(String s){
		 if(s==null||s.length()==0){
			 return null;
		 }
		
		 String contentRegEx ="^\\d*(\\、|\\.)";
		 s=s.replaceFirst(contentRegEx, "");
		 /*if(s.indexOf("")>-1&&picList.size()>0){
			 for(String picPath : picList){
				 picPath="<img class=\"question_content_img\" src=\""+picPath+"\">";
				 s=s.replaceFirst("", picPath);
			 }
		 }*/
		 s=s.replaceFirst("(\\.|。)\\s*(\\(|（)[\\w\\W]*(\\)|）)[\\s]*$", "");
		 s+="。";
		 
		 return s;
	 }
		 
	//检查试题选项，看是否包含文字
	 public boolean checkOption(String s){
		 String regex="^\\s*[A-Z]\\s*(\\.|、)\\s*[^\\s]+\\s*";
		 return s.matches(regex);
	 }
	 //取得选择题选项
	 public void getOption(String s,Map<String,String> map){
		 
		 String regex="^\\s*[A-Z]\\s*(\\.|、)";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(s);
		 while(matcher.find()){
			 int start = matcher.start();
			 int end= matcher.end();
			 String head=s.substring(start,end);
			 String content=s.substring(end);
			 head= head.trim().replaceFirst("(、|\\.)$", "");
			 content=content.trim();
			 map.put(head, content);
		 }
	 }
		 
	 //将错误信息保存到文件
	 public String saveErrorFile(String s ){
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");// 拿到实际存放地址。D:/ELN/upload/
		OutputStream out =null;
		try{
			// 获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "exam_paper/upload/" + df.format(new Date());
			File file = new File(saveUrl + extendUrl);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = UUID.randomUUID().toString() + ".txt";
			String filePath = saveUrl + extendUrl + "/" + fileName;
			// 将上传的文件写到new出来的文件中
		    out = new FileOutputStream(new File(filePath));
		    out.write(s.getBytes("UTF-8"));
		    out.flush();
	   		
		   
			 return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH") + "/"
       				+ extendUrl + "/" + fileName;
		  }catch(Exception e){
			  LOG.error("将错误信息保存到文件出错"+e);
			  return null;
		  }finally{
			  try{
				  out.close();
			  }catch(IOException e){
				  LOG.error("将错误信息保存到文件出错"+e);
		    	  return null;
			  }
		  }
	 }
	
	 /**
	 * @author JFTT)zhangchen<BR>
	 * Method name: excelImport <BR>
	 * Description: 导入excel <BR>
	 * Remark: <BR>
	 * @param filePath
	 * @param companyId
	 * @param subCompanyId
	 * @param userId
	 * @return  Map<String,Object><BR>
	 */
	@Override
	public  Map<String,Object> excelImport(String filePath, Integer companyId,Integer subCompanyId,Integer userId){
		Map<String,Object> dbObject =new HashMap<String,Object>();
		List<QuestionnaireQuestionBean> qList = new ArrayList<QuestionnaireQuestionBean>();
        StringBuilder sb = new StringBuilder();
        int fail = 0;
        int lastRow = 0;
        if (filePath.length() == 0) {
            dbObject.put("status", 2);
            LOG.error("文件不存在");
            return dbObject;
        }
        String fileName = FileUtil.getFileName(filePath, true);
        File file = new File(filePath);
        //Long  size = FileUtil.getSize(file);
        String extend = FileUtil.getExt(file);
        /**if (size > 52428800) {
            dbObject.put("status", 3);
            LOG.error("文件过大");
            return dbObject;
        }*/
        if (!"xls".equalsIgnoreCase(extend) && !"xlsx".equalsIgnoreCase(extend)) {
            dbObject.put("status", 4);
            LOG.error("文件类型错误");
            return dbObject;
        }
        if (!FileUtil.isFile(file)) {
            dbObject.put("status", 2);
            LOG.error("文件不存在");
            return dbObject;
        }
        try {
            Workbook workbook = ExcelUtils.getTemplate(file, fileName.toLowerCase().endsWith("xls"));
            Sheet sheet = workbook.getSheetAt(0);
            int firstRow = sheet.getFirstRowNum();
            lastRow = sheet.getLastRowNum();
            Map<String, Integer> mapType = new HashMap<String, Integer>();
            mapType.put("单选题", 1);
            mapType.put("多选题", 2);
            mapType.put("主观题", 3);
            mapType.put("评星题", 4);
            //取得表格第一标题行
            Row titleRow = sheet.getRow(0);
            //题干
            String cell_0 = ExcelUtils.getStringValue(titleRow.getCell(0));
            //题型
            String cell_1 = ExcelUtils.getStringValue(titleRow.getCell(1));
            //是否必答
            String cell_2 = ExcelUtils.getStringValue(titleRow.getCell(2));
            //选项内容
            String cell_3 = ExcelUtils.getStringValue(titleRow.getCell(3));
            if (titleRow.getLastCellNum() != 4
                    || !cell_0.startsWith("\u9898\u5E72")                       	//题干
                    || !"\u9898\u578B".equals(cell_1)                            	//题型
                    || !"\u662f\u5426\u5fc5\u7b54".equals(cell_2)                   //是否必答
                    || !cell_3.startsWith("\u9009\u9879\u5185\u5BB9") ) { 			//选项内容
                dbObject.put("status", 5);
                return dbObject;
            }
            for (int i = firstRow + 1; i <= lastRow; i++) {
                //取得表格中行
                Row row = sheet.getRow(i);
                if (row == null) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因为空\r\n");
                    continue;
                }
                //题干
                Cell nameCell = row.getCell(0);   
                String name = ExcelUtils.getStringValue(nameCell);
                if (nameCell == null) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因题干为空\r\n");
                    continue;
                }
                if (isBlank(name)) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因题干为空\r\n");
                    continue;
                }
                //题型
                Cell typeCell = row.getCell(1);   
                if (typeCell == null) {
                    sb.append("第" + i + "条:导入失败，原因题型为空\r\n");
                    fail++;
                    continue;
                }
                if (isBlank(ExcelUtils.getStringValue(typeCell))) {
                    sb.append("第" + i + "条:导入失败，原因题型为空\r\n");
                    fail++;
                }
                String typeName = ExcelUtils.getStringValue(typeCell);
                if (!(typeName.equals("单选题") || typeName.equals("多选题") || typeName.equals("主观题") 
                		|| typeName.equals("评星题"))) {
                    sb.append("第" + i + "条:导入失败，原因题型填写错误\r\n");
                    fail++;
                }
                Integer typeId = mapType.get(typeName);
                //是否必答
                Cell requiredCell = row.getCell(2);   
                if (requiredCell == null) {
                    sb.append("第" + i + "条:导入失败，原因是是否必答为空\r\n");
                    fail++;
                    continue;
                }
                if (isBlank(ExcelUtils.getStringValue(requiredCell))) {
                    sb.append("第" + i + "条:导入失败，原因是是否必答为空\r\n");
                    fail++;
                }
                String requiredName = ExcelUtils.getStringValue(requiredCell);
                if (!(requiredName.equals("是") || requiredName.equals("否"))) {
                    sb.append("第" + i + "条:导入失败，原因是否必答填写错误\r\n");
                    fail++;
                }
                Integer isRequired = 0;
                if("是".equals(requiredName)){
                	isRequired = 1;
                }
                //选项内容按 “|” 拆分
                Cell valueCell = row.getCell(3);   
                String value = ExcelUtils.getStringValue(valueCell);

                String[] valueArr = null;
                if (value.indexOf("|") > 0) {
                    valueArr = value.split("\\|");
                } else{
                    valueArr = new String[]{value};
                }
                switch (typeId) {
	                case 1:  //单选题
	                    if (valueArr.length < 2 ) {
	                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
	                        fail++;
	                        continue;
	                    }
	                    int res = isExit(valueArr);
	                    if (res == 0) {
	                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
	                        fail++;
	                        continue;
	                    }
	                    break;
	                case 2:  //多选题
	                    if (valueArr.length < 2 ) {
	                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
	                        fail++;
	                        continue;
	                    }
	                    int _res = isExit(valueArr);
	                    if (_res == 0) {
	                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
	                        fail++;
	                        continue;
	                    }
	                    break;
	            }
                //问卷问题
                QuestionnaireQuestionBean model = new QuestionnaireQuestionBean();
                //问题选项
                List<QuestionnaireQuestionOptionBean> optionList= new ArrayList<QuestionnaireQuestionOptionBean>();
                model.setType(typeId);
                model.setIsRequired(isRequired);
                model.setContent(name);
                //试题选项
                //单选
                if (typeId == 1) {
                    for (int j=0;j<valueArr.length;j++) {
                    	QuestionnaireQuestionOptionBean option =new QuestionnaireQuestionOptionBean();
                    	option.setContent(valueArr[j]);
                    	option.setOrderNum(j+1);
                        optionList.add(option);
                    }
                	model.setOptions(optionList);
                }
                //多选
                 if (typeId == 2) {
                    for (int j=0;j<valueArr.length;j++) {
                    	QuestionnaireQuestionOptionBean option =new QuestionnaireQuestionOptionBean();
                    	option.setContent(valueArr[j]);
                    	option.setOrderNum(j+1);
                        optionList.add(option); 
                    }
                	model.setOptions(optionList);
                }
                qList.add(model);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        dbObject.put("status", 0);
        dbObject.put("success", lastRow - fail > 0 ? lastRow - fail : 0);
        dbObject.put("fail", fail);
        if(sb.length()>0){
            String errorFilePath=saveErrorFile(sb.toString()); //将错误信息写入文件
            dbObject.put("errorFilePath", errorFilePath);
        }
        //dbObject.put("error", sb.toString());
        dbObject.put("list", qList);
        
        LOG.error(sb.toString());
        return dbObject;
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isBlank <BR>
	 * Description: 判断是否为空行 <BR>
	 * Remark: <BR>
	 * @param str
	 * @return  boolean<BR>
	 */
	public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
	 /**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isEmpty <BR>
	 * Description: 判断是否为空字符串 <BR>
	 * Remark: <BR>
	 * @param str
	 * @return  boolean<BR>
	 */
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isExit <BR>
	 * Description: isExit <BR>
	 * Remark: <BR>
	 * @param str1
	 * @return  int<BR>
	 */
	private  int isExit(String[] str1) {
        if(str1 == null && str1.length <= 0) {
            return 0;
        }
        if(str1.length > 10 ) {
            return 0;
        }
        
        List list = Arrays.asList(str1);
        Set set = new HashSet(list);
        if (set.size() < list.size()) {
            return 0;
        }
        int b = 2;
        return b;
    }

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#exportDoc(com.jftt.wifi.bean.questionnaire.InvestigationBean) <BR>
	 * Method name: exportDoc <BR>
	 * Description: 查看调查 导出word <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public XWPFDocument exportDoc(int id) throws Exception {
		// TODO Auto-generated method stub
		//调查基本信息
		InvestigationBean iBean = investigationService.get(id);
		//调查问卷问题信息
		List<QuestionnaireQuestionBean> qList = getInvestQuestions(id);
		//参与调查人员信息
		List<ManageUserBean> userList = investigationService.getUser(id);
		return writeWord(iBean,qList,userList);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: writeWord <BR>
	 * Description: 查看调查-写入word <BR>
	 * Remark: <BR>
	 * @param iBean
	 * @param qList
	 * @return  XWPFDocument<BR>
	 */
	private XWPFDocument writeWord(InvestigationBean iBean,List<QuestionnaireQuestionBean> qList,List<ManageUserBean> userList){
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun rTitle = p.createRun();
		rTitle.setBold(true);
		setFontFamily(rTitle);
		rTitle.setText(iBean.getTitle());
		rTitle.addCarriageReturn();
		//参与者
		XWPFRun rPerson = p.createRun();
		setFontFamily(rPerson);
		if(iBean.getIntendType() == 1){
			rPerson.setText("参与者：全员");
		}else{
			StringBuffer str = new StringBuffer();
			for(int i=0;i<userList.size();i++){
				str.append(userList.get(i).getName());
				if(i != userList.size()-1){
					str.append(", ");
				}
			}
			rPerson.setText("参与者："+str.toString());
		}
		rPerson.addCarriageReturn();
		//调查时间
		XWPFRun rTime = p.createRun();
		setFontFamily(rTime);
		rTime.setText("调查时间："+iBean.getBeginTime() + "-" + iBean.getEndTime());
		rTime.addCarriageReturn();
		
		//调查目的
		XWPFRun rAim = p.createRun();
		setFontFamily(rAim);
		rAim.setText("调查目的："+HtmlUtil.getTextFromHtml(iBean.getAim()));
		rAim.addCarriageReturn();
		
		//调查结果是否公开
		XWPFRun rPublic = p.createRun();
		setFontFamily(rPublic);
		rPublic.setText("结果是否公开："+ (iBean.getIsPublic() == 1?"公开":"不公开"));
		rPublic.addCarriageReturn();
		
		//题目汇总
		XWPFRun rTotal = p.createRun();
		int danxuan = 0;
		int duoxuan = 0;
		int zhuguan = 0;
		int pingxing = 0;
		for (int i = 0; i < qList.size(); i++) {
			QuestionnaireQuestionBean q = qList.get(i);
			if(q.getType() == 1){
				danxuan++;
			}else if(q.getType() == 2){
				duoxuan++;
			}else if(q.getType() == 3){
				zhuguan++;
			}else{
				pingxing++;
			}
		}
		setFontFamily(rTotal);
		rTotal.setText("单选题："+ danxuan +"；多选题："+ duoxuan +"；主观题："+ zhuguan +"；评星题："+pingxing);
		rTotal.addCarriageReturn();
		//问题详情
		for (int i = 0; i < qList.size(); i++) {
			QuestionnaireQuestionBean q = qList.get(i);
			XWPFRun r1 = p.createRun();
			r1.setBold(true);
			setFontFamily(r1);
			String content = q.getContent();
			r1.setText(String.format("%d.%s (%s)",i + 1, setNull(content), typeStrMap.get(q.getType())));
			r1.addCarriageReturn();

			XWPFRun r2 = p.createRun();
			setFontFamily(r2);
			writeQuestionDetail(q, r2);
		}
		return doc;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: writeQuestionDetail <BR>
	 * Description: 查看调查-写入试题详情 <BR>
	 * Remark: <BR>
	 * @param q
	 * @param r2  void<BR>
	 */
	private static void writeQuestionDetail(QuestionnaireQuestionBean q, XWPFRun r2) {
		switch (q.getType()) {
		case 1:
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2.setText(String.format("%s、%s", optionName[oIndex], setNull(q.getOptions().get(oIndex).getContent())));
				r2.addCarriageReturn();
			}
			break;
		case 2:
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2.setText(String.format("%s、%s", optionName[oIndex], setNull(q.getOptions().get(oIndex).getContent())));
				r2.addCarriageReturn();
			}
			break;
		case 3:
			// do nothing
			break;
		case 4:
			// do nothing
			break;
		default:
			throw new RuntimeException(String.format(
					"不支持该试题类型，试题编号：%d，试题类型：%d。", q.getId(), q.getType()));
		}
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: setNull <BR>
	 * Description: 判空 <BR>
	 * Remark: <BR>
	 * @param content
	 * @return  String<BR>
	 */
	private static String setNull(String content) {
		return StringUtils.isNotBlank(content) ? content : "暂无";
	}

	/**
	 * @author JFTT)zhangchen
	 * Method name: setFontFamily <BR>
	 * Description: 设置字体 <BR>
	 * Remark: <BR>
	 * @param r  void<BR>
	 */
	private static void setFontFamily(XWPFRun r) {
		r.setFontFamily(FONT_FAMILY);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getResultDetail <BR>
	 * Description: 获取汇总数据 <BR>
	 * Remark: <BR>
	 * @param id (调查主键ID)<BR>
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public List<QuestionnaireQuestionBean> getResultDetail(int id) throws Exception{
		//获取参与人员列表且全部已经提交
		List<InvestigationAssignBean> assignList = investigationAssignDaoMapper.getIntendList(id,1);
		//调查问卷的问题列表
		List<QuestionnaireQuestionBean> qList = getInvestQuestions(id);
		List<QuestionnaireAnswerBean> answerList = new ArrayList<QuestionnaireAnswerBean>();
		for(int i=0;i<assignList.size();i++){
			answerList.addAll(questionnaireAnswerDaoMapper.get(assignList.get(i).getId()));
		}
		//调查分配的ID
		String assignIds = investigationAssignDaoMapper.getAssignIdByInvestigationId(id);
		if(assignIds == null){
			assignIds = "0";
		}
		//组装页面所要的数据集
		for(int i=0;i<qList.size();i++){
			//单个问题基本信息
			QuestionnaireQuestionBean qBean = qList.get(i);
			Integer qId = qBean.getId();
			Integer qType = qBean.getType();
			//查询出该问题对应回答的人数
			int totalAnswerNum = questionnaireAnswerDaoMapper.getTotalAnswerNum(assignIds,qId);
			qBean.setTotalAnswerNum(totalAnswerNum);
			//以下是组装List<AnswerVoBean>
			List<AnswerVoBean> aList = new ArrayList<AnswerVoBean>();
			int starTotal = 0;
			for(int j=0;j<answerList.size();j++){
				AnswerVoBean avBean = new AnswerVoBean();
				if(qId.equals(answerList.get(j).getQuestionId())){
					if(qType == 4){//评星题
						if(StringUtils.isNumeric(answerList.get(j).getAnswer())){
							starTotal += Integer.parseInt(answerList.get(j).getAnswer());
						}
						//如果是评星题，算出平均分
						if(totalAnswerNum == 0){
							qBean.setStarAverage(0);
						}else{
							qBean.setStarAverage(starTotal/totalAnswerNum);
						}
					}
					avBean.setUserId(answerList.get(j).getUserId());
					avBean.setSubmitTime(answerList.get(j).getSubmitTime());
					avBean.setUserAnswer(answerList.get(j).getAnswer());
					ManageUserBean userBean = manageUserService.findUserById(answerList.get(j).getUserId().toString());
					String username = "暂无";
					String deptName = "暂无";
					String postName = "暂无";
					if(userBean != null){
						if(userBean.getHeadPhoto() != null && !"".equals(userBean.getHeadPhoto())){
							avBean.setHeadPhoto(userBean.getHeadPhoto());
						}else{
							avBean.setHeadPhoto(Constant.NO_HEAD_PHOTO);
						}
						username = userBean.getName();
						Integer depId = userBean.getDeptId();
						Integer postId = userBean.getPostId();
						//获取部门名称
						if(depId != null){
							ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
							if(dept != null){
								deptName = dept.getName();
							}
						}
						if(postId != null){
							ManagePostBean post = managePostDaoMapper.getById(postId);
							if(post != null){
								postName = post.getName();
							}
						}
					}// userBean end
					avBean.setUserName(username);
					avBean.setDeptName(deptName);
					avBean.setPostName(postName);
					aList.add(avBean);
				}//if end
			}//answerList for end
			qBean.setAnswerList(aList);
			//以下处理选项，只有单选与多选
			if(qType == 1 || qType == 2){
				List<QuestionnaireQuestionOptionBean> options = qBean.getOptions();
				for(QuestionnaireQuestionOptionBean oBean : options){
					Integer oId = oBean.getId();
					int selectNum = 0;
					for(int k=0;k<aList.size();k++){
						List<String> oList = new ArrayList<String>();
						String tempAns = aList.get(k).getUserAnswer();
						String[] aArray = tempAns.split(",");
						for(int n=0;n<aArray.length;n++){
							oList.add(aArray[n]);
						}
						if(oList.contains(oId.toString())){
							selectNum++;
						}
					}
					oBean.setSelectNum(selectNum);
					if(totalAnswerNum == 0){
						oBean.setSelectPercent("0.00");
					}else{
						DecimalFormat df = new DecimalFormat("0.00");
						oBean.setSelectPercent(String.valueOf(df.format((selectNum*1.0/totalAnswerNum)*100)));
					}
					
				}
			}
		}//qList for end
		return qList;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#exportResultDoc(int) <BR>
	 * Method name: exportResultDoc <BR>
	 * Description: 调查结果统计导出word <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public XWPFDocument exportResultDoc(int id) throws Exception {
		// TODO Auto-generated method stub
		//调查标题
		String title = investigationService.getInvestigationName(id);
		//答题信息
		List<QuestionnaireQuestionBean> list = getResultDetail(id);
		return writeResultWord(title,list);
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: writeResultWord <BR>
	 * Description: 结果统计写入word <BR>
	 * Remark: <BR>
	 * @param list
	 * @return  XWPFDocument<BR>
	 */
	private XWPFDocument writeResultWord(String title,List<QuestionnaireQuestionBean> list) {
		// TODO Auto-generated method stub
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun rTitle = p.createRun();
		rTitle.setBold(true);
		setFontFamily(rTitle);
		rTitle.setText(title);
		rTitle.addCarriageReturn();
		//问题详情
		for (int i = 0; i < list.size(); i++) {
			QuestionnaireQuestionBean q = list.get(i);
			XWPFRun r1 = p.createRun();
			r1.setBold(true);
			setFontFamily(r1);
			String content = q.getContent();
			if(q.getType() == 3 || q.getType() == 4){//主观题 、评星题
				r1.setText(String.format("(%s)%d.%s 答案(%d)",typeStrMap.get(q.getType()),i + 1, setNull(content), q.getTotalAnswerNum()));
			}else{
				r1.setText(String.format("(%s)%d.%s",typeStrMap.get(q.getType()),i + 1, setNull(content)));
			}
			r1.addCarriageReturn();

			XWPFRun r2 = p.createRun();
			setFontFamily(r2);
			writeResultQuestionDetail(q, r2);
		}
		return doc;
	}

	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: writeResultQuestionDetail <BR>
	 * Description: 写入答卷统计结果明细 <BR>
	 * Remark: <BR>
	 * @param q
	 * @param r2  void<BR>
	 */
	private void writeResultQuestionDetail(QuestionnaireQuestionBean q,XWPFRun r2) {
		// TODO Auto-generated method stub
		switch (q.getType()) {
		case 1://单选题
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2.setText(String.format("%s、%s  (%d人)", optionName[oIndex], 
						setNull(q.getOptions().get(oIndex).getContent()),q.getOptions().get(oIndex).getSelectNum()));
				r2.addCarriageReturn();
			}
			break;
		case 2://多选题
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2.setText(String.format("%s、%s  (%d人)", optionName[oIndex], 
						setNull(q.getOptions().get(oIndex).getContent()),q.getOptions().get(oIndex).getSelectNum()));
				r2.addCarriageReturn();
			}
			break;
		case 3://主观题
			List<AnswerVoBean> list = q.getAnswerList();
			for(int i=0;i<list.size();i++){
				r2.setText(String.format("%s 【%s %s】 %s",list.get(i).getUserName(),
						list.get(i).getDeptName(),list.get(i).getPostName(),list.get(i).getSubmitTime()));
				r2.addCarriageReturn();
				r2.setText(String.format("%s",list.get(i).getUserAnswer()));
				r2.addCarriageReturn();
			}
			break;
		case 4://评星题
			r2.setText(String.format("%d 分",q.getStarAverage() != null ? q.getStarAverage() : 0));
			r2.addCarriageReturn();
			break;
		default:
			throw new RuntimeException(String.format(
					"不支持该试题类型，试题编号：%d，试题类型：%d。", q.getId(), q.getType()));
		}
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#validateTitle(int, java.lang.String) <BR>
	 * Method name: validateTitle <BR>
	 * Description: 验证调查标题是否唯一 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param title
	 * @return  <BR>
	*/
	
	@Override
	public int validateTitle(InvestigationBean bean) {
		// TODO Auto-generated method stub
		int count = investigationDaoMapper.getTitleCount(bean);
		return count;
	}
	
}
