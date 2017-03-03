<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题详情</title>
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- <script src="<c:url value="/js/webhr.js"/>"></script> -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<script type="text/javascript">
var displayTypeStrMap = {
        1: '主观题',
        2: '单选题',
        3: '多选题',
        4: '判断题',
        5: '填空题',
        6: '组合题',
        7: '多媒体题'
};
var wrongId = '${wrong_id}';
// 0x41 stands for 'A'
var optionName = $(Array(26)).map(function(i,e){return String.fromCharCode(0x41+i);});
var isOrNo = ["错误","正确"];
var html = '';

$(function(){
    var questionBean = ${questionBean};
    var id = questionBean.id;
    var options = questionBean.options;
    var type = questionBean.type;
    var displayType = questionBean.displayType;
    var isMedia = questionBean.isMultimedia;
    var mediaType = questionBean.multimediaType;
    var mediaUrl = questionBean.multimediaUrl;
    var answerKeys = questionBean.answerKeys;
    html += '<h5>1.';
    html += RR(setNull(questionBean.content));
    html += '</h5>';
    html += '<p>题型：' + displayTypeStrMap[questionBean.displayType] + '</p>';
    if(type == 6){//组合题
        if(questionBean.subQuestions != null){
            for(var j=0;j<questionBean.subQuestions.length;j++){
                var subQuestion = questionBean.subQuestions[j];
                type = subQuestion.type;
                id = subQuestion.id;
                options = subQuestion.options;
                isMedia = subQuestion.isMultimedia;
                mediaType = subQuestion.multimediaType;
                mediaUrl = subQuestion.multimediaUrl;
                html += '<p>问题'+(j+1)+'：'+RR(setNull(subQuestion.content));
                html += '(' + displayTypeStrMap[subQuestion.displayType] + ')';
                html += '</p>';
                //题目状态标识 1-已完成 2-未完成 3-已标记 
                html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,subQuestion);
                html += '<p>试题解析: '+RR(setNull(subQuestion.analysis))+'</p>';
            }
        }
    }else{//其它题型
        html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,questionBean);
        html += '<p>试题解析: '+RR(setNull(questionBean.analysis))+'</p>';
    }
    $("div.q_m").first().html(html);
    $("div[id^='focus_']").each(function(index,element){
        //alert($(this).attr("id"));
        _topImageShow($(this).attr("id"));
    });
    $('#btn_show').on('click', function(){
        showVideoPlayerDialog('#Content');
    });
    $("div .q_m").children("h5").find(".question_content_img").each(function(index,element){
		$(this).on("click",function(){
			viewBigPic(this);
		});
	});
});

