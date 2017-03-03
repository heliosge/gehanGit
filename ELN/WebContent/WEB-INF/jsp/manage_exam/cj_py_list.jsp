<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成绩批阅列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	initGird();
	initDatepicker();
});
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

	$('#cjpyTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/exam/exam/getCjPyList.action',
						width : '1065px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						params : function(){
							var param = new Object();
							var name = $("#name").val();
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							param.beginTimeBegin = startTime;
							param.beginTimeEnd = endTime;
							param.title = name;
							return param;
						},
						indexCol : true, // 索引列
						cols : [
								{
									title : 'id',
									name : 'examId',
									hidden : true
								},
								{
									title : '考试名称',
									width : 170,
									name : 'title',
									align : 'center'
								},
								{
									title : '考试时间',
									name : 'beginTime',
									width : 210,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "";
										var beginTime = getFormatDateByLong(val, "yyyy-MM-dd hh:mm");
										var endTime = getFormatDateByLong(item.endTime, "yyyy-MM-dd hh:mm");
										str = beginTime+"至"+endTime
										return str;
									}
								},
								{
									title : '成绩是否公开',
									name : 'isScorePublic',
									width : 50,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},
								{
									title : '参与/及格/安排人数',
									name : 'attendCount',
									width : 80,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str="";
										var attendCount = val;//参与
										var passCount = item.passCount;//及格
										var assignCount = item.assignCount;//安排
										str =  attendCount+"/"+passCount+"/"+assignCount;
										return str;
									}
								},
								{
									title : '批阅人数/总人数',
									name : 'markCount',
									width : 80,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str="";
										var markCount = val;//批阅人数
										var totalCountToBeMarked = item.totalCountToBeMarked;//总人数
										str = markCount+"/"+totalCountToBeMarked;
										return str;
									}
								},{
									title : '考试创建时间',
									name : 'createTime',
									width : 100,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm");
									}
								},
								{
									title : '操作',
									name : 'operation',
									width : 220,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "";
										var eid = item.examId;//考试id
										var isScorePublished = item.isScorePublished;//是否发布
										var isMarked = item.isMarked; //是否完成批阅
										var btn1 = "";var btn2 = "";
										var btn3 = "";var btn4 = "";
										if(!isScorePublished){//未发布的可以批阅
											btn1 = "<a href=\"javascript:void(0);\" onclick=\"toRead("+eid+")\">批阅</a>&nbsp;";
										}
										if(item.isScorePublished){//已完成批阅的可以发布
											btn2 = "<a href=\"javascript:void(0);\" >已发布</a>&nbsp;";
										}
										if(item.isMarked && !item.isScorePublished){
											btn2 = "<a href=\"javascript:void(0);\" onclick=\"toPublic("+isMarked+","+eid+")\">成绩发布</a>&nbsp;";
										}
										if(isMarked && isScorePublished){//已批阅并且发布的考试才可以修改
											btn3 = "<a href=\"javascript:void(0);\" onclick=\"toModify("+eid+")\">成绩修改</a>&nbsp;";
										}
										if(item.isScorePublished){//已完成批阅的才能预览
											btn4 = "<a href=\"javascript:void(0);\" onclick=\"toPreview("+eid+")\">成绩预览</a>&nbsp;";
										}
										str = btn1+btn2+btn3+btn4;
										return str;
									}
								} ],
						plugins : [ $('#paginatorPaging').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
														// count
							page : 1, // 初始页
							pageParamName : 'pageNo', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}
/**
 * 搜索
 */
function doSearch(){
	$("#cjpyTable").mmGrid().load({"page":1});
}
/**
 * 跳转批阅
 */
function toRead(eid){
	window.location.href="<%=request.getContextPath()%>/exam/exam/gotoPy.action?examId="+eid;
}
/**
 * 跳转预览
 */
function toPreview(id){
	window.location.href="<%=request.getContextPath()%>/exam/exam/toCjYlList.action?examId="+id;
}

/*成绩修改  */
function toModify(id){
	window.location.href="<%=request.getContextPath()%>/exam/exam/gotoCjXgList.action?examId="+id;
}
/**
 * 发布
 */
 function toPublic(isMarked,id){
	if(!isMarked){//未批阅完
		dialog.alert("成绩未批阅完成,不允许发布！");
	}else{
		dialog.confirm('确认发布吗？', function(){
			var urlStr = "<%=request.getContextPath()%>/exam/exam/doPublish.action";
			$.ajax({
				type : "POST",
				async : false,
				url : urlStr,
				data : {
					"examId" : id
				},
				success : function(data) {
					if (data.rtnResult == "SUCCESS") {
						dialog.alert("操作成功！", function (){
							$("#cjpyTable").mmGrid().load();
						});
					}
				}
			});
		});
	}
	 
}
/**
 * 重置
 */
 
 function doReset(){
	$("#name").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	$("#cjpyTable").mmGrid().load();
}
</script>

</head>
<body>
	<div class="content">
		<!-- <h3>成绩批阅</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">成绩批阅</span>
		</div>
		<div class="content_2">
			<div class="search_2 fl">
				<p>
					考试名称： <input id="name" type="text" /> 考试开始时间： <input id="startTime" type="text" /> <span>
					至</span> <input id="endTime" type="text" /> <!-- 分类： <select id="type">
						<option selected="selected" value="">显示全部</option>
						<option value="">未发布</option>
						<option value="">已结束</option>
					</select> -->

				</p>
				<input type="button" value="重置" onclick="doReset()"/> <input type="button" value="查询" class="btn_cx" onclick="doSearch()"/>
			</div>
		</div>
		<div class="clear_both"></div>
		<div>
            <table id="cjpyTable"></table>
            <div id="paginatorPaging" style="text-align: right;margin-right: 18px;"></div>
         </div>
		
	</div>
</body>
</html>