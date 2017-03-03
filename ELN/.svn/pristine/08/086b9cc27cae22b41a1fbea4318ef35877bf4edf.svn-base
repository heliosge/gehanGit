<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>浏览试卷</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" media="screen" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slider.css" media="screen" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<style type="text/css">
html{-webkit-text-size-adjust: none;}
.on_test .left_test .dx h5 {
  font-size: 14px;
  font-family: 'SimSun';
}
.on_test .left_test .dx p {
   -webkit-text-size-adjust: none;
  font-size: 12px;
  font-family: 'SimSun';
}
</style>

<script type="text/javascript">
var paperBean = ${paperBean};

/*加载试卷内容  */
var typeArray = new Array();
// 0x41 stands for 'A'
var optionName = $(Array(26)).map(function(i,e){return String.fromCharCode(0x41+i);});
var qt = ["","主观题","单选题","多选题","判断题","填空题","组合题","多媒体题",""];
var displayTypeStrMap = {
        1: '主观题',
        2: '单选题',
        3: '多选题',
        4: '判断题',
        5: '填空题',
        6: '组合题',
        7: '多媒体题',
};
var difficultyLevelStrMap = {
        1: '易',
        2: '中',
        3: '难'
};

//试题库
var zTree2 = null;
//云试题库
var zTreeYun = null;

$(function(){
	if(paperBean){
		if(paperBean != null){
                  $("#totalScore").text(paperBean.totalScore);
			$("#paperName").text(paperBean.title);
			var html = '';
            if (paperBean.organizingMode == 1) {
                var paperQuestionList = paperBean.paperQuestionList;
		        $('span.exportToWord').find('a.exportToWord').attr('href', '<c:url value="/exam/paper/exportToWord.action?paperId=' + paperBean.id + '"/>');
		        $('span.exportToWord').show();
				for(var i=0;i<paperQuestionList.length;i++){
					var paperQuestionBean = paperQuestionList[i];
					var questionBean = paperQuestionList[i].questionBean;
					var id = questionBean.id;
					var options = questionBean.options;
					var type = questionBean.type;
					var displayType = questionBean.displayType;
					if(!typeArray.contains(displayType)){
						typeArray.push(displayType);
					}
					var isMedia = questionBean.isMultimedia;
					var mediaType = questionBean.multimediaType;
					var mediaUrl = questionBean.multimediaUrl;
					var answerKeys = questionBean.answerKeys;
					//html += '<a id="mao_'+id+'" name="mao_'+id+'"></a>';
		            if (questionBean.displayType == 7) {
		                html += '<div class="singleQuestion multimediaWarning">';
		                html += '<h5><span style="font-size:14px;font-family:SimSun;" >('+paperQuestionBean.score+'分)</span>';
		                html += '<span style="font-size:14px;font-family:SimSun;" >' + (i+1) + '.不支持多媒体题的导出</span>';
		                html += '</h5>';
		                html += '</div>';
		                html += '<div class="singleQuestion multimediaQuestion">';
		            } else {
		                html += '<div class="singleQuestion">';
		            }

					html += '<div class="dx" flag="'+displayType+'">';
					//题目分值及序号
					html += '<h5 class="wordBreak">';
					html += '<span style="font-size:14px;font-family:SimSun;" >('+paperQuestionList[i].score+'分)</span>'+(i+1);
					//题目题干
					html += '.'+RR(setNull(questionBean.content));
                          html += '&nbsp;(' + displayTypeStrMap[questionBean.displayType] + ')';
					html += '</h5>';
		            if(type == 6){//组合题
		                if(questionBean.subQuestions != null){
		                    var scoreList = [];
		                    if (paperQuestionBean.scoreDetail) {
		                        scoreList = paperQuestionBean.scoreDetail.split(',');
		                    }
		                    for(var j=0;j<questionBean.subQuestions.length;j++){
		                        var subQuestion = questionBean.subQuestions[j];
		                        type = subQuestion.type;
		                        id = subQuestion.id;
		                        options = subQuestion.options;
		                        isMedia = subQuestion.isMultimedia;
		                        mediaType = subQuestion.multimediaType;
		                        mediaUrl = subQuestion.multimediaUrl;
		                        var scoreStr = '<span>&nbsp;</span>';
		                        if (scoreList.length > 0 && scoreList[j]) {
		                            scoreStr = '<span style="font-size:13px;font-family:SimSun;" >('+scoreList[j]+'分)</span>';
		                        }
		                        html += '<p class="wordBreak" style="font-size:13px;font-family:SimSun;" >' + scoreStr + '问题'+(j+1)+'：'+RR(setNull(subQuestion.content));
		                        html += '&nbsp;(' + displayTypeStrMap[subQuestion.displayType] + ')';
		                        html += '</p>';
		                        //题目状态标识 1-已完成 2-未完成 3-已标记 
		                        html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,subQuestion);
		                    }
		                }
		            }else{//其它题型
		                html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,questionBean);
		            }
					
					
					html += '</div>'; // for div.dx
                          html += '</div>'; // for div.singleQuestion
				}
                $("#questionDiv").append(html);
			} else {
				$('.tool_bottom').hide();

                initZtreeForFullCategoryName();

                for (var zz = 0; zz < paperBean.organizingRuleList.length; zz++) {
                    $('#question_info_div_2 table tbody').append($('#question_info_div_2 table tfoot tr').clone());
                    var curTr = $('#question_info_div_2 table tbody tr').last();
                    curTr.find('[name=questionDisplayType]').val(paperBean.organizingRuleList[zz].questionDisplayType);
                    var categoryId = paperBean.organizingRuleList[zz].questionCategoryId;
                    var treeNode = zTree2.getNodeByParam('id', categoryId, null);
                    treeNode = treeNode ? treeNode : zTreeYun.getNodeByParam('id', categoryId, null);
                    var categoryFullName = getZTreePathName(treeNode, 'name');
                    curTr.find('[name=paperCategoryName]').val(categoryFullName);
                    curTr.find('[name=paperCategoryName]').attr('title', categoryFullName);
                    for (var di = 0; di < paperBean.organizingRuleList[zz].autoQuestionGroup.difficultyCountList.length; di++) {
                        var difficultyCount = paperBean.organizingRuleList[zz].autoQuestionGroup.difficultyCountList[di];
                        curTr.find('[name=rule_num]').eq(di).val(difficultyCount.count);
                        curTr.find('[name=rule_score]').eq(di).val(difficultyCount.score);
                    }
                }
                $('#question_info_div_2').show();
		    }
		}
		$("div[id^='focus_']").each(function(index,element){
			//alert($(this).attr("id"));
			_topImageShow($(this).attr("id"));
		});
		initElement();
		$("a[id^='btn_show_']").each(function(index,element){
			var showId = $(this).attr("name");
			$('#btn_show_'+showId).on('click', function(){
				showVideoPlayerDialog('#Content_'+showId);
			});
		});
	}
});


