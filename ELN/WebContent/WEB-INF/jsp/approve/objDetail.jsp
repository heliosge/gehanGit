<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>共享</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<script type="text/javascript">
/**
 * iframe高度自适应
 */
 function iFrameHeight() {
		var ifm = document.getElementById("xq");
		var subWeb = document.frames ? document.frames["xq"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}

//var listTeacherBean = ${listTeacherBean}; //部门
	var wayType = ${type};
	var id=${id};
$(function(){
	if(wayType==1){
		$("#xq").attr("src","<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=${id}");
	}
	else if(wayType==2){
		//$("#xq").attr("src","<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId=${id}");
		$("#xq").attr("src","<%=request.getContextPath()%>/knowledge/toKnowledgeLookUp.action?knowledgeId=${id}");
		
	}else if(wayType==3){
		$("#xq").attr("src","<%=request.getContextPath()%>/oam/toOamTopicDetailPage.action?id=${id}");
		//专门
	}
});

</script>
</head>
<body style="overflow-x:hidden;">
 <div>	
		<!-- <h3>查看详情</h3> -->
		<%-- <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看详情</span>
		 </div> --%>
	    <!-- <div class="lesson_tab_1">
		</div> -->
    	<div class="content_2" style="display: block;">
	         <div id="knowledge" class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
					<iframe id="xq" width="100%" rameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" name="xq" onload="iFrameHeight();"></iframe>	
					 
				</div>   
<!-- 			<div id="knowledge" class="first_div" style="display:none;border-right:none;border-bottom:none;border-left:none;margin:0px">
						
					<div style="margin:3px 10px 10px 10px;"></div>
					
					<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
					<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
				</div> -->
	   </div>
   </div>
</body>
</html>
