<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>选择图片</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- layer -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/layer/skin/layer.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js" ></script>
<!-- webuploader -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- jQuery UI -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/themes/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/js/jquery-ui-1.10.4.min.js" ></script>

<style type="text/css">

.need_span{
	color: red;
	display: none;
}
.need_0{
	color: red;
}
.need_1{
	color: #666666;
}
.input_0{
	width: 220px;
}
.form_table td{
	padding: 5px;
}
.text_ad_0{
	font-weight: bold;
	color: #444444;
	white-space: nowrap;
}

.span_btu{
	display: inline-block;
	background-color: #02B8EA;
	font-size: 12px;
	padding: 0px 5px;
	cursor: pointer;
	border-radius: 3px;
	min-width: 70px;
	line-height: 36px;
	text-align: center;
	color: #FFFFFF;
}

</style>

<script type="text/javascript">

$(function(){
	if(!WebUploader.Uploader.support()){
        alert('您的浏览器不支持上传插件！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error( 'WebUploader does not support the browser you are using.' );
    }
	
	
	var pagePicUploader = WebUploader.create({
		fileVal: "uploadFile",
		//自定义参数
		//formData: {fileType: type},
		// 选完文件后，是否自动上传。
	    auto: false,
	    // swf文件路径
	    swf: '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
	    // 文件接收服务端。
	    server: "<%=request.getContextPath()%>/file/uploadImg.action",
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
	    	id: '#picker',
	    	multiple: true
	    },
	    // 压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: true,
	    //单个文件上传大小限制  单位B
	    fileSingleSizeLimit:1024*1024,
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    },
		// 创建缩略图
		thumb :{
			width: 180,
		    height: 180,
		    // 图片质量，只有type为`image/jpeg`的时候才有效。
		    quality: 100,
		    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
		    allowMagnify: false,
		    // 是否允许裁剪。
		    crop: false,
		    // 为空的话则保留原有图片格式。
		    // 否则强制转换成指定的类型。
		    type: 'image/jpeg'
		}
	});
	
	//上传前验证
	pagePicUploader.on('error', function(type){
		//alert(type);
		if(type == "Q_EXCEED_NUM_LIMIT"){
			//$.ligerDialog.warn('只允许上传一个zip包');
		}else if(type == "Q_TYPE_DENIED"){
			layer.alert("只允许上传<span style='color:red;' >gif,jpg,jpeg,bmp,png</span>文件", {
			    icon: 0
			});	
		}else if(type == "F_EXCEED_SIZE"){
			layer.alert("文件最大<span style='color:red;' >1MB</span>", {
			    icon: 0
			});
		}
	});
	
	//当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
	pagePicUploader.on('beforeFileQueued', function(file) {
		//重置
		//pagePicUploader.reset();
	});
	
	// 当有文件被添加进队列的时候 监听
	pagePicUploader.on('fileQueued', function(file) {
		
		// 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    pagePicUploader.makeThumb(file, function(error, src) {
	    	//唯一标示
	        $("#hideDiv li").attr("fileId", file.id);
	    	//插入html
	        $(".select_pics ul").append($("#hideDiv").html());
	    	
	        if (error) {
	        	$(".select_pics").find("li[fileId='"+file.id+"']").find("#img_td").prepend("<div class='pic_no' >不能预览</div>");
	            return;
	        }
	        
	        //图片
	        $(".select_pics").find("li[fileId='"+file.id+"']").find("#img_td").prepend("<img class='pic' src='"+src+"' />").find(".del_div").show();
	    });
	});
	
	// 上传出错
	pagePicUploader.on('uploadError', function( file ) {
		$.ligerDialog.error('上传失败');
	});
	
	// 不管成功或者失败，文件上传完成时触发
	pagePicUploader.on('uploadComplete', function( file ) {
		
	});
	
	//当所有文件上传结束时触发
	pagePicUploader.on('uploadFinished', function( file ) {
		layer.closeAll();
		//关闭选择图片弹出框
		parent.layer.closeAll();
	});
	
	// 当文件上传成功时触发
	pagePicUploader.on('uploadSuccess', function(file, response) {

		//alert(JSON.stringify(response));
		var backData = response;
		if(typeof(response) === "string"){
			backData = jQuery.parseJSON(response);
		};
		
			parent.selectPicBack(backData.url);
	});
	
	// 某个文件开始上传前触发，一个文件只会触发一次
	pagePicUploader.on('startUpload', function( file ) {
		layer.msg('正在上传,请稍候...', {icon: 16, time: 0, shade: [0.8, '#393D49']});
	});
	
	//开始上传
	$("#start_upload").click(function(){
		
		pagePicUploader.upload();
	});
	
	//点击图片上的删除按钮
	$(".select_pics").on( "click", ".del_div_png", function(event) {

		var $li = $(this).parentsUntil('li').parent();
		var fileId = $li.attr("fileId");
		//alert(fileId);
		pagePicUploader.cancelFile(fileId);
		$li.remove();
		
	});
	
	//图集图片拖拽排序
	//$("#showUl" ).sortable();
});

