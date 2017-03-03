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

<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
h4.info{
	width: 82%;
	overflow: hidden;
	enable-background: blink;
	text-overflow: ellipsis;
	white-space: nowrap;
}
.tit-err{
	height: 80px;
	width: 100%;
	float: left;
	overflow: auto;
}
</style>

<script type="text/javascript">


$(function(){
	var mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel";
	var uploader = WebUploader.create({
		auto: false,
	    // swf文件路径
	    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

	    // 文件接收服务端。
	    server:'<%=request.getContextPath()%>/teacher/importXlsFileData.action',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',

	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,

	    // 只允许选择xls、xlxs文件。
	    accept: {
	        title: 'Images',
	        extensions: 'xls,xlsx',
	        mimeTypes: mimeType
	    }
	});
	
	uploader.on( 'fileQueued', function( file ) {
		iconfile = file;
	    $("#picker").append( '<div id="' + file.id + '" class="teacher-item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<a class="close" href="javascript:void(0)" onclick="cancelFile(this,'+file.id+')">X</a>'+
	    '</div>' );
	    $("div.dialog-content").find("span.tit").remove();
	});
	
	uploader.on("uploadAccept",function(file,ret){
		if(ret.errCode=="00"){
			$("div.tit-err").html("<span class='tit' style='color:red'>处理成功！</span>")
		}else if(ret.errCode=="10"){
			$("div.tit-err").html("<span class='tit' style='color:red'>表格超过处理最大行数！</span>")
		}else if(ret.errCode=="-1"){
			$("div.tit-err").html("<span class='tit' style='color:red'>文件异常，处理失败！</span>")
		}else{
			$("div.tit-err").html("<div>部分失败,详情如下</div><span class='tit' style='color:red'>"+ret.errCode+"</span>")
		}
		 $("#picker").find("div.teacher-item").remove();
		 
	})
  uploader.on('error', function(type) {
        if (type == 'Q_EXCEED_NUM_LIMIT') {
            dialog.alert('上传文件个数超过限制');
            return;
        }
        if (type == 'F_EXCEED_SIZE') {
            dialog.alert('上传文件过大');
            return;
        }
        if (type == 'Q_TYPE_DENIED') {
            dialog.alert('上传文件类型不匹配或文件大小为空');
            return;
        }
    });
	
	uploader.on( 'uploadFinished', function( file ,ret ) {
		uploader.reset();
	})
	$("#ctlBtn").click(function(){
		if(uploader.getFiles().length>0&&uploader.getStats().successNum==0){
			uploader.upload()
			uploader.getStats().successNum 
		}else{
			$("div.tit-err").html("<span class='tit' style='color:red'>请选择文件</span>")
		}
	})
	
	
});

//清除文件信息
function cancelFile(_this,id){
	$(_this).closest("div.teacher-item").remove();
	//uploader.cancelFile( iconfile );
	$("#iconPath").val("");
}


<%-- //新增讲师
function addTeacher(){
	
	location = "<%=request.getContextPath()%>/teacher/add.action";
} --%>
function addTeacherInfo(){
	
	var rows = 	$('#exampleTable').mmGrid().selectedRows();
	if(rows.length==0){
		artdialog.alert("请选择数据！")
		return;
	}
	
	window.parent.addTeacherInfo(rows[0].id);
	window.parent.$.ligerDialog.close(); //关闭弹出窗;  
	window.parent.$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
}
</script>
</head>
<body style="overflow-x:hidden;">
<input type="hidden" id='teacherId' name='teacherId'>
<div  class='dialog-content'>	
	    <div class="add_gr">
        	<div class="add_fl">
                <em>导入讲师：</em>
            </div>
             <div class="add_fr" >
            	<!-- <input type="button" value="选择头像" id='picker' >
            	 用来存放文件信息
    			<div id="thelist" class="uploader-list"></div>
            	<input id='iconPath'name="iconPath" class='text-p' type="file" style='display:none'> -->
            	<div id="uploader" class="wu-example">
			    <!--用来存放文件信息-->
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择文件</div>
				    </div>
				</div>
            </div>
            <input id='iconPath'name="iconPath" class='text-p' type="hidden" >
    	</div>
    	<div class='tit-err' >
    	</div>
    	 <div class="button_cz" style='padding: 0px;margin-left: 227px;'>
        	<input type="button" value="导入" id='ctlBtn' >
            <input type="button" value="返回" class="back" onclick='javascript:parent.location = "<%=request.getContextPath()%>/teacher/list.action"'>
        </div>
</div>	
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
