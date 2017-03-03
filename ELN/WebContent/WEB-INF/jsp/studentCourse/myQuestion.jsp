<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的问答</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jqueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

/**问答框*/
.answer{height:auto;}

.answer .right_as{height:auto;}

/**每条问答*/
.answer .right_as p{height:auto;width:864px;word-wrap:break-word;}

/**降序排序*/
.sortDesc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9down.png) no-repeat 75px center;}

/**升序排序*/
.sortAsc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9.png) no-repeat 75px center;}

</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var itemsPerPage = 20;//每页显示条数
var replyItemsPerPage = 20;//回答问题的课程每页显示条数
var sortName = 'last_ask_time';//排序名称
var sortOrder = 'desc';//排序顺序
var questionType = 'ask';//展现问题的类型
	
/**
 * 页面加载完成
 */
$(function(){
	$("#asktimeStart,#asktimeEnd").datepicker({
		changeMonth: true,
		changeYear: true
	});
	//初始化提出的问题
	initAskQuestions();
	//初始化回答的问题
	initReplyQuestions();
	//tab切换
	$('#notes_list li').click(function(){
		$('#notes_list li').attr('class','');
		$(this).attr('class','now');
	});
});
	
/**
 * 展现提出的问题
 */
function showAskQuestions(){
	questionType = 'ask';
	$("#askQuestions").show();
	$("#replyQuestions").hide();
	//切换时置空查询框，刷新问题列表
	$("#courseName").val('');
	$("#asktimeStart").val('');
	$("#asktimeEnd").val('');
	initAskQuestions();
}

/**
 * 展现回答的问题
 */
function showReplyQuestions(){
	questionType = 'reply';
	$("#askQuestions").hide();
	$("#replyQuestions").show();
	//切换时置空查询框，刷新问题列表
	$("#courseName").val('');
	$("#asktimeStart").val('');
	$("#asktimeEnd").val('');
	initReplyQuestions();
}

/**
 * 初始化提出的问题
 */
function initAskQuestions(){
	var opts = {
			items_per_page:itemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){
				var param = new Object();
				param.userId = userId;
				param.courseName = $("#courseName").val();
				param.asktimeStart = $("#asktimeStart").val();
				param.asktimeEnd = $("#asktimeEnd").val();
				param.sortName = sortName;
				param.sortOrder = sortOrder;
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseQuestionAction/getAskQuestions.action',
					success:function(data){
						if(data != null && data != ''){
							$("#askQuestionsContent").show();
							$("#questionNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var questionCourse = data[i];
								htmlStr += '<div class="answer">';
								htmlStr += '<div class="left_as">';
								htmlStr += '<img src="'+questionCourse.frontImage+'" width="132px" height="93px"/>';
								htmlStr += '</div>';
								htmlStr += '<div class="right_as">';
								htmlStr += '<h4 title="'+questionCourse.courseName+'" class="courseNameStyle">';
								htmlStr += ''+questionCourse.courseName+'<span>['+questionCourse.courseCode+']</span>';
								htmlStr += '</h4>';
								htmlStr += '<a href="javascript:void(0)" class="ques">问题'+(i+1)+'</a>';
								htmlStr += '<a href="javascript:void(0)" class="lk" onclick="toQuestionDetail('+questionCourse.courseId+');">查看</a>';
								
								//查询出学员提出的该门课的前2个问题
								var innerParam = new Object();
								innerParam.courseId = questionCourse.courseId;
								innerParam.userId = userId;
								$.ajax({
									type:'POST',
									async:false,//同步
									data:innerParam,
									url:'<%=request.getContextPath()%>/courseQuestionAction/getDoubleQuestions.action',
									success:function(data){
										if(data != null && data != ''){
											var questions = data;
											for(var j = 0; j < questions.length; j++){
												var question = questions[j];
												htmlStr += '<p style="color:black;">'+question+'</p>';
											}
										}
									}
								});
								
								htmlStr += '<input type="text" id="lastAskTime" style="display:none;" value="'+questionCourse.lastAskTime+'"/>';
								htmlStr += '<input type="text" id="questionCount" style="display:none;" value="'+questionCourse.questionCount+'"/>';
								htmlStr += '</div>';
								htmlStr += '</div>';
							}
							$("#askQuestionsContent").html(htmlStr);
						}else{
							$("#askQuestionsContent").hide();
							$("#questionNoResult").show();
						}
					}
				});
			}
	};
	var count = getAskQuestionsCount();
	$("#askQuestionsPager").pagination(count,opts);
}

/**
 * 获取提出的问题数量
 */
