<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- ligerUI -->
<%-- <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script> --%>
<!--时间控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/themes/flick/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/js/jquery-ui-1.10.4.min.js" ></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/js/jquery-ui-1.10.4.js" ></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/addon/jquery-ui-timepicker-addon.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/addon/jquery-ui-timepicker-zh-CN.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<jsp:include page="../common/includeKindeditor.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.treeDiv1{float: left;width:210px; min-height: 305px;max-height: 305px; border: 1px solid #cdcdcd;}
	.treeDiv2{overflow:auto; min-height: 295px;max-height: 295px;}
	<%-- .ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{margin-left:10px;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{margin-left:10px;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")} --%>
</style>
<%-- <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/ztree_diy.css"/> --%>
<title>新增调查</title>
<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';// current user
//修改时的回填参数
var iBean = ${iBean};
var iBean_userList = ${iBean_userList};
var isPublished = '${isPublished}';
/*初始化时间控件  */
$(document).ready(function() { 
	$("#beginTime").datetimepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
	$("#endTime").datetimepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	}); // 日期+时分
	$("#beginTime").datetimepicker('setDate', (new Date()) );
	//$("#endTime").datepicker('setDate', (new Date()) );

});

/*富文本编辑  */
var editor;
KindEditor.ready(function(K){
	<%-- var language= '<%=language%>';
	if(language=="en_US"){
		langType ="en";
	}  --%>
	var langType ="zh_CN";
	editor = K.create("textarea[name=content]",{
		width: "80%",
		height: "250px",
		pasteType: 2,
		langType : langType,
		uploadJson : "<%=request.getContextPath()%>/file/uploadImg.action?path=CommonImage&DB=false",
		allowFileManager : false,
		filePostName:"uploadFile",
		items: [
		        'justifyleft', 'justifycenter', 'justifyright','bold','forecolor', 'hilitecolor','underline', 'strikethrough' 
		        /* 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'selectall', 
		        'formatblock', 'fontname', 'fontsize', 'italic','lineheight', 'removeformat', */
		         
			]
		
	});
});

$(function(){
	initValidate();
});
$(function(){
	if(iBean){
    	//alert(JSON.stringify(qBean.paperCategory));
    	$("#iTitle").text("修改调查");
    	$("#id").val(iBean.id);
    	//修改， 回填
    	$("#title").val(iBean.title);
    	$("#qId").val(iBean.questionnaireId);
    	$("#qName").val(iBean.questionnaireName);
    	$("#content").val(iBean.aim);
    	$("#beginTime").val(iBean.beginTime);
    	if(iBean.endTime != ''){
    		$("#endTime").val(iBean.endTime);
    	}
    	$("input[type=radio][name=isPublic][value='"+iBean.isPublic+"']").attr("checked",true);
    	$("input[type=radio][name=intendType][value='"+iBean.intendType+"']").attr("checked",true);
    	//按组织架构，需要初始化人员
    	if(iBean.intendType == 2){
    		setIntendTypeSpan(2);
    		//加载人员数据
    		if(iBean_userList){
    			tempList = iBean_userList;
    			initList(iBean_userList);
    		}
    	}
    	if(iBean.isPublished == 1){
    		$("#beginTime").attr("disabled","disabled");
    		$("#qInput").remove();//选择问卷按钮
    		$("#intendTypeSpan").remove();//添加人员按钮
    		$("#publishButton").remove();//发布按钮
    		$("input[type=radio][name=isPublic]").attr("disabled","disabled");
    		$("input[type=radio][name=intendType]").attr("disabled","disabled");
    		$("#userList").find("li").each(function(index,element){
    			//alert($(this).attr("id"));
    			$(this).children("img").remove();//人员删除图标
    		});
    	}
    }
});

