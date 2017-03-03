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

//var listTeacherBean = ${listTeacherBean}; //部门
	var wayType = ${type};
	var id=${id};
$(function(){
	if(wayType==1){
		$("li.li_this").html("课程信息");
		$("#xq").attr("src","<%=request.getContextPath()%>/res/toCourseDetail.action?courseId=${id}");
		$("#xq").load(function() { 
			var frameWindow = $(this).contents();
			frameWindow.find("div.footer").hide();
			frameWindow.find("div.content").css("width","100%");
			$("#xq").css("height",frameWindow.find("html").css("height"));
			frameWindow.find("html").css("overflow","hidden");
		}); 
	}
	else if(wayType==2){
		$("li.li_this").html("知识信息");
		$("#xq").attr("src","<%=request.getContextPath()%>/knowledge/toKnowledgeLookUp.action?knowledgeId=${id}");
		 $("#xq").load(function() { 
			var frameWindow = $(this).contents();
			frameWindow.find("div.content").css("width","100%");
			$("#xq").css("height",frameWindow.find("html").css("height"));
			frameWindow.find("html").css("overflow","hidden");
		 })
		
	}else if(wayType==3){
		$("li.li_this").html("专题信息");
		$("#xq").attr("src","<%=request.getContextPath()%>/oam/toOamTopicDetailPage.action?id=${id}");
		$("#xq").load(function() {
			var frameWindow = $(this).contents();
			frameWindow.find("h3").hide();
			frameWindow.find("div.button_gr").hide();
			frameWindow.find("div.content").css("width","100%");
			$("#xq").css("height",frameWindow.find("html").css("height"));
			frameWindow.find("html").css("overflow","hidden");
		}); 
	}
	$("#xq").load(function() {
		var frameWindow = $(this).contents();
		frameWindow.find(".head_de").hide();
	});
});

</script>
</head>
<body style="overflow-x:hidden;">
 <div  class='content'>	
		<!-- <h3>查看详情</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看详情</span>
		 </div>
	    <div class="lesson_tab_1">
	        	<ul>
	            	<li class="li_this">知识信息</li>
	                <li class="">共享记录</li>
	            </ul>
		</div>
    	<div class="content_2" style="display: block;">
	         <div id="knowledge" class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
					<iframe id="xq" src="" style="width: 100%;height: 850px;border: 0px;"></iframe>	
					 
				</div>   
			<div id="knowledge" class="first_div" style="display:none;border-right:none;border-bottom:none;border-left:none;margin:0px">
						
					<div style="margin:3px 10px 10px 10px;"></div>
					
					<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
					<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
				</div>
	   </div>
   </div>
	<script type="text/javascript">
	
	//绑定click事件
	$("div.lesson_tab_1").find("ul li").on("click",function(){
		var index = $(this).index();
		//点击切换时，需要进行以下事情
		//1、进行标签的切换
		$(this).siblings().removeClass("li_this");
		$(this).addClass("li_this");
		
		
		//2、页面切换
		$("div.search_3").hide().eq(index).show();
		$("div.first_div").hide().eq(index).show();
		if(index==1){
			//表格
			$('#exampleTable').mmGrid({
		    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
				url:"<%=request.getContextPath()%>/approve/getShareRecordList.action",
		    	width: 'auto',
		    	height: 'auto',
		    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		    	showBackboard: false,
		 		checkCol: false,
		        multiSelect: false,
		        indexCol: false,  //索引列
		        params:function(){
		        	var param = new Object();
		        	param.wayType=wayType;
		        	param.objectId=id;
		        	return param;
		        },
		        cols: [{title: 'ID', align:'center',name: 'id', width:150, hidden:true},
					   {title: '共享时间', align:'center',name: 'shareTime', width:300,renderer:function(val, item, rowIndex){
						   var newDate = new Date(val);
						   return formatDate(newDate,"yyyy-MM-dd hh:mm:ss");
					   }},
					   {title: '行业分类', align:'center',name: 'industryName', width:300},
					   {title: '所属公司', align:'center',name: 'companyName', width:428,renderer:function(val,item,rowIdex){
						   
						  return  _getUserCompanyName(item.companyId,item.subCompanyId);
					   } },
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
		}
	})
	
	  //格式化日期,
    function formatDate(date,format){
      var paddNum = function(num){
        num += "";
        return num.replace(/^(\d)$/,"0$1");
      }
      //指定格式字符
      var cfg = {
         yyyy : date.getFullYear() //年 : 4位
        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
        ,d  : date.getDate()   //日 : 如果1位的时候不补0
        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
        ,hh : paddNum(date.getHours())  //时
        ,mm : paddNum(date.getMinutes()) //分
        ,ss : paddNum(date.getSeconds()) //秒
      }
      format || (format = "yyyy-MM-dd hh:mm:ss");
      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    } 
	//同步获取用户的所属公司名称
	function _getUserCompanyName(companyId,subCompanyId){
		
		var companyName ="";
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/approve/getUserCompanyName.action",
			async:false,
			data:{subCompanyId:subCompanyId,companyId:companyId},
			success:function(data){
				companyName=data;
			}
		})
		
		return companyName;
	}
	</script>
</body>
</html>
