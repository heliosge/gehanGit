<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<title>专题列表</title>
</head>
<body>
<div class="special">
	<!-- <h3>专题列表</h3> -->
	 <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">专题列表</span>
	</div>
    <div class="sp_list" style="padding:0;">
        <div class="all_list">
            <ul id="lists"></ul>
        </div>
        
        <!--分页  -->
		<div id="jquery_page" style="margin-top: 10px; display: none;float: right; " class="pagination"></div>
	</div>
</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<script type="text/javascript">
var pageSize = 5;
$(function(){
	getMallTopicListCount();
});

function getMallTopicListCount(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getMallTopicListCount.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		success : function(data) {
 			insertPage(data);
 		}
 	});
}
//插入分页插件
function insertPage(sum){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getMallTopicList.action";
	if(sum>pageSize){
		$("#jquery_page").show();
	}
	//插入分页插件
	$("#jquery_page").pagination(sum, {
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			var params = new Object;
			params.page = page+1;
			params.pageSize = pageSize;
			 $.ajax({
			  		type: "POST",
			  		url: urlStr,
			   		data:  params,
			  		dataType:"json",
			  		success:function(data){
			  			var datas = data.rtnDataList;
			  			initTopciList(datas);
			  		}
			   	});
		 }  
	});
}
/**
 * 生成页面元素
 */
function initTopciList(list){
	var html = '';
	var obj = $('#lists');
	if(list && list.length>0){
		$.each(list,function(index,item){
			var id = item.id;
			var name = item.name;
			var cheapPrice = item.cheapPrice;
			cheapPrice = Number(cheapPrice).toFixed(2);
			var coverImage = item.coverImage;
			var courseCount = item.courseCount;//专题中 课程的数量
			var descr = item.descr;
			var slen =  descr.length;
			if(slen > 210){
				descr = descr.substring(0,210);
				descr += '...';
			}
			html += '<li>';
			html += '<a>';
			html += '<img onclick="toTopicDetail('+id+')" style="width:247px;height:173px;" src="'+coverImage+'" />';
			html += '<div class="lis_txt">';
			html += '<h4>'+name+'</h4>';
			html += '<p style="height: 80px;">'+descr+'</p>';
			html += '<span>';
			html += '<i>课程数量：'+courseCount+'个</i>';
			html += '<i>价格：<b>'+cheapPrice+'元</b></i>';
			html += '</span>';
			html += '</div>';
			html += '</a>';
			html += '</li>';
		});
	}
	obj.html(html);
}
/**
 * 跳转专题详情页面
 */
function toTopicDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toTopicDetail.action?id='+id;
}
</script> 
</html>