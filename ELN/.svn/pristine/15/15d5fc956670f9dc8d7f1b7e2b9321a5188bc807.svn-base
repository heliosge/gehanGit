<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的报名</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
$(function() {
	initGird();
	initDatepicker();
})

// 初始化grid数据
function initGird() {
	$('#applicationTable')
			.mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/MyMegagame/getMyApplications.action',
						width : '1046px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var param = new Object();
							var searchName = $("#search_name").val();
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							var status = $("#select_1").val();
							param.userId = userId;
							param.megagameName = $.trim(searchName);
							param.startTime = $.trim(startTime);
							param.endTime = $.trim(endTime);
							param.status = status;
							return param;
						},
						cols : [
								{
									title : 'id',
									name : 'id',
									hidden : true
								},
								{
									title : '大赛名称',
									name : 'name',
									align : 'center'
								},
								{
									title : '创建时间',
									name : 'createTime',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									title : '报名时间',
									name : 'applyTime',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									title : '审批状态',
									name : 'approvalStatus',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "";
										if (val == 1) {
											str = "待审批";
										} else if (val == 2) {
											str = "审批通过";
										} else if (val == 3) {
											str = "审批拒绝";
										}
										return str;
									}
								},
								{
									title : '拒绝理由',
									name : 'refuseReason',
									align : 'center'
								},
								{
									title : '操作',
									name : 'operation',
									width : 150,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var id = item.id;
										var contestId = item.contestId;
										return "<a href=\"javascript:void(0); toSeeMore("+id+","+contestId+")\">查看</a>&nbsp;";
									}
								} ],
						plugins : [ $('#paginatorPaging').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
														// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}
function initDatepicker() {
	$("#startTime").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd'
	});

	$("#endTime").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd'
	});
}
/**
 * 查看
 */
function toSeeMore(id,megagameId){
	window.location.href = "<%=request.getContextPath()%>/MyMegagame/toSeeMoreApplication.action?id="+id+"&megagameId="+megagameId;
}
/**
 * 搜索
 */
function doSearch() {
	$('#applicationTable').mmGrid().load({"page":1});
}
/**
 * 重置按钮
 */
function doReset() {
	$("#search_name").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	$("#select_1").val(0);
	$('#applicationTable').mmGrid().load({"page":1});
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="notes_list fl">
			<!-- <h3>我的报名</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的报名</span>
			</div>
			<div class="search_1">

				<span>大赛名称：<input id="search_name" type="text" /></span> 
				<span>报名时间：<input id="startTime" type="text" class="input_3" />至
				<input id="endTime" type="text" class="input_3" /></span> 
				<span>审批状态：
					 <select id="select_1">
						<option value="0" selected="selected">显示全部</option>
						<option value="1">待审批</option>
						<option value="2">审批通过</option>
						<option value="3">审批拒绝</option>
					</select>
				</span> <input type="button" class="btn_5" onclick="doSearch()" value="查询" /> <input type="button" onclick="doReset()" class="btn_6" value="重置" />
			</div>
			<div style="clear: both;"></div>
			<div>
				<table id="applicationTable"></table>
				<div id="paginatorPaging" style="text-align: right;"></div>
			</div>
		</div>
		<jsp:include page="../common/footer.jsp" />

	</div>
</body>
</html>
