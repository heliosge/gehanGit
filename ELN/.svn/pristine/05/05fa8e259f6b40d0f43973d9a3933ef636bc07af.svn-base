<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员字段维护</title>
</head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/webhr.js"></script>
<style type="text/css">
.btn {
background: #0085FE;color: white;text-align: center;width:100px;height: 35px;border: none;border-radius: 2px;font-weight: bold;margin-right: 10px;
}
</style>
<script type="text/javascript">
$(function(){
	
	initAddFormValidate();
	
	initUpdateFormValidate();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageParam/selectExpandField.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol: true,  //索引列
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
               {title: '序号', name: 'originalFiled', width:60, hidden:true},
 			   {title: '字段', name: 'companyFieldName', width:60, align:'center'},
 			   {title: '字段类型', name: 'type_', width:60, align:'center', renderer:function(val, item, rowIndex){
 				   return val == 1 ? '文本' :(val == 2 ? '日期' : '下拉框');
 			   }},
			   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
				   
				   var buttonStr = '<a href="#" onclick="deleteRowOne('+val+')" >删除</a>  '+
				   	'<a href="#" onclick="updateOne('+item.id+',\''+item.companyFieldName+'\','+item.type_+')" >修改</a>  ' ;
				   
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
});

//删除一行
function deleteRowOne(val){
	dialog.confirm("确认删除吗?",function(obj){
		var param = [];
		param.push({"name":"ids", "value":val});
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:param,
			url:"<%=request.getContextPath()%>/manageParam/deleteExpandField.action",
			success:function(data){
				search();
		    }
		});
	})
}


//批量删除
function deleteRow(){
	
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push({"name":"ids", "value":val.id});
	    	});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		url:"<%=request.getContextPath()%>/manageParam/deleteExpandField.action",
	    		success:function(data){
	    			search();
	    	    }
	    	});
		});
	}else{
		//dialog.alert("请先选择数据！");
		dialog.alert('请先选择数据！');
	}
}

function search(){
	$('#exampleTable').mmGrid().load();
}

var artDialog;
function insertOne(){
	$(".n-yellow .msg-wrap").hide();
	$("#name").val("");
	artDialog = dialog({ 
         title: "新增人员字段",
         content : $("#addOneField"),
         width: 450,
         height:200,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				save();
	            			}
	            		});
	            	  return false;
	              }
		          },{
		              value: '取消',
		              callback: function () {
		            	 this.close();
		              }
		          }
		      ]
     }).showModal();
}

function updateOne(id,name, type_){
	$(".n-yellow .msg-wrap").hide();
	$("#updateName").val(name);
	$("#updateType_").val(type_);
	$("#id").val(id);
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:id},
	  	url:"<%=request.getContextPath()%>/manageParam/selectExpandFieldByName.action",
		success:function(data){
			$("#updateValue").val(data[0].value);
		}
	});
	artDialog = dialog({
        title: "修改人员字段",
        content: $("#updateOneField"),
        width: 450,
        height:200,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#updateForm').isValid(function(v) {
	            			if(v){
	            				update();
	            			}
	            		});
	            	  return false;
	              }
		          },{
		              value: '取消',
		              callback: function () {
		            	 this.close();
		              }
		          }
		      ]
    }).showModal();
}

function update(){
	if($("#updateType_").val() == 3 && !$("#updateValue").val()){
		dialog.alert("下拉框的值不能为空");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{companyFieldName:$("#updateName").val(),id:$("#id").val(),type_:$("#updateType_").val(),value:$("#updateValue").val()},
		url:"<%=request.getContextPath()%>/manageParam/updateExpandField.action",
		success:function(data){
			if(data == 'SUCCESS'){
				search();
				artDialog.close().remove();
				dialog.alert("修改成功。");
			}else{
				dialog.alert("修改失败");
			}
		}
	});
}


function save(){
	if($("#addType_").val() == 3 && !$("#addValue").val()){
		dialog.alert("下拉框的值不能为空");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{companyFieldName:$("#name").val(),type_:$("#addType_").val(),value:$("#addValue").val()},
		url:"<%=request.getContextPath()%>/manageParam/insertExpandField.action",
		success:function(data){
			if(data == 'SUCCESS'){
				search();
				artDialog.close().remove();
				dialog.alert("新增成功。");
			}else{
				dialog.alert("新增失败");
			}
		}
	});
}

/**
 * 表单验证
 */
function initAddFormValidate() {
	$('#addForm').validator({
		rules : {
			checkName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{
						  name:$("#name").val()},
				  	url:"<%=request.getContextPath()%>/manageParam/selectExpandFieldByName.action",
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		theme : 'yellow_right',
		msgStyle:"margin-top:10px;",
		fields : {
			name : {
				rule : "required;length[~30];checkName;",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			}
		}
	});
}

function initUpdateFormValidate() {
	$('#updateForm').validator({
		rules : {
			checkSameName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{name:$("#updateName").val()},
					url:"<%=request.getContextPath()%>/manageParam/selectExpandFieldByName.action",
					success:function(data){
						var flag = true;
						if(data.length > 0 && data[0].id != $("#id").val()){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		theme : 'yellow_right',
		msgStyle:"margin-top:10px;",
		fields : {
			updateName : {
				rule : "required;length[~30];checkSameName;",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			}
		}
	});
}

</script>
<body>

<div class="content">
	<!-- <h3>人员字段维护</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">人员字段维护</span>
	</div>
    <div class="btn_gr">
    	<input type="button" class="btn_1" value="新增字段" onClick="insertOne();"/>
        <input type="button" class="btn_2" value="批量删除" onClick="deleteRow();"/>
        
    </div>
	
	<div id="exampleTable" style="margin-top:10px;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
    
</div>

<div style="display:none;" id="addOneField">
	<form id="addForm">
		<div>	
				<table>
					<tr height="40px;">
						<td><span style="color:red">*</span>字段名称:</td>
						<td><input type="text" style="width:200px;height:30px;" id="name" name="name"/></td>
					</tr>
					<tr height="40px;">
						<td><span style="color:red">*</span>字段类型:</td>
						<td><select id="addType_" style="width:200px;height:30px;">
		             	<option value="1">文本</option>
		             	<option value="2">日期</option> 
		             	<option value="3">下拉框</option> 
	                 </select></td>
					</tr>
					<tr>
						<td><span style="color:red">*</span>字段值:</td>
						<td><textarea id="addValue" placeholder="下拉框的值用'@'分隔" style="width:300px;height:100px;"></textarea></td>
					</tr>
				</table>
	        </div>
		</form>
	</div>
	
	<div style="display:none;" id="updateOneField">
		<form id="updateForm">
			<table>
					<tr height="40px;">
						<td><span style="color:red">*</span>字段名称:</td>
						<td><input type="text" style="width:200px;height:30px;" id="updateName" name="updateName"/>
		                 <input type="hidden" id="id"/></td>
					</tr>
					<tr height="40px;">
						<td><span style="color:red">*</span>字段类型:</td>
						<td><select id="updateType_" style="width:200px;height:30px;">
		             	<option value="1">文本</option>
		             	<option value="2">日期</option> 
		             	<option value="3">下拉框</option> 
	                 </select></td>
					</tr>
					<tr>
						<td><span style="color:red">*</span>字段值:</td>
						<td><textarea id="updateValue" placeholder="下拉框的值用'@'分隔" style="width:300px;height:100px;"></textarea></td>
					</tr>
				</table>
		</form>
	</div>
</body>
</html>
