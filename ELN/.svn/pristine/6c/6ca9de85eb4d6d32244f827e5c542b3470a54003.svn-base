<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<title>政策法规新增</title>
<style type="text/css">
.webuploader-pick{
	padding:0;
	line-height: 35px;
    position: relative;
    top: 10px;
}
.lesson_add .add_gr{height: 55px;line-height: 20px;}
/*验证插件矫正小箭头位置*/
.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>
</head>
<body>
	<div class="content">
		<!-- <h3>新增政策法规</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">
			<c:if test="${id==null}">新增</c:if> <c:if test="${id!=null}">修改</c:if>政策法规</span>
		</div>
		<form id="addForm">
			<div class="lesson_add">
		    	<div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>法规名称：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="name" name="name" type="text" style="width:300px;" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>分类：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="categoryName" name="categoryName" type="text" style="width:135px;" readonly="readonly"/>
		            	<input type="button" value="选择" onclick="selectCategory()">
		            	<input id="categoryId" name="categoryId" type="hidden"  />
		            </div>
		        </div>
		       <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>发布单位：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="publishEnterprise" name="publishEnterprise" type="text" style="width:300px;" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>发布时间：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="publishTime" name="publishTime" type="text" style="width:135px;" readonly="readonly"/>
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>施行时间：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="executeTime" name="executeTime" type="text" style="width:135px;" readonly="readonly" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>文号：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="referenceNumber" name="referenceNumber" type="text" style="width:300px;" />
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>关键字：</em>
		            </div>
		            <div class="add_fr">
		            	<input id="keyWords" type="text" name="keyWords" style="width:300px;" />
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>正文内容：</em>
		            </div>
		            <div class="add_fr">
		           		 <input id="filePath"  name="filePath" type="hidden"/>
			            <div id="uploader" class="wu-example" style="float: left;">
							<!--用来存放文件信息-->
							
							<div id="thelist" class="uploader-list"></div>
							<div class="btns">
								<div id="contentPicker" style="padding: 0px;float: left;">选择文件</div>
								<input id="fileName" type="text" style="border: none;"/>
								<p style="color: #999;padding-bottom: 0;">可选的文件格式为：*.doc,*.docx,*.xls,*.xlsx,*.ppt,*.pptx,*.txt,*.pdf</p>
							</div>
			            	<input id="fileSize" type="hidden"/>
						</div>
		            </div>
			       
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>时效性：</em>
		            </div>
		             <div class="add_fr">
		            	<input type="radio" checked="checked" name="timeliness" value="1"/>
		                <span>有效</span>
		                <input type="radio"  name="timeliness"  value="2"/>
		                <span>废止</span>
		            </div>
		    	</div>
		    	 <div class="button_cz">
		        	<input type="button" value="保存" onclick="save()"/>
		            <input type="button" value="返回" class="back" onclick="history.go(-1)"/>
	      		  </div>
			</div>
		</form>
	</div>
	<div style="display: none;" id='treedialog'>
		<ul id="treePage" class="ztree"></ul>
	</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<!-- zTree -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<script type="text/javascript">
var thisId = '${id}';//当前不为null时 为修改
var selectCateId;
var selectCateName;
$(function(){
	if(thisId){//修改时先初始化数据
		initData(thisId);
	}
	initDatePicker("publishTime");
	initDatePicker("executeTime");
	initValidate();
	uploadFile();
	initCategoryTree();
});
function initDatePicker(idName){
	$("#"+idName).datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
};

function initCategoryTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback:{onClick:function(event,treeId,treeNode){
				selectCateId = '';
				if(treeNode.parentId){// 非根
					selectCateId = treeNode.id;
					selectCateName = treeNode.name;
				}
			}}  
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/policiesRules/getCategorys.action",
		success:function(data){
			var treedata = data.rtnDataList;
			$.fn.zTree.init($("#treePage"), setting, treedata);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
/**
 * 选择分类
 */
function selectCategory(){
	// 制空
	selectCateId = '';
	layer.open({
	    type: 1,
	    btn: ['确定', '取消'],
	    yes:doSelect,
	    cancel:function(){},
	    title:'选择分类',
	    skin: 'layui-layer-rim', //加上边框
	    area: ['420px', '240px'], //宽高
	    content: $('#treedialog')
	    });
}
function doSelect(){
	if(!selectCateId){// 为空
		layer.alert('请选择有效的分类');
		return;
	}
	// 赋值
	$('#categoryId').val(selectCateId);
	$('#categoryName').val(selectCateName);
	$('#addForm').validator('hideMsg','#categoryId');
	layer.closeAll();
}
function initData(id){
	var urlStr = "<%=request.getContextPath()%>/policiesRules/getById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"id":id},
		success : function(data) {
			if(data && data.rtnResult=="SUCCESS"){
				var dataBean = data.rtnData;
				var name =  dataBean.name;
				var categoryId =  dataBean.category;
				var categoryName = dataBean.categoryName;
				var publishEnterprise =  dataBean.publishEnterprise;
				var publishTime =  getFormatDateByLong(dataBean.publishTime,'yyyy-MM-dd');
				
				var executeTime = getFormatDateByLong(dataBean.executeTime,"yyyy-MM-dd");
				var referenceNumber =  dataBean.referenceNumber;
				var keyWords =  dataBean.keyWords;
				var timeliness =  dataBean.timeliness;
				var fileName =  dataBean.fileName;
				var filePath = dataBean.filePath;
				var fileSize = dataBean.fileSize;
				$("#name").val(name);
				$("#categoryId").val(categoryId);
				$("#categoryName").val(categoryName);
				$("#fileName").val(fileName);
				$("#filePath").val(filePath);
				$("#fileSize").val(fileSize);
				$("#publishEnterprise").val(publishEnterprise);
				$("#publishTime").val(publishTime);
				$("#executeTime").val(executeTime);
				$("#referenceNumber").val(referenceNumber);
				$("#keyWords").val(keyWords);
				$("input[name='timeliness']").each(function(){
					var thisVal = $(this).val();
					if(thisVal==timeliness){
						$(this)[0].checked=true;
					}
				});
			}
		}
	});
	
}

