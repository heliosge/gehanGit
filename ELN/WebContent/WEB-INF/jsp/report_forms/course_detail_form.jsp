<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.jftt.wifi.util.TimeUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程学习情况统计总表</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<style type="text/css">
.ui-dialog-body{padding:0px;}
</style>
</head>
<body>
<div class="content">
	<!-- <h3>课程学习情况统计总表</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程学习情况统计总表</span>
	</div>
    <form id="form">
		<div class="search_2">
			<p>统计日期：
	            <input type="text" value='<%=TimeUtil.getCurrentTime("yyyy-MM-dd") %>' style="border: none;" readonly="readonly"/>
	        </p>
	       <!-- <p>部门：
	            <input type="text" id="deptName" name="deptName"/>
	        </p> -->
	        <p style="padding-left:10px;">课程名称：
	            <input type="text" id="courseName" name="courseName"/>
	        </p>
	        <!-- <p style="padding-left:10px;">课程分类：
	        	<select id="courseType" name="courseType">
	        		<option value="">全部</option>
	        		<option value="1">线上课程</option>
	        		<option value="2">直播课程</option>
	        	</select>
	        </p> -->
	        <input type="button" value="重置" onclick="doReset();"/>
	        <input type="button" value="查询" class="btn_cx" onclick="query();" />
		</div>
	</form>
	<div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="tableData"></table>
	  	<div id="paginator" style="text-align:right;"></div>
     </div>
     
</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	initDataList();
});


/**
 * 重置
 */
function doReset(){
	$("#form")[0].reset();
	$('#tableData').mmGrid().load({"page":1});
}

/*截取字符  */
function subStr(str,len){
	if(str != null){
		if(str.length > len){
			return str.substr(0,len)+"...";
		}else{
			return str;
		}
	}else{
		return "";
	}
}

/**
 * 查询
 */
function query(){
	$('#tableData').mmGrid().load({"page":1});
}
function initDataList(){
	$('#tableData').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/reportForms/getCourseDetailReport.action',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    autoLoad:false,
	    params:function(){
	    	var param = new Object();
	    	/* param.deptName = $.trim($("#deptName").val()); */
	    	/* param.courseType = $("#courseType").val(); */
	    	param.courseName = $.trim($("#courseName").val());
	    	param.withManager = "no";
	    	
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id',hidden:true},
	           /* {title:'课程号', name:'courseCode', width:80,align:'center'}, */
	           {title:'课程名', name:'courseName', width:150,align:'center',
	        	   renderer: function(val, item, rowIndex){
	        		   return '<a title="'+val+'" href="javascript:void(0);" onclick="toDetail('+item.id+');">'+subStr(val,15)+'</a>';
                   }
	           },
	           {title:'课程体系', name:'courseType', width:80,align:'center'},
	           {title:'学时', name:'courseTime', width:40,align:'center'},
	           {title:'正在学习人数', name:'learningNum', width:80,align:'center'},
	           {title:'完成学习人数', name:'completeNum', width:80,align:'center'},
	           {title:'总学习人数', name:'totalNum', width:80,align:'center'},
	           {title:'总学习时长', name:'totalTime', width:80,align:'center',
	        	   renderer: function(val, item, rowIndex){
	        		   	var h = parseInt(val / 60 / 60 , 10);//计算剩余的小时数  
	        			var m = parseInt(val / 60 % 60, 10);//计算剩余的分钟数  
	        			var s = parseInt(val % 60, 10);//计算剩余的秒数 
	        		   return h + ":" + m + ":" + s;
                   }   
	           },
	           {title:'获得学分', name:'getScore', width:80,align:'center'}
	           /* {title:'学习人数占比', name:'learnPercent', width:80,align:'center'} */
	          ] ,
	   plugins : [
	    	$('#paginator').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'page',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
}

function toDetail(courseId){
	window.location = '<%=request.getContextPath()%>/reportForms/toCourseDetailByCourse.action?courseId='+courseId;
}
</script>
</html>
