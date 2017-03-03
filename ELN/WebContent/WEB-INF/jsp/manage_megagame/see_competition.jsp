<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看大赛</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<style type="text/css">
.my_font {color: #e10000;font-weight: bold;font-size: 14px}
.backBtn{width:132px;height:32px;color:#fff;background:#0198e7;text-align:center}
.cp_1 .right_cp1 .jx .jx_1{background: none;}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';//当前大赛id
$(function() {
	activeJs();
	getMegagameInfo();
	getAwardsSetting();
	getAllMatchInfo();
	initGird();
	initDatepicker();
});

function initDatepicker() {
	$("#startTime").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd'
	});

	$("#endTime").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd'
	});
}
/**
 * 查看当前大赛赛程信息
 */
function getAllMatchInfo(){
	var urlStr = "<%=request.getContextPath()%>/megagameManage/getAllMatchInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initMatchInfo(data.rtnDataList);
			}
		}
	});
}
/**
 * 生成赛程信息
 */
 function initMatchInfo(list){
	var html = "";
	var matchInfoDiv = $("#matchInfoDiv");
	var divStr1 = "<div class=\"add_gr\" style=\"height:inherit;\">";
	var divStr2 = "<div class=\"add_fl\">";
	var divStr3 = "<div class=\"add_fr\">";
	var divEndStr = "</div>";
	matchInfoDiv.empty();
	var matchName ="";
	var content ="";
	var rule="";
	var promotionNumber ="";
	var checkPeople = "";
	if(list){
		var len = list.length;
		for(var i=0;i<len;i++){
			matchName =  list[i].name;//赛程名称
			content =  list[i].content;//赛程内容
			rule =  list[i].rule;//评审规则
			promotionNumber =  list[i].promotionNumber;//晋级人数
			checkPeople = list[i].scoreMarkerName;//批阅人信息
			
			var examVo = list[i].examVo;//考试、试卷信息
			var id = "";
			var paperId = "";
			var paperName ="";
			var displayMode ="";
			var duration ="";
			var allowTimes ="";
			var testTime ="";
			var isAnswerPublic ="";
			var randomOrderMode ="";
			var isAutoMarking ="";
			var isAntiCheating ="";
			var notice ="";
			if(examVo){
				id = examVo.id;//考试信息ID
				paperId = examVo.paperId;
				paperName =  examVo.paperName;//试卷名称
				displayMode =  examVo.displayMode;//显示模式1  整卷显示2  单题可逆3  单题不可逆
				if(displayMode==1){
					displayMode="整卷显示";
				}else if(displayMode==2){
					displayMode=" 单题可逆";
				}else if(displayMode==3){
					displayMode=" 单题不可逆";
				}
				duration =  examVo.duration;//考试时长
				allowTimes =  examVo.allowTimes;//考试次数
				testTime =  examVo.beginTime+"~"+examVo.endTime;//考试时间
				isAnswerPublic =  examVo.isAnswerPublic;//答案是否公开
				if(isAnswerPublic==1){
					isAnswerPublic="是";
				}else if(isAnswerPublic==0){
					isAnswerPublic="否";
				}
				randomOrderMode =  examVo.randomOrderMode;//题目顺序随机打乱模式1题目顺序2选项顺序3题目+选项顺序
				if(randomOrderMode==1 && randomOrderMode==3){
					randomOrderMode="随机打乱";
				}else if(isAnswerPublic==0){
					isAnswerPublic="顺序";
				}
				isAutoMarking =  examVo.isAutoMarking;//主观题是否自动批阅
				if(isAutoMarking==1){
					isAutoMarking="是";
				}else if(isAutoMarking==0){
					isAutoMarking="否";
				}
				isAntiCheating =  examVo.isAntiCheating;//是否开启防作弊
				if(isAntiCheating==1){
					isAntiCheating="是";
				}else if(isAntiCheating==0){
					isAntiCheating="否";
				}
				notice =  examVo.notice;//考前须知
			}
			html+=divStr1;
			html+=divStr2+"<em>赛程名称：</em>"+divEndStr;
			html+=divStr3+"<p>"+matchName+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>赛程内容：</em>"+divEndStr;
			html+=divStr3+"<p>"+content+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>评审规则：</em>"+divEndStr;
			html+=divStr3+"<p>"+rule+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>试卷名称：</em>"+divEndStr;
			html+=divStr3+"<p>"+paperName+"<input type=\"button\" value=\"预览\" onclick=\"toSeePaper("+id+")\"/></p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>显示模式：</em>"+divEndStr;
			html+=divStr3+"<p>"+displayMode+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>考试时长：</em>"+divEndStr;
			html+=divStr3+"<p>"+duration+"分钟</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>考试次数：</em>"+divEndStr;
			html+=divStr3+"<p>"+allowTimes+"次</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>考试时间：</em>"+divEndStr;
			html+=divStr3+"<p>"+testTime+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>答案是否公开：</em>"+divEndStr;
			html+=divStr3+"<p>"+isAnswerPublic+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>题目顺序：</em>"+divEndStr;
			html+=divStr3+"<p>"+randomOrderMode+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>主观题是否自动批阅：</em>"+divEndStr;
			html+=divStr3+"<p>"+isAutoMarking+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>是否开启防作弊：</em>"+divEndStr;
			html+=divStr3+"<p>"+isAntiCheating+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>批阅人信息：</em>"+divEndStr;
			html+=divStr3+"<p>";
			var peoples = checkPeople.split(",");
			for(var k=0;k<peoples.length;k++){
				var userName = peoples[k];
				html+="<input type=\"button\" value=\""+userName+"\" />";
			}
			html+="</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>考前须知：</em>"+divEndStr;
			html+=divStr3+"<p>"+notice+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+=divStr2+"<em>晋级人数：</em>"+divEndStr;
			html+=divStr3+"<p>"+promotionNumber+"</p>"+divEndStr;
			html+=divEndStr;
			
			html+=divStr1;
			html+="<div style=\"width:100%;height:0px;border-bottom: 1px solid;\"></div>";
			html+=divEndStr;
		}
	}
	matchInfoDiv.html(html);
}
 
