package com.jftt.wifi.action;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jftt.wifi.bean.*;
import com.jftt.wifi.bean.vo.*;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.*;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import com.jftt.wifi.bean.exam.ExamAssignBean;
import com.jftt.wifi.bean.exam.ExamOrganizingRuleBean;
import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.QuestionCategoryBean;
import com.jftt.wifi.bean.exam.QuestionOptionBean;
import com.jftt.wifi.bean.exam.enumtype.ExamState;
import com.jftt.wifi.bean.exam.vo.QuestionCategorySearchOptionVo;
import com.jftt.wifi.bean.exam.vo.QuestionSearchOptionVo;
import com.jftt.wifi.bean.knowledge_contest.CommonConstants;
import com.jftt.wifi.bean.knowledge_contest.SetUserInfoUtils;
import com.jftt.wifi.bean.questionnaire.QuestionnaireCategoryBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ExamOrganizingRuleDaoMapper;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.TimeUtil;

/**
 * class name:移动端接口
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年10月14日
 * @author zhangbocheng
 */
@Controller
@RequestMapping("Mobile")
public class MobileAction {

	private static Logger log = Logger.getLogger(LoginAction.class);
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	@Resource(name="studentCourseService")
	private StudentCourseService studentCourseService;
	
	@Resource(name="courseDetailService")
	private CourseDetailService courseDetailService;
	
	@Resource(name="myExamManageService")
	private MyExamManageService myExamManageService;
	
	@Resource(name="courseCollectionService") 
	private CourseCollectionService courseCollectionService;
	
	@Resource(name="courseEvaluateService")
	private CourseEvaluateService courseEvaluateService;
	
	@Resource(name="courseRecordService")
	private CourseRecordService courseRecordService;
	
	@Resource(name="manageNoticeService")
	private ManageNoticeService manageNoticeService;
	
	@Autowired
	private ExamQuestionCategoryService questionCategoryService;

	@Autowired
	private ExamQuestionService examQuestionService;
	@Autowired
	private ExamOrganizingRuleDaoMapper examOrganizingRuleDaoMapper;

    @Resource(name="studentLearnPlanService")
    private StudentLearnPlanService studentLearnPlanService;

    @Resource(name="mobileAppVersionManageService")
    private MobileAppVersionManageService mobileAppVersionManageService;


    @Resource(name="trainService")
    private TrainService trainService;


