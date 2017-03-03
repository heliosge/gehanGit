/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: TeacherManagerDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月23日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.TeacherBean;

/**
 * class name:TeacherManagerDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月23日
 * @author JFTT)liucaibao
 */
public interface TeacherManageDaoMapper {

	/**
	 * Method name: 查询讲师列表
	 * Description: listTeacher <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  List<TeacherBean><BR>
	 */
	public List<TeacherBean>listTeacher(Map<String,Object> map) throws Exception;
	public int queryListTeacherCount(Map<String,Object> map) throws Exception;
	
	
	/**
	 * Method name: 查询讲师详情
	 * Description: detailTeacher <BR>
	 * Remark: <BR>
	 * @param teacherId
	 * @return  TeacherBean<BR>
	 */
	public TeacherBean detailTeacher(@Param("teacherId")int teacherId);
	
	
	/**
	 * Method name: 删除某个讲师
	 * Description: deleteOneTeacher <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void deleteOneTeacher(Map<String,Integer> map);
	
	
	
	/**
	 * Method name: 保存讲师信息
	 * Description: saveTeacherInfo <BR>
	 * Remark: <BR>
	 * @param teacherBean  void<BR>
	 */
	public void saveTeacherInfo(TeacherBean teacherBean)  throws Exception;
	
	
	/**
	 * Method name: getPostById <BR>
	 * Description: 查找岗位和部门名称 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String getPostById(@Param("id")String id);
	public String getDeptNameById(@Param("id")String id);
	
	
	public int queryUserCount(Map<String,Object> map);
	
	/**
	 * Method name: 更新讲师信息
	 * Description: updateTeacherInfo <BR>
	 * Remark: <BR>  void<BR>
	 */
	public void updateTeacherInfo(TeacherBean teacherBean)throws Exception;
}
