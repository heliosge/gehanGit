<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的知识中心</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.clear_both{clear:both;}
.cate-style{
	width: 100%;
	display: block;
	text-overflow: ellipsis;
	height: 40px;
	overflow: hidden;
	white-space: nowrap;
	line-height: 40px;
}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var flag =true;
var operateType = null;
$(function(){
	oldStyle();
	initGird1();
	initDatepicker();
	
	// 模型数据tree的set参数设置
	var treeSet = {
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true,
					idKey: "categoryId",
					pIdKey: "parentId",
					rootPId: 0
				},
				key:{
					title: "categoryName",
					name:"categoryName"
				}
			}
		};
	$("body").append("<div id='hideTree' style='display:none'></div>");
	$.fn.zTree.init($("#hideTree"), treeSet,${categoryList});//初始化模型树
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
function oldStyle(){
	$('#ul_exam').find('li').click(
			function() {
				$('#ul_exam').find('li').attr('class', '');
				$(this).attr('class', 'li_a');
			})
}
/**
 * tab页面切换
 */
function tabClick(type){
	if(type==1){//我上传的知识
		$("#sc_a").show();
		$("#cj_a").show();
		$("#grid1").show();
		$("#grid2").hide();
		$("#spanUser").hide();
		$("#spanPub").show();
		$("#spanStatus").show();
		operateType = null;
		$('#klCenterTable1').mmGrid().load();
	}else if(type==2){//收藏
		$("#sc_a").hide();
		$("#cj_a").hide();
		$("#grid1").hide();
		$("#grid2").show();
		operateType = 1;
		if(flag){
			initGird2();
			flag = false;
		}
		$("#spanUser").show();
		$("#spanPub").hide();
		$("#spanStatus").hide();
		$("#klCenterTable2").mmGrid().load({"operateType":"1"});
	}else if(type==3){//下载
		$("#sc_a").hide();
		$("#cj_a").hide();
		$("#grid1").hide();
		$("#grid2").show();
		operateType = 2;
		if(flag){
			initGird2();
			flag = false;
		}
		$("#spanUser").show();
		$("#spanPub").hide();
		$("#spanStatus").hide();
		$("#klCenterTable2").mmGrid().load({"operateType":"2"});
	}
	
}
//初始化grid数据
function initGird1() {
	$('#klCenterTable1')
			.mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/knowledgeLibraryInfo/getMyUploadKnowledge.action',
						width : '1046px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : true,
						indexCol : true, // 索引列
						params : function(){
							// 封装参数
							var params = new Object();
							var searchName = $("#searchName").val();
							var categoryName = $("#categoryName").val();
							var isOpen = $("#isOpen").val();
							var fromUserName = $("#fromUserName").val();
							var status = $("#status").val();
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							params.userId = userId;
							params.operateType = operateType;
							params.searchName = escapeForSql($.trim(searchName));
							params.categoryName = escapeForSql($.trim(categoryName));
							params.isOpen = isOpen;
							params.fromUserName = fromUserName;
							params.status = status;
							params.startTime =  $.trim(startTime);
							params.endTime =  $.trim(endTime);
							return params;
						},
						cols : [
								{
									title : 'id',
									name : 'knowledgeId',
									hidden : true
								},
								{
									title : '知识名称',
									name : 'knowledgeName',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var id = item.knowledgeId;
										var ahtml="<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+id;
										return "<a href=\""+ahtml+"\">"+val+"</a>";
									}
								},
								{
									title : '知识库分类',
									name : 'categoryName',
									align : 'center',
									renderer:function(val, item, rowIndex){
										var treeObj = $.fn.zTree.getZTreeObj("hideTree");
										var catePath =  getZTreePathNameWithRootNode(treeObj.getNodesByParam("categoryId",item.categoryId, null)[0],"categoryName")||val;
										return "<span class='cate-style' title='"+catePath+"'>"+catePath+"</span>";
									}
								},
								{
									title : '上传时间',
									name : 'createTime',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
									}
								},{
									title : '公开程度',
									name : 'isOpen',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var str="";
										if(val==1){
											str = "公开";
										}else{
											str = "私有";
										}
										return str;
									}
								},
								{
									title : '审核状态',
									name : 'status',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var isOpen = item.isOpen;
										var str="";
										if(val==1){
											str = "待审核";
										}else if(val==2){
											str = "审核通过";
										}else if(val==3){
											str = "拒绝";
										}
										if(isOpen!=1){
											str = "--";
										}
										return str;
									}
								},
								{
									title : '操作',
									name : 'operation',
									width : 150,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var openStatus = item.isOpen;
										var status  = item.status;
										var str = "";
										var del = "<a href=\"javascript:void(0);\" onclick=\"doDelete("+item.knowledgeId+")\">删除</a>&nbsp;";
										var mod = "<a href=\"javascript:void(0);\" onclick=\"doModify("+item.knowledgeId+","+item.uploadType+")\">修改</a>&nbsp;";
										var pubs = "<a href=\"javascript:void(0);\" onclick=\"doPublic("+item.knowledgeId+")\">&nbsp公开</a>&nbsp;";
										if(status==2){ //审批通过的知识不允许删除
											del="";
										}else if(openStatus==1 && status==1){ //公开且待审批状态下的不允许删除、修改
											del="";
											mod="";
										}
										if(openStatus==0 || (openStatus==1 && status==3)){
											pubs = "<a href=\"javascript:void(0);\" onclick=\"doPublic("+item.knowledgeId+")\">&nbsp公开</a>&nbsp;";
										}else{
											pubs = "<a href=\"javascript:void(0);\">已公开</a>&nbsp;";
										}
										str = del+mod+pubs;
										return str;
									}
								} ],
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

