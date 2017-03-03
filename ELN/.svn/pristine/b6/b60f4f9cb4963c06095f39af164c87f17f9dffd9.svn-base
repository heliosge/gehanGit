<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增群组-满足条件</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
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
<style>
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.img {margin-top:10px;width:34px;height:34px;}
</style>

<script type="text/javascript">
var group = ${group};

$(function(){
		fillGroupInfo();
		initMMGrid();
		initValidate();
});

function fillGroupInfo(){
		if(group.type == 1){
			$("#type").html("自由加入");
		}else if(group.type == 2){
			$("#type").html("通过验证");
		}else if(group.type == 3){
			$("#type").html("指定学员");
			$("#pointButton").show();
		}else if(group.type == 4){
			$("#type").html("满足条件");
			$("#condition1").show();
			$("#conditionButton").show();
			initGroupCondition();
		}
}

function initGroupCondition(){
	$.ajax({
 		type:"POST",
 		async:true,  //默认true,异步
 		data:{groupId:group.id},
 		url:"<%=request.getContextPath()%>/manageGroup/selectGroupCondition.action",
 		success:function(data){
 			for(var i=1;i<data.length;i++){
 				fillCondition();
 			}
 			for(var i=0;i<data.length;i++){
 				$("#field"+(i+1)).val(data[i].compareKey);
 				$("#value"+(i+1)).val(data[i].compareValue);
 			}
 	    }
	 });
}

function fillCondition(){
		var html = '<div class="add_gr" id="condition'+divCount+'"><div class="add_fl">&nbsp;</div><div class="add_fr">'
	 	+'<select id="field'+divCount+'"><option value="">请选择</option><option value="deptId">部门</option><option value="postId">岗位</option>'
	    +'<option value="sex">性别</option><option value="status">状态</option></select>'
	    +'<select><option value="=">等于</option></select>'
	    +' <input type="text" id="value'+divCount+'" style="width:230px;" />'
	    +'<img src="<%=request.getContextPath()%>/images/img/add_sc.png" class="img" onclick="addConditionDiv(this)"/>'
	    +'<img src="<%=request.getContextPath()%>/images/delete.png" class="img" onclick="removeConditionDiv(this)"/></div></div>';
		$("#condition1").after(html);
		divCount++;
}

function initMMGrid(){
	//表格
	var mmGrid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params:  function(){
        	var o = {};
        	o.id = group.id;
        	return o;
        },
        url:"<%=request.getContextPath()%>/manageGroup/selectGroupStudentByGroupId.action",
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '姓名', name: 'name', width:60, align:'center'},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
 			   {title: '电子邮箱', name: 'email', width:60, align:'center'},
			   {title: '联系电话', name: 'mobile', width:60, align:'center'},
			   {title: '岗位', name: 'postName', width:60, align:'center'},
 			   {title: '部门', name: 'deptName', width:60, align:'center'},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "待审核";
				   }else if(val == 3){
					   return "审核不通过";
				   }
				}}
           ]
    });
	
	mmGrid.on("loadSuccess",function(e, data){
		stuGridRows = mmGrid.rows();
		if(stuGridRows[0] == undefined){
			$(".mmg-message").css("top","50px");
		}
	});
}

function save(){
	
	var paramData = param();
	if(paramData.studentIds.length > group.capacity){
		dialog.alert("选择人员不能大于群组容量。");
		return;
	}
	
	 $('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			 		type:"POST",
			 		async:true,  //默认true,异步
			 		data:paramData,
			 		url:"<%=request.getContextPath()%>/manageGroup/updateManageGroupStudent.action",
			 		success:function(data){
			 			if(data = 'SUCCESS'){
				 			dialog.alert("修改成功！",function(){cancel();});
			 			}else{
			 				cancel();
			 			}
			 	    }
				 });
			}else{
				dialog.alert("表单验证失败！");
			}
		 });
}

