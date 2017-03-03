<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增课件</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/courseTags.css"/>

<style type="text/css">
#picker .webuploader-pick {
    padding: 6px 12px;
    display: block;
}
.webuploader-pick{
	background: red;
	width:300px;
	height:30px;
	text-align:center;
}
label.error{
	color: red;
}

.loading-tit{
    height: 50px;
    line-height: 50px;
    font-size: 24px;
    margin: 5px;
}
.file-name{
    width: 250px;
    overflow: hidden;
    display: inline-flex;;
    text-overflow: ellipsis;
    white-space: nowrap;
}
li.progress{
    line-height: 30px;
    font-size: 15px;
}
.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>
</head>



<body>
<script type="text/javascript">

var coursewareType;
$(function(){
	
	$("#tags").tags({inputName: "tags" });
	
	initValidate();
	
	initJqMeter();
	var mimeType = "application/zip,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.presentationml.presentation,application/vnd.ms-powerpoint,text/plain,application/pdf,video/*,application/octet-stream";
    var uploader = WebUploader.create({
		auto : true,
		// swf文件路径
		swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端。
		 server:'<%=request.getContextPath()%>/res/uploadFile.action?path=course%2FuploadKL',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : {
			id : '#picker',
			multiple : false
		},
		accept: {
            title: 'Videos',
            extensions: 'mp4,flv,zip,doc,docx,xls,xlsx,ppt,pptx,txt,pdf',
            mimeTypes: mimeType
        },
		resize : false
	});
    
    //var $index;
    uploader.on( 'uploadFinished', function( file ,ret ) {
        //上传完成，去掉蒙层
        layer.closeAll('loading');
        uploader.reset();
    });
    uploader.on('uploadStart', function(file) {
        //开始上传，进行蒙层处理
        layer.load(1, {
            shade: [0.5,'#fff'] //0.1透明度的白色背景
        });
    });
   	// 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
    });
	
	uploader.on('fileQueued', function(file) {
	});
	
	// 接受文件后，进行赋值操作
	uploader.on('uploadAccept', function(file, ret) {
		if(ret.result == 'OVER_CAPACITY'){
			dialog.alert("资源容量已用完，请联系管理员。");
		}else{
			coursewareType = ret.coursewareType;
			$("#filePath").val(ret.path);
			$("#fileSize").val(ret.fileSize);
			$("#name").val(ret.fileName);
			catalog = JSON.stringify(ret.catalog);
		}
	});
	
	//上传文件错误提示
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
	
});
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
var catalog;
function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = {};
			param.type = coursewareType;
			param.name = $("#name").val();
			if(coursewareType == 1){
				param.fileName = catalog;
			}else{
				param.fileName = $("#name").val();
			}
			param.durationString = $("#durationString").val();
			param.filePath = $("#filePath").val();
			param.fileSize = $("#fileSize").val();
			param.tags = $("input[name='tags']").val();
			param.descr = $("#descr").val();
			$.ajax({
		 		type:"POST",
		 		async:true,  //默认true,异步
		 		data:param,
		 		url:"<%=request.getContextPath()%>/res/insertResCourseware.action",
		 		success:function(data){
		 			if(data == 'SUCCESS'){
		 				dialog.alert("新增成功！",function(){cancel();});
		 				//cancel();
		 			}else{
		 				dialog.alert("新增失败！");
		 			}
		 	    }
			 });
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
}

//清除文件信息
function cancelFile(_this,id){
	$(_this).closest("div.teacher-item").remove();
	$("#filePath").val("");
	$("#fileSize").val("");
}

function cancel(){
	window.location.href="toResCoursewareListPage.action";
}
/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
			checkDuration : [/^\d{0,}:[0-5][0-9]:[0-5][0-9]$/,'请输入正常时长格式']
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
			filePath : {
				rule : "required",
				msg : {
					required : "课件文件不能为空"
				}
			},
			descr : {
				rule : "length[~2000]",
				msg : {
					length : "长度需小于等于2000个字符"
				}
			},
			durationString : {
				rule : "checkDuration",
				msgClass:"n-top",
				msgStyle:"left:-30px;"
			}
		}
	});
}

</script>

<div class="content">
	<!-- <h3>新增课件</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增课件</span>
	 </div>
    <form id='addForm'>
    <div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>课件上传：</em>
            </div>
             <div class="add_fr" >
				<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div class="btns">
							<div id="picker">选择文件</div>
						</div>
					</div>
            </div>
            <input id="filePath" type="hidden" name="filePath">
            <input id="fileSize" type="hidden" name="fileSize">
    	</div>
    	<div class="add_gr" style="height:100px;">
        	<div class="add_fl">
                <em>&nbsp;</em>
            </div>
             <div class="add_fr" >
		            <p style="padding-bottom:0;color: #999;height:30px;">标准课件：zip</p>
		            <p style="padding-bottom:0;color: #999;height:30px;">普通课件：doc,docx,xls,xlsx,ppt,pptx,txt,pdf</p>
		            <p style="padding-bottom:0;color: #999;height:30px;">视频课件：mp4,flv</p>
            </div>
    	</div>
        
        <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>课件名称：</em>
            </div>
             <div class="add_fr" >
            	<input type="text" id="name" name="name"/>
            </div>
    	</div>
    	
    	   <div class="add_gr">
        	<div class="add_fl">
                <em>课件时长：</em>
            </div>
             <div class="add_fr" >
            	<input type="text" id="durationString" name="durationString"/>
            	<em>温馨提示：视频课件才需要填写，时长格式是“XX:XX:XX”。</em>
            </div>
    	</div>
    	
    	<div class="add_gr">
        	<div class="add_fl">
                <em>课件标签：</em>
            </div>
            <div class="add_fr" style="line-height: 30px;">
             	<div id="tags" class="tags-box">
       			 </div>
       			 <em>温馨提示：最多输入5个标签，多个标签之间请用“空格”或“，”隔开</em>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>课件简介：</em>
            </div>
             <div class="add_fr" >
            	<textarea id="descr" name="descr"></textarea>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
        	&nbsp;
            </div>
             <div class="add_fr" >
            	 <input type="button" value="保存" class="btn_s" onclick="save()"/>
        		<input type="button" value="取消" class="btn_n" onclick="cancel()"/>
            </div>
    	</div>
       
     </div>
    </form>
</div>

<ul class="layer_notice" style="display:none;height:100px;width:350px">
	    <!-- <li class="loading-tit">文件上传中...</li> -->
	    <li class="progress"><label>文件名称:</label><label class="file-name"></label></li>
	    <li class="progress">
	    	<div id="jqMeter_div"></div>
	    </li>
    </ul>
    
</body>
</html>
