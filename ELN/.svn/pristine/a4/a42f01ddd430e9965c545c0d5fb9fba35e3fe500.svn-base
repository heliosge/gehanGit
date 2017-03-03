<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>



<style type="text/css">
	.ztree li span.button.noline_docu{width:0px;}
	.ztree li span.button.noline_open{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.td {text-align: center;padding-left: 16px;height: 36px;border: 1px solid#EAEAEA;padding-right: 16px;font-size:14px;}
	.page_div {width: 250px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script type="text/javascript">
var company = ${company};

$(function(){
	initPage();
	if(company.proportion==1){
		$("#proportion").html('100人以下');
	}else if(company.proportion==2){
		$("#proportion").html('100-999人');
	}else if(company.proportion==3){
		$("#proportion").html('1000-9999人');
	}else if(company.proportion==4){
		$("#proportion").html('10000人以上');
	}
});


function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
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
			check: {enable: false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
			}
	};
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"userId":company.initUserId},
		url: "<%=request.getContextPath()%>/index/getPageList.action",
		success:function(data){
			addIconInfo(data);
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
	if(company.id == 1){
		dialog.alert("普联企业权限不可修改。");
	}else{
		if(company.status == 1){
			window.location.href="<%=request.getContextPath()%>/manageCompany/toUpdateCompanyResPage.action?id="+company.id;
		}else{
			dialog.alert("请先审批、解冻该企业。");
		}
	}
}



</script>

</head>
<body>

<div class="content">
	<!-- <h3>查看企业</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="cancel();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看企业</span>
	</div>
	<div class="lesson_add_2">
		<div class="add_gr" style="background: #EEE;">
        	<div class="add_fl" style="font-weight: bold;">
                <em>基本信息：</em>
            </div>
             <div class="add_fr">
            	<input type="button" value="修改"  id="name" style="height:40px;" onclick="changeBaseInfo()"/>
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
                <em> 企业名称：</em>
            </div>
             <div class="add_fr">
             ${company.name }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 企业大学LOGO：</em>
            </div>
             <div class="add_fr">
             <img id="previewPath" src="${company.logoImage }" width="131px" height="27px"/>
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
                <em> 行业分类：</em>
            </div>
             <div class="add_fr">
             ${company.industryName }
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em> 企业规模：</em>
            </div>
             <div class="add_fr" id="proportion">
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
             ${company.createTime }
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
    	<div class="add_gr" style="background: #EEE;">
        	<div class="add_fl"  style="font-weight: bold;">
                <em>账户信息：</em>
            </div>
             <div class="add_fr">
            	<input type="button" value="修改"  id="name" style="height:40px;" onclick="changeRseInfo()"/>
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
                <em>初始密码：</em>
            </div>
             <div class="add_fr">
            	${company.initPassword }
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
             	<div class="page_div">
            	<h2>拥有功能模块</h2>
	            <ul id="chooseTreePage" class="ztree" style=""></ul>
	            </div>	
            </div>
    	</div>
        <div class="button_cz">
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
      </div>
   </div>
</body>
</html>
