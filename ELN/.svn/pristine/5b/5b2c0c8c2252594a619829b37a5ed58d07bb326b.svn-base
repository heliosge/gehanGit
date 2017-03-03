<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专题问答详情</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**框架*/
#thematicAskDetailContent {
	width: 1066px;
	margin: 16px auto 0px;
	padding-bottom: 200px;
	clear: both;
	overflow: hidden;
}

/**框架标题*/
#thematicAskDetailContent>h3 {
	width: 1052px;
	padding-left: 20px;
	color: #3A3A3A;
	border-bottom: 1px solid #CCC;
	padding-bottom: 10px;
	margin-bottom: 10px;
	font-size: 14px;
}

/**链接属性*/
a {
	text-decoration: none;
	cursor: pointer;
}

/**返回连接*/
#thematicAskDetailContent>h3>a {
	font-size: 24px;
	color: #888888;
}

/**问题*/
#askDiv {
	width: 990px;
	min-height: 160px;
	height: auto;
	border: 1px solid #0091DE;
	margin-left: 20px;
}

/**问题标题部分*/
#askTitle {
	width: 970px;
	height: 30px;
	background-color: #0091DE;
	color: #FFF;
	padding-left: 20px;
	padding-top: 8px;
	font-size: 13px;
	font-weight: bold;
}

/**问题内容部分*/
#askContent {
	width: 970px;
	min-height: 140px;
	height: auto;
	background-color: #FFF;
	color: #000000;
	padding-left: 20px;
	padding-top: 8px;
	padding-bottom: 15px;
}

#askContent>h3 {
	margin: 3px auto;
	font-size: 16px;
}

#askAboult {
	color: grey;
	font-size: 13px;
}

#askDetailContent {
	padding-top: 20px;
	font-size: 13px;
	width: 970px;
	word-wrap: break-word;
}

#askerAndTime {
	color: grey;
	font-size: 13px;
}

/**满意答案*/
#satisfactoryAnswerDiv {
	width: 970px;
	min-height: 80px;
	height: auto;
	border: 1px solid rgba(221, 5, 0, 0.58);
	margin-bottom: 5px;
	margin-left: 20px;
	padding-left: 20px;
	padding-top: 8px;
	padding-bottom: 15px;
	background-color: #FFF;
	margin-bottom: 20px;
	margin-top: 20px;
}

#satisfactoryAnswerDiv  h3 {
	margin: 3px auto;
	font-size: 16px;
}

#answerContent {
	font-size: 13px;
	width: 950px;
	word-wrap: break-word;
}

#answerTime {
	color: grey;
	font-size: 13px;
}

/**其他答案*/
#otherAnswerDiv {
	width: 970px;
	min-height: 80px;
	height: auto;
	border: 1px solid #0091DE;
	margin-bottom: 5px;
	margin-left: 20px;
	padding-left: 20px;
	padding-top: 8px;
	padding-bottom: 15px;
}

#otherAnswerDiv>h3 {
	margin: 3px auto;
	font-size: 16px;
}

.otherAnswerContent {
	font-size: 13px;
	width: 950px;
	word-wrap: break-word;
}

.otherAnswerTime {
	color: grey;
	font-size: 13px;
}

/**em样式*/
.thisEmStyle {
	font-style: normal;
}

/**添加图片div*/
#addPicsDiv {
	width: 950px;
}

.addPicStyle {
	width: 155px;
	height: 80px;
	padding-right: 10px;
}

.replyContentStyle{font-size: 15px;color: rgb(0, 145, 222);}

.addCoursesOrFilesStyle{font-size: 13px;margin-bottom: 5px;}
</style>

<script type="text/javascript">
var thematicAsk = ${thematicAsk};

$(function(){
	//获取问问基本信息
	getAskDetail();
	//获取回答列表
	getAnswerList();
});

/**
 * 获取问问基本信息
 */
function getAskDetail(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"askId":thematicAsk.id},
		url:'<%=request.getContextPath()%>/myAsk/getAskDetail.action',
		success:function(data){
			if(data != ''){
				$("#askContentH3").text(data.title);
				$("#answerCount").text(data.answerCount);
				if(data.typeName){
					$("#thematicAskType").text(data.typeName);
				}else{
					$("#fenlei").html('');
					$("#fenleiEnd").html('');
				}
				$("#askDetailContent").html(data.content);
				if(data.label){
					$("#thematicAskTab").text(data.label);
				}
				if(data.addPics){
					var pics = data.addPics.split(",");
					for(var i=0;i<pics.length;i++){
						$("#addPicsDiv").append('<img class="addPicStyle" src="'+pics[i]+'"/>');
					}
				}
				$("#askTime").text(data.createTime);
				$("#askerName").text(data.name);
			}
		}
	});
}

