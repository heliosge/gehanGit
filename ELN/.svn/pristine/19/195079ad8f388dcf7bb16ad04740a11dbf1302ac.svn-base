<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>知识库管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">

<!-- 弹出框 -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>


<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />

<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/knowledge.css"/>
<style type="text/css">
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
</style>

<script type="text/javascript">
var filedata = [];//[{},{}]
var uploader ;
//选择知识分类	
function selectCategory(){
	var categoryId = 0;
	//打开设置框
	 dialog({
		url: "<%=request.getContextPath()%>/knowledge/getKLCategoryTree.action?categoryId="+categoryId+"&roleType=2",
		height: 450,
		width: 300,
		title:"分类列表",
		button: [ { value: '确定', 
					callback: function () { 
						
						var iframe = this.iframeNode.contentWindow;
						var id = iframe.categoryId.value;
						var name = $(iframe.categoryId).attr("showName");
						if(!id||id=="-1"){
							dialog.alert("请选择分类！");
							return false;
						}
						$("input[name='categoryId']").val(id);
						$("input[name='categoryName']").val(name);
						
						//清除后面的手动错误
						$("input.category-search").next("div.tit-error").find("span.msg-box").remove();
					}
			 }, { value: '取消', callback: function ( ) {} } ] }).showModal();
}
	
	//清除文件信息,并删除其信息
	function closeFile(_this){
		filedata.splice($(_this).closest("div.kl-item").index(),1);
		$(_this).closest("div.kl-item").remove();
		if(filedata.length==0){
			$("div.left_up").html($("div.left_up").data("html"));
		}
		$("#fileCount").val(filedata.length==0?"":filedata.length);
		uploader.cancelFile( $(_this).closest("div.kl-item").attr("id"));
	};
	
	//初始化参数
	$(function(){
		$("#klTags").tags({inputName: "tags" });
		var mimeType ="application/pdf,text/plain,application/vnd.ms-powerpointtd,application/vnd.openxmlformats-officedocument.presentationml.presentation";
			mimeType+=",application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword";
			mimeType+=",application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel";
		//上传文件
		 uploader = WebUploader.create({
			auto: true,
		    // swf文件路径
		    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

		    // 文件接收服务端。
		    server:'<%=request.getContextPath()%>/knowledge/uploadFile.action?path=knowledge%2FuploadKL',

		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: {id:'#picker',
	    		multiple:true
	    	},
	    	fileNumLimit:10,
	    	fileSingleSizeLimit:209715200,//单个文件200M
		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: 'xls,xlsx,doc,docx,ppt,pptx,pdf,txt',
		        mimeTypes: mimeType
		    }
		});
		
		uploader.on( 'uploadAccept', function( file ,ret ) {
			  filedata.push({
				  knowledgeName: file.file.name.substring(0, file.file.name.lastIndexOf(".")),
                  filePath: ret.path,
                  extendName : file.file.ext,
                  fileSize: file.file.size,
                  fileName:file.file.name.substring(0, file.file.name.lastIndexOf("."))
              })
              $("#fileCount").val(filedata.length).next("span.msg-box").hide();
			 
		});
		uploader.on( 'uploadFinished', function( file ,ret ) {
			//上传完成，去掉蒙层
			layer.closeAll();
			$index = null;
		})
		var flag = false;
	 	uploader.on('error', function(type) {
	 		dialog.getCurrent()?dialog.getCurrent().close():null;
	        if (type == 'Q_EXCEED_NUM_LIMIT') {
		 		flag =true;
	            dialog.alert('上传文件个数超过限制');
	            return;
	        }
	        if (type == 'F_EXCEED_SIZE') {
		 		flag =true;
	            dialog.alert('上传文件过大');
	            return;
	        }
	        if (type == 'Q_TYPE_DENIED') {
		 		flag =true;
	            dialog.alert('上传文件类型不匹配');
	            return;
	        }
	        if(type == 'F_DUPLICATE'){
	        //	dialog.alert('上传文件重复');
	            return;
	        }
	    });
	 	
		uploader.on('startUpload', function(file) {
			if(flag){
				uploader.reset()
			}
			flag =false;
		})
		var $index;
		uploader.on('uploadStart', function(file) {
			//开始上传，进行蒙层处理
			 if(!$index){
		            $index =  layer.open({
		                    type: 1,
		                    //shade: [0.3,'#000'], 
		                    title: "文件上传中", //不显示标题
		                    content: $('.layer_notice'), //捕获的元素
		                    cancel: function(index){
		                            layer.close(index);
						            $("div.kl-item").find("span.close").click();
		                    }
		                }); 
		        }
				
				
			//当没有文件时，进行清空操作
			if($("div.left_up").find("div.kl-item").length==0){
				$("div.left_up").data("html",$("div.left_up").html());
				$("div.left_up").empty();
			}
		    $("div.left_up").append( '<div id="' + file.id + '" class="kl-item">' +
		        '<h4 class="info">' + file.name + '</h4>' +
		        '<span class="close"  onclick="closeFile(this)">X</span>'+
		        '<div class="progress"></div>'+
		    '</div>' );
		})
		// 文件上传过程中创建进度条实时显示。
		uploader.on( 'uploadProgress', function( file, percentage ) {
		    var $li = $("li.progress");
		    $li.find("label.file-name").html(file.name).attr("title",file.name);
		    var processedPercentage = 100*percentage-1;
	        processedPercentage = Math.round(processedPercentage < 0 ? 0 : processedPercentage);
	        initJqMeter(processedPercentage);	
		});

	})
	/**
	 * 初始化进度条
	 */
	function initJqMeter(value){
		$("#jqMeter_div").jQMeter({
			 	goal:'100',
			    raised:value+'',
			    meterOrientation:'horizontal',
			    width:'300px',
			    height:'25px',
		    	displayTotal:false, // 默认true  是否显示进度百分比
			    animationSpeed:0  // 初始化速度 默认2000 单位milliseconds
		});
	}
