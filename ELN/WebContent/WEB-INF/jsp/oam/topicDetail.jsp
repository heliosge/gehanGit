<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专题详情</title>
<!-- 引入样式表 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面操作js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- 引入dateUtil.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<!-- 页面样式 -->
<style type="text/css">
/*我的评价*/
#evaluateText{width: 925px;height: 200px;}
#submitEvaluate{
width:87px;height:27px;
border-style:none;
background:#2dc0ef;color:#fff;
float:right;}
#courseEvaluatePager{margin-right:100px;}
</style>

<script type="text/javascript">
var topic = ${topic};
	
	/**
	 * 页面加载完成
	 */
	$(function(){
		initCourse();
		fillTopicInfo();
	});
	
	/**
	 * 初始化课程详情
	 */
	function fillTopicInfo(){
		$("#frontImage").attr("src",topic.coverImage);
		$("#topicName").html(topic.name);
		$("#descr").html(topic.descr);
		$("#topicNo").html('编号：'+topic.no);
		$("#topicDetail").html('<p>'+topic.descr+'</p>');
	}
	
	function initCourse(){
		$('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
	    	width: $('#exampleTable').parent().width(),
	    	height: 'auto',
	    	 params: function(){
	    		 var o = {};
	    		 o.topicId = topic.id;
	 	    	return o;
	 	    },
	    	url : '<%=request.getContextPath()%>/oam/selectOamCourseByTopic.action',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        multiSelect: true,
	        checkCol: false,
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	     	      {title: '课程名称', name: 'name', width:60, align:'center', renderer:function(val, item, rowIndex){
					   return '<a href="<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+item.id+'" >'+val+'</a>';
				   }},
	               {title: '课程编号', name: 'code', width:60, align:'center'},
				   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
					   if(val == 1){
						   return "线上课程 ";
					   }else if(val == 2){
						   return "直播课程";
					   }
					   return "";
				   }},
				   {title: '课程体系', name: 'categoryName', width:60, align:'center'}
	           ]
	    });
	}
	
	
	
	/**
	 * 跳转到课程学习页面
	 */
	function toCourseStudy(courseWareId,courseWareType){
		window.location.href = '<%=request.getContextPath()%>/courseStudyAction/toCourseStudy.action?courseWareId='+courseWareId+'&courseWareType='+courseWareType+'&courseId='+courseBean.id;
	}
</script>
</head>
<body>
	<div id="detail">
	
		<div class="de_con">
		
			<div class="con_top">
				<div style="width:50%;float:left;">
					<h4 id="topicName"></h4>
					<!-- <p id="courseOutline"></p> -->
					<p id="topicNo"></p>
				</div>
				<div style="width:45%;float:left;">
					<img id="frontImage" src="" width="443px" height="250px"/>
				</div>
			</div>
			
			<div class="log">
				<div class="log_top" id="log">
					<ul style="width:272px;">
						<li><a href="javascript:void(0);">专题详情</a></li>
						<li class="change"><a href="javascript:void(0);">专题课程</a></li>
					</ul>
					<h5 style="width:657px;"><a href="#" class="ask" style="background:white;"></a></h5>
					
					<!-- 课程详情 -->
					<div id="topicDetail" class="course_detail fl"></div>
					
					<!-- 课程目录 -->
					<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>