<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的群组</title>
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
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var itemsPerPage = 10;//每页显示条数
	
/**
 * 页面加载完毕
 */
$(function(){
	//初始化群组
	initGroupsForMMGrid();
});
	
/**
 * 初始化群组
 */
function initGroupsForMMGrid(){
	//查询参数
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	param.userId = userId;
	param.groupName = $("#groupName").val();
	param.createUserName = $("#createUserName").val();
	
	$("#mmGridTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/studentGroupAction/getGroupsForMMGrid.action',
		width : '1042px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols : [{
			title : 'id',
			name : 'id',
			hidden : true
		},{
			title : '群组名称',
			name : 'name',
			align : 'center'
		},{
			title : '群组类型',
			name : 'type',
			align : 'center',
			renderer : function(val, item, rowIndex){
				var str = '';
				if(val == 1){
					str = '自由加入';
				}else if(val == 2){
					str = '通过验证';
				}else if(val == 3){
					str = '指定学员';
				}else if(val == 4){
					str = '满足条件';
				}
				return str;
			}
		},{
			title : '已加入人数',
			name : 'joinNum',
			align : 'center'
		},{
			title : '群组容量',
			name : 'capacity',
			align : 'center'
		},{
			title : '成立时间',
			name : 'createTime',
			align : 'center',
			renderer : function(val, item, rowIndex){
				return getSmpFormatDateByLong(val);
			}
		},{
			title : '创建人',
			name : 'createUserName',
			align : 'center'
		},{
			title : '加入状态',
			name : 'status',
			align : 'center',
			renderer : function(val, item, rowIndex){
				var str = '';
				if(val != null && val != ''){
					if(val == 1){
						str = '已加入';
					}else if(val == 2){
						str = '待验证';
					}else if(val = 3){
						str = '未通过';
					}
				}else{
					str = '未加入';
				}
				return str;
			}
		},{
			title : '操作',
			name : 'operation',
			align : 'center',
			renderer : function(val, item, rowIndex){
				var str = '';
				//没有加入状态，加入状态为审核不通过时，允许申请加入
				if(item.status == null || item.status == 3){
					//自由加入、通过验证类型的群组学员，可以申请加入
					if(item.type == 1 || item.type == 2){
						//已加入人数小于群组容量是，可以申请加入
						if(item.joinNum < item.capacity){
							str = '<a href="javascript:void(0);" onclick="joinGroup('+item.id+','+item.type+')">申请加入</a>';
						}else{
							str = '<a style="color:grey;">申请加入</a>';
						}
					}else{
						str = '<a style="color:grey;">联系管理员加入</a>';
					}
				}else if(item.status == 1){
					//已经加入，可以查看群组成员
					str = '<a href="javascript:void(0);" onclick="toGroupMembers('+item.id+');">查看组员</a>';
				}else{
					//待审核状态无法申请加入
					str = '<a style="color:grey;">申请加入</a>';
				}
				return str;
			}
		}],
		plugins : [$('#mmGridPager').mmPaginator({
			totalCountName : 'total', 
			page : 1, 
			pageParamName : 'page', 
			limitParamName : 'pageSize', 
			limitList : [10, 20, 40, 50] 
		})]
	});
}

/**
 * 查询
 */
function search(){
	//查询参数
	var searchParam = new Object();
	searchParam.companyId = companyId;
	searchParam.subCompanyId = subCompanyId;
	searchParam.userId = userId;
	searchParam.groupName = $("#groupName").val();
	searchParam.createUserName = $("#createUserName").val();
	//重新载入mmGrid
	$("#mmGridTable").mmGrid().load(searchParam);
}

/**
 * 加入群组
 */
function joinGroup(groupId,groupType){
	//插入参数
	var param = new Object();
	param.groupId = groupId;
	param.groupType = groupType;
	param.userId = userId;
	
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentGroupAction/addGroupMember.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				if(groupType == 2){
					alert("提交申请成功");
				}else{
					alert("加入成功");
				}
				//刷新页面
				search();
			}else{
				alert("加入失败");
			}
		}
	});
}

/**
 * 跳往查看群组成员页面
 */
function toGroupMembers(groupId){
	window.location.href = '<%=request.getContextPath()%>/studentGroupAction/toGroupMembers.action?groupId='+groupId;
}
</script>
</head>
<body>
	
	<div id="course_all">
		<div class="notes_list">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">加入群组</span>
			</div>
			<!-- <h3>加入群组</h3> -->
			<div class="search_1">
				<span>群组名称：<input id="groupName" type="text"/></span>
				<span>创建人：<input id="createUserName" type="text"/></span>
				<input type="button" class="btn_5" onclick="search();" value="查询"/>
			</div>
			
			<!-- mmGrid展现 -->
			<div class="clear_both"></div>
			<div id="learnPlanMMGridShow" style="padding-bottom:60px;">
				<table id="mmGridTable"></table>
				<div id="mmGridPager" style="text-align: right;"></div>
			</div>
		</div>
	</div>
</body>
</html>