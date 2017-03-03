<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>讲师管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>
<style type="text/css">
.add_gr{
margin-top: 7px;
	width: 100%; 
	/* height: 80px; */
	/* line-height: 80px; */
	float: left;
	/* width: 1066px; */
	height: 80px;
	line-height: 80px;
	float: left;
}
.add_fl{
	margin-top: 7px;
	/* width: 1066px; */
	height: 80px;
	line-height: 80px;
	float: left;
}
.add_fr{
	margin-top: 30px;
	margin-left: 16px;
		height: 80px;
	float: left;
	/* width: 880px; */
	line-height: 50px;

}
</style>

<script type="text/javascript">

var id=${USER_ID_LONG};

$(function(){
	upLoadImg();
});

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
		$("#iconPath").val(ret._raw);
		// 预览图片
		$("#previewPath").attr("src", ret._raw).css({
			"width" : "100px",
			"height" : "80px"
		});
	});
	
	uploader.on( 'uploadFinished', function( file ,ret ) {
		uploader.reset();
	})

}

function cancel(){
	window.parent.artDialog.close();
	//window.parent.$(".l-dialog,.l-window-mask").css("display","none");
}

function save(){
	var param = {};
	param.id=id;
	param.headPhoto = $("#iconPath").val();
	if(!param.headPhoto){
		dialog.alert("请选择照片。");
	}
	$.ajax({
 		type:"POST",
 		async:true,  //默认true,异步
 		data:param,
 		url:"<%=request.getContextPath()%>/manageUser/uploadPhoto.action",
 		success:function(data){
 			if(data == 'SUCCESS'){
 				dialog.alert("头像上传成功！",function(){
 					window.parent.parent.$("#headPhoto").attr("src",$("#iconPath").val());
 					window.parent.$("#headPhoto").attr("src",$("#iconPath").val());
	 				cancel();
 				});
 			}else{
 				dialog.alert("头像上传失败！");
 			}
 	    }
	 });
}


</script>
</head>
<body style="overflow-x:hidden;">
<div  class='dialog-content'>	
	    <div class="add_gr">
        	<div class="add_fl">
                <em>选择头像：</em>
            </div>
             <div class="add_fr" >
            	<div id="uploader" class="wu-example">
			    <!--用来存放文件信息-->
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择照片</div>
				    </div>
				</div>
				<img id="previewPath" src="${USER_BEAN.headPhoto }" style="margin-top:10px;" width="100px" height="80px"/>
            </div>
            <input id='iconPath'name="iconPath" class='text-p' type="hidden" >
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                &nbsp;
            </div>
             <div class="add_fr" >
            	<p class="tp">
						
					</p>
            </div>
    	</div>
    	<div class='tit-err' style='height:20px;width:100%'>
    	</div>
    	 <div class="button_cz" style='padding: 0px;margin-left: 227px;'>
        	<input type="button" value="上传" id='save' onclick="save()">
            <input type="button" value="返回" class="back" onclick='cancel()'>
        </div>
</div>	
	
</body>
</html>
