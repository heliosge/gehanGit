<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>门户信息维护</title>
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
<link rel="stylesheet" href="<%=basePath%>js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/lang/en.js"></script>
<script charset="utf-8" src="<%=basePath%>js/kindeditor-4.1.10/plugins/code/prettify.js"></script>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<!-- 弹出框 -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>
<%
	//获取语言类型
	HttpSession qqq = request.getSession();
String language= String.valueOf(RequestContextUtils.getLocale(request));

%>
<style type="text/css">

html, body{
	height: 100%;
}
.page_div {width: 250px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
.page_div h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}

</style>

<script type="text/javascript">
var company = ${company};

$(function(){
	initPage();
	fillCompanyInfo();
});

function fillCompanyInfo(){
	$("#createTime").html(getSmpFormatDateByLong(company.createTime, false));
}

function initPage(){
	if(company.initUserId == null){
		return;
	}
	//获取所有权限页面
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
/* 			check: {enable: false},
 */			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
 			},view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				dblClickExpand: false,
				addDiyDom: addDiyDom
			}
	};
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"userId":company.initUserId},
		url: "<%=request.getContextPath()%>/index/getPageList.action",
		success:function(data){
			$.fn.zTree.init($("#chooseTreePage"), setting, data);
		}
	});
	
}


function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageCompany/toCompanyListPage.action";
}

function changeBaseInfo(){
	window.location.href="<%=request.getContextPath()%>/manageCompany/toUpdateCompanyBaseInfoPage.action?id="+company.id;
}

function changeRseInfo(){
	window.location.href="<%=request.getContextPath()%>/manageCompany/toUpdateCompanyResPage.action?id="+company.id;
}



</script>

<script type="text/javascript">
	
	var editor;
	KindEditor.ready(function(K){
		var language= '<%=language%>';
		var langType ="zh_CN";
		if(language=="en_US"){
			langType ="en";
		} 
		 
		editor = K.create("textarea[name=content]",{
			width: "80%",
			height: "430px",
			pasteType: 2,
			langType : langType,
			uploadJson : "<%=basePath%>file/uploadImg.action?path=CommonImage&DB=false",
			fileManagerJson : "<%=basePath%>file/filemanageJson.action",
			allowFileManager : false,
			filePostName:"uploadFile",
			items: [
			        'source', '|','undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			        'flash', 'media','insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			        'anchor', 'link', 'unlink', '|', 'about','anchor'
				]
			
		});
		//prettyPrint();
	});	

</script>
</head>

<body style="overflow-x:hidden;">

<div class="content">
	<!-- <h3>门户信息维护</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">门户信息维护</span>
	</div>
    <div class="lesson_tab_1">
        	<ul>
                <li class="li_this" type='3'>企业信息查看</li>
                <li class="" type='2'>自定义登录</li>
            	<li class="" type='1'>联系我们</li>
            </ul>
	</div>
    <div class="content_2 about-us" style="display: none;float: left;width: 100%;">
         <div class="add_gr">
            <div class="add_fr">
				<textarea rows="10" cols="110" name="content"></textarea>
            </div>
        </div>
        <div class="button_gr" style="text-align:center;">
        	<input type="button" value="保存" class="btn_4" onclick="saveAboutUs()" style="margin-top: 20px;">
