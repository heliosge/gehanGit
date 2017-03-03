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
<!-- <script src="<c:url value="/js/webhr.js"/>"></script> -->
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

var zTree = null;
var validator = null;
var oriQuestion = null;
<c:if test="${oriQuestion != null}">
oriQuestion = ${oriQuestion};
</c:if>

function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
}

$(function() {
	
	if(!WebUploader.Uploader.support()){
        alert('您的浏览器不支持上传插件！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error( 'WebUploader does not support the browser you are using.' );
    }
	
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
            selectedMulti: false,
            showTitle: true,
            addDiyDom: addDiyDom
        }
    };
    $.ajax({
        type:"POST",
        data:null,
        url:'<c:url value="/exam/questionCategory/list.action"/>',
        success:function(categoryTree){
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            expandZTree(zTree, 1);
            
            // After ztree is inited, init question info.
            initQuestionInfo();
        }
    });

    $('#chooseCategory').on('click', function() {
        var index = layer.open({
            title:'选择题库',
            type: 1,
            area: ['500px', '300px'],
            skin: 'layui-layer-rim',
            shadeClose: true, //点击遮罩关闭
            content: $('#categoryTreeContainer')
        });

        $('#closeCategoryTree').click(function () {
            layer.close(index);
        });

        $('#selectCategory').click(function () {
            var category = getSelectedZTreeNode();
            if (!category) {
                layer.alert('请先选择数据！');
                return false;
            }
            if (category.id == null) {
            	layer.alert('不可以选择公司节点(根节点)！');
            	return false;
            }
            $('#categoryId').val(category.id);
            $('#categoryName').val(getZTreePathName(category, 'name'));
            $("#categoryName").attr('title', getZTreePathName(category, 'name'));
            
            layer.close(index);
        });
    });
    
    <c:if test="${fromPaper == 1}">
    	//试卷编辑页面，新增试题的请求
    	$(".content").first().width(920);
	</c:if>
});

function initQuestionInfo() {
    if (oriQuestion) {
        setOriQuestionInfo();
    } else {
    	switchQuestionType();
        var categoryId = null;
<c:if test="${categoryId != null}">
        categoryId = ${categoryId};
</c:if>
        if (categoryId) {
        	selectAndSetCategoryInfo(categoryId);
        }
    }
}

function selectAndSetCategoryInfo(categoryId) {
    var category = zTree.getNodeByParam('id', categoryId, null);
    $('#categoryId').val(category.id);
    $('#categoryName').val(getZTreePathName(category, 'name'));
    $("#categoryName").attr('title', getZTreePathName(category, 'name'));
    zTree.selectNode(category);
}

function setOriQuestionInfo() {
    // Set value for base info fields.
    selectAndSetCategoryInfo(oriQuestion.categoryId);
    $('#questionDisplayType').val(oriQuestion.questionDisplayType);
    $('#questionDisplayType').attr('disabled', 'disabled');
    $('#difficultyLevel').val(oriQuestion.difficultyLevel);
    $('input:radio[name=isEnabled][value='+ (oriQuestion.isEnabled ? 1 : 0) +']').prop('checked', true);
    var doNotAddFirstMultimediaUploader = true;
    switchQuestionType(doNotAddFirstMultimediaUploader);
    
    // 分题型设置初始化值
    // 注意单选、多选、组合题子试题、填空题选项、多媒体答案类型等
    var type = questionTypeIntValueMap[$('#questionDisplayType').val()];
    var setQuestionDataMap = {
            1:setSubjectiveQuestion,
            2:setSingleChoiceQuestion,
            3:setMultiChoiceQuestion,
            4:setDetermineQuestion,
            5:setFillBlankQuestion,
            6:setGroupQuestion,
            7:setMultimediaQuestion
    };
    setQuestionDataMap[type](oriQuestion);
}

function setSubjectiveQuestion(q, _this) {
	var closestTag = getClosestTag(_this);
	$(closestTag).find('[name=questionContent]').val(q.content);
	//富文本
	$(closestTag).find('#multimediaSampleTitlePicDiv').html(q.content);
	
	$(closestTag).find('[name=answerKeys]').val(q.answerKeys);
	$(closestTag).find('[name=analysis]').val(q.analysis);
	if (q.options && q.options.length > 0) {
	   $(closestTag).find('[name=optionContent]').val(q.options[0].content);
	}
}
function setChoiceQuestion(q, addOptionFunc, _this) {
	var closestTag = getClosestTag(_this);

    $(closestTag).find('[name=questionContent]').val(q.content);
  	//富文本
	$(closestTag).find('#multimediaSampleTitlePicDiv').html(q.content);
  	
    $(closestTag).find('[name=analysis]').val(q.analysis);
    var DEFAULT_OPTION_LENGTH = 4;
    // 多余默认选项数目，就增加选项
    for (var i = 0; i < q.options.length - DEFAULT_OPTION_LENGTH; i++) {
    	addOptionFunc(closestTag);
    }
    // 少于默认选项数目，就移除多余的选项
    for (var i = 0; i < DEFAULT_OPTION_LENGTH - q.options.length; i++) {
        removeChoiceQuestionLastOption(closestTag);
    }
    $.each($(closestTag).find('[name=optionContent]'), function(i, e) {
        if (q.options[i].type == 2) {
            $(e).closest('tr').find('[name=optionType]').prop('checked', true);
            switchOptionType($(e).closest('tr').find('[name=optionType]'), q.options[i].content);
        }
    });
    // 多媒体题型的情况下，可能涉及[name=optionContent]元素的增删,
    // 所以需要用另一个循环来设置试题选项等相关的值
    $.each($(closestTag).find('[name=optionContent]'), function(i, e) {
        $(e).val(q.options[i].content);
        if (q.options[i].isAnswer) {
            $(e).closest('tr').find('[name^=optionIsAnswer]').prop('checked', true);
        }
    });
    
}
function setSingleChoiceQuestion(q, _this, customizedAddOptionFunc) {
	if (!customizedAddOptionFunc) {
	    setChoiceQuestion(q, singleChoiceAddOption, _this);
	} else {
		setChoiceQuestion(q, customizedAddOptionFunc, _this);
	}
}
function setMultiChoiceQuestion(q, _this, customizedAddOptionFunc) {
    if (!customizedAddOptionFunc) {
        setChoiceQuestion(q, multiChoiceAddOption, _this);
    } else {
        setChoiceQuestion(q, customizedAddOptionFunc, _this);
    }
}
function setDetermineQuestion(q, _this) {
    var closestTag = getClosestTag(_this);
	$(closestTag).find('[name=questionContent]').val(q.content);
	$(closestTag).find('[name^=isTrue][value='+ (q.isTrue ? 1 : 0) +']').prop('checked', true);
	$(closestTag).find('[name=analysis]').val(q.analysis);
}
function setFillBlankQuestion(q, _this) {
    var closestTag = getClosestTag(_this);
    $(closestTag).find('[name=questionContent]').val(q.content);
    $(closestTag).find('[name=answerKeys]').val(q.answerKeys);
    $(closestTag).find('[name=analysis]').val(q.analysis);
    var DEFAULT_OPTION_LENGTH = 1;
    for (var i = 0; i < q.options.length - DEFAULT_OPTION_LENGTH; i++) {
    	fillBlankAddOption(closestTag);
    }
    $.each($(closestTag).find('[name=optionContent]'), function(i, e) {
        $(e).val(q.options[i].content);
    });
}
var setSpecifiedDataMap = {
		1:setSubjectiveQuestion,
		2:setSingleChoiceQuestion,
		3:setMultiChoiceQuestion,
		4:setDetermineQuestion,
		5:setFillBlankQuestion
};
function setGroupQuestion(q) {
    $('#displayZone [name=questionContent]').first().val(q.content);
    var DEFAULT_SUB_QUESTION_LENGTH = 2;
    for (var i = 0; i < q.subQuestions.length - DEFAULT_SUB_QUESTION_LENGTH; i++) {
        addSingleSubQuestionDiv();
    }
    $.each($('#displayZone .singleSubQuestionDiv'), function(i, e) {
    	$(e).find('[name=subQuestionDisplayType]').val(q.subQuestions[i].questionDisplayType);
    	switchSubQuestionType($(e).find('[name=subQuestionDisplayType]'));
    	setSpecifiedDataMap[q.subQuestions[i].displayType](q.subQuestions[i], e);
    });
}
function setMultimediaQuestion(q) {
	// multimediaAnswerType is happenly to be equal to questionDisplayType.
	// multimediaAnswerType is range from 1 ~ 3.
	var multimediaAnswerType = q.type;
    $('#displayZone [name=answerType]').val(multimediaAnswerType);
    switchMultimediaAnswerType($('#displayZone [name=answerType]'));
    var multimediaQuestionMap = {
            1:setSubjectiveQuestion,
            2:setSingleChoiceQuestion,
            3:setMultiChoiceQuestion
    };
    var customizedAddOptionMap = {
            1:null,
            2:multimediaSingleChoiceAddOption,
            3:multimediaMultiChoiceAddOption
    };
    // 通过设置该参数为null，让实际处理函数使用默认值：'#displayZone'来处理
    var closestTag = null;
    multimediaQuestionMap[multimediaAnswerType](q, closestTag, customizedAddOptionMap[multimediaAnswerType]);
    if (q.multimediaType) {
        $('#displayZone [name=multimediaType]').val(q.multimediaType);
    } else {
        // 避免上传文件按钮样式出错
        $('#displayZone [name=multimediaType]').val(1);
    }
    var doNotAddFirstMultimediaUploader = true;
    switchMultimediaType($('#displayZone [name=multimediaType]'), doNotAddFirstMultimediaUploader);
    var multimediaUrlList = JSON.parse(q.multimediaUrl);
    // 导入的试题可能没有附件信息
    if (multimediaUrlList) {
	    for (var i = 0; i < multimediaUrlList.length; i++) {
	        addMultimediaUploader(multimediaUrlList[i]);
	    }
    } else {
    	// Add first uploader
    	addMultimediaUploader();
    }
}

