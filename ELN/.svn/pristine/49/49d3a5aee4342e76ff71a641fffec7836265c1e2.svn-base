<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js" ></script>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<!-- validator -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<script src="<c:url value="/js/layer/layer.js"/>"></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css"/>
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>

<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>

<script type="text/javascript">
// 用于支持同名(name属性相同)控件的输入验证
$.validator.prototype.elements = function() {
    var validator = this,
        rulesCache = {};

    // select all valid inputs inside the form (no submit or reset buttons)
    return $( this.currentForm )
    .find( "input, select, textarea" )
    .not( ":submit, :reset, :image, [disabled], [readonly]" )
    .not( this.settings.ignore )
    .filter( function() {
        if ( !this.name && validator.settings.debug && window.console ) {
            console.error( "%o has no name assigned", this );
        }

        if (this.id) {
            if ( !validator.objectLength( $( this ).rules() ) ) {
                return false;
            }
        } else {
            // select only the first element for each name, and only those with rules specified
            if ( this.name in rulesCache || !validator.objectLength( $( this ).rules() ) ) {
                return false;
            }

            rulesCache[ this.name ] = true;
        }
        return true;
    });
};

// Add validator methods
jQuery.validator.addMethod("shouldHaveParentheses", function(value, element) {
    var closestTag = getClosestTag(element);
    var mainType = questionTypeIntValueMap[$('#questionDisplayType').val()];
    var type = (mainType == 6 && $(closestTag).is('.singleSubQuestionDiv')) ? questionTypeIntValueMap[$(closestTag).find('[name=subQuestionDisplayType]').val()] : mainType;
    if (type != 5) {
        return true;
    }
    var tempTitle = $(closestTag).find('[name=questionContent]').val().match(/[\(（][\s　]*[\)）]/g);
    var countInTitle = tempTitle ? tempTitle.length : 0;
    return this.optional(element) || countInTitle > 0;
});

jQuery.validator.addMethod("countOfOptionsShouldEqualsToCountOfParentheses", function(value, element) {
    var closestTag = getClosestTag(element);
    var mainType = questionTypeIntValueMap[$('#questionDisplayType').val()];
    var type = (mainType == 6 && $(closestTag).is('.singleSubQuestionDiv')) ? questionTypeIntValueMap[$(closestTag).find('[name=subQuestionDisplayType]').val()] : mainType;
    if (type != 5) {
        return true;
    }
    var tempTitle = $(closestTag).find('[name=questionContent]').val().match(/[\(（][\s　]*[\)）]/g);
    var countInTitle = tempTitle ? tempTitle.length : 0;
    var countOfOptions = $(closestTag).find('[name=optionContent]').length;
    return this.optional(element) || countInTitle == countOfOptions;
});

jQuery.validator.addMethod("countOfAnswerKeysShouldEqualsToCountOfParentheses", function(value, element) {
    var closestTag = getClosestTag(element);
    var mainType = questionTypeIntValueMap[$('#questionDisplayType').val()];
    var type = (mainType == 6 && $(closestTag).is('.singleSubQuestionDiv')) ? questionTypeIntValueMap[$(closestTag).find('[name=subQuestionDisplayType]').val()] : mainType;
    if (type != 5) {
        return true;
    }
    var tempTitle = $(closestTag).find('[name=questionContent]').val().match(/[\(（][\s　]*[\)）]/g);
    var countInTitle = tempTitle ? tempTitle.length : 0;
    var tempAnswerKeys = $(closestTag).find('[name=answerKeys]').val().match(/[^\s　]+/g);
    var countOfAnswerKeys = tempAnswerKeys ? tempAnswerKeys.length : 0;
    return this.optional(element) || countInTitle == countOfAnswerKeys;
});

function addBlankForEmptyBrackets(str) {
    return str ? str.replace(/([\(（])([\)）])/g, '$1　$2') : str;
}

