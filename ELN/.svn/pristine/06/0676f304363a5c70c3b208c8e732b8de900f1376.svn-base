<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>普联用户管理</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />

<style type="text/css">
	.td {text-align: center;padding-left: 16px;height: 36px;border: 1px solid#EAEAEA;padding-right: 16px;}
</style>
<script type="text/javascript">

$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageRole/getAllManageRole.action",
    	width: $('#exampleTable').width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        //indexCol: true,  //索引列
        params: function(){
	    	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '角色名称', name: 'name', width:60, align:'center'},
 			   {title: '角色描述', name: 'descr', width:60, align:'center'},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }
			   }},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   
				   var buttonStr ='';
				   if(item.status == 1){
					   buttonStr += '<a href="#" onclick="freeze('+item.id+')" >冻结</a>  ';
				   }else if(item.status == 2){
					   buttonStr += '<a href="#" onclick="unfreeze('+item.id+')" >解冻</a>  ';
				   }
				   buttonStr += '<a href="<%=request.getContextPath()%>/manageRole/toUpdateRolePage.action?id='+item.id+'" >修改</a>  <a href="#" onclick="deleteRowOne('+item.id+')" >删除</a>'
				   				+'  <a href="toRoleDetailPage.action?id='+item.id+'" >详情</a>';
				   
				   return buttonStr;
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

function param(){
	var o = {};
	o.name = $("#name").val();
	o.status = $("#status").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function toInsertRole(){
	window.location.href = "<%=request.getContextPath()%>/manageRole/toInsertRolePage.action";
}


function deleteRowOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		var param = [];
	   	param.push(id);
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : param
	   		},
	   		url:"<%=request.getContextPath()%>/manageRole/delManageRole.action",
	   		success:function(data){
	   			search();
	   			dialog.alert("删除成功。");
	   	    }
	   	});
	})
}

function deleteRows(){
		var selectRows = $('#exampleTable').mmGrid().selectedRows();
		if(selectRows.length > 0){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.id);
	    	});
			dialog.confirm("确认删除吗?",function(obj){
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/manageRole/delManageRole.action",
	    		success:function(data){
	    			search();
	    			dialog.alert("删除成功。");
	    	    }
	    	});
		})
		}else{
			dialog.alert('请先选择数据！');
		}
}


function freeze(id){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			ids : param,
   			status : 2
   		},
   		url:"<%=request.getContextPath()%>/manageRole/freezeAndUnfreezeRole.action",
   		success:function(data){
   			search();
   			dialog.alert("冻结成功。");
   	    }
   	});
}

function freezeRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
    	$.each(selectRows, function(index, val){
    		param.push(val.id);
    	});
    	
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{
    			ids : param,
    			status : 2
    		},
    		url:"<%=request.getContextPath()%>/manageRole/freezeAndUnfreezeRole.action",
    		success:function(data){
    			search();
    			dialog.alert("冻结成功。");
    	    }
    	});
	}else{
		dialog.alert('请先选择数据！');
	}
}

function unfreeze(id){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			ids : param,
   			status : 1
   		},
   		url:"<%=request.getContextPath()%>/manageRole/freezeAndUnfreezeRole.action",
   		success:function(data){
   			search();
   			dialog.alert("解冻成功。");
   	    }
   	});
}

function unfreezeRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
    	$.each(selectRows, function(index, val){
    		param.push(val.id);
    	});
    	
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{
    			ids : param,
    			status : 1
    		},
    		url:"<%=request.getContextPath()%>/manageRole/freezeAndUnfreezeRole.action",
    		success:function(data){
    			search();
    			dialog.alert("解冻成功。");
    	    }
    	});
	}else{
		dialog.alert('请先选择数据！');
	}
}

</script>
</head>

<body>



<div class="content">
	<!-- <h3>角色管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">角色管理</span>
	</div>
    <div class="btn_gr">
    	<input type="button" class="btn_1" value="新增角色" onclick="toInsertRole()"/>
        <input type="button" class="btn_1" value="冻结" onclick="freezeRows()"/>
        <input type="button" class="btn_1" value="解冻" onclick="unfreezeRows()"/>
        <input type="button" class="btn_1" value="批量删除" onclick="deleteRows()"/>
    </div>
    
	<div class="search_2">
        	<p>角色名称：
                    <input type="text" id="name"/>
                   	状态：
                    <select id="status">
                    	<option value="">全部</option>
                    	<option value="1">正常</option>
                    	<option value="2">冻结</option>
                    </select></p>
                    <input type="button" value="查询" class="btn_3" onclick="search_()"/>
        </div>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     
</div>

</body>
</html>