/**
 * 获取回答列表
 */
function getAnswerList(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"questionId":thematicAsk.id},
		url:'<%=request.getContextPath()%>/myAsk/getAnswerList.action',
		success:function(data){
			if(data != ''){
				var answer1 = '';
				var answer2 = '';
			for(var i=0;i<data.length;i++){
				if(data[i].isSatisfactory == 1){
					if(data[i].resourceNames != null && data[i].resourceNames != '' 
							&& data[i].fileNames != null && data[i].fileNames != ''){
						var resourceNamesArr = data[i].resourceNames.split(",");
						var fileNamesArr = data[i].fileNames.split(",");
						answer1 += '<div class="addCoursesOrFilesStyle">';
						answer1 += '<span class="replyContentStyle">回答推荐资源：</span><br/>';
						if(data[i].platformResources != null && data[i].platformResources != ''){
							var platformResourcesArr = data[i].platformResources.split(",");
							for(var j = 0; j < platformResourcesArr.length; j++){
								answer1 += '<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+platformResourcesArr[j]+'">'+resourceNamesArr[j]+'</a>》-课程</span>';
								answer1 += '<br/>';
							}
						}
						if(data[i].localFiles != null && data[i].localFiles != ''){
							var localFilesArr = data[i].localFiles.split(",");
							for(var j = 0; j < localFilesArr.length; j++){
								answer1 += '<span>《<a href="'+localFilesArr[j]+'">'+fileNamesArr[j]+'</a>》-文件</span>';
								answer1 += '<br/>';
							}
						}
						answer1 += '</div>';
					}
					answer1 += '<div id="answerContent"><span class="replyContentStyle">回答具体内容：</span><br/>'+data[i].content+'</div>';
					answer1 += '<div class="otherAnswerTime">'+data[i].replyerName;
					answer1 += '&nbsp;'+data[i].createTime+'</div>';
					answer1 += '<br/>';
					
					<%-- <div class="addCoursesOrFilesStyle">
						<span class="replyContentStyle">回答推荐资源：</span><br/>
						<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=72">测试开放人群001</a>》-课程</span>
						<br/>
						<span>《<a href="/upload/ask/20160108/7a467a8c-8e2d-4a57-b8dc-c2799a259340.doc">学籍卡.doc</a>》-文件</span>
						<br/>
					</div>
					<div id="answerContent">
						<span class="replyContentStyle">回答具体内容：</span><br/>
						太阳的"长斑周期"长期的观察累积的数据让科学家意识到，太阳黑子的出现是有一定规律的。
					</div>
					<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
					<br/> --%>
				}else{
					if(data[i].resourceNames != null && data[i].resourceNames != '' 
						&& data[i].fileNames != null && data[i].fileNames != ''){
						var resourceNamesArr = data[i].resourceNames.split(",");
						var fileNamesArr = data[i].fileNames.split(",");
						answer2 += '<div class="addCoursesOrFilesStyle">';
						answer2 += '<span class="replyContentStyle">回答推荐资源：</span><br/>';
						if(data[i].platformResources != null && data[i].platformResources != ''){
							var platformResourcesArr = data[i].platformResources.split(",");
							for(var j = 0; j < platformResourcesArr.length; j++){
								answer2 += '<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+platformResourcesArr[j]+'">'+resourceNamesArr[j]+'</a>》-课程</span>';
								answer2 += '<br/>';
							}
						}
						if(data[i].localFiles != null && data[i].localFiles != ''){
							var localFilesArr = data[i].localFiles.split(",");
							for(var j = 0; j < localFilesArr.length; j++){
								answer2 += '<span>《<a href="'+localFilesArr[j]+'">'+fileNamesArr[j]+'</a>》-文件</span>';
								answer2 += '<br/>';
							}
						}
						answer2 += '</div>';
					}
					answer2 += '<div class="otherAnswerContent"><span class="replyContentStyle">回答具体内容：</span><br/>'+data[i].content+'</div>';
					answer2 += '<div class="otherAnswerTime">'+data[i].replyerName;
					answer2 += '&nbsp;'+data[i].createTime+'</div>';
					answer2 += '<br/>';
					
					<%-- <div class="addCoursesOrFilesStyle">
						<span class="replyContentStyle">回答推荐资源：</span><br/>
						<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=72">测试开放人群001</a>》-课程</span>
						<br/>
						<span>《<a href="/upload/ask/20160108/7a467a8c-8e2d-4a57-b8dc-c2799a259340.doc">学籍卡.doc</a>》-文件</span>
						<br/>
					</div>
					<div class="otherAnswerContent">
						<span class="replyContentStyle">回答具体内容：</span><br/>
						火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
						物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
					</div>
					<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
					<br/> --%>
				}
			}
			$("#answerDiv").html(answer1);	
			$("#othersDiv").html(answer2);	
			}
		}
	});
}

