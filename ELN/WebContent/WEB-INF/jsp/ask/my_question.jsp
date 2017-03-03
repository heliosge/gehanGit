<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的问问</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**整体框架*/
#myAskWholeFrame {
	width: 1066px;
	margin: 16px auto 0px;
	clear: both;
	overflow: hidden;
}

/**标题*/
#myAskWholeFrame>h3 {
	width: 1052px;
	padding-left: 20px;
	color: #3A3A3A;
	border-bottom: 1px solid #CCC;
	padding-bottom: 10px;
	margin-bottom: 10px;
	font-size: 14px;
}

/**左边div*/
.leftMyAsk {
	width: 240px;
	float: left;
	padding-top: 20px;
	font-size: 12px;
}

/**用户头像*/
#userInfoDiv {
	width: 240px;
	height: 50px;
}

#userInfoDiv>img {
	width: 50px;
	height: 50px;
	float: left;
}

#userNameDiv {
	font-size: 14px;
	float: left;
	padding-left: 10px;
	padding-top: 10px;
}

/**右边div*/
.rightMyAsk {
	width: 825px;
	float: left;
	padding-top: 19px;
	font-size: 12px;
}

/**搜索框div*/
#searchContentDiv {
	width: 803px;
	height: 45px;
	background: #EAEAEA none repeat scroll 0% 0%;
	padding-top: 8px;
	padding-left: 20px;
	border: 1px solid #cccccc;
}

.upSearchDiv {
	width: 783px;
	height: 40px;
}

.upSearchDiv select {
	width: 200px;
	height: 35px;
	font-size: 15px;
	font-family: 微软雅黑;
}

#askButton {
	width: 90px;
	height: 32px;
	background-color: #0091DE;
	color: rgb(255, 255, 255);
	border: medium none;
	cursor: pointer;
}

/**问问展现内容div*/
#frontAskDiv {
	width: 825px;
	overflow: hidden;
}

#askTops {
	width: 805px;
	height: 165px;
}

/**链接属性*/
a {
	text-decoration: none;
	cursor: pointer;
}

#menuDiv ul {
	font-family: 微软雅黑;
	position: relative;
	right: 40px;
}

#menuDiv ul li {
	list-style-type: none;
	height: 36px;
	cursor: pointer;
}

#menuDiv ul li span {
	position: relative;
	top: 12px;
	left: 50px;
}

.colorLi {
	background: url(../images/menu_01.png) no-repeat center;
}

.greyLi {
	background: url(../images/menu_02.png) no-repeat center;
}

.span_01 {
	color: #fff;
}

.span_02 {
	color: #000;
}

.buttonClass {
	font-size: 14px;
	font-family: 微软雅黑;
	margin-right: 5px;
	margin-top: 2px;
	cursor: pointer;
	border-radius: 2px;
	border: none;
	background-color: #0091DE;
	padding: 5px 10px;
	color: #ffffff;
}

#frontAskDiv .div_01 {
	position: relative;
	margin-top: 5px;
	overflow: hidden;
	cursor: pointer;
}

#frontAskDiv .div_01 .left_01 {
	float: left;
	color: #fff;
	width: 60px;
	padding-left: 5px;
}

#frontAskDiv .div_01 .left_01 .content_01 {
	background-color: #0091DE;
	width: 60px;
	height: 60px;
	text-align: center;
}

#frontAskDiv .div_01 .right_01 {
	float: left;
	width: 705px;
	font-family: 微软雅黑;
	padding-left: 10px;
}

#frontAskDiv .div_01 .right_02 {
	float: left;
	font-family: 微软雅黑;
	padding-top: 25px;
}

.color_01 {
	color: #929090;
}

.color_02 {
	color: #0091DE;
}

.color_03 {
	color: #DD0500;
}

.pr_01 {
	font-size: 15px;
}

.pr_02 {
	color: #959292;
	font-size: 15px;
}

.pr_02 em {
	padding-left: 5px;
	color: #DD0500;
}

.btn_div {
	position: absolute;
	right: 36px;
	top: 26px;
	display: none;
}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var userName = '${USER_BEAN.name}';
var userPic = '${USER_BEAN.headPhoto}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var showType = '${showType}';//展现我的提问或者我的问答（1：我的提问 2：我的回答）

