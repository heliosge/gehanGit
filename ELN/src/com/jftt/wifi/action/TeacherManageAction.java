/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: IntegralManageAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月17日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.TeacherBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.TeacherManageService;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.jftt.wifi.util.XlsParserUtil;

/**
 * class name:讲师管理action类
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月23日
 * @author JFTT)liucaibao
 */
@Controller
@RequestMapping(value="teacher")
public class TeacherManageAction {
	
	private static Logger log = Logger.getLogger(TeacherManageAction.class); 
	
	
	@Resource(name="teacherManageService")
	private TeacherManageService teacherManageService;
	
	@Autowired
	private ManageUserService manageUserService;

	private List<TeacherBean> listTeacherBean ;
	//定义上传的文件最大不允许超过2万行
	private static final int maxNum = 20000;
	
	private TeacherBean teacherBean;
	
	/**
	 * Method name: 获取讲师列表页面
	 * Description: listTeacher <BR>
	 * Remark: <BR>
	 * @param request
	 * @return  String<BR>
	 */
	@RequestMapping(value="list")
	public String listTeacher(HttpServletRequest request){
		log.debug("类：TeacherManageAction，method:listTeacher 59行");
		return "teacher/listTeacher"; //"teacher/listTeacher";
	}
	
	
	@RequestMapping(value="getTeacherByMap")
	@ResponseBody
	public Object getTeacherByMap(HttpServletRequest request, int page, int pageSize, String userName, String teacherName, String teacherCategory){
		log.debug("Enter class：TeacherManageAction，method:getTeacherByMap   param:"+teacherName);
		//int userId = getUserId(request);//request.getParameter("userId");
		//组织参数
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("teacherName", teacherName);
		if(!"".equals(teacherCategory)){
			map.put("teacherCategory", teacherCategory);
		}
		int size = 0;
		try {
			map.put("companyId", getCompanyId(request));
			size = teacherManageService.queryListTeacherCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TeacherBean> rows = null;
		try {
			map.put("fromNum", (page-1)*pageSize);
			
		map.put("pageSize", pageSize);
			rows = teacherManageService.listTeacher(map);//manageUserService.findUserByCondition(map,page,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MMGridPageVoBean<TeacherBean> vo = new MMGridPageVoBean<TeacherBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	/**
	 * Method name: 获取讲师详情
	 * Description: detailTeacher <BR>
	 * Remark: <BR>
	 * @param request
	 * @param teacherId
	 * @return  String<BR>
	 */
	@RequestMapping(value="detail")
	public String detailTeacher(HttpServletRequest request,int teacherId,String mode){
		TeacherBean 	teacherBean = new TeacherBean();
		try {
			teacherBean = teacherManageService.detailTeacher(teacherId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		request.setAttribute("teacherInfo",JsonUtil.getJson4JavaObject(teacherBean) );
		if(mode.equals("detail")){
			
			return "teacher/detailTeacher";
		}else{
			return "teacher/editTeacher";
		}
	}
	/**
	 * Method name: 添加讲师
	 * Description: addTeacherInfo <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping(value="add")
	public String addTeacherInfo(){
		return "teacher/addTeacherInfo";
	}
	
	@RequestMapping(value="listUser")
	public String listUser(){
		return "teacher/listUser";
	}
	
	
	/**
	 * Method name:获取公司的个人列表
	 * Description: getUserList <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	public Object getUserList(HttpServletRequest request,String userName,String teacherName,int page, int pageSize){
		
		log.debug("Enter Class:ApproveManageAction Method:getUserList ");
		 

		//组织参数
		Map<String, Object>  param = new HashMap<String, Object>();
		int size = 0;
		try {
			param.put("companyId",getCompanyId(request));
			param.put("subCompanyId",getSubCompanyId(request));
			param.put("userName", userName);
			param.put("name", teacherName);
 			//查询总数
			size = manageUserService.findUserCountByCondition(param);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				log.error(e+"查询列表总数失败。公司id为:"+getCompanyId(request));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		List<ManageUserBean> rows = null;
		try {
			 
			rows = manageUserService.findUserByCondition(param, page, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e+"查询列表失败。公司Id："+param.get("companyId"));
		}
		
		MMGridPageVoBean<ManageUserBean> vo = new MMGridPageVoBean<ManageUserBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	@RequestMapping("queryPostName")
	@ResponseBody
	public String getPostById(HttpServletRequest request,String id){
		
		return teacherManageService.getPostById(id);
	}
	
	@RequestMapping("queryDeptName")
	@ResponseBody
	public String getDeptNameById(HttpServletRequest request,String id){
		
		return teacherManageService.getDeptNameById(id);
	}
	
	@RequestMapping("getUserInfo")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request,String id,ModelMap model){
		ManageUserBean userBean = new ManageUserBean();
		try {
			userBean	= manageUserService.findUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtil.getJson4JavaObject(userBean).toString();
	}
	
	/**
	 * Method name: 保存讲师信息
	 * Description: saveTeacherInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @param teacherBean  void<BR>
	 */
	@RequestMapping(value="save")
	@ResponseBody
	public String saveTeacherInfo(HttpServletRequest request,TeacherBean teacherBean){
	
		//入口日志
		log.debug("Enter Class:TeacherManageAction Method:saveTeacherInfo param:"+teacherBean.toString());
		int userId = getUserId(request);
		try {
			/*if(null!=imgFile&&!imgFile.isEmpty()){
				teacherBean.setIconPath(uploadImg(request,imgFile));
			}*/
			teacherBean.setUserId(userId);
			teacherBean.setCompanyId(getCompanyId(request));
			teacherManageService.saveTeacherInfo(teacherBean);
		} catch (Exception e) {
			System.out.println(e);
			log.error("保存讲师信息失败："+e+teacherBean.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constant.AJAX_FAIL;
		}
		return Constant.AJAX_SUCCESS;
	}
	
	/**
	 * Method name: checkUserName <BR>
	 * Description: 校验是否重名 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param userName
	 * @return  String<BR>
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public String checkUserName(HttpServletRequest request,String userName){
		
		
		try {
			Map<String ,Object> param = new HashMap<String, Object>();
			param.put("userName", userName);
			param.put("companyId",getCompanyId(request));
			
			int size = teacherManageService.queryUserCount(param);
			if(size==0){
				
				return Constant.AJAX_SUCCESS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constant.AJAX_SUCCESS;
		}
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: updateTeacherInfo <BR>
	 * Description: updateTeacherInfo <BR>
	 * Remark: <BR>
	 * @param imgFile
	 * @param request
	 * @param teacherBean
	 * @return  String<BR>
	 */
	/**
	 * Method name: updateTeacherInfo <BR>
	 * Description: updateTeacherInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @param teacherBean
	 * @return  String<BR>
	 */
	@RequestMapping("update")
	@ResponseBody
	public String updateTeacherInfo(HttpServletRequest request,TeacherBean teacherBean){
		try {
			teacherBean.setUserId(getUserId(request));
			teacherManageService.updateTeacherInfo(teacherBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAIL";
		}
		
		return "SUCCESS";
	}
	
	/**
	 * Method name:删除讲师信息
	 * Description: deleteTeacherInfo <BR>
	 * Remark: <BR>
	 * @param request  void<BR>
	 */
	@RequestMapping(value="deleteTeacherInfo")
	@ResponseBody
	public String   deleteTeacherInfo(HttpServletRequest request,int teacherId){
		
		log.debug("删除讲师信息:  删除讲师的id:"+teacherId);
		try {
			int userId = getUserId(request);
			teacherManageService.deleteTeacherInfo(userId,teacherId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug(e);
			return "FAIL";
		}
		return "SUCCESS";
	}
	
	/**
	 * Method name: 批量删除讲师信息
	 * Description: deleteBatchTeacherInfo <BR>
	 * Remark: <BR>
	 * @param request
	 * @param ids  void<BR>
	 */
	@RequestMapping(value="deleteBatch")
	@ResponseBody
	public String  deleteBatchTeacherInfo(HttpServletRequest request,String ids){
		int userId = getUserId(request);
		try{
		if(!ids.isEmpty()){
			List<String> idArr =  Arrays.asList(ids.split("\\|"));
			for(int i=0;i<idArr.size();i++){
				if(StringUtils.isBlank(idArr.get(i)))
					continue;
					teacherManageService.deleteTeacherInfo(userId,Integer.valueOf(idArr.get(i)));
			}
		}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Constant.AJAX_FAIL;
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
			String extendUrl = "pic/upload/"+df.format(new Date());
			
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
	@RequestMapping("addFile")
	public String addFile(HttpServletRequest request){
		return "teacher/importFile";
	}
	
	/**
	 * Method name: 解析文件数据
	 * Description: importXlsFileData <BR>
	 * Remark: <BR>  void<BR>
	 */
	@RequestMapping(value="importXlsFileData")
	@ResponseBody
	public Object  importXlsFileData(HttpServletRequest request,@RequestParam(value="file", required = false)MultipartFile imgFile){
		
		//1、获取文件储存的地址。
		String pageFileName= imgFile.getOriginalFilename();//.getName() ;
		String filetype = pageFileName.split("\\.")[1];
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/

		//获取拼接地址
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extendUrl = "teacher/template/"+df.format(new Date());
		
		JSONObject reObj = new JSONObject();
		try {
			File file = new File(saveUrl+extendUrl);
			if(!file.exists()){
				file.mkdirs();
			}
			//将上传的文件写到new出来的文件中
			String 	filePath =saveUrl +extendUrl+"/"+getUUID()+pageFileName;
			FileUtil.SaveFileFromInputStream(imgFile.getInputStream(), filePath);
			
			int maxNum = XlsParserUtil.getXLSNum(filePath,filetype);
			if(maxNum>maxNum){
				reObj.put("errCode", "10");
			}
			else{
			
			JSONArray array = new JSONArray();
				//根据文件类型，去解析
				 if("xls".equals(filetype)){
					array = XlsParserUtil.importXlsParser(filePath,"utf-8",1,1,0);
				}else if("xlsx".equals(filetype)){
					array = XlsParserUtil.importXlsxParser(filePath,"utf-8",1,1,0);
				}else{
					
				}
				 //此处缺少校验和异常处理
				 if(array.size()==0||array.size()==1){
					 reObj.put("errCode", "-1");

				 }else{
					 StringBuffer errCode = new StringBuffer();
					 for(int i=1;i<array.size();i++){
						 JSONArray sArr = array.getJSONArray(i);
						 int len = sArr.size();
						 if(!isEmptyArr(sArr)){
							 TeacherBean teacherBean = new TeacherBean();
							 
							 teacherBean.setUserId(getUserId(request));
							 teacherBean.setCompanyId(getCompanyId(request));
							 if("内部讲师".equals(sArr.getString(0))){
								 teacherBean.setTeacherCategory(1);
							 }else{
								 teacherBean.setTeacherCategory(2);
								 
							 }
							 String re = checkUserName(request, sArr.getString(1));
							 if(!Constant.AJAX_SUCCESS.equals(re)){
								 errCode.append("第"+i+"行用户名存在重复，不能导入!</br>");
								 continue;
							 }
							 
							 if(!isstring(sArr.getString(1))){
								 errCode.append("第"+i+"行用户名为非数字字母下划线组成，不能导入!</br>");
								 continue;
							 }
							
							 if(len > 1){
								 teacherBean.setUserName(sArr.getString(1));//第一位塞入用户名
							 }else{
								 teacherBean.setUserName("");
							 }
							 if(len > 2){
								 teacherBean.setTeacherName(sArr.getString(2));//
							 }else{
								 teacherBean.setTeacherName("");
							 }
							 
							 if(len > 3){
								 if("男".equals(sArr.getString(3))){
									 teacherBean.setSex(1);
								 }else if("女".equals(sArr.getString(3))) {
									 teacherBean.setSex(2);
								 }else{
									 errCode.append("第"+i+"行性别录入有误，不能导入！</br>");
									 continue;
								 }
							 }else{
								 errCode.append("第"+i+"行性别录入有误，不能导入！</br>");
								 continue;
							 }
							
							 if(len > 4){
								 if(!isEmail(sArr.getString(4))){
									 errCode.append("第"+i+"行邮箱录入有误，不能导入！</br>");
									 continue;
								 }
								 teacherBean.seteMail(sArr.getString(4));
							 }else{
								 teacherBean.seteMail("");
							 }
							 if(len > 5){
								 teacherBean.setCardId( sArr.getString(5));
							 }else{
								 teacherBean.setCardId("");
							 }
							 if(len > 6){
								 teacherBean.setEducation(sArr.getString(6));
							 }else{
								 teacherBean.setEducation("");
							 }
							 if(len > 7){
								 
								 if(!isMobile(sArr.getString(7))){
									 errCode.append("第"+i+"行电话号码录入有误，不能导入！</br>");
									 continue;
								 }
								 teacherBean.setPhoneNum(sArr.getString(7));
							 }else{
								 teacherBean.setPhoneNum("");
							 }
							 if(len > 8){
								 teacherBean.setIconPath(sArr.getString(8));
							 }else{
								 teacherBean.setIconPath("");
							 }
							 if(len > 9){
								 teacherBean.setDescription(sArr.getString(9));
							 }else{
								 teacherBean.setDescription("");
							 }
							 
							 teacherManageService.saveTeacherInfo(teacherBean);
						 }
					 }
					 if(errCode.toString().isEmpty()){
						 reObj.put("errCode", "00");
					 }else{
						 reObj.put("errCode", errCode.toString());
					 }
				 }
			}
		}catch(Exception e){
			
			System.out.println(e);
			reObj.put("errCode", "-1");
			return reObj;
		}
		return reObj;
	}
	private boolean isEmptyArr(JSONArray array){
		if(array.isEmpty()){
			return true;
		}else{
			for(int i=0;i<array.size();i++){
				if(!(array.getString(i) == null || "".equals(array.getString(i)))){
					return false;
				}
			}
		}
		return true;
	}
	
	 public Boolean isstring(String str){
		  
		  Boolean bl = false;
		  //首先,使用Pattern解释要使用的正则表达式，其中^表是字符串的开始，$表示字符串的结尾。
		  Pattern pt = Pattern.compile("^[0-9a-zA-Z_]+$");
		  //然后使用Matcher来对比目标字符串与上面解释得结果
		  Matcher mt = pt.matcher(str);
		  //如果能够匹配则返回true。实际上还有一种方法mt.find()，某些时候，可能不是比对单一的一个字符串，
		  //可能是一组，那如果只要求其中一个字符串符合要求就可以用find方法了.
		  if(mt.matches()){
		   bl = true;
		  }
		  return bl;
		 }
	
	public  boolean isEmail(String email){     
	     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	        Pattern p = Pattern.compile(str);     
	        Matcher m = p.matcher(email);     
	        return m.matches();     
	    } 
	 public boolean isMobile(String str) {   
	        Pattern p = null;  
	        Matcher m = null;  
	        boolean b = false;   
	        p = Pattern.compile("^[1][1-9][0-9]{9}$"); // 验证手机号  
	        m = p.matcher(str);  
	        b = m.matches();   
	        return b;  
	    }  
	/**
	 * Method name: downloadTemplate <BR>
	 * Description: 下载模板 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param response
	 * @param path
	 * @throws Exception  void<BR>
	 */
	@RequestMapping(value="downloadTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String basePath = request.getSession().getServletContext().getRealPath("/");
		String path =basePath +request.getParameter("path");
		
		FileUtil.writeFile(response, String.valueOf(request.getParameter("fileName")), path, false);
	}
	
	
	
	
	
	private  String getImpFilePath(HttpServletRequest request,String suffix){
		String basePath = request.getSession().getServletContext().getRealPath("/");
		//返回路径，为contextPath+/js/upload/exp_m/reportExpTemp_+sessionId+.xml
		return basePath+"upload/imp_m/"+request.getSession().getId()+suffix;
	}
	
	/**
	 * Method name: 获取随机数
	 * Description: getRandonData <BR>
	 * Remark: <BR>
	 * @param index
	 * @return  long<BR>
	 */
	private  long getRandonData(Integer index){
		
		long randomData=Math.round(Math.random()*Math.pow(10, index));
		while(new Long(randomData).toString().length()<4){
			randomData=Math.round(Math.random()*Math.pow(10, index));
		}
		return randomData;
	}
	
	 private  String getUUID() {  
	        UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        return str;
	    }  
	public List<TeacherBean> getListTeacherBean() {
		return listTeacherBean;
	}


	public void setListTeacherBean(List<TeacherBean> listTeacherBean) {
		this.listTeacherBean = listTeacherBean;
	}
	public TeacherBean getTeacherBean() {
		return teacherBean;
	}
	public void setTeacherBean(TeacherBean teacherBean) {
		this.teacherBean = teacherBean;
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
	
	/**
	 * Method name: 获取子公司id
	 * Description: getSubCompanyId <BR>
	 * Remark: <BR>
	 * @param request
	 * @return
	 * @throws Exception  int<BR>
	 */
	private int getSubCompanyId (HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		String userId = String.valueOf(session.getAttribute(Constant.SESSION_USERID_LONG));
		ManageUserBean manageUserBean = manageUserService.findUserById(userId);//拿到用户对象
		return manageUserBean.getCompanyId();
	}
}
