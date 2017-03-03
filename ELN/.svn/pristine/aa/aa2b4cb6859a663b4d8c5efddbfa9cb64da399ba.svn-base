package com.jftt.elasticsearch.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jftt.elasticsearch.bean.TermQuery;
import com.jftt.elasticsearch.common.ElasticConstant.QueryType;



public class AnalyzerUtil {
	
	//分词器，这里我们使用默认的分词器,标准分析器（好几个，但对中文的支持都不好）
	private static Analyzer analyzer = null;

	private static Analyzer iKAnalyzer = null;
	
	
	private static Analyzer getAnalyzer() {  
        if (analyzer == null) {  
            analyzer = new StandardAnalyzer();
        }  
        return analyzer;  
    }
	
	private static Analyzer getIKAnalyzer() {  
        if (iKAnalyzer == null) {  
        	iKAnalyzer = new IKAnalyzer();
        }  
        return iKAnalyzer;  
    }
	
	public static JSONObject searchObj(String[] filters, List<TermQuery> termQueryList){
		
		JSONObject back = new JSONObject();
		back.put("bool", back);
		
		if(filters !=null && filters.length > 0){
			//过滤器
			String filterString = "";
			
			for (String filter : filters) {
				
				filterString += " AND " + filter;
			}
			
			JSONObject query_string = new JSONObject();
			query_string.put("query", filterString.substring(5));
			
			JSONObject query = new JSONObject();
			query.put("query_string", query_string);
			
			back.getJSONObject("bool").accumulate("must", query);
		}
		
		if(termQueryList !=null && !termQueryList.isEmpty()){
			
			for (TermQuery termQuery : termQueryList) {
				
				//整体查询  满足整个字符串(采用分词  按符号，空格，分开)
				if(termQuery.getValue() !=null && !termQuery.getValue().equals("")){
					
					List<String> simpleAnalyzerList = getStandarAnalyzer(termQuery.getValue());
					
					//词组查询  满足字符串所包含的词组
					//IK分词结果,安字典分开
					List<String> ikAnalyzerList = getIKAnalyzer(termQuery.getValue());  
					
					ikAnalyzerList.addAll(simpleAnalyzerList);
					
					String queryString = "";
					Map<String, String> keyMap = new HashMap<String, String>();
					
					if(ikAnalyzerList != null && !ikAnalyzerList.isEmpty()){
						
						for (int i=0; i<ikAnalyzerList.size(); i++) {
							
							String keyString = "";
							
							if(!keyMap.containsKey(ikAnalyzerList.get(i))){
								
								if(isNumeric(ikAnalyzerList.get(i))){
									//纯数字
									keyString = ""+ikAnalyzerList.get(i)+"";
								}else if(isEnglisg(ikAnalyzerList.get(i))){
									//纯英文
									keyString = ""+ikAnalyzerList.get(i)+"";
								}else{
									keyString = "\"" + ikAnalyzerList.get(i) + "\"~0";   //每个字之间最多间隔0个字符
								}
								
								keyMap.put(ikAnalyzerList.get(i), ikAnalyzerList.get(i));
							}
							
							if(!keyString.equals("")){
								
								if(queryString.equals("")){
									queryString = keyString;
								}else{
									queryString += " OR " + keyString;
								}
							}
						}
						
						System.out.println(queryString);
					}
					
					JSONObject query_string = new JSONObject();
					if(termQuery.getFiled() !=null && !termQuery.getFiled().equals("")){
						String[] queryFileds = termQuery.getFiled().split(",");
						query_string.put("fields", JSONArray.fromObject(queryFileds));
					}
					
					query_string.put("query", queryString);
					if(termQuery.getBoost() !=null){
						query_string.accumulate("boost", termQuery.getBoost());
					}
					
					JSONObject query = new JSONObject();
					query.put("query_string", query_string);
					
					back.getJSONObject("bool").accumulate(termQuery.getType(), query);
				}
				
			}
		}
		
		return back;
    }
    
	public static String searchString(List<TermQuery> termQueryList){
		
		String backStr = "";
		
		if(termQueryList !=null && !termQueryList.isEmpty()){
			
			for (TermQuery termQuery : termQueryList) {
				
				String boost = "";
				if(termQuery.getBoost() !=null){
					boost = "^" + termQuery.getBoost();
				}
				
				//整体查询  满足整个字符串(采用分词  按符号，空格，分开)
				List<String> simpleAnalyzerList = getStandarAnalyzer(termQuery.getValue());
				String allKeyString = "";
				
				if(simpleAnalyzerList != null && !simpleAnalyzerList.isEmpty()){

					for(int i=0; i<simpleAnalyzerList.size(); i++){
						
						String keyString = "";
						
						if(isNumeric(simpleAnalyzerList.get(i))){
							//纯数字
							keyString = "*"+simpleAnalyzerList.get(i)+"*";
						}else if(isEnglisg(simpleAnalyzerList.get(i))){
							//纯英文
							keyString = "*"+simpleAnalyzerList.get(i)+"*";
						}else{
							keyString = "\"" + simpleAnalyzerList.get(i) + "\"~0";   //每个字之间最多间隔0个字符
						}
						
						if(allKeyString.equals("")){
							allKeyString = keyString + boost;
						}else{
							allKeyString += " OR " + keyString + boost;
						}
					}
					
					System.out.println(allKeyString);
				}
				
				//词组查询  满足字符串所包含的词组
				//IK分词结果,安字典分开
				List<String> ikAnalyzerList = getIKAnalyzer(termQuery.getValue());   
				String allKeyString2 = "";
				
				if(ikAnalyzerList != null && !ikAnalyzerList.isEmpty()){
					
					for (int i=0; i<ikAnalyzerList.size(); i++) {
						
						String key = "";
						
						if(ikAnalyzerList.get(i).matches("^[\u4E00-\u9FA5]+$")){
							//汉字
							key = "\"" + ikAnalyzerList.get(i) + "\"~0";   //每个字之间最多间隔0个字符
						}else{
							//允许通配符查询
							key = "*"+ikAnalyzerList.get(i)+"*";
						}
						
						if(allKeyString2.equals("")){
							allKeyString2 = key + boost;
						}else{
							allKeyString2 += " OR " + key + boost;
						}

					}
					
					System.out.println(allKeyString2);
				}
				
				if(termQuery.getType().equals(QueryType.must.getValue())){
					backStr += " AND (" + allKeyString + " OR " + allKeyString2 + ")";
				}else if(termQuery.getType().equals(QueryType.must_not.getValue())){
					backStr += " AND NOT (" + allKeyString + " OR " + allKeyString2 + ")";
				}else if(termQuery.getType().equals(QueryType.should.getValue())){
					backStr += " OR (" + allKeyString + " OR " + allKeyString2 + ")";
				}
			}
		}
		
		return backStr;
    }
    
