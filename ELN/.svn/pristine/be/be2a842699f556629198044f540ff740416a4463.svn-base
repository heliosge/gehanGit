/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ElastisearchUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016-2-29        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.util;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jftt.elasticsearch.bean.MappingProperty;
import com.jftt.elasticsearch.common.ElasticConstant.MappingIndex;
import com.jftt.elasticsearch.common.ElasticConstant.MappingStore;
import com.jftt.elasticsearch.common.ElasticConstant.MappingType;
import com.jftt.elasticsearch.util.ElastisearchUtil;
import com.jftt.elasticsearch.util.FileUtil;
import com.jftt.wifi.bean.KnowledgeBean;
import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.KnowledgeManagerDaoMapper;
import com.jftt.wifi.dao.PoliciesRulesDaoMapper;

/**
 * class name:ElastisearchUtil <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2016-2-29
 * @author JFTT)chenrui
 */
public class MyElastisearchUtil {

	protected static ApplicationContext context;
    static{
    	context = new ClassPathXmlApplicationContext("classpath:resourceConfig/springConfig/*.xml");
    }
	
	public static void main(String[] args) {
		
		MyElastisearchUtil test = new MyElastisearchUtil();
		
		//如果库存在，先删除库
		if(ElastisearchUtil.indexExists(Constant.ELASTICSEARCH_INDEX_knowledge)){
			
			ElastisearchUtil.indexDelete(Constant.ELASTICSEARCH_INDEX_knowledge);
		}
		
		ElastisearchUtil.indexCreate(Constant.ELASTICSEARCH_INDEX_knowledge);
		test.createKnowledge();
		test.readKnowledgeSources();
		
		//如果库存在，先删除库
		if(ElastisearchUtil.indexExists(Constant.ELASTICSEARCH_INDEX_rules)){
			
			ElastisearchUtil.indexDelete(Constant.ELASTICSEARCH_INDEX_rules);
		}	
		
		ElastisearchUtil.indexCreate(Constant.ELASTICSEARCH_INDEX_rules);
		test.createPoliciesRules();
		test.readRulesSources();
		
		System.out.println("END");
		System.exit(0);
	}
	
