/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:ManageDepartmentServiceImpl.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/14           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.CompanyVo;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageDepartmentService;
import com.jftt.wifi.service.ManageUserService;

/**
 *
 */
@Service("manageDepartmentService")
public class ManageDepartmentServiceImpl implements ManageDepartmentService{
	@Autowired
	private ManageUserService manageUserService;
	@Resource(name="manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	/**
	 * 根据条件获得部门
	 */
	public List<ManageDepartmentBean> getManageDepartmentByMap(Map<String, String> map) throws Exception{
		return manageDepartmentDaoMapper.getManageDepartmentByMap(map);
	}
	
	
	/**
	 * 新增部门
	 */
	public void addManageDepartment(ManageDepartmentBean departmentBean) throws Exception{
		departmentBean.setOrder(manageDepartmentDaoMapper.getMaxOrder(departmentBean)+1);
		manageDepartmentDaoMapper.addManageDepartment(departmentBean);
	}
	
	/**
	 * 根据Id获得部门
	 */
	public ManageDepartmentBean getManageDepartmentById(long id) throws Exception{
		return manageDepartmentDaoMapper.getManageDepartmentById(id);
	}


	@Override
	public void delManageDepartmentById(long id)  throws Exception{
		manageDepartmentDaoMapper.delManageDepartmentById(id);
	}


	@Override
	public void updateManageDepartmentById(ManageDepartmentBean departmentBean)  throws Exception {
		manageDepartmentDaoMapper.updateManageDepartmentById(departmentBean);
	}


	@Override
	public long getManageDepartmentCountByMap(Map<String, String> map)  throws Exception {
		return manageDepartmentDaoMapper.getManageDepartmentCountByMap(map);
	}


	@Override
	public void setDeptToSubCom(ManageDepartmentBean departmentBean)
			throws Exception {
		manageDepartmentDaoMapper.setDeptToSubCom(departmentBean);
	}
	

	@Override
	public void upDept(int id) {
		//该部门前一部门order+1
		ManageDepartmentBean dept = manageDepartmentDaoMapper.getManageDepartmentById(id);
		//判断该部门是否在最顶部
		if(manageDepartmentDaoMapper.getLThanThisOrder(dept) > 0){
			manageDepartmentDaoMapper.downPrevDept(dept);
			//该部门order-1;
			manageDepartmentDaoMapper.upThisDept(id);
		}
	}


	@Override
	public void downDept(int id) {
		ManageDepartmentBean dept = manageDepartmentDaoMapper.getManageDepartmentById(id);
		//判断该部门是否在最底部
		if(manageDepartmentDaoMapper.getGThanThisOrder(dept) > 0){
			//该部门后一部门order+1
			manageDepartmentDaoMapper.upNextDept(dept);
			//该部门order-1;
			manageDepartmentDaoMapper.downThisDept(id);
		}
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageDepartmentService#getMaxCode(java.util.Map) <BR>
	 * Method name: getMaxCode <BR>
	 * Description:  获取该层级的最大部门编码 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public String getMaxCode(Map<String, Object> param) {
		return manageDepartmentDaoMapper.getMaxCode(param);
	}


	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageDepartmentService#getParentCode(java.util.Map) <BR>
	 * Method name: getParentCode <BR>
	 * Description: 获取父部门编码 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  <BR>
	*/
	@Override
	public String getParentCode(Map<String, Object> param) {
		return manageDepartmentDaoMapper.getParentCode(param);
	}

	/**chenrui start*/
	/**
	 * 
	 * @Override
	 * @see com.jftt.wifi.service.ManageDepartmentService#getDepInfo(java.lang.String, long, java.lang.String) <BR>
	 * Method name: getDepInfo <BR>
	 * Description:获取当前公司下的子公司/部门   <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ManageDepartmentBean> getDepInfo(String userId, long fromNum, String pageSize) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		int companyId = userBean.getCompanyId();
		int subCompanyId = userBean.getSubCompanyId();
		return manageDepartmentDaoMapper.getDepInfo(companyId,subCompanyId,fromNum,pageSize);
	}
	@Override
	public int getDepInfoCount(String userId) throws Exception {
		ManageUserBean userBean = manageUserService.findUserById(userId);
		int companyId = userBean.getCompanyId();
		int subCompanyId = userBean.getSubCompanyId();
		return manageDepartmentDaoMapper.getDepInfoCount(companyId,subCompanyId);
	}



	/**chenrui end*/
	
	/**
	 * wj add
	 * 获得公司的所有上级公司信息 并从大到小排序
	 */
	@Override
	@Transactional
	public List<CompanyVo> getParents(ManageUserBean userBean){
		
		List<CompanyVo> backList = new ArrayList<CompanyVo>();
		
		try {
			
			//总公司
			ManageCompanyBean companyBeanZong = manageCompanyService.selectCompanyById(userBean.getCompanyId());
			CompanyVo companyZong = new CompanyVo(Long.parseLong(userBean.getCompanyId()+""), companyBeanZong.getName(), 1);
			backList.add(companyZong);
			
			if(userBean.getCompanyId().equals(userBean.getSubCompanyId())){
				//公司ID 与 分公司ID一样 集团公司的员工
				return backList;
				
			}else{
				
				//所有子公司
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("companyId", userBean.getCompanyId()+"");
				paramMap.put("isSubCompany", "1");
				
				List<ManageDepartmentBean> deptList = getManageDepartmentByMap(paramMap);
				
				/**
				 * 1.捞取所有的子公司
				 * 2. 一层一层找上去，一直找到parentId = null的
				 */
				
				if(deptList !=null && !deptList.isEmpty()){
					
					ManageDepartmentBean thisDept = null;
					
					for (ManageDepartmentBean dept : deptList) {
						
						if(dept.getId() == userBean.getSubCompanyId().intValue()){
							thisDept = dept;
							
							CompanyVo company = new CompanyVo(thisDept.getId(), thisDept.getName(), 2);
							backList.add(company);
							
							break;
						}
					}
					
					for(int i=0; i<deptList.size(); i++){
						
						//找父类
						ManageDepartmentBean patrent = getParentDept(deptList, thisDept.getParentId());
						thisDept = patrent;
						
						if(patrent == null){
							break;
							
						}else if(patrent.getParentId() == null){
							CompanyVo company = new CompanyVo(patrent.getId(), patrent.getName(), 2);
							backList.add(1, company);
							
							break;
						}else{
							CompanyVo company = new CompanyVo(patrent.getId(), patrent.getName(), 2);
							backList.add(1, company);
						}
					}
				}
			}
			
			return backList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return backList;
	}
	
	public ManageDepartmentBean getParentDept(List<ManageDepartmentBean> deptList, long parentId){
		
		for (ManageDepartmentBean dept : deptList) {
			
			if(parentId == dept.getId()){
				
				return dept;
			}
		}
		
		return null;
	}
	
	/**
	 * wj add
	 * 获得子公司的所有下级子公司 包括自己,
	 */
	@Transactional
	public List<Long> getChildren(long companyId, long subCompanyId){
		
		List<Long> backList = new ArrayList<Long>();
		
		try {
			
			//所有子公司
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("companyId", companyId+"");
			paramMap.put("isSubCompany", "1");
			List<ManageDepartmentBean> deptList = getManageDepartmentByMap(paramMap);
			
			if(companyId == subCompanyId){
				
				for (ManageDepartmentBean dept : deptList) {
					
					if(dept.getParentId() == null){
						
						backList.add(dept.getId());
						
						List<Long> childList = getChildren(deptList, dept.getId());
						
						backList.addAll(childList);
					}
				}
				
			}else{
				backList.add(subCompanyId);
				
				List<Long> childList = getChildren(deptList, subCompanyId);
				
				backList.addAll(childList);
			}
			
			return backList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return backList;
	}
	
	/**
	 * 查找子公司
	 */
	public List<Long> getChildren(List<ManageDepartmentBean> deptList, long id){
		
		List<Long> backList = new ArrayList<Long>();
		
		try {
			
			for (ManageDepartmentBean dept : deptList) {
				
				if(dept.getParentId() !=null && dept.getParentId().intValue() == id){
					
					backList.add(dept.getId());
				}
			}
			
			if(backList.isEmpty()){
				return backList;
			}else{
				
				int len = backList.size();
				
				for (int i=0; i<len; i++) {
					
					List<Long> childList = getChildren(deptList, backList.get(i));
					
					backList.addAll(childList);
				}
			}
			
			return backList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return backList;
	}


	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageDepartmentService#getParentComList(com.jftt.wifi.bean.ManageUserBean) <BR>
	 * Method name: getParentComList <BR>
	 * Description: 根据用户获取用户所有上层公司 <BR>
	 * Remark: <BR>
	 * @param user
	 * @return  <BR>
	*/
	@Override
	public List<ManageDepartmentBean> getParentComList(ManageUserBean user) {
		List<ManageDepartmentBean> list = new ArrayList<ManageDepartmentBean>();
		int subCompanyId = user.getSubCompanyId();
		while(!user.getCompanyId().equals(user.getSubCompanyId())){
			ManageDepartmentBean dept = manageDepartmentDaoMapper.getManageDepartmentById(subCompanyId);
			if(dept.getIsSubCompany() == 1){
				list.add(dept);
			}
			if(dept.getParentId() == null ){
				break;
			}
			subCompanyId = dept.getParentId();
		}
		return list;
	}


	


	
}
