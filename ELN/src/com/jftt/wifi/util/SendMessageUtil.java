/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SendMessageUtil.java
 * Version:  1.00
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-24        | JFTT)He Xiaojun    | original version
 */
package com.jftt.wifi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.jftt.wifi.mail.MailSenderInfo;
import com.jftt.wifi.mail.SimpleMailSender;

/**
 * class name:SendMessageUtil <BR>
 * class description: 提供发送E-mail和短信验证码的功能 <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月24日
 * @author JFTT)He Xiaojun
 */
public class SendMessageUtil {

	private static Logger log = Logger.getLogger(SendMessageUtil.class); 
	/**
	 * Method name: sendEmail <BR>
	 * Description: 提供发送E-mail的功能<BR>
	 * Remark: <BR>
	 * @param toAddress 收件方的地址
	 * @param title 邮件的标题
	 * @param content 邮件正文的内容
	 * @return  boolean 发送结果 true:发送成功,false:发送失败<BR>
	 */
	public static boolean sendEmail(String toAddress, String title, String content) {
		// 设置邮件相关属性
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		mailInfo.setMailServerHost(PropertyUtil.getConfigureProperties("MailServerHost"));
		mailInfo.setMailServerPort(PropertyUtil.getConfigureProperties("MailServerPort"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(PropertyUtil.getConfigureProperties("MailUserName"));
		mailInfo.setPassword(PropertyUtil.getConfigureProperties("MailPwd"));// 您的邮箱密码
		mailInfo.setFromAddress(PropertyUtil.getConfigureProperties("MailFromAddress"));
		mailInfo.setToAddress(toAddress);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		log.info("toMailAddress:" + toAddress + "  title:" + title + "  content:" +  content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		//return sms.sendTextMail(mailInfo);// 发送文体格式
		return sms.sendHtmlMail(mailInfo);//发送html格式
	}
	
	public static final String sendSMS_URL = "http://m.5c.com.cn/api/send/index.php?format=json&data=JSONSTR";
	
	/**
	 * Method name: sendSMS<BR>
	 * Description: 发送短消息 <BR>
	 * Remark: <BR>
	 * @param contentStr 发送内容
	 * @param dstStr 接收方手机号
	 * @return  boolean 发送结果 true:发送成功,false:发送失败<BR>
	 */
	public static boolean sendSMS(String contentStr, String dstStr) {
		String contentEnc = "";
		try {
			contentEnc = URLEncoder.encode(contentStr + "【安培在线】", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e, e);
		}
		
		JSONObject obj = new JSONObject();
		obj.element("username", "pulian");
		obj.element("password", "asdf1234");
		obj.element("apikey", "3b29ed73bc8adb6cd6027f62bdb3a506");
		obj.element("mobile", dstStr);
		obj.element("content", contentEnc);
		
		String requestUrl = sendSMS_URL.replace("JSONSTR", obj.toString());
		
		log.info("phone:" + dstStr + "  content:" + contentStr);
		JSONObject jsonObject = httpRequest(requestUrl);
//		返回值											                                 说明
//		{"status":"success","msg":"MSGID"}	                          提交成功
//		{"status":"error","msg":"MSGID"}		                          提交失败
//		{"status":"error","msg":"Missing username"}		          用户名为空
//		{"status":"error","msg":"Missing password"}		          密码为空
//		{"status":"error","msg":"Missing apikey"}		              APIKEY为空
//		{"status":"error","msg":"Missing recipient "}		          手机号码为空
//		{"status":"error","msg":"Missing content "}		              短信内容为空
//		{"status":"error","msg":"Account is blocked "}		      帐号被禁用
//		{"status":"error","msg":" Unrecognized encoding "}	  编码未能识别
//		{"status":"error","msg":"APIKEY or password error "}	  APIKEY或密码错误
//		{"status":"error","msg":" Unauthorized IP address "}	  未授权 IP 地址
//		{"status":"error","msg":" Account balance is insufficient "}		余额不足
//		{"status":"error","msg":" Black keywords is:党中央 "}        屏蔽词
		
		log.info("phone:" + dstStr + "  content:" + contentStr + "  result:" +  jsonObject.toString());
		
		if(jsonObject.getString("status").equals("success")){
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	/**
	 * Method name: httpRequest <BR>
	 * Description: http接口请求 <BR>
	 * Remark: <BR>
	 * @param requestUrl 接口url
	 * @return  JSONObject 接口返回json数据结构<BR>
	 */
	public static JSONObject httpRequest(String requestUrl) {
		JSONObject jsonObject = null;
		try {
			// 创建连接
			//String strURL= URLEncoder.encode(requestUrl, "utf-8");
			//System.out.println("strURL:" + strURL);
			URL url = new URL(requestUrl);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			// connection.setInstanceFollowRedirects(true);
			// connection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			connection.connect();

			// POST请求
			// DataOutputStream out = new DataOutputStream(
			// connection.getOutputStream());
			// JSONObject obj = new JSONObject();
			// obj.element("usercode", "00000000");
			// obj.element("username", "微信");
			// obj.element("makecom", "32050000");
			// obj.element("mobiles", "15150429654");
			// obj.element("content", "现在是测试，hello world!");
			// System.out.println(ADD_URL.getBytes("UTF-8"));
			// out.write(obj.toString().getBytes("UTF-8"));
			// out.flush();
			// out.close();

			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			// 读取响应
			// BufferedReader reader = new BufferedReader(new InputStreamReader(
			// connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = bufferedReader.readLine()) != null) {
				lines = new String(lines.getBytes());
				sb.append(lines);
			}
			bufferedReader.close();
			// 断开连接
			connection.disconnect();

			jsonObject = JSONObject.fromObject(sb.toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		}
		return jsonObject;

	}
	
	
	public static final String getExpress_URL = "http://apis.baidu.com/ppsuda/waybillnoquery/waybillnotrace?expresscode=COMPANY&billno=CODESTR";
	
	/**
	 * Method name: getExpressInfo <BR>
	 * Description: 获取快递信息 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param code
	 * @return  JSONObject<BR>
	 */
	public static JSONObject getExpressInfo(String companyId, String code){
		String requestUrl = getExpress_URL.replace("COMPANY",companyId).replace("CODESTR", code);
		JSONObject jsonObject = httpRequest_Get(requestUrl);
		return jsonObject;
	}
	
	/**
	 * Method name: httpRequest_Get <BR>
	 * Description: http接口get请求 <BR>
	 * Remark: <BR>
	 * @param requestUrl 接口url
	 * @return  JSONObject 接口返回json数据结构<BR>
	 */
	public static JSONObject httpRequest_Get(String requestUrl) {
		JSONObject jsonObject = null;
		try {
			// 创建连接
			//String strURL= URLEncoder.encode(requestUrl, "utf-8");
			//System.out.println("strURL:" + strURL);
			URL url = new URL(requestUrl);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			//connection.setDoOutput(true);
			//connection.setDoInput(true);
			connection.setRequestMethod("GET");
			//connection.setUseCaches(false);
			connection.setRequestProperty("apikey",  "c4ffe52c59e563992f848d46b1236058");
			// connection.setInstanceFollowRedirects(true);
			// connection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			connection.connect();

			// POST请求
			// DataOutputStream out = new DataOutputStream(
			// connection.getOutputStream());
			// JSONObject obj = new JSONObject();
			// obj.element("usercode", "00000000");
			// obj.element("username", "微信");
			// obj.element("makecom", "32050000");
			// obj.element("mobiles", "15150429654");
			// obj.element("content", "现在是测试，hello world!");
			// System.out.println(ADD_URL.getBytes("UTF-8"));
			// out.write(obj.toString().getBytes("UTF-8"));
			// out.flush();
			// out.close();

			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			// 读取响应
			// BufferedReader reader = new BufferedReader(new InputStreamReader(
			// connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = bufferedReader.readLine()) != null) {
				lines = new String(lines.getBytes());
				sb.append(lines);
			}
			bufferedReader.close();
			// 断开连接
			connection.disconnect();

			jsonObject = JSONObject.fromObject(sb.toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
		}
		return jsonObject;

	}
	

	public static void main(String[] args) {
		//sendEmail("chinesehxj@163.com","我用企业邮箱发的","我发成功了吗，成功了的话请回复");
		//sendSMS("我是用程序发的哦，收到了吗？呵呵","18021240754");
		System.out.println(getExpressInfo("ST", "3303261688487"));
	}

}