	private static void createKnowledge(){
		List<MappingProperty> propertyList = new ArrayList<MappingProperty>();
    	
    	MappingProperty knowledge_id = new MappingProperty("knowledge_id", MappingType.integer.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(knowledge_id);
    	
    	MappingProperty knowledge_name = new MappingProperty("knowledge_name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(knowledge_name);
    	
    	MappingProperty category_id = new MappingProperty("category_id", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(category_id);
    	
    	MappingProperty company_id = new MappingProperty("company_id", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(company_id);
    	
    	MappingProperty sub_company_id = new MappingProperty("sub_company_id", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(sub_company_id);
    	
    	MappingProperty is_open = new MappingProperty("is_open", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_open);
    	
    	MappingProperty is_download = new MappingProperty("is_download", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_download);
    	
    	MappingProperty is_recommend = new MappingProperty("is_recommend", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_recommend);
    	
    	MappingProperty is_publish = new MappingProperty("is_publish", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_publish);
    	
    	MappingProperty upload_type = new MappingProperty("upload_type", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(upload_type);
    	
    	MappingProperty status = new MappingProperty("status", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(status);
    	
    	MappingProperty tags = new MappingProperty("knowledge_name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(tags);
    	
    	MappingProperty knowledge_desc = new MappingProperty("knowledge_desc", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(knowledge_desc);
    	
    	MappingProperty refuse_memo = new MappingProperty("refuse_memo", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(refuse_memo);
    	
    	MappingProperty audit_time = new MappingProperty("audit_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(audit_time);
    	
    	MappingProperty is_delete = new MappingProperty("is_delete", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_delete);
    	
    	MappingProperty file_name = new MappingProperty("file_name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(file_name);
    	
    	MappingProperty file_size = new MappingProperty("file_size", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(file_size);
    	
    	MappingProperty file_path = new MappingProperty("file_path", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(file_path);
    	
    	MappingProperty extend_name = new MappingProperty("extend_name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(extend_name);
    	
    	MappingProperty resource_type = new MappingProperty("resource_type", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(resource_type);
    	
    	MappingProperty knowledge_text = new MappingProperty("knowledge_text", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(knowledge_text);
    	
    	MappingProperty create_user_id = new MappingProperty("create_user_id", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(create_user_id);

    	MappingProperty create_time = new MappingProperty("create_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(create_time);
    	
    	MappingProperty update_user_id = new MappingProperty("update_user_id", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(update_user_id);
    	
    	MappingProperty update_time = new MappingProperty("update_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(update_time);
    	
    	MappingProperty share_status = new MappingProperty("share_status", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(share_status);
    	
    	MappingProperty share_time = new MappingProperty("share_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(share_time);
    	
    	MappingProperty role_type = new MappingProperty("role_type", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(role_type);
    	
    	MappingProperty is_accident = new MappingProperty("is_accident", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_accident);
    	
    	MappingProperty file_content = new MappingProperty("file_content", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(file_content);
    	
    	ElastisearchUtil.mappingCreate(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", propertyList);
	}
	
	private static void createPoliciesRules(){
		
		List<MappingProperty> propertyList = new ArrayList<MappingProperty>();
    	
    	MappingProperty id = new MappingProperty("id", MappingType.integer.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(id);
    	
    	MappingProperty name = new MappingProperty("name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(name);
    	
    	MappingProperty category = new MappingProperty("category", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(category);
    	
    	MappingProperty publish_enterprise = new MappingProperty("publish_enterprise", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(publish_enterprise);
    	
    	MappingProperty publish_time = new MappingProperty("publish_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(publish_time);
    	
    	MappingProperty execute_time = new MappingProperty("execute_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(execute_time);
    	
    	MappingProperty reference_number = new MappingProperty("reference_number", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(reference_number);
    	
    	MappingProperty key_words = new MappingProperty("key_words", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(key_words);
    	
    	MappingProperty is_publish = new MappingProperty("is_publish", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_publish);
    	
    	MappingProperty timeliness = new MappingProperty("timeliness", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(timeliness);
    	
    	MappingProperty file_name = new MappingProperty("file_name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(file_name);
    	
    	MappingProperty file_size = new MappingProperty("file_size", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(file_size);
    	
    	MappingProperty file_path = new MappingProperty("file_path", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(file_path);
    	
    	MappingProperty extend_name = new MappingProperty("extend_name", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(extend_name);
    	
    	MappingProperty create_time = new MappingProperty("create_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(create_time);
    	
    	MappingProperty update_time = new MappingProperty("update_time", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), "yyyy-MM-dd HH:mm:ss", null);
    	propertyList.add(update_time);
    	
    	MappingProperty is_delete = new MappingProperty("is_delete", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.no.getValue(), null, null);
    	propertyList.add(is_delete);
    	
    	MappingProperty file_content = new MappingProperty("file_content", MappingType.string.getValue(), MappingStore.yes.getValue(), MappingIndex.yes.getValue(), null, null);
    	propertyList.add(file_content);
    	
    	ElastisearchUtil.mappingCreate(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules", propertyList);
	}
	//知识资源
	private static void readKnowledgeSources(){
		KnowledgeManagerDaoMapper knowledgeManagerDaoMapper = 
					 (KnowledgeManagerDaoMapper) context.getBean("knowledgeManagerDaoMapper");
		List<KnowledgeBean> kbList = knowledgeManagerDaoMapper.getAllByElastisearch();
		for (KnowledgeBean knowledgeBean : kbList) {
				String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(knowledgeBean.getCreateTime());
				String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(knowledgeBean.getUpdateTime());
				String shareTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(knowledgeBean.getShareTime());
				JSONObject rob = transKlObj(knowledgeBean);
				rob.put("createTime", createTime);
				rob.put("updateTime", updateTime);
				rob.put("shareTime", shareTime);
				ElastisearchUtil.dataAdd(Constant.ELASTICSEARCH_INDEX_knowledge, "kl_knowledge", knowledgeBean.getKnowledgeId()+"", rob);
			}
	}
	
	//政策法规
	private static void readRulesSources(){
		PoliciesRulesDaoMapper policiesRulesDaoMapper = 
					 (PoliciesRulesDaoMapper) context.getBean("policiesRulesDaoMapper");
		List<PoliciesRulesBean> ruleList = policiesRulesDaoMapper.getAllByElastisearch();
		for (PoliciesRulesBean policiesRulesBean : ruleList) {
				String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(policiesRulesBean.getCreateTime());
				String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(policiesRulesBean.getUpdateTime());
				String publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(policiesRulesBean.getPublishTime());
				String executeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(policiesRulesBean.getExecuteTime());
				
				JSONObject obj = transRuleObj(policiesRulesBean);
				obj.put("createTime", createTime);
				obj.put("updateTime", updateTime);
				obj.put("publishTime", publishTime);
				obj.put("executeTime", executeTime);
				ElastisearchUtil.dataAdd(Constant.ELASTICSEARCH_INDEX_rules, "policies_rules", policiesRulesBean.getId()+"", obj);
			}
	}
	
	public static JSONObject transKlObj(KnowledgeBean param){
		JSONObject obj = JSONObject.fromObject(param);
        String path = param.getFilePath();
		String extendName = param.getExtendName();// 获取后缀名称
		System.out.println("文件虚拟path=>" + path);
		if(path != null && !path.isEmpty()){
			String realPath = PropertyUtil.getConfigureProperties("UPLOAD_PATH") + path.substring(8, path.length());
			System.out.println("realPath=>" + realPath);
			String content  = "";
			if(extendName!=null){
				realPath = realPath.replace(".swf", "."+extendName);
				if("txt".equals(extendName)){
					content = FileUtil.readTxtFile(realPath, "gbk");
				}else{
					content = FileUtil.readAll(realPath);
				}
			}
			System.out.println("知识content=>"+content);
			obj.put("file_content", content);
		}
		return obj;
	}
	
	public static JSONObject transRuleObj(PoliciesRulesBean policiesRulesBean){
		JSONObject obj = JSONObject.fromObject(policiesRulesBean);
		String path = policiesRulesBean.getFilePath();
		String extendName = policiesRulesBean.getExtendName();// 获取后缀名称
		System.out.println("文件虚拟path=>" + path);
		if(path != null && !path.isEmpty()){
			path = path.replace(".swf", "."+extendName);
			String realPath = PropertyUtil.getConfigureProperties("UPLOAD_PATH") + path.substring(8, path.length());
			System.out.println("realPath=>" + realPath);
			String content  = "";
			if(extendName!=null){
				if("txt".equals(extendName)){
					content = FileUtil.readTxtFile(realPath, "gbk");
				}else{
					content = FileUtil.readAll(realPath);
				}
			}
			System.out.println("政策法规content=>"+content);
			obj.put("file_content", content);
		}
		return obj;
	}
}