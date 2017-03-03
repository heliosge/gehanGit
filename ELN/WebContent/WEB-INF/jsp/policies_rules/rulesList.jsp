<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政策法规</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<style type="text/css">
.ui-dialog-body {
	padding: 0px;
}

.content h3 {
	background: url(../images/img/ico_19.png) no-repeat left -2px;
	padding-left: 36px;
	width: 1010px;
	padding-bottom: 10px;
	font-size: 16px; color : #010101; border-bottom : 1px solid #000000;
	margin-bottom: 20px;
	color: #010101;
	border-bottom: 1px solid #000000;
}
.content .zx p{
	width: 1060px;
    height: 36px;
    border-bottom: 1px dotted #cccccc;
    line-height: 36px;
    text-indent: 1em;
    background: url(../images/img/ico_22.png) no-repeat left 10px;
}
.content .zx p a{
	cursor: pointer;
}
.content .zx em{
    float: right;
    color: #999999;
}

input[name='timeliness']{position: relative;top: 2px;}
</style>

    <style type="text/css">
        .course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
        .course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
        .ztree li span.button.noline_docu{display:none;}
        .ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
        .ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
    </style>
</head>
<body>
<div class="content">
	<!-- <h3>政策法规</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">政策法规查询</span>
	</div>
    <div class="course_tree" id="tree">
        <h2>全部分类</h2>
        <ul id="treePage" class="ztree" style=""></ul>

    </div>
    <div class="right_lesson"  style="">
    <form id="form">
	    <div class="search_2">
            <p>法规名称：
                <input type="text" id="name" name="name"/>

            </p>
	    	<p>&nbsp;&nbsp;&nbsp;&nbsp;全文检索关键字：
	    		<input type="text" id="searchKey"  name="searchKey"/>
	    	</p>
	    	<%--<p>&nbsp;&nbsp;&nbsp;&nbsp;--%>
	    		<%--法规分类：--%>
	    		<%--<input type="text" id="categoryName" onclick="selectCategoryDialog();"/>--%>
	    	<%--</p>--%>
		</div>
		<div class="search_2">
            <p>颁布机构：
                <input type="text" id="publishCompany" name="publishCompany"/>
            </p>
	        <p> &nbsp;&nbsp;&nbsp;&nbsp;文号：
	            <input type="text" id="referenceNumber" name="referenceNumber"/>
	        </p>
		</div>
		<%--<div class="search_2" style="border-top-style: none;">--%>
			<%--&lt;%&ndash;<p>关键字：&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
	            <%--&lt;%&ndash;<input type="text" id="keyWords" name="keyWords"/>&ndash;%&gt;--%>
	        <%--&lt;%&ndash;</p>&ndash;%&gt;--%>

		<%--</div>--%>
		<%--<div class="search_2" style="border-top-style: none;">--%>
			<%----%>
		<%--</div>--%>
	    <div class="search_2" style="border-top-style: none;">
	   		<p>发布时间：
	            <input type="text" id="publishStartTime" name="publishStartTime"/>
	            <span>至</span>
	            <input type="text" id="publishEndTime" name="publishEndTime"/>
	        </p>
		</div>
		<div class="search_2" style="border-top-style: none;">
			<p>施行时间：
	            <input type="text" id="executeStartTime" name="executeStartTime"/>
	            <span>至</span>
	            <input type="text" id="executeEndTime" name="executeEndTime"/>
	        </p>
		</div>
		<div class="search_2" style="border-top-style: none;">
            <p>
            时效性:&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="timeliness" value="1"><span>有效</span>
            <input type="checkbox" name="timeliness" value="2"><span>废止</span>
            </p>
			<div style="margin: 0 auto;margin: 0 auto;width: 150px;">
		     	<input type="button" value="重置" onclick="doReset();"/>
		        <input type="button" value="查询" class="btn_cx" onclick="query();" />
			</div>
		</div>
	</form>
	<div class="clear_both"></div>
	<div id="reLsit" class="zx" style="padding-top: 20px;">
	</div>

    <div id="jquery_page" style="margin-top: 10px; display: none;float: right;" class="pagination"></div>
    </div>
</div>

<%--<div id="categoryDialog" style="display:none;">--%>
	<%--<ul id="treePage" class="ztree"></ul>--%>
<%--</div>--%>

</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<!-- zTree -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<script type="text/javascript">
//每页显示数目
var pageSizes = 10;

var selectCateId;
var selectCateName;
var selectCategoryIds;

