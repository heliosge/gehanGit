package com.jftt.wifi.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.BaseMetaDaoMapper;
import com.jftt.wifi.interceptor.CountLineListener;
import com.jftt.wifi.service.InformationManageService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.LoginHisService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageIndustryCategoryService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.SendMessageUtil;
import com.jftt.wifi.util.TimeUtil;


/**
 * class name:LoginAction <BR>
 * class description: 处理登录页面 <BR>
 * Remark: <BR>
 * @version 1.00 2013-12-13
 * @author JFTT)WANGJIAN
 */
@Controller
@RequestMapping(value="login")
public class LoginAction {
	
	private static Logger log = Logger.getLogger(LoginAction.class);
	
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource(name="manageIndustryCategoryService")
	private ManageIndustryCategoryService manageIndustryCategoryService;
	
	@Resource(name="informationManageService")
	private InformationManageService informationManageService;
	
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	@Resource(name="baseMetaDaoMapper")
	private BaseMetaDaoMapper baseMetaDaoMapper;
	
	@Resource(name="loginHisService")
	private LoginHisService loginHisService;
	
	/**
	 * 注册页面
	 */
	@RequestMapping(value="toSignUpPage")
	public String toSignUpPage(HttpServletRequest request, HttpServletResponse response, String name){
		return "login/signUp";
	}
	