//初始化问卷弹出框
function initPaperTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
	            onClick: zTreeOnClick
	        },
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showLine: false,
				showIcon: true
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/questionnaireCategory/list.action",
		success:function(data){
			$.fn.zTree.init($("#questionnaireTree"), setting, data);
			$.fn.zTree.getZTreeObj("questionnaireTree").expandAll(true);
		}
	});
}

var categoryId;
function zTreeOnClick(event, treeId, treeNode){
	var id = treeNode.id; //分类id
	categoryId = id;
	$('#questionnaireTable').mmGrid().load({"categoryId":id});
}
function initPaperGrid() {
	var paperMmg = $('#questionnaireTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : "<%=request.getContextPath()%>/questionnaire/voList.action",
		width : 'auto',
		height : '265px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : false,
		indexCol : true, // 索引列
		params : function(){
			var param = new Object();
			param.userId = userId;
			param.categoryId = categoryId;
			param.isEnabled = 1;
			param.name = $.trim($("#questionnaireName").val());
			return param;
		},
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '问卷名称',name : 'name',align : 'center'}, 
		         {title : '问卷库',name : 'categoryFullName',align : 'center'}, 
		         /* {title : '题型分布',name : 'count1',align : 'center',
					renderer : function(val, item, rowIndex) {
						var str = "";
						str = "主观"+item.count1+"单选"+item.count2+"多选"+item.count3+"判断"+item.count4+
						"填空"+item.count5+"组合"+item.count6+"多媒体"+item.count7;
						return str;
					}
		         },  */
		         {title : '最后更新时间',name : 'updateTime',align : 'center'}
		       ],
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

/*选择问卷  */
var questionnaireDialog;
function selectQuestionnaire(){
	var selectQuestionnaire = $("#selectQuestionnaire");
	initPaperTree();
	//initPaperGrid();
	//$('#paperTable').mmGrid().load();
	questionnaireDialog = dialog({
				title : "选择问卷",
				width : 980,
				height : 350,
				content :selectQuestionnaire,
				onshow: function() {
					setTimeout(initPaperGrid, 200);
				},
				fixed:true/* ,
				okValue : '确定',
				ok : function() {
					var rowData = $('#questionnaireTable').mmGrid().selectedRows();
					if(rowData.length == 0){
						dialog.alert("请选择问卷！");
						return false;
					}else{
						$("#qId").val(rowData[0].id);
						$("#qName").val(rowData[0].name);
					}
				},
				cancelValue : '取消',
				cancel : function() {
				} */
			}).showModal();
}

function questionnaireSelect(){
	var rowData = $('#questionnaireTable').mmGrid().selectedRows();
	if(rowData.length == 0){
		dialog.alert("请选择问卷！");
		return false;
	}else{
		$("#qId").val(rowData[0].id);
		$("#qName").val(rowData[0].name);
		questionnaireDialog.close();
	}
}
/*搜索问卷  */
function doPaperSearch(){
	var paperName =  $.trim($("#questionnaireName").val());
	var params = new Object;
	params.name = paperName;
	$('#questionnaireTable').mmGrid().load(params);
}

