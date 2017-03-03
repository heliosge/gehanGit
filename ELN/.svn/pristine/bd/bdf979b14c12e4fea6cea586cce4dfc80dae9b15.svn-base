package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.vo.MegagameListParamVo;
import com.jftt.wifi.bean.vo.SearchKlVo;
import com.jftt.wifi.bean.vo.TrainArrangeVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.CourseQuestionService;
import com.jftt.wifi.service.ExamQuestionCategoryService;
import com.jftt.wifi.service.KnowledgeLibraryInfoService;
import com.jftt.wifi.service.MallCourseManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManagePageService;
import com.jftt.wifi.service.MyExamManageService;
import com.jftt.wifi.service.MyMegagameService;
import com.jftt.wifi.service.StudentCourseService;
import com.jftt.wifi.service.StudentGroupService;
import com.jftt.wifi.service.StudentLearnPlanService;
import com.jftt.wifi.service.TrainService;
import com.jftt.wifi.util.JsonUtil;

/**
 * 首页
 */
@Controller
@RequestMapping(value="index")
public class IndexAction {
	
	@Resource(name="managePageService")
	private ManagePageService managePageService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource(name="studentCourseService")
	private StudentCourseService studentCourseService;
	
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;
	
	@Resource(name = "myMegagameService")
	private MyMegagameService myMegagameService;
	
	@Resource(name="studentGroupService")
	private StudentGroupService studentGroupService;
	
	@Resource(name="courseQuestionService") 
	private CourseQuestionService courseQuestionService;
	
	@Resource(name = "knowledgeLibraryInfoService")
	private KnowledgeLibraryInfoService knowledgeLibraryInfoService;
	
	@Autowired
	private MallCourseManageService mallCourseManageService;
	
	@Autowired
	private ExamQuestionCategoryService questionCategoryService;
	
	@Resource(name="trainService")
	private TrainService trainService;
	
	@Resource(name="studentLearnPlanService")
	private StudentLearnPlanService studentLearnPlanService;
	
	/**
	 * 跳转到首页
	 * @throws Exception 
	 */
	@RequestMapping(value="toIndexPage")
	public String toIndexPage(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		ManageUserBean user = (ManageUserBean) session.getAttribute(Constant.SESSION_USERBEAN);
		ManageCompanyBean company = manageCompanyService.selectCompanyById(user.getCompanyId());
		if(Constant.PULIAN_COMPANY_ID - user.getCompanyId() == 0){
			double totalUsedCapacity = manageCompanyService.selectTotalCapacity();
			company.setUsedCapacity(totalUsedCapacity);
		}
		request.setAttribute("company", JsonUtil.getJson4JavaObject(company));
		
		return "index/indexNew";
	}
	
	@RequestMapping(value="getPageList")
	@ResponseBody
	public Object getPageList(HttpServletRequest request,long userId){
		//HttpSession session = request.getSession();
		//String userId = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
		
		//获取可以查看的页面

		List<ManagePageBean> pageList = managePageService.getManagePageByUser(userId);
		
		return pageList;
	}
	
	/**
	 * 跳转到首页 今日数据
	 */
	@RequestMapping(value="todayData")
	public String todayData(HttpServletRequest request, HttpServletResponse response){
		
		return "index/todayData";
	}
	/**
	 * 跳转建设中页面
	 */
	@RequestMapping(value="coming")
	public String coming(HttpServletRequest request, HttpServletResponse response){
		
		return "index/coming";
	}
	
	/**
	 * 跳转首页
	 */
	@RequestMapping(value="home")
	public String toHomePage(HttpServletRequest request, HttpServletResponse response){
		try{
			int userId = Integer.parseInt((String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG));
			ManageUserBean user = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			//我的课程
			int courseCount = studentCourseService.getMyCoursesCount(userId,1,null)+studentCourseService.getMyCoursesCount(userId,2,null);
			request.setAttribute("courseCount", courseCount);
			//我的考试 
			ExamQueryConditionBean examVo = new ExamQueryConditionBean();
			examVo.setUser_id(userId);
			int examCount = myExamManageService.getExamRecorderCount(examVo);
			request.setAttribute("examCount", examCount);
			//我的比赛
			MegagameListParamVo paramVo = new MegagameListParamVo();
			paramVo.setUserId(String.valueOf(userId));
			paramVo.setState("0");
			int gameCount = myMegagameService.getMegagameListCount(paramVo);
			request.setAttribute("gameCount", gameCount);
			//我的问问
			int questionCount = courseQuestionService.getAskQuestionsCount(userId,null,null,null);
			request.setAttribute("questionCount", questionCount);
			//我的群组
//			int groupCount = studentGroupService.getGroupsCount(user.getCompanyId(),user.getSubCompanyId(),null,null);
//			request.setAttribute("groupCount", groupCount);
			//我的知识
			SearchKlVo sVo = new SearchKlVo();
			sVo.setUserId(String.valueOf(userId));
			int knowledgeCount = knowledgeLibraryInfoService.getMyUploadKnowledgeCount(sVo);
			request.setAttribute("knowledgeCount", knowledgeCount);
			//事故案例
//			KlByCatParamsVo paramsVo = new KlByCatParamsVo();
//			paramsVo.setUserId(userId+"");
//			paramsVo.setIsAccident("1");
//			paramsVo.setIsCloud("1");
//			int accidentCount = knowledgeLibraryInfoService.getKnowledgeOrderByEvaluateCount(paramsVo);
//			request.setAttribute("accidentCount", accidentCount);
			//课程商城
//			MallCourseVo vo = new MallCourseVo();
//			int mallCourseCount = mallCourseManageService.selectCompanyMallCourseCount(vo);
//			vo.setCompanyId(user.getCompanyId());
//			vo.setSubCompanyId(user.getSubCompanyId());
//			mallCourseCount +=  mallCourseManageService.selectMallCourseCount(vo);
//			request.setAttribute("mallCourseCount", mallCourseCount);
			//自我练习
			QuestionCategorySearchOptionVo searchOption =  new QuestionCategorySearchOptionVo();
			searchOption.setCompanyId(user.getCompanyId());
			searchOption.setSubCompanyId(user.getSubCompanyId());
			List<QuestionCategoryBean> list = questionCategoryService.getExerciseQuestionCategory(searchOption);
			request.setAttribute("lxCount", list.size());
			//我的培训
			TrainArrangeVo vo = new TrainArrangeVo();
			vo.setCompanyId(user.getCompanyId().toString());
			vo.setSubCompanyId(user.getSubCompanyId().toString());
			vo.setDeptId(user.getDeptId().toString());
			vo.setUserId(user.getId());
			request.setAttribute("trainCount", trainService.selectJoinedTrainArrangeCount(vo));
			//学习计划
			request.setAttribute("studyPlanCount",studentLearnPlanService.getLearnPlanCount(userId,1,"")+studentLearnPlanService.getLearnPlanCount(userId,2,""));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index/home";
	}
}
