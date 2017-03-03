/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InformationManageAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.InfoAboutUsBean;
import com.jftt.wifi.bean.InformationBean;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.InformationManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;

/**
 * class name:资讯信息管理
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月17日
 * @author JFTT)liucaibao
 */
@Controller
@RequestMapping("information")
public class InformationManageAction {

	
	@Resource(name="informationManageService")
	private InformationManageService informationManageService;
	
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageCompanyService manageCompanyService;
	private static Logger log = Logger.getLogger(InformationManageAction.class); 

	
	@RequestMapping(value ="toStuListInfo")
	public String toStuListInfo(HttpServletRequest request){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:listInformation");
		return	"information/stuListInfo";
	}

	/**
	 * Method name: 获取资讯列表
	 * Description: listInforMation <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value ="list")
	public String listInformation(HttpServletRequest request,InformationBean informationBean){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:listInformation param:"+informationBean.toString());
		return	"information/listInfo";
	}
	
	@RequestMapping(value = "getInfoListByMap")
	@ResponseBody
	public Object getInfoListByMap(HttpServletRequest request, int page, int pageSize,InformationBean informationBean){

		//入口日志
		log.debug("Enter Class:InformationManageAction Method:getInfoListByMap param:"+informationBean.toString());
		//组织参数
		
		int size = 0;
		try {
			informationBean.setCompanyId(getCompanyId(request));
			//获得按照用户进行查询
			if(null!=informationBean.getUserName()&&informationBean.getUserName()!=""){
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("name", informationBean.getUserName().trim());
				param.put("companyId",getCompanyId(request));
				
				List<ManageUserBean> userBeans = manageUserService.findUserByCondition(param);
				List<String> list =new ArrayList<String>();
				for(ManageUserBean bean:userBeans ){
					list.add(bean.getId());
				}
				if(StringUtils.isNotBlank(informationBean.getUserName().trim())){
					if(!list.isEmpty()){
						informationBean.setUserIds(StringUtils.join(list,","));
					}else{
						informationBean.setUserIds("-1");//如果查找不到，则给予-1,后台就查找不到数据
					}
				}
			}
			
			size = informationManageService.queryInformationCount(informationBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e+"查询总数失败，param:"+informationBean.toString());
		}
		
		List<InformationBean> rows = null;
		try {
			informationBean.setFromNum((page-1)*pageSize);
			
			informationBean.setPage(pageSize);
			rows = informationManageService.querylistInformation(informationBean);//manageUserService.findUserByCondition(map,page,pageSize);
			//此处需要处理，将userId转成userName
			for(InformationBean kl:rows){
				String userId = kl.getCreateUserId();
				kl.setUserName(getUserBean(userId).getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e+"查询列表失败，param:"+informationBean.toString());
		}
		
		MMGridPageVoBean<InformationBean> vo = new MMGridPageVoBean<InformationBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	/**
	 * Method name: 添加资讯信息跳转action。
	 * Description: addInformation <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value ="add")
	public String  addInformation(){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:addInformation");
		return "information/addInformation";
	}
	
	
	/**
	 * Method name:修改资讯信息页面
	 * Description: editInformation <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value ="edit")
	public String editInformation(HttpServletRequest request,int infoId){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:editInformation param:"+infoId);
		//1、获取当前用户id
		String userId = request.getParameter("userId");
		//此查询获取的参数，传到前台去。
		InformationBean informationBean = informationManageService.queryInformation(infoId);
		
		return "information/editInformation";
	}
	
	/**
	 * Method name: 查询资讯详情
	 * Description: detailInformation <BR>
	 * Remark: <BR>
	 * @param request
	 * @param infoId
	 * @return  String<BR>
	 */
	@RequestMapping(value ="detail")
	public String detailInformation(HttpServletRequest request,int infoId,String mode){
		
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:detailInformation param:"+infoId);
		//此查询获取的参数，传到前台去。
		InformationBean informationBean = informationManageService.queryInformation(infoId);
		//将值塞到request中，带入前台。
		request.setAttribute("informationBean", informationBean);
		if(mode.equals("detail")){
			return "information/detailInformation";
		}else{
			return "information/editInformation";

		}
	}
	
