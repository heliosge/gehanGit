<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我要提问</title>
<!-- 系统样式 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- jQuery -->
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<!-- niceValidate -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- jQueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/courseTags.css" />
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js"></script>
<!-- mmGrid -->
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<style type="text/css">
body, h1, h2, h3, h4, h5, p, dl, dd, ul, ol, form, input, textarea, th,
	td, select {
	margin: 0;
	padding: 0;
}

em {
	font-style: normal;
}

li {
	list-style: none;
}

a {
	text-decoration: none;
}

img {
	border: none;
	vertical-align: top;
}

table {
	border-collapse: collapse;
}

textarea {
	resize: none;
	overflow: auto;
}

body {
	font-size: 12px;
}

.content {
	width: 1066px;
	margin: 0 auto;
	margin-top: 16px;
	padding-bottom: 200px;
	clear: both;
	overflow: hidden;
}

.content h3 {
	width: 1052px;
	padding-left: 20px;
	color: #3a3a3a;
	border-bottom: 1px solid #cccccc;
	padding-bottom: 10px;
	margin-bottom: 10px;
}

.lesson_add_2 {
	width: 1064px;
	float: left;
	padding-top: 30px;
	font-family: '微软雅黑';
	border: 1px solid #ccc;
	margin-bottom: 30px;
	padding-bottom: 50px;
}

.lesson_add_2 .add_gr {
	width: 1066px;
	height: 40px;
	line-height: 40px;
	float: left;
	margin-bottom: 10px;
}

.lesson_add_2 .add_gr .add_fl {
	width: 170px;
	float: left;
	text-align: right;
	line-height: 40px;
}

.lesson_add_2 .add_gr .add_fl span {
	color: red;
}

.lesson_add_2 .add_gr .add_fl em {
	color: #333;
}

.lesson_add_2 .add_gr .add_fr {
	margin-left: 16px;
	float: left;
	width: 880px;
	line-height: 40px;
}

.lesson_add_2 .add_gr .add_fr input[type=text] {
	width: 315px;
	height: 30px;
}

.lesson_add_2 .add_gr .add_fr input[type=button] {
	width: 90px;
	height: 30px;
	background: #d60500;
	color: #fff;
	font-family: '微软雅黑';
	text-align: center;
	border: none;
	cursor: pointer;
}

.lesson_add_2 .add_gr .add_fr input[type=radio] {
	vertical-align: middle;
}

.lesson_add_2 .add_gr .add_fr input[type=checkbox] {
	vertical-align: middle;
}

.lesson_add_2 .add_gr .add_fr select {
	width: 140px;
	height: 30px;
	border: 1px solid #ccc;
	margin-top: 10px;
	margin-right: 10px;
}

.lesson_add_2 .add_gr .add_fr span {
	margin-left: 4px;
	margin-right: 6px;
}

.lesson_add_2 .add_gr .add_fr textarea {
	width: 315px;
	height: 30px;
}

.lesson_add_2 .add_gr .add_fr h5 {
	color: #999;
	font-weight: normal;
	margin-top: -10px;
}

.lesson_add_2 .add_gr .add_fr em {
	color: #999;
}

.lesson_add_2 .add_gr_1 {
	width: 1066px;
	height: 300px;
	line-height: 300px;
	float: left;
	margin-bottom: 10px;
}

.lesson_add_2 .add_gr_1 .add_fl {
	width: 170px;
	float: left;
	text-align: right;
	line-height: 300px;
}

.lesson_add_2 .add_gr_1 .add_fl span {
	color: red;
}

.lesson_add_2 .add_gr_1 .add_fl em {
	color: #333;
}

.lesson_add_2 .add_gr_1 .add_fr {
	margin-left: 16px;
	float: left;
	width: 880px;
	line-height: 300px;
}

.lesson_add_2 .add_gr_1 .add_fr textarea {
	width: 500px;
	height: 300px;
	overflow: scroll;
}

.lesson_add_2 .add_gr_2 {
	width: 1066px;
	height: 388px;
	line-height: 388px;
	float: left;
	margin-bottom: 10px;
}