/*拼接选项 */
function concatOptions(questionId,type,isMedia,mediaType,mediaUrl,options,questionBean){
    var optionHtml = '<div class="q_da">';
    optionHtml += concatMedia(questionId,isMedia,mediaType,mediaUrl);
    if(type == 1){//主观题
    	var answer = null;
        if (options && options.length > 0) {
        	answer = setNull(options[0].content);
        } else {
        	answer = setNull(answer);
        }
        optionHtml += '<p class="subjectiveAnswer">'+RR(answer)+'</p>';
        optionHtml += '<p>关键字: ' + setNull(questionBean.answerKeys) + '</p>';
    }else if(type == 2){//单选题
        for(var i=0;i<options.length;i++){
            var otype = options[i].type;
            if(!otype || otype == 1){//文本
                if(options[i].isAnswer){
                    optionHtml += '<p style="margin:5px;" ><input type="radio" checked="checked" disabled="disabled" style="vertical-align: middle;" />'
                    +optionName[i]+'.<span>'+setNull(options[i].content)+'</span></p>';
                }else{
                    optionHtml += '<p style="margin:5px;" ><input type="radio" disabled="disabled" style="vertical-align: middle;" />'
                    +optionName[i]+'.<span>'+setNull(options[i].content)+'</span></p>';
                }
            }else{
                if(options[i].isAnswer){
                    optionHtml += '<p style="margin:5px;" ><input type="radio" checked="checked" disabled="disabled" style="vertical-align: middle;"/>'
                    +optionName[i]+'.<span><img src="'+options[i].content+'" class="option_img"  onclick="viewBigPic(this);"/></span></p>';
                }else{
                    optionHtml += '<p style="margin:5px;" ><input type="radio" disabled="disabled" style="vertical-align: middle;" />'
                    +optionName[i]+'.<span><img src="'+options[i].content+'" class="option_img" onclick="viewBigPic(this);"/></span></p>';
                }
            }
            
        }
    }else if(type == 3){//多选题
        for(var i=0;i<options.length;i++){
            var otype = options[i].type;
            if(!otype || otype == 1){//文本
            	if (options[i].isAnswer) {
            		optionHtml += '<p style="margin:5px;" ><input type="checkbox" checked="checked" disabled="disabled" style="vertical-align: middle;"/>'
                    +optionName[i]+'.<span>'+setNull(options[i].content)+'</span></p>';
            	} else {
            		optionHtml += '<p style="margin:5px;" ><input type="checkbox" disabled="disabled" style="vertical-align: middle;" />'
                    +optionName[i]+'.<span>'+setNull(options[i].content)+'</span></p>';
            	}
            }else{
                if(options[i].isAnswer){
                    optionHtml += '<p style="margin:5px;" ><input type="checkbox" checked="checked" disabled="disabled" style="vertical-align: middle;" />'
                    +optionName[i]+'.<span><img src="'+options[i].content+'" class="option_img" onclick="viewBigPic(this);"/></span></p>';
                }else{
                    optionHtml += '<p style="margin:5px;" ><input type="checkbox" disabled="disabled" style="vertical-align: middle;" />'
                    +optionName[i]+'.<span><img src="'+options[i].content+' class="option_img" onclick="viewBigPic(this);"/></span></p>';
                }
            }
            
        }
    }else if(type == 4){//判断题
        if(questionBean.isTrue){
            optionHtml += '<p style="margin:5px;" ><input type="radio" checked="checked" disabled="disabled" style="vertical-align: middle;" />A.<span>正确</span></p>';
            optionHtml += '<p style="margin:5px;" ><input type="radio" disabled="disabled" style="vertical-align: middle;" />B.<span>错误</span></p>';
        }else {
            optionHtml += '<p style="margin:5px;" ><input type="radio" disabled="disabled" style="vertical-align: middle;" />A.<span>正确</span></p>';
            optionHtml += '<p style="margin:5px;" ><input type="radio" checked="checked" disabled="disabled" style="vertical-align: middle;" />B.<span>错误</span></p>';
        }
    }else if(type == 5){//填空题
        for (var i = 0; i < options.length; i++) {
        	optionHtml += '<p class="fillBlankOptions">' + setNull(options[i].content) + '</p>';
        }
        optionHtml += '<p style="margin:5px;" >关键字: ' + setNull(questionBean.answerKeys) + '</p>';
    }
    optionHtml += '</div>';
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
        if(mediaType == 1 && mediaUrl && url !="" && url.length > 0){//图片
            mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
            mediaHtml += '<ul>';
            for(var i=0;i<url.length;i++){
                mediaHtml += '<li><img style="max-width:300px;max-height:140px;margin:5px 0px;" src="'+url[i]+'" onclick="viewBigPic(this);"/></li>';
            }
            mediaHtml += '</ul></div>';
        }else if(mediaType == 2){//音频
            for(var i=0;i<url.length;i++){
                mediaHtml += '<div style="">';
                mediaHtml += genAudioTag(url[i]);
                mediaHtml += '</div>';
            }
        }else if(mediaType == 3){//视频
            mediaHtml += '<a href="javascript:void(0);" id="btn_show" name="'+questionId+'">查看</a>';
            mediaHtml += '<div id="Content" style="display:none;">';
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
</script>
<style type="text/css">

.focus{width:520px;height:150px;overflow:hidden;position:relative;background:#fff;margin-left: auto;margin-right: auto;}
.focus ul{height:150px;position:absolute;}
.focus ul li{float:left;width:500px;height:150px;overflow:hidden;position:relative;background:#fff;text-align: center;}
.focus ul li div{position:absolute;overflow:hidden;}
.focus .btnBg{position:absolute;width:1200px;height:20px;left:0;bottom:0;background:#fff;}
.focus .btn{position:absolute;width:350px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
.focus .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
.focus .btn span.on{background:#fff;}
.focus .preNext{width:50px;height:100px;position:absolute;top:20px;background:url(<%=request.getContextPath()%>/images/img/sprite.png) no-repeat 0 0;cursor:pointer;}
.focus .pre{left:0;}
.focus .next{right:0;background-position:right top;}

.content .q_m .q_da{
height:inherit;
padding-right: 10px;
}
.content .q_m .q_da p{
line-height: 24px;
margin-bottom: 2px;
}
.content .q_m .q_da .subjectiveAnswer, .fillBlankOptions {
border: 1px solid;
}
.content .q_m {
width:98%;
}

.content .q_m img{
	margin:2px 5px;width:80px;height:80px;
}

.content h5, p{
word-break: break-all;
line-height: 24px;
}
.content h5{
	font-size: 20px;
	
}
</style>
</head>
<body>
<div class="content">
    <h3 style="background: none;padding-left: 2px;">试题详情</h3>
    <%-- <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">试题详情</span>
	</div> --%>
    <div class="q_m">
    </div>
</div>
</body>
</html>
