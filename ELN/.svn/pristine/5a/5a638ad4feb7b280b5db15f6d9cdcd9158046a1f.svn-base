/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InformationManageDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.InfoAboutUsBean;
import com.jftt.wifi.bean.InformationBean;

/**
 * class name:InformationManageDaoMapper <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
public interface InformationManageDaoMapper {

	/**
	 * Method name: 查询资讯列表
	 * Description: querylistInformation <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<BR>
	 */
	public List<InformationBean> querylistInformation(InformationBean informationBean);
	public int queryInformationCount(InformationBean informationBean);
	
	/**
	 * Method name: 获取页面上的信息。
	 * Description: editInformation <BR>
	 * Remark: <BR>
	 * @param infoId
	 * @return  InformationBean<BR>
	 */
	public InformationBean queryInformation(@Param("infoId")int infoId);
	
	
	/**
	 * Method name: 保存资讯信息
	 * Description: saveInformation <BR>
	 * Remark: <BR>
	 * @param informationBean  void<BR>
	 */
	public void saveInformation(InformationBean informationBean);
	
	
	
	/**
	 * Method name: 更新资讯信息
	 * Description: updateInfomation <BR>
	 * Remark: <BR>
	 * @param informationBean  void<BR>
	 */
	public void updateInfomation(InformationBean informationBean);

	
	/**
	 * Method name: 添加关于我们的相关信息
	 * Description: saveAboutUsInfo <BR>
	 * Remark: <BR>
	 * @param map  void<BR>
	 */
	public void saveAboutUsInfo(InfoAboutUsBean infoAboutUsBean);
	
	
	/**
	 * Method name: 删除资讯信息，此处为逻辑删除。
	 * Description: deleteOneInformation <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param infoId  void<BR>
	 */
	public void deleteOneInformation(@Param("userId")int userId,@Param("infoId")int infoId);
	
	/**
	 * Method name: 更新发布状态
	 * Description: updateInfoPuslish <BR>
	 * Remark: <BR>
	 * @param infoId
	 * @param isPublish  void<BR>
	 */
	public void updateInfoPuslish(InformationBean informationBean);
	
	/**
	 * Method name: 查询关于我们、服务项目、联系我们的相关富文本信息
	 * Description: queryAboutUsInfo <BR>
	 * Remark: <BR>
	 * @param map
	 * @return  String<BR>
	 */
	public String queryAboutUsInfo(Map<String, Object> map);
	
}