</script>
<style type="text/css">

.select_pics ul{
	list-style: none;
	margin: 5px 5px;
}
.select_pics ul li{
	list-style: none;
	float: left;
	margin: 8px 10px;
	background-color: #FFF;
	cursor:move;
}
.select_pics table{
	border-collapse:collapse;
	padding:0;
	margin: 0;
}
.select_pics .pic_div{
	width: 180px;
	height: 180px;
	margin: 0px 0px;
}
.select_pics .pic_td_0{

}
.select_pics .pic_td_0 .pic{
	width: 180px;
	height: 180px;
}
.select_pics .pic_td_0 .pic_no{
	width: 180px;
	height: 180px;
	background-color: #CDCDCD;
	text-align: center;
	line-height:180px;
	font-weight:bold;
	font-size: 32px;
	letter-spacing: 8px;
	color: #FFF;
}
.select_pics .pic_td_1, .all_title .pic_td_1{
	font-weight: bold;
	color: #444444;
	white-space: nowrap;
	width: 45px;
}
.select_pics .input_1, .all_title .input_1{
	border: 1px solid #DADADA;
	width: 124px;
	height: 26px;
}
.all_title .input_1{
	width: 200px;
}

</style>
</head>
<body style="">

	<div id="adListDiv" style="" align="left">
		
		<div class="select_pics" style="height:400px;margin:0px 0px;overflow:auto;" >
			<ul id="showUl">
					
			</ul>
		</div>
	
		<div class="top_page_name_bottom" style="margin:0px;background-color:#E5E9EA;height:1px;" align="center"></div>
		<div align="center">
			<table style="margin-top:6px;">
				<tr align="center" valign="top"> 
					<td style="width:10px;" ></td>
					<td>
						<span class="" style="" id="picker" >选择图片</span>
					</td>
					<td style="width:10px;" ></td>
					<td>
						<span class="span_btu" style="" id="start_upload" >开始上传</span>
					</td>
					<td style="width:10px;" ></td>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="hideDiv" style="display:none;" >
		<li>
			<table class="pic_div" style="" cellspacing="0" cellpadding="0" >
				<tr align="left" valign="top">
					<td rowspan="3" class="pic_td_0" id="img_td" >
						<div class="del_div" style="z-index:2;display:none;margin-top:-183px;" align="right"  >
							<div style="background-color:#444;height:20px;opacity:0.4;filter:alpha(opacity=40);" ></div>
							<img class="del_div_png" title="删除图片" src="<%=request.getContextPath()%>/images/delete.png" style="width:16px;cursor:pointer;position:relative;top:-18px;right:2px;" />
						</div>
					</td>
				</tr>
			</table>
		</li>
	</div>
	
</body>
</html>
