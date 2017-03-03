<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>增加部门</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- validation -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js"></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>


<style type="text/css">

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

</style>

<script type="text/javascript">

var departmentBean = ${departmentBean};
var userList = ${userList};    //人员
var departmentList = ${departmentList}; //上级部门
var isParent = "${isParent}";

$(function(){
	
	//负责人
	$.each(userList, function(index, data){
		$("#leaderUserId").append("<option value='"+data.id+"'>"+data.name+"</option>");
	});
	
	//上级部门
	$("#parentDepartmentId").append("<option value='0'></option>");
	$.each(departmentList, function(index, data){
		$("#parentDepartmentId").append("<option value='"+data.id+"'>"+data.name+"</option>");
	});
   	
   	if(departmentBean){
   		//修改
   		$(".top_page_name").text("修改部门");
   		
   		//回填值
   		$("#code").val(departmentBean.code);
   		$("#name").val(departmentBean.name);
   		$("#parentDepartmentId").val(departmentBean.parentDepartmentId);
   		$("#leaderUserId").val(departmentBean.leaderUserId);
   		$(":radio[name='status'][value='"+departmentBean.status+"']").attr("checked", "checked");
   		$("#memo").val(departmentBean.memo);
   		
   		//不可以选择自己作为上级部门
   		$("#parentDepartmentId option[value='"+departmentBean.id+"']").remove();
   		
   	}else{
   		
   	}
	
	//返回按钮
	$("#backBtu").click(function(){
		$.ligerDialog.confirm('确认返回吗？', function(yes){ 
			if(yes){
				window.location.href = "<%=request.getContextPath()%>/manageDepartment/departmentList.action";
		    }
		});
	});
	
	//验证部门编号只包含数字字母-_
	$.validator.addMethod(
	    "withOutChinese",
	    function (value, element, param){
	    	var withOutChinese = /^[\w-]+$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入字母和数字的组合(可以有@_.-)"
    );
	
	//表单验证
	//开始验证     
	$('form').validate({
		debug:true,
	    /**//* 设置验证规则 */    
	    rules: {     
	        name: {     
	            required:true,
	            maxlength:20,
	            remote:{  
	                 url:"<%=request.getContextPath()%>/manageDepartment/checkName.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{name:function(){
	                	 	return $.trim($("#name").val());
	                 	  }
	                 },  
	                 dataFilter: function(data, type) {  
	                 	if(parseInt(data) > 0){
	                 		if(departmentBean){
	                 			if($.trim($("#name").val()) == departmentBean.name){
	                 				return true;
	                 			}
	                 		}
	                 		return false;
	                 	}else{
	                 		return true;
	                 	}
	                 }  
	              }
	        },
			code: {     
		        required:true,
		        maxlength:20,
		        withOutChinese:true,
		        remote:{  
	                 url:"<%=request.getContextPath()%>/manageDepartment/checkCode.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{name:function(){
	                	 	return $.trim($("#code").val());
	                 	  }
	                 },  
	                 dataFilter: function(data, type) {  
	                 	if(parseInt(data) > 0){
	                 		if(departmentBean){
	                 			if($.trim($("#code").val()) == departmentBean.code){
	                 				return true;
	                 			}
	                 		}
	                 		return false;
	                 	}else{
	                 		return true;
	                 	}
	                 }  
	              }
		    },
	        memo: {
	        	maxlength:100
	        }
	    },      
	    /**//* 设置错误信息 */    
	    messages: {     
	        name: {         
	        	required:"部门名称不可为空",
	        	maxlength:"部门名称最多20个字符",
	        	remote:"此部门名称已经存在"
	        },
	        code: {     
	        	required:"部门编码不可为空",
	        	maxlength:"部门编码最多20个字符",
	        	withOutChinese:"部门编码只允许包含字母，数字，下划线，横线",
	        	remote:"此部门编码已经存在"
		    },
		    memo: {
	        	maxlength:"简介最多100个字符"
	        }
	    },      
	    /**//* 设置验证触发事件 */
	    onsubmit:true,
	    /**//* 设置错误信息提示DOM */    
	    errorPlacement: function(error, element) {   
	    	$(element[0]).parent().next().find(".validation_span").text(error.html()).show();  
	    },
	    success:function(error, element){
	    	$(element[0]).parent().next().find(".validation_span").text("");  
        },
	    submitHandler: function (){
	    	//dialog.alert("sunmit");
	    	save();
	    }
	});
});


