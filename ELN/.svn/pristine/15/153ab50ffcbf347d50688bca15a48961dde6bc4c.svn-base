<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>答卷详情</title>
<style type="text/css">
.correct_answer{/* height:40px; */background-color:#e9f7e0;line-height:40px;font-family: '微软雅黑';font-weight:bold;padding-left:10px;word-break: break-all;}
.wrong_answer{/* height:40px; */background-color:#f7e8eb;line-height:40px;font-family: '微软雅黑';font-weight:bold;padding-left:10px;word-break: break-all;}
.answerMargin{margin-top: 10px;}
.addWrong{float:right;padding-right:10px;}
#questionDiv2{
  font-family: "微软雅黑", Verdana, Arial, Helvetica, AppleGothic, sans-serif;
  font-size: 12px;
  color: #333;
}
#questionDiv2 table{
    border-left: 1px solid #CDCDCD;
    border-top: 1px solid #CDCDCD;
    margin-top: 10px;
}
#questionDiv2 table td{
    border-right: 1px solid #CDCDCD;
    border-bottom: 1px solid #CDCDCD;
    padding: 2px 2px;
    line-height: 30px;
}
#questionDiv2 table td input{
    height: 30px;
    margin-top:10px;
}
#questionDiv2 select {
  width: 140px;
  height: 30px;
  border: 1px solid #ccc;
  margin-top: 10px;
  margin-right: 10px;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slider.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/exam_paper/image-slider.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>


