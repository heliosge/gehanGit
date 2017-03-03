<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>考试统计（按部门）</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	initGird();
});
//初始化grid数据
function initGird() {
	$('#cjpyTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/reportForms/getExamDetailByDept.action',
						width : '1065px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						autoLoad:false,
						params : function(){
							var param = new Object();
							var deptName = $.trim($("#department").val());
							param.depName = deptName;
							return param;
						},
						indexCol : true, // 索引列
						cols : [{title : 'id',name : 'id',hidden : true},
								{title : '部门',width : 170,name : 'deptName',align : 'center'},
								{title : '参与考试人数/总数',width : 150,name : 'attendNum',align : 'center',
									renderer : function(val, item, rowIndex) {
										return val +" / "+item.userNum;
									}
								},
								{title : '考试人数覆盖率',name : 'attendPercent',width : 120,align : 'center',
									renderer : function(val, item, rowIndex) {
										return val + "%";
									}
								},
								{title : '参与考试数',width : 70,name : 'attendExamNum',align : 'center'},
								{title : '排行',name : 'rank',width : 80,align : 'center',
									renderer : function(val, item, rowIndex) {
										return val;
									}
								},
								{title : '操作',name : 'operation',width : 150,align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "<a href=\"javascript:void(0);\" onclick=\"toPreview("+item.id+")\">详情</a>&nbsp;";
										return str;
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
/**
 * 搜索
 */
function doSearch(){
	$("#cjpyTable").mmGrid().load({"page":1});
}
/**
 * 跳转预览
 */
function toPreview(id){
	window.location.href="<%=request.getContextPath()%>/reportForms/toGradeDetailByDept.action?deptId="+id;
}

/**
 * 重置
 */
 
 function doReset(){
	$("#department").val("");
	$("#cjpyTable").mmGrid().load();
}
</script>

</head>
<body>
	<div class="content">
		<!-- <h3>成绩批阅</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">考试统计（按部门）</span>
		</div>
		<div class="content_2">
			<div class="search_2 fl">
				<p>
					部门： <input id="department" type="text" /> 
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