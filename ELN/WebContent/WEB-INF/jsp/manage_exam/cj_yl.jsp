<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成绩预览</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqprint.js"></script>
<style type="text/css">
.button_cz fl{float: none;}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var examId = '${examId}';
var x_rank=0;
$(function(){
	initGird();
	getExamInfo();
});
var temp = 0;
//初始化grid数据
function initGird() {
	$('#cjylTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/exam/exam/getCjYlList.action',
						width : '1065px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							var param = new Object();
							var name = $("#name").val();
							var post = $("#post").val();
							var depName = $("#depName").val();
							var isAttend = $("#isAttend").val();
							param.userName = name;
							param.postName = post;
							param.depName = depName;
							param.isAttend = isAttend;
							param.userId = userId;
							param.examId = examId;
							return param;
						},
						cols : [
								{
									title : 'id',
									name : 'examId',
									hidden : true
								},
								{
									title : '姓名',
									name : 'name',
									align : 'center',
									width: 80
								},
								{
									title : '身份证号',
									name : 'id_card',
									align : 'center',
									width: 120
								},
								{
									title : '岗位',
									name : 'postName',
									align : 'center',
									width: 100
								},
								{
									title : '部门',
									name : 'departmentName',
									align : 'center',
									width: 100
								},{
									title : '是否参与考试',
									name : 'isAttended',
									align : 'center',
									width: 70,
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},{
									title : '成绩',
									name : 'score',
									align : 'center',
									width: 70,
									renderer : function(val, item, rowIndex) {
										if(val){
											return val;
										}else{
											return 0;
										}
									}
								},{
									title : '是否通过',
									name : 'isPassed',
									align : 'center',
									width: 70,
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},{
									title : '排名',
									name : 'rank',
									align : 'center',
									width: 70,
									renderer : function(val, item, rowIndex) {
										if(val && val!=0){
											//x_rank =val;
											temp = val;
											return val;
										}else{
											//x_rank=temp+1;
											return temp+1;
										}
										//return x_rank;
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
 * 获取考试信息
 */
function getExamInfo(){
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getExamInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"examId" : examId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initExamInfo(data.rtnData);
			}
		}
	});
}

/**
 * 生成考试信息
 */
function initExamInfo(data){
	var totalScore = 0;
	var passScore = 0;
	var duration = 0;
	var title = "";
	var html="";
	
	var ti = $("#ts_id");
	
	if(data){
		totalScore = data.totalScore;
		passScore = Math.floor((data.passScorePercent/100)*totalScore);
		duration = data.duration;
		title = data.paperName;//试卷名称
	}
	html +="<span>总分："+totalScore+"</span>";
	html +="<span>及格分："+passScore+"</span>";
	html +="<span>考试时长："+duration+"</span>";
	$("#title_pp").html(title);
	$("#title_pp2").html(title);
	ti.empty();
	ti.html(html);
	$("#ts_id2").html(html);
}
/**
 * 搜索
 */
function doSearch(){
	$('#cjylTable').mmGrid().load({"page":1});
}
/**
 * 导出word
 */
function exportDoc(){
	var form = $("#exportDocForm");
	form.submit();
}
/**
 * 导出excel
 */
function exportExcel(){
	var form = $("#exportExcelForm");
	form.submit();
}
function doPrint(){
	var ode = $("#pppp");
	var param = new Object();
	param.userId = userId;
	param.examId = examId;
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getCjYlListAll.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : param,
		success : function(data) {
			var html="";
			html +="<table border=\"1\">";
			html +="<tr>";
			html +="<th>姓名</th>";
			html +="<th>岗位</th>";
			html +="<th>部门</th>";
			html +="<th>是否参与考试</th>";
			html +="<th>成绩</th>";
			html +="<th>是否通过</th>";
			html +="<th>排名</th>";
			html +="</tr>";
			data.map(function(v,i){
				var name=v.name;
				var departmentName=v.departmentName;
				var postName=v.postName;
				if(!postName || postName=="null"){
					postName="";
				}
				if(!departmentName || departmentName=="null"){
					departmentName="";
				}
				var isAttended=v.isAttended==true?"是":"否";
				var isPassed=v.isPassed==true?"是":"否";
				var score=v.score;
				if(!score || score=="null"){
					score=0;
				}
				var rank=v.rank;
				if(!rank || rank=="null"){
					rank="";
				}
				html+="<tr>";
				html+="<td width=\"150px\" align=\"center\">"+name+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+postName+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+departmentName+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+isAttended+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+score+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+isPassed+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+rank+"</td>";
				html+="</tr>";
			});
			html +="</table>";
			$("#datas").html(html);
			ode.show();
			ode.jqprint();
			ode.hide();
		}
	});
}

