<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
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

<style>
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;margin-left:10px;position: absolute;background:url("");}
	.ztree li span.button.noline_close{z-index:999;margin-left:10px;position: absolute;background:url("");}
	.page_div {width: 250px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
    
</style>

<script type="text/javascript">
var id;
var topic = ${topic};
var courseList=[];

$(function(){
	if(topic&&topic.id){
		id=topic.id;
		fillInfo();
		$("#title_span").text("修改专题")
	}
	initCourse();
	upLoadImg();
	initValidate();

});

/*var editor;
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


function fillInfo(){
	$("#code").val(topic.code);
	$("#name").val(topic.name);
	
	$("#previewPath").attr("src",topic.coverImage);
	$("#bannerPreviewPath").attr("src",topic.bannerImage);
	$("#descr").val(topic.descr);
	$("#filePath").val(topic.coverImage);
	$("#bannerPath").val(topic.bannerImage);
}



function initCourse(){
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	 params: function(){
    		 var o = {};
    		 o.topicId = id;
 	    	return o;
 	    },
    	url : '<%=request.getContextPath()%>/mall/manage/topic/courseList.action',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: false,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课程名称', name: 'name', width:60, align:'center',renderer:function(val,item,rowIndex){
            	   return '<a href="javascript:void(0)" onclick="detailCourse('+item.id+')" >'+val+'</a>';
            	   
               }},
               /**{title: '课程编号', name: 'code', width:60, align:'center'},**/
			   {title: '课程分类', name: 'categoryName', width:60, align:'center'},
			   {title: '课程价格', name: 'price', width:60, align:'center'},
			   {title: '优惠价格', name: 'cheapPrice', width:60, align:'center'},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="javascript:void(0);" onclick="deleteCourse('+val+')" >删除</a>';
			   }}
           ]
    });
	
	$('#exampleTable').mmGrid().on("loadSuccess",function(e,data){
		var row =$('#exampleTable').mmGrid().rows();
		
		if(row&&row.length>0&&row[0]){
			courseList = row;
			priceChange();
		}
		
	
	})
	
}



var artDialog;
function chooseCourse(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/mall/manage/toChooseMallCourse.action",
        title:"选择课程" ,
        height: 500,
		width: 1100
		}).showModal();
}


function deleteCourse(id){
	$.each(courseList, function(index, val){
		if(id == val.id){
			courseList.splice(index,1);
			console.log(courseList);
			$('#exampleTable').mmGrid().removeRow(index);
			if(!courseList||courseList.length==0){
				$('.mmg-message').css('margin-top','20px');
			}
			priceChange();
		}
	});
}

function detailCourse(id){
	if(!id){
		return ;
	}
	window.open('<%=request.getContextPath()%>/mall/manage/toCourseDetailPage.action?id='+id);
}

function toParam(){
	var param = {};
	param.id = id;
	param.code = $("#code").val();
	param.name = $("#name").val();
	param.descr = $("#descr").val();
	param.price = $("#price").val();
	param.cheapPrice = $("#cheapPrice").val();
	
	//课程参数
	var courseIds = [];
	var courseRows = $('#exampleTable').mmGrid().rows();
	if(courseRows[0] != undefined){
		$.each(courseRows, function(index, val){
			courseIds.push(val.id);
		});
	}
	param.courseIds = courseIds;
	param.coverImage = $("#filePath").val();
	param.bannerImage = $("#bannerPath").val();
	return param;
}



