/*
* All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/06        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.vo.ExamSearchOptionVo;
import com.jftt.wifi.bean.exam.vo.MarkExamListItemVo;
import com.jftt.wifi.bean.exam.vo.OfficialExamListItemVo;
import com.jftt.wifi.bean.exam.vo.SimExamListItemVo;
import com.jftt.wifi.bean.exam.vo.cjylSearchVo;
import com.jftt.wifi.bean.knowledge_contest.CommonConstants;
import com.jftt.wifi.bean.knowledge_contest.SetUserInfoUtils;
import com.jftt.wifi.bean.vo.AjaxReturnVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.KnowledgeContestMatchDaoMapper;
import com.jftt.wifi.service.ExamMarkScoreService;
import com.jftt.wifi.service.ExamService;
import com.jftt.wifi.service.KnowledgeContestService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageGroupService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.FileUtil;

/**
 * class name:ExamAction <BR>
 * class description: 考试管理Action <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/06
 * @author JFTT)wangyifeng
 */
@Controller
@RequestMapping(value = "exam/exam")
@SuppressWarnings("all")
public class ExamAction {
	private static final Logger LOG = Logger.getLogger(ExamAction.class);

	@Autowired
	private ExamService examService;
	@Autowired
	private ExamMarkScoreService examMarkScoreService;
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;
	@Resource(name="manageGroupService")
	private ManageGroupService manageGroupService;
	@Resource(name = "knowledgeContestMatchDaoMapper")
	private KnowledgeContestMatchDaoMapper knowledgeContestMatchDaoMapper;
	@Resource(name = "manageCompanyService")
	private ManageCompanyService manageCompanyService;
	@Autowired
	private KnowledgeContestService knowledgeContestService;
	// wangyifeng begin
	/**
	 * Method name: addExam <BR>
	 * Description: 新增考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param newExam
	 * @return String<BR>
	 */
	@RequestMapping(value = "addExam", consumes = "application/json")
	@ResponseBody
	public String addExam(HttpServletRequest request,@RequestBody ExamScheduleBean newExam) {
		LOG.info("add");
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		try {
			examService.addExam(newExam,userBean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("add failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: deleteExams <BR>
	 * Description: 删除考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param idList
	 * @return String<BR>
	 */
	@RequestMapping(value = "deleteExams", consumes = "application/json")
	@ResponseBody
	public String deleteExams(@RequestBody List<Integer> idList) {
		LOG.info("deleteExams");
		try {
			examService.deleteExams(idList.toArray(new Integer[0]));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("deleteExams failed", e);
			return Constant.AJAX_FAIL;
		}
	}
	
	/**
	 * Method name: modifyExam <BR>
	 * Description: 修改考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param modifiedExam
	 * @return String<BR>
	 */
	@RequestMapping(value = "modifyExam", consumes = "application/json")
	@ResponseBody
	public String modifyExam(@RequestBody ExamScheduleBean modifiedExam) {
		LOG.info("modify");
		try {
			examService.modifyExam(modifiedExam);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("modify failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: publishExam <BR>
	 * Description: 发布考试 <BR>
	 * Remark: <BR>
	 * 
	 * @param examIdList
	 * @return String<BR>
	 */
	@RequestMapping(value = "publishExam", consumes = "application/json")
	@ResponseBody
	public String publishExam(HttpServletRequest request,@RequestBody List<Integer> examIdList) {
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		LOG.info("publishExam: " + examIdList);
		try {
			Assert.notNull(examIdList);
			Assert.isTrue(examIdList.size() == 1,
					"Param of publishExam should be an array with only one id.");
			examService.publishExam(examIdList.get(0),userBean);
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("publishExam failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: cancelExam <BR>
	 * Description: 取消发布 <BR>
	 * Remark: <BR>
	 * 
	 * @param examIdList
	 * @return String<BR>
	 */
	@RequestMapping(value = "cancelExam", consumes = "application/json")
	@ResponseBody
	public String cancelExam(@RequestBody List<Integer> examIdList) {
		LOG.info("cancelExam: " + examIdList);
		try {
			Assert.notNull(examIdList);
			Assert.isTrue(examIdList.size() == 1,
					"Param of cancelExam should be an array with only one id.");
			examService.cancelExam(examIdList.get(0));
			return Constant.AJAX_SUCCESS;
		} catch (Exception e) {
			LOG.warn("cancelExam failed", e);
			return Constant.AJAX_FAIL;
		}
	}

	/**
	 * Method name: getExam <BR>
	 * Description: 获取考试详情 <BR>
	 * Remark: <BR>
	 * 
	 * @param examId
	 * @return ExamScheduleBean<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getExam")
	@ResponseBody
	public ExamScheduleBean getExam(Integer examId) throws Exception {
		LOG.info("getExam: " + examId);
		// TODO wangyifeng fill scoreMarkerList
		ExamScheduleBean bean = examService.getExam(examId);
		return bean;
	}

	/**
	 * Method name: getOfficialVoList <BR>
	 * Description: 获取正式考试摘要列表 <BR>
	 * Remark: <BR>
	 * @param searchOption
	 * @return List<OfficialExamListItemVo><BR>
	 */
	@RequestMapping(value = "getOfficialVoList")
	@ResponseBody
	public Object getOfficialVoList(HttpServletRequest request,ExamSearchOptionVo searchOption) {
		LOG.info("getOfficialVoList");
		SetUserInfoUtils.doWork(request, searchOption,CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
		int size = examService.getTotalCount(searchOption);
		MMGridPageVoBean<OfficialExamListItemVo> mmVo = new MMGridPageVoBean<OfficialExamListItemVo>();
		List<OfficialExamListItemVo> rows = examService.getOfficialVoList(searchOption);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}

	/**
	 * Method name: getOfficialVoList <BR>
	 * Description: 获取模拟考试摘要列表 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return List<OfficialExamListItemVo><BR>
	 */
	@RequestMapping(value = "getSimVoList")
	@ResponseBody
	public Object getSimVoList(HttpServletRequest request,ExamSearchOptionVo searchOption) {
		LOG.info("getSimVoList");
		SetUserInfoUtils.doWork(request, searchOption,CommonConstants.FIELD_NAME_SUB_COMPANY_ID, Integer.class);
		int size = examService.getTotalCount(searchOption);
		MMGridPageVoBean<SimExamListItemVo> mmVo = new MMGridPageVoBean<SimExamListItemVo>();
		List<SimExamListItemVo> rows = examService.getSimVoList(searchOption);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}

	/**
	 * Method name: getTotalCount <BR>
	 * Description: 获取考试总数 <BR>
	 * Remark: <BR>
	 * 
	 * @param searchOption
	 * @return Integer<BR>
	 */
	@RequestMapping(value = "totalCount", consumes = "application/json")
	@ResponseBody
	public Integer getTotalCount(@RequestBody ExamSearchOptionVo searchOption) {
		LOG.info("getTotalCount");
		LOG.debug(searchOption);
		return examService.getTotalCount(searchOption);
	}
	// wangyifeng end
	
	/**chenrui start*/
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toCjPyList <BR>
	 * Description: 跳转页面-成绩批阅列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCjPyList")
	public String toCjPyList(HttpServletRequest request){
		return "manage_exam/cj_py_list";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: toCjYlList <BR>
	 * Description: 跳转页面-成绩预览列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("toCjYlList")
	public String toCjYlList(HttpServletRequest request,String examId){
		request.setAttribute("examId", examId);
		return "manage_exam/cj_yl";
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getCjPyList <BR>
	 * Description: 获取成绩批阅列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCjPyList")
	public Object getCjPyList(HttpServletRequest request,ExamSearchOptionVo searchOption) {
		LOG.debug("ExamAction执行getCjPyList方法");
		MMGridPageVoBean<MarkExamListItemVo> mmVo = new MMGridPageVoBean<MarkExamListItemVo>();
		try {
				String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
				searchOption.setUserId(userId);
				int count = examMarkScoreService.getMarkTotalCount(searchOption);
				ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
				searchOption.setSubCompanyId(userBean.getSubCompanyId());
				List<MarkExamListItemVo> list  = examMarkScoreService.getMarkVoList(searchOption);
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
		return mmVo;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: doPublish <BR>
	 * Description: 成绩发布 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("doPublish")
	public Object doPublish(HttpServletRequest request,String examId) {
		LOG.debug("ExamAction执行doPublish方法");
		AjaxReturnVo<String> arv = new AjaxReturnVo<String>();
		try {
				int examId2 = 0;
				if(examId!=null){
					examId2 = Integer.parseInt(examId);
				}
				examMarkScoreService.publishScore(examId2);
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
		return arv;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getCjPyList <BR>
	 * Description: 获取成绩预览列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getCjYlList")
	public Object getCjYlList(HttpServletRequest request,cjylSearchVo paramVo) {
		LOG.debug("ExamAction执行getCjYlList方法");
		MMGridPageVoBean<ExamAssignBean> mmVo = new MMGridPageVoBean<ExamAssignBean>();
		try {	
				int count = examMarkScoreService.getExamResultListCount(paramVo);
				String page = paramVo.getPage();
				String pageSize = paramVo.getPageSize();
				long fromNum = CommonUtil.getFromNum(pageSize, page, count);
				paramVo.setFromNum(fromNum);
				List<ExamAssignBean> list  = examMarkScoreService.getExamResultList(paramVo, paramVo.getUserId());
				mmVo.setTotal(count);
				mmVo.setRows(list);
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
		return mmVo;
	}
	@ResponseBody
	@RequestMapping("getCjYlListAll")
	public Object getCjYlListAll(HttpServletRequest request,cjylSearchVo paramVo) {
		LOG.debug("ExamAction执行getCjYlListAll方法");
		List<ExamAssignBean> list = null;
		try {	
				list  = examMarkScoreService.getCjYlListAll(paramVo, paramVo.getUserId());
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
		return list;
	}
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getExamInfo <BR>
	 * Description: 成绩预览-获取考试信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  Object<BR>
	 */
	@ResponseBody
	@RequestMapping("getExamInfo")
	public Object getExamInfo(HttpServletRequest request,String examId) {
		LOG.debug("ExamAction执行getExamInfo方法");
		AjaxReturnVo<ExamScheduleVo> arv = new AjaxReturnVo<ExamScheduleVo>();
		try {
				int examId2 = 0;
				if(examId!=null){
					examId2 = Integer.parseInt(examId);
				}
				
				ExamScheduleVo vo = examMarkScoreService.getExamInfo(examId2);
				arv.setRtnData(vo);
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
		return arv;
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: exportDoc <BR>
	 * Description: 成绩预览-导出excel <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userId
	 * @param examId
	 * @return  String<BR>
	 */
	@ResponseBody
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,cjylSearchVo paramVo) {
		LOG.debug("ExamAction执行exportExcel方法");
		try {
				HSSFWorkbook workbook = examMarkScoreService.exportExcel(paramVo);
				  
				response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
				response.addHeader("Content-Disposition", "attachment;filename="+new String("成绩预览.xls".getBytes(),"iso8859-1"));
				//得到向客户端输出二进制数据的对象 
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				workbook.write(toClient);
				toClient.close();
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
	}
	
	@ResponseBody
	@RequestMapping("exportDoc")
	public void exportDoc(HttpServletRequest request,HttpServletResponse response,cjylSearchVo paramVo) {
		LOG.debug("ExamAction执行exportDoc方法");
		try {
			XWPFDocument doc = examMarkScoreService.exportDoc(paramVo);
			response.addHeader("Content-Type","application/x-msdownload;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+new String("成绩预览.doc".getBytes(),"iso8859-1"));
			//得到向客户端输出二进制数据的对象 
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			doc.write(toClient);
		} catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
	}
	
	/**chenrui end*/
	
	/**zhangchen start*/
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: toCjXgList <BR>
	 * Description: 跳转到成绩修改页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @return  String<BR>
	 */
	@RequestMapping("gotoCjXgList")
	public String gotoCjXgList(HttpServletRequest request,String examId){
		LOG.debug("ExamAction执行gotoCjXgList方法");
		request.setAttribute("examId", examId);
		return "manage_exam/cj_xg";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyScore <BR>
	 * Description: 修改成绩 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("modifyScore")
	public Object modifyScore(ExamUserAnswerVo vo) throws Exception{
		LOG.debug("ExamAction执行modifyScore方法");
		examMarkScoreService.markScore(vo);
		//计算组合题的总得分
		/*List<ExamUserAnswerBean> list = myExamManageService.getExamQuestionDetail(vo.getExamAssignId());
		for(int i=0;i<list.size();i++){
			ExamUserAnswerBean bean = list.get(i);
			List<ExamUserAnswerBean> subAnswers = bean.getSubAnswers();
			if(subAnswers != null && !subAnswers.isEmpty()){
				int getScore = 0;
				for(int j=0;j<subAnswers.size();j++){
					getScore += subAnswers.get(j).getGetScore();
				}
				bean.setGetScore(getScore);
				myExamManageService.modifyGetScore(bean);
			}
		}*/
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoPy <BR>
	 * Description: 跳转到批阅页面 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return  Object<BR>
	 */
	@RequestMapping("gotoPy")
	public String gotoPy(HttpServletRequest request,Integer examId,String matchId){
		LOG.debug("ExamAction执行gotoPy方法");
		try {
			if(examId == null){
				examId = knowledgeContestMatchDaoMapper.getById(Integer.parseInt(matchId)).getExamId();
			}
			request.setAttribute("examId", examId);
			request.setAttribute("matchId", matchId);
		}  catch (Exception e) {
			LOG.debug(ExamAction.class,e);
		}
		return "manage_exam/py";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAttendUserList <BR>
	 * Description: 获取批阅页面参加考试的人员 <BR>
	 * Remark: <BR>
	 * @param examId
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("getAttendUserList")
	public Object getAttendUserList(ExamQueryConditionBean bean) throws Exception{
		LOG.debug("ExamAction执行getAttendUserList方法");
		List<ExamAssignBean> list = examMarkScoreService.getAttendUserList(bean);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoExamList <BR>
	 * Description: 跳转至考试安排列表 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("gotoExamList")
	public String gotoExamList(HttpServletRequest request){
		//request.setAttribute("type", type);
		return "manage_exam/exam_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoSimExamList <BR>
	 * Description: 跳转至模拟考试安排列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("gotoSimExamList")
	public String gotoSimExamList(HttpServletRequest request){
		//request.setAttribute("type", type);
		return "manage_exam/simulate_exam_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoAddExam <BR>
	 * Description: 跳转至增加考试安排 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("gotoAddExam")
	public String gotoAddExam(HttpServletRequest request,String type){
		request.setAttribute("type", type);
		if("1".equals(type)){
			return "manage_exam/exam_add";
		}else{
			return "manage_exam/simulate_exam_add";
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoEditExam <BR>
	 * Description: 编辑考试信息 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("gotoEditExam")
	public String gotoEditExam(HttpServletRequest request,String examId,String type,String examState){
		request.setAttribute("examId", examId);
		request.setAttribute("examState", examState);
		if("1".equals(type)){
			return "manage_exam/exam_edit";
		}else{
			return "manage_exam/simulate_exam_edit";
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getDepartmentBySubCompanyId <BR>
	 * Description: 根据子公司ID获取部门列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getDepartmentBySubCompanyId")
	@ResponseBody
	public Object getDepartmentBySubCompanyId(HttpServletRequest request) throws Exception{
		ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		/*Map map = new HashMap();
		if(userBean.getCompanyId() == userBean.getSubCompanyId()){
			map.put("companyId", userBean.getCompanyId());
		}else{
			map.put("companyId", userBean.getCompanyId());
			map.put("subCompanyId", userBean.getSubCompanyId());
		}
		List<ManageDepartmentBean> list = manageDepartmentService.getManageDepartmentByMap(map);*/
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<String> subList = new ArrayList<String>();
		if(user.getCompanyId().equals(user.getSubCompanyId())){
			ManageCompanyVo cv = new ManageCompanyVo();
			cv.setId(user.getCompanyId());
			List<ManageCompanyBean> companyList = manageCompanyService.selectCompanyList(cv);
			Map<String, String> param = new HashMap<String, String>();
			param.put("companyId", user.getCompanyId()+"");
			List<ManageDepartmentBean> deptList = manageDepartmentService.getManageDepartmentByMap(param);
			for(ManageDepartmentBean deptBean : deptList){
				/*if(deptBean.getSubCompanyId() != deptBean.getCompanyId() && deptBean.getIsSubCompany() == 2){
					continue;
				}*/
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", deptBean.getId()+"");
				if(deptBean.getParentId() == null){
					map.put("parentId", "com_"+deptBean.getCompanyId());
					if(deptBean.getIsSubCompany() == 1){
						subList.add(deptBean.getId()+"");
					}
				}else{
					map.put("parentId", ""+deptBean.getParentId());
				}
				map.put("name", deptBean.getName());
				list.add(map);
			}
			for(ManageCompanyBean cBean : companyList){
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", "com_"+cBean.getId());
				map.put("parentId", null);
				map.put("name", cBean.getName());
				list.add(map);
			}
		}else{
			Map<String, String> param = new HashMap<String, String>();
			param.put("subCompanyId", user.getSubCompanyId()+"");
			List<ManageDepartmentBean> deptList = manageDepartmentService.getManageDepartmentByMap(param);
			for(ManageDepartmentBean deptBean : deptList){
				Map<String, String> map = new HashMap<String, String>();
				if(deptBean.getParentId() == null){
					map.put("id", deptBean.getId()+"");
					map.put("parentId", "com_"+deptBean.getCompanyId());
					if(deptBean.getIsSubCompany() == 1){
						subList.add(deptBean.getId()+"");
					}
				}else{
					map.put("id", deptBean.getId()+"");
					map.put("parentId", ""+deptBean.getParentId());
				}
				map.put("name", deptBean.getName());
				list.add(map);
			}
		}
		return list;
	}
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGroupBySubCompanyId <BR>
	 * Description: 根据子公司ID获取群组 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getGroupBySubCompanyId")
	@ResponseBody
	public Object getGroupBySubCompanyId(HttpServletRequest request,ManageGroupBeanVo vo) throws Exception{
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		if(userBean.getCompanyId() == userBean.getSubCompanyId()){
			vo.setCompanyId(userBean.getCompanyId());
		}else{
			vo.setSubCompanyId(userBean.getSubCompanyId());
			vo.setCompanyId(userBean.getCompanyId());
		}
		int from = (vo.getPage()-1) * vo.getPageSize();
		vo.setFromNum(from);
		//vo.setPageSize(pageSize);
		List<ManageGroupBean> list = manageGroupService.selectGroupList(vo);
		int count = manageGroupService.selectGroupCount(vo);
		MMGridPageVoBean<ManageGroupBean> mmVo = new MMGridPageVoBean<ManageGroupBean>();
		mmVo.setTotal(count);
		mmVo.setRows(list);
		//return list;
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPersonBySubCompanyId <BR>
	 * Description: 通过子公司ID获取人员列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getPersonBySubCompanyId")
	@ResponseBody
	public Object getPersonBySubCompanyId(HttpServletRequest request) throws Exception{
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		Map map = new HashMap();
		map.put("subCompanyId", userBean.getSubCompanyId());
		List<ManageUserBean> list = manageUserService.findUserByListCondition(map);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPersonByCondition <BR>
	 * Description: 根据部门ID查询人员列表 <BR>
	 * Remark: <BR>
	 * @param deptId
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getPersonByCondition")
	@ResponseBody
	public Object getPersonByCondition(HttpServletRequest request,String depName,String post,String userName,String name) throws Exception{
		Map map = new HashMap();
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		map.put("companyId", userBean.getCompanyId());
		map.put("subCompanyId", userBean.getSubCompanyId());
		if(userName!=null && !"".equals(userName)){
			map.put("userName", userName);
		}
		if(name!=null && !"".equals(name)){
			map.put("name", name);
		}
		if(depName!=null && !"".equals(depName)){
			map.put("deptId", depName);
		}
		if(post!=null && !"".equals(post)){
			map.put("postId", post);
		}
		//List<ManageUserBean> list = manageUserService.findUserByListCondition(map,page,pageSize);
		List<ManageUserBean> list = manageUserService.findUserByListCondition(map);
		/*if(userList != null && !"".equals(userList)){
			String[] userListArr = userList.split(",");
			List<String> idList = new ArrayList<String>();
			for(int i=0;i<userListArr.length;i++){
				idList.add(userListArr[i]);
			}
			map.put("id",idList);
		}*/
		//List<ManageUserBean> list = manageUserService.findUserByNotInCondition(map,page,pageSize);
		//List<ManageUserBean> list2 = manageUserService.findUserByNotInCondition(map);
		MMGridPageVoBean<ManageUserBean> mmVo = new MMGridPageVoBean<ManageUserBean>();
		int count = manageUserService.findUserByListCondition(map).size();
		mmVo.setTotal(count);
		mmVo.setRows(list);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPersonByDeptId <BR>
	 * Description: getPersonByDeptId <BR>
	 * Remark: <BR>
	 * @param deptId
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getPersonByDeptId")
	@ResponseBody
	public Object getPersonByDeptId(String deptId) throws Exception{
		Map map = new HashMap();
		List<Integer> deptIdList = new ArrayList<Integer>();
		if(deptId!=null && !"".equals(deptId) && !deptId.startsWith("com")){
			knowledgeContestService.getAllChildNodes(Integer.parseInt(deptId),deptIdList);
		}
		if(deptId!=null && !"".equals(deptId)){
			/*String[] deptIdArray = deptId.split(",");
			for(int i=0;i<deptIdArray.length;i++){
				deptIdList.add(Integer.parseInt(deptIdArray[i]));
			}*/
			if(deptIdList!=null && deptIdList.size()>0){
				map.put("deptId", deptIdList);
			}
		}
		List<ManageUserBean> list = manageUserService.findUserByListCondition(map);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPersonByDeptIds <BR>
	 * Description: getPersonByDeptIds <BR>
	 * Remark: <BR>
	 * @param deptId
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getPersonByDeptIds")
	@ResponseBody
	public Object getPersonByDeptIds(String deptId) throws Exception{
		Map map = new HashMap();
		List<String> deptIdList = new ArrayList<String>();
		List<Integer> idList = new  ArrayList<Integer>();
		/*if(deptId!=null && !"".equals(deptId) && !deptId.startsWith("com")){
			knowledgeContestService.getAllChildNodes(Integer.parseInt(deptId),deptIdList);
		}*/
		List<ManageUserBean> list = new ArrayList<ManageUserBean>();
		boolean flag = false;
		Integer rootId = null;
		if(deptId!=null && !"".equals(deptId)){
			String[] deptIdArray = deptId.split(",");
			for(int i=0;i<deptIdArray.length;i++){
				if(!deptIdArray[i].startsWith("com")){
					deptIdList.add(deptIdArray[i]);
				}else{
					flag = true;
					rootId = Integer.parseInt(deptIdArray[i].substring(4));
				}
			}
			if(flag){//勾选了企业
				Map map2 = new HashMap();
				map2.put("companyId", rootId);
				map2.put("status", 1);//add by luyunlong 冻结的用户不能选择
				//map2.put("subCompanyId", 1);
				//List<ManageDepartmentBean> dList = manageDepartmentService.getManageDepartmentByMap(map);
				/*for(int i=0;i<dList.size();i++){
					idList.add((int)dList.get(i).getId());
				}*/
				list = manageUserService.findUserByListCondition(map2);
			}else{
				for(int i=0;i<deptIdList.size();i++){
					idList.add(Integer.parseInt(deptIdList.get(i)));
				}
				if(idList != null && idList.size() > 0){
					map.put("deptId", idList);
				}
				map.put("status", 1);//add by luyunlong 冻结的用户不能选择
				list = manageUserService.findUserByListCondition(map);
			}
			
		}
		return list;
	}
	
	/**
	 * wangjian add
	 * 根据 职位 查找人员
	 */
	@RequestMapping("getPersonByPost")
	@ResponseBody
	public Object getPersonByPost(String postId, HttpServletRequest request) throws Exception{
		
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		
		Map map = new HashMap();
		
		//公司
		map.put("companyId", userBean.getCompanyId());
		
		if(userBean.getCompanyId().intValue() != userBean.getSubCompanyId().intValue()){
			//获得子公司，下面所有的子公司
			List<Long> subCompanyIdList = manageDepartmentService.getChildren(userBean.getCompanyId(), userBean.getSubCompanyId());
			map.put("subCompanyId", subCompanyIdList);
		}

		List<Integer> idList = new  ArrayList<Integer>();
		
		List<ManageUserBean> list = new ArrayList<ManageUserBean>();
		
		if(postId !=null && !postId.equals("")){
			
			String[] postIdArray = postId.split(",");
			
			for(int i=0; i<postIdArray.length; i++){
				idList.add(Integer.parseInt(postIdArray[i]));
			}
			if(idList != null && idList.size() > 0){
				map.put(Constant.MANAGE_USER_POST_FIELD, idList);
			}
		}
		
		map.put("status", 1);//add by luyunlong 状态正常的用户
		list = manageUserService.findUserByListCondition(map);

		return list;
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPersonByGroupId <BR>
	 * Description: 根据群组ID查询人员列表 <BR>
	 * Remark: <BR>
	 * @param groupId
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping("getPersonByGroupId")
	@ResponseBody
	public Object getPersonByGroupId(String groupId) throws Exception{
		String[] groupIdArray = groupId.split(",");
		String userIds = "";
		for(int i=0;i<groupIdArray.length;i++){
			userIds = userIds + examService.getUserIdByGroupId(Integer.parseInt(groupIdArray[i]))+",";
		}
		
		Map map =  new HashMap();
		if(userIds != null){
			List<String> idList = new ArrayList<String>();
			String[] idArray = StringUtils.split(userIds,",");
			for(int i=0;i<idArray.length;i++){
				idList.add(idArray[i]);
			}
			map.put("id",idList);
			//List<ManageUserBean> list = manageUserService.findUserByIds(idList);
			List<ManageUserBean> list = manageUserService.findUserByListCondition(map);
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getAssignUserList <BR>
	 * Description: 获取一场考试人员列表 <BR>
	 * Remark: <BR>
	 * @param relation_id
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping("getAssignUserList")
	@ResponseBody
	public Object getAssignUserList(int relation_id,String examState) throws Exception{
		return examService.getAssignInfoByRelationId(relation_id,examState);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoViewExam <BR>
	 * Description: 查看考试详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examId
	 * @param type
	 * @return  String<BR>
	 */
	@RequestMapping("gotoViewExam")
	public String gotoViewExam(HttpServletRequest request,String examId,String type){
		request.setAttribute("examId", examId);
		if("1".equals(type)){
			return "manage_exam/exam_detail";
		}else{
			return "manage_exam/simulate_exam_detail";
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: validateExamTitle <BR>
	 * Description: 验证考试名称唯一性 <BR>
	 * Remark: <BR>
	 * @param title
	 * @param examId
	 * @return  int<BR>
	 */
	@RequestMapping("validateExamTitle")
	@ResponseBody
	public int validateExamTitle(HttpServletRequest request,ExamScheduleBean bean){
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		bean.setCompanyId(userBean.getCompanyId());
		bean.setSubCompanyId(userBean.getSubCompanyId());
		int count = examService.getTitleCount(bean);
		return count;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: isAddUser <BR>
	 * Description: 查询一场考试是否有添加人员 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  boolean<BR>
	 */
	@RequestMapping("isAddUser")
	@ResponseBody
	public boolean isAddUser(HttpServletRequest request,int id){
		return examMarkScoreService.getUserByRelationId(id);
	}
	
	/**zhangchen end*/
}
