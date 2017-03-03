<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>选择群组</title>
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

</style>

<script type="text/javascript">


$(function(){
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageGroup/selectManageGroupList.action",
		width: $('#exampleTable').width(),
	    height: '445px',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: true,
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.name = $.trim($("[name='name']").val());
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'id', width:60, hidden:true},
               {title: '组名称', name: 'name', width:60},
			   {title: '描述', name: 'descr', width:120,renderer:function(val, item, rowIndex){
				   if(val&&val.length>45){
					   return val.substring(0,45)+'...';
				   }
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

function addGroup(){
	
	var rows = 	$('#exampleTable').mmGrid().selectedRows();
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
	window.parent.addUserByGroup(ids);
	window.parent.artDialog.close();
}
</script>
</head>
<body style="overflow-x:hidden;">
<input type="hidden" id='teacherId' name='teacherId'>
<div  class='dialog-content'>	
	<div class="search_3" style="">
                  <p>	
        
                     群组名称：
                    <input type="text" name="name">
                </p>
                <input type="button" value="查询" class="btn_cx" onclick='search()'>
                <input type="button" value="新增" class="btn_cx" onclick="addGroup()" >
    
    </div>
	<div  style="border-right:none;border-bottom:none;border-left:none;margin:0px">
			
		<div style="margin:3px 10px 10px 10px;"></div>
		
		<div id="exampleTable" style="margin-top:10px;" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</div>	
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