function beforeSave(){
	$("form").submit();
}

//保存
function save(){
	
	var param = new Object();

	param.code = $.trim($("#code").val());
	param.name = $.trim($("#name").val());
	param.parentDepartmentId = $("#parentDepartmentId").val();
	param.leaderUserId = $("#leaderUserId").val();
	param.status = $(":radio[name='status']:checked").val();
	param.memo = $.trim($("#memo").val());
	
	var urlStr = "<%=request.getContextPath()%>/manageDepartment/addManageDepartment.action";
	
	if(departmentBean){
		//修改
		urlStr = "<%=request.getContextPath()%>/manageDepartment/updateManageDepartment.action";
		param.id = departmentBean.id;
		
		//如果是其他部门的上级部门，不可以改为为启用
		if(isParent && param.status == "1"){
			$.ligerDialog.warn("此部门是其它部门的上级部门，不可以改为未启用!");
			return false;
		}
		
	}
	
	$.ligerDialog.confirm('确认保存吗？', function(yes){ 
		if(yes){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:param,
				url:urlStr,
				success:function(data){
					
					if(data == "SUCCESS"){
						
						$.ligerDialog.success('保存成功！', "提示", function(){
							window.location.href = "<%=request.getContextPath()%>/manageDepartment/departmentList.action";
						});

	    			}else{
	    				
	    				$.ligerDialog.warn('保存失败！', "提示", function(){
							window.location.href = "<%=request.getContextPath()%>/manageDepartment/departmentList.action";
						});
	    				
	    			}
			    }
			});
	    }
	});
	
}

</script>
</head>
<body style="">

		<div class="top_page_name">增加部门</div>

		<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;">
		
			<div class="search_div">
			
			<form >
			<table class="param_table" cellpadding="0" cellspacing="0" style="margin-top:10px;">
				<tr>
					<td style="width:100px;" align="right">
						<span>部门名称：</span>
					</td>
					<td style="width:220px;">
						<input id="name" type="text" name="name" class="input_0" />
						<span class="need_span">*</span>
					</td>
					<td style="width:160px;">
						<span class="validation_span"></span>
					</td>
					<td style="width:100px;" align="right">
						<span>部门编号：</span>
					</td>
					<td style="width:220px;">
						<input id="code" type="text" name="code" class="input_0" />
						<span class="need_span">*</span>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
				</tr>
				<tr>
					<!-- <td style="" align="right">
						<span>上级部门：</span>
					</td>
					<td style="">
						<select id="parentDepartmentId" name="parentDepartmentId" class="input_0" style="width:202px;">
							
						</select>
					</td> -->
					<td>
						
					</td>
					<td style="display:none;" align="right">
						<span>负责人：</span>
					</td>
					<td style="display:none;">
						<select id="leaderUserId" name="leaderUserId" class="input_0">
							
						</select>
					</td>
					<td style="display:none;">
						
					</td>
				</tr>
				<tr style="display:none;">
					<td style="" align="right">
						<span>是否启用：</span>
					</td>
					<td style="">
						<input type="radio" id="status" name="status" value="0" checked="checked"/> 已启用
						<span style="margin-left:35px;"></span>
						<input type="radio" id="status" name="status" value="1" /> 未启用
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
					<td>
						
					</td>
					<td>
						
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td style="width:100px;" align="right">
						<span>部门简介：</span>
					</td>
					<td colspan="4">
						<textarea id="memo" name="memo" style="width:600px;height:100px;" class="input_0"></textarea>
					</td>
					<td>
						<span class="validation_span"></span>
					</td>
				</tr>
			</table>
			</form>
	
		</div>
	
	<div align="right" style="margin-right:100px;">
		<button class="btu_0" onclick="beforeSave()">保存</button>
		<button class="btu_0" id="backBtu">返回</button>
	</div>
	
</div>

</body>
</html>
