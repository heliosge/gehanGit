/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagService.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserRoleBean;
import com.mongodb.BasicDBList;

/**
 * ManageService.java
 */
public interface ManageUserService {
	
	/**
	 * Method name: insert <BR>
	 * Description: 插入一个用户 <BR>
	 * Remark: <BR>
	 * @param user
	 * @return 用户主键id
	 * @throws Exception  int<BR>
	 */
	public int insert(ManageUserBean user) throws Exception ; 
	
    /**
     * Method name: findUserById <BR>
     * Description: 根据id查询一个用户 <BR>
     * Remark: <BR>
     * @param id
     * @return  ManageUserBean<BR>
     */
    public ManageUserBean findUserById(String id) throws Exception ;  
    
    /**
     * Method name: findUserById <BR>
     * Description: 根据id查询一个用户 <BR>
     * Remark: <BR>
     * @param id
     * @return  ManageUserBean<BR>
     */
    public List<ManageUserBean> findUserByIds(List<String> ids) throws Exception ;

    /**
     * Method name: findAll <BR>
     * Description: 查询所有用户 <BR>
     * Remark: <BR>
     * @return  List<ManageUserBean><BR>
     */
    public List<ManageUserBean> findAll() throws Exception ;   
    
    /**
     * Method name: findByRegex <BR>
     * Description: 根据姓名模糊查询用户  <BR>
     * Remark: <BR>
     * @param regex
     * @return  List<ManageUserBean><BR>
     */
    public List<ManageUserBean> findByName(String name) throws Exception ;
    
    /**
     * Method name: findUserByAccurateName <BR>
     * Description: 根据姓名精确查询该公司内的用户 <BR>
     * Remark: <BR>
     * @param name
     * @param companyId
     * @param subCompanyId
     * @return
     * @throws Exception  List<ManageUserBean><BR>
     */
    public List<ManageUserBean> findUserByAccurateName(String name, Integer companyId, Integer subCompanyId) throws Exception; 

    /**
     * Method name: removeOne <BR>
     * Description: 根据id删除一个用户 <BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
    public void removeOne(String id) throws Exception ;   


    /**
     * Method name: findAndModify <BR>
     * Description: 根据id查询并且更新改用户 <BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
    public void update(ManageUserBean user) throws Exception;   
    
    /**
     * Method name: count <BR>
     * Description: 用户数量 <BR>
     * Remark: <BR>
     * @return  long<BR>
     */
    public int findUserCountByCondition(Map<String, Object> map) throws Exception;
    
    /**
     * Method name: findUserByCondition <BR>
     * Description: 根据条件获取用户列表 <BR>
     * Remark: <BR>
     * @return  MMGridPageVoBean<BR>
     */
    public List<ManageUserBean> findUserByCondition(Map<String, Object> map) throws Exception;  
    
    /**
     * Method name: findUserByCondition <BR>
     * Description: 根据条件获取用户列表 <BR>
     * Remark: <BR>
     * @return  MMGridPageVoBean<BR>
     */
    public List<ManageUserBean> findUserByCondition(Map<String, Object> map, int page, int pageSize) throws Exception;   

    
    /**
     * Method name: findUserByUserName <BR>
     * Description: 根据登录名称查询用户 <BR>
     * Remark: <BR>
     * @param companyId 公司
     * @param name 姓名
     * @return  ManageUserBean<BR>
     */
    public ManageUserBean findUserByUserName(String userName, Integer companyId)  throws Exception;
	
	/**
	 * Method name: addUserRole <BR>
	 * Description: addUserRole <BR>
	 * Remark: <BR>
	 * @param manageUserRole
	 * @throws Exception  void<BR>
	 */
	public void addUserRole(ManageUserRoleBean manageUserRole) throws Exception;
	
	/**
	 * Method name: deleteUserRole <BR>
	 * Description: deleteUserRole <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param roleId
	 * @throws Exception  void<BR>
	 */
	public void deleteUserRole(String userId, String roleId) throws Exception;
	
