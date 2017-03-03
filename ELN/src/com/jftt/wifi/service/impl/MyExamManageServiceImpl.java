/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyExamManageServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.action.MyExamManageAction;
import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.ExamWrongCardBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.QuestionOptionBean;
import com.jftt.wifi.bean.exam.vo.ExamOrganizingRuleVo;
import com.jftt.wifi.bean.vo.ExamAssignDetailVo;
import com.jftt.wifi.bean.vo.ExamGetScoreVo;
import com.jftt.wifi.bean.vo.ExamGradeVo;
import com.jftt.wifi.bean.vo.ExamRecorderVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.ExamWrongCardVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamOrganizingRuleDaoMapper;
import com.jftt.wifi.dao.ExamPaperQuestionDaoMapper;
import com.jftt.wifi.dao.ExamQuestionDaoMapper;
import com.jftt.wifi.dao.ExamQuestionOptionDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.ExamUserAnswerDaoMapper;
import com.jftt.wifi.dao.ExamWrongCardDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseRecordService;
import com.jftt.wifi.service.ExamMarkScoreService;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.util.TimeUtil;

import fr.opensagres.xdocreport.core.utils.Assert;

/**
 * @author JFTT)zhangchen
 * class name:MyExamManageServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 */
@Service("myExamManageService")
public class MyExamManageServiceImpl implements MyExamManageService{
	
