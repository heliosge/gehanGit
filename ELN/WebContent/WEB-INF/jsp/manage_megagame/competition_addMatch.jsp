<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>赛程管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/ztree_diy.css"/>
<style type="text/css">
#startTime .msg-wrap.n-error{margin-left: -145px;}
#endTime .msg-wrap.n-error{margin-left: -145px;}
.lesson_add .add_gr .add_fr span{margin-left: 0px;}
.course_tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
.ztree li{margin-left:8px}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var operateType = '${operateType}';//1新增；2修改
var matchId = '${matchId}';
var megagameId = '${megagameId}';
var matchInfoList;//当前大赛下的所有赛程信息
var dateLimit = [];
var tempExamId;
var x_categoryId;
var x_deptId;
$(function(){
	$("#saveElse").show();
	initHeanTitle();
	dateLimit = getLastMatchEndTime();
	$("#operatorTitle").html("新增赛程");
	if(operateType==2){//修改
		$("#operatorTitle").html("修改赛程");
		initHeadInfo();
		//根据赛程id获取数据
		initMatchInfo();
		$("#saveElse").hide();
	}
	initValidate();
	initDatepicker();
});
/**
 * 获取当前赛程的上一个赛程的结束时间、下一个赛程的开始时间
 */
function getLastMatchEndTime(){
	var dates = [];
	var startTime;
	var endTime;
	if(matchInfoList){
		var len = matchInfoList.length;
		for(var i=0;i<len;i++){
			var mid = matchInfoList[i].id;
			var urlStr = "<%=request.getContextPath()%>/megagameManage/getExamInfoForMatch.action";
			if(mid==matchId){//当前赛程
				var orderNum;
				if(len>0){
					orderNum = matchInfoList[i].orderNum;
				}
				if(len<=1){//只有一个赛程或一个赛程都没有
					startTime = new Date();
					dates.push(startTime);
					dates.push(endTime);
					return dates;
				}else if(len>1 && i==len-1){//最后一条
					var examId = matchInfoList[i-1].examId;//上一个赛程的考试信息
					$.ajax({
						type : "POST",
						url : urlStr,
						async:false,
						data : {"examId":examId},
						success : function(data) {
							if(data.rtnResult=="SUCCESS"){
								var datas = data.rtnData;
								var bTime=datas.beginTime;
								var eTime=datas.endTime;
								startTime = eTime;
								dates.push(startTime);
								dates.push(endTime);
							}
						}
					});
					return dates;
				}else{
					if(i!=0){//非第一个
						var examId = matchInfoList[i-1].examId;//上一个赛程的考试id
						$.ajax({
							type : "POST",
							url : urlStr,
							async:false,
							data : {"examId":examId},
							success : function(data) {
								if(data.rtnResult=="SUCCESS"){
									var datas = data.rtnData;
									var bTime=datas.beginTime;
									var eTime=datas.endTime;
									startTime = eTime;
								}
							}
						});
					}
					var examId2 = matchInfoList[i+1].examId;//下一个赛程的考试id
					$.ajax({
						type : "POST",
						url : urlStr,
						async:false,
						data : {"examId":examId2},
						success : function(data) {
							if(data.rtnResult=="SUCCESS"){
								var datas = data.rtnData;
								var bTime=datas.beginTime;
								var eTime=datas.endTime;
								endTime = bTime;
							}
						}
					});
					dates.push(startTime);
					dates.push(endTime);
				}
			}else if(operateType==1 && i==len-1){//在原有基础上新增赛程时，取最后一个赛程
				var examId = matchInfoList[i].examId;//考试信息id
				$.ajax({
					type : "POST",
					url : urlStr,
					async:false,
					data : {"examId":examId},
					success : function(data) {
						if(data.rtnResult=="SUCCESS"){
							var datas = data.rtnData;
							var eTime=datas.endTime;
							startTime = eTime;
						}
					}
				});
				dates.push(startTime);
			}
		}
	}
	return dates;
}
/**
 * 初始化日历控件
 */
function initDatepicker() {
	$("#startTime").datetimepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd',
		minDate: new Date() 
	});

	$("#endTime").datetimepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd',
		minDate: new Date() 
	});
}
/**
 * 修改--获取大赛下的所有赛程
 */