<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var exam_assign_id = '${exam_assign_id}';//考试记录ID
var flag = ${flag};
var paramMap = ${paramMap};
var organizing_mode = paramMap[0].organizing_mode;//组卷方式 1标准组卷2随机组卷
var is_answer_public  = paramMap[0].is_answer_public;//答案是否公开
var is_marked = paramMap[0].is_marked;//试卷是否批阅完成
//alert(flag);
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var isOrNo = ["错误","正确"];
var typeArray = new Array();
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
var html = '';
//试题库
var zTree2 = null;
//云试题库
var zTreeYun = null;
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":exam_assign_id},
		url: "<%=request.getContextPath()%>/myExamManage/getGradeOtherInfo.action",
		success:function(data){
			if(data != null){
				$("#title").text(data.title);
				$("#totalScore").text("总分："+data.totalScore);
				$("#passScore").text("及格分："+data.passScore);
				$("#duration").text("考试时长："+data.duration);
			}
	    }
	});
});
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"exam_assign_id":exam_assign_id,"user_id":userId},
		url: "<%=request.getContextPath()%>/myExamManage/getExamAssignDetail.action",
		success:function(data){
			if(data != null){
				if(organizing_mode == 1){
					var paperBean = data.examScheduleVo.paperBean;
					if(paperBean != null){
						//$("#paperName").text(paperBean.title);
						//$("#organizingMode").val(paperBean.organizingMode);
						var paperQuestionList = paperBean.paperQuestionList;
						if(paperQuestionList != null){
							//alert(paperQuestionList[0].questionBean.content);
							for(var i=0;i<paperQuestionList.length;i++){
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
								html += '<div class="dx" flag="'+displayType+'">';
								//题目分值及序号
								html += '<h5>';
								html += '<span>('+paperQuestionList[i].score+'分)</span>'+(i+1);
								//题目题干
								html += '.'+questionBean.content+'</h5>';
								var scoreDetail = [];
								var zuheAns = '';
								if(type == 6){//组合题
									var subQuestions = questionBean.subQuestions;
									if(paperQuestionList[i].scoreDetail != null){
										scoreDetail = paperQuestionList[i].scoreDetail.split(",");
									}
									//alert(JSON.stringify(subQuestions));
									if(subQuestions != null){
										for(var j=0;j<subQuestions.length;j++){
											type = subQuestions[j].type;
											id = subQuestions[j].id;
											options = subQuestions[j].options;
											isMedia = subQuestions[j].isMultimedia;
											mediaType = subQuestions[j].multimediaType;
											mediaUrl = subQuestions[j].multimediaUrl;
											answerKeys = subQuestions[j].answerKeys;
											html += '<p><span>('+scoreDetail[j]+'分)</span>问题'+(j+1)+'：'+setNull(subQuestions[j].content)+'</p>';
											if(type == 5){//填空题的话，将answerKeys当作options
												html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys);
											}else{
												html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options);
											}
											zuheAns += setZuHeCorrectAnswer(j+1,type,subQuestions[j].correctAnswer);
										}
									}
								}else{//其它题型
									if(type == 5){//填空题的话，将answerKeys当作options
										html += concatOptions(id,type,isMedia,mediaType,mediaUrl,answerKeys);
									}else{
										html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options);
									}
								}
								if(is_answer_public != null && is_answer_public != 0){
									html += '<div class="correct_answer answerMargin">正确答案：';
									if(questionBean.type == 6){
										html += '<span>'+zuheAns+'</span>';
									}else if(questionBean.type == 1 || questionBean.type == 5){
										html += '<span>'+questionBean.correctAnswer+'</span>';
									}else{
										setCorrectAnswer(questionBean.type,questionBean.correctAnswer);
									}
									html += '</div>';
								}
								html += '</div>';
							}//for end
						}
					}//paperBean end
					$("#questionDiv").html(html);
					$("a[id^='btn_show_']").each(function(index,element){
						var showId = $(this).attr("name");
						$('#btn_show_'+showId).on('click', function(){
							showVideoPlayerDialog('#Content_'+showId);
						});
					});
					initElement();
					$("div[id^='focus_']").each(function(index,element){
						//alert($(this).attr("id"));
						_topImageShow($(this).attr("id"));
					});
					$("div .dx").children("h5").find(".question_content_img").each(function(index,element){
						$(this).on("click",function(){
							viewBigPic(this);
						});
					});
				}else{//2
					$("#inputDiv").remove();
					$("#questionDiv").remove();
					/* for(var i=0;i<data.length;i++){
						var type = data[i].type;
						html += '<div class="dx">';
						html += '<span style="font-size:20px;font-weight:bold;">';
						html +=  qt[type]+data[i].typeCount+"个";
						html += '</span>';
						html += '</div>';
					} */
					/* html += '<div class="dx">';
					for (var zz = 0; zz < data.length; zz++) {
			            var difficultyStr = '';
			            for (var di = 0; di < data[zz].autoQuestionGroup.difficultyCountList.length; di++) {
			                var difficultyCount = data[zz].autoQuestionGroup.difficultyCountList[di];
			                difficultyStr += difficultyLevelStrMap[difficultyCount.difficultyLevel] + ': ';
			                difficultyStr += difficultyCount.count + '题&nbsp;&nbsp;';
			            }
			            html += '<h5>规则' + (zz+1) + '.';
			            html += '题型:' + displayTypeStrMap[data[zz].questionDisplayType]
			                 + ';&nbsp;&nbsp;试题库名称:' + data[zz].questionCategoryName
			                 + ';&nbsp;&nbsp;难易度分布:' + difficultyStr;
			            html += '</h5>';
			        }
					html += '</div>'; */
					initZtreeForFullCategoryName();
					for (var zz = 0; zz < data.length; zz++) {
	                    $('#questionDiv2 table tbody').append($('#questionDiv2 table tfoot tr').clone());
	                    var curTr = $('#questionDiv2 table tbody tr').last();
	                    curTr.find('[name=questionDisplayType]').val(data[zz].questionDisplayType);
	                    var categoryId = data[zz].questionCategoryId;
	                    var treeNode = zTree2.getNodeByParam('id', categoryId, null);
	                    treeNode = treeNode ? treeNode : zTreeYun.getNodeByParam('id', categoryId, null);
	                    var categoryFullName = getZTreePathName(treeNode, 'name');
	                    curTr.find('[name=paperCategoryName]').val(categoryFullName);
	                    curTr.find('[name=paperCategoryName]').attr('title', categoryFullName);
	                    for (var di = 0; di < data[zz].autoQuestionGroup.difficultyCountList.length; di++) {
	                        var difficultyCount = data[zz].autoQuestionGroup.difficultyCountList[di];
	                        curTr.find('[name=rule_num]').eq(di).val(difficultyCount.count);
	                        curTr.find('[name=rule_score]').eq(di).val(difficultyCount.score);
	                    }
	                }
					$("#questionDiv2").show();
				}
			}
				
	    }
	});
});

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
        url:'<%=request.getContextPath()%>/exam/questionCategory/list.action',
        success:function(categoryTree){
            zTree2 = $.fn.zTree.init($("#categoryTree2"), setting, categoryTree);
        }
    });
    //云试题库
    $.ajax({
        type:"POST",
        async:false,
        data:null,
        url:'<%=request.getContextPath()%>/exam/questionCategory/listYun.action',
        success:function(categoryTree){
            zTreeYun = $.fn.zTree.init($("#categoryTreeYun"), setting, categoryTree);
        }
    });
}


//初始化部分元素
function initElement(){
	typeArray.sort();
	//初始化头部各题型按钮
	for(var n=0;n<typeArray.length;n++){
		var num = typeArray[n];
		$("#inputDiv").append('<input type="button" style="margin-right: 5px;" value="'+qt[num]+'" id="input_'+num+'" onclick="selectQuestion('+num+');"/>');
	}
}
//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
}; 


