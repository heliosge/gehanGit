package com.jftt.wifi.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jftt.wifi.bean.ManagePageBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManagePageService;
import com.jftt.wifi.util.SpringContextUtil;

/**
 * 拦截器
 */
public class SystemInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger log = Logger.getLogger(SystemInterceptor.class);
	
	private static ManagePageService managePageService;
	static{
		managePageService = (ManagePageService)SpringContextUtil.getBean("managePageService");
	}
	/*private static BaseMetaDaoMapper baseMetaDaoMapper;
	static{
		baseMetaDaoMapper = (BaseMetaDaoMapper)SpringContextUtil.getBean("baseMetaDaoMapper");
	}*/
	@Override  
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{ 
	    
	    String uri = request.getRequestURI();
	    log.debug("Pre-handle url="+uri);
	    
		if (uri.startsWith(request.getContextPath()+"/login")||uri.startsWith(request.getContextPath()+"/Mobile/")){
			
			log.debug(uri + " 不进行拦截");
			
	    }else if(checkThisUrl(uri, request.getSession())){
	    	
	    	//session失效，到登录页面
	    	HttpSession session = request.getSession();
	    	String userId = (String) session.getAttribute(Constant.SESSION_USERNAME_STRING);
	    	
	    	if(session.getAttribute(Constant.SESSION_TIME) != null){

	    		session.removeAttribute(Constant.SESSION_TIME);
	    		log.debug("删除 " + Constant.SESSION_TIME + " " + session.getId());
	    	}
	    	
	    	if(session.getAttribute(Constant.SESSION_AGAIN) != null){
	    		//重复登陆
	    		session.removeAttribute(Constant.SESSION_USERNAME_STRING);
	    		session.removeAttribute(Constant.SESSION_USERID_LONG);
	    		
	    		//AJAX方法访问
    			if (handler instanceof HandlerMethod) {  
    	            if (((HandlerMethod) handler).getMethod().isAnnotationPresent(ResponseBody.class)) {// 有权限控制的就要检查 
    	            	
    	            	response.sendRedirect(request.getContextPath()+"/ajaxSessionAgain.txt");
    	            }else{
    	            	//非ajax请求,页面跳转请求
    	            	response.sendRedirect(request.getContextPath()+"/ajaxSessionAgain.jsp");
    	            }
    		    }else{
    		    	//非ajax请求,页面跳转请求
    		    	response.sendRedirect(request.getContextPath()+"/ajaxSessionAgain.jsp");
    		    }

	    	}else if(userId == null || userId.equals("")){
    			
    			//AJAX方法访问
    			if (handler instanceof HandlerMethod) {  
    	            if (((HandlerMethod) handler).getMethod().isAnnotationPresent(ResponseBody.class)) {// 有权限控制的就要检查 
    	            	
    	    			response.sendRedirect(request.getContextPath()+"/ajaxSessionOut.txt");
    	            }else{
    	            	//非ajax请求,页面跳转请求
    	    			response.sendRedirect(request.getContextPath()+"/login/loginPage.action");
    	            }
    		    }else{
    		    	//非ajax请求,页面跳转请求
        			response.sendRedirect(request.getContextPath()+"/login/loginPage.action");
    		    }
    			
    			log.debug("首页："+request.getContextPath()+"/login/loginPage.action");
    			log.debug("SESSION 失效 返回登陆页面");
    			
		    	return false;
	    	}else{
	    		ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
	    		MDC.put("userId", user.getId());
	    		MDC.put("userName", user.getName());
	    		MDC.put("userIp", request.getRemoteAddr());
	    	}
	    }else{
	    	log.debug("首页："+request.getContextPath()+"/login/loginout.action");
	    	response.sendRedirect(request.getContextPath()+"/login/loginout.action");
	    }
		
	    return true;
	}  
	  
	private boolean checkThisUrl(String uri, HttpSession session) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("url", uri);
		param.put("userId", session.getAttribute(Constant.SESSION_USERID_LONG));
		//判断该请求是否是页面请求
		ManagePageBean page = managePageService.getManagePageByMap(param);
		if(page != null){
			//判断该用户是否该页面权限
			param.put("pageId", page.getId());
			if(managePageService.checkManagePageByUser(param) != null){
				return true;
			}
		}else{
			return true;
		}
		return false;
	}

	private HandlerMethod HandlerMethod(Object handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override  
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{  
		//logger.warn("Post-handle");
	    //System.out.println("Post-handle");  
	}  
	  
	@Override  
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){  
		//logger.warn("After completion handle");
	    //System.out.println("After completion handle");  
	}
}
