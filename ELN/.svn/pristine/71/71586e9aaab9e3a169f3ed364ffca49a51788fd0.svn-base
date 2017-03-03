/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KnowledgeContestServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.KnowledgeContestApplyQualificationBean;
import com.jftt.wifi.bean.KnowledgeContestAwardBean;
import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.PaperCategoryBean;
import com.jftt.wifi.bean.knowledge_contest.ContestBean;
import com.jftt.wifi.bean.knowledge_contest.ContestConstants;
import com.jftt.wifi.bean.vo.AddCompetitionVo;
import com.jftt.wifi.bean.vo.GetAllPeopleByPostVo;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.QualificationVo;
import com.jftt.wifi.common.InstationMessageConstant;
import com.jftt.wifi.dao.ExamAssignInfoDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestApplyQualificationDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestAwardDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestContestDaoMapper;
import com.jftt.wifi.dao.KnowledgeContestMatchDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.dao.MatchJoinUserDaoMapper;
import com.jftt.wifi.service.KnowledgeContestService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageUserService;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
@Service
public class KnowledgeContestServiceImpl implements KnowledgeContestService {
	@Autowired
	KnowledgeContestContestDaoMapper contestDaoMapper;
	@Autowired
	KnowledgeContestMatchDaoMapper matchDaoMapper;
	@Autowired
	KnowledgeContestApplyQualificationDaoMapper applyQualificationDaoMapper;
	@Autowired
	KnowledgeContestAwardDaoMapper awardDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageDepartmentDaoMapper departmentDaoMapper;
	@Autowired
	private ManageCompanyService manageCompanyService;
	@Autowired
	private ManagePostDaoMapper managePostDaoMapper;
	@Autowired
	private ManageNoticeService manageNoticeService;
	@Autowired
	private ExamAssignInfoDaoMapper examAssignInfoDaoMapper;
	@Autowired
	private MatchJoinUserDaoMapper matchJoinUserDaoMapper;
	// wangyifeng begin
	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestService#addContest(com.jftt.wifi.bean.knowledge_contest.ContestBean) <BR>
	 * Method name: addContest <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param contest  <BR>
	*/