function initGird2() {
	$('#klCenterTable2')
			.mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : '<%=request.getContextPath()%>/knowledgeLibraryInfo/getMyKnowledgeForCollectDownload.action',
						width : '1046px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : true,
						multiSelect : true,
						indexCol : true, // 索引列
						params :  function(){
							// 封装参数
							var params = new Object();
							var searchName = $("#searchName").val();
							var categoryName = $("#categoryName").val();
							var isOpen = $("#isOpen").val();
							var fromUserName = $("#fromUserName").val();
							var status = $("#status").val();
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							params.userId = userId;
							params.operateType = operateType;
							params.searchName = escapeForSql($.trim(searchName));
							params.categoryName = escapeForSql($.trim(categoryName));
							params.isOpen = isOpen;
							params.fromUserName = fromUserName;
							params.status = status;
							params.startTime =  $.trim(startTime);
							params.endTime =  $.trim(endTime);
							return params;
						},
						cols : [
								{
									title : 'id',
									name : 'knowledgeId',
									hidden : true
								},
								{
									title : '知识名称',
									name : 'knowledgeName',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var id = item.knowledgeId;
										var ahtml="<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+id;
										return "<a href=\""+ahtml+"\">"+val+"</a>";
									}
								},
								{
									title : '知识库分类',
									name : 'categoryName',
									align : 'center',
									renderer:function(val, item, rowIndex){
										var treeObj = $.fn.zTree.getZTreeObj("hideTree");
										var catePath =  getZTreePathNameWithRootNode(treeObj.getNodesByParam("categoryId",item.categoryId, null)[0],"categoryName")||val;
										return "<span class='cate-style' title='"+catePath+"'>"+catePath+"</span>";
									}
								},{
									title : '上传者',
									name : 'createUserName',
									align : 'center'
								},
								{
									title : '上传时间',
									name : 'createTime',
									align : 'center',
									renderer : function(val, item, rowIndex) {
										return getFormatDateByLong(val, "yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									title : '操作',
									name : 'operation',
									width : 150,
									align : 'center',
									renderer : function(val, item, rowIndex) {
										var id = item.knowledgeId;
										var str = "";
										var s = "";
										var url = item.filePath;
										var uploadType = item.uploadType;
										var isDownload = item.isDownload;
										var extendName = item.extendName;
										if(url){
											url = url.replace(/.swf/,"."+extendName);
										}
										if(isDownload==1 && operateType==1){
											s = "<a href=\"javascript:void(0);\" onclick=\"toDownload("+id+","+uploadType+")\">下载</a>&nbsp;";
										}else if(operateType==2){
											if(item.collectFlag==1){//已收藏过
												s = "<a href=\"javascript:void(0);\">已收藏</a>&nbsp;";	
											}else{
												s = "<a href=\"javascript:void(0);\" onclick=\"doCollect("+item.knowledgeId+")\">收藏</a>&nbsp;";
											}
										}
										var del = "<a href=\"javascript:void(0);\" onclick=\"doDelColDow("+item.colDowId+")\">删除</a>&nbsp;";
										str = del+s;
										return str;
									}
								} ],
						plugins : [ $('#paginatorPaging2').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
														// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						})]
					});
}