/**
 * 试卷预览
 */
 function toSeePaper(examId){
	window.open("<%=request.getContextPath()%>/myExamManage/gotoViewPaper.action?id="+examId);
}
//初始化资讯grid数据
function initGird() {
	$('#messageTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/megagameManage/getMessageList.action',
						width : 'auto',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var param = new Object();
							var title = escapeForSql($("#title").val());
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							param.userId = userId;
							param.contestId = megagameId;
							param.createTimeBegin = startTime;
							param.createTimeEnd = endTime;
							param.title = title;
							return param;
						},
						cols : [
								{
									title : 'id',
									name : 'id',
									hidden : true
								},
								{
									title : '标题',
									name : 'title',
									width:300,
									align : 'center'
								},
								{
									title : '创建时间',
									name : 'createTime',
									align : 'center',
									width:300,
									renderer : function(val, item, rowIndex) {
										return getSmpFormatDateByLong(val, true);
									}
								},
								{
									title : '操作',
									name : 'operation',
									align : 'center',
									width:335,
									renderer : function(val, item, rowIndex) {
										var isPublished = item.isPublished;
										var str=""
										var publishStr="";
										var delStr = "<a href=\"javascript:void(0);\" onclick=\"deleteNews("+item.id+");\">删除</a>&nbsp;";
										var modifyStr = "<a href=\"javascript:void(0);\" onclick=\"editNews("+item.id+");\">修改</a>&nbsp;";
										var detailStr = "<a href=\"javascript:void(0);\" onclick=\"viewNews("+item.id+");\">详情</a>&nbsp;";
										if(!isPublished){
											publishStr = "<a href=\"javascript:void(0);\" onclick=\"publishNews("+item.id+");\">发布</a>&nbsp;";
										}
										str =delStr+modifyStr+detailStr+publishStr;
										return str;
									}
								} ],
						plugins : [ $('#paginatorPaging').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
														// count
							page : 1, // 初始页
							pageParamName : 'pageNo', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}

/*删除资讯  */
function deleteNews(id){
	dialog.confirm("确认删除吗？", function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{"id":id},
			url: "<%=request.getContextPath()%>/megagameManage/deleteNews.action",
			success:function(data){
				if(data == "SUCCESS"){
					 dialog.alert("操作成功！")
					search(true);
				}else{
					 dialog.alert("操作失败！")
				}
		    }
		});
	});
}

