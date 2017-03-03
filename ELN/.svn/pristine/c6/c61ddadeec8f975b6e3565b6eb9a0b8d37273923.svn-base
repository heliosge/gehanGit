<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学员总体概况统计表</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />

<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
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
	<!-- <h3>学员总体概况统计表</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学员总体概况统计表</span>
	</div>
    <form id="form">
		<div class="search_2">
	    	<p>姓名：
	            <input type="text" id="name" name="name"/>
	        </p>
	       <p>部门：
	            <input type="text" id="deptName" name="deptName" onclick="chooseDept();" />
    			<input type="hidden" id="deptId" name="deptId" />
	        </p>
	        <p>岗位：
	            <input type="text" id="postName" name="postName" onclick="choosePost();" />
    			<input type="hidden" id="postId" name="postId" />
	        </p>
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

 <div id="deptDialog" style="display:none;height:350px;overflow:auto;">
	<ul id="categoryTree" class="ztree" ></ul>   
 </div>
 
 <div id="postDialog" style="display:none;height:350px;overflow:auto;">
	<ul id="postTree" class="ztree" ></ul>   
 </div>
 
</body>

<script type="text/javascript">
$(function(){
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
/**
 * 查询
 */
function query(){
	$('#tableData').mmGrid().load({"page":1});
}
function initDataList(){
	$('#tableData').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/reportForms/getStuTotalityInfoForm.action',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    autoLoad:false,
	    params:function(){
	    	var param = new Object();
	    	param.name = $.trim($("#name").val());
	    	param.deptId = $.trim($("#deptId").val());
	    	param.postId = $.trim($("#postId").val());
	    	
	    	//不包含初始管理员
	    	param.withManager = "no";
	    	
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'userId',hidden:true},
	           {title:'姓名', name:'name', width:160,align:'center'},
	           {title:'部门', name:'deptName', width:80,align:'center'},
	           {title:'岗位', name:'postName', width:80,align:'center'},
	           {title:'获得积分', name:'getIntegral', width:80,align:'center'},
	           /* {title:'扣除积分', name:'deductIntegral', width:80,align:'center'}, */
	           {title:'获得学分', name:'getCredit', width:80,align:'center'},
	           {title:'扣除学分', name:'deductCredit', width:80,align:'center'},
	           {title:'总学时', name:'totalStuTime', width:80,align:'center'},
	           {title:'自学完成课程数', name:'finishCourseBySelf', width:80,align:'center'},
	           {title:'完成指派学习课程数', name:'finishCourseByAppoint', width:80,align:'center'},
	           {title:'参加考试数', name:'attendExamCounts', width:80,align:'center'},
	           {title:'考试通过率', name:'passExamRate', width:80,align:'center',
	        	   renderer:function(val,item, rowIndex){
	        		   if(!val || val == 0){
	        			   return 0;
	        		   }else{
		        		   return Number(val*100).toFixed(2) + "%";
	        		   }
              		}},
	          /*  {title:'分享知识数', name:'shareKnowledgeCount', width:80,align:'center'}, */
	           {title:'登录系统次数', name:'loginCounts', width:80,align:'center'},
	           /* {title:'提问总数', name:'askCounts', width:80,align:'center'},
	           {title:'回答总数', name:'answerCounts', width:80,align:'center'},
	           {title:'评论总数', name:'evaluationCounts', width:80,align:'center'}, */
	           {title:'首次登录时间', name:'firstLoginTime', width:120,align:'center'},
	           {title:'最近登录时间', name:'lastLoginTime', width:120,align:'center'}] ,
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
