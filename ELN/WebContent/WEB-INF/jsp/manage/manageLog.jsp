<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>操作日志</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- jQuery UI -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/themes/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js" ></script>

<style type="text/css">
.mmGrid,.mmPaginator{

}

</style>

<script type="text/javascript">

$(function(){
	
	var allUserList = ${userList};
	//dialog.alert(JSON.stringify(allUserList));
	$("#operateId").append("<option value='0' >--全部--</option>");
	for(var i=0; i<allUserList.length; i++){
		$("#operateId").append("<option value='"+allUserList[i].id+"' >"+allUserList[i].name+"</option>");
	}
	
	//时间插件
	$("#operateTime, #operateTimeEnd").datepicker({
		dateFormat:"yy-mm-dd",
		maxDate: 0
	});
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',
		url:"<%=request.getContextPath()%>/manageLog/getManageLog.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //checkCol: true,
        //multiSelect: true,
        indexCol: true,  //索引列
        params:function(){
        	var param = new Object();
        	
        	param.operateId = $.trim($("#operateId").val());
        	param.operateTime = $.trim($("#operateTime").val());
        	param.operateTimeEnd = $.trim($("#operateTimeEnd").val());
        	
        	return param;
        },
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
			   {title: '操作人', name: 'operateName', width:70},
			   {title: '操作时间', name: 'operateTime', width:160, renderer:function(val, item, rowIndex){
				   return val.substr(0, 19);
			   }},
			   {title: '操作内容', name: 'content', width:600}
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',   //对应MMGridPageVoBean count
        		page: 1,                   //初始页
        		pageParamName: 'page',     //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [20, 30, 50]
        	})
        ]
    });
});

//查询
function search(){
	/* var param = new Object();
	
	param.code = $.trim($("#code").val());
	param.name = $.trim($("#name").val()); */
	
	$('#exampleTable').mmGrid().load({"page":1});
}


</script>
</head>
<body style="">

	<div class="top_page_name">操作日志</div>
	
	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;">
		
		<!-- 查询 -->
		<table class="param_table" cellpadding="0" cellspacing="0" style="">
			<tr>
				<td style="width:10px;"></td>
				<td style="width:250px;">
					<span>操作员：</span>
					<select id="operateId" name="operateId" class="input_0">
						
					</select>
				</td>
				<td style="width:350px;">
					<span>操作时间：</span>
					<input value="${todayDate}" type="text" id="operateTime" name="operateTime" class="input_0" style="width:120px;" />
					-
					<input value="${todayDate}" type="text" id="operateTimeEnd" name="operateTimeEnd" class="input_0" style="width:120px;" />
				</td>
				<td style="">
					<button class="btu_0" onclick="search()">查询</button>
				</td>
			</tr>
		</table>
		
		<div style="margin:3px 10px 10px 10px;"></div>
		
		<!-- 表格 -->
		<table id="exampleTable"></table>
		<div id="paginator11-1" style="text-align:right;"></div>
		
	</div>
	
</body>
</html>
