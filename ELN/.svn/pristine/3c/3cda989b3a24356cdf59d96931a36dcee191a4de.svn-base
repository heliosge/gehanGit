<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>编辑学习计划基本信息</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<style type="text/css">

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}
.n-icon,.n-arrow{
	margin-left: 0px!important;
	margin-right: 0px!important;
}
</style>

<script type="text/javascript">

var learnPlan={};
learnPlan= ${learnPlan};

$(function(){
	
	
	//时间插件
	$("#beginTime").datetimepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd',
		minDate: new Date() 
	});

	$("#endTime").datetimepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat : 'yy-mm-dd',
		minDate: new Date() 
	});
   	if(learnPlan){
   		//修改
   		$("#top_page_name").text("修改计划基本信息");
   		
   		//回填值
   		$("#id").val(learnPlan.id);
   		$("#name").val(learnPlan.name);
   		$("#type").val(learnPlan.type);
   		$("#description").val(learnPlan.description);
   		$("#beginTime").val(learnPlan.beginTime);
   		$("#endTime").val(learnPlan.endTime);
   		$("#coverImage").val(learnPlan.coverImage);
   		$("#previewPath").attr("src",learnPlan.coverImage);
   	}else{
   		
   	}
   	
	
	    //上传
   		var uploader = WebUploader
   				.create({
   					auto : true,
   					// swf文件路径
   					swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

   					// 文件接收服务端。
   					server : '<%=request.getContextPath()%>/learnPlan/uploadImg.action',

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
   				"width" : "278px",
   				"height" : "105px"
   			});
   			if(!ret._raw||ret._raw==" "){
   				$("#sapn_fmtp").show();
   			}else{
   				$("#sapn_fmtp").hide();
   			}
   		});

   	


	
	//返回按钮
	$("#backBtu").click(function(){
		dialog.confirm('确认返回吗？', function(){ 
		    	window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
		    
		});
	});
	
 
	
    
    	initValidate();
	
	
});

/**
 * 表单验证
 */
