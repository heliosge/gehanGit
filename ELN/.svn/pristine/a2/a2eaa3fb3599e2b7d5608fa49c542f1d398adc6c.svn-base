/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: DataTransferTask.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   |   2014年10月19日      | JFTT)caiyicheng    | original version
 */
package com.jftt.wifi.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageNoticeService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.SendMessageUtil;
import com.jftt.wifi.util.SpringContextUtil;
import com.jftt.wifi.util.TimeUtil;



public class SendCompanyExpireNotice implements Job{

	@Resource(name="manageCompanyService")
	private static ManageCompanyService manageCompanyService;
	
	@Resource(name="manageNoticeService")
	private static ManageNoticeService manageNoticeService;
	
	@Resource(name="manageUserService")
	private static ManageUserService manageUserService;
	static{
		manageCompanyService = (ManageCompanyService)SpringContextUtil.getBean("manageCompanyService");
		manageNoticeService = (ManageNoticeService)SpringContextUtil.getBean("manageNoticeService");
		manageUserService = (ManageUserService)SpringContextUtil.getBean("manageUserService");
	}
	
	@Override
	public void execute(JobExecutionContext arg0)  throws JobExecutionException {
		//发送企业过期前30站内信
		sendCompanyExpireNotice();
		//发送用户过期前30天站内信‘
		sendUserExpireMailAndMessage();
	}
	
	private void sendUserExpireMailAndMessage() {
		List<ManageCompanyBean> list;
		try {
			list = manageCompanyService.selectCompanyList(new ManageCompanyVo()); 
			for(ManageCompanyBean company : list){
				if(company.getEndTime() == null || "".equals(company.getEndTime())){
					continue;
				}
				if("1".equals(company.getStatus().toString()) && company.getId() > 1){
					Date endDate = TimeUtil.parseString2Date(company.getEndTime());
				    int dayBetween = daysBetween(new Date(),endDate);
					if( dayBetween <= 30 && dayBetween > 0){
						//Map<String, Object> map = new HashMap<String, Object>();
						//map.put("companyId", company.getId());
						//List<ManageUserBean> userList = manageUserService.findUserByCondition(map);
						String content = "尊敬的用户：您的账号还有"+dayBetween+"天即将到期，为保证您的使用不受影响，请及时联系我们进行续费，感谢您的长期支持和信赖。<br/>安培在线，互联网+安全培训服务专家，为您提供随时随地的安全培训。<br/>Powered by 安培在线<br/>咨询热线：4008-430-400<br/>";
						String messageContent = "尊敬的用户：您的账号还有"+dayBetween+"天即将到期，为保证您的使用不受影响，请及时联系我们进行续费，感谢您的长期支持和信赖。";
						//ManageUserBean user = manageUserService.findUserById(String.valueOf(company.getInitUserId()));
						int count = 1;
				    	if(company.getEmail() != null && !"".equals(company.getEmail())){
				    		while(!SendMessageUtil.sendEmail(company.getEmail(), "安培在线系统会员账号到期提醒", content) && count++ < 3);
				    	}
				    	if(company.getPhoneNum() != null && !"".equals(company.getPhoneNum())){
				    		while(!SendMessageUtil.sendSMS(messageContent, company.getPhoneNum()) && count++ < 3);
				    	}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendCompanyExpireNotice(){
		List<ManageCompanyBean> list;
		try {
			list = manageCompanyService.selectCompanyList(new ManageCompanyVo()); 
			for(ManageCompanyBean company : list){
				if(company.getEndTime() == null || "".equals(company.getEndTime())){
					continue;
				}
			    Date endDate = TimeUtil.parseString2Date(company.getEndTime());
				if(daysBetween(new Date(),endDate) == 30){
					ManageNoticeBean notice = new ManageNoticeBean("平台使用期限到期提醒", "尊敬的XX企业大学<br/>：您的账户使用期限还剩30天，请提前做好续费准备，避免对您的使用产生影响。<br/>"+
							"企业账户信息如下：购买账户数："+company.getAccountCount()+"人;<br/>使用期限： "+company.getStartTime()+"至"+company.getEndTime(), null, company.getInitUserId(), 1);
					manageNoticeService.insertNotice(notice);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	       return Integer.parseInt(String.valueOf(between_days));           
	    }


}
