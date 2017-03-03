<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>知识库管理</title>

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
 
 <!-- 弹出框 -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/knowledge.css"/>
<style type="text/css">

html, body{
	height: 100%;
}


</style>

<script type="text/javascript">

$(function(){
	
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
					idKey: "categoryId",
					pIdKey: "parentId",
					rootPId: 0
				},
				key:{
					title: "categoryName",
					name:"categoryName"
				}
			},
			callback: {
				beforeDrag: function(){
					
				},//在拖动前调用，用于检查父节点，返回如果为false，则不允许拖动
				onDrop: function(){
					
				}//将相关的数据节点拖动到对象上
				,
				onClick:function (e, treeId, treeNode){
					
					var ids = [];
					var fn =function(treeNode){
						ids.push(treeNode.categoryId);
						if(treeNode.isParent){
							var nodes = treeNode.children;
							for(var i=0;i<nodes.length;i++){
								if(nodes[i].isParent){
									fn(nodes[i]);
								}else{
									ids.push(nodes[i].categoryId);
								}
							}
						}
					}
					fn(treeNode)
					$("#categoryIds").val(ids.join(","))
					
					search();
				}
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom

			}
		};
	
	$.fn.zTree.init($("#categoryTree"), treeSet,${categoryList});//初始化模型树
	
	var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	var o_nodes = treeObj.getNodes();//.getNodesByParam("id", 1, null);
	if (o_nodes.length>0) {
		treeObj.expandNode(o_nodes[0], true, false, true);
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
        ,hh : paddNum(date.getHours())  //时
        ,mm : paddNum(date.getMinutes()) //分
        ,ss : paddNum(date.getSeconds()) //秒
      }
      format || (format = "yyyy-MM-dd hh:mm:ss");
      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    } 
	  
    function initDatepicker() {
    	$("#beginTime").datepicker({
    		dateFormat : 'yy-mm-dd',
    		  changeMonth: true,
       	      changeYear: true,
   			 onClose: function( selectedDate ) {
   		        $( "#endTime" ).datepicker( "option", "minDate", selectedDate );
   		      }
    	});

    	$("#endTime").datepicker({
    		dateFormat : 'yy-mm-dd',
    		  changeMonth: true,
       	      changeYear: true,
   			 onClose: function( selectedDate ) {
   		        $( "#beginTime" ).datepicker( "option", "maxDate", selectedDate );
   		      }
    	});
    }
    initDatepicker();
    
  //表格
	var mm = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/knowledge/getAuditKLListByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        checkCol: true,
        multiSelect: true,
    /*     indexCol: true,  //索引列 */
        params:function(){
        	var param = new Object();
        	
        	param.knowledgeName = escapeForSql($.trim($("input[name='knowledgeName']").val()));
        	if("-1"!=$("select[name='status']").val()){
        		param.status=$("select[name='status']").val();
        	};
        	param.userName=$.trim($("input[name='userName']").val());
        	param.beginTime =$.trim($("input[name='beginTime']").val());
        	param.endTime =$.trim($("input[name='endTime']").val());
        	param.categoryIds = $("#categoryIds").val()
        	
        	return param;
        },
        cols: [{title: 'knowledgeId', name: 'knowledgeId',align:'center', width:60,height:40, hidden:true},
			   {title: '知识名称', name: 'knowledgeName',align:'center', width:50,height:40,renderer:function(val, item, rowIndex){
				   
				   return "<span class='cate-style' title='"+val+"' style='color: #0C9C92;cursor: pointer;' onclick='detailKL("+item.knowledgeId+")'> "+val+" </span>";
			   }},
			   {title: '知识分类',align:'center', name: 'categoryName', width:40,renderer:function(val, item, rowIndex){
				    var catePath =  getZTreePathName(treeObj.getNodesByParam("categoryId", item.categoryId, null)[0],"categoryName");
					return "<span class='cate-style' title='"+catePath+"'>"+catePath+"</span>";
			   }},
			   {title: '上传者',align:'center', name: 'userName', width:30},
			   {title: '上传时间',align:'center', name: 'createTime', width:50,renderer:function(val, item, rowIndex){
				   var newDate = new Date(val);
				   return formatDate(newDate,"yyyy-MM-dd hh:mm:ss");
			   }},
			   {title: '审核状态',align:'center', name: 'status', width:20,renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "未审批";
				   } else if(val == 2){
					   return "审批通过";
				   }else if(val ==3){
					   return "审批拒绝";
				   }
				    }},

			   {title: '操作',align:'center', name: 'knowledgeId', width:40, renderer:function(val, item, rowIndex){
				   var buttonStr = '<span class="span_btn" onclick="deleteKL('+val+')" >删除</span>';
				   var createUserId =  item.createUserId; //资源上传人
				    if(item.status==1){
				    	buttonStr += '<span class="span_btn " onclick="auditKL('+val+')" >审批</span>'; 
				    }else{
				    	buttonStr +='<span class="span_btn gray" >审批</span>'; 
				    }
				    if(item.isRecommend==0){
				    	if(item.status==2){
					    	buttonStr +='<span class="span_btn" onclick="doRecommend('+val+',1,'+createUserId+')" >推荐</span>'; 
				    	}else{
				    		buttonStr +='<span class="span_btn gray">推荐</span>';
				    	}
				    }else{
				    	buttonStr +='<span class="span_btn" onclick="doRecommend('+val+',0,'+createUserId+')">取消推荐</span>'; 
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
});
function doRecommend(id,type,createUserId){//type:1 推荐操作 0 取消推荐操作
	var urlStr = "<%=request.getContextPath()%>/knowledge/doRecommend.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"id" : id,
			"type" : type,
			"createUserId" : createUserId
		},
		success : function(data) {
			$('#exampleTable').mmGrid().load();
		}
	});
}
//查询
function search(){

	$('#exampleTable').mmGrid().load({"page":1});
}