function getAskQuestionsCount(){
	var param = new Object();
	param.userId = userId;
	param.courseName = $("#courseName").val();
	param.asktimeStart = $("#asktimeStart").val();
	param.asktimeEnd = $("#asktimeEnd").val();
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseQuestionAction/getAskQuestionsCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 初始化学员回答的问题
 */
function initReplyQuestions(){
	var opts = {
			items_per_page:replyItemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){
				var param = new Object();
				param.userId = userId;
				param.courseName = $("#courseName").val();
				param.asktimeStart = $("#asktimeStart").val();
				param.asktimeEnd = $("#asktimeEnd").val();
				param.sortName = sortName;
				param.sortOrder = sortOrder;
				param.page = page;
				param.pageSize = replyItemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseQuestionAction/getReplyQuestions.action',
					success:function(data){
						if(data != null && data != ''){
							$("#replyQuestionsContent").show();
							$("#replyNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var questionCourse = data[i];
								htmlStr += '<div class="answer">';
								htmlStr += '<div class="left_as">';
								htmlStr += '<img src="'+questionCourse.frontImage+'" width="132px" height="93px"/>';
								htmlStr += '</div>';
								htmlStr += '<div class="right_as">';
								htmlStr += '<h4 title="'+questionCourse.courseName+'" class="courseNameStyle">';
								htmlStr += ''+questionCourse.courseName+'<span>['+questionCourse.courseCode+']</span>';
								htmlStr += '</h4>';
								htmlStr += '<a href="javascript:void(0)" class="ques">问题'+(i+1)+'</a>';
								htmlStr += '<a href="javascript:void(0)" class="lk" onclick="toQuestionDetail('+questionCourse.courseId+');">查看</a>';
								
								//查询出学员回答的该门课的前2个问题
								var innerParam = new Object();
								innerParam.courseId = questionCourse.courseId;
								innerParam.userId = userId;
								$.ajax({
									type:'POST',
									async:false,//同步
									data:innerParam,
									url:'<%=request.getContextPath()%>/courseQuestionAction/getReplyDoubleQuestions.action',
									success:function(data){
										if(data != null && data != ''){
											var questions = data;
											for(var j = 0; j < questions.length; j++){
												var question = questions[j];
												htmlStr += '<p style="color:black;">'+question+'</p>';
											}
										}
									}
								});
								
								htmlStr += '<input type="text" id="lastAskTime" style="display:none;" value="'+questionCourse.lastAskTime+'"/>';
								htmlStr += '<input type="text" id="questionCount" style="display:none;" value="'+questionCourse.questionCount+'"/>';
								htmlStr += '</div>';
								htmlStr += '</div>';
							}
							$("#replyQuestionsContent").html(htmlStr);
						}else{
							$("#replyQuestionsContent").hide();
							$("#replyNoResult").show();
						}
					}
				});
			}
	};
	var count = getReplyQuestionsCount();
	$("#replyQuestionsPager").pagination(count,opts);
	
	//隐藏回答的问题
	if(questionType == 'ask'){
		$("#replyQuestions").hide();
	}else{
		$("#replyQuestions").show();
	}
}

/**
 * 获取学员回答问题的数量
 */
function getReplyQuestionsCount(){
	var param = new Object();
	param.userId = userId;
	param.courseName = $("#courseName").val();
	param.asktimeStart = $("#asktimeStart").val();
	param.asktimeEnd = $("#asktimeEnd").val();
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseQuestionAction/getReplyQuestionsCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 查询
 */
function search(){
	$("#searchButton").attr("class","btn_5");
	$("#resetButton").attr("class","btn_6");
	//查询
	if(questionType == 'ask'){
		initAskQuestions();
	}else if(questionType == 'reply'){
		initReplyQuestions();
	}
}

/**
 * 重置
 */
function reset(){
	$("#searchButton").attr("class","btn_6");
	$("#resetButton").attr("class","btn_5");
	//清空输入框
	$("#courseName").val('');
	$("#asktimeStart").val('');
	$("#asktimeEnd").val('');
	//查询
	if(questionType == 'ask'){
		initAskQuestions();
	}else if(questionType == 'reply'){
		initReplyQuestions();
	}
}

/**
 * 按照提问时间排序
 */
function sortByAskTime(){
	if(sortName == 'last_ask_time' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByAskTime").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByAskTime").attr("class","sortDesc");
	}
	sortName = 'last_ask_time';
	//查询
	if(questionType == 'ask'){
		initAskQuestions();
	}else if(questionType == 'reply'){
		initReplyQuestions();
	}
}

/**
 * 按照提问条数排序
 */
function sortByQuestionCount(){
	if(sortName == 'question_count' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByQuestionCount").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByQuestionCount").attr("class","sortDesc");
	}
	sortName = 'question_count';
	//查询
	if(questionType == 'ask'){
		initAskQuestions();
	}else if(questionType == 'reply'){
		initReplyQuestions();
	}
}

/**
 * 每页显示20条数据
 */
function changePagesizeOfTwenty(){
	$("#pageSizeTwenty").addClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 20;
	//查询
	initAskQuestions();
}

/**
 * 每页显示40条数据
 */