<!--             <input type="button" value="返回">
 -->        </div>
   </div>
   
   
 <div class="content_2 company-detail" style="display: block;">
  <div class="lesson_add_2">
  	<div class="add_gr" style="background: #EEE;">
        	<div class="add_fl"  style="font-weight: bold;">
                <em>账户信息：</em>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>初始账号：</em>
            </div>
             <div class="add_fr">
            	${company.initUsername }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>购买账户数量：</em>
            </div>
             <div class="add_fr">
            	${company.accountCount }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>已用账户数量：</em>
            </div>
             <div class="add_fr">
            	${usedAccountCount }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>使用期限：</em>
            </div>
             <div class="add_fr" id="useTime">
            	${company.startTime }至${company.endTime }
            </div>
    	</div>
    	<div class="add_gr" style="height:auto;">
        	<div class="add_fl">
                <em>拥有权限：</em>
            </div>
             <div class="add_fr" >
             	<div class="page_div" >
            	<h2>拥有功能模块</h2>
	            <ul id="chooseTreePage" class="ztree" ></ul>
	            </div>	
            </div>
    	</div>
		<div class="add_gr" style="background: #EEE;">
        	<div class="add_fl" style="font-weight: bold;">
                <em>基本信息：</em>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>企业代码：</em>
            </div>
             <div class="add_fr">
            	${company.code }
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em> 企业大学名称：</em>
            </div>
             <div class="add_fr">
             ${company.name }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 企业大学地址/域名：</em>
            </div>
             <div class="add_fr">
             http://${company.domain }.anpeiwang.com
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 企业邮箱：</em>
            </div>
             <div class="add_fr">
             ${company.email }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 详细地址：</em>
            </div>
             <div class="add_fr">
             ${company.address }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 邮政编码：</em>
            </div>
             <div class="add_fr">
             ${company.postCode }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 所属行业：</em>
            </div>
             <div class="add_fr">
             ${company.industryName }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 企业规模：</em>
            </div>
             <div class="add_fr">
             ${company.proportion }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 业务联系人：</em>
            </div>
             <div class="add_fr">
             ${company.attention }
            </div>
    	</div>
    	
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 联系电话：</em>
            </div>
             <div class="add_fr">
             ${company.attentionPhone }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 创建时间：</em>
            </div>
             <div class="add_fr" id="createTime">
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 手机号码：</em>
            </div>
             <div class="add_fr">
             ${company.phoneNum }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 公司网站：</em>
            </div>
             <div class="add_fr">
             ${company.website }
            </div>
    	</div>
    
      </div>
</div>
	<div id="course_all "  class="content_2 define-login" style="display: none;">
      <div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl" >
                <em>登录页背景图：</em>
            </div>
            <div class="add_fr" style='position:relative; ;top: 8px;'>
 				<div id="uploader" class="wu-example">
			    <!--用来存放文件信息-->
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				        <div id="picker">选择图片</div>
				    </div>
				</div>
				<span style="position: absolute;margin-left: 200px;margin-top: -37px;">支持图片格式为：gif,jpg,jpeg,bmp,png。建议分辨率为1024*768以上</span>
				<input id="filePath" type="hidden">
             </div>
        </div>
          <div class="add_gr">
        	<div class="add_fl">&nbsp
            </div>
            <div class="add_fr" style="margin-top: 7px;">
           		<div class="previewPic">
           			<img alt="" src="" name="previewPath" id="previewPath">
           		</div>
            </div>
            
            
        </div>
           <div class="button_cz" style="margin-left: 187px;">
           	<input type="button" value="保存" onclick="saveLoginPic()">
           </div>
      </div>

                
                	
  </div>
    </div>
    <div id="tempDiv" style='display:none'></div>
