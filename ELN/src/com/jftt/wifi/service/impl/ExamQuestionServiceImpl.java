/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamQuestionServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/27        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.ExamWrongCardBean;
import com.jftt.wifi.bean.ExerciseQuestionBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.QuestionOptionBean;
import com.jftt.wifi.bean.exam.enumtype.QuestionDisplayType;
import com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo;
import com.jftt.wifi.bean.exam.vo.DifficultyLevelCountVo;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.QuestionCategoryWithFullNameVo;
import com.jftt.wifi.bean.exam.vo.QuestionExportVo;
import com.jftt.wifi.bean.exam.vo.QuestionListItemVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;
import com.jftt.wifi.common.IntegralConstant;
import com.jftt.wifi.dao.ExamQuestionCategoryDaoMapper;
import com.jftt.wifi.dao.ExamQuestionDaoMapper;
import com.jftt.wifi.dao.ExamQuestionOptionDaoMapper;
import com.jftt.wifi.dao.ExamUserAnswerDaoMapper;
import com.jftt.wifi.dao.ExamWrongCardDaoMapper;
import com.jftt.wifi.dao.ExerciseQuestionDaoMapper;
import com.jftt.wifi.service.ExamQuestionCategoryService;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.util.ConvertUtil;
import com.jftt.wifi.util.ExcelUtils;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:ExamQuestionServiceImpl <BR>
 * class description: 试题Service <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/23
 * @author JFTT)wangyifeng
 */
