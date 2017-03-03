package com.jftt.wifi.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import com.jftt.wifi.bean.vo.PostApplyVo;
import com.jftt.wifi.service.ManagementLearningMapService;
import com.jftt.wifi.util.SpringContextUtil;

public class CheckPromotionFail implements Job{

	@Resource(name="managementLearningMapService")
	private static ManagementLearningMapService managementLearningMapService;
	static{
		managementLearningMapService = (ManagementLearningMapService)SpringContextUtil.getBean("managementLearningMapService");
	}
	
	@Override
	public void execute(JobExecutionContext arg0)  throws JobExecutionException {
		//检查晋升失败人员
		checkPromoteFail();
	}
	
	private void checkPromoteFail() {
		try {
		//Integer id = managementLearningMapService.getMaxStateId();
		List<PostApplyVo> list;
	
			list =managementLearningMapService.getUnfinishRecord(null,null);
			for(PostApplyVo record : list){
				if(record.getReStartApproveStatus()==1){
					if(record.getStageBeginTime()==null){
						continue;
					}
					Date endDate = record.getStageBeginTime();
					int dayBetween = daysBetween(endDate,new Date());
					if(dayBetween>record.getPeriod()){
						managementLearningMapService.setPromotionFail(record.getId());
					}
				}else if(record.getReStartApproveStatus()==4){
					if(record.getReStartTime()==null){
						continue;
					}
					Date endDate = record.getStageBeginTime();
					int dayBetween = daysBetween(endDate,new Date());
					if(dayBetween>record.getPeriod()){
						managementLearningMapService.setPromotionFail(record.getId());
					}
				}else{
					continue;
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
