<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问答详情</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**最外部框架*/
#courseQuestion{width:1046px;margin:0 auto;margin-top:60px;}

/**课程问答详情框架*/
#questionDetail{width:1046px;padding-bottom:80px;}

/**课程问答标题*/
#questionCourseTitle{width:1038px;padding-bottom:10px;font-size:16px;color:#010101;
border-bottom:1px solid #000000;padding-left:8px;margin-bottom:20px;
text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}

/**问答列表*/
#questionList{padding-top:10px;padding-bottom:5px;}

/**问答列表无结果显示用*/
#questionListNoResult{display:none;text-align:center;margin-top:120px;}

/**每个问答详情*/
.questionDetailStyle{padding-top:10px;padding-bottom:10px;}

/**问答详情左边*/
.questionLeftStyle{width:5%;float:left;}

/**头像*/
.personImgStyle{width:45px;height:45px;}

/**问答详情右边*/
.questionRightStyle{width:94%;float:left;padding-bottom:10px;}

.blueSpanStyle{padding-left:5px;float:left;color:#2894FF;}

.greySpanStyle{padding-left:5px;float:left;color:grey;}

/**问答内容*/
.questionContentStyle{padding-top:5px;padding-bottom:5px;padding-left:5px;width:100%;word-wrap:break-word;}

/**回答框架*/
.answerDivStyle{border-style:solid;border-width:1px;border-color:grey;display:none;}

/**每条回复*/
.answerDetail{padding-top:5px;padding-bottom:5px;}

/**回复的回复*/
.replyConterntStyle{padding-top:5px;padding-bottom:5px;padding-left:5px;width:99%;word-wrap:break-word;
background:#FFFFBB;}
</style>

<script type="text/javascript">
/**全局变量*/
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var courseId = '${courseId}';
var itemsPerPage = 20;

/**
 * 页面加载完毕
 */
$(function(){
	initCourseQuestions();	
});

/**
 * 初始化问答列表
 */
function initCourseQuestions(){
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
				param.courseId = courseId;
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//默认同步
					data:param,
					url:'<%=request.getContextPath()%>/courseQuestionAction/getQuestionsByCourseId.action',
					success:function(data){
						if(data){
							$("#questionListShow").show();
							$("#questionListNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var question = data[i];
								htmlStr += '<div class="questionDetailStyle">';
								htmlStr += '<div class="questionLeftStyle">';
								htmlStr += '<img class="personImgStyle" src="'+question.userPic+'"></img>';
								htmlStr += '</div>';
								htmlStr += '<div class="questionRightStyle">';
								htmlStr += '<span class="blueSpanStyle">'+question.userName+'</span>';
								htmlStr += '<span class="greySpanStyle">['+question.userPost+']</span>';
								htmlStr += '<br/>';
								htmlStr += '<div class="questionContentStyle">';
								htmlStr += question.content;
								htmlStr += '</div>';
								htmlStr += '<div>';
								htmlStr += '<span class="greySpanStyle">'+getSmpFormatDateByLong(question.asktime)+'</span>';
								htmlStr += '<span class="blueSpanStyle">';
								if(question.replyCount == 0){
									htmlStr += '<a href="javascript:void(0)">回答（0）</a>';
								}else{
									htmlStr += '<a href="javascript:void(0)" onclick="slideToggleAnswers('+question.id+');">回答（'+question.replyCount+'）</a>';
								}
								htmlStr += '</span>';
								htmlStr += '</div>';
								htmlStr += '<br/>';
								
								//根据问题id获取回复内容
								var innerParam = new Object();
								innerParam.topicId = question.id;
								$.ajax({
									type:'POST',
									async:false,//此处为同步
									data:innerParam,
									url:'<%=request.getContextPath()%>/courseQuestionAction/getQuestionReplys.action',
									success:function(data){
										var replys = data;
										if(replys){
											htmlStr += '<div id="answerDiv'+question.id+'" class="answerDivStyle">';
											for(var j = 0 ; j < data.length; j++){
												var reply = data[j];
												htmlStr += '<div class="answerDetail">';
												htmlStr += '<span class="blueSpanStyle">'+reply.userName+'</span>';
												htmlStr += '<span class="greySpanStyle">['+reply.userPost+']</span>';
												htmlStr += '<span class="greySpanStyle">'+getSmpFormatDateByLong(reply.answertime)+'</span>';
												htmlStr += '<br/>';
												if(reply.replyContent){
													htmlStr += '<div class="replyConterntStyle">';
													htmlStr += reply.replyContent;
													htmlStr += '</div>';
												}
												htmlStr += '<div class="questionContentStyle">';
												htmlStr += reply.content;
												htmlStr += '</div>';
												htmlStr += '</div>';
											}
											htmlStr += '</div>';
										}
									}
								});
								
								htmlStr += '</div>';
								htmlStr += '</div>';
							}
							$("#questionList").html(htmlStr);
						}else{
							$("#questionListShow").hide();
							$("#questionListNoResult").show();
						}
					}
				});
			}
	};
	var count = getCourseQuestionsCount();
	$("#questionListPager").pagination(count,opts);
}

