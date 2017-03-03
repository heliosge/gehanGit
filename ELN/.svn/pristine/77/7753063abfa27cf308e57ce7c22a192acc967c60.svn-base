/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015p
 * FileName: ExamPaperServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/05        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.enumtype.ExamConstants;
import com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo;
import com.jftt.wifi.bean.exam.vo.ExamOrganizingRuleVo;
import com.jftt.wifi.bean.exam.vo.PaperCategoryWithFullNameVo;
import com.jftt.wifi.bean.exam.vo.PaperListItemVo;
import com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo;
import com.jftt.wifi.dao.ExamOrganizingRuleDaoMapper;
import com.jftt.wifi.dao.ExamPaperCategoryDaoMapper;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.dao.ExamPaperQuestionDaoMapper;
import com.jftt.wifi.service.ExamPaperCategoryService;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.util.ExamPaperExportUtils;

/**
 * class name:ExamPaperServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/05
 * @author JFTT)wangyifeng
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperService {
	@Autowired
	private ExamPaperQuestionDaoMapper paperQuestionDaoMapper;
	@Autowired
	private ExamOrganizingRuleDaoMapper examOrganizingRuleDaoMapper;
	@Autowired
	private ExamPaperDaoMapper paperDaoMapper;
	@Autowired
	private ExamPaperCategoryDaoMapper paperCategoryDaoMapper;
	@Autowired
	private ExamPaperCategoryService paperCategoryService;
	@Autowired
	private ExamQuestionService questionService;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private MyExamManageService manageServiceImpl;

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperService#addPaper(com.jftt.wifi.bean.exam.PaperBean) <BR>
	 *      Method name: addPaper <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param newPaper
	 * <BR>
	 */

	@Override
	@Transactional
	public void addPaper(PaperBean newPaper) {
		updatePaperTotalScore(newPaper);
		paperDaoMapper.addPaper(newPaper);
		Integer paperId = newPaper.getId();

		Assert.notNull(paperId);
		if (newPaper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_NORMAL) {
			Assert.notEmpty(newPaper.getPaperQuestionList());
			for (int i = 0; i < newPaper.getPaperQuestionList().size(); i++) {
				PaperQuestionBean question = newPaper.getPaperQuestionList()
						.get(i);
				question.setPaperId(paperId);
				question.setOrderNum(i + 1);
				paperQuestionDaoMapper.addPaperQuestion(question);
			}
		} else if ((newPaper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_RANDOM)) {
			Assert.notEmpty(newPaper.getOrganizingRuleList());
			for (int i = 0; i < newPaper.getOrganizingRuleList().size(); i++) {
				ExamOrganizingRuleBean rule = newPaper.getOrganizingRuleList()
						.get(i);
				rule.setPaperId(paperId);
				rule.setOrderNum(i + 1);
				examOrganizingRuleDaoMapper.addExamOrganizingRule(rule);
			}
		}
	}

	/**
	 * Method name: updatePaperTotalScore <BR>
	 * Description: 根据试卷关联的试题集合，计算并设置试卷的总分 <BR>
	 * Remark: <BR>
	 * 
	 * @param newPaper
	 *            void<BR>
	 */
	@Transactional
	private void updatePaperTotalScore(PaperBean newPaper) {
		int totalScore = 0;
		if (newPaper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_NORMAL) {
			Assert.notEmpty(newPaper.getPaperQuestionList());
			for (PaperQuestionBean question : newPaper.getPaperQuestionList()) {
				Assert.notNull(question.getScore());
				totalScore += question.getScore();
			}
		} else if ((newPaper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_RANDOM)) {
			Assert.notEmpty(newPaper.getOrganizingRuleList());
			for (ExamOrganizingRuleBean rule : newPaper.getOrganizingRuleList()) {
				Assert.notNull(rule.getTotalScore());
				totalScore += rule.getTotalScore();
			}
		}
		newPaper.setTotalScore(totalScore);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperService#deletePapers(java.util.List) <BR>
	 *      Method name: deletePapers <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param paperIdList
	 * <BR>
	 */

	@Override
	@Transactional
	public void deletePapers(List<Integer> paperIdList) {
		if (paperIdList != null) {
			for (Integer id : paperIdList) {
				paperDaoMapper.deletePaper(id);
				// TODO wangyifeng
				// Delete or ignore paper question info.
				// Delete or ignore 自由组卷信息
			}
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperService#modifyPaper(com.jftt.wifi.bean.exam.PaperBean) <BR>
	 *      Method name: modifyPaper <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param paper
	 * <BR>
	 */

	@Override
	@Transactional
	public void modifyPaper(PaperBean paper) {
		if (paperDaoMapper.checkPaperReference(paper.getId())) {
			// disable old paper
			//paperDaoMapper.disablePaper(paper.getId());
			//删除老试卷
			paperDaoMapper.deletePaper(paper.getId());

			// add new paper
			// set creator info
			PaperBean oriPaper = paperDaoMapper.getPaper(paper.getId());
			paper.setCreateUserId(oriPaper.getCreateUserId());
			paper.setSubCompanyId(oriPaper.getSubCompanyId());
			paper.setCompanyId(oriPaper.getCompanyId());

			addPaper(paper);
		} else {
			realModifyPaper(paper);
		}
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: realModifyPaper <BR>
	 * Description: 实际更新试卷 <BR>
	 * Remark: <BR>
	 * @param paper  void<BR>
	 */
	@Transactional
	private void realModifyPaper(PaperBean paper) {
		updatePaperTotalScore(paper);

		Assert.notNull(paper.getId());
		paperDaoMapper.modifyPaper(paper);

		if (paper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_NORMAL) {

			Assert.notEmpty(paper.getPaperQuestionList());
			// 对于是否需要更新试题，可以进行优化：<br>
			// 记录试题是否被增删，只有在增删的情况下，需要如下操作<br>
			// 否则简单更新PaperQuestion里对应试题的分值即可<br>
			paperQuestionDaoMapper.realDeleteRecords(paper.getId());

			for (int i = 0; i < paper.getPaperQuestionList().size(); i++) {
				PaperQuestionBean question = paper.getPaperQuestionList()
						.get(i);
				question.setPaperId(paper.getId());
				question.setOrderNum(i + 1);
				paperQuestionDaoMapper.addPaperQuestion(question);
			}
		} else if (paper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_RANDOM) {
			Assert.notEmpty(paper.getOrganizingRuleList());
			examOrganizingRuleDaoMapper.realDeleteRecords(paper.getId());
			for (int i = 0; i < paper.getOrganizingRuleList().size(); i++) {
				ExamOrganizingRuleBean rule = paper.getOrganizingRuleList()
						.get(i);
				rule.setPaperId(paper.getId());
				rule.setOrderNum(i + 1);
				examOrganizingRuleDaoMapper.addExamOrganizingRule(rule);
			}
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperService#getPaper(java.lang.Integer) <BR>
	 *      Method name: getPaper <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param paperId
	 * @return <BR>
	 */

	@Override
	@Transactional
	public PaperBean getPaper(Integer paperId) {
		PaperBean paper = paperDaoMapper.getPaper(paperId);
		if (paper == null) {
			return null;
		}
		if (paper.getCategoryId() != null) {
			paper.setPaperCategory(paperCategoryService.getCategory(paper
					.getCategoryId()));
		}
		if (paper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_NORMAL) {
			List<PaperQuestionBean> paperQuestionList = paperQuestionDaoMapper
					.getPaperQuestionList(paperId);
			if (paperQuestionList != null && paperQuestionList.size() > 0) {
				paper.setPaperQuestionList(paperQuestionList);
				// 这里可以考虑增加questionService.getQuestionList接口来提升效率
				for (PaperQuestionBean paperQuestion : paperQuestionList) {
					paperQuestion.setQuestionBean(questionService
							.getQuestion(paperQuestion.getQuestionId()));
					// Calculate subQuestion score for group questions
					paperQuestion.calculateAndSetSubQuestionScoreForGroupQuestion();
				}
			}
		} else if (paper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_RANDOM) {
			List<ExamOrganizingRuleBean> ruleList = examOrganizingRuleDaoMapper
					.getRuleList(paperId);
			paper.setOrganizingRuleList(ruleList);
		}
		return paper;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperService#getVoList(com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo) <BR>
	 *      Method name: getVoList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	@Transactional
	public List<PaperListItemVo> getVoList(PaperSearchOptionVo searchOption) {
		Map<Integer, PaperCategoryWithFullNameVo> paperCategoryWithFullNameMap = paperCategoryService
				.getPaperCategoryWithFullNameMap(searchOption.getCompanyId());
		resolveFamilyTree(searchOption);
		List<PaperListItemVo> resultList = paperDaoMapper
				.getVoList(searchOption);
		for (PaperListItemVo q : resultList) {
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
	 * @see com.jftt.wifi.service.ExamPaperService#getTotalCount(com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo) <BR>
	 *      Method name: getTotalCount <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
	@Transactional
	public Integer getTotalCount(PaperSearchOptionVo searchOption) {
		resolveFamilyTree(searchOption);
		return paperDaoMapper.getTotalCount(searchOption);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamPaperService#exportToWord(java.lang.Integer) <BR>
	 *      Method name: exportToWord <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param paperId
	 * @return <BR>
	 */

	@Override
	@Transactional
	public XWPFDocument exportToWord(Integer paperId) {
		PaperBean paperBean = getPaper(paperId);
		return ExamPaperExportUtils.exportAsWord(paperBean);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamPaperService#getPaperQuestions(java.lang.Integer) <BR>
	 * Method name: getPaperQuestions <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param paperId
	 * @return  <BR>
	*/

	@Override
	@Transactional(readOnly = true)
	public List<PaperQuestionBean> getPaperQuestions(Integer paperId) {
		PaperBean paper = getPaper(paperId);
		final List<PaperQuestionBean> EMPTY_RESULT = new ArrayList<PaperQuestionBean>();
		if (paper == null) {
			return EMPTY_RESULT;
		}
		if (paper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_NORMAL) {
			return paper.getPaperQuestionList();
		} else if (paper.getOrganizingMode() == ExamConstants.PAPER_ORGANIZING_MODE_RANDOM) {
			List<PaperQuestionBean> result = new ArrayList<PaperQuestionBean>();
			List<AutoQuestionGroupVo> autoQuestionGroupList = new ArrayList<AutoQuestionGroupVo>();
			List<List<Integer>> listOfIdList = new ArrayList<List<Integer>>();
			List<Integer> allIdList = new ArrayList<Integer>();
			Map<Integer, QuestionBean> questionMap = new HashMap<Integer, QuestionBean>();
			for (ExamOrganizingRuleBean rule : paper.getOrganizingRuleList()) {
				AutoQuestionGroupVo autoQuestionGroup = rule
						.getAutoQuestionGroup();
				List<Integer> idList = questionService
						.autoGenerateQuestionIdList(autoQuestionGroup);
				allIdList.addAll(idList);
				autoQuestionGroupList.add(autoQuestionGroup);
				listOfIdList.add(idList);
			}
			List<QuestionBean> questionList = questionService
					.getQuestionList(allIdList);
			for (QuestionBean q : questionList) {
				questionMap.put(q.getId(), q);
			}
			for (int idx = 0; idx < autoQuestionGroupList.size(); idx++) {
				AutoQuestionGroupVo autoQuestionGroup = autoQuestionGroupList
						.get(idx);
				List<Integer> idList = listOfIdList.get(idx);
				for (int i = 0; i < idList.size(); i++) {
					QuestionBean q = questionMap.get(idList.get(i));
					PaperQuestionBean paperQuestion = new PaperQuestionBean(
							paper.getId(), q.getId(),
							autoQuestionGroup.getScoreByDifficultyLevel(q
									.getDifficultyLevel()), (i + 1), q);
					// Calculate subQuestion score for group questions
					paperQuestion
							.calculateAndSetSubQuestionScoreForGroupQuestion();

					result.add(paperQuestion);
				}
			}
			return result;
		}
		return EMPTY_RESULT;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: resolveFamilyTree <BR>
	 * Description: resolveFamilyTree <BR>
	 * Remark: <BR>
	 * @param searchOption  void<BR>
	 */
	@Transactional
	private void resolveFamilyTree(PaperSearchOptionVo searchOption) {
		if (searchOption.getCategoryIdListStr() == null) {
			searchOption.setCategoryIdListStr(paperCategoryDaoMapper
					.getCategoryFamilyTree(searchOption.getCategoryId(),
							searchOption.getSubCompanyId()));
			searchOption.setCategoryId(null);
		}
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamPaperService#getPaperListForMMGrid(com.jftt.wifi.bean.exam.vo.PaperSearchOptionVo) <BR>
	 * Method name: getPaperListForMMGrid <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/

	@Override
	@Transactional
	public MMGridPageVoBean<PaperListItemVo> getPaperListForMMGrid(
			PaperSearchOptionVo searchOption) {
		MMGridPageVoBean<PaperListItemVo> re = new MMGridPageVoBean<PaperListItemVo>();
		resolveFamilyTree(searchOption);
		re.setTotal(getTotalCount(searchOption));
		re.setRows(getVoList(searchOption));
		return re;
	}
	/**chenrui start*/
	
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.ExamPaperCategoryService#getPaperByCategory(java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getPaperByCategory <BR>
	 * Description: 根据试卷分类获取试卷信息  <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @param name
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional
	public List<PaperBean> getPaperByCategory(String categoryId, String name, String userId,long fromNum,String pageSize) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		Integer comId = userBean.getCompanyId();
		int subCompanyId = userBean.getSubCompanyId();
		List<Integer> categoryIdList = new ArrayList<Integer>();
		if(categoryId!=null && !"".equals(categoryId)){
			getAllChildNodes(Integer.parseInt(categoryId), categoryIdList);
		}
		List<PaperBean> papers = paperDaoMapper.getPaperByCategory(categoryIdList,name,comId,subCompanyId,fromNum,pageSize);
		for (PaperBean paperBean : papers) {
			int paperId = paperBean.getId();
			int organizingMode = paperBean.getOrganizingMode();
				if(organizingMode==2){//随机组卷 
					List<ExamOrganizingRuleVo> ruleVo = manageServiceImpl.getRuleList(paperId);
					for (ExamOrganizingRuleVo examOrganizingRuleVo : ruleVo) {
						int type = examOrganizingRuleVo.getType();
						switch (type) {
						case 1:
							paperBean.setCount1(examOrganizingRuleVo.getTypeCount());
							break;
						case 2:
							paperBean.setCount2(examOrganizingRuleVo.getTypeCount());
							break;
						case 3:
							paperBean.setCount3(examOrganizingRuleVo.getTypeCount());
							break;
						case 4:
							paperBean.setCount4(examOrganizingRuleVo.getTypeCount());
							break;
						case 5:
							paperBean.setCount5(examOrganizingRuleVo.getTypeCount());
							break;
						case 6:
							paperBean.setCount6(examOrganizingRuleVo.getTypeCount());
							break;
						case 7:
							paperBean.setCount7(examOrganizingRuleVo.getTypeCount());
							break;
							
						default:
							break;
						}
						
					}
				}
		}
		return papers;
	}
	@Override
	@Transactional
	public int getPaperByCategoryCount(String categoryId, String name, String userId) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		Integer comId = userBean.getCompanyId();
		int subCompanyId = userBean.getSubCompanyId();
		List<Integer> categoryIdList = new ArrayList<Integer>();
		if(categoryId!=null && !"".equals(categoryId)){
			getAllChildNodes(Integer.parseInt(categoryId), categoryIdList);
		}
		return paperDaoMapper.getPaperByCategoryCount(categoryIdList,name,comId,subCompanyId);
	}
	
	private List<Integer> getAllChildNodes(int categoryId,List<Integer> result) throws Exception{
		List<PaperCategoryBean> beans = paperCategoryDaoMapper.getCategoryByParentId(categoryId);
		result.add(categoryId);
		if(beans!=null && beans.size()>0){
			
			for (PaperCategoryBean paperCategoryBean : beans) {
				int id = paperCategoryBean.getId();
				getAllChildNodes(id, result);
			}
		}
		return result;
	}

	/**chenrui end*/
	
	/**
	 * 获得随机试卷的规则
	 */
	public List<ExamOrganizingRuleBean> getExamOrganizingRuleBeanByPaperId(Integer paperId){
		
		return examOrganizingRuleDaoMapper.getRuleList(paperId);
	}
}
