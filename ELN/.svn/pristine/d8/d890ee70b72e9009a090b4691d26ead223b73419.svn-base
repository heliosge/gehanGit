<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训详情</title>
<!-- 引入样式表 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- 引入页面操作js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- 引入dateUtil.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<!-- 页面样式 -->
<style type="text/css">
h3 {
	margin-top: 16px;
    background: url(<%=request.getContextPath()%>/images/img/ico_4.png) no-repeat left 2px;
    width: 1046px;
    padding-left: 20px;
    color: #3a3a3a;
    border-bottom: 1px solid #cccccc;
    padding-bottom: 10px;
    margin-bottom: 10px;
}
.arrangeNameStyle{
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.arrangeDescrStyle{
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	font-weight:normal;
}

.tal{text-align: left;}

.tar{text-align: right;}

.grid_style {border:solid #e4e4e4; border-width:1px 0px 0px 1px;width:100%;margin-top: 10px;}
.grid_style th{text-align:center; height:40px; border:solid #e4e4e4; border-width:0px 1px 1px 0px;background-color: #494949;color:white;}
.grid_style td{text-align:center; height:40px; border:solid #e4e4e4; border-width:0px 1px 1px 0px;}
</style>

<script type="text/javascript">

var time = ${time};
var contents = ${contents};
	
	/**
	 * 页面加载完成
	 */
	$(function(){
		//初始化培训基本信息
		initArrangeInfo();
		//初始化培训内容
		initArrangeContents();
		
		//初始化培训资源
		initArrangeSource();
		
	});
	
	
	function initArrangeInfo(){
		var beginTime = ${arrange.beginTimeDate.time};
		var endTime = ${arrange.endTimeDate.time};
		$("#status").append(beginTime>time?'未开始':((beginTime < time && endTime >time)?"进行中":"已结束"));
	}
	
	function initArrangeContents(){
		var grid = $('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
	    	width: $('#exampleTable').width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        //indexCol: true,  //索引列
	     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
	               {title: '培训内容', name: 'content', width:120, align:'center', renderer:function(val, item, rowIndex){
		            	  return val.content;
				   }},
	               {title: '培训形式', name: 'content', width:60, align:'center', renderer:function(val, item, rowIndex){
	            	  return val.trainType == 1 ?'在线培训':'面授培训';
				   }},
	 			   {title: '培训时间', name: 'content', width:120, align:'center', renderer:function(val, item, rowIndex){
	 				  return val.beginTime+"—"+val.endTime;
				   }},
				   {title: '培训讲师', name: 'content', width:60, align:'center', renderer:function(val, item, rowIndex){
		 				  return val.teacherName;
				   }},
				   {title: '培训地点', name: 'content', width:60, align:'center', renderer:function(val, item, rowIndex){
					   return val.trainType == 1 ?'——': val.place;
				   }},
				   {title: '是否签到', name: 'isSign', width:40, align:'center', renderer:function(val, item, rowIndex){
					   return item.content.trainType == 1 ?'——': (val=='1'?'已签到':'未签到');
				   }},
				   {title: '通过条件', name: 'content', width:120, align:'center', renderer:function(val, item, rowIndex){
					  // return (val.afterClassExam != 0 ? "考试成绩不少于"+val.passPercent+"%" : (val.passPercent != 0 ? "考试成绩不少于"+val.passPercent+"分" :(val.trainType == 1 ? "完成课程" :"签到")))
						//	   +(item.isPass == 1 ?'/已通过':"/未通过");
					  return val.trainType == 1 ?'完成课程':  "学习成绩不少于"+val.passPercent+"分";
				   }},
				   {title: '操作', name: 'content', width:120, align:'center', renderer:function(val, item, rowIndex){
					   return val.trainType == 1 ?'<a href="<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+val.courseId+'">我要学习</a>':'' ;
					   }}
	           ]
	    });
		
		grid.on("loadSuccess",function(e, data){
			$.each(contents, function(index, val){
				grid.addRow(val);
			})
		});
	}
	
	function initArrangeSource(){
		var html = '<div class="lesson_add_2">';
		$.each(contents,function(index, val){
			if(val.content.beforeClassCourseId){
				html += '<div class="add_gr" style="background: #EEE;"> <div class="add_fl" style="width:1000px;font-weight: bold;text-align:left;"><em>&nbsp;&nbsp;'+val.content.beforeClassCourseName
				+'【课程】 </em></div> <div class="add_fr tar" style="width:46px;background: #CCCCCC;"><a href="<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+val.content.beforeClassCourseId+'">进入</a>&nbsp;&nbsp;</div></div>';
			}
			if(val.content.beforeClassFileName){
				html += '<div class="add_gr" style="background: #EEE;"> <div class="add_fl" style="width:1000px;font-weight: bold;text-align:left;"><em>&nbsp;&nbsp;'+val.content.beforeClassFileName
				+'【文件】 </em></div> <div class="add_fr tar" style="width:46px;background: #CCCCCC;"><a href="javascript:void(0);" onclick="downLoadFile(\''+val.content.beforeClassFileName+'\',\''+val.content.beforeClassFilePath+'\')">下载</a>&nbsp;&nbsp;</div></div>';
			}
		})
		html += '</div>';
		$("#source").html(html);
	}
	
	//跳转到课程学习页面
	function toCourseDetail(courseId){
		window.location.href = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+courseId;
	}
	
	//下载文件
	function downLoadFile(fileName, path){
		$("#fileName").val(fileName);
		$("#filePath").val(path);
		$("#form").submit();
	}
	
	//考试
	function gotoMatchTest(arrangeId, examId){
		//新建考试记录
		$.ajax({
			type:'POST',
			async:true,//默认异步
			data:{arrangeId: arrangeId, examId: examId},
			url:'<%=request.getContextPath()%>/train/gotoMatchTest.action',
			success:function(data){
				if(data != null){
					if(data.testTimes < ${arrange.aceAllowTimes }){
						window.open("<%=request.getContextPath() %>/train/gotoExamTest.action?exam_assign_id="+data.assignId+"&arrangeId="+arrangeId+"&examId="+examId,"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
					}else{
						dialog.alert("考试次数过多，不能参与。");
					}
				}
			}
		});
	}
	
</script>
</head>
<body>
	<div id="detail">
			<!-- <h3>培训详情</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训详情</span>
		</div>
		<div class="de_con" style="border:none;width:1046px;padding-left: 0px;">
			<form id="form" action="downTrainArrangeContentBeforeClassFile.action" method="post">
				<input type="hidden" id="fileName" name="fileName"/>
				<input type="hidden" id="filePath" name="path"/>
			</form>
			<div class="con_top" style="width:1046px;">
				<div style="width:45%;float:left;">
					<img id="frontImage" src="${arrange.cover }" width="443px" height="250px"/>
				</div>
				<div style="width:53%;float:right;border:1px solid #cccccc;">
					<h4 title="${arrange.name }" class="arrangeNameStyle">${arrange.name }</h4>
					<p>&nbsp;</p>
					<p>适合人群：${arrange.fitCrowd }</p>
					<p>&nbsp;</p>
					<p title="${arrange.descr }" class="arrangeDescrStyle">培训简介：${arrange.descr } </p>
					<p>&nbsp;</p>
					<p id="status">培训状态：</p>
					<p>&nbsp;</p>
					
				</div>
			</div>
			
			<div class="log">
				<div class="log_top" id="log"  style="width:1046px;">
					<ul>
						<li class="change"><a href="javascript:void(0);">培训详情</a></li>
						<li><a href="javascript:void(0);">调研评估</a></li>
						<li><a href="javascript:void(0);">培训资源</a></li>
					</ul>
					<h5 style="width: 639px;"></h5>
					
					<!-- 培训详情 -->
					<div class="course_detail fl" style="height:auto;min-height:300px;display:block;width:1046px;">
							<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
							<p>&nbsp;</p>
							<div id="exampleTable2" style="margin-top:10px;width:100%;" ></div>
					</div>
					
					<!-- 调研评估 -->
					<div id="test" class="course_log fl" style="height:auto;min-height:300px;display:none;width:1046px;padding-top:30px;">
						<table class="grid_style">
							<tr>
								<th width="60%">课后评估</th>
								<th width="20%">
									状态
								</th>
								<th width="20%">
									操作
								</th>
							</tr>
							<c:forEach var="item" items="${afterClassTests }" varStatus="status">
							<tr>
								<td>${item.contentName }——${item.name }</td>
								<td>
									 <c:choose>
					                	<c:when test="${item.status==2 }">
					                		已提交
					                	</c:when>
					                	<c:otherwise>
					                		未提交
					                	</c:otherwise>
					                </c:choose>
								</td>
								<td>
									<a href="toQuestionnaireDetail.action?contentId=${item.contentId}&assignId=${item.assignId}&qId=${item.id}">查看</a>
									<c:choose>
					                	<c:when test="${item.status==1 }">
					                		<a href="toAnswerQuestionnaire.action?contentId=${item.contentId}&assignId=${item.assignId}&qId=${item.id}">进入</a>
					                	</c:when>
					                	<c:otherwise>
					                		<a href="javascript:void(0);" style="">进入</a>
					                	</c:otherwise>
					                </c:choose>
								</td>
							</tr>
							</c:forEach>
						</table>
						
						<table class="grid_style">
							<tr>
								<th width="40%">通过考试</th>
								<th width="20%">
									考试成绩
								</th>
								<th width="20%">
									是否通过
								</th>
								<th width="20%">
									操作
								</th>
							</tr>
							 <c:choose>
				                	<c:when test="${arrange.afterClassExam != 0 }">
										<tr>
											<td>${arrange.afterClassExamName }</td>
											<td>${arrange.score }</td>
											<td>
												 <c:choose>
								                	<c:when test="${arrange.isPass==1 }">
								                		已通过
								                	</c:when>
								                	<c:otherwise>
								                		未通过
								                	</c:otherwise>
								                </c:choose>
											</td>
											<td>
												<a href="#" onclick="gotoMatchTest(${arrange.id},${arrange.afterClassExam})">考试</a>
											</td>
										</tr>
								</c:when>
							</c:choose>
						</table>
					</div>
					
					<!-- 培训资源-->
					<div id="source" class="course_ev fl" style="height:auto;min-height:300px;width:1046px;border:1px;">
						
					 </div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>