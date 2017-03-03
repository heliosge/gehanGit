package com.jftt.wifi.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jftt.wifi.bean.ExamQueryConditionBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.SearchKlVo;
import com.jftt.wifi.dao.ExamScheduleDaoMapper;
import com.jftt.wifi.dao.KnowledgeManagerDaoMapper;
import com.jftt.wifi.service.ManageUserService;
public class Test {
	protected static ApplicationContext context;
    static{
    	context = new ClassPathXmlApplicationContext("classpath:resourceConfig/springConfig/*.xml");
    }
    public static void main(String[] args) throws Exception {
    	ManageUserService ma = (ManageUserService)context.getBean("manageUserService");
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("companyId", 1);
    	map.put("postId", 2);
    	System.out.println(ma.findUserCountByCondition(map));
//    	Map<String, List> map = new HashMap<String, List>();
//    	List<String> list = new ArrayList<String>();
//    	list.add("1");
//    	map.put("id", list);
//    	List<String> list_ = new ArrayList<String>();
//    	list_.add("管理员");
//    	map.put("name", list_);
//    	System.out.println(ma.findUserByNotInCondition(map).size());
//    	Map<String, List> map = new HashMap<String, List>();
//    	map.put("deptId", "财务部");
//    	List<String> list = new ArrayList<String>();
//    	list.add("1");
//    	map.put("id", list);
//    	System.out.println(ma.findUserByNotInCondition(map));
//    	Integer a = 1;
//    	Integer b = 1;
//    	System.out.println(a==b);
    	
//    	Random ra =new Random(9);
//    	for (int i=0;i<30;i++)
//    	{System.out.println(""+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10)+ra.nextInt(10));}
    	//System.out.println(daysBetween(TimeUtil.parseString2Date("2015-09-10"), new Date()));
    }
    
}
