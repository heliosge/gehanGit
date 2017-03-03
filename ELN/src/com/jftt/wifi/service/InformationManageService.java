/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InformationManageService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.jftt.wifi.bean.InfoAboutUsBean;
import com.jftt.wifi.bean.InformationBean;

/**
 * class name:InformationManageService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
public interface InformationManageService {
	
	
	
	
	/**
	 * Method name: 查询资讯列表,查询列表总数
	 * Description: querylistInformation <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return  List<BR>
	 */
	public List<InformationBean> querylistInformation(InformationBean informationBean) throws Exception;
	public int 	queryInformationCount(InformationBean informationBean)throws Exception;;
	
	/**
	 * Method name: 
	 * Description: editInformation <BR>
	 * Remark: <BR>
	 * @param infoId
	 * @return  InformationBean<BR>
	 */
	public InformationBean queryInformation(int infoId);
	
	
	/**
	 * Method name:保存资讯信息
	 * Description: saveInformation <BR>
	 * Remark: <BR>
	 * @param informationBean  void<BR>
	 */
	public void saveInformation(InformationBean informationBean) throws Exception;
	
	
	
	/**
	 * Method name: 更新资讯信息
	 * Description: updateInfomation <BR>
	 * Remark: <BR>
	 * @param informationBean  void<BR>
	 */
	public void updateInfomation(InformationBean informationBean)throws Exception;
	
	
	
	/**
	 * Method name: 发布资讯
	 * Description: pushInformation <BR>
	 * Remark: <BR>
	 * @param infoId
	 * @param isPublish  void<BR>
	 */
	public void pushInformation(InformationBean informationBean)throws Exception;
	
	
	
	/**
	 * Method name: deleteOneInformation <BR>
	 * Description: deleteOneInformation <BR>
	 * Remark: <BR>
	 * @param userId  用户id
	 * @param infoId  资讯id
	 */
	public void deleteOneInformation(int userId,int infoId)throws Exception;
	
	
	/**
	 * Method name: 保存关于我们等相关信息
	 * Description: saveAboutUsInfo <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param type
	 * @param infoDesc  void<BR>
	 */
	public void saveAboutUsInfo(InfoAboutUsBean infoAboutUsBean)throws Exception;
	
	
	/**
	 * Method name:进入关于我们的编辑页面
	 * Description: editAboutUsInfo <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param type
	 * @return  StringBuffer<BR>
	 */
	public StringBuffer editAboutUsInfo(int type,int companyId)throws Exception;

}
