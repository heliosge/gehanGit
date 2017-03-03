<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>讲师管理</title>
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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<%-- <script type="text/javascript" src= '<%=request.getContextPath()%>/js/artdialog/artdialog.js?skin=chrome'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/js/artdialog/iframetools.js'></script> --%>	

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">

</style>

<script type="text/javascript">

//var listTeacherBean = ${listTeacherBean}; //部门

$(function(){
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/teacher/getTeacherByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: true,
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	
        	param.userName = escapeForSql($.trim($("[name='userName']").val()));
        	param.teacherName = escapeForSql($.trim($("[name='teacherName']").val()));
        	if($("[name='teacherCategory']").val()!=-1){
	        	param.teacherCategory = $("[name='teacherCategory']").val();
        	}
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'teacherId', width:60, hidden:true},
			   {title: '用户名', align:'center',name: 'userName', width:50,renderer:function(val, item, rowIndex){
				   
				   return "<span onclick='detailTeacher("+item.teacherId+")'><a href='javascript:void'>"+val+"</a></span>";
			   }},
			   {title: '讲师姓名', align:'center',name: 'teacherName', width:50},
			   {title: '电子邮箱', align:'center',name: 'eMail', width:50, },
			   {title: '分类', align:'center',name: 'teacherCategory', width:50,renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "内部讲师";
				   }else if(val == 2){
					   return "外部讲师";
				   }
			   }},
			   {title: '擅长领域', align:'center',name: 'description', width:50, },
			 
			   {title: '操作', align:'center',name: 'teacherId', width:60, renderer:function(val, item, rowIndex){
				   
				   var buttonStr = '<a class="a-btn" href="#" onclick="deleteTeacher('+val+')" >删除</a>'+ 
				   				   '<a class="a-btn" href="#" onclick="editTeacher('+val+')" >修改</a> ' ;
				   
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
	$('#exampleTable').mmGrid().load({"page":1});
}

//重置
function reset(){
	
	$("[name='userName']").val("")
	$("[name='teacherName']").val("");
    $("[name='teacherCategory']").val(-1);
    search()
}


//删除讲师
function deleteTeacher(id){
	
	dialog.confirm("请确认删除吗?",function(obj){
    	 $.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{teacherId:id},
	    		url:"<%=request.getContextPath()%>/teacher/deleteTeacherInfo.action",
	    		success:function(data){
	    			if(data=="SUCCESS"){
	    				dialog.alert("删除成功！");
		    			search();
	    			}else{
	    				dialog.alert("删除失败！");
	    			}
	    	    }
 		 })
     })

}

//删除批量
function deleteBatch(){
	
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	
	if(selectRows.length > 0){
		dialog.confirm('确认删除吗？', function(){ 
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.teacherId);
	    	});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{ids:param.join("|")},
	    		url:"<%=request.getContextPath()%>/teacher/deleteBatch.action",
	    		success:function(data){
	    			search();
	    	    }
	    	});
		});
	}else{
		//alert("请先选择数据！");
		dialog.alert('请先选择数据！');
	}
}

//讲师详情
function detailTeacher(id){
	
	 if(id){
	   	location = "<%=request.getContextPath()%>/teacher/detail.action?mode=detail&teacherId="+id;
	 }
}
//新增讲师
function addTeacher(){
	
	location = "<%=request.getContextPath()%>/teacher/add.action";
}

//进入讲师修改页面
function editTeacher(id){
	location = "<%=request.getContextPath()%>/teacher/detail.action?mode=edit&teacherId="+id;
}

//导入讲师
function importFile(){
	dialog({
        url:"<%=request.getContextPath()%>/teacher/addFile.action",
        title:"导入讲师" ,
		height: 250,
		width: 450
		}).showModal();
}

function downloadTemplate(){
	$("#form").submit();
}

    
</script>
</head>
<body style="overflow-x:hidden;">
 <div id="content_i" class='content'>	
	<div>
		<!-- <h3>讲师管理</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">讲师管理</span>
		</div>
	</div>

	<div class="btn_gr">
	    	<input type="button" class="btn_1" value="新增讲师" onclick="addTeacher()">
	        <input type="button" class="btn_1" value="导入讲师" onclick="importFile()">
	        <input type="button" class="btn_1" value="下载模板" onclick="downloadTemplate()">
        	<%-- <a class="btn_1" href="<%=request.getContextPath()%>/template/导入讲师模板.xls">下载模板</a> --%>
	        <input type="button" class="btn_2" value="批量删除" onclick='deleteBatch()'>
	        
	</div>
	 <form id="form" action="<%=request.getContextPath()%>/teacher/downloadTemplate.action" method="post">
    	<input type="hidden" value="template/导入讲师模板.xls" name="path"/>
 	    <input type="hidden" value="导入讲师模板.xls" name="fileName"/>
    </form>
	<div class="search_2">
    	<p>
        	讲师姓名：
            <input type="text" name='teacherName'>
            用户名：
            <input type="text" name='userName'>
            分类：
            <select name='teacherCategory'>
            	<option selected="selected"  value='-1'>显示全部</option>
                <option value='1'>内部讲师</option>
                <option value='2'>外部讲师</option>
            </select>

        </p>
        <input type="button" value="重置" onclick='reset()'>
        <input type="button" value="查询" class="btn_cx" onclick='search()'>

    </div>
	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
			
		<div style="margin:3px 10px 10px 10px;"></div>
		
		<div id="exampleTable" style="margin-top:10px;" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</div>	
</body>
</html>