function uploadFile(){
		var mimeType = "application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,"
						+"application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,"
						+"application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,"
						+"application/pdf,text/plain";
		var uploader = WebUploader.create({
			auto : true,
			// swf文件路径
			swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
	
			// 文件接收服务端。
			server : '<%=request.getContextPath()%>/knowledge/uploadFile.action?path=policiesRules%2FuploadRules',
	
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : {
				id : '#contentPicker',
				multiple : false
			},
	
			// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			resize : false,
	
			accept : {
				title : 'files',
				extensions : 'doc,docx,ppt,pptx,xls,xlsx,pdf,txt',
				mimeTypes : mimeType
			}
		});
		var $index;
		//文件开始上传前
		uploader.on('uploadStart', function(file) {
			$("#fileName").val(file.name);
			$("#fileSize").val(file.size);
			 //开始上传，进行蒙层处理
	        if(!$index){
	             //loading层
	           	 $index = layer.load(1, {
	           	    shade: [0.2,'#000000'] //0.1透明度的白色背景
	           	});
	        }
		});
		uploader.on( 'uploadFinished', function( file ,ret ) {
	        //上传完成，去掉蒙层
	        layer.closeAll();
	        $index = null;
	        uploader.reset();
	    });
		// 接受文件后，进行赋值操作
		uploader.on('uploadAccept', function(file, ret) {
			$("#filePath").val(ret.path);
			if($("#filePath").val()!=""){
				$("#addForm").validator('hideMsg','#filePath');
			}
		});
}
/**
 * save data
 */
function save(){
	
	var params = new Object;
	var name = $("#name").val();
	var categoryId = $("#categoryId").val();
	var publishEnterprise = $("#publishEnterprise").val();
	var publishTime = $("#publishTime").val();
	var executeTime = $("#executeTime").val();
	var referenceNumber = $("#referenceNumber").val();
	var keyWords = $("#keyWords").val();
	var fileName = $("#fileName").val();
	var extendName = fileName.substring(fileName.lastIndexOf(".")+1);
	var filePath = $("#filePath").val();
	var fileSize = $("#fileSize").val();
	var timeliness;
	$("input[name='timeliness']").each(function(){
		var thisVal = $(this).val();
		if($(this)[0].checked){
			timeliness = thisVal;
		}
	});
	params.id = thisId;
	params.name = $.trim(name);
	params.category = $.trim(categoryId);
	params.publishEnterprise = $.trim(publishEnterprise);
	params.publishTimeStr = $.trim(publishTime);
	params.executeTimeStr = $.trim(executeTime);
	params.referenceNumber = $.trim(referenceNumber);
	params.keyWords = $.trim(keyWords);
	params.fileName = $.trim(fileName);
	params.filePath = $.trim(filePath);
	params.fileSize = $.trim(fileSize);
	params.extendName = $.trim(extendName);
	params.timeliness = $.trim(timeliness);
	
	$('#addForm').isValid(function(v){
		var urlStr = "<%=request.getContextPath()%>/policiesRules/add.action";
		if(thisId){
			urlStr = "<%=request.getContextPath()%>/policiesRules/updateById.action";
		}
		if(v){
			$.ajax({
				type : "POST",
				url : urlStr,
				data : params,
				success : function(data) {
					if(data && data.rtnResult=="SUCCESS"){
						dialog.alert("保存成功!",function(){
							window.location.href = "<%=request.getContextPath()%>/policiesRules/toManageRulesList.action";
						});
					}else{
						dialog.alert("保存失败!");
					}
				}
			});
		}
	});
}


/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		timely : 2,
		theme : 'yellow_right_effect',
		msgStyle : 'margin-top:10px;margin-left:5px;',
		fields : {
			name : {
				rule : "required;length[~50]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于50个字符"
				}
				
			},
			categoryId : {
				rule : "required;",
				msg : {
					required : "不能为空"
				}
			},
			publishEnterprise : {
				rule : "required;length[~50]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于50个字符"
				}
			},
			publishTime : {
				rule : "required;",
				msg : {
					required : "不能为空",
				}
			},
			executeTime : {
				rule : "required;",
				msg : {
					required : "不能为空",
				}
			},
			referenceNumber : {
				rule : "required;length[~30]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			keyWords : {
				rule : "required;length[~30]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			filePath : {
				rule : "required;",
				msg : {
					required : "请上传文件",
				}
			}
		}
	});
}
</script>
</html>