function initHeanTitle(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAllMatchBymegagameId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {"megagameId":megagameId},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				matchInfoList= data.rtnDataList;
			}
		}
	});
}
/**
 * 生成头部标签
 */
function initHeadInfo(){
	var list = matchInfoList;
	var html="";
	var opd = $("#title_ul");
	html+="<li class=\"com_2\" style=\"cursor: pointer;\" onclick=\"toModifyMegagame("+megagameId+")\">修改大赛</li>";
	if(list){
		var len = list.length;
		for(var i=0;i<len;i++){
			var classStr = "com_2";
			var id = list[i].id;
			var orderNum = list[i].orderNum;
			if(id==matchId){//当前修改的赛程，则控制样式为选中
				classStr="com_1";
			}
			html+="<li class=\""+classStr+"\" style=\"cursor: pointer;\" onclick=\"toModifyMatch("+id+")\">修改赛程"+orderNum+"</li>";
		}
	}
	opd.empty();
	opd.html(html);
}
/**
 * 跳转修改大赛
 */
function toModifyMegagame(megagameId){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMegagame.action?operateType=2&megagameId="+megagameId;
}

/**
 * 跳转 修改赛程
 */
 function toModifyMatch(id){
	 window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMatch.action?operateType=2&megagameId="+megagameId+"&matchId="+id;
}
/**
 * 修改-初始化赛程信息
 */
function initMatchInfo(){
	var urlStr = "<%=request.getContextPath()%>/megagameManage/getMatchInfoById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"matchId":matchId},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var datas = data.rtnData;
				var name = datas.name;
				var content = datas.content;
				var rule = datas.rule;
				var examId = datas.examId;
				var promotionNumber = datas.promotionNumber;
				
				tempExamId = examId;//存放全局，方便修改时传递参数
				initExamInfo(examId);
				$("#scName").val(name);
				$("#scContent").val(content);
				$("#rule").val(rule);
				$("#promotionNumber").val(promotionNumber);
			}
		}
	});
}
/**
 * 修改-初始化赛程关联考试信息
 */
function initExamInfo(examId){
	var urlStr = "<%=request.getContextPath()%>/megagameManage/getExamInfoForMatch.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {"examId":examId},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var datas = data.rtnData;
				var paperId=datas.paperId;
				var paperName=datas.paperName;
				var displayMode=datas.displayMode;
				var passScorePercent=datas.passScorePercent;
				var duration=datas.duration;
				var allowTimes=datas.allowTimes;
				var beginTime=datas.beginTime;
				var endTime=datas.endTime;
				var isAnswerPublic=datas.isAnswerPublic;
				var randomOrderMode=datas.randomOrderMode;
				var isAutoMarking = datas.isAutoMarking;
				var notice = datas.notice;
				var allowLeaveTimes = datas.allowLeaveTimes;
				var scoreMarkerId = datas.scoreMarker;
				var scoreMarkerName = datas.scoreMarkerName;
				$("#sjId").val(paperId);
				$("#sjName").val(paperName);
				
				$("input[name='rd_2']").each(function(){
					if($(this).val()==displayMode){
						$(this)[0].checked=true;
					}
				});
				$("#duration").val(duration);
				$("#passScorePercent").val(passScorePercent);
				$("#times").val(allowTimes);
				$("#startTime").val(beginTime);
				$("#endTime").val(endTime);
				$("input[name='rd_3']").each(function(){
					if($(this).val()==isAnswerPublic){
						$(this)[0].checked=true;
					}
				});
				$("input[name='rd_4']").each(function(){
					if($(this).val()==randomOrderMode){
						$(this)[0].checked=true;
					}
				});
				$("input[name='rd_5']").each(function(){
					if($(this).val()==isAutoMarking){
						$(this)[0].checked=true;
					}
				});
				$("#allowLeaveTimes").val(allowLeaveTimes);
				$("#notice").val(notice);
				$("#pyrId").val(scoreMarkerId);
				$("#pyrName").val(scoreMarkerName);
			}
		}
	});
}
function initTree1(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: zTreeOnClick1
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/exam/paperCategory/list.action",
		success:function(data){
			$.fn.zTree.init($("#treePage1"), setting, data);
		}
	});
}
function zTreeOnClick1(event, treeId, treeNode){
	var id = treeNode.id; //分类id
	x_categoryId = id;
	$('#paperTable').mmGrid().load({"categoryId":id});
}
function initGrid1() {
	$('#paperTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : "<%=request.getContextPath()%>/megagameManage/getPaperByCategory.action",
						width : 'auto',
						height : '265px',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : false,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var params = new Object();
							
							var paperName = $("#paperName").val();
							params.userId = userId;
							params.paperName = paperName;
							params.categoryId = x_categoryId;
							
							return params;
						},
						cols : [ {
							title : 'id',
							name : 'id',
							hidden : true
						}, {
							title : '试卷名称',
							name : 'title',
							align : 'center'
						}, {
							title : '试卷库',
							name : 'categoryName',
							align : 'center'
						}, { 
							title : '题型分布',
							name : 'count1',
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								str = "主观"+item.count1+"单选"+item.count2+"多选"+item.count3+"判断"+item.count4+
								"填空"+item.count5+"组合"+item.count6+"多媒体"+item.count7;
								return str;
							}
						}, {
							title : '试卷总分',
							name : 'totalScore',
							align : 'center'
						}],
						plugins : [ $('#paginatorPaging1').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
							// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}

function initTree2(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: zTreeOnClick2
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/exam/exam/getDepartmentBySubCompanyId.action",
		success:function(data){
			$.fn.zTree.init($("#treePage2"), setting, data);
		}
	});
}
function zTreeOnClick2(event, treeId, treeNode) {
	var deptId = treeNode.id;
	x_deptId = deptId;
	$('#peopleTable').mmGrid().load({"deptId":deptId});
	$("#userName").val("");
	$("#name").val("");
	
};

