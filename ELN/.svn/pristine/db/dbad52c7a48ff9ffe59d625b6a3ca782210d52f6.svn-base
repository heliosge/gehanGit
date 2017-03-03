<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增课程</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="ctext/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<jsp:include page="../common/includeKindeditor.jsp"></jsp:include>
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
	.ztree {width: 250px;min-height: 300px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
</style>
<script>

$(function() {
	upLoadImg();
	
	initDeptTree();
	
	initValidate();
	
	$("#tags").tags({inputName: "tags" });
	
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
});

/* 初始化部门tree */
function initDeptTree(){
	var setting = {
			data: {key: {url: "xUrl"},simpleData: {enable: true, pIdKey: "parentId" }},
			check: {enable:  true,chkboxType: { "Y": "s", "N": "" }},
			view: {
				showLine: false,
				showIcon: true
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			$.fn.zTree.init($("#treePage"), setting, data.data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

var editor;
KindEditor.ready(function(K){
	var langType ="zh_CN";
	editor = K.create("textarea[name=content]",{
		width: "80%",
		height: "430px",
		pasteType: 2,
		langType : langType,
		uploadJson : "<%=request.getContextPath()%>/file/uploadImg.action?path=CommonImage&DB=false",
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
function selectPicBack(path){
	editor.insertHtml("<div align='center' style='margin:5px 0px 5px 0px;'><img src='"+path+"' data-ke-src='"+path+"' align='middle'/></div>");
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

var deptIds = [];
function chooseDept(){
	dialog({
		width : 300,
		height : 350,
		title : '请选择部门',
		content : $("#deptDialog"),
	 	button: [
          {
          value: '确定',
          callback: function () {
        	  $("#dept").html('');
        	  deptIds = [];
        	  $.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(true), function(index, val){
        		  if(val.id.indexOf('com') == -1){
        			  deptIds.push(val.id);
            		  $("#dept").append("部门："+val.name+"; ");
        		  }
      		  });
        	  this.close();
          }
          }
		      ]
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
		width: 800,
		focus: true
	}).showModal();
}

function clearInfo(){
	postIds = [];
	groupIds = [];
	$("#post").html("");
	$("#group").html("");
}

var id = '';

function param(){
	var o = {};
	o.categoryId = categoryId;
	//o.code = $("#code").val();
	o.name=$("#name").val();
	o.learnTime = $("#learnTime").val();
	o.learnScore = $("#learnScore").val();
	o.tags = $("input[name='tags']").val();
	o.teacherId = teacherId;
	o.outline = editor.html();
	o.postIds = postIds;
	o.groupIds = groupIds;
	o.deptIds = deptIds;
	o.frontImage = $("#filePath").val();
	$.each($("input[name='type']"), function(index, val){
		if(val.checked){
			o.type = $(val).val();
		}
	});
	return o;
}

function next(){
	$('#addForm').isValid(function(v) {
		if(v){
			$.ajax({
		 		type:"POST",
		 		async:true,  //默认true,异步
		 		data:param(),
		 		url:"<%=request.getContextPath()%>/res/insertResCourse.action",
		 		success:function(data){
		 			if(data != null){
		 				id = data;
		 				window.location.href="<%=request.getContextPath()%>/res/toUpdateResSectionPage.action?courseId="+id+"&sectionCount=0";
		 			}else{
		 				dialog.alert("新增失败！");
		 			}
		 	    }
			 });
			}
	})
}
	
function cancel(){
	window.location.href="<%=request.getContextPath()%>/res/toResCourseListPage.action";
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


function save() {
	$('#addForm').isValid(function(v) {
		if(v){
			if(id != ''){
				return;
			}
			var paramData = param();
			if(paramData.categoryId == undefined){
				dialog.alert("课程体系不能为空！");
				return
			}
			$.ajax({
		 		type:"POST",
		 		async:false,  //默认true,异步
		 		data:paramData,
		 		url:"<%=request.getContextPath()%>/res/insertResCourse.action",
		 		success:function(data){
		 			if(data != null){
		 				id = data;
		 				dialog.alert("操作成功！",function(){cancel();});
		 			}else{
		 				dialog.alert("操作失败！");
		 			}
		 	    }
			 });
		}else{
			//dialog.alert("表单验证不通过");
		}
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
						if(data.length > 0){
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
			/* code : {
				rule : "required;length[~30];",
				msg : {
					required : "编号不能为空",
					length : "长度需小于等于30个字符"
				}
			}, */
			categoryName : {
				rule : "required",
				msg : {
					required : "课程体系不能为空"
				}
			},
			learnTime : {
				rule : "required;integer[+]",
				msg : {
					required : "学时不能为空",
					checknum : "学时必须是数字"
				}
			},
			learnScore : {
				rule : "required;integer[+]",
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
	<!-- <h3>新增课程</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增课程</span>
	 </div>
	<form id='addForm'>
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程体系：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="categoryName" name="categoryName" disabled/>
                <input type="button" value="选择" class="xz" onclick="chooseCategory()"/>
            </div>
        </div>
       <!--  <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程编号：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="code" name="code"/>
            </div>
        </div> -->
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
                <input type="radio"  name="type" value="2"/>
                <span>线下课程</span>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程学时：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="learnTime"  name="learnTime"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程学分：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="learnScore"  name="learnScore"/>
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
        <div class="add_gr">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<em>建议图片大小为（宽*高）：250*170</em>
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
            	<input type="button" value="选择部门" class="gw" onclick="chooseDept()"/>
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
            	<span id="dept"></span>
            	<span id="post"></span>
            	<span id="group"></span>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>课程讲师：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="teacherName" disabled name="teacherName"/>
                <input type="button" value="选择讲师" class="te" onclick="chooseTeacher()"/>
            </div>
        </div>
        <div class="add_gr_1">
        	<div class="add_fl">
                <em>课程大纲：</em>
            </div>
            <div class="add_fr">
            	<textarea name="content" id="content"  name="content"></textarea>
            </div>
        </div>
        <div class="button_cz">
        	<input type="button" value="保存"  onclick="save()"/>
            <input type="button" value="下一步"  onclick="next()" id="nextButton"/>
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
    </div>
    </form>
</div>

 <div id="deptDialog" style="display: none;overflow: auto;height: 350px;">
	     <ul id="treePage" class="ztree" style=""></ul>
 </div>
</body>
</html>
