<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成绩预览</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
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
var examId = '${examId}';
var x_rank=0;
$(function(){
	initGird();
	getExamInfo();
	
	initDeptTree();
	initPostTree();
});
var temp = 0;
//初始化grid数据
function initGird() {
	$('#cjylTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/reportForms/getGradeDetail.action',
						width : '1064px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							var param = new Object();
							var name = $("#name").val();
							//var post = $("#post").val();
							//var depName = $("#depName").val();
							var isAttend = $("#isAttend").val();
							param.userName = name;
							//param.postName = post;
							//param.depName = depName;
							
							param.deptIds = $.trim($("#deptId").val());
							param.postIds = $.trim($("#postId").val());
							
							//是否通过
							var isPassed = $("#isPassed").val();
							if(isPassed != "all"){
								param.isPassed = isPassed;
							}
							
							param.isAttend = isAttend;
							param.userId = userId;
							param.examId = examId;
							
							//不包含初始管理员
					    	param.withManager = "no";
							
							return param;
						},
						cols : [
								{title : 'id',name : 'examId',hidden : true},
								{title : '用户名',name : 'userName',align : 'center'},
								{title : '姓名',name : 'name',align : 'center'},
								{title : '岗位',name : 'postName',align : 'center'},
								{title : '部门',name : 'departmentName',align : 'center'},
								{title : '是否参与考试',name : 'isAttended',align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},
								{title : '成绩',name : 'score',align : 'center',
									renderer : function(val, item, rowIndex) {
										/* var isAttended = "";
										if(item.isAttended){
											isAttended = "已参与";
										}else{
											isAttended = "未参与";
										} */
										if(val){
											return val;
										}else{
											return 0;
										}
									}
								},
								{title : '是否通过',name : 'isPassed',align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val){
											return "是";
										}else{
											return "否";
										}
									}
								},
								{title : '排名',name : 'rank',align : 'center',
									renderer : function(val, item, rowIndex) {
										if(val && val!=0){
											//x_rank =val;
											temp = val;
											return val;
										}else{
											//x_rank=temp+1;
											return temp+1;
										}
										//return x_rank;
									}
								}],
						plugins : [ $('#paginatorPaging').mmPaginator({
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
/**
 * 导出word
 */
function exportDoc(){
	var form = $("#exportDocForm");
	form.submit();
}
/**
 * 导出excel
 */
function exportExcel(){
	var form = $("#exportExcelForm");
	form.submit();
}
function doPrint(){
	var ode = $("#pppp");
	var param = new Object();
	param.userId = userId;
	param.examId = examId;
	var urlStr = "<%=request.getContextPath()%>/exam/exam/getCjYlListAll.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : param,
		success : function(data) {
			var html="";
			html +="<table border=\"1\">";
			html +="<tr>";
			html +="<th>姓名</th>";
			html +="<th>岗位</th>";
			html +="<th>部门</th>";
			html +="<th>是否参与考试</th>";
			html +="<th>成绩</th>";
			html +="<th>是否通过</th>";
			html +="<th>排名</th>";
			html +="</tr>";
			data.map(function(v,i){
				var name=v.name;
				var departmentName=v.departmentName;
				var postName=v.postName;
				if(!postName || postName=="null"){
					postName="";
				}
				if(!departmentName || departmentName=="null"){
					departmentName="";
				}
				var isAttended=v.isAttended==true?"是":"否";
				var isPassed=v.isPassed==true?"是":"否";
				var score=v.score;
				if(!score || score=="null"){
					score=0;
				}
				var rank=v.rank;
				if(!rank || rank=="null"){
					rank="";
				}
				html+="<tr>";
				html+="<td width=\"150px\" align=\"center\">"+name+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+postName+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+departmentName+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+isAttended+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+score+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+isPassed+"</td>";
				html+="<td width=\"150px\" align=\"center\">"+rank+"</td>";
				html+="</tr>";
			});
			html +="</table>";
			$("#datas").html(html);
			ode.show();
			ode.jqprint();
			ode.hide();
		}
	});
}

/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/reportForms/toExamDetailForm.action";
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
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">成绩预览</span>
	</div>
   <!--  <div class="py_top" >
    	<h4 id="title_pp">2015届一年级语文期中考试</h4>
    </div> -->
    <div class="ts" id="ts_id">
    </div>
    <%-- <div class="button_cz fl" style="margin:0; padding-bottom:20px;">
    	<form id="exportDocForm" action="<%=request.getContextPath()%>/exam/exam/exportDoc.action" style="float: left;">
    		<input type="hidden"  name ="userId" value="${USER_ID_LONG}">
    		<input type="hidden" name="examId"  value="${examId}">
	    	<input type="button" value="导出word" onclick="exportDoc()"/>
    	</form>
    	<form id="exportExcelForm" action="<%=request.getContextPath()%>/exam/exam/exportExcel.action"style="float: left;">
    		<input type="hidden"  name ="userId" value="${USER_ID_LONG}">
    		<input type="hidden" name="examId"  value="${examId}">
	        <input type="button" value="导出excel" onclick="exportExcel()"/>
    	</form>
        <input type="button" value="打印成绩"  onclick="doPrint()"/>

    </div> --%>
    <div class="content_2">
        <div class="search_2 fl">
            <p>
                <span>姓名：</span>
                <input id="name" type="text"/>
                <span>岗位：</span>
                <input type="text" id="postName" name="postName" onclick="choosePost();" />
    			<input type="hidden" id="postId" name="postId" />
                <span>部门：</span>
                <input type="text" id="deptName" name="deptName" onclick="chooseDept();" />
    			<input type="hidden" id="deptId" name="deptId" />
                <span>是否参与考试：</span>
                <select id="isAttend">
                	<option selected="selected" value="">显示全部</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
        		<span> 是否通过：</span>
                <select id="isPassed">
                	<option selected="selected" value="all">所有</option>
                    <option value="yes">是</option>
                    <option value="no">否</option>
                </select>
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

 <div id="deptDialog" style="display:none;height:350px;overflow:auto;">
	<ul id="categoryTree" class="ztree" ></ul>   
 </div>
 
 <div id="postDialog" style="display:none;height:350px;overflow:auto;">
	<ul id="postTree" class="ztree" ></ul>   
 </div>
 
</body>
</html>