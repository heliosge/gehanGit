package com.jftt.wifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.InfoAboutUsBean;
import com.jftt.wifi.bean.InformationBean;
import com.jftt.wifi.dao.InformationManageDaoMapper;
import com.jftt.wifi.service.InformationManageService;

/**
 * class name:资讯信息管理实现类
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
@Service("informationManageService")
public class InformationManageServiceImpl implements InformationManageService {

	
	@Resource(name="informationManageDaoMapper")
	private InformationManageDaoMapper informationManageDaoMapper;
	

	@Override
	public List<InformationBean> querylistInformation(InformationBean informationBean) {
		return 	informationManageDaoMapper.querylistInformation(informationBean);
	}
	public int  queryInformationCount(InformationBean informationBean){
		return 	informationManageDaoMapper.queryInformationCount(informationBean);
	}
	

	@Override
	public InformationBean queryInformation(int infoId) {
		
		return informationManageDaoMapper.queryInformation(infoId);
	}
	
	@Override
	public void saveInformation(InformationBean informationBean)throws Exception {
		//进行数据的保存
		informationManageDaoMapper.saveInformation(informationBean);
	}
	
	

	@Override
	public void updateInfomation(InformationBean informationBean) throws Exception  {
		
		//加载参数后，进行数据的保存
		informationManageDaoMapper.updateInfomation(informationBean);
	}
	
	@Override
	public void pushInformation(InformationBean informationBean) throws Exception  {
		
		informationManageDaoMapper.updateInfoPuslish(informationBean);
	}
	
	

	@Override
	public void deleteOneInformation(int userId, int infoId) throws Exception {
		// TODO Auto-generated method stub
		informationManageDaoMapper.deleteOneInformation(infoId,infoId);
	}
	@Override
	public void saveAboutUsInfo(InfoAboutUsBean infoAboutUsBean) throws Exception {
		// TODO Auto-generated method stub
		informationManageDaoMapper.saveAboutUsInfo(infoAboutUsBean);
	}

	@Override
	public StringBuffer editAboutUsInfo(int type,int companyId) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer strB = new StringBuffer();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("companyId",companyId);
		
		strB =strB.append(informationManageDaoMapper.queryAboutUsInfo(map));
		
		return strB;
	}
}
