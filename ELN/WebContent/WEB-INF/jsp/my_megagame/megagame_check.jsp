<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>批阅试卷</title>
<style type="text/css">
	.py{ display:block; width:132px; height:28px; line-height:28px; text-align:center;
		font-size:16px; font-weight:bold; background:#0085fe; border-radius:2px;margin-left: 320px;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<!--分页控件  -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css"/>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var keyWord = [];
$(function(){
	$("#userId").val(userId);
	searchData(0,4);
})

function searchData(page, pageSize){
	keyWord = $("form").serialize();
	//alert(keyWord);
	$.ajax({
  		type: "POST",
  		async: false,
  		url: "<%=request.getContextPath()%>/MyMegagame/getMegagameCheckListCount.action",
  		data: keyWord,
  		dataType:"json",
  		success:function(data){
  			//alert("dataSum:"+data);
  			dataSum = data;
  			//插入分页
  			insertPage(dataSum,4);
  		}
  	});
}

//插入分页插件
function insertPage(sum,pageSize){
	$("#jquery_page").show();
	//插入分页插件
	$("#jquery_page").pagination(sum, {
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			 //alert(pageSize);
			 //alert(keyWord);
			 $.ajax({
			  		type: "POST",
			  		async: false,
			  		url: "<%=request.getContextPath()%>/MyMegagame/getMegagameCheckList.action",
			   		data: keyWord + "&page="+(page*pageSize)+"&pageSize="+pageSize,
			  		dataType:"json",
			  		success:function(data){
			  			$("#dataDiv").html("");
			  			if(data != null){
			  				//alert(JSON.stringify(data));
			  				//alert(data.length);
			  				if(data.length == 0){
				  				$("#dataDiv").html("暂无数据！");
				  			}else{
				  				 for(var i=0;i<data.length;i++){
				  					var html ='';
				  					if(i%2 == 0){
				  						html += '<li>';
				  					}else{
				  						html += '<li class="last_1">';
				  					}
				  					html += '<div class="zt" style="z-index:-1">';
				  					var status = data[i].status;
				  					var statusImg = '';
				  					var statusSpan = '';
				  					if(status == 1){
				  						statusImg = '<%=request.getContextPath() %>/images/img/ico_16.png';
				  						statusSpan = '未开始';
				  					}else if(status == 2){
				  						statusImg = '<%=request.getContextPath() %>/images/img/ico_17.png';
				  						statusSpan = '进行中';
				  					}else if(status == 3){
				  						statusImg = '<%=request.getContextPath() %>/images/img/ico_18.png';
				  						statusSpan = '已结束';
				  					}
				  					html += '<img src="'+statusImg+'" /><span>'+statusSpan+'</span></div>';
				  					html += ' <div class="cor_txt" style="height: 190px;">';
				  					html += '<h4>大赛名称：'+data[i].name+'</h4>';
				  					html += '<p>'+subText(data[i].detail,100)+'</p>';
				  					var match = data[i].matchList;
				  					if(match != null && match != ''){
				  						var mindex = match.length-1;
					  					if(status == 1){
					  						mindex = 0;
					  					}else if(status == 2){
					  						for(var j=0;j<match.length;j++){
					  							if(match[j].status == 2){
					  								mindex = j;
					  								break;
					  							}
					  						}
					  					}
				  						html += ' <p class="time_1">';
					  					html += '<span>考试时长：'+match[mindex].examTime+'</span>';
					  					html += ' <span>考试次数：'+match[mindex].examCount+'</span></p>';
					  					html += '<p class="time"><span>考试时间：'+match[mindex].startTime;
					  					html += '~'+match[mindex].endTime+'</span></p>';
					  					html += '<strong>晋级人数：'+match[mindex].promoteCount+'</strong>';
				  					}else{
				  						html += ' <p class="time_1">';
					  					html += '<span>考试时长：'+'</span>';
					  					html += ' <span>考试次数：'+'</span></p>';
					  					html += '<p class="time"><span>考试时间：';
					  					html += '</span></p>';
					  					html += '<strong>晋级人数：'+'</strong>';
				  					}
 				  					//html += '<a href="javascript:void(0);"  onclick="gotoPy('+data[i].examId+');">批阅试卷</a>';
				  					html += '</div>';
				  					html += '<div class="py"><a href="javascript:void(0);" style="color:#fff;" onclick="gotoPy('+data[i].curMatchId+","+status+');">批阅试卷</a></div>';
				  					html += ' </li>';
									$("#dataDiv").append(html);
				  	  			} 
				  			}
			  			}
			  			
			  		}
			   	});
		 }  
	});
}

/*字符截取  */
function subText(title,len){
	if(title != null){
		if(title.length>len){
			title = title.substr(0,len)+"...";
		} 
	}
	return title;
}
$(document).ready(function() {     
	$("#startTime").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
	$("#endTime").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
}); 

function query(){
	searchData(0,4);
}
/*批阅  */
function gotoPy(matchId,status){
	if(status==1){//大赛未开始
		dialog.alert("大赛还未开始");
	}else if(status==2){//大赛进行中
		window.location.href="<%=request.getContextPath()%>/exam/exam/gotoPy.action?matchId="+matchId;
	}else if(status==3){//大赛已结束  
		dialog.alert("大赛已结束");
	}
}

//重置
function clearAll(){
	$("#startTime").val("");
	$("#endTime").val("");
	$("#megagameName").val("");
	$("#status").val("0");
	query();
}
</script>
</head>
<body>
<div id="course_all">
            <div class="notes_list fl">
            	<!-- <h3>专家评分</h3> -->
            	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
					<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
					<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">专家评分</span>
				</div>
            	<div class="search_1">
            		<form id="form">
            		<input type="hidden" name="userId" id="userId"/>
                    <span>大赛名称：<input type="text" id="megagameName" name="megagameName"/></span>
                    <span>
                    	考试时间：<input type="text" id="startTime" name="startTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                    	至 <input type="text" id="endTime" name="endTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                    </span>
                    <span>大赛状态：
                        <select id="status" name="status">
                        	<option value="0" selected="selected">显示全部</option>
                            <option value="1">未开始</option>
                            <option value="2">进行中</option>
                            <option value="3">已结束</option>
                        </select>
                    </span>
                    <input type="button" class="btn_5" onclick="query();" value="查询" />
                    <input type="button" class="btn_6" onclick="clearAll();" value="重置" />
                   </form>
       			</div>
                <div class="correct clear" >
                	<ul id="dataDiv"></ul>
                </div>
        	</div>
        	<!--分页  -->
			<div style="margin-top: 10px;display: none;float: right;" id="jquery_page" class="pagination"> 
			
			</div>
    </div>
	 <%@include file="../common/footer.jsp" %>
</body>
</html>
