package com.jftt.wifi.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jftt.wifi.dao.MallOrderDaoMapper;
import com.jftt.wifi.util.SpringContextUtil;

public class OrderInvalidCheck implements Job {

	private static MallOrderDaoMapper mallOrderDaoMapper;
	static{
		mallOrderDaoMapper = (MallOrderDaoMapper)SpringContextUtil.getBean("mallOrderDaoMapper");
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			checkeOrdelInvalidTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkeOrdelInvalidTime() throws Exception{
		mallOrderDaoMapper.checkeOrdelInvalidTime();
	}
}
