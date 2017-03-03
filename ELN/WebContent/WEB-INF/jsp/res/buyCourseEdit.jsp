<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增课程</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--富文本  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/lang/en.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/courseTags.css"/>
<style type="text/css">
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.tempImg{ cursor: pointer; }
</style>
<script>

var course = ${course};

var editor;
KindEditor.ready(function(K){
	var langType ="zh_CN";
	editor = K.create("textarea[name=content]",{
		width: "80%",
		height: "430px",
		pasteType: 2,
		langType : langType,
		uploadJson : "<%=request.getContextPath()%>/file/uploadImg.action?path=CommonImage&DB=false",
		fileManagerJson : "<%=request.getContextPath()%>/file/filemanageJson.action",
		allowFileManager : false,
		filePostName:"uploadFile",
		items: [
		        'source', '|','undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
		        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
		        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
		        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
		        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
		        'flash', 'media','insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
		        'anchor', 'link', 'unlink', '|', 'about','anchor'
			]
		
	});
	//prettyPrint();
});

$(function(){
	fillCourseInfo();
	upLoadImg();
	initValidate();
	
	$("input[name='type']").click(function(){
		if($(this).val() == 1){
			$("#nextButton").show();
		}else{
			$("#nextButton").hide();
		}
	});
	$(".tempImg").click(function(){
		$("#filePath").val($(this).attr("src"));
		$("#previewPath").attr("src",$(this).attr("src"));
	})
	if(!course.frontImage){
		$("#filePath").val('<%=request.getContextPath()%>/images/course_temp1.jpg');
		$("#previewPath").attr("src",'<%=request.getContextPath()%>/images/course_temp1.jpg');
	}
})

function fillCourseInfo(){
	categoryId=course.categoryId;
	$("#previewPath").attr("src",course.frontImage);
	$("#filePath").val(course.frontImage);
	$("#categoryName").val(course.categoryName);
	$("#code").val(course.code);
	$("#name").val(course.name);
	$("#learnTime").val(course.learnTime);
	$("#learnScore").val(course.learnScore);
	$("#tags").tags({
		valueString: course.tags,
        inputCls: "noborder",
        inputName: "tags" });
	$("#teacherId").val(course.teacherName);
	$("#content").html(course.outline);
	teacherId = course.teacherId;
	var postName = '';
	var groupName = '';
	for(var i = 0; i < course.postList.length; i++){
		postName += "岗位："+course.postList[i].name+"； ";
		postIds.push(course.postList[i].id);
	}
	for(var i = 0; i < course.groupList.length; i++){
		groupName += "群组："+course.groupList[i].name+"； ";
		groupIds.push(course.groupList[i].id);
	}
	$("#post").append(postName);
	$("#group").append(groupName);
	if(course.type == 1){
		$("#nextButton").show();
	}else{
		$("#nextButton").hide();
	}
	$.each($("input[name='type']"), function(index, val){
		if($(val).val() == course.type){
			val.checked = true;
		}
	});
}

var categoryId;
var categoryName;
var artDialog;
function chooseCategory(){
	artDialog = dialog({
        title: "选择课程体系",
        url:"<%=request.getContextPath()%>/res/toChooseCategory.action",
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}

var postIds = [];
function choosePost(){
	artDialog = dialog({
        title: "选择岗位",
        url:"<%=request.getContextPath()%>/res/toChoosePost.action",
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}

var groupIds = [];
function chooseGroup(){
	artDialog = dialog({
        title: "选择群组" ,
        url:"<%=request.getContextPath()%>/res/toChooseGroup.action",
        lock:true,
        height: 500,
		width: 800
	}).showModal();
}

var teacherId;
function chooseTeacher(){
	artDialog = dialog({
		 url:"<%=request.getContextPath()%>/res/toChooseTeacher.action",
	        title:"选择讲师" ,
       lock:true,
       height: 400,
		width: 800
	}).showModal();
}

function clearInfo(){
	postIds = [];
	groupIds = [];
	$("#post").html("");
	$("#group").html("");
}


var id;
function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var paramData = param();
			if(paramData.categoryId == undefined){
				dialog.alert("课程体系不能为空！");
			}
			$.ajax({
		 		type:"POST",
		 		async:true,  //默认true,异步
		 		data:paramData,
		 		url:"<%=request.getContextPath()%>/res/updateResCourse.action",
		 		success:function(data){
		 			if(data=='SUCCESS'){
		 				//cancel();
		 				//dialog.alert("修改成功！");
		 				dialog.alert("修改成功。",function(){cancel();});
		 			}else{
		 				dialog.alert("修改失败！");
		 			}
		 	    }
			 });
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
	
}

function param(){
	var o = {};
	o.id = course.id;
	o.categoryId = categoryId;
	o.code = $("#code").val();
	o.name=$("#name").val();
	o.learnTime = $("#learnTime").val();
	o.learnScore = $("#learnScore").val();
	o.tags = $("input[name='tags']").val();
	o.teacherId = teacherId;
	o.outline = editor.html();
	o.postIds = postIds;
	o.groupIds = groupIds;
	o.frontImage = $("#filePath").val();
	$.each($("input[name='type']"), function(index, val){
		if(val.checked){
			o.type = $(val).val();
		}
	});
	o.mallStatus = 4;
	return o;
}




function cancel(){
	window.location.href="<%=request.getContextPath()%>/res/toBuyResCourseListPage.action";
	}
	
function upLoadImg() {
	var uploader = WebUploader
			.create({
				auto : true,
				// swf文件路径
				swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

				// 文件接收服务端。
				server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',

				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick : {
					id : '#picker',
					multiple : false
				},

				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
				resize : false,

				// 只允许选择图片文件。
				accept : {
					title : 'Images',
					extensions : 'gif,jpg,jpeg,bmp,png',
					mimeTypes : 'image/*'
				}
			});

	uploader.on('fileQueued', function(file) {
	});

	// 接受文件后，进行赋值操作
	uploader.on('uploadAccept', function(file, ret) {
		$("#filePath").val(ret._raw);
		// 预览图片
		$("#previewPath").attr("src", ret._raw).css({
			"width" : "124px",
			"height" : "86px"
		});
	});

}

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkCode:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{code:element.value},
					url:"<%=request.getContextPath()%>/res/selectResCourseListByCode.action",
					success:function(data){
						var flag = true;
						if(data.length > 0 && data[0].id != course.id){
							flag = false;
						}
						str =  flag || "已存在相同编号";
					}
				});
				return str;
			},
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		msgStyle:"margin-top:10px;margin-left:10px;",
		fields : {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			/**
			code : {
				rule : "required;length[~30];checkCode",
				msg : {
					required : "编号不能为空",
					length : "长度需小于等于30个字符"
				}
			}, **/
			categoryName : {
				rule : "required",
				msg : {
					required : "课程体系不能为空",
					checknum : "学时必须是数字"
				}
			},
			learnTime : {
				rule : "required;checknum",
				msg : {
					required : "学时不能为空",
					checknum : "学时必须是数字"
				}
			},
			learnScore : {
				rule : "required;checknum",
				msg : {
					required : "学分不能为空"
				}
			}
		}
	});
}
</script>


