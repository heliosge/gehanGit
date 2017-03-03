<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理问答</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**框架*/
#thematicAskDetailContent{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**框架标题*/
#thematicAskDetailContent > h3{
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;font-size:14px;}

/**链接属性*/
a{text-decoration: none;cursor:pointer;}

/**返回连接*/
#thematicAskDetailContent > h3 > a{font-size:24px;color:#888888;}

/**问题*/
#askDiv{width:990px;min-height:160px;height:auto;border:1px solid #0091DE;
margin-bottom:5px;margin-left:20px;}

/**问题标题部分*/
#askTitle{width: 970px;height: 30px;background-color: #0091DE;color: #FFF;
padding-left: 20px;padding-top: 8px;font-size:13px;font-weight:bold;}

/**问题内容部分*/
#askContent{width:970px;min-height:140px;height:auto;background-color:#FFF;color:#000000;
padding-left:20px;padding-top:8px;padding-bottom:15px;}

#askContent > h3{margin: 3px auto;font-size:16px;}

#askAboult{color: grey;font-size: 13px;}

#askDetailContent{padding-top: 20px;font-size: 13px;width:970px;word-wrap:break-word;}

#askerAndTime{color: grey;font-size: 13px;}

/**其他答案*/
#allAnswerDiv{width:970px;min-height:80px;height:auto;border:1px solid #0091DE;
margin-bottom:5px;margin-left:20px;padding-left:20px;padding-top:8px;padding-bottom:15px;}

#allAnswerDiv > h3{margin: 3px auto;font-size:16px;}

.otherAnswerContent{font-size: 13px;width:100%;word-wrap:break-word;}

.otherAnswerTime{color: grey;font-size: 13px;width:100%;}

/**em样式*/
.thisEmStyle{font-style: normal;}