.lesson_add_2 .add_gr_2 .add_fl {
	width: 170px;
	float: left;
	text-align: right;
	line-height: 388px;
}

.lesson_add_2 .add_gr_2 .add_fl span {
	color: red;
}

.lesson_add_2 .add_gr_2 .add_fl em {
	color: #333;
}

.lesson_add_2 .add_gr_2 .add_fr {
	margin-left: 16px;
	float: left;
	width: 880px;
	line-height: 388px;
}

.lesson_add_2 .add_gr_2 .add_fr textarea {
	width: 500px;
	height: 388px;
	overflow: scroll;
}

.treeDiv1 {
	float: left;
	width: 210px;
	min-height: 305px;
	max-height: 305px;
	border: 1px solid #cdcdcd;
}

.treeDiv2 {
	overflow: auto;
	min-height: 295px;
	max-height: 295px;
}

.lesson_add_2 .add_gr {
	width: 1066px;
	height: auto;
	line-height: 40px;
	float: left;
	margin-bottom: 10px;
}

.webuploader-pick {
	position: relative;
	display: inline-block;
	cursor: pointer;
	background: #0085FE;
	padding: 1px 15px;
	color: #fff;
	text-align: center;
	border-radius: 3px;
	overflow: hidden;
}

#btn_oper input {
	width: 100px;
	height: 30px;
	background: #d60500;
	color: #fff;
	text-align: center;
	line-height: 30px;
	border: none;
	margin-right: 10px;
	cursor: pointer;
}

#btn_oper .back {
	background: #0085fe;
}

.imgsDivStyle {
	position: relative;
	width: 272px;
	height: 107px;
	float: left;
}

.removeStyle {
	position: absolute;
	top: 1px;
	right: 1px;
	z-index: 99;
}

.addPicStyle {
	position: relative;
	width: 270px;
	height: 105px;
}
</style>

<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';
var userName = '${USER_NAME}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var addPicsCount = 0;
var addPicsStr = '';
var zTree = null;

//mmGrid存放人员列表
var total = 0;//总条数
var pageNum = 0;//总页数
var page = 1;//当前页
var pageSize = 10;//每页数量
var userList = [];//存放原始userList
var tempUserList = [];
var tempList = [];//存放当前页面查询的userList
var nowData = [];//存放当前mmgrid显示的userList

/**
 * 页面加载完成
 */
$(function(){
	//初始化时间控件  
	$("#effectiveTime").datepicker({
		dateFormat : 'yy-mm-dd',
		changeMonth: true,
        changeYear: true
	});
	//初始化标签控件
	$("#tags").tags({inputName: "tags" });
	//初始化验证
	initValidate();
	//初始化上传控件
	initWebUploader();
});

/**
 * 初始化上传控件
 */