	@Resource(name="examAssignInfoDaoMapper")
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Resource(name="examScheduleDaoMapper")
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Resource(name="examWrongCardDaoMapper")
	private ExamWrongCardDaoMapper examWrongCardDaoMapper;
	@Resource(name="examQuestionDaoMapper")
	private ExamQuestionDaoMapper examQuestionDaoMapper;
	@Resource(name="examUserAnswerDaoMapper")
	private ExamUserAnswerDaoMapper examUserAnswerDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="examQuestionOptionDaoMapper")
	private ExamQuestionOptionDaoMapper examQuestionOptionDaoMapper;
	@Resource(name="manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	@Resource(name="examOrganizingRuleDaoMapper")
	private ExamOrganizingRuleDaoMapper examOrganizingRuleDaoMapper;
	@Autowired
	private ExamPaperService examPaperService;
	@Resource(name="manageParamService")
	private ManageParamService manageParamService;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	@Resource(name="courseRecordService")
	private CourseRecordService courseRecordService;
	@Autowired
	private ExamMarkScoreService examMarkScoreService;
	@Autowired
	private ExamPaperQuestionDaoMapper examPaperQuestionDaoMapper;
	@Autowired
	private ExamQuestionService examQuestionService;
	
	private static Logger logger = Logger.getLogger(MyExamManageServiceImpl.class);
	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getMyExamRecorderVo(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getMyExamRecorderVo <BR>
	 * Description: 查询我的考试-参与考试记录 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamRecorderVo> getMyExamRecorderVo(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectExamAssignInfoByUserId(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getExamInfo(int, int) <BR>
	 * Method name: getExamInfo <BR>
	 * Description: 查询我的考试-进入考试页面信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param type
	 * @return  <BR>
	*/
	
	@Override
	public ExamScheduleVo getExamInfo(int id) {
		// TODO Auto-generated method stub
		ExamScheduleVo vo = examScheduleDaoMapper.selectExamInfo(id);
		return vo;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getExamScheduleVo(int, int) <BR>
	 * Method name: getExamScheduleVo <BR>
	 * Description: 查询一场考试试卷内容 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	@Transactional
	public ExamScheduleVo getExamScheduleVo(int id) {
		// TODO Auto-generated method stub
		//ExamScheduleVo vo = examScheduleDaoMapper.selectExamScheduleVoNew(id);
		ExamScheduleVo vo = examScheduleDaoMapper.selectExamScheduleVo(id);
		if(vo != null){
			Integer random_order_mode = vo.getRandomOrderMode();
			PaperBean paperBean = vo.getPaperBean();
			//PaperBean paperBean = new PaperBean();
			if(paperBean != null){
				//组卷方式
				int organizing_mode = paperBean.getOrganizingMode();
				//int organizing_mode = vo.getOrganizingMode();
				if(organizing_mode == 1){//标准试卷
					List<PaperQuestionBean> paperQuestionList = paperBean.getPaperQuestionList();
					/*List<PaperQuestionBean> paperQuestionList = examPaperQuestionDaoMapper.getPaperQuestionList(vo.getPaperId());
					
					if (paperQuestionList != null && paperQuestionList.size() > 0) {
						// 这里可以考虑增加questionService.getQuestionList接口来提升效率
						for (PaperQuestionBean paperQuestion : paperQuestionList) {
							paperQuestion.setQuestionBean(examQuestionService.getQuestion(paperQuestion.getQuestionId()));
							// Calculate subQuestion score for group questions
							paperQuestion.calculateAndSetSubQuestionScoreForGroupQuestion();
						}
					}*/
					//试题或选项打乱
					if(random_order_mode != null && random_order_mode != 0){
						paperQuestionList = RandomQuestionOrOptions(paperQuestionList,random_order_mode);
					}
					if(!paperQuestionList.isEmpty()){
						for(int i=0;i<paperQuestionList.size();i++){
							QuestionBean qb = paperQuestionList.get(i).getQuestionBean();
							if(qb != null){
								if(qb.getQuestionIdList() != null && !"".equals(qb.getQuestionIdList())){
									//查询子试题列表
									//List<QuestionBean> subQuestions = examQuestionDaoMapper.selectQuestionListByIdList(qb.getQuestionIdList());
									qb.setSubQuestions(getSubQuestionsByIdList(qb.getQuestionIdList()));
								}
							}
						}
					}
					paperBean.setPaperQuestionList(paperQuestionList);
					vo.setPaperBean(paperBean);
				}else{//随机组卷,调用wangyifeng的接口
					int paperId = paperBean.getId();
					List<PaperQuestionBean> paperQuestionList = examPaperService.getPaperQuestions(paperId);
					paperBean.setPaperQuestionList(paperQuestionList);
				}
			}
		}		
		return vo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getSubQuestionsByIdList <BR>
	 * Description: 通过idList获取子试题内容 <BR>
	 * Remark: <BR>
	 * @param idList
	 * @return  List<QuestionBean><BR>
	 */
	public List<QuestionBean> getSubQuestionsByIdList(String idList){
		List<QuestionBean> subQuestions = new ArrayList<QuestionBean>();
		if(idList != null && !"".equals(idList)){
			String[] idArray = StringUtils.split(idList, ",");
			for(int i=0;i<idArray.length;i++){
				QuestionBean subQuestion = examQuestionDaoMapper.selectQuestionById(Integer.parseInt(idArray[i]));
				subQuestions.add(subQuestion);
			}
		}
		return subQuestions;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: RandomQuestionOrOptions <BR>
	 * Description: 随机打乱试题或选项 
	 * 随机打乱模式
		1题目顺序
		2选项顺序
		3题目+选项顺序<BR>
	 * Remark: <BR>
	 * @param list
	 * @param random_order_mode  void<BR>
	 * @return List<PaperQuestionBean>
	 */
	public List<PaperQuestionBean> RandomQuestionOrOptions(List<PaperQuestionBean> list,int random_order_mode){
		List<PaperQuestionBean> resultList = new ArrayList<PaperQuestionBean>();
		if(random_order_mode == 1){//题目顺序
			//resultList = RandomQuesiton(list);
			resultList = RandomQuestionByType(list);
		}else if(random_order_mode == 2){//选项顺序
			resultList = RandomOptions(list);
		}else{//题目+选项顺序
			//resultList = RandomQuesiton(list);
			resultList = RandomQuestionByType(list);
			resultList = RandomOptions(resultList);
		}
		return resultList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: RandomQuesiton <BR>
	 * Description: 打乱试题 <BR>
	 * Remark: <BR>
	 * @param list
	 */
	public List<PaperQuestionBean> RandomQuesiton(List<PaperQuestionBean> list){
		/*HashSet<Integer> set = new HashSet<Integer>();
		randomSet(0,list.size()-1,list.size(),set);
		for (int i : set){
			resultList.add(list.get(i));
		}*/
		return randomList(list);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: RandomQuestionByType <BR>
	 * Description: 根据题型打乱试题 <BR>
	 * Remark: <BR>
	 * @param list
	 * @return  List<PaperQuestionBean><BR>
	 */
	public List<PaperQuestionBean> RandomQuestionByType(List<PaperQuestionBean> list){
		List<PaperQuestionBean> tempList = new ArrayList<PaperQuestionBean>();
		Map<Integer,List<PaperQuestionBean>> map = new HashMap<Integer, List<PaperQuestionBean>>(); 
		for(int i=1;i<8;i++){
			map.put(i, new ArrayList<PaperQuestionBean>());
		}
		for(PaperQuestionBean pqBean : list){
			QuestionBean qBean = pqBean.getQuestionBean();
			Integer displayType = qBean.getDisplayType();
			for (Integer key : map.keySet()){
				if(displayType.equals(key)){
					map.get(key).add(pqBean);
				}
			 }
		}
		for (Integer key : map.keySet()){
			tempList.addAll(randomList(map.get(key)));
		}
		return tempList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: RandomOptions <BR>
	 * Description: 打乱选项 <BR>
	 * Remark: <BR>
	 * @param list
	 */
	public List<PaperQuestionBean> RandomOptions(List<PaperQuestionBean> list){
		List<PaperQuestionBean> resultList = new ArrayList<PaperQuestionBean>();
		for(int i=0;i<list.size();i++){
			PaperQuestionBean paperQuestionBean = list.get(i);
			QuestionBean questionBean = paperQuestionBean.getQuestionBean();
			//QuestionBean qb = new QuestionBean();
			//PaperQuestionBean paperQuestionBean = new PaperQuestionBean();
			List<QuestionOptionBean> oList =  list.get(i).getQuestionBean().getOptions();
			List<QuestionOptionBean> tempList = new ArrayList<QuestionOptionBean>();
			//打乱选项
			/*HashSet<Integer> set = new HashSet<Integer>();
			randomSet(0,oList.size()-1,oList.size(),set);
			for (int j : set){
				tempList.add(oList.get(j));
			}
			qb.setOptions(tempList);
			paperQuestionBean.setQuestionBean(qb);*/
			tempList = randomOptionList(oList);
			questionBean.setOptions(tempList);
			paperQuestionBean.setQuestionBean(questionBean);
			resultList.add(paperQuestionBean);
		}
		return resultList;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: randomList <BR>
	 * Description: 打乱试题顺序 <BR>
	 * Remark: <BR>
	 * @param sourceList
	 * @return  List<T><BR>
	 */
	public static List<PaperQuestionBean> randomList(List<PaperQuestionBean> sourceList){
		if (sourceList.isEmpty()) {
			 return sourceList; 
		} 
		 List<PaperQuestionBean> randomList = new ArrayList<PaperQuestionBean>(sourceList.size()); 
		 do{ 
			 int randomIndex = Math.abs( new Random( ).nextInt( sourceList.size() ) ); 
			 randomList.add( sourceList.remove( randomIndex ) ); 
		 }while( sourceList.size()>0); 
		 return randomList; 
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: randomOptionList <BR>
	 * Description: 打乱选项顺序 <BR>
	 * Remark: <BR>
	 * @param sourceList
	 * @return  List<QuestionOptionBean><BR>
	 */
	public static List<QuestionOptionBean> randomOptionList(List<QuestionOptionBean> sourceList){
		if (sourceList.isEmpty()) {
			 return sourceList; 
		} 
		 List<QuestionOptionBean> randomList = new ArrayList<QuestionOptionBean>(sourceList.size()); 
		 do{ 
			 int randomIndex = Math.abs( new Random( ).nextInt( sourceList.size() ) ); 
			 randomList.add( sourceList.remove( randomIndex ) ); 
		 }while( sourceList.size()>0); 
		 return randomList; 
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getWrongCard(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getWrongCard <BR>
	 * Description: 查看错题集列表  <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamWrongCardVo> getWrongCard(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		List<ExamWrongCardVo> list = examWrongCardDaoMapper.selectWrongCardByUserId(bean);
		for(int i=0;i<list.size();i++){
			if(!"-".equals(list.get(i).getMyAnswer())){
				//如果是单选与多选题，需要将答案中的ID换成对应的order_num
				int type = list.get(i).getType();
				if(type == 2 || type == 3){//单选题与多选题要查出选项的order_num
					if(list.get(i).getMyAnswer() != null && !"-".equals(list.get(i).getMyAnswer()) && !"".equals(list.get(i).getMyAnswer())){
						ExamQueryConditionBean tempBean = new ExamQueryConditionBean();
						tempBean.setExam_assign_id(list.get(i).getAssignId());
						tempBean.setQuestion_id(list.get(i).getQuestionId());
						tempBean.setOption_id(list.get(i).getMyAnswer());
						String answer = examUserAnswerDaoMapper.selectChoiceAnswer(tempBean);
						list.get(i).setMyAnswer(answer);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getWrongQuestionDetail(int) <BR>
	 * Method name: getWrongQuestionDetail <BR>
	 * Description:查看错题详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public ExamWrongCardVo getWrongQuestionDetail(int id) {
		// TODO Auto-generated method stub
		ExamWrongCardVo vo = examWrongCardDaoMapper.selectWrongQuestionDetail(id);
		if(vo != null){
			ExamUserAnswerBean answerBean = vo.getUserAnswerBean();
			/*int type = examQuestionDaoMapper.selectTypeByID(answerBean.getQuestionId());
			if(type != 6){
				String correctAns = examUserAnswerDaoMapper.selectCorrectAnswer(answerBean.getQuestionId(), type);
				answerBean.setCorrectAnswer(correctAns);
			}*/
			//查询正确答案
			answerBean.setCorrectAnswer(selectCorrectAnswerByQuestionId(answerBean.getQuestionId()));
			int assign_id = answerBean.getExamAssignId();
			int order_num = answerBean.getOrderNum();
			List<ExamUserAnswerBean> subAnswers = examUserAnswerDaoMapper.selectQuestionDetailByOrderNum(assign_id, order_num);
			for(int j=0;j<subAnswers.size();j++){
				ExamUserAnswerBean answer = subAnswers.get(j);
				/*int type2 = examQuestionDaoMapper.selectTypeByID(answer.getQuestionId());
				if(type2 != 6){
					String correctAns = examUserAnswerDaoMapper.selectCorrectAnswer(answer.getQuestionId(), type2);
					answer.setCorrectAnswer(correctAns);
				}*/
				//查询正确答案
				answer.setCorrectAnswer(selectCorrectAnswerByQuestionId(answer.getQuestionId()));
				subAnswers.set(j, answer);
			}
			answerBean.setSubAnswers(subAnswers);
		}
		return vo;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getExamSimulate(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExamSimulate <BR>
	 * Description: 获取模拟试题列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamRecorderVo> getExamSimulate(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectSimulateRecorder(bean);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCorrectAnswerByQuestionId <BR>
	 * Description: 通过试题ID查询试题的正确答案 <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @return  String<BR>
	 */
	@Override
	public String selectCorrectAnswerByQuestionId(int questionId){
		String correctAns = "";
		int type = examQuestionDaoMapper.selectTypeByID(questionId);
		if(type != 6){
			correctAns = examUserAnswerDaoMapper.selectCorrectAnswer(questionId, type);
		}
		return correctAns;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectCorrectAnswerByQuestionId <BR>
	 * Description: 通过试题ID与类型查询试题的正确答案  <BR>
	 * Remark: <BR>
	 * @param questionId
	 * @param type
	 * @return  String<BR>
	 */
	@Override
	public String selectCorrectAnswerByQuestionIdAndType(int questionId,int type){
		String correctAns = "";
		if(type != 6){
			correctAns = examUserAnswerDaoMapper.selectCorrectAnswer(questionId, type);
		}
		return correctAns;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getAllGrade(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getAllGrade <BR>
	 * Description: 成绩总览  <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	public List<ExamGradeVo> getAllGrade(ExamQueryConditionBean bean) throws Exception {
		// TODO Auto-generated method stub
		String name = bean.getName();
		String depName = bean.getDepartment();
		String post = bean.getPost();
		Map<String, Object> map =new HashMap<String, Object>();
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(depName!=null && !"".equals(depName)){
			map.put("deptId", depName);
		}
		if(post!=null && !"".equals(post)){
			map.put("postId", post);
		}
		if(bean.getCompanyId() !=null){
			map.put("companyId", bean.getCompanyId());
		}
		List<ExamGradeVo> list = new ArrayList<ExamGradeVo>();
		List<ManageUserBean> userList = null;
		if(map.size()>0){
			userList = manageUserService.findUserByListCondition(map);
			if (userList == null || userList.size() == 0) {
				return new ArrayList<ExamGradeVo>();
			}
		}
		bean.setUserList(userList);
		List<ExamGradeVo> tempList = examAssignInfoDaoMapper.selectAllGrade(bean);
		ExamGradeVo examGradeVo = null;
		/*for(int i=0;i<list.size();i++){
			tempBean = manageUserService.findUserById(String.valueOf(list.get(i).getUserId()));
			examBean = list.get(i);
			if(tempBean != null){
				examBean.setUserName(tempBean.getName());
				list.set(i, examBean);
			}
		}*/
		for(ExamGradeVo egv : tempList){
			examGradeVo = new ExamGradeVo();
			int userId = egv.getUserId();
			int score = egv.getScore();
			int isPassed = egv.getIsPassed();
			int testTimes = egv.getTestTimes();
			int rownum = egv.getRownum();
			ManageUserBean userBean = manageUserService.findUserById(userId+"");
			Integer depId = 0;
			String userName = "暂无";
			String postName = "暂无";
			String departmentName = "暂无";
			Integer postId = 0;
			if(userBean != null){
				depId = userBean.getDeptId();
				userName = userBean.getName();
				postId = userBean.getPostId();
				//获取部门名称
				if(depId != null){
					examGradeVo.setDepartmentId(depId);
					ManageDepartmentBean dept= manageDepartmentDaoMapper.getManageDepartmentById(depId);
					if(dept != null){
						departmentName = dept.getName();
					}
				}
				if(postId != null){
					ManagePostBean postBean = manageParamService.selectManagePostById(postId);
					if(postBean!=null){
						postName = postBean.getName();
					}
				}
			}
			examGradeVo.setUserId(userId);
			examGradeVo.setUserName(userName);
			examGradeVo.setPost(postName);
			examGradeVo.setDepartmentName(departmentName);
			examGradeVo.setScore(score);
			examGradeVo.setIsPassed(isPassed);
			examGradeVo.setTestTimes(testTimes);
			examGradeVo.setRownum(rownum);
			list.add(examGradeVo);
			examGradeVo = null;
		}
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAllGradeCount <BR>
	 * Description: 成绩总览条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  int<BR>
	 */
	@Override
	public int getAllGradeCount(ExamQueryConditionBean bean) throws Exception{
		String name = bean.getName();
		String depName = bean.getDepartment();
		String post = bean.getPost();
		Map<String, Object> map =new HashMap<String, Object>();
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(depName!=null && !"".equals(depName)){
			map.put("deptId", depName);
		}
		if(post!=null && !"".equals(post)){
			map.put("postId", post);
		}
		if(bean.getCompanyId() !=null){
			map.put("companyId", bean.getCompanyId());
		}
		
		List<ManageUserBean> userList  = manageUserService.findUserByListCondition(map);
		if(userList==null || userList.size()==0){
			return 0;
		}
		bean.setUserList(userList);
		return examAssignInfoDaoMapper.selectAllGradeCount(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getExamAssignDetail(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExamAssignDetail <BR>
	 * Description: 查询考试详情 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamUserAnswerBean> getExamAssignDetail(int exam_assign_id) {
		// TODO Auto-generated method stub
		List<ExamUserAnswerBean> list = examUserAnswerDaoMapper.selectExamQuestionDetail(exam_assign_id);
		for(int i=0;i<list.size();i++){
			ExamUserAnswerBean answer = list.get(i);
			/*int type = examQuestionDaoMapper.selectTypeByID(answer.getQuestionId());
			if(type != 6){
				String correctAns = examUserAnswerDaoMapper.selectCorrectAnswer(answer.getQuestionId(), type);
				answer.setCorrectAnswer(correctAns);
			}*/
			//查询正确答案
			answer.setCorrectAnswer(selectCorrectAnswerByQuestionId(answer.getQuestionId()));
		}
		for(int i=0;i<list.size();i++){
			int order_num = list.get(i).getOrderNum();
			List<ExamUserAnswerBean> subAnswers = examUserAnswerDaoMapper.selectQuestionDetailByOrderNum(exam_assign_id, order_num);
			for(int j=0;j<subAnswers.size();j++){
				ExamUserAnswerBean answer = subAnswers.get(j);
				/*int type = examQuestionDaoMapper.selectTypeByID(answer.getQuestionId());
				if(type != 6){
					String correctAns = examUserAnswerDaoMapper.selectCorrectAnswer(answer.getQuestionId(), type);
					answer.setCorrectAnswer(correctAns);
				}*/
				//查询正确答案
				answer.setCorrectAnswer(selectCorrectAnswerByQuestionId(answer.getQuestionId()));
			}
			list.get(i).setSubAnswers(subAnswers);
		}
		return list;
	}
	
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#initialNoAnswerExamDetail(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: initialNoAnswerExamDetail <BR>
	 * Description: 在无答题的情况下查看试题详情 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	@Override
	public ExamAssignDetailVo getNoAnswerExamDetail(int exam_assign_id){
		//获取试题详情
		ExamAssignDetailVo vo = examAssignInfoDaoMapper.selectExamAssignDetail(exam_assign_id);
		if(vo != null){
			PaperBean paperBean = vo.getExamScheduleVo().getPaperBean();
			List<PaperQuestionBean> paperQuestionList = paperBean.getPaperQuestionList();
			if(!paperQuestionList.isEmpty()){
				for(int i=0;i<paperQuestionList.size();i++){
					QuestionBean qb = paperQuestionList.get(i).getQuestionBean();
					//String ans = examUserAnswerDaoMapper.selectExamCorrectAnswer(qb.getId());
					if(qb != null){
						if(qb.getQuestionIdList() != null && !"".equals(qb.getQuestionIdList())){
							//查询子试题列表
							//List<QuestionBean> subQuestions = examQuestionDaoMapper.selectQuestionListByIdList(qb.getQuestionIdList());
							List<QuestionBean> subQuestions = getSubQuestionsByIdList(qb.getQuestionIdList());
							for(int j=0;j<subQuestions.size();j++){
								//String ans2 = examUserAnswerDaoMapper.selectExamCorrectAnswer(subQuestions.get(j).getId());
								subQuestions.get(j).setCorrectAnswer(selectCorrectAnswerByQuestionIdAndType(subQuestions.get(j).getId(),subQuestions.get(j).getType()));
							}
							qb.setSubQuestions(subQuestions);
						}else{
							qb.setCorrectAnswer(selectCorrectAnswerByQuestionIdAndType(qb.getId(),qb.getType()));
						}
					}
				}
			}
		}
		return vo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOneExamQuestionDetail <BR>
	 * Description: 通过 assign_id与question_id,查询单个试题详情 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  ExamUserAnswerBean<BR>
	 */
	@Override
	public ExamUserAnswerBean getOneExamQuestionDetail(ExamQueryConditionBean bean){
		ExamUserAnswerBean answerBean = examUserAnswerDaoMapper.selectOneExamQuestionDetail(bean);
		int assign_id = answerBean.getExamAssignId();
		int order_num = answerBean.getOrderNum();
		List<ExamUserAnswerBean> subAnswers = examUserAnswerDaoMapper.selectQuestionDetailByOrderNum(assign_id, order_num);
		answerBean.setSubAnswers(subAnswers);
		return answerBean;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#deleteWrongCard(int) <BR>
	 * Method name: deleteWrongCard <BR>
	 * Description: 移出错题集 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/
	
	@Override
	@Transactional
	public void deleteWrongCard(int id,int answer_id) {
		// TODO Auto-generated method stub
		//先删除错题
		examWrongCardDaoMapper.deleteWrongCardById(id);
		//更新用户答案中的is_favorated
		examUserAnswerDaoMapper.updateFavorated(answer_id, 0);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteWrongCard <BR>
	 * Description: 移出错题集 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	@Transactional
	public void deleteWrongCard(int id) {
		// TODO Auto-generated method stub
		//先删除错题
		examWrongCardDaoMapper.deleteWrongCardById(id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getExamRecorderCount(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExamRecorderCount <BR>
	 * Description: 获取我的考试记录条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public int getExamRecorderCount(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectExamRecorderCount(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getExamWrongCount(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExamWrongCount <BR>
	 * Description: 获得错题条数  <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public int getExamWrongCount(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examWrongCardDaoMapper.selectWrongCount(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#getSimulateCount(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getSimulateCount <BR>
	 * Description: 获取模拟试题条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public int getSimulateCount(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectSimulateCount(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen
	 * @see com.jftt.wifi.service.MyExamManageService#calculateScore(com.jftt.wifi.bean.ExamUserAnswerVo) <BR>
	 * Method name: calculateScore <BR>
	 * Description: 提交试卷时计算分数 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	 * @throws Exception 
	*/
	
	@Override
	@Transactional
	public int calculateScore(ExamUserAnswerVo vo,int userId) throws Exception {
		// TODO Auto-generated method stub
		int totalScore = 0;
		if(vo != null){
			String[] questionId = vo.getQuestionId();
			String[] questionType = vo.getQuestionType();
			String[] userAnswer = vo.getUserAnswer();
			String[] orderNum = vo.getOrderNum();
			String[] subOrderNum = vo.getSubOrderNum();
			String[] score = vo.getScore();
			String[] parentId = vo.getParentId();
			String[] subQuestionId = vo.getSubQuestionId();
			int assign_id = vo.getExamAssignId();
			Assert.notNull(assign_id, "calculateScore方法中assign_id不能为空！");
			ExamAssignBean tempBean = examAssignInfoDaoMapper.selectTestTimes(assign_id);
			int testTimes = tempBean.getTestTimes();
			if(vo.getExamType() == Constant.EXAM_OFFICAL){//普通考试
				if(testTimes == 1){
					//积分管理
					integralManageService.handleIntegral(userId, 7026);
				}
			}
			//普通考试、赛程考试、课程考试
			if(vo.getExamType() == Constant.EXAM_OFFICAL || vo.getExamType() == Constant.EXAM_RACE || vo.getExamType() == Constant.EXAM_COURSE){
				//先删除原先的用户答案
				examUserAnswerDaoMapper.deleteUserAnswer(assign_id);
			}
			//将用户答案存往数据库
			//计算分数
			boolean flag = false;
			for(int i=0;i<questionId.length;i++){
				ExamUserAnswerBean answerBean = new ExamUserAnswerBean();
				if(!"6".equals(questionType[i])){//非组合题
					flag = isAnswer(questionId[i],questionType[i],userAnswer[i]);
					if(flag){
						totalScore += Integer.parseInt(score[i]);
						answerBean.setGetScore(Integer.parseInt(score[i]));
					}else{
						answerBean.setGetScore(0);
					}
				}else{//组合题
					answerBean.setGetScore(0);
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
										flag_zuhe = isAnswer(questionId[k], questionType[k], userAnswer[k]);
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
				//普通考试、赛程考试、课程考试需要保存答案
				if(vo.getExamType() == Constant.EXAM_OFFICAL || vo.getExamType() == Constant.EXAM_RACE || vo.getExamType() == Constant.EXAM_COURSE){
					answerBean.setExamAssignId(vo.getExamAssignId());
					answerBean.setQuestionId(Integer.parseInt(questionId[i]));
					if("3".equals(questionType[i])){//多选题
						answerBean.setAnswer(userAnswer[i].replace("##**##", ","));
					}else{
						answerBean.setAnswer(userAnswer[i].replace("@_@", ","));
					}
					answerBean.setScore(Integer.parseInt(score[i]));
					answerBean.setOrderNum(Integer.parseInt(orderNum[i]));
					answerBean.setSubOrderNum(Integer.parseInt(subOrderNum[i]));
					examUserAnswerDaoMapper.insertUserAnswer(answerBean);
					
				}
				
				//错题集中组合题只存大题题干ID
				//模拟考试时，错题自动加入错题集
				if(vo.getExamType() == Constant.EXAM_SIM){
					if("0".equals(parentId[i])){
						if(!flag){//如果题目错误，直接录入错题集
							ExamWrongCardBean wrongBean = new ExamWrongCardBean();
							wrongBean.setFromUserId(userId);
							wrongBean.setQuestionId(Integer.parseInt(questionId[i]));
							wrongBean.setJoinTime(new Date());
							wrongBean.setExamResultId(assign_id);
							wrongBean.setType(2);//错题类型 模拟考试-2
							if("3".equals(questionType[i])){//多选题
								wrongBean.setUserAnswer(userAnswer[i].replace("##**##", ","));
							}else{
								wrongBean.setUserAnswer(userAnswer[i]);
							}
							//wrongBean.setUserAnswer(userAnswer[i]);
							examWrongCardDaoMapper.insertWrongCard(wrongBean);
						}
					}
				}
			}
			//普通考试、赛程考试、课程考试
			if(vo.getExamType() == Constant.EXAM_OFFICAL || vo.getExamType() == Constant.EXAM_RACE || vo.getExamType() == Constant.EXAM_COURSE){
				List<ExamUserAnswerBean> ansList = getQuestionGetScore(assign_id);
				for(int i=0;i<ansList.size();i++){
					ExamUserAnswerBean bean = ansList.get(i);
					List<ExamUserAnswerBean> subAnswers = bean.getSubAnswers();
					if(subAnswers != null && !subAnswers.isEmpty()){
						int getScore = 0;
						for(int j=0;j<subAnswers.size();j++){
							getScore += subAnswers.get(j).getGetScore();
						}
						bean.setGetScore(getScore);
						modifyGetScore(bean);
					}
				}
			}
			//模拟考试不需要更新任何记录
			//if(vo.getExamType() != Constant.EXAM_SIM){
			//更新考试记录表
			ExamAssignBean bean = new ExamAssignBean();
			bean.setExamStatus(3);//已提交
			bean.setId(assign_id);
			bean.setScore(totalScore);
			bean.setExamSubmitTime(new Date());
			bean.setRemainingTime(vo.getRemainingTime());
			if(vo.getExamType() == Constant.EXAM_OFFICAL || vo.getExamType() == Constant.EXAM_RACE){//普通考试、赛程考试
				if(vo.getIsAutoMarking() == 1){
					bean.setIsMarked(true);
					bean.setExamStatus(4);
				}else{
					bean.setIsMarked(false);
				}
			}
			if(vo.getExamType() != Constant.EXAM_COURSE){//课程考试不需要更新以下信息
				int passScore = examAssignInfoDaoMapper.selectPassScore(assign_id);
				if(totalScore >= passScore){
					bean.setIsPassed(true);
					if(vo.getExamType() == Constant.EXAM_OFFICAL){//普通考试通过时需要增加积分
						if(testTimes == 1){
							//积分管理
							integralManageService.handleIntegral(userId, 7027);
						}
					}
				}else{
					bean.setIsPassed(false);
				}
			}
			examAssignInfoDaoMapper.updateExamAssignInfo(bean);
			//}
			//更新课程考试记录
			if(vo.getExamType() == Constant.EXAM_COURSE){
				courseRecordService.recordExamAndCourseAfterTest(vo.getExamRecordId(), vo.getRemainingTime(), totalScore, vo.getTotalScore());
			}
			//普通考试、赛程考试需要更新表中的批阅字段
			if(vo.getExamType() == Constant.EXAM_OFFICAL || vo.getExamType() == Constant.EXAM_RACE){
				if(vo.getIsAutoMarking() == 1){
					//更新考试信息表中的is_marked字段
					examMarkScoreService.modifyExamIsMarked(vo.getExamId(),"isScorePublished");
				}
			}
		}
		return totalScore;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isAnswer <BR>
	 * Description: 判断答案是否正确 ，不包含组合题<BR>
	 * Remark: <BR>
	 * @param questionId
	 * @param questionType
	 * @param userAnswer
	 * @return  boolean<BR>
	 */
	@Override
	public boolean isAnswer(String questionId,String questionType,String userAnswer){
		boolean flag = false;
		//如果用户答案为空，直接返回false
		if(userAnswer == null || "-".equals(userAnswer) || "".equals(userAnswer)){
			return flag;
		}
		String correctAnswer = "";
		if(!"6".equals(questionType)){
			correctAnswer = examUserAnswerDaoMapper.selectCorrectAnswer2(Integer.parseInt(questionId),Integer.parseInt(questionType));
		}
		if("1".equals(questionType) || "5".equals(questionType)){//主观题,答案关键字完全匹配
			if(correctAnswer != null){
				String[] answerKeys = StringUtils.split(correctAnswer, " ");
				int count = 0;
				for(int j=0;j<answerKeys.length;j++){
					if(userAnswer.contains(answerKeys[j])){
						count++ ;
					}
				}
				if(answerKeys.length == count){
					flag = true;
				}
			}
		}else if("2".equals(questionType)){//单选题
			if(userAnswer.equals(correctAnswer)){
				flag = true;
			}
		}else if("3".equals(questionType)){//多选题
			String[] ca = StringUtils.split(correctAnswer,",");
			Arrays.sort(ca);
			String[] ua = StringUtils.split(userAnswer,",");
			Arrays.sort(ua);
			if(StringUtils.join(ca).equals(StringUtils.join(ua))){
				flag = true;
			}
		}else if("4".equals(questionType)){//判断题
			if(userAnswer.equals(correctAnswer)){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isNumeric <BR>
	 * Description: 判断一个字符串是否都是数字 <BR>
	 * Remark: <BR>
	 * @param str
	 * @return  boolean<BR>
	 */
	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}
		return true;
	}
	
	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#updateExamAssignInfo(com.jftt.wifi.bean.exam.ExamAssignBean) <BR>
	 * Method name: updateExamAssignInfo <BR>
	 * Description: 更新考试信息 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	*/
	@Override
	public void updateExamAssignInfo(ExamAssignBean bean){
		examAssignInfoDaoMapper.updateExamAssignInfo(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#initialExamPaper(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: initialExamPaper <BR>
	 * Description: 初始化一份试卷 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	@Transactional
	public Object initialExamPaper(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		modifyExamAssignInfo(bean.getExam_assign_id());
		//获取试卷内容
		ExamScheduleVo vo = getExamScheduleVo(bean.getId());
		return vo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyExamAssignInfo <BR>
	 * Description: 更新考试记录表中的信息 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id  void<BR>
	 */
	@Override
	public void modifyExamAssignInfo(int exam_assign_id){
		//更新考试记录中的test_times与exam_begin_time
		ExamQueryConditionBean cbean = new ExamQueryConditionBean();
		cbean = examAssignInfoDaoMapper.selectInfoByAssignId(exam_assign_id);
		ExamAssignBean eBean = new ExamAssignBean();
		if(cbean.getExam_type() == 1 || cbean.getExam_type() == 2){//普通考试，赛程考试
			int exam_status = cbean.getExam_status();
			if(exam_status < 3){//3、已提交
				eBean.setExamStatus(2);//未提交
			}
		}
		eBean.setExamBeginTime(new Date());
		eBean.setTestTimes(1);
		eBean.setId(exam_assign_id);
		examAssignInfoDaoMapper.updateExamAssignInfo(eBean);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectInfoByAssignId <BR>
	 * Description: 通过ID查询一场考试所需要的一些重要参数 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamQueryConditionBean<BR>
	 */
	@Override
	public ExamQueryConditionBean selectInfoByAssignId(int id){
		return examAssignInfoDaoMapper.selectInfoByAssignId(id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamQuestionDetail <BR>
	 * Description: 根据assign_id查询答题情况 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	@Override
	public List<ExamUserAnswerBean> getExamQuestionDetail(int assign_id){
		List<ExamUserAnswerBean> answerList = examUserAnswerDaoMapper.selectExamQuestionDetail(assign_id);
		for(int i=0;i<answerList.size();i++){
			int order_num = answerList.get(i).getOrderNum();
			List<ExamUserAnswerBean> subAnswers = examUserAnswerDaoMapper.selectQuestionDetailByOrderNum(assign_id, order_num);
			answerList.get(i).setSubAnswers(subAnswers);
		}
		return answerList;  
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getQuestionGetScore <BR>
	 * Description: 查询答案的获取分数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  List<ExamUserAnswerBean><BR>
	 */
	@Override
	public List<ExamUserAnswerBean> getQuestionGetScore(int assign_id){
		List<ExamUserAnswerBean> answerList = examUserAnswerDaoMapper.selectQuestionGetScore(assign_id);
		for(int i=0;i<answerList.size();i++){
			int order_num = answerList.get(i).getOrderNum();
			List<ExamUserAnswerBean> subAnswers = examUserAnswerDaoMapper.selectQuestionGetScoreByOrderNum(assign_id, order_num);
			answerList.get(i).setSubAnswers(subAnswers);
		}
		return answerList;  
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyGetScore <BR>
	 * Description: 更新得分 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void modifyGetScore(ExamUserAnswerBean bean){
		examUserAnswerDaoMapper.updateScore(bean);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectExamGetScore <BR>
	 * Description: 查询答卷详情页面的得分总览 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  ExamGetScoreVo<BR>
	 */
	@Override
	public ExamGetScoreVo getExamGetScore(int assign_id){
		return examUserAnswerDaoMapper.selectExamGetScore(assign_id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: selectGradeOtherInfo <BR>
	 * Description: 查询成绩总览页面的其它信息，如考试时长、及格分、总分  <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamRecorderVo<BR>
	 */
	@Override
	public ExamRecorderVo getGradeOtherInfo(int id){
		return examAssignInfoDaoMapper.selectGradeOtherInfo(id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#addWrongCard(com.jftt.wifi.bean.ExamWrongCardBean) <BR>
	 * Method name: addWrongCard <BR>
	 * Description: 加入错题集 <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	*/
	
	@Override
	@Transactional
	public void addWrongCard(ExamWrongCardBean bean,int answer_id) {
		// TODO Auto-generated method stub
		//插入错题集
		examWrongCardDaoMapper.insertWrongCard(bean);
		//更新用户答案中的is_favorated
		examUserAnswerDaoMapper.updateFavorated(answer_id, 1);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getInfoByAssignId <BR>
	 * Description: 通过assign_id获取一些需要的参数 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  ExamQueryConditionBean<BR>
	 */
	@Override
	public ExamQueryConditionBean getInfoByAssignId(int exam_assign_id){
		ExamQueryConditionBean bean = examAssignInfoDaoMapper.selectInfoByAssignId(exam_assign_id);
		return bean;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCountByAssignId <BR>
	 * Description: 通过assign_id查询有无答案记录 ,若有返回true,若无返回false<BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  boolean<BR>
	 */
	@Override
	public boolean getCountByAssignId(int exam_assign_id){
		int count = examUserAnswerDaoMapper.selectCountByassignId(exam_assign_id);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getRuleList <BR>
	 * Description: 获取抽题规则 <BR>
	 * Remark: <BR>
	 * @param paper_id
	 * @return  ExamOrganizingRuleVo<BR>
	 */
	@Override
	public List<ExamOrganizingRuleVo> getRuleList(int paper_id){
		List<ExamOrganizingRuleBean> list = examOrganizingRuleDaoMapper.getRuleList(paper_id);
		List<ExamOrganizingRuleVo> voList = new ArrayList<ExamOrganizingRuleVo>();
		for(int i=0;i<list.size();i++){
			ExamOrganizingRuleVo vo = new ExamOrganizingRuleVo();
			vo.setType(list.get(i).getQuestionDisplayType());
			String[] ruleItem = StringUtils.split(list.get(i).getRule(), ';');
			int count = 0;
			for(int j=0;j<ruleItem.length;j++){
				count += Integer.parseInt(ruleItem[j].substring(ruleItem[j].indexOf(":", 0)+1, ruleItem[j].lastIndexOf(":")));
			}
			vo.setTypeCount(count);
			voList.add(vo);
		}
		return voList;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getLeaveTimes(int) <BR>
	 * Method name: getLeaveTimes <BR>
	 * Description: 获取考试离开次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  <BR>
	*/
	
	@Override
	public int getLeaveTimes(int assign_id) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectLeaveTimes(assign_id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#selectOrganizingMode(int) <BR>
	 * Method name: selectOrganizingMode <BR>
	 * Description: 通过assign_id查询组卷方式 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  <BR>
	*/
	
	@Override
	public ExamQueryConditionBean getTestDetailParam(int exam_assign_id) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectTestDetailParam(exam_assign_id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getExamTitle(int) <BR>
	 * Method name: getExamTitle <BR>
	 * Description: 获取一场考试的名称 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  <BR>
	*/
	
	@Override
	public String getExamTitle(int exam_assign_id) {
		// TODO Auto-generated method stub
		return examScheduleDaoMapper.selectExamTitle(exam_assign_id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#selectInfoById(int) <BR>
	 * Method name: selectInfoById <BR>
	 * Description: 通过考试ID查询确大赛考试信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/
	
	@Override
	public ExamQueryConditionBean selectInfoById(int id,int userId) {
		// TODO Auto-generated method stub
		return examScheduleDaoMapper.selectInfoById(id,userId);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getOrganizingMode <BR>
	 * Description: 通过考试ID查询试卷组卷类型 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  PaperBean<BR>
	 */
	@Override
	public ExamScheduleVo getOrganizingMode(int id){
		return examScheduleDaoMapper.selectOrganizingModeById(id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTestTimes <BR>
	 * Description: 获得考试次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  ExamAssignBean<BR>
	 */
	@Override
	public ExamAssignBean getTestTimes(int assign_id){
		return examAssignInfoDaoMapper.selectTestTimes(assign_id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getExamScheduleInfo(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExamScheduleInfo <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public ExamScheduleVo getExamScheduleInfo(int id) {
		// TODO Auto-generated method stub
		return examScheduleDaoMapper.selectExamScheduleInfo2(id);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getExerciseWrongCard(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExerciseWrongCard <BR>
	 * Description: 查询练习的错题集 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamWrongCardVo> getExerciseWrongCard(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		List<ExamWrongCardVo> list = examWrongCardDaoMapper.selectExerciseWrongByUserId(bean);
		for(int i=0;i<list.size();i++){
			String myAnswer = list.get(i).getMyAnswer();
			int type = list.get(i).getType();
			if(type == 3 || type == 2){//单选题或多选题
				if(!"-".equals(myAnswer) && !"".equals(myAnswer)){
					list.get(i).setMyAnswer(examWrongCardDaoMapper.selectChoiceAnswer2(list.get(i).getWrongId(), myAnswer));
				}
			}
		}
		return list;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getExerciseWrongCount(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getExerciseWrongCount <BR>
	 * Description: 查询练习的错题集条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public int getExerciseWrongCount(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examWrongCardDaoMapper.selectExerciseWrongCount(bean);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseWrongDetail <BR>
	 * Description: 获取练习中的错题详情 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ExamWrongCardVo<BR>
	 */
	@Override	
	@Transactional
	public ExamWrongCardVo getExerciseWrongDetail(int id){
		ExamWrongCardVo vo = examWrongCardDaoMapper.selectExerciseWrongDetail(id);
		QuestionBean qb = vo.getQuestionBean();
		/*String myAnswer = vo.getMyAnswer();
		if(qb.getType() == 3 || qb.getType() == 2){//单选题或多选题
			vo.setMyAnswer(examWrongCardDaoMapper.selectChoiceAnswer2(qb.getId(), myAnswer));
		}*/
		if(qb != null){
			if(qb.getQuestionIdList() != null && !"".equals(qb.getQuestionIdList())){
				//查询子试题列表
				//List<QuestionBean> subQuestions = examQuestionDaoMapper.selectQuestionListByIdList(qb.getQuestionIdList());
				List<QuestionBean> subQuestions = getSubQuestionsByIdList(qb.getQuestionIdList());
				for(int i=0;i<subQuestions.size();i++){
					String correctAnswer2 = selectCorrectAnswerByQuestionIdAndType(subQuestions.get(i).getId(),subQuestions.get(i).getType());
					subQuestions.get(i).setCorrectAnswer(correctAnswer2);
				}
				qb.setSubQuestions(subQuestions);
			}else{
				String correctAnswer = selectCorrectAnswerByQuestionIdAndType(qb.getId(),qb.getType());
				qb.setCorrectAnswer(correctAnswer);
			}
		}
		vo.setQuestionBean(qb);
		return vo;
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getSimExamWrongCount(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getSimExamWrongCount <BR>
	 * Description: 获取模拟考试错题集列表条数 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public int getSimExamWrongCount(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examWrongCardDaoMapper.selectSimExamWrongCount(bean);
	}

	/**
	 * @Override
	 * @author JFTT)zhangchen<BR>
	 * @see com.jftt.wifi.service.MyExamManageService#getSimWrongCard(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getSimWrongCard <BR>
	 * Description: 获取模拟考试错题集列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamWrongCardVo> getSimWrongCard(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		List<ExamWrongCardVo> list = examWrongCardDaoMapper.selectSimWrongCard(bean);
		for(int i=0;i<list.size();i++){
			if(!"-".equals(list.get(i).getMyAnswer())){
				//如果是单选与多选题，需要将答案中的ID换成对应的order_num
				int type = list.get(i).getType();
				if(type == 2 || type == 3){//单选题与多选题要查出选项的order_num
					if(!"-".equals(list.get(i).getMyAnswer()) && !"".equals(list.get(i).getMyAnswer())){
						ExamQueryConditionBean tempBean = new ExamQueryConditionBean();
						tempBean.setExam_assign_id(list.get(i).getAssignId());
						tempBean.setQuestion_id(list.get(i).getQuestionId());
						tempBean.setOption_id(list.get(i).getMyAnswer());
						String answer = examUserAnswerDaoMapper.selectChoiceAnswer(tempBean);
						list.get(i).setMyAnswer(answer);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCorrectAnswerByQuestionId <BR>
	 * Description: 通过questionId查询题目的正确答案 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  QuestionBean<BR>
	 */
	@Override
	public QuestionBean getCorrectAnswerByQuestionId(int id){
		QuestionBean qb = new QuestionBean();
		if(qb.getQuestionIdList() != null && !"".equals(qb.getQuestionIdList())){
			//查询子试题列表
			List<QuestionBean> subQuestions = getSubQuestionsByIdList(qb.getQuestionIdList());
			for(int i=0;i<subQuestions.size();i++){
				String correctAnswer2 = selectCorrectAnswerByQuestionIdAndType(subQuestions.get(i).getId(),subQuestions.get(i).getType());
				subQuestions.get(i).setCorrectAnswer(correctAnswer2);
			}
			qb.setSubQuestions(subQuestions);
		}else{
			String correctAnswer = selectCorrectAnswerByQuestionId(id);
			qb.setCorrectAnswer(correctAnswer);
		}
		return qb;
	}
	
	
	/** zhangbocheng start*/
	/**
	 * Method name: getExamAssignById <BR>
	 * Description: 根据id查询考试分配信息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  ExamAssignBean<BR>
	 */
	public ExamAssignBean getExamAssignById(Integer id) throws Exception{
	
		return examAssignInfoDaoMapper.getById(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyExamManageService#getMyExamRecorderVo(com.jftt.wifi.bean.ExamQueryConditionBean) <BR>
	 * Method name: getMyExamRecorderVoForMobile <BR>
	 * Description: 查询我的考试-参与考试记录-移动端 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  <BR>
	*/
	
	@Override
	public List<ExamRecorderVo> getMyExamRecorderVoForMobile(ExamQueryConditionBean bean) {
		// TODO Auto-generated method stub
		return examAssignInfoDaoMapper.selectExamAssignInfoByUserIdForMobiel(bean);
	}
	/**
	 * Method name: getExamRecorderCountForMobile <BR>
	 * Description: 获取我的考试记录条数-移动端 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  int<BR>
	 */
	public int getExamRecorderCountForMobile(ExamQueryConditionBean bean){
		return examAssignInfoDaoMapper.selectExamRecorderCountForMobile(bean);
	}
	/** zhangbocheng end*/
}