function toDownload(id,uploadType){
	var downloadCount=0;
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/judgeCollecDownload.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {
			"userId" : userId,
			"knowledgeId" : id
		},
		success : function(data) {
			downloadCount = data.downloadCount;
		}
	});
	if(2==uploadType){ //创建的知识
		$("#hide_form").attr("action","<%=request.getContextPath()%>/knowledgeLibraryInfo/downCreateKl.action");
		$("#klId").val(id);
		$("#hide_form")[0].submit();
	}else if(1 == uploadType){
		$("#hide_form").attr("action","<%=request.getContextPath()%>/knowledgeLibraryInfo/downUploadKl.action");
		$("#klId").val(id);
		$("#hide_form")[0].submit();
	}
	if(downloadCount>0){
		return;
	}
	//下载表插入记录
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/addCollectAndDownload.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"resourceId" : id,
			"operateType" : 2
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				if(type==1){
				 dialog.alert("操作成功！");
				}
				judgeCollecDownload();
			}
		}
	});
}
/**
 * 删除我收藏下载的记录
 */
function doDelColDow(id){
	dialog.confirm('确认删除？',function () {
    	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/doDelColDow.action";
    	$.ajax({
    		type : "POST",
    		url : urlStr,
    		data : {
    			"id" : id
    		},
    		success : function(data) {
    			$("#klCenterTable2").mmGrid().load({"operateType":operateType});
    		}
    	});
    });
}
/**
 * 单个删除
 */
function doDelete(id){
	dialog.confirm('确认删除？',function () {
		var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/delKlById.action";
    	$.ajax({
    		type : "POST",
    		url : urlStr,
    		data : {
    			"knowledgeId" : id
    		},
    		success : function(data) {
    			$("#klCenterTable1").mmGrid().load();
    		}
    	});
    });
}
/**
 * 修改
 */
 
function doModify(id,type){
	if(type==2){
		window.location.href = "<%=request.getContextPath()%>/knowledge/edit.action?knowledgeId="+id+"&type=editCreate"+"&roleType=2";
	}else{
		window.location.href = "<%=request.getContextPath()%>/knowledge/edit.action?knowledgeId="+id+"&type=editUpload"+"&roleType=2";

	}
}
/**
 * 公开
 */
function doPublic(id){
	dialog({
	    title: '提示',
	    content: '确认公开？',
	    width:150,
	    okValue: '确定',
	    ok: function () {
	    	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toPublic.action";
	    	$.ajax({
	    		type : "POST",
	    		url : urlStr,
	    		data : {
	    			"knowledgeId" : id
	    		},
	    		success : function(data) {
	    			if(operateType==1){
	    				$("#klCenterTable2").mmGrid().load({"operateType":"1"});
	    			}else if(operateType==2){
	    				$("#klCenterTable2").mmGrid().load({"operateType":"2"});
	    			}else{
	    				$("#klCenterTable1").mmGrid().load();
	    			}
	    		}
	    	});
	    },
	    cancelValue: '取消',
	    cancel: function () {
	    }
	}).showModal();
}
/**
 * 收藏操作
 */
 function doCollect(id){
	 var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/addCollectAndDownload.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			data : {
				"operateType" : 1,//收藏操作
				"userId" : userId,
				"resourceId" : id
			},
			success : function(data) {
				if (data.rtnResult == "SUCCESS") {
					$("#klCenterTable2").mmGrid().load({"operateType":operateType});
				}
			}
		});
}
/**
 * 搜索
 */
