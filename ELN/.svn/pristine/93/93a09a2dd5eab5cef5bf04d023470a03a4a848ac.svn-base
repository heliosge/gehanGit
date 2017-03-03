<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>管理员管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>

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
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkIDCard.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>


<style type="text/css">

html, body{
	height: 100%;
}

.mmGrid,.mmPaginator{
    
}


.span_btus{
	margin:2px 2px;
}
.span_btu{
	display: inline-block;
	background-color: #4ABCF0;
	font-size: 12px;
	
	padding: 0px 5px;
	cursor: pointer;
	border-radius: 5px;
	min-width: 40px;
	text-align: center;
	color: #FFFFFF;
	margin: 0px 2px;
}
.span_btu_1{
	background-color: #34CC67;
}
.span_btu_2{
	background-color: #FF6766;
}
.span_btu_3{
	background-color: #F8CB0E;
}

</style>

<script type="text/javascript">

//切换内外讲师
function switchType(type){

	$("#teacherCategory").val(type);
	
	$("div.add_fr").find("input[type='button']").hide();
	$("input[name='userName']").val("");
	$("input[name='userName']").removeAttr("disabled");
	if(type==1){
		$("div.add_fr").find("input[type='button']").show();
		$("input[name='userName']").attr("disabled","disabled");
	}	
	
}

function getUserList(){
	
	var d = dialog({
        title: '人员列表',
        url:"<%=request.getContextPath()%>/teacher/listUser.action",
        lock:true,
        height: 530,
		width: 750
	}).showModal();
	
}

function addTeacherInfo(id){
	
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{id:id},
		url:"<%=request.getContextPath()%>/teacher/getUserInfo.action",
		success:function(data){
			var json = JSON.parse(data);
			
			 if(typeof json =="object"){
				  $("input[name='userName']").val(json.userName);
				  $("input[name='teacherName']").val(json.name);
				  $("input[name='eMail']").val(json.email);
				  $("input[name='cardId']").val(json.idCard);
				  $("input[name='education']").val(json.highEducation);
				  $("input[name='phoneNum']").val(json.mobile);
				  $("textarea[name='description']").val(""); 
				  if(json.sex==1){
					  $("input[name='xb']").eq(0).attr("checked",true);
				  }else{
					  $("input[name='xb']").eq(1).attr("checked",true);
				  }
				  
				  //隐藏掉提示框
				  $("span.msg-box").hide();
			 }else{
				 
				dialog.alert("加载数据失败，请重新选择！");	 
			}
	    }
	});
	
	
	
}
</script>
</head>
<body style="overflow-x:hidden;">
<div id="content_i" class='content'>	
	<div>
		<!-- <h3>新增讲师</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick='javascript:location = "<%=request.getContextPath()%>/teacher/list.action"'/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增讲师</span>
		</div>
	</div>
	<form action="" id='addForm'>
		<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>讲师分类：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" checked="checked" name="lx" onclick='switchType(1)'>
                <span>内部讲师</span>
                <input type="radio" name="lx" onclick='switchType(2)'>
                <span>外部讲师</span>
                <input type="button" value="选择讲师" class="te" onclick='getUserList()'>
                <input id='teacherCategory' name='teacherCategory' type='hidden' value='1'/> 
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>用户名：</em>
            </div>
            <div class="add_fr">
            	<input type="text" name="userName"   disabled="disabled" >
                <em>不小于6个字符，只能包含字母、数字、下划线</em>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>姓名：</em>
            </div>
             <div class="add_fr">
            	<input type="text" name="teacherName" >
            </div>
    	</div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>性别：</em>
            </div>
             <div class="add_fr">
            	<input type="radio" checked="checked" name="xb">
                <span>男</span>
                <input type="radio" name="xb">
                <span>女</span>
                <input id='sex' name='sex' type='hidden'/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>邮箱地址：</em>
            </div>
             <div class="add_fr">
            	<input type="text" name="eMail">
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>身份证号：</em>
            </div>
             <div class="add_fr">
            	<input type="text" name="cardId" >
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>学历：</em>
            </div>
             <div class="add_fr">
            	<input type="text" name="education">
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>手机号码：</em>
            </div>
             <div class="add_fr">
            	<input type="text" name="phoneNum">
            </div>
    	</div>
        <div class="add_gr" style="height: auto;margin-bottom: 5px;">
        	<div class="add_fl">
                <em>选择头像：</em>
            </div>
             <div class="add_fr" style='margin-top: 7px;'>
            	<!-- <input type="button" value="选择头像" id='picker' >
            	 用来存放文件信息
    			<div id="thelist" class="uploader-list"></div>
            	<input id='iconPath'name="iconPath" class='text-p' type="file" style='display:none'> -->
            	<div id="uploader" class="wu-example">
			    <!--用来存放文件信息-->
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择头像</div>
				    </div>
				</div>
				<div style='margin-top:5px '>
					<img  class='dom-hide' id='userIcon' style='width: 150px;height: 150px;display:none'>
				</div>
            </div>
            <input id='iconPath'name="iconPath" class='text-p' type="hidden" >
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>擅长领域：</em>
            </div>
            <div class="add_fr">
            	<textarea name="description"></textarea>
            </div>
        </div>
        <div class="button_cz">
        	<input type="button" value="保存" id='ctlBtn' >
            <input type="button" value="返回" class="back" onclick='javascript:location = "<%=request.getContextPath()%>/teacher/list.action"'>
        </div>
    </div>
    </form>
