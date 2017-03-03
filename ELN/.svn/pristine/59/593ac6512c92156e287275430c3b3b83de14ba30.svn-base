/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: OamBarDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-10        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.OamBarBean;
import com.jftt.wifi.bean.vo.IndexShowVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:OamBarDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-10
 * @author JFTT)ShenHaijun
 */
public interface OamBarDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取运维栏 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws DataBaseException  OamBarBean<BR>
	 */
	public OamBarBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getBarsByCompanyId <BR>
	 * Description: 获取该公司的所有推广模块 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @param style
	 * @return
	 * @throws DataBaseException  List<IndexShowVo><BR>
	 */
	public List<IndexShowVo> getBarsByCompanyId(@Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId, @Param("style")Integer style) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