function initWebUploader(){
	//补充图片
	var uploader = WebUploader.create({
		//设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
		auto : true,
		//swf文件路径
		swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端
		server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',
		// 选择文件的按钮，可选，内部根据当前运行是创建，可能是input元素，也可能是flash。
		pick : {
			id : '#picker',
			multiple : false
		},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传。
		resize : false,
		// 只允许选择图片文件。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});
	
	// 接受文件后的操作
	uploader.on('uploadAccept', function(file, ret) {
		//图片数量
		if(addPicsCount >= 5){
			dialog.alert("最多上传"+addPicsCount+"张图片");
			return;
		}
		addPicsCount = addPicsCount + 1;
		//图片字符串
		addPicsStr = addPicsStr + ret._raw + ',';
		//拼接字符串
		var htmlStr = '';
		htmlStr += '<div class="imgsDivStyle">';
		htmlStr += '<div class="removeStyle">';
		htmlStr += '<img src="<%=request.getContextPath()%>/images/img/icon_remove.png" style="cursor:pointer;" onclick="removePic(this);"/>';
		htmlStr += '</div>';
		htmlStr += '<img src="'+ret._raw+'" class="addPicStyle"/>';
		htmlStr += '</div>';
		$("#imgsDiv").append(htmlStr);
	});
	
	//上传完刷新
	uploader.on( 'uploadFinished', function( file ,ret ) {
        uploader.reset();
    });
	
	//封面图片
	var covUploader = WebUploader.create({
		//设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
		auto : true,
		//swf文件路径
		swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端
		server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',
		// 选择文件的按钮，可选，内部根据当前运行是创建，可能是input元素，也可能是flash。
		pick : {
			id : '#selectCoverPicButton',
			multiple : false
		},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传。
		resize : false,
		// 只允许选择图片文件。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});
	
	// 接受文件后的操作
	covUploader.on('uploadAccept', function(file, ret) {
		$("#coverPic").val(ret._raw);
	});
}

/**
 * 移除图片
 */
function removePic(obj){
	//图片数量减1
	addPicsCount = addPicsCount - 1;
	if(addPicsCount < 0){
		addPicsCount = 0;
	}
	//图片字符串
	var picStr = $(obj).parent().parent().find(".addPicStyle").attr("src");
	addPicsStr = addPicsStr.replace(picStr+',',"");
	//移除图片
	$(obj).parent().parent().remove();	
}
/**
 * 初始化进度条
 */
function initJqMeter(value){
	$("#jqMeter_div").jQMeter({
		 	goal:'100',
		    raised:value+'',
		    meterOrientation:'horizontal',
		    width:'300px',
		    height:'25px'
	});
}

function selectType(){
	reloadZTree();
	dialog({
		title : "选择分类",
		width : 300,
		height : 200,
		content :$("#categoryDiv"),
		okValue : '确定',
		fixed:true,
		ok : function() {
			 var category = getSelectedZTreeNode();
	        if (!category) {
	        	dialog.alert('请先选择数据！');
	        	return false;
	        }
	        $('#typeId').val(category.id);
	        $("#typeName").val(category.name);
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
}

function reloadZTree() {
    //zTree配置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                pIdKey: "parentId",
                idKey: "id",
                rootPid: null
            },
        },
        view: {
            showTitle: true,
            selectedMulti: false,
			addDiyDom: addDiyDom
        },
        callback: {
        }
    };
    $.ajax({
        type:"POST",
        //async:false,  //默认true,异步
        data:null,
        url:'<%=request.getContextPath()%>/myAsk/getRootTypesUnCount.action',
        success:function(categoryTree){
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            //全部展开
            zTree.expandAll(true);
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

//获取树的被选中节点
function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length > 0) {
        return nodes[0];
    }
    return null;
}

function save(){
	$('#addForm').isValid(function(v) {
		var sjName = $("#typeName").val();
		if(!sjName || sjName == ""){
			$("#span_typeName").show();
			v=false;
			document.getElementById("typeName").focus();
		}else{
			$("#span_typeName").hide();
		}
		if (!v) {
			return false;
		}else{
			dialog.confirm("确认保存吗？", function(){
				var params = new Object;
				params.title = $.trim($("#title").val()); //疑问标题
				params.content = $.trim($("#content").val()); //问题补充
				if(addPicsStr != null && addPicsStr != ''){
					params.addPics = addPicsStr.substr(0,addPicsStr.length-1);//补充图片
				}
				params.label = $("input[name='tags']").val();//标签
				params.effectiveTime = $("#effectiveTime").val();//有效期
				params.typeId = $("#typeId").val();//选择分类id
				params.toWhom = $("#pyrId").val();//向谁提问
				 var isAnonymous = 2;
				 if($("#hideName:checked").val()){
					 isAnonymous = 1;
				 }
				 params.isAnonymous = isAnonymous;
				 params.isDelete = 2;
				 params.isThematic = 1;
				 params.isTop = 2;
				 
				$.ajax({
					type : "POST",
					async:false,  //此处为同步
					contentType:"application/json; charset=utf-8",
					url : "<%=request.getContextPath()%>/myAsk/addAskDetail.action",
					data : JSON.stringify(params),
					success : function(data) {
						if(data == 'SUCCESS'){
							dialog.alert("操作成功！", function(){
								window.location.href = "<%=request.getContextPath()%>/myAsk/toMyQuestion.action?showType=1";
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

/*表单验证  */
function initValidate() {
	$("#addForm").validator({
		theme:'yellow_right',
		rules:{
			checkTitle:function(element,param,field){
				var str;
				$.ajax({
					type:'POST',
					async:false,//此处为同步
					data:{"title":element.value},
					url:'<%=request.getContextPath()%>/myAsk/getAsksByTitle.action',
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str = flag || "已存在相同标题";
					}
				});
				return str;
			},
			checkToWhom:function(element,param,field){
				var str;
				$.ajax({
					type:'POST',
					async:false,//此处为同步
					data:{"toWhomName":element.value,"companyId":companyId,"subCompanyId":subCompanyId},
					url:'<%=request.getContextPath()%>/myAsk/getUserByName.action',
					success : function(data) {
						var flag = true;
						if (data.length <= 0) {
							flag = false;
							str = flag || "查无此人";
						} else if (data.length > 1) {
							flag = false;
							str = flag
									|| "此姓名存在员工重名，请让管理员添加标志（如在姓名后面添加数字），以区别重名"
						} else {
							$("#askToWhomId").val(
									data[0].id);
							flag = true;
							str = flag;
						}
					}
				});
				return str;
			}
		},
		msgStyle : 'margin-top:10px;margin-left:10px;',
		fields : {
			title : {
				rule : 'required;length[~200];checkTitle',
				msg : {
					required : "疑问不能为空",
					length : "长度小于等于200个字符"
				}
			},
			content : {
				rule : 'length[~200]',
				msg : {
					length : "长度小于等于200个字符"
				}
			},
			askToWhom : {
				rule : 'checkToWhom'
			}
		}
	});
}

/*选择批阅人  */
var peopleDialog;
function selectPeople(){
	var selectPeople = $("#selectPeople");
	initPeopleTree();
	//initPeopleGrid();
	//$('#peopleTable').mmGrid().load();
	peopleDialog = dialog({
		title : "选择提问人",
		width : 980,
		height : 350,
		content :selectPeople,
		onshow: function() {
			setTimeout(initPeopleGrid, 200);
		},
		fixed:true,
		okValue : '确定',
		ok : function() {
			var items = $('#peopleTable').mmGrid().selectedRows();
			var len = items.length;
			if(len == 0){
				dialog.alert("请至少选择一条数据!");
				return false; 
			}else{
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
			}
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
}

function initPeopleTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				view: { 
					showTitle: true,
					addDiyDom: addDiyDom
				},
				onClick: peopleOnClick
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				//addDiyDom: addDiyDom
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/exam/exam/getDepartmentBySubCompanyId.action",
		success:function(data){
			$.fn.zTree.init($("#peopleTree"), setting, data);
			$.fn.zTree.getZTreeObj("peopleTree").expandAll(true);
		}
	});
}

var departmentId;
function peopleOnClick(event, treeId, treeNode) {
	var deptId = treeNode.id;
	departmentId = deptId;
	$('#peopleTable').mmGrid().load({"deptId":deptId});
	$("#userName").val("");
	$("#name").val("");
	
};

function initPeopleGrid() {
	$('#peopleTable').mmGrid({
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
			var param = new Object();
			param.userId = userId;
			param.deptId = departmentId;
			param.userName = $.trim($("#userName1").val());
			param.name = $.trim($("#name1").val());
			param.allCompany = "all";
			return param;
		},
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '用户名',name : 'userName',align : 'center'}, 
		         {title : '姓名',name : 'name',align : 'center'}, 
		         {title : '部门',name : 'deptName',align : 'center'},
		         {title : '岗位',name : 'postName',align : 'center'}
		       ],
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

/*返回  */
function cancel() {
	window.history.go(-1);
}

//zhangchen end
</script>
</head>
<body>
	<div class="content">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我要提问</span>
		</div>
		<!-- <h3>我要提问</h3> -->
		<form id="addForm">
			<div class="lesson_add_2" style="padding-bottom: 0px; margin-bottom: 5px;">
				<div class="add_gr">
					<div class="add_fl">
						<span>*</span> <em>你的疑问：</em>
					</div>
					<div class="add_fr">
						<textarea style="height: 70px; width: 600px;" id="title" name="title"></textarea>
					</div>
				</div>
				<div class="add_gr">
					<div class="add_fl">
						<span></span> <em>问题补充：</em>
					</div>
					<div class="add_fr">
						<textarea style="height: 70px; width: 600px;" id="content" name="content"></textarea>
					</div>
				</div>
				<div class="add_gr">
					<div class="add_fl">
						<span></span> <em>补充图片：</em>
					</div>
					<div class="add_fr">
						<div id="thelist" class="uploader-list"></div>
						<div id="picker" style="line-height: 30px;">选择图片</div>
						<em class="psStyle">选填，最多选择5张图片，支持bmp、jpg、jpeg、gif、png格式的图片</em>
						<div id="imgsDiv"></div>
					</div>
				</div>
				<div class="add_gr">
					<div class="add_fl">
						<span></span> <em>标签：</em>
					</div>
					<div class="add_fr">
						<div id="tags" class="tags-box"></div>
						<em class="psStyle">最多输入5个标签，标签之间用空格或","隔开</em>
					</div>
				</div>
				<div class="add_gr">
					<div class="add_fl">
						<span></span> <em>有效期：</em>
					</div>
					<div class="add_fr">
						<input type="text" style="width: 135px;" id="effectiveTime" name="effectiveTime" /> 
						<span class="msg-box" id="msgHolder"></span>
					</div>
				</div>
				<div class="add_gr">
					<div class="add_fl">
						<span>*</span> <em>选择分类：</em>
					</div>
					<div class="add_fr">
						<input type="hidden" id="typeId" name="typeId" /> 
						<input id="typeName" name="typeName" type="text" style="width: 300px;" readonly="readonly" /> 
						<input type="button" value="选择分类" id="chooseCategory" class="py" onclick="selectType();" /> 
						<span id="span_typeName" class="msg-box n-right" for="times" style="display: none;"> 
							<span class="msg-wrap n-error" role="alert"> 
								<span class="n-arrow"><b>◆</b><i>◆</i></span>
								<span class="n-icon"></span> <span class="n-msg">分类不能为空</span>
							</span>
						</span>
					</div>
				</div>
				<div class="add_gr">
		        	<div class="add_fl">
		            	<span></span>
		                <em>向谁提问：</em>
		            </div>
		            <div class="add_fr">
		            	<input  type="hidden" id="pyrId"/>
						<input type="text" style="width: 300px;" id="pyrName" readonly="readonly"/>
		            	<input type="button" value="选择提问人" class="py"  onclick="selectPeople()"/>
		            </div>
		        </div>
				<div class="add_gr" style="padding-left: 90px;">
					<div class="add_fr">
						<input type="checkbox" name="isScorePublic" value="1" /> <span>匿名发布</span>
					</div>
				</div>
			</div>
		</form>
		<div id="btn_oper">
			<input type="button" value="保存" onclick="save();" /> 
			<input type="button" value="取消" class="back" onclick="cancel();" />
		</div>
	</div>
	<!-- dialog2 选择批阅人 start -->
	<div id="selectPeople" style="display: none;">
		<div class="course_tree treeDiv1">
        	<div id="peopleTree" class="ztree treeDiv2"></div>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 700px;border: none">
	        	<p>	
	            	用户名： <input id="userName1" type="text">
	               	姓名： <input id="name1" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doPeopleSearch()">
        </div>
   		<div style="width: 720px;float: right;">
   			
   			<table id="peopleTable"></table>
			<div id="paginatorPaging2" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	<!-- dialog2 end -->
	<div class="course_tree" style="display: none;" id="categoryDiv">
		<ul id="categoryTree" class="ztree"></ul>
	</div>
</body>
</html>
