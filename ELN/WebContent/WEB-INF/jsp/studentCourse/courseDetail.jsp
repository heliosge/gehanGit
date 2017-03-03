<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程详情</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
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

.bigSize {
	font-size: 16px
}

#evaluateText {
	width: 925px;
	height: 200px;
}

#submitEvaluate {
	width: 87px;
	height: 27px;
	border-style: none;
	background: #2dc0ef;
	color: #fff;
	float: right;
}

#courseEvaluatePager {
	margin-right: 100px;
}

.sectionOver {
	width: 56px;
	height: 20px;
	color: #fff;
	background: #d0d0d0;
	text-align: center;
	line-height: 20px;
	display: block;
	float: right;
	border-radius: 2px;
}

.sectionTest {
	width: 56px;
	height: 20px;
	color: #fff;
	background: #e30103;
	text-align: center;
	line-height: 20px;
	display: block;
	float: right;
	border-radius: 2px;
}

/**每个评价详情*/
.evaluateDetailStyle {
	padding-top: 10px;
	padding-bottom: 10px;
}

/**评价详情左边*/
.evaluateLeftStyle {
	width: 5%;
	float: left;
}

/**头像*/
.personImgStyle {
	width: 45px;
	height: 45px;
}

/**问答详情右边*/
.evaluateRightStyle {
	width: 94%;
	float: left;
	padding-bottom: 10px;
}

.blueSpanStyle {
	padding-left: 5px;
	float: left;
	color: #2894FF;
}

/**星星span*/
.starSpanStyle {
	padding-left: 5px;
	float: left;
}

.greySpanStyle {
	padding-left: 5px;
	float: right;
	color: grey;
}

/**评价内容*/
.evaluateContentStyle {
	padding-top: 5px;
	padding-bottom: 5px;
	padding-left: 5px;
	width: 100%;
	word-wrap: break-word;
	min-height: 17px;
	height: auto;
}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var courseBean = ${courseBean};
var itemsPerPage = 20;//每页显示条数
var backFlag = '${backFlag}';//返回页面标志

/**
 * 页面加载完成
 */
$(function(){
	//tab切换
	tabChange();
	//初始化课程收藏（判断该课程是否被收藏）
	initCourseCollection();
	//初始化课程详情
	initCourseDetails();
	//初始化所有章节
	initCourseChapters();
	//初始化课程评价
	initCourseEvaluates();
	//初始化该学员对该课程的评分
	initMyEvaluate();
});

/**
 * tab切换
 */
function tabChange(){
	$('#log').find('li').click(function(){
		$('#log').find('li').attr('class','');
		$('#log>div').css('display','none');
		$(this).attr('class','change');
		$('#log>div').eq($(this).index()).css('display','block');
	});
}

/**
 * 初始化课程收藏（判断该课程是否被收藏）
 */
function initCourseCollection(){
	var param = new Object();
	param.courseId = courseBean.id;
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/getCourseCollection.action',
		success:function(data){
			if(data != null && data != ''){
				if(data == true){
					//$("#isCollectedImg").attr("src","<%=request.getContextPath()%>/images/img/ico_10.png");
					$("#collectA").css("display","block");
					$("#unCollectA").css("display","none")
				}else{
					//$("#isCollectedImg").attr("src","<%=request.getContextPath()%>/images/img/bg_11.png");
					$("#unCollectA").css("display","block");
					$("#collectA").css("display","none")
				}
			}else{
				//$("#isCollectedImg").attr("src","<%=request.getContextPath()%>/images/img/bg_11.png");
				$("#unCollectA").css("display","block");
				$("#collectA").css("display","none")
			}
		}
	});
}

/**
 * 收藏该课程
 */
function collectThisCourse(){
	var param = new Object();
	param.courseId = courseBean.id;
	param.userId = userId;
	param.collectTime = new Date();
	param.updateTime = new Date();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	//保存
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/collectThisCourse.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				alert("收藏成功！");
				//刷新收藏标志
				initCourseCollection();
			}else{
				alert("收藏失败...");
			}
		}
	});
}
	
/**
 * 初始化课程详情
 */
