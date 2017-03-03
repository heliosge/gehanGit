<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>群组管理</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.td {text-align: center;padding-left: 16px;height: 36px;border: 1px solid#EAEAEA;padding-right: 16px;}
</style>
<script type="text/javascript">

$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageGroup/selectManageGroupList.action",
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
               {title: '群组名称', name: 'name', width:60, align:'center'},
			   {title: '类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "自由加入";
				   }else if(val == 2){
					   return "通过验证";
				   }else if(val == 3){
					   return "指定学员";
				   }else if(val == 4){
					   return "满足条件";
				   }
			   }},
			   {title: '成员数', name: 'capacity', width:60, align:'center'},
			   {title: '已加入成员数', name: 'userCount', width:60, align:'center'},
			   {title: '成立时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);
			   }},
			   {title: '创建人', name: 'createUserName', width:60, align:'center'},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   
				   var buttonStr = '<a href="toGroupDetail.action?id='+item.id+'" >详情</a>  '
						   		+'<a href="toUpdateGroupPage.action?id='+item.id+'" >修改</a>  '
				   				+'<a href="#" onclick="deleteRowOne('+item.id+')" >删除</a>'
				   				;
				   if(item.type == 2){
					   buttonStr += '  <a href="toCheckGroupPage.action?id='+item.id+'" >审批</a>  ';
				   }else{
					   buttonStr += '   <a href="#" style="color:#999;">审批</a>  ';
				   }
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
	o.createUserName = $("#createUserName").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function insertGroup(){
	window.location.href = "<%=request.getContextPath()%>/manageGroup/toInsertGroupPage.action";
}

function reset(){
	$("#name").val("");
	$("#createUserName").val("");
	search_();
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
	   		url:"<%=request.getContextPath()%>/manageGroup/deleteGroup.action",
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
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.id);
	    	});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/manageGroup/deleteGroup.action",
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

</script>
</head>

<body>



<div class="content">
	<!-- <h3>群组管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">群组管理</span>
	</div>
    <div class="btn_gr">
    	<input type="button" class="btn_1" value="新增群组" onclick="insertGroup()"/>
        <input type="button" class="btn_1" value="批量删除" onclick="deleteRows()"/>
    </div>
    
	<div class="search_2">
        	<p>群组名称：
                    <input type="text" id="name"/>
                    创建人：
                    <input type="text" class="input_1" id="createUserName"/></p>
                     <input type="button" value="查询" class="btn_cx" onclick="search_()"/>
                     <input type="button" value="重置" class="btn_3" onclick="reset()"/>
        </div>
	
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     
</div>

</body>
</html>
