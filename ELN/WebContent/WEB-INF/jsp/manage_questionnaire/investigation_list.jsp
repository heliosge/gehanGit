<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参与考试</title>
<style type="">
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
$(function(){
	$("#userId").val(userId);
	$('#investigationTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/questionnaire/investigationList.action',
		width: '1065px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.title = escapeForSql($.trim($("#title").val()));
	    	param.beginTime = $("#publishBeginTime").val();
	    	param.endTime = $("#publishEndTime").val();
	    	param.isPeriod = $("#isPeriod").val();
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'调查名称', name:'title', width:200,align:'center',
		     	   renderer:function(val,item, rowIndex){
		          		return '<a href="javascript:void(0);" onclick="view('+ item.id +')" >'+val+'</a>';
		          	}
	    		},
	           {title:'发布时间', name:'publishTime', width:170,align:'center'},
	           {title:'调查时间', name:'beginTime', width:200,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.endTime != null){
            			   return item.beginTime + "-" + item.endTime;
            		   }else{
            			   return item.beginTime + "-";
            		   }
               		
               	}
               },
               {title:'是否在调研期',name:'isPeriod', width:80,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isPeriod == 1){
            			   return "是"; 
            		   }else{
            			   return "否";
            		   }
              		}
              	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str = '';
                		var str_del = '<a href="javascript:void(0);" onclick="del('+ item.id +')" >删除</a>&nbsp;';
                		var str_edit = '<a href="javascript:void(0);" onclick="edit('+ rowIndex +')" >修改</a>&nbsp;';
                		var str_publish = '<a href="javascript:void(0);" onclick="publish('+ rowIndex +')" >发布</a>';
                   		if(item.isPublished == 1){
                   			str = str_edit;
                   		}else{
                   			str = str_del+str_edit+str_publish;
                   		}
                		return str;	  
                	}	
	}] ,
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
})

/*查看报告  */
function view(id){
	window.location = '<%=request.getContextPath() %>/questionnaire/toViewInvestigation.action?id='+id;
}

/*删除调查  */
function del(id){
	dialog.confirm('确认删除吗？', function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify([id]),
			url: "<%=request.getContextPath()%>/questionnaire/deleteInvestigation.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						search(true);
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}

/*编辑调查  */
function edit(rowIndex){
	var rowData = $('#investigationTable').mmGrid("row", rowIndex);
	window.location = '<%=request.getContextPath() %>/questionnaire/toModifyInvestigation.action?id='+rowData.id;
}

/*发布调查  */
function publish(rowIndex){
	var rowData = $('#investigationTable').mmGrid("row", rowIndex);
	/* var curDate = new Date().getTime();
	var beginTime = new Date(Date.parse(rowData.beginTime.replace(/-/g,"/"))).getTime();
	if(beginTime < curDate){
		dialog.alert("考试开始时间已到达，不可以发布考试！");
		return;
	} */
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":rowData.id},
		url: "<%=request.getContextPath()%>/questionnaire/isAddUser.action",
		success:function(data){
			if(data == false){
				dialog.alert("请添人员！");
			}else{
				dialog.confirm('确认发布吗？', function(){
					$.ajax({
						type:"POST",
						async:true,  //默认true,异步
						data:{"id":rowData.id},
						url: "<%=request.getContextPath()%>/questionnaire/publish.action",
						success:function(data){
							if(data == "SUCCESS"){
								dialog.alert("操作成功！", function (){
									search(true);
								});
							}else{
								dialog.alert("操作失败！");
							}
					    }
					});
				});
			}
	    }
	});
}

function jqchk(){ //jquery获取复选框值 
	 var chk_value ='';
	$('input[name="subcheck"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//查询
function query(){
	$('#investigationTable').mmGrid().load({"page":1});
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#investigationTable').mmGrid().load();
	}else{
		$('#investigationTable').mmGrid().load({"page":1});		
	}
}
$(document).ready(function() {     
	$("#publishBeginTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
 		//maxDate: da,
	});
	$("#publishEndTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
		//maxDate: da,
	});
}); 

//重置
function clearAll(){
	$("#title").val("");
	$("#publishBeginTime").val("");
	$("#publishEndTime").val("");
    $('#isPeriod').val("");
    query();
}

/*新增调查  */
function add(){
	window.location = "<%=request.getContextPath()%>/questionnaire/toAddInvestigation.action";
}

</script>
</head>
<body>
    <div class="content">
	<!-- <h3>调查管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">调查管理</span>
	</div>
    <div class="content_2">
        <div style="margin-bottom: 5px;">
            <input type="button" class="buttonClass" style="background-color:#d60500;" value="新增调查" onclick="add();"/>
        </div>
        <form id="form">
        <div class="search_2 fl">
			<p>调查名称：<input type="text" id="title" name="title"/>
               	发布时间：<input type="text" id="publishBeginTime" name="publishBeginTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                  	至 <input type="text" id="publishEndTime" name="publishEndTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
              		是否在调研期：
               <select id="isPeriod" name="isPeriod" style="width:80px;">
                   	<option value="0">全部</option>
                   	<option value="1">是</option>
                   	<option value="2">否</option>
                   </select>
   
           </p>
           <input type="button" onclick="clearAll();" value="重置" />
           <input type="button" class="btn_cx" onclick="query();" value="查询" />
	        </div>
        </form>
         <div class="clear_both"></div>
	     <div id="dataDiv">
	     	<table id="investigationTable"></table>
		  	<div id="paginator11-1" style="text-align:right;"></div>
	     </div>
   </div> 
</div>
	 <%@include file="../common/footer.jsp" %>
</body>
</html>
