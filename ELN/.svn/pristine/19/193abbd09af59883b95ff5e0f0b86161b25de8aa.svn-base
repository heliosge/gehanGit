/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyExamManageAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-3        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.ExamWrongCardBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.vo.ExamOrganizingRuleVo;
import com.jftt.wifi.bean.vo.ExamAssignDetailVo;
import com.jftt.wifi.bean.vo.ExamGradeVo;
import com.jftt.wifi.bean.vo.ExamRecorderVo;
import com.jftt.wifi.bean.vo.ExamScheduleVo;
import com.jftt.wifi.bean.vo.ExamWrongCardVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ExamOrganizingRuleDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.util.TimeUtil;

/**
 * @author JFTT)zhangchen
 * class name:MyExamManageAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-3
 */
@Controller
@RequestMapping(value="myExamManage")
public class MyExamManageAction {
	private static Logger logger = Logger.getLogger(MyExamManageAction.class);
	
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	@Autowired
	private ExamOrganizingRuleDaoMapper examOrganizingRuleDaoMapper;
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoMyExam <BR>
	 * Description: 跳转至我的考试页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoMyExam")
	public String gotoMyExam(HttpServletRequest request){
		logger.debug("MyExamManageAction_gotoMyExam start");
		return "my_exam/my_exam_list";
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoEnterExam <BR>
	 * Description: 查询我的考试-进入考试页面信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param type
	 * @return  Object<BR>
	 */
	@RequestMapping(value="gotoEnterExam")
	public String gotoEnterExam(HttpServletRequest request,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_gotoEnterExam start");
		/*request.setAttribute("id", id);
		request.setAttribute("exam_type", exam_type);
		request.setAttribute("exam_status", exam_status);*/
		request.setAttribute("curDate", TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		request.setAttribute("paramMap", JSONArray.fromObject(bean));
		return "my_exam/enter_exam";
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoWrongCard <BR>
	 * Description: 跳转至错题集列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoWrongCard")
	public String gotoWrongCard(HttpServletRequest request){
		logger.debug("MyExamManageAction_gotoWrongCard start");
		return "my_exam/exam_wrong_list";
	}
	
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoWrongCard <BR>
	 * Description: 跳转至模拟考试错题集列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoSimulateWrongCard")
	public String gotoSimulateWrongCard(HttpServletRequest request){
		logger.debug("MyExamManageAction_gotoWrongCard start");
		return "my_exam/simulate_wrong_list";
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoExamSimulate <BR>
	 * Description: 跳转至模拟考试列表 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoExamSimulate")
	public String gotoExamSimulate(HttpServletRequest request){
		logger.debug("MyExamManageAction_gotoExamSimulate start");
		return "my_exam/simulate_exam_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoWrongCardDetail <BR>
	 * Description: gotoWrongCardDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoWrongCardDetail")
	public String gotoWrongCardDetail(HttpServletRequest request,int wrong_id){
		logger.debug("MyExamManageAction_gotoWrongCard start");
		request.setAttribute("wrong_id", wrong_id);
		return "my_exam/wrong_detail";
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: gotoExamTest <BR>
	 * Description: 进入试卷测试页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 * @throws ParseException 
	 */
	@RequestMapping(value="gotoExamTest")
	public String gotoExamTest(HttpServletRequest request,int exam_assign_id) throws ParseException{
		logger.debug("MyExamManageAction_gotoExamTest start");
		ExamQueryConditionBean bean = myExamManageService.selectInfoByAssignId(exam_assign_id);
		if(bean.getBegin_time() != null && bean.getEnd_time() != null){
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = sd.parse(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			Date d2 = TimeUtil.parseString2Date(bean.getEnd_time(),"yyyy-MM-dd HH:mm");
			long sec = (d2.getTime() - d1.getTime())/1000;
			bean.setRemaining_time((int) sec);
		}
		request.setAttribute("paramMap", JSONArray.fromObject(bean));
		request.setAttribute("examRecordId", 0);//防止页面取值时报错
		request.setAttribute("exam_assign_id", exam_assign_id);
		//以下是用户信息，用户名、部门名、身份证号、头像
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		request.setAttribute("userName", userBean.getName());
		request.setAttribute("deptName", userBean.getDeptName());
		request.setAttribute("idCard", userBean.getIdCard());
		if(userBean.getPhoto() == null || "".equals(userBean.getPhoto())){
			request.setAttribute("headPhoto", request.getContextPath() + "/images/img/avatar_male.png");
		}else{
			request.setAttribute("headPhoto", userBean.getPhoto());
		}
		
		/*boolean flag = myExamManageService.getCountByAssignId(exam_assign_id);
		if(flag){//如果有答案
			return "my_exam/online_test_again";
		}else{*/
			return "my_exam/online_test";
		/*}*/
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamStatus <BR>
	 * Description: 判断考试现有状态 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException  String<BR>
	 */
	@RequestMapping(value="getExamStatus")
	@ResponseBody
	public String getExamStatus(HttpServletRequest request,String beginTime,String endTime) throws ParseException{
		Date d1  = TimeUtil.parseString2Date(beginTime,"yyyy-MM-dd HH:mm");
		Date d2 = TimeUtil.parseString2Date(endTime,"yyyy-MM-dd HH:mm");
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date cur = sd.parse(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		if(cur.getTime() < d1.getTime()){//未开始
			return "notStart";
		}else{
			if(cur.getTime() < d2.getTime()){//进行中
				return "proccessing";
			}else{//已结束
				return "end";
			}
		}
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoMatchTest <BR>
	 * Description: 进入赛程考试 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoMatchTest")
	public String gotoMatchTest(HttpServletRequest request,int id,String examRecordId,String isFree){
		logger.debug("MyExamManageAction_gotoExamTest start");
		if(examRecordId != null){//课程考试记录ID
			request.setAttribute("examRecordId", examRecordId);
		}else{
			request.setAttribute("examRecordId", 0);
		}
		//isFree 商城课程用 -chenrui
		request.setAttribute("isFree", isFree);
		
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
		ExamQueryConditionBean bean = myExamManageService.selectInfoById(id,Integer.parseInt(userId));
		
		ExamQueryConditionBean cbean = myExamManageService.selectInfoByAssignId(bean.getExam_assign_id());
		request.setAttribute("paramMap", JSONArray.fromObject(cbean));
		//request.setAttribute("examRecordId", 0);//防止页面取值时报错
		request.setAttribute("exam_assign_id", bean.getExam_assign_id());
		/*boolean flag = myExamManageService.getCountByAssignId(bean.getExam_assign_id());
		if(flag){//如果有答案
			return "my_exam/online_test_again";
		}else{*/
			return "my_exam/online_test";
		/*}*/
		//request.setAttribute("paramMap", JSONArray.fromObject(bean));
		//return "my_exam/online_test";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoGradeDetail <BR>
	 * Description: 跳转至成绩总览页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoGradeDetail")
	public String gotoGradeDetail(HttpServletRequest request,int exam_assign_id){
		logger.debug("MyExamManageAction_gotoGradeDetail start");
		ExamQueryConditionBean bean = myExamManageService.selectInfoByAssignId(exam_assign_id);
		request.setAttribute("paramMap", JSONArray.fromObject(bean));
		return "my_exam/grade_detail";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoExamDetail <BR>
	 * Description: 跳转至答题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param exam_assign_id
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoExamDetail")
	public String gotoExamDetail(HttpServletRequest request,int exam_assign_id,String isPy, String toPrint){
		logger.debug("MyExamManageAction_gotoExamDetail start");
		request.setAttribute("exam_assign_id", exam_assign_id);
		//判断有没有答题，若无答题，查询试题详情，若有答案，查询答题详情
		boolean flag = myExamManageService.getCountByAssignId(exam_assign_id);
		request.setAttribute("flag", flag);
		//判断是否为批阅页面
		if(isPy != null && !"".equals(isPy)){
			request.setAttribute("isPy", isPy);
		}else{
			request.setAttribute("isPy", 0);
		}
		ExamQueryConditionBean bean = myExamManageService.getTestDetailParam(exam_assign_id);
		//int organizing_mode = bean.getOrganizing_mode();
		//request.setAttribute("organizing_mode", organizing_mode);
		request.setAttribute("paramMap", JSONArray.fromObject(bean));
		if(flag){
			
			if(toPrint !=null && toPrint.equals("toPrint")){
				//成绩预览页面，打印试卷
				return "my_exam/test_detail_print";
			}
			
			return "my_exam/test_detail";
		}else{
			return "my_exam/no_answer_test_detail";
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getMyExamList <BR>
	 * Description: 获取我的考试列表数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getMyExamList")
	@ResponseBody
	public Object getMyExamList(HttpServletRequest request, int page, int pageSize,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_getMyExamList start");
		int size = myExamManageService.getExamRecorderCount(bean);
		//放入分页
		int from = (page-1) * pageSize;
		bean.setFromNum(from);
		bean.setPageSize(pageSize);
		MMGridPageVoBean<ExamRecorderVo> mmVo = new MMGridPageVoBean<ExamRecorderVo>();
		List<ExamRecorderVo> rows = myExamManageService.getMyExamRecorderVo(bean);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamAssignDetail <BR>
	 * Description: 查询考试详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExamAssignDetail")
	@ResponseBody
	public Object getExamAssignDetail(HttpServletRequest request,int exam_assign_id,String isTest){
		logger.debug("MyExamManageAction_getExamAssignDetail start");
		if("online_test".equals(isTest)){
			myExamManageService.modifyExamAssignInfo(exam_assign_id);
		}
		boolean flag = myExamManageService.getCountByAssignId(exam_assign_id);
		ExamQueryConditionBean ebean = myExamManageService.getInfoByAssignId(exam_assign_id);
		int organizing_mode = ebean.getOrganizing_mode();
		//int organizing_mode = myExamManageService.selectOrganizingMode(exam_assign_id);
		if(organizing_mode == 1){//标准组卷
			if(flag){
				List<ExamUserAnswerBean> list = myExamManageService.getExamAssignDetail(exam_assign_id);
				return list;
			}else{
				ExamAssignDetailVo vo = myExamManageService.getNoAnswerExamDetail(exam_assign_id);
				return vo;
			}
		}else{//随机组卷
			if(flag){
				List<ExamUserAnswerBean> list = myExamManageService.getExamAssignDetail(exam_assign_id);
				return list;
			}else{
				//return myExamManageService.getRuleList(ebean.getPaper_id());
				return examOrganizingRuleDaoMapper.getRuleList(ebean.getPaper_id());
			}
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getPyPaperDetail <BR>
	 * Description: 获取批阅试卷内容 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param exam_assign_id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getPyPaperDetail")
	@ResponseBody
	public Object getPyPaperDetail(HttpServletRequest request,int exam_assign_id){
		logger.debug("MyExamManageAction_getExamAssignDetail start");
		List<ExamUserAnswerBean> list = myExamManageService.getExamAssignDetail(exam_assign_id);
		return list;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamTitle <BR>
	 * Description: 获取考试名称 <BR>
	 * Remark: <BR>
	 * @param exam_assign_id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExamTitle")
	@ResponseBody
	public Object getExamTitle(int id){
		String title = myExamManageService.getExamTitle(id);
		return title;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: initialNoAnswerExamDetail <BR>
	 * Description: 在无答题的情况下查看试题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="initialNoAnswerExamDetail")
	@ResponseBody
	public Object initialNoAnswerExamDetail(HttpServletRequest request,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_initialNoAnswerExamDetail start");
		return myExamManageService.initialExamPaper(bean);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamGetScoreVo <BR>
	 * Description: 查询答卷详情页面的得分总览 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExamGetScoreVo")
	@ResponseBody
	public Object getExamGetScoreVo(int assign_id){
		logger.debug("MyExamManageAction_getExamGetScoreVo start");
		return myExamManageService.getExamGetScore(assign_id);
	}
	
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getAllGrade <BR>
	 * Description: 成绩总览 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 * @throws Exception 
	 */
	@RequestMapping(value="getAllGrade")
	@ResponseBody
	public Object getAllGrade(HttpServletRequest request, ExamQueryConditionBean bean,int page, int pageSize) throws Exception{
		logger.debug("MyExamManageAction_getAllGrade start");
		
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		if(userBean != null){
			bean.setCompanyId(userBean.getCompanyId());
		}
		
		int size = myExamManageService.getAllGradeCount(bean);
		//放入分页
		int from = (page-1) * pageSize;
		bean.setFromNum(from);
		bean.setPageSize(pageSize);
		MMGridPageVoBean<ExamGradeVo> mmVo = new MMGridPageVoBean<ExamGradeVo>();
		List<ExamGradeVo> rows = myExamManageService.getAllGrade(bean);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getGradeOtherInfo <BR>
	 * Description: 查询成绩总览页面的其它信息，如考试时长、及格分、总分 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getGradeOtherInfo")
	@ResponseBody
	public Object getGradeOtherInfo(int id){
		logger.debug("MyExamManageAction_getGradeOtherInfo start");
		return myExamManageService.getGradeOtherInfo(id);
	}
	/**
	 * @author JFTT)zhangchen
	 * Method name: initialExamPaper <BR>
	 * Description: 初始化一份试卷,进入考试时调用的方法 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @param type
	 * @return  Object<BR>
	 */
	@RequestMapping(value="initialExamPaper")
	@ResponseBody
	public Object initialExamPaper(HttpServletRequest request,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_initialExamPaper start");
		logger.debug("===========================initialExamPaper start:"+TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss SSS"));
		Object obj = myExamManageService.initialExamPaper(bean);
		logger.debug("===========================initialExamPaper end:"+TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss SSS"));
		logger.debug("MyExamManageAction_initialExamPaper end");
		return obj;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamScheduleInfo <BR>
	 * Description: getExamScheduleInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExamScheduleInfo")
	@ResponseBody
	public Object getExamScheduleInfo(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_getExamScheduleInfo start");
		return myExamManageService.getExamScheduleInfo(id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExamInfo <BR>
	 * Description: 获取进入考试页面的基本信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExamInfo")
	@ResponseBody
	public Object getExamInfo(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_getExamInfo start");
		return myExamManageService.getExamInfo(id);
	}
	
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getWrongCard <BR>
	 * Description: 查看错题集列表 <BR>
	 * Remark: <BR>
	 * @param requestO
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getWrongCard")
	@ResponseBody
	public Object getWrongCard(HttpServletRequest request, int page, int pageSize,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_getWrongCard start");
		int size = myExamManageService.getExamWrongCount(bean);
		//放入分页
		int from = (page-1) * pageSize;
		bean.setFromNum(from);
		bean.setPageSize(pageSize);
		MMGridPageVoBean<ExamWrongCardVo> mmVo = new MMGridPageVoBean<ExamWrongCardVo>();
		List<ExamWrongCardVo> rows = myExamManageService.getWrongCard(bean);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getWrongCard <BR>
	 * Description: 查看模拟考试错题集列表 <BR>
	 * Remark: <BR>
	 * @param requestO
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getSimulateWrongCard")
	@ResponseBody
	public Object getSimulateWrongCard(HttpServletRequest request, int page, int pageSize,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_getWrongCard start");
		int size = myExamManageService.getSimExamWrongCount(bean);
		//放入分页
		int from = (page-1) * pageSize;
		bean.setFromNum(from);
		bean.setPageSize(pageSize);
		MMGridPageVoBean<ExamWrongCardVo> mmVo = new MMGridPageVoBean<ExamWrongCardVo>();
		List<ExamWrongCardVo> rows = myExamManageService.getSimWrongCard(bean);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getWrongQuestionDetail <BR>
	 * Description: 查看错题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getWrongQuestionDetail")
	@ResponseBody
	public Object getWrongQuestionDetail(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_getWrongQuestionDetail start");
		return myExamManageService.getWrongQuestionDetail(id);
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: deleteWrongCard <BR>
	 * Description: 移出错题集 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteWrongCard")
	@ResponseBody
	public Object deleteWrongCard(HttpServletRequest request,int id,int answer_id){
		logger.debug("MyExamManageAction_deleteWrongCard start");
		myExamManageService.deleteWrongCard(id,answer_id);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: deleteWrongCard <BR>
	 * Description: deleteWrongCard <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="deleteWrongCard2")
	@ResponseBody
	public Object deleteWrongCard2(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_deleteWrongCard start");
		myExamManageService.deleteWrongCard(id);
		return "SUCCESS";
	}
	
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: getExamSimulate <BR>
	 * Description: 获取模拟试题列表 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExamSimulate")
	@ResponseBody
	public Object getExamSimulate(HttpServletRequest request,int page, int pageSize,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_getExamSimulate start");
		int size = myExamManageService.getSimulateCount(bean);
		//放入分页
		int from = (page-1) * pageSize;
		bean.setFromNum(from);
		bean.setPageSize(pageSize);
		MMGridPageVoBean<ExamRecorderVo> mmVo = new MMGridPageVoBean<ExamRecorderVo>();
		List<ExamRecorderVo> rows = myExamManageService.getExamSimulate(bean);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen
	 * Method name: CalculateScore <BR>
	 * Description: 提交试卷时计算分数，并提出错题 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param examAssignId
	 * @param vo
	 * @return  Object<BR>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="submitPaper")
	@ResponseBody
	public Object submitPaper(HttpServletRequest request,ExamUserAnswerVo vo) throws NumberFormatException, Exception{
		logger.debug("MyExamManageAction_submitPaper start");
		//积分管理
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		int totalScore = 0;
		try {
			totalScore = myExamManageService.calculateScore(vo,Integer.parseInt(userId));
			return totalScore;
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("calculateScore error");
			return "error";
		}
		
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: modifyExamAssignInfo <BR>
	 * Description: 更新考试信息 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="updateExamAssignInfo")
	public void modifyExamAssignInfo(ExamAssignBean bean){
		logger.debug("MyExamManageAction_updateExamAssignInfo start");
		myExamManageService.updateExamAssignInfo(bean);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: addWrongCard <BR>
	 * Description: 加入错题集 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@RequestMapping(value="addWrongCard")
	@ResponseBody
	public String addWrongCard(ExamWrongCardBean bean,int answer_id){
		logger.debug("MyExamManageAction_addWrongCard start");
		myExamManageService.addWrongCard(bean,answer_id);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getLeaveTimes <BR>
	 * Description: 获取考试离开次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  int<BR>
	 */
	@RequestMapping(value="getLeaveTimes")
	@ResponseBody
	public int getLeaveTimes(int assign_id){
		logger.debug("MyExamManageAction_getLeaveTimes start");
		return myExamManageService.getLeaveTimes(assign_id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: updateLeaveTimes <BR>
	 * Description: 更新页面离开次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  String<BR>
	 */
	@RequestMapping(value="updateLeaveTimes")
	@ResponseBody
	public String updateLeaveTimes(int assign_id){
		logger.debug("MyExamManageAction_updateLeaveTimes start");
		ExamAssignBean bean = new ExamAssignBean();
		bean.setId(assign_id);
		bean.setLeaveTimes(1);
		myExamManageService.updateExamAssignInfo(bean);
		return "SUCCESS";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getTimeTimes <BR>
	 * Description: 获得考试次数 <BR>
	 * Remark: <BR>
	 * @param assign_id
	 * @return  int<BR>
	 */
	@RequestMapping(value="getTestTimes")
	@ResponseBody
	public Object getTestTimes(int exam_assign_id){
		logger.debug("MyExamManageAction_getTimeTimes start");
		ExamAssignBean bean = myExamManageService.getTestTimes(exam_assign_id);
		return bean;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoViewPaper <BR>
	 * Description: 跳转到试卷查看页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoViewPaper")
	public String gotoViewPaper(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_gotoViewPaper start");
		ExamScheduleVo exam = myExamManageService.getOrganizingMode(id);
		request.setAttribute("id", id);
		request.setAttribute("organizingMode", exam.getOrganizingMode());
		request.setAttribute("examType", exam.getExamType());
		return "my_exam/view_paper";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: viewPaper <BR>
	 * Description: 查看试卷 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="viewPaper")
	@ResponseBody
	public Object viewPaper(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_viewPaper start");
		ExamScheduleVo exam = myExamManageService.getOrganizingMode(id);
		if(exam.getOrganizingMode() == 1){//普通组卷
			return myExamManageService.getExamScheduleVo(id);
		}else{
			List<ExamOrganizingRuleBean> vo = examOrganizingRuleDaoMapper.getRuleList(exam.getPaperId());
			exam.setRuleList(vo);
			return exam;
		}
	}
	
	/**
	 * @author JFTT)wangjian
	 * 跳转到选择选择学员页面
	 */
	@RequestMapping(value="selectStudent")
	public String selectStudent(HttpServletRequest request, String idInputId, String nameInputId){
		
		request.setAttribute("idInputId", idInputId);
		request.setAttribute("nameInputId", nameInputId);
		
		return "manage_exam/select_student";
	}
}
