<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>审批</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>

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

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>
<script type="text/javascript">

//var listTeacherBean = ${listTeacherBean}; //部门

$(function(){
	//表格
	var mmg = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/approve/getApproveCourseListByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: true,
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.name = escapeForSql($.trim($("[name='courseName']").val()));
        	param.code = escapeForSql($.trim($("[name='courseCode']").val()));
        	if($("[name='courseStatus']").val()!=-1){
        		if($("[name='courseStatus']").val()==4){
        			param.shareStatusString="4,5,6,7,8" 
        		}else{
		        	param.shareStatusString = $("[name='courseStatus']").val();
        		}
        	} 
        	if($("#companyId").val()!=-1){
        		param.companyId=$("#companyId").val();
        	}
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'id', width:60, hidden:true},
			   {title: '课程名称', align:'center',name: 'name', width:50,renderer:function(val, item, rowIndex){
				   
				   return "<span onclick=detailObj(1,"+item.id+")><a href='javascript:void'>"+val+"</a></span>";
			   }},
			   {title: '课程编号', align:'center',name: 'code', width:50,hidden:true},
			   {title: '课程体系', align:'center',name: 'categoryName', width:50},
			   {title: '状态', align:'center',name: 'shareStatus', width:50, renderer:function(val, item, rowIndex){
				   if(2==val){
					   return "待审批";
				   }else if(3==val){
					   return "审批驳回";
				   }
				   return "审批通过";
			   	} },
			   {title: '共享时间', align:'center',name: 'shareTime', width:50,renderer:function(val, item, rowIndex){
				   var newDate = new Date(val);
				   return formatDate(newDate,"yyyy-MM-dd");
			   	}  },
			   {title: '操作', align:'center',name: 'id', width:60, renderer:function(val, item, rowIndex){
				   var buttonStr = '<span class="span_btn" href="#" onclick="detailObj(1,'+val+')" >查看</span>';
					   if(item.shareStatus==2){
					  	 buttonStr += '<span class="span_btn" href="#" onclick="approveObj(1,'+val+","+item.shareStatus+","+item.subCompanyId+')" >审批</span>'; 
					   }else{
						   buttonStr += '<span class="span_btn gray" >审批</span>';
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
    
	//2、页面切换
	$("div.search_3").hide().eq(1).show();
	$("div.first_div").hide().eq(1).show();

	//3、加载数据	
	jzData(1);
	
	 mmg.on('loadSuccess', function(e, data){
         //这个事件需要在数据加载之前注册才能触发
         $("#course input.checkAll").on("click",function(){
 			if($(this).is(":checked")){
 				$.each($("#course input.mmg-check"),function(){
 					console.log($(this).closest("tr").index())
 					var rowIndex = $(this).closest("tr").index();
 					var item = mmg.row(rowIndex);
 					if(item&&item.shareStatus==2){
	 					mmg.select(rowIndex)
 					}else{
 						$(this).prop("checked",false);
 						$(this).closest("tr").removeClass("selected");
 					}
 				});
 			}
         });
     }).load();

	// 模型数据tree的set参数设置
	var treeSet = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
					isCopy: false,
					prev: false,
					next: false,
					inner: false,
					zIndex:1000
				}
			},
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId",
					rootPId: 0
				},
				key:{
					title: "name",
					name:"name"
				}
			},
			callback: {
				onClick:function (e, treeId, treeNode){
					if(treeNode.isParent){
						$("#companyId").val("-1");
					}else{
						$("#companyId").val(treeNode.id)
					}
					var index = $("div.lesson_tab_1").find("ul li.li_this").index();
					if(0==index){
						searchCourse()
					}else if(1==index){
						searchKnowledge()
					}else{
						searchTopic();
					}
				}
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom
			}
		};
	
	$.fn.zTree.init($("#companyTree"), treeSet,${subCompanyList});//初始化模型树
	var treeObj = $.fn.zTree.getZTreeObj("companyTree");
	treeObj.expandAll(true);
});

