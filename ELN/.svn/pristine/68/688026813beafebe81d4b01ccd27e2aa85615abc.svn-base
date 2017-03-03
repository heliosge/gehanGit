<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>共享</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>


<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>

</head>
<body style="overflow-x:hidden;">
 <div  class='content'>	
		<!-- <h3>共享</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">共享</span>
		 </div>
	    <div class="lesson_tab_1">
	        	<ul>
	            	<li class="li_this">课程</li>
	                <li class="">知识</li>
	                <li class="">专题</li>
	            </ul>
		</div>
	    	<div class="content_2" style="display: block;">
	            
	            <div class="search_3" style='width: 100%;' id='shareCourse'>
	                <p>	
	                     课程名称：
	                    <input type="text" id="courseName" name="courseName">
	                    
	                    课程编号：
	                    <input type="text" id="courseCode" name="courseCode">
	                	<c:if test="${isCompany=='true'}">
		                    所属公司：
		                    <select id="courseCompanyId" name="courseCompanyId">
		                    	<option value="-1">全部显示</option>
		                    	<c:forEach var ="item" items="${companyArray}">
		                    	   <option value="${item.id }">${item.name}</option>
		                    	</c:forEach>
		                    </select>
	                   </c:if>
	                </p>
	                <input type="button" value="重置" onclick="resetCourse()">
	                <input type="button" value="查询" class="btn_cx" onclick="searchCourse()">
	    
	            </div>
	                <div class="search_3" style='width: 100%;display:none' id='shareKnowledge'>
	                <p>	
	                     知识名称：
	                    <input type="text" name="knowledgeName" id="knowledgeName">
	                    
	                    知识分类：
	                    <input type="text" name="categoryName" id="categoryName">
	                <c:if test="${isCompany=='true'}">
	                
	                    所属公司：
	                    <select id="knowledgeCompanyId" name="knowledgeCompanyId">
	                    	<option value="-1">全部显示</option>
	                    	<c:forEach var ="item" items="${companyArray}">
		                    	   <option value="${item.id }">${item.name}</option>
		                    	</c:forEach>
	                    </select>
	                   </c:if>
	                </p>
	                <input type="button" value="重置" onclick="resetKnowledge()">
	                <input type="button" value="查询" class="btn_cx" onclick="searchKnowledge()">
	    
	            </div>
	                <div class="search_3" style='width: 100%;display:none' id='shareTopic'>
	                <p>	
	                     专题名称：
	                    <input type="text" id="topicName" name="topicName">
	                    
	                    专题编号：
	                    <input type="text" id="topicNo" name="topicNo">
	                <c:if test="${isCompany=='true'}">
	                    所属公司：
	                    <select id="topicCompanyId" name="topicCompanyId">
	                    	<option value="-1">全部显示</option>
	                    	<c:forEach var ="item" items="${companyArray}">
		                    	   <option value="${item.id }">${item.name}</option>
		                    	</c:forEach>
	                    </select>
	                   </c:if>
	                </p>
	                <input type="button" value="重置" onclick="resetTopic()">
	                <input type="button" value="查询" class="btn_cx" onclick="searchTopic()">
	    
	            </div>
	            <div class="tab_" style='color:#000;width:100%'>
				<div id="course" class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
						
					<div style="margin:3px 10px 10px 10px;"></div>
					
					<div id="exampleTable" style="margin-top:10px;" ></div>
					<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
				</div>
				<div id="knowledge" class="first_div" style="display:none;border-right:none;border-bottom:none;border-left:none;margin:0px">
						
					<div style="margin:3px 10px 10px 10px;"></div>
					
					<div id="knowledgeTable" style="margin-top:10px;" ></div>
					<div id="knowledgePaginator11-1" align="right" style="margin-right:10px;" ></div>
				</div>
				<div id="topic" class="first_div" style="display:none;border-right:none;border-bottom:none;border-left:none;margin:0px">
						
					<div style="margin:3px 10px 10px 10px;"></div>
					
					<div id="topicTable" style="margin-top:10px;" ></div>
					<div id="topicPaginator11-1" align="right" style="margin-right:10px;" ></div>
				</div>
	         </div> 
	   </div>
   </div>
