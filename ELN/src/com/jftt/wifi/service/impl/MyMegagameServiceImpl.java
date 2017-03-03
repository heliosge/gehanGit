/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyMegagameServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.KnowledgeContestApplicationBean;
import com.jftt.wifi.bean.KnowledgeContestAwardBean;
import com.jftt.wifi.bean.KnowledgeContestContestBean;
import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.vo.ApplicationsSerchParam;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.MegagameProcessInfoVo;
import com.jftt.wifi.bean.vo.MyApplicationsReturnVo;
import com.jftt.wifi.bean.vo.SearchJoinListParamsVo;
import com.jftt.wifi.bean.vo.SearchMegagameCheckParamVo;
import com.jftt.wifi.bean.vo.ShowWinAwardsVo;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestApplicationDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestAwardDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestContestDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestMatchDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestNewsDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.MatchJoinUserDaoMapper;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.ExamService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyMegagameService;

/**
 * class name:MyMegagameServiceImpl <BR>
 * class description: 我的大赛 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
@Service("myMegagameService")
public class MyMegagameServiceImpl implements MyMegagameService {
	@Resource(name = "knowledgeContestContestDaoMapper")
	private KnowledgeContestContestDaoMapper knowledgeContestContestDaoMapper;
	@Resource(name = "knowledgeContestAwardDaoMapper")
	private KnowledgeContestAwardDaoMapper knowledgeContestAwardDaoMapper;
	@Resource(name = "knowledgeContestMatchDaoMapper")
	private KnowledgeContestMatchDaoMapper knowledgeContestMatchDaoMapper;
	@Resource(name = "manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	@Resource(name = "knowledgeContestNewsDaoMapper")
	private KnowledgeContestNewsDaoMapper knowledgeContestNewsDaoMapper;
	@Resource(name = "knowledgeContestApplicationDaoMapper")
	private KnowledgeContestApplicationDaoMapper knowledgeContestApplicationDaoMapper;
	@Resource(name = "matchJoinUserDaoMapper")
	private MatchJoinUserDaoMapper matchJoinUserDaoMapper;
	@Resource(name ="manageParamService")
	private ManageParamService manageParamService;
	@Resource(name = "manageUserService")
	private ManageUserService manageUserService;
	@Autowired
	private ExamAssignInfoDaoMapper assignInfoDaoMapper;
	@Autowired
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private ExamScheduleDaoMapper examScheduleDaoMapper;
	/**chenrui start*/
	/**
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMegagameList(java.lang.String) <BR>
	 * Method name: getMegagameList <BR>
	 * Description: 获取大赛列表信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<KnowledgeContestContestVo> getMegagameList(MegagameListParamVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		int postId = manageUserBean.getPostId();
		int deptId = manageUserBean.getDeptId();
		paramVo.setDeptId(deptId);
		paramVo.setPostId(postId);
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		return knowledgeContestContestDaoMapper.getMegagameList(paramVo);
	}
	@Override
	public int getMegagameListCount(MegagameListParamVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserByIdCondition(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		int postId = manageUserBean.getPostId()==null?-1:manageUserBean.getPostId();
		int deptId = manageUserBean.getDeptId()==null?-1:manageUserBean.getDeptId();
		paramVo.setDeptId(deptId);
		paramVo.setPostId(postId);
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		return knowledgeContestContestDaoMapper.getMegagameListCount(paramVo);
	}
	
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMegagameInfoById(java.lang.String, java.lang.String) <BR>
	 * Method name: getMegagameInfoById <BR>
	 * Description: 查看大赛详细信息 根据id来查询对应的大赛信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public KnowledgeContestContestBean getMegagameInfoById(String userId, String megagameId) throws Exception {
		if(megagameId!=null && !"".equals(megagameId)){
			return knowledgeContestContestDaoMapper.getById(Long.parseLong(megagameId));
		}
		return null;
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getAwardsSetting(java.lang.String) <BR>
	 * Method name: getAwardsSetting <BR>
	 * Description: 获取奖项设置 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<KnowledgeContestAwardBean> getAwardsSetting(String megagameId) throws Exception {
		return knowledgeContestAwardDaoMapper.getAwardsSetting(megagameId);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMegagameProcessInfo(java.lang.String) <BR>
	 * Method name: getMegagameProcessInfo <BR>
	 * Description:获取大赛赛程安排信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MegagameProcessInfoVo> getMegagameProcessInfo(String megagameId) throws Exception {
		return knowledgeContestMatchDaoMapper.getMegagameProcessInfo(megagameId);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#getMegagameProcessInfoById(java.lang.String) <BR>
	 * Method name: getMegagameProcessInfoById <BR>
	 * Description:根据赛程id获取大赛赛程安排信息  <BR>
	 * Remark: <BR>
	 * @param processId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public MegagameProcessInfoVo getMegagameProcessInfoById(String processId) throws Exception {
		return knowledgeContestMatchDaoMapper.getMegagameProcessInfoById(processId);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getShowWinAwards(java.lang.String) <BR>
	 * Method name: getShowWinAwards <BR>
	 * Description: 获奖公示-最终获奖者信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ShowWinAwardsVo> getShowWinAwards(String megagameId) throws Exception {
		//根据大赛id获取本次大赛最后一个赛程的详细信息
		KnowledgeContestMatchBean lastProcess = knowledgeContestMatchDaoMapper.getLastMatchInfo(megagameId);
		int examId = lastProcess.getExamId();
		int lastMatchId = lastProcess.getId();
		List<ShowWinAwardsVo> list = matchJoinUserDaoMapper.getShowWinAwards(megagameId,lastMatchId,examId);
		for(ShowWinAwardsVo sav : list){
			long userId = sav.getUserId();
			ManageUserBean userBean = manageUserService.findUserById(userId+"");
			Integer depId = userBean.getDeptId();
			String userName = userBean.getUserName();
			Integer postId = userBean.getPostId();
			String headImg = userBean.getHeadPhoto();
			//获取部门名称
			String departmentName = manageDepartmentDaoMapper.getManageDepartmentById(depId).getName();
			ManagePostBean postBean = manageParamService.selectManagePostById(postId);
			String postName = "";
			if(postBean!=null){
				postName = postBean.getName();
			}
			sav.setUserName(userName);
			sav.setPost(postName);
			sav.setDepartmentId(depId);
			sav.setDepartmentName(departmentName);
			sav.setHeadImg(headImg);
		}
		return list;
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#getPromoteInfoByProcessId(java.lang.String) <BR>
	 * Method name: getPromoteInfoByProcessId <BR>
	 * Description: 根据赛程id获取当前赛程下的晋级者信息 <BR>
	 * Remark: <BR>
	 * @param processId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ShowWinAwardsVo> getPromoteInfoByProcessId(String processId) throws Exception {
		List<ShowWinAwardsVo> list = matchJoinUserDaoMapper.getPromoteInfoByProcessId(processId);
		for(ShowWinAwardsVo sav : list){
			
			long userId = sav.getUserId();
			ManageUserBean userBean = manageUserService.findUserById(userId+"");
			Integer depId = userBean.getDeptId();
			String userName = userBean.getUserName();
			Integer postId = userBean.getPostId();
			String headImg = userBean.getHeadPhoto();
			
			//获取部门名称
			String departmentName = manageDepartmentDaoMapper.getManageDepartmentById(depId).getName();
			ManagePostBean postBean = manageParamService.selectManagePostById(postId);
			String postName = "";
			if(postBean!=null){
				postName = postBean.getName();
			}
			sav.setUserName(userName);
			sav.setPost(postName);
			sav.setDepartmentId(depId);
			sav.setDepartmentName(departmentName);
			sav.setHeadImg(headImg);
		}
		return list;
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMatchMessageList(java.lang.String) <BR>
	 * Method name: getMatchMessageList <BR>
	 * Description: 获取比赛资讯列表 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<KnowledgeContestNewsBean> getMatchMessageList(String megagameId,long fromNum,String pageSize) throws Exception {
		return knowledgeContestNewsDaoMapper.getMatchMessageList(megagameId,fromNum,pageSize);
	}
	@Override
	public int getMatchMessageListCount(String megagameId) throws Exception {
		return knowledgeContestNewsDaoMapper.getMatchMessageListCount(megagameId);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMatchMessageById(java.lang.String) <BR>
	 * Method name: getMatchMessageById <BR>
	 * Description: 根据资讯ID获取资讯信息 <BR>
	 * Remark: <BR>
	 * @param messageId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public KnowledgeContestNewsBean getMatchMessageById(String messageId) throws Exception {
		return knowledgeContestNewsDaoMapper.getById(Long.parseLong(messageId));
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMyApplications(java.lang.String) <BR>
	 * Method name: getMyApplications <BR>
	 * Description: 我的报名获取 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MyApplicationsReturnVo> getMyApplications(ApplicationsSerchParam serchParam) throws Exception {
		return knowledgeContestApplicationDaoMapper.getMyApplications(serchParam);
	}
	@Override
	public int getMyApplicationsCount(ApplicationsSerchParam serchParam) throws Exception {
		return knowledgeContestApplicationDaoMapper.getMyApplicationsCount(serchParam);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getGradeInfoPublic(java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: getGradeInfoPublic <BR>
	 * Description: 获取成绩公示数据 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @param processId
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ShowWinAwardsVo> getGradeInfoPublic(String userId,String processId,long fromNum,String pageSize) throws Exception {
		int examId = knowledgeContestMatchDaoMapper.getById(Long.parseLong(processId)).getExamId();
		int isAutoMark = examScheduleDaoMapper.getById(examId).getIsAutoMarking();
		List<ShowWinAwardsVo> list = matchJoinUserDaoMapper.getGradeInfoPublic(processId,fromNum,pageSize,isAutoMark);
		/*
		 * 获取当前赛程对应的考试信息
		 */
		for(ShowWinAwardsVo sav : list){
			long uid = sav.getUserId();
			ExamAssignBean assignBean = examAssignInfoDaoMapper.getAssignInfoByUserExamId(uid+"", examId+"");
			if(assignBean!=null){
				boolean isMark = assignBean.getIsMarked();
				if(!isMark && isAutoMark==0){//本场考试非自动批阅且当前人的成绩未批阅的，则不显示
					sav.setScore(-1);
				}
			}
			ManageUserBean userBean = manageUserService.findUserByIdCondition(uid+"");
			Integer depId = userBean.getDeptId();
			String userName = userBean.getName();
			//获取部门名称
			String departmentName = userBean.getDeptName();
			String postName = userBean.getPostName();
			sav.setUserName(userName);
			sav.setPost(postName);
			sav.setDepartmentId(depId);
			sav.setDepartmentName(departmentName);
		}
		return list;
	}
	@Override
	public int getGradeInfoPublicCount(String userId,String processId) throws Exception {
		return matchJoinUserDaoMapper.getGradeInfoPublicCount(processId);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getJoinListByProcessId(java.lang.String, java.lang.String, long, java.lang.String) <BR>
	 * Method name: getJoinListByProcessId <BR>
	 * Description: 根据赛程id获取参赛列表信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param processId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ShowWinAwardsVo> getJoinListByProcessId(SearchJoinListParamsVo paramsVo) throws Exception {
		String name = paramsVo.getName();
		String depName = paramsVo.getDepartment();
		String post = paramsVo.getPost();
		String processId = paramsVo.getProcessId();
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
		List<ManageUserBean> userList = null;
		if(map.size()>0){
			userList  = manageUserService.findUserByListCondition(map);
			if(userList==null || userList.size()==0){
				return new ArrayList<ShowWinAwardsVo>();
			}
		}
		int examId = knowledgeContestMatchDaoMapper.getById(Long.parseLong(processId)).getExamId();
		int isAutoMark = examScheduleDaoMapper.getById(examId).getIsAutoMarking();
		paramsVo.setIsAutoMark(isAutoMark);
		paramsVo.setUserList(userList);
		List<ShowWinAwardsVo> list = matchJoinUserDaoMapper.getJoinListByProcessId(paramsVo);
		//遍历 获取用户基本信息
		double tmpScore = 0;
		int tmpRank = 0;
		 for(ShowWinAwardsVo vo:list){
			long tepUserId = vo.getUserId();
			double score = vo.getScore();
			int rank = vo.getRownum();
			if(score==tmpScore){
				vo.setRownum(tmpRank);
			}else{
				tmpScore = score;
				tmpRank++;
				vo.setRownum(tmpRank);
			}
			ExamAssignBean assignBean = examAssignInfoDaoMapper.getAssignInfoByUserExamId(tepUserId+"", examId+"");
			boolean isMark = assignBean.getIsMarked();
			if(!isMark && isAutoMark==0){//本场考试非自动批阅且当前人的成绩未批阅的，则不显示
				vo.setScore(-1);
				vo.setRownum(-1);
			}

			ManageUserBean tepUser = manageUserService.findUserById(tepUserId+"");
			String userName = tepUser.getName();
			Integer postId = tepUser.getPostId();//岗位id
			Integer deptId = tepUser.getDeptId();
			if(postId==null){
				postId = 0;
			}
			if(deptId==null){
				deptId = 0;
			}
			String deptName = "";
			ManageDepartmentBean dpbean = manageDepartmentDaoMapper.getManageDepartmentById(deptId);
			if(dpbean!=null){
				deptName = dpbean.getName();
			}
			String postName = "";
			ManagePostBean mpbean = manageParamService.selectManagePostById(postId);
			if(mpbean!=null){
				postName = mpbean.getName();
			}
			vo.setDepartmentId(deptId);
			vo.setDepartmentName(deptName);
			vo.setPost(postName);
			vo.setUserName(userName);
		}
		return list;
	}
	@Override
	public int getJoinListByProcessIdCount(SearchJoinListParamsVo paramsVo) throws Exception {
		String name = paramsVo.getName();
		String depName = paramsVo.getDepartment();
		String post = paramsVo.getPost();
		String processId = paramsVo.getProcessId();
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
		List<ManageUserBean> userList = null;
		if(map.size()>0){
			userList  = manageUserService.findUserByListCondition(map);
			if(userList==null || userList.size()==0){
				return 0;
			}
		}
		int examId = knowledgeContestMatchDaoMapper.getById(Long.parseLong(processId)).getExamId();
		int isAutoMark = examScheduleDaoMapper.getById(examId).getIsAutoMarking();
		paramsVo.setIsAutoMark(isAutoMark);
		paramsVo.setUserList(userList);
		return matchJoinUserDaoMapper.getJoinListByProcessIdCount(paramsVo);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getLastPromoteInfo(java.lang.String, java.lang.String) <BR>
	 * Method name: getLastPromoteInfo <BR>
	 * Description: 获取上一赛程的晋级状态 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @param orderNum
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int getLastPromoteInfo(String userId,String megagameId, String orderNum) throws Exception {
			int matchId = knowledgeContestMatchDaoMapper.getMatchInfoByOrderNum(megagameId,orderNum).getId();
			Integer promoteFlag = matchJoinUserDaoMapper.getLastPromoteInfo(userId,matchId);
			if(promoteFlag!=null){
				return promoteFlag;
			}
			return 0;
	}
	
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#getMegagameCheckList(com.jftt.wifi.bean.vo.SearchMegagameCheckParamVo) <BR>
	 * Method name: getMegagameCheckList <BR>
	 * Description: 批阅试卷大赛信息列表 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<KnowledgeContestContestVo> getMegagameCheckList(SearchMegagameCheckParamVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		return knowledgeContestContestDaoMapper.getMegagameCheckList(paramVo);
	}
	@Override
	public int getMegagameCheckListCount(SearchMegagameCheckParamVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		paramVo.setCompanyId(companyId);
		paramVo.setSubCompanyId(subCompanyId);
		return knowledgeContestContestDaoMapper.getMegagameCheckListCount(paramVo);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#getJoinListByProcessId(java.lang.String) <BR>
	 * Method name:  根据大赛id获取最后一个赛程信息 <BR>
	 * Description:  <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public KnowledgeContestMatchBean getJoinListByProcessId(String megagameId) throws Exception {
		//根据大赛id获取本次大赛最后一个赛程的详细信息
		return  knowledgeContestMatchDaoMapper.getLastMatchInfo(megagameId);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#getAllMatchBymegagameId(java.lang.String) <BR>
	 * Method name: getAllMatchBymegagameId <BR>
	 * Description: 获取当前大赛下的所有赛程信息 <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<KnowledgeContestMatchBean> getAllMatchBymegagameId(String megagameId) throws Exception {
		return knowledgeContestMatchDaoMapper.getAllMatchBymegagameId(megagameId);
	}

	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#isApply(java.lang.String, java.lang.String) <BR>
	 * Method name: isApply <BR>
	 * Description: 判断是否已报名 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public KnowledgeContestApplicationBean isApply(String userId, String megagameId) throws Exception {
		KnowledgeContestApplicationBean bean =  knowledgeContestApplicationDaoMapper.isApply(userId,megagameId);
		return bean;
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#doApply(java.lang.String, java.lang.String) <BR>
	 * Method name: doApply <BR>
	 * Description: 报名 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public void doApply(String userId, String megagameId) throws Exception {
		KnowledgeContestApplicationBean applicationBean = isApply(userId, megagameId);
		if(applicationBean==null){//从未报名过
			knowledgeContestApplicationDaoMapper.doApply(userId,megagameId);
		}else{
			knowledgeContestApplicationDaoMapper.updateApply(userId,megagameId);
		}
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#getMyApplicationInfoById(java.lang.String, java.lang.String) <BR>
	 * Method name: getMyApplicationInfoById <BR>
	 * Description:根据大赛id获取我报名的详细信息  <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public KnowledgeContestApplicationBean getMyApplicationInfoById(String id) throws Exception {
		return knowledgeContestApplicationDaoMapper.getById(Long.parseLong(id));
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.MyMegagameService#checkJoinQualification(java.lang.String, java.lang.String) <BR>
	 * Method name: checkJoinQualification <BR>
	 * Description:   验证当前用户的报名审批状态是否为通过 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int checkJoinQualification(String userId, String megagameId) throws Exception {
		return knowledgeContestApplicationDaoMapper.checkJoinQualification(userId,megagameId);
	}
	
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#getAssignInfoByUserExamId(java.lang.String, java.lang.String) <BR>
	 * Method name: getAssignInfoByUserExamId <BR>
	 * Description: 根据用户 和考试id获取考试记录信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param examId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public ExamAssignBean getAssignInfoByUserExamId(String userId, String examId) throws Exception {
		return examAssignInfoDaoMapper.getAssignInfoByUserExamId(userId,examId);
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#addExamAssignInfoByMatch(java.lang.String, java.lang.String) <BR>
	 * Method name: addExamAssignInfoByMatch <BR>
	 * Description: 添加examAssigninfo记录表信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param examId
	 * @throws Exception  <BR>
	 */
	@Override
	public void addExamAssignInfoByMatch(String userId, String examId) throws Exception {
		/*
		 * 判断考试记录是否存在
		 */
		ExamAssignBean eb = assignInfoDaoMapper.getAssignInfoByUserExamId(userId,examId);
		
		if(eb==null){//不存在才插入考试记录
			//插入信息=>考试分配表
			 ExamScheduleBean examScheduleBean = examService.getExam(Integer.parseInt(examId));
			 //根据考试id获取绑定的试卷id
			 int paperId = examScheduleBean.getPaperId();
			 //根据试卷id获取试卷信息
			  PaperBean paperBean = examPaperService.getPaper(paperId);
			  int passScorePercent = examScheduleBean.getPassScorePercent();
			  int passScore = (int) (paperBean.getTotalScore()*((double)passScorePercent/100));
			  ExamAssignBean assignBean = new ExamAssignBean();
			  assignBean.setRelationType(1);
			  assignBean.setRelationId(Integer.parseInt(examId));
			  assignBean.setPaperId(paperId);
			  assignBean.setUserId(Integer.parseInt(userId));
			  assignBean.setExamStatus(1);
			  assignBean.setTestTimes(0);
			  assignBean.setIsPassed(false);
			  assignBean.setIsMarked(false);
			  assignBean.setPassScore(passScore);
			  assignBean.setLeaveTimes(examScheduleBean.getAllowTimes());
			  assignInfoDaoMapper.addAssignInfo(assignBean);
		}
	}
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.MyMegagameService#addJoinNotes(java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: addJoinNotes <BR>
	 * Description: 添加参赛人员记录信息 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param megagameId
	 * @param matchId
	 * @throws Exception  <BR>
	 */
	@Override
	public void addJoinNotes(String userId, String megagameId, String matchId) throws Exception {
		int count = matchJoinUserDaoMapper.checkExistJoinNote(userId,matchId);
		if(count==0){
			matchJoinUserDaoMapper.addJoinNotes(userId, megagameId, matchId);
		}
	}
	@Override
	public void updateStatusByTime() throws Exception {
		knowledgeContestContestDaoMapper.updateStatusByTime();
	}
	
	
	/**chenrui end*/
}