	@Override
	public void addContest(ContestBean contest) {
		// TODO Auto-generated method stub
		contestDaoMapper.addContest(contest);
		addContestAttachments(contest);
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: addContestAttachments <BR>
	 * Description: 新增大赛附属信息：报名资格、奖项 <BR>
	 * Remark: <BR>
	 * @param contest  void<BR>
	 */
	private void addContestAttachments(ContestBean contest) {
		for (KnowledgeContestApplyQualificationBean aq : contest
				.getApplyQualificationList()) {
			aq.setContestId(contest.getId());
			applyQualificationDaoMapper.addApplyQualification(aq);
		}
		int orderNum = 1;
		for (KnowledgeContestAwardBean award : contest.getAwardList()) {
			award.setContestId(contest.getId());
			award.setOrderNum(orderNum);
			awardDaoMapper.addAward(award);

			orderNum++;
		}
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: realDeleteContestAttachments <BR>
	 * Description: 物理删除大赛附属信息：报名资格、奖项 <BR>
	 * Remark: <BR>
	 * @param contestId  void<BR>
	 */
	private void realDeleteContestAttachments(Integer contestId) {
		applyQualificationDaoMapper.realDeleteApplyQualifications(contestId);
		awardDaoMapper.realDeleteAwards(contestId);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestService#deleteContest(java.lang.Integer) <BR>
	 * Method name: deleteContest <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/

	@Override
	public void deleteContest(Integer id) {
		// TODO Auto-generated method stub
		contestDaoMapper.deleteContest(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestService#modifyContest(com.jftt.wifi.bean.knowledge_contest.ContestBean) <BR>
	 * Method name: modifyContest <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param contest  <BR>
	*/

	@Override
	public void modifyContest(ContestBean contest) {
		// TODO Auto-generated method stub
		realDeleteContestAttachments(contest.getId());
		addContestAttachments(contest);
		contestDaoMapper.modifyContest(contest);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestService#publishContest(java.lang.Integer) <BR>
	 * Method name: publishContest <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/

	@Override
	public void publishContest(Integer id) {
		
		contestDaoMapper.publishContest(id);
	}

	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestService#getContest(java.lang.Integer) <BR>
	 * Method name: getContest <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  <BR>
	*/

	@Override
	public ContestBean getContest(Integer id) {
		// TODO Auto-generated method stub
		ContestBean contest = contestDaoMapper.getContest(id);
		contest.setApplyQualificationList(applyQualificationDaoMapper
				.getApplyQualificationList(id));
		contest.setAwardList(awardDaoMapper.getAwardList(id));
		return contest;
	}


	/**
	 * @Override
	 * @author JFTT)wangyifeng
	 * @see com.jftt.wifi.service.KnowledgeContestService#startNewMatchOrEndContest(java.lang.Integer) <BR>
	 * Method name: startNewMatchOrEndContest <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param contestId  <BR>
	*/

	@Override
	public void startNewMatchOrEndContest(Integer contestId) {
		ContestBean contest = contestDaoMapper.getContest(contestId);
		List<Integer> matchIdList = matchDaoMapper.getMatchIdListOrderByOrderNum(contestId);
		Integer idToStart = null;
		Integer idToEnd = null;
		if (contest.getStatus() == ContestConstants.CONTEST_STATUS_NOT_START) {
			contestDaoMapper.startFirstMatch(contestId);
			idToStart = matchIdList.get(0);
		} else if (contest.getStatus() == ContestConstants.CONTEST_STATUS_PROCESSING) {
			idToEnd = contest.getCurMatchId();
			int curMatchIndex = matchIdList.indexOf(contest.getCurMatchId());
			if (curMatchIndex + 1 != matchIdList.size()) {
				idToStart = matchIdList.get(curMatchIndex + 1);
				contestDaoMapper.startNextMatch(contestId, curMatchIndex + 1);
			} else {
				contestDaoMapper.finishLastMatch(contestId);
			}
		}
		if (idToStart != null) {
			matchDaoMapper.updateMatchStatus(idToStart,ContestConstants.MATCH_STATUS_PROCESSING);
		}
		if (idToEnd != null) {
			//idToEnd : 需 要结束的赛程id
			try {
				//当赛程结束时，对要结束的赛程进行晋级判定
				makePromote(idToEnd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			matchDaoMapper.updateMatchStatus(idToEnd,ContestConstants.MATCH_STATUS_OVER);
		}
	}
	// wangyifeng end
	
	/**chenrui start
	 * @throws Exception */
	@Transactional(rollbackFor=Exception.class)
	private void makePromote(int matchId) throws Exception{
		//获取到赛程的考试id
		KnowledgeContestMatchBean bean = matchDaoMapper.getById(matchId);
		int examId = bean.getExamId();
		//获取赛程允许最大晋级人数
		int promoteNum = bean.getPromotionNumber();
		//根据考试id、允许最大晋级人数 获取当前考试记录，最大人数多少就获取多少条 按分数倒序取
		List<ExamAssignBean> assignBeans= examAssignInfoDaoMapper.getInfoByPromoteNum(examId,promoteNum);
		for (ExamAssignBean examAssignBean : assignBeans) {
			int userId = examAssignBean.getUserId();
			matchJoinUserDaoMapper.updatePromoteStatus(userId, matchId+"", 1);
		}
	}
	
	/**
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#getContestVoList(com.jftt.wifi.bean.knowledge_contest.KnowledgeContestSearchOptionVo) <BR>
	 * Method name: getContestVoList <BR>
	 * Description: 管理员-获取大赛列表<BR>
	 * Remark: <BR>
	 * @return  <BR>
	 * @throws Exception 
	*/

	@Override
	public List<KnowledgeContestContestVo> getContestVoList(MegagameListParamVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean userBean = manageUserService.findUserById(userId);
		paramVo.setSubCompanyId(userBean.getSubCompanyId());
		paramVo.setCompanyId(userBean.getCompanyId());
		
		return contestDaoMapper.getManageContestVoList(paramVo);
	}

	/**
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#getContestTotalCount(com.jftt.wifi.bean.knowledge_contest.KnowledgeContestSearchOptionVo) <BR>
	 * Method name: getContestTotalCount <BR>
	 * Description: 管理员-获取大赛列表数目 <BR>
	 * Remark: <BR>
	 * @return  <BR>
	 * @throws Exception 
	*/

	@Override
	public Integer getContestTotalCount(MegagameListParamVo paramVo) throws Exception {
		String userId = paramVo.getUserId();
		ManageUserBean userBean = manageUserService.findUserById(userId);
		paramVo.setSubCompanyId(userBean.getSubCompanyId());
		paramVo.setCompanyId(userBean.getCompanyId());
		return contestDaoMapper.getManageContestTotalCount(paramVo);
	}
	
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#addCompetition(com.jftt.wifi.bean.vo.AddCompetitionVo) <BR>
	 * Method name: addCompetition <BR>
	 * Description: 大赛新增 <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer addCompetition(AddCompetitionVo paramVo) throws Exception {
		
		String operateType = paramVo.getOperateType();
		/*
		 * 获取当前用户公司id
		 */
	
		String uid = paramVo.getUserId();
		ManageUserBean userBean = manageUserService.findUserByIdCondition(uid);
		paramVo.setCompanyId(userBean.getCompanyId()+"");
		paramVo.setSubCompanyId(userBean.getSubCompanyId()+"");
		if("1".equals(operateType)){
			//新增大赛记录
			contestDaoMapper.addMegagame(paramVo);
		}else if("2".equals(operateType)){//修改操作
			contestDaoMapper.updateMegagame(paramVo);
		}
		
		/*
		 * 新增参赛资格记录
		 */
		String qualificationType = paramVo.getQualificationType();
		String postIdStr = paramVo.getPostId();
		String[] postIdArr = postIdStr.split(",");
		if("2".equals(operateType)){//对于奖项和参赛资格的修改，先清空，再重新添加
			applyQualificationDaoMapper.cleanRecord(paramVo.getId());//清空参赛资格
			awardDaoMapper.cleanRecord(paramVo.getId());//清空奖项
		}
		if(postIdArr.length>1){
			int postLen = postIdArr.length;
			for(int j=0;j<postLen;j++){
				KnowledgeContestApplyQualificationBean cBean = new KnowledgeContestApplyQualificationBean();
				cBean.setContestId(paramVo.getId());
				cBean.setQualificationType(Integer.parseInt(qualificationType));
				cBean.setQualificationId(Integer.parseInt(postIdArr[j]));
				applyQualificationDaoMapper.addApplyQualification(cBean);
			}
		}else{
			KnowledgeContestApplyQualificationBean cBean = new KnowledgeContestApplyQualificationBean();
			cBean.setContestId(paramVo.getId());
			cBean.setQualificationType(Integer.parseInt(qualificationType));
			cBean.setQualificationId(Integer.parseInt(postIdStr));
			applyQualificationDaoMapper.addApplyQualification(cBean);
		}
		
		/*
		 * 新增奖项数据
		 */
		String[] awardsNameStrs = paramVo.getAwardsNameStrs();//奖项名称
		String[] awardsCountsStrs = paramVo.getAwardsCountsStrs();//奖项数量
		String[] prizeNameStrs = paramVo.getPrizeNameStrs();//奖品名称
		String[] awardsPathStrs = paramVo.getAwardsPathStrs();//奖品图片路径
		String[] awardsNumsStrs = paramVo.getAwardsNumsStrs();//奖项序号
		int len = awardsNameStrs.length;
		
		for(int i=0;i<len;i++){
			KnowledgeContestAwardBean bean = new KnowledgeContestAwardBean();
			bean.setContestId(paramVo.getId());
			bean.setAwardName(awardsNameStrs[i]);
			bean.setTotalCount(Integer.parseInt(awardsCountsStrs[i]));
			bean.setPrizeName(prizeNameStrs[i]);
			bean.setCoverImage(awardsPathStrs[i]);
			bean.setOrderNum(Integer.parseInt(awardsNumsStrs[i]));
			awardDaoMapper.addAward(bean);
		}
		return paramVo.getId();
	}
	
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#getAllPeopleByPost(java.lang.String) <BR>
	 * Method name: getAllPeopleByPost <BR>
	 * Description:  根据选择的岗位获取人员信息  <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	
	@Override
	public List<ManageUserBean> getAllPeopleByDept(GetAllPeopleByPostVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		String userName = paramsVo.getUserName();
		String name = paramsVo.getName();
		String deptId = paramsVo.getDeptId();
		ArrayList<Integer> deptIdList = new ArrayList<Integer>();
		if(deptId!=null && !"".equals(deptId) && !deptId.startsWith("com")){
			getAllChildNodes(Integer.parseInt(deptId),deptIdList);
		}
		//当前用户
		ManageUserBean userBean = manageUserService.findUserById(userId);
		//获取公司id
		Integer comId = userBean.getCompanyId();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("companyId", comId);
		
		if(paramsVo.getAllCompany() !=null && paramsVo.getAllCompany().equals("all")){
			
		}else{
			map.put("subCompanyId", userBean.getSubCompanyId());
		}

		if(userName!=null && !"".equals(userName)){
			map.put("userName", userName);
		}
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(deptId!=null && !"".equals(deptId)){
			if(deptIdList!=null && deptIdList.size()>0){
				map.put("deptId", deptIdList);
			}
		}
		return manageUserService.findUserByListCondition(map, Integer.parseInt(paramsVo.getPage()), Integer.parseInt(paramsVo.getPageSize()));
	}
	
	@Override
	public int getAllPeopleByDeptCount(GetAllPeopleByPostVo paramsVo) throws Exception {
		String userId = paramsVo.getUserId();
		String userName = paramsVo.getUserName();
		String name = paramsVo.getName();
		String deptId = paramsVo.getDeptId();
		ArrayList<Integer> deptIdList = new ArrayList<Integer>();
		if(deptId!=null && !"".equals(deptId) && !deptId.startsWith("com")){
			getAllChildNodes(Integer.parseInt(deptId),deptIdList);
		}
		//当前用户
		ManageUserBean userBean = manageUserService.findUserById(userId);
		//获取公司id
		Integer comId = userBean.getCompanyId();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("companyId", comId);
		
		if(paramsVo.getAllCompany() !=null && paramsVo.getAllCompany().equals("all")){
			
		}else{
			map.put("subCompanyId", userBean.getSubCompanyId());
		}
		
		if(userName!=null && !"".equals(userName)){
			map.put("userName", userName);
		}
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(deptId!=null && !"".equals(deptId)){
			if(deptIdList!=null && deptIdList.size()>0){
				map.put("deptId", deptIdList);
			}
		}
		return manageUserService.findUserByListCondition(map).size();
	}
	
	@Override
	public List<Integer> getAllChildNodes(int deptId,List<Integer> result) throws Exception{
		Map map = new HashMap();
		map.put("parentId",deptId);
		List<ManageDepartmentBean> beans = departmentDaoMapper.getManageDepartmentByMap(map);
		result.add(deptId);
		if(beans!=null && beans.size()>0){
			for (ManageDepartmentBean manageDeptBean : beans) {
				int id = (int) manageDeptBean.getId();
				getAllChildNodes(id, result);
			}
		}
		return result;
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#getQualification(java.lang.String) <BR>
	 * Method name: getQualification <BR>
	 * Description:  大赛参赛资格获取<BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<QualificationVo> getQualification(String megagameId) throws Exception {
		List<KnowledgeContestApplyQualificationBean> li = applyQualificationDaoMapper.getApplyQualificationList(Integer.parseInt(megagameId));
		List<QualificationVo> list = new ArrayList<QualificationVo>();
		for(KnowledgeContestApplyQualificationBean be : li){
			QualificationVo vo = new QualificationVo();
			int type = be.getQualificationType();
			vo.setQualificationType(type+"");
			int qualificationId = be.getQualificationId();
			vo.setQualificationId(qualificationId+"");
			String name=null;
			if(type==1){//部门
				name = departmentDaoMapper.getManageDepartmentById(qualificationId).getName();
			}else if(type==2){//公司
				name = manageCompanyService.selectCompanyById(qualificationId).getName();
			}else if(type==3){//岗位
				name = managePostDaoMapper.getById(qualificationId).getName();
			}else if(type==4){//人员
				name = "所有人";
			}
			vo.setName(name);
			list.add(vo);
		}
		return list;
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#validatorNameIsOnly(java.lang.String) <BR>
	 * Method name: checkMegagameNameIsOnly <BR>
	 * Description:  新增大赛--验证大赛名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int checkMegagameNameIsOnly(String name,String userId,String megagameId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		
		return contestDaoMapper.checkMegagameNameIsOnly(name,companyId,subCompanyId,megagameId);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#checkMatchNameIsOnly(java.lang.String, java.lang.String) <BR>
	 * Method name: checkMatchNameIsOnly <BR>
	 * Description:  新增赛程--验证赛程名称的唯一性<BR>
	 * Remark: <BR>
	 * @param name
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int checkMatchNameIsOnly(String name, String userId,String matchId,String megagameId) throws Exception {
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);
		int companyId = manageUserBean.getCompanyId();
		int subCompanyId = manageUserBean.getSubCompanyId();
		return matchDaoMapper.checkMatchNameIsOnly(name,companyId,subCompanyId,matchId,megagameId);
	}
	/**
	 * 
	 * @Override
	 * @author JFTT)chenrui
	 * @see com.jftt.wifi.service.KnowledgeContestService#doPublish(java.lang.String) <BR>
	 * Method name: doPublish <BR>
	 * Description:大赛发布  <BR>
	 * Remark: <BR>
	 * @param megagameId
	 * @throws Exception  <BR>
	 */
	@Override
	public void doPublish(String megagameId,String currentUserId) throws Exception {
		String currentUserName = manageUserService.findUserById(currentUserId).getName();
		int matchId = matchDaoMapper.getDoPublish(megagameId);
		String megagameName = contestDaoMapper.getContest(Integer.parseInt(megagameId)).getName();
		//更新赛程状态
		matchDaoMapper.getDoPublish1(matchId);
		//更新大赛信息
		contestDaoMapper.getDoPublish1(megagameId,matchId);
		//获取大赛的开始时间(第一赛程的开始时间)
		Date startTime = contestDaoMapper.getContestStartTime(megagameId);
		//获取大赛的结束时间（最后赛程的结束时间）
		Date endTime = contestDaoMapper.getContestEndTime(megagameId);
		/*
		 * 站内信发送
		 */
		//获取当前大赛的参赛资格人员信息
		List<KnowledgeContestApplyQualificationBean> qualificationList = applyQualificationDaoMapper.getApplyQualificationList(Integer.parseInt(megagameId));
		Set<String> userIdSet = new HashSet<String>();
		for(KnowledgeContestApplyQualificationBean bb :qualificationList){
			int type = bb.getQualificationType();
			int quaId= bb.getQualificationId();
			if(type==2){//公司
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("companyId", quaId);
				List<ManageUserBean> userBeanListByCompany = manageUserService.findUserByListCondition(map);
				for(ManageUserBean ubc : userBeanListByCompany){
					userIdSet.add(ubc.getId());
				}
			}else if(type==3){//岗位
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("postId",quaId);
				List<ManageUserBean> userBeanListByPost = manageUserService.findUserByListCondition(map);
				for(ManageUserBean ubp : userBeanListByPost){
					userIdSet.add(ubp.getId());
				}
			}else if(type==4){//所有人
				List<ManageUserBean> userBeanAll = manageUserService.findAll();
				for(ManageUserBean ubAll : userBeanAll){
					userIdSet.add(ubAll.getId());
				}
			}
		}
		for(String usId:userIdSet){
			ManageNoticeBean noticeBean = new ManageNoticeBean();
			String userName = manageUserService.findUserById(usId).getName();
			noticeBean.setTitle(InstationMessageConstant.applyInform);
			noticeBean.setContent(InstationMessageConstant.getApplyContent(userName, megagameName, startTime, endTime));
			noticeBean.setSendUserId(Integer.parseInt(currentUserId));
			noticeBean.setSendUserName(currentUserName);
			noticeBean.setSendTime(new Date());
			noticeBean.setRecUserId(Integer.parseInt(usId));
			noticeBean.setRecUserName(userName);
			noticeBean.setIsRead(2);
			noticeBean.setIsSystem(1);
			noticeBean.setSendDeleteFlag(1);
			noticeBean.setRecDeleteFlag(1);
			manageNoticeService.insertNotice(noticeBean);
		}
		
	}
	
	/**chenrui end*/

}