function getSubjectiveQuestionSpecifiedData(_this) {
	return {
		    "content":addBlankForEmptyBrackets($(_this).find('[name=questionContent]').val()),
            "isSubQuestion":false,
            "isMultimedia":false,
            "answerKeys":(function() {
                var temp = $(_this).find('[name=answerKeys]').val().match(/[^\s　]+/g);
                return temp ? temp.join(' ') : '';
            })(),
            "analysis":$(_this).find('[name=analysis]').val(),
            options:[{
            content:$(_this).find('[name=optionContent]').val(),
            isAnswer:true,
            type:1}]
	};
}
function getSubjectiveQuestion() {
    var question = {
            "categoryId":$('#categoryId').val(),
            "difficultyLevel":$('#difficultyLevel').val(),
            "isEnabled":$('input:radio[name=isEnabled]:checked').val() == '1',
            
            "type":questionTypeIntValueMap[$('#questionDisplayType').val()],
    };
    $.extend(question, getSubjectiveQuestionSpecifiedData($('#displayZone')));
    return question;
}

function getSingleChoiceQuestionSpecifiedData(_this) {
	return {
            "content":addBlankForEmptyBrackets($(_this).find('[name=questionContent]').val()),
            "isSubQuestion":false,
            "isMultimedia":false,
            "analysis":$(_this).find('[name=analysis]').val(),
            options:$.map($(_this).find('[name=optionContent]'),
                function(e) {
                    return {
                        content:$(e).val(),
                        isAnswer:$(e).closest('tr').find('[name^=optionIsAnswer]:checked').length > 0,
                        type:$(e).closest('tr').find('[name=optionType]:checked').length > 0 ? 2 : 1
                    };
                })
	};
}
function getSingleChoiceQuestion() {
    var question = {
            "categoryId":$('#categoryId').val(),
            "type":questionTypeIntValueMap[$('#questionDisplayType').val()],
            "difficultyLevel":$('#difficultyLevel').val(),
            "isEnabled":$('input:radio[name=isEnabled]:checked').val() == '1',
    };
    $.extend(question, getSingleChoiceQuestionSpecifiedData($('#displayZone')));
    return question;
}
var getMultiChoiceQuestionSpecifiedData = getSingleChoiceQuestionSpecifiedData;
var getMultiChoiceQuestion = getSingleChoiceQuestion;
function getDetermineQuestionSpecifiedData(_this) {
	return {
            "content":addBlankForEmptyBrackets($(_this).find('[name=questionContent]').val()),
            "isSubQuestion":false,
            "isMultimedia":false,
            "isTrue":$(_this).find('[name^=isTrue]:checked').val() == '1',
            "analysis":$(_this).find('[name=analysis]').val(),
	};
}
function getDetermineQuestion() {
    var question = {
            "categoryId":$('#categoryId').val(),
            "type":questionTypeIntValueMap[$('#questionDisplayType').val()],
            "difficultyLevel":$('#difficultyLevel').val(),
            "isEnabled":$('input:radio[name=isEnabled]:checked').val() == '1'
    };
    $.extend(question, getDetermineQuestionSpecifiedData($('#displayZone')));
    return question;
}
function getFillBlankQuestionSpecifiedData(_this) {
	return {
            "content":addBlankForEmptyBrackets($(_this).find('[name=questionContent]').val()),
            "isSubQuestion":false,
            "isMultimedia":false,
            "answerKeys":(function() {
            	var temp = $(_this).find('[name=answerKeys]').val().match(/[^\s　]+/g);
            	return temp ? temp.join(' ') : '';
            })(),
            "analysis":$(_this).find('[name=analysis]').val(),
            options:$.map($(_this).find('[name=optionContent]'),
                function(e) {
                    return {
                        content:$(e).val(),
                        isAnswer:true,
                        type:1
                    };
                })
	};
}
function getFillBlankQuestion() {
    var question = {
            "categoryId":$('#categoryId').val(),
            "isEnabled":$('input:radio[name=isEnabled]:checked').val() == '1',
            "type":questionTypeIntValueMap[$('#questionDisplayType').val()],
            "difficultyLevel":$('#difficultyLevel').val(),
    };
    $.extend(question, getFillBlankQuestionSpecifiedData($('#displayZone')));
    return question;
}
var getSpecifiedDataMap = {
		1:getSubjectiveQuestionSpecifiedData,
		2:getSingleChoiceQuestionSpecifiedData,
		3:getMultiChoiceQuestionSpecifiedData,
		4:getDetermineQuestionSpecifiedData,
		5:getFillBlankQuestionSpecifiedData
};
function getGroupQuestion() {
    var question = {
            "categoryId":$('#categoryId').val(),
            "content":addBlankForEmptyBrackets($('#displayZone [name=questionContent]').first().val()),
            "type":questionTypeIntValueMap[$('#questionDisplayType').val()],
            "isSubQuestion":false,
            "isMultimedia":false,
            "difficultyLevel":$('#difficultyLevel').val(),
            "isEnabled":$('input:radio[name=isEnabled]:checked').val() == '1',
            "subQuestions":$.map($('#displayZone .singleSubQuestionDiv'),
            function(e){
            	var subQuestionType = questionTypeIntValueMap[$(e).find('[name=subQuestionDisplayType]').val()];
            	var subQuestionData = getSpecifiedDataMap[subQuestionType]($(e));
            	$.extend(subQuestionData, {
                       "isSubQuestion":true,
                       "type":subQuestionType,
            	});
            	return subQuestionData;
            })
    };
    return question;
}
function getMultimediaQuestion() {
    var multimediaAnswerType = $('#displayZone [name=answerType]').val();
    var multimediaQuestionMap = {
    		1:getSubjectiveQuestion,
    		2:getSingleChoiceQuestion,
    		3:getMultiChoiceQuestion
    };
    // multimediaAnswerType is happenly to be equal to questionDisplayType
    var questionDisplayTypeMap = {
    		1:1,
    		2:2,
    		3:3
    };
    var question = multimediaQuestionMap[multimediaAnswerType]();
    question.isMultimedia = true;
    question.type = questionDisplayTypeMap[multimediaAnswerType];
    question.multimediaType = $('#displayZone [name=multimediaType]').val();
    question.multimediaUrl = JSON.stringify(
    		$.map($('#displayZone [name=multimediaUrl]'),
    				function(e) {
    			return $(e).val();
    		}));
    question.file_size = $('#displayZone [name=file_size]').val();
    return question;
}

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
	var type = questionTypeIntValueMap[$('#questionDisplayType').val()];
	if (type == 6) {
		/* var result = $.map($('#displayZone .singleSubQuestionDiv'), function (e) {
			validateCheckboxImpl(e);
		}); */
		var result = [];
		$('#displayZone .singleSubQuestionDiv').each(function(index, val){
			result.push(validateCheckboxImpl(val));
		});
		//alert(result);
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

function validateAndGetQuestionData() {
	var validateResult = validator.form();
	var validateCheckboxResult = validateCheckbox();
    if (!validateResult || !validateCheckboxResult) {
        return false;
    }
    var type = questionTypeIntValueMap[$('#questionDisplayType').val()];
    var getQuestionDataMap = {
            1:getSubjectiveQuestion,
            2:getSingleChoiceQuestion,
            3:getMultiChoiceQuestion,
            4:getDetermineQuestion,
            5:getFillBlankQuestion,
            6:getGroupQuestion,
            7:getMultimediaQuestion
    };
    var question = getQuestionDataMap[type]();
    
    return question;
}

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
function saveQuestionForPaper() {
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
    'SUBJECTIVE': 'subjectiveSample',
    'SINGLE_CHOICE': 'singleChoiceSample',
    'MULTI_CHOICE': 'multiChoiceSample',
    'DETERMINE': 'determineSample',
    'FILL_BLANK': 'fillBlankSample',
    'GROUP': 'groupSample',
    'MULTIMEDIA': 'multimediaSample',
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

function switchQuestionType(doNotAddFirstMultimediaUploader) {
    var questionTypeKey = $('#questionDisplayType').val();

    $('#displayZone').html('');
    $('#displayZone').append($('div.' + questionTypeMap[questionTypeKey]).clone().css('display', 'inherit'));
    
    if (questionTypeKey == 'MULTIMEDIA'){
    	//多媒体题
    	//多媒体，题干插入图片
		createMultimediaSampleTitlePic();
    }
    
    if (questionTypeKey == 'MULTIMEDIA' && !doNotAddFirstMultimediaUploader) {
    	addMultimediaUploader();
    }
    makeFirstRadioCheckboxChecked();
    
    validateOptions = {
        focusCleanup:true,
        ignore:null,
        rules: {
            categoryId: {
                required: true
            },
            questionContent: {
                required: true,
                shouldHaveParentheses: true
            },
            optionContentValidationSummaryResult: {
            	required: true,
            	countOfOptionsShouldEqualsToCountOfParentheses: true
            },
            optionContent: {
            	required: true,
            	maxlength: 2000
            },
            answerKeys: {
            	required: true,
            	maxlength: 100,
                countOfAnswerKeysShouldEqualsToCountOfParentheses: true
            },
            /* multimediaUrl: {
            	required: true
            }, */
            multimediaType: {
            	required: true
            }
        },
        messages:{
            categoryId: {
                required: '不能为空'
            },
            questionContent: {
                required: '不能为空',
                shouldHaveParentheses: '不能没有括号'
            },
            optionContentValidationSummaryResult: {
                required: '不能为空',
                countOfOptionsShouldEqualsToCountOfParentheses: '题干中括号个数与答案的个数应该相等'
            },
            optionContent: {
            	required: '不能为空',
                maxlength: '不能超过2000字符'
            },
            answerKeys: {
                required: '不能为空',
                maxlength: '不能超过100字符',
                countOfAnswerKeysShouldEqualsToCountOfParentheses: '题干中括号个数与关键词的个数应该相等'
            },
            /* multimediaUrl: {
                required: '不能为空'
            }, */
            multimediaType: {
                required: '不能为空'
            }
        },
        errorPlacement: function(error, element) {
            if (element.attr('name') == 'categoryId') { // 试题分类ID
                error.appendTo( element.closest('div') );
            } else if (element.attr('name') == 'optionContent') { // 试题答案选项
            	if (element.parent().is('td') || element.is('[type=hidden]')) { // 单选/多选题答案选项的情况
            	    error.appendTo(element.closest('td'));
            	} else { // 主观、填空题答案选项的情况
            	    error.appendTo(element.parent());
            	}
            } else if (element.attr('name') == 'multimediaUrl') { // 多媒体附件URL
            	error.appendTo($(element).closest('div.uploadMultimedia'));
            } else if (element.attr('name') == 'multimediaType') { // 多媒体附件类型
                error.appendTo(element.parent());
            } else {
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

// File upload related
function initPicUploader(uploaderElement, urlFieldName, url) {
    var uploader = WebUploader.create({
        auto: true,
        // swf文件路径
        swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server:'<%=request.getContextPath()%>/exam/question/uploadFile.action',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id:uploaderElement,
            multiple:false
        },

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'jpg,png',
            mimeTypes: 'image/*'
        },
        
        fileNumLimit: 1,
        fileSingleSizeLimit: 512000,
        duplicate: true
    });
    
    function addEventForRemoveBtn() {
    	$(uploaderElement).next().find('a.close').on('click', function() {
            $(uploaderElement).next().html('<input type="hidden" name="' + urlFieldName + '"/>');
            uploader.reset();
        });
    }
    
    var hiddenUrlTag = function(url) {
    	if (url) {
    	    return '<input type="hidden" name="' + urlFieldName + '" value="' + url + '"/>';
    	} else {
    		return '<input type="hidden" name="' + urlFieldName + '"/>';
    	}
    };
    var originalFileNameTag = function(fileName) {
    	return '<input class="originalFileName" type="text" value="' + fileName + '" style="width:220px;border:none;" />';
    };
    var imgPreviewTag = function(src) {
        return '<img src="' + src + '" style="height:30px;vertical-align:middle;max-width:220px;" />';
    };
    var removeBtnTag = function() {
    	return '<a class="close" href="javascript:void(0)">X&nbsp;&nbsp;&nbsp;&nbsp;</a>';
    };
    
    var UPLOADING = 1;
    var UPLOADED = 2;
    
    function updateUploadFileInfo(type, url) {
    	if (type == UPLOADING) {
	        $(uploaderElement).next().html(
	                hiddenUrlTag() +
	                originalFileNameTag(url) +
	                removeBtnTag());
    	} else {
	        $(uploaderElement).next().html(
	                hiddenUrlTag(url) +
	                imgPreviewTag(url) +
	                removeBtnTag());
    	}
        addEventForRemoveBtn();
    }
    
    uploader.on( 'fileQueued', function( file ) {
    	updateUploadFileInfo(UPLOADING, file.name);
    });
    
    uploader.on( 'uploadAccept', function( file ,ret ) {
    	if(ret.result == 'OVER_CAPACITY'){
			dialog.alert("资源容量已用完，请联系管理员。");
		}else{
			updateUploadFileInfo(UPLOADED, ret._raw);
		}
    	
    });
    
    uploader.on('error', function(type) {
        if (type == 'Q_EXCEED_NUM_LIMIT') {
            dialog.alert('只能上传一个文件');
            return;
        }
        if (type == 'F_EXCEED_SIZE') {
            dialog.alert('上传文件过大');
            return;
        }
        if (type == 'Q_TYPE_DENIED') {
            dialog.alert('上传文件类型不匹配');
            return;
        }
        layer.alert('上传文件出错:' + type);
    });
    
    if (url) {
        updateUploadFileInfo(UPLOADED, url);
    }
}
function switchOptionType(_this, content) {
    if ($(_this).prop('checked')) {
        $(_this).prev().remove();
        $(_this).before('<span class="uploadOption"><span class="uploadBtn">上传图片</span>'+
                '<span class="uploadFileInfo"><input type="hidden" name="optionContent"/>'+
        '</span>'+
        '</span>');
        var uploadBtn = $(_this).closest('tr').find('.uploadBtn');
        initPicUploader(uploadBtn, 'optionContent', content);
    } else {
        $(_this).prev().remove();
        $(_this).before('<input type="text" name="optionContent" maxlength="100"/>');
    }
    checkAndSetIdAttr();
}

function addMultimediaUploader(multimediaUrl) {
    $('#displayZone .uploadMultimediaGroup').append($('#hiddenZone .multimediaSampleOptionControls .uploadMultimedia').clone());
    var multimediaType = $('#displayZone [name=multimediaType]').val();
    initialUploadMethodMap[multimediaType]($('#displayZone .uploadMultimediaGroup').find('.uploadBtn').last(), 'multimediaUrl', multimediaUrl);
    checkAndSetIdAttr();
}



function initAudioUploader(uploaderElement, urlFieldName, url) {
    var uploader = WebUploader.create({
        auto: true,
        // swf文件路径
        swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server:'<%=request.getContextPath()%>/exam/question/uploadFile.action',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id:uploaderElement,
            multiple:false
        },

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        accept: {
            title: 'Audios',
            extensions: 'mp3',
            mimeTypes: 'audio/*'
        },
        
        fileNumLimit: 1,
        fileSingleSizeLimit: 8388608,//8MB
        duplicate: true
    });
    
    function addEventForRemoveBtn() {
        $(uploaderElement).next().find('a.close').on('click', function() {
            $(uploaderElement).next().html('<input type="hidden" name="' + urlFieldName + '"/>');
            uploader.reset();
        });
    }
    var hiddenUrlTag = function(url, size) {
        if (url) {
        	
        	var str = '<input type="hidden" name="' + urlFieldName + '" value="' + url + '"/>';
        	if(size){
        		str += '<input type="hidden" name="file_size" value="' + size + '"/>';
        	}
            return str;
        } else {
            return '<input type="hidden" name="' + urlFieldName + '"/>';
        }
    };
    var originalFileNameTag = function(fileName) {
        return '<input class="originalFileName" type="text" value="' + fileName + '" style="width:220px;border:none;" />';
    };
    var previewTag = function(url) {
        return '<a href="javascript:void(0);" class="btn_show_media">查看</a>';
    };
    var mediaTag = function(url) {
        var mediaHtml = '<div style="margin-left:15px; margin-top:15px;">';
        mediaHtml += genAudioTag(url);
        mediaHtml += '</div>';
        return mediaHtml;
    }
    var removeBtnTag = function() {
        return '<a class="close" href="javascript:void(0)">X&nbsp;&nbsp;&nbsp;&nbsp;</a>';
    };
    
    var UPLOADING = 1;
    var UPLOADED = 2;
    
    function updateUploadFileInfo(type, url, size) {
    	
        if (type == UPLOADING) {
            $(uploaderElement).next().html(
                    hiddenUrlTag() +
                    originalFileNameTag(url) +
                    removeBtnTag());
        } else {
        	//alert(type + "  " + url + "  " + size);
            $(uploaderElement).next().html(
                    hiddenUrlTag(url, size) +
                    previewTag(url) +
                    removeBtnTag());
            $(uploaderElement).next().find('.btn_show_media').on('click', function() {
                dialog({
                    title:'视窗',
                    width : 430,
                    height : 60,
                    content: mediaTag(url),
                    fixed:true
                }).showModal();
            });
        }
        addEventForRemoveBtn();
    }
    
    uploader.on( 'fileQueued', function( file ) {
        updateUploadFileInfo(UPLOADING, file.name, file.size);
    });
    
    
    uploader.on( 'uploadAccept', function( file ,ret ) {
    	if(ret.result == 'OVER_CAPACITY'){
			dialog.alert("资源容量已用完，请联系管理员。");
		}else{
			updateUploadFileInfo(UPLOADED, ret._raw, file.file.size);
		}
        
    });
    
    // upload progress dialog begin
    var $index;
    uploader.on( 'uploadFinished', function( file ,ret ) {
        //上传完成，去掉蒙层
        layer.closeAll();
        $index = null;
    })
    uploader.on('uploadStart', function(file) {
        //开始上传，进行蒙层处理
        if(!$index){
            $index =  layer.open({
                    type: 1,
                    // shade: [0.3,'#000'], 
                    title: '文件上传中',
                    content: $('.layer_notice'), //捕获的元素
                    cancel: function(index){
                            layer.close(index);
                            $index = null;
                            $(uploaderElement).next().find('a.close').click();
                    }
                }); 
        }
    })
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $("li.progress");
        $li.find("label.file-name").html(file.name).attr("title",file.name);
        var processedPercentage = 100*percentage-1;
        processedPercentage = Math.round(processedPercentage < 0 ? 0 : processedPercentage);
        initJqMeter(processedPercentage);
    });
    /**
     * 初始化进度条
     */
    function initJqMeter(value){
        $("#jqMeter_div").jQMeter({
                goal:'100',
                raised:value+'',
                meterOrientation:'horizontal',
                width:'300px',
                height:'25px'
        });
    }
    // upload progress dialog end
    
    uploader.on('error', function(type) {
        if (type == 'Q_EXCEED_NUM_LIMIT') {
            dialog.alert('只能上传一个文件');
            return;
        }
        if (type == 'F_EXCEED_SIZE') {
            dialog.alert('上传文件过大');
            return;
        }
        if (type == 'Q_TYPE_DENIED') {
            dialog.alert('上传文件类型不匹配');
            return;
        }
    });
    
    if (url) {
        updateUploadFileInfo(UPLOADED, url);
    }
}


function initVideoUploader(uploaderElement, urlFieldName, url) {
    var uploader = WebUploader.create({
        auto: true,
        // swf文件路径
        swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server:'<%=request.getContextPath()%>/exam/question/uploadFile.action',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id:uploaderElement,
            multiple:false
        },

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        accept: {
            title: 'Videos',
            extensions: 'mp4,flv'
            // 默认情况下，Windows无法识别flv为视频文件，通过注释下面内容，使用户上传文件时可以看到所有文件
            // mimeTypes: 'video/*'
        },
        
        fileNumLimit: 1,
        fileSingleSizeLimit: 8388608,//8MB
        duplicate: true
    });
    
    function addEventForRemoveBtn() {
        $(uploaderElement).next().find('a.close').on('click', function() {
            $(uploaderElement).next().html('<input type="hidden" name="' + urlFieldName + '"/>');
            uploader.reset();
        });
    }
    var hiddenUrlTag = function(url, size) {
        if (url) {
        	var str = '<input type="hidden" name="' + urlFieldName + '" value="' + url + '"/>';
        	if(size){
        		str += '<input type="hidden" name="file_size" value="' + size + '"/>';
        	}
            return str;
        } else {
            return '<input type="hidden" name="' + urlFieldName + '"/>';
        }
    };
    var originalFileNameTag = function(fileName) {
        return '<input class="originalFileName" type="text" value="' + fileName + '" style="width:220px;border:none;" />';
    };
    var previewTag = function(url) {
        return '<a href="javascript:void(0);" class="btn_show_video">查看</a>';
    };
    var videoTag = function(url) {
    	var mediaHtml = '';
        mediaHtml += '<div id="Content">';
<c:choose>
    <c:when test="${fromPaper == 1}">
        mediaHtml += genVideoTagWithoutInitScript(url, 300, 200);
    </c:when>
    <c:otherwise>
        mediaHtml += genVideoTagWithoutInitScript(url);
    </c:otherwise>
</c:choose>
        mediaHtml += '</div>';
        return mediaHtml;
    }
    var removeBtnTag = function() {
        return '<a class="close" href="javascript:void(0)">X&nbsp;&nbsp;&nbsp;&nbsp;</a>';
    };
    
    var UPLOADING = 1;
    var UPLOADED = 2;
    
    function updateUploadFileInfo(type, url, size) {
        if (type == UPLOADING) {
            $(uploaderElement).next().html(
                    hiddenUrlTag() +
                    originalFileNameTag(url) +
                    removeBtnTag());
        } else {
            $(uploaderElement).next().html(
                    hiddenUrlTag(url, size) +
                    previewTag(url) +
                    removeBtnTag());
            $(uploaderElement).next().find('.btn_show_video').on('click', function() {
<c:choose>
    <c:when test="${fromPaper == 1}">
                showVideoPlayerDialog(videoTag(url), null, 300, 200);
    </c:when>
    <c:otherwise>
                showVideoPlayerDialog(videoTag(url));
    </c:otherwise>
</c:choose>
            });
        }
        addEventForRemoveBtn();
    }
    
    uploader.on( 'fileQueued', function( file ) {
        updateUploadFileInfo(UPLOADING, file.name, file.size);
    });
    
    uploader.on( 'uploadAccept', function( file ,ret ) {
    	if(ret.result == 'OVER_CAPACITY'){
			dialog.alert("资源容量已用完，请联系管理员。");
		}else if (ret._raw == 'FAIL') {
    		return false;
    	}
        updateUploadFileInfo(UPLOADED, ret._raw, file.file.size);
    });
    
    uploader.on( 'uploadError', function(file, reason) {
        //上传完成，去掉蒙层
        layer.closeAll();
        $index = null;
    	$(uploaderElement).next().find('a.close').click();
        dialog.alert('文件上传失败');
    });
    /*// simple version
    uploader.on( 'uploadProgress', function( file, percentage ) {
    	updateUploadFileInfo(UPLOADING, '上传中：'+parseInt(percentage*100)+'%: ' + file.name);
    });
    */
    // upload progress dialog begin
    var $index;
    uploader.on( 'uploadSuccess', function( file ,ret ) {
        //上传完成，去掉蒙层
        layer.closeAll();
        $index = null;
    })
    uploader.on('uploadStart', function(file) {
        //开始上传，进行蒙层处理
        if(!$index){
            $index =  layer.open({
                    type: 1,
                    // shade: [0.3,'#000'], 
                    title: '文件上传中',
                    content: $('.layer_notice'), //捕获的元素
                    cancel: function(index){
                            layer.close(index);
                            $index = null;
                            $(uploaderElement).next().find('a.close').click();
                    }
                }); 
        }
    })
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $("li.progress");
        $li.find("label.file-name").html(file.name).attr("title",file.name);
        var processedPercentage = 100*percentage-1;
        processedPercentage = Math.round(processedPercentage < 0 ? 0 : processedPercentage);
        initJqMeter(processedPercentage);
    });
    /**
     * 初始化进度条
     */
    function initJqMeter(value){
        $("#jqMeter_div").jQMeter({
                goal:'100',
                raised:value+'',
                meterOrientation:'horizontal',
                width:'300px',
                height:'25px'
        });
    }
    // upload progress dialog end
    
    uploader.on('error', function(type) {
        if (type == 'Q_EXCEED_NUM_LIMIT') {
            dialog.alert('只能上传一个文件');
            return;
        }
        if (type == 'F_EXCEED_SIZE') {
            dialog.alert('上传文件过大');
            return;
        }
        if (type == 'Q_TYPE_DENIED') {
            dialog.alert('上传文件类型不匹配');
            return;
        }
    });
    
    if (url) {
        updateUploadFileInfo(UPLOADED, url);
    }
}

