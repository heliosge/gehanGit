<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑积分商品</title>
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
	.close{color: black;
    font-size: 15px;
    height: 10px;
    position: absolute;
    right: -5px;
    text-decoration: none;
    top: -45px;
    width: 20px;}
    
    .close:hover{color:white}
</style>
<script>
var commodity={};
var id;
commodity= ${commodity};

var showImageCount=0;
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
KindEditor.plugin('multiimage',function(K){
	var self   = this,name = 'multiimage';
	self.clickToolbar(name,function(){
		layer.open({
			type:2,
			title: '选择图片',
			shade:[0.3,'#393D49'],
			area:['830px','490px'],
			content:'<%=request.getContextPath()%>/uploadAction/toUploadImgPage.action'
		});
	});
});
function selectPicBack(path){
	editor.insertHtml("<div align='center' style='margin:5px 0px 5px 0px;'><img src='"+path+"' data-ke-src='"+path+"' align='middle'/></div>");
}
$(function() {
	upLoadImg();
	
	initValidate();
	
	if(commodity&&commodity.id){
   		//修改
   		$("#top_page_name").text("修改商品");
   		id=commodity.id;
   		//回填值
   		$("#id").val(commodity.id);
   		$("#categoryId").val(commodity.categoryId);
   		$("#categoryName").val(commodity.categoryName);
   		$("#name").val(commodity.name);
   		$("#code").val(commodity.code);
   		$("#integral").val(commodity.integral);
   		$("#stock").val(commodity.stock);
   		$("#content").html(commodity.descr);
   		$("#coverImage").val(commodity.coverImage);
   		$("#previewPath").attr("src",commodity.coverImage);
   	    var showImageList = JSON.parse(commodity.showImage);
   	    if(showImageList&&showImageList.length>0){
   	    	for(var i = 0;i<showImageList.length;i++){
   	    		addShowImageSpan(showImageList[i]);
   	    	}
   	    }
   	}else{
   		$("#top_page_name").text("新建商品");
   	}
	
});

function addShowImageSpan(url){
	var htmlStr='<span class="uploadFileInfo" style="position:relative">'+
    '<input id="_random_id_8" type="hidden" value="'+url+'" name="showImage">'+
    '<img style="vertical-align: middle; max-width:80px; height: 80px;" src="'+url+'">'+
    '<a class="close" href="javascript:void(0)" >X    </a>'+
    '</span> ';
    
   $("#show_image_div").append(htmlStr);
   showImageCount++;
   return true;
}


