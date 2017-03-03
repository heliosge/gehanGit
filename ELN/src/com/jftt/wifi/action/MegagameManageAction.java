/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MegagameManageAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-25        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.KnowledgeContestContestBean;
import com.jftt.wifi.bean.KnowledgeContestMatchBean;
import com.jftt.wifi.bean.KnowledgeContestNewsBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestApplicationSearchOption;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsListItemVo;
import com.jftt.wifi.bean.knowledge_contest.ContestNewsSearchOption;
import com.jftt.wifi.bean.knowledge_contest.KnowledgeContestSearchOptionVo;
import com.jftt.wifi.bean.vo.AddCompetitionVo;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.GetAllPeopleByPostVo;
import com.jftt.wifi.bean.vo.KnowledgeContestContestVo;
import com.jftt.wifi.bean.vo.ManagerContestMatchVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.QualificationVo;
import com.jftt.wifi.bean.vo.SaveMatchParamVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ExamPaperService;
import com.jftt.wifi.service.KnowledgeContestApplicationService;
import com.jftt.wifi.service.KnowledgeContestMatchService;
import com.jftt.wifi.service.KnowledgeContestNewsService;
import com.jftt.wifi.service.KnowledgeContestService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.MyMegagameService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * @author JFTT)chenrui
 * class name:MegagameManageAction <BR>
 * class description: 管理员模块-大赛管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-25
 * @author JFTT)chenrui
 */