// 移动、增删元素相关的函数
function upChoiceQuestionOption(upMark) {
    if ($(upMark).closest('tr').prev().prev().length > 0) {
        $(upMark).closest('tr').prev().before($(upMark).closest('tr'));
    }
}
function downChoiceQuestionOption(downMark) {
    if ($(downMark).closest('tr').next().length > 0) {
        $(downMark).closest('tr').next().after($(downMark).closest('tr'));
    }
}
var initialUploadMethodMap = {
        1:initPicUploader,
        2:initAudioUploader,
        3:initVideoUploader
};
function addFirstUploader(multimediaTypeControl) {
    $('#displayZone .uploadMultimediaGroup').append($('#hiddenZone .multimediaSampleOptionControls .uploadMultimedia').clone());
    // 导入的数据有可能没添加附件
    if ($(multimediaTypeControl).val()) {
        initialUploadMethodMap[$(multimediaTypeControl).val()]($('#displayZone .uploadMultimediaGroup .uploadBtn'), 'multimediaUrl');
    }
}
function switchMultimediaType(multimediaTypeControl, doNotAddFirstMultimediaUploader) {
    $('#displayZone .multimediaTypeDiv').hide();
    $('#displayZone .multimediaTypeDiv').eq($(multimediaTypeControl).val() - 1).show();
    $('#displayZone .uploadMultimedia').remove();
    $('#displayZone .uploadMultimediaGroup .uploadMultimedia').remove();
    if (!doNotAddFirstMultimediaUploader) {
    	addFirstUploader(multimediaTypeControl);
    }
    checkAndSetIdAttr();
}
function switchMultimediaAnswerType(_this) {
	$(_this).closest('.multimediaAnswerDiv').replaceWith($('#hiddenZone .multimediaSampleOptionControls .multimediaAnswerDiv_' + $(_this).val()).clone());
	makeFirstRadioCheckboxChecked();
	checkAndSetIdAttr();
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
    
    if ($('#displayZone .singleSubQuestionDiv').length == 2) {
    	//组合题  至少要有2个题目
    	$("input[onclick='removeSingleSubQuestionDiv(this)']").hide();
    }
    
    reOrderSingleSubQuestionDiv();
}
function addSingleSubQuestionDiv() {
    $('#displayZone .singleSubQuestionDiv').last().after($('#hiddenZone .singleSubQuestionDiv').last().clone());
    reOrderSingleSubQuestionDiv();
    checkAndSetIdAttr();
    
    if ($('#displayZone .singleSubQuestionDiv').length > 2) {
    	//组合题  至少要有2个题目
    	$("input[onclick='removeSingleSubQuestionDiv(this)']").show();
    }
}