function initGrid2() {
	$('#peopleTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : "<%=request.getContextPath()%>/megagameManage/getAllPeopleByDept.action",
						width : 'auto',
						height : '265px',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var params = new Object();
							
							var userName = $("#userName").val();
							var name = $("#name").val();
							params.userId = userId;
							params.userName = userName;
							params.name = name;
							params.deptId = x_deptId;
							
							return params;
						},
						cols : [ {
							title : 'id',
							name : 'id',
							hidden : true
						}, {
							title : '用户名',
							name : 'userName',
							align : 'center'
						}, {
							title : '姓名',
							name : 'name',
							align : 'center'
						}, {
							title : '部门',
							name : 'deptName',
							align : 'center'
						},{
							title : '岗位',
							name : 'postName',
							align : 'center'
						}],
						plugins : [ $('#paginatorPaging2').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
							// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}
/**
 * 选择试卷
 */
function selectPaper(){
	var selectPaper = $("#selectPaper");
	initTree1();
	//initGrid1();
	//$('#paperTable').mmGrid().load();
	dialog(
			{
				title : "选择试卷",
				width : 980,
				height : 350,
				content :selectPaper,
				okValue : '确定',
				fixed:true,
				ok : function() {
					var items = $('#paperTable').mmGrid().selectedRows();
					var len = items.length;
					if(len==1){
							var pIds ="";
							var pNames ="";
							var pId = items[0].id;
							var paperName = items[0].title;
							pIds +=pId;
							pNames +=paperName;
							$("#sjId").val(pIds);
							$("#sjName").val(pNames);
					}else{
						dialog.alert("请选择一条数据！");
						return false;
					}
					var sjId = $("#sjId").val();
					if(!sjId || sjId==""){
						$("#span_xzsj").show();
					}else{
						$("#span_xzsj").hide();
					}
				},
				cancelValue : '取消',
				cancel : function() {
				}
			}).showModal();
			setTimeout(function(){
				initGrid1();
				$('#paperTable').mmGrid().load();
			},200);
}
/**
 * 选择批阅人
 */
function selectPeople(){
	var selectPeople = $("#selectPeople");
	initTree2();
	dialog(
			{
				title : "选择批阅人",
				width : 980,
				height : 350,
				content : selectPeople,
				okValue : '确定',
				ok : function() {
					var items = $('#peopleTable').mmGrid().selectedRows();
					var len = items.length;
					var uIds ="";
					var uNames ="";
					for(var i=0;i<len;i++){
						var uId = items[i].id;
						var userName = items[i].userName;
						uIds +=uId;
						uNames +=userName;
						if(i!=len-1){//非最后一条
							uIds +=",";
							uNames +=",";
						}
					}
					$("#pyrId").val(uIds);
					$("#pyrName").val(uNames);
					var pyrId = $("#pyrId").val();
					if(!pyrId || pyrId==""){
						$("#span_xzpyr").show();
					}else{
						$("#span_xzpyr").hide();
					}
				},
				cancelValue : '取消',
				cancel : function() {
				}
			}).showModal();
	
			setTimeout(function(){
				initGrid2();
				$('#peopleTable').mmGrid().load();
			},200);
}
/**
 * 人员搜索
 */
function doPeopleSearch(){
	$('#peopleTable').mmGrid().load({"page":1});
}
/**
 * 搜索试卷
 */
function doPaperSearch(){
	$('#paperTable').mmGrid().load({"page":1});
}
/**
 * 保存
 */
 function doSave(saveFlag){
	var matchName = $("#scName").val(); //赛程名称
	var matchWay = 1 //比赛方式 默认为考试，此字段后台未用到（预留便于后期扩展赛程方式）
	var matchContent = $("#scContent").val();//赛程内容
	var rule = $("#rule").val();//评审规则
	var paperId = $("#sjId").val();//选择试卷id
	var duration = $("#duration").val();//考试时长
	var times = $("#times").val();//考试次数
	var passScorePercent = $("#passScorePercent").val();//及格分 百分比
	var startTime  =  $("#startTime").val();//考试开始时间
	var endTime  =  $("#endTime").val();//考试结束时间
	var allowLeaveTimes  =  $("#allowLeaveTimes").val();//允许离开次数
	var checkPeoples  =  $("#pyrId").val();//批阅人
	var notice  =  $("#notice").val();//考前须知
	var promotionNumber  =  $("#promotionNumber").val();//晋级人数
	var displayModel="";//显示模式
	$("input[name='rd_2']").each(function(){
		if($(this)[0].checked==true){
			displayModel = $(this).val();
		}
	});
	var isPublic="";//答案是否公开
	$("input[name='rd_3']").each(function(){
		if($(this)[0].checked==true){
			isPublic = $(this).val();
		}
	});
	var randomModel="";//随机打乱
	$("input[name='rd_4']").each(function(){
		if($(this)[0].checked==true){
			randomModel = $(this).val();
		}
	});
	var isAutoMarking="";//主观题是否自动批阅
	$("input[name='rd_5']").each(function(){
		if($(this)[0].checked==true){
			isAutoMarking = $(this).val();
		}
	});
	
	var params = new Object;
	params.megagameId = megagameId;
	params.matchName = $.trim(matchName);
	params.matchWay = matchWay;
	params.matchContent = $.trim(matchContent);
	params.rule = $.trim(rule);
	params.paperId = paperId;
	params.duration = $.trim(duration);
	params.times = $.trim(times);
	params.startTime = $.trim(startTime);
	params.endTime = $.trim(endTime);
	params.allowLeaveTimes = $.trim(allowLeaveTimes);
	params.checkPeoples = checkPeoples;
	params.notice = $.trim(notice);
	params.promotionNumber = $.trim(promotionNumber);
	params.displayModel = displayModel;
	params.isPublic = isPublic;
	params.randomModel = randomModel;
	params.isAutoMarking = isAutoMarking;
	params.passScorePercent = $.trim(passScorePercent);
	params.operateType = operateType;
	params.examId = tempExamId;//修改时-考试id
	params.matchId = matchId;//修改时-赛程id
	$('#addForm').isValid(function(v) {
		var sjId = $("#sjId").val();
		if(!sjId || sjId==""){
			v=false;
			$("#span_xzsj").show();
		}else{
			$("#span_xzsj").hide();
		}
		var pyrId = $("#pyrId").val();
		if(!pyrId || pyrId==""){
			v=false;
			$("#span_xzpyr").show();
		}else{
			$("#span_xzpyr").hide();
		}
		
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		
		if(!startTime || startTime=="" || !endTime || endTime==""){
			v=false;
			$("#msg_time").html("开始时间和结束时间不能为空");
			$("#span_time").show();
		}else{
			//处理时间类型兼容性
			startTime =  convertToDate(startTime);
			
			endTime =  convertToDate(endTime);
			
			var msg="";
			//开始时间验证
			var startDate =null;
			if(dateLimit){
				startDate = dateLimit[0];
			}
			var checkDate = startTime;
			if(checkDate>endTime){
				msg = "开始时间不能大于结束时间";
			}else{
				if(startDate){
					if(checkDate<startDate){
						msg = "开始时间不能小于上一赛程结束时间("+dateLimit[0]+")";
					}
				}
			}
			//结束时间验证
			var endDate = null;
			if(dateLimit){
				endDate = dateLimit[1];
			}
			var checkDate = endTime;
			if(checkDate<startTime){
				msg = "结束时间不能小于开始时间";
			}else{
				if(endDate){
					if(checkDate>endDate){
						msg = "结束时间不能大于下一赛程开始时间("+dateLimit[1]+")";
					}
				}
			}
			if(msg!=""){
				v=false;
				$("#msg_time").html(msg);
				$("#span_time").css("display","inline-block");
			}else{
				$("#span_time").hide();
				$("#span_time").css("display","none");
			}
		}
		
		var urlStr = "<%=request.getContextPath()%>/megagameManage/saveOrUpdate2Match.action";
		if (v) {
			$.ajax({
				type : "POST",
				url : urlStr,
				data : params,
				success : function(data) {
					dialog.alert("操作成功！");
					if(saveFlag==2){//保存并继续添加赛程
						$("#addForm")[0].reset();//重置表单
						dateLimit.push(params.endTime);
						dateLimit.push(null);
					}else{
						window.location.href = "<%=request.getContextPath()%>/megagameManage/toMegagameList.action";
					}
				}
			});
		}else{
			dialog.alert("填写有误，请检查");
		}
	});
}

function convertToDate(str){
	if(str){
		var temp = str.split(" ");
		var temp1 = temp[0].split("-");
		var temp2 = temp[1].split(":");
		var temp2len = temp2.length;
		
		var years = temp1[0];
		var months = temp1[1];
		var days = temp1[2];
		
		var hours = temp2[0];
		var minutes = temp2[1];
		var seconds;
		if(temp2len==3){
			seconds = temp2[2];
		}else{
			seconds = "00";
		}
		return new Date(years,months,days,hours,minutes,seconds);
	}
}
/**
 * 开始、结束时间离开输入框事件
 */
function checkWhenBlur(flagIndex){
	var msg="";
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if(!startTime || startTime=="" || !endTime || endTime==""){
		$("#msg_time").html("开始时间和结束时间不能为空");
		$("#span_time").show();
	}else{
		//处理时间类型兼容性
		startTime =  convertToDate(startTime);
		endTime =  convertToDate(endTime);
		if(flagIndex==1){//开始时间
			//开始时间验证
			var startDate =null;
			if(dateLimit){
				startDate = dateLimit[0];
			}
			var currentDate = new Date;
			var checkDate = startTime;
			if(checkDate>endTime){
				msg = "开始时间不能大于结束时间";
			}else{
				if(startDate){
					if(checkDate<startDate){
						msg = "开始时间不能小于上一赛程结束时间("+dateLimit[0]+")";
					}
				}
			}
		}else if(flagIndex==2){//结束时间
			//结束时间验证
			var endDate = null;
			if(dateLimit){
				endDate = dateLimit[1];
			}
			var currentDate = new Date;
			var checkDate = endTime;
			if(checkDate<startTime){
				msg = "结束时间不能小于开始时间";
			}else{
				if(endDate){
					if(checkDate>endDate){
						msg = "结束时间不能大于下一赛程开始时间("+dateLimit[1]+")";
					}
				}
			}
		}
		if(msg!=""){
			$("#msg_time").html(msg);
			$("#span_time").css("display","inline-block");
		}else{
			$("#span_time").css("display","none");
		}
	}
}
/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right_effect',
		msgStyle:"margin-top: 10px;left: 10px;",
		rules : {
			checkTitle:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{
						"userId":userId,
						"megagameId":megagameId,
						"matchId":matchId,
						"name":$.trim($("#scName").val())
						},
					url:"<%=request.getContextPath()%>/megagameManage/checkMatchNameIsOnly.action",
					success:function(data){
						var flag = true;
						if(data > 0){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		fields : {
			name : {
				rule : "required;checkTitle;length[~30]",
				msg : {
					required : "赛程不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			content : {
				rule : "required;length[~200]",
				msg : {
					required : "参赛内容不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			rule : {
				rule : "required;length[~200]",
				msg : {
					required : "评审规则不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			duration : {
				rule : "required;range[1~999]",
				msg : {
					required : "考试时长不能为空",
					range : "输入1~999之间的数字"
				}
			},
			passScorePercent : {
				rule : "required;integer[+];range[1~100]",
				msg : {
					required : "及格分不能为空",
					integer : "请输入1~100的正整数",
					range : "请输入1~100的正整数"
				}
			},
			times : {
				rule : "required;range[1~100]",
				msg : {
					required : "考试次数不能为空",
					range : "输入1~100之间的数字"
				}
			},
			notice : {
				rule : "required;length[~2000]",
				msg : {
					required : "考前须知不能为空",
					length : "长度需小于等于2000个字符"
				}
			},
			allowLeaveTimes : {
				rule : "required;integer[+]",
				msg : {
					required : "允许离开考试次数不能为空",
					integer : "请输入正整数"
				}
			},
			promotionNumber : {
				rule : "required;integer[+]",
				msg : {
					required : "晋级人数不能为空",
					integer : "人数请输入正整数"
				}
			}
		}

	});
}
function addDiyDom(treeId, treeNode) {
	var spaceWidth = 16;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 0) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
	if(!treeNode.isParent){
		switchObj.remove();
	}
}
</script>
</head>
<body>
<div class="content">
		<!-- <h3 id="operatorTitle">新增大赛</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);;"/> 
			<span id="operatorTitle" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增大赛</span>
		</div>
		<div class="tab_competition">
			<ul id="title_ul">
				<li class="com_2">1新增大赛</li>
				<li class="com_1">2新增赛程</li>
			</ul>
		</div>
		<div class="lesson_add">
		<form id="addForm">
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>赛程名称：</em>
				</div>
				<div class="add_fr">
					<input type="text" name="name" id="scName"/>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>比赛方式：</em>
				</div>
				<div class="add_fr">
					<input type="radio" checked="checked" name="rd_1" id="bsfs"/> <span>考试</span>
				</div>
			</div>

			<div class="add_gr" style="margin-bottom: 40px;">
				<div class="add_fl">
					<span>*</span> <em>赛程内容：</em>
				</div>
				<div class="add_fr">
					<textarea style="overflow: scroll; height: 70px;" id="scContent" name="content"></textarea>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>评审规则：</em>
				</div>
				<div class="add_fr">
					<textarea style="overflow: scroll; height: 70px;" id="rule" name="rule"></textarea>
				</div>
			</div>

			<div class="add_gr" style="margin-top: 30px;">
				<div class="add_fl">
					<span>*</span> <em>选择试卷：</em>
				</div>
				<div class="add_fr">
					<input type="hidden" id="sjId">
					<input type="text" id="sjName" style="width: 300px;" readonly="readonly"/>
					 <input type="button" value="选择试卷" class="xz" onclick="selectPaper()" />
					 <span id="span_xzsj" class="msg-box n-right" for="sjName" style="margin-top: 12px;display: none;">
							<span class="msg-wrap n-error" role="alert">
								<span class="n-arrow"><b>◆</b><i>◆</i></span>
								<span class="n-icon"></span>
								<span class="n-msg">试卷不能为空</span>
							</span>
						</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>显示模式：</em>
				</div>
				<div class="add_fr">
					<input type="radio" name="rd_2" value="1" checked="checked"/> <span>整卷显示</span> 
					<input type="radio" name="rd_2" value="2"/> <span>单题可逆</span> 
					<input type="radio" name="rd_2" value="3"/> <span>单题不可逆</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>考试时长：</em>
				</div>
				<div class="add_fr">
					<input type="text" id="duration" name="duration"/>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>及格分：</em>
				</div>
				<div class="add_fr">
					总分的<input type="text" id="passScorePercent" name="passScorePercent" style="width: 280px;"/>%
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>考试次数：</em>
				</div>
				<div class="add_fr">
					<input type="text" id="times" name="times"/>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>考试时间：</em>
				</div>
				<div class="add_fr">
					<input type="text" style="width: 135px;" id="startTime" name="startTime" readonly="readonly" onblur="checkWhenBlur(1)"/> <span>
					至</span> <input type="text" style="width: 148px;" id="endTime" name="endTime" readonly="readonly" onblur="checkWhenBlur(2)"/>
					
					<span id="span_time" class="msg-box n-right" style="margin-top: 12px;display: none;">
						<span class="msg-wrap n-error">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span id="msg_time" class="n-msg">开始时间不能为空</span>
						</span>
					</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>答案是否公开：</em>
				</div>
				<div class="add_fr">
					<input type="radio" name="rd_3" value="1"/> <span>是</span> 
					<input type="radio" checked="checked" name="rd_3" value="0" /> <span>否</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>随机打乱：</em>
				</div>
				<div class="add_fr">
					<input type="radio" name="rd_4" value="1" checked="checked"/> <span>题目顺序</span> 
					<input type="radio" name="rd_4" value="2"/> <span>选项顺序</span>
					<input type="radio" name="rd_4" value="3"/> <span>题目+选项顺序</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>包含主观题是否自动批阅：</em>
				</div>
				<div class="add_fr">
					<input type="radio" name="rd_5" value="1"/> <span>是</span> 
					<input type="radio" checked="checked" name="rd_5" value="0"/> <span>否</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>允许离开页面次数：</em>
				</div>
				<div class="add_fr">
					<input type="text" id="allowLeaveTimes" name="allowLeaveTimes"/>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>批阅人信息：</em>
				</div>
				<div class="add_fr">
					<input  type="hidden" id="pyrId"/>
					<input type="text" style="width: 300px;" id="pyrName" readonly="readonly"/>
					<input type="button" value="选择批阅人" class="py" onclick="selectPeople()"/>
					<span id="span_xzpyr" class="msg-box n-right" for="pyrName" style="margin-top: 12px;display: none;">
						<span id="aaaccc" class="msg-wrap n-error">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">批阅人不能为空</span>
						</span>
					</span>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>考前须知：</em>
				</div>
				<div class="add_fr">
					<textarea style="overflow: scroll; height: 70px;"id="notice" name="notice"></textarea>
				</div>
			</div>
			<div class="add_gr">
				<div class="add_fl">
					<span>*</span> <em>晋级人数：</em>
				</div>
				<div class="add_fr">
					<input type="text" id="promotionNumber" name="promotionNumber"/>
				</div>
			</div>
			</form>
		</div>
		<div class="button_cz">
			<input type="button" value="保存" onclick="doSave(1)"/> 
			<input type="button" id="saveElse" value="保存并继续添加" onclick="doSave(2)"/> 
			<input type="button" value="返回" class="back" onclick="history.go(-1);"/>
		</div>
		
		<!-- dialog1 选择试卷 start -->
	<div id="selectPaper" style="display: none;">
		<div class="course_tree treeDiv1" style="float: left;overflow:auto;">
        	<ul id="treePage1" class="ztree" style=""></ul>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none">
	        	<p>	
	               	试卷名称： <input id="paperName" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doPaperSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			
   			<table id="paperTable"></table>
			<div id="paginatorPaging1" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	<!-- dialog1 end -->
	
	<!-- dialog2 选择批阅人 start -->
	<div id="selectPeople" style="display: none;">
		<div class="course_tree treeDiv1" style="float: left;overflow: auto;">
        	<ul id="treePage2" class="ztree" style=""></ul>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none">
	        	<p>	
	            	用户名： <input id="userName" type="text">
	               	姓名： <input id="name" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doPeopleSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			
   			<table id="peopleTable"></table>
			<div id="paginatorPaging2" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	<!-- dialog2 end -->
	</div>
</body>
</html>