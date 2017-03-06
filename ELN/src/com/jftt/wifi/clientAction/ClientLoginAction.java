package com.jftt.wifi.clientAction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.BaseMetaDaoMapper;
import com.jftt.wifi.service.InformationManageService;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.LoginHisService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageIndustryCategoryService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.CommonUtil;
import com.jftt.wifi.util.TimeUtil;

import net.sf.json.JSONObject;

/**
 * class name:LoginAction <BR>
 * class description: 处理登录页面 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2013-12-13
 * @author JFTT)WANGJIAN
 */
@Controller
@RequestMapping(value = "login_new")
public class ClientLoginAction {

	private static Logger log = Logger.getLogger(ClientLoginAction.class);
	
	private String jsonpcall = "jsonpCallback({msg:"+"'";
	
	private String jsonpcallEnding = "'"+"})";

	@Resource(name = "manageUserService")
	private ManageUserService manageUserService;

	@Resource(name = "manageCompanyService")
	private ManageCompanyService manageCompanyService;

	@Resource(name = "manageIndustryCategoryService")
	private ManageIndustryCategoryService manageIndustryCategoryService;

	@Resource(name = "informationManageService")
	private InformationManageService informationManageService;

	@Resource(name = "integralManageService")
	private IntegralManageService integralManageService;

	@Resource(name = "baseMetaDaoMapper")
	private BaseMetaDaoMapper baseMetaDaoMapper;

	@Resource(name = "loginHisService")
	private LoginHisService loginHisService;

	/**
	 * Method name: pclogin <BR>
	 * Description: 登录的ajax <BR>
	 * Remark: <BR>
	 */
	@RequestMapping(value = "pclogin")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String userName, @RequestParam(required=true)String passWord,
			@RequestParam(required=true)String domain) {
		try {
			log.debug("系统登陆 参数信息 UserName: " + userName + "PassWord: " + passWord + "domain:" + domain);
			// TODO:修改companyId
			// 默认普联企业id
			Integer companyId = Constant.PULIAN_COMPANY_ID;
			// Integer companyId = null;
			ManageCompanyBean company = new ManageCompanyBean();
			// 根据域名获取该用户的公司
			ManageCompanyVo vo = new ManageCompanyVo();
			vo.setDomain(domain);// TODO
			List<ManageCompanyBean> comList = manageCompanyService.selectCompanyList(vo);
			log.debug("comList:" + "\n" + comList);
			if (comList != null && comList.size() > 0) {
				company = comList.get(0);
				companyId = company.getId();
				log.debug("公司ID ：" + companyId + "\n" + "冻结:" + company.getStatus() + comList.toString());
				if (companyId > Constant.PULIAN_COMPANY_ID) {
					// 判断公司账号过期时间
					if (new Date().getTime() > TimeUtil.parseString2Date(company.getEndTime()).getTime()) {
						return jsonpcall+"该企业租用时间已经过期"+jsonpcallEnding;
					}
					// 判断该企业是否冻结
					if (!"1".equals(company.getStatus().toString())) {
						return jsonpcall+"该企业已经被冻结"+jsonpcallEnding;
					}
					// 判断该企业当前在线用户是否大于最大并发用户
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("companyId", companyId);
					int currentUser = loginHisService.selectUserCountByMap(param);
					if (currentUser >= company.getMaxConcurrent()) {
						return jsonpcall+"该企业当前在线用户大于最大并发用"+jsonpcallEnding;
					}
				}
			}
			// 根据公司Id、登陆账号获取该用户
			ManageUserBean userBean = manageUserService.findUserByUserName(userName.trim(), companyId);
			log.debug("userBean:" + "\n" + userBean);
			if (null != userBean) {
				log.debug("\n" + "userBean.getPassword" + userBean.getPassword() + "\n" + "输入密码:"
						+ CommonUtil.getMD5(passWord));
				if (CommonUtil.getMD5(passWord).equalsIgnoreCase(userBean.getPassword())) {

					if (!"1".equals(userBean.getStatus().toString())) {
						return jsonpcall+"该企业已经被冻结"+jsonpcallEnding;
					}

					// 设置Session
					HttpSession session = request.getSession();
					session.setAttribute(Constant.SESSION_USER_COMPANYID, userBean.getCompanyId());
					session.setAttribute(Constant.SESSION_USERID_LONG, userBean.getId());
					session.setAttribute(Constant.SESSION_USERNAME_STRING, userBean.getUserName());
					session.setAttribute(Constant.SESSION_NAME, userBean.getName());
					session.setAttribute(Constant.SESSION_USERBEAN,
							manageUserService.findUserByIdCondition(userBean.getId()));

					log.debug(userName + " 登陆成功");
					// 积累积分
					integralManageService.handleIntegral(Integer.parseInt(userBean.getId()), 7009);
					// 初始化课件查询参数
					String VideoLimitMinute = baseMetaDaoMapper.getValueByKey("VideoLimitMinute");//当前视频课件预览最大分钟
					String TestLimitMinute = baseMetaDaoMapper.getValueByKey("TestLimitMinute");//测试课件预览最大分钟
					String ScormLimitMinute = baseMetaDaoMapper.getValueByKey("ScormLimitMinute");//Scorm课件预览最大分钟
					String LimitPage = baseMetaDaoMapper.getValueByKey("LimitPage");//flexpaper预览最大页码
					String PdfTimeInterval = baseMetaDaoMapper.getValueByKey("PdfTimeInterval");//pdf课件翻页间隔时间
					if (VideoLimitMinute != null && !VideoLimitMinute.isEmpty()) {
						session.setAttribute("VideoLimitMinute", Integer.parseInt(VideoLimitMinute));
					}
					if (TestLimitMinute != null && !TestLimitMinute.isEmpty()) {
						session.setAttribute("TestLimitMinute", Integer.parseInt(TestLimitMinute));
					}
					if (ScormLimitMinute != null && !ScormLimitMinute.isEmpty()) {
						session.setAttribute("ScormLimitMinute", Integer.parseInt(ScormLimitMinute));
					}
					if (LimitPage != null && !LimitPage.isEmpty()) {
						session.setAttribute("LimitPage", Integer.parseInt(LimitPage));
					}
					if (PdfTimeInterval != null && !PdfTimeInterval.isEmpty()) {
						session.setAttribute("PdfTimeInterval", Integer.parseInt(PdfTimeInterval));
					}
					return jsonpcall+"登录成功"+jsonpcallEnding;
				} else {

					log.debug(userName + " 密码错误");
					return jsonpcall+"密码错误"+jsonpcallEnding;
				}

			} else {

				log.debug(userName + " 不存在");
				return jsonpcall+"用户不存在"+jsonpcallEnding;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			return jsonpcall+"其他错误"+jsonpcallEnding;
		}
	}

}
