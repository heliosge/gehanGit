<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>积分管理</title>
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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">

</style>

<script type="text/javascript">

//var listTeacherBean = ${listTeacherBean}; //部门

$(function(){

	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/integral/getListByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        checkCol: true,
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	
        	return param;
        },
        cols: [{title: 'ruleId', align:'center', name: 'ruleId', width:60, hidden:true},
			   {title: '模块', align:'center', name: 'moduleName', width:50},
			   {title: '动作', align:'center', name: 'actionName', width:50},
			   {title: '单次积分', align:'center', name: 'integral', width:50},
			   {title: '是否禁用', align:'center', name: 'isEnabled', width:50,renderer:function(val, item, rowIndex){
				   if(val == 0){
					   return "禁用";
				   }else if(val == 1){
					   return "启用";
				   }}},
			  
			   {title: '描述', align:'center', name: 'ruleDesc', width:40,},
			    
			   {title: '操作', align:'center', name: 'ruleId', width:60, renderer:function(val, item, rowIndex){
				   
				   
				   var buttonStr = '<a class="a-btn" href="#" onclick="editIntegral('+val+')" >修改</a>' +
				   				   '<a class="a-btn" href="#" onclick="deleteIntegral('+val+')" >删除</a>';
				   				   
				   	if(item.isEnabled==1){
				   		buttonStr+='<a class="a-btn" href="#" onclick="disabledIntegral('+val+')" >禁用</a>';
				   	}else{
				   		buttonStr+='<a class="a-btn" href="#" onclick="enabledIntegral('+val+')" >启用</a>';
				   	}			   
				   
				   return "<div class='span_btus' >" + buttonStr + "</div>";
			   }}
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });

	 $("body").data("ruleHtml",$("#ruleDialog").html());
	$("#ruleDialog").remove();
});

//查询
function search(){
	$('#exampleTable').mmGrid().load({"page":1});
}

//删除讲师
function deleteTeacher(id){
	
	dialog.confirm("请确认删除吗?",function(obj){
    	 $.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{teacherId:id},
	    		url:"<%=request.getContextPath()%>/teacher/deleteTeacherInfo.action",
	    		success:function(data){
	    			if(data=="SUCCESS"){
	    				dialog.alert("删除成功！");
		    			search();
	    			}else{
	    				dialog.alert("删除失败！");
	    			}
	    	    }
 		 })
     })

}

//删除批量
function deleteBatch(){
	
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	
	if(selectRows.length > 0){
		dialog.confirm('确认删除吗？', function(){ 
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.ruleId);
	    	});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{ids:param.join("|")},
	    		url:"<%=request.getContextPath()%>/integral/deleteBatch.action",
	    		success:function(data){
	    			 if("SUCCESS"==data){
						 dialog.alert("操作成功");
					}else{
						dialog.alert("操作失败");
					} 
	    			search();
	    	    }
	    	});
		});
	}else{
		//alert("请先选择数据！");
		dialog.alert('请先选择数据！');
	}
}

//删除单条积分
function deleteIntegral(id){
	
	dialog.confirm('确认删除吗？', function(yes){ 
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
	   		data:{ruleId:id},
			url:"<%=request.getContextPath()%>/integral/delete.action",
			success:function(data){
				 if("SUCCESS"==data){
					 dialog.alert("操作成功");
				}else{
					dialog.alert("操作失败");
				} 
				search();
		    }
		});
	})
}


function enabledIntegral(id){
	
	dialog.confirm('确认启用吗？', function(){ 
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
    		data:{ruleId:id,isDisabled:1},
			url:"<%=request.getContextPath()%>/integral/disabled.action",
			success:function(data){
				 if("SUCCESS"==data){
					dialog.alert("操作成功");
				}else{
					dialog.alert("操作失败");
				} 
				search();
		    }
		});
	})
}
function disabledIntegral(id){
	
	dialog.confirm('确认禁用吗？', function(){ 
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
    		data:{ruleId:id,isDisabled:0},
			url:"<%=request.getContextPath()%>/integral/disabled.action",
			success:function(data){
				 if("SUCCESS"==data){
					 dialog.alert("操作成功");
				}else{
					dialog.alert("操作失败");
				} 
				search();
		    }
		});
	})
}

//批量禁用
function disableBatch(){
	
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	
	if(selectRows.length > 0){
		dialog.confirm('确认批量禁用吗？', function(yes){ 
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.ruleId);
	    	});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{ids:param.join("|")},
	    		url:"<%=request.getContextPath()%>/integral/disableBatch.action",
	    		success:function(data){
	    			if("SUCCESS"==data){
	    				 dialog.alert("操作成功");
	    			}else{
	    				dialog.alert("保存失败");
	    			}
		    		search();
	    	    }
	    	});
		});
	}else{
		//alert("请先选择数据！");
		dialog.alert('请先选择数据！');
	}
}

