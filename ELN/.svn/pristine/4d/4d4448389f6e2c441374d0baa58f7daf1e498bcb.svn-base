<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模拟考试</title>
<style type="text/css">
	.clear_both{clear:both;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<!--日期控件  -->
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script> --%>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
$(function(){
	$("#userId").val(userId);
	$('#examTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/myExamManage/getExamSimulate.action',
		width: '1046px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.user_id = $("#userId").val();
	    	param.title = escapeForSql($.trim($("#title").val()));
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'考试名称', name:'title', width:150,align:'center'},
	           {title:'考试次数', name:'testTimes', width:50,align:'center'},
	           {title:'考试时长',name:'duration', width:50,align:'center'},
	           {title:'上次考试得分', name:'score', width:50,align:'center',
	        	   renderer:function(val,item, rowIndex){
	        		   if(item.testTimes < 1){
	        			   return "-"; 
	        		   }else{
	        			   return val;
	        		   }
	               		
               		}
	           },
	           {title:'上次考试时间', name:'beginTime', width:50,align:'center'},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str_enter = '<a href="javascript:void(0);" onclick="enterExam('+ rowIndex +')" >进入考试</a>&nbsp;';
                		return str_enter;
                	}	
	}] ,
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
})

/*进入考试  */
function enterExam(rowIndex){
	//alert("考试ID：" + id);
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	window.open("<%=request.getContextPath() %>/myExamManage/gotoExamTest.action?exam_assign_id="+rowData.examAssignId);
}


function jqchk(){ //jquery获取复选框值 
	 var chk_value ='';
	$('input[name="subcheck"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//查询
function query(){
	$('#examTable').mmGrid().load({"page":1});
}

/* $(document).ready(function() {     
	$("#beginTime").datepicker({
		dateFormat:'yy-mm-dd'
		//minDate: 0,   
 		//maxDate: da,
	});
	$("#endTime").datepicker({
		dateFormat:'yy-mm-dd'
		//minDate: 0,   
		//maxDate: da,
	});
});  */


//重置
function clearAll(){
	//$("#beginTime").val("");
	//$("#endTime").val("");
	$("#title").val("");
	query();
}
</script>
</head>
<body>
<input type="hidden" id="userId" name="userId"/>
<div id="course_all">
	<div class="notes_list" style="padding-bottom: 0px;">
    	<!-- <h3>模拟考试</h3> -->
        <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">模拟考试</span>
		</div>
        <div class="search_1">
                     <!-- <span>
                    	考试时间：<input type="text" id="beginTime" name="beginTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                    	至 <input type="text" id="endTime" name="endTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                    </span> -->
                     <span>考试名称：<input type="text" id="title" name="title"/></span>
                     <!-- <strong>是否通过：是<input type="checkbox" name="subcheck" value="1"/>否<input type="checkbox" name="subcheck" value="0"/></strong> -->
                    <input type="button" class="btn_5" onclick="query();" value="查询" />
                    <input type="button" class="btn_6" onclick="clearAll();" value="重置" />
                  
            
        </div>
        <!-- <div class="tab_1">
        	<table cellspacing="0" width="100%">
            	<tr>
                	<th width="33%">考试名称</th>
                    <th width="33%">考试时间</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td>****考试</td>
                    <td>3</td>
                    <td><a href="#" class="first_a">进入考试</a></td>
                </tr>
               
            
            </table>
        
        </div> -->
     </div>
     <div class="clear_both"></div>
      <div id="dataDiv">
     	<table id="examTable"></table>
	  	<div id="paginator11-1" style="text-align:right;"></div>
     </div>
        <!-- <div class="page clear">
     	<div class="left_page fl">
        	<a href="#" class="first">20</a>
            <a href="#">40</a>
            <a href="#">100</a>
            <a href="#">200</a>
        </div>
        <div class="right_page fr">
        	<span>共254条</span>
            <a href="#" class="r_first">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">></a>
            <span class="span_1">去第<input type="text" />页</span>
            <input type="button" name="tz" value="跳转" class="btn_2"/>            
        </div>
     </div> -->
    </div>
	<%@include file="../common/footer.jsp" %>
</body>
</html>
