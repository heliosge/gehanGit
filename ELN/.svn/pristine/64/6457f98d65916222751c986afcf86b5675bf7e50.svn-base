<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增练习</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
.pClass{height:50px;margin-top:10px;}
.pClass ul{padding:0px 0px 0px 20px;}
.pClass ul li{background-color: #d95627;width:120px;color:#fff;padding:10px 10px;text-align: center;cursor: pointer;}
.subClass ul{ margin:0px;padding-left:20px;list_style:none;}
.subClass ul li{ margin:5px 10px 5px 0px;
				display:inline-block;list-style-type:none;white-space:nowrap;
				background-color: #e4e4e4;
				padding:10px 10px;cursor: pointer;}
</style>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:null,
		url: "<%=request.getContextPath()%>/exam/questionCategory/getExerciseQuestionCategory.action",
		success:function(data){
			if(data != null){
				var html = '';
				for(var i=0;i<data.length;i++){
					var subList = data[i].subCategoryList;
					if(subList != null && subList != ''){
						html += '<div id="pCatagory" class="pClass">';
						html += '<ul><li onmouseover="getNum(this,'+data[i].id+');" >'+data[i].name+'</li></ul>';
						html += '</div>';
						html += '<div id="subCatagory" class="subClass">';
        				html += '<ul>';
        				for(var j=0;j<subList.length;j++){
        					html += '<li onclick="excuteExercise('+data[i].id+','+subList[j].id+');" onmouseover="getNum(this,'+subList[j].id+');" >'+subList[j].name+'</li>';
    					}
        				html += '</ul>';
        				html += '</div>';
					}else{
						html += '<div id="pCatagory" class="pClass">';
						html += '<ul><li onclick="excuteExercise('+data[i].id+','+data[i].id+');" onmouseover="getNum(this,'+data[i].id+');" >'+data[i].name+'</li></ul>';
						html += '</div>';
					}
				}
				$("#catagoryDiv").html(html);
			}else{
				dialog.alert("操作失败！");
			}
	    }
	});
});

/*生成练习  */
function excuteExercise(pCategoryId,categoryId){
	//dialog.confirm('确认生成练习吗？', function(){
		var keyword="";
		keyword += "categoryId="+categoryId;
		keyword += "&ParentCategoryId="+pCategoryId;
		window.open("<%=request.getContextPath()%>/myExercise/gotoGenerateExercise.action?"+keyword);
	//});
}

/*获取分类下的题目总数及完成数  */
function getNum(obj,categoryId){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"categoryId":categoryId},
		url: "<%=request.getContextPath()%>/myExercise/getCategoryNum.action",
		success:function(data){
			if(data != null){
				var str = "总数："+data.split(",")[0] + "，已完成："+data.split(",")[1];
				$(obj).attr("title",str);
				//$(obj).css("background-color","#9bb6cb");
			}else{
				dialog.alert("操作失败！");
			}
	    }
	});
}

</script>
</head>
<body>
<div class="content">
    <!-- <h3 style="background-image: none;padding-left: 0px;font-size: 16px;color: #010101;border-bottom: 1px solid #000000;padding-left: 8px;">我的练习</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的练习</span>
	</div>
    <div class="lesson_add_2" style="padding-top: 0px;" onm>
	    <form id="questionForm" action="">
	        <div id="catagoryDiv">
	        	<!-- <div id="pCatagory" class="pClass">
	        		<ul><li onclick="alert(1);">一级试题库名称</li></ul>
	        	</div>
	        	<div id="subCatagory" class="subClass">
	        		<ul>
	        			<li onclick="alert(2);">二级试题库名称1</li>
	        			<li>二级试题库名称2</li>
	        			<li>二级试题库名称3</li>
	        			<li>二级试题库名称4</li>
	        			<li>二级试题库名称5</li>
	        			<li>二级试题库名称6</li>
	        			<li>二级试题库名称7</li>
	        			<li>二级试题库名称8</li>
	        			<li>二级试题库名称9</li>
	        			<li>二级试题库名称10</li>
	        		</ul>
	        	</div>
	            <div id="pCatagory" class="pClass">
	        		<ul><li onclick="alert(1);">一级试题库名称</li></ul>
	        	</div>
	        	<div id="subCatagory" class="subClass">
	        		<ul>
	        			<li onclick="alert(2);">二级试题库名称1</li>
	        			<li>二级试题库名称2</li>
	        			<li>二级试题库名称3</li>
	        			<li>二级试题库名称4</li>
	        			<li>二级试题库名称5</li>
	        			<li>二级试题库名称6</li>
	        			<li>二级试题库名称7</li>
	        			<li>二级试题库名称8</li>
	        			<li>二级试题库名称9</li>
	        			<li>二级试题库名称10</li>
	        		</ul>
	        	</div>  --> 	
	
	        </div>
	    </form>
    </div>
</div>
<!--弹出的题库 tree  -->
<div id="categoryTreeContainer" style="display:none;overflow: auto;">
        <div class="_left_tree" >
	        <div id="categoryTree" class="ztree" style="overflow: auto;min-height: 295px;max-height: 295px;">
	        </div>
        </div>
    </div>
</body>
</html>
