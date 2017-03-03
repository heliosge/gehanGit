<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.jftt.wifi.util.TimeUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程学习情况明细表（按课程）</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<!-- jQuery UI -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>

<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">

.ui-dialog-body{padding:0px;}

.ztree li span.button.noline_docu{display:none;}
.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
</style>
</head>
<body>
<div class="content">
	<!-- <h3>课程学习情况统计总表</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程学习情况明细表（按课程）</span>
	</div>
    <form id="form">
    	<div class="search_2">
    		<p>用户名：<input type="text" id="userName" name="userName"/></p>
    		<p style="padding-left:10px;">姓名：<input type="text" id="name" name="name"/></p>
    		<p style="padding-left:10px;">部门：
    			<input type="text" id="deptName" name="deptName" onclick="chooseDept();" />
    			<input type="hidden" id="deptId" name="deptId" />
    		</p>
    		<p style="padding-left:10px;">岗位：
    			<input type="text" id="postName" name="postName" onclick="choosePost();" />
    			<input type="hidden" id="postId" name="postId" />
    		</p>
    		<p style="padding-left:10px;">学习进度：
    			<select id="learn_process" name="learn_process" >
    				<option value="all" >全部</option>
    				<option value="1" >进行中</option>
    				<option value="2" >已完成</option>
    			</select>
    		</p>
    		<input type="button" value="重置" onclick="doReset();"/>
	        <input type="button" value="查询" class="btn_cx" onclick="query();" />
    	</div>
		<div class="search_2" style="border-top-style: none;">
			<p>
				<span style="font-weight: bold;">课程名称：</span>
	            <!-- <input type="text" id="courseName" name="courseName" onfocus="auto_complete();"/> -->
	            <!-- <input type="text" id="courseName" name="courseName" style="border: none;" readonly="readonly"/> -->
	            <span id="courseName" name="courseName"></span>
	        </p>
	       <!-- <p style="padding-left:10px;">
	       		<span style="font-weight: bold;">课程编号：</span>
	            <input type="text" id="courseCode" name="courseCode" style="border: none;" readonly="readonly"/>
	        	<span id="courseCode" name="courseCode"></span>
	        </p> -->
	        <p style="padding-left:10px;">
	        	<span style="font-weight: bold;">课程分类：</span>
	        	<!-- <input type="text" id="courseType" name="courseType" style="border: none;" readonly="readonly"/> -->
	        	<span id="courseType" name="courseType"></span>
	        </p>
			<p style="padding-left:10px;">
				<span style="font-weight: bold;">学时：</span>
	            <!-- <input type="text" id="courseTime" name="courseTime" style="border: none;" readonly="readonly"/> -->
	        	<span id="courseTime" name="courseTime"></span>
	        </p>
	        <p style="padding-left:10px;">
	        	<span style="font-weight: bold;">学分：</span>
	            <!-- <input type="text" id="getScore" name="getScore" style="border: none;" readonly="readonly"/> -->
	        	<span id="getScore" name="getScore"></span>
	        </p>
	        <p style="padding-left:10px;">
	        	<span style="font-weight: bold;">统计日期：</span>
	            <input type="text" value='<%=TimeUtil.getCurrentTime("yyyy-MM-dd") %>' style="border: none;" readonly="readonly"/>
	        </p>
	        <!-- <input type="button" value="重置" onclick="doReset();"/>
	        <input type="button" value="查询" class="btn_cx" onclick="query();" /> -->
		</div>
	</form>
	<div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="tableData"></table>
	  	<div id="paginator" style="text-align:right;"></div>
     </div>
     
</div>

 <div id="deptDialog" style="display:none;height:350px;">
	<ul id="categoryTree" class="ztree" ></ul>   
 </div>
 
 <div id="postDialog" style="display:none;height:350px;">
	<ul id="postTree" class="ztree" ></ul>   
 </div>

