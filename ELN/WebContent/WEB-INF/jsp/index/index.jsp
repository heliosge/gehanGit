<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>首页</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js" ></script>
<!-- bootstrap -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />


<style type="text/css">
html, body{
	width:100%;
	height:100%;
	overflow:hidden;
}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
<%-- var param = "<%=request.getAttribute("param")%>"; --%>
$(document).ready(function(){
	
});
var topIframeHeight = 0;
//调整iframe尺寸
$(window).resize(function(){
	iframeResize();
});

function iframeResize(){
	var hei = $("body").height() - 87;
	$("#myIframe").height(hei);
	topIframeHeight = hei;
}

var company = ${company};
$(function(){
	if(company.logoImage != ''){
		$("#nav_div").css("background","url("+company.logoImage+") no-repeat;");
	}
	iframeResize();
	
	//导入菜单数据
	//var pageList = ${pageList};
	var count = 0;
	var moreCount = 0;
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"userId":userId},
		url: "<%=request.getContextPath()%>/index/getPageList.action",
		success:function(data){
			//bootstrap pageList
			var pageList = data;
			if(pageList){
				$.each(pageList, function(index, page){
					if(page.levelType == 0){
						if(count <= 6){
							$("#nav_con").append("<li><a href='#'>"+page.name+"</a> <div class='sec_1'><span class='sec_2' id='"+page.id+"_span'></span></div></li>");
							if(count == 6){
								//更多
								$("#nav_con").append("<li><a href='#'>更多</a><div class='sec_3'><span class='sec_4' id='more_span'></span></div></li>");	
							}
							count ++;
						}else{
							if(moreCount% 6 ==0){
								$(".sec_3").height(210*(moreCount/6+1));
							}
							$("#more_span").append("<span class='sec_5' id='"+page.id+"_span'><h4>"+page.name+"</h4></span>");
							moreCount++;
						}
					}else if(page.levelType == 1){
						//二级菜单
						$("#"+page.parentId+"_span").append("<a href='<%=request.getContextPath()%>/"+page.url+"' target='mainIframe'>"+page.name+"</a>");
							
					}
					
				});
				<%-- if(param == "examlist"){
					$("#myIframe").attr("src","<%=request.getContextPath()%>/myExamManage/gotoMyExam.action");
				}else if(param == "simulatelist"){
					$("#myIframe").attr("src","<%=request.getContextPath()%>/myExamManage/gotoExamSimulate.action");
				}else{ --%>
					$("#myIframe").attr("src","<%=request.getContextPath()%>/oam/toBarIndex.action");
				/* } */
			}
			
			
			$('#nav_con').find('li').hover(function(){
				$('#nav_con li').attr('class','');
				$(this).attr('class','li_hover');
				$('#nav_con li').find('div').eq($(this).index()).css('display','block');
			},
								
			function(){
				$('#nav_con li').attr('class','');
				$('#nav_con li').find('div').css('display','none');			
									
			})
			$('#nav_con li').find('div').hover(function(){
				$(this).css('display','block');
				
				},
			
			function(){
				$(this).css('display','none');
				})
			
			//退出
			$("#loginOut").click(function(){
				
				$.ligerDialog.confirm('确认退出吗？', function(yes){ 
					if(yes){
						window.location.href = "<%=request.getContextPath()%>/login/loginout.action";
				    }
				});
			});
			
	    }
	});
})

function toIndex(){
	$("#myIframe").attr("src","<%=request.getContextPath()%>/oam/toBarIndex.action");
}
</script>
</head>

<body >

<div id="header">
	<div class="head">
		<p>你好！欢迎来到普联在线，<a href="<%=request.getContextPath()%>/login/loginout.action" >[退出]</a>
		或<a href="#" onclick="toIndex()">[返回首页]</a></p>	
	</div>
</div>
<div id="nav_s">
	<div class="nav_con" id="nav_div">
    	<h3>普联中瑞</h3>
        <div class="nav_ul">
        	<ul id="nav_con">
            </ul>
        </div>
    </div>
</div>
<div>
	<iframe id="myIframe" name="mainIframe" frameborder="0" style="width:100%;height:100%;" src="" ></iframe>
</div>
	
</body>

</html>
