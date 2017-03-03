package com.jftt.wifi.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.CertificateBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.bean.ManageRoleBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ManageUserIdSeqBean;
import com.jftt.wifi.bean.ManageUserRoleBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ManageCompanyDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.dao.ManageUserDaoMapper;
import com.jftt.wifi.service.IntegralManageService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageParamService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.service.ManagementLearningMapService;
import com.jftt.wifi.util.MyExcelHelp;
import com.jftt.wifi.util.TimeUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.util.Hash;

/**
 * manageUserService.java
 */
@Service("manageUserService")
public class ManageUserServiceImpl implements ManageUserService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private MongoDbFactory mongoDbFactory;
	
	
	@Resource(name="manageUserDaoMapper")
	private ManageUserDaoMapper manageUserDaoMapper;
	
	@Resource(name = "manageDepartmentDaoMapper")
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	
	@Resource(name="managePostDaoMapper")
	private ManagePostDaoMapper managePostDaoMapper; 
	
	@Resource(name="manageParamService")
	private ManageParamService manageParamService; 
	
	@Resource(name="manageCompanyService")
	private ManageCompanyService manageCompanyService;
	
	@Resource
	private ManagementLearningMapService managementLearningMapService;
	
	@Resource(name="integralManageService")
	private IntegralManageService integralManageService;
	
	/**
     * Method name: findAll <BR>
     * Description: 查询所有用户 <BR>
     * Remark: <BR>
     * @return  List<ManageUserBean><BR>
     */
	@Override
	public List<ManageUserBean> findAll() throws Exception {
		return mongoTemplate.find(new Query(), ManageUserBean.class);

	}

	/**
     * Method name: findAndModify <BR>
     * Description: 根据id查询并且更新改用户 <BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
	@Override
	public void update(ManageUserBean user) throws Exception {
		Update update = new Update();
		Field[] fields = ManageUserBean.class.getDeclaredFields();
		for(Field field : fields){
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			field.setAccessible(true);
			if(field.get(user) != null){
				update.set(field.getName(), field.get(user));
				//修改用户岗位时对岗位路线操作
				if("postId".equals(field.getName())){
					managementLearningMapService.insertPostPromotionStateByUser(Integer.parseInt(user.getId()), user.getPostId());
				}
			}
		}
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(user.getId())), update, ManageUserBean.class);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageUserService#updateManageUserPassword(java.lang.String, java.lang.String) <BR>
	 * Method name: updateManageUserPassword <BR>
	 * Description: 修改密码 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param md5
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateManageUserPassword(String id, String username, String md5)
			throws Exception {
		Update update = Update.update("userName", username).set("password", md5);
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), update, ManageUserBean.class);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageUserService#resetPassword(java.lang.String, java.lang.String) <BR>
	 * Method name: resetPassword <BR>
	 * Description: 重置密码 <BR>
	 * Remark: <BR>
	 * @param id
	 * @param md5
	 * @throws Exception  <BR>
	*/
	@Override
	public void resetPassword(String id, String md5)
			throws Exception {
		Update update = new Update();
		update.set("password", md5);
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), update, ManageUserBean.class);
	}

	 /**
     * Method name: findByRegex <BR>
     * Description: 根据姓名模糊查询用户  <BR>
     * Remark: <BR>
     * @param regex
     * @return  List<ManageUserBean><BR>
     */
	@Override
	public List<ManageUserBean> findByName(String name) throws Exception {
		Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
		Criteria criteria = new Criteria("name").regex(pattern.toString());
		return mongoTemplate.find(new Query(criteria), ManageUserBean.class);

	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageUserService#findUserByAccurateName(java.lang.String, java.lang.String, java.lang.String) <BR>
	 * Method name: findUserByAccurateName <BR>
	 * Description: 根据姓名精确查询该公司内的用户 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<ManageUserBean> findUserByAccurateName(String name, Integer companyId, Integer subCompanyId) throws Exception{
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		query.addCriteria(new Criteria("companyId").is(companyId));
		query.addCriteria(new Criteria("subCompanyId").is(subCompanyId));
		return mongoTemplate.find(query, ManageUserBean.class);
	}
	
	 /**
     * Method name: findOne <BR>
     * Description: 根据id查询一个用户 <BR>
     * Remark: <BR>
     * @param id
     * @return  ManageUserBean<BR>
     */
	@Override
	public ManageUserBean findUserById(String id) throws Exception {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), ManageUserBean.class);  
	}

	/**
	 * Method name: insert <BR>
	 * Description: 插入一个用户 <BR>
	 * Remark: <BR>
	 * @param person  void<BR>
	 */
	@Override
	public int insert(ManageUserBean user) throws Exception {
		//自增id
		int id = getNextSequence();
		user.setId(id+"");
		user.setOrder(id);
		user.setCreateTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		if("1".equals(user.getSex().toString())){
			user.setHeadPhoto("/ELN/images/img/avatar_male.png");
		}else{
			user.setHeadPhoto("/ELN/images/img/avatar_female.png");
		}
		//默认照片为空
		//user.setPhoto("");
		mongoTemplate.insert(user, Constant.MANAGE_USER_DB_NAME);
		//修改用户岗位时对岗位路线操作
		if(user.getPostId() != null && !"".equals(user.getPostId())){
			managementLearningMapService.insertPostPromotionStateByUser(Integer.parseInt(user.getId()), user.getPostId());
		}
		//获得积分
		integralManageService.handleIntegral(id,7010);
		return id;
	}

	 /**
     * Method name: removeOne <BR>
     * Description: 根据id删除一个用户 <BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
	@Override
	public void removeOne(String id) throws Exception {
		ManageUserBean user = findUserById(id);
		if(user != null){
			mongoTemplate.remove(user);
		}

	}

	/**
     * Method name: count <BR>
     * Description: 所有用户数量 <BR>
     * Remark: <BR>
     * @return  int<BR>
     */
	@Override
	public int findUserCountByCondition(Map<String, Object> map) throws Exception {
		return findUserByCondition(map,0,0).size();
	}
	
	 /**
     * Method name: findMMGrid <BR>
     * Description: findMMGrid <BR>
     * Remark: <BR>
     * @return  List<ManageUserBean><BR>
     */
	@Override
	public List<ManageUserBean> findUserByCondition(Map<String, Object> map) throws Exception{
		return findUserByCondition(map,0,0);
	}

	 /**
     * Method name: findUserByCondition <BR>
     * Description: findUserByCondition <BR>
     * Remark: <BR>
     * @return  List<ManageUserBean><BR>
     */
	@Override
	public List<ManageUserBean> findUserByCondition(Map<String, Object> map, int page, int pageSize) throws Exception{
		Query query = new Query();
		Iterator i = map.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry e = (Entry) i.next();
			String key = String.valueOf(e.getKey());
			if(e.getValue() != null && !"".equals(e.getValue())) {
				if(Constant.MONGO_BEAN_NAME.equals(key) || Constant.MONGO_BEAN_ADDRESS.equals(key)){
						Pattern pattern = Pattern.compile(String.valueOf(e.getValue()), Pattern.CASE_INSENSITIVE);
						query.addCriteria(new Criteria(key).regex(pattern.toString()));
				}else{
					query.addCriteria(new Criteria(key).is(e.getValue()));	
				}
			}
		}
		if(page != 0 && pageSize != 0){
			query.skip((page-1)*pageSize).limit(pageSize);
		}
		return mongoTemplate.find(query, ManageUserBean.class);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageUserService#findUserByListCondition(java.util.Map) <BR>
	 * Method name: in条件 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManageUserBean> findUserByListCondition(Map<String, Object> map, int page, int pageSize) throws Exception{
		DB db = mongoDbFactory.getDb();
    	DBCollection coll = db.getCollection(Constant.MANAGE_USER_DB_NAME);
		Iterator i = map.entrySet().iterator();
		DBObject command = new BasicDBObject();
		while(i.hasNext()){
			Map.Entry e = (Entry) i.next();
			String key = String.valueOf(e.getKey());
			if(e.getValue() != null && !"".equals(e.getValue())) {
				if(e.getValue().getClass() == ArrayList.class){
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, new BasicDBObject("$in",e.getValue()));
					}else{
						command.put(key, new BasicDBObject("$in",e.getValue()));
					}
				} else if (Constant.MANAGE_USER_DEPT_FIELD.equals(key)){
					//部门处理
					if(e.getValue().getClass() == String.class){
						Map<String, String> mapParam = new HashMap<String, String>();
						mapParam.put("name", String.valueOf(e.getValue()));
						List<ManageDepartmentBean> deptList = manageDepartmentDaoMapper.getManageDepartmentByMap(mapParam);//获取岗位list
						BasicDBList basicDBList = new BasicDBList();//部门id List
						for(ManageDepartmentBean temp : deptList){
							basicDBList.add(temp.getId());
						}
						command.put(key, new BasicDBObject("$in",basicDBList));
					}else{
						command.put(key, e.getValue());
					}
				} else if (Constant.MANAGE_USER_POST_FIELD.equals(key)){
					//岗位处理
					if(e.getValue().getClass() == String.class){
						Map<String, Object> mapParam = new HashMap<String, Object>();
						mapParam.put("name", String.valueOf(e.getValue()));
						List<ManagePostBean> postList = managePostDaoMapper.selectManagePost(mapParam);//获取岗位list
					
						BasicDBList basicDBList = new BasicDBList();//岗位id List
						for(ManagePostBean temp : postList){
							basicDBList.add(temp.getId());
						}
						command.put(key, new BasicDBObject("$in",basicDBList));
					}else{
						command.put(key, e.getValue());
					}
				}else if(Constant.MONGO_BEAN_NAME.equals(key) || Constant.MONGO_BEAN_ADDRESS.equals(key) 
						|| Constant.MONGO_BEAN_MOBILE.equals(key) || Constant.MONGO_BEAN_USER_NAME.equals(key)){
					Pattern pattern = Pattern.compile("^.*" + e.getValue()+ ".*$", Pattern.CASE_INSENSITIVE); 
					command.put(key, pattern);
				}else if(Constant.MONGO_BEAN_PHOTO.equals(key)){
					if("1".equals(e.getValue())){
						command.put(key, new BasicDBObject(QueryOperators.EXISTS,true));
					}else{
						command.put(key, new BasicDBObject(QueryOperators.EXISTS,false));
					}
				}else {
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, e.getValue());
					}else{
						command.put(key, e.getValue());
					}
				}
			}
		}
		if(pageSize != 0){
			return dbObjectToUser(coll.find(command).sort(new BasicDBObject("order",-1)).skip((page-1)*pageSize).limit(pageSize).toArray(), true);
		}else{
			return dbObjectToUser(coll.find(command).sort(new BasicDBObject("order",-1)).toArray(), true);
		}
	}
	
	@Override
	public List<ManageUserBean> findUserByListCondition(Map<String, Object> map) throws Exception{
		return findUserByListCondition(map, 0, 0);
	}
	
	/**
	 * Method name: findUserByListCondition_ <BR>
	 * Description: 不获取部门、岗位名称 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	@Override
	public List<ManageUserBean> findUserByListCondition_(Map<String, Object> map) throws Exception{
		DB db = mongoDbFactory.getDb();
    	DBCollection coll = db.getCollection(Constant.MANAGE_USER_DB_NAME);
		Iterator i = map.entrySet().iterator();
		DBObject command = new BasicDBObject();
		while(i.hasNext()){
			Map.Entry e = (Entry) i.next();
			String key = String.valueOf(e.getKey());
			if(e.getValue() != null && !"".equals(e.getValue())) {
				if(e.getValue().getClass() == ArrayList.class){
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, new BasicDBObject("$in",e.getValue()));
					}else{
						command.put(key, new BasicDBObject("$in",e.getValue()));
					}
				} else if (Constant.MANAGE_USER_DEPT_FIELD.equals(key)){
					//部门处理
					if(e.getValue().getClass() == String.class){
						Map<String, String> mapParam = new HashMap<String, String>();
						mapParam.put("name", String.valueOf(e.getValue()));
						List<ManageDepartmentBean> deptList = manageDepartmentDaoMapper.getManageDepartmentByMap(mapParam);//获取岗位list
						BasicDBList basicDBList = new BasicDBList();//部门id List
						for(ManageDepartmentBean temp : deptList){
							basicDBList.add(temp.getId());
						}
						command.put(key, new BasicDBObject("$in",basicDBList));
					}else{
						command.put(key, e.getValue());
					}
				} else if (Constant.MANAGE_USER_POST_FIELD.equals(key)){
					//岗位处理
					if(e.getValue().getClass() == String.class){
						Map<String, Object> mapParam = new HashMap<String, Object>();
						mapParam.put("name", String.valueOf(e.getValue()));
						List<ManagePostBean> postList = managePostDaoMapper.selectManagePost(mapParam);//获取岗位list
					
						BasicDBList basicDBList = new BasicDBList();//岗位id List
						for(ManagePostBean temp : postList){
							basicDBList.add(temp.getId());
						}
						command.put(key, new BasicDBObject("$in",basicDBList));
					}else{
						command.put(key, e.getValue());
					}
				}else if(Constant.MONGO_BEAN_NAME.equals(key) || Constant.MONGO_BEAN_ADDRESS.equals(key) 
						|| Constant.MONGO_BEAN_MOBILE.equals(key) || Constant.MONGO_BEAN_USER_NAME.equals(key)){
					Pattern pattern = Pattern.compile("^.*" + e.getValue()+ ".*$", Pattern.CASE_INSENSITIVE); 
					command.put(key, pattern);
				}else if(Constant.MONGO_BEAN_PHOTO.equals(key)){
					if("1".equals(e.getValue())){
						command.put(key, new BasicDBObject(QueryOperators.EXISTS,true));
					}else{
						command.put(key, new BasicDBObject(QueryOperators.EXISTS,false));
					}
				}else {
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, e.getValue());
					}else{
						command.put(key, e.getValue());
					}
				}
			}
		}
		return dbObjectToUser(coll.find(command).sort(new BasicDBObject("order",-1)).toArray(), false);
	}
	
	@Override
	public int findUserByListConditionCount(Map<String, Object> map) throws Exception{
		DB db = mongoDbFactory.getDb();
    	DBCollection coll = db.getCollection(Constant.MANAGE_USER_DB_NAME);
		Iterator i = map.entrySet().iterator();
		DBObject command = new BasicDBObject();
		while(i.hasNext()){
			Map.Entry e = (Entry) i.next();
			String key = String.valueOf(e.getKey());
			if(e.getValue() != null && !"".equals(e.getValue())) {
				if(e.getValue().getClass() == ArrayList.class){
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, new BasicDBObject("$in",e.getValue()));
					}else{
						command.put(key, new BasicDBObject("$in",e.getValue()));
					}
				} else if (Constant.MANAGE_USER_DEPT_FIELD.equals(key)){
					//部门处理
					if(e.getValue().getClass() == String.class){
						Map<String, String> mapParam = new HashMap<String, String>();
						mapParam.put("name", String.valueOf(e.getValue()));
						List<ManageDepartmentBean> deptList = manageDepartmentDaoMapper.getManageDepartmentByMap(mapParam);//获取岗位list
						BasicDBList basicDBList = new BasicDBList();//部门id List
						for(ManageDepartmentBean temp : deptList){
							basicDBList.add(temp.getId());
						}
						command.put(key, new BasicDBObject("$in",basicDBList));
					}else{
						command.put(key, e.getValue());
					}
				} else if (Constant.MANAGE_USER_POST_FIELD.equals(key)){
					//岗位处理
					if(e.getValue().getClass() == String.class){
						Map<String, Object> mapParam = new HashMap<String, Object>();
						mapParam.put("name", String.valueOf(e.getValue()));
						List<ManagePostBean> postList = managePostDaoMapper.selectManagePost(mapParam);//获取岗位list
					
						BasicDBList basicDBList = new BasicDBList();//岗位id List
						for(ManagePostBean temp : postList){
							basicDBList.add(temp.getId());
						}
						command.put(key, new BasicDBObject("$in",basicDBList));
					}else{
						command.put(key, e.getValue());
					}
				}else if(Constant.MONGO_BEAN_NAME.equals(key) || Constant.MONGO_BEAN_ADDRESS.equals(key) 
						|| Constant.MONGO_BEAN_MOBILE.equals(key) || Constant.MONGO_BEAN_USER_NAME.equals(key)){
					Pattern pattern = Pattern.compile("^.*" + e.getValue()+ ".*$", Pattern.CASE_INSENSITIVE); 
					command.put(key, pattern);
				}else if(Constant.MONGO_BEAN_PHOTO.equals(key)){
					if("1".equals(e.getValue())){
						command.put(key, new BasicDBObject(QueryOperators.EXISTS,true));
					}else{
						command.put(key, new BasicDBObject(QueryOperators.EXISTS,false));
					}
				}else {
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, e.getValue());
					}else{
						command.put(key, e.getValue());
					}
				}
			}
		}
		return coll.find(command).sort(new BasicDBObject("order",-1)).count();
	}
	
	@Override
	public ManageUserBean findUserByIdCondition(String id) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		List<ManageUserBean> list = findUserByListCondition(param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<ManageUserBean> findUserByNotInCondition(Map<String, List> map) throws Exception{
		DB db = mongoDbFactory.getDb();
    	DBCollection coll = db.getCollection(Constant.MANAGE_USER_DB_NAME);
		Iterator i = map.entrySet().iterator();
		DBObject command = new BasicDBObject();
		while(i.hasNext()){
			Map.Entry e = (Entry) i.next();
			String key = String.valueOf(e.getKey());
			if(e.getValue() != null && !"".equals(e.getValue())) {
				if(e.getValue().getClass() == ArrayList.class){
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, new BasicDBObject("$nin",e.getValue()));
					}else{
						command.put(key, new BasicDBObject("$nin",e.getValue()));
					}
				}
			}
		}
		return dbObjectToUser(coll.find(command).toArray(), true);
	}
	
	@Override
	public List<ManageUserBean> findUserByNotInCondition(Map<String, List> map, int page, int pageSize) throws Exception{
		DB db = mongoDbFactory.getDb();
    	DBCollection coll = db.getCollection(Constant.MANAGE_USER_DB_NAME);
		Iterator i = map.entrySet().iterator();
		DBObject command = new BasicDBObject();
		while(i.hasNext()){
			Map.Entry e = (Entry) i.next();
			String key = String.valueOf(e.getKey());
			if(e.getValue() != null && !"".equals(e.getValue())) {
				if(e.getValue().getClass() == ArrayList.class){
					if(Constant.MANAGE_USER_ID_FIELD.equals(key)){
						command.put("_"+key, new BasicDBObject("$nin",e.getValue()));
					}else{
						command.put(key, new BasicDBObject("$nin",e.getValue()));
					}
				}
			}
		}
		return dbObjectToUser(coll.find(command).skip((page-1)*pageSize).limit(pageSize).toArray(), true);
	}
	
	/**
	 * Method name: dbObjectToUser <BR>
	 * Description: mongodb对象转换成ManageUser <BR>
	 * Remark: <BR>
	 * @param list
	 * @return
	 * @throws Exception  List<ManageUserBean><BR>
	 */
	private List<ManageUserBean> dbObjectToUser(List<DBObject> list, boolean flag) throws Exception {
		List<ManageUserBean> userList = new ArrayList<ManageUserBean>();
	
		for(DBObject o : list){
			ManageUserBean user = new ManageUserBean();
			Field[] fields = ManageUserBean.class.getDeclaredFields();
			for(Field field : fields){
				if (field.getName().equals("serialVersionUID")) {
					continue;
				}
				field.setAccessible(true);
				if(field.getType() == Integer.class){
					if(o.get(field.getName()) == null){
						field.set(user, 0);
					}else{
						int fieldValue = (int)Double.parseDouble(String.valueOf(o.get(field.getName())));
						field.set(user, fieldValue);
					}
				}else{
					if(Constant.MANAGE_USER_ID_FIELD.equals(field.getName())){
						field.set(user, String.valueOf(o.get("_id")));
					}else {
						field.set(user, (field.getType().cast(o.get(field.getName()))));
					}
				}
			}
			if(flag){
				//if(user.getCompanyId().equals(user.getSubCompanyId())){
				if(user.getDeptId() != 0){
					ManageDepartmentBean dept =  manageDepartmentDaoMapper.getManageDepartmentById(user.getDeptId());
					user.setDeptName(dept == null ?"":dept.getName());
				}
				//}
				if(user.getPostId() != 0){
					ManagePostBean post = manageParamService.selectManagePostById(user.getPostId());
					user.setPostName(post == null ?"":post.getName());
				}
				if(user.getCompanyId() != 0){
					ManageCompanyBean company = manageCompanyService.selectCompanyById(user.getCompanyId());
					user.setCompanyName(company.getName());
					if(user.getCompanyId().equals(user.getSubCompanyId())){
						user.setSubCompanyName(company.getName());
					}else{
						ManageDepartmentBean subCompany =  manageDepartmentDaoMapper.getManageDepartmentById(user.getSubCompanyId());
						user.setSubCompanyName(subCompany == null?"":subCompany.getName());
					}
				}
			}
			userList.add(user);
		}
		return userList;
	}
	
	/**
	 * @Override
	 * Method name: findUserByUserName <BR>
     * Description: 根据登录名称查询用户 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param name
	 * @return  <BR>
	*/
	@Override
	public ManageUserBean findUserByUserName(String userName, Integer companyId) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		if(companyId != null){
			map.put("companyId", companyId);
		}
		List<ManageUserBean> list = findUserByCondition(map);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
	/**
	 * Method name: getNextSequence <BR>
	 * Description: 获取自增id <BR>
	 * Remark: <BR>
	 * @return  int<BR>
	 */
	public int getNextSequence(){
		ManageUserIdSeqBean bean = new ManageUserIdSeqBean();
		manageUserDaoMapper.getManageUserIdSeq(bean);
		return bean.getId();
	}
	
	/**
	 * 增加用户角色
	 */
	@Override
	public void addUserRole(ManageUserRoleBean manageUserRole) throws Exception {
		manageUserDaoMapper.addUserRole(manageUserRole);
	}
	
	/**
	 * 删除某个用户的所有角色
	 */
	@Override
	public void deleteUserRoleByUserId(String userId) throws Exception {
		manageUserDaoMapper.deleteUserRoleByUserId(userId);
	}
	
	/**
	 * 删除某个用户的某个角色
	 */
	@Override
	public void deleteUserRole(String userId, String roleId) throws Exception {
		manageUserDaoMapper.deleteUserRole(userId, roleId);
	}
	
	/**
	 * 根据用户获取角色id
	 */
	@Override
	public List<Integer> getRoleIdByUserId(String userId) throws Exception {
		return manageUserDaoMapper.getRoleIdByUserId(userId);
	}
	
	/**
	 * 根据用户获取角色
	 */
	@Override
	public List<ManageRoleBean> getRoleByUserId(String userId) throws Exception{
		return manageUserDaoMapper.getRoleByUserId(userId);
	}
	
	
	/**
	 * 删除某角色的所有用户 
	 */
	@Override
	public void deleteUserRoleByRole(String roleId) throws Exception {
		manageUserDaoMapper.deleteUserRoleByRole(roleId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageUserService#findUserByIds(java.util.List) <BR>
	 * Method name: findUserByIds <BR>
	 * Description: 根据id列表返回人员列表 <BR>
	 * Remark: <BR>
	 * @param ids
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManageUserBean> findUserByIds(List<String> ids) throws Exception {
		List<ManageUserBean> list = new ArrayList<ManageUserBean>();
		for(String id : ids){
			list.add(findUserById(id));
		}
		return list;
	}

	/**
	 * Method name: deleteSubManagePageByUserId <BR>
	 * Description: 修改公司权限，删除该公司子用户缺少的权限 <BR>
	 * Remark: <BR>
	 * @param roleId  void<BR>
	 */
	@Override
	public void deleteSubManagePageByUserId(long roleId)  throws Exception {
		manageUserDaoMapper.deleteSubManagePageByUserId(roleId);
	}

	@Override
	public List<String> findUserIdByRoleName(Map<String, Object> map) {
		return manageUserDaoMapper.findUserIdByRoleName(map);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageUserService#exportStudent(java.util.List) <BR>
	 * Method name: exportStudent <BR>
	 * Description: 导出学生excel <BR>
	 * Remark: <BR>
	 * @param list
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public HSSFWorkbook exportStudent(List<ManageUserBean> list)
			throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "学生列表");
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");

		HSSFDataFormat dataFormat = workbook.createDataFormat();
		// 单元格数据样式准备设置
		HSSFCellStyle leftStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_LEFT);// 左
		HSSFCellStyle centerStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_CENTER);// 中
		HSSFCellStyle rightStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_RIGHT);// 右
		HSSFCellStyle wrapStyle = MyExcelHelp.createWrapCellStyle(workbook, font, CellStyle.ALIGN_LEFT, true);// 单元格内容自动换行
		HSSFCellStyle dateStyle = MyExcelHelp.createFormatCellStyle(workbook, font, CellStyle.ALIGN_RIGHT, dataFormat, MyExcelHelp.DATE_FORMAT);
		HSSFCellStyle moneyStyle = MyExcelHelp.createFormatCellStyle(workbook, font, CellStyle.ALIGN_RIGHT, dataFormat,MyExcelHelp. MONEY_FORMAT);
		// 标题行
		HSSFRow row = sheet.createRow(0);
		int j = 0;
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "用户名");
		sheet.setColumnWidth(0, 30 * 128);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "姓名");
		sheet.setColumnWidth(1, 30 * 128);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "性别");
		sheet.setColumnWidth(2, 20 * 128);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "出生日期");
		sheet.setColumnWidth(3, 50 * 128);
		MyExcelHelp.createStringCell(row, 4, centerStyle, "身份证号");
		sheet.setColumnWidth(4, 100 * 128);
		MyExcelHelp.createStringCell(row, 5, centerStyle, "地址");
		sheet.setColumnWidth(5, 100 * 128);
		MyExcelHelp.createStringCell(row, 6, centerStyle, "手机号码");
		sheet.setColumnWidth(6, 50 * 128);
		MyExcelHelp.createStringCell(row, 7, centerStyle, "电子邮箱");
		sheet.setColumnWidth(7, 50 * 128);
		MyExcelHelp.createStringCell(row, 8, centerStyle, "公司名称");
		sheet.setColumnWidth(8, 50 * 128);
		MyExcelHelp.createStringCell(row, 9, centerStyle, "部门");
		sheet.setColumnWidth(9, 50 * 128);
		MyExcelHelp.createStringCell(row, 10, centerStyle, "岗位");
		sheet.setColumnWidth(10, 50 * 128);
		MyExcelHelp.createStringCell(row, 11, centerStyle, "状态");
		sheet.setColumnWidth(11, 30 * 128);
//		MyExcelHelp.createStringCell(row, 7, centerStyle, "角色");
//		sheet.setColumnWidth(12, 10 * 128);
		
		
		for (j = 0; j < list.size(); j++) {// 控制行
			ManageUserBean bean = list.get(j);
			row = sheet.createRow(j+1);
			int colIndex = 0;
//			String status = "";
//			if(bean.getStatus() == null){
//				
//			}else if (bean.getStatus() ==1){
//				status = "正常";
//			}else{
//				status = "冻结";
//			}
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, bean.getUserName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, bean.getName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, String.valueOf(bean.getSex() == 1 ? "男" : "女"));
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,String.valueOf(bean.getBirthDay()));
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle,bean.getIdCard());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, bean.getAddress());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle,bean.getMobile());
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle,bean.getEmail());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,bean.getCompanyName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,bean.getDeptName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,bean.getPostName());
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,(bean.getStatus() == 1 ?"正常":"冻结"));
//			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle,bean.getDescr());
		}
		return workbook;
	}

	@Override
	public int checkStudentStudyRecord(int id) {
		return manageUserDaoMapper.checkStudentStudyRecord(id);
	}



}
