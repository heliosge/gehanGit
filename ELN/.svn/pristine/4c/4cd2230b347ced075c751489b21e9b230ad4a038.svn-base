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

<!-- 上传插件 -->
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/vendor/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/jquery.iframe-transport.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryFileUpload/jquery.fileupload.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
	.ztree{overflow: auto;height: 520px;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: left;}
	.company_tree {width: 250px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
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
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.loginId = $.trim($("[name='userName']").val());
        	param.name = $.trim($("[name='name']").val());
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
function addUser(){
	
	var rows =$('#exampleTable').mmGrid().selectedRows();
	if(rows.length==0){
		dialog.alert("请选择数据！");
		return;
	}
	var idArr =[];
	var ids={};
	$.each(rows, function(index, val){
		idArr.push(val.id);
	});
	ids=idArr.join(',');
	window.parent.addUser(ids);
	window.parent.$.ligerDialog.close(); //关闭弹出窗;  
	window.parent.$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
}
</script>
</head>
<body style="overflow-x:hidden;">
<input type="hidden" id='teacherId' name='teacherId'>
<div class="content" style="margin-top: 0px;padding-bottom:0px;">
 <div class="content_2">
        <div class="company_tree">
            <h2>公司组织结构</h2>
        	<ul id="treePage" class="ztree" ></ul>
        </div>
        <div class="right_lesson" >
            <div class="search_3" style="margin-top:-1px;">
               <p>用户名：
            <input type="text" id="userName"/>
           	 姓名：
            <input type="text" id="name"/>
			<!-- 岗位：
            <input type="text" id="postId"/>
          	 部门：
            <input type="text" id="deptId"/> -->
        </p>
        <input type="button" value="查询" class="btn_cx" onclick="search()"/>
        <input type="button" value="确定" class="btn_3" onclick="addUser()"/>
    
            </div>
    <div id="exampleTable" style="margin-top:10px;width:100%;display:none;" ></div>
    <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

    </div>
    </div>
    </div>

<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
