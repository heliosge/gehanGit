/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:TagDaoMapper.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2013/01/07           |  JFTT)wangjian    |  original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.BaseMetaBean;
import com.jftt.wifi.dao.BaseMetaDaoMapper;
import com.jftt.wifi.service.BaseMetaService;


/**
 * BaseMetaBean.java 元数据
 */
@Service("baseMetaService")
public class BaseMetaServiceImpl implements BaseMetaService{
	
	@Resource(name="baseMetaDaoMapper")
	private BaseMetaDaoMapper baseMetaDaoMapper;
	
	//获取元数据
	public List<BaseMetaBean> getMetaByKey(String key){
		
		BaseMetaBean metaBean = new BaseMetaBean();
		
		metaBean.setMetaKey(key);
		metaBean.setDeleteFlag(0);
		
		return baseMetaDaoMapper.getMeta(metaBean);
	}
	
	//获取所以元数据
	public List<BaseMetaBean> getAllBaseMeta(){
		
		return baseMetaDaoMapper.getAllBaseMeta();
	}
	
	//增加节点
	@Transactional(rollbackFor=Exception.class)
	public void addMeta(BaseMetaBean baseMetaBean){
		
		baseMetaDaoMapper.addMeta(baseMetaBean);
	}
	
	//修改元数据
	@Transactional(rollbackFor=Exception.class)
	public void updateMeta(BaseMetaBean baseMetaBean){
		
		baseMetaDaoMapper.updateMeta(baseMetaBean);
	}
	
	//删除元数据
	@Transactional(rollbackFor=Exception.class)
	public void deleteMeta(long id){
		
		BaseMetaBean metaBean = new BaseMetaBean();
		
		metaBean.setId(id);
		metaBean.setDeleteFlag(1);
		
		baseMetaDaoMapper.updateMeta(metaBean);
	}

	@Override
	public String getValueById(long id) {
		String name = baseMetaDaoMapper.findValueByID(id);
		return name;
	}
	
	@Override
	public String getValueByKey(String code) {
		
		return baseMetaDaoMapper.getValueByKey(code);
	}
	
	
}
