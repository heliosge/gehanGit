package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.LogisticsCompanyBean;


public interface LogisticsCompanyDaoMapper {


	/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查公司 <BR>
	 * Remark: <BR>
	 * @param id  LogisticsCompanyBean<BR>
	 */
	public LogisticsCompanyBean getById(@Param("id")Integer id) throws Exception;

	

	/**
	 * Method name: checkname <BR>
	 * Description: 检查名称重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkName(LogisticsCompanyBean bean) throws Exception;
	
	/**
	 * Method name: insertLogisticsCompany <BR>
	 * Description: 新增物流公司 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertLogisticsCompany(LogisticsCompanyBean bean) throws Exception;

	/**
	 * Method name: updateLogisticsCompany <BR>
	 * Description: 修改 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateLogisticsCompany(LogisticsCompanyBean bean) throws Exception;

	/**
	 * Method name: deleteLogisticsCompany<BR>
	 * Description: 删除 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteLogisticsCompany(@Param("id")Integer id) throws Exception;

	

	/**
	 * Method name: selectLogisticsCompany <BR>
	 * Description: 查询<BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<LogisticsCompanyBean> selectLogisticsCompany() throws Exception;
	

	
	
}
