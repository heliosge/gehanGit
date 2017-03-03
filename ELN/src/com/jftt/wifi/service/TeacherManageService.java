/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: TeacherManageService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月22日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.TeacherBean;

/**
 * class name:TeacherManageService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月22日
 * @author JFTT)liucaibao
 */
public interface TeacherManageService {
	
	/**
	 * Method name:获取讲师列表
	 * Description: listTeacher <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<TeacherBean><BR>
	 */
	public List<TeacherBean> listTeacher(Map<String,Object> map) throws Exception;
	public int queryListTeacherCount(Map<String,Object> map) throws Exception; 
	
	
	/**
	 * Method name: 查询讲师详情
	 * Description: detailTeacher <BR>
	 * Remark: <BR>
	 * @param teacherId
	 * @return  TeacherBean<BR>
	 */
	public TeacherBean  detailTeacher(int teacherId);
	
	/**
	 * Method name: 保存讲师信息
	 * Description: saveTeacherInfo <BR>
	 * Remark: <BR>
	 * @param teacherBean  void<BR>
	 */
	public void saveTeacherInfo(TeacherBean teacherBean) throws Exception;
	
	/**
	 * Method name: getPostById <BR>
	 * Description: 根据职位id查询职位 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String getPostById(String id);
	public String getDeptNameById(String id);
	
	
	/**
	 * Method name: queryUserCount <BR>
	 * Description: 差用用户数 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  int<BR>
	 */
	public int queryUserCount(Map<String,Object> map);
	/**
	 * Method name: 更新讲师信息
	 * Description: updateTeacherInfo <BR>
	 * Remark: <BR>
	 * @param teacherBean
	 * @throws Exception  void<BR>
	 */
	public void updateTeacherInfo(TeacherBean teacherBean)throws Exception;;
	
	/**
	 * Method name: 删除讲师信息
	 * Description: deleteTeacherInfo <BR>
	 * Remark: <BR>
	 * @param userId  void<BR>
	 */
	public void deleteTeacherInfo(int userId,int teacherId) throws Exception;
}