</div>
	<script  type='text/javascript'>
	//清除文件信息
	function cancelFile(_this,id){
		$(_this).closest("div.teacher-item").remove();
		//uploader.cancelFile( iconfile );
		$("#iconPath").val("");
	    $("#userIcon").hide();
	};
	
	//上传图片
	$(function(){
	//	var iconfile;
		var uploader = WebUploader.create({
			auto: true,
		    // swf文件路径
		    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

		    // 文件接收服务端。
		    server:'<%=request.getContextPath()%>/teacher/uploadImg.action',

		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: {id:'#picker',
	    		multiple:false
	    	},

		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    }
		});
	
		
		uploader.on( 'fileQueued', function( file ) {
			iconfile = file;
			$("#picker").find("div.teacher-item").remove();
		    $("#picker").append( '<div id="' + file.id + '" class="teacher-item">' +
		        '<h4 class="info">' + file.name + '</h4>' +
		        '<a class="close" href="javascript:void(0)" onclick="cancelFile(this,'+file.id+')">X</a>'+
		    '</div>' );
		 
		});
		
		
		uploader.on( 'uploadAccept', function( file ,ret ) {
			$("#iconPath").val(ret._raw);
			 $("#userIcon").attr("src",ret._raw).show();
		    //$( '#'+file.id ).find('p.state').text('已上传');
		    //上传完成后，进行表单的提交 
			uploader.reset();
		});
		
	//用户名称校验
	function checkUerName(){
		var ret = true;
		$.ajax({
    		type:"POST",
    		async:false,  //默认true,异步
    		data:{userName:$("input[name='userName']").val()},
    		url:"<%=request.getContextPath()%>/teacher/checkName.action",
    		success:function(data){
    			if("SUCCESS"!=data){
    				/* artDialog.alert("名称重复"); */
    				ret = false;
    			}
    	    }
    	});
		return ret;
	}
	
	//手机号码校验
	function checkMobile(str) { 
		var re = /^1\d{10}$/ ;
		if (re.test(str)){ 
			return true;
			} 
		else { 
			return false;
		}
	}
	
	//加入自定义校验
	 jQuery.validator.addMethod("checkUser", function(value, element) {
		    return this.optional(element) || /^[a-zA-Z0-9_]+$/.test(value);
		});   
	
	 jQuery.validator.addMethod("repeatName", function(value, element) {
		    return this.optional(element) ||checkUerName();
		});  
	
	
	 jQuery.validator.addMethod("valiIdCard", function(value, element) {
		    return this.optional(element) ||valiIdCard(value);
		}); 
	 jQuery.validator.addMethod("checkMobile", function(value, element) {
		    return this.optional(element) ||checkMobile(value);
		});
	 
	 //检测身份证是否合法
	 function valiIdCard(idCard){
		  var checkFlag = new clsIDCard(idCard);
		  if (!checkFlag.IsValid()) {
		   return false;
		  } 
		  return true;
	 }
	
	 var  validate = $("#addForm").validate({ 
		   focusCleanup:true,    
		   ignore: "", 
	       rules: {  
	    	   userName: {  
                required:true,  
                rangelength:[6,30],  
                checkUser:true,
                repeatName:true
        		},
        	 teacherName:{
        		 required:true,
   		   	maxlength:30
   		   },
			   eMail:{
				   required:true,  
				   email:true ,
				   maxlength:40 
			   },
			   cardId:{
				   required:true, 
				   number:true,
				   valiIdCard:true
			   } ,
			   phoneNum:{
				   number:true, 
				   rangelength:[11,11],
				   checkMobile:true
			   },
			   description:{
				 	maxlength:200 
			   }
	       },  
	       messages:{  
	           userName: {  
                required:"请输入或者选择用户名",  
                rangelength:"输入长度必须介于 6 和 30 之间的字符串",
                checkUser:"只能输入字母数字下划线",
             	  repeatName:"用户名存在重复"
     	   },
     	   teacherName:{
     		   required:"请输入姓名",
     		   maxlength:jQuery.validator.format("输入长度最多是 {0}位字符") 
     	   },
     	   eMail:{
                required:"请输入电子邮箱",  
				   email:"必须输入正确格式的电子邮件",
	     		   maxlength:jQuery.validator.format("输入长度最多是{0}位字符") 
			   },
     	   cardId:{
				   required:"请输入身份证号码",  
				   number:"请输入合法身份证号码",
				   valiIdCard:"请输入合法身份证号码"
			   },
			   phoneNum:{
				   number:"请输入合法号码", 
				   rangelength: "请输入正确的号码", 
			   	checkMobile:"请输入正确的号码"
			   },
			   description:{
	     		   maxlength:jQuery.validator.format("输入长度最多是 {0}位字符") 
			   }
			   
	       }  
	      /*  showErrors:showErrors   */
	   })
	function validateForm() {  
	   //validate方法参数可选  
	  return validate.form();  
	}  
	// validateForm();
	 
	
   $("#ctlBtn").on( 'click', function() {
	   
	   if(validateForm()){
		   saveTeacherInfo();
	   }
          
    });
   
   //数据的保存
	function saveTeacherInfo(){
			
			var param = {};
			param.teacherCategory=$("#teacherCategory").val();
			
			param.sex=2;
			if($("input[name='xb']").eq(0).is(":checked")){
				param.sex=1;
			}
			param.userName = $("input[name='userName']").val();
			param.teacherName = $("input[name='teacherName']").val();
			param.eMail = $("input[name='eMail']").val();
			param.cardId = $("input[name='cardId']").val();
			 param.education = $("input[name='education']").val()||"";
			param.phoneNum = $("input[name='phoneNum']").val()||"";;
			param.iconPath = $("#iconPath").val()||"";
			param.description=$("textarea[name='description']").val()||""; 
			
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		url:"<%=request.getContextPath()%>/teacher/save.action",
	    		success:function(data){
	    			if("SUCCESS"==data){
	    				dialog.alert({content:"保存成功",lock:true},function(){
    						location = "<%=request.getContextPath()%>/teacher/list.action"
	    				});
	    			}else{
	    				dialog.alert("保存失败,请重试");
	    			}
	    	    }
	    	});
			
		}
})


</script>
	
</body>
</html>