$(function(){
	//初始化用户信息
	initUserInfo();
	//Tab点击事件
	initTabClick();
	//根据类型展现相关内容
	if(showType){
		if(showType == 1){
			$("#headTitle").html('我的提问');
			getMyAsk(1);
		}else if(showType == 2){
			$("#headTitle").html('我的回答');
			$("#answerLi").click();
		}else{
			$("#headTitle").html('推荐给我的问题');
			$("#frontAskDiv").click();
		}
	}else{
		$("#headTitle").html('我的提问');
		getMyAsk(1);
	}
});

/**
 * Tab点击事件
 */
function initTabClick(){
	$('#menuUl').find('li').click(function(){
		$('#menuUl').find('li').attr('class','greyLi span_02');
		$(this).removeClass();
		$(this).attr('class','colorLi span_01');
		var id = $(this).attr("id");
		if(id == 'questionLi'){
			$("#headTitle").html('我的提问');
			$("#search01").css("display","block");
			$("#search02").css("display","none");
			$("#search01").find("input").css("display","inline-block");
			getMyAsk(1);
		}else if(id == "answerLi"){
			$("#headTitle").html('我的回答');
			$("#search01").css("display","none");
			$("#search02").css("display","block");
			getMyAsk(2);
		}else{
			$("#headTitle").html('推荐给我的问题');
			$("#search01").css("display","block");
			$("#search02").css("display","none");
			$("#search01").find("input").css("display","none");
			getRecommend();
		}
	});
}

/**
 * 获取我的提问列表  1-我的提问 2-我的回答 
 */
function getMyAsk(type){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"type":type},
		url:'<%=request.getContextPath()%>/myAsk/getMyAskList.action',
		success:function(data){
			if(data != null){
				var html = '';
				for(var i=0;i<data.length;i++){
					var count = data[i].answerCount;
					if(count > 0){
						html += '<div class="div_01" flag="1" onmouseover="moveIn(this);" onmouseout="moveOut(this);">';
					}else{
						html += '<div class="div_01" flag="2" onmouseover="moveIn(this);" onmouseout="moveOut(this);">';
					}
					html += '<div class="left_01">';
					html += '<div class="content_01">';
					html += '<p style="padding-top:5px;">'+data[i].answerCount+'</p>';
					html += '<p>回答</p>';
					html += '</div></div>';
					html += '<div class="right_01" onclick="view('+data[i].id+');">';
					html += '<div>';
					html += '<p class="pr_01">'+data[i].title+'</p>';
					html += '<p class="pr_02">'+data[i].createTime;
					if(type == 1){
						html += '<em>'+data[i].answerCount+'个新回答</em></p>'
					}else{
						html += '</p>';
					}
					html += '</div></div>';
					if(type == 1){
						if(data[i].satisfactoryAnswerCount > 0){
							html += '<div class="right_02 color_01">';
							html += '<p>已处理</p>';
							html += '</div>';
						}
					}else if(type == 2){
						if(data[i].satisfactoryAnswerCount > 0){
							html += '<div class="right_02 color_02">';
							html += '<p>已处理</p>';
							html += '</div>';
						}else{
							html += '<div class="right_02 color_03">';
							html += '<p>未处理</p>';
							html += '</div>';
						}
					}
					html += '<div class="btn_div">';
					if(type == 1){
						html += '<input type="button" class="buttonClass" value="删除" onclick="del('+data[i].id+');"/>';
					}
					html += '</div>';
					html += '</div>';
				}
				$("#frontAskDiv").html(html);
			}
		}
	});
}

/*推荐给我的问题  */
function getRecommend(){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"toWhom":userId},
		url:'<%=request.getContextPath()%>/myAsk/getRecommendedToMeQuestions.action',
		success:function(data){
			if(data != null){
				var html = '';
				for(var i=0;i<data.length;i++){
					var count = data[i].answerCount;
					if(count > 0){
						html += '<div class="div_01" flag="1" onmouseover="moveIn(this);" onmouseout="moveOut(this);">';
					}else{
						html += '<div class="div_01" flag="2" onmouseover="moveIn(this);" onmouseout="moveOut(this);">';
					}
					html += '<div class="left_01">';
					html += '<div class="content_01">';
					html += '<p style="padding-top:5px;">'+data[i].answerCount+'</p>';
					html += '<p>回答</p>';
					html += '</div></div>';
					html += '<div class="right_01" onclick="view('+data[i].id+');">';
					html += '<div>';
					html += '<p class="pr_01">'+data[i].title+'</p>';
					html += '<p class="pr_02">'+data[i].createTime;
					html += '<em>'+data[i].answerCount+'个新回答</em></p>'
					html += '</div></div>';
					if(data[i].satisfactoryAnswerCount > 0){
						html += '<div class="right_02 color_02">';
						html += '<p>已处理</p>';
						html += '</div>';
					}else{
						html += '<div class="right_02 color_03">';
						html += '<p>未处理</p>';
						html += '</div>';
					}
					html += '<div class="btn_div">';
					html += '</div>';
					html += '</div>';
				}
				$("#frontAskDiv").html(html);
			}
		}
	});
}

