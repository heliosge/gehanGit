<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增学员</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exhide-3.5.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkIDCard.js"></script>
<!-- layer -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/layer/skin/layer.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<!-- laydate -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/laydate-v1.1/laydate/laydate.js"></script>

<style type="text/css">
	.ztree {min-width: 250px;width:auto;min-height: 300px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.lesson_add .add_gr .add_fr select { width: 315px;}
</style>

<script type="text/javascript">

$(function(){
	//initDatepicker();
	initDate();
	getCompanyRole();
	initDeptTree();
	initPostTree();
	initExpandField();
	//initValidate();
});

function initExpandField(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageParam/queryCompanyParamList.action",
		success:function(data){
			for(var i=0;i<data.length;i++){
				if(data[i].propertyType_ != 3){
					var re = '<div class="add_gr"><div class="add_fl">'+(data[i].isEmpty == 0?'<span>*</span>':'')+'<em>'+data[i].propertyName+
					'：</em></div><div class="add_fr"><input type="text" name="field'+data[i].originalFiled+'"id="field'+data[i].originalFiled+'"/></div></div>';
					$("#content").append(re);
					//判断该字段是否是日期
					data[i].propertyType_ == 2 && initDatepickerById('field'+data[i].originalFiled)
				}else{
					var re = '<div class="add_gr"><div class="add_fl">'+(data[i].isEmpty == 0?'<span>*</span>':'')+'<em>'+data[i].propertyName+
					'：</em></div><div class="add_fr"><select type="text" name="field'+data[i].originalFiled+'"id="field'+data[i].originalFiled+'">';
					var propertyValue = data[i].propertyValue.split('@');
					for(var j = 0; j < propertyValue.length;j++){
						re += '<option value="'+propertyValue[j]+'">'+propertyValue[j]+'</option>';
					}
					re += '</select></div></div>'
					$("#content").append(re);
				}
			}
			
			initValidate(data);
		}
	})
}

//根据id初始化日期
function initDatepickerById(inputId) {
	$("#"+inputId).datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});
	$("#"+inputId).datepicker('setDate', (new Date()) );
}

function getCompanyRole(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageRole/getCompanyRole.action",
		success:function(data){
			var re = '';
			for(var i=0;i<data.length;i++){
				re += '<input type="checkbox" name="role" style="width:14px;height:13px;" value="'+data[i].id+'"/>'+data[i].name;
			}
			$("#role").append(re);
		}
	})
}


function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param =  $("#addForm").serializeObject();
			param.sex = $("input[name='sex']:checked").val();
			param.isManager = $("input[name='isManager']:checked").val();			
			param.deptId = deptId;
			param.address = $("#address").val();
			if(deptId == undefined){
				dialog.alert("请选择部门");
				return;
			}
			param.postId = postId;
			if(deptId == undefined){
				dialog.alert("请选择岗位");
				return;
			}
			var roleIds = [];
			$.each($("input[name='role']"), function(index, val){
				if(val.checked){
					roleIds.push($(val).val());
				}
			});
			param.roleIds = roleIds;
			if(roleIds.length ==0 ){
				dialog.alert("必须选择角色。");
				return;
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:param,
				url:"<%=request.getContextPath()%>/manageUser/inserStudent.action",
				success:function(data){
					if(data == 'SUCCESS'){
						dialog.alert("新增成功！",function(){cancel();});
					}else if(data == 'ACCOUNT_OVER'){
						dialog.alert("用户数量已经达上限。");
					}else{
						dialog.alert("新增失败。");
					}
				}
			});
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageUser/toStudentListPage.action";
}

/* function initDatepicker() {
	$("#birthDay").datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});
	$("#birthDay").datepicker('setDate', (new Date()) );
} */

/* 初始化时间插件 */
function initDate(){
		laydate({
			istoday: true,
		    elem: '#birthDay', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
		});
	}


function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}



function initDeptTree(){
	var setting = {
			data: {key: {url: "xUrl"},simpleData: {enable: true, pIdKey: "parentId" }},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {onClick: deptOnClick}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			$.fn.zTree.init($("#treePage"), setting, data.data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

var deptId;
function deptOnClick(event, treeId, treeNode) {
	if(treeNode.id.indexOf("com") == 0){
		dialog.alert("不能选择公司节点");
		return;
	}
	if(treeNode.isSubCompany == 1){
		dialog.alert("不能选择公司节点");
		return;
	}
	deptId = treeNode.id;
	$("#deptName").val(getZTreePathName(treeNode,'name'));
};
var postId;
function postOnClick(event, treeId, treeNode) {
	postId = treeNode.id;
	//$("#postName").val(treeNode.name);
	$("#postName").val(getZTreePathNameWithRootNode(treeNode,'name'));
};


function initPostTree(){
	var setting = {
			data: {key: {url: "xUrl"},simpleData: {enable: true, pIdKey: "parentId" }},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {onClick: postOnClick}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageParam/selectManagePost.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#postTreePage"), setting, data);
			$.fn.zTree.getZTreeObj("postTreePage").expandAll(true);
		}
	});
}

function chooseDept(){
	dialog({
		width : 300,
		height : 350,
		title : '请选择部门',
		content : $("#deptDialog"),
		fixed:true,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  this.close();
	              }
		          }
		      ]
	}).showModal();
}