var tempList = [];//存放当前页面查询的userList
var userList = [];
var idArray = [];
//初始化授权部门弹出框
function initDepartmentTree(){
	idArray = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  true,
					chkboxType: { "Y": "", "N": "" }
			},
			callback: {
				//onClick: departmentOnClick
				onCheck: departmentOnClick
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				/*showTitle: true,
				addDiyDom: addDiyDom*/
				showLine: false,
				showIcon: true
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/exam/exam/getDepartmentBySubCompanyId.action",
		success:function(data){
			//addIconInfo(data);
			$.fn.zTree.init($("#departmentTree"), setting, data);
			$.fn.zTree.getZTreeObj("departmentTree").expandAll(true);
		}
	});
	initCheckedPage();
	dialog({
		title : "选择部门",
		width : 400,
		height : 200,
		content :$("#departmentDiv"),
		okValue : '确定',
		fixed:true,
		ok : function() {
			var deptId = $("#deptId").val();
			if(deptId == ''){
				dialog.alert("请选择部门！");
				return false;
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"deptId":deptId},
				url:"<%=request.getContextPath()%>/exam/exam/getPersonByDeptIds.action",
				success:function(data){
					//userList = data;
					if(data != '' && data != null){
						//var personListGrid = $('#personListTable').mmGrid();
						var items = data;
						var results = [];
						if(tempList != ''){
							for(var i=0;i<tempList.length;i++){
								results.push(tempList[i]);
							}
							//personListGrid.load(userList);
						}
						for(var index = items.length-1; index>=0; index--){
							var item = items[index];
							if(item){
								if(idArray.indexOf(item.id) != -1){
									continue;
								}
								results.push(item);
							}
						}
						tempList = results;
						//alert(JSON.stringify(tempList));
						initList(tempList);
						//personListGrid.addRow(results);
						//personListGrid.load(results);
						//userList= personListGrid.rows();
					}
				}
			});
			//$('#personListTable').mmGrid().load(userList);
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
}

//部门勾选
function departmentOnClick(event, treeId, treeNode) {
	/*var departmentId = treeNode.id;
	$("#deptId").val(departmentId);*/
	var ztreeObj = $.fn.zTree.getZTreeObj("departmentTree");
	var nodes = ztreeObj.getCheckedNodes(true);
	var departmentIds = "";
	for(var i=0;i<nodes.length;i++){
		departmentIds += nodes[i].id + ",";
	}
	$("#deptId").val(departmentIds.substring(0, departmentIds.length-1));
};

//初始化树节点，勾选节点
function initCheckedPage(){
	var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
	var deptIds = $("#deptId").val();
	if(deptIds != ''){
		var deptArray = deptIds.split(",");
		$.each(deptArray, function(index, val){
			var node = treeObj.getNodeByParam("id", val, null);
			if(node.parentId !=null){
				treeObj.checkNode(node,true,true);
			}else if(node.children == null){
				treeObj.checkNode(node,true,true);
			}
		});
	}
	
}

//初始化选择人员弹出框
function initPersonTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: personOnClick
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
			$.fn.zTree.init($("#personTree"), setting, data);
		}
	});
}
function personOnClick(event, treeId, treeNode) {
	var deptId = treeNode.id;
	$('#personTable').mmGrid().load({"deptId":deptId});
	$("#userName2").val("");
	$("#name2").val("");
	
};

function initPersonGrid() {
	var param = new Object();
	var personMmg = $('#personTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : "<%=request.getContextPath()%>/exam/exam/getPersonByCondition.action",
		width : 'auto',
		height : '265px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '用户名',name : 'userName',align : 'center'}, 
		         {title : '姓名',name : 'name',align : 'center'}, 
		         {title : '部门',name : 'deptName',align : 'center'},
		         {title : '岗位',name : 'postName',align : 'center'}
		       ]/*,
		plugins : [ $('#paginatorPaging4').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]*/
	});
	personMmg.on("loadSuccess",function(){
		if(personMmg){
			personMmg.select(function(item,index){
				if(item){
					if(idArray.indexOf(item.id) != -1 ){
						return true;
						//mmg.select(index);
					}
				}
				return false;
			});
		}
	});
}

