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
			String userName, String domain) throws Exception {
		log.debug("userName:" + userName + "  " + "domain:" + domain);
		Integer companyId = null;
		Integer dept_id = null;
		String companyName = null;
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
			companyName = company.getName();// 企业名称
		}
		log.debug("companyId/companyName:" + companyId + "||" + companyName);
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
			}else {//如果部门Id为空，查总公司组织机构
				departmentMap.put("companyId", companyId.toString());
			}
		}
		List<ManageDepartmentBean> deptList1 = manageDepartmentService.getManageDepartmentByMap2(departmentMap);
		log.debug("deptList:" + deptList1.size() + "||" + deptList1.toString());
		return deptList1;

	}

	/**
	 * 返回 公司 子公司 部门下属所有用户信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "userList")
	@ResponseBody
	public List<ManageUserBean> getUserInformation(HttpServletRequest request, HttpServletResponse response,
			Integer companyId, Integer subCompanyId, Integer deptId) throws Exception {
		log.debug("companyId:" + companyId + "  " + "subCompanyId:" + subCompanyId + "  " + "deptId:" + deptId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("subCompanyId", subCompanyId);
		map.put("deptId", deptId);
		log.debug("map:" + map.toString());
		List<ManageUserBean> userList = manageUserService.findUserByCondition(map);
		log.debug("userList:" + userList.size() + " " + userList.toString());
		List<ManageUserBean> listuser = new ArrayList<ManageUserBean>();
		for (int i = 0; i < userList.size(); i++) {
			ManageUserBean user = new ManageUserBean();
			user.setUserName(userList.get(i).getUserName() == null ? "" : userList.get(i).getUserName());
			user.setName(userList.get(i).getName() == null ? "" : userList.get(i).getName());
			user.setIdCard(userList.get(i).getIdCard());
			user.setSex(userList.get(i).getSex());
			user.setDeptName(userList.get(i).getDeptName() == null ? "" : userList.get(i).getDeptName());
			user.setPostName(userList.get(i).getPostName() == null ? "" : userList.get(i).getPostName());
			user.setAddress("");
			user.setBirthDay("");
			user.setCompanyName("");
			user.setCreateTime("");
			user.setEmail("");
			user.setGraduateCollege("");
			user.setWorkYear("");
			user.setWorkNo("");
			user.setWechartNo("");
			user.setSubCompanyName("");
			user.setQqNo("");
			user.setPhoto("");
			user.setPassword("");
			user.setMobile("");
			user.setMajor("");
			user.setJoinWorkTime("");
			user.setIsEdit("");
			user.setId("");
			user.setHighEducation("");
			user.setHeadPhoto("");
			user.setGraduateCollege("");
			user.setField1("");
			user.setField2("");
			user.setField3("");
			user.setField4("");
			user.setField5("");
			user.setField6("");
			user.setField7("");
			user.setField8("");
			user.setField9("");
			user.setField10("");
			user.setField11("");
			user.setField12("");
			user.setField13("");
			user.setField14("");
			user.setField15("");
			user.setField16("");
			user.setField17("");
			user.setField18("");
			user.setField19("");
			user.setField20("");
			user.setField21("");
			user.setField22("");
			user.setField23("");
			user.setField24("");
			user.setField25("");
			user.setField26("");
			user.setField27("");
			user.setField28("");
			user.setField29("");
			user.setField30("");
			user.setField31("");
			user.setField32("");
			user.setField33("");
			user.setField34("");
			user.setField35("");
			user.setField36("");
			user.setField37("");
			user.setField38("");
			user.setField39("");
			user.setField40("");
			user.setField41("");
			user.setField42("");
			user.setField43("");
			user.setField44("");
			user.setField45("");
			user.setField46("");
			user.setField47("");
			user.setField48("");
			user.setField49("");
			user.setField50("");
			user.setField51("");
			user.setField52("");
			user.setField53("");
			user.setField54("");
			user.setField55("");
			user.setField56("");
			user.setField57("");
			user.setField58("");
			user.setField59("");
			user.setField60("");
			user.setField61("");
			user.setField62("");
			user.setField63("");
			user.setField64("");
			user.setField65("");
			user.setField66("");
			user.setField67("");
			user.setField68("");
			user.setField69("");
			user.setField70("");
			user.setField71("");
			user.setField72("");
			user.setField73("");
			user.setField74("");
			user.setField75("");
			user.setField76("");
			user.setField77("");
			user.setField78("");
			user.setField79("");
			user.setField80("");
			user.setField81("");
			user.setField82("");
			user.setField83("");
			user.setField84("");
			user.setField85("");
			user.setField86("");
			user.setField87("");
			user.setField88("");
			user.setField89("");
			user.setField90("");
			user.setField91("");
			user.setField92("");
			user.setField93("");
			user.setField94("");
			user.setField95("");
			user.setField96("");
			user.setField97("");
			user.setField98("");
			user.setField99("");
			user.setField100("");

			listuser.add(user);
		}
		return listuser;

	}

}
