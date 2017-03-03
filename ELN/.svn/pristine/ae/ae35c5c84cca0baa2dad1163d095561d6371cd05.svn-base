<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>管理员管理</title>
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
<!-- 上传插件 -->
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/vendor/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/jquery.iframe-transport.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/jquery.fileupload.js"></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">

html, body{
	height: 100%;
}

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

var listTeacherBean = ${listTeacherBean}; //部门

$(function(){
	
	//部门
	$.each(departmentList, function(index, data){
		$("#departmentId").append("<option value='"+data.id+"'>"+data.name+"</option>");
	});
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/getManageUserByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //checkCol: true,
        multiSelect: true,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	
        	param.name = $.trim($("#name").val());
        	param.loginId = $.trim($("#loginId").val());
        	param.departmentId = $("#departmentId").val();
        	param.status = $("#status").val();
        	
        	return param;
        },
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
			   {title: '姓名', name: 'name', width:50},
			   {title: '账号', name: 'userId', width:50},
			   {title: '部门', name: 'departmentId', width:50, renderer:function(val, item, rowIndex){
			       if(departmentList && departmentList.length > 0){
			    	   for(var i=0; i<departmentList.length; i++){
			    		   if(departmentList[i].id == val){
			    			   return departmentList[i].name;
			    		   }
			    	   }
			       }
			       
			       return "";
			   }},
			   {title: '职位', name: 'position', width:50},
			  /*  {title: '工作状态', name: 'workStatus', width:40, renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "在职";
				   }else if(val == 1){
					   return "离职";
				   }
			   }}, */
			   {title: '性别', name: 'gender', width:40, renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "男";
				   }else if(val == 1){
					   return "女";
				   }
			   }},
			   {title: '移动电话', name: 'phone', width:60},
			   {title: '邮箱', name: 'email', width:160},
			   /* {title: '是否锁定', name: 'status', width:40, renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "未锁定";
				   }else if(val == 1){
					   return "锁定";
				   }
			   }}, */
			   /* {title: '是否内部人员', name: 'internal', width:60, renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "内部人员";
				   }else if(val == 1){
					   return "非内部人员";
				   }
			   }} */
			   {title: '操作', name: 'id', width:60, renderer:function(val, item, rowIndex){
				   
				   var buttonStr = '<span class="span_btu span_btu_1" onclick="editRowOne('+val+')" >修改</span>' +
				   				   '<span class="span_btu span_btu_2" onclick="deleteRowOne('+val+')" >删除</span>';
				   
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

//查询
function search(){
	/* var param = new Object();
	
	param.name = $.trim($("#name").val());
	param.loginId = $.trim($("#loginId").val());
	param.departmentId = $("#departmentId").val();
	param.status = $("#status").val(); */
	
	$('#exampleTable').mmGrid().load({"page":1});
}


//删除一行
function deleteRow(){
	
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	
	if(selectRows.length > 0){
		$.ligerDialog.confirm('确认删除吗？', function(yes){ 
			if(yes){
				var param = [];
		    	$.each(selectRows, function(index, val){
		    		param.push({"name":"ids", "value":val.id});
		    	});
		    	
		    	$.ajax({
		    		type:"POST",
		    		async:true,  //默认true,异步
		    		data:param,
		    		url:"<%=request.getContextPath()%>/manageUser/delManageUser.action",
		    		success:function(data){
		    			search();
		    	    }
		    	});
		    }
		});
	}else{
		//alert("请先选择数据！");
		$.ligerDialog.warn('请先选择数据！');
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
	    		url:"<%=request.getContextPath()%>/manageUser/delManageUser.action",
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
	$("#add_iframe iframe").height($("body").height());
	$("#add_iframe iframe").attr("src", "<%=request.getContextPath()%>/manageUser/userAdd.action");
	$("div").first().hide();
	$("#add_iframe").show();
	//window.location.href = "<%=request.getContextPath()%>/manageUser/userAdd.action";
}

//修改标签
function editRowOne(id){
	
	$("#add_iframe iframe").attr("src", "<%=request.getContextPath()%>/manageUser/userAdd.action?id="+id);
	$("div").first().hide();
	$("#add_iframe").show();
}

//修改标签
function editRow(){
	
	if($('#exampleTable').mmGrid().selectedRows().length > 1){
		//alert("只能单选操作！");
		$.ligerDialog.warn('只能单选操作!');
	}else{
		//被选中的一行
		var selectRowIndex = $('#exampleTable').mmGrid().selectedRowsIndex();
		
		if(selectRowIndex != ""){
			var row = $('#exampleTable').mmGrid().row(selectRowIndex);
			$("#add_iframe iframe").attr("src", "<%=request.getContextPath()%>/manageUser/userAdd.action?id="+row.id);
			$("div").first().hide();
			$("#add_iframe").show();
			//window.location.href = "<%=request.getContextPath()%>/manageUser/userAdd.action?id="+row.id;
		}else{
			$.ligerDialog.warn('请先选择一行！');
		}
	}
}

</script>
</head>
<body style="overflow-x:hidden;">
	
<div>
<div class="top_page_name">新增讲师</div>

</div>
<style type="text/css">
.text-p{margin: 10px; width:250px;
margin: 10px;
width: 250px;
padding: 0 2px;
height: 28px;
color: #555;
font: 12px Verdana;
border: 1px solid #cacaca;
line-height: 20px;
vertical-align: middle;}	
</style>	
	<table style='margin-left: 15%;margin-top: 8%;'>
		<tr><td><span>*</span> </td><td><input id='wayType' name='wayType' type='hidden'/>  <input  type="radio" name="teacherType"></input>课程&nbsp<input checked="true" type="radio" name="teacherType"></input>知识<input checked="true" type="radio" name="teacherType"></input>专题</td><td></td></tr>
		<tr><td>是否需要审批：</td><td><input id='isAprrove' name='isAprrove' type='hidden'/> <input id='aprrove' name='isAprrove' type='radio'/>是<input id='aprrove' name='isAprrove' type='radio'/>否</td><td></td></tr>
		
		<tr><td><button  onclick='saveTeacherInfo()'>保存</button></td><td><button  >返回</button></td></tr>
		
	</table>
	<button onclick="query()">查询审核信息</button>
	
	<button onclick="queryUserList()">查询用户列表</button>
	<button onclick="queryRoleList()">查询角色列表</button>
	<button onclick="queryLeader()">查询领导</button>
	<button onclick="queryPostList()">查询岗位列表</button>
	<script  type='text/javascript'>
	function saveTeacherInfo(){
		
			var param = {};
		param.wayType=2;
	 	param.isApprove=1;
	 //	param.wayId= 8;
		 
		param.listStep=[];
	 	var step= {};
		step.orderNum =0;
		step.approverId=222;
		step.approverType=2;
		
		param.listStep.push(step);
		
		var step1= {};
		step1.orderNum =1;
		step1.approverId=12;
		step1.approverType=1;
		
		param.listStep.push(step1);
		
		param.deptList=[];
		param.deptList.push(21);
		param.deptList.push(22);
		param.deptList.push(23);  
		
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
   		  	contentType:"application/json; charset=utf-8",
    		data:JSON.stringify(param),
    		url:"<%=request.getContextPath()%>/approve/saveApproveInfo.action",
    		success:function(data){
    			if("SUCCESS"==data){
    				alert("保存成功");
    			}else{
    				alert("保存失败");
    			}
    			//search();
    	    }
    	});
    	//$.post(url,data,callback,type)
    	<%-- $.post("<%=request.getContextPath()%>/approveManage/saveApproveInfo.action",{"wayType":1},function(data){
    		alert(data)
    	}) --%>
	}
	
	
	function queryPostList(){
		
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
   		  	contentType:"application/json; charset=utf-8",
    		data:JSON.stringify({wayType:2,companyId:1111}),
    		url:"<%=request.getContextPath()%>/approve/query.action",
    		success:function(data){
    			alert(JSON.stringify(data))
    			/* if("SUCCESS"==data){
    				alert("保存成功");
    			}else{
    				alert("保存失败");
    			} */
    			//search();
    	    }
    	});
    }
	
	function queryUserList(){
		
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
   		  	contentType:"application/json; charset=utf-8",
    		data:JSON.stringify({wayType:2,companyId:1111}),
    		url:"<%=request.getContextPath()%>/approve/getUserList.action",
    		success:function(data){
    			alert(JSON.stringify(data))
    			/* if("SUCCESS"==data){
    				alert("保存成功");
    			}else{
    				alert("保存失败");
    			} */
    			//search();
    	    }
    	})
    }
	function queryRoleList(){
		
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
   		  	contentType:"application/json; charset=utf-8",
    		data:JSON.stringify({wayType:2,companyId:1111}),
    		url:"<%=request.getContextPath()%>/approve/query.action",
    		success:function(data){
    			alert(JSON.stringify(data))
    			/* if("SUCCESS"==data){
    				alert("保存成功");
    			}else{
    				alert("保存失败");
    			} */
    			//search();
    	    }
    	})
    }
	function queryLeader(){
		
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
   		  	contentType:"application/json; charset=utf-8",
    		data:JSON.stringify({wayType:2,companyId:1111}),
    		url:"<%=request.getContextPath()%>/approve/query.action",
    		success:function(data){
    			alert(JSON.stringify(data))
    			/* if("SUCCESS"==data){
    				alert("保存成功");
    			}else{
    				alert("保存失败");
    			} */
    			//search();
    	    }
    	})
    }
	</script>
	
</body>
</html>
