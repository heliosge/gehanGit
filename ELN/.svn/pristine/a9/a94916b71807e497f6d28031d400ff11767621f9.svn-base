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

<!-- 弹出框 -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>
<style type="text/css">

</style>

<script type="text/javascript">


$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/teacher/getUserList.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: true,
        multiSelect: false,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.userName = $.trim($("[name='userName']").val());
        	param.teacherName = $.trim($("[name='teacherName']").val());
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'id', width:60, hidden:true},
			   {title: '用户名', align:'center',name: 'userName', width:50},
			   {title: '姓名', align:'center',name: 'name', width:50},
			   {title: '部门', align:'center',name: 'deptId', width:50,renderer:function(val, item, rowIndex){
				   var deptName ="";
				   $.ajax({
			    		type:"POST",
			    		async:false,  //默认true,异步
			    		data:{id:val},
			    		url:"<%=request.getContextPath()%>/teacher/queryDeptName.action",
			    		success:function(data){
			    			deptName= data;
			    	    }
			    	});
				   return deptName;
			   }},
			   {title: '岗位', align:'center',name: 'postId', width:50,renderer:function(val, item, rowIndex){
				  
				   var postName ="";
				   $.ajax({
			    		type:"POST",
			    		async:false,  //默认true,异步
			    		data:{id:val},
			    		url:"<%=request.getContextPath()%>/teacher/queryPostName.action",
			    		success:function(data){
			    			postName= data;
			    	    }
			    	});
				   return postName;
				   
			   }},
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


<%-- //新增讲师
function addTeacher(){
	
	location = "<%=request.getContextPath()%>/teacher/add.action";
} --%>
function addTeacherInfo(){
	
	var rows = 	$('#exampleTable').mmGrid().selectedRows();
	if(rows.length==0){
		window.parent.dialog.alert({content:"请选择数据！",lock:false})
		return;
	}
	

	window.parent.addTeacherInfo(rows[0].id);
	var dialog1 = parent.dialog.get(window);
	dialog1.close();
	dialog1.remove();
	//window.parent.$.ligerDialog.close(); //关闭弹出窗;  
	//window.parent.$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
}
</script>
</head>
<body style="overflow-x:hidden;">
<input type="hidden" id='teacherId' name='teacherId'>
<div  class='dialog-content'>	
	<div class="search_3">
                <p>	
                    用户名：
                    <input type="text" name="userName">
                     姓名：
                    <input type="text" name="teacherName">
                </p>
                <input type="button" value="查询" class="btn_cx" onclick='search()'>
                <input type="button" value="新增" class="btn_cx" onclick="addTeacherInfo()" >
    
    </div>
	<div  style="border-right:none;border-bottom:none;border-left:none;margin:0px">
			
		<div style="margin:3px 10px 10px 10px;"></div>
		
		<div style="height: 450px;overflow: auto;border: 1px solid #eee;">
			<div id="exampleTable" style="margin-top:10px;" ></div>
		</div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</div>	
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
