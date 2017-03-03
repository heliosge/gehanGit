<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>安培在线</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- bootstrap -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style_.css" />


<style type="text/css">
html, body{
	width:100%;
	height:100%;
	overflow:hidden;
}
.tools_capacity{
	color: #fff;
	float:left;
	margin-top:16px;
	background:url(<%=request.getContextPath()%>/images/img/bg_23.png) repeat-x;
	line-height:20px;
	border-radius:8px;
	padding: 0px 5px;
    font-weight: bold;
}
</style>

<script type="text/javascript">
window.onbeforeunload = onbeforeunload_handler;
function onbeforeunload_handler(){ 
	$.ajax({
		type: "GET",
		async: true,  //默认true,异步
		url: "<%=request.getContextPath()%>/login/loginoutTime.action",
		success:function(data){
	    }
	});
}

var userId = '${USER_ID_LONG}';//登录用户ID
var userSex = '${USER_BEAN.sex}';
var userHeadPhoto = '${USER_BEAN.headPhoto}';
var param = "<%=request.getAttribute("param")%>";
$(document).ready(function(){
	
});
//调整iframe尺寸
$(window).resize(function(){
	iframeResize();
});

function iframeResize(){
	var hei = $("body").height() - 144;
	$("#myIframe").height(hei);
}

var company = ${company};
$(function(){
	if(company.logoImage != ''){
		$("#nav_div").css("background","url("+company.logoImage+") no-repeat;");
	}
	if(userHeadPhoto == '' || userHeadPhoto == 'null'){
		if(userSex == 1){
			$("#headPhoto").attr("src","<%=request.getContextPath()%>/images/img/avatar_male.png");
		}else{
			$("#headPhoto").attr("src","<%=request.getContextPath()%>/images/img/avatar_female.png");
		}
	}
	iframeResize();
	
	//导入菜单数据
	var count = 0;
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"userId":userId},
		url: "<%=request.getContextPath()%>/index/getPageList.action",
		success:function(data){
			var pageList = data;
			if(pageList){
				$.each(pageList, function(index, page){
					if(page.levelType == 0){
						if(count <= 6){
							if(count < 5){
								$("#nav_c").append("<li><a href='#'>"+page.name+"</a> <div class='option_3'><p style='width:106px;height:8px;position: absolute;top: -8px;'>&nbsp;<img src='<%=request.getContextPath()%>/images/img/bg_26.png' /></p><span class='option_4' id='"+page.id+"_span'></span></div></li>");
							}else if(count == 5){
								$("#nav_c").append("<li class='last_2'><a href='#'>"+page.name+"</a> <div class='option_3'><p style='width:106px;height:8px;position: absolute;top: -8px;'>&nbsp;<img src='<%=request.getContextPath()%>/images/img/bg_26.png' /></p><span class='option_4' id='"+page.id+"_span'></span></div></li>");
							}else if(count == 6){
								$("#nav_c").append(" <li style='float:right;'><a href='#'><img src='<%=request.getContextPath()%>/images/img/bg_25.png' class='img_3' />更多</a><div class='option_more' id='option_more'><p style='width:1046px;position: absolute;top: -8px;height:8px;'>&nbsp;<img src='<%=request.getContextPath()%>/images/img/bg_26.png' class='img_5' /></p></div></li>");	
								$("#option_more").append("<span class='option_5' id='"+page.id+"_span' style='line-height:30px;'><h4>"+page.name+"</h4></span>");
							}
						}else{
							$("#option_more").append("<span class='option_5' id='"+page.id+"_span' style='line-height:30px;'><h4>"+page.name+"</h4></span>");
						}
						count ++;
					}else if(page.levelType == 1){
						//二级菜单
						$("#"+page.parentId+"_span").append("<a href='javascript:void(0);' onclick='toMyIframe(\""+page.url+"\",this)' >"+page.name+"</a>");
					}
					
				});
			 	if(param == "examlist"){
					$("#myIframe").attr("src","<%=request.getContextPath()%>/myExamManage/gotoMyExam.action");
				}else if(param == "simulatelist"){
					$("#myIframe").attr("src","<%=request.getContextPath()%>/myExamManage/gotoExamSimulate.action");
				}else{
					$("#myIframe").attr("src","<%=request.getContextPath()%>/index/home.action");
					//$("#myIframe").attr("src","<%=request.getContextPath()%>/studentInfoAction/toStudentIndex.action");
				} 
			}
			
			//菜单效果
			var nt = !1;
			$(window).bind("scroll",
				function() {
				var st = $(document).scrollTop();//往下滚的高度
				nt = nt ? nt: $(".nav_n").offset().top;
				var sel=$(".nav_n");
				if (nt < st) {
					sel.addClass("nav_fixed");
				} else {
					sel.removeClass("nav_fixed");
				}
			});


			$('#nav_c').find('li').hover(
					function(){
						$('#nav_c li').attr('class','');
						$(this).attr('class','li_h');
						$('#nav_c li').find('div').eq($(this).index()).css('display','block');
						},
					function(){
						$('#nav_c li').attr('class','');
						$('#nav_c li').find('div').css('display','none');			
						}
						);

			$('#nav_c li').find('div').hover(
					function(){
						$(this).css('display','block');
					},
					function(){
						$(this).css('display','none');
					});
			
			//退出
			$("#loginOut").click(function(){
				
				dialog.confirm('确认退出吗？', function(yes){ 
					if(yes){
						window.location.href = "<%=request.getContextPath()%>/login/loginout.action";
				    }
				});
			});
			
	    }
	});
})

