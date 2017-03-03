/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName: FileAction.java
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |	2014年8月4日		|	JFTT)CaiYicheng	|	original version
 */

/*

                  |
              \       /
                .---. 
           '-.  |   |  .-'
             ___|   |___
        -=  [  NO BUG!  ]  =-
            `---.   .---' 
         __||__ |   | __||__
         '-..-' |   | '-..-'
           ||   |   |   ||
           ||_.-|   |-,_||
         .-"`   `"`'`   `"-.
       .'                   '.
       
 */

package com.jftt.wifi.action;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jftt.wifi.util.PropertyUtil;

import net.sf.json.JSONObject;



/**
 * class name:FileAction <BR>
 * class description: 文件上传，删除，管理后台Action <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2014年8月4日
 * @author JFTT)CaiYicheng
 */
@Controller
@RequestMapping("file")
public class FileAction  {

	private static final long serialVersionUID = 3083456352331699082L;
	//private File uploadFile;// 上传File
	//private String projectPath = ReadProperties.getValue("ImgPath");// 项目实际路径，在info.properties中设置

	/**
	 * 
	 * Author:CaiYicheng <BR>
	 * Date2014年8月4日 <BR>
	 * Method name: uploadImg <BR>
	 * Description: 处理上传文件并通过PrintWriter返回结果 <BR>
	 * 
	 * @return void <BR>
	 */
	@RequestMapping("uploadImg")
	@ResponseBody
	public String uploadImg(HttpServletRequest request,@RequestParam(value="uploadFile", required = false)MultipartFile uploadFile) {

		String path = request.getParameter("path");
	//	String Path=ReadProperties.getValue(Type);// 文件存放文件夹
		String uploadDB = request.getParameter("DB");// 是否更新数据库
		String saveUrl = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
		
		//获取拼接地址
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extendUrl ="";
		if(StringUtils.isNotBlank(path)){
			 extendUrl = path+"/"+df.format(new Date());
		}else{
			 extendUrl = "kindEditor/"+df.format(new Date());
		}
		
		try {
			//获取类型
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			
			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

			//最大文件大小
			long maxSize = 104857600;//默认给为100M
			
			
			//检查目录写权限
			//检查目录
			File uploadDir = new File(saveUrl);
			if(!uploadDir.canWrite()){
				return	getError("上传目录没有写权限。");
			}
			
			//检查扩展名
			String fileName = uploadFile.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
			}
			
			//检查文件的大小
			if(uploadFile.getSize() > maxSize){
				return getError("上传文件大小超过限制。不允许大于100M");
			}
			
			//文件上传处理
			String result = "3";
			saveUrl +=extendUrl;
			result = UploadFileExec(uploadFile,getFileType(uploadFile.getOriginalFilename()),
					uploadDB,saveUrl);


			JSONObject obj = new JSONObject();
			int errortype = 0;
			if (result.length() == 1)
				errortype = result.charAt(0) - 48;

			System.out.println(errortype);
			obj.put("error", errortype);
			obj.put("filename", result);
			obj.put("url", PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+result);
			return  obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return getError("上传文件失败，请重新上传。");
		}
	}


	/**
	 * 
	 * Author:CaiYicheng <BR>
	 * Date2014年8月4日 <BR>
	 * Method name: filemanageJson <BR>
	 * Description: 获取某个目录下的文件信息，富文本编辑器文件管理功能后台执行函数<BR>
	 * 
	 * @return void <BR>
	 */