    /**
	 * Method name: GetCompanyList <BR>
	 * Description: 获取企业列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetCompanyList")
	@ResponseBody
	public Object GetCompanyList(HttpServletRequest request){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			
			List<ManageCompanyBean> list = manageCompanyService.selectCompanyList(null);
			for(ManageCompanyBean bean:list){
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("TenantId", bean.getId());
				resultMap.put("TenantName", bean.getName());
				resultMap.put("Logo", bean.getLogoImage());
				resultList.add(resultMap);
			}
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultList;
	}
	
	/**
	 * Method name: GetCompanyId <BR>
	 * Description: 估计企业名称获取企业编号 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetCompanyId")
	@ResponseBody
	public Object GetCompanyId(HttpServletRequest request,String companyName){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			Integer id = manageCompanyService.getCompanyIdByName(companyName);
			if(id!=null){
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
				resultMap.put("tid", id);
			}else{
				resultMap.put("result", 0);
				resultMap.put("msg", "FAIL");
			}
			
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetCompanyByUrl <BR>
	 * Description: 估计企业域名获取企业 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetCompanyByUrl")
	@ResponseBody
	public Object GetCompanyByUrl(HttpServletRequest request,String url){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String domain = String.valueOf(url).split(Constant.PULIAN_SITE)[0].replace(Constant.HTTP, "");
			//根据域名获取该用户的公司
			ManageCompanyVo vo = new ManageCompanyVo();
			vo.setDomain(domain);
			List<ManageCompanyBean> comList = manageCompanyService.selectCompanyList(vo);
			if(comList!=null&&comList.size()==1){
				resultMap.put("TenantId", comList.get(0).getId());
				resultMap.put("TenantName", comList.get(0).getName());
				resultMap.put("Logo", comList.get(0).getLogoImage());
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
				resultMap.put("tid", comList.get(0).getId());
			}else{
				resultMap.put("result", 0);
				resultMap.put("msg", "FAIL");
			}
			
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
		}
		return resultMap;
	}
	
	/**
	 * Method name: login <BR>
	 * Description: 登录的ajax <BR>
	 * Remark: <BR>
	 */
	@RequestMapping(value="login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, String username, String pwd,Integer tid){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Integer companyId = tid;
		resultMap.put("tid", companyId);
		try {
			log.debug("系统登陆 参数信息 UserName: " + username + "PassWord: " + pwd+" 公司id:"+companyId);
			
			ManageCompanyBean company = new ManageCompanyBean();
			//根据域名获取该用户的公司
			ManageCompanyVo vo = new ManageCompanyVo();
			vo.setId(companyId);
			List<ManageCompanyBean> comList = manageCompanyService.selectCompanyList(vo);
			if(comList != null && comList.size() > 0){
				company = comList.get(0);
				if(companyId > Constant.PULIAN_COMPANY_ID){
					//判断公司账号过期时间
					if(new Date().getTime() > TimeUtil.parseString2Date(company.getEndTime()).getTime()){
						resultMap.put("result", 0);
						resultMap.put("msg", "该企业租用时间已经过期");
						return resultMap;
					}
					//判断该企业是否冻结
					if(!"1".equals(company.getStatus().toString())){
						resultMap.put("result", 0);
						resultMap.put("msg", "该企业已经被冻结");
						return resultMap;
					}
				}
			}
			//根据域名、登陆账号获取该用户
			ManageUserBean userBean = manageUserService.findUserByUserName(username.trim(), companyId);
			
			if (null != userBean) {
				
				if (CommonUtil.getMD5(pwd).equalsIgnoreCase(userBean.getPassword())) {
					
					if(!"1".equals(userBean.getStatus().toString())){
						resultMap.put("result", 0);
						resultMap.put("msg", "该账号已经被冻结");
						return resultMap;
					}
					
					// 设置Session
					HttpSession session = request.getSession();
					session.setAttribute(Constant.SESSION_USERID_LONG, userBean.getId());
					session.setAttribute(Constant.SESSION_USERNAME_STRING, userBean.getUserName());
					session.setAttribute(Constant.SESSION_NAME, userBean.getName());
					session.setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(userBean.getId()));
					
					log.debug(username+" 登陆成功");
					//积累积分
					integralManageService.handleIntegral(Integer.parseInt(userBean.getId()),7009);
					resultMap.put("result", 1);
					resultMap.put("msg", "登陆成功");
					resultMap.put("uid", userBean.getId());
					resultMap.put("Realname",userBean.getName());
					resultMap.put("Photo",userBean.getHeadPhoto());
					return resultMap;
					
				} else {
					
					log.debug(username+" 密码错误");
					resultMap.put("result", 0);
					resultMap.put("msg", "密码错误");
					return resultMap;
				}
				
			} else {
				
				log.debug(username+" 不存在");
				resultMap.put("result", 0);
				resultMap.put("msg", "账号不存在");
				return resultMap;
			}
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "ERROR");
			return resultMap;
		} 

	}
	
	/**课程开始*/
	
	/**
	 * Method name: GetRecommendCourse <BR>
	 * Description: 获取推荐课程，每次获取 5 条数据，规则目前为最新创建的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetRecommendCourse")
	@ResponseBody
	public Object GetRecommendCourse(HttpServletRequest request,
			Integer tid,Integer uid,Integer top){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(top==null){
			top=5;
		}
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getRecommendCourse(tid,uid,top,1,user.getPostId(),user.getDeptId(),subCompanyId);
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				courseMap.put("FrontImg", vo.getFrontImage());
				courseMap.put("WareCount", vo.getWareNum());
				courseMap.put("IsCollect", vo.getIsCollect());
				courseMap.put("CourseCommentScore", vo.getAverageScore());
				courseMap.put("LearnUserCount", vo.getStudentNum());
				courseMap.put("CollectUserCount", vo.getCollectUserCount());
				courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
				resultList.add(courseMap);
			}
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount", resultList.size());
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount",0);
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetTopNewCourse <BR>
	 * Description: 获取推荐课程，每次获取 5 条数据，规则目前为最新创建的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetTopNewCourse")
	@ResponseBody
	public Object GetTopNewCourse(HttpServletRequest request,
			Integer tid,Integer uid,Integer top){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(top==null){
			top=5;
		}
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getRecommendCourse(tid,uid,top,1,user.getPostId(),user.getDeptId(),subCompanyId);
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				courseMap.put("FrontImg", vo.getFrontImage());
				courseMap.put("WareCount", vo.getWareNum());
				courseMap.put("IsCollect", vo.getIsCollect());
				courseMap.put("CourseCommentScore", vo.getAverageScore());
				courseMap.put("LearnUserCount", vo.getStudentNum());
				courseMap.put("CollectUserCount", vo.getCollectUserCount());
				courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
				resultList.add(courseMap);
			}
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount", resultList.size());
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount",0);
		}
		return resultMap;
	}
	

	/**
	 * Method name: GetTopHotCourse <BR>
	 * Description: 获取推荐课程，每次获取 5 条数据，规则目前为学习最多的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetTopHotCourse")
	@ResponseBody
	public Object GetTopHotCourse(HttpServletRequest request,
			Integer tid,Integer uid,Integer top){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(top==null){
			top=5;
		}
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getRecommendCourse(tid,uid,top,2,user.getPostId(),user.getDeptId(),subCompanyId);
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				courseMap.put("FrontImg", vo.getFrontImage());
				courseMap.put("WareCount", vo.getWareNum());
				courseMap.put("IsCollect", vo.getIsCollect());
				courseMap.put("CourseCommentScore", vo.getAverageScore());
				courseMap.put("LearnUserCount", vo.getStudentNum());
				courseMap.put("CollectUserCount", vo.getCollectUserCount());
				courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
				resultList.add(courseMap);
			}
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount", resultList.size());
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount",0);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: GetShortcutMenuCount <BR>
	 * Description: 获取快捷菜单上的数量<BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetShortcutMenuCount")
	@ResponseBody
	public Object GetShortcutMenuCount(HttpServletRequest request,Integer  tid,Integer uid,Integer type,String lastupdatetime){
		Map<String,Object> resultMap = new HashMap<String,Object>();

		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			if(type==0){
				int size =studentCourseService.getNewCoursesCount(tid,subCompanyId,lastupdatetime,uid,user.getPostId(),user.getDeptId());
				resultMap.put("result", 1);
				resultMap.put("count ", size);
			}else if(type==1){
				ExamQueryConditionBean bean =new ExamQueryConditionBean ();
				bean.setExam_status(0);
				bean.setUser_id(uid);
				int size = myExamManageService.getExamRecorderCountForMobile(bean);
				resultMap.put("result", 1);
				resultMap.put("count ", size);
			}else{
				resultMap.put("result", 0);
			}
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
		}
		return resultMap;
	} 
	
	
	/**
	 * Method name: GetSearchList  <BR>
	 * Description: 根据关键字进行搜索 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchList")
	@ResponseBody
	public Object GetSearchList (HttpServletRequest request,
			Integer tid,Integer uid,Integer type,String name,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		pageIndex = (pageIndex-1)*pageSize;
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			if(type==null||type==0){//全部
				List<Map<String,Object>> courseList = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> examList = new ArrayList<Map<String,Object>>();
				getSearchCourseList(uid,user.getPostId(),user.getDeptId(),tid, subCompanyId,null, name, pageIndex, pageSize,courseList);
				getSearchExamList( name, uid, null, pageIndex, pageSize,examList);
				resultMap.put("course", courseList);
				resultMap.put("exam", examList);
			}else if(type==1){//课程
				List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
                getSearchCourseList(uid,user.getPostId(),user.getDeptId(),tid, subCompanyId,null, name, pageIndex, pageSize,resultList);
                resultMap.put("exam", resultList);
			}else if(type==2){//考试
				List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
				getSearchExamList( name, uid, type, pageIndex, pageSize,resultList);
				resultMap.put("exam", resultList);
			}
			
			
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "ERROR");
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: GetSearchCourseList <BR>
	 * Description: 获取课程列表，每次获取 16 条数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchCourseList")
	@ResponseBody
	public Object GetSearchCourseList(HttpServletRequest request,Integer uid,
			Integer tid,Integer cateid,String name,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		if(cateid!=null&&cateid==0){
			cateid=null;
		}
		pageIndex = (pageIndex-1)*pageSize;
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			//查询课程
			getSearchCourseList(uid,user.getPostId(),user.getDeptId(),tid, subCompanyId,cateid, name, pageIndex, pageSize,resultList);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount", resultList.size());
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount",0);
		}
		return resultMap;
	}
	
	/**
	 * 返回数据封装
	 * @param tid
	 * @param subCompanyId
	 * @param cateid
	 * @param name
	 * @param pageIndex
	 * @param pageSize
	 * @param resultList
	 * @throws Exception
	 */
	private void getSearchCourseList(Integer uid,Integer postId,Integer deptId,Integer tid,Integer subCompanyId,Integer cateid,String name,Integer pageIndex,Integer pageSize,List<Map<String,Object>> resultList)throws Exception{
		
		List<ResCourseVo> courses = studentCourseService.getSearchCourseList(tid,subCompanyId,cateid,name,pageIndex,pageSize,uid,postId,deptId);
		for(ResCourseVo vo :courses){
			Map<String,Object> courseMap = new HashMap<String,Object>();
			courseMap.put("CourseId", vo.getId());
			courseMap.put("CourseName", vo.getName());
			courseMap.put("FrontImg", vo.getFrontImage());
			courseMap.put("WareCount", vo.getWareNum());
			courseMap.put("IsCollect", vo.getIsCollect());
			courseMap.put("CourseCommentScore", vo.getAverageScore());
			courseMap.put("LearnUserCount", vo.getStudentNum());
			courseMap.put("CollectUserCount", vo.getCollectUserCount());
			courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
			resultList.add(courseMap);
		}
	}
	
	/**
	 * Method name: GetSearchCourseName <BR>
	 * Description: 搜索课程，提示相关的课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchCourseName")
	@ResponseBody
	public Object GetSearchCourseName(HttpServletRequest request,
			Integer tid,Integer uid,Integer cateid,String name,Integer top){

		Map<String,Object> resultMap =new HashMap<String,Object>();
	
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(cateid!=null&&cateid==0){
			cateid=null;
		}
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getSearchCourseName(tid,subCompanyId,cateid,name,top,uid,user.getPostId(),user.getDeptId());
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				
				resultList.add(courseMap);
			}
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount", resultList.size());
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount",0);
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetCourseCategorys <BR>
	 * Description: 查询该公司的所有课程体系 （根节点课程体系）  <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetCourseCategorys")
	@ResponseBody
	public Object GetCourseCategorys(HttpServletRequest request,Integer tid,Integer uid){
		 Map<String,Object> resultMap =new HashMap<String,Object>();
		if(tid != null){
			try {
				log.info("开始查询公司id为"+tid+"的所有课程体系");
				ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
				if(user==null||user.getCompanyId()==null){
					return resultMap;
				}
				//Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
				
				List<ResCourseCategoryBean> courseCategorys = studentCourseService.getCourseCategorysByCompanyId(tid,tid);
				getChildCategorys(courseCategorys);
				resultMap.put("data",courseCategorys);
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
				log.info("查询公司id为"+tid+"所有课程体系结束");
			} catch (Exception e) {
				resultMap.put("result", 0);
				resultMap.put("msg", "FAIL");
				log.warn(MobileAction.class,e);
			}
		}
		return resultMap;
	}
	
   /**
    * 查询出所有子体系
    * @param courseCategorys
    * @throws Exception
    */
	private void getChildCategorys(List<ResCourseCategoryBean> courseCategorys)throws Exception{
		if(courseCategorys!=null&&courseCategorys.size()>0){
			for(ResCourseCategoryBean bean:courseCategorys ){
				List<ResCourseCategoryBean> children =studentCourseService.getChildCategorys(bean.getId());
				bean.setChildren(children);
				if(children!=null&&children.size()>0){
					getChildCategorys(children);
				}
			}
		}
		
	}
	/**
	 * Method name: CollectCourse <BR>
	 * Description: 收藏该门课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("CollectCourse")
	@ResponseBody
	public Object CollectCourse(HttpServletRequest request,Integer courseId,Integer uid,Integer tid){
            Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			CourseCollectionBean collection =new CourseCollectionBean();
			collection.setCourseId(courseId);
			collection.setUserId(uid);
			collection.setCompanyId(tid);
			courseDetailService.collectThisCourse(collection);
			//设置积分（收藏课程）
			integralManageService.handleIntegral(collection.getUserId(), 7004);
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
		} catch (Exception e) {
			resultMap.put("result", 0);
		    resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	/**
	 * Method name: RemoveCollectCourse <BR>
	 * Description: 取消收藏该课程 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("RemoveCollectCourse")
	@ResponseBody
	public Object RemoveCollectCourse(HttpServletRequest request,Integer uid,String courseIds){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			if(courseIds!=null){
				String [] ids =  courseIds.split(",");
				for(String id :ids){
					courseCollectionService.cancelCollection(uid,Integer.parseInt(id));
				}
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			}else{
				courseCollectionService.cancelAllCollection(uid);
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			}
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetCourseDetail <BR>
	 * Description: 获取课程详情 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetCourseDetail")
	@ResponseBody
	public Object GetCourseDetail(HttpServletRequest request,
			Integer tid,Integer uid,Integer courseId){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			//查询课程
			ResCourseVo coursevo = studentCourseService.selectCourseByIdForMobile(courseId,uid);
			Map<String,Object> courseMap =new HashMap<String,Object>();
			Map<String,Object> resourceMap =new HashMap<String,Object>();
			courseMap.put("CourseId", coursevo.getId());
			courseMap.put("CourseName", coursevo.getName());
			courseMap.put("FrontImg", coursevo.getFrontImage());
			courseMap.put("IsCollect", coursevo.getIsCollect());
			courseMap.put("CourseCommentScore", coursevo.getAverageScore());
			courseMap.put("MyCourseCommentScore", coursevo.getMyEvaluatorScore());
			courseMap.put("LearnUserCount", coursevo.getStudentNum());
			courseMap.put("CollectUserCount", coursevo.getCollectUserCount());
			courseMap.put("CourseCommentUserCount", coursevo.getEvaluatorNum());
			Integer recordId =  coursevo.getRecordId();
			if(recordId==null){
				recordId=0;
			}
			courseMap.put("recordId",recordId);
			courseMap.put("Outline", coursevo.getOutline());
			courseMap.put("Tags", coursevo.getTags());
			courseMap.put("CommentCount", coursevo.getEvaluatorCount());
			
			resourceMap.put("wareCount", coursevo.getWareNum());
			resourceMap.put("commentCount", coursevo.getEvaluatorCount());
			resourceMap.put("examCount", coursevo.getExamNum());

			//系统中没有
			resourceMap.put("correlationCount", 0);
			
			resultMap.put("course", courseMap);
			resultMap.put("resourceCount", resourceMap);
			
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetCourseSection <BR>
	 * Description: 查询该课程所有章节 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  Object<BR>
	 */
	@RequestMapping("GetCourseSection")
	@ResponseBody
	public Object GetCourseSection(HttpServletRequest request,Integer courseId,Integer uid,Integer recordId){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			
		    	List<ResSectionBean> sections = courseDetailService.getCourseSections(courseId);
		    	for(ResSectionBean bean:sections){
		    		Map<String,Object> sectionMap =new HashMap<String,Object>();	
		    		sectionMap.put("SectionId", bean.getId());
		    		sectionMap.put("SectionName", bean.getName());
		    		sectionMap.put("descr", bean.getDescr());
		    		
		    		resultList.add(sectionMap);
		    	}
		    	resultMap.put("dataList", resultList);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	
	/**
	 * Method name: GetCourseWare <BR>
	 * Description: 查询该课程所有课件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  Object<BR>
	 */
	@RequestMapping("GetCourseWare")
	@ResponseBody
	public Object GetCourseWare(HttpServletRequest request,Integer courseId,Integer uid,Integer sectionId,Integer recordId){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			//TODO 查询课程章节内所有课件
		    	List<SectionWareVo> wares = studentCourseService.getAllCourseWares(sectionId, uid);
		    	for(SectionWareVo ware:wares){
		    		Map<String,Object> wareMap =new HashMap<String,Object>();	
		    		wareMap.put("WareId", ware.getId());
		    		wareMap.put("WareName", ware.getName());
		    		wareMap.put("Progress", ware.getProgressPercent()==null?0:ware.getProgressPercent());
		    		wareMap.put("Path", ware.getFilePath());
		    		wareMap.put("sectionId", ware.getSectionId());
                    wareMap.put("total", ware.getTotalContent());
                    wareMap.put("current", ware.getCurrentContent());
		    		Integer Type = ware.getType();
		    		if(Type==1){
		    			Type=0;
		    		}else if(Type==2){
		    			Type =1;
		    		}else if (Type == 3)
		    		{
		    			Type=2;
		    		}
		    		wareMap.put("Type",Type);
		    		wareMap.put("Player",ware.getFileName());
		    		//以下系统没有数据
		    		wareMap.put("FileSize", 0);
		    		wareMap.put("Duration", "");
		    		wareMap.put("PackId", 0);
		    		wareMap.put("WareType",Type);
		    		resultList.add(wareMap);
		    	}
		    	resultMap.put("dataList", resultList);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: GetCourseExam <BR>
	 * Description: 查询课程测试及测试记录 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param sectionId
	 * @return  Object<BR>
	 */
	@RequestMapping("GetCourseExam")
	@ResponseBody
	public Object GetCourseExam(HttpServletRequest request,Integer courseId,Integer sectionId,Integer uid,Integer recordId){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			
			List<SectionExamVo> exams = courseDetailService.getSectionTests(sectionId,uid);
		    	for(SectionExamVo exam:exams){
		    		Map<String,Object> examMap =new HashMap<String,Object>();	
		    		examMap.put("ExamTitle", exam.getTitle());
		    		examMap.put("ExamId", exam.getRsExamId());
		    		examMap.put("Pass", exam.getMaxScore()>=exam.getPassScore()?1:0);
		    		examMap.put("TimeLength", exam.getExamDuration());
		    		examMap.put("Score", exam.getMaxScore());
		    		examMap.put("PassScore", exam.getPassScore());
		    		examMap.put("TotalScore",exam.getTotalScore());
		    		examMap.put("IsGoIn",exam.getTestTimes()<exam.getPermitTestTimes()?0:1);
		    		
		    		examMap.put("sectionId", exam.getSectionId());
		    		resultList.add(examMap);
		    	}
		    	resultMap.put("dataList", resultList);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}

	
	/**
	 * Method name: GetCourseComment <BR>
	 * Description: 获取该课程所有评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  Object<BR>
	 */
	@RequestMapping("GetCourseComment")
	@ResponseBody
	public Object GetCourseComment(HttpServletRequest request,Integer courseId,Integer uid,
			  Integer pageIndex,Integer pageSize){
	
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
	
		if(courseId != null &&  pageIndex != null  && pageSize != null ){
			try {
				ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
				if(user==null||user.getCompanyId()==null){
					return resultMap;
				}
				Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
				Integer companyId = user.getCompanyId();
				//注意page是从1开始的
				Integer fromNum = (pageIndex-1)*pageSize;
				//分页查询
				log.info("查询课程"+courseId+"的评价");
				Integer evaluateCount = courseEvaluateService.getEvaluateCount(courseId,companyId,subCompanyId);
				List<CourseEvaluationVo> evaluations = courseEvaluateService.getCourseEvaluations(
						courseId,companyId,subCompanyId,fromNum,pageSize);
				for(CourseEvaluationVo vo:evaluations){
					Map<String,Object> commentMap =new HashMap<String,Object>();	
					commentMap.put("CommentId", vo.getId());
					commentMap.put("Photo", vo.getUserPic());
					commentMap.put("Realname",vo.getUserName());
					commentMap.put("UserId", vo.getUserId());
					commentMap.put("CommentTimeStr", CommonUtil.getDateTimeString(vo.getCommentTime()));
					commentMap.put("CommentTime",  CommonUtil.getDateTimeString(vo.getCommentTime()));
					commentMap.put("CommentScore", vo.getScore());
					commentMap.put("Comment", vo.getContent());
					//评论没有回答
					commentMap.put("Answer", null);
					
					
					resultList.add(commentMap);
				}
				log.info("查询课程"+courseId+"的评价结束");
				resultMap.put("totalCount", evaluateCount);
				resultMap.put("dataList", resultList);
				return resultMap;
			} catch (Exception e) {
				log.warn(MobileAction.class,e);
			}
		}
		return resultMap;
	} 

	
	/**
	 * Method name: SubmitCourseComment <BR>
	 * Description: 添加一条课程评价 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("SubmitCourseComment")
	@ResponseBody
	public Object SubmitCourseComment(HttpServletRequest request,Integer  courseId,Integer uid,Integer tid,Integer parentId,String comment,Integer score){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
	
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			if(score!=null){
				courseEvaluateService.giveMyScore(uid, courseId, score, tid, subCompanyId);
			}
			if(comment!=null){
				courseEvaluateService.giveMyEvaluation(uid, courseId, comment, tid, subCompanyId);
			}
			
			
			//设置积分（完成课程评估）
			//integralManageService.handleIntegral(uid, 7003);
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCESS");
			
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**   
	 * Method name: BeforeLearnWare <BR>  
	 * Description: 在课件学习前调用 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("BeforeLearnWare")
	@ResponseBody
	public Object BeforeLearnWare(HttpServletRequest request,Integer recordId,
			Integer uid,Integer tid,Integer wareId,Integer wareType,Integer courseId,Integer sectionId){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();

        if(uid==null||tid==null||wareId==null||wareType==null||courseId==null||sectionId==null){
            resultMap.put("result", 0);
            resultMap.put("msg", "参数提交错误");
            return resultMap;
        }
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			int realWareType =wareType+1 ;
			courseRecordService.recordCourseAndWare(wareId,realWareType,uid,sectionId,courseId,tid,subCompanyId);
			
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "ERROR");
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	
	
	
	/**   
	 * Method name: RecordLearnWareProcess <BR>  
	 * Description: 记录课程和课件学习情况 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("RecordLearnWareProcess")
	@ResponseBody
	public Object RecordLearnWareProcess(HttpServletRequest request,Integer recordId,
			Integer uid,Integer tid,Integer wareId,Integer wareType,Integer courseId,Integer sectionId,String progress,String allprogress){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
        if(uid==null||tid==null||wareId==null||wareType==null||courseId==null||sectionId==null||progress==null||allprogress==null){
            resultMap.put("result", 0);
            resultMap.put("msg", "参数提交错误");
            return resultMap;
        }
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			log.info("开始记录课程"+courseId+"和课件"+wareId+"的学习情况");

			CourseWareRecordBean recordBean = courseRecordService.getCourseWareByConditions(sectionId,wareId,uid);
			if(recordBean==null){
				int realWareType =wareType+1 ;
				courseRecordService.recordCourseAndWare(wareId,realWareType,uid,sectionId,courseId,tid,subCompanyId);
				recordBean = courseRecordService.getCourseWareByConditions(sectionId,wareId,uid);
			}
			if(recordId==null||recordId==0){
				recordId=recordBean.getRecordId();
			}
			log.info("课件类型"+wareType);
			if(wareType==0){//Scorm课件
				String[] total = JsonUtil.getStringArray4Json(allprogress);
				String[] studyed = JsonUtil.getStringArray4Json(progress);
				courseRecordService.recordScromWareAfterLeavePage(recordBean.getId(),recordId,courseId,uid,total,studyed);
			}else if(wareType==1){//普通课件
                if(Integer.parseInt(allprogress)<Integer.parseInt(progress)){
                    resultMap.put("result", 0);
                    resultMap.put("msg", "已学习页数大于总页数");
                    return resultMap;
                }
				courseRecordService.recordSwfWareAfterLeavePage(recordBean.getId(),recordId,courseId,uid,allprogress,progress);
			}else if(wareType==2){//视频课件
                if(Integer.parseInt(allprogress)<Integer.parseInt(progress)){
                    resultMap.put("result", 0);
                    resultMap.put("msg", "已学习时间大于总时间");
                    return resultMap;
                }
				courseRecordService.recordVedioWareAfterLeavePage(recordBean.getId(),recordId,courseId,uid,allprogress,progress);
			}
			log.info("记录课程"+courseId+"和课件"+wareId+"的学习情况完毕");
			
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "ERROR");
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	/**
	 * Method name: GoInExam <BR>
	 * Description: 进入课程考试<BR>
	 * Remark: <BR>
	 * @param request
	 * @param courseId
	 * @return  Object<BR>
	 */
	@RequestMapping("GoInExam")
	@ResponseBody
	public Object GoInExam(HttpServletRequest request,Integer recordId,Integer tid,Integer uid,Integer courseId,Integer sectionId,Integer examId){
		Map<String,Object> resultMap =new HashMap<String,Object>();
	
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			log.info("开始保存用户"+uid+"的考试和考试记录信息");
			ResSectionExamBean rsExamBean=courseDetailService.getSectionExamById(examId);
			Map<String, Integer> IntegerMap = courseRecordService.saveExamAndAssignInfo(rsExamBean.getExamId(),rsExamBean.getExamDuration(),rsExamBean.getExamTimes(),rsExamBean.getPassPercent(),uid,tid,subCompanyId);
			Integer examRecordId = courseRecordService.recordExamAndCourseBeforeTest(sectionId,rsExamBean.getExamId(), uid,
					courseId,  tid, subCompanyId,rsExamBean.getExamTimes(),rsExamBean.getPassPercent());
			resultMap.put("result", 1);
			resultMap.put("euId", IntegerMap.get("assignId"));
			resultMap.put("examRecordId", examRecordId);
			log.info("保存用户"+uid+"的考试和考试记录信息完毕");
		} catch (Exception e) {
			resultMap.put("result", 0);
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	
	
	/**
	 * Method name: StartLearnCourse <BR>
	 * Description: 学习课程，生成学习记录 ID<BR>
	 * Remark: <BR>
	 */
	@RequestMapping("StartLearnCourse")
	@ResponseBody
	public Object StartLearnCourse(HttpServletRequest request,Integer recordId,Integer uid,Integer courseId){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
	
		try {
			ManageUserBean user =manageUserService.findUserById(uid.toString()) ;
			if(user==null||user.getCompanyId()==null){
				return resultMap;
			}
			Integer subCompanyId =user.getSubCompanyId()==null?1:user.getSubCompanyId();
			Integer id =courseRecordService.saveCourseRecord(courseId,uid,user.getCompanyId(),subCompanyId);
			resultMap.put("recordId", id);
		} catch (Exception e) {
			resultMap.put("recordId", 0);
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: GetSearchMyCourseList <BR>
	 * Description: 获取我的课程列表，每次获取 16 条数据<BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchMyCourseList")
	@ResponseBody
	public Object GetSearchMyCourseList(HttpServletRequest request,
			Integer tid,Integer uid,Integer cateid,String name,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		if(cateid!=null&&cateid==0){
			cateid=null;
		}
		pageIndex = (pageIndex-1)*pageSize;
		try {
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getMyCoursesForMobile(name,cateid,uid,null,pageIndex,pageSize);
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				courseMap.put("FrontImg", vo.getFrontImage());
				courseMap.put("WareCount", vo.getWareNum());
				courseMap.put("IsCollect", vo.getIsCollect());
				courseMap.put("CourseCommentScore", vo.getAverageScore());
				courseMap.put("LearnUserCount", vo.getStudentNum());
				courseMap.put("CollectUserCount", vo.getCollectUserCount());
				courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
				Integer process =vo.getLearnProcess();
				if(process==1){
					process=2;
				}else{
					process =1;
				}
				courseMap.put("LearnProcess", process);
				resultList.add(courseMap);
			}
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount", resultList.size());
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			resultMap.put("dataList", resultList);
			resultMap.put("recordCount",0);
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetMyCollectCourseList <BR>
	 * Description: 获取我收藏的课程列表，每次获取 16 条数据<BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetMyCollectCourseList")
	@ResponseBody
	public Object GetMyCollectCourseList(HttpServletRequest request,
			Integer tid,Integer uid,String courseName,Integer pageIndex,Integer pageSize){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		pageIndex = (pageIndex-1)*pageSize;
		try {
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getCollectCoursesForMobile(courseName,uid,pageIndex,pageSize);
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				courseMap.put("FrontImg", vo.getFrontImage());
				courseMap.put("WareCount", vo.getWareNum());
				courseMap.put("IsCollect", vo.getIsCollect());
				courseMap.put("CourseCommentScore", vo.getAverageScore());
				courseMap.put("LearnUserCount", vo.getStudentNum());
				courseMap.put("CollectUserCount", vo.getCollectUserCount());
				courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
				
				resultList.add(courseMap);
			}
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultList;
	}
	
	/**
	 * Method name: GetMyCourseList <BR>
	 * Description: 获取我学习过的课程列表，每次获取 16 条数据 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetMyCourseList")
	@ResponseBody
	public Object GetMyCourseList(HttpServletRequest request,
			Integer tid,Integer uid,Integer learnStatus,String courseName,Integer pageIndex,Integer pageSize){
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		
		pageIndex = (pageIndex-1)*pageSize;
		try {
			//查询课程
			List<ResCourseVo> courses = studentCourseService.getMyCoursesForMobile(courseName,null,uid,learnStatus,pageIndex,pageSize);
			for(ResCourseVo vo :courses){
				Map<String,Object> courseMap = new HashMap<String,Object>();
				courseMap.put("CourseId", vo.getId());
				courseMap.put("CourseName", vo.getName());
				courseMap.put("FrontImg", vo.getFrontImage());
				courseMap.put("WareCount", vo.getWareNum());
				courseMap.put("IsCollect", vo.getIsCollect());
				courseMap.put("CourseCommentScore", vo.getAverageScore());
				courseMap.put("LearnUserCount", vo.getStudentNum());
				courseMap.put("CollectUserCount", vo.getCollectUserCount());
				courseMap.put("CourseCommentUserCount", vo.getEvaluatorNum());
				
				resultList.add(courseMap);
			}
			
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
			
		}
		return resultList;
	}
	
	
	/**课程结束*/
	
	/**考试开始*/
	/**
	 * Method name: GetSearchExamList <BR>
	 * Description: 获取考试列表，每次获取 16 条数据 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchExamList")
	@ResponseBody
	public Object GetSearchExamList(HttpServletRequest request,String name,Integer uid,Integer type,Integer pageIndex,Integer pageSize){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		pageIndex = (pageIndex-1) * pageSize;
		try {
		
			   getSearchExamList( name, uid, type, pageIndex, pageSize,resultList);
			   resultMap.put("dataList", resultList);
			   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String dateString  = f.format(Calendar.getInstance().getTime());
			   resultMap.put("serverTime", dateString);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	private void  getSearchExamList(String name,Integer uid,Integer type,Integer pageIndex,Integer pageSize,List<Map<String,Object>> resultList) throws Exception{
		ExamQueryConditionBean bean =new ExamQueryConditionBean ();
		
		bean.setFromNum(pageIndex);
		bean.setPageSize(pageSize);
		bean.setUser_id(uid);
		if(type==null){
			bean.setExam_status(0);
		}else if(type==0){
			bean.setExam_status(2);
		}else if(type==1){
			bean.setExam_status(1);
		}else if(type ==2){
			bean.setExam_status(4);
		}
		bean.setTitle(name);
		List<ExamRecorderVo> exams = myExamManageService.getMyExamRecorderVoForMobile(bean);
    	for(ExamRecorderVo exam:exams){
    		Map<String,Object> examMap =new HashMap<String,Object>();	
    		examMap.put("ExamTitle", exam.getTitle());
    		examMap.put("EuId", exam.getExamAssignId());
    		examMap.put("Pass", exam.getIsPassed());
    		Integer IsGoIn = 1;
    		
    		if(exam.getExamState().equals(ExamState.PROCESSING) ||exam.getExamState().equals(ExamState.BEFORE_START)){
    			
    			if(exam.getAllowTimes()>exam.getTestTimes()){
    				ExamAssignBean assignBean = myExamManageService.getTestTimes(exam.getExamAssignId());
    				if(assignBean.getIsAttend()!=null&&assignBean.getIsAttend()==1){
    					IsGoIn= 0;
    				}
    				
    			}
       		}
    		examMap.put("IsGoIn", IsGoIn);
    		examMap.put("ExamBegintime", exam.getBeginTime());
    		examMap.put("ExamEndTime", exam.getEndTime());
    		examMap.put("TestTimes", exam.getAllowTimes());
    		examMap.put("GoInTestTimes", exam.getTestTimes());
    		examMap.put("PassScore", exam.getPassScore());
    		examMap.put("TotalScore",exam.getTotalScore());
    		examMap.put("ExamLength",exam.getDuration()*60);
    		examMap.put("isAutoMarking", exam.getIsAutoMarking());
    		//系统没有这个字段(考试剩余时间,现在返回的是剩余次数)
    		examMap.put("RemainingTime",exam.getAllowTimes()-exam.getTestTimes());
    		examMap.put("ExamState",exam.getExamState());
    		resultList.add(examMap);
    	}
	}
	
	/**
	 * Method name: GetSearchExamName <BR>
	 * Description: 搜索考试，提示相关的考试 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchExamName")
	@ResponseBody
	public Object GetSearchExamName(HttpServletRequest request,String name,Integer uid,Integer type,Integer top){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
	
		try {
			ExamQueryConditionBean bean =new ExamQueryConditionBean ();
			int from = 0;
			if(top!=null&&top!=0){
				bean.setFromNum(from);
				bean.setPageSize(top);
			}
			bean.setUser_id(uid);
			if(type==0){
				bean.setExam_status(2);
			}else if(type==1){
				bean.setExam_status(1);
			}else if(type ==2){
				bean.setExam_status(4);
			}
			bean.setTitle(name);
			List<ExamRecorderVo> exams = myExamManageService.getMyExamRecorderVoForMobile(bean);
		    	for(ExamRecorderVo exam:exams){
		    		Map<String,Object> examMap =new HashMap<String,Object>();	
		    		examMap.put("ExamTitle", exam.getTitle());
		    		examMap.put("EuId", exam.getExamAssignId());
		    		Integer IsGoIn = 1;
		    		if(exam.getExamState().equals(ExamState.PROCESSING) ||exam.getExamState().equals(ExamState.BEFORE_START)){
		    			if(exam.getAllowTimes()>exam.getTestTimes()){
		    				ExamAssignBean assignBean = myExamManageService.getTestTimes(exam.getExamAssignId());
		    				if(assignBean.getIsAttend()!=null&&assignBean.getIsAttend()==1){
		    					IsGoIn= 0;
		    				}
		    				
		    			}
		       		}
		    		examMap.put("IsGoIn", IsGoIn);
		    		
		    		resultList.add(examMap);
		    	}
		    	resultMap.put("dataList", resultList);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	
	/**
	 * Method name: IsCanExamTest <BR>
	 * Description: 获取考试基本信息 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("IsCanExamTest")
	@ResponseBody
	public Object IsCanExamTest(HttpServletRequest request,Integer type,Integer Euid){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		if(type==null){
			type=0;
		}
		try {
			Map<String,Object> examMap =new HashMap<String,Object>();	
			ExamAssignBean assignBean = myExamManageService.getExamAssignById(Euid);
			ExamScheduleVo exam = myExamManageService.getExamInfo(assignBean.getRelationId());
			ExamAssignBean bean = myExamManageService.getTestTimes(Euid);
			examMap.put("ExamTitle", exam.getTitle());
    		examMap.put("ExamRuleList", exam.getNotice());
    		examMap.put("ExamBegintime", exam.getBeginTime());
    		examMap.put("ExamEndTime", exam.getEndTime());
    		examMap.put("ExamLength",exam.getDuration()*60);
    		if(exam.getEndTime()!=null){
    			long durationTime = getDateMinusMinutes(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(exam.getEndTime()),new Date());
        		if(durationTime<0){
        			durationTime=0;
        		}
        		if(durationTime<exam.getDuration()){
        			examMap.put("ExamLength",durationTime);
        		}
    		}
    		
    	
    		//系统没有这个字段(考试剩余时间),剩余次数
    		examMap.put("RemainingTime",exam.getAllowTimes()-bean.getTestTimes());
    		
    		
    		examMap.put("isAutoMarking",exam.getIsAutoMarking());
    		resultMap.put("result", 1);
    		resultMap.put("msg", "SUCCESS");
    		if(type!=1){
    			resultMap.put("testTimes", exam.getAllowTimes()-bean.getTestTimes());
        		resultMap.put("examTestTimes", exam.getAllowTimes());
        		resultMap.put("isAttend", bean.getIsAttend());
    		    resultMap.put("data", examMap);
    		}
    		 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String dateString  = f.format(Calendar.getInstance().getTime());
				resultMap.put("serverTime", dateString);
			return resultMap;
		} catch (Exception e) {
			resultMap.put("result", 0);
    		resultMap.put("msg", "ERROR");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	 public  Long getDateMinusMinutes(Date d1, Date d2){
		 if(d1==null||d2==null){
			 return null;
		 }
	    	long l = (d1.getTime() - d2.getTime())/(1000);
	    	return l;
	    }
	
	
	/**
	 * Method name: GetExampaperDetail <BR>
	 * Description: 获取试卷详情 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("GetExampaperDetail")
	@ResponseBody
	public Object GetExampaperDetail(HttpServletRequest request,Integer Euid,Integer uid){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			ExamAssignBean assignBean = myExamManageService.getExamAssignById(Euid);
			ExamQueryConditionBean bean =new ExamQueryConditionBean ();
			bean.setFromNum(0);
			bean.setPageSize(5);
		
			bean.setUser_id(uid);
			bean.setId(assignBean.getRelationId());
			ExamScheduleVo exam = myExamManageService.getExamScheduleVo(assignBean.getRelationId());
			List<ExamRecorderVo> exams = myExamManageService.getMyExamRecorderVo(bean);
			ExamRecorderVo exam2=null;
			if(exams!=null&&exams.size()==1){
				 exam2=exams.get(0);
			}
			
		    	Map<String,Object> examMap =new HashMap<String,Object>();	
		    		examMap.put("ExamTitle", exam.getTitle());
		    		examMap.put("EuId", exam.getId());
		    		examMap.put("ExamRuleList", exam.getNotice());
		    		examMap.put("ExamBegintime", exam.getBeginTime());
		    		examMap.put("ExamEndTime", exam.getEndTime());
		    		examMap.put("displayMode", exam.getDisplayMode());//试卷显示方式1  整卷显示2  单题可逆3  单题不可逆
		    		examMap.put("PassScore", exam.getPassScore());
		    		examMap.put("TotalScore",exam.getTotalScore());
		    		examMap.put("ExamScore",exam.getTotalScore());
		    		examMap.put("ExamLength",exam.getDuration()*60);
		    		if(exam.getEndTime()!=null){
		    			Long durationTime = getDateMinusMinutes(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(exam.getEndTime()),new Date());
			    		if(durationTime<0){
			    			durationTime=0l;
			    		}
			    		if(durationTime<exam.getDuration()){
			    			examMap.put("ExamLength",durationTime);
			    		}
		    		}
		    		
		    		//后加
		    		examMap.put("IsAutoMarking",exam.getIsAutoMarking());
		    		
		    		
		    		if(exam2!=null){
		    			examMap.put("GoInTestTimes", exam2.getTestTimes());
		    			examMap.put("TestTimes", exam2.getAllowTimes());
		    			//系统没有这个字段(考试剩余时间),改为剩余次数
			    		examMap.put("RemainingTime",exam2.getAllowTimes()-exam2.getTestTimes());
			    		Integer IsGoIn = 1;
			    		if(exam2.getExamState().equals(ExamState.PROCESSING) ||exam2.getExamState().equals(ExamState.BEFORE_START)){
			    			if(exam2.getAllowTimes()>exam2.getTestTimes()){
			    				ExamAssignBean examAssignBean = myExamManageService.getTestTimes(Euid);
			    				if(examAssignBean.getIsAttend()!=null&&examAssignBean.getIsAttend()==1){
			    					IsGoIn= 0;
			    				}
			    				
			    			}
			       		}
			    		examMap.put("IsGoIn", IsGoIn);
			    		examMap.put("Pass", exam2.getIsPassed());
		    		}else{
		    			examMap.put("TestTimes", exam.getAllowTimes());
		    			examMap.put("GoInTestTimes", exam.getAllowTimes());
		    			examMap.put("IsGoIn", 1);
			    		examMap.put("Pass", 0);
			    		//系统没有这个字段(考试剩余时间),改为剩余次数
			    		examMap.put("RemainingTime",exam.getAllowTimes());
		    		}
		    		
		    		
		    		
		    	
		      List<Map<String,Object>> questionList = new ArrayList<Map<String,Object>>();
		     
		      PaperBean paper = exam.getPaperBean();
		      List<PaperQuestionBean> questionBeanList = paper.getPaperQuestionList();
		            int i= 1;
		            //循环试题list
		           for(PaperQuestionBean paperQuestion:questionBeanList){
		        	   Map<String,Object> questionMap =new HashMap<String,Object>();
		        	   QuestionBean question = paperQuestion.getQuestionBean();
		        	   Integer disType = question.getDisplayType();
		        	   //试题类型为多媒体时
		        	   questionMap.put("QType", disType);
		        	   //试题为填空题
		        	   if(disType == 5){
		        		   questionMap.put("FillBlankCount", question.getOptions().size());
		        		
		        	   }
		        	   
		        	   //组合题
		        	   if(disType==6){
		        		   List<Map<String,Object>> subQuestionList =   convertSubQuestion(question,null);
		        		   String [] subQuestionScoreArr  =paperQuestion.getScoreDetail().split(",");
		        		   for(int a=0;a<subQuestionScoreArr.length;a++){
		        			   subQuestionList.get(a).put("Score", subQuestionScoreArr[a]);
		        		   }
		        		   questionMap.put("subQuestionList", subQuestionList) ;
		        		   
		        	   }

		        	   questionMap.put("Score", paperQuestion.getScore());
		        	   questionMap.put("QuestionContent",question.getContent() );
		        	   questionMap.put("QuestionID", question.getId());
		        	   questionMap.put("QuestionOrder", i);
		        	   List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
		        	   //试题类型为非判断题
		        	   if(question.getType()!=4){
		        		   //循环试题选项list
		        		   for(QuestionOptionBean option:question.getOptions()){
		        			   Map<String,Object> optionMap =new HashMap<String,Object>();
		        			   optionMap.put("OptionId", option.getId());
		        			   optionMap.put("AnswerContent", option.getContent());
		        			   if(question.getType()==1||question.getType()==5){
		            				 optionMap.put("AnswerContent", "");
		            			   }
		        			   optionMap.put("Order", option.getOrderNum());
		        			   optionMap.put("oldOrder", option.getOrderNum());
		        			   optionMap.put("isPic", option.getType());//是否是图片
		        			   //试题类型为多媒体时
		        			   if(disType ==7){
		        				   questionMap.put("multimediaType", question.getMultimediaType());//多媒体类型1：图片 2：音频 3：视频
		        				   questionMap.put("multimediaUrl", question.getMultimediaUrl());
		        				   switch(question.getType()){
		        				   case 1:
		        					   optionMap.put("AnswerType", 0);
		        					   questionMap.put("QAnswerType", 0);
		        				        break;
		        				   case 2:
		        					   optionMap.put("AnswerType", 1);
		        					   questionMap.put("QAnswerType", 1);
		        				        break;
		        				   case 3:
		        					   optionMap.put("AnswerType", 2);
		        					   questionMap.put("QAnswerType", 2);
		        				        break;
		        				   default:
		        					   break;
		        				   }
		        				   
		        			   }else{
		        				   optionMap.put("AnswerType", question.getType());
		        			   }
		        			   optionList.add(optionMap);
			        	   } 
		        	   }else{
		        		   //试题为判断题时
		        		   for(int j=0;j<2;j++){
		        			   Map<String,Object> optionMap =new HashMap<String,Object>();
		        			   optionMap.put("OptionId", j);
		        			   if(j==0){
		        				   optionMap.put("AnswerContent", "正确");
		        			   }else{
		        				   optionMap.put("AnswerContent", "错误");
		        			   }
		        			  
		        			   optionMap.put("Order", j);
		        			   optionMap.put("oldOrder", j);
		        			   optionMap.put("AnswerType", question.getType());
		        			   optionList.add(optionMap);
		        		   }
		        		  
		        	   }
		        	   questionMap.put("QuestionAnswer", optionList);
		        	   questionList.add(questionMap);
		        	   i++;
		           }
		    		resultMap.put("exambase", examMap);
		    		resultMap.put("exampaper", questionList);
		    		resultMap.put("result", 1);
		    	    resultMap.put("msg", "SUCCESS");
			return resultMap;
		} catch (Exception e) {
			resultMap.put("result", 0);
    	    resultMap.put("msg", "ERROR");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	
	
	/**
	 * 转换组合题的子试题
	 * @param questionBean
	 * @return
	 */
	private List<Map<String,Object>> convertSubQuestion(QuestionBean questionBean,Integer type){
		 List<QuestionBean> questionBeanList = questionBean.getSubQuestions();
		 List<Map<String,Object>> subQuestionList = new ArrayList<Map<String,Object>>();
		 if(questionBeanList==null||questionBeanList.size()==0){
			return  subQuestionList;
		 }
		  int i= 1;
          //循环试题list
         for(QuestionBean question:questionBeanList){
      	   Map<String,Object> questionMap =new HashMap<String,Object>();
      	   Integer disType = question.getDisplayType();
      	   questionMap.put("QType", question.getDisplayType());
      	   questionMap.put("QuestionContent",question.getContent() );
      	   questionMap.put("QuestionID", question.getId());
      	   questionMap.put("QuestionOrder", i);
      	   List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
      	   //试题类型为非判断题
      	   if(question.getType()!=4){
      		   //循环试题选项list
      		   for(QuestionOptionBean option:question.getOptions()){
      			   Map<String,Object> optionMap =new HashMap<String,Object>();
      			   optionMap.put("OptionId", option.getId());
      			   optionMap.put("AnswerContent", option.getContent());
      			 if(question.getType()==1||question.getType()==5){
      				 optionMap.put("AnswerContent", "");
      			   }
      			   if(type!=null){
      				 optionMap.put("AnswerContent", option.getContent());
      				 optionMap.put("isTrue", option.getIsAnswer());
      				questionMap.put("analysis", question.getAnalysis());
      			   }
      			  
      			   
      			   optionMap.put("Order", option.getOrderNum());
      			   optionMap.put("oldOrder", option.getOrderNum());
      			   optionMap.put("isPic", option.getType());//是否是图片
      			   //试题类型为多媒体时
      			   if(disType ==7){
      				 questionMap.put("multimediaType", question.getMultimediaType());//多媒体类型1：图片 2：音频 3：视频
      				questionMap.put("multimediaUrl", question.getMultimediaUrl());
      				   switch(question.getType()){
      				   case 1:
      					   optionMap.put("AnswerType", 0);
      					   questionMap.put("QAnswerType", 0);
      				        break;
      				   case 2:
      					   optionMap.put("AnswerType", 1);
      					   questionMap.put("QAnswerType", 1);
      				        break;
      				   case 3:
      					   optionMap.put("AnswerType", 2);
      					   questionMap.put("QAnswerType", 2);
      				        break;
      				   default:
      					   break;
      				   }
      				   
      			   }else{
      				   optionMap.put("AnswerType", question.getType());
      			   }
      			   optionList.add(optionMap);
	        	   } 
      	   }else{
      		   //试题为判断题时
      		   for(int j=0;j<2;j++){
      			   Map<String,Object> optionMap =new HashMap<String,Object>();
      			   optionMap.put("OptionId", j);
      			   if(j==0){
      				   optionMap.put("AnswerContent", "正确");
      				  if(type!=null){
      					 if(question.getIsTrue()&&type!=null){
        					   optionMap.put("isTrue", true);
        				     }else{
        					   optionMap.put("isTrue", false);
        				     }
      				  }
      				 
      			   }else{
      				   optionMap.put("AnswerContent", "错误");
      				   if(type!=null){
      					 if(!question.getIsTrue()){
        					   optionMap.put("isTrue", true);
        				     }else{
        					   optionMap.put("isTrue", false);
        				     }
      				   }
      				 
      			   }
      			  
      			   optionMap.put("Order", j);
      			   optionMap.put("oldOrder", j);
      			   optionMap.put("AnswerType", question.getType());
      			   optionList.add(optionMap);
      		   }
      		  
      	   }
      	   
      	   
      	   questionMap.put("QuestionAnswer", optionList);
      	   subQuestionList.add(questionMap);
      	   i++;
         }
		return subQuestionList;
	}
	
	/**
	 * Method name: SubmitStudentAnswer <BR>
	 * Description: 提交考试 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("SubmitStudentAnswer")
	@ResponseBody
	public Object SubmitStudentAnswer(HttpServletRequest request,Integer Euid,String answer,Integer IsAutoMarking,Integer userRemainingTime,Integer uid,Integer examType){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			
			   List<Map<String,Object>> answerList= JsonUtil.getList4Json(answer,Map.class);
			   if(answerList==null||answerList.size()<=0){
				    resultMap.put("result", 0);
					resultMap.put("msg", "FAIL");
			   }else{
				   if(examType==null){
					   examType=1;  
				   }
				   ExamUserAnswerVo vo = new ExamUserAnswerVo ();
				    vo.setExamType(examType);
					vo.setUserId(uid);
					vo.setExamAssignId(Euid);
					vo.setIsAutoMarking(IsAutoMarking);
					vo.setRemainingTime(userRemainingTime);
				    vo=getExamUserAnswerVo4Answer(answerList,vo);
					Integer totalScore = myExamManageService.calculateScore(vo,uid);
					myExamManageService.modifyExamAssignInfo(Euid);
				   resultMap.put("result", 1);
				   resultMap.put("msg", "SUCCESS");
				   resultMap.put("score", totalScore);
			   }
				
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	/**
	 * Method name: SubmitExerciseStudentAnswer <BR>
	 * Description: 提交学员练习答案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("SubmitExerciseStudentAnswer")
	@ResponseBody
	public Object SubmitExerciseStudentAnswer(HttpServletRequest request,Integer uid,Integer Euid,String answer,Integer userRemainingTime,Integer examRecordId,Integer TotalScore){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			   List<Map<String,Object>> answerList= JsonUtil.getList4Json(answer,Map.class);
			   if(answerList==null||answerList.size()<=0){
				    resultMap.put("result", 0);
					resultMap.put("msg", "FAIL");
			   }else{
				    ExamUserAnswerVo vo = new ExamUserAnswerVo ();
				    vo.setExamType(5);
					vo.setUserId(uid);
					vo.setExamAssignId(Euid);
					vo.setRemainingTime(userRemainingTime);
					vo.setExamRecordId(examRecordId);
					vo.setTotalScore(TotalScore);
				    vo=getExamUserAnswerVo4Answer(answerList,vo);
					Integer totalScore = myExamManageService.calculateScore(vo,uid);
				   resultMap.put("result", 1);
				   resultMap.put("msg", "SUCCESS");
				   resultMap.put("UserScore", totalScore);
				  
				   CourseExamRecordBean examRecord = courseRecordService.getExamRecordById(examRecordId);
				   myExamManageService.modifyExamAssignInfo(Euid);
				   resultMap.put("IsPass", examRecord.getMaxScore()>=examRecord.getTotalScore()*examRecord.getPassScorePercent()/100?1:0);
				   resultMap.put("OverplusTestTimes",examRecord.getPassTimes()-examRecord.getTestTimes());
				 
			   }
				
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class, e);
		}
		return resultMap;
	}
	
	/**
	 * 从答案list中获得ExamUserAnswerVo
	 * @param answerList
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private ExamUserAnswerVo getExamUserAnswerVo4Answer(List<Map<String,Object>> answerList,ExamUserAnswerVo vo) throws Exception{
		String[] questionId = new String[answerList.size()];//问题ID
		String[] parentId= new String[answerList.size()];;//问题父ID
		String[] questionType= new String[answerList.size()];;//题型：1主观题，2单选题，3多选题，4判断题，5填空题，6组合题
		String[] userAnswer= new String[answerList.size()];;//用户答案
		String[] orderNum= new String[answerList.size()];;//题目序号
		String[] subOrderNum= new String[answerList.size()];;//子题目序号
		String[] score= new String[answerList.size()];;//题目分值
		String[] subQuestionId= new String[answerList.size()];;//子问题ID
	    int i=0;
		for(Map<String,Object> answerMap:answerList){
	    	questionId[i]=answerMap.get("id").toString();
	    	parentId[i]=answerMap.get("parentId").toString();
	    	questionType[i]=answerMap.get("questionType").toString();
	    	userAnswer[i]=answerMap.get("content").toString();
	    	if(answerMap.get("orderNum")!=null){
	    		orderNum[i]=answerMap.get("orderNum").toString();
	    	}
	    	if(answerMap.get("score")!=null){
	    		score[i]=answerMap.get("score").toString();
	    	}
	    	if(answerMap.get("subOrderNum")!=null){
	    		subOrderNum[i]=answerMap.get("subOrderNum").toString();
	    	}
	    	
	    
	    	subQuestionId[i]=answerMap.get("subQuestionId").toString();
	    	i++;
	    }
		
		vo.setQuestionId(questionId);
		vo.setParentId(parentId);
		vo.setQuestionType(questionType);
		vo.setUserAnswer(userAnswer);
		vo.setOrderNum(orderNum);
		vo.setSubOrderNum(subOrderNum);
		vo.setSubQuestionId(subQuestionId);
		vo.setScore(score);
		return vo;
	}
	
	/**
	 * Method name: GetMyExamScore <BR>
	 * Description: 查询考试成绩 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("GetMyExamScore")
	@ResponseBody
	public Object GetMyExamScore(HttpServletRequest request,String name,Integer uid,Integer pageIndex,Integer pageSize){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		try {
			ExamQueryConditionBean bean =new ExamQueryConditionBean ();
			int from = (pageIndex-1) * pageSize;
			bean.setFromNum(from);
			bean.setPageSize(pageSize);
			bean.setUser_id(uid);
			
			bean.setTitle(name);
			List<ExamRecorderVo> exams = myExamManageService.getMyExamRecorderVo(bean);
		    	for(ExamRecorderVo exam:exams){
		    		Map<String,Object> examMap =new HashMap<String,Object>();	
		    		examMap.put("ExaminationTitle", exam.getTitle());
		    		examMap.put("PassScore", exam.getPassScore());
		    		examMap.put("UserScore", exam.getScore());
		    		examMap.put("IsPass", exam.getIsPassed());
		    		examMap.put("EuId", exam.getExamAssignId());
		    		examMap.put("TestTimes", exam.getAllowTimes());
		    		examMap.put("UserTestTimes", exam.getTestTimes());
		    		examMap.put("ExamBegintime", exam.getBeginTime());
		    		examMap.put("ExamEndTime", exam.getEndTime());
		    		examMap.put("ExamLength",exam.getDuration()*60);
		    		
		    		
		    		resultList.add(examMap);
		    	}
		    	resultMap.put("dataList", resultList);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	
	
	/**
	 * Method name: GetExamResultDetail <BR>
	 * Description: 学员答卷详情 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("GetExamResultDetail")
	@ResponseBody
	public Object GetExamResultDetail(HttpServletRequest request,Integer Euid,Integer uid){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			ExamAssignBean assignBean = myExamManageService.getExamAssignById(Euid);
			
			ExamQueryConditionBean bean =new ExamQueryConditionBean ();
			bean.setFromNum(0);
			bean.setPageSize(5);
			bean.setUser_id(uid);
			bean.setId(assignBean.getRelationId());
			ExamScheduleVo exam = myExamManageService.getExamScheduleVo(assignBean.getRelationId());
			List<ExamRecorderVo> exams = myExamManageService.getMyExamRecorderVo(bean);
			
			ExamRecorderVo exam2=null;
			if(exams!=null&&exams.size()==1){
				 exam2=exams.get(0);
			}
			
		    	Map<String,Object> examMap =new HashMap<String,Object>();	
		    		examMap.put("ExamTitle", exam.getTitle());
		    		examMap.put("EuId", exam.getId());
		    		examMap.put("ExamRuleList", exam.getNotice());
		    		examMap.put("ExamBegintime", exam.getBeginTime());
		    		examMap.put("ExamEndTime", exam.getEndTime());
		    		examMap.put("TestTimes", exam.getAllowTimes());
		    		
		    		examMap.put("PassScore", exam.getPassScore());
		    		examMap.put("TotalScore",exam.getTotalScore());
		    		examMap.put("ExamScore",exam.getTotalScore());
		    		examMap.put("userScore",0);
		    		examMap.put("ExamLength",exam.getDuration()*60);
		    		
		    		
		    		if(exam2!=null){
		    			examMap.put("GoInTestTimes", exam2.getAllowTimes());
		    			Integer IsGoIn = 1;
		    			//系统没有这个字段(考试剩余时间),改为剩余次数
			    		examMap.put("RemainingTime",exam2.getAllowTimes()-exam2.getTestTimes());
			    		if(exam2.getExamState().equals(ExamState.PROCESSING) ||exam2.getExamState().equals(ExamState.BEFORE_START)){
			    			if(exam2.getAllowTimes()>exam2.getTestTimes()){
			    				ExamAssignBean examAssignBean = myExamManageService.getTestTimes(Euid);
			    				if(examAssignBean.getIsAttend()!=null&&examAssignBean.getIsAttend()==1){
			    					IsGoIn= 0;
			    				}
			    				
			    			}
			       		}
			    		examMap.put("IsGoIn", IsGoIn);
			    		examMap.put("IsGoIn", IsGoIn);
			    		examMap.put("Pass", exam2.getIsPassed());
			    		examMap.put("userScore",exam2.getScore());
		    		}else{
		    			examMap.put("GoInTestTimes", exam.getAllowTimes());
		    			examMap.put("IsGoIn", 1);
			    		examMap.put("Pass", 0);
			    		//系统没有这个字段(考试剩余时间),改为剩余次数
			    		examMap.put("RemainingTime",exam.getAllowTimes());
		    		}
		    	

					List<ExamUserAnswerBean> list= null;
					List<ExamOrganizingRuleBean> ruleList =null;
					//判断是否做了试卷
					boolean flag = myExamManageService.getCountByAssignId(Euid);
					ExamQueryConditionBean ebean = myExamManageService.getInfoByAssignId(Euid);
					int organizing_mode = ebean.getOrganizing_mode();
					if(organizing_mode == 1){//标准组卷
						if(flag){
							list = myExamManageService.getExamAssignDetail(Euid);
							examMap.put("organizingMode", 1);
							examMap.put("isFinish", flag);
						}else{
							ExamAssignDetailVo vo = myExamManageService.getNoAnswerExamDetail(Euid);
							examMap.put("organizingMode", 1);
							examMap.put("isFinish", flag);
							List<Map<String,Object>> questionList = getPaperDetail(vo.getExamScheduleVo().getPaperBean());
							 resultMap.put("exampaper", questionList);
						}
					}else{//随机组卷
						if(flag){
							list = myExamManageService.getExamAssignDetail(Euid);
							examMap.put("organizingMode", 2);
							examMap.put("isFinish", flag);
						}else{
							//return myExamManageService.getRuleList(ebean.getPaper_id());
							 ruleList = examOrganizingRuleDaoMapper.getRuleList(ebean.getPaper_id());
								
								examMap.put("organizingMode", 2);
								examMap.put("isFinish", flag);
						}
					}
					
			if(list!=null&&list.size()>0){
				
		          List<Map<String,Object>> questionList = new ArrayList<Map<String,Object>>();
		     
		            int i= 1;
		            //循环试题list
		           for(ExamUserAnswerBean paperQuestion:list){
		        	   Map<String,Object> questionMap =new HashMap<String,Object>();
		        	   QuestionBean question = paperQuestion.getQuestion();
		        	   Integer disType = question.getDisplayType();
		        	   Integer type = question.getType();
		        	   questionMap.put("QType", disType);
		        	   //试题类型为多媒体时
		        	   if(disType == 5){
		        		   questionMap.put("FillBlankCount", question.getOptions().size());
		        		  
		        	   }
		        	   if(disType==6){
		        		   List<Map<String,Object>> subQuestionList =convertQuestionHaveAnswer(paperQuestion.getSubAnswers());
		        		   questionMap.put("subQuestionList", subQuestionList);
		        	   }

		        	   questionMap.put("Score", paperQuestion.getScore());
		        	   questionMap.put("getScore", paperQuestion.getGetScore());
		        	   questionMap.put("QuestionContent",question.getContent() );
		        	   questionMap.put("QuestionID", question.getId());
		        	   questionMap.put("QuestionOrder", i);
		        	   questionMap.put("analysis", question.getAnalysis());
		        	   List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
		        	   //试题类型为非判断题
		        	   if(question.getType()!=4){
		        		 
		        		   //循环试题选项list
		        		   for(QuestionOptionBean option:question.getOptions()){
		        			   Map<String,Object> optionMap =new HashMap<String,Object>();
		        			   optionMap.put("OptionId", option.getId());
		        			   optionMap.put("AnswerContent", option.getContent());
		        			   optionMap.put("Order", option.getOrderNum());
		        			   optionMap.put("oldOrder", option.getOrderNum());
		        			   optionMap.put("isPic", option.getType());
		        			   optionMap.put("isTrue", option.getIsAnswer());
		        			   //试题类型为多媒体时
		        			   if(disType ==7){
		        				   questionMap.put("multimediaType", question.getMultimediaType());//多媒体类型1：图片 2：音频 3：视频
		        				   questionMap.put("multimediaUrl", question.getMultimediaUrl());
		        				   switch(question.getType()){
		        				   case 1:
		        					   optionMap.put("AnswerType", 0);
		          					   questionMap.put("QAnswerType", 0);
		        				        break;
		        				   case 2:
		        					   optionMap.put("AnswerType", 1);

		          					   questionMap.put("QAnswerType", 1);
		        				        break;
		        				   case 3:
		        					   optionMap.put("AnswerType", 2);
		          					   questionMap.put("QAnswerType", 2);
		        				        break;
		        				   default:
		        					   break;
		        				   }
		        				   
		        			   }else{
		        				   optionMap.put("AnswerType", question.getType());
		        			   }
		        			   optionList.add(optionMap);
			        	   } 
		        	   }else{
		        		   //试题为判断题时
		        		   for(int j=1;j>=0;j--){
		        			   Map<String,Object> optionMap =new HashMap<String,Object>();
		        			   optionMap.put("OptionId", j);
		        			   if(j==1){
		        				   optionMap.put("AnswerContent", "正确");
		        				   if(question.getIsTrue()){
		        					   optionMap.put("isTrue", true);
		        				   }else{
		        					   optionMap.put("isTrue", false);
		        				   }
		        			   }else{
		        				   optionMap.put("AnswerContent", "错误");
		        				   if(!question.getIsTrue()){
		        					   optionMap.put("isTrue", true);
		        				   }else{
		        					   optionMap.put("isTrue", false);
		        				   }
		        			   }
		        			   optionMap.put("Order", j);
		        			   optionMap.put("oldOrder", j);
		        			   optionMap.put("AnswerType", question.getType());
		        			   optionList.add(optionMap);
		        		   }
		        		  
		        	   }
		        	   String answer = paperQuestion.getAnswer();
		        	   if(answer!=null&&answer.length()>0&&!answer.equals("-")){
		        		   String [] answerArr=null;
		        		   switch (type){
		        		   case 1:
		        			   answerArr= new String[1];
		        			   answerArr[0]=answer; 
		        			   questionMap.put("UserAnswer",answerArr);
		        			   break;
		        		   case 2:
		        			   answerArr = answer.split(",");
		        			   questionMap.put("UserAnswer",answerArr);
		        			   break;
		        		   case 3:
		        			   answerArr = answer.split(",");
		        			   questionMap.put("UserAnswer",answerArr);
		        			   break;
		        		   case 4:
		        			   answerArr = new String[1];
		        			   answerArr[0]=answer; 
		        			   questionMap.put("UserAnswer",answerArr);
		        			   break;
		        		   case 5:
		        			   answerArr = answer.split("##\\*\\*##",-1);
		        			   questionMap.put("UserAnswer",answerArr);
		        			   break;
		        		   case 6:
		        			  
		        			   questionMap.put("UserAnswer",null);
		        			   break;
		        		   default:
		        			   questionMap.put("UserAnswer",null);
		        			   break;
		        		   }
		        	   }else{
		        		    questionMap.put("UserAnswer",null);
		        	   }
		        	   
		        	   questionMap.put("QuestionAnswer", optionList);
		        	   questionList.add(questionMap);
		        	   i++;
		           }
		           resultMap.put("exampaper", questionList);
					}
		    		resultMap.put("exambase", examMap);
		    		if(ruleList!=null&&ruleList.size()>0){
		    			 resultMap.put("exampaper", ruleList);
		    		}
		    		resultMap.put("result", 1);
		    	    resultMap.put("msg", "SUCCESS");
			return resultMap;
		} catch (Exception e) {
			resultMap.put("result", 0);
    	    resultMap.put("msg", "ERROR");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	/**
	 * 有答案的子试题转换
	 * @param subAnswers
	 * @return
	 */
	private List<Map<String,Object>> convertQuestionHaveAnswer(List<ExamUserAnswerBean> subAnswers){
		List<Map<String,Object>> subQuestionList = new ArrayList<Map<String,Object>>();
		
		 int i= 1;
         //循环试题list
        for(ExamUserAnswerBean paperQuestion:subAnswers){
     	   Map<String,Object> questionMap =new HashMap<String,Object>();
     	   QuestionBean question = paperQuestion.getQuestion();
     	   Integer disType = question.getDisplayType();
     	   Integer type = question.getType();
     	   questionMap.put("QType", disType);
     	   questionMap.put("Score", paperQuestion.getScore());
     	   questionMap.put("getScore", paperQuestion.getGetScore());
     	   questionMap.put("QuestionContent",question.getContent() );
     	   questionMap.put("QuestionID", question.getId());
     	   questionMap.put("QuestionOrder", i);
     	   List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
     	   //试题类型为非判断题
     	   if(question.getType()!=4){
     		 
     		   //循环试题选项list
     		   for(QuestionOptionBean option:question.getOptions()){
     			   Map<String,Object> optionMap =new HashMap<String,Object>();
     			   optionMap.put("OptionId", option.getId());
     			   optionMap.put("AnswerContent", option.getContent());
     			   optionMap.put("Order", option.getOrderNum());
     			   optionMap.put("oldOrder", option.getOrderNum());
     			   optionMap.put("isPic", option.getType());
     			   optionMap.put("isTrue", option.getIsAnswer());
     			   //试题类型为多媒体时
     			   if(disType ==7){
     				   questionMap.put("multimediaType", question.getMultimediaType());//多媒体类型1：图片 2：音频 3：视频
     				  questionMap.put("multimediaUrl", question.getMultimediaUrl());
     				   switch(question.getType()){
     				   case 1:
     					   optionMap.put("AnswerType", 0);
       					   questionMap.put("QAnswerType", 0);
     				        break;
     				   case 2:
     					   optionMap.put("AnswerType", 1);

       					   questionMap.put("QAnswerType", 1);
     				        break;
     				   case 3:
     					   optionMap.put("AnswerType", 2);
       					   questionMap.put("QAnswerType", 2);
     				        break;
     				   default:
     					   break;
     				   }
     				   
     			   }else{
     				   optionMap.put("AnswerType", question.getType());
     			   }
     			   optionList.add(optionMap);
	        	   } 
     	   }else{
     		   //试题为判断题时
     		   for(int j=1;j>=0;j--){
     			   Map<String,Object> optionMap =new HashMap<String,Object>();
     			   optionMap.put("OptionId", j);
     			   if(j==1){
     				   optionMap.put("AnswerContent", "正确");
     				  if(question.getIsTrue()){
   					   optionMap.put("isTrue", true);
   				      }else{
   					   optionMap.put("isTrue", false);
   				      }
     			   }else{
     				   optionMap.put("AnswerContent", "错误");
     				  if(!question.getIsTrue()){
   					   optionMap.put("isTrue", true);
   				        }else{
   					   optionMap.put("isTrue", false);
   				        }
     			   }
     			   optionMap.put("Order", j);
     			   optionMap.put("oldOrder", j);
     			   optionMap.put("AnswerType", question.getType());
     			   optionList.add(optionMap);
     		   }
     		  
     	   }
     	   String answer = paperQuestion.getAnswer();
     	   if(answer!=null&&answer.length()>0&&!answer.equals("-")){
     		   String [] answerArr=null;
     		   switch (type){
     		   case 1:
     			   answerArr= new String[1];
     			   answerArr[0]=answer; 
     			   questionMap.put("UserAnswer",answerArr);
     			   break;
     		   case 2:
     			   answerArr = answer.split(",");
     			   questionMap.put("UserAnswer",answerArr);
     			   break;
     		   case 3:
     			   answerArr = answer.split(",");
     			   questionMap.put("UserAnswer",answerArr);
     			   break;
     		  case 4:
   			   answerArr = new String[1];
   			   answerArr[0]=answer; 
   			   questionMap.put("UserAnswer",answerArr);
   			   break;
   		      case 5:
   			   answerArr = answer.split("##\\*\\*##",-1);
   			   questionMap.put("UserAnswer",answerArr);
   			   break;
     		   default:
     			   questionMap.put("UserAnswer",null);
     			   break;
     		   }
     	   }else{
     		    questionMap.put("UserAnswer",null);
     	   }
     	   
     	   questionMap.put("QuestionAnswer", optionList);
     	   subQuestionList.add(questionMap);
     	   i++;        }
		return subQuestionList;
	}
	
	
	/**
	 * 根据试卷bean将所有试题重新封装
	 * @param paper
	 * @return
	 */
	private List<Map<String,Object>> getPaperDetail(PaperBean paper ){

	      List<Map<String,Object>> questionList = new ArrayList<Map<String,Object>>();
	     
	      List<PaperQuestionBean> questionBeanList = paper.getPaperQuestionList();
	            int i= 1;
	            //循环试题list
	           for(PaperQuestionBean paperQuestion:questionBeanList){
	        	   Map<String,Object> questionMap =new HashMap<String,Object>();
	        	   QuestionBean question = paperQuestion.getQuestionBean();
	        	   Integer disType = question.getDisplayType();
	        	   //试题类型为多媒体时
	        	   questionMap.put("QType", disType);
	        	   //试题为填空题
	        	   if(disType == 5){
	        		   questionMap.put("FillBlankCount", question.getOptions().size());
	        		
	        	   }
	        	   
	        	   //组合题
	        	   if(disType==6){
	        		   List<Map<String,Object>> subQuestionList =   convertSubQuestion(question,1);
	        		   questionMap.put("subQuestionList", subQuestionList) ;
	        	   }

	        	   questionMap.put("Score", paperQuestion.getScore());
	        	   questionMap.put("QuestionContent",question.getContent() );
	        	   questionMap.put("QuestionID", question.getId());
	        	   questionMap.put("QuestionOrder", i);
	        	   List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
	        	   //试题类型为非判断题
	        	   if(question.getType()!=4){
	        		   //循环试题选项list
	        		   for(QuestionOptionBean option:question.getOptions()){
	        			   Map<String,Object> optionMap =new HashMap<String,Object>();
	        			   optionMap.put("OptionId", option.getId());
	        			   optionMap.put("AnswerContent", option.getContent());
	        			   optionMap.put("Order", option.getOrderNum());
	        			   optionMap.put("oldOrder", option.getOrderNum());
	        			   optionMap.put("isPic", option.getType());//是否是图片
	        				 optionMap.put("isTrue", option.getIsAnswer());
	        				questionMap.put("analysis", question.getAnalysis());
	        			   //试题类型为多媒体时
	        			   if(disType ==7){
	        				   questionMap.put("multimediaType", question.getMultimediaType());//多媒体类型1：图片 2：音频 3：视频
	        				   questionMap.put("multimediaUrl", question.getMultimediaUrl());
	        				   switch(question.getType()){
	        				   case 1:
	        					   optionMap.put("AnswerType", 0);
	        					   questionMap.put("QAnswerType", 0);
	        				        break;
	        				   case 2:
	        					   optionMap.put("AnswerType", 1);
	        					   questionMap.put("QAnswerType", 1);
	        				        break;
	        				   case 3:
	        					   optionMap.put("AnswerType", 2);
	        					   questionMap.put("QAnswerType", 2);
	        				        break;
	        				   default:
	        					   break;
	        				   }
	        				   
	        			   }else{
	        				   optionMap.put("AnswerType", question.getType());
	        			   }
	        			   optionList.add(optionMap);
		        	   } 
	        	   }else{
	        		   //试题为判断题时
	        		   for(int j=0;j<2;j++){
	        			   Map<String,Object> optionMap =new HashMap<String,Object>();
	        			   optionMap.put("OptionId", j);
	        			   if(j==0){
	        				   optionMap.put("AnswerContent", "正确");
	        				   if(question.getIsTrue()){
	        					   optionMap.put("isTrue", true);
	        				   }else{
	        					   optionMap.put("isTrue", false);
	        				   }
	        			   }else{
	        				   optionMap.put("AnswerContent", "错误");
	        				   if(!question.getIsTrue()){
	        					   optionMap.put("isTrue", true);
	        				   }else{
	        					   optionMap.put("isTrue", false);
	        				   }
	        			   }
	        			   
	        			   optionMap.put("Order", j);
	        			   optionMap.put("oldOrder", j);
	        			   optionMap.put("AnswerType", question.getType());
	        			   optionList.add(optionMap);
	        		   }
	        		  
	        	   }
	        	   questionMap.put("QuestionAnswer", optionList);
	        	   questionList.add(questionMap);
	        	   i++;
	           }
	           return questionList;
	}
	
	
	
	
	/**
	 * Method name: UpdateUserExamTests <BR>
	 * Description: 修改考生的进入次数、剩余时间 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("UpdateUserExamTests")
	@ResponseBody
	public Object UpdateUserExamTests(HttpServletRequest request,Integer Euid,Integer testTimes,Integer userRemainingTime){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
		
			myExamManageService.modifyExamAssignInfo(Euid);
			
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	/**考试 结束*/
	
	/**个人中心 开始*/
	
	
	/**
	 * Method name: GetUserDetail <BR>
	 * Description: 获取用户信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetUserDetail")
	@ResponseBody
	public Object GetUserDetail(HttpServletRequest request,Integer uid){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			    ManageUserBean user = manageUserService.findUserByIdCondition(uid.toString());
			    Map<String,Object> userMap =new HashMap<String,Object>();
			    userMap.put("Photo", user.getHeadPhoto());
			    userMap.put("Phone", user.getMobile());
			    userMap.put("Realname",user.getName());
			    userMap.put("Birthday", user.getBirthDay());
			    userMap.put("Email", user.getEmail());
			    //userMap.put("TenantName", user.getCompanyName());
                 userMap.put("TenantName", user.getSubCompanyName());
			    userMap.put("Sex", user.getSex());
			    userMap.put("DeptName", user.getDeptName());
			    userMap.put("PostName", user.getPostName());
			    userMap.put("Username", user.getUserName());
			    
			
			    //系统没有的
			    //userMap.put("Signature", null);
			    //userMap.put("UserCode", null);
			    resultMap.put("data", userMap);
		} catch (Exception e) {
			resultMap.put("data", null);
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: UploadUserPhoto <BR>
	 * Description: 上传更新用户头像 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param imgfileStr  Base64 编码的图片字符串,注意将+号换成%2B
	 * @return  Object<BR>
	 */
	@RequestMapping("UploadUserPhoto")
	@ResponseBody
	public Object UploadUserPhoto(HttpServletRequest request,Integer uid,String imgfileStr){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
		       // Base64 编码的图片转换，保存,注意图片字符串+号问题%2B
			String filePath =GenerateImage(imgfileStr);
			if(filePath==null){
				resultMap.put("result", 0);
				resultMap.put("msg", "图片转换失败");
				return resultMap;
			}
			ManageUserBean user = new ManageUserBean();
			user.setId(uid.toString());
			user.setHeadPhoto(filePath);
			manageUserService.update(user);
			request.getSession().setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(user.getId()));
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}

	
	/**
	 * Method name: SaveUserDetail <BR>
	 * Description: 更新用户信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("SaveUserDetail")
	@ResponseBody
	public Object SaveUserDetail(HttpServletRequest request,Integer uid,String birthday,String email,String signature,String phone){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			ManageUserBean user = manageUserService.findUserByIdCondition(uid.toString());
			user.setBirthDay(birthday);
			user.setEmail(email);
			user.setMobile(phone);
			manageUserService.update(user);
			//重置session
			
			request.getSession().setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(user.getId()));
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: UploadUserPassword <BR>
	 * Description: 修改密码 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("UploadUserPassword")
	@ResponseBody
	public Object UploadUserPassword(HttpServletRequest request,Integer uid,String oldPwd,String pwd){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			ManageUserBean userBean = manageUserService.findUserByIdCondition(uid.toString());
			if(!userBean.getPassword().equalsIgnoreCase(CommonUtil.getMD5(oldPwd))){
				resultMap.put("result", 2);
				resultMap.put("msg", "原密码错误");
				return resultMap;
			}
			
			manageUserService.updateManageUserPassword(userBean.getId(), userBean.getUserName(), CommonUtil.getMD5(pwd));
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	
	/**
	 * Method name: GetRecMessageList <BR>
	 * Description: 获取收件箱列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetRecMessageList")
	@ResponseBody
	public Object GetRecMessageList(HttpServletRequest request,Integer tid,Integer uid,String title,Integer  pageIndex,Integer pageSize){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		  List<Map<String,Object>> resultList =new ArrayList<Map<String,Object>>();
		try {
			ManageNoticeVo vo =new ManageNoticeVo();
			vo.setTitle(title);
			vo.setFromNum((pageIndex-1)*pageSize);
			vo.setPage(pageIndex);
			vo.setPageSize(pageSize);
			vo.setRecUserId(uid);
			vo.setRecDeleteFlag(1);
			
			List<ManageNoticeBean> rows = manageNoticeService.select(vo);
			for(ManageNoticeBean row : rows){
				Map<String,Object> noticeMap =new HashMap<String,Object>();
				noticeMap.put("MessageId", row.getId());
				noticeMap.put("Title", row.getTitle());
				if(row.getSendUserId()!=null){
					ManageUserBean user =manageUserService.findUserById(row.getSendUserId().toString());
					if(user!=null){
						noticeMap.put("SendUserRealName", user.getName());
					}else{
						noticeMap.put("SendUserRealName", "该用户已被删除");
					}
					
				}else{
					noticeMap.put("SendUserRealName", "系统");
				}
				Integer isRead = row.getIsRead();
				if(isRead!=null&&isRead==2){
					isRead=0;
				}
				noticeMap.put("RecStatus",isRead );
				noticeMap.put("CreateTime", row.getSendTime());
				resultList.add(noticeMap);
			}
				resultMap.put("dataList", resultList);
			
	
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}

	
	/**
	 * Method name: GetSendMessageList <BR>
	 * Description: 获取发件箱列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSendMessageList")
	@ResponseBody
	public Object GetSendMessageList(HttpServletRequest request,Integer tid,Integer uid,String title,Integer  pageIndex,Integer pageSize){
		  Map<String,Object> resultMap =new HashMap<String,Object>();
		  List<Map<String,Object>> resultList =new ArrayList<Map<String,Object>>();
		try {
			ManageNoticeVo vo =new ManageNoticeVo();
			vo.setTitle(title);
			vo.setFromNum((pageIndex-1)*pageSize);
			vo.setPage(pageIndex);
			vo.setPageSize(pageSize);
			vo.setSendDeleteFlag(1);
			vo.setSendUserId(uid);
			vo.setIsSystem(2);
			
			List<ManageNoticeBean> rows = manageNoticeService.select(vo);
			for(ManageNoticeBean row : rows){
				Map<String,Object> noticeMap =new HashMap<String,Object>();
				noticeMap.put("MessageId", row.getId());
				noticeMap.put("Title", row.getTitle());
				if(row.getRecUserId()!=null){
					ManageUserBean user =manageUserService.findUserById(row.getRecUserId().toString());
					if(user!=null){
                        noticeMap.put("RecUserRealName", user.getName());
					}else{
						noticeMap.put("SendUserRealName", "该用户已被删除");
					}
					
				}else{
					noticeMap.put("RecUserRealName", "");
				}
				noticeMap.put("CreateTime", row.getSendTime());
				resultList.add(noticeMap);
			}
				resultMap.put("dataList", resultList);
			
	
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: SendMessage <BR>
	 * Description: 发件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping("SendMessage")
	@ResponseBody
	public Object SendMessage(HttpServletRequest request,Integer tid,Integer uid,String content,String title,String recUserIds,String imgContent){
		//TODO  imgContent 图片（json 数组，组成 string） 现有系统没有
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			String [] recUserIdArr = null;
			if(recUserIds!=null&&recUserIds.length()>0){
				recUserIdArr=recUserIds.split(",");
			}
			
			for(String receiverId : recUserIdArr){
				manageNoticeService.insertNotice(new ManageNoticeBean(title, content, uid, 
						Integer.parseInt(receiverId), 2));
			}
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: DeleteMessages <BR>
	 * Description: 删除站内信 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param messageIds
	 * @param type 类型  0：发件箱；1：收件箱
	 * @return  Object<BR>
	 */
	@RequestMapping("DeleteMessages")
	@ResponseBody
	public Object DeleteMessages(HttpServletRequest request,String messageIds,Integer type){
		//  type 类型  0：发件箱；1：收件箱   
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			String [] messageIdArr = null;
			if(messageIds!=null&&messageIds.length()>0){
				messageIdArr=messageIds.split(",");
			}
			ManageNoticeVo vo = new ManageNoticeVo();
			for(String messageId : messageIdArr){
				vo.setId(Integer.parseInt(messageId));
				
				if(type==1){
					vo.setRecDeleteFlag(2);
				}
				if(type==0){
					vo.setSendDeleteFlag(2);
				}
				manageNoticeService.deleteNotice(vo);
			}
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
			
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	/**
	 * Method name: GetMessageInfo <BR>
	 * Description: 查看站内信 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param messageId
	 * @param type 类型  0：发件箱；1：收件箱 
	 * @return  Object<BR>
	 */
	@RequestMapping("GetMessageInfo")
	@ResponseBody
	public Object GetMessageInfo(HttpServletRequest request,Integer messageId,Integer type){
	
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			
			ManageNoticeVo vo = new ManageNoticeVo();
				vo.setId(messageId);
				manageNoticeService.updateNoticeReadStatus(messageId);
				ManageNoticeBean bean=manageNoticeService.selectById(messageId);
				
				resultMap.put("result", 1);
				resultMap.put("msg", "SUCCESS");
				resultMap.put("RecUserRealName", manageUserService.findUserById(bean.getRecUserId().toString()).getName());
				resultMap.put("SendUserRealName", manageUserService.findUserById(bean.getSendUserId().toString()).getName());
				resultMap.put("MessageContent", bean.getContent());
				resultMap.put("CreateTime", bean.getSendTime());
				resultMap.put("Title", bean.getTitle());
			
	
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	/**
	 * Method name: GetUserList <BR>
	 * Description: 查看学员账号 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetUserList")
	@ResponseBody
	public Object GetUserList(HttpServletRequest request,Integer uid,Integer tid,String name,String userName,Integer pageIndex,Integer pageSize){
		
		if(pageIndex==null){
			pageIndex=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
		try {
		ManageUserBean userBean =manageUserService.findUserById(uid.toString());
	
	
	
			Map<String, Object> param = new HashMap<String, Object>();
			String[] deptIds = request.getParameterValues("deptIds[]");
			if(deptIds != null && deptIds.length > 0){
				List<Integer> deptList = new ArrayList<Integer>();
				for(String deptId : deptIds){
					deptList.add(Integer.parseInt(deptId));
				}
				param.put("deptId",deptList);
			}
			param.put("companyId", userBean.getCompanyId());
			if(userBean.getCompanyId() - userBean.getSubCompanyId() != 0){
				param.put("subCompanyId", userBean.getSubCompanyId());
			}
			if(name != null && !"".equals(name)){
				param.put("name", name);
			}
			if(userName != null && !"".equals(userName)){
				param.put("userName", userName);
			}
		
			List<ManageUserBean> list = manageUserService.findUserByListCondition(param);
			int size = list.size();
			resultMap.put("total", size);
			int fromIndex = (pageIndex-1)*pageSize;
			int toIndex = fromIndex + pageSize;
			if(fromIndex >= list.size()){
				fromIndex = list.size();
			}
			if(toIndex >= list.size()){
				toIndex = list.size();
			}
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			if(list.size() > 0){
				list = list.subList(fromIndex, toIndex);
				for(ManageUserBean user : list){
					Map<String,Object> userMap = new HashMap<String,Object>();
					userMap.put("id", user.getId());
					userMap.put("name", user.getName());
					userMap.put("userName", user.getUserName());
					userMap.put("deptName", user.getDeptName());
					userMap.put("deptId", user.getDeptId());
					userMap.put("postId", user.getPostId());
					userMap.put("postName", user.getPostName());
					userMap.put("tid", user.getCompanyId());
					userMap.put("companyName", user.getCompanyName());
					userMap.put("sex", user.getSex());
					userList.add(userMap);
				}
				resultMap.put("list", userList);
			}else{
				resultMap.put("list", userList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
		}
		return resultMap;
	}
	
	/**个人中心 结束*/
	
	
	/** 练习 开始 */
	
	/**
	 * Method name: GetExerciseQuestionCategory <BR>
	 * Description: 获取我的练习试题分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "/GetExerciseQuestionCategory")
	@ResponseBody
	public Object GetExerciseQuestionCategory(HttpServletRequest request,Integer uid,Integer tid){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			ManageUserBean userBean =manageUserService.findUserById(uid.toString());
			QuestionCategorySearchOptionVo searchOption =  new QuestionCategorySearchOptionVo();
			searchOption.setCompanyId(tid);
			searchOption.setSubCompanyId(userBean.getSubCompanyId());
			searchOption.setUserId(uid.toString());
			List<QuestionCategoryBean> list = questionCategoryService.getExerciseQuestionCategory(searchOption);
			//查出所有子分类
			/**
			if(list!=null&&list.size()>0){
				for(QuestionCategoryBean firstBean:list){
					List<QuestionCategoryBean> subList =firstBean.getSubCategoryList();
					if(subList!=null&&subList.size()>0){
						for(QuestionCategoryBean childBean:subList){
							getSubList(childBean,tid,userBean.getSubCompanyId());
						}
					}
				}
			}
			*/
			//List<QuestionCategoryBean> list= questionCategoryService.getQuestionCategoryList(searchOption);
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			resultMap.put("data", list);
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
	}
	
	
	public void getSubList(QuestionCategoryBean bean,Integer tid,Integer subCompanyId){
		QuestionCategorySearchOptionVo searchOption =  new QuestionCategorySearchOptionVo();
		searchOption.setCompanyId(tid);
		searchOption.setId(bean.getId());
		searchOption.setSubCompanyId(subCompanyId);
		QuestionCategoryBean copyBean = questionCategoryService.getQuestionCategoryById(searchOption);
		
		if(copyBean!=null&&copyBean.getSubCategoryList()!=null&&copyBean.getSubCategoryList().size()>0){
			bean.setSubCategoryList(copyBean.getSubCategoryList());
			for(QuestionCategoryBean childBean:bean.getSubCategoryList()){
				getSubList(childBean,tid,subCompanyId);
			}
		}
	}
	
	/**
	 * Method name: GetQuestionCategoryById <BR>
	 * Description: 根据ID获取试题分类信息 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value = "/GetQuestionCategoryById")
	@ResponseBody
	public Object GetQuestionCategoryById(HttpServletRequest request,Integer categoryId,Integer uid,Integer tid){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			//ManageUserBean userBean =manageUserService.findUserById(uid.toString());
			QuestionCategorySearchOptionVo searchOption =  new QuestionCategorySearchOptionVo();
			searchOption.setCompanyId(tid);
			//searchOption.setSubCompanyId(userBean.getSubCompanyId());
			searchOption.setId(categoryId);
			QuestionCategoryBean bean = questionCategoryService.getQuestionCategoryById(searchOption);
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			resultMap.put("data", bean);
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	/**
	 * Method name: GetExerciseInfo <BR>
	 * Description: 获取练习信息 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="GetExerciseInfo")
	@ResponseBody
	public Object GetExerciseInfo(HttpServletRequest request,Integer uid,Integer tid,Integer categoryId,Integer parentCategoryId){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			QuestionSearchOptionVo searchOption =new QuestionSearchOptionVo ();
			searchOption.setCategoryId(categoryId);
			searchOption.setParentCategoryId(parentCategoryId);
			ManageUserBean userBean =manageUserService.findUserById(uid.toString());
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
			//获取试题库中的试题数量
			String count = examQuestionService.getExerciseQuestionCount(searchOption);
			String [] countArr = count.split(",");
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			resultMap.put("categoryId", categoryId);
			resultMap.put("questionIds", idStr);
			resultMap.put("questionCount", countArr[0]);
			resultMap.put("finishCount", countArr[1]);
			resultMap.put("errorCount", countArr[2]);
		}catch(Exception e){
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
		}
		return resultMap;
		
	}
	
	/**
	 * Method name: GetExerciseQuestionIdList <BR>
	 * Description: 获取试题库的题目ID <BR>
	 * Remark: <BR>
	 * @param request
	 * @param searchOption
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetExerciseQuestionIdList")
	@ResponseBody
	public Object GetExerciseQuestionIdList(HttpServletRequest request,Integer uid,Integer tid, QuestionSearchOptionVo searchOption){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{
			ManageUserBean userBean = manageUserService.findUserById(uid.toString());
			searchOption.setCompanyId(userBean.getCompanyId());
			searchOption.setSubCompanyId(userBean.getSubCompanyId());
			searchOption.setUserId(Integer.parseInt(userBean.getId()));
			String idStr = examQuestionService.getExerciseQuestionIdList(searchOption);
			
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			resultMap.put("questionIds", idStr);
		}catch(Exception e){
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
		}
		
		return resultMap;
	}
	
	
	/**
	 * Method name: GetExerciseQuestion <BR>
	 * Description: 生成练习题 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping(value="GetExerciseQuestion")
	@ResponseBody
	public Object GetExerciseQuestion(HttpServletRequest request,Integer uid,Integer tid,Integer questionId){
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try{ 
			Map<String,Object> questionMap =new HashMap<String,Object>();
			QuestionBean question =	examQuestionService.getOneExerciseQuestion(questionId);
			Integer disType = question.getDisplayType();
     	   //试题类型为多媒体时
     	   questionMap.put("QType", disType);
     	   //试题为填空题
     	   if(disType == 5){
     		   questionMap.put("FillBlankCount", question.getOptions().size());
     		
     	   }
     	   
     	   //组合题
     	   if(disType==6){
     		   List<Map<String,Object>> subQuestionList =   convertSubQuestion(question,1);
     		   questionMap.put("subQuestionList", subQuestionList) ;
     	   }

     	   questionMap.put("QuestionContent",question.getContent() );
     	   questionMap.put("QuestionID", question.getId());
     	   questionMap.put("analysis", question.getAnalysis());
     	   List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
     	   //试题类型为非判断题
     	   if(question.getType()!=4){
     		   //循环试题选项list
     		   for(QuestionOptionBean option:question.getOptions()){
     			   Map<String,Object> optionMap =new HashMap<String,Object>();
     			   optionMap.put("OptionId", option.getId());
     			   optionMap.put("AnswerContent", option.getContent());
     			   optionMap.put("Order", option.getOrderNum());
     			   optionMap.put("oldOrder", option.getOrderNum());
     			   optionMap.put("isPic", option.getType());//是否是图片
     			   optionMap.put("isTrue", option.getIsAnswer());
     			   //试题类型为多媒体时
     			   if(disType ==7){
     				   questionMap.put("multimediaType", question.getMultimediaType());//多媒体类型1：图片 2：音频 3：视频
     				   questionMap.put("multimediaUrl", question.getMultimediaUrl());
     				   switch(question.getType()){
     				   case 1:
     					   optionMap.put("AnswerType", 0);
     					   questionMap.put("QAnswerType", 0);
     				        break;
     				   case 2:
     					   optionMap.put("AnswerType", 1);
     					   questionMap.put("QAnswerType", 1);
     				        break;
     				   case 3:
     					   optionMap.put("AnswerType", 2);
     					   questionMap.put("QAnswerType", 2);
     				        break;
     				   default:
     					   break;
     				   }
     				   
     			   }else{
     				   optionMap.put("AnswerType", question.getType());
     			   }
     			   optionList.add(optionMap);
	        	   } 
     	   }else{
     		   //试题为判断题时
     		   for(int j=0;j<2;j++){
     			   Map<String,Object> optionMap =new HashMap<String,Object>();
     			   optionMap.put("OptionId", j);
     			   if(j==0){
     				   optionMap.put("AnswerContent", "正确");
     				  if(question.getIsTrue()){
   					   optionMap.put("isTrue", true);
   				   }else{
   					   optionMap.put("isTrue", false);
   				   }
     			   }else{
     				   optionMap.put("AnswerContent", "错误");
     				  if(!question.getIsTrue()){
   					   optionMap.put("isTrue", true);
   				     }else{
   					   optionMap.put("isTrue", false);
   				     }
     			   }
     			  
     			   optionMap.put("Order", j);
     			   optionMap.put("oldOrder", j);
     			   optionMap.put("AnswerType", question.getType());
     			   optionList.add(optionMap);
     		   }
     		  
     	   }
     	   questionMap.put("QuestionAnswer", optionList);
			
			
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			resultMap.put("questionBean", questionMap);
			
		}catch(Exception e){
			log.warn(MobileAction.class,e);
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
		}
		return resultMap;
	}
	
	/**
	 * Method name: submitExerciseQuestionAnswer <BR>
	 * Description: 提交答案 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  Object<BR>
	 */
	@RequestMapping(value="submitExerciseQuestionAnswer")
	@ResponseBody
	public Object submitExerciseQuestionAnswer(HttpServletRequest request,Integer uid,String answer ,Integer categoryId,String isAddWrong) {
		
		JSONObject obj = new JSONObject();
		boolean flag;
		Map<String,Object> resultMap =new HashMap<String,Object>();
		try {
			
			  List<Map<String,Object>> answerList= JsonUtil.getList4Json(answer,Map.class);
			   if(answerList==null||answerList.size()<=0){
				    resultMap.put("result", 0);
					resultMap.put("msg", "FAIL");
			   }else{
				   ExamUserAnswerVo vo = new ExamUserAnswerVo ();
					vo.setUserId(uid);
				    vo=getExamUserAnswerVo4Answer(answerList,vo);
			        vo.setCategoryId(categoryId);
                    vo.setUserId(uid);
			
			flag = examQuestionService.isAnswerCorrect(vo,uid,isAddWrong);
			obj.elementOpt("flag", flag);
			resultMap.put("result", 1);
			resultMap.put("msg", "SUCCESS");
			resultMap.put("flag", flag);}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 0);
			resultMap.put("msg", "FAIL");
			
		}
		return resultMap;
	}
	
	/** 练习 结束 */
	
	/**模拟考试 开始*/
	/**
	 * Method name: GetSearchSimulateExamList <BR>
	 * Description: 获取模拟考试列表，每次获取 16 条数据 <BR>
	 * Remark: <BR>
	 * @return  Object<BR>
	 */
	@RequestMapping("GetSearchSimulateExamList")
	@ResponseBody
	public Object GetSearchSimulateExamList(HttpServletRequest request,String name,Integer uid,Integer pageIndex,Integer pageSize){
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(pageSize==null){
			pageSize=16;
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		pageIndex = (pageIndex-1) * pageSize;
		try {
		
			   getSearchSimulateExamList( name, uid, pageIndex, pageSize,resultList);
			   resultMap.put("dataList", resultList);
			   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String dateString  = f.format(Calendar.getInstance().getTime());
			   resultMap.put("serverTime", dateString);
			return resultMap;
		} catch (Exception e) {
			log.warn(MobileAction.class,e);
		}
		return resultMap;
		
	}
	
	private void  getSearchSimulateExamList(String name,Integer uid,Integer pageIndex,Integer pageSize,List<Map<String,Object>> resultList) throws Exception{
		ExamQueryConditionBean bean =new ExamQueryConditionBean ();
		
		bean.setFromNum(pageIndex);
		bean.setPageSize(pageSize);
		bean.setUser_id(uid);

		bean.setTitle(name);
		List<ExamRecorderVo> exams = myExamManageService.getExamSimulate(bean);
    	for(ExamRecorderVo exam:exams){
    		Map<String,Object> examMap =new HashMap<String,Object>();	
    		examMap.put("ExamTitle", exam.getTitle());
    		examMap.put("EuId", exam.getExamAssignId());
    	
    		examMap.put("ExamBegintime", exam.getBeginTime());
    		examMap.put("ExamEndTime", exam.getEndTime());
    		examMap.put("TestTimes", exam.getAllowTimes());
    		examMap.put("GoInTestTimes", exam.getTestTimes());
    		examMap.put("TotalScore",exam.getTotalScore());
    		examMap.put("Score",exam.getScore());
    		examMap.put("ExamLength",exam.getDuration()*60);
    		examMap.put("isAutoMarking", exam.getIsAutoMarking());
    	
    		examMap.put("ExamState",exam.getExamState());
    		resultList.add(examMap);
    	}
	}
	
	/**模拟考试 结束*/

   /** 学习计划 开始 **/

    /**
     * Method name: GetLearnPlanList <BR>
     * Description: 获取学习计划列表，每次获取 16 条数据 <BR>
     * Remark: <BR>
     * @return  Object<BR>
     */
    @RequestMapping("GetLearnPlanList")
    @ResponseBody
    public Object GetLearnPlanList(HttpServletRequest request,String name,Integer uid,Integer pageIndex,Integer pageSize,Integer type){

        Map<String,Object> resultMap =new HashMap<String,Object>();
        if(pageSize==null){
            pageSize=16;
        }
        if(pageIndex==null){
            pageIndex=1;
        }
        pageIndex = (pageIndex-1) * pageSize;
        try {
            List<LearnPlanVo> learnPlans = studentLearnPlanService.getLearnPlansByConditions(
                    uid,type,name,pageIndex,pageSize);
           Integer  count = studentLearnPlanService.getLearnPlanCount(uid,type,name);
            resultMap.put("dataList", learnPlans);
            resultMap.put("recordCount",count);
            resultMap.put("result", 1);
            return resultMap;
        } catch (Exception e) {
            log.warn(MobileAction.class,e);
            resultMap.put("result", 0);
            resultMap.put("msg", "ERROR");
        }
        return resultMap;

    }

    /**
     * Method name: GetLearnPlanStageList <BR>
     * Description: 获取学习计划阶段列表，每次获取 16 条数据 <BR>
     * Remark: <BR>
     * @return  Object<BR>
     */
    @RequestMapping("GetLearnPlanStageList")
    @ResponseBody
    public Object GetLearnPlanStageList(HttpServletRequest request,Integer planId,Integer uid){

        Map<String,Object> resultMap =new HashMap<String,Object>();

        try {
            List<LearnPlanStageVo> planStages = studentLearnPlanService.getLearnPlanStages(planId, uid); 
            boolean isPass=true;
            for(LearnPlanStageVo vo:planStages){
                    if(vo.getProcess()!=100){
                    isPass=false;
                }
            }
            if(isPass){
                studentLearnPlanService.updateLearnPlanStatus(planId,uid);
            }
            resultMap.put("dataList", planStages);
            resultMap.put("result", 1);
            resultMap.put("status",isPass?1:0);
            return resultMap;
        } catch (Exception e) {
            log.warn(MobileAction.class,e);
            resultMap.put("result", 0);
            resultMap.put("msg", "ERROR");
        }
        return resultMap;

    }

    /**
     * Method name: GetLearnPlanStageCourses <BR>
     * Description: 获取学习计划阶段中课程，每次获取 16 条数据 <BR>
     * Remark: <BR>
     * @return  Object<BR>
     */
    @RequestMapping("GetLearnPlanStageCourses")
    @ResponseBody
    public Object GetLearnPlanStageCourses(HttpServletRequest request,Integer stageId,Integer uid){

        Map<String,Object> resultMap =new HashMap<String,Object>();

        try {
            List<LearnPlanStageCourseVo> stageCourses = studentLearnPlanService.getLearnPlanStageCourses(stageId,uid);
            resultMap.put("dataList", stageCourses);
            resultMap.put("result", 1);
            return resultMap;
        } catch (Exception e) {
            log.warn(MobileAction.class,e);
            resultMap.put("result", 0);
            resultMap.put("msg", "ERROR");
        }
        return resultMap;

    }

    /**
     * Method name: updateLearnPlanStatus <BR>
     * Description: 更新学习计划状态 <BR>
     * Remark: <BR>
     * @param request
     * @param planId
     * @param uid
     * @return  Object<BR>
     */
    @RequestMapping("updateLearnPlanStatus")
    @ResponseBody
    public Object updateLearnPlanStatus(HttpServletRequest request,Integer planId,Integer uid){
        Map<String,Object> resultMap =new HashMap<String,Object>();
        try {
            studentLearnPlanService.updateLearnPlanStatus(planId,uid);
            resultMap.put("result", 1);
            resultMap.put("msg", "SUCCESS");
        } catch (DataBaseException e) {
            resultMap.put("result", 0);
            resultMap.put("msg", "ERROR");
            log.warn(MobileAction.class, e);
        }
        return resultMap;
    }




    /** 学习计划 结束 **/

    /**
     * Method name: VersionUpdate <BR>
     * Description: 版本更新 <BR>
     * Remark: <BR>
     * @param request
     * @return  Object<BR>
     */
    @RequestMapping("VersionUpdate")
    @ResponseBody
    public Object VersionUpdate(HttpServletRequest request,Integer Uid,Integer VerType){
        Map<String,Object> resultMap =new HashMap<String,Object>();

        try {
            log.info("开始版本更新");

            //TODO 版本更新
           MobileAppVersionBean bean = mobileAppVersionManageService.getNewVersion();
            if(bean==null){
                resultMap.put("result", 0);
                resultMap.put("msg", "当前没有发布的版本");
                return resultMap;
            }
            resultMap.put("VerDesc", bean.getDescr());
            resultMap.put("VerCode", bean.getCode());
            resultMap.put("VerName", bean.getName());
            resultMap.put("Path", bean.getPath());
            resultMap.put("PublishTime",bean.getPublicTime());
            resultMap.put("ForceUpdate", bean.getIsForce());
            resultMap.put("result", 1);
            resultMap.put("msg", "查询成功");
        } catch (Exception e) {
            resultMap.put("result", 0);
            resultMap.put("msg", "查询失败");
            log.warn(MobileAction.class,e);
        }

        return resultMap;
    }


    /** 培训考试 start */
    /**
     * Method name: GetTrainExamList <BR>
     * Description: 已报名培训的考试列表 <BR>
     * Remark: <BR>
     * @param request
     * @param vo （TrainArrangeVo）
     * @return  Object<BR>
     */
    @RequestMapping(value="GetTrainExamList")
    @ResponseBody
    public Object GetTrainExamList(HttpServletRequest request, Integer uid,Integer tid, TrainArrangeVo vo){
        Map<String,Object> resultMap =new HashMap<String,Object>();
        try{
            ManageUserBean user = manageUserService.findUserById(uid.toString());
            if(user==null){
                resultMap.put("result", 0);
                resultMap.put("msg", "用户不存在");
                return resultMap;
            }
            vo.setCompanyId(user.getCompanyId().toString());
            //vo.setSubCompanyId(user.getSubCompanyId().toString());
            vo.setDeptId(user.getDeptId().toString());
            vo.setUserId(user.getId());
            vo.setIsHaveTest(1);
            int total = trainService.selectJoinedTrainArrangeCount(vo);
            List<TrainArrangeBean> rows = trainService.selectJoinedTrainArrangeList(vo);
            List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
            for(TrainArrangeBean arrange : rows){

                Map<String,Object> arrangeTestMap =new HashMap<String,Object>();
                arrangeTestMap.put("arrangeId",arrange.getId());
                arrangeTestMap.put("arrangeName",arrange.getName());
                arrangeTestMap.put("examId",arrange.getAfterClassExam());
                arrangeTestMap.put("examName",arrange.getAfterClassExamName());
                arrangeTestMap.put("duration",arrange.getAceDuration());
                arrangeTestMap.put("allowTimes",arrange.getAceAllowTimes());
                arrangeTestMap.put("isPass",arrange.getIsPass());
                arrangeTestMap.put("score",arrange.getScore());
                arrangeTestMap.put("beginTime",arrange.getBeginTime());
                arrangeTestMap.put("endTime",arrange.getEndTime());
                dataList.add(arrangeTestMap);
            }
            resultMap.put("result",1);
            resultMap.put("dataList",dataList);
            resultMap.put("recordCount",total);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString  = f.format(Calendar.getInstance().getTime());
            resultMap.put("serverTime", dateString);
            return resultMap;
        }catch(Exception e){
            resultMap.put("result", 0);
            resultMap.put("msg", "查询失败");
            log.warn(MobileAction.class,e);
            return resultMap;
        }

    }

    /**
     * Method name: GotoMatchExam <BR>
     * Description: 进入【通过考试】页面，进行考试 <BR>
     * Remark: <BR>
     * @param request
     * @param examId 考试id
     * @param arrangeId 培训id
     * @return  String<BR>
     */
    @RequestMapping(value="GotoMatchExam")
    @ResponseBody
    public Object GotoMatchExam(HttpServletRequest request,Integer examId,Integer arrangeId,Integer uid){
        Map<String,Object> resultMap =new HashMap<String,Object>();
        try {
            ManageUserBean user = manageUserService.findUserById(uid.toString());
            if(user==null){
                resultMap.put("result", 0);
                resultMap.put("msg", "用户不存在");
                return resultMap;
            }
            Map<String, Integer> r = new HashMap<String, Integer>();
            TrainArrangeBean arrange = trainService.selectTrainArrangeById(arrangeId);
            r = trainService.saveExamAndAssignInfo(examId, arrange.getAceDuration(), arrange.getAceAllowTimes(), arrange.getPassPercent(), Integer.parseInt(user.getId()), user.getCompanyId(), user.getSubCompanyId());
            if(r!=null){
                resultMap.put("result", 1);
                resultMap.put("Euid",r.get("assignId")) ;
            }else {
                resultMap.put("result", 0);
                resultMap.put("msg", "FAIL");
            }
        }catch (Exception e) {
            resultMap.put("result", 0);
            resultMap.put("msg", "ERROR");
            log.warn(MobileAction.class,e);
        }
        return resultMap;
    }


    /**
     * Method name: InsertTrainScore <BR>
     * Description: InsertTrainScore <BR>
     * Remark: <BR>
     * @param request
     * @param Euid 考试记录id
     * @param arrangeId 培训id
     * @return
     * @throws java.text.ParseException  String<BR>
     */
    @RequestMapping(value="InsertTrainScore")
    @ResponseBody
    public Object InsertTrainScore(HttpServletRequest request,Integer Euid, Integer arrangeId,Integer uid){
        Map<String,Object> resultMap =new HashMap<String,Object>();
        try {
            ExamAssignBean examAssign = myExamManageService.getExamAssignById(Euid);
            ManageUserBean user = manageUserService.findUserById(uid.toString());
            if(user==null){
                resultMap.put("result", 0);
                resultMap.put("msg", "用户不存在");
                return resultMap;
            }
            TrainArrangeUserBean arrangeUser = new TrainArrangeUserBean();
            arrangeUser.setUserId(Integer.parseInt(user.getId()));
            arrangeUser.setTrainArrangeId(arrangeId);
            arrangeUser.setScore(examAssign.getScore());
            if(examAssign.getIsPassed()){
                arrangeUser.setIsPass(1);
            }else{
                arrangeUser.setIsPass(2);
            }

            trainService.updateTrainArrangeUserScore(arrangeUser);
            resultMap.put("result", 1);
            resultMap.put("msg", "SUCCESS");
        } catch (Exception e) {
            resultMap.put("result", 0);
            resultMap.put("msg", "ERROR");
            log.warn(MobileAction.class,e);
        }
        return resultMap;
    }


    /** 培训考试 end */

/**
	 * 图片转换
	 * @param imgStr
	 * @return
	 */
	private  String GenerateImage(String imgStr)
    {//对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
              log.info(imgStr);
            Pattern pattern = Pattern.compile("^data:image/[a-z]*;base64,");
    		Matcher matcher = pattern.matcher(imgStr);	
    		
    		while(matcher.find()){
    			String str = matcher.group();
    			String ext = str.substring(11,(str.length()-8));
    			log.info(ext);
            //Base64解码
    			int index = imgStr.indexOf(str);
  				imgStr =imgStr.substring(index+str.length());
  				log.info(imgStr);
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i< b.length;++i)
            {
                if(b[i]< 0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
			String nameSuff=".jpg";
			if(ext!=null&&ext.length()>0){
				nameSuff="."+ext;
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
		
            //生成jpeg图片
            
            OutputStream out = new FileOutputStream(filePath);    
            out.write(b);
            out.flush();
            out.close();
            return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+fileName;
    		}
    		return null;
    		} 
        catch (Exception e) 
        {
        	log.warn(MobileAction.class,e);
            return null;
        }
    }
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    }


    /**
     *
     * @author JFTT)zhangbocheng
     * Method name: uploadImg <BR>
     * Description: 图片上传 <BR>
     * Remark: <BR>
     * @param request
     * @param imgStr
     * @return  String<BR>
     */

    @RequestMapping("UploadImg")
    @ResponseBody
    public String addContestUploadImg(HttpServletRequest request,String imgStr){
        log.debug("MobileAction执行UploadImg方法");
        try {
            if(imgStr!=null&&imgStr.length()>0){
              return   GenerateImage(imgStr);
            }
            else{
                return Constant.AJAX_FAIL;
            }

        } catch (Exception e) {
            log.debug(MobileAction.class,e);
            return Constant.AJAX_FAIL;
        }
    }


    /**
     * Method name: 下载apk <BR>
     * Description: 下载 <BR>
     * Remark: <BR>
     * @param request
     * @param response
     * @param
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public String download(HttpServletRequest request,HttpServletResponse response) {
        log.info("下载apk");
        try {
            HttpSession session = request.getSession();

            MobileAppVersionBean bean = mobileAppVersionManageService.getNewVersion();
            String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");
            String filePath =       bean.getPath();
            String name =bean.getName();
            System.out.println(filePath);
            System.out.println(name);
            String UPLOAD_VIRTUAL_PATH = PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH");
            filePath =filePath.replaceFirst(UPLOAD_VIRTUAL_PATH,saveUrl);
            System.out.println(filePath);
            File file = new File(filePath);
            // 取得文件名。
            // String filename = file.getName();
            // 取得文件的后缀名。
            //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
// 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename*=UTF-8''%s",
                    URLEncoder.encode(name + ".apk", "UTF-8"));
            System.out.println(headerValue);
            response.setHeader(headerKey, headerValue);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            //response.setContentType("application/vnd.android.package-archive");
            response.setHeader("Content-Type","application/vnd.android.package-archive");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return null;
        } catch (Exception e) {
            log.warn("Failed to download apk.", e);
        }
        return null;
    }



}