//设置正确答案
function setCorrectAnswer(type,correctAnswer){
	if(type == 2){//单选
		html += '<span>'+ optionName[correctAnswer] +'</span>';
	}else if(type == 3){//多选
		html += '<span>';
		cArray = correctAnswer.split(",");
		for(var i=0;i<cArray.length;i++){
			var num = cArray[i];
			html += optionName[num];
			if(i!=cArray.length - 1){
				html += ',';
			}
		}
		html += '</span>';
	}else if(type == 1 || type == 5){//主观题、填空题
		html += '<span>'+ correctAnswer +'</span>';
	}else{//判断题
		html += '<span>'+ isOrNo[correctAnswer] +'</span>';
	}
}

//设置组合题正确答案
function setZuHeCorrectAnswer(num,type,correctAnswer){
	var zuhe = '问题'+num+'：';
	if(type == 2){//单选
		zuhe += '<span>'+ optionName[correctAnswer] +'</span>';
	}else if(type == 3){//多选
		zuhe += '<span>'
		cArray = correctAnswer.split(",");
		for(var i=0;i<cArray.length;i++){
			var num = cArray[i];
			zuhe += optionName[num];
			if(i!=cArray.length - 1){
				zuhe += ',';
			}
		}
		zuhe += '</span>';
	}else if(type == 1 || type == 5){//主观题、填空题
		zuhe += '<span>'+ correctAnswer +'</span>';
	}else{//判断题
		zuhe += '<span>'+ isOrNo[correctAnswer] +'</span>';
	}
	return zuhe+"；";
}

