<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>部门管理</title>
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

<style type="text/css">
.mmGrid,.mmPaginator{

}

.span_btus{
	margin:2px 2px;
}
.span_btu{
	display: inline-block;
	background-color: #4ABCF0;
	font-size: 12px;
	
	padding: 0px 5px;
	cursor: pointer;
	border-radius: 5px;
	min-width: 40px;
	text-align: center;
	color: #FFFFFF;
	margin: 0px 2px;
}
.span_btu_1{
	background-color: #34CC67;
}
.span_btu_2{
	background-color: #FF6766;
}
.span_btu_3{
	background-color: #F8CB0E;
}

</style>

<script type="text/javascript">

$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',
		url:"<%=request.getContextPath()%>/manageDepartment/getManageDepartmentByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //checkCol: true,
        multiSelect: true,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	
        	param.code = $.trim($("#code").val());
        	param.name = $.trim($("#name").val());
        	
        	return param;
        },
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
			   {title: '编号', name: 'code', width:60},
			   {title: '部门名称', name: 'name', width:60},
			   //{title: '负责人', name: 'leaderUserName', width:60},
			   /* {title: '上级部门', name: 'parentDepartmentName', width:60, renderer:function(val, item, rowIndex){
				   //dialog.alert(val);
				   if(val){
					   return val;
				   }else{
					   return "";
				   }
			   }}, */
			   {title: '简介', name: 'memo', width:300},
			   {title: '操作', name: 'id', width:60, renderer:function(val, item, rowIndex){
				   
				   var buttonStr = '<span class="span_btu span_btu_1" onclick="editRowOne('+val+')" >修改</span>' +
				   				   '<span class="span_btu span_btu_2" onclick="deleteRowOne('+val+')" >删除</span>';
				   
				   return "<div class='span_btus' >" + buttonStr + "</div>";
			   }}
			   /* {title: '是否启用', name: 'status', width:60, renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "已启用";
				   }else if(val == 1){
					   return "未启用";
				   }
			   }} */
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',   //对应MMGridPageVoBean count
        		page: 1,                   //初始页
        		pageParamName: 'page',     //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
});

//查询
function search(){
	/* var param = new Object();
	
	param.code = $.trim($("#code").val());
	param.name = $.trim($("#name").val()); */
	
	$('#exampleTable').mmGrid().load();
}


//删除一行
function deleteRow(){
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	
	if(selectRows.length > 0){
		
		var param = [];
    	$.each(selectRows, function(index, val){
    		param.push({"name":"ids", "value":val.id});
    	});
		
		//验证此部门不是其他部门的上级部门
		var flagData = null;
		$.ajax({
    		type:"POST",
    		async:false,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/manageDepartment/checkParentDepartment.action",
    		success:function(data){
    			flagData = data;
    	    }
    	});
		if(parseInt(flagData) > 0){
			$.ligerDialog.warn("被删除的部门中有部门是其他部门的上级部门，无法删除！");
			return;
		}

		$.ligerDialog.confirm('确认删除吗？', function(yes){ 
			if(yes){
				$.ajax({
		    		type:"POST",
		    		async:true,  //默认true,异步
		    		data:param,
		    		url:"<%=request.getContextPath()%>/manageDepartment/delManageDepartment.action",
		    		success:function(data){
		    			search();
		    	    }
		    	});
		    }
		});
		
	}else{
		$.ligerDialog.warn("请先选择数据！");
	}
}

//删除一行
function deleteRowOne(id){
	
	$.ligerDialog.confirm('确认删除吗？', function(yes){ 
		if(yes){
			
			var param = [];
			param.push({"name":"ids", "value":id});
			
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		url:"<%=request.getContextPath()%>/manageDepartment/delManageDepartment.action",
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				$.ligerDialog.success('保存成功！', '提示');
	    				search();
	    			}else{
	    				$.ligerDialog.warn('保存失败！', '提示');
	    			}
	    	    }
	    	});
	    }
	});
}

//增加自定义标签
function addRow(){

	window.location.href = "<%=request.getContextPath()%>/manageDepartment/departmentAdd.action";

}

//修改标签
function editRow(){

	if($('#exampleTable').mmGrid().selectedRows().length > 1){
		//dialog.alert("只能单选操作！");
		$.ligerDialog.warn('只能单选操作！');
	}else{
		//被选中的一行
		var selectRowIndex = $('#exampleTable').mmGrid().selectedRowsIndex();
		
		if(selectRowIndex != ""){
			var row = $('#exampleTable').mmGrid().row(selectRowIndex);
	
			window.location.href = "<%=request.getContextPath()%>/manageDepartment/departmentAdd.action?id="+row.id;
			
		}else{
			$.ligerDialog.warn('请先选择一行！');
		}
	}
}

//修改标签
function editRowOne(id){

	window.location.href = "<%=request.getContextPath()%>/manageDepartment/departmentAdd.action?id="+id;
}
</script>
</head>
<body style="">

	<div class="top_page_name">部门管理</div>
	
	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;">
		
		<!-- 查询 -->
		<table class="param_table" cellpadding="0" cellspacing="0" style="">
			<tr>
				<td style="width:10px;"></td>
				<td style="width:250px;">
					<span>编号：</span>
					<input type="text" id="code" name="code" class="input_0" />
				</td>
				<td style="width:250px;">
					<span>部门名称：</span>
					<input type="text" id="name" name="name" class="input_0" />
				</td>
				<td style="">
					<button class="btu_0" onclick="search()">查询</button>
					<button class="btu_0" onclick="addRow()" style="margin-right:10px;">增加</button>
				</td>
			</tr>
		</table>
		
		<div style="margin:3px 10px 10px 10px;background-color:#DDDDDD;height:1px;"></div>
		
		<!-- add delete edit -->
		<%-- <table class="png_table" cellpadding="0" cellspacing="0" style="margin-left:10px;margin-bottom:10px;">
			<tr>
				<td>
					<a class="button_a" href="javascript:;" onclick="addRow()">
						<img src="<%=request.getContextPath()%>/images/add.png" />
						<span>增加</span>
					</a>
					<button class="btu_0" onclick="addRow()" style="margin-right:10px;">增加</button>
				</td>
				<td>
					<a class="button_a" href="javascript:;" onclick="deleteRow()">
						<img src="<%=request.getContextPath()%>/images/delete.png"/>
						<span>删除</span>
					</a>
					<button class="btu_0" onclick="deleteRow()" style="margin-right:10px;">删除</button>
				</td>
				<td>
					<a class="button_a" href="javascript:;" onclick="editRow()">
						<img src="<%=request.getContextPath()%>/images/edit.png" />
						<span>修改</span>
					</a>
					<button class="btu_0" onclick="editRow()" style="margin-right:10px;">修改</button>
				</td>
			</tr>
		</table> --%>
		
		<!-- 表格 -->
		<table id="exampleTable"></table>
		<div id="paginator11-1" style="text-align:right;"></div>
		
	</div>
	
</body>
</html>
