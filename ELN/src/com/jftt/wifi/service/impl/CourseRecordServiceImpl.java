/*
\ * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseRecordServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月15日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CourseExamRecordBean;
import com.jftt.wifi.bean.CourseStudyRecordBean;
import com.jftt.wifi.bean.CourseWareRecordBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.common.DataBaseConstant;
import com.jftt.wifi.dao.CourseExamRecordDaoMapper;
import com.jftt.wifi.dao.CourseStudyRecordDaoMapper;
import com.jftt.wifi.dao.CourseWareRecordDaoMapper;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamPaperDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseRecordService;
import com.jftt.wifi.service.IntegralManageService;

/**
 * class name:CourseRecordServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月15日
 * @author JFTT)ShenHaijun
 */
@Service("courseRecordService")
public class CourseRecordServiceImpl implements CourseRecordService{
	@Resource(name="courseStudyRecordDaoMapper")
	private CourseStudyRecordDaoMapper courseStudyRecordDaoMapper;
	@Resource(name="courseWareRecordDaoMapper")
	private CourseWareRecordDaoMapper courseWareRecordDaoMapper;
	@Resource(name="courseExamRecordDaoMapper")
	private CourseExamRecordDaoMapper courseExamRecordDaoMapper;
	@Resource(name="examScheduleDaoMapper")
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	@Resource(name="examPaperDaoMapper")
	private ExamPaperDaoMapper examPaperDaoMapper;
	@Resource(name="examAssignInfoDaoMapper")
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#recordCourseAndWare(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: recordCourseAndWare <BR>
	 * Description: 记录课程和课件学习情况 <BR>
	 * Remark: <BR>
	 * @param courseWareId 课件id
	 * @param courseWareType 课件类型
	 * @param userId 用户id
	 * @param sectionId 章节id
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recordCourseAndWare(Integer courseWareId,
			Integer courseWareType, Integer userId, Integer sectionId,
			Integer courseId, Integer companyId, Integer subCompanyId)
			throws Exception {
		//保存课程记录
		Integer courseRecordId = saveCourseRecord(courseId,userId,companyId,subCompanyId);
		//保存章节课件记录
		Integer wareRecordId = courseWareRecordDaoMapper.getIdByConditions(sectionId,courseWareId,userId);
		//保存课件记录
		CourseWareRecordBean wareRecord = new CourseWareRecordBean();
		if(wareRecordId == null){
			//设置插入记录参数
			wareRecord.setRecordId(courseRecordId);
			wareRecord.setSectionId(sectionId);
			wareRecord.setWareId(courseWareId);
			wareRecord.setWareType(courseWareType);
			wareRecord.setUserId(userId);
			wareRecord.setProgressPercent(0);//课件学习进度
			wareRecord.setCourseId(courseId);
			wareRecord.setCompanyId(companyId);
			wareRecord.setSubCompanyId(subCompanyId);
			wareRecord.setStartLearnTime(new Date());
			//插入记录
			courseWareRecordDaoMapper.addCourseWareRecordBean(wareRecord);
		}else{
			wareRecord.setId(wareRecordId);
			wareRecord.setStartLearnTime(new Date());
			courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#saveCourseRecord(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: saveCourseRecord <BR>
	 * Description: 保存课程记录 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer saveCourseRecord(Integer courseId,Integer userId,Integer companyId,Integer subCompanyId) throws Exception{
		//查询课程记录id
		Integer courseRecordId = courseStudyRecordDaoMapper.getIdByConditions(courseId,userId);
		if(courseRecordId == null){
			//设置插入记录参数
			CourseStudyRecordBean courseRecord = new CourseStudyRecordBean();
			courseRecord.setCourseId(courseId);
			courseRecord.setUserId(userId);
			courseRecord.setCompanyId(companyId);
			courseRecord.setSubCompanyId(subCompanyId);
			courseRecord.setLearnProcess(DataBaseConstant.COURSESTUDYRECORD_LEARNPROCESS_PROCESSING);//进行中
			courseRecord.setStartTime(new Date());
			courseRecord.setThisLearnTime(new Date());
			//插入记录
			courseStudyRecordDaoMapper.addCourseStudyRecordBean(courseRecord);
			courseRecordId = courseRecord.getId();
		}else{
			//更新课程记录
			CourseStudyRecordBean courseRecord = courseStudyRecordDaoMapper.getById(courseRecordId);
			courseRecord.setId(courseRecordId);
			courseRecord.setThisLearnTime(new Date());
			courseStudyRecordDaoMapper.updateCourseStudyRecordBean(courseRecord);
		}
		return courseRecordId;
	}
	
	/**
	 * Method name: isCoursePassed <BR>
	 * Description: 判断该课程是否已经学完 ，学完返回true，未学完返回false <BR>
	 * Remark: <BR>
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @return boolean
	 * @throws Exception  boolean<BR>
	 */
	private boolean isCoursePassed(Integer courseRecordId,Integer courseId) throws Exception{
		//判断课件学习进度
		boolean allStudyed = true;//是否相关课件全部学完
		//根据学习记录id查询出userId
		CourseStudyRecordBean courseRecord = courseStudyRecordDaoMapper.getById(courseRecordId); 
		//根据courseId和userId查询出该课程的所有课件的学习记录
		List<CourseWareRecordBean> wareRecords = new ArrayList<CourseWareRecordBean>();
		if(courseRecord != null && courseRecord.getUserId() != null){
			wareRecords = courseWareRecordDaoMapper.getWareRecordsByCourseIdUserId(courseId,courseRecord.getUserId());
		}
		if(wareRecords != null && wareRecords.size() > 0){
			for (int i = 0; i < wareRecords.size(); i++) {
				if(wareRecords.get(i) == null){
					allStudyed = false;//有课程没有学习记录，说明有课程没有学
				}else{
					if(wareRecords.get(i).getProgressPercent() == null || wareRecords.get(i).getProgressPercent() != 100){
						allStudyed = false;//如果课程中有没有学习进度的或者学习进度没有达到100的，就判断课程没有全部学完
					}
				}
			}
		}else{
			allStudyed = false;//没有课件学习记录说明还没有学
		}
		
		//判断该课程是否有章节测试，如果没有，当课件全部学完时，就判断课程已经学完
		List<PaperBean> papers = examPaperDaoMapper.getCoursePapers(courseId);
		if(papers == null || papers.size() <= 0){
			//课程没有章节测试，判断课件学习情况
			if(allStudyed == true){
				return true;//课程全部学完，返回true
			}else{
				return false;//课程没有全部学完，返回false
			}
		}else{
			//课程有章节测试，判断章节测试情况
			boolean allExamed = true;//是否相关测试全部通过
			List<CourseExamRecordBean> examRecords = courseExamRecordDaoMapper.getExamRecordsByCourseIdUserId(courseId,courseRecord.getUserId());
			if(examRecords != null && examRecords.size() > 0){
				for (int i = 0; i < examRecords.size(); i++) {
					if(examRecords.get(i) == null){
						allExamed = false;//有测试没有记录，说明有测试没有测
					}else{
						//如果超过了测试允许次数，就判断测试未通过
						if(examRecords.get(i).getTestTimes() != null && examRecords.get(i).getPassTimes() != null){
							if(examRecords.get(i).getTestTimes() > examRecords.get(i).getPassTimes()){
								allExamed = false;//如果考试次数大于允许次数，就判断测试没有通过
							}
						}else{
							allExamed = false;//为null判断没测试完
						}
						//如果历次考试的最高分都小于及格分，就判断测试未通过
						if(examRecords.get(i).getMaxScore() != null && examRecords.get(i).getPassScorePercent() != null && examRecords.get(i).getTotalScore() != null){
							if(examRecords.get(i).getMaxScore() < (examRecords.get(i).getPassScorePercent() * examRecords.get(i).getTotalScore())/100){
								allExamed = false;//如果历次考试最高分都小于及格分，就判断测试没有通过
							}
						}else{
							allExamed = false;//为null判断没测试完
						}
					}
				}
			}else{
				allExamed = false;//没有章节测试记录说明还没有测试
			}
			//如果该课程测试都通过了，就添加积分
			if(allExamed == true){
				integralManageService.handleIntegral(courseRecord.getUserId(), 7002);
			}
			//如果课件全部学完，并且测试全部通过，就判断该课程已经学完
			if(allStudyed == true && allExamed == true){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#recordExamAndCourseBeforeTest(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: recordExamAndCourseBeforeTest <BR>
	 * Description: 记录章节测试（测试前） <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param examId 试卷id
	 * @param userId 用户id
	 * @param courseId 课程id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param passTimes 允许次数
	 * @param passScorePercent 通过比例
	 * @return Integer
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer recordExamAndCourseBeforeTest(Integer sectionId,
			Integer examId, Integer userId, Integer courseId,
			Integer companyId, Integer subCompanyId,Integer passTimes,Integer passScorePercent) throws Exception {
		//保存课程记录
		Integer courseRecordId = saveCourseRecord(courseId,userId,companyId,subCompanyId);
		//保存测试记录
		Integer examRecordId = courseExamRecordDaoMapper.getIdByConditions(sectionId,examId,userId);
		if(examRecordId == null){
			CourseExamRecordBean examRecord = new CourseExamRecordBean();
			//设置插入记录参数
			examRecord.setRecordId(courseRecordId);
			examRecord.setCourseId(courseId);
			examRecord.setSectionId(sectionId);
			examRecord.setExamId(examId);
			examRecord.setUserId(userId);
			examRecord.setCompanyId(companyId);
			examRecord.setSubCompanyId(subCompanyId);
			examRecord.setTestTimes(1);//当前为第一次测试，设置测试次数为1
			examRecord.setPassTimes(passTimes);
			examRecord.setPassScorePercent(passScorePercent);
			//查询该exam的总分
			Integer totalScore = examPaperDaoMapper.getTotalScoreById(examId);
			examRecord.setTotalScore(totalScore);
			//插入记录
			courseExamRecordDaoMapper.addCourseExamRecordBean(examRecord);
			examRecordId = examRecord.getId();
		}else{
			CourseExamRecordBean examRecord = courseExamRecordDaoMapper.getById(examRecordId);
			examRecord.setId(examRecordId);
			examRecord.setTestTimes(examRecord.getTestTimes() + 1);//测试次数加1
			//更新记录
			courseExamRecordDaoMapper.updateCourseExamRecordBean(examRecord);
		}
		return examRecordId;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#recordExamAndCourseAfterTest(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: recordExamAndCourseAfterTest <BR>
	 * Description: 记录章节测试（测试后） <BR>
	 * Remark: <BR>
	 * @param examRecordId 考试记录id
	 * @param thisTestDuration 本次考试时长
	 * @param thisScore 本次得分
	 * @param totalScore 试卷总分
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recordExamAndCourseAfterTest(Integer examRecordId,
			Integer thisTestDuration, Integer thisScore,
			Integer totalScore) throws Exception {
		//记录测试情况
		CourseExamRecordBean examRecord = courseExamRecordDaoMapper.getById(examRecordId);
		//设置历次测试最高分
		Integer maxScore = examRecord.getMaxScore();
		if(maxScore == null){
			examRecord.setMaxScore(thisScore);
		}else{
			if(maxScore < thisScore){
				examRecord.setMaxScore(thisScore);
			}
		}
		//更新该条课程测试记录
		courseExamRecordDaoMapper.updateCourseExamRecordBean(examRecord);
		//判断课程的学习进度是否为已完成，如果已完成，则更新课程的学习进度
		if(isCoursePassed(examRecord.getRecordId(),examRecord.getCourseId()) == true){
			CourseStudyRecordBean courseRecord = new CourseStudyRecordBean();
			courseRecord.setId(examRecord.getRecordId());
			courseRecord.setLearnProcess(DataBaseConstant.COURSESTUDYRECORD_LEARNPROCESS_COMPLETED);//已完成
			courseRecord.setEndTime(new Date());
			courseStudyRecordDaoMapper.updateCourseStudyRecordBean(courseRecord);
			//设置积分（获得课程学分）
			integralManageService.handleIntegral(examRecord.getUserId(), 7001);
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#saveExamAndAssignInfo(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: saveExamAndAssignInfo <BR>
	 * Description: 保存考试和考试分配信息 <BR>
	 * Remark: <BR>
	 * @param examId 试卷id
	 * @param duration 时长
	 * @param allowTimes 允许次数
	 * @param passScorePercent 通过分数比例
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Map<String, Integer>
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Integer> saveExamAndAssignInfo(Integer examId, Integer duration,Integer allowTimes, Integer passScorePercent, 
			Integer userId,Integer companyId,Integer subCompanyId) throws Exception {
		//保存考试信息
		String title = examPaperDaoMapper.getPaper(examId).getTitle();
		Integer examScheduleId = examScheduleDaoMapper.getIdByConditions(title,examId,duration,allowTimes,passScorePercent,5,companyId,subCompanyId);
		if(examScheduleId == null){
			ExamScheduleBean examSchedule = new ExamScheduleBean();
			examSchedule.setTitle(title);
			examSchedule.setPaperId(examId);
			examSchedule.setDuration(duration);
			examSchedule.setDisplayMode(1);//整卷显示
			examSchedule.setRandomOrderMode(0);
			examSchedule.setAllowTimes(allowTimes);
			examSchedule.setPassScorePercent(passScorePercent);
			examSchedule.setType(5);//课程章节考试
			examSchedule.setIsAutoMarking(0);
			examSchedule.setIsScorePublic(1);
			examSchedule.setIsAntiCheating(0);
			examSchedule.setCompanyId(companyId);
			examSchedule.setSubCompanyId(subCompanyId);
			examScheduleDaoMapper.addExam(examSchedule);
			examScheduleId = examSchedule.getId();
		}
		//保存考试记录信息
		Integer assignId = examAssignInfoDaoMapper.getIdByConditions(1,examScheduleId,examId,userId);
		if(assignId == null){
			ExamAssignBean assignInfo = new ExamAssignBean();
			assignInfo.setRelationType(1);
			assignInfo.setRelationId(examScheduleId);
			assignInfo.setPaperId(examId);
			assignInfo.setUserId(userId);
			examAssignInfoDaoMapper.addAssignInfo(assignInfo);
			assignId = assignInfo.getId();
		}
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		returnMap.put("examScheduleId", examScheduleId);
		returnMap.put("assignId", assignId);
		return returnMap;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#getCourseWareByConditions(java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getCourseWareByConditions <BR>
	 * Description: 根据条件查询课件学习记录 <BR>
	 * Remark: <BR>
	 * @param sectionId 章节id
	 * @param courseWareId 课件id
	 * @param userId 用户id
	 * @return CourseWareRecordBean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public CourseWareRecordBean getCourseWareByConditions(Integer sectionId,
			Integer courseWareId, Integer userId) throws DataBaseException {
		return courseWareRecordDaoMapper.getCourseWareByConditions(sectionId,courseWareId,userId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#recordScromWareAfterLeavePage(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String[], java.lang.String[]) <BR>
	 * Method name: recordScromWareAfterLeavePage <BR>
	 * Description: 离开页面时记录scrom课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param total 总共
	 * @param studyed 已学
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recordScromWareAfterLeavePage(Integer wareRecordId,
			Integer courseRecordId,Integer courseId, Integer userId, String[] total, String[] studyed)
			throws Exception {
		if(studyed != null && total != null){
			//将传进来的数组和上次已学的数组合并，得到全部已学数组
			CourseWareRecordBean lastWareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			String[] allStydyed = megerStudyed(lastWareRecord.getStudyedContent(),studyed);
			Float studyedCount = (float) allStydyed.length;
			Integer progressPercent = (total.length == 0) ? 0 : (int) Math.floor((studyedCount/total.length)*100);
			//获取课件已有学习进度，如果学习进度大于已有学习进度则更新记录，否则不做处理
			Integer lastProgressPercent = lastWareRecord.getProgressPercent();
			CourseWareRecordBean wareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			Date endLearnTime = new Date();
			if(progressPercent > lastProgressPercent){
				//记录课件进度
				wareRecord.setProgressPercent(progressPercent);
				wareRecord.setStudyedContent(pressStringArrToString(allStydyed));
				wareRecord.setTotalContent(pressStringArrToString(total));
				wareRecord.setEndLearnTime(endLearnTime);
				Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
				if(wareRecord.getTotalDuration() != null){
					totalDuration += wareRecord.getTotalDuration();
				}
				wareRecord.setTotalDuration(totalDuration);
				courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
				//判断课程的学习进度是否为已完成，如果已完成，则更新课程的学习进度
				if(isCoursePassed(courseRecordId,courseId) == true){
					CourseStudyRecordBean courseRecord = new CourseStudyRecordBean();
					courseRecord.setId(courseRecordId);
					courseRecord.setLearnProcess(DataBaseConstant.COURSESTUDYRECORD_LEARNPROCESS_COMPLETED);//已完成
					courseRecord.setEndTime(new Date());
					courseStudyRecordDaoMapper.updateCourseStudyRecordBean(courseRecord);
					//如果课程已经学完，设置积分
					integralManageService.handleIntegral(userId, 7001);
				}
			}else{
				//仅更新课件学习记录的结束学习时间和总时长
				wareRecord.setEndLearnTime(endLearnTime);
				Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
				if(wareRecord.getTotalDuration() != null){
					totalDuration += wareRecord.getTotalDuration();
				}
				wareRecord.setTotalDuration(totalDuration);
				courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
			}
		}else{
			//仅更新课件学习记录的结束学习时间和总时长
			CourseWareRecordBean wareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			Date endLearnTime = new Date();
			wareRecord.setEndLearnTime(endLearnTime);
			Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
			if(wareRecord.getTotalDuration() != null){
				totalDuration += wareRecord.getTotalDuration();
			}
			wareRecord.setTotalDuration(totalDuration);
			courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
		}
	}
	
	/**
	 * 
	 * Method name: megerStudyed <BR>
	 * Description: 合并已学习的scrom课件 <BR>
	 * Remark: <BR>
	 * @param studyedContent 记录中已学scrom课件字符串
	 * @param studyed 传进来参数已学习内容字符串数组
	 * @return  String[] 合并后的字符串数组<BR>
	 */
	private String[] megerStudyed(String studyedContent, String[] studyed) {
		if(studyedContent != null){
			String[] lastStudyed = studyedContent.split(",");
			//Set是不允许重复的，利用Set特性去除掉两个String数组中重复的部分，并合并为一个数组
			Set<String> studyedSet = new HashSet<String>();
			for (int i = 0; i < lastStudyed.length; i++) {
				studyedSet.add(lastStudyed[i]);
			}
			for (int i = 0; i < studyed.length; i++) {
				studyedSet.add(studyed[i]);
			}
			//遍历Set，将值存入到新的String数组中
			Iterator<String> setIterator = studyedSet.iterator();
			String[] returnArrs = new String[studyedSet.size()];
			int num = 0;
			while(setIterator.hasNext()){
				String thisStr = setIterator.next();
				returnArrs[num] = thisStr;
				num = num + 1;
			}
			Arrays.sort(returnArrs);//对合并后的数组进行排序
			return returnArrs;
		}else{
			return studyed;//studyedContent为null，说明刚刚开始学习，返回已学的所有内容
		}
	}

	/**
	 * Method name: pressStringArrToString <BR>
	 * Description: 将String数组转化为String <BR>
	 * Remark: <BR>
	 * @param stringArr String数组
	 * @return  String<BR>
	 */
	private String pressStringArrToString(String[] stringArr) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < stringArr.length; i++) {
			buffer.append(stringArr[i]);
			if(i < stringArr.length -1){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#recordSwfWareAfterLeavePage(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String) <BR>
	 * Method name: recordSwfWareAfterLeavePage <BR>
	 * Description: 离开页面时记录swf课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param totalPages 总页数
	 * @param currPage 当前页
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recordSwfWareAfterLeavePage(Integer wareRecordId,
			Integer courseRecordId, Integer courseId, Integer userId,
			String totalPages, String currPage) throws Exception {
		if(totalPages != null && currPage != null){
			//课件记录参数
			Double currPageNum = Double.parseDouble(currPage);
			Integer progressPercent = ("0".equals(totalPages)) ? 0 : (int)Math.floor((currPageNum/Double.parseDouble(totalPages))*100);
			//获取课件已有学习进度，如果学习进度大于已有学习进度则更新记录，否则不做处理
			Integer lastProgressPercent = courseWareRecordDaoMapper.getById(wareRecordId).getProgressPercent();
			CourseWareRecordBean wareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			Date endLearnTime = new Date();
			if(progressPercent > lastProgressPercent){
				//记录课件进度
				wareRecord.setProgressPercent(progressPercent);
				wareRecord.setStudyedContent(currPage);
				wareRecord.setTotalContent(totalPages);
				wareRecord.setEndLearnTime(endLearnTime);
				Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
				if(wareRecord.getTotalDuration() != null){
					totalDuration += wareRecord.getTotalDuration();
				}
				wareRecord.setTotalDuration(totalDuration);
				courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
				//判断课程的学习进度是否为已完成，如果已完成，则更新课程的学习进度
				if(isCoursePassed(courseRecordId,courseId) == true){
					CourseStudyRecordBean courseRecord = new CourseStudyRecordBean();
					courseRecord.setId(courseRecordId);
					courseRecord.setLearnProcess(DataBaseConstant.COURSESTUDYRECORD_LEARNPROCESS_COMPLETED);//已完成
					courseRecord.setEndTime(new Date());
					courseStudyRecordDaoMapper.updateCourseStudyRecordBean(courseRecord);
					//如果课程已经学完，设置积分
					integralManageService.handleIntegral(userId, 7001);
				}
			}else{
				//仅更新课件学习记录的结束学习时间和总时长
				wareRecord.setEndLearnTime(endLearnTime);
				Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
				if(wareRecord.getTotalDuration() != null){
					totalDuration += wareRecord.getTotalDuration();
				}
				wareRecord.setTotalDuration(totalDuration);
				courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
			}
		}else{
			//仅更新课件学习记录的结束学习时间和总时长
			CourseWareRecordBean wareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			Date endLearnTime = new Date();
			wareRecord.setEndLearnTime(endLearnTime);
			Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
			if(wareRecord.getTotalDuration() != null){
				totalDuration += wareRecord.getTotalDuration();
			}
			wareRecord.setTotalDuration(totalDuration);
			courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseRecordService#recordVedioWareAfterLeavePage(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String) <BR>
	 * Method name: recordVedioWareAfterLeavePage <BR>
	 * Description: 离开页面时记录视频课件的学习进度 <BR>
	 * Remark: <BR>
	 * @param wareRecordId 课件记录id
	 * @param courseRecordId 课程记录id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param totalTime 总共时长
	 * @param currentTime 已有时长
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recordVedioWareAfterLeavePage(Integer wareRecordId,
			Integer courseRecordId, Integer courseId, Integer userId,
			String totalTime, String currentTime) throws Exception {
		if(currentTime != null && totalTime != null){
			//课件记录参数
			Double currentTimeNum = Double.parseDouble(currentTime);
			Integer progressPercent = ("0".equals(totalTime)) ? 0 :  (int)Math.floor((currentTimeNum/Double.parseDouble(totalTime))*100);
			//获取课件已有学习进度，如果学习进度大于已有学习进度则更新记录，否则不做处理
			Integer lastProgressPercent = courseWareRecordDaoMapper.getById(wareRecordId).getProgressPercent();
			CourseWareRecordBean wareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			Date endLearnTime = new Date();
			if(progressPercent > lastProgressPercent){
				//记录课件进度
				wareRecord.setProgressPercent(progressPercent);
				wareRecord.setStudyedContent(currentTime);
				wareRecord.setTotalContent(totalTime);
				wareRecord.setEndLearnTime(endLearnTime);
				Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
				if(wareRecord.getTotalDuration() != null){
					totalDuration += wareRecord.getTotalDuration();
				}
				wareRecord.setTotalDuration(totalDuration);
				courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
				//判断课程的学习进度是否为已完成，如果已完成，则更新课程的学习进度
				if(isCoursePassed(courseRecordId,courseId) == true){
					CourseStudyRecordBean courseRecord = new CourseStudyRecordBean();
					courseRecord.setId(courseRecordId);
					courseRecord.setLearnProcess(DataBaseConstant.COURSESTUDYRECORD_LEARNPROCESS_COMPLETED);//已完成
					courseRecord.setEndTime(new Date());
					courseStudyRecordDaoMapper.updateCourseStudyRecordBean(courseRecord);
					//如果课程已经学完，设置积分
					integralManageService.handleIntegral(userId, 7001);
				}
			}else{
				//仅更新课件学习记录的结束学习时间和总时长
				wareRecord.setEndLearnTime(endLearnTime);
				Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
				if(wareRecord.getTotalDuration() != null){
					totalDuration += wareRecord.getTotalDuration();
				}
				wareRecord.setTotalDuration(totalDuration);
				courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
			}
		}else{
			//仅更新课件学习记录的结束学习时间和总时长
			CourseWareRecordBean wareRecord = courseWareRecordDaoMapper.getById(wareRecordId);
			Date endLearnTime = new Date();
			wareRecord.setEndLearnTime(endLearnTime);
			Long totalDuration = (endLearnTime.getTime() - wareRecord.getStartLearnTime().getTime())/1000;
			if(wareRecord.getTotalDuration() != null){
				totalDuration += wareRecord.getTotalDuration();
			}
			wareRecord.setTotalDuration(totalDuration);
			courseWareRecordDaoMapper.updateCourseWareRecordBean(wareRecord);
		}
	}
	
	
	/**zhangbocheng mobile start */
	 /**
	  * 获取课程记录
	  * @param examRecordId
	  * @return CourseExamRecordBean
	  * @throws Exception
	  */
	public CourseExamRecordBean getExamRecordById(Integer examRecordId) throws Exception{
		return   courseExamRecordDaoMapper.getById(examRecordId);
	}
	/**zhangbocheng mobile end */
}