function initCourseDetails(){
	$("#courseName").html(courseBean.name);
	//$("#courseCode").html('编号：'+courseBean.code);
	$("#courseFenlei").html('分类：'+courseBean.categoryName);
	$("#courseTags").html('标签：'+courseBean.tags);
	$("#courseDetails").html(courseBean.outline);
	
	//根据讲师id获取讲述姓名
	$("#courseTeacher").html('讲师：');
	var param = new Object();
	param.teacherId = courseBean.teacherId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/getTeacherNameById.action',
		success:function(data){
			if(data != null && data != ''){
				$("#courseTeacher").append(data);
			}
		}
	});
	
	//根据课程id获取学习人数
	$("#studentNum").html('学习人数：');
	var param = new Object();
	param.courseId = courseBean.id;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/getStudentNumById.action',
		success:function(data){
			if(data != null && data != ''){
				$("#studentNum").append(data+"人");
			}else{
				$("#studentNum").append("0人");
			}
		}
	});
	
	//获取课程时长
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"courseId":courseBean.id},
		url:'<%=request.getContextPath()%>/courseDetailAction/getCourseTotalDuration.action',
		success:function(data){
			if(data){
				var htmlStr = '课程时长：';
				htmlStr += '<span class="bigSize">'+data[0]+'</span>小时';
				htmlStr += '<span class="bigSize">'+data[1]+'</span>分';
				htmlStr += '<span class="bigSize">'+data[2]+'</span>秒';
				$("#couseTime").html(htmlStr);
			}
		}
	});
	
	//获取已学时长
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:{"courseId":courseBean.id,"userId":userId},
		url:'<%=request.getContextPath()%>/courseDetailAction/getCourseStudyedDurationNew.action',
		success:function(data){
			if(data){
				var htmlStr = '已学时长：';
				htmlStr += '<span class="bigSize">'+data[0]+'</span>小时';
				htmlStr += '<span class="bigSize">'+data[1]+'</span>分';
				htmlStr += '<span class="bigSize">'+data[2]+'</span>秒';
				$("#studyTime").html(htmlStr);
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
					htmlStr += '<span style="margin-left: 0px;cursor: pointer;" onclick="slideToggleOfSections('+section.id+');">第'+(i+1)+'章&nbsp;&nbsp;&nbsp;&nbsp;'+section.name+'</span>';
					htmlStr += '<a style="padding-left:880px;" onclick="slideToggleOfSections('+section.id+');">';
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
									htmlStr += '<a onclick="toCourseStudy('+ware.id+','+ware.type+','+section.id+',\''+ware.currentContent+'\')">'+ware.name+'</a>';
									htmlStr += '</span>';
									if(ware.type == 3){
										//htmlStr += '<b style="left:450px">'+Math.floor(ware.totalContent/60)+":"+ware.totalContent%60+'</b>';
									}
									htmlStr += '<b>'+ware.progressPercent+'%</b>';
									if(ware.progressPercent == 0){
										htmlStr += '<a class="continue" onclick="toCourseStudy('+ware.id+','+ware.type+','+section.id+',\''+ware.currentContent+'\')">开始学习</a>';
									}else{
										htmlStr += '<a class="continue" onclick="toCourseStudy('+ware.id+','+ware.type+','+section.id+',\''+ware.currentContent+'\')">继续学习</a>';
									}
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
					testParam.userId = userId;
					$.ajax({
						type:'POST',
						async:false,//此处为同步
						data:testParam,
						url:'<%=request.getContextPath()%>/courseDetailAction/getSectionTests.action',
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
									htmlStr += '<td>测试次数：'+examRecord.permitTestTimes+'次</td>';
									htmlStr += '<td>及格分：'+examRecord.passScore+'</td>';
									htmlStr += '<td>最高分：'+examRecord.maxScore+'</td>';
									if(examRecord.testTimes != null && examRecord.testTimes >= examRecord.permitTestTimes){
										htmlStr += '<td style="text-align:right;"><a class="sectionOver">进入测试</a></td>';
									}else{
										htmlStr += '<td style="text-align:right;"><a class="sectionTest" onclick="toSectionTest('+examRecord.sectionId+','+examRecord.id+','+examRecord.examDuration+','+examRecord.passPercent+','+examRecord.permitTestTimes+','+examRecord.permitTestTimes+');">进入测试</a></td>';
									}
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
			prev_text:'<',
			next_text:'>',
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
								htmlStr += '<div class="evaluateDetailStyle">';
								htmlStr += '<div class="evaluateLeftStyle">';
								htmlStr += '<img class="personImgStyle" src="'+evaluate.userPic+'">';
								htmlStr += '</div>';
								htmlStr += '<div class="evaluateRightStyle">';
								htmlStr += '<span class="blueSpanStyle">'+evaluate.userName+'</span>';
								htmlStr += '<span class="starSpanStyle">';
								if(evaluate.score >= 1){
									for(var k = 1; k <= evaluate.score; k++){
										htmlStr += '<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />';
									}
								}
								htmlStr += '</span>';
								htmlStr += '<span class="greySpanStyle">'+getSmpFormatDateByLong(evaluate.commentTime)+'</span>';
								htmlStr += '<br/>';
								htmlStr += '<div class="evaluateContentStyle">';
								if(evaluate.content){
									htmlStr += evaluate.content;
								}
								htmlStr += '</div>';
								htmlStr += '</div>';
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

/**部分ie浏览器不支持trim的处理*/
if(typeof String.prototype.trim=='undefined'){
    String.prototype.trim = function () {
        return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
    }   
}

/**
 * 评价课程
 */
function evaluateCourse(){
	//验证输入
	var content = $("#evaluateText").val();
	if(content == null || content.trim() == ''){
		alert("评价不能为空");
		return;
	}
	//设置参数
	var param = new Object();
	param.courseId = courseBean.id;
	param.userId = userId;
	param.content = content;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	//评论课程
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseEvaluateAction/giveMyEvaluation.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				alert("评价成功！");
				//刷新评价列表
				initCourseEvaluates();
				//清空评论栏
				$("#evaluateText").val("");
			}else{
				alert("给出我的评价异常...");
			}
		}
	});
}