var validator = null;
//var oriQuestion = null;
//alert(JSON.stringify(parent.oriQuestion));
//<c:if test="${oriQuestion != null}">
//oriQuestion = parent.oriQuestion;
//</c:if>
$(function() {
	//alert(oriQuestion);
   	<c:if test="${fromPaper == 1}">
    	//试卷编辑页面，新增试题的请求
    	//$(".content").first().width(600);
    	switchQuestionType();
	</c:if>
	<c:if test="${fromPaper == 2}">
	//试卷编辑页面，新增试题的请求
	//$(".content").first().width(600);
		setOriQuestionInfo();
	</c:if> 
	//initQuestionInfo();
});

function initQuestionInfo() {
    if (parent.oriQuestion) {
        setOriQuestionInfo();
    } else {
    	switchQuestionType();
    }
}

function setOriQuestionInfo() {
    // Set value for base info fields.
    //selectAndSetCategoryInfo(oriQuestion.categoryId);
    $('#questionDisplayType').val(parent.oriQuestion.type);
    $('#questionDisplayType').attr('disabled', 'disabled');
    $('#questionContent').val(parent.oriQuestion.content);
    $('input:radio[name=isRequired][value='+ (parent.oriQuestion.isRequired) +']').prop('checked', true);
    switchQuestionType();
    // 分题型设置初始化值
    // 注意单选、多选、主观、评星
    var type = $('#questionDisplayType').val();
    var setQuestionDataMap = {
            1:setSingleChoiceQuestion,
            2:setMultiChoiceQuestion,
            3:setSubjectiveQuestion,
            4:setStarQuestion
    };
    /*设置各类型问题的选项  */
    setQuestionDataMap[type](parent.oriQuestion);
}

/*设置单选题的选项  */
function setSingleChoiceQuestion(q, _this, customizedAddOptionFunc) {
	if (!customizedAddOptionFunc) {
	    setChoiceQuestion(q, singleChoiceAddOption, _this);
	} else {
		setChoiceQuestion(q, customizedAddOptionFunc, _this);
	}
}
/*设置多选题的选项  */
function setMultiChoiceQuestion(q, _this, customizedAddOptionFunc) {
    if (!customizedAddOptionFunc) {
        setChoiceQuestion(q, multiChoiceAddOption, _this);
    } else {
        setChoiceQuestion(q, customizedAddOptionFunc, _this);
    }
}
/* var setSpecifiedDataMap = {
		1:setSingleChoiceQuestion,
		2:setMultiChoiceQuestion,
		3:setSubjectiveQuestion,
		4:setStarQuestion
}; */

/*设置主观题信息  */
function setSubjectiveQuestion(q, _this) {
	var closestTag = getClosestTag(_this);
	//$(closestTag).find('[name=questionContent]').val(q.content);
	if (q.options && q.options.length > 0) {
	   $(closestTag).find('[name=optionContent]').val(q.options[0].content);
	}
}
/*设置评星题信息  */
function setStarQuestion(q, _this) {
	var closestTag = getClosestTag(_this);
	//$(closestTag).find('[name=questionContent]').val(q.content);
	if (q.options && q.options.length > 0) {
	   $(closestTag).find('[name=optionContent]').val(q.options[0].content);
	}
}
function setChoiceQuestion(q, addOptionFunc, _this) {
	var closestTag = getClosestTag(_this);
    //$(closestTag).find('[name=questionContent]').val(q.content);
    var DEFAULT_OPTION_LENGTH = 2;
    // 多余默认选项数目，就增加选项
    for (var i = 0; i < q.options.length - DEFAULT_OPTION_LENGTH; i++) {
    	addOptionFunc(closestTag);
    }
    // 少于默认选项数目，就移除多余的选项
    for (var i = 0; i < DEFAULT_OPTION_LENGTH - q.options.length; i++) {
        removeChoiceQuestionLastOption(closestTag);
    }
    /* $.each($(closestTag).find('[name=optionContent]'), function(i, e) {
        if (q.options[i].type == 2) {
            $(e).closest('tr').find('[name=optionType]').prop('checked', true);
            switchOptionType($(e).closest('tr').find('[name=optionType]'), q.options[i].content);
        }
    }); */
    // 多媒体题型的情况下，可能涉及[name=optionContent]元素的增删,
    // 所以需要用另一个循环来设置试题选项等相关的值
    $.each($(closestTag).find('[name=optionContent]'), function(i, e) {
        $(e).val(q.options[i].content);
        /* if (q.options[i].isAnswer) {
            $(e).closest('tr').find('[name^=optionIsAnswer]').prop('checked', true);
        } */
    });
    
}
/*获取主观题信息  */
function getSubjectiveQuestion() {
    var question = {
            "type":$('#questionDisplayType').val(),
    		"content":addBlankForEmptyBrackets($("#questionContent").val()),
            "isRequired":$('input:radio[name=isRequired]:checked').val()
    };
   // $.extend(question, getSubjectiveQuestionSpecifiedData($('#displayZone')));
    return question;
}

