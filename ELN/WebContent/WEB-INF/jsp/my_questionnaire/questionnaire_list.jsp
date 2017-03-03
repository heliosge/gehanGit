<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参与问卷</title>
<style type="">
 .timer{padding-left: 5px;color: red;font-size: 18px;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
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
var isPeriod = null;
$(function(){
	$("#userId").val(userId);
	$('#examTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/myQuestionnaire/getList.action',
		width: '1046px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.type = 1;
	    	param.user_id = $("#userId").val();
	    	param.status = $("#status").val();
	    	param.title = escapeForSql($.trim($("#title").val()));
	    	param.beginTime = $("#beginTime").val();
	    	param.endTime = $("#endTime").val();
	    	param.isPeriod = $("#isPeriod").val();
	    	param.isPublic = $("#isPublic").val();
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'调查名称', name:'title', width:170,align:'center'},
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
 	           {title:'是否在调研期',name:'isPeriod', width:80,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isPeriod == 1){
            			   return "是"; 
            		   }else{
            			   return "否";
            		   }
              		}
              	},
               {title:'是否可以查看结果',name:'isPublic', width:100,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isPublic == 1){
            			   return "是";
            		   }else{
            			   return "否";
            		   }
              		}
              	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str = '';
                		var str_view = '<a href="javascript:void(0);" onclick="view('+ rowIndex +')" >查看</a>&nbsp;';
                		var str_attend = '<a href="javascript:void(0);" onclick="attend('+ rowIndex +')" >参与调查</a>&nbsp;';
                		var str_viewResult = '<a href="javascript:void(0);" onclick="viewResult('+ item.id +')" >结果汇总</a>';
                   		if(item.status == 1 && item.isPeriod == 1){
                   			str += str_attend;
                   		}
                		//if(item.status == 2){
                			str += str_view;
                		//}
                		if(item.status == 2 && item.isPublic == 1){
                			str += str_viewResult;
                		}
                		return str;	  
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

/*查看  */
function view(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	window.location = "<%=request.getContextPath() %>/myQuestionnaire/toUserAnswerDetail.action?id="+rowData.id+"&assignId="+rowData.assignId+"&backType=questionnaireList";
}


/*参与调查  */
function attend(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	window.location = "<%=request.getContextPath() %>/myQuestionnaire/toAttend.action?id="+rowData.id+"&assignId="+rowData.assignId;
}

/*结果汇总  */
function viewResult(id){
	window.location = "<%=request.getContextPath() %>/myQuestionnaire/gotoViewResult.action?id="+id;
}

//查询
function query(){
	$('#examTable').mmGrid().load({"page":1});
}

$(document).ready(function() {     
	$("#beginTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
 		//maxDate: da,
	});
	$("#endTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
		//maxDate: da,
	});
}); 

//重置
function clearAll(){
	$("#beginTime").val("");
	$("#endTime").val("");
	$("#title").val("");
    $('#isPublic').val("");
    $('#isPeriod').val("");
    query();
}

/*头部tab点击事件  */
$(function(){
	var status = $("#status").val();
	$('#ul_exam').find('li[id='+status+']').addClass('li_a');
	$('#ul_exam').find('li[id!='+status+']').removeClass();
	$('#ul_exam').find('li').click(function(){
		$('#ul_exam').find('li').attr('class','');
		$(this).attr('class','li_a');
		$("#status").val($(this).attr("id"));
		query();
	})
});

/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/myQuestionnaire/toList.action";
}

</script>
<style type="">
	.notes_list .ul_exam ul li {
	  width: 100px;
	  height: 26px;
	  line-height: 26px;
	  text-align: center;
	  float: left;
	  margin-right: -1px;
	}

</style>
</head>
<body>
<input type="hidden" name="userId" id="userId"/>
<input type="hidden" name="status" id="status" value="0"/>
<div id="course_all">
	<div class="notes_list" style="padding-bottom: 0px;">
    	<!-- <h3>我的问卷</h3> -->
    	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的问卷</span>
		</div>
         <div class="ul_exam">
        	<ul id="ul_exam">
            	<li class="li_a" id="0">全部</li>
                <li id="2">已做问卷</li>
                <li id="1">未做问卷</li>
            </ul>
        </div>
        <div class="search_1" style="margin-bottom: 0px;">
        	<span>调查名称：<input type="text" id="title" name="title"/></span>
           <span>
           	截止时间：<input type="text" id="beginTime" name="beginTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
           	至 <input type="text" id="endTime" name="endTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
           </span>
           <span>是否在调研期：
               <select id="isPeriod" name="isPeriod" style="width:80px;">
               	<option value="0">全部</option>
                  <option value="1">是</option>
                  <option value="2">否</option>
               </select>
             </span>
             <span>是否可以查看结果：
               <select id="isPublic" name="isPublic" style="width:80px;">
               	<option value="">全部</option>
                  <option value="1">是</option>
                  <option value="0">否</option>
               </select>
             </span>
           
           <input type="button" class="btn_6" onclick="clearAll();" style="float: right;margin-right: 5px;" value="重置" />
           <input type="button" class="btn_5" onclick="query();" style="float: right;margin-right: 5px;" value="查询" />
        </div>
       <!--  <div class="search_1" style="border-top: 0px;">
 			<span>是否在调研期：
               <select id="isPeriod" name="isPeriod" style="width:80px;">
               	<option value=" selected="selected">全部</option>
                  <option value="1">是</option>
                  <option value="0">否</option>
               </select>
             </span>
             <span>是否可以查看结果：
               <select id="isPeriod" name="isPeriod" style="width:80px;">
               	<option value=" selected="selected">全部</option>
                  <option value="1">是</option>
                  <option value="0">否</option>
               </select>
             </span> 
        </div>-->
     </div>
     <div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="examTable"></table>
	  	<div id="paginator11-1" style="text-align:right;"></div>
     </div>
    </div>
	 <%@include file="../common/footer.jsp" %>
</body>
</html>
