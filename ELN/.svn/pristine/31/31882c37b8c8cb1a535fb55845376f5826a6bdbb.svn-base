<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改课件</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
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
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>
</head>



<body>
<script type="text/javascript">
var courseware = ${courseware};

$(function(){
	fillCoursewareInfo();
	initValidate();
	
});

function fillCoursewareInfo(_this,id){
	$("#name").val(courseware.name);
	//$("#tags").val(courseware.tags);
	$("#tags").tags({
		valueString: courseware.tags,
        inputCls: "noborder",
        inputName: "tags" });
	$("#descr").val(courseware.descr);
	$("#durationString").val(courseware.durationString);
	courseware.type == 3 ? $("#durationDiv").show():'';
}

function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = {};
			param.id=courseware.id;
			param.name=$("#name").val();
			param.tags = $("input[name='tags']").val();
			param.descr = $("#descr").val();
			param.durationString = $("#durationString").val();
			 $.ajax({
		 		type:"POST",
		 		async:true,  //默认true,异步
		 		data:param,
		 		url:"<%=request.getContextPath()%>/res/updateResCourseware.action",
		 		success:function(data){
		 			if(data=="SUCCESS"){
		 				dialog.alert("修改成功！",function(){cancel();});
		 				//dialog.alert("修改成功！");
		 			}else{
		 				dialog.alert("修改失败！");
		 			}
		 	    }
			 });
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
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
	<!-- <h3>修改课件</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改课件</span>
	 </div>
    <form id="addForm">
    <div class="lesson_add">
        
        <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>课件名称：</em>
            </div>
             <div class="add_fr" >
            	<input type="text" id="name" name="name"/>
            </div>
    	</div>
    	<div class="add_gr" id="durationDiv">
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
            	<textarea id="descr"></textarea>
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
</body>
</html>