.answerEmStyle{font-style: normal;color:#0091DE;}

.satisfactoryAnswerEmStyle{font-style: normal;color:#D60500;}

/**添加图片div*/
#addPicsDiv{width:950px;}

.addPicStyle{width: 155px;height: 80px;padding-right: 10px;}

/**每个回答样式*/
.contentAll{width:970px;min-height:80px;height:auto;padding-bottom:18px;}

.contentLeft{width:100%;height:auto;}

.contentRight{width:100%;height:auto;}

.buttonStyle{background: #0091DE none repeat scroll 0% center;color: #FFF;text-align: center;
width: 130px;height: 35px;border: medium none;border-radius: 2px;
font-weight: bold;margin-right: 10px;cursor: pointer;font-size: 12px;}

.replyContentStyle{font-size: 15px;color: rgb(0, 145, 222);}

.addCoursesOrFilesStyle{font-size: 13px;margin-bottom: 5px;}
</style>

<script type="text/javascript">
var thematicAsk = ${thematicAsk};

$(function(){
	//初始化提问
	initAsk();
	//初始化全部回答
	initAnswers();
});

/**
 * 初始化提问
 */
function initAsk(){
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
 * 初始化全部回答
 */
function initAnswers(){
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"questionId":thematicAsk.id},
		url:'<%=request.getContextPath()%>/myAsk/getAnswerListIncludeShield.action',
		success:function(data){
			if(data != null && data.length > 0){
				var htmlStr = '';
				for(var i = 0; i < data.length; i++){
					var answer = data[i];
					htmlStr += '<div class="contentAll">';
					htmlStr += '<div class="contentLeft">';
					
					if(data[i].resourceNames != null && data[i].resourceNames != '' 
							&& data[i].fileNames != null && data[i].fileNames != ''){
						var resourceNamesArr = data[i].resourceNames.split(",");
						var fileNamesArr = data[i].fileNames.split(",");
						htmlStr += '<div class="addCoursesOrFilesStyle">';
						htmlStr += '<span class="replyContentStyle">回答推荐资源：</span><br/>';
						if(data[i].platformResources != null && data[i].platformResources != ''){
							var platformResourcesArr = data[i].platformResources.split(",");
							for(var j = 0; j < platformResourcesArr.length; j++){
								htmlStr += '<span>《<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+platformResourcesArr[j]+'">'+resourceNamesArr[j]+'</a>》-课程</span>';
								htmlStr += '<br/>';
							}
						}
						if(data[i].localFiles != null && data[i].localFiles != ''){
							var localFilesArr = data[i].localFiles.split(",");
							for(var j = 0; j < localFilesArr.length; j++){
								htmlStr += '<span>《<a href="'+localFilesArr[j]+'">'+fileNamesArr[j]+'</a>》-文件</span>';
								htmlStr += '<br/>';
							}
						}
						htmlStr += '</div>';
					}
					
					htmlStr += '<div class="otherAnswerContent"><span class="replyContentStyle">回答具体内容：</span><br/>';
					htmlStr += answer.content;
					htmlStr += '</div>';
					htmlStr += '<br/>';
					htmlStr += '<div class="otherAnswerTime">';
					if(answer.isAnonymous == 1){
						htmlStr += '匿名&nbsp;';
					}else{
						htmlStr += answer.replyerName + '&nbsp;';
					}
					htmlStr += answer.createTime + '&nbsp;&nbsp;';
					if(answer.isSatisfactory == 1){
						htmlStr += '<em class="satisfactoryAnswerEmStyle">';
						htmlStr += '（满意答案）';
						htmlStr += '</em>';
					}else if(answer.isShield == 1){
						htmlStr += '<em class="answerEmStyle">';
						htmlStr += '（已屏蔽）';
						htmlStr += '</em>';
					}else if(answer.isShield == 2){
						htmlStr += '<em class="answerEmStyle">';
						htmlStr += '（未屏蔽）';
						htmlStr += '</em>';
					}
					htmlStr += '</div>';
					htmlStr += '<br/>';
					htmlStr += '</div>';
					htmlStr += '<div class="contentRight">';
					if(answer.isShield == 1){
						htmlStr += '<input type="button" value="解除屏蔽答案" class="buttonStyle" onclick="showAnswer('+answer.id+','+answer.isSatisfactory+');"/>';
					}else if(answer.isShield == 2){
						htmlStr += '<input type="button" value="屏蔽答案" class="buttonStyle" onclick="shielAnswer('+answer.id+','+answer.isSatisfactory+');"/>';
					}
					htmlStr += '<input type="button" value="采为满意答案" class="buttonStyle" onclick="satisfactoryAnswer('+answer.id+','+answer.isSatisfactory+');"/>';
					htmlStr += '</div>';
					htmlStr += '</div>';
					
					/* <div class="contentAll">
						<div class="contentLeft">
							<div class="otherAnswerContent">
								火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
								物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
								火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
								物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
							</div>
							<br/>
							<div class="otherAnswerTime">
								邓伟伟&nbsp;2014-11-04 08:54:02&nbsp;&nbsp;<em class="satisfactoryAnswerEmStyle">（满意答案）</em>
							</div>
							<br/>
						</div>
						<div class="contentRight">
							<input type="button" value="屏蔽答案" class="buttonStyle"/>
							<input type="button" value="采为满意答案" class="buttonStyle"/>
						</div>
					</div> */
					
				}
				$("#othersDiv").html(htmlStr);
			}else{
				$("#othersDiv").html("暂无回答！");
			}
		}
	});
}

/**
 * 屏蔽答案
 */
function shielAnswer(id,isSatisfactory){
	//验证
	if(isSatisfactory == 1){
		dialog.alert("满意答案不能屏蔽");
		return;
	}
	//屏蔽
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"id":id},
		url:'<%=request.getContextPath()%>/manageThematicAsk/shielAnswer.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("屏蔽成功！");
				//刷新列表
				initAnswers();
			}else{
				dialog.alert("屏蔽失败！");
			}
		}
	});
}

/**
 * 解除屏蔽
 */
function showAnswer(id,isSatisfactory){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"id":id},
		url:'<%=request.getContextPath()%>/manageThematicAsk/showAnswer.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("解除屏蔽成功！");
				//刷新列表
				initAnswers();
			}else{
				dialog.alert("解除屏蔽失败！");
			}
		}
	});
}