/*获取评星题信息  */
function getStarQuestion() {
    var question = {
            "type":$('#questionDisplayType').val(),
    		"content":addBlankForEmptyBrackets($("#questionContent").val()),
            "isRequired":$('input:radio[name=isRequired]:checked').val()
    };
   // $.extend(question, getSubjectiveQuestionSpecifiedData($('#displayZone')));
    return question;
}

/*获取单选题 选项信息  */
function getSingleChoiceQuestionSpecifiedData(_this) {
	return {
            options:$.map($(_this).find('[name=optionContent]'),
                function(e) {
                    return {
                        content:$(e).val()
                    };
                })
	};
}
/*获取单选题 基本信息  */
function getSingleChoiceQuestion() {
    var question = {
            "type":$('#questionDisplayType').val(),
            "content":addBlankForEmptyBrackets($("#questionContent").val()),
            "isRequired":$('input:radio[name=isRequired]:checked').val()
    };
    $.extend(question, getSingleChoiceQuestionSpecifiedData($('#displayZone')));
    return question;
}

var getMultiChoiceQuestionSpecifiedData = getSingleChoiceQuestionSpecifiedData;
var getMultiChoiceQuestion = getSingleChoiceQuestion;


function validateCheckboxImpl(_this) {
    var closestTag = getClosestTag(_this);
	// 当有单选/多选框时，保证至少有一个选项选中
	if ($(closestTag).find('input[type!=hidden][name^=optionIsAnswer]').length > 0) {
		if ($(closestTag).find('input[type!=hidden][name^=optionIsAnswer]:checked').length > 0) {
			$(closestTag).find('.validateCheckboxResult').remove();
			return true;
		} else {
            $(closestTag).find('.appendValidateCheckboxResult').parent()
                .append('<span for="validateCheckboxResult" class="msg-box n-right" style="display: inline-block;">' +
                '<span class="msg-wrap n-error" role="alert"><span class="n-arrow"><b>◆</b><i>◆</i></span>' +
                '<span class="n-icon"></span><span class="n-msg">试题至少有一个正确选项</span></span></span>');
			return false;
		}
	}
	// 没有单选/多选框时，固定返回true
	return true;
}

function validateCheckbox() {
	var type = $('#questionDisplayType').val();
	if (type == 6) {
		var result = $.map($('#displayZone .singleSubQuestionDiv'), function (e) {
			validateCheckboxImpl(e);
		});
		for (var i = 0; i < result.length; i++) {
			if (!result[i]) {
				return false;
			}
		}
		return true;
	} else {
		return validateCheckboxImpl();
	}
}

/*表单验证  */
function validateAndGetQuestionData() {
	var validateResult = validator.form();
	//var validateCheckboxResult = validateCheckbox();
    if (!validateResult) {
        return false;
    } 
    var type = $('#questionDisplayType').val();
    var getQuestionDataMap = {
            1:getSingleChoiceQuestion,
            2:getMultiChoiceQuestion,
            3:getSubjectiveQuestion,
            4:getStarQuestion
    };
    //alert(getQuestionDataMap[type]);
    var question = getQuestionDataMap[type]();
    return question;
}

/* function getEditDivId(){
	return parent.editDivId;
} */

/*保存问题  */
function saveQuestion() {
    var question = validateAndGetQuestionData();
    if (!question) {
    	return false;
    }
	if (oriQuestion) {
        question.id = oriQuestion.id;
		modifyQuestion(question);
	} else {
		addQuestion(question);
	}
}

//试卷增加页面 调取
function saveQuestionForQuestionnaire() {
	return validateAndGetQuestionData();
}

