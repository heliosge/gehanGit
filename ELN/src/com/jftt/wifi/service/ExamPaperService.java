/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: PaperService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/22        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.vo.PaperListItemVo;
import com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo;

/**
 * class name:PaperService <BR>
 * class description: 试卷Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/22
 * @author JFTT)wangyifeng
 */
public interface ExamPaperService {
	// wangyifeng begin

	/**
	 * Method name: addPaper <BR>
	 * Description: 增加试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param newPaper
	 *            void<BR>
	 */
	void addPaper(PaperBean newPaper);

	/**
	 * Method name: deletePapers <BR>
	 * Description: 逻辑删除指定ID列表对应的试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param paperIdList
	 *            void<BR>
	 */
	void deletePapers(List<Integer> paperIdList);

	/**
	 * Method name: modifyPaper <BR>
	 * Description: 修改试卷 <BR>
	 * Remark: <BR>
	 * 
	 * @param paper
	 *            void<BR>
	 */
	void modifyPaper(PaperBean paper);

	/**
	 * Method name: getPaper <BR>
	 * Description: 获取试卷 <BR>
	 * 关于打印： <BR>
	 * 打印功能是在UI端对试题显示样式的调整，<br/>
	 * 使用浏览器自带的打印功能，不使用特殊控件实现<br/>
	 * Remark: <BR>
	 * 
	 * @param paperId
	 * @return PaperBean<BR>
	 */
	PaperBean getPaper(Integer paperId);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getPaperQuestions <BR>
	 * Description: 获取试题列表：普通试卷时，返回固定试题列表；随机试卷时，根据规则随机生成试题列表 <BR>
	 * Remark: <BR>
	 * @param paperId
	 * @return  PaperBean<BR>
	 */
	List<PaperQuestionBean> getPaperQuestions(Integer paperId);

	/**
	 * Method name: getVoList <BR>
	 * Description: 获取试卷基本信息列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<PaperListItemVo><BR>
	 */
	List<PaperListItemVo> getVoList(PaperSearchOptionVo searchOption);

	/**
	 * Method name: getTotalCount <BR>
	 * Description: 获取试卷总数 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return Integer<BR>
	 */
	Integer getTotalCount(PaperSearchOptionVo searchOption);

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getPaperListForMMGrid <BR>
	 * Description: 获取用于列表页面的试卷列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  MMGridPageVoBean<PaperListItemVo><BR>
	 */
	MMGridPageVoBean<PaperListItemVo> getPaperListForMMGrid(
			PaperSearchOptionVo searchOption);

	/**
	 * Method name: exportToWord <BR>
	 * Description: 导出到Word <BR>
	 * Remark: <BR>
	 * 
	 * @param paperId
	 * @return XWPFDocument<BR>
	 */
	XWPFDocument exportToWord(Integer paperId);

	// wangyifeng end
	
	/**chenrui start*/
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPaperByCategory <BR>
	 * Description: 根据试卷分类获取试卷信息 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @param name
	 * @param userId
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	public List<PaperBean> getPaperByCategory(String categoryId, String name, String userId,long fromNum,String pageSize) throws Exception;
	int getPaperByCategoryCount(String categoryId, String name, String userId) throws Exception;
	/**chenrui end*/
	
	/**
	 * 获得随机试卷的规则
	 */
	List<ExamOrganizingRuleBean> getExamOrganizingRuleBeanByPaperId(Integer paperId);
}
