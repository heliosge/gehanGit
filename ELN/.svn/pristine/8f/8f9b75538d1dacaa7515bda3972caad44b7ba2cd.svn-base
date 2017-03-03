<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看组员</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}
</style>

<script type="text/javascript">
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var groupId = '${groupId}';
	
/**
 * 页面加载完成
 */
$(function(){
	initGroupMembers();
});
	
/**
 * 初始化群组成员
 */
function initGroupMembers(){
	//查询参数
	var param = new Object();
	param.id = groupId;
	//初始化mmGrid
	$("#mmGridTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/manageGroup/getStudentsByGroupId.action',
		width : '1042px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols : [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '姓名', name: 'name', width:60, align:'center'},
	 			   {title: '用户名', name: 'userName', width:60, align:'center'},
	 			   {title: '电子邮箱', name: 'email', width:60, align:'center'},
				   {title: '联系电话', name: 'mobile', width:60, align:'center'},
				   {title: '岗位', name: 'postName', width:60, align:'center'},
	 			   {title: '部门', name: 'deptName', width:60, align:'center'}
				],
		plugins : [$('#mmGridPager').mmPaginator({
			totalCountName : 'total', 
			page : 1, 
			pageParamName : 'page', 
			limitParamName : 'pageSize', 
			limitList : [10, 20, 40, 50] 
		})]
	});
}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看组员</span>
			</div>
			<!-- <h3>查看组员</h3> -->
			<!-- <div class="search_1">
				<span>用户名：<input id="userName" type="text"/></span>
				<span>姓名：<input id="name" type="text"/></span>
				<span>岗位：<input id="post" type="text"/></span>
				<span>部门：<input id="department" type="text"/></span>
				<input type="button" class="btn_5" onclick="search();" value="查询"/>
			</div> -->
		
			<!-- mmGrid展现 -->
			<div class="clear_both"></div>
			<div id="groupMemberMMGridShow" style="padding-bottom:60px;">
				<table id="mmGridTable"></table>
				<div id="mmGridPager" style="text-align: right;"></div>
			</div>
		</div>
	</div>
</body>
</html>