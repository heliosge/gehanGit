/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyQuestionnaireServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.questionnaire.InvestigationAnswerVo;
import com.jftt.wifi.bean.questionnaire.InvestigationBean;
import com.jftt.wifi.bean.questionnaire.InvestigationVoBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireAnswerBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireBean;
import com.jftt.wifi.bean.questionnaire.QuestionnaireQuestionBean;
import com.jftt.wifi.bean.questionnaire.SearchOptionBean;
import com.jftt.wifi.dao.InvestigationAssignDaoMapper;
import com.jftt.wifi.dao.QuestionnaireAnswerDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.InvestigationService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyQuestionnaireService;
import com.jftt.wifi.service.QuestionnaireService;
import com.jftt.wifi.util.HtmlUtil;

/**
 * @author JFTT)zhangchen<BR>
 * class name:MyQuestionnaireServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-3
 */
@Service
public class MyQuestionnaireServiceImpl implements MyQuestionnaireService{
	@Autowired
	private InvestigationAssignDaoMapper investigationAssignDaoMapper;
	@Autowired
	private QuestionnaireAnswerDaoMapper questionnaireAnswerDaoMapper;
	@Autowired
	private InvestigationService investigationService;
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private ManageUserService manageUserService;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
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
	 * @see com.jftt.wifi.service.MyQuestionnaireService#getVoList(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getVoList <BR>
	 * Description: 获取我的问卷列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public List<QuestionnaireBean> getVoList(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		return investigationAssignDaoMapper.getVoList(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyQuestionnaireService#getTotalCount(com.jftt.wifi.bean.questionnaire.SearchOptionBean) <BR>
	 * Method name: getTotalCount <BR>
	 * Description: 我的问卷列表数量 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/
	
	@Override
	public int getTotalCount(SearchOptionBean searchOption) {
		// TODO Auto-generated method stub
		return investigationAssignDaoMapper.getTotalCount(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyQuestionnaireService#saveAnswer(com.jftt.wifi.bean.questionnaire.InvestigationAnswerVo) <BR>
	 * Method name: saveAnswer <BR>
	 * Description: 保存问卷问题答案 <BR>
	 * Remark: <BR>
	 * @param vo  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public void saveAnswer(InvestigationAnswerVo vo) throws Exception {
		// TODO Auto-generated method stub
		if(vo != null){
			//积分管理
			integralManageService.handleIntegral(vo.getUserId(), 7022);
			String[] questionId = vo.getQuestionId();
			String[] answer = vo.getAnswer();
			Integer assignId = vo.getAssignId();
			QuestionnaireAnswerBean qaBean = new QuestionnaireAnswerBean();
			for(int i=0;i<questionId.length;i++){
				qaBean.setAssignId(assignId);
				qaBean.setQuestionId(Integer.parseInt(questionId[i]));
				qaBean.setAnswer(answer[i]);
				questionnaireAnswerDaoMapper.add(qaBean);
			}
			//modify status
			investigationAssignDaoMapper.modifyStatus(assignId);
		}
		
	}
	
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.QuestionnaireService#exportDoc(com.jftt.wifi.bean.questionnaire.InvestigationBean) <BR>
	 * Method name: exportDoc <BR>
	 * Description: 员工答卷详情 导出word <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public XWPFDocument exportDoc(int assignId) throws Exception {
		// TODO Auto-generated method stub
		//先通过参与ID查询到调查ID
		Integer investigationId = investigationAssignDaoMapper.getInvestigationId(assignId);
		//调查基本信息
		InvestigationBean iBean = investigationService.get(investigationId);
		//调查问卷问题信息
		//List<QuestionnaireQuestionBean> qList = questionnaireService.getInvestQuestions(investigationId);
		List<QuestionnaireQuestionBean> qList = questionnaireService.getAnswerDetail(assignId);
		//参与调查人员信息
		//List<ManageUserBean> userList = investigationService.getUser(investigationId);
		return writeWord(iBean,qList);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: writeWord <BR>
	 * Description: 员工答卷详情-写入word <BR>
	 * Remark: <BR>
	 * @param iBean
	 * @param qList
	 * @return  XWPFDocument<BR>
	 */
	private XWPFDocument writeWord(InvestigationBean iBean,List<QuestionnaireQuestionBean> qList){
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun rTitle = p.createRun();
		rTitle.setBold(true);
		setFontFamily(rTitle);
		rTitle.setText(iBean.getTitle());
		rTitle.addCarriageReturn();
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
		/*XWPFRun rPublic = p.createRun();
		setFontFamily(rPublic);
		rPublic.setText("结果是否公开："+ (iBean.getIsPublic() == 1?"公开":"不公开"));
		rPublic.addCarriageReturn();*/
		
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

			//XWPFRun r2 = p.createRun();
			//setFontFamily(r2);
			writeQuestionDetail(q, p);
		}
		return doc;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: writeQuestionDetail <BR>
	 * Description: 员工答卷详情-写入试题详情 <BR>
	 * Remark: <BR>
	 * @param q
	 * @param r2  void<BR>
	 */
	private static void writeQuestionDetail(QuestionnaireQuestionBean q, XWPFParagraph p) {
		List<String> aList = new ArrayList<String>();
		if(q.getType() == 1 || q.getType() == 2){
			if(q.getAnswer() != null){
				String[] ansArray = q.getAnswer().split(",");
				for(int i=0;i<ansArray.length;i++){
					aList.add(ansArray[i]);
				}
			}
		}
		XWPFRun r2 = null;
		switch (q.getType()) {
		case 1://单选题
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2 = p.createRun();
				setFontFamily(r2);
				r2.setText(String.format("%s、%s", optionName[oIndex], setNull(q.getOptions().get(oIndex).getContent())));
				if(aList.contains(q.getOptions().get(oIndex).getId().toString())){
					r2.setColor("FF0000");
				}
				r2.addCarriageReturn();
			}
			break;
		case 2://多选题
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2 = p.createRun();
				setFontFamily(r2);
				r2.setText(String.format("%s、%s", optionName[oIndex], setNull(q.getOptions().get(oIndex).getContent())));
				if(aList.contains(q.getOptions().get(oIndex).getId().toString())){
					r2.removeCarriageReturn();
					r2.setColor("FF0000");
				}else{
					r2.removeCarriageReturn();
					r2.setColor("000000");
				}
				r2.addCarriageReturn();
			}
			break;
		case 3://主观题
			if(q.getAnswer() != null){
				r2 = p.createRun();
				setFontFamily(r2);
				r2.setText(String.format("%s",q.getAnswer()));
				r2.addCarriageReturn();
			}
			break;
		case 4://评星题
			if(q.getAnswer() != null){
				r2 = p.createRun();
				setFontFamily(r2);
				r2.setText(String.format("%s 分",q.getAnswer()));
				r2.addCarriageReturn();
			}
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
	 * Method name: generateFileName <BR>
	 * Description: 生成word文件名 <BR>
	 * Remark: <BR>
	 * @param title
	 * @param userBean
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public String generateFileName(int assignId) throws Exception{
		String value = "暂无名称";
		if(assignId != 0){
			//String title = investigationAssignDaoMapper.getInvestigationName(assignId);
			InvestigationVoBean bean = investigationAssignDaoMapper.getById(assignId);
			if(bean != null){
				value = bean.getTitle();
				Integer userId = bean.getUserId();
				ManageUserBean userBean = manageUserService.findUserById(userId + "");
				if(userBean != null){
					value += "_" + userBean.getName();
				}
			}
		}
		return value;
	}

}
