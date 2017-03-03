<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<title>Insert title here</title>

<style type="text/css">
.webuploader-pick{
	padding:0;
	line-height: 35px;
    position: relative;
    top: 10px;
}
/*验证插件矫正小箭头位置*/
.lesson_add .add_gr .add_fr span{margin-left: 0px;}
.lesson_add .add_gr{height: auto;}
</style>
</head>
<body>

<div class="content">
	<c:if test="${type==1}">
      <!--  <h3>新增微电影</h3> -->
	      <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">
			<c:if test="${id==null}">新增</c:if><c:if test="${id!=null}">修改</c:if>微电影</span>
		</div>
   	</c:if>
   	<c:if test="${type==2}">
		<!-- <h3>新增宣传片</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">
			<c:if test="${id==null}">新增</c:if><c:if test="${id!=null}">修改</c:if>宣教片</span>
		</div>
	</c:if>
	<c:if test="${type==3}">
		<!-- <h3>新增教育片</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">
			<c:if test="${id==null}">新增</c:if><c:if test="${id!=null}">修改</c:if>教育片</span>
		</div>
	</c:if>
	<form id="addForm">
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<c:if test="${type==1}">
	                <em>微电影标题：</em>
            	</c:if>
            	<c:if test="${type==2}">
	                <em>宣传片标题：</em>
            	</c:if>
            	<c:if test="${type==3}">
	                <em>教育片标题：</em>
            	</c:if>
            </div>
            <div class="add_fr">
            	<input type="text" id="name" name="name"/>
            </div>
        </div>
        
        <div class="add_gr" style="margin-top:30px;">
        	<div class="add_fl">
            	<span>*</span>
               <c:if test="${type==1}">
	                <em>微电影视频：</em>
            	</c:if>
            	<c:if test="${type==2}">
	                <em>宣传片视频：</em>
            	</c:if>
            	<c:if test="${type==3}">
	                <em>教育片视频：</em>
            	</c:if>
            </div>
             <div class="add_fr">
            	<input type="text"  readonly="readonly" id="fileName"/>
				<input id="filePath"  name="filePath" type="hidden"/>
                <div id="uploader" class="wu-example">
					<!--用来存放文件信息-->
					<div id="thelist" class="uploader-list"></div>
					<div class="btns">
						<div id="contentPicker" style="padding: 0px;">选择视频</div>
						<p style="color: #999;padding-bottom: 0;">可选的文件格式为：*.flv,*.mp4</p>
					</div>
	            	<input id="fileSize" type="hidden"/>
				</div>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>描述：</em>
            </div>
            <div class="add_fr">
            	<textarea style="overflow:scroll; height:100px;" id="descr" name="descr"></textarea>
            </div>
        </div>
    </div>
    </form>
        <div class="button_cz">
        	<input type="button" value="保存" onclick="save()"/>
            <input type="button" value="返回" class="back" onclick="history.go(-1);"/>
        </div>
    </div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<script type="text/javascript">
var thisId = '${id}';//当前不为null时 为修改
var type = '${type}';//1 微电影  2 宣传片  3 教育片
$(function(){
	if(thisId){//修改时先初始化数据
		initData(thisId);
	}
	initValidate();
	uploadFile();
});

function initData(id){
	var urlStr = "<%=request.getContextPath()%>/safetyEducation/getById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"id":id},
		success : function(data) {
			if(data && data.rtnResult=="SUCCESS"){
				var dataBean = data.rtnData;
				var name =  dataBean.title;
				var descr =  dataBean.descr;
				var fileName =  dataBean.fileName;
				var filePath = dataBean.filePath;
				var fileSize = dataBean.fileSize;
				$("#name").val(name);
				$("#descr").val(descr);
				$("#fileName").val(fileName);
				$("#filePath").val(filePath);
				$("#fileSize").val(fileSize);
			}
		}
	});
}
function uploadFile(){
	var mimeType = "video/x-flv,video/mp4";
	var uploader = WebUploader.create({
		auto : true,
		// swf文件路径
		swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

		// 文件接收服务端。
		server : '<%=request.getContextPath()%>/res/uploadFile.action?path=safetyAdd%2FuploadEducation',

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
			extensions : 'flv,mp4',
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
	var descr = $("#descr").val();
	var fileName = $("#fileName").val();
	var filePath = $("#filePath").val();
	var fileSize = $("#fileSize").val();
	params.id = thisId;
	params.title = $.trim(name);
	params.type = type;
	params.descr = $.trim(descr);
	params.fileName = $.trim(fileName);
	params.filePath = $.trim(filePath);
	params.fileSize = $.trim(fileSize);
	
	$('#addForm').isValid(function(v){
		var urlStr = "<%=request.getContextPath()%>/safetyEducation/add.action";
		if(thisId){//修改
			urlStr = "<%=request.getContextPath()%>/safetyEducation/updateById.action";
		}
		if(v){
			$.ajax({
				type : "POST",
				url : urlStr,
				data : params,
				success : function(data) {
					if(data && data.rtnResult=="SUCCESS"){
						dialog.alert("保存成功!",function(){
							window.location.href = "<%=request.getContextPath()%>/safetyEducation/toManageSafetyList.action";
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
		msgStyle:"margin-top: 10px;margin-left:10px",
		fields : {
			name : {
				rule : "required;length[~50]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于50个字符"
				}
			},
			descr : {
				rule : "length[~200]",
				msg : {
					length : "长度需小于等于200个字符"
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