/**
 * 设置满意回答
 */
function satisfactoryAnswer(id,isSatisfactory){
	//验证
	if(isSatisfactory == 1){
		dialog.alert("该回答已是满意答案");
		return;
	}else{
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:{"questionId":thematicAsk.id},
			url:'<%=request.getContextPath()%>/manageThematicAsk/getSatisfactoryAnswer.action',
			success:function(data){
				if(data){
					dialog.alert("已存在满意答案，不能更换满意答案！");
					return;
				}else{
					//采为满意答案
					dialog.confirm("确定要采为满意答案吗？采为满意答案后将不能更换满意答案！",function(obj){
						setSatisfactoryAnswer(id);
					});
				}
			}
		});
	}
}

/**
 * 采为满意答案
 */
function setSatisfactoryAnswer(id){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"id":id},
		url:'<%=request.getContextPath()%>/manageThematicAsk/setSatisfactoryAnswer.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("采为满意答案成功！");
				//刷新列表
				initAnswers();
			}else{
				dialog.alert("采为满意答案失败！");
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
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">处理问答</span>
		</div>
		<!-- <h3><a href="javascript:void(0)" onclick="backLastPage();">&lt;</a>&nbsp;&nbsp;&nbsp;处理问答</h3> -->
		<!-- 提问 -->
		<div id="askDiv">
			<div id="askTitle">提问</div>
			<div id="askContent">
				<h3 id="askContentH3"></h3>
				<br/>
				<span id="askAboult">
					<em id="answerCount" class="thisEmStyle"></em>个回答&nbsp;|&nbsp;
					<em id="fenlei" class="thisEmStyle">分类：</em><em id="thematicAskType" class="thisEmStyle"></em><em id="fenleiEnd" class="thisEmStyle">&nbsp;|&nbsp;</em>
					标签：<em id="thematicAskTab" class="thisEmStyle"></em>
				</span>
				<br/>
				<div id="askDetailContent"></div>
				<br/>
				<div id="addPicsDiv"></div>
				<br/>
				<span id="askerAndTime">
					<em id="askerName" class="thisEmStyle"></em>&nbsp;
					<em id="askTime" class="thisEmStyle"></em>
				</span>
			</div>
		</div>
		<!-- 其他回答 -->
		<div id="allAnswerDiv">
			<h3>所有回答</h3>
			<br/>
			<!-- 其他回答具体内容 -->
			<div id="othersDiv">
				<!-- <div class="contentAll">
					<div class="contentLeft">
						<div class="otherAnswerContent">
							火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
							物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
							火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
							物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
						</div>
						<br/>
						<div class="otherAnswerTime">
							邓伟伟&nbsp;2014-11-04 08:54:02&nbsp;&nbsp;<em class="satisfactoryAnswerEmStyle">（满意答案）</em>
						</div>
						<br/>
					</div>
					<div class="contentRight">
						<input type="button" value="屏蔽答案" class="buttonStyle"/>
						<input type="button" value="采为满意答案" class="buttonStyle"/>
					</div>
				</div> -->
				<!-- <div class="contentAll">
					<div class="contentLeft">
						<div class="otherAnswerContent">
							火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
							物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
							火山喷发将大量的氮气排放到空气中，与此同时也喷薄出硫磺、水蒸气和二氧化碳。当地壳被挤压，大量的不稳定物质释放到位于上方的石头中。这些
							物质包括氮元素，他们或者被压制到石头中，或者变成气体在火山喷发的时候释放出来。
						</div>
						<br/>
						<div class="otherAnswerTime">
							邓伟伟&nbsp;2014-11-04 08:54:02&nbsp;&nbsp;<em class="answerEmStyle">（已屏蔽）</em>
						</div>
						<br/>
					</div>
					<div class="contentRight">
						<input type="button" value="屏蔽答案" class="buttonStyle"/>
						<input type="button" value="采为满意答案" class="buttonStyle"/>
					</div>
				</div> -->
			</div>
		</div>
	</div>
</body>
</html>