//新增积分规则信息
function addRuleInfo(){
	
	//打开设置框
	var d =dialog({
		title: '新增积分',
		width:460,
		height:420,
	    content:$("body").data("ruleHtml") ,
	    okValue: '确定',
	    ok: function () {
	    	if(validate()){
	    		saveIntRule();
	    	}else{
		        return false;
	    	}
	    },
	    cancelValue: '取消',
	    cancel: function () {}
	})
	d.showModal();
	//初始化数据
	initRule()
}

//初始化数据
function initRule(){
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		url:"<%=request.getContextPath()%>/integral/initRule.action",
		success:function(data){
			//alert($("#proModel").html())
			$("body").data("data",data);
			var optionHtml ="<option value='-1'>请选择模块</option>"
			$.each(data,function(k,v){
				optionHtml+="<option value='"+k+"'>"+k+"</option>";
			})
			$("#proModel").html(optionHtml);
	    }
	});
}


//修改积分规则
function editIntegral(id){
	
	//打开设置框
	var d =dialog({
		title: '修改积分',
		width:460,
		height:420,
	    content:$("body").data("ruleHtml") ,//$("#ruleDialog") ,
	    okValue: '确定',
	    ok: function () {
	    	if(validate()){
	    		saveIntRule();
	    	}else{
		        return false;
	    	}
	    },
	    cancelValue: '取消',
	    cancel: function () {}
	})
	d.showModal();
	//初始化数据
	initEditRule(id)
}


//初始化需要修改的数据
function initEditRule(id){
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{ruleId:id},
		url:"<%=request.getContextPath()%>/integral/queryInt.action",
		success:function(data){
			var param = data;
			//值赋值给页面
			$("#proModel").html("<option>"+param.moduleName+"<option>").attr("disabled",true);
			$("#actionCode").html("<option value='"+param.actionCode+"'>"+param.actionName+"</option>").attr("disabled",true);
			$("#ruleId").val(param.ruleId);
			$("#actionCode").val(param.actionCode);
			$("select[name='timeRange']").val(param.timeRange);
			changeTimeRange();
			
			$("input[name='integral']").val(param.integral);
			if(param.isEnabled==1){
				
				$("input[name='isEnabled']").attr("checked",true);
			}else{
				$("input[name='isEnabled']").attr("checked",false);
			}
			$("input[name='maxTimes']").val(param.maxTimes);
			$("[name='ruleDesc']").val(param.ruleDesc);
	    }
	});
}


//修改模块时，对应的动作也需要跟着改变
function changeModel(_this){
	var model = $(_this).val();
	var optionHtml ="<option value='-1'>请选择规则</option>"
	if(model==-1){
		$("#actionCode").html(optionHtml);
	}else{
		var data = $("body").data("data");
		
		$.each(data[model],function(k,v){
			optionHtml+="<option value='"+k+"'>"+v+"</option>"
		})
		$("#actionCode").html(optionHtml);
	}
}

function changeTimeRange(_this){
	
	var val = $(_this).val()||$("select[name='timeRange']").val();
	
	if(val==3||val==4){
		
		$("#maxTimesGroup").show();
		$("input[name='maxTimes']").val("");
	}else{
		
		$("#maxTimesGroup").hide();
	}
}

//保存规则
function saveIntRule(){
		
	var param = {}; 
	if($("#ruleId").val()&&$.trim($("#ruleId").val())!=""){
		param.ruleId=$.trim($("#ruleId").val());
	}
	param.actionCode=$.trim($("#actionCode").val());
	param.timeRange=$.trim($("select[name='timeRange']").val());
	param.integral=$.trim($("input[name='integral']").val());
	if($("input[name='isEnabled']").is(":checked")){
		param.isEnabled=1;
	}else{
		param.isEnabled=0;
	}
	param.maxTimes=$.trim($("input[name='maxTimes']").val());
	param.ruleDesc=$.trim($("[name='ruleDesc']").val());
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		contentType:"application/json; charset=utf-8",
		data:JSON.stringify(param),
		url:"<%=request.getContextPath()%>/integral/save.action",
		success:function(data){
			if("SUCCESS"==data){
				dialog.alert("保存成功");
			}else{
				dialog.alert("保存失败");
			}
			search();
	    }
	});
}

//加入自定义的校验函数
jQuery.validator.addMethod("checkSelect", function(value, element) {
	    return this.optional(element) || -1!=value;
	}); 
jQuery.validator.addMethod("positiveinteger", function(value, element) {
	   var aint=parseInt(value);	
	    return aint>0&& (aint+"")==value;   
	  }, "Please enter a valid number.");   