//重置查询条件
function resetSearch(){
	 //将查询条件归置为空
	 $("input[name='knowledgeName']").val("");
	 $("input[name='categoryName']").val("") ;
	 $("input[name='userName']").val("");
	 $("input[name='beginTime']").val("");
	 $("input[name='endTime']").val("") ; 
	 $("select[name='status']").val(-1);
	 
	 $("input[name='categoryIds']").val("");//主要针对右侧选择框
	 var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	 treeObj.cancelSelectedNode();
	$('#exampleTable').mmGrid().load({"page":1});
}

//发布该知识
function publishKL(klId){
	dialog.confirm('确认发布吗？', function(yes){ 
		if(yes){
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:{knowledgeId:klId},
				url:"<%=request.getContextPath()%>/knowledge/publish.action",
				success:function(data){
					if("SUCCESS"==data){
						dialog.alert("发布成功");
					}else{
						dialog.alert("发布失败");
					}
					search();
			    }
			});
		}
	})
}

//删除知识
function deleteKL(id){
	
	dialog.confirm('确认删除吗？', function(){ 
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{knowledgeId:id},
	    		url:"<%=request.getContextPath()%>/knowledge/deleteKL.action",
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				dialog.alert('删除成功！');
	    				search();
	    			}else{
	    				dialog.alert('删除失败！');
	    			}
	    	    }
	    	});
	});
}


function auditKL(id){
	
	$("#refuseMemo").val("");
	var callBack = function(status){
		
		var param = {};
		param.refuseMemo=$("#refuseMemo").val();
		param.knowledgeId = id;
		param.status = status;
    	
     	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/knowledge/audit.action",
    		success:function(data){
    			if(data == "SUCCESS"){
    				dialog.alert('操作成功！',function(){
	    				search();
    				});
    				
    			}else{
    				dialog.alert('操作失败！');
    			}
    	    }
    	});
	}
	
	//打开设置框
	var d =dialog({
		title: "知识审批",
		align: 'left',
		width:360,
		height:200,
	    content:$("#divedit") ,
	    button: [  { value: '拒绝', callback: function (i, d) {
	    	if($.trim($("#refuseMemo").val())!=""){
				callBack(3);
	    	}else{
	    		$("#divedit").find("div.error").show();
	    		return false;
	    	}
			}},
        { value: '通过', callback: function (i, d) {
        	callBack(2);
        }}
    	 ] 
	})
	d.showModal(); 
}

