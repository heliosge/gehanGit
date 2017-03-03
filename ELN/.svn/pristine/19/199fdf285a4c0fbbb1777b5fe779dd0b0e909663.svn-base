<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增问卷</title>
<style type="text/css">
.loadQuestionBtu{font-size:14px;font-family:微软雅黑;margin-right:5px;margin-top:2px;cursor: pointer;
	border-radius:2px;border:none;background-color:#f5a742;padding:5px 10px;color: #ffffff;}
.need_span{display: none;color: red;}
.optionTypeClass{
	font-size: 14px;
  	font-family: 微软雅黑;
  	font-weight: bold;
  	margin-right: 5px;
  	border-radius: 2px;
  	border: none;
  	background-color: #def3df;
  	padding: 2px 5px;
  	color: #6bbb34;
}
</style>
<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
//问卷库树
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var isOrNo = ["错误","正确"];
var qType = ["","单","多","主","评"];
var q_index = 1;//题目的序号

//修改时的回填参数
var qBean = ${qBean};
var qBean_questionList = ${qBean_questionList};

$(function(){
	if(qBean){
    	$(".paper_title").text("问卷详情");
    	//修改， 回填
    	$("#name").val(qBean.name);
    	$("#description").val(qBean.description);
    	$("#categoryId").val(qBean.categoryId);
		$("#categoryName").val(qBean.categoryBean.name);
		//导入试题
		for(var i=0;i<qBean_questionList.length;i++){
	    	appendQuestion(qBean_questionList[i], null);
		}
    }
});

/*新增问题  */
function appendQuestion(questionBean, editDivId){
	if(questionBean != null){
		var html = "";
		var id = questionBean.id;
		var isRequired = questionBean.isRequired;
		var content = questionBean.content;
	    var options = questionBean.options;
	    var type = questionBean.type;
	    //拼接问题
	    html += '<div class="question_div" questionId="'+id+'" questionType="'+type+'" isRequired="'+isRequired+'">';
	    html += '<div>';
	    html += '<span><input type="button" class="optionTypeClass" value="'+qType[type]+'"/></span>';
		html += '<span class="question_no">'+(q_index++)+'</span>. ';
	    html += '<span class="question_content">'+content+'</span>';
	    html += '</div>';
	    html += concatOptions(id,type,options,questionBean);
	    html += '</div>';
		$("#questionnaireDiv").first().append(html);
		//统计问题类型数量
		$("#type"+type).text(parseInt($("#type"+type).text())+1);
	}
}

/*拼接选项 */
function concatOptions(questionId,type,options,questionBean){
    var optionHtml = '<div id="optionDiv" class="option_div">';
    if(type == 3){//主观题
    	optionHtml += '<textarea style="width:300px;height:100px;" disabled="disabled" ></textarea>';
    }else if(type == 1){//单选题
        for(var i=0;i<options.length;i++){
            optionHtml += '<p><input type="radio" disabled="disabled"/><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p>';
        }
    }else if(type == 2){//多选题
        for(var i=0;i<options.length;i++){
            optionHtml += '<p><input type="checkbox" disabled="disabled"/><span>'+optionName[i+1]+"."+'</span><span>'+setNull(options[i].content)+'</span></p>';
        }
    }else{//评星题
        optionHtml += '<img src="<%=request.getContextPath()%>/images/star.png" style="width:100px;"/>';
    }
    optionHtml += '</div>';
    return optionHtml;
}


//返回问问卷列表
function backList(){
	window.location.href = "<%=request.getContextPath()%>/questionnaire/gotoManageList.action";
}

/*判空  */
function setNull(title){
    if(title == null){
        return "暂无";
    }
    return title;
}


</script>
<style type="text/css">

</style>
</head>
<body>
<div class="content" style="margin-top:20px;padding-bottom:10px;">
    <!-- <h3 class="paper_title" style="background-position:left 4px;" >新增问卷</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backList();"/> 
		<span class="paper_title" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增问卷</span>
	</div>
    <div class="title_1 fl">
        <p>基本信息</p>
    </div>
    <div class="lesson_add">
	    <form id="questionForm" action="">
	        <div id="basicInfoZone">
	        	<div class="add_gr">
	                <div class="add_fl">
	                    <em>问卷名称：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="text" style="width:200px;" disabled="disabled" id="name" name="name" />
	                </div>
	            </div>
	            <div class="add_gr">
	                <div class="add_fl">
	                    <em>问卷分类：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="hidden" id="categoryId" name="questionnaireCategory.categoryId" />
	                    <input id="categoryName" name="categoryName" disabled="disabled" type="text" style="width:200px;" readonly="readonly" />
	                </div>
	            </div>
		    	<div class="add_gr" style="">
		        	<div class="add_fl">
		                <em>问卷说明：</em>
		            </div>
		             <div class="add_fr">
		            	<textarea style="height:70px;width:250px;" disabled="disabled" id="description" name="description" ></textarea>
		            </div>
		    	</div>
	        </div>
	    </form>
    </div>
	<div class="title_1 fl" style="margin-top:10px;">
        <p>问卷信息</p>
    </div>
    <div style="border: 1px solid #ccc;padding-bottom: 5px;">
    	<div id="typeTotalDiv" style="padding-left: 5px;">
    		<span>已新增：</span>
    		单选题：<span id="type1">0</span>&nbsp;
    		多选题：<span id="type2">0</span>&nbsp;
    		主观题：<span id="type3">0</span>&nbsp;
    		评星题：<span id="type4">0</span>
    	</div>
    	<div id="questionnaireDiv" class="q_m2" style="padding-left: 5px;">
    		
    	</div>
    </div>
     <div class="" style="padding-top: 15px;">
          <input type="button" value="返回" class="buttonClass" style="background-color: #0085fe;" onclick="backList()" />
      </div>
</div>

</body>
</html>