/*拼接选项 */
function concatOptions(questionId,type,isMedia,mediaType,mediaUrl,options){
	var optionHtml = '';
	optionHtml += concatMedia(questionId,isMedia,mediaType,mediaUrl);
	if(type == 1){//主观题
		optionHtml += '<p><textarea id="option_'+questionId+'" name="option_'+questionId+'" ></textarea></p>';
	}else if(type == 2){//单选题
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id
				+'" />'+optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
			}else{
				optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="'+options[i].id
				+'" />'+optionName[i+1]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
			}
		}
	}else if(type == 3){//多选题
		for(var i=0;i<options.length;i++){
			var otype = options[i].type;
			if(otype == 1){//文本
				optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id
				+'" />'+optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
			}else{
				optionHtml += '<p><input type="checkbox" name="option_'+questionId+'" value="'+options[i].id
				+'" />'+optionName[i+1]+'.<span><img class="option_img" src="'+options[i].content+'" onclick="viewBigPic(this);"/></span></p>';
			}
		}
	}else if(type == 4){//判断题
		optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="1" />A.<span>正确</span></p>';
		optionHtml += '<p><input type="radio" name="option_'+questionId+'" value="0" />B.<span>错误</span></p>';
	}else if(type == 5){//填空题
		var answerKeys = [];
		if(options != null && options != ''){
			answerKeys = options.split(" ");
		}
		for(var i=0;i<answerKeys.length;i++){
			optionHtml += '<p><input type="text" name="option_'+questionId+'" /></p>';
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
		if(url != ''){
			if(mediaType == 1){//图片
				mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
				mediaHtml += '<ul>';
				for(var i=0;i<url.length;i++){
					mediaHtml += '<li><img class="media_img" src="'+url[i]+'" onclick="viewBigPic(this);"/></li>';
				}
				mediaHtml += '</ul></div>';
			}else if(mediaType == 2){//音频
				for(var i=0;i<url.length;i++){
					mediaHtml += '<div style="padding: 10px 0px 10px 70px;">';
					mediaHtml += genAudioTag(url[i]);
					mediaHtml += '</div>';
				}
			}else if(mediaType == 3){//视频
				mediaHtml += '<a href="javascript:void(0);" id="btn_show_'+questionId+'" name="'+questionId+'">查看</a>';
				mediaHtml += '<span id="Content_'+questionId+'" style="display:none;">';
				mediaHtml += '<div id="al" style="padding-left: 5px;">';
				for(var i=0;i<url.length;i++){
					mediaHtml += genVideoTagWithoutInitScript(url[i]);
				}
				mediaHtml += '</div>';
				mediaHtml += '</span>';
			}
		}
	}
	return mediaHtml;
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

/*判空  */
function setNull(title){
	if(title == null){
		return "暂无";
	}
	return title;
}

/*控制按钮样式与切换试题  */
function selectQuestion(type){
	$("#input_"+type).addClass("input_a");
	$("#inputDiv").find("input[id!='input_"+type+"']").removeClass();
	if(type != 0){
		//$("#div_"+type).css("display","block");
		//$("#questionDiv").find("div[flag ='flag_"+type+"']").css("display","block");
		//$("#questionDiv").find("div[flag!='flag_"+type+"']").css("display","none");
		$("#questionDiv >div").each(function(){
	          //alert($(this).attr("id"));  //打印子div的ID
	          if($(this).attr("flag") != type){
	        	  $(this).css("display","none");
	          }else{
	        	  $(this).css("display","block");
	          }
		});
	}else{
		$("#questionDiv").find("div").css("display","block");
	}
}
/*加入错题集  */
function addWrong(questionId,answer_id){
	dialog.confirm('确认加入吗？', function(){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{"fromUserId":userId,"examResultId":exam_assign_id,"questionId":questionId,"answer_id":answer_id},
			url: "<%=request.getContextPath()%>/myExamManage/addWrongCard.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						$("#wrong_"+questionId).remove();
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}

</script>
</head>

<body>
	<div class="on_test" style="margin-top:30px;">
    	<div class="test_title">
        	<h3 id="title"></h3>
        </div>
        <div class="test_tool">
        	<div class="tool_top">
            	<p>考试详情：
                	<span id="totalScore"></span>
                    <span id="passScore"></span>
                    <span id="duration"></span>
                </p>
            </div>
            <div class="tool_bottom" id="inputDiv">
            	<input type="button" value="显示全部" class="input_a" id="input_0" onclick="selectQuestion(0);"/>
               <!--  <input type="button" value="填空题" />
                <input type="button" value="判断题" />
                <input type="button" value="多选题" />
                <input type="button" value="单选题" />
                <input type="button" value="主观题" />
                <input type="button" value="组合题" />
                <input type="button" value="多媒体题" /> -->
            
            </div>
        </div>
    	<div class="left_test" id="questionDiv">
        	<!-- <div class="dx">
            	<h5><span>(1分)</span>1.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为：</h5>
                <p><input type="radio" name="dx" checked="checked" />A.<span>60:40</span></p>
                <p><input type="radio" name="dx" />B.<span>70:30</span></p>
                <p><input type="radio" name="dx" />C.<span>50:50</span></p>
                <p><input type="radio" name="dx" />D.<span>40:20:20</span></p>
            </div>
            <div class="dx">
            	<h5><span>(3分)</span>2.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为：</h5>
                <p><input type="checkbox" name="da" checked="checked"/>A.<span>60:40</span></p>
                <p><input type="checkbox" name="da" checked="checked" />B.<span>70:30</span></p>
                <p><input type="checkbox" name="da" />C.<span>50:50</span></p>
                <p><input type="checkbox" name="da" />D.<span>40:20:20</span></p>
            </div>
            <div class="dx">
            	<h5><span>(3分)</span>3.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为50:50，此说法：</h5>
                <p><input type="radio" name="pd" checked="checked" />A.<span>正确</span></p>
                <p><input type="radio" name="pd" />B.<span>错误</span></p>
                
            </div>
            <div class="dx">
            	<h5><span>(10分)</span>4.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为:</h5>
                <p><input type="text" name="tk" /></p>
                <p><input type="text" name="tk" /></p>
                
            </div>
            <div class="dx">
            	<h5><span>(12分)</span>5.全新QX250具备灵活的车内空间组合，其后排座椅放倒比例为:</h5>
                <p><textarea></textarea></p>
            </div>
            <div class="dx">
            	<h5><span>(12分)</span>6.这是一段描述题干，这是一段描述题干，这是一段描述题干</h5>
                <p>问题1：是8级么</p>
                <p><input type="text" name="tk" /></p>
                <p>问题2：是9级么</p>
                <p><input type="text" name="tk" /></p>
                
            </div>
             <div class="dx">
            	<h5><span>(12分)</span>7.视频说的是什么:</h5>
                <a href="#" id="btn_show">查看</a>
                <span id="Content" style="display:none;">
                	<img src="img/player.png">
                </span>
                <p><textarea></textarea></p>
            </div> -->
        </div>
        <div id="questionDiv2" style="padding-left:10px;display:none;">
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
            
            <div id="categoryTreeContainer" style="display:none;" >
		        <div class="tree_tab_div">
		            <ul id="categoryTree2"></ul>
		        </div>
		        <div class="tree_tab_div">
		            <ul id="categoryTreeYun"></ul>
		        </div>
    		</div>
    </div>
</body>
</html>
