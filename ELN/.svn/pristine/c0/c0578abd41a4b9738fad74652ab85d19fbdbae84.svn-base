<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>他人的知识</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />

<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var othersId = '${othersId}';

$(function(){
	getPeopleInfo();
	initGird();
	initDatepicker();
});
function getPeopleInfo(){
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getPeopleInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : othersId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				var name = data.rtnData.name;
				var count = data.count;
				var html=name+"贡献的知识["+count+"篇]";
				$("#head_info").html(html);
			}
		}
	});
}
function initDatepicker() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});

	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
}
//初始化grid数据
function initGird() {
	$('#othersKLTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/knowledgeLibraryInfo/getOthersKnowledge.action',
						width : '1046px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var params = new Object();
							var searchName = $("#searchName").val();
							var categoryName = $("#categoryName").val();
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							params.userId = userId;
							params.othersId = othersId;
							params.searchName = searchName;
							params.categoryName = categoryName;
							params.startTime = startTime;
							params.endTime = endTime;
							return params;
						},
						cols : [
								{
									title : 'id',
									name : 'knowledgeId',
									hidden : true
								},
								{
									title : '知识名称',
									name : 'knowledgeName',
									align : 'center'
								},
								{
									title : '知识库分类',
									name : 'categoryName',
									align : 'center'
								},
								{
									title : '上传时间',
									name : 'createTime',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
									}
								}],
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

/**
 * 搜索
 */
function doSearch(){
	$("#othersKLTable").mmGrid().load({"page":1});
}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list" style="padding-bottom: 0px">
			<!-- <h3>他人的知识</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">他人的知识</span>
			</div>
			<div>
				<h4 class="gx" id="head_info">张三贡献的知识[1篇]</h4>
				<div class="search_5">
					<p>
						<span>知识名称：</span> <input type="text" id="searchName"/> 
						<span>知识库分类：</span> <input type="text" id="categoryName"/> 
						<input type="button" class="btn_5" value="查询" onclick="doSearch()"/>
					</p>
					<p>
						<span>时间：</span> <input type="text" id="startTime"/> <em>至</em> <input type="text" id="endTime"/>
					</p>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
		<div>
			<table id="othersKLTable"></table>
			<div id="paginatorPaging" style="text-align: right;"></div>
		</div>
	</div>
</body>
</html>