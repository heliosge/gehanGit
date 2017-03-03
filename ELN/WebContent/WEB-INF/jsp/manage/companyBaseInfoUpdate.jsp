<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/webhr.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
</style>

<script type="text/javascript">
var company = ${company};

$(function(){
	upLoadImg();
	initDatepicker();
	initProvince();
	initIndustry();
	$("#province").change(function(){
		initCity($("#province").val());
	})
	fillCompanyInfo();
	initValidate();
});

function initDatepicker() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});

	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});
	
	$("#startTime").datepicker('setDate', (new Date()) );
	$("#endTime").datepicker('setDate', (new Date()) );
}

function fillCompanyInfo(){
	$("#name").val(company.name);
	$("#domain").val(company.domain);
	$("#code").val(company.code);
	$("#province").val(company.province);
	initCity(company.province);
	$("#city").val(company.city);
	$("#address").val(company.address);
	$("#industry").val(company.industry);
	$("#proportion").val(company.proportion);
	$("#email").val(company.email);
	$("#attention").val(company.attention);
	$("#attentionPhone").val(company.attentionPhone);
	$("#phoneNum").val(company.phoneNum);
	$("#website").val(company.website);
	$("#postCode").val(company.postCode);
	if(company.startTime!=''){
		$("#startTime").val(company.startTime);
	}
	if(company.endTime!=''){
		$("#endTime").val(company.endTime);
	}
	$("#previewPath").attr("src", company.logoImage);
	$("#filePath").val(company.logoImage);
}

	function save(){
		$('#addForm').isValid(function(v) {
			if(v){
				$.ajax({
			   		type:"POST",
			   		async:true,  //默认true,异步
			   		data:param(),
			   		url:"<%=request.getContextPath()%>/manageCompany/updateCompanyBaseInfo.action",
			   		success:function(data){
			   			if(data == 'SUCCESS'){
			   				dialog.alert("修改成功！",function(){cancel();});
			   			}else{
				   			dialog.alert("修改失败。");
			   			}
			   	    }
			   	});
			} else {
				//dialog.alert("表单验证不通过");
			}
		});
		
	}
	
	function recieveActiveCode(){
		if(!$("#email").val()){
			dialog.alert("请输入邮箱地址。");
			return;
		}
		$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			email : $("#email").val()
	   		},
	   		url:"<%=request.getContextPath()%>/login/recieveActiveCode.action",
	   		success:function(data){
	   			dialog.alert("激活码已发送。");
	   	    }
	   	});
	}
	
	function param(){
		var o = {};
		o.id = company.id;
		o.name=$("#name").val();
		o.domain=$("#domain").val();
		o.code=$("#code").val();
		o.province=$("#province").val();
		o.city=$("#city").val();
		o.industry=$("#industry").val();
		o.address=$("#address").val();
		o.industry=$("#industry").val();
		o.proportion=$("#proportion").val();
		o.email=$("#email").val();
		o.attention=$("#attention").val();
		o.attentionPhone=$("#attentionPhone").val();
		o.phoneNum=$("#phoneNum").val();
		o.website=$("#website").val();
		o.postCode = $("#postCode").val();
		o.startTime=$("#startTime").val();
		o.endTime = $("#endTime").val();
		o.logoImage = $("#filePath").val();
		return o;
	}
	
	function initIndustry(){
		$.ajax({
	   		type:"POST",
	   		async:false,  //默认true,异步
	   		url:"<%=request.getContextPath()%>/login/initIndustry.action",
	   		success:function(data){
	   			var re = '';
	   			for(var i=0;i<data.length; i++){
	   				//if(data[i].parentId != null){
	   					re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	   				//}
	   			}
	   			$("#industry").html(re);
	   	    }
	   	});
	}
	
	function initProvince(){
		$.ajax({
	   		type:"POST",
	   		async:false,  //默认true,异步
	   		url:"<%=request.getContextPath()%>/login/initCity.action",
	   		success:function(data){
	   			var re = '<option value="">请选择</option>';
	   			for(var i=0;i<data.length; i++){
	   				if(data[i].parent_id == 0){
	   					re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	   				}
	   			}
	   			$("#province").html(re);
	   	    }
	   	});
	}
	
	function initCity(provinceId){
		$.ajax({
	   		type:"POST",
	   		async:false,  //默认true,异步
	   		data:{
	   			provinceId : provinceId
	   		},
	   		url:"<%=request.getContextPath()%>/login/initCity.action",
	   		success:function(data){
	   			var re = '<option  value="">请选择</option>';
	   			for(var i=0;i<data.length; i++){
	   				re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	   			}
	   			$("#city").html(re);
	   	    }
	   	});
	}
	
	function upLoadImg() {
		var uploader = WebUploader
				.create({
					auto : true,
					// swf文件路径
					swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

					// 文件接收服务端。
					server : '<%=request.getContextPath()%>/megagameManage/addContest_uploadImg.action',

					// 选择文件的按钮。可选。
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.
					pick : {
						id : '#picker',
						multiple : false
					},

					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
					resize : false,

					// 只允许选择图片文件。
					accept : {
						title : 'Images',
						extensions : 'gif,jpg,jpeg,bmp,png',
						mimeTypes : 'image/*'
					}
				});

		uploader.on('fileQueued', function(file) {
		});

		// 接受文件后，进行赋值操作
		uploader.on('uploadAccept', function(file, ret) {
			$("#filePath").val(ret._raw);
			// 预览图片
			$("#previewPath").attr("src", ret._raw).css({
				"width" : "278px",
				"height" : "105px"
			});
		});

	}
	
	function cancel(){
		window.location.href="<%=request.getContextPath()%>/manageCompany/toCompanyListPage.action";
	}
	
	/**
	 * 表单验证
	 */
	function initValidate() {
		$('#addForm').validator({
			theme : 'yellow_right',
			rules : {
				checkDomain:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{domain:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0 && data[0].id != company.id){
								flag = false;
							}
							str =  flag || "已存在相同域名";
						}
					});
					return str;
				},
				checkCode:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{code:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0 && data[0].id != company.id){
								flag = false;
							}
							str =  flag || "已存在相同代码";
						}
					});
					return str;
				},
				checkSameEmail:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{email:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0 && data[0].id != company.id){
								flag = false;
							}
							str =  flag || "该邮箱已注册";
						}
					});
					return str;
				},
				checkSamePhone:function(element,param,field){
					var str;
					$.ajax({
						type:"POST",
						async:false,  //默认true,异步
						data:{phoneNum:element.value},
						url:"<%=request.getContextPath()%>/login/selectManageCompanyList.action",
						success:function(data){
							var flag = true;
							if(data.length > 0 && data[0].id != company.id){
								flag = false;
							}
							str =  flag || "该手机号码已注册";
						}
					});
					return str;
				},
				checkPostCode:[ /^[1-9][0-9]{5}$/, '邮政编码格式不正确！' ],
				checkMobile:[ /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/, '电话号码格式不正确！' ],
				checkPhone:[/^1[358]\d{9}$/,'电话号码格式不正确！'],
				checkDomainName:[/^[0-9a-zA-Z]*$/,'域名只能输入数字、字母！'],
				checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
			},
			msgStyle:"margin-top:10px;margin-left:10px;",
			fields : {
				code : {
					rule : "required;length[~30];checkCode",
					msg : {
						required : "企业大学代码不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				name : {
					rule : "required;length[~30]",
					msg : {
						required : "企业大学名称不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				domain : {
					rule : "required;length[~30];checkDomain;checkDomainName",
					msg : {
						required : "域名不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				industry : {
					rule : "required",
					msg : {
						required : "行业分类不能为空"
					}
				},
				province : {
					rule : "required",
					msg : {
						required : "省不能为空"
					},
					msgStyle:"left:-30px;",
					msgClass:"n-top"
				},
				city : {
					rule : "required",
					msg : {
						required : "市不能为空"
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
					rule : "required;email;checkSameEmail",
					msg : {
						required : "邮箱不能为空",
					}
				},
				postCode : {
					rule : "required;length[6];checkPostCode",
					msg : {
						required : "邮编不能为空",
						length : "长度必须等于6个字符"
					}
				},
				attention : {
					rule : "required;length[~30]",
					msg : {
						required : "联系人不能为空",
						length : "长度需小于等于30个字符"
					}
				},
				attentionPhone : {
					rule : "required;checkMobile",
					msg : {
						required : "联系电话不能为空",
						length : "长度需等于11个字符"
					}
				},
				phoneNum : {
					rule : "required;length[11];checkPhone;checkSamePhone",
					msg : {
						required : "手机号码不能为空",
						length : "长度需等于11个字符"
					}
				},
				website : {
					rule : "required;length[~30]",
					msg : {
						required : "网址不能为空",
						length : "长度需小于等于30个字符"
					}
				}
			}
		});
	}
</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>修改企业</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">修改企业</span>
	</div>
	<form id="addForm">
   	<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>企业大学名称：</em>
            </div>
            <div class="add_fr">
            	<input type="text" name="name" id="name"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>企业logo：</em>
            </div>
            <div class="add_fr"   style="line-height:20px;">
            	<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择图片</div>
						</div>
						<input id="filePath" type="hidden">
				</div>
            </div>
        </div>
        <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
                &nbsp;
            </div>
            <div class="add_fr">
					<p class="tp">
						<img id="previewPath" src="" width="210px" height="56px"/>
					</p>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>企业大学网站地址/域名：</em>
            </div>
            <div class="add_fr">
            	http://<input type="text" id="domain" name="domain"/>.anpeiwang.com
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>企业代码：</em>
            </div>
            <div class="add_fr">
            	<input type="text" class="i_name" id="code" name="code"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>详细地址：</em>
            </div>
            <div class="add_fr">
            	<select id="province" style="width:100px;" name="province">
                </select>
                <select id="city" style="width:100px;" name="city">
                </select>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	&nbsp;
            </div>
            <div class="add_fr">
            	<textarea id="address" name="address"></textarea>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>邮政编码：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="postCode" name="postCode"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>行业分类：</em>
            </div>
            <div class="add_fr">
            	<select id="industry" style="width:100px;" name="industry">
                	</select>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>企业规模：</em>
            </div>
            <div class="add_fr">
            	<select id="proportion" style="width:100px;" name="proportion">
                	<option value="1">100人以下</option>
                	<option value="2">100-999人</option>
                	<option value="3">1000-9999人</option>
                	<option value="4">10000人以上</option></select>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>邮箱：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="email" name="email"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>业务联系人：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="attention" name="attention"/>
            </div>
        </div>
          <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>联系电话：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="attentionPhone" name="attentionPhone"/>
            </div>
        </div>
          <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>使用期限：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="startTime" name="startTime" readonly/>至<input type="text" id="endTime" name="endTime" readonly/>
            </div>
        </div>
           <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>手机号码：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="phoneNum" name="phoneNum"/>
            </div>
        </div>
           <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
            	<em>公司网址：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="website" name="website"/>
            </div>
        </div>
        <div class="button_cz">
        	 <input type="button" class="btn_2" value="提交" onclick="save()"/>
        	 <input type="button" class="btn_2" value="取消" onclick="cancel()"/>
        </div>
    </div>
    </form>
</div>
</body>
</html>