<script type="text/javascript">


var isCompany = "${isCompany}";

$(function(){
	//if(isCompany != "true"){ // 子公司
	//	initCourseGrid();
	//}else{// 租赁企业
		$("div.lesson_tab_1").find("ul li").eq(0).hide();// 隐藏课程标签页
		$("div.lesson_tab_1").find("ul li").eq(1).attr('class','li_this');
		initKlGrid();
		// 课程搜索条件 和gridDIV隐藏
		$("div.search_3").hide().eq(0).hide();
		$("div.first_div").hide().eq(0).hide();
		// 知识搜索条件 和gridDIV显示
		$("div.search_3").hide().eq(1).show();
		$("div.first_div").hide().eq(1).show();
	//}
	
});

function initCourseGrid(){
	if(isCompany != "true"){// 租赁企业
		//表格
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/approve/getShareCourseList.action",
	    	width: 'auto',
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	 		checkCol: false,
	        multiSelect: false,
	        indexCol: true,  //索引列
	        params:function(){
	        	var param = new Object();
	        	
	         	param.name = escapeForSql($.trim($("[name='courseName']").val()));
	        	param.code = escapeForSql($.trim($("[name='courseCode']").val()));
	        	if($("[name='courseCompanyId']").val()!=-1){
		        	param.subCompanyId = $("[name='courseCompanyId']").val();
	        	} 
	        	return param;
	        },
	        cols: [{title: 'ID', align:'center',name: 'id', width:60, hidden:true},
				   {title: '课程名称', align:'center',name: 'name', width:50,renderer:function(val, item, rowIndex){
					   
					   return "<span onclick='detailCourse("+item.id+")'><a href='javascript:void'>"+val+"</a></span>";
				   }},
				   {title: '课程编号', align:'center',name: 'code', width:50},
				   {title: '课程体系', align:'center',name: 'categoryName', width:50, },
				   {title: '所属公司', align:'center',name: 'subCompanyId', width:50,renderer:function(val, item, rowIndex){
					   return _getUserCompanyName(item.companyId,item.subCompanyId);
				   	}  },
				   {title: '操作', align:'center',name: 'id', width:60, renderer:function(val, item, rowIndex){
					   var buttonStr = ""
			                if(isCompany!="true"){//子公司
							   if(item.shareStatus==1||item.shareStatus==3){
							  	 buttonStr += '<span class="span_btn" href="#" onclick="shareObj(1,'+val+","+item.shareStatus+","+item.subCompanyId+","+item.createUserId+')" >共享</span>'; 
							   }else{
								   buttonStr += '<span class="span_btn gray" >已共享</span>';
							   }
			                }else{
			                	if(item.shareStatus==4||item.shareStatus==6){
								  	 buttonStr += '<span class="span_btn" href="#" onclick="shareObj(1,'+val+","+item.shareStatus+","+item.subCompanyId+","+item.createUserId+')" >共享</span>'; 
								   }else{
									   buttonStr += '<span class="span_btn gray" >已共享</span>';
								   }	
			                }
					   
					   return "<div class='span_btus' >" + buttonStr + "</div>";
				   }}
	           ],
	        plugins : [
	        	$('#paginator11-1').mmPaginator({
	        		totalCountName: 'total',    //对应MMGridPageVoBean count
	        		page: 1,                    //初始页
	        		pageParamName: 'page',      //当前页数
	        		limitParamName: 'pageSize', //每页数量
	        		limitList: [10, 20, 40, 50]
	        	})
	        ]
	    });
	}
}

//查询
function searchCourse(){
	$('#exampleTable').mmGrid().load({"page":1});
}

//重置
function resetCourse(){
	
	$("[name='courseName']").val("");
	$("[name='courseCode']").val("");
    searchCourse()
}


