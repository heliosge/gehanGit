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
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script>

var cer = ${cer};

$(function(){
	initValidate();
	upLoadImg();
	initDatepicker();
	fillCerInfo();
})

function fillCerInfo(){
	receiveUserId = cer.receiveUserId;
	$("#name").val(cer.name);
	$("#receiveUserName").val(cer.receiveUserName);
	$("#theoryScore").val(cer.theoryScore);
	$("#operateScore").val(cer.operateScore);
	$("#sendAgency").val(cer.sendAgency);
	$("#sendDate").val(cer.sendDate);
	$("#firstCheckDate").val(cer.secondCheckDate);
	$("#secondCheckDate").val(cer.secondCheckDate);
	$.each($("input[name='checkStatus']"), function(index, val){
		if($(val).val() == cer.checkStatus){
			val.checked = true;
		}
	});
	$("#trainStyle").val(cer.trainAgency);
	$("#trainAgencyLevel").val(cer.trainAgencyLevel);
	$("#trainAgency").val(cer.trainAgency);
	$("#changeDate").val(cer.changeDate);
	$("#descr").val(cer.descr);
	$("#trainAgency").val(cer.trainAgency);
	$("#previewPath").attr("src",cer.frontImg);
	$("#filePath").val(cer.frontImg);
	$("#fileSize").val(cer.frontImgSize);
}


var receiveUserId;
var artDialog;
function chooseUser(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/certificate/chooseStu.action",
        title:"选择人员" ,
        height: 500,
		width: 1100
		}).showModal();
}


function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var paramData = param();
			/* if(paramData.receiveUserId == undefined){
				dialog.alert("获得人员不能为空！");
				return
			} */
			$.ajax({
		 		type:"POST",
		 		async:true,  //默认true,异步
		 		data:paramData,
		 		url:"<%=request.getContextPath()%>/certificate/updateCertificate.action",
		 		success:function(data){
		 			if(data=='SUCCESS'){
		 				dialog.alert("修改成功！",function(){cancel();});
		 			}else{
		 				dialog.alert("修改失败！");
		 			}
		 	    }
			 });
		} else {
			//dialog.alert("表单验证不通过");
		}
	})
}

function param(){
	var o = {};
	o.id = cer.id;
	o.receiveUserId = receiveUserId;
	o.name=$("#name").val();
	o.theoryScore = $("#theoryScore").val();
	o.operateScore = $("#operateScore").val();
	o.sendAgency = $("#sendAgency").val();
	o.sendDate = $("#sendDate").val();
	if($("#firstCheckDate").val() != ''){
		o.firstCheckDate = $("#firstCheckDate").val();
	}
	if($("#secondCheckDate").val() != ''){
		o.secondCheckDate = $("#secondCheckDate").val();
	}
	$.each($("input[name='checkStatus']"), function(index, val){
		if(val.checked){
			o.checkStatus = $(val).val();
		}
	});
	o.trainStyle = $("#trainStyle").val();
	o.trainAgencyLevel = $("#trainAgencyLevel").val();
	o.trainAgency = $("#trainAgency").val();
	if($("#changeDate").val() != ''){
		o.changeDate = $("#changeDate").val();
	}
	o.descr = $("#descr").val();
	o.trainAgency = $("#trainAgency").val();
	o.frontImg = $("#filePath").val();
	o.frontImgSize = $("#fileSize").val();
	return o;
}

function initDatepicker() {
	$("#sendDate").datepicker({
		dateFormat : 'yy-mm-dd'
	});

	$("#firstCheckDate").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	
	$("#secondCheckDate").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	
	$("#changeDate").datepicker({
		dateFormat : 'yy-mm-dd'
	});
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/certificate/toCertificateListPage.action";
	}
	
function upLoadImg() {
	var uploader = WebUploader
			.create({
				auto : true,
				// swf文件路径
				swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

				// 文件接收服务端。
				//server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',
				server:' <%=request.getContextPath()%>/train/uploadFile.action?path=certificate%2FuploadKL',

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
		//$("#filePath").val(ret._raw);
		$("#filePath").val(ret.path);
		$("#fileSize").val(ret.fileSize);
		// 预览图片
		$("#previewPath").attr("src", ret.path).css({
			"width" : "278px",
			"height" : "105px"
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
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		//msgStyle:"height: 10px;",
		fields : {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			receiveUserName : {
				rule : "required;length[~200]",
				msg : {
					required : "备注不能为空",
					length : "长度需小于等于200个字符"
				},
				msgStyle:"left:-30px;",
				msgClass:"n-top"
			},
			sendDate : {
				rule : "required",
				msg : {
					required : "发证日期不能为空"
				}
			}
		}
	});
}
</script>


</head>
<body>
<div class="content">
	<!-- <h3>新增证书</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改证书</span>
	 </div>
	<form id="addForm">
	<div class="lesson_add">
		<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>证书名称：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="name" name="name"/>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>获得人员：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:319px;" id="receiveUserName" disabled name="receiveUserName"/>
                <input type="button" value="选择" class="xz" onclick="chooseUser()"/>
            </div>
        </div>
        <div style="height:40px;width: 1066px;float: left;">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
                <em>证书图片：</em>
            </div>
            <div style="height:40px;margin-left: 16px;float: left;width: 880px;">
            	<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择图片</div>
						</div>
						<input id="filePath" type="hidden" name="filePath">
						<input id="fileSize" type="hidden" name="fileSize">
				</div>
            </div>
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img id="previewPath" src="" width="278px" height="105px"/>
					</p>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>理论成绩：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="theoryScore" name="theoryScore"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>实操成绩：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="operateScore" name="operateScore"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>发证机构：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="sendAgency" name="sendAgency"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>发证日期：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="sendDate" name="sendDate" readonly/>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>初次复审日期：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="firstCheckDate" name="firstCheckDate" readonly/>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>二次复审日期：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="secondCheckDate" name="secondCheckDate" readonly/>
            </div>
    	</div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>证书复审状态：</em>
            </div>
             <div class="add_fr">
            	<input type="radio" name="checkStatus" value="1"/>
                <span>已复审</span>
                <input type="radio"  name="checkStatus" value="2"/>
                <span>未复审</span>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>培训方式：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="trainStyle" name="trainStyle"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>培训机构：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="trainAgency" name="trainAgency"/>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>培训机构级别：</em>
            </div>
             <div class="add_fr">
            	<select id="trainAgencyLevel">
            		<option value="">请选择</option>
            		<option value="1">国家一级</option>
            		<option value="2">国家二级</option>
            		<option value="3">国家三级</option>
            		<option value="4">国家四级</option>
            		<option value="5">其它</option>
            	</select>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>换证日期：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="changeDate" name="changeDate" readonly/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>备注：</em>
            </div>
             <div class="add_fr">
            	<textarea id="descr" name="descr"></textarea>
            </div>
    	</div>
      
          <div class="add_gr">
        	<div class="add_fl">
            	&nbsp;
            </div>
            <div class="add_fr" id="post">
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
