<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>编辑晋升路径</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- validation -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
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

var path = ${path};

$(function(){
   	if(path&&path.id){
   		//回填值
   		$("#name").val(path.name);
   		$("#pathDescription").val(path.desc);
   		
   	}else{
   		
   	}
   	
	
	


	
	
	
	//验证名称(中文，数字，字母)
	$.validator.addMethod(
	    "onlyName",
	    function (value, element, param){
	    	var withOutChinese = /^([\u4E00-\u9FA5]|\w)*[^_]$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入正确的名称"
    );
	
	//开始验证     
	$('#path_form').validate({
		debug:true,
	    /**//* 设置验证规则 */    
	    rules: {     
	    	name: {     
	            required:true,
	            maxlength:30,
	            //withOutChinese: true,
	             remote:{  
	                 url:"<%=request.getContextPath()%>/map/checkPathName.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{name:function(){
	                	 return $.trim($("#name").val());
	                 },id:function(){if(path&&path.id){
	                	 return path.id;
	                 }else
	                	 return 0;}},  
	                 dataFilter: function(data, type) {  
	                 	if(data=="SUCCESS"){
	                 	
	                 		return true;
	                 	}else{
	                 		return false;
	                 	}
	                 }
	              }
	           
	        },
	  
	        pathDescription: {
	        	maxlength:2000
	        }
	    },      
	    /**//* 设置错误信息 */    
	    messages: {     
	    	name: {         
	        	required:"路径名称不可为空",
	        	maxlength:"路径名称30个字符之内",
	        	remote:"路径名称已经存在",
	            onlyName:"请输入正确的路径名称(中文,数字,字母)"
	        },
	    
	        pathDescription: {     
	            maxlength:"最多2000个字符"
	        }
	    },      
	    /**//* 设置验证触发事件 */
	    onsubmit:true,
	   
	    submitHandler: function (){
	    	//alert("sunmit");
	    	savePath();
	    }
	}); 
});

function beforePathSave(){
	$("#path_form").submit();
}

function closePathEdit(){
	dialog.confirm('确认取消吗？', function(){ 
		window.parent.artDialog.close();
	    
	});
}

//保存
function savePath(){
	
	var param = new Object();
	
	param.name = $.trim($("#name").val());
	param.desc = $.trim($("#pathDescription").val());
	param.isDisabled=0;
	
	var urlStr = "<%=request.getContextPath()%>/map/savePath.action";
	
	if(path){
		//修改
		urlStr = "<%=request.getContextPath()%>/map/updatePath.action";
		param.id = path.id
		
	}
	
	dialog.confirm('确认保存吗？', function(){ 
		
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		dataType:"json",
	    		url:urlStr,
	    		success:function(data){
	    			if(data&&data.result == "SUCCESS"){
	    				if(data.id){
	    					param.id=data.id;
	    				}
	    				dialog.alert('保存成功！');
	    				window.parent.savePath(param);
	    				window.parent.artDialog.close();
	    				
	    			}else if(data== "SUCCESS"){
	    				dialog.alert('保存成功！');
	    				window.parent.savePath(param);
	    				window.parent.artDialog.close();
	    			}
	    			else{
	    				
	    				dialog.alert('保存失败！');
	    			}
	    		
			    	
	    	    }
	    	});
	    
	});
}

</script>

</head>
<body style="">
<style type="text/css">
form{width:720px}
.lesson_add_2{width:720px}
.lesson_add_2 .add_gr{width:700px}
.lesson_add_2 .add_gr .add_fl{width:100px}
.lesson_add_2 .add_gr .add_fr{width:540px}
</style>
<div  class='dialog-content'>
 
    <form id="path_form">
     
	<div class="lesson_add_2" style=" border:none;">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>路径名称：</em>
            </div>
            <div class="add_fr">
            	<input id="name" type="text" name="name"/>
                <em>最多30个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>
    
       <div class="add_gr" style="margin-bottom:20px;">
        	<div class="add_fl">
                <em>路径描述：</em>
            </div>
            <div class="add_fr">
            	<textarea style="height:150px" id="pathDescription" row="3" name="pathDescription"></textarea>
                <em>最多2000个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>

    </div>
    </form >
      <div class="button_cz fl" style="margin-top:20px; width:540px;padding-top: 30px;">
        	<input type="button" name="saveBtn" onclick="beforePathSave()" value="保存" />
            <input type="button" name="backBtn" onclick="closePathEdit()" value="取消" class="btn_n" />
    </div>


        
</div>

	
</body>
</html>
