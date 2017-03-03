<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程详情</title>
<!-- 引入样式表 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
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
/**课程名称*/
.courseNameStyle{
text-overflow: ellipsis;
white-space: nowrap;
overflow: hidden;
}
</style>

<script type="text/javascript">
	var userId = '${USER_ID_LONG}';
	var companyId = '${USER_BEAN.companyId}';
	var subCompanyId = '${USER_BEAN.subCompanyId}';
	var courseBean = ${courseBean};
	var itemsPerPage = 20;//每页显示条数
	
	/**
	 * 页面加载完成
	 */
	$(function(){
		//初始化该课程所有目录结构
		initThisCourseCategory();
		//初始化课程详情
		initCourseDetails();
		//初始化所有章节和课件
		initCourseChapters();
		//初始化课程测试
		//initCourseTest();
		//初始化课程评价
		initCourseEvaluates();
	});
	
	/**
	 * 初始化课程详情
	 */
	function initCourseDetails(){
		$("#frontImage").attr("src",courseBean.frontImage);
		$("#courseName").html(courseBean.name);
		//$("#courseOutline").html(courseBean.outline);
		//$("#courseCode").html('编号：'+courseBean.code);
		$("#courseTags").html('标签：'+courseBean.tags);
		$("#courseDetails").html(courseBean.outline);
		$("#courseTeacher").html('讲师：');
		$.ajax({
			type:'POST',
			async:true,//默认异步
			data:{teacherId:courseBean.teacherId},
			url:'<%=request.getContextPath()%>/courseDetailAction/getTeacherNameById.action',
			success:function(data){
				if(data != null && data != ''){
					$("#courseTeacher").append(data);
				}
			}
		});
		
	}
	
	/**
	 * 初始化课程体系
	 */
	function initThisCourseCategory(){
		var param = new Object();
		param.courseId = courseBean.id;
		$.ajax({
			type:'POST',
			async:true,//异步
			data:param,
			url:'<%=request.getContextPath()%>/courseDetailAction/getThisCourseCategory.action',
			success:function(data){
				if(data != null && data != ''){
					var htmlStr = '课程\\';
					for(var i = 0; i < data.length; i++){
						var category = data[i];
						htmlStr += category.name + '\\';
						//设置课程分类
						if(category.parentId == null){
							$("#courseFenlei").html('分类：'+category.name);
						}
					}
					htmlStr += courseBean.name;
					//$("#categorysTitle").html(htmlStr);
				}
			}
		});
	}
	
	/**
	 * 初始化所有章节和课件
	 */
	function initCourseChapters(){
		var param = new Object();
		param.courseId = courseBean.id;
		$.ajax({
			type:'POST',
			async:false,//同步
			data:param,
			url:'<%=request.getContextPath()%>/courseDetailAction/getCourseSections.action',
			success:function(data){
				if(data != null && data != ''){
					var htmlStr = '';
					var sections = data;
					for(var i = 0; i < sections.length; i++){
						var section = sections[i];
						htmlStr += '<div class="thir_capter">';
						htmlStr += '<h3>';
						htmlStr += '第'+(i+1)+'章';
						htmlStr += '<span >'+section.name+'</span>';
						htmlStr += '<a style="padding-left:700px;float:right;margin-top: -7px;" onclick="slideToggleOfSections('+section.id+');">';
						htmlStr += '<img src="<%=request.getContextPath()%>/images/img/btn_1.png" />';
						htmlStr += '</a>';
						htmlStr += '</h3>';
						
						if(i == 0){
							htmlStr += '<div id="section'+section.id+'" style="padding-top:30px;margin-top:5px;">';
						}else{
							htmlStr += '<div id="section'+section.id+'" style="padding-top:30px;margin-top:5px;display:none;">';
						}
						//章节介绍
						htmlStr += '<div style="width:800px;word-wrap:break-word;padding-bottom:10px;color:grey;font-size:15px;">';
						if(section.descr != null){
							htmlStr += '章节简介：'+section.descr;
						}
						htmlStr += '</div>';
						//根据章节id获取所有课件及课件的学习进度
						var innerParam = new Object();
						innerParam.sectionId = section.id;
						innerParam.userId = userId;
						$.ajax({
							type:'POST',
							async:false,//同步
							data:innerParam,
							url:'<%=request.getContextPath()%>/courseDetailAction/getSectionWares.action',
							success:function(data){
								if(data != null && data != ''){
									htmlStr += '<div style="padding-top:5px;padding-bottom:5px;">';
									var wares = data;
									for(var j = 0; j < wares.length; j++){
										var ware = wares[j];
										htmlStr += '<h4>';
										if(ware.type == 1){
											htmlStr += '标准课件';
										}else if(ware.type == 2){
											htmlStr += '普通课件';
										}else if(ware.type == 3){
											htmlStr += '视频课件';
										}
										htmlStr += '<span>';
										htmlStr += '<a>';
										htmlStr += ware.name;
										htmlStr += '</a>';
										htmlStr += '</span>';
										/* htmlStr += '<b>'+ware.progressPercent+'%</b>';
										htmlStr += '<a class="continue" onclick="toCourseStudy('+ware.id+','+ware.type+','+section.id+')">继续学习</a>'; */
										htmlStr += '</h4>';
									}
									htmlStr += '</div>';
									htmlStr += '<br/>';
								}
							}
						});
						
						//根据章节id查询所有章节测试的测试时长、测试次数、及格分、测试最高分、是否及格
						var testParam = new Object();
						testParam.sectionId = section.id;
						$.ajax({
							type:'POST',
							async:false,//此处为同步
							data:testParam,
							url:'<%=request.getContextPath()%>/res/selectCourseExam.action',
							success:function(data){
								if(data != null && data != ''){
									htmlStr += '<table width="928px">';
									var exams = data;
									for(var k = 0; k < exams.length; k++){
										htmlStr += '<tr height="30">';
										var examRecord = exams[k];
										htmlStr += '<td>章节测试</td>';
										htmlStr += '<td>'+examRecord.title+'</td>';
										htmlStr += '<td>测试时长：'+examRecord.examDuration+'分钟</td>';
										htmlStr += '<td>测试次数：'+examRecord.examTimes+'次</td>';
										htmlStr += '<td>通过比例：'+examRecord.passPercent+'</td>';
										htmlStr += '</tr>';
									}
									htmlStr += '</table>';
								}
							}
						});
						
						htmlStr += '</div>';
						htmlStr += '</div>';
					}
					$("#courseChapters").html(htmlStr);
				}
			}
		});
	}
	
	/**
	 * slideToggle章节内容
	 */
	function slideToggleOfSections(sectionId){
		$("#section"+sectionId+"").slideToggle();
	}
	
	/**
	 * 初始化课程测试
	 */
	function initCourseTest(){
		var param = new Object();
		param.courseId = courseBean.id;
		$.ajax({
			type:'POST',
			async:true,//异步
			data:param,
			url:'<%=request.getContextPath()%>/res/selectCourseExam.action',
			success:function(data){
				var htmlStr = '';
				htmlStr += '<h3>总结：<span>测试</span></h3>';
				if(data != null && data != ''){
					for(var i = 0; i < data.length; i++){
						var examRecord = data[i];
						htmlStr += '<h4>';
						htmlStr += examRecord.title;
						htmlStr += '<strong>';
						htmlStr += '<a>测试时长：'+examRecord.examDuration+'分钟</a>';
						htmlStr += '</strong>';
						htmlStr += '<b>测试次数：'+examRecord.examTimes+'次</b>';
						/* if(examRecord.testTimes < examRecord.permitTestTimes){
							htmlStr += '<a class="test">进入测试</a>';
						}else{
							htmlStr += '<a class="over">进入测试</a>';
						} */
						htmlStr += '</h4>';
					}
				}
				$("#lastChapter").html(htmlStr);
			}
		});
	}
	
	/**
	 * 获取该课程评价人数
	 */
	function getEvaluateCount(){
		//查询参数
		var param = new Object();
		param.courseId = courseBean.id;
		param.companyId = companyId;
		param.subCompanyId = subCompanyId;
		//总条数
		var count = 0;
		$.ajax({
			type:'POST',
			async:false,//同步
			data:param,
			url:'<%=request.getContextPath()%>/courseEvaluateAction/getEvaluateCount.action',
			success:function(data){
				count = data;
			}
		});
		return count;
	}
	
	/**
	 * 初始化课程评价
	 */
	function initCourseEvaluates(){
		var opts = {
				items_per_page:itemsPerPage,//每页显示条数
				prev_text:'上一页',
				next_text:'下一页',
				prev_show_always:false,//不一直显示上一页
				next_show_always:false,//不一直显示下一页
				num_edge_entries:2,//两侧显示的首尾分页的条目数
				callback:function(page,node){//page从0开始，指代当前页，node是分页的div
					//查询参数
					var param = new Object();
					param.courseId = courseBean.id;
					param.companyId = companyId;
					param.subCompanyId = subCompanyId;
					param.page = page;//当前页
					param.pageSize = itemsPerPage;//每页显示条数
					//异步查询
					$.ajax({
						type:'POST',
						async:true,//异步
						data:param,
						url:'<%=request.getContextPath()%>/courseEvaluateAction/getCourseEvaluations.action',
						success:function(data){
							if(data != null && data != ''){
								var htmlStr = '';
								for(var i = 0; i < data.length; i++){
									var evaluate = data[i];
									var starId = 'star_'+ evaluate.id;
									var score = evaluate.score;
									htmlStr += '<div class="course_pj">';
									htmlStr += '<div class="left_pj">';
									htmlStr += '<img src="<%=request.getContextPath()%>/images/img/ico_11.png" />';
									htmlStr += '</div>';
									htmlStr += '<div class="right_pj">';
									htmlStr += '<p>';
									htmlStr += '<span style="float:left;color:#0d9cb8;">'+evaluate.userName+'</span>';
									htmlStr += '&nbsp;&nbsp;'+evaluate.score+'分';
									htmlStr += '</p>';
									htmlStr += '</div>';
									htmlStr += '<span style="color:gray;float:right;">';
									htmlStr += getSmpFormatDateByLong(evaluate.commentTime);
									htmlStr += '</span>';
									htmlStr += '<br/><br/>';
									htmlStr += '<p>'+evaluate.content+'</p>';
									htmlStr += '</div>';
								}
								$("#courseEvaluateDetail").html(htmlStr);
							}
						}
					});
				}
		};
		//分页查询
		var count = getEvaluateCount();
		$("#courseEvaluatePager").pagination(count,opts);
	}
	
	/**
	 * 每页显示20条数据
	 */
	function changePagesizeOfTwenty(){
		//修改样式
		$("#pageSizeTwenty").addClass("first");
		$("#pageSizeFourty").removeClass("first");
		$("#pageSizeOneHundred").removeClass("first");
		$("#pageSizeTwoHundred").removeClass("first");
		//修改pageSize
		itemsPerPage = 20;
		//查询
		initCourseEvaluates();
	}
	
	/**
	 * 每页显示40条数据
	 */
	function changePagesizeOfFourty(){
		//修改样式
		$("#pageSizeTwenty").removeClass("first");
		$("#pageSizeFourty").addClass("first");
		$("#pageSizeOneHundred").removeClass("first");
		$("#pageSizeTwoHundred").removeClass("first");
		//修改pageSize
		itemsPerPage = 40;
		//查询
		initCourseEvaluates();
	}
	
	/**
	 * 每页显示100条数据
	 */
	function changePagesizeOfOneHundred(){
		//修改样式
		$("#pageSizeTwenty").removeClass("first");
		$("#pageSizeFourty").removeClass("first");
		$("#pageSizeOneHundred").addClass("first");
		$("#pageSizeTwoHundred").removeClass("first");
		//修改pageSize
		itemsPerPage = 100;
		//查询
		initCourseEvaluates();
	}
	
	/**
	 * 每页显示200条数据
	 */
	function changePagesizeOfTwoHundred(){
		//修改样式
		$("#pageSizeTwenty").removeClass("first");
		$("#pageSizeFourty").removeClass("first");
		$("#pageSizeOneHundred").removeClass("first");
		$("#pageSizeTwoHundred").addClass("first");
		//修改pageSize
		itemsPerPage = 200;
		//查询
		initCourseEvaluates();
	}
	
	/**
	 * 评价课程
	 */
	function evaluateCourse(){
		//设置参数
		var score = $("#scoreText").val();
		if(score == null || score == ''){
			dialog.alert("评分不能为空");
			return;
		}
		if(isNaN(score)){
			dialog.alert("评分填写错误，请填写数字");
			return;
		}
		if(score > 5 || score < 1){
			dialog.alert("请填写1到5之间的数字");
			return;
		}
		var content = $("#evaluateText").val();
		if(content == null || content == ''){
			dialog.alert("评价不能为空");
			return;
		}
		var param = new Object();
		param.courseId = courseBean.id;
		param.userId = userId;
		param.score = score;
		param.commentTime = new Date();
		param.content = content;
		param.companyId = companyId;
		param.subCompanyId = subCompanyId;
		//评论课程
		$.ajax({
			type:'POST',
			async:false,//同步
			data:param,
			url:'<%=request.getContextPath()%>/courseEvaluateAction/addCourseEvaluation.action',
			success:function(data){
				if(data.rtnResult == 'SUCCESS'){
					dialog.alert("添加评论成功！");
					initCourseEvaluates();
					//清空评分和评论栏
					$("#scoreText").val("");
					$("#evaluateText").val("");
				}
			}
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
	
		<div class="head_de">
			<!-- <h3 id="categorysTitle"  class="courseNameStyle"></h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程详情</span>
			 </div>
			<%-- <a href="#"><img src="<%=request.getContextPath()%>/images/img/bg_11.png" /></a> 
			<a href="#"><img src="<%=request.getContextPath()%>/images/img/bg_12.png" /></a> --%>
		</div>
		
		<div class="de_con">
		
			<div class="con_top">
				<div style="width:35%;float:left;">
					<img id="frontImage" src="" width="323px" height="225px"/>
				</div>
				<div style="width:60%;float:left;">
					<h4 id="courseName" title="${courseBean.name }" class="courseNameStyle"></h4>
					<!-- <p id="courseOutline"></p> -->
					<p id="courseCode"></p>
					<p id="courseFenlei"></p>
					<p id="courseTags"></p>
					<p id="courseTeacher"></p>
				</div>
			</div>
			
			<div class="log">
				<div class="log_top" id="log">
					<ul>
						<li><a href="javascript:void(0);">课程详情</a></li>
						<li class="change"><a href="javascript:void(0);">课程目录</a></li>
						<li><a href="javascript:void(0);">课程评价</a></li>
					</ul>
					<h5><a href="#" class="ask" style="background:white;"></a></h5>
					
					<!-- 课程详情 -->
					<div id="courseDetails" class="course_detail fl" style="height:auto;min-height:300px;word-wrap:break-word;"></div>
					
					<!-- 课程目录 -->
					<div id="courseChapters" class="course_log fl" style="height:auto;min-height:300px;"></div>
					
					<!-- 课程评价 -->
					<div id="courseEvaluate" class="course_ev fl" style="height:auto;min-height:300px;">
						<!-- 具体评价 -->
						<div id = "courseEvaluateDetail">
	                    </div>
	                    <!-- 评价分页 -->
						<div class="page clear">
							<!-- 每页显示数量栏 -->
							<div class="left_page fl">
								<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
								<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
								<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
								<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
							</div>
							<!-- 分页栏 -->
							<div id="courseEvaluatePager" class="right_page fr">
							</div>
						</div>
						
						<!-- 给出我的评价 -->
						<!-- <div id="myEvaluate">
						 	<div id="evaluateScore">
						 		<h4><span>给出你的评分：</span></h4>
						 		<input type="text" id="scoreText" style="width:50px;"/>
						 		（请填写1到5之间的数字）
						 	</div>
						 	<br/>
						 	<div id="evaluateContent">
						 		<h4>给出你的评价：</h4>
						 		<br/>
						 		<textarea id="evaluateText"></textarea>
						 		<br/><br/>
						 		<input id="submitEvaluate" type="button" onclick="evaluateCourse();" value="提交评价"/>
						 	</div>
						</div> -->
					 </div>
				</div>
			</div>
		</div>
	</div>

	<!-- 页脚 -->
	<jsp:include page="../common/footer.jsp"/>

</body>
</html>