/**
 * 返回上一步
 */
function backLastPage(){
	window.history.go(-1);
}
</script>
</head>
<body>
	<div id="thematicAskDetailContent">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">问题详情</span>
		</div>
		<!-- <h3>
			<a href="javascript:void(0)" onclick="backLastPage();">&lt;</a>&nbsp;&nbsp;&nbsp;问题详情
		</h3> -->
		<!-- 提问 -->
		<div id="askDiv">
			<div id="askTitle">提问</div>
			<div id="askContent">
				<h3 id="askContentH3"></h3>
				<br /> 
				<span id="askAboult"> 
					<em id="answerCount" class="thisEmStyle">4</em>个回答&nbsp;|&nbsp; 
					<em id="fenlei" class="thisEmStyle">分类：</em><em id="thematicAskType" class="thisEmStyle"></em><em id="fenleiEnd" class="thisEmStyle">&nbsp;|&nbsp;</em> 
					标签：<em id="thematicAskTab" class="thisEmStyle">学</em>
				</span> 
				<br />
				<div id="askDetailContent"></div>
				<br />
				<div id="addPicsDiv">
					<!-- <img src="" class="addPicStyle">
					<img src="" class="addPicStyle">
					<img src="" class="addPicStyle">
					<img src="" class="addPicStyle">
					<img src="" class="addPicStyle"> -->
				</div>
				<br /> 
				<span id="askerAndTime"> 
					<em id="askerName" class="thisEmStyle"></em>&nbsp; 
					<em id="askTime" class="thisEmStyle"></em>
				</span>
			</div>
		</div>
		<!-- 满意答案 -->
		<div id="satisfactoryAnswerDiv">
			<h3><img src="<%=request.getContextPath() %>/images/cup.png"/> 满意答案</h3>
			<br />
			<div id="answerDiv">
				<%-- <div class="addCoursesOrFilesStyle">
					<span class="replyContentStyle">回答推荐资源：</span><br/>
					<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=72">测试开放人群001</a>》-课程</span>
					<br/>
					<span>《<a href="/upload/ask/20160108/7a467a8c-8e2d-4a57-b8dc-c2799a259340.doc">学籍卡.doc</a>》-文件</span>
					<br/>
				</div>
				<div id="answerContent">
					<span class="replyContentStyle">回答具体内容：</span><br/>
					太阳的"长斑周期"长期的观察累积的数据让科学家意识到，太阳黑子的出现是有一定规律的。
				</div>
				<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
				<br/> --%>
			</div>
		</div>
		<!-- 其他回答 -->
		<div id="otherAnswerDiv">
			<h3>其他回答</h3>
			<br />
			<!-- 其他回答具体内容 -->
			<div id="othersDiv">
				<%-- <div class="addCoursesOrFilesStyle">
					<span class="replyContentStyle">回答推荐资源：</span><br/>
					<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=72">测试开放人群001</a>》-课程</span>
					<br/>
					<span>《<a href="/upload/ask/20160108/7a467a8c-8e2d-4a57-b8dc-c2799a259340.doc">学籍卡.doc</a>》-文件</span>
					<br/>
				</div>
				<div class="otherAnswerContent">
					<span class="replyContentStyle">回答具体内容：</span><br/>
					火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
					物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
				</div>
				<div class="otherAnswerTime">邓伟伟&nbsp;2014-11-04 08:54:02</div>
				<br/> --%>
			</div>
		</div>
	</div>
</body>
</html>