$(function(){
	initDates();
	stu_getPoliciesRulesListCount();
	initCategoryTree();
});
function initDates(){
	initDatePicker("publishStartTime");
	initDatePicker("publishEndTime");
	initDatePicker("executeStartTime");
	initDatePicker("executeEndTime");
}
function initDatePicker(idName){
	$("#"+idName).datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
};
function stu_getPoliciesRulesListCount(){
	var param = new Object();
	param.name = $.trim($("#name").val());
	param.searchKey = $.trim($("#searchKey").val());
	param.publishStartTime = $("#publishStartTime").val();
	param.publishEndTime = $("#publishEndTime").val();
	param.referenceNumber = $.trim($("#referenceNumber").val());
	param.publishCompany = $.trim($("#publishCompany").val());
	var arr = [];
	$("input[name='timeliness']:checked").each(function(){
		arr.push($(this).val());
	});
	
	param.timeliness = arr.join(",");
	param.executeStartTime = $.trim($("#executeStartTime").val());
	param.executeEndTime = $.trim($("#executeEndTime").val());
	param.keyWords = $.trim($("#keyWords").val());
	param.category = selectCategoryIds;
	
	var urlStr = "<%=request.getContextPath()%>/policiesRules/stu_getPoliciesRulesListCount.action";
	$.ajax({
		type:"POST",
		url : urlStr,
		data :param,
		success : function(data) {
			var url = "<%=request.getContextPath()%>/policiesRules/stu_getPoliciesRulesList.action";
			insertPage(data,pageSizes,url,param);
		}
	});
}

//插入分页插件
function insertPage(sum,pageSize,urlStr,param){
	$("#jquery_page").show();
	//插入分页插件
	$("#jquery_page").pagination(sum, {
		link_to:"javascript:void(0);",
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			 param.page = page+1;
			 param.pageSize = pageSize;
			 $.ajax({
			  		type: "POST",
			  		url: urlStr,
			   		data:param,
			  		dataType:"json",
			  		success:function(data){
				  		initList(data.rtnDataList);
			  			//initList(data.data);
			  		}
			   	});
		 }  
	});
}

function initList(list){
	var html = '';
	var obj = $("#reLsit");
	if(list){
		$.each(list,function(index,item){
			var id = item.id;
			var name = item.name;
			if (item.timeliness && item.timeliness == 2) {
				name += '<span style="color: red">（已废止）</span>';
			}
			var publishTime = item.publishTime;
			publishTime = getFormatDateByLong(publishTime,'yyyy-MM-dd');
			html +='<p><a javascript:void(0); onclick="toSeeDetails('+id+')">'+name+'</a><em>'+publishTime+'</em></p>';
		});
	}
	obj.html(html);
}
function toSeeDetails(id){
	window.location.href = "<%=request.getContextPath()%>/policiesRules/toDetail.action?id="+id;
}
/**
 * 重置
 */
function doReset(){
	$("#form")[0].reset();
	$("input[name='timeliness']").each(function(){
		$(this)[0].checked = false;
	});
	stu_getPoliciesRulesListCount();
}
/**
 * 查询
 */
function query(){
	stu_getPoliciesRulesListCount();
}

/**
 * 选择分类
 */
function selectCategory(){
	if(selectCateName){
		layer.closeAll();
		// $('#categoryId').val(selectCateId);
		$('#categoryName').val(selectCateName);
	}else{
		layer.alert('请选择有效分类');
	}
}
/**
 * 分类弹出框
 */
function selectCategoryDialog(){
	layer.open({
	    type: 1,
	    btn: ['确定', '取消'],
	    yes:selectCategory,
	    cancel:function(){},
	    title:'选择分类',
	    skin: 'layui-layer-rim', //加上边框
	    area: ['420px', '240px'], //宽高
	    content: $('#categoryDialog')
	    });
}

function initCategoryTree(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback:{onClick:function(event,treeId,treeNode){
				if(treeNode.parentId){// 非根
					selectCateId = treeNode.id;
					selectCateName = treeNode.name;
				} else { // 根节点
					selectCateId = '';
					selectCateName = '所有分类';
				}
				
				var treeObj = $.fn.zTree.getZTreeObj("treePage");
				var nodes = treeObj.transformToArray(treeObj.getSelectedNodes());
				var tmpIds = [];
				$.each(nodes,function(index,item){
					tmpIds.push(item.id);
				});
				selectCategoryIds = tmpIds.join();
				
			}}  
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/policiesRules/getCategorys.action",
		success:function(data){
			var treedata = data.rtnDataList;
			$.fn.zTree.init($("#treePage"), setting, treedata);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
</script>
</html>
