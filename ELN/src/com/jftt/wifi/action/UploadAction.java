/**
 * 
 */
package com.jftt.wifi.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenrui
 *
 */
@Controller
@RequestMapping("uploadAction")
public class UploadAction {
	/**
	 * 跳转图片上传页面
	 * @param request
	 * @return
	 */
	@RequestMapping("toUploadImgPage")
	public String toUploadImgPage(HttpServletRequest request){
		return "common/uploadImgPage";
	}
}
