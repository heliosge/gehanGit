<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人信息</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/webhr.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">

$(function(){
	initValidate();
	initDatepicker();
})

function save(){
	$('#personnal').isValid(function(v) {
		if(v){
			var user = $("#personnal").serializeObject();
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:user,
				url:"<%=request.getContextPath()%>/manageUser/updateManageUser.action",
				success:function(data){
					if(data == "SUCCESS"){
						dialog.alert("个人信息修改成功。");
					}else{
						dialog.alert("个人信息修改失败。");
					}
			    }
			});
		} else {
			//dialog.alert("表单验证不通过");
		}
	});
	
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

var artDialog;
function uploadHeadPhoto(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/toUploadPhoto.action",
        title:"选择头像" ,
        height: 250,
		width: 450
		}).showModal();
}

function initDatepicker() {
	$("#joinCompanyTime").datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});

	$("#joinWorkTime").datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});
	
	$("#joinCompanyTime").datepicker('setDate', (new Date()) );
	$("#joinWorkTime").datepicker('setDate', (new Date()) );
}

/**
 * 表单验证
 */
function initValidate() {
	$('#personnal').validator({
		theme : 'yellow_right',
		rules : {
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
			checkPhone:[/^1[358]\d{9}$/,'手机号码格式不正确']
		},
		//msgStyle:"height: 10px;",
		fields : {
			mobile : {
				rule : "length[~11];checkPhone",
				msg : {
					required : "手机号码不能为空",
					length : "长度需等于11个字符",
					checknum : "必须是数字"
				}
			},
			email : {
				rule : "email"
			}/* ,
			qqNo : {
				rule : "required;length[~30];integer",
				msg : {
					required : "qq号码不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			wechartNo : {
				rule : "required;length[~30]",
				msg : {
					required : "微信号码不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			highEducation : {
				rule : "required;length[~30]",
				msg : {
					required : "最高学历不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			address : {
				rule : "required;length[~30]",
				msg : {
					required : "详细地址不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			email : {
				rule : "required;email",
				msg : {
					required : "邮箱不能为空",
				}
			},
			graduateCollege : {
				rule : "required;length[~30]",
				msg : {
					required : "毕业学校不能为空",
					length : "长度必须小于等于30个字符"
				}
			},
			major : {
				rule : "required;length[~30]",
				msg : {
					required : "专业不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			joinCompanyTime : {
				rule : "required",
				msg : {
					required : "入职时间",
				}
			},
			joinWorkTime : {
				rule : "required",
				msg : {
					required : "参加工作时间不能为空"
				}
			},
			workNo : {
				rule : "required;length[~30]",
				msg : {
					required : "工号不能为空",
					length : "长度需小于等于30个字符"
				}
			}, */
		}
	});
}

</script>

<body>
<div id="content_i">
	<div class="left_nav">
		<img id="headPhoto" src="${USER_BEAN.headPhoto }" style="height:188px;width:256px;cursor:pointer;" onclick="uploadHeadPhoto()"/>
    	<h3>
        	<span style="margin-left:-15px;">${USER_NAME_STRING }</span>
        	<span>学员</span>
        </h3>
        <h4>账户信息</h4>
        <ul  id="left_nav">
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalBaseInfo.action">基本信息</a></li>
            <li class="active_2"><a href="#" class="active_3">个人信息</a></li>
           <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
       
        <div class="right_option2">
            <form id="personnal">
                <table width="100%" class="tab_3">
                    <tr>
                        <th>手机号码：</th>
                        <td width="600px;"><input type="text" value="${USER_BEAN.mobile }" id="mobile" name="mobile"/></td>
                    </tr>
                    <tr>
                        <th>qq号：</th>
                        <td><input type="text" value="${USER_BEAN.qqNo }" id="qqNo" name="qqNo"/></td>
                    </tr>
                    <tr>
                        <th>微信号：</th>
                        <td><input type="text" value="${USER_BEAN.wechartNo }" id="wechartNo" name="wechartNo"/></td>
                    </tr>
                    <tr>
                        <th>电子邮箱：</th>
                        <td><input type="text" value="${USER_BEAN.email }" id="email" name="email"/></td>
                    </tr>
                    <tr>
                        <th>通讯地址：</th>
                        <td><input type="text" value="${USER_BEAN.address }" class="input_1" id="address" name="address"/></td>
                    </tr>
                    <tr>
                        <th>最高学历：</th>
                        <td><input type="text" value="${USER_BEAN.highEducation }" class="input_2" id="highEducation" name="highEducation"/></td>
                    </tr>
                    <tr>
                        <th>毕业院校：</th>
                        <td><input type="text" value="${USER_BEAN.graduateCollege }" id="graduateCollege" name="graduateCollege"/></td>
                    </tr>
                    <tr>
                        <th>专业：</th>
                        <td><input type="text" value="${USER_BEAN.major }" id="major" name="major"/></td>
                    </tr>
                    <tr>
                        <th>入职时间：</th>
                        <td><input type="text" value="${USER_BEAN.joinCompanyTime }" id="joinCompanyTime" name="joinCompanyTime"/></td>
                    </tr>
                    <tr>
                        <th>参加工作时间：</th>
                        <td><input type="text" value="${USER_BEAN.joinWorkTime }" id="joinWorkTime" name="joinWorkTime"/></td>
                    </tr>
                    <tr>
                        <th>工号：</th>
                        <td><input type="text" value="${USER_BEAN.workNo }" id="workNo" name="workNo"/></td>
                    </tr>
                </table>
            <input type="button" value="保存" class="btn_7" onclick="save();"/>
            
            </form>
        </div>
     </div>
</div>
</body>

</html>
