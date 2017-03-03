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
</style>
<script>
var course ={};
var id ;
var selectCourseId;
var courseId;
course= ${course} 
//填充数据
function renderCourse(){
	if(course&&course.id){
		id=course.id;
		courseId=course.courseId;
		categoryId = course.categoryId;
		fillInfo();
		$('#title_span').text('修改课程');
	}else if (course&&course.selectCourseId){
		categoryId = course.categoryId;
		selectCourseId = course.selectCourseId;
		fillInfo();
	}else{
		course ={};
	}
}

function fillInfo(){
	$("#categoryName").val(course.categoryName);
	
	$("#name").val(course.name);
	$("#code").val(course.code);
	$("#price").val(course.price);
	$("#discount").val(course.discount);
	$("#cheapPrice").val(course.cheapPrice);
	$("#filePath").val(course.frontImage);
	$("#previewPath").attr("src",course.frontImage).css({
		"width" : "278px",
		"height" : "105px"
	});
	$("#descr").val(course.outline);
}

$(function() {
	upLoadImg();
	
	initValidate();
	
	renderCourse();
	$(".tempImg").click(function(){
		$("#filePath").val($(this).attr("src"));
		$("#previewPath").attr("src",$(this).attr("src"));
	})
	if(!course.frontImage){
		$("#filePath").val('<%=request.getContextPath()%>/images/course_temp1.jpg');
		$("#previewPath").attr("src",'<%=request.getContextPath()%>/images/course_temp1.jpg');
	}
});