/**
 * 小数验证
 */
function check(c)
{
    var r= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
    return r.test(c);
}

/**页面打开就是全屏*/
var fulls = "left=0,screenX=0,top=0,screenY=0,scrollbars=1";    //定义弹出窗口的参数  
if (window.screen) {  
     var ah = screen.availHeight - 30;  
     var aw = screen.availWidth - 10;  
     fulls += ",height=" + ah;  
     fulls += ",innerHeight=" + ah;  
     fulls += ",width=" + aw;  
     fulls += ",innerWidth=" + aw;  
     fulls += ",resizable"  
 } else {  
     fulls += ",resizable"; // 对于不支持screen属性的浏览器，可以手工进行最大化。 manually  
 }

function openNewWindow(url,name){  
  window.open(url,name,fulls);  
} 

/**
 * 跳转到课程学习页面
 */
function toCourseStudy(courseWareId,courseWareType,sectionId, currentContent){
	//记录课件及课程学习记录
	var param = new Object();
	//课件参数
	param.courseWareId = courseWareId;
	param.courseWareType = courseWareType;
	param.userId = userId;
	param.sectionId = sectionId;
	//课程参数
	param.courseId = courseBean.id;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	//记录课程和课件学习情况
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/recordCourseAndWare.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				//如果学习记录保存成功，跳转到课程学习页面
				//openNewWindow("<%=request.getContextPath() %>/courseStudyAction/toCourseStudy.action?courseWareId="+courseWareId+"&courseWareType="+courseWareType+"&courseId="+courseBean.id+"&sectionId="+sectionId,"_blank");
				window.location.href = '<%=request.getContextPath() %>/courseStudyAction/toCourseStudyNew.action?courseWareId='+courseWareId+'&courseWareType='+courseWareType+'&courseId='+courseBean.id+'&sectionId='+sectionId+'&currentContent='+currentContent;
			}else{
				alert("保存课件学习记录出错...");
			}
		}
	});
}

/**
 * 跳转到章节测试页面
 */
function toSectionTest(sectionId,examId,examDuration,passPercent,permitTestTimes){
	//记录测试及课程学习记录
	var param = new Object();
	//章节测试参数
	param.sectionId = sectionId;
	param.examId = examId;
	param.userId = userId;
	param.passTimes = permitTestTimes;
	param.passScorePercent = passPercent;
	//课程参数
	param.courseId = courseBean.id;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	//记录测试及课程学习情况
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/recordExamAndCourseBeforeTest.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				//跳转到测试页面
				var examRecordId = data.rtnData;//课程测试记录的id
				saveExamAndAssignInfo(examRecordId,examId,examDuration,permitTestTimes,passPercent,userId,companyId,subCompanyId);
				initCourseChapters();//刷新课程测试
			}else{
				alert("保存课程测试记录出错...");
			}
		}
	});
}