function checkChoiceOptionMaxLimit(closestTag) {
	if ($(closestTag).find('[name=optionContent]').length >= 26) {
		layer.alert('选项个数范围是2~26个!');
		return false;
	}
	return true;
}

function checkChoiceOptionMinLimit(closestTag) {
    if ($(closestTag).find('[name=optionContent]').length <= 2) {
        layer.alert('选项个数范围是2~26个!');
        return false;
    }
    return true;
}

function singleChoiceAddOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMaxLimit(closestTag)) {
    	return false;
    }
    $(closestTag).find('[name=optionContent]').last().closest('tr').after($('#hiddenZone .singleChoiceSample [name=optionContent]').last().closest('tr').clone());
    checkAndSetIdAttr();
}

function multiChoiceAddOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMaxLimit(closestTag)) {
        return false;
    }
    $(closestTag).find('[name=optionContent]').last().closest('tr').after($('#hiddenZone .multiChoiceSample [name=optionContent]').last().closest('tr').clone());
    checkAndSetIdAttr();
}

function fillBlankAddOption(_this) {
    var closestTag = getClosestTag(_this);
    $(closestTag).find('[name=optionContent]').last().closest('div').after($('#hiddenZone .fillBlankSample [name=optionContent]').last().closest('div').clone());
    checkAndSetIdAttr();
}

function multimediaSingleChoiceAddOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMaxLimit(closestTag)) {
        return false;
    }
    $(closestTag).find('[name=optionContent]').last().closest('tr').after($('#hiddenZone .multimediaSampleOptionControls .multimediaSingleChoiceAnswer [name=optionContent]').last().closest('tr').clone());
    checkAndSetIdAttr();
}

function multimediaMultiChoiceAddOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMaxLimit(closestTag)) {
        return false;
    }
    $(closestTag).find('[name=optionContent]').last().closest('tr').after($('#hiddenZone .multimediaSampleOptionControls .multimediaMultiChoiceAnswer [name=optionContent]').last().closest('tr').clone());
    checkAndSetIdAttr();
}

function foldOrUnfoldSingleSubQuestion(_this) {
    if (_this.value == '收起') {
        $(_this).closest('.groupSubQuestionControl').next().hide();
        _this.value='展开';
    } else {
        $(_this).closest('.groupSubQuestionControl').next().show();
        _this.value='收起';
    }
}

function removeChoiceQuestionOption(_this) {
    var closestTag = getClosestTag(_this);
    if (!checkChoiceOptionMinLimit(closestTag)) {
    	return false;
    }
    $(_this).closest('tr').remove();
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
function removeFileUploader(_this) {
    var closestTag = getClosestTag(_this);
    if ($(closestTag).find('[name=multimediaUrl]').length > 1) {
        $(_this).closest('div').remove();
    }
}

function removeFillBlankQuestionOption(_this) {
    var closestTag = getClosestTag(_this);
    if ($(closestTag).find('[name=optionContent]').length > 1) {
        $(_this).closest('div').remove();
    }
}

//多媒体，题干插入图片
function createMultimediaSampleTitlePic(){
	var pagePicUploader = WebUploader.create({
		//fileVal: "uploadFile",
		//自定义参数
		//formData: {fileType:"ad", logoPosition:0},
		// 选完文件后，是否自动上传。
	    auto: true,
	 	// swf文件路径
        swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        server:'<%=request.getContextPath()%>/exam/question/uploadFile.action',	
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
	    	id: '#multimediaSampleTitlePic',
	    	multiple: false
	    },
	    // 压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: true,
	    //单个文件上传大小限制  单位B
	    //fileSingleSizeLimit:5000,
	    // 只允许选择图片文件。
	    accept: {
	        //title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png'
	        //mimeTypes: 'image/*'
	    },
	    compress: {
	    	 width: 2000
	    }
	});
	
	//上传前验证
	pagePicUploader.on('error', function(type){
		//alert(type);
		if(type == "Q_EXCEED_NUM_LIMIT"){
			//$.ligerDialog.warn('只允许上传一个zip包');
		}else if(type == "Q_TYPE_DENIED"){
			$.ligerDialog.warn("只允许上传<span style='color:red;' >gif,jpg,jpeg,bmp,png</span>文件");
		}else if(type == "F_EXCEED_SIZE"){
			//$.ligerDialog.warn("文件最大<span style='color:red;' >10MB</span>");
		}
	});
	
	//当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
	pagePicUploader.on('beforeFileQueued', function(file) {
		//重置
		pagePicUploader.reset();
	});
	
	// 当有文件被添加进队列的时候 监听
	pagePicUploader.on('fileQueued', function(file) {
		//alert(file.width);
	});
	
	// 上传出错
	pagePicUploader.on('uploadError', function( file ) {
		layer.alert('上传失败');
	});
	
	// 某个文件开始上传前触发，一个文件只会触发一次
	pagePicUploader.on('uploadStart', function( file ) {
		layer.msg('正在上传,请稍候...', {icon: 16, time:0});
	});
	
	// 不管成功或者失败，文件上传完成时触发
	pagePicUploader.on('uploadComplete', function( file ) {
		layer.closeAll();
	});
	
	// 当文件上传成功时触发
	pagePicUploader.on('uploadSuccess', function(file, response) {
		//alert(response);

		layer.closeAll();

		if(lastDivRange){
			lastDivRange.insertNode($("<img src='"+response._raw+"' class='question_content_img' />")[0]);
		}else{
			$("#multimediaSampleTitlePicDiv").append("<img src='"+response._raw+"' class='question_content_img' />");
		}
		
		//多媒体，题干变化，插入到textarea中  questionContentId
		multimediaSampleTitlePicDivChange($("#multimediaSampleTitlePicDiv"));
	});
}

