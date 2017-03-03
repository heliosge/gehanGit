<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>线下考试成绩查看</title>

<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqprint.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script src="<c:url value="/js/layer/layer.js"/>"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">

var passScore = parseInt("${offlineTestBean.pass_score}", 10);

$(function(){

	//表格
	$('#questionTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/offlineTest/getOfflineTestResult.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //checkCol: true,
        //multiSelect: true,
        indexCol: true,  //索引列
        nowrap: true,
        params:function(){
        	var param = new Object();
        	
        	param.offline_test_id = "${offlineTestBean.id}";
        	
        	return param;
        },
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
			   {title: '姓名', name: 'name', width:120, align:"center"},
			   {title: '邮箱', name: 'email', width:140, align:"center"},
			   {title: '联系电话', name: 'phone', width:120, align:"center"},
			   {title: '岗位', name: 'job', width:80, align:"center"},
			   {title: '部门', name: 'department', width:80, align:"center"},
			   {title: '成绩', name: 'score', width:80, align:"center"},
			   {title: '是否通过', name: 'score', width:50, align:"center", renderer:function(val, item, rowIndex){
				   
				   if(val >= passScore){
					   return "是";
				   }
			       
			       return "否";
			   }}
           ],
        plugins : [
        	$('#paginator').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 30, 50]
        	})
        ]
    });
	
	var startTime =  $("#start_time").text();
	$("#start_time").text(startTime.substring(0, 19));
	$(".start_time").text(startTime.substring(0, 19));
	
	var endTime =  $("#end_time").text();
	$("#end_time").text(endTime.substring(0, 19));
	$(".end_time").text(endTime.substring(0, 19));
});

//打印
function doPrint(){
	
	layer.msg('打印准备中...', {icon: 16, time: 0, shade: [0.3, '#393D49']});
	
	//清除老数据
	$("#printTable table").find("tr:gt(0)").remove();
	
	$.ajax({
		type : "POST",
		url : "<%=request.getContextPath()%>/offlineTest/getOfflineTestById.action",
		data : "id=${offlineTestBean.id}",
		success : function(data) {
			//alert(JSON.stringify(data));
			
			if(data && data.resultList){
				for(var i=0; i<data.resultList.length; i++){
					
					var user = data.resultList[i];
					
					var html = '<tr>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+(i+1)+'</td>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+getStr(user.name)+'</td>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+getStr(user.email)+'</td>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+getStr(user.phone)+'</td>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+getStr(user.job)+'</td>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+getStr(user.department)+'</td>'+
				        			'<td align="center" valign="middle" style="font-size:13px;padding:2px;">'+getStr(user.score)+'</td>';
				        			
				   	if(user.score >= passScore){
					   	html += '<td align="center">是</td>';
				   	}else{
					   	html += '<td align="center">否</td>';
				   	}
				   	html += '</tr>'; 			
					
				   	$("#printTable table").append(html);
				}
			}
			
			var ode = $("#printTable");
			ode.show();
			ode.jqprint();
			
			layer.closeAll();
			
			ode.hide();
		}
	});
}

function getStr(str){
	
	if(str){
		return str;
	}
	
	return "";
}

function backPaper(){
	//返回
	window.location.href = "<%=request.getContextPath()%>/offlineTest/testList.action";
}

//导出
function ecport(){
	$("#exportForm").submit();
}

</script>
<style type="text/css">
.test_word_0{
	line-height:28px;font-size:13px;font-weight: bold;
}
.test_word_1{
	line-height:28px;font-size:13px;
}
</style>
</head>

<body>

<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content" style="margin-top:20px;padding-bottom:10px;">

	<!-- <h3>线下考试查看</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backPaper();"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">线下考试查看</span>
	</div>

    <div class="right_lesson" style="padding-bottom:0px;margin-left:0px;width:100%;">
        <div class="button_gr" style="height:30px;line-height:30px;width:100%;">
            <input type="button" value="打印" onclick="doPrint();" style="background-color:#D60500;width:100px;" />
            <input type="button" value="导出" onclick="ecport();" style="background-color:#D60500;width:100px;" />
            <input type="button" value="返回" class="back" onclick="backPaper()" style="width:100px;"  />
        </div>
        <!-- <div class="search_3" style="margin-top:16px;width:1044px;">
            <p> 
               	考试名称：
                <input type="text" id="title" />
                                考试时间：
                <input type="text" id="time_start"  />
                                至：
               	<input type="text" id="time_end"  />
            </p>
            <input type="button" value="重置" onclick="clearSearchOptions()" />
            <input type="button" value="查询" class="btn_cx" onclick="reloadmmGrid(1)" />
        </div> -->
        
        <div style="clear:both;height:20px;" ></div>
        
        <div style="padding-top:0px;">
        	<span class="test_word_0">考试名称：</span>
        	<span class="test_word_1">${offlineTestBean.name}</span>
        </div>
        <div>
        	<span class="test_word_0">考试时间：</span>
        	<span class="test_word_1"><span id="start_time">${offlineTestBean.start_time}</span> 至 <span id="end_time">${offlineTestBean.end_time}</span></span>
        	<span class="test_word_0" style="margin-left:100px;">总分：</span>
        	<span class="test_word_1">${offlineTestBean.total_score}</span>
        	<span class="test_word_0" style="margin-left:100px;">及格分数：</span>
        	<span class="test_word_1">${offlineTestBean.pass_score}</span>
        </div>
        
         <div style="clear:both;height:15px;" ></div>
        
        <div class="" style="" >
            <div id="questionTable"></div>
            <div id="paginator" align="right" style="margin-top:10px;" ></div>
        </div>
    </div>
	
	<div id="printTable" style="display:none;" >
		<div style="padding-top:0px;">
        	<span style="line-height:28px;font-size:13px;font-weight: bold;" >考试名称：</span>
        	<span style="line-height:28px;font-size:13px;" >${offlineTestBean.name}</span>
        </div>
        <div>
        	<span style="line-height:28px;font-size:13px;font-weight: bold;" >考试时间：</span>
        	<span style="line-height:28px;font-size:13px;" ><span class="start_time">${offlineTestBean.start_time}</span> 至 <span class="end_time">${offlineTestBean.end_time}</span></span>
        	<span style="line-height:28px;font-size:13px;font-weight: bold;margin-left:60px;">总分：</span>
        	<span style="line-height:28px;font-size:13px;" >${offlineTestBean.total_score}</span>
        	<span style="line-height:28px;font-size:13px;font-weight: bold;margin-left:60px;">及格分数：</span>
        	<span style="line-height:28px;font-size:13px;" >${offlineTestBean.pass_score}</span>
        </div>
        
        <table style="width:100%;margin-top:10px;" border="1" >
        	<thead>
        		<tr>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">序号</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">姓名</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">邮箱</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">联系电话</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">岗位</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">部门</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">成绩</th>
        			<th align="center" valign="middle" style="font-size:13px;padding:2px;">是否通过</th>
        		</tr>
        	</thead>
        </table>
	</div>
	
	<form id="exportForm" action="<%=request.getContextPath()%>/offlineTest/exportTest.action" method="post" target="_blank" style="display:none;">
		<input name="id" value="${offlineTestBean.id}" />
	</form>
	
</div>
</body>
</html>