	/**
	 * Method name: findPassword <BR>
	 * Description: 找回密码 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="findPassword")
	public String findPassword(){
		return "login/findPasswd";
	}
	
	/**
	 * Method name: reSetPassWd <BR>
	 * Description: 重置密码 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param keyType
	 * @param key
	 * @param userName
	 * @param activeCode
	 * @return  String<BR>
	 */
	@RequestMapping(value="reSetPassWd")
	@ResponseBody
	public String reSetPassWd(HttpServletRequest request, String keyType, String key, String userName, String activeCode){
		try {
			//验证邮件激活码
			int flag = manageCompanyService.checkActiveCode(key, activeCode);
			if(flag == 0){
				return "WRONG_ACTIVE_CODE";
			}else if(flag > 0){
				//验证用户名、手机的一致性
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userName", userName);
				if("1".equals(keyType)){
					param.put("mobile", key);
				}else{
					param.put("email", key);
				}
				List<ManageUserBean> beanL = manageUserService.findUserByCondition(param);
				if(beanL != null && beanL.size() > 0){
					ManageCompanyBean company = manageCompanyService.selectCompanyById(beanL.get(0).getCompanyId());
					//发送密码
					Random ra =new Random();
			    	String password = ""+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10);
			    	int count = 0;
			    	if("1".equals(keyType)){
			    		while(!SendMessageUtil.sendSMS(company.getName()+"系统密码已改为："+password, key) && count++ < 3);
			    			
			    	}else{
			    		while(!SendMessageUtil.sendEmail(key, company.getName()+"找回密码", company.getName()+"密码已改为："+password) && count++ < 3);
			    	}
			    	//修改密码
			    	beanL.get(0).setPassword(CommonUtil.getMD5(password));
			    	manageUserService.update(beanL.get(0));
				}else{
					return "NOT_MATCH";
				}
			}
			return Constant.AJAX_SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		} 
	}
	
	/**
	 * Method name: recieveActiveCode <BR>
	 * Description: 【找回密码】获取邮件激活码 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param email
	 * @return  String<BR>
	 */
	@RequestMapping(value="recieveActiveCode")
	@ResponseBody
	public String recieveActiveCode(HttpServletRequest request, String email, String phone, String userName){
		try {
			//验证用户名、手机的一致性
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userName", userName);
			if(phone != null && !"".equals(phone)){
				param.put("mobile", phone);
			}else{
				param.put("email", email);
			}
			List<ManageUserBean> beanL = manageUserService.findUserByCondition(param);
			if(beanL == null || beanL.size() == 0){
				return "NOT_MATCH";
			}
			//验证手机
			manageCompanyService.insertCompanyActiveCode(email, phone);
			return Constant.AJAX_SUCCESS;
		}catch (Exception e) {
			return Constant.AJAX_FAIL;
		} 
	}
	
	/**
	 * Method name: recieveActiveCodeOnSign <BR>
	 * Description: 【注册】获取邮件激活码 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param email
	 * @return  String<BR>
	 */
	@RequestMapping(value="recieveActiveCodeOnSign")
	@ResponseBody
	public String recieveActiveCodeOnSign(HttpServletRequest request, String email){
		try {
			manageCompanyService.insertCompanyActiveCode(email, null);
			return Constant.AJAX_SUCCESS;
		}catch (Exception e) {
			return Constant.AJAX_FAIL;
		} 
	}
	
	
	
	
	/**
	 * Method name: signUp <BR>
	 * Description: 注册公司 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param company
	 * @return  String<BR>
	 */
	@RequestMapping(value="signUp")
	@ResponseBody
	public String signUp(HttpServletRequest request, ManageCompanyBean company){
		try {
			//验证邮件激活码
			int flag = manageCompanyService.checkActiveCode(company.getEmail(), company.getActiveCode().toString());
			if(flag == 0){
				return "WRONG_ACTIVE_CODE";
			}else if(flag > 0){
				manageCompanyService.insertCompany(company);
				//发送邮件、短信
				int count = 0;
				String content = "尊敬的用户：感谢您选择安培在线，您的注册申请已提交成功，稍后我们将会和您联系，请您耐心等待。如有问题请联系我们的在线客服人员或致电400-8430-400<br/>安培在线，互联网+安全培训服务专家，为您提供随时随地的安全培训。<br/>Powered by 安培在线<br/>咨询热线：4008-430-400<br/>";
				String messageContent = "尊敬的用户：感谢您选择安培在线，您的注册申请已提交成功，稍后我们将会和您联系，请您耐心等待。如有问题请联系我们的在线客服人员或致电400-8430-400<br/>";
				while(!SendMessageUtil.sendEmail(company.getEmail(),"安培在线会员审核",content+"") && count++ < 3);
				while(!SendMessageUtil.sendSMS(messageContent, company.getPhoneNum().toString()) && count++ < 3);
			}
			return Constant.AJAX_SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		} 
	}
	
	/**
	 * Method name: selectManageCompanyList <BR>
	 * Description: 根据条件查询公司列表 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param vo
	 * @return  Object<BR>
	 */
	@RequestMapping(value="selectManageCompanyList")
	@ResponseBody
	public Object selectManageCompanyList(HttpServletRequest request, ManageCompanyVo vo){
		List<ManageCompanyBean> rows = new ArrayList<ManageCompanyBean>();
		try {
			rows = manageCompanyService.selectCompanyList(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * Method name: initIndustry <BR>
	 * Description: 获取企业分类 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="initIndustry")
	@ResponseBody
	public Object initIndustry(HttpServletRequest request, String id, String name, String parentId){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("parentId", parentId);
			return manageIndustryCategoryService.select(map);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Method name: initCity <BR>
	 * Description: 获取城市 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param provinceId
	 * @return  Object<BR>
	 */
	@RequestMapping(value="initCity")
	@ResponseBody
	public Object initCity(HttpServletRequest request, String provinceId){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", provinceId);
			return manageCompanyService.selectCity(map);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	/**
	 * Method name: loginPage <BR>
	 * Description: loginPage <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param name
	 * @return  String<BR>
	 */
	@RequestMapping(value="loginPage")
	public String loginPage(HttpServletRequest request, HttpServletResponse response, String name){
		// 设置Session
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.SESSION_USERID_LONG);
		session.removeAttribute(Constant.SESSION_USERNAME_STRING);
		session.removeAttribute(Constant.SESSION_NAME);
		session.removeAttribute(Constant.SESSION_USERBEAN);
		
		//session.invalidate();
		return "login/loginNew";
	}
	
	//登陆成功
	/**
	 * Method name: login <BR>
	 * Description: 登录的ajax <BR>
	 * Remark: <BR>
	 */
	@RequestMapping(value="tologin")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, String userName, String password){
		
		try {
			log.debug("系统登陆 参数信息 UserName: " + userName + "PassWord: " + password);
			// TODO:修改companyId
			//默认普联企业id
			Integer companyId = Constant.PULIAN_COMPANY_ID;
			//Integer companyId = null;
			ManageCompanyBean company = new ManageCompanyBean();
			//获取用户登录域名
//			String url = request.getHeader("Host");//TODO
//			System.out.println("url:"+url);
//			StringBuffer url= request.getRequestURL();
//			String domain = url.split(Constant.PULIAN_SITE)[0].replace(Constant.HTTP, "");//TODO
			//根据域名获取该用户的公司
			
			ManageUserBean userBean = manageUserService.findUserByUserName(userName.trim(), null);
			log.debug("userBean.getCompanyId:"+userBean.getCompanyId());
			ManageCompanyVo vo = new ManageCompanyVo();
			vo.setId(userBean.getCompanyId());
//			vo.setDomain(domain);//TODO
			List<ManageCompanyBean> comList = manageCompanyService.selectCompanyList(vo);
			log.debug("comList:"+"\n"+comList);
			if(comList != null && comList.size() > 0){
				company = comList.get(0);
				companyId = company.getId();
				log.debug("公司ID ："+companyId+"\n"+"冻结:"+company.getStatus()+comList.toString());
				if(companyId > Constant.PULIAN_COMPANY_ID){
					//判断公司账号过期时间
					if(new Date().getTime() > TimeUtil.parseString2Date(company.getEndTime()).getTime()){
						return "OVER_DATE";
					}
					//判断该企业是否冻结
					if(!"1".equals(company.getStatus().toString())){
						return "COMPANY_STATUS_FREEZE";
					}
					//判断该企业当前在线用户是否大于最大并发用户
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("companyId", companyId);
					int currentUser = loginHisService.selectUserCountByMap(param);
					if(currentUser >= company.getMaxConcurrent()){
						return "OVER_MAXCONCURRENT";
					}
				}
			}
			//根据域名、登陆账号获取该用户
//			ManageUserBean userBean = manageUserService.findUserByUserName(userName.trim(), companyId);
			log.debug("userBean:"+"\n"+userBean);
			if (null != userBean) {
				log.debug("\n"+"userBean.getPassword"+userBean.getPassword()+"\n"+"输入密码:"+CommonUtil.getMD5(password));
				if (CommonUtil.getMD5(password).equalsIgnoreCase(userBean.getPassword())) {
					
					if(!"1".equals(userBean.getStatus().toString())){
						return "STATUS_FREEZE";
					}
					
					// 设置Session
					HttpSession session = request.getSession();
					session.setAttribute(Constant.SESSION_USER_COMPANYID, userBean.getCompanyId());
					session.setAttribute(Constant.SESSION_USERID_LONG, userBean.getId());
					session.setAttribute(Constant.SESSION_USERNAME_STRING, userBean.getUserName());
					session.setAttribute(Constant.SESSION_NAME, userBean.getName());
					session.setAttribute(Constant.SESSION_USERBEAN, manageUserService.findUserByIdCondition(userBean.getId()));
					
					log.debug(userName+" 登陆成功");
					//积累积分
					integralManageService.handleIntegral(Integer.parseInt(userBean.getId()),7009);
					//初始化课件查询参数
					String VideoLimitMinute = baseMetaDaoMapper.getValueByKey("VideoLimitMinute");
					String TestLimitMinute = baseMetaDaoMapper.getValueByKey("TestLimitMinute");
					String ScormLimitMinute = baseMetaDaoMapper.getValueByKey("ScormLimitMinute");
					String LimitPage = baseMetaDaoMapper.getValueByKey("LimitPage");
					String PdfTimeInterval = baseMetaDaoMapper.getValueByKey("PdfTimeInterval");
					log.debug("当前视频课件预览最大分钟=>"+VideoLimitMinute);
					log.debug("测试课件预览最大分钟=>"+TestLimitMinute);
					log.debug("Scorm课件预览最大分钟=>"+ScormLimitMinute);
					log.debug("flexpaper预览最大页码=>"+LimitPage);
					log.debug("pdf课件翻页间隔时间=>"+PdfTimeInterval);
					if(VideoLimitMinute !=null && !VideoLimitMinute.isEmpty()){
						session.setAttribute("VideoLimitMinute", Integer.parseInt(VideoLimitMinute));
					}
					if(TestLimitMinute !=null && !TestLimitMinute.isEmpty()){
						session.setAttribute("TestLimitMinute", Integer.parseInt(TestLimitMinute));
					}
					if(ScormLimitMinute !=null && !ScormLimitMinute.isEmpty()){
						session.setAttribute("ScormLimitMinute", Integer.parseInt(ScormLimitMinute));
					}
					if(LimitPage !=null && !LimitPage.isEmpty()){
						session.setAttribute("LimitPage", Integer.parseInt(LimitPage));
					}
					if(PdfTimeInterval != null && !PdfTimeInterval.isEmpty()){
						session.setAttribute("PdfTimeInterval", Integer.parseInt(PdfTimeInterval));
					}
					return "OK";
					
				} else {
					
					log.debug(userName+" 密码错误");
					
					return "PASSWORD_ERROR";
				}
				
			} else {
				
				log.debug(userName+" 不存在");
				
				return "NO_EXIST";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			return "ERROR";
		} 

	}
	
	/**
	 * Method name: initBackground <BR>
	 * Description: 初始化登录背景 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  Object<BR>
	 */
	@RequestMapping(value="initBackground")
	@ResponseBody
	public Object initBackground(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//获取用户登录域名
			StringBuffer url= request.getRequestURL();
			String domain = String.valueOf(url).split(Constant.PULIAN_SITE)[0].replace(Constant.HTTP, "");
			//根据域名获取该用户的公司
			ManageCompanyVo vo = new ManageCompanyVo();
			vo.setDomain(domain);
			List<ManageCompanyBean> comList = manageCompanyService.selectCompanyList(vo);
			Integer companyId = -1;
			if(comList != null && comList.size() > 0){
				companyId = comList.get(0).getId();
				map.put("logo", comList.get(0).getLogoImage());
				map.put("copyright", informationManageService.editAboutUsInfo(1,companyId).toString());
			}else{
				companyId = Constant.PULIAN_COMPANY_ID;
				map.put("logo", "");
				map.put("copyright","");
			}
			if(companyId - Constant.PULIAN_COMPANY_ID == 0){
				map.put("isPulian", "1");
			}else{
				map.put("isPulian", "2");
			}
			if(companyId != -1){
				map.put("cover", informationManageService.editAboutUsInfo(2,companyId).toString());
				return map;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * Method name: loginout <BR>
	 * Description: 退出系统 <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="loginout")
	public String loginout(HttpServletRequest request){
		
		// 设置Session
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.SESSION_USERID_LONG);
		session.removeAttribute(Constant.SESSION_USERNAME_STRING);
		session.removeAttribute(Constant.SESSION_NAME);
		session.removeAttribute(Constant.SESSION_USERBEAN);
		
		//session.invalidate();
		
		return "login/loginNew";
	}
	
	/**
	 * 定时退出
	 */
	@RequestMapping(value="loginoutTime")
	@ResponseBody
	public void loginoutTime(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		final String sessionId = session.getId();
		
		session.removeAttribute(Constant.SESSION_TIME);
		
		Timer timer = new Timer(session.getId());
        timer.schedule(new TimerTask() {  
            public void run() {  
            	
            	HttpSession thisSession = CountLineListener.map.get(sessionId);
                
            	if(thisSession !=null && thisSession.getAttribute(Constant.SESSION_TIME) !=null && thisSession.getAttribute(Constant.SESSION_TIME).equals("over")){
            		thisSession.invalidate();
            	}
            	
            }  
        }, 5000);
        // 设定指定的时间time,此处为2000毫秒  
        session.setAttribute(Constant.SESSION_TIME, "over");
	}

}