/*选择人员  */
var personDialog;
function selectPerson(){
	idArray = [];
	var selectPerson = $("#selectPerson");
	// 封装参数
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			var id = tempList[i].id;
			idArray.push(id);
		}
	}
	//alert(idArray);
	personDialog = dialog({
		title : "选择人员",
		width : 980,
		height : 350,
		content :selectPerson,
		onshow: function() {
			setTimeout(initPersonGrid, 200);
		},
		fixed:true/* ,
		okValue : '确定',
		ok : function() {		
			var personTableGrid = $('#personTable').mmGrid();
			//var personListGrid = $('#personListTable').mmGrid();
			var items = personTableGrid.selectedRows();
			var results = [];
			if(tempList != ''){
				for(var i=0;i<tempList.length;i++){
					results.push(tempList[i]);
				}
			}
			for(var index = items.length-1; index>=0; index--){
				var item = items[index];
				if(item){
					if(idArray.indexOf(item.id) != -1){
						continue;
					}
					results.push(item);
				}
			}
			tempList = results;
			initList(tempList);
			//personListGrid.addRow(results);
			//userList= personListGrid.rows();
			//initList(userList);
		},
		cancelValue : '取消',
		cancel : function() {
		} */
	}).showModal();		
}

function personSelect(){
	var personTableGrid = $('#personTable').mmGrid();
	//var personListGrid = $('#personListTable').mmGrid();
	var items = personTableGrid.selectedRows();
	var results = [];
	if(tempList != ''){
		for(var i=0;i<tempList.length;i++){
			results.push(tempList[i]);
		}
	}
	for(var index = items.length-1; index>=0; index--){
		var item = items[index];
		if(item){
			if(idArray.indexOf(item.id) != -1){
				continue;
			}
			results.push(item);
		}
	}
	tempList = results;
	initList(tempList);
	personDialog.close();
	//personListGrid.addRow(results);
	//userList= personListGrid.rows();
	//initList(userList);
}

//人员搜索
function doPersonSearch(){
	var userName =  $.trim($("#userName2").val());
	var name =  $.trim($("#name2").val());
	var depName =  $.trim($("#depName2").val());
	var post =  $.trim($("#postName2").val());
	var params = new Object;
	params.userName = userName;
	params.name = name;
	params.depName = depName;
	params.post = post;
	$('#personTable').mmGrid().load(params);
}

/*生成人员列表  */
function initList(tempList){
	$("#userList").empty();
	for(var i=0;i<tempList.length;i++){
		$("#userList").append('<li id="'+tempList[i].id+'">'+tempList[i].name
				+'<img style="width:10px;padding-left:10px;" src="<%=request.getContextPath()%>/images/question_delete.png" onclick="delPerson(this)"/>'
				+'</li>');
	}
}

/*点击人员删除图标  */
function delPerson(obj){
	//alert(obj);
	var parentLi = $(obj).parent();
	//alert(parentLi.attr("id"));
	parentLi.remove();
	delFromTempList(parentLi.attr("id"));
}

/*将删除的人员从列表中去除  */
function delFromTempList(id){
	idArray = [];
	//alert(tempList.length);
	var results = [];
	var len=tempList.length;
	for(var i=0;i<len;i++){
		var lid = tempList[i].id;
		if(id==lid){
			continue;
		}
		results.push(tempList[i]);
	}
	tempList = results;
}