var lastDivRange;
function multimediaSampleTitlePicDivChange(obj){
	//多媒体，题干变化，插入到textarea中  questionContentId
	//alert($(obj).html());	
	$("#questionContentId").val($.trim($(obj).html()));
	
	if(window.getSelection()){
		lastDivRange = window.getSelection().getRangeAt(0);
	}else if(document.selection){
		lastDivRange = document.selection.getRangeAt(0);
	}
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
  background: #2c2c2c;
  color: #fff;
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

/* web uploader button begin */
#displayZone .uploadBtn{
  display: inline-block;
  vertical-align: middle;
  margin: 0 12px 0 0;
  width: 70px;
  height: 30px;
  background: #d60500;
  color: #fff;
  font-family: '微软雅黑';
  text-align: center;
  border: none;
  margin-right: 10px;
}
.uploadMultimedia input[type=button] {
    vertical-align: middle;
}
.webuploader-pick {
  line-height: 30px;
  width: 70px;
  padding: inherit;
  border-radius: inherit;
}
/* web uploader button end */

._left_tree{ height:205px;overflow:auto;width:498px;border:1px solid #333333; float:left; font-family:'微软雅黑';}
#selectCategory{margin: 10px 20px 0 36%;}
/* ztree begin */
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:98%;height:30px;padding-top: 0px;border-bottom: 1px dotted #ccc;}
.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
.ztree li a span.button.switch {visibility:visible}
.ztree.showIcon li a span.button.switch {visibility:visible;}
.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}

.ztree li span.button{  background: none;
}
.ztree li span.button.center_close,.ztree li span.button.root_close,.ztree li span.button.roots_close,.ztree li span.button.bottom_close  {
    background: url(<%=request.getContextPath()%>/images/img/ico_zk.png) 0 0 no-repeat;
    vertical-align: middle;
    margin-top: 0px;
}
.ztree li span.button.center_open,.ztree li span.button.root_open,.ztree li span.button.roots_open,.ztree li span.button.bottom_open{
    background: url(<%=request.getContextPath()%>/images/img/ico_sq.png) 0 0 no-repeat;
    vertical-align: middle;
    margin-top: 0px;
}
.ztree li span.button.ico_docu{
    background: url(<%=request.getContextPath()%>/images/img/ico_txt.png) 0 0 no-repeat;
    vertical-align: middle;
    margin-top: 0px;
}

.ztree li span.button.ico_close{
    display: none;
}
.ztree li span.button.ico_open{
    display: none;
}
.ztree li ul.line{
    background: none;
}
/* ztree end */