</head>
<body>
<div class="content">
	<!-- <h3>修改课程</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改课程</span>
	 </div>
	<form id="addForm">
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程体系：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="categoryName" name="categoryName"  disabled/>
                <input type="button" value="选择" class="xz" onclick="chooseCategory()"/>
            </div>
        </div>
        <!--  
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程编号：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="code" name="code"/>
            </div>
        </div>-->
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程名称：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="name" name="name"/>
            </div>
    	</div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程类型：</em>
            </div>
             <div class="add_fr">
            	<input type="radio" checked="checked" name="type" value="1"/>
                <span>线上课程</span>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程学时：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="learnTime" name="learnTime"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程学分：</em>
            </div>
             <div class="add_fr">
            	<input type="text"  id="learnScore" name="learnScore"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>课程标签：</em>
            </div>
             <div class="add_fr" style="line-height: 30px;">
             	<div id="tags" class="tags-box">
       			 </div>
       			  <em>最多输入5个标签，多个标签之间请用空格或","隔开</em>
            </div>
    	</div>
    	<div class="add_gr" style="height:auto;">
        	<div class="add_fl">
                <em>模板图片：</em>
            </div>
             <div class="add_fr">
            	<img class="tempImg" src="<%=request.getContextPath()%>/images/course_temp1.jpg" width="124px" height="86px"/>
            	<img class="tempImg" src="<%=request.getContextPath()%>/images/course_temp2.jpg" width="124px" height="86px"/>
            	<img class="tempImg" src="<%=request.getContextPath()%>/images/course_temp3.jpg" width="124px" height="86px"/>
            	<img class="tempImg" src="<%=request.getContextPath()%>/images/course_temp4.jpg" width="124px" height="86px"/>
            </div>
    	</div>
    	<div style="height:40px;width: 1066px;float: left;padding-top:10px;">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
                <em>封面图片：</em>
            </div>
            <div style="height:40px;margin-left: 16px;float: left;width: 880px;">
            	<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择图片</div>
						</div>
						<input id="filePath" type="hidden">
				</div>
            </div>
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img id="previewPath" src="<%=request.getContextPath()%>/images/course_temp1.jpg" width="124px" height="86px"/>
					</p>
            </div>
        </div>
    	
        <div class="add_gr">
        	<div class="add_fl">
                <em>开放人群：</em>
            </div>
            <div class="add_fr">
                <input type="button" value="选择岗位" class="gw" onclick="choosePost()"/>
                <input type="button" value="选择群组" class="st" onclick="chooseGroup()"/>
                <input type="button" value="清除选择" onclick="clearInfo()"/>
                <em>温馨提示：如果不选择，默认对所有人开放</em>
            </div>
        </div>
          <div class="add_gr">
        	<div class="add_fl">
            	&nbsp;
            </div>
            <div class="add_fr">
            	<span id="post"></span>
            	<span id="group"></span>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>课程讲师：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="teacherName" name="teacherName"/>
                <input type="button" value="选择讲师" class="te" onclick="chooseTeacher()"/>
            </div>
        </div>
        <div class="add_gr_1">
        	<div class="add_fl">
                <em>课程大纲：</em>
            </div>
            <div class="add_fr">
            	<textarea name="content" id="content"></textarea>
            </div>
        </div>
        <div class="button_cz">
        	<input type="button" value="保存"  onclick="save()"/>
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
    </div>
    </form>
</div>
<script>
</script>
</body>
</html>
