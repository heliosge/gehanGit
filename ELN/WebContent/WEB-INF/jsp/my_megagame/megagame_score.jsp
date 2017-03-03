<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成绩公示</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.cp_5 .left_ul{margin-top: 20px;}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';// 当前大赛id
$(function(){
	getAllMatchBymegagameId();
	initScore();
});

/**
 * 左侧tab样式控制 自定义
 */
function tabStyle(){
	$("#sc").find("li").click(function(){
		$("#sc").find("li").each(function(){
			$(this).removeAttr('class');
			$(this).find("img").remove();
		});
		$(this).attr('class','active_b');
		$(this).append("<img src=\"<%=request.getContextPath()%>/images/img/ico_21.png\" />");
	});
}
/**
 * 获取大赛所有赛程信息
 */
function getAllMatchBymegagameId(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAllMatchBymegagameId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initMatchTab(data.rtnDataList);
			}
		}
	});
}
function initMatchTab(list){
	var matchUl = $("#sc");
	matchUl.empty();
	var html="";
	if(list){
		var len = list.length;
		for(var i=0;i<len;i++){
			if (!list[i]) {
				continue;
			}
			var matchNum = list[i].orderNum;
			if(i==0){
				html +="<li onclick=\"turnMatch("+list[i].id+")\" class=\"active_b\">赛程"+matchNum+"-成绩<img src=\"<%=request.getContextPath()%>/images/img/ico_21.png\" /></li>";
			}else{
				html +="<li onclick=\"turnMatch("+list[i].id+")\" >赛程"+matchNum+"-成绩</li>"
			}
		}
	}
	matchUl.html(html);
	matchUl.find("li").eq(0).click();
	tabStyle();
}
/**
 * 切换赛程
 */
function turnMatch(matchId){
	var param = new Object;
	param.processId = matchId;
	$('#scoreTable').mmGrid().load(param);
}
/**
 * 初始化成绩grid
 */
function initScore(){
	$('#scoreTable').mmGrid(
			{
				root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
				url : "<%=request.getContextPath()%>/MyMegagame/getJoinListByProcessId.action",
				width : '837px',
				height : 'auto',
				fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
				showBackboard : false,
				checkCol : false,
				multiSelect : true,
				indexCol : true, // 索引列
				params : function(){
					// 封装参数
					var param = new Object();
					param.userId = userId;
					param.megagameId = megagameId;
					return param;
				},
				cols : [
						{
							title : 'id',
							name : 'id',
							width:120,
							hidden : true
						},
						{
							title : '姓名',
							name : 'userName',
							width:120,
							align : 'center'
						},
						{
							title : '岗位',
							name : 'post',
							width:120,
							align : 'center'
						},
						{
							title : '部门',
							width:150,
							name : 'departmentName',
							align : 'center'
						},
						{
							title : '成绩',
							width:120,
							name : 'score',
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								if (val == -1) {
									str = "-";
								}else{
									str = val;
								}
								return str;
							}
						},
						{
							title : '当前排名',
							name : 'rownum',
							width:120,
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								if (val == -1) {
									str = "-";
								}else{
									str = val;
								}
								return str;
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
</script>
</head>
<body>
	<div class="cp_5" id="cp_5" style="display: block;">
		<div class="left_ul" id="left_ul">
			<ul id="sc">
			</ul>
		</div>
		<div style="float: right;">
			<h5>成绩公示</h5>
			<div>
				<table id="scoreTable"></table>
				<div id="paginatorPaging" style="text-align: right;"></div>
			</div>
		</div>
	</div>
</body>
</html>