</script>
</head>

<body style="overflow-x:hidden;">

<div id='content_i' class="content" >
	<div class='knowledge'>
		<!-- <h3>上传知识</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="window.location='<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action'"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">上传知识</span>
		</div>
		<input type="hidden" value='1' name='uploadType'></input>
	</div>
	<div style='margin: 20px' >
	<form action="" id="knowledgeForm">
			<div class="up_l1">
	        	<div class="left_up" style='padding-top: 10px;'>
	            	<div class="up_notice">
	                	<h5>上传须知：</h5>
	                    <p>1.每次最多上传10份文档，每份文档不超过<span>200M</span>；</p>
	                    <p>2.上传不妥的文档将会被移除；</p>
	                    <p>3.为了保证文档能正常显示，我们支持以下格式的文档上传</p>
	                </div>
	                <div class="up_wdgs">
	                	<p>MS Office文档：<img src="<%=request.getContextPath()%>/images/img/word.png">doc,docx<img src="<%=request.getContextPath()%>/images/img/ppt.png">ppt,pptx
	                	<img src="<%=request.getContextPath()%>/images/img/excel.png">xls,xlsx</p>
	                    <p>pdf:<img src="<%=request.getContextPath()%>/images/img/pdf.png">pdf</p>
	                    <p>文本文档：<img src="<%=request.getContextPath()%>/images/img/txt.png">txt</p>
	                </div>
	                
	            </div>
	            <div class="right_up">
	            	<div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择文件</div>
				    </div>
	                <p>1、可一次性选择多个知识或多次选择知识；</p>
	                <p>2、你可以上传私有文档，仅自己可以看到；</p>
	            </div>
    	    </div>
    	    <input type="hidden" name="fileCount" id="fileCount"/>
    	    <div class='basic'>
    	    <ul class='kl-list'>
     	  			<h5>基本信息</h5>		
				<li><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>知识分类:
					</div>
					<div class='kl-content'> 
						<input type='text' class='text-p' name='categoryName' placeholder='选择分类' style='cursor:pointer'
							readonly="readonly" ></input>
							<input type="hidden" name='categoryId' />
						<input type="button" class='category-search' value="选择" onclick='selectCategory()'>
						<div class="tit-error"></div>	
					</div></li>
				<li><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>是否开放:
					</div>
					<div class='kl-content'>
						<select class='text-p' name='isOpen'>
							<option value='0'>否</option>	
							<option value='1'>是</option>	
						</select>
					</div></li>
				<li><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>允许下载:
					</div>
					<div class='kl-content'>
						<select  class='text-p' name='isDownload' value='1'>
							<option value='0'>否</option>	
							<option value='1' selected>是</option>	
						</select>
					</div></li>
				<li style="display: none;"><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>精彩推荐:
					</div>
					<div class='kl-content'>
						<select class='text-p' name='isRecommend' value='1'>
						<option value='0' selected>否</option>	
							<option value='1' >是</option>	
						</select>
					</div></li>
				<li ><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>标签:
					</div>
					<div class='kl-content' style="height: 80px;">
					<div id="klTags" class="tags-box fn-clear" style="width: 70%;">
	       			 </div>
						<!-- <input type='text' class='text-p' name='"klTags"' id="klTags"></input>
						<input type="hidden" class='text-p' name='tags' id="tags"></input> -->
						<h6 style='margin-left: 0px;padding-left: 0px;margin-top: 0px;margin-bottom: 31px;width: 100%;font-size: 12px;'>最多输入5个标签，多个标签之间请用空格或","隔开</h6>
					</div></li>
				<li><div class='kl-label'>知识描述:</div>
					<div class='kl-content'>
						<textarea  placeholder='2000个字符以内，文本输入' name='knowledgeDesc'  rows="5" cols="120" ></textarea>
					</div></li>
				<li  style="display: none;"><div class='kl-label'>
						<span
							style="font-family: 'Applied Font Regular', 'Applied Font'; color: #FF0000;">*</span>是否共享:
					</div>
					<div class='kl-content'>
						<input type='hidden' name='isShare'></input><input type='radio'
							name='klShare' ></input>共享 <input type='radio' style='margin-left: 60px;'
							name='klShare'  checked="checked"></input>不共享
					</div></li>
				<li><div class=""
						style='margin-top: 20px; margin-left: 100px;'>
						<div class="controls">
							<input type="button" id="btnSubmit" class="btn" value="保存"> 
							<input type="button" onclick="window.location='<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action'"
								class="btn btn-cancel" value="取消">
						</div>
					</div></li>
			</ul>
			</div>
		</form>
	</div>