//zhangchen start
/*保存或发布   1-保存 2-保存并发布*/
function save(saveFlag){
	$('#addForm').isValid(function(v) {
		var qName = $("#qName").val();
		if(!qName || qName == ""){
			$("#span_qName").show();
			v=false;
			//document.getElementById("qName").focus();
		}else{
			$("#span_qName").hide();
		}
		var content = editor.html();
		if(!content || content == ""){
			$("#span_content").show();
			v=false;
		}else{
			$("#span_content").hide();
		}
		var endTime = $("#endTime").val();
		if(endTime != ''){
			var curDate = new Date().getTime();
			var beginTime = new Date(Date.parse($("#beginTime").val().replace(/-/g,"/"))).getTime();
			var endTime = new Date(Date.parse($("#endTime").val().replace(/-/g,"/"))).getTime();
			if(beginTime > endTime){
				$("#span_time").show();
				v=false;
			}else{
				$("#span_time").hide();
			}
		}
		if (!v) {
			//alert("表单验证不通过！");
			return false;
		}else{
			var saveInfo = '';
			if(saveFlag == 1){
				saveInfo = "确认保存吗？";
			}else{
				saveInfo = "确认保存并发布吗？";
			}
			/* if(saveFlag == 2){
				var curDate = new Date().getTime();
				var beginTime = new Date(Date.parse($("#beginTime").val().replace(/-/g,"/"))).getTime();
				//alert(beginTime + "   " + curDate);
				//var endTime = new Date(Date.parse($("#endTime").text().replace(/-/g,"/"))).getTime();
				if(beginTime < curDate){
					dialog.alert("考试开始时间已到达，不可以发布考试！");
					return;
				}
			} */
			var intendType = GetRadioValue("intendType");//参与者类型
			if(intendType == 2){
				if(saveFlag == 2){
					if(tempList.length == 0){
						dialog.alert("请添加人员！");
						return false;
					}
				}
			}
			var url = "<%=request.getContextPath()%>/questionnaire/addInvestigation.action";
			dialog.confirm(saveInfo, function(){
				var layerIndex = layer.msg('保存中…', {icon:16, time:0});
				var title = $.trim($("#title").val()); //调查标题
				var qId = $("#qId").val();//选择问卷id
				var aim = editor.html();//调查目的
				var beginTime  = $("#beginTime").val();//考试开始时间
				var endTime = $("#endTime").val();//考试结束时间
				var isPublic = GetRadioValue("isPublic");//结果是否公开
				var params = new Object;
				if(iBean){
					params.id = iBean.id;
					url = "<%=request.getContextPath()%>/questionnaire/modifyInvestigation.action";
				}
				params.title = title;
				params.questionnaireId = qId;
				params.aim = aim;
				params.beginTime = beginTime;
				params.endTime = endTime;
				params.intendType = intendType;
				params.isPublic = isPublic;
				//params.notice = notice.replace(/\n/g,"<br/>");
				if(saveFlag == 2){//保存并发布按钮
					if(iBean){//修改时的保存并发布,这时候的人员列表重新存入数据
						params.modifyType = 1;
					}
					params.isPublished = 1;
					params.publishTime = new Date().Format("yyyy-MM-dd HH:mm:SS");
					params.userList = tempList;
				}else{//保存按钮
					if(iBean){//修改时的保存
						//params.isPublished = iBean.isPublished;
						if(iBean.isPublished == 1){//已发布
							params.modifyType = 2;
						}else{//已保存，还未发布
							params.modifyType = 1;
							params.userList = tempList;
						}
					}else{//新增时的保存
						params.isPublished = 0;
						params.userList = tempList;
					}
				}
				//alert(JSON.stringify(params));
				$.ajax({
					type : "POST",
					async:true,  //默认true,异步
					contentType:"application/json; charset=utf-8",
					url : url,
					data : JSON.stringify(params),
					success : function(data) {
						layer.close(layerIndex);
						if(data == 'SUCCESS'){
							dialog.alert("操作成功！", function (){
								goBack();
							});
						}else{
							dialog.alert("操作失败！");
						}
					}
				});
			});
		}
	});
	
	
}

Date.prototype.Format = function(formatStr)   {   
    var str = formatStr;   
    var Week = ['日','一','二','三','四','五','六'];  
  
    str=str.replace(/yyyy|YYYY/,this.getFullYear());   
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   
  
    str=str.replace(/MM/,this.getMonth()>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));   
    str=str.replace(/M/g,(this.getMonth()+1));   
  
    str=str.replace(/w|W/g,Week[this.getDay()]);   
  
    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
    str=str.replace(/d|D/g,this.getDate());   
  
    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
    str=str.replace(/h|H/g,this.getHours());   
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
    str=str.replace(/m/g,this.getMinutes());   
  
    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
    str=str.replace(/s|S/g,this.getSeconds());   
  
    return str;   
}   
/*表单验证  */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkTitle:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{"title":$.trim($("#title").val()),"id":$("#id").val()},
					url:"<%=request.getContextPath()%>/questionnaire/validateTitle.action",
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
			title : {
				rule : "required;length[1~30];checkTitle",
				msg : {
					required : "调查标题不能为空",
					length:"考试名称必须小于等于30字符"
				}
			},
			aim : {
				rule : "required;length[1~1000];",
				msg : {
					required : "调查目的不能为空",
					length:"考试名称必须小于等于1000字符"
				}
			},
			beginTime : {
				rule : "required",
				msg : {
					required : "开始时间不能为空"
				}
			}
		}
	});
}
/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/questionnaire/gotoInvestigationList.action";
}

