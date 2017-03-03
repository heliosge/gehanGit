<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增专题问答</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- niceValidate -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- jQueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/courseTags.css"/>
<!-- webuploader -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**页面框架*/
#newThematicAskContent{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**页面框架标题*/
#newThematicAskContent > h3{background: transparent url("../images/img/ico_4.png") no-repeat scroll left 2px;
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;font-size:14px;}

/**整体框架*/
#addThematicAskDiv{width: 1066px;font-family: "微软雅黑";margin: 0px;padding: 0px;}

/**每个新增项框架*/
.eachAddDiv{width: 1066px;min-height: 80px;height:auto;line-height: 80px;float: left;font-size: 12px;padding-top: 10px;}

/**新增项左边div*/
.addLeft{width: 170px;float: left;text-align: right;line-height: 50px;}

.addLeft span{color:#F00;}

.addLeft em{color:#333;font-style: normal;font-weight: normal;}

/**新增项右边div*/
.addRight{margin-left: 16px;float: left;width: 880px;line-height: 50px;}

/**标题输入框*/
#thematicTitle{width: 385px;height: 30px;margin-top: 20px;}

/**问题补充*/
#thematicContent{width: 385px;height: 90px;margin-top: 20px;}

/**答案（初始）*/
#initialAnswer{width: 385px;height: 90px;margin-top: 20px;}

/**注解样式*/
.psStyle{color: #999;font-style: normal;font-weight: normal;}

/**选择图片按钮*/
#addPicsButton{line-height:13px;}

/**封面图片输入框*/
#coverPic{width: 460px;height: 30px;margin-top: 20px;}

/**封面图片选择按钮*/
#selectCoverPicButton{line-height:13px;margin-top:10px;}

/**有效期输入框*/
#effictiveTime{width:100px;height:30px;margin-top: 20px;}

/**奖励积分输入框*/
#rewardsPoints{width:100px;height:30px;margin-top: 20px;}

/**按钮框架*/
#buttonsDiv{padding-left: 180px;}

/**保存按钮*/
#saveThematic{width: 100px;height: 30px;background: #D60500 none repeat scroll 0% 0%;
color: #FFF;text-align: center;line-height: 30px;border: medium none;margin-right: 10px;cursor: pointer;}

/**取消按钮*/
#cancelThematic{width: 100px;height: 30px;background: #0085FE none repeat scroll 0% 0%;margin-right: 10px;
color: #FFF;text-align: center;line-height: 30px;border: medium none;margin-right: 10px;cursor: pointer;}

/**补充图片*/
.imgsDivStyle{position:relative;width:272px;height:107px;float:left;}

.removeStyle{position:absolute;top:1px;right:1px;z-index:99;}

.addPicStyle{position:relative;width:270px;height:105px;}

/**上传按钮样式*/
.webuploader-pick {position: relative;display: inline-block;cursor: pointer;
background: #0085FE none repeat scroll 0% 0%;
padding: 10px 15px;color: #fff;text-align: center;border-radius: 3px;overflow: hidden;}

</style>

<script type="text/javascript">
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var userId = '${USER_ID_LONG}';
var userName = '${USER_NAME}';
var addPicsCount = 0;
var addPicsStr = '';

/**
 * 页面加载完成
 */
$(function(){
	//初始化标签控件
	$("#tags").tags({inputName: "tags" });
	//初始化时间控件
	$("#effictiveTime").datepicker({
		changeMonth: true,
		changeYear: true
	});
	//初始化验证
	initValidation();
	//初始化上传控件
	initWebUploader();
});

/**
 * 初始化验证
 */
function initValidation(){
	$("#addThematicAskForm").validator({
		theme:'yellow_right',
		rules:{
			checkTitle:function(element,param,field){
				var str;
				$.ajax({
					type:'POST',
					async:false,//此处为同步
					data:{"thematicTitle":element.value},
					url:'<%=request.getContextPath()%>/manageThematicAsk/getThematicAsksByTitle.action',
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str = flag || "已存在相同标题";
					}
				});
				return str;
			},
			checkPoint:[/^[1-9]+\d*$/,'请输入大于0的整数']
		},
		msgStyle:'margin-top:10px;margin-left:10px;',
		fields:{
			thematicTitle:{
				rule:'required;length[~50];checkTitle',
				msg:{
					required:"标题不能为空",
					length:"长度小于等于50个字符",
				}
			},
			thematicContent:{
				rule:'length[~200]',
				msg:{
					length:"长度小于等于200个字符"
				}
			},
			initialAnswer:{
				rule:'length[~200]',
				msg:{
					length:"长度小于等于200个字符"
				}
			},
			rewardsPoints:{
				rule:'checkPoint',
				msg:{
					checkPoint:"请输入大于0的整数"
				}
			}
		}
	});
}

/**
 * 初始化上传控件
 */
function initWebUploader(){
	//补充图片
	var uploader = WebUploader.create({
		//设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
		auto : true,
		//swf文件路径
		swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端
		server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',
		// 选择文件的按钮，可选，内部根据当前运行是创建，可能是input元素，也可能是flash。
		pick : {
			id : '#addPicsButton',
			multiple : true
		},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传。
		resize : false,
		// 只允许选择图片文件。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});
	
	// 接受文件后的操作
	uploader.on('uploadAccept', function(file, ret) {
		//图片数量
		if(addPicsCount >= 5){
			dialog.alert("最多上传"+addPicsCount+"张图片");
			return;
		}
		addPicsCount = addPicsCount + 1;
		//图片字符串
		addPicsStr = addPicsStr + ret._raw + ',';
		//拼接字符串
		var htmlStr = '';
		htmlStr += '<div class="imgsDivStyle">';
		htmlStr += '<div class="removeStyle">';
		htmlStr += '<img src="<%=request.getContextPath()%>/images/img/icon_remove.png" style="cursor:pointer;" onclick="removePic(this);"/>';
		htmlStr += '</div>';
		htmlStr += '<img src="'+ret._raw+'" class="addPicStyle"/>';
		htmlStr += '</div>';
		$("#imgsDiv").append(htmlStr);
	});
	
	//上传完刷新
	uploader.on( 'uploadFinished', function( file ,ret ) {
        uploader.reset();
    });
	
	//封面图片
	var covUploader = WebUploader.create({
		//设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
		auto : true,
		//swf文件路径
		swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端
		server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',
		// 选择文件的按钮，可选，内部根据当前运行是创建，可能是input元素，也可能是flash。
		pick : {
			id : '#selectCoverPicButton',
			multiple : false
		},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传。
		resize : false,
		// 只允许选择图片文件。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});
	
	// 接受文件后的操作
	covUploader.on('uploadAccept', function(file, ret) {
		$("#coverPic").val(ret._raw);
	});
}

/**
 * 移除图片
 */
function removePic(obj){
	//图片数量减1
	addPicsCount = addPicsCount - 1;
	if(addPicsCount < 0){
		addPicsCount = 0;
	}
	//图片字符串
	var picStr = $(obj).parent().parent().find(".addPicStyle").attr("src");
	addPicsStr = addPicsStr.replace(picStr+',','');
	//移除图片
	$(obj).parent().parent().remove();
}

/**
 * 保存
 */
function saveThematicAsk(){
	if($('#addThematicAskForm').isValid()){
		if(addPicsStr != ''){
			addPicsStr = addPicsStr.substr(0,addPicsStr.length-1);
		}
		var param = {};
		param.title = $("#thematicTitle").val();//标题
		param.content = $("#thematicContent").val();//问题补充
		param.initialAnswer = $("#initialAnswer").val();//初始答案
		param.addPics = addPicsStr;//补充图片
		param.coverPic = $("#coverPic").val();//封面图片
		param.tags = $("input[name='tags']").val();//标签
		param.effectiveTime = $("#effictiveTime").val();//有效期
		param.rewardPoints = $("#rewardsPoints").val();//奖励积分
		param.askerId = userId;//提问人id
		param.askerName = userName;//提问人姓名
		param.companyId = companyId;//公司id
		param.subCompanyId = subCompanyId;//子公司id
		//保存
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:param,
			url:'<%=request.getContextPath()%>/manageThematicAsk/addThematicAsk.action',
			success:function(data){
				if(data.rtnResult == 'SUCCESS'){
					dialog.alert("保存成功！",function(obj){
						window.location.href = '<%=request.getContextPath()%>/manageThematicAsk/toManageThematicAsk.action';
					});
				}else{
					dialog.alert("保存失败...");
				}
			}
		});
	}
}

/**
 * 取消
 */
function cancelThematicAsk(){
	window.history.go(-1);
}

</script>
</head>
<body>
	<div id="newThematicAskContent">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增专题回答</span>
		</div>
		<!-- <h3>新增专题回答</h3> -->
		<form id="addThematicAskForm">
			<div id="addThematicAskDiv">
				<!-- 标题 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<span>*</span>
						<em>标题：</em>
					</div>
					<div class="addRight">
						<input type="text" id="thematicTitle" name="thematicTitle"/>
					</div>
				</div>
				<!-- 问题补充 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>问题补充：</em>
					</div>
					<div class="addRight">
						<textarea id="thematicContent" name="thematicContent"></textarea>
					</div>
				</div>
				<!-- 答案 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>答案（初始）：</em>
					</div>
					<div class="addRight">
						<textarea id="initialAnswer" name="initialAnswer"></textarea>
						<br/>
						<em class="psStyle">答案（初始）输入内容后，即为满意答案，您将不可再次设置满意答案！</em>
					</div>
				</div>
				<!-- 补充图片 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>补充图片：</em>
					</div>
					<div class="addRight">
						<div id="addPicsButton">选择图片</div>
						<em class="psStyle">选填，最多选择5张图片，支持bmp、jpg、jpeg、gif、png格式的图片</em>
						<div id="imgsDiv">
							<%-- <div class="imgsDivStyle">
								<div class="removeStyle">
									<img src="<%=request.getContextPath()%>/images/img/icon_remove.png" style="cursor:pointer;" onclick="removePic(this);"/>
								</div>
								<img src="" class="addPicStyle"/>
							</div> --%>
						</div>
					</div>
				</div>
				<!-- 封面图片 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>封面图片：</em>
					</div>
					<div class="addRight">
						<input type="text" id="coverPic" disabled/>
						<div id="selectCoverPicButton">浏览...</div>
						<em class="psStyle">选填，只能选择一张图片</em>
					</div>
				</div>
				<!-- 标签 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>标签：</em>
					</div>
					<div class="addRight">
						<div id="tags" class="tags-box"></div>
						<em class="psStyle">最多输入5个标签，标签之间用空格或","隔开</em>
					</div>
				</div>
				<!-- 有效期 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>有效期：</em>
					</div>
					<div class="addRight">
						<input type="text" id="effictiveTime"/>
					</div>
				</div>
				<!-- 奖励积分 -->
				<div class="eachAddDiv">
					<div class="addLeft">
						<em>奖励积分：</em>
					</div>
					<div class="addRight">
						<input type="text" id="rewardsPoints" name="rewardsPoints"/>
					</div>
				</div>
				<!-- 按钮 -->
				<div id="buttonsDiv">
					<input type="button" id="saveThematic" onclick="saveThematicAsk();" value="保存"/>
					<input type="button" id="cancelThematic" onclick="cancelThematicAsk();" value="取消"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>