function match(){
	var param = {};
	for(var i = 1;i < divCount;i++){
		if($("#value"+i).val() != ''){
			if($("#field"+i).val() == 'deptId'){
				param.deptId = $("#value"+i).val();
			}else if($("#field"+i).val() == 'postId'){
				param.postId = $("#value"+i).val();
			}else if($("#field"+i).val() == 'sex'){
				param.sex = $("#value"+i).val() =='男'?1:2;
			}else if($("#field"+i).val() == 'status'){
				param.status = $("#value"+i).val() == '正常'?1:2;
			}
		}
	}
	param.page = 1;
	param.pageSize = 9999;
	$.ajax({
 		type:"POST",
 		async:true,  //默认true,异步
 		data:param,
 		url:"<%=request.getContextPath()%>/manageUser/selectStudentList.action",
 		success:function(data){
 			$('#exampleTable').mmGrid().load([]);
 			$('#exampleTable').mmGrid().load(data.rows);
 	    }
	 })
}

var divCount = 2;
function addConditionDiv(obj){
	var html = '<div class="add_gr" id="condition'+divCount+'"><div class="add_fl">&nbsp;</div><div class="add_fr">'
 	+'<select id="field'+divCount+'"><option value="">请选择</option><option value="deptId">部门</option><option value="postId">岗位</option>'
    +'<option value="sex">性别</option><option value="status">状态</option></select>'
    +'<select><option value="=">等于</option></select>'
    +' <input type="text" id="value'+divCount+'" style="width:230px;" />'
    +'<img src="<%=request.getContextPath()%>/images/img/add_sc.png" class="img" onclick="addConditionDiv(this)"/>'
    +'<img src="<%=request.getContextPath()%>/images/delete.png" class="img" onclick="removeConditionDiv(this)"/></div></div>';
	$($(obj).parent().parent()[0]).after(html);
	divCount++;
}

function removeConditionDiv(obj){
	$($(obj).parent().parent()[0]).remove();
}

var studentIds = [];
function param(){
	studentIds = [];
	var o = {};
	o.id=group.id;
	var selectRows = $('#exampleTable').mmGrid().rows();
	if(selectRows[0] != undefined){
		$.each(selectRows, function(index, val){
			studentIds.push(val.id);
		});
	}
	o.studentIds = studentIds;
	var fields = [];
	var values = [];
	for(var i = 1;i < divCount;i++){
		if($("#value"+i).val() != ''){
			if($("#field"+i).val() == 'deptId'){
				fields.push('deptId');
				values.push($("#value"+i).val());
			}else if($("#field"+i).val() == 'postId'){
				fields.push('postId');
				values.push($("#value"+i).val());
			}else if($("#field"+i).val() == 'sex'){
				fields.push('sex');
				values.push($("#value"+i).val() =='男'?1:2);
			}else if($("#field"+i).val() == 'status'){
				fields.push('status');
				values.push($("#value"+i).val() == '正常'?1:2);
			}
		}
	}
	o.type = group.type;
	if(group.type == 4){
		o.fields = fields;
		o.values = values;
	}
	return o;
}
	
var artDialog;
function pointStudent(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageGroup/chooseStu.action",
        title:"选择人员" ,
        height: 500,
		width: 1100
		}).showModal();
}

function deleteRows(){
	var rows = $('#exampleTable').mmGrid().rows();
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认删除吗?",function(obj){
			for(var i = rows.length-1; i>=0; i--){
				$.each(selectRows, function(index, val){
					if(rows[i].id == val.id){
						$('#exampleTable').mmGrid().removeRow(i);
					}
				});
			}
		hideErrorInfo();
		});
	}else{
		dialog.alert('请先选择数据！');
	}
}

function hideErrorInfo(){
	if($('#exampleTable').mmGrid().rows()[0] == undefined){
		$(".mmg-message").css("top","50px");
	}
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageGroup/toGroupListPage.action";
	}

