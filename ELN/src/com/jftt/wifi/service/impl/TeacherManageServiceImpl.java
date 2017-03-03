/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: TeacherManageServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月23日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.TeacherBean;
import com.jftt.wifi.dao.TeacherManageDaoMapper;
import com.jftt.wifi.service.TeacherManageService;

/**
 * class name:讲师管理实现类
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月23日
 * @author JFTT)liucaibao
 */
@Service("teacherManageService")
public class TeacherManageServiceImpl implements TeacherManageService {
	
	@Resource(name="teacherManageDaoMapper")
	private TeacherManageDaoMapper teacherManageDaoMapper;
	

	@Override
	public List<TeacherBean> listTeacher(Map<String,Object> param) throws Exception  {
		return  teacherManageDaoMapper.listTeacher(param);
	}
	@Override
	public int queryListTeacherCount(Map<String,Object> map) throws Exception{
		return teacherManageDaoMapper.queryListTeacherCount(map);
	}
	
	@Override
	public TeacherBean  detailTeacher(int teacherId){
		
		return teacherManageDaoMapper.detailTeacher(teacherId);
	}

	@Override
	public void saveTeacherInfo(TeacherBean teacherBean)  throws Exception{
		teacherManageDaoMapper.saveTeacherInfo(teacherBean);
	}
	
	public String getPostById(String id){
		return teacherManageDaoMapper.getPostById(id);
	}
	public String getDeptNameById(String id){
		return teacherManageDaoMapper.getDeptNameById(id);
	}
	public int queryUserCount(Map<String,Object> map){
		return teacherManageDaoMapper.queryUserCount(map);
	}
	
	public void updateTeacherInfo(TeacherBean teacherBean)throws Exception{
		
		teacherManageDaoMapper.updateTeacherInfo(teacherBean);
	}
	
	@Override
	public void deleteTeacherInfo(int userId,int teacherId) throws Exception{
		
		Map<String,Integer> param = new HashMap<String, Integer>();
		param.put("userId", userId);
		param.put("teacherId", teacherId);
		teacherManageDaoMapper.deleteOneTeacher(param);
	}

}