/*发布资讯  */
function editNews(id){
	window.location = "<%=request.getContextPath()%>/megagameManage/gotoEditNews.action?id="+id;
}
/*查看资讯详情  */
function viewNews(id){
	$.ajax({
		type:"POST",
		data:{"messageId":id},
		url: "<%=request.getContextPath()%>/MyMegagame/getMatchMessageById.action",
		success:function(data){
			if(data != null){
				$("#content").html(data.rtnData.content);
				$("#bach").hide();
				$("#fwb").show();
			}

	    }
	});
	//window.location = "<%=request.getContextPath()%>/MyMegagame/getMatchMessageById.action?messageId="+id;
}
/**
 * 资讯返回
 */
function toBack(){
	$("#bach").show();
	$("#fwb").hide();
}
/*发布资讯  */
function publishNews(id){
	dialog.confirm("确认发布吗？", function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{"id":id},
			url: "<%=request.getContextPath()%>/megagameManage/publishNews.action",
			success:function(data){
				if(data == "SUCCESS"){
					 dialog.alert("操作成功！")
					search(true);
				}else{
					 dialog.alert("操作失败！")
				}
		    }
		});
	});
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#messageTable').mmGrid().load();
	}else{
		$('#messageTable').mmGrid().load({"page":1});		
	}
}

/**
 * 获取大赛基础信息
 */
function getMegagameInfo() {
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameInfoById.action";
	$.ajax({
		type : "POST",
		async:false,
		url : urlStr,
		data : {
			"userId" : userId,
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initMegagameBaseInfo(data.rtnData);
			}
		}
	});
}
/**
 * 获取奖项设置数据
 */
function getAwardsSetting() {
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAwardsSetting.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initAwards(data.rtnDataList);
			}
		}
	});
}
/**
 * 生成奖项设置
 */
function initAwards(list) {
	var html = "<h4>奖项设置</h4>";
	var awardsDiv = $("#awardsDiv");
	awardsDiv.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var awardName = list[i].awardName;// 奖项名称
			var prizeName = list[i].prizeName;// 奖品名称
			var coverImage = list[i].coverImage;// 奖品图片
			// 测试
			//coverImage = "/images/img/iphone.png";
			html += "<div class=\"jx_1\">";
			html += "<div style=\"float: right;width: 54px;height: 110px;\">";
			html += "<img src=\"" + coverImage+ "\" style=\"width:56px;height:112px;\"/></div>";
			html += "<div><h6>" + awardName + "</h6></div>";
			html += "<div class=\"my_font\"><span>" + prizeName+ "</span></div>";
			html += "</div>";
		}
	}
	awardsDiv.html(html);
}
/**
 * 搜索
 */
function doSearch(){
	$('#messageTable').mmGrid().load({"page":1});
}
/**
 * 重置
 */
function doReset(){
	$("#startTime").val("");
	$("#endTime").val("");
	$("#title").val("");
	$('#messageTable').mmGrid().load({"page":1});
}
/**
 * 生成大赛基础信息
 */
