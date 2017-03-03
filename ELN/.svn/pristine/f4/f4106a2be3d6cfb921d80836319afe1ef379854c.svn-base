package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.SafetyEducationBean;
import com.jftt.wifi.bean.vo.SafetyEducationSearchVo;
/**
 * 安全宣教表
 * class name:SafetyEducationDaoMapper <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月13日
 * @author JFTT)chenrui
 */
public interface SafetyEducationDaoMapper {

    SafetyEducationBean selectByPrimaryKey(Integer id);

	void add(SafetyEducationBean paramVo) throws Exception;
	/**
	 * 修改
	 * Method name: updateById <BR>
	 * Description: updateById <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	void updateById(SafetyEducationBean paramVo) throws Exception;
	/**
	 * 获取安全宣教列表
	 * Method name: getSafetyEducationList <BR>
	 * Description: getSafetyEducationList <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  List<SafetyEducationBean><BR>
	 */
	List<SafetyEducationBean> getSafetyEducationList(SafetyEducationSearchVo params) throws Exception;

	int getSafetyEducationListCount(SafetyEducationSearchVo params) throws Exception;
	
	/**
	 * 删除
	 * Method name: deleteByIds <BR>
	 * Description: deleteByIds <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  void<BR>
	 */
	void deleteByIds(@Param("ids") String[] ids) throws Exception;

	List<SafetyEducationBean> stu_getSafetyEducationList(SafetyEducationSearchVo params) throws Exception;

	int stu_getSafetyEducationListCount(SafetyEducationSearchVo params) throws Exception;

}