//查询
function searchCourse(){
	$('#exampleTable').mmGrid().load({"page":1});
}

//重置
function resetCourse(){
	
	$("[name='courseName']").val("");
	$("[name='courseCode']").val("");
    $("[name='courseStatus']").val(-1);
    searchCourse();
}


</script>
</head>
<body style="overflow-x:hidden;">
<input type="hidden" id="companyId">
	<div class="content">
		<!-- <h3>共享审批</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">共享审批</span>
		 </div>
	    <div class="lesson_tab_1">
	        	<ul>
	            	<li class="" style="display:none;">课程</li>
	                <li class="li_this">知识</li>
	                <li class="">专题</li>
	            </ul>
		</div>
	    <div class="content_2 " style="display: block;">
	        <div class="left_tree">
	            <h2>子公司结构</h2>
	            <%-- <p class="sch">
	            <input type="text">
	            <a href="#"><img src="<%=request.getContextPath()%>/images/img/ico_search.png"></a>
	            </p> --%>
	            <div id='companyTree' class='tree ztree' style="height: 480px;overflow: auto;">
	            </div>
	       
	        </div>
	        <div class="right_lesson">
	            
	            <div class="button_gr">
	                <input type="button" value="批量审批" class="btn_4" onclick="batchApprove()">
	            </div>
	          	<div class="content_2" style="display: block;">
	            
	            <div class="search_3" style='width: 100%;display:none;' id='shareCourse'>
	                <p>	
	                     课程名称：
	                    <input type="text" id="courseName" name="courseName">
	                    
	                 <!--    课程编号：
	                    <input type="text" id="courseCode" name="courseCode"> -->
	                
	                    状态：
	                    <select id="courseStatus" name="courseStatus">
	                    	<option value="-1">全部显示</option>
             		         <option value="2">待审批</option>
	                    	 <option value="3">审批拒绝</option>
	                    	 <option value="4">审批通过</option>
	                    </select>
	                   
	                </p>
	                <input type="button" value="重置" onclick="resetCourse()">
	                <input type="button" value="查询" class="btn_cx" onclick="searchCourse()">
	    
	            </div>
	                <div class="search_3" style='width: 100%;' id='shareKnowledge'>
	                <p>	
	                     知识名称：
	                    <input type="text" name="knowledgeName" id="knowledgeName">
	                    
	                    知识分类：
	                    <input type="text" name="categoryName" id="categoryName">
	                
	                     状态：
	                    <select id="knowledgeShareStatus" name="knowledgeShareStatus">
	                    	<option value="-1">全部显示</option>
             		         <option value="2">待审批</option>
	                    	 <option value="3">审批拒绝</option>
	                    	 <option value="4">审批通过</option>
	                    </select>
	                   
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
	                	 状态：
	                    <select id="topicShareStatus" name="topicShareStatus">
	                    	<option value="-1">全部显示</option>
             		        <option value="2">待审批</option>
	                    	 <option value="3">审批拒绝</option>
	                    	 <option value="4">审批通过</option>
	                    </select>
	                   
	                </p>
	                <input type="button" value="重置" onclick="resetTopic()">
	                <input type="button" value="查询" class="btn_cx" onclick="searchTopic()">
	    
	            </div>
	            <div  class="" style='color:#000;width:100%;float: left;'>
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
	   </div>
	   	<div title='审批' id='divedit' style='display:none'>
	 		审批理由<textarea rows="4" cols="40"  id='reason'  maxlength='200'></textarea>
	 		<div class='error' style='display: none;color:red'>请输入理由</div>
 		</div>
   </div>
   <script type="text/javascript">
   var mmt=null,mmk=null;//定义表格对象
   $("#reason").on("focus",function(){
		$("#divedit").find("div.error").hide()
				
	})
   
 	//绑定click事件
	$("div.lesson_tab_1").find("ul li").on("click",function(){
		
		 var treeObj = $.fn.zTree.getZTreeObj("companyTree");
		 treeObj.cancelSelectedNode();
		 $("#companyId").val(-1);
		 
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
			
			//表格
			if(mmk){
				return;
			}
		  mmk=	$('#knowledgeTable').mmGrid({
		    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
				url:"<%=request.getContextPath()%>/approve/getApproveKLListByMap.action",
		    	width: 'auto',
		    	height: 'auto',
		    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		    	showBackboard: false,
		 		checkCol: true,
		        multiSelect: true,
		        indexCol: false,  //索引列
		        params:function(){
		        	var param = new Object();
		        	
		       		param.knowledgeName = escapeForSql($.trim($("[name='knowledgeName']").val()));
		        	param.categoryName = escapeForSql($.trim($("[name='categoryName']").val()));
		        	if($("[name='knowledgeShareStatus']").val()!=-1){
		        		if($("[name='knowledgeShareStatus']").val()==4){
		        			param.shareStatusString="4,5,6,7,8" 
		        		}else{
				        	param.shareStatusString = $("[name='knowledgeShareStatus']").val();
		        		}
		        	}  
		        	if($("#companyId").val()!=-1 && $("#companyId").val() != ''){
		        		param.companyId=$("#companyId").val();
		        	}
		        	return param;
		        },
		        cols: [{title: 'ID', align:'center',name: 'knowledgeId', width:60, hidden:true},
					   {title: '知识名称', align:'center',name: 'knowledgeName', width:50,renderer:function(val, item, rowIndex){
						   
						   return "<span onclick='detailKnowledge("+item.knowledgeId+")'><a href='javascript:void'>"+val+"</a></span>";
					   }},
					   {title: '知识分类', align:'center',name: 'categoryName', width:50},
					   {title: '状态', align:'center',name: 'shareStatus', width:50, renderer:function(val, item, rowIndex){
						   if(2==val){
							   return "待审批";
						   }else if(3==val){
							   return "审批驳回";
						   }
						   return "审批通过";
					   	} },
					   {title: '共享时间', align:'center',name: 'shareTime', width:50,renderer:function(val, item, rowIndex){
						   var newDate = new Date(val);
						   return formatDate(newDate,"yyyy-MM-dd");
					   	}  },
					   {title: '操作', align:'center',name: 'knowledgeId', width:60, renderer:function(val, item, rowIndex){
						   var buttonStr = '<span class="span_btn" href="#" onclick="detailObj(2,'+val+')" >查看</span>';
							   if(item.shareStatus==2){
							  	 buttonStr += '<span class="span_btn" href="#" onclick="approveObj(2,'+val+","+item.shareStatus+","+item.subCompanyId+')" >审批</span>'; 
							   }else{
								   buttonStr += '<span class="span_btn gray" >审批</span>';
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
			
		 mmk.on('loadSuccess', function(e, data){
	         //这个事件需要在数据加载之前注册才能触发
	         $("#knowledge input.checkAll").on("click",function(){
	 			if($(this).is(":checked")){
	 				$.each($("#knowledge input.mmg-check"),function(){
	 					var rowIndex = $(this).closest("tr").index();
	 					var item = mmk.row(rowIndex);
	 					if(item&&item.shareStatus==2){
		 					mmk.select(rowIndex)
	 					}else{
	 						$(this).prop("checked",false);
	 						$(this).closest("tr").removeClass("selected");
	 					}
	 				});
	 			}
	         });
	     }).load();
		}else if(index==2){
			
			//表格
		if(mmt){
			return;
		}
		  mmt =	$('#topicTable').mmGrid({
		    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
				url:"<%=request.getContextPath()%>/approve/getApproveTopicListByMap.action",
		    	width: 'auto',
		    	height: 'auto',
		    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		    	showBackboard: false,
		 		checkCol: true,
		        multiSelect: true,
		        indexCol: false,  //索引列
		        params:function(){
		        	var param = new Object();
		        	
		         	param.name = escapeForSql($.trim($("[name='topicName']").val()));
		        	param.no = escapeForSql($.trim($("[name='topicNo']").val()));
		        	if($("[name='topicShareStatus']").val()!=-1){
		        		if($("[name='topicShareStatus']").val()==4){
		        			param.shareStatusString="4,5,6,7,8" 
		        		}else{
				        	param.shareStatusString = $("[name='topicShareStatus']").val();
		        		}
		        	}
		        	if($("#companyId").val()!=-1){
		        		param.companyId=$("#companyId").val();
		        	}
		        	return param;
		        },
		        cols: [{title: 'ID', align:'center',name: 'id', width:60, hidden:true},
					   {title: '专题名称', align:'center',name: 'name', width:50,renderer:function(val, item, rowIndex){
						   
						   return "<span onclick='detailTopic("+item.id+")'><a href='javascript:void'>"+val+"</a></span>";
					   }},
					   {title: '专题编号', align:'center',name: 'no', width:50},
					   {title: '状态', align:'center',name: 'shareStatus', width:50, renderer:function(val, item, rowIndex){
						   if(2==val){
							   return "待审批";
						   }else if(3==val){
							   return "审批驳回";
						   }
						   return "审批通过";
					   	} },
					   {title: '共享时间', align:'center',name: 'shareTime', width:50,renderer:function(val, item, rowIndex){
						   var newDate = new Date(val);
						   return formatDate(newDate,"yyyy-MM-dd");
					   	}  },
					   {title: '操作', align:'center',name: 'id', width:60, renderer:function(val, item, rowIndex){
						   var buttonStr = '<span class="span_btn" href="#" onclick="detailObj(3,'+val+')" >查看</span>';
							   if(item.shareStatus==2){
							  	 buttonStr += '<span class="span_btn" href="#" onclick="approveObj(3,'+val+","+item.shareStatus+","+item.subCompanyId+')" >审批</span>'; 
							   }else{
								   buttonStr += '<span class="span_btn gray" >审批</span>';
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
				
			 mmt.on('loadSuccess', function(e, data){
		         //这个事件需要在数据加载之前注册才能触发
		         $("#topic input.checkAll").on("click",function(){
		 			if($(this).is(":checked")){
		 				$.each($("#topic input.mmg-check"),function(){
		 					var rowIndex = $(this).closest("tr").index();
		 					var item = mmt.row(rowIndex);
		 					if(item&&item.shareStatus==2){
			 					mmt.select(rowIndex)
		 					}else{
		 						$(this).prop("checked",false)
 								$(this).closest("tr").removeClass("selected");
			 					//mmt.deselect($(this).index())
		 					}
		 				});
		 			}
		         });
		     }).load();
			
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
 	 	$("[name='knowledgeShareStatus']").val("-1");

	    var treeObj = $.fn.zTree.getZTreeObj("companyTree");
		 treeObj.cancelSelectedNode();
		 $("#companyId").val(-1);
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
 	 	$("[name='topicShareStatus']").val("-1");
	    var treeObj = $.fn.zTree.getZTreeObj("companyTree");
		 treeObj.cancelSelectedNode();
		 $("#companyId").val(-1);
	    searchTopic()
	}
	
	//详情
	function detailObj(type,id){
		if(1==type){
			detailCourse(id)
		}else if(2==type){
			detailKnowledge(id)
		}else{
			detailTopic(id)
		}
	}
	
	//审批
	function approveObj(type,id,shareStatus,subCompanyId){
		
		$("#reason").val("");
		var callBack = function(isPass){
			var param = {};
			param.wayType=type;
			param.isPass=isPass;
			param.objectId=id;
			param.shareStatus=shareStatus;
			param.subCompanyId=subCompanyId;
			param.reason=$("#reason").val();
			//定义 type 1:课程，2知识。3专题
			$.ajax({
				type:"post",
				async:true,
				url:"<%=request.getContextPath()%>/approve/approveObj.action",
				data:param,
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
		};
		
		//打开设置框
		var d =dialog({
			title: "审批",
			align: 'left',
			width:360,
			height:200,
		    content:$("#divedit") ,
		    button: [  { value: '拒绝', callback: function (i, d) {
		    	if($.trim($("#reason").val())!=""){
					callBack(0);
		    	}else{
		    		$("#divedit").find("div.error").show();
		    		return false;
		    	}
				}},
	        { value: '通过', callback: function (i, d) {
	        	callBack(1);
	        }}
	    	 ] 
		})
		d.showModal(); 
	}
	
	
	//批量审批
	function batchApprove(){
		var index = $("div.lesson_tab_1").find("ul li.li_this").index();
		var rows,type;
		if(index==0){
			 rows = $('#exampleTable').mmGrid().selectedRows();
			 type=1;//课程
		}else if(index==1){
			rows = $('#knowledgeTable').mmGrid().selectedRows();
			 type=2;//知识
		}else{
			rows = $('#topicTable').mmGrid().selectedRows();
			 type=3;//专题
		}
		
		if(rows.length==0){
			dialog.alert("请选择数据");			
			return;
		}
		
		for(var i=0;i<rows.length;i++){
			if(rows[i].shareStatus!=2){
				dialog.alert("存在已经审批过的内容");
				return ;
			}
		}
		
		$("#reason").val("");
		var callBack = function(isPass){
			
			//循环调用审批的方法
			var size = rows.length;
			var arr =[];
			for(var i=0;i<rows.length;i++){
				
				var param = {};
				param.wayType=type;
				param.isPass=isPass;
				param.objectId=rows[i].knowledgeId||rows[i].id;
				param.shareStatus=rows[i].shareStatus;
				param.subCompanyId=rows[i].subCompanyId;
				param.crateUserId=rows[i].crateUserId;
				param.reason=$("#reason").val();
				//定义 type 1:课程，2知识。3专题
				$.ajax({
					type:"post",
					async:true,
					url:"<%=request.getContextPath()%>/approve/approveObjByPL.action",
					data:param,
					success:function(data){
						arr.push(data);
						if(arr.length==size){
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
					}
				})
			}
		};
		
		//打开设置框
		var d =dialog({
			title: "审批",
			align: 'left',
			width:360,
			height:200,
		    content:$("#divedit") ,
		    button: [  { value: '拒绝', callback: function (i, d) {
		    	if($.trim($("#reason").val())!=""){
					callBack(0);
		    	}else{
		    		$("#divedit").find("div.error").show();
		    		return false;
		    	}
				}},
	        { value: '通过', callback: function (i, d) {
	        	callBack(1);
	        }}
	    	 ] 
		});
		d.showModal(); 
	}
	
	//跳转课程详情
	function detailCourse(id){
		location = "<%=request.getContextPath()%>/approve/objDetail.action?type=1&id="+id;
	}
	
	//跳转知识详情
	function detailKnowledge(id){
		
		location = "<%=request.getContextPath()%>/approve/objDetail.action?type=2&id="+id;
	}
	
	//跳转专题详情
	function detailTopic(id){
		
		location = "<%=request.getContextPath()%>/approve/objDetail.action?type=3&id="+id;
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
	
	  //格式化日期,
    function formatDate(date,format){
      var paddNum = function(num){
        num += "";
        return num.replace(/^(\d)$/,"0$1");
      }
      //指定格式字符
      var cfg = {
         yyyy : date.getFullYear() //年 : 4位
        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
        ,d  : date.getDate()   //日 : 如果1位的时候不补0
        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
        ,hh : date.getHours()  //时
        ,mm : date.getMinutes() //分
        ,ss : date.getSeconds() //秒
      }
      format || (format = "yyyy-MM-dd hh:mm:ss");
      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    } 
   </script>
</body>
</html>