/*切换参与者类型 执行方法  */
function setIntendTypeSpan(type){
	if(type == 2){
		$("#intendTypeSpan").css("display","block");
	}else{
		$("#intendTypeSpan").css("display","none");
		$("#userList").empty();
		tempList = [];
	}
}


//获得单选按钮选中的值
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}

/*清空结束时间  */
function clearEndTime(){
	$("#endTime").val("");
}

//zhangchen end 
	
	
</script>
<style type="text/css">
.subClass{  }
.subClass ul{ list_style:none;}
.subClass ul li{ margin:5px 10px 5px 0px;
				display:inline-block;list-style-type:none;white-space:nowrap;
				background-color: #e4e4e4;
				padding:10px 10px;cursor: pointer;}
				
.lesson_add_2 .add_gr {
  width: 1066px;
  height: auto;
  line-height: 40px;
  float: left;
  margin-bottom: 10px;
}
</style>
</head>
<body>
<input type="hidden" id="id" value="0"/>
<input type="hidden" id="deptId"/>
<div class="content">
	<!-- <h3 id="iTitle">新增调查</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span id="iTitle" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增调查</span>
	</div>
	<form id="addForm">
	<div class="lesson_add_2">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>调查标题：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:300px;" id="title" name="title"/>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>选择问卷：</em>
            </div>
            <div class="add_fr">
            	<input type="hidden" id="qId" name="qId">
            	<input type="text" style="width:300px;" readonly="readonly" id="qName" name="qName"/>
                <input type="button" id="qInput" value="选择问卷" class="xz" onclick="selectQuestionnaire()"/>
                <span id="span_qName" class="msg-box n-right" for="qName" style="display: none;">
					<span class="msg-wrap n-error" role="alert">
						<span class="n-arrow"><b>◆</b><i>◆</i></span>
						<span class="n-icon"></span>
						<span class="n-msg">问卷不能为空</span>
					</span>
				</span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>调查目的：</em>
                <span id="span_content" class="msg-box n-right" for="times" style="display: none;">
					<span class="msg-wrap n-error" role="alert">
						<span class="n-arrow"><b>◆</b><i>◆</i></span>
						<span class="n-icon"></span>
						<span class="n-msg">内容不能为空</span>
					</span>
				</span>
            </div>
            <div class="add_fr">
            	<textarea name="content" id="content"></textarea>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>调查时间：</em>
            </div>
             <div class="add_fr">
             	<input type="text" style="width:135px;" id="beginTime" name="beginTime" readonly="readonly"/>
                <span>至</span>
             	<input type="text" style="width:135px;" id="endTime" name="endTime" readonly="readonly"/>
             	<img src="<%=request.getContextPath() %>/images/del_time.png" title="清空结束时间" onclick="clearEndTime();" style="position: relative;top:10px;cursor: pointer;"/>
             	<span id="span_time" class="msg-box n-right" for="times" style="display: none;">
					<span class="msg-wrap n-error" role="alert">
						<span class="n-arrow"><b>◆</b><i>◆</i></span>
						<span class="n-icon"></span>
						<span class="n-msg">结束时间要小于开始时间</span>
					</span>
				</span>
             </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>结果是否公开：</em>
            </div>
            <div class="add_fr">
            	<input type="radio"  name="isPublic" value="1"/>
                <span>公开（每个人可见）</span>
                <input type="radio" checked="checked"  name="isPublic" value="0"/>
                <span>不公开（所有的用户均不可见）</span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>参与者：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" checked="checked" name="intendType" value="1" onclick="setIntendTypeSpan(1);" />
                <span>全部</span>
                <input type="radio" name="intendType" value="2" onclick="setIntendTypeSpan(2);"/>
                <span>组织架构</span>
                <span id="intendTypeSpan" style="display: none;">
                	<input type="button" class="buttonClass" value="选择部门" onclick="initDepartmentTree();"/>
            		<input type="button" class="buttonClass" value="选择人员" onclick="selectPerson();"/>
                </span>
                <div class="subClass" style="line-height:10px;">
	            	<ul id="userList">
	            		<%-- <li>张三三<img style="width:10px;padding-left:10px;" src="<%=request.getContextPath()%>/images/question_delete.png"/></li> --%>
	            	</ul>
	            </div>
            </div>
          	<!-- <div>
            	<input type="button" class="buttonClass" value="选择部门" onclick="selectDept();"/>
            	<input type="button" class="buttonClass" value="选择人员" onclick="selectPerson();"/>
            </div> -->
        </div>
    </div>
    </form>
      <div class="button_cz fl" style="margin-top:20px; margin-left:0; padding:0; width:1046px;">
        	<input type="button" value="保存" onclick="save(1);"/>
            <input id="publishButton" type="button" value="保存并发布" onclick="save(2);"/>
            <input type="button" value="返回" class="back" onclick="goBack();"/>
    </div>
