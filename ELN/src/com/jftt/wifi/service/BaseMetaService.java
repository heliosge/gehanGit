/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.BaseMetaBean;


/**
 * BaseMetaBean.java 元数据
 */
public interface BaseMetaService {
	
	//获取元数据
	public List<BaseMetaBean> getMetaByKey(String key);
	
	//获取所以元数据
	public List<BaseMetaBean> getAllBaseMeta();
	
	//增加节点
	public void addMeta(BaseMetaBean baseMetaBean);
	
	//修改元数据
	public void updateMeta(BaseMetaBean baseMetaBean);
	
	//删除元数据
	public void deleteMeta(long id);
	
	/**
	 * Method name: getValueById <BR>
	 * Description: 根据ID获取值 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  String<BR>
	 */
	public String getValueById(long id);

	public String getValueByKey(String code);
}
