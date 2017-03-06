/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * ManageDepartmentDaoMapper.java
 */
public interface ManageDepartmentDaoMapper {
	
	/**
	 * 根据条件获得部门
	 */
	public List<ManageDepartmentBean> getManageDepartmentByMap(Map<String, String> map);
	
	/**
	 * 新增部门
	 */
	public void addManageDepartment(ManageDepartmentBean departmentBean);
	
	/**
	 * 根据Id获得部门
	 */
	public ManageDepartmentBean getManageDepartmentById(@Param("id")long id);

	/**
	 * Method name: delManageDepartmentById <BR>
	 * Description: 删除部门 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void delManageDepartmentById(@Param("id")long id);

	/**
	 * Method name: updateManageDepartmentById <BR>
	 * Description: 修改部门 <BR>
	 * Remark: <BR>
	 * @param DepartmentBean  void<BR>
	 */
	public void updateManageDepartmentById(ManageDepartmentBean departmentBean);

	/**
	 * Method name: getManageDepartmentCountByMap <BR>
	 * Description: 验证部门属性 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  long<BR>
	 */
	public long getManageDepartmentCountByMap(Map<String, String> map);
	
	/**
	 * Method name: setDeptToSubCom <BR>
	 * Description: 部门改为子公司 <BR>
	 * Remark: <BR>
	 * @param departmentBean  void<BR>
	 */
	public void setDeptToSubCom(ManageDepartmentBean departmentBean);
	
	
	/**
	 * Method name: downPrevDept <BR>
	 * Description: 前一部门order+1 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void downPrevDept(ManageDepartmentBean dept);

	/**
	 * Method name: upThisDept <BR>
	 * Description: 该部门order-1 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void upThisDept(@Param("id")int id);
	
	/**
	 * Method name: upNextDept <BR>
	 * Description: 下一部门order-1 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void upNextDept(ManageDepartmentBean dept);

	/**
	 * Method name: downThisDept <BR>
	 * Description: 该部门order+1 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void downThisDept(@Param("id")int id);
	
	
	/**
	 * Method name: getMaxOrder <BR>
	 * Description: 获取同级部门的最大order <BR>
	 * Remark: <BR>
	 * @param parentId
	 * @param companyId
	 * @return  Integer<BR>
	 */
	public Integer getMaxOrder(ManageDepartmentBean departmentBean);
	
	/**
	 * Method name: getGThanThisOrder <BR>
	 * Description: 获取order比当前order大的部门 <BR>
	 * Remark: <BR>
	 * @param dept
	 * @return  int<BR>
	 */
	public int getGThanThisOrder(ManageDepartmentBean dept);
	
	/**
	 * Method name: getLThanThisOrder <BR>
	 * Description: 获取order比当前order小的部门 <BR>
	 * Remark: <BR>
	 * @param dept
	 * @return  int<BR>
	 */
	public int getLThanThisOrder(ManageDepartmentBean dept);
	
	/**
	 * Method name: selectCourseOpenDeptByCourseId <BR>
	 * Description: 获取课程开放部门 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> selectCourseOpenDeptByCourseId(@Param("courseId")Integer id);
	
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
	 * Description: 获取父部门编码  <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  String<BR>
	 */
	public String getParentCode(Map<String, Object> param);
	
	/**chenrui start*/
	/**
	 * 
	 * Method name: getByName <BR>
	 * Description: 根据部门名称获取部门id <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  ManageDepartmentBean<BR>
	 */
	public ManageDepartmentBean getByName(@Param("name")String name)throws Exception;
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: getDepInfo <BR>
	 * Description: 获取当前公司下的子公司/部门  <BR>
	 * Remark: <BR>
	 * @param subCompanyId
	 * @param fromNum
	 * @param pageSize
	 * @return  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> getDepInfo(@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId, 
			@Param("fromNum")long fromNum,@Param("pageSize")String pageSize)throws Exception;
	public int getDepInfoCount(@Param("companyId")int companyId,@Param("subCompanyId")int subCompanyId)throws Exception;

	/**chenrui end*/
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCompanysByCompanyId <BR>
	 * Description: 根据集团公司id查询出所有公司 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return
	 * @throws DataBaseException  List<ManageDepartmentBean><BR>
	 */
	public List<ManageDepartmentBean> getCompanysByCompanyId(@Param("companyId")Integer companyId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
