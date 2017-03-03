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
</style>
<script type="text/javascript">
var group = ${group};

$(function(){
	
		fillGroupInfo();
		
		initValidate();
});

function fillGroupInfo(){
	$("#name").val(group.name);
	$("#descr").val(group.descr);
	$("#capacity").val(group.capacity);
	$.each($("input[name='type']"), function(index, val){
		$(val).attr("disabled","disabled");
		if($(val).val() == group.type){
			if($(val).val() == 3){
				$("#pointButton").show();
			}
			val.checked = true;
		}
	});
	if(group.type != 3){
		$("pointButton").hide();
	}
}


function save(){
	
	 $('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			 		type:"POST",
			 		async:true,  //默认true,异步
			 		data:param(),
			 		url:"<%=request.getContextPath()%>/manageGroup/updateManageGroup.action",
			 		success:function(data){
			 			if(data = 'SUCCESS'){
				 			dialog.alert("修改成功！",function(){cancel();});
			 			}else{
			 				cancel();
			 			}
			 	    }
				 });
			}
		 });
}

var studentIds = [];
function param(){
	var o = {};
	o.id=group.id;
	o.name = $("#name").val();
	o.capacity = $("#capacity").val();
	o.descr = $("#descr").val();
	$.each($("input[name='type']"), function(index, val){
		if(val.checked){
			o.type = $(val).val();
		}
	});
	return o;
}
	
function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageGroup/toGroupListPage.action";
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
				rule : "required;integer[+];",
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
	<!-- <h3>修改群组</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改群组</span>
	</div>
	<form id="addForm">
	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>群组名称：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="name" name="name"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>群组容量：</em>
            </div>
            <div class="add_fr">
            	<input type="text" style="width:135px;" id="capacity" name="capacity"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>群组类型：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" checked="checked" name="type" value="1"/>
                <span>自由加入</span>
                <input type="radio"  name="type" value="2"/>
                <span>通过验证</span>
                <input type="radio"  name="type" value="3"/>
                <span>指定学员</span>
                <input type="radio"  name="type" value="4"/>
                <span>满足条件</span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>描述：</em>
            </div>
             <div class="add_fr">
            	<textarea style="height:70px;" id="descr" name="descr"></textarea>
            </div>
    	</div>
        
        <!--  <div class="add_gr" id="condition1" style="display:none">
        	<div class="add_fl">
            	<span>*</span>
                <em>满足条件：</em>
            </div>
             <div class="add_fr">
            	<select>
                	<option>请选择</option>
                </select>
                <select>
                	<option>小于</option>
                </select>
                <input type="text" style="width:230px;" />
            </div>
	   	</div>
        <div class="add_gr" id="condition2" style="display:none;">
        	<div class="add_fl">
            	<span>&nbsp;</span>
                <em>&nbsp;</em>
            </div>
             <div class="add_fr">
            	<select>
                	<option>请选择</option>
                </select>
                <select>
                	<option>小于</option>
                </select>
                <input type="text" style="width:230px;" />
            </div>
        </div>
        <div class="zd" id="conditionButton" style="display:none;">
        	<input type="button" value="自动匹配学员" />
        </div> -->
           
    
    <div>
   <!--   <div class="btn_gr fl">
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
            <select>
            	<option value="">显示全部</option>
                <option value="1">正常</option>
                <option value="2">待审核</option>
                <option value="3">审核不通过</option>
            </select>

        </p>

    </div>
    <div id="exampleTable" style="margin-top:10px;width:100%;" ></div> -->
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