</div>

<!-- dialog1 选择问卷 start -->
	<div id="selectQuestionnaire" style="display: none;">
		<div class="course_tree treeDiv1" >
        	<div id="questionnaireTree" class="ztree treeDiv2" ></div>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none;">
	        	<p>	
	               	问卷名称： <input id="questionnaireName" type="text">
	        	</p>
	        	<input type="button" value="确定" class="btn_cx"  style="background-color: #0085fe;margin-right: 0px;" onclick="questionnaireSelect();">
	            <input type="button" value="查询" class="btn_cx" onclick="doPaperSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			
   			<table id="questionnaireTable"></table>
			<div id="paginatorPaging1" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	<!-- dialog1 end -->
	
	<!-- dialog2 end -->
	<div class="course_tree" style="display:none;" id="departmentDiv">
       	<ul id="departmentTree" class="ztree" style=""></ul>
  	</div>
	<!-- <div class="course_tree" style="display:none;" id="groupDiv">
       	<ul id="groupTree" class="ztree" style=""></ul>
  	</div> -->
  	<div id="selectGroup" style="display: none;">
   		<div class="search_3 fl" style="width: 700px;border: none">
	        	<p>	
	            	群组名： <input id="groupName" type="text">
	               	创建人： <input id="createUserName" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doGroupSearch()">
        </div>
       <div class="clear_both"></div>
   		<div style="width: 720px;">
   			<table id="groupTable"></table>
			<div id="paginatorPaging3" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
  	<div id="selectPerson" style="display: none;">
   		<div class="search_3 fl" style="width: 950px;border: none">
	        	<p>	
	            	用户名： <input id="userName2" type="text">
	               	姓名： <input id="name2" type="text">
	               	部门名称： <input id="depName2" type="text">
	               	岗位名称： <input id="postName2" type="text">
	        	</p>
	        	<input type="button" value="确定" class="btn_cx"  style="background-color: #0085fe;margin-right: 0px;" onclick="personSelect();">
	            <input type="button" value="查询" class="btn_cx" style="margin-right: 2px;" onclick="doPersonSearch()">
        </div>
        <div class="clear_both"></div>
   		<div style="width: 970px;">
   			<table id="personTable"></table>
			<div id="paginatorPaging4" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
</body>
</html>