function initKlGrid(){
	$('#knowledgeTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/approve/getShareKLList.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: false,
        multiSelect: false,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	
       		param.knowledgeName = escapeForSql($.trim($("[name='knowledgeName']").val()));
        	param.categoryName = escapeForSql($.trim($("[name='categoryName']").val()));
        	if($("[name='knowledgeCompanyId']").val()!=-1){
	        	param.subCompanyId = $("[name='knowledgeCompanyId']").val();
        	}  
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'knowledgeId', width:60, hidden:true},
			   {title: '知识名称', align:'center',name: 'knowledgeName', width:50,renderer:function(val, item, rowIndex){
				   
				   return "<span onclick='detailKnowledge("+item.knowledgeId+")'><a href='javascript:void'>"+val+"</a></span>";
			   }},
			   {title: '知识分类', align:'center',name: 'categoryName', width:50},
			   {title: '上传者', align:'center',name: 'createUserId', width:50, renderer:function(val, item, rowIndex){
				   return _getUserName(val);
			   	} },
			   {title: '所属公司', align:'center',name: 'subCompanyId', width:50,renderer:function(val, item, rowIndex){
				   return _getUserCompanyName(item.companyId,item.subCompanyId);
			   	} },
			   {title: '操作', align:'center',name: 'knowledgeId', width:60, renderer:function(val, item, rowIndex){
				   var buttonStr = ""
	                if(isCompany!="true"){//子公司
					   if(item.shareStatus==1||item.shareStatus==3){
					  	 buttonStr += '<span class="span_btn" href="#" onclick="shareObj(2,'+val+","+item.shareStatus+","+item.subCompanyId+","+item.createUserId+')" >共享</span>'; 
					   }else{
						   buttonStr += '<span class="span_btn gray" >已共享</span>';
					   }
	                }else{
                	   if(item.shareStatus==4||item.shareStatus==6){
						  	 buttonStr += '<span class="span_btn" href="#" onclick="shareObj(2,'+val+","+item.shareStatus+","+item.subCompanyId+","+item.createUserId+')" >共享</span>'; 
						   }else{
							   buttonStr += '<span class="span_btn gray" >已共享</span>';
						   }
	                }
				   
				   return "<div class='span_btus' >" + buttonStr + "</div>";
			   }}
           ],
        plugins : [
        	$('#knowledgePaginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
	
}


function initTopicGrid(){
	$('#topicTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/approve/getShareTopicList.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: false,
        multiSelect: false,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	
         	param.name = escapeForSql($.trim($("[name='topicName']").val()));
        	param.no = escapeForSql($.trim($("[name='topicNo']").val()));
        	if($("[name='topicCompanyId']").val()!=-1){
	        	param.subCompanyId = $("[name='topicCompanyId']").val();
        	}
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'teacherId', width:60, hidden:true},
			   {title: '专题名称', align:'center',name: 'name', width:50,renderer:function(val, item, rowIndex){
				   
				   return "<span onclick='detailTopic("+item.id+")'><a href='javascript:void'>"+val+"</a></span>";
			   }},
			   {title: '专题编号', align:'center',name: 'no', width:50},
			   {title: '上传者', align:'center',name: 'createUserId', width:50, renderer:function(val, item, rowIndex){
				   return _getUserName(val);
			   	}
			   },
			   {title: '所属公司', align:'center',name: 'subCompanyId', width:50, renderer:function(val, item, rowIndex){
				   return _getUserCompanyName(item.companyId,item.subCompanyId);
			   	}},
			   {title: '操作', align:'center',name: 'id', width:60, renderer:function(val, item, rowIndex){
				   var buttonStr = ""
	                if(isCompany!="true"){//子公司
					   if(item.shareStatus==1||item.shareStatus==3){
					  	 buttonStr += '<span class="span_btn" href="#" onclick="shareObj(3,'+val+","+item.shareStatus+","+item.subCompanyId+","+item.createUserId+')" >共享</span>'; 
					   }else{
						   buttonStr += '<span class="span_btn gray" >已共享</span>';
					   }
	                }else{
                	  if(item.shareStatus==4||item.shareStatus==6){
					  	 buttonStr += '<span class="span_btn" href="#" onclick="shareObj(3,'+val+","+item.shareStatus+","+item.subCompanyId+","+item.createUserId+')" >共享</span>'; 
					   }else{
						   buttonStr += '<span class="span_btn gray" >已共享</span>';
					   }
	                }
				   
				   return "<div class='span_btus' >" + buttonStr + "</div>";
			   }}
           ],
        plugins : [
        	$('#topicPaginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
}
//绑定click事件
$("div.lesson_tab_1").find("ul li").on("click",function(){
	var index = $(this).index();
	//点击切换时，需要进行以下事情
	//1、进行标签的切换
	$(this).siblings().removeClass("li_this");
	$(this).addClass("li_this");
	
	
	//2、页面切换
	$("div.search_3").hide().eq(index).show();
	$("div.first_div").hide().eq(index).show();

	//3、加载数据	
	jzData(index);
})
//加载数据
function jzData(index){
	
	
	if(index==1){
		initKlGrid();
	}else if(index==2){
		initTopicGrid();
	}
	
}

//查询知识
function searchKnowledge(){
	$('#knowledgeTable').mmGrid().load({"page":1});
}

//重置知识查询
function resetKnowledge(){
	
	$("[name='knowledgeName']").val("");
   	$("[name='categoryName']").val("");
    searchKnowledge()
}

//查询专题
function searchTopic(){
	$('#topicTable').mmGrid().load({"page":1});
}

//重置专题查询
function resetTopic(){
	
	$("[name='topicName']").val("");
   	$("[name='topicNo']").val("");
    searchTopic()
}

function shareObj(type,id,shareStatus,subCompanyId,crateUserId){
	//定义 type 1:课程，2知识。3专题
	var type = type;
	var confirmStr = "确认集团共享吗？";
	if(shareStatus>=4){
		confirmStr="确认普联共享吗？";
	}
	dialog.confirm(confirmStr,function(){
	
		$.ajax({
			type:"post",
			async:true,
			url:"<%=request.getContextPath()%>/approve/shareObj.action",
			data:{wayType:type,objectId:id,shareStatus:shareStatus,subCompanyId:subCompanyId,objectUserId:crateUserId},
			success:function(data){
				if(data=="SUCCESS"){
					dialog.alert("操作成功！",function(){
						if(1==type){
							searchCourse()
						}else if(2==type){
							searchKnowledge()
						}else{
							searchTopic();
						}
						
					});
				}else{
					dialog.alert("操作失败！");
				}
			}
		})
		
	});
}


//跳转课程详情
function detailCourse(id){
	//location = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+id;
	location = "<%=request.getContextPath()%>/approve/detailObj.action?type=1&id="+id;
}

//跳转知识详情
function detailKnowledge(id){
	
	//location = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+id;
	location = "<%=request.getContextPath()%>/approve/detailObj.action?type=2&id="+id;
	
}

//跳转专题详情
function detailTopic(id){
	
	//location = "<%=request.getContextPath()%>/oam/toOamTopicDetailPage.action?id"+id;
	location = "<%=request.getContextPath()%>/approve/detailObj.action?type=3&id="+id;

}


//同步获取用户姓名
function _getUserName(id){
	var userName =""
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/approve/getUserName.action",
		async:false,
		data:{userId:id},
		success:function(data){
			userName = data;	
		}
	})
	return userName;		
}
//同步获取用户的所属公司名称
function _getUserCompanyName(companyId,subCompanyId){
	
	var companyName ="";
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/approve/getUserCompanyName.action",
		async:false,
		data:{subCompanyId:subCompanyId,companyId:companyId},
		success:function(data){
			companyName=data;
		}
	})
	
	return companyName;
}
</script>
</body>
</html>