function choosePost(){
	dialog({
		width : 300,
		height : 350,
		title : '请选择岗位',
		content : $("#postDialog"),
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  this.close();
	              }
		          }
		      ]
	}).showModal();
}

$.fn.serializeObject = function(){
	var o = {};
	var a = this.serializeArray();
	$.each(a, function(){
		if(o[this.name]){
			if(!o[this.name].push){
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		}else{
			o[this.name] = this.value || '';
		}
	});
	return o;
}

//检测身份证是否合法
function valiIdCard(idCard){
	  var checkFlag = new clsIDCard(idCard);
	  if (!checkFlag.IsValid()) {
	   return false;
	  } 
	  return true;
}

/**
 * 表单验证
 */
function initValidate(data) {
	var fields = {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			userName : {
				rule : "required;length[6~30];checkUserName",
				msg : {
					required : "用户名不能为空",
					length : "长度需在6~30个字符"
				}
			},
			deptName : {
				rule : "required",
				msg : {
					required : "部门不能为空"
				},
				msgStyle:"left:-30px;",
				msgClass:"n-top"
			},
			postName : {
				rule : "required",
				msg : {
					required : "岗位不能为空"
				},
				msgStyle:"left:-30px;",
				msgClass:"n-top"
			},
			birthDay : {
				rule : "required",
				msg : {
					required : "生日不能为空"
				}
			},
			idCard : {
				rule : "required;checkIdCard",
				msg : {
					required : "身份证不能为空"
				}
			} ,
			mobile : {
				rule : "checkPhone"
			},
			email : {
				rule : "email"
			}
		};
	for(var i=0;i<data.length;i++){
		if(data[i].isEmpty == 0){
			fields["field"+data[i].originalFiled]={rule : "required",msg : {required : data[i].propertyName+"不能为空"}};
		}
	}
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkUserName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{userName:element.value},
					url:"<%=request.getContextPath()%>/manageUser/getAllManageUser.action",
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str =  flag || "已存在相同用户名";
					}
				});
				return str;
			},
			checkIdCard:function(element,param,field){
				var checkFlag = new clsIDCard(element.value);
				  if (!checkFlag.IsValid()) {
				   return "身份证不合法";
				  } 
				  return true;
			},
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
			checkPhone:[/^1[358]\d{9}$/,'电话号码格式不正确']
		},
		msgStyle:"margin-top:10px;margin-left:10px;",
		fields : fields
	});
}

</script>

</head>
<body>

<div class="content">
	<!-- <h3>新增学员</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增学员</span>
	</div>
	<form id="addForm">
	<div class="lesson_add" id="content">
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>用户名：</em>
            </div>
             <div class="add_fr">
            	<input type="text"  id="userName" name="userName"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>姓名：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="name" name="name"/>
            </div>
    	</div>
    	  <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>性别：</em>
            </div>
             <div class="add_fr">
            	<input type="radio" name="sex" value="1"  checked/>男
            	<input type="radio" name="sex" value="2"/>女
            </div>
    	</div>
    	  <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>出生日期：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="birthDay" name="birthDay" readonly/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>身份证号：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="idCard" name="idCard"/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>地址：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="address" name="address"/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>部门：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="deptName" name="deptName" disabled/>&nbsp;&nbsp;<input type="button" value="选择部门" onclick="chooseDept()"/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>岗位：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="postName" name="postName" disabled/>&nbsp;&nbsp;<input type="button" value="选择岗位" onclick="choosePost()"/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>手机号码：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="mobile" name="mobile"/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>电子邮箱：</em>
            </div>
             <div class="add_fr">
            	<input type="text" id="email" name="email"/>
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
                <em>是否管理员：</em>
            </div>
             <div class="add_fr">
            	<input type="radio" name="isManager" value="1" />是
            	<input type="radio" name="isManager" value="2" checked/>否
            </div>
    	</div>
    	 <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>角色：</em>
            </div>
             <div class="add_fr" id="role">
            </div>
    	</div>
        </div>
       </form>
        <div class="button_cz">
        	<input type="button" value="保存" onclick="save()"/>
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
      </div>
      
      <div id="deptDialog" style="display: none;">
	     <ul id="treePage" class="ztree" style=""></ul>
     </div>
     
    <div id="postDialog" style="display: none;">
       <ul id="postTreePage" class="ztree" style=""></ul>
     </div>
</body>
</html>