/*
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
});*/
function selectPicBack(path){
	editor.insertHtml("<div align='center' style='margin:5px 0px 5px 0px;'><img src='"+path+"' data-ke-src='"+path+"' align='middle'/></div>");
}
var categoryId;
var categoryName;
var artDialog;
function chooseCategory(){
	artDialog = dialog({
        title: "选择课程分类",
        url:"<%=request.getContextPath()%>/mall/manage/toChooseCategory.action",
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}



function param(){
	var o = {};
	o.categoryId = categoryId;
	o.selectCourseId = selectCourseId;
	o.id=id;
	o.courseId = courseId;
	o.isCopy=course.isCopy;
	o.copyCourseId =course.copyCourseId;
	o.code = $("#code").val();
	o.name=$("#name").val();
	o.price = $("#price").val();
	o.cheapPrice = $("#cheapPrice").val();
	o.discount = $("#discount").val();
	o.outline = $("#descr").val();

	o.frontImage = $("#filePath").val();

	return o;
}

function next(){
	var url ="<%=request.getContextPath()%>/mall/manage/course/insert.action"
	if(id&&id!=''){
		url="<%=request.getContextPath()%>/mall/manage/course/update.action"
	}
	if(!categoryId){
		dialog.alert("请选择一个课程分类");
		return false;
	}
	$('#addForm').isValid(function(v) {
		if(v){
			$.ajax({
		 		type:"POST",
		 		async:true,  //默认true,异步
		 		data:param(),
		 		url:url,
		 		success:function(data){
		 			if(id){
		 				if(data != null&&data=="SUCCESS"){
		 					window.location.href="<%=request.getContextPath()%>/mall/manage/toSectionEditPage.action?mallCourseId="+id+"&courseId="+courseId+"&sectionCount=0";
		 				}else{
		 					dialog.alert("修改失败！");
		 				}
		 				
		 			}else{
		 				if(data != null&&data.id&&data.result=="SUCCESS"){
		 					id = data.id;
		 					courseId = data.courseId;
			 				window.location.href="<%=request.getContextPath()%>/mall/manage/toSectionEditPage.action?mallCourseId="+id+"&courseId="+courseId+"&sectionCount=0";
			 			}else{
			 				dialog.alert("新增失败！");
			 			}
		 			}
		 			
		 	    }
			 });
			}
	})
}
	
function cancel(){
	window.location.href="<%=request.getContextPath()%>/mall/manage/toCourseListPage.action";
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
	var url ="<%=request.getContextPath()%>/mall/manage/course/insert.action"
		if(id&&id!=''){
			url="<%=request.getContextPath()%>/mall/manage/course/update.action"
		}
	if(!categoryId){
		dialog.alert("请选择一个课程分类");
		return false;
	}
		$('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			 		type:"POST",
			 		async:true,  //默认true,异步
			 		data:param(),
			 		url:url,
			 		success:function(data){
			 			if(id){
			 				if(data != null&&data=="SUCCESS"){
			 					dialog.alert("修改成功",function(){
				 					cancel();
				 				})
			 				}else{
			 					dialog.alert("修改失败！");
			 				}
			 				
			 			}else{
			 				if(data != null&&data.id&&data.result=="SUCCESS"){
				 				
			 					courseId = data.id;
				 				dialog.alert("新增成功",function(){
				 					cancel();
				 				})
				 			}else{
				 				dialog.alert("新增失败！");
				 			}
			 			}
			 			
			 	    }
				 });
				}
		})
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
					data:{code:element.value,id:id,isCopy:course.isCopy,copyCourseId:course.copyCourseId},
					url:"<%=request.getContextPath()%>/mall/manage/course/checkCode.action",
					success:function(data){
						var flag = true;
						if(data.length > 0){
							
							if(data!="SUCCESS"){
								
									flag = false;
								
							}
							
							
						}
						str =  flag || "已存在相同编号";
					}
				});
				return str;
			},
			checkPrice : [ /^(0|0\.[1-9]|0\.[0-9][1-9]|[1-9]\d{0,6}(\.[0-9][1-9]?)?)$/, '请输入有效数字' ],
			checkDiscount : [ /^([1-9]\d?|0|100)$/, '请输入0-100的整数' ]
	
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
			},**/
			categoryName : {
				rule : "required",
				msg : {
					required : "课程分类不能为空"
				}
			},
			price : {
				rule : "required;checkPrice",
				msg : {
					required : "价格不能为空",
					checkPrice : "请输入小数点前7位，小数点后两位的小数"
				}
			},
			cheapPrice : {
				rule : "required;checkPrice",
				msg : {
					required : "优惠价格不能为空",
					checkPrice : "请输入小数点前7位，小数点后两位的小数"
				}
			},
			discount : {
				rule : "required;checkDiscount",
				msg : {
					required : "折扣不能为空",
					checkDiscount:"请输入0-100整数"
				}
			}
		}
	});
}

function priceChange(){
	var price = $("#price").val();
	var discount = $("#discount").val();
	if(price!=0&&!price){
		return;
	}
	if(!/^(0|0\.[1-9]|0\.[0-9][1-9]|[1-9]\d{0,6}(\.[0-9][1-9]?)?)$/.test(price)){
		return;
	}
	if(discount!=0&&!discount){
		discount= 100;
	}
	if(!/^([1-9]\d?|0|100)$/.test(discount)){
		return;
	}
	var cheapPrice = 0;
	cheapPrice = price*discount/100;
	$("#cheapPrice").val(cheapPrice.toFixed(2));
}
</script>


</head>
<body>
<div class="content">
	<!-- <h3>新增课程</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增课程</span>
	</div>
	<form id='addForm'>
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程分类：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="categoryName" name="categoryName" disabled/>
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
        </div>
        -->
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
                <em>课程价格：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="price"   onchange="priceChange()" name="price"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程折扣：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="discount"  onchange="priceChange()" name="discount"/>%
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程优惠价格：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="cheapPrice"  name="cheapPrice" disabled/>
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
       
       
     
        <div class="add_gr" style="padding-top:10px">
        	<div class="add_fl">
                <em>课程大纲：</em>
            </div>
            <div class="add_fr">
            	<textarea name="descr" id="descr"  style="width:700px;height:400px;"></textarea>
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
<script>
</script>
</body>
</html>