/*查看问题详情  */
function view(id){
	window.location = "<%=request.getContextPath()%>/myAsk/toAskDetail.action?askId="+id;
}

/*删除提问  */
function del(id){
	dialog.confirm("确认删除吗？",function(){
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:{"id":id},
			url:'<%=request.getContextPath()%>/myAsk/delAsk.action',
			success:function(data){
				if(data == 'SUCCESS'){
					dialog.alert("删除成功！",function(){
						getMyAsk();
					});
				}else{
					dialog.alert("删除失败！");
				}
			}
		});
	});
}

/*搜索  */
function query(obj){
	//1-已处理 2-未处理
	var type = $(obj).val();
	if(type == 0){
		$("div[flag]").each(function(index,element){
			$(this).css("display","block");
		});
	}else{
		$("div[flag]").each(function(index,element){
			if($(this).attr("flag") == type){
				$(this).css("display","block");
			}else{
				$(this).css("display","none");
			}
		});
	}
}

/*我要提问  */
function toAsk(){
	window.location = "<%=request.getContextPath()%>/myAsk/toAsk.action";
}

/*发现更多  */
function findMore() {
	alert("更多来了！");
}

/**
 * 初始化用户信息
 */
function initUserInfo() {
	$("#userName").html(userName);
	$("#userPic").attr("src", userPic);
}

/*鼠标移入  */
function moveIn(obj) {
	$(obj).css("background-color", "#D9EFED");
	$(obj).children().last().css("display", "block");
}

/*鼠标移出  */
function moveOut(obj) {
	$(obj).css("background-color", "#FFFFFF");
	$(obj).children().last().css("display", "none");
}
</script>
</head>
<body>
	<div id="myAskWholeFrame">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span id="headTitle" style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;"></span>
		</div>
		<!-- <h3>我要提问</h3> -->
		<!-- 左边div -->
		<div class="leftMyAsk">
			<!-- 用户头像 -->
			<div id="userInfoDiv">
				<img id="userPic" src="" />
				<div id="userNameDiv">
					<span id="userName"></span>
				</div>
			</div>
			<!-- 提问和我的回答 -->
			<div id="menuDiv">
				<ul id="menuUl">
					<li id="questionLi" class="colorLi span_01"><span>我的提问</span></li>
					<li id="answerLi" class="greyLi span_02"><span>我的回答</span></li>
					<li id="recommedLi" class="greyLi span_02"><span>推荐给我的问题</span></li>
				</ul>
			</div>
		</div>
		<!-- 右边div -->
		<div class="rightMyAsk">
			<!-- 搜索框 -->
			<div id="searchContentDiv">
				<div class="upSearchDiv" id="search01">
					<select onchange="query(this);">
						<option value="0">全部提问</option>
						<option value="1">已处理</option>
						<option value="2">未处理</option>
					</select> 
					<input type="button" value="我要提问" id="askButton" onclick="toAsk();" />
				</div>
				<div class="upSearchDiv" style="display: none;" id="search02">
					<select onchange="query(this);">
						<option value="0">全部回答</option>
						<option value="1">已处理</option>
						<option value="2">未处理</option>
					</select> 
					<!-- <input type="button" value="发现更多" id="askButton" onclick="findMore();" /> -->
				</div>
			</div>
			<div style="clear: both;"></div>
			<!-- 问问展现内容 -->
			<div id="frontAskDiv">
				<div class="div_01" onmouseover="moveIn(this);" onmouseout="moveOut(this);">
					<div class="left_01">
						<div class="content_01">
							<p style="padding-top: 5px;">0</p>
							<p>回答</p>
						</div>
					</div>
					<div class="right_01">
						<div>
							<p class="pr_01">喜欢旅游，要一起么？</p>
							<p class="pr_02">
								2014-11-24 12:20:30<em>0个新回答</em>
							</p>
						</div>
					</div>
					<div class="right_02">
						<p>已处理</p>
					</div>
					<div class="btn_div">
						<input type="button" class="buttonClass" value="删除" onclick="del();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>