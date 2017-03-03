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
</style>

<script type="text/javascript">


$(function(){
	
	var uploader = WebUploader.create({
		auto: false,
	    // swf文件路径
	    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

	    // 文件接收服务端。
	    server:'<%=request.getContextPath()%>/manageUser/importXlsFileData.action',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',

	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,

	    // 只允许选择xls、xlxs文件。
	    accept: {
	        title: 'Excel',
	        extensions: 'xls,xlsx',
	        mimeTypes: 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
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
		if(ret.errCode == '上传成功'){
			dialog.alert("上传成功",function(){
				window.parent.search();
				window.parent.artDialog.close().remove();
			});
		}else{
			$("div.tit-err").html("<span class='tit' style='color:red'>"+ret.errCode+"</span>");
			$("#picker").find("div.teacher-item").remove();
		}
		 
	})
	uploader.on("error",function(err){
		 if (type == 'Q_EXCEED_NUM_LIMIT') {
	            dialog.alert('上传文件个数超过限制');
	            return;
	        }
	        if (type == 'F_EXCEED_SIZE') {
	            dialog.alert('上传文件过大');
	            return;
	        }
	        if (type == 'Q_TYPE_DENIED') {
	            dialog.alert('上传文件类型不匹配');
	            return;
	        }
	})
	
	uploader.on( 'uploadProgress', function( file, percentage ) {
		$("div.tit-err").html("<span class='tit' style='color:red'>正在上传</span>");
		$("#picker").find("div.teacher-item").remove();
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
</script>
</head>
<body style="overflow-x:hidden;">
<div  class='dialog-content'>	
	    <div class="add_gr">
        	<div class="add_fl">
                <em>导入用户：</em>
            </div>
             <div class="add_fr" >
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
    	<div class='tit-err' style='height:70px;width:100%;overflow-y:auto;float:left;'>
    	</div>
    	 <div class="button_cz" style='padding: 0px;margin-left: 227px;'>
        	<input type="button" value="导入" id='ctlBtn' >
            <input type="button" value="返回" class="back" onclick='javascript:parent.location = "<%=request.getContextPath()%>/manageUser/toStudentListPage.action"'>
        </div>
</div>	
	
</body>
</html>