<script  type='text/javascript'>
	var uploader;
	//保存关于我们信息
	function saveAboutUs(){
		
		var param = {};
		param.infoDesc=editor.html();//knowledge_name
		param.type=1;
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/information/saveAboutUsInfo.action",
    		success:function(data){
    			if("SUCCESS"==data){
    				dialog.alert("保存成功",function(){
    				});
    			}else{
    				dialog.alert("保存失败");
    			}
    	    }
    	});
	}
	//保存登录图片
	function saveLoginPic(){

		var param = {};
		param.infoDesc=$("#filePath").val();// 
		param.type=2;
		$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:param,
    		url:"<%=request.getContextPath()%>/information/saveAboutUsInfo.action",
    		success:function(data){
    			if("SUCCESS"==data){
    				dialog.alert("保存成功",function(){
    				});
    			}else{
    				dialog.alert("保存失败");
    			}
    	    }
    	});
		
	}
	
	$("div.lesson_tab_1").find("ul li").on("click",function(){
		//点击切换时，需要进行以下事情
		//2、进行标签的切换
		$(this).siblings().removeClass("li_this");
		$(this).addClass("li_this");
		
		var type = $(this).attr("type")
		$("div.content_2").hide();
		if(1==type){
			$("div.about-us").show();
		}else if(3==type){
			$("div.company-detail").show();
			
		}else if(2==type){
			$("div.define-login").show();

			if(uploader){
				return;
			}
			    uploader = WebUploader.create({
				auto: true,
			    // swf文件路径
			    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

			    // 文件接收服务端。
			    server:'<%=request.getContextPath()%>/teacher/uploadImg.action',

			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    pick:  {id:'#picker',
	    			multiple:false
	    		},

			    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    resize: false,

			    // 只允许选择图片文件。
			    accept: {
			        title: 'Images',
			        extensions: 'gif,jpg,jpeg,bmp,png',
			        mimeTypes: 'image/*'
			    }
			});
			
			uploader.on( 'fileQueued', function( file ) {
			});
			
			//接受文件后，进行赋值操作
			uploader.on( 'uploadAccept', function( file ,ret ) {
				 $("#picker").find("span.error").remove();
				$("#filePath").val(ret._raw);
				
				//预览图片
				$("#previewPath").attr("src",ret._raw).css({"width":"700px","height":"300px"});;
				$("#previewPath").closest("div.add_gr").css("height","300px");
			});
		}
		
	})
	
	
	//上传图片
	$(function(){
	//	var iconfile;
	

	 var  validate = $("#addForm").validate({ 
		   focusCleanup:true,      
	       rules: {  
	    	   infoName: {  
                required:true,  
                rangelength:[1,30],  
        		},
        	 content:{
        		 required:true,
   		   },
   			filePath:{
				   required:true,  
				   email:true 
			   }
	       },  
	       messages:{  
	    	   infoName: {  
                required:"请输入标题",  
                rangelength:"输入长度必须小于30个字符",
     	   },
     	  content:{
     		   required:"请输入内容",
     	   },
     	  filePath:{
                required:"请选择图片",  
			   },
			   
	       },  
	   })
	function validateForm() {  
	   //validate方法参数可选  
	   var checkForm =function(){
		   
		  if($.trim(editor.html())==""){
			  dialog.alert("请输入内容");
			  return false;
		  } 
		   
		   if($.trim($("#filePath").val())==""){
			   $("#picker").append('<label for="filePath" class="error" style="isplay: inline;position: absolute; width: 200px; top: 0px;left: 120px;height: 30px; line-height: 30px;">请选择图片</label>')  
			   return false; 
		   }
			 return  true;
	   }
	   
	  return validate.form()&&checkForm();  
	}  
	
   $("#ctlBtn").on( 'click', function() {
	   
	   if(validateForm()){
		   saveInformation();
	   }
	   
   	 });
   
   //初始化数据
   function initData(){
	   //查找关于我们等信息
	   var param = {};
		param.type=1;
		$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:param,
   		url:"<%=request.getContextPath()%>/information/editAboutUsInfo.action",
   		success:function(data){ 
   			if(data!="null"){
	   			$("#tempDiv").html(data);
	   			editor.html($("#tempDiv").html());
   			}
   	    }
   		
   	});
		param.type=2;
		$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:param,
   		url:"<%=request.getContextPath()%>/information/editAboutUsInfo.action",
   		success:function(data){ 
			//预览图片
			if(data!=""&&data!="null"){
   			 $("#picker").find("span.error").remove();
				$("#filePath").val(data);
				$("#previewPath").attr("src",data).css({"width":"700px","height":"300px"});
				$("#previewPath").closest("div.add_gr").css("height","300px");
			}
   	    }
   	});
		
   }
   initData();
   
})
	
	</script>
</body>
</html>