@Controller
@RequestMapping("megagameManage")
public class MegagameManageAction {
	private static Logger logger = Logger.getLogger(MegagameManageAction.class);
	@Autowired
	private KnowledgeContestService knowledgeContestService;
	@Autowired
	private KnowledgeContestNewsService knowledgeContestNewsService;
	@Autowired
	private KnowledgeContestMatchService knowledgeContestMatchService;
	@Autowired
	private KnowledgeContestApplicationService knowledgeContestApplicationService;
	@Resource(name = "myMegagameService")
	private MyMegagameService myMegagameService;
	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private ManageDepartmentService manageDepartmentService;  
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toSeeCompetition <BR>
	 * Description: 管理员-跳转页面 查看大赛详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  String<BR>
	 */
	@RequestMapping("toSeeCompetition")
	public String toSeeCompetition(HttpServletRequest request,String megagameId){
		request.setAttribute("megagameId", megagameId);
		return "manage_megagame/see_competition";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toMegagameList <BR>
	 * Description: 管理-管理我的大赛列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toMegagameList")
	public String toMegagameList(HttpServletRequest request){
		return "manage_megagame/manage_competition";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toAddMegagame <BR>
	 * Description: 跳转-大赛新增页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toAddMegagame")
	public String toAddMegagame(HttpServletRequest request,String megagameId,String operateType){
		request.setAttribute("megagameId", megagameId);
		request.setAttribute("operateType", operateType);
		return "manage_megagame/competition_add";
	}
	
	@RequestMapping("toAddMatch")
	public String toAddMatch(HttpServletRequest request,String megagameId,String operateType,String matchId){
		request.setAttribute("megagameId", megagameId);
		request.setAttribute("matchId", matchId);
		request.setAttribute("operateType", operateType);
		return "manage_megagame/competition_addMatch";
	}
	/**chenrui start*/
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getExamInfoForMatch <BR>
	 * Description: 根据考试id获取赛程关联的考试信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getExamInfoForMatch")
	public Object getExamInfoForMatch(HttpServletRequest request,String examId){
		logger.debug("MegagameManageAction执行getExamInfoForMatch方法");
		AjaxReturnVo<ExamScheduleVo> arv = new AjaxReturnVo<ExamScheduleVo>();
		try {
			ExamScheduleVo bean = knowledgeContestMatchService.getExamInfoForMatch(examId);
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMatchCounts <BR>
	 * Description: 获取大赛下赛程数目 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("getMatchCounts")
	public int getMatchCounts(HttpServletRequest request,String megagameId){
		logger.debug("MegagameManageAction执行getMatchCounts方法");
		int count=0;
		try {
			count = knowledgeContestMatchService.getMatchCounts(megagameId);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return count;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMessageList <BR>
	 * Description: 获取大赛资讯列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param paramsVo
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMessageList")
	public Object getMessageList(HttpServletRequest request,ContestNewsSearchOption paramsVo){
		logger.debug("MegagameManageAction执行getMessageList方法");
		MMGridPageVoBean<ContestNewsListItemVo> mmVo = new MMGridPageVoBean<ContestNewsListItemVo>();
		try {
			int count = knowledgeContestNewsService.getNewsTotalCount(paramsVo);
			List<ContestNewsListItemVo> list  = knowledgeContestNewsService.getNewsVoList(paramsVo);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllMatchInfo <BR>
	 * Description: 获取当前大赛下的所有赛程信息及赛程中考试、试卷的基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param messageId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getAllMatchInfo")
	public Object getAllMatchInfo(HttpServletRequest request,String megagameId){
		logger.debug("MegagameManageAction执行getAllMatchInfo方法");
		AjaxReturnVo<ManagerContestMatchVo> arv = new AjaxReturnVo<ManagerContestMatchVo>();
		try {
			List<ManagerContestMatchVo> list = knowledgeContestMatchService.getAllMatchInfo(megagameId);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMegagameListByM <BR>
	 * Description: 获取大赛列表信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param megagameName
	 * @param state
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getContestVoList")
	public Object getMegagameListByM(HttpServletRequest request,MegagameListParamVo paramVo){
		logger.debug("MegagameManageAction执行getContestVoList方法");
		AjaxReturnVo<KnowledgeContestContestVo> arv = new AjaxReturnVo<KnowledgeContestContestVo>();
		try {
			/**
			 * 每次查询之前更新下当前大赛的状态，将时间到了未开始的状态更改成已开始
			 */
			myMegagameService.updateStatusByTime();
			int count = knowledgeContestService.getContestTotalCount(paramVo);
			String page = paramVo.getPage();
			String pageSize = paramVo.getPageSize();
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramVo.setFromNum(fromNum);
			List<KnowledgeContestContestVo> list = knowledgeContestService.getContestVoList(paramVo);
			arv.setCount(count);
			arv.setRtnDataList(list);
		} catch (Exception e) {
			arv.setRtnResult(Constant.AJAX_FAIL);
			logger.debug(MegagameManageAction.class,e);
		}
		return arv;
	}
	
	@ResponseBody
	@RequestMapping("getContestVoListCount")
	public int getContestVoListCount(HttpServletRequest request,MegagameListParamVo paramVo){
		logger.debug("MegagameManageAction执行getContestVoListCount方法");
		int count = 0;
		try {
			count = knowledgeContestService.getContestTotalCount(paramVo);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return count;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doPublish <BR>
	 * Description: 大赛发布 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("doPublish")
	public void doPublish(HttpServletRequest request,String megagameId){
		logger.debug("MegagameManageAction执行doPublish方法");
		try {
			String currentUserId = (String) request.getSession().getAttribute("USER_ID_LONG");
			knowledgeContestService.doPublish(megagameId,currentUserId);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: deleteContest <BR>
	 * Description: 删除大赛 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("deleteContest")
	public void deleteContest(HttpServletRequest request,String id){
		logger.debug("MegagameManageAction执行deleteContest方法");
		try {
			knowledgeContestService.deleteContest(Integer.parseInt(id));
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getQualification <BR>
	 * Description: 获取参赛资格 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param megagameId  void<BR>
	 */
	@ResponseBody
	@RequestMapping("getQualification")
	public List<QualificationVo> getQualification(HttpServletRequest request,String megagameId){
		logger.debug("MegagameManageAction执行getQualification方法");
		try {
			return knowledgeContestService.getQualification(megagameId);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return null;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addOrUpdateCompetition <BR>
	 * Description: 新增/更新大赛 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("addOrUpdateCompetition")
	public Integer addOrUpdateCompetition(HttpServletRequest request,String awardsName,String awardsCounts,String prizeName,AddCompetitionVo paramVo){
		logger.debug("MegagameManageAction执行addOrUpdateCompetition方法");
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		paramVo.setUserId(userId);
		paramVo.setAwardsNameStrs(awardsName.split(","));
		paramVo.setAwardsCountsStrs(awardsCounts.split(","));
		paramVo.setPrizeNameStrs(prizeName.split(","));
		/*paramVo.setAwardsNameStrs(request.getParameterValues("awardsName[]"));
		paramVo.setAwardsCountsStrs(request.getParameterValues("awardsCounts[]"));
		paramVo.setPrizeNameStrs(request.getParameterValues("prizeName[]"));*/
		paramVo.setAwardsPathStrs(request.getParameterValues("awardsPath[]"));
		paramVo.setAwardsNumsStrs(request.getParameterValues("awardsNums[]"));
		try {
			return knowledgeContestService.addCompetition(paramVo);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return null;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkMegagameNameIsOnly <BR>
	 * Description: 新增大赛--验证大赛名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param name
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("checkMegagameNameIsOnly")
	public int checkMegagameNameIsOnly(HttpServletRequest request,String name,String userId,String megagameId){
		logger.debug("MegagameManageAction执行checkMegagameNameIsOnly方法");
		try {
			return knowledgeContestService.checkMegagameNameIsOnly(name,userId,megagameId);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return 0;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: checkMatchNameIsOnly <BR>
	 * Description: 新增赛程--验证赛程名称的唯一性 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param name
	 * @param userId
	 * @return  int<BR>
	 */
	@ResponseBody
	@RequestMapping("checkMatchNameIsOnly")
	public int checkMatchNameIsOnly(HttpServletRequest request,String name,String userId,String matchId,String megagameId){
		logger.debug("MegagameManageAction执行checkMatchNameIsOnly方法");
		try {
			return knowledgeContestService.checkMatchNameIsOnly(name,userId,matchId,megagameId);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return 0;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getMatchInfoById <BR>
	 * Description: 根据赛程id获取赛程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param matchId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getMatchInfoById")
	public Object getMatchInfoById(HttpServletRequest request,String matchId){
		logger.debug("MyMegagameAction执行getMatchInfoById方法");
		AjaxReturnVo<KnowledgeContestMatchBean> arv = new AjaxReturnVo<KnowledgeContestMatchBean>();
		try {
			KnowledgeContestMatchBean bean  = knowledgeContestMatchService.getMatch(Integer.parseInt(matchId));
			arv.setRtnData(bean);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
			arv.setRtnResult(Constant.AJAX_FAIL);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getAllPeopleByPost <BR>
	 * Description: 根据岗位获取人员信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userName
	 * @param name
	 * @param postId
	 * @param userId
	 * @return  Object<BR>
	 */
	/*@ResponseBody
	@RequestMapping("getAllPeopleByPost")
	public Object getAllPeopleByPost(HttpServletRequest request,GetAllPeopleByPostVo paramsVo){
		logger.debug("MegagameManageAction执行getAllPeopleByPost方法");
		MMGridPageVoBean<ManageUserBean> mmVo = new MMGridPageVoBean<ManageUserBean>();
		try {
			String page = paramsVo.getPage();
			String pageSize = paramsVo.getPageSize();
			int count = knowledgeContestService.getAllPeopleByPostCount(paramsVo);
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramsVo.setFromNum(fromNum);
			List<ManageUserBean> list  = knowledgeContestService.getAllPeopleByPost(paramsVo);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return mmVo;
	}*/
	
	@ResponseBody
	@RequestMapping("getAllPeopleByDept")
	public Object getAllPeopleByDept(HttpServletRequest request,GetAllPeopleByPostVo paramsVo){
		logger.debug("MegagameManageAction执行getAllPeopleByDept方法");
		MMGridPageVoBean<ManageUserBean> mmVo = new MMGridPageVoBean<ManageUserBean>();
		try {
			String page = paramsVo.getPage();
			String pageSize = paramsVo.getPageSize();
			int count = knowledgeContestService.getAllPeopleByDeptCount(paramsVo);
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			paramsVo.setFromNum(fromNum);
			List<ManageUserBean> list  = knowledgeContestService.getAllPeopleByDept(paramsVo);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getPaperByCategory <BR>
	 * Description: 根据试卷分类id获取试卷信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param categoryId
	 * @param name
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getPaperByCategory")
	public Object getPaperByCategory(HttpServletRequest request,String categoryId,String paperName,String page,String pageSize){
		logger.debug("MegagameManageAction执行getAllPeopleByPost方法");
		MMGridPageVoBean<PaperBean> mmVo = new MMGridPageVoBean<PaperBean>();
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		try {
			int count = examPaperService.getPaperByCategoryCount(categoryId,paperName,userId);
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			List<PaperBean> list  = examPaperService.getPaperByCategory(categoryId,paperName,userId,fromNum,pageSize);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return mmVo;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getDepInfo <BR>
	 * Description: 获取当前公司下的子公司/部门 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param page
	 * @param pageSize
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getDepInfo")
	public Object getDepInfo(HttpServletRequest request,String page,String pageSize){
		logger.debug("MegagameManageAction执行getDepInfo方法");
		MMGridPageVoBean<ManageDepartmentBean> mmVo = new MMGridPageVoBean<ManageDepartmentBean>();
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		try {
			int count = manageDepartmentService.getDepInfoCount(userId);
			long fromNum = CommonUtil.getFromNum(pageSize, page, count);
			List<ManageDepartmentBean> list  = manageDepartmentService.getDepInfo(userId,fromNum,pageSize);
			mmVo.setTotal(count);
			mmVo.setRows(list);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: openNext <BR>
	 * Description: 开启赛程或 结束赛程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id  void<BR>
	 */
	@ResponseBody
	@RequestMapping("openNext")
	public void openNext(HttpServletRequest request,String id){
		logger.debug("MegagameManageAction执行openNext方法");
		try {
			knowledgeContestService.startNewMatchOrEndContest(Integer.parseInt(id));
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: saveOrUpdate2Match <BR>
	 * Description: 新增/更新赛程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param params  void<BR>
	 */
	@ResponseBody
	@RequestMapping("saveOrUpdate2Match")
	public void saveOrUpdate2Match(HttpServletRequest request,SaveMatchParamVo params){
		logger.debug("MegagameManageAction执行saveOrUpdate2Match方法");
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		try {
			params.setUserId(userId);
			knowledgeContestMatchService.save2Match(params);
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
		}
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: addContestUploadImg <BR>
	 * Description: 大赛-图片上传 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param imgFile
	 * @return  String<BR>
	 */
	@ResponseBody
	@RequestMapping("addContest_uploadImg")
	public String addContestUploadImg(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){
		logger.debug("MegagameManageAction执行addContestUploadImg方法");
		try {
			//1、获取文件储存的地址。
			String pageFileName= imgFile.getOriginalFilename();//.getName() ;
			String nameSuff=".jpg";
			if(StringUtils.isNotBlank(pageFileName)){
				nameSuff=pageFileName.substring(pageFileName.lastIndexOf("."));
			}
			
			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
			
			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "pic/upload/"+df.format(new Date());
			
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			
			//2、获取文件的新的名称。以时间戳+四位随机数组成
			String fileName = getUUID()+nameSuff;
			String filePath = saveUrl+extendUrl+"/"+fileName;
			File sourceFile= new File(filePath);
			
			//先删除此文件
			FileUtil.deleteFile(sourceFile);
			
			//将上传的文件写到new出来的文件中
			FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);

			return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+fileName;
		} catch (Exception e) {
			logger.debug(MegagameManageAction.class,e);
			return Constant.AJAX_FAIL;
		}
	}
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    } 
	 
	 
	/**chenrui end*/
	
	/**zhangchen start*/
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoMegagameApprove <BR>
	 * Description: 进入大赛审批页面 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("gotoMegagameApprove")
	public String gotoMegagameApprove(){
		logger.debug("MegagameManageAction执行gotoMegagameApprove方法");
		return "manage_megagame/competition_sp";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAppliationVoList <BR>
	 * Description: 获取大赛报名审批列表 <BR>
	 * Remark: <BR>
	 * @param page
	 * @param pageSize
	 * @param searchOption
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping("getAppliationVoList")
	@ResponseBody
	public Object getAppliationVoList(HttpServletRequest request,ContestApplicationSearchOption searchOption) throws Exception{
		logger.debug("MegagameManageAction执行getAppliationVoList方法");
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		int size = knowledgeContestApplicationService.getAppliationTotalCount(searchOption);
		MMGridPageVoBean<ContestApplicationListItemVo> mmVo = new MMGridPageVoBean<ContestApplicationListItemVo>();
		List<ContestApplicationListItemVo> rows = knowledgeContestApplicationService.getAppliationVoList(searchOption);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteApplication <BR>
	 * Description: 删除大赛报名申请 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteApplication")
	@ResponseBody
	public Object deleteApplication(int id){
		knowledgeContestApplicationService.deleteApplication(id);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: approveApplication <BR>
	 * Description: approveApplication <BR>
	 * Remark: <BR>
	 * @param applyIds
	 * @param refuseReason
	 * @param approveStatus
	 * @return  Object<BR>
	 */
	@RequestMapping("approveApplication")
	@ResponseBody
	public Object approveApplication(String applyIds,String contestIds,String applyUserIds,String refuseReason,int approveStatus){
		try {
			knowledgeContestApplicationService.approveApplication(applyIds,contestIds,applyUserIds,refuseReason,approveStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteNews <BR>
	 * Description: 删除资讯 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping("deleteNews")
	@ResponseBody
	public Object deleteNews(Integer id){
		knowledgeContestNewsService.deleteNews(id);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoPublshNews <BR>
	 * Description: 发布资讯 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 * @throws Exception 
	 */
	@RequestMapping("gotoEditNews")
	public String gotoEditNews(HttpServletRequest request,int id) throws Exception{
		logger.debug("MegagameManageAction执行gotoEditOrViewNews方法");
		KnowledgeContestNewsBean bean = knowledgeContestNewsService.getNews(id);
		/*KnowledgeContestContestBean kbean = myMegagameService.getMegagameInfoById("",contestId);
		if(bean != null){
			request.setAttribute("contestName", kbean.getName());
		}else{
			request.setAttribute("contestName","");
		}*/
		request.setAttribute("newbean", JsonUtil.getJsonString4JavaPOJO(bean));
		//flag 1-编辑 2-查看
		return "manage_megagame/edit_news";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: mofifyNews <BR>
	 * Description: 修改资讯 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping("mofifyNews")
	@ResponseBody
	public Object mofifyNews(KnowledgeContestNewsBean bean){
		logger.debug("MegagameManageAction执行mofifyNews方法");
		knowledgeContestNewsService.modifyNews(bean);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: publishNews <BR>
	 * Description: 发布资讯 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping("publishNews")
	@ResponseBody
	public Object publishNews(int id){
		logger.debug("MegagameManageAction执行publishNews方法");
		knowledgeContestNewsService.publishNews(id);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoPublishNews <BR>
	 * Description: 跳转至发布资讯页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param contestId
	 * @return
	 * @throws Exception  String<BR>
	 */
	@RequestMapping("gotoPublishNews")
	public String gotoPublishNews(HttpServletRequest request,String contestId) throws Exception{
		logger.debug("MegagameManageAction执行gotoPublishNews方法");
		KnowledgeContestContestBean bean = myMegagameService.getMegagameInfoById("",contestId);
		if(bean != null){
			request.setAttribute("contestName", bean.getName());
		}else{
			request.setAttribute("contestName","");
		}
		request.setAttribute("contestId",contestId);
		return "manage_megagame/publish_news";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: saveNews <BR>
	 * Description: 保存资讯 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping("saveNews")
	@ResponseBody
	public Object saveNews(KnowledgeContestNewsBean bean){
		logger.debug("MegagameManageAction执行saveNews方法");
		knowledgeContestNewsService.addNews(bean);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addAndPublishNews <BR>
	 * Description: 保存并发布资讯 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping("addAndPublishNews")
	@ResponseBody
	public Object addAndPublishNews(KnowledgeContestNewsBean bean){
		logger.debug("MegagameManageAction执行addAndPublishNews方法");
		knowledgeContestNewsService.addAndPublishNews(bean);
		return "SUCCESS";
	}
	
	
	/**zhangchen end*/
}
