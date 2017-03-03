/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MyExerceseAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-10-15        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ExamUserAnswerVo;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;
import com.jftt.wifi.bean.vo.ExamWrongCardVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ExamQuestionService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.service.impl.MyExamManageServiceImpl;


/**
 * @author JFTT)zhangchen<BR>
 * class name:MyExerceseAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-10-15
 */
@Controller
@RequestMapping(value="myExercise")
public class MyExerciseAction {
	private static Logger logger = Logger.getLogger(MyExamManageServiceImpl.class);
	
	@Autowired
	private ExamQuestionService examQuestionService;
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoMyExercise <BR>
	 * Description: 跳转到添加练习页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoMyExercise")
	public String gotoMyExercise(HttpServletRequest request){
		logger.debug("MyExamManageAction_gotoMyExercise start");
		return "my_exercise/exercise_start";
	}
	
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoExerciseWrongList <BR>
	 * Description: 跳转至错题集 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoExerciseWrongList")
	public String gotoExerciseWrongList(HttpServletRequest request){
		logger.debug("MyExamManageAction_gotoExerciseWrongList start");
		return "my_exercise/exercise_wrong_list";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoExerciseWrongDetail <BR>
	 * Description: 跳转至错题详情页面 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoExerciseWrongDetail")
	public String gotoExerciseWrongDetail(HttpServletRequest request,int id,int wrongType){
		logger.debug("MyExamManageAction_gotoExerciseWrongDetail start");
		request.setAttribute("wrong_id", id);
		request.setAttribute("wrongType", wrongType);
		return "my_exercise/wrong_detail";
	}
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoExerciseAgain <BR>
	 * Description: 再次练习 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  String<BR>
	 */
	@RequestMapping(value="gotoExerciseAgain")
	public String gotoExerciseAgain(HttpServletRequest request,int id){
		logger.debug("MyExamManageAction_gotoExerciseAgain start");
		request.setAttribute("wrong_id", id);
		return "my_exercise/exercise_again";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: gotoGenerateExercise <BR>
	 * Description: 跳转至练习页面 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="gotoGenerateExercise")
	public String gotoGenerateExercise(HttpServletRequest request,QuestionSearchOptionVo searchOption) throws UnsupportedEncodingException{
		logger.debug("MyExamManageAction_gotoGenerateExercise start");
		Integer categoryId = searchOption.getCategoryId();
		request.setAttribute("categoryId", categoryId);
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		searchOption.setUserId(Integer.parseInt(userBean.getId()));
		//判断题目是否已经全部做完，做完后可以重新开始答题，否则继续完成题库题目
		examQuestionService.exerciseIsOrNotComplete(searchOption);
		//以上方法会将searchOption里的某些属性修改，所以需要重新赋值
		searchOption.setCategoryId(categoryId);
		searchOption.setCategoryIdListStr(null);
		//获取试题库的试题ID
		String idStr = examQuestionService.getExerciseQuestionIdList(searchOption);
		//获取试题库中的试题数量,格式为总数,完成数,错误数
		String count = examQuestionService.getExerciseQuestionCount(searchOption);
		request.setAttribute("idStr", idStr);
		request.setAttribute("questionCount", count);
		return "my_exercise/online_exercise";
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getCategoryNum <BR>
	 * Description: 获取分类下的题目总数及完成数 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getCategoryNum")
	@ResponseBody
	public Object getCategoryNum(HttpServletRequest request,QuestionSearchOptionVo searchOption){
		//session中的人员信息
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		searchOption.setUserId(Integer.parseInt(userBean.getId()));
		//获取试题库中的试题数量,格式为总数,完成数,错误数
		String count = examQuestionService.getExerciseQuestionCount(searchOption);
		return count;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseQuestionIdList <BR>
	 * Description: 获取试题库的题目ID <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExerciseQuestionIdList")
	@ResponseBody
	public Object getExerciseQuestionIdList(HttpServletRequest request,QuestionSearchOptionVo searchOption){
		ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		searchOption.setCompanyId(userBean.getCompanyId());
		searchOption.setSubCompanyId(userBean.getSubCompanyId());
		searchOption.setUserId(Integer.parseInt(userBean.getId()));
		String idStr = examQuestionService.getExerciseQuestionIdList(searchOption);
		return idStr;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: GenerateExercise <BR>
	 * Description: 生成练习题 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GenerateExercise")
	@ResponseBody
	public Object GenerateExercise(int id){
		logger.debug("MyExamManageAction_GenerateExercise start");
		return examQuestionService.getOneExerciseQuestion(id);
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: submitAnswer <BR>
	 * Description: 提交答案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping(value="submitAnswer")
	@ResponseBody
	public Object submitAnswer(HttpServletRequest request,ExamUserAnswerVo vo,String isAddWrong) throws Exception{
		String userId = (String) request.getSession().getAttribute("USER_ID_LONG");
		JSONObject obj = new JSONObject();
		boolean flag;
		try {
			flag = examQuestionService.isAnswerCorrect(vo,Integer.parseInt(userId),isAddWrong);
			obj.elementOpt("flag", flag);
			if(flag){
				return "正确";
			}else{
				return "错误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
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
	@RequestMapping(value="getExerciseWrongCard")
	@ResponseBody
	public Object getExerciseWrongCard(HttpServletRequest request, int page, int pageSize,ExamQueryConditionBean bean){
		logger.debug("MyExamManageAction_getWrongCard start");
		int size = myExamManageService.getExerciseWrongCount(bean);
		//放入分页
		int from = (page-1) * pageSize;
		bean.setFromNum(from);
		bean.setPageSize(pageSize);
		MMGridPageVoBean<ExamWrongCardVo> mmVo = new MMGridPageVoBean<ExamWrongCardVo>();
		List<ExamWrongCardVo> rows = myExamManageService.getExerciseWrongCard(bean);
		mmVo.setTotal(size);
		mmVo.setRows(rows);
		return mmVo;
	}
	
	/**
	 * @author JFTT)zhangchen<BR>
	 * Method name: getExerciseWrongDetail <BR>
	 * Description: 获取练习中的错题详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param id
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getExerciseWrongDetail")
	@ResponseBody
	public Object getExerciseWrongDetail(HttpServletRequest request,int id){
		ExamWrongCardVo vo = myExamManageService.getExerciseWrongDetail(id);
		return vo;
	}
	
}