//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
}; 

//初始化部分元素
function initElement(){
	typeArray.sort();
	//初始化头部各题型按钮
	for(var n=0;n<typeArray.length;n++){
		var num = typeArray[n];
		$("#inputDiv").append('<input type="button" style="margin-right: 5px;" value="'+qt[num]+'" id="input_'+num+'" onclick="selectQuestion('+num+');"/>');
	}
}

/*拼接选项 */
function concatOptions(questionId,type,isMedia,mediaType,mediaUrl,options,questionBean){
    var optionHtml = '';
    optionHtml += concatMedia(questionId,isMedia,mediaType,mediaUrl);
    if(type == 1){//主观题
        optionHtml += '<p><textarea disabled="disabled"></textarea></p>';
    }else if(type == 2){//单选题
        for(var i=0;i<options.length;i++){
            var otype = options[i].type;
            if(otype == 1 || questionBean.displayType != 7){//文本
                optionHtml += '<p><input type="radio" disabled="disabled"/>'
                +optionName[i]+'.<span style="font-size:12px;font-family:SimSun;" >'+setNull(options[i].content)+'</span></p>';
            }else{
                optionHtml += '<p><input type="radio" disabled="disabled"/>'
                +optionName[i]+'.<span style="font-size:12px;font-family:SimSun;" ><img src="'+options[i].content+'" class="option_img"  /></span></p>';
            }
            
        }
    }else if(type == 3){//多选题
        for(var i=0;i<options.length;i++){
            var otype = options[i].type;
            if(otype == 1 || questionBean.displayType != 7){//文本
                optionHtml += '<p><input type="checkbox" disabled="disabled"/>'
                +optionName[i]+'.<span style="font-size:12px;font-family:SimSun;" >'+setNull(options[i].content)+'</span></p>';
            }else{
                optionHtml += '<p><input type="checkbox" disabled="disabled"/>'
                +optionName[i]+'.<span style="font-size:12px;font-family:SimSun;" ><img src="'+options[i].content+'" class="option_img" /></span></p>';
            }
            
        }
    }else if(type == 4){//判断题
        optionHtml += '<p class="determineOption" style="font-size:12px;font-family:SimSun;" ><input type="radio" disabled="disabled"/>A.<span>正确</span></p>';
        optionHtml += '<p class="determineOption" style="font-size:12px;font-family:SimSun;" ><input type="radio" disabled="disabled"/>B.<span>错误</span></p>';
    }else if(type == 5){//填空题
        for (var i = 0; i < options.length; i++) {
            optionHtml += '<p><input type="text" disabled="disabled"/></p>';
        }
    }
    return optionHtml;
}

