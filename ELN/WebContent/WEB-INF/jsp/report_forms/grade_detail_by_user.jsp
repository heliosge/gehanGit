<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>考试统计（按人员）</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqprint.js"></script>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
.button_cz fl{float: none;}

.ui-dialog-body{padding:0px;}

.ztree li span.button.noline_docu{display:none;}
.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background-image:url("")}

</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var deptId = '${deptId}';
var x_rank=0;

$(function(){
	initGird();
	initDatepicker();
	//getExamInfo();
	
	initDeptTree();
	initPostTree();
});

function initDatepicker() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});

	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
}
var temp = 0;
//初始化grid数据
function initGird() {
	$('#cjylTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/reportForms/getGradeDetailByUser.action',
						width : '1064px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						indexCol : true, // 索引列
						autoLoad:false,
						params : function(){
							var param = new Object();
							
							param.userName = $("#userName").val();
							param.name = $("#name").val();
							param.deptId = $.trim($("#deptId").val());
					    	param.postId = $.trim($("#postId").val());
					    	
					    	var isPassed = $("#isPassed").val();
							if(isPassed != "all"){
								param.is_passed = isPassed;
							}
							
							param.begin_time  = $("#startTime").val();
							param.end_time = $("#endTime").val();

							param.exam_schedule_title = $("#examTitle").val();							

					    	//不包含初始管理员
					    	param.withManager = "no";
					    	
							return param;
						},
						cols : [
								{title : 'id',name : 'userId',hidden : true},
								{title : '用户名',name : 'userName',align : 'center', width:60},
								{title : '姓名',name : 'name',align : 'center', width:60},
								{title : '部门',name : 'deptName',align : 'center', width:70},
								{title : '岗位',name : 'postName',align : 'center'},
								{title : '考试名称',name : 'exam_schedule_title',align : 'center'},
								{title : '考试时间',name : 'begin_time',width : 210,align : 'center',
									renderer : function(val, item, rowIndex) {
										var str = "";
										//var beginTime = getFormatDateByLong(val, "yyyy-MM-dd hh:mm");
										//var endTime = getFormatDateByLong(item.endTime, "yyyy-MM-dd hh:mm");
										str = item.begin_time+" - "+item.end_time
										return str;
									}
								},
								{title : '你的得分/总分/及格分',name : 'score',align : 'center',
									renderer : function(val, item, rowIndex) {
										return val + "/" + item.total_score + "/" + item.pass_score;
									}
								},
								{title : '是否通过',name : 'is_passed',align : 'center', width:50,
									renderer : function(val, item, rowIndex) {
										if(val == 1){
											return "是";
										}else{
											return "否";
										}
									}
								}
							],
						plugins : [ $('#paginatorPaging').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean  // count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}
/**
 * 获取考试信息
 */
function getExamInfo(){
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getExamInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"examId" : examId},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initExamInfo(data.rtnData);
			}
		}
	});
}

/**
 * 生成考试信息
 */
function initExamInfo(data){
	var totalScore = 0;
	var passScore = 0;
	var duration = 0;
	var title = "";
	var html="";
	var ti = $("#ts_id");
	if(data){
		totalScore = data.totalScore;
		passScore = Math.floor((data.passScorePercent/100)*totalScore);
		duration = data.duration;
		title = data.paperName;//试卷名称
	}
	html +="<span>试题名称："+title+"</span>";
	html +="<span>总分："+totalScore+"</span>";
	html +="<span>及格分："+passScore+"</span>";
	html +="<span>考试时长："+duration+"</span>";
	//$("#title_pp").html(title);
	$("#title_pp2").html(title);
	ti.empty();
	ti.html(html);
	$("#ts_id2").html(html);
}
/**
 * 搜索
 */
function doSearch(){
	$('#cjylTable').mmGrid().load({"page":1});
}


/*返回  */
function goBack(){
	
}