</body>
<script type="text/javascript">
var courseId = ${courseId};
$(function(){
	getCourseInfo();
	initDataList();
	
	initDeptTree();
	initPostTree();
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

/*搜索框的自动完成  */
function getCourseInfo(){
	$.ajax({
		async: false,
		type: "post",
		url: "<%=request.getContextPath()%>/reportForms/getCourseInfo.action",
		data:{"courseId":courseId},
		success: function(data){//自动设置用户名、密码
			//alert(JSON.stringify(data));
			$("#courseName").text(subStr(data.name,20));
			$("#courseName").attr("title",data.name);
			$("#courseCode").text(data.code);
			var type = "";
			if(data.type == 1){
				type = "线上课程";
			}else{
				type = "直播课程";
			}
			$("#courseType").text(type);
			$("#courseTime").text(data.learnTime);
			$("#getScore").text(data.learnScore);
		}
	});
}

/*搜索框的自动完成  */
function auto_complete(){
	$.ajax({
		async: false,
		type: "post",
		url: "<%=request.getContextPath()%>/reportForms/getSearchValue.action",
		dataType: "json",
		success: function(data){//自动设置用户名、密码
			//alert(JSON.stringify(data));
			var allLP = data;
			$.each(allLP, function(index, lp){
				//组织符合要求的数据
				lp.value = lp.name;
				
				allLP[index] = lp;
			});
			
			//自动提示
			$("#courseName").autocomplete({
				source:allLP
			});
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			
		}
	});
	
}

/**
 * 查询
 */
function query(){
	getCourseInfo();
	$('#tableData').mmGrid().load({"page":1});
}
function initDataList(){
	$('#tableData').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/reportForms/getCourseDetailByCourse.action',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.courseId = courseId;
	    	param.userName = $.trim($("#userName").val());
	    	param.name = $.trim($("#name").val());
	    	//param.deptName = $.trim($("#deptId").val());
	    	param.deptId = $.trim($("#deptId").val());
	    	//param.postName = $.trim($("#postName").val());
	    	param.postId = $.trim($("#postId").val());
	    	
	    	if($("#learn_process").val() != "all"){
	    		param.learn_process = $("#learn_process").val();
	    	}
	    	
	    	//不包含初始管理员
	    	param.withManager = "no";
	    	
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id',hidden:true},
	           /* {title:'课程号', name:'courseCode', width:80,align:'center'},
	           {title:'课程名', name:'courseName', width:150,align:'center',
	        	   renderer: function(val, item, rowIndex){
	        		   return '<div title="'+val+'">'+subStr(val,15)+'</div>';
                   }
	           },
	           {title:'课程分类', name:'courseType', width:80,align:'center'}, */
	           {title:'部门', name:'department', width:40,align:'center'},
	           {title:'用户名', name:'userName', width:40,align:'center'},
	           {title:'姓名', name:'name', width:40,align:'center'},
	           {title:'岗位', name:'post', width:40,align:'center'},
	           {title:'开始学习时间', name:'startTime', width:80,align:'center'},
	           {title:'最近学习时间', name:'thisLearnTime', width:80,align:'center'},
	           {title:'学习进度', name:'learnProcess', width:50,align:'center',
	        	   renderer: function(val, item, rowIndex){
	        		  if(val == 1){
	        			  return "进行中";
	        		  }else if(val == 2){
	        			  return "已完成";
	        		  }else{
	        			  return "-";
	        		  }
                   }   
	           },
	           {title:'学习时长', name:'durationTime', width:50,align:'center',
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

/* 初始化部门tree */
function initDeptTree(){
	var setting = {
			data: {key: {url: "xUrl"}, simpleData: {enable: true, pIdKey: "parentId", idKey: "id"}},
			check: {enable:  true,
				chkboxType: { "Y": "s", "N": "s" }
			},
			view: {
				showLine: false,
				showIcon: true
				//selectedMulti: false
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			var zTree = $.fn.zTree.init($("#categoryTree"), setting, data.data);
			
			//全部展开
            zTree.expandAll(true);
		}
	});
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function chooseDept(){
	dialog({
		width : 270,
		height : 350,
		title : '请选择部门',
		content : $("#deptDialog"),
	 	button: [
          {
          value: '确定',
          callback: function () {
        	  //$("#dept").html('');
        	  //deptIds = [];
        	  
        	  var ztreeObj = $.fn.zTree.getZTreeObj("categoryTree");
           	  var nodes = ztreeObj.getCheckedNodes(true);
           	  
           	  var names = [];
           	  var ids = [];
           	  $.each(nodes, function(index, val){
           			names.push(val.name);
           			
           			if(val.id.indexOf("com") != -1){
           				//alert(val.id + "   " + val.id.substring(4, val.id.length));
           				ids.push(val.id.substring(4, val.id.length));
           			}else{
           				ids.push(val.id);
           			}
           			
           	  });
           	  
           	  $("#deptName").val(names.join(","));
           	  $("#deptId").val(ids.join(","));
        	  
        	  this.close();
          }
          }
		]
	}).showModal();
}

/* 初始化岗位tree */
function initPostTree(){
	var setting = {
			data: {key: {url: "xUrl"}, simpleData: {enable: true, pIdKey: "parentId", idKey: "id"}},
			check: {enable:  true,
				chkboxType: { "Y": "s", "N": "s" }
			},
			view: {
				showLine: false,
				showIcon: true
				//selectedMulti: false
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageParam/selectManagePost.action",
		success:function(data){
			
			//alert(JSON.stringify(data));
			
			addIconInfo(data);
			var zTree = $.fn.zTree.init($("#postTree"), setting, data);
			
			//全部展开
            zTree.expandAll(true);
		}
	});
}

function choosePost(){
	dialog({
		width : 270,
		height : 350,
		title : '请选择岗位',
		content : $("#postDialog"),
	 	button: [
          {
          value: '确定',
          callback: function () {
        	  //$("#dept").html('');
        	  //deptIds = [];
        	  
        	  var ztreeObj = $.fn.zTree.getZTreeObj("postTree");
           	  var nodes = ztreeObj.getCheckedNodes(true);
           	  
           	  var names = [];
           	  var ids = [];
           	  $.each(nodes, function(index, val){
           		  
           			names.push(val.name);           			
           			ids.push(val.id);	
           	  });
           	  
           	  $("#postName").val(names.join(","));
           	  $("#postId").val(ids.join(","));
        	  
        	  this.close();
          }
          }
		]
	}).showModal();
}

</script>
</html>
