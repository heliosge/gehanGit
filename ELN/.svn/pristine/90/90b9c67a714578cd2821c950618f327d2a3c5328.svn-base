<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<style type="text/css">
	.course_tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>

$(function(){
	var companyId = ${USER_BEAN.companyId};
	var subCompanyId = ${USER_BEAN.subCompanyId};
	if(companyId == subCompanyId){
		$("#iShare").remove();
	}
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/selectShareResCourseList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: false,
        indexCol: true,  //索引列
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
              /*  {title: '课程编号', name: 'code', width:60, align:'center'}, */
               {title: '课程名称', name: 'name', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+item.id+'" >'+val+'</a>';
			   }},
 			   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "线下课程";
				   }
				}},
 			   {title: '课程体系', name: 'categoryName', width:60, align:'center'},
 			  {title: '状态', name: 'shareStatus', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 3 || val == 6){
					   return "<span title='"+item.checkReason+"'>审批拒绝</span>";
				   }else if(val == 2 || val == 5){
					   return "<span>待审批</span>";
				   }else if(val == 4 || val == 7){
					   return "<span title='"+item.checkReason+"'>审批通过</span>";
				   }
				}},
			   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
				   var buttonStr = '';
				   if(flag){
					   buttonStr = '<a href="#" style="color: #999;">修改</a>';
				   }else{
					   buttonStr = '<a href="toUpdateResCoursePage.action?id='+item.id+'" >修改</a>';
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
})

function param(){
	var o = {};
	o.name = $("#name").val();
	o.code = $("#code").val();
	o.type = $("#type").val();
	o.queryType =$("#queryType").val();
	o.shareStatus = '2,3,4,5,6,7';
	return o;
}
	


function search(){
	$('#exampleTable').mmGrid().load();
}
var flag = true;
function iShare(){
	$("#iShare").attr("style","");
	$("#iShare").attr("class","btn_4");
	$("#shareToMe").attr("style","background: #999;");
	flag = false;
	$("#queryType").val(1);
	var o = param();
	$('#exampleTable').mmGrid().load(o);
}

function shareToMe(){
	$("#iShare").attr("style","background: #999;");
	$("#shareToMe").attr("style","");
	$("#shareToMe").attr("class","btn_4");
	flag = true;
	$("#queryType").val(2);
	var o = param();
	$('#exampleTable').mmGrid().load(o);
}


</script>

</head>
<body>

<div class="content">
	<!-- <h3>课程管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程管理</span>
	 </div>
   	<div class="course_tree">
   		<h2>课程体系</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson">
    	<div class="lesson_tab">
        	<ul>
            	<li><a href="<%=request.getContextPath()%>/res/toResCourseListPage.action" style="color:black;">企业课程</a></li>
                 <li><a href="<%=request.getContextPath()%>/res/toBuyResCourseListPage.action" style="color:black;">购买的课程</a></li>
                <%-- <li><a href="<%=request.getContextPath()%>/res/toResCloundCourseListPage.action" style="color:black;">云平台课程</a></li> --%>
                <li class="li_this">共享记录</li>
            </ul>
        </div>
        <div class="button_gr">
        	<input type="button" value="我共享的" onclick="iShare()" id="iShare" style="background: #999;"/>
            <input type="button" value="共享给我的" class="btn_4"  onclick="shareToMe()" id="shareToMe"/>
        </div>
        <div class="search_3">
        	<input id="queryType" type="hidden" value="2"/>
        	<p>	
            	课程编码：
                <input type="text" id="code"/>
            	 课程名称：
                <input type="text" id="name"/>
            	课程类型：
                <select id="type">
                    <option value="">全部</option>
                    <option value="1">线上课程</option>
                    <option value="2">线下课程</option>
                </select>
               
        	</p>
        	<input type="button" value="查询" class="btn_cx" onclick="search()"/>

        </div>
        <div style="width: 780px;float: left;">
				<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
				<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     	</div> 
        
    </div>
        
     
</div>
<script>
</script>

</body>
</html>