//打印多个试卷时，需要加载的数量，与已加载的数量
var myPrintNum = {"total":0, "load":0};
function doPrintPaper(){
	//alert(1);
	
	myPrintNum = {"total":0, "load":0};
	
	$("#allPaper").html("");
	var ode = $("#allPaper");
	
	var param = new Object();
	param.userId = userId;
	param.examId = examId;
	
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getCjYlListAll.action";
	$.ajax({
		type : "POST",
		async: false,  //默认true,异步
		url : urlStr,
		data : param,
		success : function(data) {
			//alert(JSON.stringify(data));
			/* html +="<tr>";
			html +="<th>姓名</th>";
			html +="<th>岗位</th>";
			html +="<th>部门</th>";
			html +="<th>是否参与考试</th>";
			html +="<th>成绩</th>";
			html +="<th>是否通过</th>";
			html +="<th>排名</th>";
			html +="</tr>"; */
			
			myPrintNum.total = data.length;
			
			data.map(function(v,i){
				
				var html="";
				html +="<table >";
				
				var name=v.name;
				if(!name){
					name = "";
				}
				
				var departmentName=v.departmentName;
				var postName=v.postName;
				if(!postName || postName=="null"){
					postName="";
				}
				if(!departmentName || departmentName=="null"){
					departmentName="";
				}
				var isAttended=v.isAttended==true?"是":"否";
				var isPassed=v.isPassed==true?"是":"否";
				var score=v.score;
				if(!score || score=="null"){
					score=0;
				}
				var rank=v.rank;
				if(!rank || rank=="null"){
					rank="";
				}
				html+="<tr>";
				html+="<td align=\"left\">姓名："+name+"</td>";
				html+="<td style='padding-left:20px;' align=\"left\">岗位："+postName+"</td>";
				html+="<td style='padding-left:20px;' align=\"left\">部门："+departmentName+"</td>";
				html+="<td style='padding-left:20px;' align=\"left\">是否参加考试："+isAttended+"</td>";
				html+="<td style='padding-left:20px;' align=\"left\">成绩："+score+"</td>";
				html+="<td style='padding-left:20px;' align=\"left\">是否通过："+isPassed+"</td>";
				html+="</tr>";
				
				html +="</table>";
				$("#allPaper").append(html);
				
				//考试信息
				/* var examHtml = "";
				examHtml += "<div>";
				examHtml += "<span>"+$("#title_pp").text()+"</span>";
				examHtml += "<span style='margin-left:20px;' >"+$("#ts_id").find("span").eq(0).text()+"</span>";
				examHtml += "<span style='margin-left:20px;' >"+$("#ts_id").find("span").eq(1).text()+"</span>";
				examHtml += "<span style='margin-left:20px;' >"+$("#ts_id").find("span").eq(2).text()+"</span>";
				examHtml += "</div>"; 
				
				$("#allPaper").append(examHtml);*/
				
				//试卷信息
				if(isAttended == "否"){
					//未参加考试
					$("#allPaper").append("<div style='margin-bottom:40px;font-size:16px;' >未参加考试，无试卷信息</div>");
					myPrintNum.load ++;
					
				}else{
					
					var url = "<%=request.getContextPath()%>/myExamManage/gotoExamDetail.action?exam_assign_id="+v.id+"&isPy=1&toPrint=toPrint";
					
					$("#allPaper").append("<iframe id='paperIframe_"+v.id+"' frameborder='0' style='width:100%;min-width:800px;' src='"+url+"' ></iframe>");
					
				}
			});
			
			
		}
	});
	
	var printTime = setInterval(function(){
		//alert(myPrintNum.total +"   "+ myPrintNum.load);
		//需要加载的试卷 与 已加载的试卷数量 一样时
		if(myPrintNum.total == myPrintNum.load){
			
			clearInterval(printTime);
			
			ode.show();
			ode.jqprint();
			ode.hide();
		}
	}, 1000);
}

/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/exam/exam/toCjPyList.action";
}
</script>
</head>
<body>
<div class="content">
	<!-- <h3>成绩预览</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">成绩预览</span>
	</div>
    <div class="py_top" >
    	<h4 id="title_pp">2015届一年级语文期中考试</h4>
    </div>
    <div class="ts" id="ts_id">
    </div>
    <div class="button_cz fl" style="margin:0; padding-bottom:20px;">
    	<form id="exportDocForm" action="<%=request.getContextPath()%>/exam/exam/exportDoc.action" style="float: left;">
    		<input type="hidden"  name ="userId" value="${USER_ID_LONG}">
    		<input type="hidden" name="examId"  value="${examId}">
	    	<input type="button" value="导出word" onclick="exportDoc()"/>
    	</form>
    	<form id="exportExcelForm" action="<%=request.getContextPath()%>/exam/exam/exportExcel.action"style="float: left;">
    		<input type="hidden"  name ="userId" value="${USER_ID_LONG}">
    		<input type="hidden" name="examId"  value="${examId}">
	        <input type="button" value="导出excel" onclick="exportExcel()"/>
    	</form>
        <input type="button" value="打印成绩"  onclick="doPrint()"/>
        <input type="button" value="打印试卷" onclick="doPrintPaper()"/>

    </div>
    <div class="content_2">
        <div class="search_2 fl">
            <p>
                姓名：
                <input id="name" type="text"/>
                岗位：
                <input id="post" type="text" />
                部门：
                <input id="depName" type="text" />
                是否参与考试：
                <select id="isAttend">
                	<option selected="selected" value="">显示全部</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </p>
            <input type="button" value="查询" class="btn_cx" onclick="doSearch()"/>
    
       	</div>
       	<div class="clear_both"></div>
        <div>
            <table id="cjylTable"></table>
            <div id="paginatorPaging" style="text-align: right;left: -18px;position: relative;"></div>
         </div>

    </div>
</div>
<div id="pppp" style="display: none;">
	<div class="py_top" >
    	<h4 id="title_pp2">2015届一年级语文期中考试</h4>
    </div>
    <div class="ts" id="ts_id2"></div>
    <div id="datas"></div>
</div>

<div id="allPaper" style="display:none;" >

</div>

</body>
</html>