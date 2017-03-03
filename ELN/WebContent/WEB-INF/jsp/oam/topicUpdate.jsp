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
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
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

var topic = ${topic};
var bar = ${bar};

$(function(){
	initCourse();
	fillInfo();
	upLoadImg();
	fillBarInfo();
	$("div[name='bar']").click(function(){
		redThisDiv($(this));
	});
	initValidate();
	initCategoryTreePage();
	
	$("input[name='isSpread']").click(function(){
		if($(this).val()==1){
			$("#bar_left_info").show();
			$("#bar_"+style).show();
		}else{
			$("#bar_left_info").hide();
			$("#bar_1").hide();
			$("#bar_2").hide();
			$("#bar_3").hide();
		}
	})
});

function initCategoryTreePage(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				onClick: zTreeOnClick
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/oam/selectOamTopicCategory.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#categoryTree"), setting, data);
		}
	});
	
	$.fn.zTree.getZTreeObj("categoryTree").expandAll(true);
}

function zTreeOnClick(event, treeId, treeNode){
	$("#categoryId").val(treeNode.id);
	$("#categoryName").val(treeNode.name);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function fillInfo(){
	$("#categoryId").val(topic.categoryId);
	$("#categoryName").val(topic.categoryName);
	$("#no").val(topic.no);
	$("#name").val(topic.name);
	$("#descr").val(topic.descr);
	$("#previewPath").attr("src",topic.coverImage);
	$.each($("input[name='isSpread']"), function(index, val){
		if($(val).val() == topic.isSpread){
			val.checked = true;
		}
	});
	//课程参数
	if(bar.id != ''){
		order = bar.order;
	}
	$("#filePath").val(topic.coverImage);
}

function blackBar(){
	var bars = $("div[name='bar']");
	$.each(bars, function(i, bar){
		if($(bar).attr("id") == 'bar4' && $(bar).attr("class") == 'right_3'){
			$(bar).attr("style","color:black;margin-left:0; width:544px; background-repeat:repeat-x;");
		}else{
			$(bar).attr("style","color:black;");
		}
	});
}

var order = '';
function redThisDiv(obj){
	blackBar();
	if($(obj).attr("id") == 'bar4' && $(obj).attr("class") == 'right_3'){
		$(obj).attr("style","color:red;margin-left:0; width:544px; background-repeat:repeat-x;");
	}else{
		$(obj).attr("style","color:red");
	}
	order = $(obj).attr("id").replace("bar","");
}

function initCourse(){
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	 params: function(){
    		 var o = {};
    		 o.topicId = topic.id;
 	    	return o;
 	    },
    	url : '<%=request.getContextPath()%>/oam/selectOamCourseByTopic.action',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: false,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课程名称', name: 'name', width:60, align:'center'},
               {title: '课程编号', name: 'code', width:60, align:'center'},
			   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "直播课程";
				   }
				   return "";
			   }},
			   {title: '课程体系', name: 'categoryName', width:60, align:'center'},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="javascript:void(0);" onclick="deleteCourse('+val+')" >删除</a>';
			   }}
           ]
    });
}



var artDialog;
function chooseCourse(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/res/toChooseCourse.action",
        title:"选择课程" ,
        height: 500,
		width: 1100
		}).showModal();
}


function deleteCourse(id){
	var rows = $('#exampleTable').mmGrid().rows();
	$.each(rows, function(index, val){
		if(id == val.id){
			$('#exampleTable').mmGrid().removeRow(index);
		}
	});
}

function toParam(){
	var param = {};
	param.id = topic.id;
	param.no = $("#no").val();
	param.name = $("#name").val();
	param.descr = $("#descr").val();
	param.categoryId = $("#categoryId").val();
	$.each($("input[name='isSpread']"), function(index, val){
		if(val.checked){
			param.isSpread = $(val).val();
		}
	});
	//课程参数
	var courseIds = [];
	var courseRows = $('#exampleTable').mmGrid().rows();
	if(courseRows[0] != undefined){
		$.each(courseRows, function(index, val){
			courseIds.push(val.id);
		});
	}
	param.courseIds = courseIds;
	param.order = order;
	//param.style = style;
	param.barId = bar.id;
	param.coverImage = $("#filePath").val();
	return param;
}

function chooseCategory(){
	artDialog = dialog({ 
        title: "选择专题体系",
        content: $("#chooseCategory"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  this.close();
	              }
		          }
		      ]
    }).showModal();
}

function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = toParam();
			if(param.categoryId == ''){
				dialog.alert("请选择专题体系");
				return;
			}
			if(param.courseIds.length == 0){
				dialog.alert("请选择专题课程");
				return;
			}
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:param,
				url:"<%=request.getContextPath()%>/oam/updateOamTopic.action",
				success:function(data){
					if(data == 'SUCCESS'){
						dialog.alert("修改成功！",function(){cancel();});
					}else{
						dialog.alert("修改失败。");
					}
			    }
			});
		} else {
			//dialog.alert("表单验证不通过");
		}
	});
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/oam/toOamTopicListPage.action";
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
		// 预览图片
		$("#previewPath").attr("src", ret._raw).css({
			"width" : "278px",
			"height" : "105px"
		});
	});

}