//批量审批
function batchAudit(){
	
	var rows = $('#exampleTable').mmGrid().selectedRows();
	if(rows.length==0){
		dialog.alert("请选择需要审批的内容");
		return;
	}
	for(var i=0;i<rows.length;i++){
		if(rows[i].status==2||rows[i].status==3){
			dialog.alert("存在已经审批过的内容");
			return ;
		}
	}
	
	$("#refuseMemo").val("");
	var callBack = function(status){
		
		var param = {};
		param.refuseMemo=$("#refuseMemo").val();
		param.status = status;
	
		
		var	ids = [];
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].knowledgeId)
		}
		param.ids = ids.join("|");
    	
     	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/knowledge/batchAudit.action",
    		success:function(data){
    			if(data == "SUCCESS"){
    				dialog.alert('操作成功！',function(){
    					
	    				search();
    				});
    			}else{
    				dialog.alert('操作失败！');
    			}
    	    }
    	});
	}
	//打开设置框
	var d =dialog({
		title: "知识审批",
		align: 'left',
		width:360,
		height:200,
	    content:$("#divedit") ,
	    button: [  { value: '拒绝', callback: function (i, d) {
	    	if($.trim($("#refuseMemo").val())!=""){
				callBack(3);
	    	}else{
	    		$("#divedit").find("div.error").show();
	    		return false;
	    	}
			}},
        { value: '通过', callback: function (i, d) {
        	callBack(2);
        }}
    	 ] 
	})
	d.showModal(); 
	
}


//批量删除知识
function deleteBatchKL(){
	
	var rows = $('#exampleTable').mmGrid().selectedRows();
	
	if(rows.length==0){
		dialog.alert('请选择需要删除的知识！');
		return ;
	}
	
	dialog.confirm('确认批量删除吗？', function(yes){ 
		if(yes){
			
			var	ids = [];
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].knowledgeId)
			}
	/* 		
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify(param), */
    		
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{"ids":ids.join("|")},
	    		url:"<%=request.getContextPath()%>/knowledge/deleteBatch.action",
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				dialog.alert('删除成功！',function(){
	    					
		    				search();
	    				});
	    			}else{
	    				dialog.alert('删除失败！');
	    			}
	    	    }
	    	});
	    }
	});
}

</script>

</head>

<body style="overflow-x:hidden;">
<input type="hidden" id="categoryIds" name ="categoryIds">
<div id="" class='content'>	
		<!-- <h3>知识审批</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">知识审批</span>
		</div>
	<div  class='content_2' >
		<div class='tree ztree'>
			<h2 class="tree-tit">
                知识库分类</h2>
			<div  id='categoryTree' style='margin: 4px;height: 550px;overflow: auto;'>
			
			</div>
		</div>	
		<div class='right_lesson'>
			<div class="op-area fn-clear">
	            <input type="button" class="red-btn btn" value="批量审批" onclick="batchAudit();">
	        </div>
			<div class="search_3" style="margin-bottom: -1px">
	                    	<span>知识名称：</span><input name='knowledgeName' type="text" class="text" style='width: 250px;'>
	                     <!--    <span>知识库分类：</span><input  name='categoryName' type="text" class="text"> -->
	                        <span>审批状态：</span><select  name='status' class="text">
	                        						<option value='-1'>全部显示</option>
	                        						<option value='1'>未审批</option>
	                        						<option value='2'>审批通过</option>
                							        <option value='3'>审批拒绝</option>
	                        					</select>
	                   	   <input type="button" class="gray-btn btn" onclick='resetSearch()' value="重置">
	                       <input type="button" class="red-btn btn" onclick='search()' value="查询">
	           </div>
	          <div class="search_3" style="margin-bottom: -1px">
	           <span>上传者：</span><input name='userName' type="text"  class="text">
	           <span>上传时间：</span><input name='beginTime' type="text" id='beginTime' class="text"/>&nbsp至<input name='endTime'  type="text" id='endTime' class="text"/>
	           </div>
			  <div class="" style='color:#000;width:100%;float: left;'>
				<div id="exampleTable" style="margin-top:10px;" ></div>
				<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
			</div>
		</div>
 	</div>
 	
 	<div title='知识审批' id='divedit' style='display:none'>
 		<label style="display:block">审批理由</label>
 		<textarea rows="4" cols="40"  id='refuseMemo'  maxlength='200'></textarea>
 		<div class='error' style='display: none;color:red'>请输入理由</div>
 	</div>
</div>
<script  type='text/javascript'>
	$("#refuseMemo").on("focus",function(){
		$("#divedit").find("div.error").hide()
				
	}).on("blur",function(){
		/* if(this.value==""){
			$("#divedit").find("label.error").show();
		} */
	})
	
	function uploadKnowledge(){
		
		location = "<%=request.getContextPath()%>/knowledge/upload.action";
	}
	function createKnowledge(){
		
		location = "<%=request.getContextPath()%>/knowledge/create.action";
	}
	
	function detailKL(id){
		
		//location = "<%=request.getContextPath()%>/knowledge/toKnowledgeLookUp.action?knowledgeId="+id;
		//跳转知识详情
		location = "<%=request.getContextPath()%>/approve/objDetail.action?type=2&id="+id;
	}
	</script>
</body>
</html>