	/**
	 * Method name: deleteUserRoleByRole <BR>
	 * Description: deleteUserRoleByRole <BR>
	 * Remark: <BR>
	 * @param roleId
	 * @throws Exception  void<BR>
	 */
	public void deleteUserRoleByRole(String roleId) throws Exception;
	
	public List<Integer> getRoleIdByUserId(String userId) throws Exception;
	
	public List<ManageRoleBean> getRoleByUserId(String userId) throws Exception;

	/**
	 * Method name: updateManageUserPassword <BR>
	 * Description: 修改账户、密码 <BR>
	 * Remark: <BR>
	 * @param string
	 * @param md5
	 * @throws Exception  void<BR>
	 */
	public void updateManageUserPassword(String id, String username, String md5) throws Exception;

		/**
	 * Method name: deleteSubManagePageByUserId <BR>
	 * Description: 修改公司权限，删除该公司子用户缺少的权限 <BR>
	 * Remark: <BR>
	 * @param roleId  void<BR>
	 */
	public void deleteSubManagePageByUserId(long roleId) throws Exception ;

	/**
	 * Method name: findUserByListCondition <BR>
	 * Description: 条件in查询 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	List<ManageUserBean> findUserByListCondition(
				Map<String, Object> map) throws Exception;

	/**
	 * Method name: findUserByListCondition <BR>
	 * Description: 条件in查询 <BR>
	 * Remark: <BR>
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	List<ManageUserBean> findUserByListCondition(Map<String, Object> map,
			int page, int pageSize) throws Exception;
	
	/**
	 * Method name: findUserByListCondition <BR>
	 * Description: 条件in查询 <BR>
	 * Remark: <BR>
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	List<ManageUserBean> findUserByNotInCondition(Map<String, List> map) throws Exception;

	/**
	 * Method name: resetPassword <BR>
	 * Description: 重置密码 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param md5  void<BR>
	 */
	public void resetPassword(String id, String md5) throws Exception;

	/**
	 * Method name: deleteUserRoleByUserId <BR>
	 * Description: 删除某个用户的所有角色 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @throws Exception  void<BR>
	 */
	void deleteUserRoleByUserId(String userId) throws Exception;

	/**
	 * Method name: findUserByIdCondition <BR>
	 * Description: findUserByIdCondition <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  ManageUserBean<BR>
	 */
	ManageUserBean findUserByIdCondition(String id) throws Exception;

	/**
	 * Method name: findUserByNotInCondition <BR>
	 * Description: not in 分页 <BR>
	 * Remark: <BR>
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	List<ManageUserBean> findUserByNotInCondition(Map<String, List> map,
			int page, int pageSize) throws Exception;

	/**
	 * Method name: findUserIdByRoleName <BR>
	 * Description: 根据角色名称获取人员id <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  List<String><BR>
	 */
	public List<String> findUserIdByRoleName(Map<String, Object> map);

	/**
	 * Method name: findUserByListConditionCount <BR>
	 * Description: 根据条件查询用户数量 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  int<BR>
	 */
	int findUserByListConditionCount(Map<String, Object> map) throws Exception;

	/**
	 * Method name: exportStudent <BR>
	 * Description: 导出学生excel <BR>
	 * Remark: <BR>
	 * @param list
	 * @return
	 * @throws Exception  HSSFWorkbook<BR>
	 */
	public HSSFWorkbook exportStudent(List<ManageUserBean> list) throws Exception;

	/**
	 * Method name: checkStudentStudyRecord <BR>
	 * Description: 判断学生是否存在学习记录 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  int<BR>
	 */
	public int checkStudentStudyRecord(int id);

	/**
	 * Method name: findUserByListCondition_ <BR>
	 * Description: findUserByListCondition_ <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	List<ManageUserBean> findUserByListCondition_(Map<String, Object> map)
			throws Exception;
	

}
