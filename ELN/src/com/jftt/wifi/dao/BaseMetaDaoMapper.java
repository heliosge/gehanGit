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

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.BaseMetaBean;

/**
 * BaseMetaBean.java 元数据
 */
public interface BaseMetaDaoMapper {
	
	//获取元数据
	public List<BaseMetaBean> getMeta(BaseMetaBean baseMetaBean);
	
	//获取所以元数据
	public List<BaseMetaBean> getAllBaseMeta();
	
	//增加节点
	public void addMeta(BaseMetaBean baseMetaBean);
	
	//修改元数据
	public void updateMeta(BaseMetaBean baseMetaBean);
	
	/**
	 * Method name: getTeseByLouPan <BR>
	 * Description: 根据楼盘获取特色的源数据 <BR>
	 * Remark: <BR>
	 * @param loupan_id
	 * @return  List<BaseMetaBean><BR>
	 */
	public List<BaseMetaBean> getTeseByLouPan(@Param("loupan_id")long loupan_id);

	/**
	 * Method name: findValueByID <BR>
	 * Description: 根据ID获取值 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String findValueByID(long id);

	/**
	 * Method name: getIdByNameAndKey <BR>
	 * Description: 根据名称和KEY查询ID <BR>
	 * Remark: <BR>
	 * @param name
	 * @param key
	 * @return  long<BR>
	 */
	public String getIdByNameAndKey(@Param("name")String name, @Param("key")String key);

	public String getValueByKey(@Param("key")String code);
}
