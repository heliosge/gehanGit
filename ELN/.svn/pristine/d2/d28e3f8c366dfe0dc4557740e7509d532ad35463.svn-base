/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyMegagameAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.KnowledgeContestApplicationBean;
import com.jftt.wifi.bean.KnowledgeContestAwardBean;
import com.jftt.wifi.bean.KnowledgeContestContestBean;
import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.ApplicationsSerchParam;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.MegagameProcessInfoVo;
import com.jftt.wifi.bean.vo.MyApplicationsReturnVo;
import com.jftt.wifi.bean.vo.SearchJoinListParamsVo;
import com.jftt.wifi.bean.vo.SearchMegagameCheckParamVo;
import com.jftt.wifi.bean.vo.ShowWinAwardsVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.MyMegagameService;
import com.jftt.wifi.util.CommonUtil;

/**
 * class name:MyMegagameAction <BR>
 * class description: 我的大赛 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("MyMegagame")
public class MyMegagameAction {
	@Resource(name = "myMegagameService")
	private MyMegagameService myMegagameService;
	private static Logger logger = Logger.getLogger(MyMegagameAction.class);
	
	/**chenrui start*/
	
	/**
	 * @author JFTT)chenrui
	 * Method name: toMyCompetition <BR>
	 * Description: 跳转-我的比赛 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyCompetition")
	public String toMyCompetition(HttpServletRequest request){
		return "my_megagame/my_competition";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagame1 <BR>
	 * Description: tab-大赛首页 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameHome")
	public String toMegagameHome(HttpServletRequest request,String megagameId){
		request.setAttribute("megagameId", megagameId);
		return "my_megagame/megagame_home";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMatchPage <BR>
	 * Description: tab-赛程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMatchPage")
	public String toMatchPage(HttpServletRequest request,String megagameId,String matchId){
		request.setAttribute("matchId", matchId);
		request.setAttribute("megagameId", megagameId);
		return "my_megagame/matchPage";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagameScore <BR>
	 * Description: tab-跳转成绩公示 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameScore")
	public String toMegagameScore(HttpServletRequest request,String megagameId){
		request.setAttribute("megagameId", megagameId);
		return "my_megagame/megagame_score";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagameInformation <BR>
	 * Description: tab-跳转资讯页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameInformation")
	public String toMegagameInformation(HttpServletRequest request,String megagameId){
		request.setAttribute("megagameId", megagameId);
		return "my_megagame/megagame_information";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagameAware <BR>
	 * Description: tab-跳转获奖公示页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameAware")
	public String toMegagameAware(HttpServletRequest request,String megagameId){
		request.setAttribute("megagameId", megagameId);
		return "my_megagame/megagame_aware";
	}
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoMegagameCheck <BR>
	 * Description: 跳转至批阅大赛页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameCheck")
	public String toMegagameCheck(HttpServletRequest request){
		return "my_megagame/megagame_check";
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: toMyApplications <BR>
	 * Description: 跳转至我的报名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMyApplications")
	public String toMyApplications(HttpServletRequest request){
		return "my_megagame/my_applications";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagameInfo <BR>
	 * Description: 跳转-查看大赛 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameInfo")
	public String toMegagameInfo(HttpServletRequest request,String megagameId,String tabType){
		request.setAttribute("megagameId", megagameId);
		request.setAttribute("tabType", tabType);
		return "my_megagame/megagame_info";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagameInfo <BR>
	 * Description: 我的报名-查看详细 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  String<BR>
	 */
	@RequestMapping("toSeeMoreApplication")
	public String toSeeMoreApplication(HttpServletRequest request,String id,String megagameId){
		request.setAttribute("id", id);
		request.setAttribute("megagameId", megagameId);
		return "my_megagame/application_toSee";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMegagameList <BR>
	 * Description: 获取大赛列表信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMegagameList")
	public Object getMegagameList(HttpServletRequest request,MegagameListParamVo paramVo){
		logger.debug("MyMegagameAction执行getMegagameList方法");
		AjaxReturnVo<KnowledgeContestContestVo> arv = new AjaxReturnVo<KnowledgeContestContestVo>();
		try {
			/**
			 * 每次查询之前更新下当前大赛的状态，将时间到了未开始的状态更改成已开始
			 */
			myMegagameService.updateStatusByTime();
			int count = myMegagameService.getMegagameListCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			List<KnowledgeContestContestVo> list = myMegagameService.getMegagameList(paramVo);
			arv.setCount(count);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(MyMegagameAction.class,e);
		}
		return arv;
	}
	/**
	 * @author JFTT)chenrui
	 * Method name: getMegagameListCount <BR>
	 * Description: 获取大赛列表数目 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameName
	 * @param state
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("getMegagameListCount")
	public int getMegagameListCount(HttpServletRequest request,MegagameListParamVo paramVo){
		logger.debug("MyMegagameAction执行getMegagameListCount方法");
		int count = 0;
		try {
			count = myMegagameService.getMegagameListCount(paramVo);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: isApply <BR>
	 * Description: 判断是否已报名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("isApply")
	public KnowledgeContestApplicationBean isApply(HttpServletRequest request,String userId,String megagameId){
		logger.debug("MyMegagameAction执行isApply方法");
		try {
			return myMegagameService.isApply(userId,megagameId);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return null;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doApply <BR>
	 * Description: 报名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("doApply")
	public void doApply(HttpServletRequest request,String userId,String megagameId){
		logger.debug("MyMegagameAction执行doApply方法");
		try {
			myMegagameService.doApply(userId,megagameId);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
	}
	/**
	 * @author JFTT)chenrui
	 * Method name: getMegagameInfoById <BR>
	 * Description: 查看大赛详细信息 根据id来查询对应的大赛信息<BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMegagameInfoById")
	public Object getMegagameInfoById(HttpServletRequest request,String userId,String megagameId){
		logger.debug("MyMegagameAction执行getMegagameInfoById方法");
		AjaxReturnVo<KnowledgeContestContestBean> arv = new AjaxReturnVo<KnowledgeContestContestBean>();
		try {
			KnowledgeContestContestBean bean = myMegagameService.getMegagameInfoById(userId,megagameId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * @author JFTT)chenrui
	 * Method name: getGradeInfoPublic <BR>
	 * Description: 获取成绩公示数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getGradeInfoPublic")
	public Object getGradeInfoPublic(HttpServletRequest request,String userId,String processId,String page,String pageSize){
		logger.debug("MyMegagameAction执行getGradeInfoPublic方法");
		MMGridPageVoBean<ShowWinAwardsVo> mmVo = new MMGridPageVoBean<ShowWinAwardsVo>();
		try {
				int count = myMegagameService.getGradeInfoPublicCount(userId,processId);
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				List<ShowWinAwardsVo> list  = myMegagameService.getGradeInfoPublic(userId,processId,fromNum,pageSize);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return mmVo;
	}
	/**
	 * @author JFTT)chenrui
	 * Method name: getAwardsSetting <BR>
	 * Description: 获取奖项设置 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getAwardsSetting")
	public Object getAwardsSetting(HttpServletRequest request,String megagameId){
		logger.debug("MyMegagameAction执行getAwardsSetting方法");
		AjaxReturnVo<KnowledgeContestAwardBean> arv = new AjaxReturnVo<KnowledgeContestAwardBean>();
		try {
			List<KnowledgeContestAwardBean> list = myMegagameService.getAwardsSetting(megagameId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * @author JFTT)chenrui
	 * Method name: getMegagameProcessInfo <BR>
	 * Description: 获取大赛赛程安排信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMegagameProcessInfo")
	public Object getMegagameProcessInfo(HttpServletRequest request,String megagameId){
		logger.debug("MyMegagameAction执行getMegagameProcessInfo方法");
		AjaxReturnVo<MegagameProcessInfoVo> arv = new AjaxReturnVo<MegagameProcessInfoVo>();
		try {
			List<MegagameProcessInfoVo> list = myMegagameService.getMegagameProcessInfo(megagameId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMegagameProcessInfoById <BR>
	 * Description: 根据赛程id获取大赛赛程安排信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param processId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMegagameProcessInfoById")
	public Object getMegagameProcessInfoById(HttpServletRequest request,String processId){
		logger.debug("MyMegagameAction执行getMegagameProcessInfoById方法");
		AjaxReturnVo<MegagameProcessInfoVo> arv = new AjaxReturnVo<MegagameProcessInfoVo>();
		try {
			MegagameProcessInfoVo bean = myMegagameService.getMegagameProcessInfoById(processId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * @author JFTT)chenrui
	 * Method name: getShowWinAwards <BR>
	 * Description: 获奖公示-最终获奖者信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getShowWinAwards")
	public Object getShowWinAwards(HttpServletRequest request,String megagameId){
		logger.debug("MyMegagameAction执行getShowWinAwards方法");
		AjaxReturnVo<ShowWinAwardsVo> arv = new AjaxReturnVo<ShowWinAwardsVo>();
		try {
			List<ShowWinAwardsVo> list = myMegagameService.getShowWinAwards(megagameId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addJoinNotes <BR>
	 * Description:  添加参赛人员记录信息<BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("addJoinNotes")
	public void addJoinNotes(HttpServletRequest request,String userId,String megagameId,String matchId){
		logger.debug("MyMegagameAction执行addJoinNotes方法");
		try {
			myMegagameService.addJoinNotes(userId,megagameId,matchId);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPromoteInfoByProcessId <BR>
	 * Description: 根据赛程id获取当前赛程下的晋级者信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param processId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getPromoteInfoByProcessId")
	public Object getPromoteInfoByProcessId(HttpServletRequest request,String processId){
		logger.debug("MyMegagameAction执行getPromoteInfoByProcessId方法");
		AjaxReturnVo<ShowWinAwardsVo> arv = new AjaxReturnVo<ShowWinAwardsVo>();
		try {
			List<ShowWinAwardsVo> list = myMegagameService.getPromoteInfoByProcessId(processId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addExamAssignInfoByMatch <BR>
	 * Description: 添加examAssigninfo记录表信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param examId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("addExamAssignInfoByMatch")
	public Object addExamAssignInfoByMatch(HttpServletRequest request,String userId,String examId){
		logger.debug("MyMegagameAction执行addExamAssignInfoByMatch方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
			myMegagameService.addExamAssignInfoByMatch(userId,examId);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	
	/**
	 * @author JFTT)chenrui
	 * Method name: getMatchMessageList <BR>
	 * Description: 获取比赛资讯列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMatchMessageList")
	public Object getMatchMessageList(HttpServletRequest request,String megagameId,String page,String pageSize){
		logger.debug("MyMegagameAction执行getMatchMessageList方法");
		AjaxReturnVo<KnowledgeContestNewsBean> arv = new AjaxReturnVo<KnowledgeContestNewsBean>();
		try {
			int count = myMegagameService.getMatchMessageListCount(megagameId);
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			List<KnowledgeContestNewsBean> list = myMegagameService.getMatchMessageList(megagameId,fromNum,pageSize);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	@ResponseBody
	@RequestMapping("getMatchMessageListCount")
	public int getMatchMessageListCount(HttpServletRequest request,String megagameId){
		logger.debug("MyMegagameAction执行getMatchMessageListCount方法");
		int count = 0;
		try {
			count = myMegagameService.getMatchMessageListCount(megagameId);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return count;
	}
	/**
	 * @author JFTT)chenrui
	 * Method name: getMatchMessageById <BR>
	 * Description: 根据资讯ID获取资讯信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param messageId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMatchMessageById")
	public Object getMatchMessageById(HttpServletRequest request,String messageId){
		logger.debug("MyMegagameAction执行getMatchMessageById方法");
		AjaxReturnVo<KnowledgeContestNewsBean> arv = new AjaxReturnVo<KnowledgeContestNewsBean>();
		try {
			KnowledgeContestNewsBean bean = myMegagameService.getMatchMessageById(messageId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}	
	/**
	 * @author JFTT)chenrui
	 * Method name: getMyApplications <BR>
	 * Description: 我的报名获取 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMyApplications")
	public Object getMyApplications(HttpServletRequest request,ApplicationsSerchParam serchParam){
		logger.debug("MyMegagameAction执行getMyApplications方法");
		MMGridPageVoBean<MyApplicationsReturnVo> mmVo = new MMGridPageVoBean<MyApplicationsReturnVo>();
		try {
			int count = myMegagameService.getMyApplicationsCount(serchParam);
			String pageSize = serchParam.getPageSize();
			String page = serchParam.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			serchParam.setFromNum(fromNum);
			List<MyApplicationsReturnVo> list = myMegagameService.getMyApplications(serchParam);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAssignInfoByUserExamId <BR>
	 * Description: 根据用户 和考试id获取考试信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  List<QualificationVo><BR>
	 */
	@ResponseBody
	@RequestMapping("getAssignInfoByUserExamId")
	public ExamAssignBean getAssignInfoByUserExamId(HttpServletRequest request,String userId,String examId){
		logger.debug("MegagameManageAction执行getAssignInfoByUserExamId方法");
		try {
			return myMegagameService.getAssignInfoByUserExamId(userId,examId);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return null;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMyApplicationInfoById <BR>
	 * Description: 根据报名记录id获取我报名的详细信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param messageId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMyApplicationInfoById")
	public Object getMyApplicationInfoById(HttpServletRequest request,String id){
		logger.debug("MyMegagameAction执行getMyApplicationInfoById方法");
		AjaxReturnVo<KnowledgeContestApplicationBean> arv = new AjaxReturnVo<KnowledgeContestApplicationBean>();
		try {
			KnowledgeContestApplicationBean bean = myMegagameService.getMyApplicationInfoById(id);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}	
	/**
	 * @author JFTT)chenrui
	 * Method name: getJoinListByProcessId <BR>
	 * Description: 根据赛程id获取参赛列表信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameId
	 * @param processId
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getJoinListByProcessId")
	public Object getJoinListByProcessId(HttpServletRequest request,SearchJoinListParamsVo paramsVo){
		logger.debug("MyMegagameAction执行getJoinListByProcessId方法");
		MMGridPageVoBean<ShowWinAwardsVo> mmVo = new MMGridPageVoBean<ShowWinAwardsVo>();
		try {
			int count = myMegagameService.getJoinListByProcessIdCount(paramsVo);
			String pageSize = paramsVo.getPageSize();
			String page = paramsVo.getPage();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramsVo.setFromNum(fromNum);
			List<ShowWinAwardsVo> list=null;
			list  = myMegagameService.getJoinListByProcessId(paramsVo);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return mmVo;
	}
	
	
	/**
	 * @author JFTT)chenrui
	 * Method name: getMegagameCheckList <BR>
	 * Description: 批阅试卷大赛信息列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping("getMegagameCheckList")
	@ResponseBody
	public Object getMegagameCheckList(HttpServletRequest request,SearchMegagameCheckParamVo paramVo) throws Exception{
		logger.debug("MyMegagameAction执行getMegagameCheckList方法");
		List<KnowledgeContestContestVo> list  = myMegagameService.getMegagameCheckList(paramVo);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getMegagameCheckListCount <BR>
	 * Description: 获取大赛列表数目 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramVo
	 * @return  int<BR>
	 */
	
	@ResponseBody
	@RequestMapping("getMegagameCheckListCount")
	public int getMegagameCheckListCount(HttpServletRequest request,SearchMegagameCheckParamVo paramVo){
		logger.debug("MyMegagameAction执行getMegagameCheckListCount方法");
		int count = 0;
		try {
			count = myMegagameService.getMegagameCheckListCount(paramVo);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getLastMatchInfoBymegagameId <BR>
	 * Description:  根据大赛id获取最后一个赛程信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getLastMatchInfoBymegagameId")
	public Object getLastMatchInfoBymegagameId(HttpServletRequest request,String megagameId){
		logger.debug("MyMegagameAction执行getLastMatchInfoBymegagameId方法");
		AjaxReturnVo<KnowledgeContestMatchBean> arv = new AjaxReturnVo<KnowledgeContestMatchBean>();
		try {
			KnowledgeContestMatchBean bean  = myMegagameService.getJoinListByProcessId(megagameId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllMatchBymegagameId <BR>
	 * Description: 获取当前大赛下的所有赛程信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getAllMatchBymegagameId")
	public Object getAllMatchBymegagameId(HttpServletRequest request,String megagameId){
		logger.debug("MyMegagameAction执行getAllMatchBymegagameId方法");
		AjaxReturnVo<KnowledgeContestMatchBean> arv = new AjaxReturnVo<KnowledgeContestMatchBean>();
		try {
			List<KnowledgeContestMatchBean> list  = myMegagameService.getAllMatchBymegagameId(megagameId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkJoinQualification <BR>
	 * Description: 验证当前用户的报名审批状态是否为通过 ,进入考试的按钮控制<BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("checkJoinQualification")
	public int checkJoinQualification(HttpServletRequest request,String megagameId,String userId){
		logger.debug("MyMegagameAction执行checkJoinQualification方法");
		int count = 0;
		try {
			count  = myMegagameService.checkJoinQualification(userId,megagameId);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getLastPromoteInfo <BR>
	 * Description: 获取上一赛程的晋级状态 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @param orderNum
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("getLastPromoteInfo")
	public int getLastPromoteInfo(HttpServletRequest request,String megagameId,String orderNum){
		logger.debug("MyMegagameAction执行getLastPromoteInfo方法");
		int count =-1;
		try {
			String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
			count  = myMegagameService.getLastPromoteInfo(userId,megagameId,orderNum);
		} catch (Exception e) {
			logger.debug(MyMegagameAction.class,e);
		}
		return count;
	}
	
	/**chenrui end*/
}