/* 初始化部门tree */
function initDeptTree(){
	var setting = {
			data: {key: {url: "xUrl"}, simpleData: {enable: true, pIdKey: "parentId", idKey: "id"}},
			check: {enable:  true,
				chkboxType: { "Y": "s", "N": "s" }
			},
			view: {
				showLine: false,
				showIcon: true
				//selectedMulti: false
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			var zTree = $.fn.zTree.init($("#categoryTree"), setting, data.data);
			
			//全部展开
            zTree.expandAll(true);
		}
	});
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function chooseDept(){
	dialog({
		width : 270,
		height : 350,
		title : '请选择部门',
		content : $("#deptDialog"),
	 	button: [
          {
          value: '确定',
          callback: function () {
        	  //$("#dept").html('');
        	  //deptIds = [];
        	  
        	  var ztreeObj = $.fn.zTree.getZTreeObj("categoryTree");
           	  var nodes = ztreeObj.getCheckedNodes(true);
           	  
           	  var names = [];
           	  var ids = [];
           	  $.each(nodes, function(index, val){
           			names.push(val.name);
           			
           			if(val.id.indexOf("com") != -1){
           				//alert(val.id + "   " + val.id.substring(4, val.id.length));
           				ids.push(val.id.substring(4, val.id.length));
           			}else{
           				ids.push(val.id);
           			}
           			
           	  });
           	  
           	  $("#deptName").val(names.join(","));
           	  $("#deptId").val(ids.join(","));
        	  
        	  this.close();
          }
          }
		]
	}).showModal();
}

/* 初始化岗位tree */
function initPostTree(){
	var setting = {
			data: {key: {url: "xUrl"}, simpleData: {enable: true, pIdKey: "parentId", idKey: "id"}},
			check: {enable:  true,
				chkboxType: { "Y": "s", "N": "s" }
			},
			view: {
				showLine: false,
				showIcon: true
				//selectedMulti: false
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageParam/selectManagePost.action",
		success:function(data){
			
			//alert(JSON.stringify(data));
			
			addIconInfo(data);
			var zTree = $.fn.zTree.init($("#postTree"), setting, data);
			
			//全部展开
            zTree.expandAll(true);
		}
	});
}

function choosePost(){
	dialog({
		width : 270,
		height : 350,
		title : '请选择岗位',
		content : $("#postDialog"),
	 	button: [
          {
          value: '确定',
          callback: function () {
        	  //$("#dept").html('');
        	  //deptIds = [];
        	  
        	  var ztreeObj = $.fn.zTree.getZTreeObj("postTree");
           	  var nodes = ztreeObj.getCheckedNodes(true);
           	  
           	  var names = [];
           	  var ids = [];
           	  $.each(nodes, function(index, val){
           		  
           			names.push(val.name);           			
           			ids.push(val.id);	
           	  });
           	  
           	  $("#postName").val(names.join(","));
           	  $("#postId").val(ids.join(","));
        	  
        	  this.close();
          }
          }
		]
	}).showModal();
}
</script>
</head>
<body>
<div class="content">
	<!-- <h3>成绩预览</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">考试统计（按人员）</span>
	</div>

    <div class="content_2">
        <div class="search_2 fl">
        	<p>
				<span>用户名：</span>
				<input id="userName" type="text"/>
                <span>姓名：</span>
                <input id="name" type="text"/>
                <span>部门：</span>
                <input type="text" id="deptName" name="deptName" onclick="chooseDept();" />
    			<input type="hidden" id="deptId" name="deptId" />
                <span>岗位：</span>
                <input type="text" id="postName" name="postName" onclick="choosePost();" />
    			<input type="hidden" id="postId" name="postId" />
                <span>是否通过：</span>
                <select id="isPassed">
                	<option selected="selected" value="all">所有</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </p>
       	</div>
       	<div class="search_2 fl" style="border-top: none;">
       	<p>
       		<span>考试名称：</span>
       		<input id="examTitle" type="text" />
       		<span>考试开始时间：</span>
      		<input id="startTime" type="text" /> 
      		<span>至</span> 
			<input id="endTime" type="text" />
		</p>
            <input type="button" value="查询" class="btn_cx" onclick="doSearch()"/>
       	</div>
       	<div class="clear_both"></div>
        <div>
            <table id="cjylTable"></table>
            <div id="paginatorPaging" style="text-align: right;left: -18px;position: relative;"></div>
         </div>

    </div>
</div>
<div id="pppp" style="display: none;">
	<div class="py_top" >
    	<h4 id="title_pp2">2015届一年级语文期中考试</h4>
    </div>
    <div class="ts" id="ts_id2"></div>
    <div id="datas"></div>
</div>

 <div id="deptDialog" style="display:none;height:350px;">
	<ul id="categoryTree" class="ztree" ></ul>   
 </div>
 
 <div id="postDialog" style="display:none;height:350px;">
	<ul id="postTree" class="ztree" ></ul>   
 </div>
</body>
</html>