	@RequestMapping(value ="toStuDetail")
	public String toStuDetail(HttpServletRequest request,int infoId){
		
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:detailInformation param:"+infoId);
		//此查询获取的参数，传到前台去。
		InformationBean informationBean = informationManageService.queryInformation(infoId);
		//将值塞到request中，带入前台。
		request.setAttribute("informationBean", informationBean);
		return "information/stuDetailInformation";
	}
	
	/**
	 * Method name: 保存资讯信息
	 * Description: saveInformation <BR>
	 * Remark: <BR>
	 * @param request
	 * @param informationBean 实体bean，主要为页面传过来的资讯信息
	 * @return  String<BR>
	 */
	@RequestMapping(value ="save")
	@ResponseBody
	public String saveInformation(HttpServletRequest request,InformationBean informationBean){
		
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:saveInformation param:"+informationBean.toString());
		//1、获取当前用户id
		int userId = getUserId(request);
		informationBean.setUserId(userId);
		try {
			informationBean.setCompanyId(getCompanyId(request));
			//进行数据的保存
			informationManageService.saveInformation(informationBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+informationBean.toString());
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 更新资讯信息
	 * Description: updateInfomation <BR>
	 * Remark: <BR>
	 * @param request
	 * @param informationBean  void<BR>
	 */
	@RequestMapping(value ="update")
	@ResponseBody
	public String updateInfomation(HttpServletRequest request,InformationBean informationBean){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:updateInfomation param:"+informationBean.toString());
		//1、获取当前用户id
		try {
			//进行数据的保存
			informationBean.setUserId(getUserId(request));
			informationManageService.updateInfomation(informationBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+informationBean.toString());
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	/**
	 * Method name:点击发布资讯/或者取消发布
	 * Description: pushInformation <BR>
	 * Remark: <BR>  void<BR>
	 */	
	@RequestMapping(value ="publish")
	@ResponseBody
	public String pushInformation(HttpServletRequest request,InformationBean bean){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:pushInformation param:"+bean.toString());
		try {
			//数据的更新
			bean.setUserId(getUserId(request));
			informationManageService.pushInformation(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+bean);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 删除单个资讯信息
	 * Description: deleteOneInformation <BR>
	 * Remark: <BR>
	 * @param request
	 * @param infoId  void<BR>
	 */
	@RequestMapping(value ="delete")
	@ResponseBody
	public String deleteOneInformation(HttpServletRequest request,int infoId){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:deleteOneInformation param:"+infoId);
		//1、获取当前用户id
		
		try {
			int userId = getUserId(request);
			informationManageService.deleteOneInformation(userId,infoId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+infoId);
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 批量删除资讯信息。
	 * Description: deleteBatchInformation <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids  void<BR>
	 */
	@RequestMapping(value ="deleteBatch")
	@ResponseBody
	public String deleteBatchInformation(HttpServletRequest request,String ids){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:deleteBatchInformation param:"+ids.toString());
		if(!ids.isEmpty()){
			List<String> idArr =  Arrays.asList(ids.split("\\|"));
			int userId = getUserId(request);
			try{
				for(int i=0;i<idArr.size();i++){
					if(StringUtils.isBlank(idArr.get(i)))
						continue;
						informationManageService.deleteOneInformation(userId,Integer.valueOf(idArr.get(i)));
					}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e+"param:"+ids.toString());
				return Constant.AJAX_FAIL;
			}
		}
		return Constant.AJAX_SUCCESS;
	}
	

	@RequestMapping(value="uploadImg")
	@ResponseBody
	public  String  uploadImg(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){

		try {
			
			//1、获取文件储存的地址。
			String pageFileName= imgFile.getOriginalFilename();//.getName() ;
			String nameSuff=".jpg";
			if(StringUtils.isNotBlank(pageFileName)){
				nameSuff=pageFileName.substring(pageFileName.lastIndexOf("."));
			}
			
			String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/

			//获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "information/"+df.format(new Date());
			
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			
			//2、获取文件的新的名称。以时间戳+四位随机数组成
			String fileName =getUUID()+nameSuff;
			String filePath = saveUrl+extendUrl+"/"+fileName;
			File sourceFile= new File(filePath);
			
			//先删除此文件
			FileUtil.deleteFile(sourceFile);
			
			//将上传的文件写到new出来的文件中
			FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);

			return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
	}
	
	
	/**
	 * Method name: setSystem <BR>
	 * Description: 设置门户信息 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("system")
	public String setSystem(HttpServletRequest  request){
		try {
			int id = getCompanyId(request);
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(id);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", bean.getId());
			request.setAttribute("usedAccountCount", manageUserService.findUserByCondition(param).size());
			request.setAttribute("company", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "information/system";
	}
	
	/**
	 * Method name:保存关于我们的详细信息
	 * Description:  
	 * Remark: <BR>
	 * @param request
	 * @param type
	 * @param info_desc  void<BR>
	 */
	@RequestMapping(value ="saveAboutUsInfo")
	@ResponseBody
	public String saveAboutUsInfo(HttpServletRequest request,InfoAboutUsBean infoAboutUsBean){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:saveAboutUsInfo" );
		//1、获取当前用户id
		
		try {
			infoAboutUsBean.setUserId(getUserId(request));
			infoAboutUsBean.setCompanyId(getCompanyId(request));
			informationManageService.saveAboutUsInfo(infoAboutUsBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e+"param:"+infoAboutUsBean.toString());
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	
	/**
	 * Method name: 修改时，将获取的文本信息加载传输到页面
	 * Description: editAboutUsInfo <BR>
	 * Remark: <BR>
	 * @param response
	 * @param request
	 * @param type  void<BR>
	 */
	@RequestMapping(value = "editAboutUsInfo")
	@ResponseBody
	public Object editAboutUsInfo(HttpServletRequest request,int type){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:saveAboutUsInfo param:"+type );
			
		try {
			int companyId = getCompanyId(request);
				
			Object infoDesc =	informationManageService.editAboutUsInfo(type,companyId);
			return infoDesc;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e+"param:"+type);
		}
		return Constant.AJAX_FAIL;
	}
	
	
	/**
	 * Method name: 查询公司信息详情
	 * Description: queryCompanyDetail <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping("queryCompanyDetail")
	public String  queryCompanyDetail(HttpServletRequest request){
		//入口日志
		log.debug("Enter Class:InformationManageAction Method:queryCompanyDetail" );
		try {
			int id = getCompanyId(request);
			ManageCompanyBean bean = manageCompanyService.selectCompanyById(id);
			request.setAttribute("company", JsonUtil.getJson4JavaObject(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information/companyDetail";
		
	}
	
	/**
	 * Method name: getUserBean <BR>
	 * Description: 获取userBean <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  ManageUserBean<BR>
	 */
	private ManageUserBean getUserBean(String userId) throws Exception{
		return 	 manageUserService.findUserById(userId);
	}
	/**
	 * Method name: 获取用户Id
	 * Description: getUserId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  int<BR>
	 */
	private int getUserId (HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute(Constant.SESSION_USERID_LONG);
		if(null!=userId){
			return Integer.valueOf(String.valueOf(userId));
		}
		return 0;
	}
	
	/**
	 * Method name: 获取集团公司id
	 * Description: getCompanyId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  int<BR>
	 */
	private int getCompanyId (HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String userId = String.valueOf(session.getAttribute(Constant.SESSION_USERID_LONG));
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
		return manageUserBean.getCompanyId();
	}
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    }  
	 
	 
	 
}