function modifyQuestion(question) {
	$.ajax({
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(question),
        url:'<c:url value="/exam/question/modify.action" />',
        success:function(data){
            if (data == 'SUCCESS') {
                dialog.alert('保存成功!', function() {
                	history.go(-1);
                });
            } else {
                dialog.alert('保存失败!');
            }
        }
    });
}

function addQuestion(question) {
    $.ajax({
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(question),
        url:'<c:url value="/exam/question/add.action" />',
        success:function(data){
        	// data.rtnData: Question id for new question added.
        	if (data.rtnResult == 'SUCCESS') {
                showAddSuccessDialog();
        	} else {
        		dialog.alert('保存失败!');
        	}
        }
    });
}

function showAddSuccessDialog() {
    var param = {
            title:"提示",
            okValue: '确定',
            ok: function() {
            	history.go(-1);
            },
            content: "保存成功",
            width:"250px",
            cancelValue: '继续新增',
            cancel: function () {
                continueToAddQuestion();
                this.close();
            }
    };
    var d = dialog(param);
    d.showModal();
}

function redirectToOriginalPage() {
    dialog.confirm('确认返回么？', function() {
        history.go(-1);
    });
}

function continueToAddQuestion() {
	location.reload();
}

var questionTypeIntValueMap = {
        'SUBJECTIVE': 1,
        'SINGLE_CHOICE': 2,
        'MULTI_CHOICE': 3,
        'DETERMINE': 4,
        'FILL_BLANK': 5,
        'GROUP': 6,
        'MULTIMEDIA': 7,
};

var questionTypeMap = {
    '1': 'singleChoiceSample',
    '2': 'multiChoiceSample',
    '3': 'subjectiveSample',
    '4': 'starSample'
};

function switchSubQuestionType(_this) {
	var singleSubQuestionDiv = $(_this).closest('.singleSubQuestionDiv');
    var questionTypeKey = $(singleSubQuestionDiv).find('[name=subQuestionDisplayType]').val();
    $(singleSubQuestionDiv).find('.subQuestionEntity').html('');
    $(singleSubQuestionDiv).find('.subQuestionEntity').append($('#hiddenZone div.' + questionTypeMap[questionTypeKey]).clone().css('display', 'inherit'));
    renameRadioButtonGroups(singleSubQuestionDiv);
    makeFirstRadioCheckboxChecked(singleSubQuestionDiv);
    checkAndSetIdAttr();
}

// 同一个From下同名的RadioButton默认作为同一组按钮来对待，这一组按钮在同一时间只能有一个按钮处于checked状态
// 通过重命名RadioButton，将RadioButton划分到不同组来避免上述问题
function renameRadioButtonGroups(singleSubQuestionDiv) {
	$.each(['optionIsAnswer', 'isTrue'], function(i, name) {
		var needIncrease = false;
        $(singleSubQuestionDiv).find('[name=' + name + ']').each(function (i, e) {
        	$(e).attr('name', name + curMaxId);
        	needIncrease = true;
        });
        if (needIncrease) {
            curMaxId += 1;
        }
	});
}

/*  */
function switchQuestionType() {
    var questionTypeKey = $('#questionDisplayType').val();
	//alert(questionTypeKey);
    $('#displayZone').html('');
    $('#displayZone').append($('div.' + questionTypeMap[questionTypeKey]).clone().css('display', 'inherit'));
    validateOptions = {
        focusCleanup:true,
        ignore:null,
        rules: {
            questionContent: {
                required: true,
                maxlength: 500
            },
            optionContent: {
            	required: true,
            	maxlength: 200
            }
        },
        messages:{
            questionContent: {
                required: '不能为空',
                maxlength: '不能超过500字符'
            },
            optionContent: {
            	required: '不能为空',
                maxlength: '不能超过200字符'
            }
        },
        errorPlacement: function(error, element) {
            if (element.attr('name') == 'optionContent') { // 试题答案选项
            	if (element.parent().is('td') || element.is('[type=hidden]')) { // 单选/多选题答案选项的情况
            	    error.appendTo(element.closest('td'));
            	} else { // 主观、填空题答案选项的情况
            	    error.appendTo(element.parent());
            	}
            }  else {
                error.insertAfter(element);
            }
        }
    };
    checkAndSetIdAttr();
    validator = $('#questionForm').validate(validateOptions);
}

