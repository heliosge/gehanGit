/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageNoticeServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月5日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.vo.ManageNoticeVo;
import com.jftt.wifi.dao.ManageNoticeDaoMapper;
import com.jftt.wifi.service.ManageNoticeService;

/**
 * class name:ManageNoticeServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月5日
 * @author JFTT)Lu Yunlong
 */
@Service("manageNoticeService")
public class ManageNoticeServiceImpl implements ManageNoticeService{
	
	@Resource(name="manageNoticeDaoMapper")
	private ManageNoticeDaoMapper manageNoticeDaoMapper;

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#insertNotice(com.jftt.wifi.bean.ManageNoticeBean) <BR>
	 * Method name: 新增消息 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param bean  <BR>
	 * @throws Exception 
	*/
	@Override
	public void insertNotice(ManageNoticeBean bean) throws Exception {
		manageNoticeDaoMapper.insert(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#deleteNotice(java.lang.Integer) <BR>
	 * Method name: 删除消息 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteNotice(ManageNoticeVo vo) throws Exception {
		manageNoticeDaoMapper.deleteById(vo);
	}

	@Override
	public List<ManageNoticeBean> select(ManageNoticeVo param) throws Exception {
		return manageNoticeDaoMapper.select(param);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#selectById(java.lang.Integer) <BR>
	 * Method name: 根据id查询 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public ManageNoticeBean selectById(Integer id) throws Exception {
		ManageNoticeVo param = new ManageNoticeVo();
		param.setId(id);
		return manageNoticeDaoMapper.select(param).get(0);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#selectCount(com.jftt.wifi.bean.vo.ManageNoticeVo) <BR>
	 * Method name: 查询数量 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public int selectCount(ManageNoticeVo param) throws Exception {
		return manageNoticeDaoMapper.selectCount(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#updateNoticeReadStatus(java.lang.String) <BR>
	 * Method name: updateNoticeReadStatus <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	 * @throws Exception 
	*/
	@Override
	public void updateNoticeReadStatus(int id) throws Exception {
		manageNoticeDaoMapper.updateNoticeReadStatus(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#selectSendNoticeCount(com.jftt.wifi.bean.vo.ManageNoticeVo) <BR>
	 * Method name: selectSendNoticeCount <BR>
	 * Description: 查询发件箱数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	*/
	@Override
	public int selectSendNoticeCount(ManageNoticeVo vo) {
		// TODO Auto-generated method stub
		return manageNoticeDaoMapper.selectSendNoticeCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageNoticeService#selectSendNotice(com.jftt.wifi.bean.vo.ManageNoticeVo) <BR>
	 * Method name: selectSendNotice <BR>
	 * Description: 查询发件箱 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	*/
	@Override
	public List<ManageNoticeBean> selectSendNotice(ManageNoticeVo vo) {
		// TODO Auto-generated method stub
		return manageNoticeDaoMapper.selectSendNotice(vo);
	}
	
	

}