function initValidate() {
	$('form').validator({
		theme : 'yellow_right',
		rules : {
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
			checkname: [/^([\u4E00-\u9FA5]|\w)*[^_]$/,'请输入正确的名称(中文,数字,字母)'],
			checkPlanName:function(element,param,field){
				var str;
				$.ajax({
					type:"post",  
	                 dataType:"text",  
					async:true,  //默认true,异步
					data:{
						id:function(){if(learnPlan){
		                	 return learnPlan.id;
		                 }else
		                	 return 0;},
						name:$.trim($("#name").val())
						},
					url:"<%=request.getContextPath()%>/learnPlan/checkLearnPlanName.action",
					success:function(data){
						var flag = true;
						if(data=="FAIL"){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		msgStyle:"margin-top:10px;",
		fields : {
			name : {
				rule : "required;length[~21];cheakname;checkPlanName",
				msg : {
					required : "计划名称不可为空",
					length : "长度需小于等于20个字符"
				}
			},

			description : {
				rule : "length[~1001]",
				msg : {
					length : "最多1000个字符"
				}
			}
			/*,
			beginTime:{
				rule : "required",
				msg : {
					required : "开始时间不能为空"
				}
			},
			endTime:{
				rule : "required",
				msg : {
					required : "结束时间不能为空"
				}
			}*/
		}
	});
}

function doNext(){
		if(!learnPlan||!learnPlan.id){
			dialog.alert("请先保存");
			return;
		}
	    window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanStageEdit.action?planId="+learnPlan.id;

}

function picChange(){
	var coverImage = $.trim($("#coverImage").val());
	if(!coverImage){
		$('#span_fmtp').show();
	}else{
		$('#sapn_fmtp').hide();
	}
}

function beforeSave(){
	$("form").submit();

}

//保存
function save(callBack){
	
	$('form').isValid(function(v) {
		var param = new Object();
		
		param.name = $.trim($("#name").val());
		param.type = $.trim($("#type").val());
		param.beginTime = $.trim($("#beginTime").val());
		param.endTime = $.trim($("#endTime").val());
		param.description = $.trim($("#description").val());
		param.coverImage = $.trim($("#coverImage").val());

		if(!param.coverImage){
			$("#sapn_fmtp").show();
			v=false;
		}else{
			$("#sapn_fmtp").hide();
		}
		
		if(v){
			
			var urlStr = "<%=request.getContextPath()%>/learnPlan/addLearnPlan.action";
			if(learnPlan&&learnPlan.id){
				//修改
				urlStr = "<%=request.getContextPath()%>/learnPlan/updateLearnPlan.action";
				param.id = learnPlan.id
				
			}
			dialog.confirm('确认保存吗？', function(){ 
					$.ajax({
			    		type:"POST",
			    		async:true,  //默认true,异步
			    		contentType:"application/json;charset=utf-8",
			    		data:JSON.stringify(param),
			    		dataType:"json",
			    		url:urlStr,
			    		success:function(data){
			    			if(data&&data.result&&data.result == "SUCCESS"&&data.id){
			    	
			    				learnPlan=param;
			    				learnPlan.id=data.id;
			    				
			    				if(callBack&&$.isFunction(callBack)){
			    					callBack();
			    				}else{
			    					dialog.alert('保存成功！',function(){
			    						window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
			    					});
			    				}
			    			}else if(data=="SUCCESS"){
			    				
			    				if(callBack&&$.isFunction(callBack)){
			    					callBack();
			    				}else{
			    					dialog.alert('保存成功！',function(){
			    						window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
			    					});
			    				}
			    				
			    			}else {
			    				dialog.alert('保存失败！');
			    				
			    			}

			    	    }
			    	});
			    
			});
		}else{
			
		}});

}

</script>
</head>
<body style="">
<div class="content">
	<!-- <h3>安排课程</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">安排课程</span>
	 </div>
    <div class="make_sc">
    	<div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png" />
            <span>1</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect1.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png" />
            <span class="span_2">2</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png" />
            <span class="span_2">3</span>
        </div>
        <div class="bt">
        	<span class="bt_1">创建计划基本信息</span>
            <span>新增计划阶段与内容</span>
            <span class="last_bt">选择计划人员</span>
        </div>
        
       
        
        
    </div>
    <form >
	<div class="lesson_add_2" style=" border:none;">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>计划名称：</em>
            </div>
            <div class="add_fr">
            	<input id="name" type="text" name="name"/>
                <em>最多20个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>

        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>计划时间：</em>
            </div>
            <div class="add_fr">
            	<input id=beginTime type="text" name="beginTime" style="width:135px;" readonly data-rule="开始日期: required"/>
                <span>至</span>
            	<input id="endTime" type="text" name="endTime" style=" width:135px;" readonly data-rule="结束日期: required; match[gt, beginTime, date]"/>
            	
          
            </div>
        </div>
       <div class="add_gr_1" style="margin-bottom:20px;">
        	<div class="add_fl">
                <em>计划描述：</em>
            </div>
            <div class="add_fr">
            	<textarea id="description" name="description"></textarea>
                <em>最多1000个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>
             <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
        	    <span>*</span>
                <em>封面图片：</em>
            </div>
            <div class="add_fr">
            	<div id="uploader" class="wu-example" style="line-height: 17px;">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择图片</div>
							<em>建议图片大小为（宽*高）：250*170</em>
						</div>
						<input id="coverImage"  type="hidden">
				</div>
				
				    <span id="sapn_fmtp" class="msg-box n-right" for="coverImage" style="display: none;">
						<span class="msg-wrap n-error" role="alert">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">封面图片不能为空</span>
						</span>
					</span>
					<p class="tp">
						<img style="width: 300px;" id="previewPath" src="<%=request.getContextPath()%>/images/img/left_2.png" />
					</p>
            </div>
        </div>

        
       
        
    </div>
    </form >
      <div class="button_cz fl" style="margin-top:20px; width:1046px;">
        	<input type="button" name="saveBtn" onclick="save()" value="保存" />
            <input type="button" name="nextBtn" id="nextBtu" onclick="save(doNext)"  value="保存并下一步" class="back" />
            <input type="button" name="backBtn" id="backBtu" value="取消" class="btn_n" />
    </div>


        
</div>

	
</body>
</html>