function doSearch(){
	if(!operateType){//我上传的tab
		$("#klCenterTable1").mmGrid().load({"page":1});
	}else{
		$("#klCenterTable2").mmGrid().load({"page":1});
	}
}
/**
 * 跳转上传知识页面
 */
function toUpload(){
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toUpload.action";
}

/**
 * 跳转创建知识页面
 */
function toCreate(){
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toCreate.action";
}
/**
 * 批量删除
 */
function toDeleteMore(){
	var ids=[];
	var urlStr="";
	if(operateType==1 || operateType==2){
		var items = $("#klCenterTable2").mmGrid().selectedRows();
		var len = items.length;
		for(var i=0;i<len;i++){
			var id = items[i].colDowId;
			ids.push(id);
		}
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toDelColDown.action";
	}else{
		var items = $("#klCenterTable1").mmGrid().selectedRows();
		var len = items.length;
		for(var i=0;i<len;i++){
			var id = items[i].knowledgeId;
			var x_open = items[i].isOpen;
			var x_status = items[i].status;
			if(x_status==2 || (x_status==1 && x_open==1)){//审核通过、公开且待审核中的不能删除
				dialog.alert("审核通过以及公开且待审核中的不能删除！");
				return;
			}
			ids.push(id);
		}
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toDeleteMoreWithMy.action";
	}
	if(ids.length==0){
		dialog.alert("请先选择要删除的数据！");
		return;
	}
	dialog.confirm('确认删除？',function () {
		$.ajax({
    		type : "POST",
    		url : urlStr,
    		data :{
    			"ids":ids
    			},
    		success : function(data) {
    			if (data.rtnResult == "SUCCESS") {
    				if(!operateType){//我上传
    					$("#klCenterTable1").mmGrid().load();
    				}else{
    					$("#klCenterTable2").mmGrid().load({"operateType":operateType});
    				}
    			}
    		}
    	});
    });
	
}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list">
			<!-- <h3>我的知识中心</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的知识中心</span>
			</div>
			<div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1)">我上传的知识</li>
					<li onclick="tabClick(2)">我收藏的知识 </li>
					<li onclick="tabClick(3)">我下载的知识</li>
				</ul>
			</div>
			<div>
				<div class="button_s">
					<a href="javascript:void(0);" id="sc_a"><input type="button" value="上传知识" onclick="toUpload()"/></a>
					 <a href="javascript:void(0);"><input type="button" value="批量删除" class="delete" onclick="toDeleteMore()"/></a>
					  <a href="javascript:void(0);" id="cj_a"><input type="button" value="创建知识" onclick="toCreate()"/></a>
				</div>

					<div class="search_5">
						<p>
							<span>知识名称：</span> <input type="text" id="searchName" name="searchName"/> 
							<span>知识库分类：</span> <input type="text" id="categoryName" name="categoryName"/> 
								<span id="spanPub"><span>公开程度：</span> 
									<select id="isOpen" name="isOpen">
										<option value="" selected="selected">显示全部</option>
										<option value="1">公开</option>
										<option value="0">私有</option>
									</select>
								</span>
							<span id="spanUser" style="display: none;"><span >上传人：</span> <input type="text" id="fromUserName" name="fromUserName" /></span>
							<input type="button" class="btn_5" value="查询" onclick="doSearch()"/>
						</p>
						<p>
							<span id="spanStatus"><span>状态：</span> <select id="status" name="status">
								<option value="0" selected="selected">显示全部</option>
								<option value="1">待审批</option>
								<option value="2">审批通过</option>
								<option value="3">审批拒绝</option>
							</select>
							</span>
							<span>时间：</span>
							
							<input type="text" id="startTime" name="startTime"/> <em>至</em> <input id="endTime" type="text" name="endTime"/>
						</p>
	
					</div>
					<div class="clear_both"></div>
				<div id="grid1">
					<table id="klCenterTable1"></table>
					<div id="paginatorPaging1" style="text-align: right;"></div>
				</div>
				<div id="grid2" style="display: none;">
					<table id="klCenterTable2"></table>
					<div id="paginatorPaging2" style="text-align: right;"></div>
				</div>
			</div>
		</div>
	</div>
	<form style="display: none;" id="hide_form">
     	<input id="klId" name="klId"/>
     </form>
</body>
</html>