//校验
function validate(){
	
	 var  validate = $("#ruleForm").validate({ 
		   focusCleanup:true,      
	       rules: {  
	    	   proModel:{
	    		  checkSelect:true,
	 	 	  },
	    	  actionCode: {  
	    		  checkSelect:true,  
      			},
      	 	
   			timeRange:{
   				checkSelect:true,  
		 	  },
		 	 maxTimes:{
				   required:true, 
				   number:true,
				   positiveinteger:true
			   } ,
		   integral:{
			   required:true, 
			   number:true, 
			   positiveinteger:true
		  	 },
		   ruleDesc:{
			   maxlength:200		  		 
		  	 }
	  	   },  
	       messages:{  
	    	   proModel: {  
		    		   checkSelect:"请选择模块",  
	   	   },
	   		actionCode:{
	   			checkSelect:"请选择动作",
	   	   },
	   		timeRange:{
	   			checkSelect:"请选择周期",  
				   },
		   maxTimes:{
			   required:"请输入最大次数",  
			   number:"请输入合法数字",
			   positiveinteger:"请输入正整数"
		   },
		   integral:{
			   required:"请输入积分数", 
			   number:"请输入合法数字", 
/* 		   	   maxlength: jQuery.validator.format("请输入一个 长度最多是 {0}位数字"),
 */		   	positiveinteger:"请输入正整数"
		   },
		   ruleDesc:{
			   maxlength:jQuery.validator.format("输入不允许超过{0}个字符")		  		 
		  	 }
				   
		       },  
		})
	return validate.form();
}

</script>
</head>
<body style="overflow-x:hidden;">
 <div id="content_i" class='content'>	
	<div>
		<!-- <h3>积分管理</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">积分管理</span>
		</div>
	</div>

	<div class="btn_gr">
	    	<input type="button" class="btn_1" value="新增积分规则" onclick="addRuleInfo()">
	        <input type="button" class="btn_2" value="批量删除" onclick="deleteBatch()">
	       <!--  <input type="button" class="btn_1" value="下载模板"> -->
	        <input type="button" class="btn_2" value="批量禁用" onclick='disableBatch()'>
	        
	</div>
	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
			
		<div style="margin:3px 10px 10px 10px;"></div>
		
		<div id="exampleTable" style="margin-top:10px;" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</div>	

<div id="ruleDialog" class="layui-layer-content" style="height: auto;display:none">
	<div class="jf_add layui-layer-wrap" style="display: block;">
	<form action="" id='ruleForm'>
		<input type="hidden" id="ruleId"/>
        	<div class="group">
            	<div class="gr_txt">
                	<span>*</span>
                    <em>模块</em>
                </div>
                <div class="gr_ipt">
                	<select name="proModel" id="proModel" onchange="changeModel(this)">
                    	<option>请选择模块</option>
                    </select>
                </div>
            </div>
            <div class="group">
            	<div class="gr_txt">
                	<span>*</span>
                    <em>动作名称</em>
                </div>
                <div class="gr_ipt">
                	<select name="actionCode" id="actionCode">
                    	 <option value='-1'>请选择规则</option>
                    </select>
                </div>
            </div>
            <div class="group">
            	<div class="gr_txt">
                	<span>*</span>
                    <em>周期范围</em>
                </div>
                <div class="gr_ipt">
                	<select name="timeRange" onchange="changeTimeRange(this)">
                    	<option value='-1'>请选择周期</option>
                    	<option value='1'>没有限制</option>
                        <option value='2' selected="selected">一次性</option>
                        <option value='3'>每天</option>
                        <option value='4'>每小时</option>
                    </select>
                </div>
            </div>
               <div class="group" id='maxTimesGroup' style='display:none'>
            	<div class="gr_txt">
                	<span>*</span>
                    <em>周期内最大次数</em>
                </div>
                <div class="gr_ipt">
                	<input type="text" name='maxTimes' maxlength="5">
                </div>
            </div>
            <div class="group">
            	<div class="gr_txt">
                	<span>*</span>
                    <label>单次积分数</label>
                </div>
                <div class="gr_ipt">
                	<input type="text" name='integral' maxlength="5">
                </div>
            </div>
            <div class="group">
            	<div class="gr_txt">
	                </div>
                <div class="gr_ipt">
                	<p><input type="checkbox" name="isEnabled">启用</p>
                </div>
            </div>
            <div class="group">
            	<div class="gr_txt">
                    <em>描述</em>
                </div>
                <div class="gr_ipt">
                	<textarea name="ruleDesc"></textarea>
                </div>
           	</div>
           	</form>
        </div>
      </div>
</body>
</html>