/*拼接多媒体题  */
function concatMedia(questionId,isMedia,mediaType,mediaUrl){
    var mediaHtml = '';
    if(isMedia){
        var url = [];
        if(mediaUrl != null && mediaUrl != ''){
            url = JSON.parse(mediaUrl);
        }
        if(mediaType == 1){//图片
            mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
            mediaHtml += '<ul>';
            for(var i=0;i<url.length;i++){
                mediaHtml += '<li><img style="max-width:300px;max-height:120px;" src="'+url[i]+'"/></li>';
            }
            mediaHtml += '</ul></div>';
        }else if(mediaType == 2){//音频
            for(var i=0;i<url.length;i++){
                mediaHtml += '<div style="">';
                mediaHtml += genAudioTag(url[i]);
                mediaHtml += '</div>';
            }
        }else if(mediaType == 3){//视频
            mediaHtml += '<a href="javascript:void(0);" id="btn_show_'+questionId+'" name="'+questionId+'">查看</a>';
            mediaHtml += '<div id="Content_'+questionId+'" style="display:none;">';
            for(var i=0;i<url.length;i++){
                mediaHtml += genVideoTagWithoutInitScript(url[i]);
            }
            mediaHtml += '</div>';
        }
    }
    return mediaHtml;
}

/*判空  */
function setNull(title){
    var tempStr = title ? title.trim() : title;
    return tempStr ? tempStr : '暂无';
}

function restoreReturn(str) {
    return str.replace(/\r\n|\n/g, '<br/>');
}
var RR = restoreReturn;

/*控制按钮样式与切换试题  */
function selectQuestion(type){
	$("#input_"+type).addClass("input_a");
	$("#inputDiv").find("input[id!='input_"+type+"']").removeClass("input_a");
	$("#questionDiv .dx[flag]").each(function(){
          if(type != 0 && $(this).attr("flag") != type){
        	  $(this).addClass('screenHide');
          }else{
        	  $(this).removeClass('screenHide');
          }
	});
}

function initZtreeForFullCategoryName() {
    //zTree配置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                pIdKey: "parentId",
                idKey: "id",
                rootPid: null
            },
        },
        view: {
            showTitle: true,
            selectedMulti: false,
            addDiyDom: addDiyDom
        }
    };

    //试题库
    $.ajax({
        type:"POST",
        async:false,
        data:null,
        url:'<c:url value="/exam/questionCategory/list.action"/>',
        success:function(categoryTree){
            zTree2 = $.fn.zTree.init($("#categoryTree2"), setting, categoryTree);
        }
    });
    //云试题库
    $.ajax({
        type:"POST",
        async:false,
        data:null,
        url:'<c:url value="/exam/questionCategory/listYun.action"/>',
        success:function(categoryTree){
            zTreeYun = $.fn.zTree.init($("#categoryTreeYun"), setting, categoryTree);
        }
    });
}
</script>
<style type="text/css">
/* common styles */
.wordBreak {
  word-break: break-all;
}
</style>
<style type="text/css" media="screen">
#paperName {
float: none;font-size: 30px;
}
.on_test .test_tool {
    max-height: 90px;
    height: inherit;
}
span.exportToWord{
  display: inline-block;
  float: right;
  margin-right: 5px;
}
.multimediaWarning{
    display:none;
}
.screenHide {
    display:none;
}