/* file upload progress dialog begin */
.loading-tit{
    height: 50px;
    line-height: 50px;
    font-size: 24px;
    margin: 5px;
}
.file-name{
    width: 250px;
    overflow: hidden;
    display: inline-flex;;
    text-overflow: ellipsis;
    white-space: nowrap;
}
li.progress{
    line-height: 30px;
    font-size: 15px;
}
/* file upload progress dialog end */
</style>
</head>
<body>
<div class="content" style="width:1066px;margin-top:20px;padding-bottom:10px;">
    <%-- <h3>${title}</h3> --%>
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="redirectToOriginalPage();"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">${title}</span>
	</div>
    <div class="title_1 fl">
        <p>试题基本信息</p>
    </div>
    <div class="lesson_add" style="width:100%;">
	    <form id="questionForm" action="">
	        <div id="basicInfoZone">
	            <div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>所属题库：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="hidden" id="categoryId" name="categoryId" />
	                    <input id="categoryName" type="text" style="width:135px;" readonly="readonly" />
	                    <input type="button" value="选择题库" class="xz" id="chooseCategory" />
	                </div>
	            </div>
	            <div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>题型：</em>
	                </div>
	                <div class="add_fr">
	                    
	                    <select id="questionDisplayType" name="questionDisplayType" onchange="switchQuestionType();">
	                        <option value="SUBJECTIVE" selected="selected">主观题</option>
	                        <option value="SINGLE_CHOICE">单选题</option>
	                        <option value="MULTI_CHOICE">多选题</option>
	                        <option value="DETERMINE">判断题</option>
	                        <option value="FILL_BLANK">填空题</option>
	                        <option value="MULTIMEDIA">多媒体题</option>
	                        <option value="GROUP">组合题</option>
	                    </select>
	                    
	                </div>
	            </div>
	            <div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>难度：</em>
	                </div>
	                 <div class="add_fr">
	                    <select id="difficultyLevel" name="difficultyLevel">
	                        <option value="1">易</option>
	                        <option value="2" selected="selected">中</option>
	                        <option value="3">难</option>
	                    </select>
	                </div>
	            </div>
	            
	             <div class="add_gr" <c:if test="${fromPaper != null}">style="display:none;"</c:if>>
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>可用/禁用：</em>
	                </div>
	                 <div class="add_fr">
	                    <input type="radio" checked="checked" name="isEnabled" value="1" />
	                    <span>可用</span>
	                    <input type="radio"  name="isEnabled" value="0" />
	                    <span>禁用</span>
	                </div>
	            </div>
	            <div class="title_1 fl">
	            <p>试题信息</p>
	            </div>
	        </div>
	        <div id="displayZone">
	        </div>
	    </form>
        <div id="hiddenZone">
            <div class="subjectiveSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                        <!-- <img src="../../img/dg.png"  /> -->
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <span>*</span>
                        <em>正确答案：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="optionContent" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                        <input name="optionIsAnswer" type="hidden" value="1" />
                    </div>
                </div>
                <div class="add_gr">
                    <div class="add_fl">
                        <span>*</span>
                        <em>关键词：</em>
                    </div>
                     <div class="add_fr">
                        （每个空格对应一个关键词，每个关键词用空格隔开，如AA BB CC）
                        <h5><input name="answerKeys" type="text" maxlength="100" /></h5>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <em>试题解析：</em>
                    </div>
                    <div class="add_fr">
                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                    </div>
                </div>
            </div>

            <div class="singleChoiceSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                        <!-- <img src="../../img/dg.png"  /> -->
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <span>*</span>
                        <em>试题选项：</em>
                    </div>
                     <div class="add_fr">
                        <input type="button" value="新增选项" class="xz appendValidateCheckboxResult" style="margin-top:10px;" onclick="singleChoiceAddOption(this)"/>
                        
                    </div>
                </div>
                <div class="fr_tab">
                            <table width="100%">
                                <tr>
                                    <th width="70%" align="center" style="text-align:center;">选项内容</th>
                                    <th width="15%" align="center" style="text-align:center;">正确答案</th>
                                    <th align="center" style="text-align:center;">操作</th>
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                            </table>
                           
                         
                        </div>
                
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <em>试题解析：</em>
                    </div>
                    <div class="add_fr">
                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                    </div>
                </div>
            </div>

            <div class="multiChoiceSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                        <!-- <img src="../../img/dg.png"  /> -->
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <span>*</span>
                        <em>试题选项：</em>
                    </div>
                     <div class="add_fr">
                        <input type="button" value="新增选项" class="xz appendValidateCheckboxResult" style="margin-top:10px;" onclick="multiChoiceAddOption(this)"/>
                        
                    </div>
                </div>
                <div class="fr_tab">
                            <table width="100%">
                                <tr>
                                    <th width="70%" align="center" style="text-align:center;">选项内容</th>
                                    <th width="15%" align="center" style="text-align:center;">正确答案</th>
                                    <th align="center" style="text-align:center;">操作</th>
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                            </table>
                           
                         
                        </div>
                
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <em>试题解析：</em>
                    </div>
                    <div class="add_fr">
                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                    </div>
                </div>
            </div>

            <div class="determineSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <span>*</span>
                        <em>正确答案：</em>
                    </div>
                     <div class="add_fr">
                        <input name="isTrue" type="radio" value="1"/>
                        <span>正确</span>
                        <input name="isTrue" type="radio" value="0"/>
                        <span>错误</span>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <em>试题解析：</em>
                    </div>
                    <div class="add_fr">
                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                    </div>
                </div>
            </div>

            <div class="fillBlankSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;height:inherit">
                    <div class="add_fl">
                        <span>*</span>
                        <em>正确答案：</em>
                    </div>
                     <div class="add_fr">
                        <div>
                            <input type="button" value="新增正确答案" class="xz" style="margin-top:10px;width:100px;" onclick="fillBlankAddOption(this)"/>
                            <span>（点击‘新增正确答案’按钮新增正确答案）</span>
                            <input type="hidden" name="optionContentValidationSummaryResult" value="dummy"></input>
                        </div>
                        <div>
                            <span style="color:red">*</span>
                            <input type="text" name="optionContent" maxlength="100" />
                            <input type="button" value="删除" class="xz" style="margin-top:10px;" onclick="removeFillBlankQuestionOption(this)"/>
                            <input name="optionIsAnswer" type="hidden" value="1" />
                        </div>
                    </div>
                </div>
                <div class="add_gr">
                    <div class="add_fl">
                        <span>*</span>
                        <em>关键词：</em>
                    </div>
                     <div class="add_fr">
                        （每个空格对应一个关键词，每个关键词用空格隔开，如AA BB CC）
                        <h5><input name="answerKeys" type="text" maxlength="30" /></h5>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <em>试题解析：</em>
                    </div>
                    <div class="add_fr">
                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                    </div>
                </div>
            </div>


            <div class="multimediaSampleOptionControls" style="display:none">
                <div class="uploadMultimedia">
                    <span class="uploadBtn">上传文件</span>
                    <span class="uploadFileInfo"><input type="hidden" name="multimediaUrl" style="width:300px;"/></span>
                    <input type="button" value="删除" onclick="removeFileUploader(this)"/>
                </div>

                <div class="multimediaAnswerDiv multimediaSubjectiveAnswer multimediaAnswerDiv_1">
                    <div class="add_gr" style="margin-top:30px;height:inherit;">
                        <div class="add_fl">
                            <span>*</span>
                            <em>正确答案：</em>
                        </div>
                         <div class="add_fr">
                            <div>
                                <select name="answerType" onchange="switchMultimediaAnswerType(this)">
                                    <option value="1" selected="selected">主观题</option>
                                    <option value="2">单选题</option>
                                    <option value="3">多选题</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="multimediaAnswerDiv">
                        <div class="add_gr">
                            <div class="add_fl">
                                <em>&nbsp;</em>
                            </div>
                             <div class="add_fr">
                                <span style="color:red">*</span>
                                <textarea name="optionContent" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                                <input name="optionIsAnswer" type="hidden" value="1" />
                            </div>
                        </div>
                    </div>
                    <div class="add_gr">
                        <div class="add_fl">
                            <span>*</span>
                            <em>关键词：</em>
                        </div>
                         <div class="add_fr">
                            （每个空格对应一个关键词，每个关键词用空格隔开，如AA BB CC）
                            <h5><input name="answerKeys" type="text" maxlength="30" /></h5>
                        </div>
                    </div>
                </div>


                <div class="multimediaAnswerDiv multimediaSingleChoiceAnswer multimediaAnswerDiv_2">
                    <div class="add_gr" style="margin-top:30px;height:inherit;">
                        <div class="add_fl">
                            <span>*</span>
                            <em>正确答案：</em>
                        </div>
                         <div class="add_fr">
                            <div>
                                <select name="answerType" onchange="switchMultimediaAnswerType(this)">
                                    <option value="1">主观题</option>
                                    <option value="2" selected="selected">单选题</option>
                                    <option value="3">多选题</option>
                                </select>
                                <input type="button" value="新增选项" class="xz appendValidateCheckboxResult" style="margin-top:10px;" onclick="multimediaSingleChoiceAddOption(this)"/>
                            </div>
                        </div>
                    </div>
                    <div class="fr_tab">
                            <table width="100%">
                                <tr>
                                    <th width="70%" align="center" style="text-align:center;">选项内容</th>
                                    <th width="15%" align="center" style="text-align:center;">正确答案</th>
                                    <th align="center" style="text-align:center;">操作</th>
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                        <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                        <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                        <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                        <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                        <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                    </td>
                                
                                </tr>
                                <tr>
                                    <td class="td_fr">
                                        <span>*</span>
                                        <input type="text" name="optionContent" maxlength="100" />
                                        <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                    </td>
                                    <td>
                                        <input type="radio" name="optionIsAnswer" />
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


                <div class="multimediaAnswerDiv multimediaMultiChoiceAnswer multimediaAnswerDiv_3">
                    <div class="add_gr" style="margin-top:30px;height:inherit;">
                        <div class="add_fl">
                            <span>*</span>
                            <em>正确答案：</em>
                        </div>
                         <div class="add_fr">
                            <div>
                                <select name="answerType" onchange="switchMultimediaAnswerType(this)">
                                    <option value="1">主观题</option>
                                    <option value="2">单选题</option>
                                    <option value="3" selected="selected">多选题</option>
                                </select>
                                <input type="button" value="新增选项" class="xz appendValidateCheckboxResult" style="margin-top:10px;" onclick="multimediaMultiChoiceAddOption(this)"/>
                            </div>
                        </div>
                    </div>
                    <div class="fr_tab">
                                <table width="100%">
                                    <tr>
                                        <th width="70%" align="center" style="text-align:center;">选项内容</th>
                                    	<th width="15%" align="center" style="text-align:center;">正确答案</th>
                                        <th align="center" style="text-align:center;">操作</th>
                                    </tr>
                                    <tr>
                                        <td class="td_fr">
                                            <span>*</span>
                                            <input type="text" name="optionContent" maxlength="100" />
                                            <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                        </td>
                                        <td>
                                            <input type="checkbox" name="optionIsAnswer" />
                                        </td>
                                        <td>
                                            <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                            <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                            <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                        </td>
                                    
                                    </tr>
                                    <tr>
                                        <td class="td_fr">
                                            <span>*</span>
                                            <input type="text" name="optionContent" maxlength="100" />
                                            <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                        </td>
                                        <td>
                                            <input type="checkbox" name="optionIsAnswer" />
                                        </td>
                                        <td>
                                            <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                            <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                            <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                        </td>
                                    
                                    </tr>
                                    <tr>
                                        <td class="td_fr">
                                            <span>*</span>
                                            <input type="text" name="optionContent" maxlength="100" />
                                            <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                        </td>
                                        <td>
                                            <input type="checkbox" name="optionIsAnswer" />
                                        </td>
                                        <td>
                                            <a href="javascript:void(0);" onclick="upChoiceQuestionOption(this)">↑</a>
                                            <a href="javascript:void(0);" onclick="downChoiceQuestionOption(this)">↓</a>
                                            <a href="javascript:void(0);" onclick="removeChoiceQuestionOption(this)">×</a>
                                        </td>
                                    
                                    </tr>
                                    <tr>
                                        <td class="td_fr">
                                            <span>*</span>
                                            <input type="text" name="optionContent" maxlength="100" />
                                            <input type="checkbox" onclick="switchOptionType(this)" name="optionType" />使用图片
                                        </td>
                                        <td>
                                            <input type="checkbox" name="optionIsAnswer" />
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
            </div>
			
			<!-- 多媒体 -->
            <div class="multimediaSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        
                        <div id="multimediaSampleTitlePicDiv" contentEditable="true" style="overflow:scroll;border:1px solid #A9A9A9;display:inline-block;" class="inputHeight inputWidth" onblur="multimediaSampleTitlePicDivChange(this)" >
                        	
                        </div>
                        <textarea id="questionContentId" name="questionContent" style="overflow:scroll;display:none;" class="inputHeight inputWidth"></textarea>
                        
                        <span id="multimediaSampleTitlePic" style="" >插入图片</span>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;height:inherit;">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>试题附件：</em>
                    </div>
                    <div class="add_fr">
                        <div>
                            <select name="multimediaType" onchange="switchMultimediaType(this)">
                                <option value="1" selected="selected">图片</option>
                                <option value="2">音频</option>
                                <option value="3">视频</option>
                            </select>
                            <span class="multimediaTypeDiv">
                                <input type="button" value="新增附件" onclick="addMultimediaUploader()" />
                                上传格式：<span class="multimediaTypeDescription">jpg、png大小在500K以内，建议尺寸（宽*高，单位是像素）：300*120</span>
                            </span>
                            <span class="multimediaTypeDiv" style="display:none;">
                                上传格式：<span class="multimediaTypeDescription">mp3 大小在8M以内</span>
                            </span>
                            <span class="multimediaTypeDiv" style="display:none;">
                                上传格式：<span class="multimediaTypeDescription">mp4,flv 大小在8M以内</span>
                            </span>
                        </div>
                        <div class="uploadMultimediaGroup">
                        </div>
                    </div>
                </div>
                <div class="multimediaAnswerDiv multimediaSubjectiveAnswer multimediaAnswerDiv_1">
                    <div class="add_gr" style="margin-top:30px;height:inherit;">
                        <div class="add_fl">
                            <span>*</span>
                            <em>正确答案：</em>
                        </div>
                         <div class="add_fr">
                            <div>
                                <select name="answerType" onchange="switchMultimediaAnswerType(this)">
                                    <option value="1" selected="selected">主观题</option>
                                    <option value="2">单选题</option>
                                    <option value="3">多选题</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="multimediaAnswerDiv">
                        <div class="add_gr">
                            <div class="add_fl">
                                <em>&nbsp;</em>
                            </div>
                             <div class="add_fr">
                                <span style="color:red">*</span>
                                <textarea name="optionContent" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                                <input name="optionIsAnswer" type="hidden" value="1" />
                            </div>
                        </div>
                    </div>
                    <div class="add_gr">
                        <div class="add_fl">
                            <span>*</span>
                            <em>关键词：</em>
                        </div>
                         <div class="add_fr">
                            （每个空格对应一个关键词，每个关键词用空格隔开，如AA BB CC）
                            <h5><input name="answerKeys" type="text" maxlength="30" /></h5>
                        </div>
                    </div>
                </div>
                <div class="add_gr" style="margin-top:30px;">
                    <div class="add_fl">
                        <em>试题解析：</em>
                    </div>
                    <div class="add_fr">
                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                    </div>
                </div>
            </div>

            <div class="groupSample" style="display:none">
                <div class="add_gr_1">
                    <div class="add_fl">
                        <span style="color:red;">*</span>
                        <em>题干：</em>
                    </div>
                     <div class="add_fr">
                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                        <!-- <img src="../../img/dg.png"  /> -->
                    </div>
                </div>
                <div>
                    <div class="singleSubQuestionDiv">
                        <div class="groupSubQuestionControl">
                          <div class="subQuestionNoTag">问题<span class="subQuestionNo">1</span></div>
                          <div class="groupSubQuestionControlButtons">
                            <input type="button" value="收起" onclick="foldOrUnfoldSingleSubQuestion(this)" />
                            <input type="button" value="删除" onclick="removeSingleSubQuestionDiv(this)" style="display:none;" />
                          </div>
                        </div>
                        <div class="groupSubQuestion">
			                <div class="add_gr">
			                    <div class="add_fl">
			                        <span>*</span>
			                        <em>题型：</em>
			                    </div>
			                    <div class="add_fr">
			                        <select name="subQuestionDisplayType" onchange="switchSubQuestionType(this);">
			                            <option value="SUBJECTIVE" selected="selected">主观题</option>
			                            <option value="SINGLE_CHOICE">单选题</option>
			                            <option value="MULTI_CHOICE">多选题</option>
			                            <option value="DETERMINE">判断题</option>
			                            <option value="FILL_BLANK">填空题</option>
			                        </select>
			                    </div>
			                </div>
			                <div class="subQuestionEntity">
	                            <div class="add_gr">
	                                <div class="add_fl">
	                                    <span style="color:red;">*</span>
	                                    <em>题干：</em>
	                                </div>
	                                 <div class="add_fr">
	                                    <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
	                                    <!-- <img src="../../img/dg.png"  /> -->
	                                </div>
	                            </div>
	                            <div class="add_gr" style="margin-top:30px;">
	                                <div class="add_fl">
	                                    <span>*</span>
	                                    <em>正确答案：</em>
	                                </div>
	                                 <div class="add_fr">
	                                    <textarea name="optionContent" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
	                                    <input name="optionIsAnswer" type="hidden" value="1" />
	                                </div>
	                            </div>
	                            <div class="add_gr">
	                                <div class="add_fl">
	                                    <span>*</span>
	                                    <em>关键词：</em>
	                                </div>
	                                 <div class="add_fr">
	                                    （每个空格对应一个关键词，每个关键词用空格隔开，如AA BB CC）
	                                    <h5><input name="answerKeys" type="text" maxlength="30" /></h5>
	                                </div>
	                            </div>
	                            <div class="add_gr" style="margin-top:30px;">
	                                <div class="add_fl">
	                                    <em>试题解析：</em>
	                                </div>
	                                <div class="add_fr">
	                                    <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
	                                </div>
	                            </div>
	                        </div>
                        </div>
                    </div>

                    <div class="singleSubQuestionDiv">
                        <div class="groupSubQuestionControl">
                          <div class="subQuestionNoTag">问题<span class="subQuestionNo">2</span></div>
                          <div class="groupSubQuestionControlButtons">
                            <input type="button" value="收起" onclick="foldOrUnfoldSingleSubQuestion(this)" />
                            <input type="button" value="删除" onclick="removeSingleSubQuestionDiv(this)" style="display:none;" />
                          </div>
                        </div>
                        <div class="groupSubQuestion">
                            <div class="add_gr">
                                <div class="add_fl">
                                    <span>*</span>
                                    <em>题型：</em>
                                </div>
                                <div class="add_fr">
                                    <select name="subQuestionDisplayType" onchange="switchSubQuestionType(this);">
                                        <option value="SUBJECTIVE" selected="selected">主观题</option>
                                        <option value="SINGLE_CHOICE">单选题</option>
                                        <option value="MULTI_CHOICE">多选题</option>
                                        <option value="DETERMINE">判断题</option>
                                        <option value="FILL_BLANK">填空题</option>
                                    </select>
                                </div>
                            </div>
                            <div class="subQuestionEntity">
                                <div class="add_gr">
                                    <div class="add_fl">
                                        <span style="color:red;">*</span>
                                        <em>题干：</em>
                                    </div>
                                     <div class="add_fr">
                                        <textarea name="questionContent" style="overflow:scroll;" class="inputHeight inputWidth"></textarea>
                                        <!-- <img src="../../img/dg.png"  /> -->
                                    </div>
                                </div>
                                <div class="add_gr" style="margin-top:30px;">
                                    <div class="add_fl">
                                        <span>*</span>
                                        <em>正确答案：</em>
                                    </div>
                                     <div class="add_fr">
                                        <textarea name="optionContent" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                                        <input name="optionIsAnswer" type="hidden" value="1" />
                                    </div>
                                </div>
                                <div class="add_gr">
                                    <div class="add_fl">
                                        <span>*</span>
                                        <em>关键词：</em>
                                    </div>
                                     <div class="add_fr">
                                        （每个空格对应一个关键词，每个关键词用空格隔开，如AA BB CC）
                                        <h5><input name="answerKeys" type="text" maxlength="30" /></h5>
                                    </div>
                                </div>
                                <div class="add_gr" style="margin-top:30px;">
                                    <div class="add_fl">
                                        <em>试题解析：</em>
                                    </div>
                                    <div class="add_fr">
                                        <textarea name="analysis" style="overflow:scroll; height:70px;" maxlength="2000"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="add_gr_1" class="groupSubQuestionControl">
                        <div class="add_fl">

                        </div>
                        <div class="add_fr">
                            <input type="button" value="新增问题" onclick="addSingleSubQuestionDiv();" style="margin-left:100px;margin-top:5px;" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="button_cz" <c:if test="${fromPaper != null}">style="display:none;"</c:if>>
            <input type="button" value="保存" onclick="saveQuestion()" />
            <input type="button" value="返回" class="back" onclick="redirectToOriginalPage()"/>
        </div>
    </div>

    <div id="categoryTreeContainer" style="display:none;">
        <div class="_left_tree">
	        <ul id="categoryTree" class="ztree">
	        </ul>
        </div>
        <input id="selectCategory" type="button" value="选择" class="ly_bt1" />
        <input id="closeCategoryTree" type="button" value="取消" class="ly_bt2"/>
    </div>
    <ul class="layer_notice" style="display:none;height:150px;width:350px">
        <li class="progress"><label>文件名称:</label><label class="file-name"></label></li>
        <li class="progress">
            <div id="jqMeter_div"></div>
        </li>
    </ul>
</div>

</body>
</html>