//	public void filemanageJson() {
//		try {
//			System.out.println("开始执行文件管理Action");
//			HttpServletResponse response = ServletActionContext.getResponse();
//			HttpServletRequest request = ServletActionContext.getRequest();
//			// 根据path参数，设置各路径和URL
//			String path = request.getParameter("path") != null ? request
//					.getParameter("path") : "";
//			// 根目录实际物理路径
//
//			String rootPath = projectPath;
//
//			// 根目录URL
//			String rootUrl = "/";
//
//			// 图片扩展名
//			String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png",
//					"bmp" };
//
//			String dirName = request.getParameter("dir");
//			if (dirName != null) {
//				/*
//				 * "media", "file"}).contains(dirName)){
//				 * out.println("Invalid Directory name."
//				 * );//!!!!!!!!!!!!!!!!!!!!!!! return; }
//				 */
//				rootPath += dirName + "\\";
//				rootUrl += dirName + "/";
//				System.out.println("rootPath:" + rootPath);
//				System.out.println("rootUrl:" + rootUrl);
//				File saveDirFile = new File(rootPath);
//				if (!saveDirFile.exists()) {
//					saveDirFile.mkdirs();
//				}
//			}
//
//			String currentPath = rootPath + path;
//			String currentUrl = rootUrl + path;
//			String currentDirPath = path;
//			String moveupDirPath = "";
//			if (!"".equals(path)) {
//				String str = currentDirPath.substring(0,
//						currentDirPath.length() - 1);
//				moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
//						str.lastIndexOf("/") + 1) : "";
//			}
//
//			// 排序形式，name or size or type
//			String order = request.getParameter("order") != null ? request
//					.getParameter("order").toLowerCase() : "name";
//
//			// 不允许使用..移动到上一级目录
//			if (path.indexOf("..") >= 0) {
//				throw new Exception("Access is not allowed.");
//			}
//			// 最后一个字符不是/
//			if (!"".equals(path) && !path.endsWith("/")) {
//				throw new Exception("Parameter is not valid.");
//			}
//			// 目录不存在或不是目录
//			File currentPathFile = new File(currentPath);
//			if (!currentPathFile.isDirectory()) {
//				throw new Exception("Directory does not exist.");
//			}
//
//			// 遍历目录取的文件信息
//			List<Hashtable> fileList = new ArrayList<Hashtable>();
//			if (currentPathFile.listFiles() != null) {
//				for (File file : currentPathFile.listFiles()) {
//					Hashtable<String, Object> hash = new Hashtable<String, Object>();
//					String fileName = file.getName();
//					if (file.isDirectory()) {
//						hash.put("is_dir", true);
//						hash.put("has_file", (file.listFiles() != null));
//						hash.put("filesize", 0L);
//						hash.put("is_photo", false);
//						hash.put("filetype", "");
//					} else if (file.isFile()) {
//						String fileExt = fileName.substring(
//								fileName.lastIndexOf(".") + 1).toLowerCase();
//						hash.put("is_dir", false);
//						hash.put("has_file", false);
//						hash.put("filesize", file.length());
//						hash.put("is_photo", Arrays.<String> asList(fileTypes)
//								.contains(fileExt));
//						hash.put("filetype", fileExt);
//					}
//					hash.put("filename", fileName);
//					hash.put("datetime", new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
//					fileList.add(hash);
//				}
//			}
//
//			if ("size".equals(order)) {
//				Collections.sort(fileList, new SizeComparator());
//			} else if ("type".equals(order)) {
//				Collections.sort(fileList, new TypeComparator());
//			} else {
//				Collections.sort(fileList, new NameComparator());
//			}
//
//			System.out.println("moveup_dir_path" + moveupDirPath);
//			System.out.println("current_dir_path" + currentDirPath);
//			System.out.println("current_url" + currentUrl);
//			System.out.println("total_count" + fileList.size());
//
//			JSONObject result = new JSONObject();
//			result.put("moveup_dir_path", moveupDirPath);
//			result.put("current_dir_path", currentDirPath);
//			result.put("current_url", currentUrl);
//			result.put("total_count", fileList.size());
//			result.put("file_list", fileList);
//
//			response.setContentType("application/json; charset=UTF-8");
//			response.getWriter().println(result.toString());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	/**
	 * 
	 * Author:CaiYicheng <BR>
	 * Date2014年8月4日 <BR>
	 * Method name: DelFileFromLocal <BR>
	 * Description: 将文件从硬盘中删除<BR>
	 * 
	 * @return Boolean <BR>
	 */
	private Boolean DelFileFromLocal(String realPath) {

		try {
			System.out.println("要删除的文件路径为:" + realPath);
			File file = new File(realPath);
			if (file.exists()) {
				System.out.println("文件存在,执行删除");
				file.delete();
			} else {
				System.out.println("文件不存在");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * Author:CaiYicheng <BR>
	 * Date2014年8月4日 <BR>
	 * Method name: UploadFileExec <BR>
	 * Description: 文件上传具体实现<BR>
	 * 
	 * @return String <BR>
	 */
	private String UploadFileExec(MultipartFile uploadFile,String fileType,  String uploadDB,String saveUrl) {

		//HttpServletRequest request = ServletActionContext.getRequest();
		// ServletContext pageContext=ServletActionContext.getServletContext();

		//String saveUrl = ReadProperties.getValue(Type+"URL");
		if (null != uploadFile) {

			String name = "";
			try {
				DataInputStream in = new DataInputStream(uploadFile.getInputStream());
				//System.out.println(uploadFile.getName());
				String backPath = saveUrl;
				
				File saveDirFile = new File(backPath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				SimpleDateFormat df = new SimpleDateFormat(
						"HHmmss-SSS");
				String UUID = df.format(new Date())+"-"+java.util.UUID.randomUUID().toString();//df.format(new Date());
				name = UUID + '.' + fileType;// 文件名
				backPath = backPath +"/"+ name;// 文件详细地址

				// 开始准备写入硬盘
				DataOutputStream out = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream(backPath)));
				int b = -1;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				out.close();
				in.close();
				/*if (!uploadDB.equals("false")) {
					// 开始准备插入数据库
					Map map = new HashMap();
					map.put("filename", name);
					map.put("path", saveUrl);
					map.put("type", Type);
					try {
					//	Object ID = logic.add("insertfile", map);
						if (!"".equals(ID)) {
							System.out.println("文件信息成功插入数据库");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("已跳过插入数据库步骤");
				}*/
				return name;

			} catch (Exception e) {
				e.printStackTrace();
				return "3";
			}

		} else {
			System.out.println("文件为空或不存在");
		}
		return "3";
	}

	/**
	 * 
	 * Author:CaiYicheng <BR>
	 * Date2014年8月4日 <BR>
	 * Method name: getFileType <BR>
	 * Description: 根据文件名取得文件扩展名<BR>
	 * 
	 * @return String <BR>
	 */
	private String getFileType(String filename) {
		if (filename == null || "".equals(filename)) {
			return "";
		}
		int i = 0;
		while (filename.charAt(i) != '.' && i < filename.length()) {
			i++;
		}
		i++;
		String type = "";
		for (; i < filename.length(); i++)
			type += filename.charAt(i);

		return type;

	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}
}
