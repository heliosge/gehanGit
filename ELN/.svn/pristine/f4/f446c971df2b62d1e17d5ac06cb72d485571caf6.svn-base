<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>录入考试</title>

<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<script src="<c:url value="/js/layer/layer.js"/>"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="">
.span_btu{
	display: inline-block;
	background-color: #02B8EA;
	font-size: 12px;
	padding: 0px 5px;
	cursor: pointer;
	border-radius: 2px;
	min-width: 50px;
	line-height: 30px;
	text-align: center;
	color: #FFFFFF;
}
</style>

<script type="text/javascript">

$(function(){
	
	$("#start_time, #end_time").datetimepicker({
		dateFormat:"yy-mm-dd", 
	    timeFormat: "HH:mm:ss",
	    changeMonth: true,
        changeYear: true
	});
	
	$('#testAddForm').validator({
		theme : 'yellow_right',
		//msgStyle:"margin-top: 10px;",
		rules: {
	        // 自定义规则mobile，如果通过返回true，否则返回错误消息
	        timeCompare: function(element, param, field) {
	        	
	        	var startTime = $.trim($("#"+param[0]).val());
	        	var endTime = $.trim($("#"+param[1]).val());
	        	
	        	if(startTime == "" || endTime == ""){
	        		return '时间不可为空';
	        	}
	        	
	        	function getTimeInt(time){
	        		var timeArray = time.split(" ");
	        		
	        		return parseInt(timeArray[0].split("-").join("") + timeArray[1].split(":").join(""), 10);
	        	}
	        	
	            return (getTimeInt(endTime)>getTimeInt(startTime)) || '结束时间要大于开始时间';
	        },
	        intLimitThan: function(element, param, field) {
	        	//必须比那个整数小
	        	var big = parseInt($.trim($("#"+param[0]).val()));
	        	
	        	var small = parseInt($.trim(element.value));
	        	
	            return (big>=small) || '结束时间要大于开始时间';
	        }
	    },
		fields : {
			name : {
				rule : "required;length[~31]",
				msg : {
					required : "考试名称不可为空",
					length : "考试名称最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			},
			"start_time" : {
				rule : "required",
				msg : {
					required : "考试开始时间不可为空"
				},
				msgStyle :"left:-30px;top:-30px;"
			},
			"end_time" : {
				rule : "required, timeCompare[start_time, end_time]",
				msg : {
					required : "考试结束时间不可为空"
				},
				msgStyle :"left:0px;"
			},
			"total_score" : {
				rule : "required;integer;range[1~]",
				msg : {
					integer : "请输入大于0的整数",
					range : "请输入大于0的整数"
				},
				msgStyle :"left:0px;"
			},
			"pass_score" : {
				rule : "required;integer;range[1~];intLimitThan[total_score]",
				msg : {
					integer : "请输入大于0的整数",
					range : "请输入大于0的整数",
					intLimitThan: "不可以超过总分"
				},
				msgStyle :"left:0px;"
			}
		}
	});
	
	<c:if test="${offlineTestBean !=null }">
		//修改时	
		var startTime =  $("#start_time").val();
		if(startTime !="" && startTime.length > 19){
			$("#start_time").val(startTime.substring(0, 19));
		}
		
		var endTime =  $("#end_time").val();
		if(endTime !="" && endTime.length > 19){
			$("#end_time").val(endTime.substring(0, 19));
		}
		
		$('.user_div form.user_table_div').each(function(index, form){
			createUserValidate(form);
		});
	</c:if>
});

//保存
function saveBefore(){
	
	var formPast = 0;
	
	//验证所有表单
	var formAll = $('#testAddForm').add(".user_div form.user_table_div");
	for(var i=0; i<formAll.length; i++){
		$(formAll[i]).isValid(function(v) {
			if(v){
				formPast++;
			}
		});
	}
	
	//所有表单通过验证
	if(formPast == formAll.length){

		dialog.confirm('确认保存吗？', function(){
			
			layer.msg('保存中...', {icon: 16, time: 0, shade: [0.3, '#393D49']});
			//alert("保存");
			//组织参数
			var param = $("#testAddForm").serialize();
			
			//学员成绩
			$(".user_div form.user_table_div").each(function(index, form){
				
				var userParam = [];
				var $form = $(form);
				
				var name = $.trim($form.find("#name").val());
				var email = $.trim($form.find("#email").val());
				var phone = $.trim($form.find("#phone").val());
				var job = $.trim($form.find("#job").val());
				var department = $.trim($form.find("#department").val());
				var score = $.trim($form.find("#score").val());
				
				userParam.push("resultList["+index+"].name="+name);
				userParam.push("resultList["+index+"].email="+email);
				userParam.push("resultList["+index+"].phone="+phone);
				userParam.push("resultList["+index+"].job="+job);
				userParam.push("resultList["+index+"].department="+department);
				userParam.push("resultList["+index+"].score="+score);
				
				param += "&" + userParam.join("&");
			});
			
			//增加
			var url = "<%=request.getContextPath()%>/offlineTest/testAdd.action";
			
			<c:if test="${offlineTestBean !=null }">
				//修改时	
				url = "<%=request.getContextPath()%>/offlineTest/testEdit.action";
			</c:if>
			
			$.ajax({
	    		type:"POST",
	    		async: true,  //默认true,异步
	    		data: param,
	    		url: url,
	    		error: function(XMLHttpRequest, textStatus, errorThrown) {
	    			dialog.alert("保存失败!");
	            },
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				
	    				layer.closeAll();
	    				dialog.alert("保存成功!");
	    				
						//返回
	    				backPaper();
						
	    			}else{
	    				
	    				dialog.alert("保存失败!");
	    			}
	    			
	    	    }
	    	});
	    });
	}
}

