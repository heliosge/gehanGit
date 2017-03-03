/*
* All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/06        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.OfflineTestBean;
import com.jftt.wifi.bean.OfflineTestResultsBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.OfflineTestResultsService;
import com.jftt.wifi.service.OfflineTestService;
import com.jftt.wifi.util.ConvertUtil;
import com.jftt.wifi.util.FileUtil;
import com.jftt.wifi.util.PropertyUtil;
import com.sun.jndi.toolkit.url.UrlUtil;


/**
 * 线下考试
 */
@Controller
@RequestMapping(value = "offlineTest")
public class OfflineTestAction {
	private static final Logger LOG = Logger.getLogger(OfflineTestAction.class);
	
	@Resource(name="offlineTestService")
	private OfflineTestService offlineTestService;
	
	@Resource(name="offlineTestResultsService")
	private OfflineTestResultsService offlineTestResultsService;

	/**
	 * 跳转到线下考试列表页面
	 */
	@RequestMapping(value="testList")
	public String testList(HttpServletRequest request) {
		
		return "offlineTest/test_list";
	}
	
	/**
	 * 跳转到选择人员页面
	 */
	@RequestMapping(value="chooseUser")
	public String chooseUser(HttpServletRequest request) {
		
		return "offlineTest/chooseUser";
	}
	
	/**
	 * 跳转到线下考试  录入考试页面
	 */
	@RequestMapping(value="testAddPage")
	public String testAddPage(HttpServletRequest request, String id) {
		
		if(id !=null && !id.equals("")){
			//修改
			//request.setAttribute("id", id);
			
			long testId = Long.parseLong(id);
			OfflineTestBean testBean = offlineTestService.getOfflineTestById(testId);
			List<OfflineTestResultsBean> resultList = offlineTestResultsService.getOfflineTestResultsByTestId(testId);
			testBean.setResultList(resultList);
			
			request.setAttribute("offlineTestBean", testBean);
		}
		
		return "offlineTest/test_add";
	}
	
	/**
	 * 获取考试数据
	 */
	@RequestMapping(value="getOfflineTestById")
	@ResponseBody
	public Object getOfflineTestById(HttpServletRequest request, long id) {
		
		OfflineTestBean testBean = offlineTestService.getOfflineTestById(id);
		
		List<OfflineTestResultsBean> resultList = offlineTestResultsService.getOfflineTestResultsByTestId(id);
		
		testBean.setResultList(resultList);
		
		return testBean;
	}
	
	/**
	 * 获取考试数据
	 */
	@RequestMapping(value="getOfflineTest")
	@ResponseBody
	public Object getOfflineTest(HttpServletRequest request, int page, OfflineTestBean offlineTest) {
		
		offlineTest.setIs_delete(0);
		
		//子公司
		ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
		offlineTest.setSub_company_id(userInfo.getSubCompanyId());
		
		int size = offlineTestService.getOfflineTestNum(offlineTest);
		
		//分页
		int from = (page-1)*offlineTest.getPageSize();
		offlineTest.setFromNum(from);
		
		List<OfflineTestBean> rows = offlineTestService.getOfflineTest(offlineTest);
		
		MMGridPageVoBean<OfflineTestBean> vo = new MMGridPageVoBean<OfflineTestBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	/**
	 * 增加
	 */
	@RequestMapping(value="testAdd")
	@ResponseBody
	public String testAdd(HttpServletRequest request, OfflineTestBean offlineTest) {
		
		try {
			
			//子公司
			ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			offlineTest.setSub_company_id(userInfo.getSubCompanyId());
			
			offlineTestService.addOfflineTest(offlineTest);
			return "SUCCESS";
		} catch (Exception e) {
			
			return "ERROR";
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="testEdit")
	@ResponseBody
	public String testEdit(HttpServletRequest request, OfflineTestBean offlineTest) {
		
		try {
			offlineTestService.updateOfflineTest(offlineTest);
			return "SUCCESS";
		} catch (Exception e) {
			
			return "ERROR";
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="testDelete")
	@ResponseBody
	public String testDelete(HttpServletRequest request, long[] id) {
		
		try {
			
			offlineTestService.deleteOfflineTest(id);
			
			return "SUCCESS";
		} catch (Exception e) {
			
			return "ERROR";
		}
	}
	
	/**
	 * 跳转到线下考试结果列表页面
	 */
	@RequestMapping(value="testResultList")
	public String testResultList(HttpServletRequest request, long id) {
		
		OfflineTestBean testBean = offlineTestService.getOfflineTestById(id);
		
		request.setAttribute("offlineTestBean", testBean);
		
		return "offlineTest/test_result_list";
	}
	
	/**
	 * 获取考试结果数据
	 */
	@RequestMapping(value="getOfflineTestResult")
	@ResponseBody
	public Object getOfflineTestResult(HttpServletRequest request, int page, OfflineTestResultsBean resultsBean) {
		
		resultsBean.setIs_delete(0);
		
		int size = offlineTestResultsService.getOfflineTestResultsNum(resultsBean);
		
		//分页
		int from = (page-1)*resultsBean.getPageSize();
		resultsBean.setFromNum(from);
		
		List<OfflineTestResultsBean> rows = offlineTestResultsService.getOfflineTestResults(resultsBean);
		
		MMGridPageVoBean<OfflineTestResultsBean> vo = new MMGridPageVoBean<OfflineTestResultsBean>();
		vo.setTotal(size);
		vo.setRows(rows);
		
		return vo;
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="exportTest")
	public void exportTest(HttpServletRequest request, HttpServletResponse response, long id) {
		
		OfflineTestBean testBean = offlineTestService.getOfflineTestById(id);
		
		Workbook wb = offlineTestService.exportOfflineTest(testBean);
		
		try {
	      	  
            String fileName = testBean.getName() + ".xlsx";  
              
            // 设置下载文件类型
            response.setContentType("application/octet-stream;charset=UTF-8");
            // 设置下载文件头
            response.setHeader("Content-Disposition", "attachment; filename=" + UrlUtil.encode(fileName, "UTF-8"));
            // 定义下载文件流
            //ServletOutputStream out = response.getOutputStream();
            
            wb.write(response.getOutputStream()); // 输出流控制workbook  
            response.getOutputStream().flush();  
            response.getOutputStream().close();  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入excel
	 */
	@RequestMapping(value="addByExcel")
	@ResponseBody
	public String addByExcel(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
		
		try {
			// 文件名
			String pageFileName = file.getOriginalFilename();
			Assert.hasText(pageFileName);
			// 获取文件后缀名
			String fileExt = pageFileName.substring(pageFileName.lastIndexOf("."));
			Assert.hasText(fileExt);

			String basePath = PropertyUtil.getConfigureProperties("UPLOAD_PATH");// 拿到实际存放地址。

			// 获取拼接地址
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extendUrl = "offline_test/" + df.format(new Date());

			File saveFolder = new File(basePath + extendUrl);
			if (!saveFolder.exists()) {
				saveFolder.mkdirs();
			}

			// 获取文件的新的名称。使用UUID作为新文件名
			String relativeFilePath = extendUrl + "/" + UUID.randomUUID().toString() + fileExt;
			String physicalFilePath = basePath + relativeFilePath;

			// 将上传的文件写到new出来的文件中
			FileUtil.SaveFileFromInputStream(file.getInputStream(), physicalFilePath);
			
			//子公司
			ManageUserBean userInfo = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);

			//写入数据库
			offlineTestService.addByExcel(physicalFilePath, userInfo.getSubCompanyId());
			
			return "SUCCESS";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}
}