function fillBarInfo(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/oam/selectOamBar.action",
		success:function(data){
			fillBarSettingInfo(data);
		}
	});
}
var style = 1;
function fillBarSettingInfo(data){
	if(data.data == null){
		$("#bar_1").show();
		return;
	}
	var bars = $("div[name='bar']");
	style = data.style;
	$("#bar_"+data.style).show();
	if(data.data !=null){
		$.each(data.data, function(index, val){
			$.each(bars, function(i, bar){
				if($(bar).attr("id") == "bar"+val.order){
					if(val.spreadObject != null){
						if(val.type == 1){
							$(bar).append("<img style='z-index:-1;width:100%;height:100%' src='"+val.spreadObject.coverImage+"' />");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【专题】</a>");
						}else if(val.type == 2){
							$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%' src='"+val.spreadObject.frontImage+"'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【课程】</a>");
						}else if(val.type == 3){
							$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%' src='"+val.spreadObject.coverImageForList+"'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【大赛】</a>");
						}
					}
				}
			});
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
			no : {
				rule : "required;length[~30]",
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
					required : "描述不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			categoryName : {
				rule : "required",
				msg : {
					required : "体系不能为空"
				}
			}
		}
	});
}
</script>


</head>
<body>

<div class="content">
	<!-- <h3>新增专题</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改专题</span>
	 </div>
	<form id="addForm">
	<div class="lesson_add" >
		<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                 <em>专题体系：</em>
            </div>
            <div class="add_fr">
            	 <input type="text" id="categoryName" name="categoryName" disabled/>
            	  <input type="hidden" id="categoryId" name="categoryId"/>
            	 <input type="button" value="选择体系" class="ly_bt1" onclick="chooseCategory()"/>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                 <em>专题编号：</em>
            </div>
            <div class="add_fr">
            	 <input type="text" id="no" name="no"/>
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
         <div class="add_gr" style="margin-top: 10px;">
        	<div class="add_fl">
            	<span>*</span>
                    <em>专题说明：</em>
            </div>
            <div class="add_fr">
            	 <textarea id="descr" name="descr"></textarea>
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
                <em>是否推广：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" name="isSpread" value ="1" checked="checked"/><span>是</span>
            	<input type="radio" name="isSpread" value ="2"/><span>否</span>
            </div>
        </div>
         <div class="add_gr" style="margin-top:20px;" id="bar_left_info">
		        	<div class="add_fl">
	            	<span>*</span>
	                <em>选择推广位置：</em>
	            </div>
	            <div class="add_fr">
	            &nbsp;
            	</div>
           	</div>
           <div class="add_gr" style="height:auto;margin-top:20px;">
		            	<div class="pic_1" id="bar_1" style="display:none;">
					    	<div class="left_pic fl">
					        	<div class="left_1" id="bar1" name="bar">
					            </div>
					            <div class="left_2" id="bar2" name="bar">
					            </div>
					            <div class="left_3" id="bar3" name="bar">
					            </div>
					        </div>
					        <div class="right_pic fl">
					        	<div class="right_top clear">
					                <div class="right_3" style="margin-left:0; width:544px; background-repeat:repeat-x;" id="bar4" name="bar">
					                </div>
					            <div class="right_bottom">
					            	<div class="right_4" id="bar5" name="bar">
					                </div>
					                <div class="right_5" id="bar6" name="bar">
					                </div>
					                
					            </div>
					        </div>
					    </div>
					    
					      </div> 
					      
					      <div class="pic_1" id="bar_2" style="display:none;">
					    	<div class="left_pic fl">
					        	<div class="left_1" id="bar1" name="bar">
					            </div>
					            <div class="left_2" id="bar2" name="bar">
					            </div>
					            <div class="left_3" id="bar3" name="bar">
					            </div>
					        </div>
					        <div class="right_pic fl">
					        	<div class="right_top clear">
					            	<div class="top_12">
					            		<div class="right_1" id="bar4" name="bar">
					                    </div>
					               	 	<div class="right_2" id="bar5" name="bar">
					                	</div>
					                </div>
					                <div class="right_3" id="bar6" name="bar">
					                </div>
					                	
					            	
					            <div class="right_bottom">
					            	<div class="right_4" id="bar7" name="bar">
					                </div>
					                <div class="right_5" id="bar8" name="bar">
					                </div>
					            </div>
					        </div>
					    </div> 
					    </div>
					    
					    <div class="pic_1" id="bar_3" style="display:none;">
					    	<div class="left_pic fl">
					        	<div class="left_4" id="bar1" name="bar">
					            </div>
					             <div class="left_5" id="bar2" name="bar">
					            </div>
					            <div class="left_6" id="bar3" name="bar">
					            </div>
					            <div class="left_2" id="bar4" name="bar">
					            </div>
					            <div class="left_3" id="bar5" name="bar">
					            </div>
					        </div>
					        <div class="right_pic fl">
					        	<div class="right_top clear">
					            	<div class="top_12">
					            		<div class="right_1" id="bar6" name="bar">
					                    </div>
					               	 	<div class="right_2" id="bar7" name="bar">
					                	</div>
					                </div>
					                <div class="right_3" id="bar8" name="bar">
					                </div>
					                	
					            	
					            <div class="right_bottom">
					            	<div class="right_4" id="bar9" name="bar">
					                </div>
					                <div class="right_5" id="bar10" name="bar">
					                </div>
					            </div>
					        </div>
					    </div> 
					    </div>
        	</div>
    </div>
    <div class="button_gr" style=" padding-left:250px;">
                <input type="button" value="保存" class="ly_bt1" onclick="save()"/>
                <input type="button" value="返回" class="btn_4" onclick="cancel();"/>
            </div>
</form>
</div>
<div class="page_div" style="display:none" id="chooseCategory">
   		<h2>专题体系</h2>
        <ul id="categoryTree" class="ztree" style=""></ul>
   	</div> 
<script>
</script>
</body>
</html>