@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {

	private static final Logger LOG = Logger
			.getLogger(ExamQuestionServiceImpl.class);

	private static final String COMMA = ",";
	private static final boolean SHOULD_NOT_ADD_POINTS = false;
	private static final boolean SHOULD_ADD_POINTS = true;

	@Autowired
	private ExamQuestionDaoMapper questionDaoMapper;
	@Autowired
	private ExamQuestionOptionDaoMapper questionOptionDaoMapper;
	@Autowired
	private ExamQuestionCategoryService questionCategoryService;
	@Autowired
	private ExamQuestionCategoryDaoMapper questionCategoryDaoMapper;
	@Autowired
	private IntegralManageService integralManageService;
	@Resource(name="examQuestionDaoMapper")
	private ExamQuestionDaoMapper examQuestionDaoMapper;
	@Resource(name="exerciseQuestionDaoMapper")
	private ExerciseQuestionDaoMapper exerciseQuestionDaoMapper;
	@Resource(name="examUserAnswerDaoMapper")
	private ExamUserAnswerDaoMapper examUserAnswerDaoMapper;
	@Resource(name="examWrongCardDaoMapper")
	private ExamWrongCardDaoMapper examWrongCardDaoMapper;
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;

	// wangyifeng begin
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#getQuestionListItemVoList(com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo) <BR>
	 *      Method name: getQuestionListItemVoList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */

	@Override
    @Transactional(readOnly = true)
	public List<QuestionListItemVo> getQuestionListItemVoList(
			QuestionSearchOptionVo searchOption) {
		Map<Integer, QuestionCategoryWithFullNameVo> questionCategoryWithFullNameMap = questionCategoryService
				.getQuestionCategoryWithFullNameMap(searchOption.getCompanyId());
		resolveFamilyTree(searchOption);
		List<QuestionListItemVo> resultList = questionDaoMapper
				.getQuestionListItemVoList(searchOption);
		for (QuestionListItemVo q : resultList) {
			String categoryFullName = null;
			if (questionCategoryWithFullNameMap.containsKey(q
					.getQuestionCategoryId())) {
				categoryFullName = questionCategoryWithFullNameMap.get(
						q.getQuestionCategoryId()).getFullName();
			}
			q.setCategoryFullName(categoryFullName);
		}
		return resultList;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: resolveFamilyTree <BR>
	 * Description: resolveFamilyTree <BR>
	 * Remark: <BR>
	 * @param searchOption  void<BR>
	 */
	private void resolveFamilyTree(QuestionSearchOptionVo searchOption) {
		if (searchOption.getCategoryIdListStr() == null) {
			searchOption.setCategoryIdListStr(questionCategoryDaoMapper
					.getCategoryFamilyTree(searchOption.getCategoryId(),
							searchOption.getSubCompanyId()));
			searchOption.setCategoryId(null);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#getQuestionTotalCount(com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo) <BR>
	 *      Method name: getQuestionTotalCount <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param searchOption
	 * @return <BR>
	 */
	@Override
    @Transactional(readOnly = true)
	public Integer getQuestionTotalCount(QuestionSearchOptionVo searchOption) {
		resolveFamilyTree(searchOption);
		return questionDaoMapper.getQuestionTotalCount(searchOption);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamQuestionService#getQuestionListForMMGrid(com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo) <BR>
	 * Method name: getQuestionListForMMGrid <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  <BR>
	*/

	@Override
    @Transactional(readOnly = true)
	public MMGridPageVoBean<QuestionListItemVo> getQuestionListForMMGrid(
			QuestionSearchOptionVo searchOption) {
		MMGridPageVoBean<QuestionListItemVo> re = new MMGridPageVoBean<QuestionListItemVo>();
		resolveFamilyTree(searchOption);
		re.setTotal(getQuestionTotalCount(searchOption));
		re.setRows(getQuestionListItemVoList(searchOption));
		return re;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#addQuestion(com.jftt.wifi.bean.exam.QuestionBean) <BR>
	 *      Method name: addQuestion <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param newQuestion
	 * <BR>
	 */

	@Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
	public Integer addQuestion(QuestionBean newQuestion) {
		return addQuestion(newQuestion, SHOULD_ADD_POINTS);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: addQuestion <BR>
	 * Description: 新增问题 <BR>
	 * Remark: <BR>
	 * @param newQuestion 新问题
	 * @param needAddPoints 是否需要增加积分
	 * @return  Integer<BR>
	 */
	private Integer addQuestion(QuestionBean newQuestion, boolean needAddPoints) {
		if (newQuestion.getQuestionDisplayType() == QuestionDisplayType.GROUP) {
			addSubQuestions(newQuestion);
		}
		addQuestionWithOptions(newQuestion);
		if (needAddPoints) {
			try {
				Assert.notNull(newQuestion.getCreateUserId());
				integralManageService.handleIntegral(
						newQuestion.getCreateUserId(),
						IntegralConstant.Num_EXAM_ADD_QUESTION);
			} catch (Exception e) {
				LOG.warn(
						String.format(
								"Failed to add points for question id: %d, user id: %d.",
								newQuestion.getId(),
								newQuestion.getCreateUserId()), e);
				throw new RuntimeException(e);
			}
		}
		return newQuestion.getId();
	}

	/**
	 * Method name: addQuestionWithOptions <BR>
	 * Description: 新增试题及其选项 <BR>
	 * Remark: <BR>
	 * 
	 * @param newQuestion
	 *            void<BR>
	 */
	private void addQuestionWithOptions(QuestionBean newQuestion) {
		questionDaoMapper.addQuestion(newQuestion);
		for (int i = 0; i < newQuestion.getOptions().size(); i++) {
			QuestionOptionBean option = newQuestion.getOptions().get(i);
			option.setQuestionId(newQuestion.getId());
			option.setOrderNum(i + 1);
			questionOptionDaoMapper.addQuestionOption(option);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#modifyQuestion(com.jftt.wifi.bean.exam.QuestionBean) <BR>
	 *      Method name: modifyQuestion <BR>
	 *      Description: 修改试题 <BR>
	 *      Remark: <BR>
	 * @param question
	 * <BR>
	 */

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void modifyQuestion(QuestionBean question) {
		if (questionDaoMapper.checkQuestionReference(question.getId())) {
			// disable old question
			questionDaoMapper.disableQuestion(question.getId());

			// add new question
			// set creator info
			QuestionBean oriQuestion = questionDaoMapper.getQuestion(question
					.getId());
			question.setCreateUserId(oriQuestion.getCreateUserId());
			question.setSubCompanyId(oriQuestion.getSubCompanyId());
			question.setCompanyId(oriQuestion.getCompanyId());

			addQuestion(question, SHOULD_NOT_ADD_POINTS);
		} else {
			realModifyQuestion(question);
		}
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: realModifyQuestion <BR>
	 * Description: 实际更新试题 <BR>
	 * Remark: <BR>
	 * @param question  void<BR>
	 */
	private void realModifyQuestion(QuestionBean question) {
		if (question.getQuestionDisplayType() == QuestionDisplayType.GROUP) {
			// 物理删除组合题原来的子试题
			QuestionBean oriQuestion = getQuestion(question.getId());
			for (QuestionBean q : oriQuestion.getSubQuestions()) {
				realDeleteQuestion(q.getId());
			}

			// 保存组合题新的子试题
			addSubQuestions(question);
		}
		modifyQuestionWithOptions(question);
	}

	/**
	 * Method name: addSubQuestions <BR>
	 * Description: 增加子试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param question
	 *            void<BR>
	 */
	private void addSubQuestions(QuestionBean question) {
		List<Integer> subQuestionIdList = new ArrayList<Integer>();
		for (QuestionBean subQuestion : question.getSubQuestions()) {
			addQuestionWithOptions(subQuestion);
			subQuestionIdList.add(subQuestion.getId());
		}
		question.setQuestionIdList(StringUtils.join(subQuestionIdList, ",")
				.toString());
	}

	/**
	 * Method name: modifyQuestionWithOptions <BR>
	 * Description: 修改单个试题及选项 <BR>
	 * Remark: <BR>
	 * 
	 * @param question
	 *            void<BR>
	 */
	private void modifyQuestionWithOptions(QuestionBean question) {
		questionOptionDaoMapper.deleteQuestionOptions(question.getId());
		questionDaoMapper.modifyQuestion(question);
		for (int i = 0; i < question.getOptions().size(); i++) {
			QuestionOptionBean option = question.getOptions().get(i);
			option.setQuestionId(question.getId());
			option.setOrderNum(i + 1);
			questionOptionDaoMapper.addQuestionOption(option);
		}
	}

	/**
	 * Method name: realDeleteQuestion <BR>
	 * Description: 物理删除试题及其选项 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 *            void<BR>
	 */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
	public void realDeleteQuestion(Integer questionId) {
		questionDaoMapper.realDeleteQuestion(questionId);
		questionOptionDaoMapper.deleteQuestionOptions(questionId);
	}

	/**
	 * Method name: deleteQuestion <BR>
	 * Description: 逻辑删除试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 *            void<BR>
	 */
	private void deleteQuestion(Integer questionId) {
		questionDaoMapper.deleteQuestion(questionId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#deleteQuestions(java.util.List) <BR>
	 *      Method name: deleteQuestions <BR>
	 *      Description: 删除试题集合 <BR>
	 *      Remark: <BR>
	 * @param questionIdList
	 * <BR>
	 */

	@Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteQuestions(List<Integer> questionIdList) {
		Assert.notNull(questionIdList);
		Assert.notEmpty(questionIdList);
		for (Integer questionId : questionIdList) {
			deleteQuestion(questionId);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#getQuestion(java.lang.Integer) <BR>
	 *      Method name: getQuestion <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param questionId
	 * @return <BR>
	 */

	@Override
    @Transactional(readOnly = true)
	public QuestionBean getQuestion(Integer questionId) {
		QuestionBean result = getSingleQuestion(questionId);

		if (result != null
				&& result.getQuestionDisplayType() == QuestionDisplayType.GROUP) {
			if (result.getQuestionIdList() != null) {
				String[] idList = StringUtils.split(result.getQuestionIdList(),
						COMMA);
				List<QuestionBean> subQuestions = new ArrayList<QuestionBean>();
				for (String id : idList) {
					subQuestions.add(getSingleQuestion(Integer.parseInt(id)));
				}
				result.setSubQuestions(subQuestions);
			}
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionBean> getQuestionList(List<Integer> questionIdList) {
		List<QuestionBean> resultList = new ArrayList<QuestionBean>();
		if (questionIdList != null && questionIdList.size() > 0) {
			Map<Integer, QuestionBean> allQuestionMap = new HashMap<Integer, QuestionBean>();
			Map<Integer, List<Integer>> groupQuestionMap = new HashMap<Integer, List<Integer>>();
			String questionIdListStr = StringUtils.join(questionIdList, ",");
			List<QuestionBean> basicQuestionList = questionDaoMapper
					.getQuestionList(questionIdListStr);
			List<Integer> subQuestionIdList = new ArrayList<Integer>();
			for (QuestionBean q : basicQuestionList) {
				allQuestionMap.put(q.getId(), q);
				if (q.getQuestionDisplayType() == QuestionDisplayType.GROUP) {
					if (q.getQuestionIdList() != null) {
						String[] idStrList = StringUtils.split(
								q.getQuestionIdList(), COMMA);
						List<Integer> idList = new ArrayList<Integer>();
						for (String id : idStrList) {
							idList.add(Integer.parseInt(id));
						}
						subQuestionIdList.addAll(idList);
						groupQuestionMap.put(q.getId(), idList);
					}
				}
			}
			List<QuestionBean> subQuestionList = new ArrayList<QuestionBean>();
			if (subQuestionIdList.size() > 0) {
				String subQuestionIdListStr = StringUtils.join(
						subQuestionIdList, ",");
				subQuestionList = questionDaoMapper
						.getQuestionList(subQuestionIdListStr);
				for (QuestionBean q : subQuestionList) {
					allQuestionMap.put(q.getId(), q);
				}
			}
			List<Integer> allQuestionIdList = new ArrayList<Integer>(
					questionIdList);
			allQuestionIdList.addAll(subQuestionIdList);
			String allQuestionIdListStr = StringUtils.join(allQuestionIdList,
					",");
			List<QuestionOptionBean> allOptionList = questionOptionDaoMapper
					.getOptionList(allQuestionIdListStr);

			// 已经在QuestionBean给options和subQuestions赋值了空数组
			// for (QuestionBean q : allQuestionMap.values()) {
			// q.setOptions(new ArrayList<QuestionOptionBean>());
			// }
			// for (Integer id : groupQuestionMap.keySet()) {
			// allQuestionMap.get(id).setSubQuestions(new
			// ArrayList<QuestionBean>());
			// }

			for (QuestionOptionBean o : allOptionList) {
				QuestionBean q = allQuestionMap.get(o.getQuestionId());
				q.getOptions().add(o);
			}

			for (Integer groupQuestionId : groupQuestionMap.keySet()) {
				QuestionBean q = allQuestionMap.get(groupQuestionId);
				for (Integer subQuestionId : groupQuestionMap
						.get(groupQuestionId)) {
					q.getSubQuestions().add(allQuestionMap.get(subQuestionId));
				}
			}

			resultList = basicQuestionList;
		}
		return resultList;
	}

	/**
	 * Method name: getSingleQuestion <BR>
	 * Description: getSingleQuestion <BR>
	 * Remark: <BR>
	 * 
	 * @param questionId
	 * @return QuestionBean<BR>
	 */
	private QuestionBean getSingleQuestion(Integer questionId) {
		List<QuestionOptionBean> options = questionOptionDaoMapper
				.selectOptionsByQuestionId(questionId);
		QuestionBean result = questionDaoMapper.getQuestion(questionId);
		if (result != null) {
			result.setOptions(options);
		}
		return result;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#getDifficultyLevelCountList(com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo) <BR>
	 *      Method name: getDifficultyLevelCountList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param autoQuestionGroup
	 * @return <BR>
	 */

	@Override
    @Transactional(readOnly = true)
	public List<DifficultyLevelCountVo> getDifficultyLevelCountList(
			AutoQuestionGroupVo autoQuestionGroup) {
		resolveFamilyTree(autoQuestionGroup);
		return questionDaoMapper.getDifficultyLevelCountList(autoQuestionGroup);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: resolveFamilyTree <BR>
	 * Description: resolveFamilyTree <BR>
	 * Remark: <BR>
	 * @param searchOption  void<BR>
	 */
	private void resolveFamilyTree(AutoQuestionGroupVo searchOption) {
		if (searchOption.getCategoryIdListStr() == null) {
			searchOption.setCategoryIdListStr(questionCategoryDaoMapper
					.getCategoryFamilyTree(searchOption.getCategoryId(), null));
			searchOption.setCategoryId(null);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ExamQuestionService#autoGenerateQuestionIdList(com.jftt.wifi.bean.exam.vo.AutoQuestionGroupVo) <BR>
	 *      Method name: autoGenerateQuestionIdList <BR>
	 *      Description: please write your description <BR>
	 *      Remark: <BR>
	 * @param autoQuestionGroup
	 * @return <BR>
	 */

	@Override
    @Transactional(readOnly = true)
	public List<Integer> autoGenerateQuestionIdList(
			AutoQuestionGroupVo autoQuestionGroup) {
		// may fail if question count modified by other users
		// return [] for fail
		resolveFamilyTree(autoQuestionGroup);
		List<Integer> resultList = new ArrayList<Integer>();
		for (DifficultyLevelCountVo difficultyLevelCount : autoQuestionGroup
				.getDifficultyCountList()) {
			autoQuestionGroup.setCurDifficultyLevel(difficultyLevelCount
					.getDifficultyLevel());
			List<Integer> beforeFilterIdList = questionDaoMapper
					.getQuestionIdListForAutoGroup(autoQuestionGroup);
			LOG.debug("beforeFilterIdList: ");
			LOG.debug(beforeFilterIdList);
			resultList.addAll(autoSelectQuestions(beforeFilterIdList,
					difficultyLevelCount.getCount()));
		}
		return resultList;
	}

	/**
	 * Method name: autoSelectQuestions <BR>
	 * Description: 根据原始ID列表，随机选出指定书目的ID <BR>
	 * Remark: <BR>
	 * 
	 * @param beforeFilterIdList
	 * @param requireCount
	 * @return List<Integer><BR>
	 */
	private List<Integer> autoSelectQuestions(List<Integer> beforeFilterIdList,
			Integer requireCount) {
		Assert.notNull(requireCount);
		Assert.isTrue(
				requireCount <= beforeFilterIdList.size(),
				String.format(
						"auto group function: requireCount(%d) should be less than or equal to totalCount(%d).",
						requireCount, beforeFilterIdList.size()));
		List<Integer> indexList = autoSelectIndexList(requireCount,
				beforeFilterIdList.size());
		List<Integer> resultIdList = new ArrayList<Integer>();
		for (Integer idx : indexList) {
			resultIdList.add(beforeFilterIdList.get(idx));
		}
		return resultIdList;
	}

	/**
	 * Method name: autoSelectIndexList <BR>
	 * Description: 随机选择指定数目的索引列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param requireCount
	 * @param totalCount
	 * @return List<Integer><BR>
	 */
	private List<Integer> autoSelectIndexList(int requireCount, int totalCount) {
		List<Integer> idxList = new ArrayList<Integer>();
		for (int i = 0; i < totalCount; i++) {
			idxList.add(i);
		}
		List<Integer> resultIdxList = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = totalCount; i > totalCount - requireCount; i--) {
			resultIdxList.add(idxList.remove(random.nextInt(i)));
		}
		LOG.debug(String.format("autoSelectIndexList(%d, %d): %s",
				requireCount, totalCount, resultIdxList));
		return resultIdxList;
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.ExamQuestionService#uploadFile(org.springframework.web.multipart.MultipartFile) <BR>
	 * Method name: uploadFile <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param imgFile
	 * @return
	 * @throws IOException  <BR>
	*/
	@Override
	public String uploadFile(MultipartFile imgFile) throws IOException {
		// 获取文件储存的地址。
		String pageFileName = imgFile.getOriginalFilename();
		Assert.hasText(pageFileName);
		// 获取文件后缀名
		String fileExt = pageFileName.substring(pageFileName.lastIndexOf("."));
		Assert.hasText(fileExt);

		String basePath = PropertyUtil.getConfigureProperties("UPLOAD_PATH");// 拿到实际存放地址。

		// 获取拼接地址
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extendUrl = "exam/" + df.format(new Date());

		File saveFolder = new File(basePath + extendUrl);
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}

		// 获取文件的新的名称。使用UUID作为新文件名
		String relativeFilePath = extendUrl + "/"
				+ UUID.randomUUID().toString() + fileExt;
		String physicalFilePath = basePath + relativeFilePath;

		// 将上传的文件写到new出来的文件中
		FileUtil.SaveFileFromInputStream(imgFile.getInputStream(),
				physicalFilePath);

		if (ConvertUtil.needConvertToFlv(fileExt)) {
			relativeFilePath = ConvertUtil.convertToFlv(relativeFilePath);
		}

		return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH") + "/"
				+ relativeFilePath;
	}
	
	// wangyifeng end
	
	/**zhangbocheng start*/
	
	
	/**
	 * Method name: getCategoryMap <BR>
	 * Description: 获取所有试题分类的map <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 */
	public Map<String,Integer> getCategoryMap(Integer companyId,Integer subCompanyId){
		QuestionCategorySearchOptionVo vo = new QuestionCategorySearchOptionVo();
		vo.setCompanyId(companyId);
		vo.setSubCompanyId(subCompanyId);
		List<QuestionCategoryBean> list=questionCategoryDaoMapper.getQuestionCategoryList(vo);
		
		if(list==null||list.size()==0){
			return null;
		}
		Map<String,Integer> map =new HashMap<String,Integer>();
		for(QuestionCategoryBean bean:list){
			map.put(bean.getName(), bean.getId());
		}
		return map;
	}
	
	
	/**
	 * Method name: wordImport <BR>
	 * Description: word导入试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param filePath
	 * @return List<Integer><BR>
	 */
	@Override
	public  Map<String,Object> wordImport(String filePath,Integer companyId,Integer subCompanyId,Integer userId,Integer categoryId,Integer difficultId){
		
		Map<String,Object> dbObject =new HashMap<String,Object>();
		List<Integer> questionIdList = new ArrayList<Integer>();
		 StringBuilder sb = new StringBuilder();
        if (filePath.length() == 0) {
            dbObject.put("status", 2);
            LOG.error("文件不存在");
            return dbObject;
        }

        //String fileName = FileUtil.getFileName(filePath, true);
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
	            //读取文字
	            Range range = hwpf.getRange();// 得到文档的读取范围
                //所有图片
	            PicturesTable pTable = hwpf.getPicturesTable();
  
            QuestionBean question =null ;
            Map<String,String> optionMap = new LinkedHashMap<String,String>();
            String [] answerArr =null;
            List<String> picList = new ArrayList<String>();
            for(int k=0;k<range.numParagraphs();k++){
              
          	  Paragraph paragraph = range.getParagraph(k);
                String s = paragraph.text();
                //判读是否匹配题干规则
                s=s.replace((char)12288, ' ').replace((char)160, ' ').trim().replaceAll("\t|\r|\n", "");
                if(s.matches("^\\d*(\\、|\\.)[\\w\\W]*(.|。)\\s*(\\(|（)[\\w\\W]+(\\)|）)[\\s]*$")){
                	//判断上一题是否存在
              	  if(question!=null&&question.getType()!=null&&question.getContent()!=null){
              		  //question  保存
              		  saveQuestionFromWord(question,answerArr,optionMap,categoryId,difficultId,companyId,subCompanyId,userId,sb); 
              		  if(question.getId()!=null){
                  		  questionIdList.add(question.getId());
              		  }
              		  question=new QuestionBean();
              		  optionMap.clear();
  				      answerArr=null;
  				      picList.clear();
              	  }else{
              		  question = new QuestionBean();//创建新的试题对象
              	  }
              	  Integer typeId= checkType(s); //获得试题类型
              	  LOG.info(s+ typeId+"：试题类型");
              	  if(typeId==null||typeId<1){
              		LOG.error(s+ "：不能得到具体的试题类型");
              		sb.append(s+ "：不能得到具体的试题类型\r\n");
              		question=null;
              		continue; 
              	  }
              	  question.setType(typeId);
              	  answerArr= getAnswer(s,typeId);//获得试题答案
              
              	  if(answerArr==null||answerArr.length==0){
              		LOG.error(s+ "：没有答案");
              		sb.append(s+ "：没有答案\r\n");
              		question=null;
              		continue; 
              	  }
            	 
              	  if(typeId==1){
              		question.setAnswerKeys(answerArr[0].replaceAll("(,|，)", " "));//主观题保存关键字
              	  }else if(typeId==4){
              		  //判断题保存是否正确
              		 if ("正确".equals(answerArr[0])) {
                         question.setIsTrue(true);
                     }
                     else{
                   	  question.setIsTrue(false);
                     }
              	  }else if(typeId==5){
              		  //填空题保存答案
              		
              		Integer count =getStrCount(s,"(\\(|（)\\s*(\\)|）)");
              		
            		if(count==null||count<=0){
            			LOG.error(s+ "：填空题题干内()数小于1");
                  		sb.append(s+ "：填空题题干内()数小于1\r\n");
            			question=null;
                  		continue;
            		}else if(count!=answerArr.length){
            			LOG.error(s+ "：填空题题干内()数与答案数不等");
                  		sb.append(s+ "：填空题题干内()数与答案数不等\r\n");
            			question=null;
            			
                  		continue;
            		}
            		List<QuestionOptionBean> optionList = new ArrayList<QuestionOptionBean>();
            		String keyStr="";
            		for(int ii=0;ii<answerArr.length;ii++){
            			String answer=answerArr[ii];
            			QuestionOptionBean option= new QuestionOptionBean();
            			option.setContent(answer);
            			option.setIsAnswer(true);
            			option.setOrderNum(ii+1);
            			optionList.add(option);
            			keyStr+=answer+" ";
            		}
            		question.setOptions(optionList);
            		question.setAnswerKeys(keyStr.trim());
              	  }
              	  //判断是否存在图片
              	  picList = new ArrayList<String>();
              	  String picPath = savePicture(paragraph,pTable,1,picList);
              	 
              	  //图片以附件方式保存
              	 
              	  if(picPath!=null&&picPath.length()>0){
              		  question.setIsMultimedia(true);
              		  question.setMultimediaType(1);
                      //question.setMultimediaUrl(picPath);
              	  }
              	  String content=getContent(s,picList);//获得题干
              	  question.setContent(content);
                }else{
                	//不匹配题干规则，判断试题是否存在
              	  if(question!=null&&question.getType()!=null&&question.getContent()!=null){
              		  switch(question.getType())
                  	  {
                  	     case 1:
                  	    	 if(s.matches("^\\s*(\\(|（)解析(:|：)[\\w\\W]*(\\)|）)\\s*$")){
                  	    		 s=s.replaceFirst("^\\s*(\\(|（)\\s*解析\\s*(:|：)", "").replaceFirst("(\\)|）)", "");
                  	    		 question.setAnalysis(s);
                  	    	 }
                  		    break;
                  	     case 2:
                  	    	 if(s.matches("^\\s*[A-Z]\\s*(.|、)[\\w\\W]*$")){
                  	    	          boolean isHaveText =checkOption(s);
                  	    	    	String picPath = savePicture(paragraph,pTable,0,null);
                                	  if(picPath!=null&&picPath.length()>0){
                                		  if(isHaveText){
                                			  LOG.error(question.getContent()+ "：试题选项既有文字又有图片");
                                              sb.append(question.getContent()+ "：试题选项既有文字又有图片\r\n");
                                              question=null;
                                        		continue; 
                                		  }else{

                                    		  question.setIsMultimedia(true);
                                              getOption(s,optionMap,picPath);
                                		  }
                                	  }else{
                                		  getOption(s,optionMap,null);
                                	  }	
                            		
                            	  
                  	    		
                  	    	 }
                  	    	 break;
                  	     case 3:
                  	    	if(s.matches("^\\s*[A-Z]\\s*(.|、)[\\w\\W]*$")){

                  	          boolean isHaveText =checkOption(s);
            	    	    	String picPath = savePicture(paragraph,pTable,0,null);
                          	  if(picPath!=null&&picPath.length()>0){
                          		  if(isHaveText){
                          			  LOG.error(question.getContent()+ "：试题选项既有文字又有图片");
                                        sb.append(question.getContent()+ "：试题选项既有文字又有图片\r\n");
                                        question=null;
                                  		continue; 
                          		  }else{

                              		  question.setIsMultimedia(true);
                                        getOption(s,optionMap,picPath);
                          		  }
                          	  }else{
                          		  getOption(s,optionMap,null);
                          	  }	
                      		  }
                  	    	 break;
                  	     default:
                  	    	 break;
                  	  }
              	
              	  }else{
              		  continue;
              	  }
                }
                //读到最后，保存试题
                if(k==(range.numParagraphs()-1)){
                	if(question!=null&&question.getType()!=null&&question.getContent()!=null){
                		  //question  保存
                		  saveQuestionFromWord(question,answerArr,optionMap,categoryId,difficultId,companyId,subCompanyId,userId,sb);
                		  if(question.getId()!=null){
                      		  questionIdList.add(question.getId());
                  		  }
                	}
                }
        }


                
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        dbObject.put("list", questionIdList);
        if(sb.length()>0){
            String errorFilePath=saveErrorFile(sb.toString()); //将错误信息写入文件
            dbObject.put("errorFilePath", errorFilePath);
        }
        dbObject.put("status", 0);
        return dbObject;
	}
	
	//保存试题
	private QuestionBean saveQuestionFromWord(QuestionBean question,String [] answerArr,Map<String,String> optionMap,Integer categoryId
			,Integer difficultId,Integer companyId,Integer subCompanyId,Integer userId,StringBuilder sb){
		
		 question.setCategoryId(categoryId);
         question.setDifficultyLevel(difficultId);
         question.setCompanyId(companyId);
         question.setSubCompanyId(subCompanyId);
         question.setIsDeleted(false);
         question.setCreateUserId(userId);
         question.setIsEnabled(true);
         question.setIsSubQuestion(false);
         if(question.getIsMultimedia()==null){
        	 question.setIsMultimedia(false);
         }
 		  if(question.getType()==2){
 			  if(answerArr.length==1&&optionMap.size()>=2){
 				String answer = optionMap.get(answerArr[0]);
   			  if(answer!=null&&answer.length()>0){
   				List<QuestionOptionBean> optionList = new ArrayList<QuestionOptionBean>();
         			int i=1;
         			for(Map.Entry<String,String> entry:optionMap.entrySet()){
         				QuestionOptionBean option= new QuestionOptionBean();
         				String optionStr=entry.getValue();
         		   if(optionStr.startsWith("")){
           				 option.setType(2);
           				 optionStr=optionStr.substring(1);
           			 }else{
                 			 option.setType(1);
           			 }
           			option.setContent(optionStr);
           			option.setIsAnswer(answerArr[0].equals(entry.getKey()));
           			option.setOrderNum(i);
           			optionList.add(option);
           			i++;
         			}

         			question.setOptions(optionList);
         			addQuestion(question);
   			  }else{
   				LOG.error(question.getContent()+ "：单选题答案与选项不匹配");
   				sb.append(question.getContent()+ "：单选题答案与选项不匹配\r\n");
   			  }
   			
 			  }else{
       	    		LOG.error(question.getContent()+ "：单选题答案数大于1或选项数小于2");
       	    		sb.append(question.getContent()+ "：单选题答案数大于1或选项数小于2\r\n");
 			  }
 			  
 		  }else if(question.getType()==3){
 			
 			 if(answerArr.length>1&&optionMap.size()>=2&&answerArr.length<=optionMap.size()){
 				List<QuestionOptionBean> optionList = new ArrayList<QuestionOptionBean>();
    			int i=1;
    			int rightAnswer=0;
    			for(Map.Entry<String,String> entry:optionMap.entrySet()){
    				QuestionOptionBean option= new QuestionOptionBean();
    				for(String answer:answerArr){
    					if(entry.getKey().equals(answer)){
    						
    						rightAnswer+=1;
    						option.setIsAnswer(true);
    						break;
    					}else{
    						option.setIsAnswer(false);
    					}
    				}
    				
    			 String optionStr=entry.getValue();
    			 if(optionStr.startsWith("")){
    				 option.setType(2);
    				 optionStr=optionStr.substring(1);
    			 }else{
          			 option.setType(1);
    			 }
      			 option.setContent(optionStr);
      			 option.setOrderNum(i);
      			 optionList.add(option);
      			 i++;
    			}
    			question.setOptions(optionList);
    			if(rightAnswer==answerArr.length){
    				addQuestion(question);
    			}else{
    				LOG.error(question.getContent()+ "：多选题答案与选项不匹配");
    				sb.append(question.getContent()+ "：多选题答案与选项不匹配\r\n");
    			}
			  }else{
				  LOG.error(question.getContent()+ "：多选题答案数小于2或选项数小于2或答案数大于选项数");
				  sb.append(question.getContent()+ "：多选题答案数小于2或选项数小于2或答案数大于选项数\r\n");
			  }
			
 		   }else if(question.getType()==1){
 			   if(question.getOptions()==null||question.getOptions().size()==0){
 				  List<QuestionOptionBean> optionList = new ArrayList<QuestionOptionBean>();
 				  QuestionOptionBean option= new QuestionOptionBean();
 				  option.setContent(question.getAnswerKeys());
 				  option.setIsAnswer(true);
      			  option.setOrderNum(1);
      		      optionList.add(option);
      		      question.setOptions(optionList);
 			   }
 			  addQuestion(question);
 		   }
 		  else{
 			 addQuestion(question);
 		   }
 		  
 		  return question;
	}
	
	/**
	 * Method name: excelImport <BR>
	 * Description: excel导入试题 <BR>
	 * Remark: <BR>
	 * 
	 * @param filePath
	 * @return Map<String,Object><BR>
	 */
	@Override
	public  Map<String,Object> excelImport(String filePath, Integer companyId,Integer subCompanyId,Integer userId,Integer categoryId){
		Map<String,Object> dbObject =new HashMap<String,Object>();
		List<Integer> idList = new ArrayList<Integer>();
		
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

        Long  size = FileUtil.getSize(file);
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
            mapType.put("主观题", 1);
            mapType.put("单选题", 2);
            mapType.put("多选题", 3);
            mapType.put("判断题", 4);
            mapType.put("填空题", 5);
            Map<String, Integer> mapMultimedia = new HashMap<String, Integer>();
            mapMultimedia.put("是", 1);
            mapMultimedia.put("否", 0);
            Map<String, Integer> mapDifficult = new HashMap<String, Integer>();
            mapDifficult.put("易", 1);
            mapDifficult.put("中", 2);
            mapDifficult.put("难", 3);
  

            //取得表格第一标题行
            Row titleRow = sheet.getRow(0);
            //题干
            String cell_0 = ExcelUtils.getStringValue(titleRow.getCell(0));
            //题库
            //String cell_1 = ExcelUtils.getStringValue(titleRow.getCell(1));
            //题型
            String cell_1 = ExcelUtils.getStringValue(titleRow.getCell(1));
            //难度
            String cell_2 = ExcelUtils.getStringValue(titleRow.getCell(2));
            //试题解析
            String cell_3 = ExcelUtils.getStringValue(titleRow.getCell(3));
            //选项内容
            String cell_4 = ExcelUtils.getStringValue(titleRow.getCell(4));
            //答案
            String cell_5 = ExcelUtils.getStringValue(titleRow.getCell(5));
             //关键字
            String cell_6 = ExcelUtils.getStringValue(titleRow.getCell(6));
            if (titleRow.getLastCellNum() != 7
                    || !cell_0.startsWith("\u9898\u5E72")                        //题干
                    //|| !"\u8BD5\u9898\u5E93".equals(cell_1)           //题库
                    || !"\u9898\u578B".equals(cell_1)                             //题型
                    || !"\u96BE\u5EA6".equals(cell_2)                             //难度
                    || !"\u8BD5\u9898\u89E3\u6790".equals(cell_3)         //试题解析
                    || !cell_4.startsWith("\u9009\u9879\u5185\u5BB9")  //选项内容
                    || !cell_5.startsWith("\u7B54\u6848")|| !cell_6.startsWith("\u5173\u952E\u5B57")) {                  //答案
                dbObject.put("status", 5);
                return dbObject;
            }

            //Map<String,Integer> categoryMap =getCategoryMap(companyId, subCompanyId);
            for (int i = firstRow + 1; i <= lastRow; i++) {
                //取得表格中行
                Row row = sheet.getRow(i);
                if (row == null) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因为空\r\n");
                   
                    continue;
                }

                Cell nameCell = row.getCell(0);   //题干
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

                /**
                //验证题干是否重复
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", name);
                map.put("status", 0);
                if (ExamQuestionDaoMapper.getCount("question", map) > 0) {
                    sb.append("第" + i + "条:导入失败，原因题干重复<br />");
                    fail++;
                    continue;
                }
                */

               
                /**
                Cell categoryCell = row.getCell(1);   //
                String categoryName = ExcelUtils.getStringValue(categoryCell);
                if (categoryCell == null) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因试题分类为空<br />");
                    continue;
                }

                if (isBlank(categoryName)) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因试题分类为空<br />");
                    continue;
                }
               
                //Integer categoryId = categoryMap.get(categoryName);
                if(categoryId==null){
                	 fail++;
                     sb.append("第" + i + "条:导入失败，原因试题分类不存在\n");
                     continue;
                }
                  */
                Cell typeCell = row.getCell(1);   //题型
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
                if (!(typeName.equals("单选题") || typeName.equals("多选题") || typeName.equals("判断题") 
                		|| typeName.equals("主观题")|| typeName.equals("填空题"))) {
                    sb.append("第" + i + "条:导入失败，原因题型填写错误\r\n");
                    fail++;
                }

                Integer typeId = mapType.get(typeName);

                Cell difficultCell = row.getCell(2);   //难度
                String difficultName = ExcelUtils.getStringValue(difficultCell);
                if (difficultCell == null) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因难度为空\r\n");
                    continue;
                }

                if (isBlank(difficultName)) {
                    fail++;
                    sb.append("第" + i + "条:导入失败，原因难度为空\r\n");
                    continue;
                }

                Integer difficultId = mapDifficult.get(difficultName);

                Cell desCell = row.getCell(3);   //解析
               
                String des = ExcelUtils.getStringValue(desCell);
              
           
                Cell valueCell = row.getCell(4);   //选项内容按 “|” 拆分
                String value = ExcelUtils.getStringValue(valueCell);

                String[] valueArr = null;
                if (value.indexOf("|") > 0) {
                    valueArr = value.split("\\|");
                } else{
                    valueArr = new String[]{value};
                }

                Cell rightCell = row.getCell(5);   //选项答案  “|” 拆分
                String right = ExcelUtils.getStringValue(rightCell);

                String[] rightArr = null;
               
                if (typeId == 5||typeId==3) {
                    if (right.indexOf("|") > 0) {
                        rightArr = right.split("\\|");
                    } else {
                        rightArr = new String[]{right};
                    }
                }
                else {
                    rightArr = new String[]{right};
                }
                switch (typeId) {
             
                case 2:  //单选题
                    if (valueArr.length < 2 || rightArr.length != 1 || isEmpty(right)) {
                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
                        fail++;
                        continue;
                    }
                    int res = isExit(valueArr, rightArr);
                    if (res == 0) {
                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
                        fail++;
                        continue;
                    }
                    if (res == 1) {
                        sb.append("第" + i + "条:导入失败，原因答案不符合\r\n");
                        fail++;
                        continue;
                    }
                    break;
                case 3:  //多选题
                    if (valueArr.length < 2 ||  rightArr.length < 0 || valueArr.length < rightArr.length || isEmpty(right)) {
                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
                        fail++;
                        continue;
                    }
                    int _res = isExit(valueArr, rightArr);
                    if (_res == 0) {
                        sb.append("第" + i + "条:导入失败，原因选项内容不符合\r\n");
                        fail++;
                        continue;
                    }
                    if (_res == 1) {
                        sb.append("第" + i + "条:导入失败，原因答案不符合\r\n");
                        fail++;
                        continue;
                    }
                    break;
                case 4:  //判断题
                    if (isEmpty(right)) {
                        sb.append("第" + i + "条:导入失败，原因答案不符合\r\n");
                        fail++;
                        continue;
                    }
                    if (!("正确".equals(right)) && !("错误".equals(right))) {
                        sb.append("第" + i + "条:导入失败，原因答案不符合\r\n");
                        fail++;
                        continue;
                    }

                    break;
                 case 5://填空题
                	 Integer count =getStrCount(name,"(\\(|（)\\s*(\\)|）)");
                	 if(count==null||count<=0){
                		 sb.append("第" + i + "条:导入失败，题干错误\r\n");
                         fail++;
                         continue;
                	}else{
                		 if(count!=rightArr.length){
                			sb.append("第" + i + "条:导入失败，答案数目错误\r\n");
                            fail++;
                            continue;
                		}
                	}
            }

                
                Cell keysCell = row.getCell(6);   //关键字
                String keys = ExcelUtils.getStringValue(keysCell);
//                if(typeId==1&&isBlank(keys)){
//                	sb.append("第" + i + "条:导入失败，主观题关键字不能为空\r\n");
//                    fail++;
//                    continue;
//                }
                if(typeId==5&&!isBlank(keys)){
           		 String [] keyArr = keys.trim().split(" ");
           		 if(keyArr.length!=rightArr.length){
           			    sb.append("第" + i + "条:导入失败，填空题关键字与题干中答案不匹配\r\n");
                        fail++;
                        continue; 
           		 }
           	   }
                QuestionBean model = new QuestionBean();
                List<QuestionOptionBean> optionList= new ArrayList<QuestionOptionBean>();
                name = name.replaceAll("(\\(|（)\\s*(\\)|）)", "(    )");
                model.setCategoryId(categoryId);
                model.setType(typeId);
                model.setContent(name);
                model.setDifficultyLevel(difficultId);
                model.setCompanyId(companyId);
                model.setSubCompanyId(subCompanyId);
                model.setAnalysis(des);
                model.setIsDeleted(false);
                model.setAnswerKeys(keys);
                model.setIsMultimedia(false);
                model.setCreateUserId(userId);
                model.setIsEnabled(true);
                model.setIsSubQuestion(false);
                //试题选项
                //填空
                if (typeId == 5) {
                	String keyStr="";
                	 for (int j=0;j<rightArr.length;j++) {
                     	QuestionOptionBean option =new QuestionOptionBean();
                     	option.setContent(rightArr[j]);
                     	option.setOrderNum(j+1);
                     	option.setIsAnswer(true);
                        optionList.add(option);
                        keyStr+=rightArr[j]+" ";
                     }
                	 if(isBlank(keys)){
                		 model.setAnswerKeys(keyStr.trim()); 
                	 }
                 	model.setOptions(optionList);
                }
                //单选
                if (typeId == 2) {
                	
                    for (int j=0;j<valueArr.length;j++) {
                    	QuestionOptionBean option =new QuestionOptionBean();
                    	option.setContent(valueArr[j]);
                    	option.setOrderNum(j+1);
                        if (valueArr[j].equals(rightArr[0])) {
                        	option.setIsAnswer(true);
                           
                        }else{
                        	option.setIsAnswer(false);
                        }
                       
                    	option.setType(1);
                        optionList.add(option);
                    }
                	model.setOptions(optionList);
                }
                //多选
                 if (typeId == 3) {
                    for (int j=0;j<valueArr.length;j++) {
                    	QuestionOptionBean option =new QuestionOptionBean();
                    	option.setContent(valueArr[j]);
                    	option.setOrderNum(j+1);
                    	for(String rightStr:rightArr){
                    		if(valueArr[j].equals(rightStr)){
                    			option.setIsAnswer(true);
                                break;
                    		}
                    	}
                    	if(option.getIsAnswer()==null){
                    		option.setIsAnswer(false);
                    	}
                    	option.setType(1);
                        optionList.add(option); 
                    }
                	model.setOptions(optionList);
                }
                 //判断
                if (typeId == 4) {
                    if ("正确".equals(rightArr[0])) {
                        model.setIsTrue(true);
                    }
                    else {
                        model.setIsTrue(false);
                    }
                }
              
                //主观
                if (typeId == 1) {
                	QuestionOptionBean option =new QuestionOptionBean();
                	option.setContent(rightArr[0]);
                	option.setIsAnswer(true);
                	optionList.add(option);
                	model.setOptions(optionList);
                	if(isBlank(keys)){
               		 model.setAnswerKeys(rightArr[0]); 
               	   }
                }
                addQuestion(model);
                
                idList.add(model.getId());
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
        dbObject.put("list", idList);
        
        LOG.error(sb.toString());
        return dbObject;
	}
	
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
	
	 public static boolean isEmpty(String str) {
	        return str == null || str.length() == 0;
	    }

	 private  int isExit(String[] str1, String[] str2) {
	        if(str1 == null && str1.length <= 0 && str2 == null && str2.length <= 0) {
	            return 0;
	        }
	        if(str1.length > 10 || str2.length > 10) {
	            return 0;
	        }
	        if (str1.length < str2.length) {
	            return 0;
	        }
	        List list = Arrays.asList(str1);
	        Set set = new HashSet(list);
	        if (set.size() < list.size()) {
	            return 0;
	        }

	        int b = 2;
	        for(String str : str2) {
	            if (!list.contains(str.trim())) {
	                b = 1;
	                break;
	            }
	        }
	        return b;
	    }
	 
	 //匹配的数目
	 public Integer getStrCount (String str,String regEx){
		 
		 Pattern pattern = Pattern.compile(regEx);
         Matcher matcher = pattern.matcher(str);
         Integer i=0;
         while(matcher.find()){
        	 i++;
         }
         return i;
	 }
	 
	 //获得题干
	 private String getContent(String s,List<String> picList){
		 if(s==null||s.length()==0){
			 return null;
		 }
		
		 String contentRegEx ="^\\d*(\\、|\\.)";
		 s=s.replaceFirst(contentRegEx, "");
		 if(s.indexOf("")>-1&&picList.size()>0){
			 for(String picPath : picList){
				 picPath="<img class=\"question_content_img\" src=\""+picPath+"\">";
				 s=s.replaceFirst("", picPath);
			 }
		 }
		 s=s.replaceFirst("(\\.|。)\\s*(\\(|（)[\\w\\W]*(\\)|）)[\\s]*$", "").replaceAll("", "");
		 s=s.replaceAll("(\\(|（)\\s*(\\)|）)", "(    )");
		 s+="。";
		 return s;
	 }
	 
	 //获得试题类型
	 public  Integer checkType(String s){
         String zhuGuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*关键词(：|\\:)[\\w\\W]*(\\)|）)[\\s]*$";
         String danxuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*[A-Z]{1}\\s*(\\)|）)[\\s]*$";
         String duoxuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*[A-Z]{2,26}\\s*(\\)|）)[\\s]*$";
         String panDuan="^[\\w\\W]+(\\.|。)\\s*(\\(|（)\\s*(正确|错误)\\s*(\\)|）)[\\s]*$";
         String tianKong="^[\\w\\W]+(\\(|（)\\s*(\\)|）)[\\w\\W]*(\\.|。)\\s*(\\(|（)[\\w\\W]+(\\)|）)[\\s]*$";

         if(s.matches(zhuGuan)){
        	 return 1;
         }else if(s.matches(danxuan)){
        	 return 2;
         }else if(s.matches(duoxuan)){
        	 return 3;
         }else if(s.matches(panDuan)){
        	 return 4;
         }else if(s.matches(tianKong)){
        	 return 5;
         }
		 return 0;
	 }
	 
	 
	 
	 //获取答案数组
	 public String [] getAnswer(String s,Integer typeId){
		  String danxuan="(\\(|（)\\s*[A-Z]{1}\\s*(\\)|）)[\\s]*$";
	      String duoxuan="(\\(|（)\\s*[A-Z]{1,6}\\s*(\\)|）)[\\s]*$";
	      String panDuan="(\\(|（)\\s*(正确|错误)\\s*(\\)|）)[\\s]*$";
	      String zhuGuan="(\\(|（)\\s*关键词(：|\\:)[\\w\\W]+(\\)|）)[\\s]*$";
	      String tianKong="(\\.|\\。)\\s*(\\(|（)[\\w\\W]+(\\)|）)[\\s]*$";
	      String regex="";
	      switch(typeId){
	      case 1:
	    	  regex= zhuGuan;
	    	  break;
	      case 2:
	    	  regex= danxuan;
	    	  break;
	      case 3:
	    	  regex= duoxuan;
	    	  break;
	    	  
	      case 4:
	    	  regex= panDuan;
	    	  break;
	      case 5:
	    	  regex= tianKong;
	    	  break;
	    default:
	    	return null;
	    	
	      }
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(s);
		 while(matcher.find()){
			String answeres =  matcher.group(); 
			answeres =answeres.trim().replaceFirst("(\\(|（)", "").replaceFirst("(\\)|）)", "").replaceAll("", "");
			if(typeId==1){
				answeres=answeres.replaceFirst("关键词(:|：)", "");
				return new String[]{answeres};
			}else if(typeId==2){
				return new String[]{answeres};
			}else if(typeId==3){
		
			 char[] cArr=answeres.toCharArray();
			 String [] strArr=new String[cArr.length];
				for(int i =0;i<cArr.length;i++){
					strArr[i] = String.valueOf(cArr[i]);
				}
				return strArr;
			}else if(typeId==4){
				return new String[]{answeres};
			}else if(typeId==5){
				
				answeres = answeres.substring(1);
				String [] strArr=answeres.split("(,|，)");
				return strArr;
			}else{
				return null;
			}
		 }
		 return null;
	 }
	 //检查试题选项，看是否包含文字
	 public boolean checkOption(String s){
		 String regex="^\\s*[A-Z]\\s*(\\.|、)\\s*[^\\s]+\\s*";
		 return s.matches(regex);
	 }
	 //取得选择题选项
	 public void getOption(String s,Map<String,String> map,String picPath){
		 
		 String regex="^\\s*[A-Z]\\s*(\\.|、)";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(s);
		 while(matcher.find()){
			 int start = matcher.start();
			 int end= matcher.end();
			 String head=s.substring(start,end);
			 String content=s.substring(end);
			 head= head.trim().replaceFirst("(、|\\.)$", "");
			 
			 if(picPath!=null&&picPath.length()>0){
				 content=""+picPath;
			 }else{
				 content=content.trim().replaceAll("", "");
			 }
			 map.put(head, content);
		 }
	 }
	//保存图片 type为1时，保存题干中的图片
	 public String savePicture(Paragraph paragraph,PicturesTable pTable,Integer type,List<String> picList) throws Exception{
		 String s ="";
		 boolean isFirst = true;
  		 for(int j=0;j<paragraph.numCharacterRuns();j++){
         	  CharacterRun characterRun =paragraph.getCharacterRun(j);
         	  //是否存在图片
         	  if (pTable.hasPicture(characterRun)) {
                   Picture pic = pTable.extractPicture(characterRun, false);
                   String picName = pic.suggestFullFileName();
                // 1、获取文件储存的地址。
       			String nameSuff = ".jpg";
       			if (StringUtils.isNotBlank(picName)) {
       				nameSuff = picName.substring(picName.lastIndexOf("."));
       			}
       			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");// 拿到实际存放地址

       			// 获取拼接地址
       			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
       			String extendUrl = "exam/" + df.format(new Date());

       			File file = new File(saveUrl + extendUrl);
       			if (!file.exists()) {
       				file.mkdirs();
       			}

       			// 2、获取文件的新的名称。以时间戳+四位随机数组成
       			String fileName = UUID.randomUUID().toString() + nameSuff;
       			String filePath = saveUrl + extendUrl + "/" + fileName;
       			// 将上传的文件写到new出来的文件中
       		   OutputStream out = new FileOutputStream(new File(filePath));
       		   pic.writeImageContent(out);
       			  
                   if(isFirst){
                	  
                	   String pathStr=PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH") + "/"
                      			+ extendUrl + "/" + fileName;
                	   if(type==1){
                		   picList.add(pathStr);
                		   pathStr="\""+pathStr+"\"";
                		  
                	   }
                	   s=pathStr;
                	   isFirst=false;
                   }else{
                	  
                	   String pathStr=PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH") + "/"
                     			+ extendUrl + "/" + fileName;
                	   if(type==1){
                		   picList.add(pathStr);
                		   pathStr=",\""+pathStr+"\"";
                		   s+=pathStr;
                		  
                	   }
                	   
                   }
                  
               }
         	   
           }
  	     if(type==1&&s.length()>0){
  	    	 s="["+s+"]";
  	     }
  		 return s;
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
	  * 查询出可导出的试题列表
	  * @param searchOption
	  * @return
	  */
	    @Transactional(readOnly = true)
		public List<QuestionBean> getQuestionExportVoList(QuestionSearchOptionVo searchOption) {
			resolveFamilyTree(searchOption);
			List<QuestionBean> resultList = questionDaoMapper.getQuestionExportVoList(searchOption);
			
			return resultList;
		}
	 
	 
	    /**
	     * 导出的试题list转换
	     * @param searchOption
	     * @return
	     */
	    @Override
	 public List<QuestionExportVo> getExportList(QuestionSearchOptionVo searchOption){
		 List<QuestionExportVo> list = new ArrayList<QuestionExportVo>();
		 List<QuestionBean> questionList= getQuestionExportVoList(searchOption);
		 if(questionList!=null&&questionList.size()>0){
			 for(QuestionBean bean :questionList){
				 QuestionExportVo exportVo = new QuestionExportVo();
				 exportVo.setContent(bean.getContent());
				 exportVo.setAnalysis(bean.getAnalysis());
				 exportVo.setDifficultyLevel(bean.getDifficultyLevel());
				 Integer type = bean.getDisplayType();
				 exportVo.setType(type);
				 exportVo.setKey(bean.getAnswerKeys());
				 if(type!=4){
					 String answer ="";
					 String options ="";
					 int i = 0;
					 for(QuestionOptionBean option:bean.getOptions()){
						 switch(type){
						   case 1:
							   answer =option.getContent();
							   break;
						   case 2:
							   if(options.length()>0){
								   options+="|";
							   }
							   options+=option.getContent();
							   if(option.getIsAnswer()){
								   answer+=option.getContent();
							   }
							   
							   
							   break;
						   case 3:
							   if(options.length()>0){
								   options+="|";
							   }
							   if(answer.length()>0&&option.getIsAnswer()){
								   answer+="|";
							   }
							   options+=option.getContent();
							   if(option.getIsAnswer()){
								   answer+=option.getContent();
							   }
							  
							   break;
						   case 5:
							   answer+=option.getContent();
							   if((i+1)!=bean.getOptions().size()){
								 answer+="|";
							   }
							   break;
						 }
						 i++;
					 }
					 exportVo.setAnswer(answer);
					 exportVo.setOptions(options);
				 }else{
					 if(bean.getIsTrue()){
						 exportVo.setAnswer("正确");
					 }else{
						 exportVo.setAnswer("错误"); 
					 }
					 
				 }
				 list.add(exportVo);
			 }
		 }
		 return list;
	 }
	 
	/**zhangbocheng end*/
	 
	 /**zhangchen start */
	 /**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionList <BR>
	 * Description: 练习-生成试题 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  List<QuestionBean><BR>
	 */
	@Override
	 public List<QuestionBean> getExerciseQuestionList(QuestionSearchOptionVo searchOption) {
		resolveFamilyTree(searchOption);
		List<QuestionBean> resultList = questionDaoMapper.getExerciseQuestionList(searchOption);
		return resultList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionIdList <BR>
	 * Description: 练习-生成试题 IDlist <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  String<BR>
	 */
	@Override
	public String getExerciseQuestionIdList(QuestionSearchOptionVo searchOption) {
		resolveFamilyTree(searchOption);
		String idStr = questionDaoMapper.getExerciseQuestionIdList(searchOption);
		ExerciseQuestionBean bean = new ExerciseQuestionBean();
		bean.setUserId(searchOption.getUserId());
		//bean.setCategoryId(searchOption.getCategoryId());
		bean.setCategoryIdListStr(searchOption.getCategoryIdListStr());
		//String completeStr = exerciseQuestionDaoMapper.getCompleteQuestionId(bean);
		
		//1、顺序 2、随机
		/*if("2".equals(searchOption.getDisplayMode())){
			idStr = randomStr(idStr);
		}*/
		//return diffString(idStr,completeStr);
		return idStr;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: diffString <BR>
	 * Description: diffString <BR>
	 * Remark: <BR>
	 * @param str1
	 * @param str2
	 * @return  String<BR>
	 */
	private String diffString(String str1,String str2){
		if(str1 == null){
			return null;
		}
		if(str2 == null){
			return str1;
		}else{
			String str = "";
			String[] strArray1 = StringUtils.split(str1, ",");
			String[] strArray2 = StringUtils.split(str2, ",");
			List<String> strList = new ArrayList<String>();
			for(int i=0;i<strArray2.length;i++){
				strList.add(strArray2[i]);
			}
			for(int i=0;i<strArray1.length;i++){
				if(!strList.contains(strArray1[i])){
					str += strArray1[i] + ",";
				}
			}
			if(str.length() > 1){
				return str.substring(0, str.length()-1);
			}else{
				return str;
			}
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionCount <BR>
	 * Description: 练习-生成试题的总数 个数 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  Integer<BR>
	 */
	@Override
	public String getExerciseQuestionCount(QuestionSearchOptionVo searchOption) {
		resolveFamilyTree(searchOption);
		int total = questionDaoMapper.getExerciseQuestionCount(searchOption);
		ExerciseQuestionBean bean = new ExerciseQuestionBean();
		bean.setUserId(searchOption.getUserId());
		//bean.setCategoryId(searchOption.getCategoryId());
		bean.setCategoryIdListStr(searchOption.getCategoryIdListStr());
		int complete = exerciseQuestionDaoMapper.getCompleteQuestionNum(bean);
		bean.setIsWrong(1);
		int wrong = exerciseQuestionDaoMapper.getCompleteQuestionNum(bean);
		return total+","+complete+","+wrong;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: randomList <BR>
	 * Description: 打乱str <BR>
	 * Remark: <BR>
	 * @param sourceList
	 * @return  List<T><BR>
	 */
	public static String randomStr(String str){
		if (str == null || "".equals(str)) {
			 return str; 
		} 
		String[] strArray = str.split(",");
		List<String> sourceList = new ArrayList<String>();
		List<String> randomList = new ArrayList<String>();
		for(int i=0;i<strArray.length;i++){
			sourceList.add(strArray[i]);
		}
		 do{ 
			 int randomIndex = Math.abs( new Random( ).nextInt( sourceList.size() ) ); 
			 randomList.add( sourceList.remove( randomIndex ) ); 
		 }while( sourceList.size()>0);
		 String strValue = "";
		 for(int i=0;i<randomList.size();i++){
			 strValue += randomList.get(i);
			 if(i != randomList.size()){
				 strValue += ",";
			 }
		 }
		 return strValue; 
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOneExerciseQuestion <BR>
	 * Description: 生成一道试题 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionBean<BR>
	 */
	@Override
	public QuestionBean getOneExerciseQuestion(int id) {
		QuestionBean qb = questionDaoMapper.selectQuestionById(id);
		if(qb != null){
			if(qb.getQuestionIdList() != null && !"".equals(qb.getQuestionIdList())){
				//查询子试题列表
				List<QuestionBean> subQuestions = examQuestionDaoMapper.selectQuestionListByIdList(qb.getQuestionIdList());
				for(int i=0;i<subQuestions.size();i++){
					String correctAnswer2 = myExamManageService.selectCorrectAnswerByQuestionIdAndType(subQuestions.get(i).getId(),subQuestions.get(i).getType());
					subQuestions.get(i).setCorrectAnswer(correctAnswer2);
				}
				qb.setSubQuestions(subQuestions);
			}else{
				String correctAnswer = myExamManageService.selectCorrectAnswerByQuestionId(id);
				qb.setCorrectAnswer(correctAnswer);
			}
		}
		return qb;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isAnswerCorrect <BR>
	 * Description: 判断题目是否正确 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @param userId
	 * @return
	 * @throws Exception  boolean<BR>
	 */
	@Override
	@Transactional
	public boolean isAnswerCorrect(ExamUserAnswerVo vo,int userId,String isAddWrong) throws Exception {
		// TODO Auto-generated method stub
		if(vo != null){
			String[] questionId = vo.getQuestionId();
			String[] questionType = vo.getQuestionType();
			String[] userAnswer = vo.getUserAnswer();
			String[] parentId = vo.getParentId();
			String[] subQuestionId = vo.getSubQuestionId();
			//插入练习试题，已经完成试题
			ExerciseQuestionBean bean = new ExerciseQuestionBean();
			bean.setUserId(userId);
			bean.setCategoryId(vo.getCategoryId());
			//计算分数
			boolean flag = false;
			for(int i=0;i<questionId.length;i++){
				bean.setQuestionId(Integer.parseInt(questionId[i]));
				if("0".equals(parentId[i]) && !"6".equals(questionType[i])){//非组合题
					flag = myExamManageService.isAnswer(questionId[i],questionType[i],userAnswer[i]);
				}else{//组合题
					if("0".equals(parentId[i])){//组合题题干
						String idList = subQuestionId[i];//子试题
						if(!"".equals(idList)){
							String[] idArray = idList.split(",");
							boolean flag_zuhe = true;
							for(int j=0;j<idArray.length;j++){
								if(!flag_zuhe){
									break;
								}
								for(int k=0;k<questionId.length;k++){
									if(questionId[k].equals(idArray[j])){
										flag_zuhe = myExamManageService.isAnswer(questionId[k], questionType[k], userAnswer[k]);
										if(!flag_zuhe){
											flag = false;
											break;
										}else{
											flag = true;
										}
									}
								}
							}
						}
					}
				}
				//如果是再次练习，答错时，不再保存到错题集
				if(!"0".equals(isAddWrong)){
					//错题集中组合题只存大题题干ID
					if("0".equals(parentId[i])){
						if(!flag){//如果题目错误，直接录入错题集
							ExamWrongCardBean wrongBean = new ExamWrongCardBean();
							wrongBean.setFromUserId(userId);
							wrongBean.setQuestionId(Integer.parseInt(questionId[i]));
							wrongBean.setJoinTime(new Date());
							wrongBean.setType(3);//错题类型 练习-3
							if("3".equals(questionType[i])){//多选题
								wrongBean.setUserAnswer(userAnswer[i].replace("##**##", ","));
							}else{
								wrongBean.setUserAnswer(userAnswer[i]);
							}
							examWrongCardDaoMapper.insertWrongCard(wrongBean);
							bean.setIsWrong(1);
						}else{
							bean.setIsWrong(0);
						}
						//先判断该题有没有重复做
						Integer completeId = exerciseQuestionDaoMapper.isCompleted(bean);
						if(completeId != null){
							//更新已完成试题
							bean.setId(completeId);
							exerciseQuestionDaoMapper.updateExerciseQuestion(bean);
						}else{
							//插入已完成试题
							exerciseQuestionDaoMapper.insertExerciseQuestion(bean);
						}
						
					}
				}
			}
			return flag;
		}else{
			return false;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: exerciseIsOrNotComplete <BR>
	 * Description: 判断一次练习中，一级题库是否全部做完，如果做完，需要清空已做试题数据 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return  boolean<BR>
	 */
	@Override
	@Transactional
	public boolean exerciseIsOrNotComplete(QuestionSearchOptionVo searchOption){
		boolean flag = false;
		//int parentCategoryId = searchOption.getParentCategoryId();
		//searchOption.setCategoryId(parentCategoryId);
		int categoryId = searchOption.getCategoryId();
		searchOption.setCategoryId(categoryId);
		resolveFamilyTree(searchOption);
		int total = questionDaoMapper.getExerciseQuestionCount(searchOption);
		ExerciseQuestionBean bean = new ExerciseQuestionBean();
		bean.setUserId(searchOption.getUserId());
		//bean.setCategoryId(searchOption.getCategoryId());
		bean.setCategoryIdListStr(searchOption.getCategoryIdListStr());
		int complete = exerciseQuestionDaoMapper.getCompleteQuestionNum(bean);
		if(complete >= total){
			flag = true;
		}
		//如果试题库的试题已经全部做完，清空表数据
		if(flag){
			exerciseQuestionDaoMapper.deleteCompleteQuestion(bean);
		}
		return flag;
	}
	
	 /**zhangchen end */
	

}