function initMegagameBaseInfo(data){
	var baseInfoDiv = $("#baseInfoDiv");
	baseInfoDiv.empty();
	var html = "";
	if(data){
		html += "<h4>比赛详情</h4>";
		html += "<p>" + data.detail + "</p>";
		html += "<h4>参赛资格</h4>";
		html += "<p>" + data.qualification + "</p>";
		html += "<h4>参赛流程</h4>";
		html += "<p>" + data.processDescription + "</p>";
	}
	baseInfoDiv.html(html);
}
function activeJs() {
	$('#tab #tab_1 li').click(function() {
		$('#tab>div').css('display', 'none');
		$('#tab>div').eq($(this).index()).css('display', 'block');
		$('#tab #tab_1 li').attr('class', '')
		$(this).attr('class', 'active_c');

	})
	$('#sc li').click(function() {
		$('#cp_5>div').css('display', 'none');
		$('#left_ul').css('display', 'block')
		$('#cp_5>div').eq($(this).index() + 1).css('display', 'block');
		$('#sc li').attr('class', '')
		$('#sc li').find('img').remove();
		$(this).attr('class', 'active_b')
		$(this).append('<img src="img/ico_21.png"/>');

	})
	$('#sc_1 li').click(function() {
		$('#cp_6>div').css('display', 'none');
		$('#left_ul1').css('display', 'block')
		$('#cp_6>div').eq($(this).index() + 1).css('display', 'block');
		$('#sc_1 li').attr('class', '')
		$('#sc_1 li').find('img').remove();
		$(this).attr('class', 'active_b')
		$(this).append('<img src="img/ico_21.png"/>');

	})
	$('#sc_2 li').click(function() {
		$('#cp_7>div').css('display', 'none');
		$('#left_ul2').css('display', 'block')
		$('#cp_7>div').eq($(this).index() + 1).css('display', 'block');
		$('#sc_2 li').attr('class', '')
		$('#sc_2 li').find('img').remove();
		$(this).attr('class', 'active_b')
		$(this).append('<img src="img/ico_21.png"/>');

	})

	$('#cp_7 p a').click(function() {
		$('#cp_7>div').css('display', 'none');
		$('#zx_de').css('display', 'block');
	})
	$('#back').click(function() {
		$('#left_ul2').css('display', 'block')
		$('#zx_1').css('display', 'block');
		$('#zx_de').css('display', 'none');
	})
}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list fl">
			<!-- <h3 class="score" style="background: none; padding-left: 0;">查看大赛</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看大赛</span>
			</div>
			<div class="tab_5" id="tab">

				<ul id="tab_1" class="tab_a">
					<li class="active_c">查看大赛</li>
					<li>查看赛程</li>
					<li>查看资讯</li>
				</ul>
				<div class="cp_1" style="display: block;">
					<div class="left_cp1" id="baseInfoDiv"></div>
					<div class="right_cp1">
						<div class="jx" id="awardsDiv" style="border: none;"></div>
					</div>

				</div>
				<div class="lesson_add_1" id="matchInfoDiv" style="display: none;">
				</div>

				<div class="cp_5" id="cp_7">
					<div id="bach">
						<div class="search_3" style="width: 986px; margin-top: 10px; margin-bottom: 10px;">
							<p>
								资讯标题： <input type="text" id="title"/> 创建时间： <input type="text" id="startTime"/><span>至</span><input type="text" id="endTime"/>
	
							</p>
							<input type="button" value="重置" onclick="doReset();"/> <input type="button" value="查询" class="btn_cx" onclick="doSearch()"/>
	
						</div>
						<div style="clear: both;"></div>
						<div>
							<table id="messageTable"></table>
							<div id="paginatorPaging" style="text-align: right;"></div>
						</div>
					</div>
					
					<div id="fwb" style="display: none;">
						<div id="content"></div>
						<input type="button" value="返回" class="backBtn" onclick="toBack()"/>
					</div>
					
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>