/**
 * 获取列表中问答数量
 */
function getCourseQuestionsCount(){
	var count = 0;//列表中问答数量
	var param = new Object();
	param.courseId = courseId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:false,//此处为异步
		data:param,
		url:'<%=request.getContextPath()%>/courseQuestionAction/getCourseQuestionsCount.action',
		success:function(data){
			if(data){
				count = data;
			}
		}
	});
	return count;
}

/**
 * 隐藏显示回答内容
 */
function slideToggleAnswers(questionId){
	$("#answerDiv"+questionId+"").slideToggle();
}
</script>
</head>
<body>
	<div id="courseQuestion">
		<div id="questionDetail">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">${courseName} 的问答</span>
			</div>
			<%-- <div id="questionCourseTitle" title="${courseName} 的问答">${courseName} 的问答</div> --%>
			
			<!-- 展现问答列表 -->
			<div id="questionListShow">
				<!-- 问答列表 -->
				<div id="questionList">
				<%-- <div class="questionDetailStyle">
						<div class="questionLeftStyle">
							<img class="personImgStyle" src="<%=request.getContextPath()%>/images/bg_13.png"></img>
						</div>
						<div class="questionRightStyle">
							<span class="blueSpanStyle">艾欢</span>
							<span class="greySpanStyle">[数据中心总监]</span>
							<br/>
							<div class="questionContentStyle">
								这是一个测试，内容会很长长常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常。
							</div>
							<div>
								<span class="greySpanStyle">2015-09-23 10:53:10</span>
								<span class="blueSpanStyle"><a href="javascript:void(0)" onclick="slideToggleAnswers();">回答（1）</a></span>
							</div>
							<br/>
							<div id="answerDiv" class="answerDivStyle">
								<div class="answerDetail">
									<span class="blueSpanStyle">庞丽丽</span>
									<span class="greySpanStyle">[测试组中级测试工程师]</span>
									<span class="greySpanStyle">2015-10-11 10:53:10</span>
									<br/>
									<div class="replyConterntStyle">
										zhangsan：这是一个测试，内容会很长长常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常。
										<br/>
										lisi：这是一个测试，内容会很长长常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常。
										<br/>
									</div>
									<div class="questionContentStyle">
										这是一个测试，内容会很长长常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常常。
									</div>
								</div>
							</div>
						</div>
					</div> --%>
				</div>
				
				<!-- 分页栏 -->
				<div class="page clear">
					<div class="left_page f1">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a>
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a>
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="questionListPager" class="right_page fr"></div>
				</div>
			</div>
			
			<!-- 页面无结果显示用 -->
			<div id="questionListNoResult">暂无结果</div>
		</div>
	</div>
</body>
</html>