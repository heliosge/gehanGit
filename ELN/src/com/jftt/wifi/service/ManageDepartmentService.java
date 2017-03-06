/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagService.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.CompanyVo;

/**
 * ManageDepartmentService.java
 */
public interface ManageDepartmentService {

	
	/**
	 * 根据条件获得部门信息
	 */
	public List<ManageDepartmentBean> getManageDepartmentByMap(Map<String, String> map) throws Exception;
	
	/**
	 * 新增部门信息
	 */
	public void addManageDepartment(ManageDepartmentBean departmentBean) throws Exception;
	
	/**
	 * 根据Id获得部门信息
	 */
	public ManageDepartmentBean getManageDepartmentById(long id) throws Exception;
	
	/**
	 * 删除部门
	 */
	public void delManageDepartmentById(long id) throws Exception;
	
	
	/**
	 * Method name: updateManageDepartmentById <BR>
	 * Description: 修改部门 <BR>
	 * Remark: <BR>
	 * @param departmentBean  void<BR>
	 * @throws Exception 
	 */
	public void updateManageDepartmentById(ManageDepartmentBean departmentBean) throws Exception;
	
	/**
	 * Method name: getManageDepartmentCountByMap <BR>
	 * Description: 验证部门属性 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  long<BR>
	 */
	public long getManageDepartmentCountByMap(Map<String, String> map) throws Exception;
	
	/**
	 * Method name: setDeptToSubCom <BR>
	 * Description: 将部门设置为子公司 <BR>
	 * Remark: <BR>
	 * @param departmentBean
	 * @throws Exception  void<BR>
	 */
	public void setDeptToSubCom(ManageDepartmentBean departmentBean) throws Exception;
	
	/**
	 * Method name: upDept <BR>
	 * Description: 上移部门 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public void upDept(int id);
	
	/**
	 * Method name: upDept <BR>
	 * Description: 下移部门 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public void downDept(int id);
	
	/**
	 * Method name: getMaxCode <BR>
	 * Description: 获取该层级的最大部门编码 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  String<BR>
	 */
	public String getMaxCode(Map<String, Object> param);
	
	
	/**
	 * Method name: getParentCode <BR>
	 * Description: 获取父部门编码 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  String<BR>
	 */
	public String getParentCode(Map<String, Object> param);
	
	/**
	 * Method name: getParentComList <BR>
	 * Description: 获取用户的所有上层公司 <BR>
	 * Remark: <BR>
	 * @param user
	 * @return  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> getParentComList(ManageUserBean user);
	/**chenrui start*/
	
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getDepInfo <BR>
	 * Description: 获取当前公司下的子公司/部门  <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param fromNum
	 * @param pageSize
	 * @return  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> getDepInfo(String userId, long fromNum, String pageSize) throws Exception;
	
	public int getDepInfoCount(String userId) throws Exception;
	
	/**chenrui end*/
	
	/**
	 * wj add
	 * 获得公司的所有上级公司信息 并从大到小排序
	 */
	public List<CompanyVo> getParents(ManageUserBean userBean);
	
	/**
	 * wj add
	 * 获得子公司的所有下级子公司 包括自己,
	 */
	public List<Long> getChildren(long companyId, long subCompanyId);
	
		
}