#question_info_div_2{
  font-family: "微软雅黑", Verdana, Arial, Helvetica, AppleGothic, sans-serif;
  font-size: 12px;
  color: #333;
}
#question_info_div_2 table{
    border-left: 1px solid #CDCDCD;
    border-top: 1px solid #CDCDCD;
    margin-top: 10px;
}
#question_info_div_2 table td{
    border-right: 1px solid #CDCDCD;
    border-bottom: 1px solid #CDCDCD;
    padding: 2px 2px;
    line-height: 30px;
}
#question_info_div_2 table td input{
    height: 30px;
    margin-top:10px;
}
#question_info_div_2 select {
  width: 140px;
  height: 30px;
  border: 1px solid #ccc;
  margin-top: 10px;
  margin-right: 10px;
}
</style>
<style type="text/css" media="print">
.test_tool .tool_bottom {
    display:none;
}
*{
font-size: 12pt;
font-family:'微软雅黑';
margin-bottom: 10px;
margin-top: 0px;
}
span.exportToWord{
    display:none;
}
.multimediaQuestion, .determineOption{
    display:none;
}
input[type=radio], input[type=checkbox], textarea, input[type=text]{
    display:none;
}
div.singleQuestion {
margin-bottom: 15pt;
}
</style>
</head>
<body>
	<div class="on_test">
    	<div class="test_title">
        	<h3>
        	    <span id="paperName"></span>
		        <span class="exportToWord" style="display:none;">
		            <a class="exportToWord" href="javascript:void(0)" download>导出试卷</a>
		            <a href="javascript:void(0)" onclick="window.print()">打印试卷</a>
		        </span>
        	</h3>
        </div>
        <div class="test_tool">
        	<div class="tool_top">
            	<p>试卷详情：
                	<span>总分：<span id="totalScore"></span></span>
                </p>
            </div>
          	<div class="tool_bottom" id="inputDiv">
            	<input type="button" value="显示全部" class="input_a" id="input_0" onclick="selectQuestion(0);"/>
            </div>
        </div>
        <form id="questionForm" method="post" action="">
	        <input type="hidden" id="userId" name="userId" />
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" id="examType" name="examType"/>
			<input type="hidden" id="examAssignId" name="examAssignId"/>
			<input type="hidden" id="isAutoMarking" name="isAutoMarking"/>
			<input type="hidden" id="remainingTime" name="remainingTime" value="0"/>
			<input type="hidden" id="organizingMode" name="organizingMode"/>
    	<div class="left_test" id="questionDiv">
            <div id="question_info_div_2" class="add_fr" style="display:none;">
                <table style="width:645px;">
                    <tbody>
                    </tbody>
                    <tfoot style="display: none;">
                        <tr>
                            <td style="width:150px;">
                                <span>题型：</span>
                                <select name="questionDisplayType" style="width:90px;" disabled="disabled">
                                    <option value="1" selected="selected">主观题</option>
                                    <option value="2">单选题</option>
                                    <option value="3">多选题</option>
                                    <option value="4">判断题</option>
                                    <option value="5">填空题</option>
                                    <option value="6">组合题</option>
                                    <option value="7">多媒体题</option>
                                </select>   
                            </td>
                            <td style="width:210px;" valign="middle">
                                <span style="" >试题库：</span>
                                <input type="text" name="paperCategoryName" style="width:135px;" disabled="disabled"/>
                            </td>
                            <td>
                                <div>
                                    <span style="">难度：易</span>
                                    <span style="margin-left:10px;">数量：</span>
                                    <input type="text" style="width:40px;" name="rule_num" value="1" disabled="disabled"/>   
                                    <span style="margin-left:10px;">分值：</span>
                                    <input type="text" style="width:40px;" name="rule_score" value="1" disabled="disabled"/> 

                                </div>
                                <div>
                                    <span style="">难度：中</span>
                                    <span style="margin-left:10px;">数量：</span>
                                    <input type="text" style="width:40px;" name="rule_num" value="1" disabled="disabled"/>   
                                    <span style="margin-left:10px;">分值：</span>
                                    <input type="text" style="width:40px;" name="rule_score" value="1" disabled="disabled"/> 
                                </div>
                                <div>
                                    <span style="">难度：难</span>
                                    <span style="margin-left:10px;">数量：</span>
                                    <input type="text" style="width:40px;" name="rule_num" value="1" disabled="disabled"/>       
                                    <span style="margin-left:10px;">分值：</span>
                                    <input type="text" style="width:40px;" name="rule_score" value="1" disabled="disabled"/>     
                                </div>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        </form>
    </div>


    <div id="categoryTreeContainer" style="display:none;" >
        <div class="tree_tab_div">
            <ul id="categoryTree2"></ul>
        </div>
        <div class="tree_tab_div">
            <ul id="categoryTreeYun"></ul>
        </div>
    </div>
</body>
<script src="<%=request.getContextPath() %>/js/jquery.artwl.thickbox.js"></script>
</html>
