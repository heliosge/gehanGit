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
	.lesson_add .add_gr{width:450px;}
	.lesson_add .add_gr .add_fl {width:100px;}
	.lesson_add .add_gr .add_fr {width:300px;} 
	.lesson_add .add_gr .add_fr input[type=text] { width: 100px;}
</style>
<script type="text/javascript">

$(function(){
	
	initDatepicker();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageCompany/selectManageCompanyCapacityList.action",
    	width: $('#exampleTable').width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //multiSelect: true,
        //checkCol: true,
        indexCol: true,  //索引列
        params: function(){
	    	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '企业代码', name: 'code', width:60, align:'center'},
 			   {title: '企业大学名称', name: 'name', width:120, align:'center'},
 			   {title: '行业分类', name: 'industryName', width:60, align:'center'},
 			   {title: '企业域名', name: 'domain', width:60, align:'center'},
			   {title: '注册时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);
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
			   {title: '可用容量（GB）/总容量（GB）', name: 'totalCapacity', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return (val-item.usedCapacity).toFixed(2)+"/"+val;
			   }},
			   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="javascript:void(0);" onclick="addCapacityDiv('+val+','+item.totalCapacity+')" >扩容</a>'  ;
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
	o.domain = $("#domain").val();
	o.status = $("#status").val();
	o.startTime = $("#startTime").val();
	o.endTime = $("#endTime").val();
	o.industry = $("#industry").val();
	o.isOver80Per = $("input[name='isOver80Per']:checked").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
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



function addCapacityDiv(id,totalCapacity){
	$("#companyId").val(id);
	$("#totalCapacity").html(totalCapacity+'GB');
	$("#addCapacity").val('');
	dialog({
		width : 450,
		height : 200,
		title : '扩容',
		content : $("#checkDialog"),
		fixed:true,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  addCapacity();
	            	  this.close();
	              }
		          },
		          {
		              value: '取消',
		              callback: function () {
		            	  this.close();
		              }
			          }
		      ]
		}).showModal();
}

function addCapacity(){
	var o = {};
	o.id = $("#companyId").val();
	o.addCapacity = $("#addCapacity").val();
	if(isNaN(o.addCapacity)){
		//dialog.alert("需扩容量必须是数字");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:o,
		url:"<%=request.getContextPath()%>/manageCompany/addCapacity.action",
		success:function(data){
			if(data == 'SUCCESS'){
				dialog.alert("扩容成功。",function(){search();});
			}else{
				dialog.alert("扩容失败。");
			}
		}
	});
}

function exportFile(){
	$("#form").submit();
}


</script>
</head>

<body>
<div id="checkDialog" style="display:none;">
		 <div class="lesson_add" style="width:450px;">
		 	<div class="add_gr">
	        	<div class="add_fl">
	                <em>目前总容量：</em>
	            </div>
	            <div class="add_fr" id="totalCapacity">
	            	
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>需扩容：</em>
	            </div>
	            <div class="add_fr">
	            	<input type="hidden" id="companyId"/>
	            	<input type="text" id="addCapacity"/>GB
	            </div>
          	</div>
		 </div>
	</div>


<div class="content">
	<!-- <h3>企业容量管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">企业容量管理</span>
	</div>
    <div class="btn_gr">
        <input type="button" class="btn_2" value="批量导出企业" onclick="exportFile()"/>
    </div>
    <form id="form" action="<%=request.getContextPath()%>/manageCompany/exportExcel.action" method="post">
		<div class="search_2">
	        	<p>	企业代码：
		<input type="text" id="code" name="code"/>
		企业大学名称：
		<input type="text" class="input_1" id="name" name="name"/>
		行业分类：
		<input type="text" id="industry" name="industry"/>
		注册时间：
		<input type="text" id="startTime" name="startTime"/>至<input type="text" id="endTime" name="endTime"/>
		</div>
		<div class="search_2"><p>
		企业域名：
		<input type="text" id="domain" name="domain"/>
		<!-- 状态：
		<select id="status">
		<option value="">全部</option>
		<option value="1">正常</option>
		<option value="2">冻结</option>
		<option value="4">待审批</option>
		<option value="5">审批不通过</option>
		</select> -->
	    <input type="checkbox" id="isOver80Per" name="isOver80Per" value="1"/>显示所用容量超过80%的企业
		</p>
		<input type="button" value="查询" class="btn_cx" onclick="search_()"/>
		</div>
	</form>
	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
	<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     
</div>

</body>
</html>
