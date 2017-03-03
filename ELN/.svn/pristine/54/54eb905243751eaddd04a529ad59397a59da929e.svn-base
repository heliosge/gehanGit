<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册企业管理</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />

<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
</style>
<script type="text/javascript">

$(function(){
	
	initDatepicker();
	initIndustry();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageCompany/selectManageCompanyList.action",
    	width: $('#exampleTable').width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol: true,  //索引列
        params: function(){
	    	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '企业大学名称', name: 'name', width:120, align:'center'},
 			   {title: '行业分类', name: 'industryName', width:60, align:'center'},
 			   {title: '手机', name: 'phoneNum', width:90, align:'center'},
 			   {title: '邮箱', name: 'email', width:120, align:'center'},
 			   {title: '企业域名', name: 'domain', width:120, align:'center'},
			   {title: '注册时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);
			   }},
			   {title: '到期时间', name: 'endTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return val == null ? '' : getSmpFormatDateByLong(val, false);
			   }},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }else if(val == 4){
					   return "待审批";
				   }else if(val == 5){
					   return "审批不通过";
				   }
			   }},
			   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
				   
				   var buttonStr ='';
				   if(item.id==1){
					   buttonStr += '<a style="color: #999;">冻结</a>  ';
				   }else{
					   if(val == 1){
						   buttonStr += '<a href="javascript:void(0);" onclick="freeze('+item.id+')" >冻结</a>  ';
					   }else if(val == 2){
						   buttonStr += '<a href="javascript:void(0);" onclick="unfreeze('+item.id+')" >解冻</a>  ';
					   }else if(val == 4 || val == 5){
						   buttonStr += '<a href="<%=request.getContextPath()%>/manageCompany/toCheckCompanyPage.action?id='+item.id+'" >审批</a>  ';
					   }
				   }
				   buttonStr += '<a href="<%=request.getContextPath()%>/manageCompany/toUpdateCompanyBaseInfoPage.action?id='+item.id+'" >修改</a>'+
						   ' <a href="<%=request.getContextPath()%>/manageCompany/toCompanyDetailPage.action?id='+item.id+'" >详情</a>';
				   if(val == 1){
					   if(item.id == 1){
						   buttonStr += ' <a href="javascript:void(0);" style="color: #999;">权限</a>';
					   }else{
						   buttonStr += '  <a href="<%=request.getContextPath()%>/manageCompany/toUpdateCompanyResPage.action?id='+item.id+'" >权限</a>';
					   }
				   }else{
					   buttonStr += ' <a href="javascript:void(0);" style="color: #999;">权限</a>';
				   }
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

function param(){
	var o = {};
	o.code = $("#code").val();
	o.name = $("#name").val();
	o.phoneNum = $("#phoneNum").val();
	o.email = $("#email").val();
	o.domain = $("#domain").val();
	o.status = $("#status").val();
	o.startTime = $("#startTime").val();
	o.endTime = $("#endTime").val();
	o.industry = $("#industry").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function initIndustry(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/login/initIndustry.action",
		success:function(data){
			var html = '<option value="">请选择</option>';
			$.each(data,function(index, val){
				html += '<option valie="'+val.name+'">'+val.name+'</option>';
			});
			$("#industry").html(html);
		}
	});
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
	
	/* $("#startTime").datepicker('setDate', (new Date()) );
	$("#endTime").datepicker('setDate', (new Date()) ); */
}

function freeze(id){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			ids : param,
   			status : 2
   		},
   		url:"<%=request.getContextPath()%>/manageCompany/freezeAndUnfreezeManageCompany.action",
   		success:function(data){
   			search();
   			dialog.alert("冻结成功。");
   	    }
   	});
}

function freezeRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
    	$.each(selectRows, function(index, val){
    		if(val.status != 4 && val.status != 5 ){
	    		param.push(val.id);
    		}
    	});
    	if(param.length == 0){
    		dialog.alert("请先审批选中的企业。");
    		return;
    	}
    	
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{
    			ids : param,
    			status : 2
    		},
    		url:"<%=request.getContextPath()%>/manageCompany/freezeAndUnfreezeManageCompany.action",
    		success:function(data){
    			search();
    			dialog.alert("冻结成功。");
    	    }
    	});
	}else{
		dialog.alert('请先选择数据！');
	}
}

function unfreeze(id){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			ids : param,
   			status : 1
   		},
   		url:"<%=request.getContextPath()%>/manageCompany/freezeAndUnfreezeManageCompany.action",
   		success:function(data){
   			search();
   			dialog.alert("解冻成功。");
   	    }
   	});
}

function unfreezeRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		var param = [];
    	$.each(selectRows, function(index, val){
    		if(val.status != 4 && val.status != 5 ){
    			param.push(val.id);
    		}
    	});
    	if(param.length == 0){
    		dialog.alert("请先审批选中的企业。");
    		return;
    	}
    	$.ajax({
    		type:"POST",
    		async:true,  //默认true,异步
    		data:{
    			ids : param,
    			status : 1
    		},
    		url:"<%=request.getContextPath()%>/manageCompany/freezeAndUnfreezeManageCompany.action",
    		success:function(data){
    			search();
    			dialog.alert("解冻成功。");
    	    }
    	});
	}else{
		dialog.alert('请先选择数据！');
	}
}

function toInsertCompany(){
	window.location.href="<%=request.getContextPath()%>/manageCompany/toCompanyAddPage.action";
}

var artDialog;
//导入讲师
function importFile(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageCompany/importFile.action",
        title:"导入企业" ,
		height: 250,
		width: 450
		}).showModal();
}

function downloadTemplate(){
	$("#form").submit();
}


</script>
</head>

<body>



<div class="content">
	<!-- <h3>企业管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">企业管理</span>
	</div>
    <div class="btn_gr">
    	<input type="button" class="btn_1" value="新增企业" onclick="toInsertCompany()"/>
        <input type="button" class="btn_2" value="批量导入企业" onclick="importFile()"/>
       <!--  <a class="btn_1" href="/upload/template/companyTemplate.xls">下载模板</a> -->
         <input type="button" class="btn_1" value="下载模板" onclick="downloadTemplate()"/>
        <input type="button" class="btn_1" value="冻结" onclick="freezeRows()"/>
        <input type="button" class="btn_1" value="解冻" onclick="unfreezeRows()"/>
    </div>
    
     <form id="form" action="<%=request.getContextPath()%>/teacher/downloadTemplate.action" method="post">
    	<input type="hidden" value="template/导入企业模板.xls" name="path"/>
 	    <input type="hidden" value="导入企业模板.xls" name="fileName"/>
    </form>
    
	<div class="search_2">
        	<p>	企业代码：
	<input type="text" id="code"/>
	企业大学名称：
	<input type="text" class="input_1" id="name"/>
	手机：
	<input type="text" id="phoneNum"/>
	邮箱：
	<input type="text" id="email"/>
	行业分类：
	<select id="industry"/></select>
	</p>
	</div>
	<div class="search_2"><p>
	企业域名：
	<input type="text" id="domain"/>
	状态：
	<select id="status">
	<option value="">全部</option>
	<option value="1">正常</option>
	<option value="2">冻结</option>
	<option value="4">待审批</option>
	<option value="5">审批不通过</option>
	</select>
	注册时间：
	<input type="text" id="startTime"/>至<input type="text" id="endTime"/>
	</p>
	<input type="button" value="查询" class="btn_cx" onclick="search_()"/>
	</div>
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     
</div>

</body>
</html>