</div>	
	<ul class="layer_notice"
		style="display: none; height: 150px; width: 350px">
		<!-- 	<li class="loading-tit">文件上传中...</li>
 -->
		<li class="progress"><label>文件名称:</label><label class="file-name"></label></li>
		<li class="progress">
			<div id="jqMeter_div"></div>
		</li>
	</ul>
<script  type='text/javascript'>
	$("#btnSubmit").click(function(){
		
		if(validateForm()){
			saveKnowledge()
		}
	})
	

//检验检测
 jQuery.validator.addMethod("repeatName", function(value, element) {
	    return this.optional(element) ||checkUerName();
	});  
	//校验表单
	function validateForm() {  
		
	 var  validate = $("#knowledgeForm").validate({ 
		   focusCleanup:true,  
		   ignore: "", 
	       rules: {  
	    		categoryName:{  
	                required:true  
	        	},
	        	tags:{
	        		required:true  
	        	},
	        	knowledgeDesc: {
	        		maxlength: 2000
	        		},
	        		fileCount:{
	        		required:true  
	        	},fileCount:{
	        		required:true  
	        	}
	       },  
	       messages:{  
	    	  
	 	  		categoryName:{  
	            	required:"请选择知识分类"  
	    		},
	        	tags:{
	        		required:"知识标签必填"  
	        	},
	        	knowledgeDesc:{
	        		maxlength: "知识描述最多2000个字符！"
        		},
	        	fileCount:{
	        		required:"请选择文件	."  
	        	}
	       },  
	       errorPlacement: function(error, element) {  
				if($(element).closest("div.kl-content").find("div.tit-error").length>0){
					$(element).closest("div.kl-content").find("div.tit-error").html(error);
				}else if(element.attr("id")=="tags"){
					$("#klTags").after(error);
				}else{
					
					$(element).after(error);
				}
	    	 }
	   })
	  return validate.form();  
	} 

	//保存创建的知识
	function saveKnowledge(){
		
		$.blockUI({message:"<font size='5'>保存知识中，请耐心等待...</font>"});
		 for (var i = 0; i < filedata.length; i++) {
             var item = filedata[i];
             item.uploadType=$("input[name='uploadType']").val();
             item.categoryId = $("input[name='categoryId'").val();
             item.isOpen = $("[name='isOpen']").val();
             item.isDownload = $("[name='isDownload']").val();
             item.isRecommend = $("[name='isRecommend']").val();
             item.tags = $("input[name='tags'").val();
             item.knowledgeDesc = $("[name='knowledgeDesc']").val();
             item.isPublish=1;
             item.shareStatus=1;
             item.roleType = 2;
     		if($("input[name='klShare']").eq(0).is(":checked")){
     			item.shareStatus=5;
     		}
         }
		 $.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		traditional:true,
    		    contentType : 'application/json;charset=utf-8',
	    		data:JSON.stringify(filedata),
	    		url:"<%=request.getContextPath()%>/knowledge/save.action",
	    		success:function(data){
	    			$.unblockUI(); 
	    			if("SUCCESS"==data){
	    				dialog.alert("保存成功",function(){
		    				location = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action";
	    				});
	    			}else{
	    				dialog.alert("保存失败");
	    			}
	    	    }
	    	});
		 
	}
	</script>
</body>
</html>