var categoryId;
var categoryName;
var artDialog;
function chooseCategory(){
	artDialog = dialog({
        title: "选择商品分类",
        url:"<%=request.getContextPath()%>/integral/commodity/manage/toChooseCategory.action",
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}


var id = '';

function param(){
	var o = {};
	console.log(id)
	o.id= id;
	o.categoryId = $("#categoryId").val();
	o.code = $("#code").val();
	o.name=$("#name").val();
	o.integral = $("#integral").val();
	o.stock = $("#stock").val();
	o.coverImage = $("#coverImage").val();
	o.descr = editor.html();
	o.showImage = JSON.stringify(
    		$.map($('#show_image_div [name=showImage]'),
    				function(e) {
    			return $(e).val();
    		}));
	return o;
}


	
function cancel(){
	window.location.href="<%=request.getContextPath()%>/integral/commodity/manage/toCommodityListPage.action";
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
		$("#coverImage").val(ret._raw);
		// 预览图片
		$("#previewPath").attr("src", ret._raw).css({
			"width" : "200px",
			"height" : "200px"
		});
	});

	
	
	var uploader2 = WebUploader
	.create({
		auto : true,
		// swf文件路径
		swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

		// 文件接收服务端。
		server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',

		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : {
			id : '#showPicker',
			multiple : true
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

uploader2.on('fileQueued', function(file) {

});
uploader2.on('beforeFileQueued', function(file) {
	if(showImageCount>=8){
		dialog.alert("展示图片不能超过8张");
		return false;
	}
});


// 接受文件后，进行赋值操作
uploader2.on('uploadAccept', function(file, ret) {
	
   if(addShowImageSpan(ret._raw)){
	   $("#show_image_div").find('a.close').on('click', function() {
		   //console.log(event);
			$(this).parent().remove();
			uploader2.reset();
			showImageCount--;
	   });
   }

   
});


}


function save(flag) {
	$('#addForm').isValid(function(v) {
		var paramData = param();
		if(!paramData.coverImage||paramData.coverImage.length==0||paramData.coverImage=='FAIL'){
			$("#sapn_fmtp").show();
			v=false;
		}else{
			$("#sapn_fmtp").hide();
		}
		console.log(showImageCount)
		if(!showImageCount){
			$("#sapn_zstp").show();
			v=false;
		}else{
			$("#sapn_zstp").hide();
		}
		
		if(!paramData.descr||paramData.descr.length==0){
			$("#sapn_spxq").show();
			v=false;
		}else{
			$("#sapn_spxq").hide();
		}
		
		if(v){
			
			if(paramData.categoryId == undefined){
				dialog.alert("商品分类不能为空！");
				return;
			}
			
			var url;
			if(id != ''){
				url="<%=request.getContextPath()%>/integral/commodity/manage/update.action";
			}else{
				url="<%=request.getContextPath()%>/integral/commodity/manage/insert.action";
			}
			
			
			$.ajax({
		 		type:"POST",
		 		async:false,  //默认true,异步
		 		data:paramData,
		 		url:url,
		 		success:function(data){
		 			if(data != null){
                      if(id&&data=="SUCCESS"){
                    	  if(flag){
  		 					  doPutaway(id);
  		 			     	}else{
  		 					dialog.alert("保存成功！",function(){cancel();});
  		 				    }	
		 				}else if(data.id&&data.result=="SUCCESS"){
		 					id = data.id;
		 					    if(flag){
	  		 					doPutaway(id);
	  		 			     	}else{
	  		 					dialog.alert("保存成功！",function(){cancel();});
	  		 				    }
		 				}
		 				
		 				
		 				
		 				
		 			}else{
		 				dialog.alert("操作失败！");
		 			}
		 	    }
			 });
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
	
	function doPutaway(id){
		$.ajax({
	 		type:"POST",
	 		async:false,  //默认true,异步
	 		data:{ids:id},
	 		url:"<%=request.getContextPath()%>/integral/commodity/manage/putaway.action",
	 		success:function(data){
	 			if(data != null){
	 				if(data=="SUCCESS"){
	 					dialog.alert("保存并上架成功！",function(){cancel();});
	 				}else{
	 					dialog.alert("操作失败！");
	 				}
	 				
	 				
	 			}else{
	 				dialog.alert("操作失败！");
	 			}
	 	    }
		 });
	}
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
					data:{code:element.value,id:id},
					url:"<%=request.getContextPath()%>/integral/commodity/manage/checkCommodityCode.action",
					success:function(data){
						var flag = true;
						if(data!="SUCCESS"){
							flag = false;
						}
						str =  flag || "已存在相同编号";
					}
				});
				return str;
			},
			checknum : [ /^\d*$/, '请输入有效数字' ]
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
			code : {
				rule : "required;length[~30];checkCode",
				msg : {
					required : "编号不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			categoryName : {
				rule : "required",
				msg : {
					required : "商品分类不能为空"
				}
			},
			integral : {
				rule : "required;integer,checknum",
				msg : {
					required : "兑换积分不能为空",
					checknum : "积分必须是正整数"
				}
			},
			stock : {
				rule : "required;integer,checknum",
				msg : {
					required : "库存不能为空",
					checknum : "库存必须是正整数"
				}
			}
		}
	});
}
</script>


</head>
<body>
<div class="content">
	<!-- <h3 id="top_page_name">编辑积分商品</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="top_page_name" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">编辑积分商品</span>
	</div>
	<form id='addForm'>
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>商品分类：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="categoryName" name="categoryName" disabled/>
            	<input type="hidden" id="categoryId" name="categoryId"/>
                <input type="button" value="选择" class="xz" onclick="chooseCategory()"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>商品编号：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="code" name="code"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>商品名称：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="name" name="name"/>
            </div>
    	</div>

      <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>兑换积分：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="integral"  name="integral"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>库存：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="stock"  name="stock"/>
            </div>
    	</div>
    
    	<div style="height:40px;width: 1066px;float: left;">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
        	<span style="color:red">*</span>
                <em>封面图片：</em>
            </div>
            <div style="height:40px;margin-left: 16px;float: left;width: 880px;">
            	<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择图片</div>
						</div>
						<input id="coverImage" type="hidden">
					
				</div>
				
					 <span id="sapn_fmtp" class="msg-box n-right" for="coverImage" style="display: none;">
						<span class="msg-wrap n-error" role="alert">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">封面图片不能为空</span>
						</span>
					</span>
            </div>
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img style="width:200px;height:200px" id="previewPath" src="<%=request.getContextPath()%>/images/img/left_2.png" />
					</p>
            </div>
        </div>
        
        
        	<div style="height:40px;width: 1066px;float: left;padding-top:5px">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
        	<span style="color:red">*</span>
                <em>展示图片：</em>
            </div>
            <div style="height:40px;margin-left: 16px;float: left;width: 880px;">
            	<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="showPicker">选择图片</div>
						</div>
						
				</div>
				
            </div>
            
            	 <span id="sapn_zstp" class="msg-box n-right" for="showImage" style="display: none;">
						<span class="msg-wrap n-error" role="alert">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">展示图片不能为空</span>
						</span>
					</span>
        </div>
      
       <div class="add_gr"  style="height:auto;padding-bottom:5px">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div id="show_image_div" class="add_fr">
				
            </div>
        </div>  
 
    
  
        <div class="add_gr_1">
        	<div class="add_fl">
        	<span>*</span>
                <em>商品详情：</em>
            </div>
            <div class="add_fr">
            	<textarea name="content" id="content"  name="content"></textarea>
            	 <span id="sapn_spxq" class="msg-box n-right" for="content" style="display: none;">
						<span class="msg-wrap n-error" role="alert">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">商品详情不能为空</span>
						</span>
					</span>
            </div>
        </div>
        <div class="button_cz">
        	<input type="button" value="保存并上架"  onclick="save(1)"/>
            <input type="button" value="存草稿"  onclick="save()" />
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
    </div>
    </form>
</div>
<script>
</script>
</body>
</html>