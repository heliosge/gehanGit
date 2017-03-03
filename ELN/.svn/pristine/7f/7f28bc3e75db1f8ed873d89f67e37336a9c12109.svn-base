<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>结果统计</title>
<style type="">
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
$(function(){
	$("#userId").val(userId);
	$('#investigationTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/questionnaire/getResultList.action',
		width: '1065px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.title = escapeForSql($.trim($("#title").val()));
	    	param.beginTime = $("#publishBeginTime").val();
	    	param.endTime = $("#publishEndTime").val();
	    	param.state = $("#state").val();
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'调查名称', name:'title', width:200,align:'center'},
	           {title:'发布时间', name:'publishTime', width:170,align:'center'},
	           {title:'调查时间', name:'beginTime', width:200,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.endTime != null){
            			   return item.beginTime + "-" + item.endTime;
            		   }else{
            			   return item.beginTime + "-";
            		   }
               		
               	}
               },
               {title:'调查状态',name:'state', width:80,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.state == 1){
            			   return "未开始"; 
            		   }else if(item.state == 2){
            			   return "进行中";
            		   }else{
            			   return "已结束";
            		   } 
              		}
              	},
              	{title:'安排人数',name:'totalNum', width:80,align:'center',
             	   renderer:function(val,item, rowIndex){
             		   return item.intendNum+"/"+item.totalNum;
               		}
               	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str = '';
                		str = '<a href="javascript:void(0);" onclick="resultTotal('+ item.id +')" >结果统计</a>&nbsp;';
                		return str;	  
                	}	
	}] ,
	   plugins : [
	    	$('#paginator11-1').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'pageNo',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
})

/*查看报告  */
function resultTotal(id){
	window.location = '<%=request.getContextPath() %>/questionnaire/toResultToal.action?id='+id;
}

//查询
function query(){
	$('#investigationTable').mmGrid().load({"page":1});
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#investigationTable').mmGrid().load();
	}else{
		$('#investigationTable').mmGrid().load({"page":1});		
	}
}
$(document).ready(function() {     
	$("#publishBeginTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
 		//maxDate: da,
	});
	$("#publishEndTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
		//maxDate: da,
	});
}); 

//重置
function clearAll(){
	$("#title").val("");
	$("#publishBeginTime").val("");
	$("#publishEndTime").val("");
    $('#state').val("");
    query();
}

</script>
</head>
<body>
    <div class="content">
	<!-- <h3>结果统计</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">结果统计</span>
	</div>
    <div class="content_2">
        <form id="form">
        <div class="search_2 fl">
			<p>调查名称：<input type="text" id="title" name="title"/>
               	发布时间：<input type="text" id="publishBeginTime" name="publishBeginTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                  	至 <input type="text" id="publishEndTime" name="publishEndTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
              		调查状态：
               <select id="state" name="state" style="width:80px;">
                   	<option value="0">全部</option>
                   	<option value="1">未开始</option>
                   	<option value="2">进行中</option>
                   	<option value="3">已结束</option>
                   </select>
   
           </p>
           <input type="button" onclick="clearAll();" value="重置" />
           <input type="button" class="btn_cx" onclick="query();" value="查询" />
	        </div>
        </form>
         <div class="clear_both"></div>
	     <div id="dataDiv">
	     	<table id="investigationTable"></table>
		  	<div id="paginator11-1" style="text-align:right;"></div>
	     </div>
   </div> 
</div>
	 <%@include file="../common/footer.jsp" %>
</body>
</html>