// curMaxId用于随机id和radio button group的命名
// 注意：使用后让curMaxId自增1
var curMaxId = 1;
function checkAndSetIdAttr() {
	$('#questionForm').find('input[type=text],input[type=hidden],textarea').each(function(index, e) {
		if (!$(e).attr('id')) {
            $(e).attr('id', '_random_id_'+curMaxId);
            curMaxId += 1;
            // console.log($(e).attr('id'));
		}
	});
}

function getClosestTag(_this) {
	var rtn = '#displayZone';
	if (_this) {
		if ($(_this).is('.singleSubQuestionDiv')) {
			rtn = _this;
		} else if ($(_this).closest('.singleSubQuestionDiv').length > 0) {
			rtn = $(_this).closest('.singleSubQuestionDiv');
		}
	}
	return rtn;
}

function makeFirstRadioCheckboxChecked(_this) {
    // For radio, checkbox elements:
    // make sure first one checked
    var closestTag = getClosestTag(_this);
    $(closestTag).find('input:radio[name^=isTrue]').first().prop('checked', true);
    $(closestTag).find('input:radio[name^=optionIsAnswer]').first().prop('checked', true);
    // 多选题设置第一个选项为选中状态 没有意义
    //$('#displayZone input:checkbox[name^=optionIsAnswer]').first().prop('checked', true);
}


function switchOptionType(_this, content) {
    if ($(_this).prop('checked')) {
        $(_this).prev().remove();
        $(_this).before('<span class="uploadOption"><span class="uploadBtn">上传图片</span>'+
                '<span class="uploadFileInfo"><input type="hidden" name="optionContent"/>'+
        '</span>'+
        '</span>');
        var uploadBtn = $(_this).closest('tr').find('.uploadBtn');
        //initPicUploader(uploadBtn, 'optionContent', content);
    } else {
        $(_this).prev().remove();
        $(_this).before('<input type="text" name="optionContent" maxlength="100"/>');
    }
    checkAndSetIdAttr();
}

// 向上移动元素 tr
function upChoiceQuestionOption(upMark) {
    if ($(upMark).closest('tr').prev().prev().length > 0) {
        $(upMark).closest('tr').prev().before($(upMark).closest('tr'));
    }
    var tableId = $(upMark).parent().parent().parent().closest("table").attr("id");
	setTrIndex(tableId);
}
//向下移动元素 tr
function downChoiceQuestionOption(downMark) {
    if ($(downMark).closest('tr').next().length > 0) {
        $(downMark).closest('tr').next().after($(downMark).closest('tr'));
    }
    var tableId = $(downMark).parent().parent().parent().closest("table").attr("id");
	setTrIndex(tableId);
}
//删除元素tr
function removeChoiceQuestionOption(_this) {
	var tableId = $(_this).parent().parent().parent().closest("table").attr("id");
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMinLimit(closestTag)) {
    	return false;
    }
    $(_this).closest('tr').remove();
	setTrIndex(tableId);
}

function reOrderSingleSubQuestionDiv() {
    $('#displayZone .subQuestionNo').each(function (index) {
        $('#displayZone .subQuestionNo').eq(index).text(index+1);
    });
}
function removeSingleSubQuestionDiv(questionDiv) {
    if ($('#displayZone .singleSubQuestionDiv').length > 2) {
        $(questionDiv).closest('.singleSubQuestionDiv').remove();
    }
    reOrderSingleSubQuestionDiv();
}
function addSingleSubQuestionDiv() {
    $('#displayZone .singleSubQuestionDiv').last().after($('#hiddenZone .singleSubQuestionDiv').last().clone());
    reOrderSingleSubQuestionDiv();
    checkAndSetIdAttr();
}