function backPaper(){
	//返回
	window.location.href = "<%=request.getContextPath()%>/offlineTest/testList.action";
}

//删除考试人
function deleteTestUser(obj){
	dialog.confirm('确认删除吗？', function(){
		$(obj).parent().parent().remove();
    });
}

//增加考试人
function addTestUser(){
	$("#add_user_div").before($("#myAddForm").html());
	
	createUserValidate($('.user_div form.user_table_div').last());
}

function createUserValidate(form){
	//增加验证
	$(form).validator({
		theme : 'yellow_right',
		//msgStyle:"margin-top: 10px;",
		fields : {
			name : {
				rule : "required;length[~31]",
				msg : {
					required : "姓名不可为空",
					length : "姓名最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			},
			job : {
				rule : "required;length[~31]",
				msg : {
					required : "岗位不可为空",
					length : "岗位最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			},
			department : {
				rule : "required;length[~31]",
				msg : {
					required : "部门不可为空",
					length : "部门最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			},
			score : {
				rule : "required;integer[+0];length[~31]",
				msg : {
					required : "请输入整数",
					integer : "请输入整数",
					length : "成绩最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			}
		}
	});
}

var selectUserForm;
var artDialog;
function chooseUser(obj){
	
	selectUserForm = $(obj).parentsUntil(".user_table_div").parent();
	
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/offlineTest/chooseUser.action",
        title:"选择学员" ,
        height: 500,
		width: 1100
		}).showModal();
}

function setUser(data){
	
	$(selectUserForm).find("#name").val(data.name);
	$(selectUserForm).find("#email").val(data.email);
	$(selectUserForm).find("#phone").val(data.mobile);
	$(selectUserForm).find("#job").val(data.postName);
	$(selectUserForm).find("#department").val(data.deptName);
	
	artDialog.close();
}
</script>
<style type="text/css">


.need_span{
	color: red;
	display: none;
}
.need_0{
	color: red;
}
.need_1{
	color: #666666;
}
.input_0{
	height: 30px;
	border: 1px solid #A9A9A9;
	text-indent: 2px;
	width: 200px;
}
.form_table{
	border-collapse: collapse;
}
.form_table td{
	padding: 5px;
}
.user_table .input_0{
	width: 150px;
}
.user_table_div{
	float: left;
	background-color: #EFEFEF;
	margin:10px 10px 0px 0px;
	padding: 10px 8px;
}
</style>
</head>

<body>

<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content" style="margin-top:20px;padding-bottom:10px;">
	
	<!-- <h3>线下考试录入</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backPaper();"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">线下考试录入</span>
	</div>
	
	<form id="testAddForm" method="post" >
		<!-- 修改时的ID -->
		<input type="hidden" class="input_0" id="testId" name="id" value="${offlineTestBean.id}" />
		<table class="form_table" style="border-collapse: collapse;margin-left:30px;margin-top:10px;">
			<tr>
				<td align="right" class="text_ad_0" >
					<span class="need_0">*</span>
					<span class="text_ad_0">考试名称：</span>
				</td>
				<td style="width: 340px;">
					<input type="text" class="input_0" id="name" name="name" value="${offlineTestBean.name}" />
					<span class="need_span"  >*</span>
				</td>
				<td align="right" class="text_ad_0" >
					<span class="need_0">*</span>
					<span class="text_ad_0">考试时间：</span>
				</td>
				<td>
					<input type="text" class="input_0" id="start_time" name="start_time" style="width:130px;" readonly="readonly" value="${offlineTestBean.start_time}" />
					<span>至</span>
					<input type="text" class="input_0" id="end_time" name="end_time" style="width:130px;" readonly="readonly" value="${offlineTestBean.end_time}" />
					<span class="need_span"  >*</span>
				</td>
			</tr>
			<tr>
				<td align="right" class="text_ad_0" >
					<span class="need_0">*</span>
					<span class="text_ad_0">总分：</span>
				</td>
				<td>
					<input type="text" class="input_0" id="total_score" name="total_score" value="${offlineTestBean.total_score}" />
					<span class="need_span"  >*</span>
				</td>
				<td align="right" class="text_ad_0" >
					<span class="need_0">*</span>
					<span class="text_ad_0">及格分数：</span>
				</td>
				<td>
					<input type="text" class="input_0" id="pass_score" name="pass_score" value="${offlineTestBean.pass_score}" />
					<span class="need_span"  >*</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left" >
						
				</td>
			</tr>
		</table>
	</form>
	
	<div style="background-color:#EEE;font-weight:bold;padding:8px 0px;margin: 10px 0px 10px 0px;text-indent: 20px;">
		参加考试人员信息
	</div>
	
	<div class="user_div">
	
			<!-- 修改 回填参数 -->
			<c:forEach var="user" items="${offlineTestBean.resultList}" >
				<form class="user_table_div " method="post">
		    		<div align="right">
		    			<img src="<%=request.getContextPath()%>/images/offline_test_close.png" alt="删除考试人员" title="删除考试人员" style="cursor:pointer;width:20px;margin-top:-5px;margin-right:-2px;" onclick="deleteTestUser(this)" />
		    		</div>
					<table class="form_table user_table" style="border-collapse: collapse;">
						<tr>
							<td align="right" class="text_ad_0" >
								<span class="need_0">*</span>
								<span class="text_ad_0">用户姓名：</span>
							</td>
							<td style="width: 280px;">
								<input type="text" class="input_0" id="name" name="name" value="${user.name}" />
								<span class="need_span"  >*</span>
								<span onclick="chooseUser(this)" class="span_btu" style="background-color:#E53E38;" >选择学员</span>
							</td>
						</tr>
						<tr>
							<td align="right" class="text_ad_0" >
								<span class="need_0"></span>
								<span class="text_ad_0">电子邮箱：</span>
							</td>
							<td style="">
								<input type="text" class="input_0" id="email" name="email" value="${user.email}"  />
								<span class="need_span"  >*</span>
							</td>
						</tr>
						<tr>
							<td align="right" class="text_ad_0" >
								<span class="need_0"></span>
								<span class="text_ad_0">手机：</span>
							</td>
							<td style="">
								<input type="text" class="input_0" id="phone" name="phone" value="${user.phone}" />
								<span class="need_span"  >*</span>
							</td>
						</tr>
						<tr>
							<td align="right" class="text_ad_0" >
								<span class="need_0">*</span>
								<span class="text_ad_0">岗位：</span>
							</td>
							<td style="">
								<input type="text" class="input_0" id="job" name="job" value="${user.job}" />
								<span class="need_span"  >*</span>
							</td>
						</tr>
						<tr>
							<td align="right" class="text_ad_0" >
								<span class="need_0">*</span>
								<span class="text_ad_0">部门：</span>
							</td>
							<td style="">
								<input type="text" class="input_0" id="department" name="department" value="${user.department}" />
								<span class="need_span"  >*</span>
							</td>
						</tr>
						<tr>
							<td align="right" class="text_ad_0" >
								<span class="need_0">*</span>
								<span class="text_ad_0">成绩：</span>
							</td>
							<td style="">
								<input type="text" class="input_0" id="score" name="score" value="${user.score}" />
								<span class="need_span"  >*</span>
							</td>
						</tr>
					</table>
				</form>	
			</c:forEach>
		
		<div id="add_user_div" class="user_table_div add_user" >
			<img src="<%=request.getContextPath()%>/images/offline_test_add.png" alt="增加考试人员" title="增加考试人员" style="cursor:pointer;" onclick="addTestUser()" />
		</div>	
	</div>
	
	<div style="clear:both;" ></div>
	
	<div class="button_cz" align="center" style="display:block;float:none;margin:10px;padding:0px;">
        <input type="button" value="保存" onclick="saveBefore()" />
        <input type="button" value="返回" class="back" onclick="backPaper()" />
    </div>
    
    
    <div id="myAddForm" style="display:none;">
    	<form class="user_table_div " method="post">
    		<div align="right">
    			<img src="<%=request.getContextPath()%>/images/offline_test_close.png" alt="删除考试人员" title="删除考试人员" style="cursor:pointer;width:20px;margin-top:-5px;margin-right:-2px;" onclick="deleteTestUser(this)" />
    		</div>
			<table class="form_table user_table" style="border-collapse: collapse;">
				<tr>
					<td align="right" class="text_ad_0" >
						<span class="need_0">*</span>
						<span class="text_ad_0">用户姓名：</span>
					</td>
					<td style="width: 280px;">
						<input type="text" class="input_0" id="name" name="name" />
						<span class="need_span"  >*</span>
						<span onclick="chooseUser(this)" class="span_btu" style="background-color:#E53E38;" >选择学员</span>
					</td>
				</tr>
				<tr>
					<td align="right" class="text_ad_0" >
						<span class="need_0"></span>
						<span class="text_ad_0">电子邮箱：</span>
					</td>
					<td style="">
						<input type="text" class="input_0" id="email" name="email" />
						<span class="need_span"  >*</span>
					</td>
				</tr>
				<tr>
					<td align="right" class="text_ad_0" >
						<span class="need_0"></span>
						<span class="text_ad_0">手机：</span>
					</td>
					<td style="">
						<input type="text" class="input_0" id="phone" name="phone" />
						<span class="need_span"  >*</span>
					</td>
				</tr>
				<tr>
					<td align="right" class="text_ad_0" >
						<span class="need_0">*</span>
						<span class="text_ad_0">岗位：</span>
					</td>
					<td style="">
						<input type="text" class="input_0" id="job" name="job" />
						<span class="need_span"  >*</span>
					</td>
				</tr>
				<tr>
					<td align="right" class="text_ad_0" >
						<span class="need_0">*</span>
						<span class="text_ad_0">部门：</span>
					</td>
					<td style="">
						<input type="text" class="input_0" id="department" name="department" />
						<span class="need_span"  >*</span>
					</td>
				</tr>
				<tr>
					<td align="right" class="text_ad_0" >
						<span class="need_0">*</span>
						<span class="text_ad_0">成绩：</span>
					</td>
					<td style="">
						<input type="text" class="input_0" id="score" name="score" />
						<span class="need_span"  >*</span>
					</td>
				</tr>
			</table>
		</form>	
    </div>
    
</div>
</body>
</html>
