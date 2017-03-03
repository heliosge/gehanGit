<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />

<body>

<script type="text/javascript">
var id = ${USER_ID_LONG};

$(function(){
	
	initDatepicker();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/selectMyRecNoticeList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol: true,  //索引列
        params: function(){
	    	var o = {};
	    	o.recUserId = id;
	    	o.isSystem = 2;
	    	o.recDeleteFlag = 1;
	    	o.isRead = $("#isRead").val();
	    	o.title = $("#title").val();
	    	o.startTime = $("#startTime").val();
	    	o.endTime = $("#endTime").val();
	    	return o;
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '序号', name: 'id', width:60, hidden:true},
 			   {title: '状态', name: 'isRead', width:60, align:'center',renderer:function(val, item, rowIndex){
				   return val == '1'?'已读':'未读';
			   }},
			   {title: '邮件标题', name: 'title', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="noticeDetail.action?id='+item.id+'">'+val+'</a>';
			   }},
 			   {title: '发件人', name: 'sendUserName', width:60, align:'center'},
			   {title: '时间', name: 'sendTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);
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

function search(){
	$('#exampleTable').mmGrid().load();
}

function deleteRows(){
	//被选中的一行
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
    	$.each(selectRows, function(index, val){
    		param.push(val.id);
    	});
    	
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{
    			ids : param,
    			recDeleteFlag : 1
    		},
    		url:"<%=request.getContextPath()%>/manageUser/deleteRecNotice.action",
    		success:function(data){
    			search();
    	    }
    	});
	}else{
		dialog.alert('请先选择数据！');
	}
}

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

</script>

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
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li  class="active_2"><a href="#"  class="active_3">站内信-收件箱</a></li>
         	<li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
        <div class="right_option5" style="padding-bottom:0px;">
            <input type="button" value="批量删除" class="btn_10" onclick="deleteRows()"/>
            <p>邮件标题：<input type="text" id="title"/>发件人：<input type="text" />
            	<select style="height:32px;" id="isRead">
                 	<option value="">全部</option>
                    <option value="1">已读</option>
                    <option value="2">未读</option>
                </select>
            </p>
            <p>时间：<input type="text" id="startTime"/>至<input type="text" id="endTime"/><input type="button" value="查询" class="btn_11" onclick="search()"/></p>
        </div>
         	
         	
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
    </div>
</div>
</body>

</html>
