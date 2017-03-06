package com.jftt.wifi.action_new;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;

@Controller
@RequestMapping(value = "node")
public class PcNodeAction {
	private static Logger log = Logger.getLogger(LoginNewAction.class);

	@Resource(name = "manageCompanyService")
	private ManageCompanyService manageCompanyService;

	@Resource(name = "manageUserService")
	private ManageUserService manageUserService;

	@Resource(name = "manageDepartmentService")
	private ManageDepartmentService manageDepartmentService;

	/**
	 * 返回节点树
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "nodetree")
	@ResponseBody
	public List<ManageDepartmentBean> getNodeTree(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=true)String userName,@RequestParam(required=true) String domain) throws Exception {
		log.debug("userName:" + userName + "  " + "domain:" + domain);
		Integer companyId = null;
		Integer dept_id = null;
		Integer isSubCompany = null;
		Integer subCompanyId = null;
		// 根据域名查询公司信息
		ManageCompanyVo vo = new ManageCompanyVo();
		vo.setDomain(domain);
		ManageCompanyBean company = new ManageCompanyBean();
		List<ManageCompanyBean> comList = manageCompanyService.selectCompanyList(vo);
		log.debug("comList:" + "||" + comList);
		if (comList != null && comList.size() > 0) {
			company = comList.get(0);
			companyId = company.getId();// 企业Id
		}
		log.debug("companyId/companyName:" + companyId + "||" + company.getName());
		// 根据companyId与userName获得岗位id
		ManageUserBean userBean = manageUserService.findUserByUserName(userName.trim(), companyId);
		log.debug("userBean:" + "||" + userBean.toString());
		Map<String, String> departmentMap = new HashMap<String, String>();
		if (null != userBean) {
			dept_id = userBean.getDeptId();
			if (null != dept_id && !"".equals(dept_id)) {
				dept_id = userBean.getDeptId();
				log.debug("dept_id:" + "||" + dept_id);
				// 根据部门Id与公司Id查出该部门是否是子公司并查出子公司Id
				departmentMap.put("id", dept_id.toString());// TODO
				departmentMap.put("companyId", companyId.toString());
				List<ManageDepartmentBean> deptList0 = manageDepartmentService.getManageDepartmentByMap2(departmentMap);
				if (deptList0 != null && deptList0.size() > 0) {
					isSubCompany = deptList0.get(0).getIsSubCompany();// 是否是子公司
					subCompanyId = deptList0.get(0).getSubCompanyId();// 子公司Id
				}
				// 如果是子公司只显示子公司组织机构 非子公司根据companyId查询全部
				if (isSubCompany != null && !"".equals(isSubCompany)) {
					if (1 == isSubCompany) {
						departmentMap.remove("id");// 去掉刚刚加入的部门id
						departmentMap.put("subCompanyId", subCompanyId.toString());
					}
				}
			} else {// 如果部门Id为空，查总公司组织机构
				departmentMap.put("companyId", companyId.toString());
			}
		}
		List<ManageDepartmentBean> deptList1 = manageDepartmentService.getManageDepartmentByMap2(departmentMap);
		log.debug("deptList:" + deptList1.size() + "||" + deptList1.toString());
		response.addHeader("Access-Control-Allow-Origin", "*");
		return deptList1;

	}

	/**
	 * 返回 公司 子公司 部门下属所有用户信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "userList")
	@ResponseBody
	public List<Map<String, Object>> getUserInformation(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(required=true) Integer companyId, Integer subCompanyId, Integer deptId) throws Exception {
		log.debug("companyId:" + companyId + "  " + "subCompanyId:" + subCompanyId + "  " + "deptId:" + deptId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("subCompanyId", subCompanyId);
		map.put("deptId", deptId);
		log.debug("map:" + map.toString());
		List<ManageUserBean> userList = manageUserService.findUserByCondition(map);
		log.debug("userList:" + userList.size() + " " + userList.toString());
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < userList.size(); i++) {
			Map<String, Object> usermap = new HashMap<String, Object>();
			usermap.put("userName", userList.get(i).getUserName() == null ? "" : userList.get(i).getUserName());
			usermap.put("name", userList.get(i).getName() == null ? "" : userList.get(i).getName());
			usermap.put("idCard", userList.get(i).getIdCard());
			usermap.put("sex", userList.get(i).getSex());
			usermap.put("deptName", userList.get(i).getDeptName() == null ? "" : userList.get(i).getDeptName());
			usermap.put("postName", userList.get(i).getPostName() == null ? "" : userList.get(i).getPostName());
			listmap.add(usermap);
			
		}
		
		return listmap;

	}

}