/*验证选项个数的最大值  */
function checkChoiceOptionMaxLimit(closestTag) {
	if ($(closestTag).find('[name=optionContent]').length >= 26) {
		layer.alert('选项个数范围是2~26个!');
		return false;
	}
	return true;
}
/*验证选项个数的最小值  */
function checkChoiceOptionMinLimit(closestTag) {
    if ($(closestTag).find('[name=optionContent]').length <= 2) {
        layer.alert('选项个数范围是2~26个!');
        return false;
    }
    return true;
}
/*单选题-添加选项  */
function singleChoiceAddOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMaxLimit(closestTag)) {
    	return false;
    }
    $(closestTag).find('[name=optionContent]').last().closest('tr').after($('#hiddenZone .singleChoiceSample [name=optionContent]').last().closest('tr').clone());
    checkAndSetIdAttr();
    setTrIndex("singleChoiceTable");
}
/*多选题-添加选项  */
function multiChoiceAddOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMaxLimit(closestTag)) {
        return false;
    }
    $(closestTag).find('[name=optionContent]').last().closest('tr').after($('#hiddenZone .multiChoiceSample [name=optionContent]').last().closest('tr').clone());
    checkAndSetIdAttr();
    setTrIndex("multiChoiceTable");
}

/*设置tr序号  */
function setTrIndex(tableId){
	var len = $('#'+tableId+' tr').length;
	//var len = $(closestTag).find('[name=optionContent]');
    for(var i = 1;i<len;i++){
        $('#'+tableId+' tr:eq('+i+') td:first').text(i);
    }
}


// 移除最后一个单选题/多选题的选项
function removeChoiceQuestionLastOption(_this) {
    var closestTag = getClosestTag(_this);
    // 故意不做该check，防止脏数据影响画面的加载
    // if (!checkChoiceOptionMinLimit(closestTag)) {
    //     return false;
    // }
    $(closestTag).find('[name=optionContent]').last().closest('tr').remove();
}


</script>
<style type="text/css">

#multimediaSampleTitlePicDiv img{
	margin:2px;width:70px;height:70px;
}

#displayZone th, td {
  text-align: center;
  padding-left: 16px;
  height: 36px;
  border: 1px solid #eaeaea;
  color: #666666;
  padding-right: 16px;
}

#displayZone .fr_tab th {
  padding-left: 0px;
  height: 40px;
  background: #f6f6f6;
  font-weight: bold;
  text-align: left;
}

.singleSubQuestionDiv{
	margin-left: 100px;
}

.groupSubQuestion {
  float: left;
  border-left: 30px solid #eee;
  border-right: 30px solid #eee;
  border-top: 5px solid #eee;
  border-bottom: 5px solid #eee;
  width: 90%;
}
.groupSubQuestion .add_gr .add_fl {
    width:80px;
}
.groupSubQuestion .add_gr_1 .add_fl {
    width:80px;
}

.fr_tab {
  width: 80%;
}

.groupSubQuestion .fr_tab {
  margin-left: 95px;
  width: 85%;
}



.groupSubQuestionControl {
  float: left;
  margin-top: 3px;
  background-color: rgb(160, 160, 160);
  width:90%;
  padding-left:30px;
  padding-right:30px;
  color: #fff;
}
.groupSubQuestionControl .subQuestionNoTag {
  float: left;
  margin-top: 7px;
}
.groupSubQuestionControl .groupSubQuestionControlButtons {
  display: inline-block;
  float: right;
  margin-right: 30px;
}
.groupSubQuestionControl .groupSubQuestionControlButtons input[type=button] {
  width: 70px;
  height: 30px;
  background: #d60500;
  color: #fff;
  font-family: '微软雅黑';
  text-align: center;
  border: none;
  margin-right: 10px;
  cursor: pointer;
  outline: none;
  margin: 0;
  padding: 0;
}
.inputHeight {
    /*height: 261px !important;*/
    height: 139px !important;
}
.inputWidth {
    /*width: 590px !important;*/
    width: 315px !important;
}
.add_gr {
    height: inherit !important;
}
.lesson_add .add_gr{

}
/* adjust for validation msg */
.n-icon,.n-arrow{
    margin-left: 0px!important;
    margin-right: 0px!important;
}