function changePagesizeOfFourty(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").addClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 40;
	//查询
	initAskQuestions();
}

/**
 * 每页显示100条数据
 */
function changePagesizeOfOneHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").addClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 100;
	//查询
	initAskQuestions();
}

/**
 * 每页显示200条数据
 */
function changePagesizeOfTwoHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").addClass("first");
	itemsPerPage = 200;
	//查询
	initAskQuestions();
}

/**回答的问题的分页栏每页显示条数*/

/**
 * 每页显示20条数据
 */
function changeReplyPagesizeOfTwenty(){
	$("#replyPageSizeTwenty").addClass("first");
	$("#replyPageSizeFourty").removeClass("first");
	$("#replyPageSizeOneHundred").removeClass("first");
	$("#replyPageSizeTwoHundred").removeClass("first");
	replyItemsPerPage = 20;
	//查询
	initReplyQuestions();
}

/**
 * 每页显示40条数据
 */
function changeReplyPagesizeOfFourty(){
	$("#replyPageSizeTwenty").removeClass("first");
	$("#replyPageSizeFourty").addClass("first");
	$("#replyPageSizeOneHundred").removeClass("first");
	$("#replyPageSizeTwoHundred").removeClass("first");
	replyItemsPerPage = 40;
	//查询
	initReplyQuestions();
}

/**
 * 每页显示100条数据
 */
function changeReplyPagesizeOfOneHundred(){
	$("#replyPageSizeTwenty").removeClass("first");
	$("#replyPageSizeFourty").removeClass("first");
	$("#replyPageSizeOneHundred").addClass("first");
	$("#replyPageSizeTwoHundred").removeClass("first");
	replyItemsPerPage = 100;
	//查询
	initReplyQuestions();
}

/**
 * 每页显示200条数据
 */
function changeReplyPagesizeOfTwoHundred(){
	$("#replyPageSizeTwenty").removeClass("first");
	$("#replyPageSizeFourty").removeClass("first");
	$("#replyPageSizeOneHundred").removeClass("first");
	$("#replyPageSizeTwoHundred").addClass("first");
	replyItemsPerPage = 200;
	//查询
	initReplyQuestions();
}

/**
 * 跳转到问题详情页面
 */
function toQuestionDetail(courseId){
	window.location.href = '<%=request.getContextPath()%>/courseQuestionAction/toQuestionDetail.action?courseId='+courseId;
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="notes_list fl">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的问答</span>
			</div>
			<!-- <h3>我的问答</h3> -->
			<ul id="notes_list" class="notes_1">
				<li class="now" onclick="showAskQuestions();">提出的问题</li>
				<li onclick="showReplyQuestions();">回答的问题</li>
			</ul>
			<div class="search_1 fl">
				<span>课程名称：<input type="text" id="courseName"/></span> 
				<span>
					提问时间：
					<input type="text" id="asktimeStart"/>
					至
					<input type="text" id="asktimeEnd"/>
				</span> 
				<input id="searchButton" type="button" class="btn_5" value="查询" onclick="search();"/> 
				<input id="resetButton" type="button" class="btn_6" value="重置" onclick="reset();"/>
				<div class="sort_1 fr">
					<a id="sortByAskTime" class="sortDesc" href="javascript:void(0)" onclick="sortByAskTime();">按提问时间</a> 
					<a id="sortByQuestionCount" class="sortDesc" href="javascript:void(0)" onclick="sortByQuestionCount();">按提问条数</a>
				</div>
			</div>
			
			<!-- 提出的问题 -->
			<div id="askQuestions">
				<div id="askQuestionsContent">
				</div>
				
				<!-- 页面无结果显示用 -->
				<div id="questionNoResult" style="display:none;text-align:center;margin-top:120px;">
					暂无结果
				</div>
				
				<!-- 分页栏 -->
				<div class="page clear" style="padding-top:50px;">
					<div class="left_page fl">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="askQuestionsPager" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 回答的问题 -->
			<div id="replyQuestions">
				<div id="replyQuestionsContent">
				</div>
				
				<!-- 页面无结果显示用 -->
				<div id="replyNoResult" style="display:none;text-align:center;margin-top:120px;">
					暂无结果
				</div>
				
				<!-- 分页栏 -->
				<div class="page clear" style="padding-top:50px;">
					<div class="left_page fl">
						<a id="replyPageSizeTwenty" onclick="changeReplyPagesizeOfTwenty();" class="first">20</a> 
						<a id="replyPageSizeFourty" onclick="changeReplyPagesizeOfFourty();">40</a> 
						<a id="replyPageSizeOneHundred" onclick="changeReplyPagesizeOfOneHundred();">100</a>
						<a id="replyPageSizeTwoHundred" onclick="changeReplyPagesizeOfTwoHundred();">200</a>
					</div>
					<div id="replyQuestionsPager" class="right_page fr">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>