    //的到按标准分词器分词得到的结果, 将连续的合并起来
    public static List<String> getStandarAnalyzer(String keyword){
    	
    	try {
    		
    		TokenStream tokenStream = getAnalyzer().tokenStream("content", new StringReader(keyword));
    		CharTermAttribute termAtt = (CharTermAttribute) tokenStream.getAttribute(CharTermAttribute.class);
    		
    		List<String> reList = new ArrayList<String>();
    		
    		int beforeAt = 0;              //前一个词的位置
    		String beforeText = "";        //前一个词的内容
    		 
    		String lianJie = beforeText;   //连续的词连接起来
     		
    		tokenStream.reset();
    		while (tokenStream.incrementToken()) {  
    			
    			//当前词位置
    			int nowAt = keyword.indexOf(termAtt.toString(), beforeAt);
			    
    			if((beforeAt + beforeText.length()) == nowAt){
    				//连续的
    				lianJie += termAtt.toString();
    			}else{
    				if(!lianJie.equals("")){
    					reList.add(lianJie);
    				}
    				lianJie = termAtt.toString();
    			}
    			
    			beforeAt = nowAt;
    			beforeText = termAtt.toString();
			}
    		tokenStream.close();
    		
    		//最后一个
    		reList.add(lianJie);
    		
    		return reList;
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		} 
    	
    }
    
    //的到按IK分词器分词得到的结果
    public static List<String> getIKAnalyzer(String keyword){
    	
    	try {
    		
    		TokenStream tokenStream = getIKAnalyzer().tokenStream("content", new StringReader(keyword));  
    		CharTermAttribute termAtt = (CharTermAttribute) tokenStream.getAttribute(CharTermAttribute.class);
    		
    		List<String> reList = new ArrayList<String>();
    		
    		/*// 其实字符和终止字符的偏移量  
            OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);  
            // 位置增量（默认为1）  
            PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);  
            // 语汇单元类型（默认为单词）  
            TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);  
    		
            int position = 0;*/
            
    		tokenStream.reset();
    		while (tokenStream.incrementToken()) {  
    			  
			    //if(termAtt.toString().length() > 1){
			    	reList.add(termAtt.toString());
			    //}
			    	
			    /*int increment = positionIncrementAttribute.getPositionIncrement();
		        // 打印所有语汇单元详细信息  
		        System.out.println("【Trem：" + termAtt.toString() + "】" + "【position: " + (position += increment) + "】" +
		        		"【StartOffset：" + offsetAttribute.startOffset() + "】【EndOffset：" + offsetAttribute.endOffset() + "】" +
		        		"【Type：" + typeAttribute.type()+"】");  */
			    
			}
    		tokenStream.close();
    		
    		return reList;
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		} 
    	
    }
    
    //判断字符串是否是数字
    public static boolean isNumeric(String str){ 
    	Pattern pattern = Pattern.compile("[0-9]*"); 
        return pattern.matcher(str).matches();    
    }
    
    //判断字符串是否是英文
    public static boolean isEnglisg(String str){ 
    	Pattern pattern = Pattern.compile("[a-zA-Z]*"); 
        return pattern.matcher(str).matches();    
    }
    
    //根据条件获得，分词查询条件
    public static String getSimpleAnalyzer(String keyword){
    	
    	//整体查询  满足整个字符串(采用分词  按符号，空格，分开)
		List<String> simpleAnalyzerList = getStandarAnalyzer(keyword);
		
		String allKeyString = "";
		
		if(simpleAnalyzerList != null && !simpleAnalyzerList.isEmpty()){
			
			for(int i=0; i<simpleAnalyzerList.size(); i++){
				
				String keyString = "";
				
				if(isNumeric(simpleAnalyzerList.get(i))){
					//允许通配符查询
					//queryParser.setAllowLeadingWildcard(true);
					//纯数字
					keyString = "*"+simpleAnalyzerList.get(i)+"*";
				}else if(isEnglisg(simpleAnalyzerList.get(i))){
					//允许通配符查询
					//queryParser.setAllowLeadingWildcard(true);
					//纯英文
					keyString = "*"+simpleAnalyzerList.get(i)+"*";
				}else{
					keyString = "\"" + simpleAnalyzerList.get(i) + "\"~0";   //每个字之间最多间隔0个字符
				}
				
				if(allKeyString.equals("")){
					allKeyString = keyString;
				}else{
					allKeyString += " OR " + keyString;
				}
			}
			
			System.out.println(allKeyString);
		}
		
		return allKeyString;
    }

}