/**
 * 保存考试和考试分配信息
 */
function saveExamAndAssignInfo(examRecordId,examId,examDuration,permitTestTimes,passPercent,userId,companyId,subCompanyId){
	//保存考试和考试分配信息
	var innerParam = new Object();
	innerParam.examId = examId;
	innerParam.duration = examDuration;//考试时长
	innerParam.allowTimes = permitTestTimes;//允许考试次数
	innerParam.passScorePercent = passPercent;//及格分（百分比）
	innerParam.userId = userId;
	innerParam.companyId = companyId;
	innerParam.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:false,//此处为异步
		data:innerParam,
		url:'<%=request.getContextPath()%>/courseDetailAction/saveExamAndAssignInfo.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				var result = data.rtnData;
				window.open("<%=request.getContextPath() %>/myExamManage/gotoMatchTest.action?id="+result.examScheduleId+"&examRecordId="+examRecordId,"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
			}
		}
	});
}

/**
 * 初始化我的评价
 */
function initMyEvaluate(){
	var param = new Object();
	param.userId = userId;
	param.courseId = courseBean.id;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseEvaluateAction/getMyEvaluate.action',
		success:function(data){
			if(data){
				var starCount = data.score;
				if(starCount){
					if(starCount == 1){
						$("#giveOneStar").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveTwoStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
						$("#giveThreeStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
						$("#giveFourStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
						$("#giveFiveStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
					}else if(starCount == 2){
						$("#giveOneStar").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveTwoStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveThreeStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
						$("#giveFourStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
						$("#giveFiveStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
					}else if(starCount == 3){
						$("#giveOneStar").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveTwoStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveThreeStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveFourStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
						$("#giveFiveStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
					}else if(starCount == 4){
						$("#giveOneStar").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveTwoStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveThreeStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveFourStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveFiveStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />');
					}else if(starCount == 5){
						$("#giveOneStar").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveTwoStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveThreeStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveFourStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
						$("#giveFiveStars").html('<img src="<%=request.getContextPath()%>/images/img/pingjia_star_red@3x.png" />');
					}
				}
			}
		}
	});
}

/**
 * 给出我的评分
 */
function giveMyScore(score){
	var param = new Object();
	param.userId = userId;
	param.courseId = courseBean.id;
	param.score = score;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseEvaluateAction/giveMyScore.action',
		success : function(data) {
			if (data.rtnResult == 'SUCCESS') {
				alert("评分成功！");
				//刷新评分列表
				initCourseEvaluates();
				//刷新我的评分
				initMyEvaluate();
			} else {
				alert("给出课程评分异常...");
			}
		}
	});
}

/**
 * 返回上一级页面
 */
function backLastPage(){
	//根据返回标志返回相应页面，1：全部课程页面、2：我的课程页面
	if(backFlag == 1){
		window.location.href = '<%=request.getContextPath()%>/courseUserAction/toAllCourses.action';
	}else if(backFlag == 2){
		window.location.href = '<%=request.getContextPath()%>/courseUserAction/toMyCourses.action';
	}else{
		window.history.go(-1);
	}
}
</script>
</head>
<body>
	<div id="detail">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;padding-top: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="backLastPage();"> 
			<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程详情</span>
		</div>
		<!-- <div class="head_de">
			<h3 style="background: none">课程详情</h3>
		</div> -->
		<div class="de_con" style="padding-top: 8px;border-top: none;">
			<div style="min-height: 280px;height:auto;">
				<div style="width: 35%; float: left;">
					<img src="${courseBean.frontImage}" title="${courseBean.name}" width="323px" height="225px" style="padding-left: 1px;" />
				</div>

				<div style="width: 60%; float: left; min-height: 225px;height:auto;">
					<div class="top" style="width: 100%; min-height: 200px;height:auto;">
						<div style="width: 88%; float: left;min-height: 200px;height:auto;">
							<p id="courseName" title="${courseBean.name}" style="font-size: 22px; color: red; font-weight: bold;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"></p>
							<!-- <p id="courseCode" style="padding-top: 8px;"></p> -->
							<p id="courseFenlei" style="padding-top: 8px;"></p>
							<p id="courseTags" style="padding-top: 8px;"></p>
							<p id="courseTeacher" style="padding-top: 8px;"></p>
							<p id="couseTime" style="padding-top: 8px;">
								<span class="bigSize">02</span>小时<span class="bigSize">40</span>分<span class="bigSize">55</span>秒
							</p>
							<p id="studyTime" style="padding-top: 8px;">
								<span class="bigSize">01</span>小时<span class="bigSize">10</span>分<span class="bigSize">16秒</span>
							</p>
							<p id="studentNum" style="padding-top: 8px;">学习人数：0人</p>
						</div>
						<div style="width: 12%; float: right">
							<div id="unCollectA" style="display: none; width: 60px; height: 60px; background-color: rgb(244, 80, 59); text-align: center;  cursor: pointer;" onclick="collectThisCourse();">
								<img id="unCollectedImg" src="<%=request.getContextPath()%>/images/img/ico_24.png" style="width: 40px; height: 40px" />
								<div style="color: white">点我收藏</div> 
							</div> 
							<div id="collectA" style="display: none; width: 60px; height: 60px; background-color: white; text-align: center;">
								<img id="CollectedImg" src="<%=request.getContextPath()%>/images/img/ico_23.png" style="width: 40px; height: 40px" />
								<div style="color: red">已收藏</div> 
							</div>
						</div>
					</div>

					<!-- <div class="bottom" style="width: 100%; height: 28%">
						<div style="width: 30%; float: left;">
							<p style="padding-top: 8px;">课程时长</p>
							<p id="couseTime" style="padding-top: 8px;">
								<span class="bigSize">02</span>小时<span class="bigSize">40</span>分<span class="bigSize">55</span>秒
							</p>
						</div> 
						<div style="width: 30%; float: left;">
							<p style="padding-top: 8px;">已学时长</p>
							<p id="studyTime" style="padding-top: 8px;">
								<span class="bigSize">01</span>小时<span class="bigSize">10</span>分<span class="bigSize">16秒</span>
							</p>
						</div> 
						<div style="width: 35%; float: left;">
							<p style="padding-top: 8px; height: 17px"></p>
							<p id="studentNum" style="padding-top: 8px;">学习人数：0人</p>
						</div>
					</div> -->
				</div>

			</div>

			<div class="log">
				<div class="log_top" id="log">
					<ul>
						<li><a href="javascript:void(0)">课程详情</a></li>
						<li class="change"><a href="javascript:void(0)">课程目录</a></li>
						<li><a href="javascript:void(0)">课程评价</a></li>
					</ul>
					<h5></h5>

					<!-- 课程详情 -->
					<div id="courseDetails" class="course_detail fl" style="height:auto;min-height:300px;word-wrap:break-word;"></div>

					<!-- 课程目录 -->
					<div id="courseChapters" class="course_log fl" style="height:auto;min-height:300px;"></div>

					<!-- 课程评价 -->
					<div id="courseEvaluate" class="course_ev fl" style="height:auto;min-height:300px;">
						<!-- 具体评价 -->
						<div id="courseEvaluateDetail"></div>
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
							<div id="courseEvaluatePager" class="right_page fr"></div>
						</div>

						<!-- 给出我的评价 -->
						<div class="clear_both"></div>
						<div id="myEvaluate">
							<div id="evaluateScore">
								<h4>给出你的评分：</h4>
								<!-- <input type="text" id="scoreText" style="width:50px;"/> -->
								<span> 
									<a id="giveOneStar" class="starStyle" onclick="giveMyScore(1);">
										<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />
									</a>
									<a id="giveTwoStars" class="starStyle"onclick="giveMyScore(2);">
										<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />
									</a>
									<a id="giveThreeStars" class="starStyle" onclick="giveMyScore(3);">
										<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />
									</a>
									<a id="giveFourStars" class="starStyle" onclick="giveMyScore(4);">
										<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />
									</a>
									<a id="giveFiveStars" class="starStyle" onclick="giveMyScore(5);">
										<img src="<%=request.getContextPath()%>/images/img/pingjia_star_gray@3x.png" />
									</a>
								</span>
							</div>
							<br />
							<div id="evaluateContent">
								<h4>给出你的评价：</h4>
								<br />
								<textarea id="evaluateText"></textarea>
								<br />
								<input id="submitEvaluate" type="button" onclick="evaluateCourse();" value="提交评价" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>