function toMyIframe(url,obj){
	$($(obj)[0]).parent().parent().css('display',"none");
	$("#myIframe").attr("src","<%=request.getContextPath()%>/"+url);
}

function toIndex(){
	$("#myIframe").attr("src","<%=request.getContextPath()%>/oam/toBarIndex.action");
}

function toPersonalInfo(){
	$("#myIframe").attr("src","<%=request.getContextPath()%>/manageUser/toPersonnalBaseInfo.action");
}

function toNotice(){
	$("#myIframe").attr("src","<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action");
}

function toHome(){
	$("#myIframe").attr("src","<%=request.getContextPath()%>/index/home.action");
}

$(function(){
	if(company.logoImage != ''){
		$("#logo").attr("src",company.logoImage);
	}
})

document.onkeydown = function(event)
{
	if ( event.keyCode == 116)
	{
		$("#myIframe").attr("src",$("#myIframe").attr("src"));
		if(event && event.preventDetault){
			event.preventDetault();
		}else{
			event.keyCode = 0;
			event.cancelBubble = true;
		}
		return false;
	}
}

</script>
</head>

<body>
<div class="head_n">
	<div class="tool_n">
		<div style="max-width:300px;min-width:212px;height:56px;width:auto;float:left">
    	<a href="<%=request.getContextPath()%>/index/home.action" target="mainIframe">
    		<img id="logo" src="<%=request.getContextPath()%>/images/img/logo_2_new.png" class="img_1"  width="100%" height="56px" min-width="212px"/>
    	</a>
    	</div>
        <div class="right_tool fr" style="width:auto;">
        	<a href="#" class="tools_1" title="个人中心" onclick="toPersonalInfo()"><img id="headPhoto" src="${USER_BEAN.headPhoto }" style="width:36px;height:36px;"/></a>
            <a href="#" class="tools_4" title="个人中心" onclick="toPersonalInfo()" style="width:auto;">${USER_BEAN.name }</a>
            <a href="#" class="tools_2" title="站内信" onclick="toNotice()"></a>
         	<span class="tools_capacity">${company.usedCapacity }GB/${company.totalCapacity }GB</span>
            <a href="<%=request.getContextPath()%>/login/loginPage.action" class="tools_3" title="退出"></a>
        </div>
    </div>
    <div class="nav_n">
    	<div class="nav_c">
        	<ul id="nav_c">
        		<li onclick="toHome()">
       				<a >首页</a> 
        		<div>
        		</div>
        		</li>
        	</ul>
        </div>
    </div>
</div>
<div>
		<iframe id="myIframe" name="mainIframe" style="width:100%;height:100%;border:0px;" src="" ></iframe>
</div>
	
</body>

</html>