function save(){
	var url="<%=request.getContextPath()%>/mall/manage/topic/insert.action";
	if(id){
		url="<%=request.getContextPath()%>/mall/manage/topic/update.action";
	}
	$('#addForm').isValid(function(v) {
		if(v){
			var param = toParam();
			
			if(param.courseIds.length == 0){
				dialog.alert("请选择专题课程");
				return;
			}
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:param,
				url:url,
				success:function(data){
					if(id){
						if(data&&data=='SUCCESS'){
							dialog.alert("修改成功！",function(){cancel();});
						}else{
							dialog.alert("修改失败。");
						}
					}else{
						if(data&&data.id&&data.result == 'SUCCESS'){
							dialog.alert("新建成功！",function(){cancel();});
						}else{
							dialog.alert("新建失败。");
						}
					}
					
			    }
			});
		} else {
			//dialog.alert("表单验证不通过");
		}
	});
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/mall/manage/toTopicListPage.action";
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

		uploader.reset();
		// 预览图片
		$("#previewPath").attr("src", ret._raw).css({
			"width" : "278px",
			"height" : "105px"
		});
	});
	
	var bannerUploader = WebUploader
	.create({
		auto : true,
		// swf文件路径
		swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

		// 文件接收服务端。
		server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',

		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : {
			id : '#bannerPicker',
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

	bannerUploader.on('fileQueued', function(file) {
        });

        // 接受文件后，进行赋值操作
        bannerUploader.on('uploadAccept', function(file, ret) {
          $("#bannerPath").val(ret._raw);
         // 预览图片
         bannerUploader.reset();
        $("#bannerPreviewPath").attr("src", ret._raw).css({
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
			checkCode:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{code:element.value,id:id},
					url:"<%=request.getContextPath()%>/mall/manage/topic/checkCode.action",
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
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		msgStyle:"margin-top: 10px;",
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
			filePath : {
				rule : "required",
				msg : {
					required : "图片不能为空"
				}
			},
			descr : {
				rule : "required;length[~200]",
				msg : {
					required : "专题说明不能为空",
					length : "长度需小于等于200个字符"
				}
			}
		}
	});
}
function priceChange(){
	
	if(courseList&&courseList.length>0){
		
		var price=0;
		var cheapPrice=0;
		$.each(courseList,function(index,val){
		  price+=val.price;
		  cheapPrice+=val.cheapPrice;
		})
		$("#price").val(price.toFixed(2));
		$("#cheapPrice").val(cheapPrice.toFixed(2));
	}else{
		$("#price").val(0);
		$("#cheapPrice").val(0);
	}
}
</script>


</head>
<body>

<div class="content">
	<!-- <h3 id="title_span">新增专题</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增专题</span>
	 </div>
	<form id="addForm">
	<div class="lesson_add" >
		
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                 <em>专题编号：</em>
            </div>
            <div class="add_fr">
            	 <input type="text" id="code" name="code"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                 <em>专题名称：</em>
            </div>
            <div class="add_fr">
            	  <input type="text" id="name" name="name"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>专题课程：</em>
            </div>
            <div class="add_fr">
            	<input type="button" value="选择课程" class="ly_bt1" onclick="chooseCourse()"/>
            </div>
        </div>
          <div class="add_gr" style="height:auto;">
        	<div class="add_fl">
            	&nbsp;
            </div>
            <div class="add_fr">
            	<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
            </div>
        </div>
        
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>专题价格：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="price"  name=""price"" disabled/>
            </div>
    	</div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>专题优惠价格：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="cheapPrice"  name="cheapPrice" disabled/>
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
						<input id="filePath" type="hidden" name="filePath">
				</div>
            </div>
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img id="previewPath" src="<%=request.getContextPath()%>/images/img/left_2.png" width="278px" height="105px"/>
					</p>
            </div>
        </div>
          <div style="height:40px;width: 1066px;float: left;padding-top:5px">
        	<div style="width: 170px;float: left;text-align: right;height:40px;">
        	  <span style="color:red">*</span>
                <em>banner图片：</em>
            </div>
            <div style="height:40px;margin-left: 16px;float: left;width: 880px;">
            	<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="bannerPicker">选择图片</div>
						</div>
						<input id="bannerPath" type="hidden" name="filePath">
				</div>
            </div>
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img id="bannerPreviewPath" src="<%=request.getContextPath()%>/images/img/left_2.png" width="278px" height="105px"/>
					</p>
            </div>
        </div>
           <div class="add_gr" style="padding-top:10px">
        	<div class="add_fl">
        	    <span>*</span>
                <em>专题说明：</em>
            </div>
            <div class="add_fr">
            	<textarea name="descr" id="descr" ></textarea>
            </div>
        </div>

       
        
         
         
    </div>
    <div class="button_gr" style=" padding-left:250px;">
                <input type="button" value="保存" class="ly_bt1" onclick="save()"/>
                <input type="button" value="返回" class="btn_4" onclick="cancel();"/>
            </div>
</form>
</div>
<script>
</script>
</body>
</html>
