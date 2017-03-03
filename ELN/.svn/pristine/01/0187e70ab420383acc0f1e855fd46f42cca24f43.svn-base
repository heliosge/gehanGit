package com.jftt.wifi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.TrainArrangeBean;
import com.jftt.wifi.bean.TrainArrangeContentBean;
import com.jftt.wifi.bean.vo.TrainArrangeUserVo;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {

	private static Configuration configuration = null;
	
	public static Class getClassForStatic() {
		return new Object() {
			public Class getClassForStatic() {
				return this.getClass();
			} 
		}.getClassForStatic();
	}

	public static String createTrainWord(TrainArrangeBean train, List<TrainArrangeUserVo> userList, String place, String courseName) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {

			dataMap.put("train", train);
			dataMap.put("userList", userList);
			dataMap.put("place", place);
			dataMap.put("courseName", courseName);

			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(WordUtil.getClassForStatic(), "/ftl"); // FTL文件所存在的位置
			Template template = configuration.getTemplate("train.ftl");

			// 拿到文件实际存放地址
			String path = PropertyUtil.getConfigureProperties("UPLOAD_PATH");
			String extendUrl = "temp/";
			path += extendUrl;
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			String fileName = System.currentTimeMillis() + ".doc";
			String filePath = path + fileName;
			File outFile = new File(filePath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			template.process(dataMap, out);
			out.close();

			// 虚拟访问路径
			String uploadVirtualPath = PropertyUtil
					.getConfigureProperties("UPLOAD_VIRTUAL_PATH");
			return uploadVirtualPath + "/" + extendUrl + "/" + fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static String createTrainRecordWord(TrainArrangeBean train, TrainArrangeUserVo user, List<TrainArrangeContentBean> content) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {

			dataMap.put("train", train);
			dataMap.put("user", user);
			dataMap.put("content", content);

			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(WordUtil.getClassForStatic(), "/ftl"); // FTL文件所存在的位置
			Template template = configuration.getTemplate("trainRecord.ftl");

			// 拿到文件实际存放地址
			String path = PropertyUtil.getConfigureProperties("UPLOAD_PATH");
			String extendUrl = "temp/";
			path += extendUrl;
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			String fileName = System.currentTimeMillis() + ".doc";
			String filePath = path + fileName;
			File outFile = new File(filePath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			template.process(dataMap, out);
			out.close();

			// 虚拟访问路径
			String uploadVirtualPath = PropertyUtil
					.getConfigureProperties("UPLOAD_VIRTUAL_PATH");
			return uploadVirtualPath + "/" + extendUrl + "/" + fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