</style>
</head>
<body>
<div class="content" style="width:600px;margin-top:0px;padding-bottom:10px;padding-left: 10px;">
    <%-- <h3>${title}</h3> --%>
    <!-- <div class="title_1 fl">
        <p>试题基本信息</p>
    </div> -->
    <div class="lesson_add" style="width:100%;">
	    <form id="questionForm" action="">
	        <div id="basicInfoZone">
	            <div class="add_gr">
	                <div class="add_fl2">
	                    <span>*</span>
	                    <em>题型：</em>
	                </div>
	                <div class="add_fr">
	                    <select id="questionDisplayType" name="questionDisplayType" onchange="switchQuestionType();">
	                        <option value="3" selected="selected">主观题</option>
	                        <option value="1">单选题</option>
	                        <option value="2">多选题</option>
	                        <option value="4">评星题</option>
	                    </select>
	                    
	                </div>
	            </div>
	            <div class="add_gr">
	                <div class="add_fl2">
	                    <span>*</span>
	                    <em>题干：</em>
	                </div>
	                 <div class="add_fr">
	                    <textarea id="questionContent" name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
	                </div>
	            </div>
	            <!--<c:if test="${fromPaper != null}">style="display:none;"</c:if>  -->
	             <div class="add_gr">
	                <div class="add_fl2">
	                    <span>*</span>
	                    <em>必答：</em>
	                </div>
	                 <div class="add_fr">
	                    <input type="radio" checked="checked" name="isRequired" value="0" />
	                    <span>否</span>
	                    <input type="radio"  name="isRequired" value="1" />
	                    <span>是</span>
	                </div>
	            </div>
	        </div>
	        <div id="displayZone">
	        </div>
	    </form>
        <div id="hiddenZone">
        	<!--单选题 start  -->
            <div class="singleChoiceSample" style="display:none">
                <div class="add_gr">
                    <div class="add_fl3">
                        <span>*</span>
                        <em>试题选项：</em>
                    </div>
                     <div class="add_fr2">
                        <input type="button" value="新增选项" class="xz appendValidateCheckboxResult" style="margin-top:10px;" onclick="singleChoiceAddOption(this)"/>
                        
                    </div>
                </div>
                <div class="fr_tab2">
                            <table id="singleChoiceTable">
                                <tr>
                                    <th width="10%" align="center" style="text-align:center;">序号</th>
                                    <th width="45%" align="center" style="text-align:center;">选项内容</th>
                                    <th width="10%" align="center" style="text-align:center;">操作</th>
                                </tr>
                                <tr>
                                	<td>
                                        1
                                    </td>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                	<td>
                                        2
                                    </td>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                            </table>
                         
                        </div>
            </div>
			<!--单选题 end  -->
			<!--多选题 start  -->
            <div class="multiChoiceSample" style="display:none">
                <div class="add_gr">
                    <div class="add_fl3">
                        <span>*</span>
                        <em>试题选项：</em>
                    </div>
                     <div class="add_fr2">
                        <input type="button" value="新增选项" class="xz appendValidateCheckboxResult" style="margin-top:10px;" onclick="multiChoiceAddOption(this)"/>
                    </div>
                </div>
                <div class="clear:both;"></div>
                <div class="fr_tab2">
                   <table id="multiChoiceTable">
                       <tr>
                           <th width="10%" align="center" style="text-align:center;">序号</th>
                           <th width="45%" align="center" style="text-align:center;">选项内容</th>
                           <th width="10%" align="center" style="text-align:center;">操作</th>
                       </tr>
                       <tr>
                       		<td>
                               1
                           </td>
                           <td class="td_fr">
                               <span>*</span>
                               <input type="text" name="optionContent" maxlength="100" />
                           </td>
                           <td>
                               <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                               <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                               <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                           </td>
                       
                       </tr>
                       <tr>
                       		<td>
                               2
                           </td>
                           <td class="td_fr">
                               <span>*</span>
                               <input type="text" name="optionContent" maxlength="100" />
                           </td>
                           <td>
                               <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                               <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                               <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                           </td>
                       
                       </tr>
                   </table>
               </div>
            </div>
            <!--多选题 end  -->
        </div>
        
        <%-- <div class="button_cz" <c:if test="${fromPaper != null}">style="display:none;"</c:if>>
            <input type="button" value="保存" class="buttonClass" style="background-color: #0c9c92" onclick="saveQuestion()" />
            <input type="button" value="返回" class="buttonClass" style="background-color: #999999" onclick="redirectToOriginalPage()"/>
        </div> --%>
    </div>

</div>

</body>
</html>