var stuGridRows;
function search(){
	var rows = $('#exampleTable').mmGrid().rows();
	for(var i = rows.length-1; i >= 0; i--){
		$('#exampleTable').mmGrid().removeRow(i);
	}
	$('#exampleTable').mmGrid().addRow(stuGridRows);
	rows = $('#exampleTable').mmGrid().rows();
	var userName = $("#userName").val();
	var name_ = $("#name_").val();
	var postId = $("#postId").val();
	var deptId = $("#deptId").val();
	var sex = $("#sex").val();
	var status = $("#status").val();
	for(var index = rows.length-1; index>=0; index--){
		var item = rows[index];
		if(name_){
			if(item.name.indexOf(name_) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(userName){
			if(item.userName.indexOf(userName) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(sex){
			if(item.sex != sex){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(status){
			if(item.status != status){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(postId){
			if( item.postName==null || item.postName.indexOf(postId) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
		if(deptId){
			if( item.deptName==null || item.deptName.indexOf(deptId) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
				continue;
			}
		}
	}
	hideErrorInfo();
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
		msgStyle:"margin-top:10px;margin-left:10px;",
		fields : {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			capacity : {
				rule : "required;integer;",
				msg : {
					required : "用户名不能为空",
					checknum : "必须是数字"
				}
			},
			descr : {
				rule : "required",
				msg : {
					required : "描述不能为空"
				}
			}
		}
	});
}
	

</script>

</head>
<body>

<div class="content">
	<!-- <h3>群组详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">群组详情</span>
	</div>
	<form id="addForm">
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
                <em>群组名称：</em>
            </div>
            <div class="add_fr">
            	${group.name }
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>群组容量：</em>
            </div>
            <div class="add_fr">
            	${group.capacity }
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>群组类型：</em>
            </div>
            <div class="add_fr" id="type">
            </div>
        </div>
        <div class="add_gr" style="height:auto;">
        	<div class="add_fl">
                <em>描述：</em>
            </div>
             <div class="add_fr">
            	${group.descr }
            </div>
    	</div>
        
        <div class="add_gr" id="condition1" style="display:none;">
        	<div class="add_fl">
            	<span>*</span>
                <em>满足条件：</em>
            </div>
             <div class="add_fr">
            	<select id="field1">
                	<option value="">请选择</option>
                	<option value="deptId">部门</option>
                	<option value="postId">岗位</option>
                	<option value="sex">性别</option>
                	<option value="status">状态</option>
                </select>
                <select>
                	<!-- <option value=">">大于</option> -->
                	<option value="=">等于</option>
                	<%-- <option value="<">小于</option> --%>
                </select>
                <input type="text" id="value1" style="width:230px;" />
                <img src="<%=request.getContextPath()%>/images/img/add_sc.png" style="margin-top:10px;width:34px;height:34px;" onclick="addConditionDiv(this)"/>
            </div>
	   	</div>
        <div class="zd" id="conditionButton" style="display:none;">
        	<input type="button" value="自动匹配学员"  onclick="match()"/>
        </div>
           
    
    <div>
     <div class="btn_gr fl">
        <input type="button" class="btn_1" value="选择人员" onclick="pointStudent()" id="pointButton" style="display:none;"/>
    	<input type="button" class="btn_1" value="批量删除" onclick="deleteRows()"/>
    </div>
	<div class="search_2 fl" style="margin-bottom:-1px;width:98%;">
    	<p>用户名：
            <input type="text" id="userName"/>
           	 姓名：
            <input type="text" id="name_"/>
			岗位：
            <input type="text" id="postId"/>
          	 部门：
            <input type="text" id="deptId"/>
        </p>
        <input type="button" value="查询" class="btn_cx" onclick="search()"/>

    </div>
    <div class="search_2 fl" style="width:98%;">
    	<p>	性别：
            <select id="sex">
            	<option value="">显示全部</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
           	 状态：
            <select id="status">
            	<option value="">显示全部</option>
                <option value="1">正常</option>
                <option value="2">待审核</option>
                <option value="3">审核不通过</option>
            </select>

        </p>

    </div>
    <div style="float: left;width:1066px;">
    	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
    </div>
	</div>
        <div class="button_cz fl" style="padding-left:150px;">
        	<input type="button" value="保存" onclick="save()"/>
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
    </div>
    </form>
    
         
</div>
</body>
</html>
