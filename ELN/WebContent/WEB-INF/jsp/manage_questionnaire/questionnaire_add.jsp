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
</style>
<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<c:url value="/js/layer/layer.js"/>"></script>
<jsp:include page="../common/mediaelementInclude.jsp"></jsp:include>
<style type="text/css">
/* ztree begin */
.ztree{padding:3px;}
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

.ztree li span.button{	background: url("") 0 0 no-repeat;}
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
.ztree li span.button.ico_close{display: none;}
.ztree li span.button.ico_open{display: none;}
.ztree li ul.line{background: url("");}
ul.ztree{height: 200px;overflow-y:scroll;overflow-x:auto;}
/* ztree end */

</style>
<script type="text/javascript">
//问卷库树
var zTree = null;
var loadQuestionDialog = 0;
var oriQuestion = null;
var editDivId = null;
var editQNo = null;
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var isOrNo = ["错误","正确"];
var q_index = 1;//题目的序号
var did = 0;//问题divId

//修改时的回填参数
var qBean = ${qBean};
var qBean_questionList = ${qBean_questionList};

function getlay() {  //利用这个方法向子页面传递layer的index
    return loadQuestionDialog;
}

/*重新树中的图片  */
function addIconInfo(categoryTree) {
    for (var idx in categoryTree) {
        categoryTree[idx]['icon'] = '<c:url value="/images/img/ico_txt.png" />';
        categoryTree[idx]['iconOpen'] = '<c:url value="/images/img/ico_sq.png" />';
        categoryTree[idx]['iconClose'] = '<c:url value="/images/img/ico_zk.png" />';
    }
}
$(function(){
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
	
	//问卷库
    $.ajax({
        type:"POST",
        data:null,
        url:'<c:url value="/questionnaireCategory/list.action"/>',
        success:function(categoryTree){
            //addIconInfo(categoryTree);
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            //全部展开
            zTree.expandAll(true);
        }
    });
	
	/*问卷库弹出层  */
	$('#chooseCategory').on('click', function() {
        var index = layer.open({
            title:'选择问卷库',
            type: 1,
            area: ['300px', '300px'],
            skin: 'layui-layer-rim',
            shadeClose: true, //点击遮罩关闭
            content: $('#categoryTreeContainer')
        });
		
        /*问卷库弹出层-取消  */
        $('#closeCategoryTree').click(function () {
            layer.close(index);
        });
		/*问卷库弹出层-确定  */
    	$('#selectCategory').click(function () {
            var category = getSelectedZTreeNode();
            
            if (!category) {
                layer.alert('请先选择数据！');
                return;
            }else if(category && category.id == null){
            	layer.alert('不可以选择公司节点(根节点)');
            	return;
            }
            $('#categoryId').val(category.id);
            var fullName = getZTreePathName(category);
            $("#categoryName").val(fullName);
            $("#categoryName").attr('title', fullName);
            layer.close(index);
        });
    });
	
});
/*初始化表单验证  */
$(function(){
	$('#questionForm').validator({
		theme : 'yellow_right',
		//msgStyle:"margin-top: 10px;",
		fields : {
			name : {
				rule : "required;length[1~30]",
				msg : {
					required : "问卷名称不可为空",
					length : "问卷名称最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			},
			"categoryName" : {
				rule : "required",
				msg : {
					required : "问卷库不可为空"
				},
				msgStyle :"left:80px;"
			},
			description : {
				rule : "required;length[1~200]",
				msg : {
					required : "问卷描述不可为空",
					length : "问卷描述最多200个字符"
				}
				//msgStyle :"margin-top:0px;"
			}
		}
	});
});

$(function(){
	if(qBean){
    	//alert(JSON.stringify(qBean.paperCategory));
    	
    	$(".paper_title").text("修改问卷");
    	
    	//修改， 回填
    	$("#name").val(qBean.name);
    	$("#description").val(qBean.description);
    	$("#categoryId").val(qBean.categoryId);
		$("#categoryName").val(qBean.categoryBean.name);
		
		/* if(qBean.isEnabled){
			$("#questionForm input[name='isEnabled'][value='1']").prop("checked", true);
		}else{
			$("#questionForm input[name='isEnabled'][value='0']").prop("checked", true);
		}
		
		if(qBean.isEnabled){
			$("#questionForm input[name='isEnabled'][value='1']").prop("checked", true);
		}else{
			$("#questionForm input[name='isEnabled'][value='0']").prop("checked", true);
		} */
		
			
		//导入试题
		for(var i=0;i<qBean_questionList.length;i++){
	    	appendQuestion(qBean_questionList[i], null);
		}
    	
    	//统计 题目 信息
    	//showQuestionInfo("old");
    }
});

function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
}

$(function(){
	//试题库导入
	var btuType = 0;
	$('.loadQuestionBtu').on('click', function() {
		//1：试题库导入  2:新增试题
		btuType = $(this).attr("btuType");
	    var dialogTitle = "试题库导入";
	    var dialogArea = ['900px', '480px'];
	    $('#loadQuestion_div div').show();
		<%-- if(btuType == 1){//试题库导入
			$('#loadQuestion_div #loadQuestionIframe').height(389);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/exam/paper/pageLoadQuestion.action");
		}else  --%>
		if(btuType == 2){//新增问题
			editDivId = null;
			editQNo = null;
			$('#loadQuestion_div #loadQuestionIframe').height(389);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/questionnaire/toAdd.action?fromPaper=1");
			dialogTitle = "新增问题";
			dialogArea = ['650px', '480px'];
		}else if(btuType == 3){//导入问题
			$('#loadQuestion_div #loadQuestionIframe').height(300);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/questionnaire/questionnaireFileInput.action");
			dialogTitle = "导入问题";
			dialogArea = ['500px', '240px'];
			$('#loadQuestion_div div').hide();
		}else if(btuType == 4){//下载模板
			window.location.href = "<%=request.getContextPath()%>/file/questionnaire_example.zip";
			return true;
		}
		/*点击弹出层  */
	    loadQuestionDialog = layer.open({
	        title: dialogTitle,
	        type: 1,
	        area: dialogArea,
	        skin: 'layui-layer-rim',
	        shadeClose: true, //点击遮罩关闭
	        content: $('#loadQuestion_div')
	    });
	});
	
	/*关闭弹出层  */
	$('#loadQuestion_close').click(function () {
        layer.close(loadQuestionDialog);
    });
	/*点击弹出层，确定  */
	$('#loadQuestion_ok').click(function () {
    	//if(btuType == 2){//新增问题
			var question = loadQuestionIframeName.saveQuestionForQuestionnaire();
    		//alert(loadQuestionIframeName.getEditDivId());
			//alert(JSON.stringify(question));
			if(question){
				//layerIndex = layer.msg('试题新建中…', {icon:16, time:0});
				appendQuestion(question,editDivId);
				layer.close(loadQuestionDialog);
				//layer.close(layerIndex);
			}
		//}
    });
});

/*导入问题  */
function showQuestion(qBean_questionList){
	for(var i=0;i<qBean_questionList.length;i++){
    	appendQuestion(qBean_questionList[i], null);
	}
}

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
	    html += '<div class="question_div" id="div'+(did++)+'" questionId="'+id+'" questionType="'+type+'" isRequired="'+isRequired+'">';
	    html += '<div>';
	    if(editQNo != null){
	    	 html += '<span class="question_no">'+(editQNo)+'</span>. ';
	    }else{
		    html += '<span class="question_no">'+(q_index++)+'</span>. ';
	    }
	    html += '<span class="question_content">'+content+'</span>';
	    html += '<span class="question_png" >';
	    html += '<img title="上移" src="<%=request.getContextPath()%>/images/question_up.png" onclick="questionUp(this)" />';
	    html += '<img title="下移" src="<%=request.getContextPath()%>/images/question_down.png" onclick="questionDown(this)" />';
	    html += '<img title="编辑" src="<%=request.getContextPath()%>/images/question_edit.png" onclick="questionEdit(this)" />';
	    html += '<img title="删除" src="<%=request.getContextPath()%>/images/question_delete.png" onclick="questionDelete(this)"/>';
	    html += '</span>';
	    html += '</div>';
	    html += concatOptions(id,type,options,questionBean);
	    html += '</div>';
		if(editDivId != null){
			$("div[id="+editDivId+"]").replaceWith(html);
		}else{
			$("#questionnaireDiv").first().append(html);
			//统计问题类型数量
			$("#type"+type).text(parseInt($("#type"+type).text())+1);
		}
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
            optionHtml += '<p><input type="checkbox" disabled="disabled" /><span>'+optionName[i+1]+"."+'</span><span>'+setNull(options[i].content)+'</span></p>';
        }
    }else{//评星题
        optionHtml += '<img src="<%=request.getContextPath()%>/images/star.png" style="width:100px;"/>';
    }
    optionHtml += '</div>';
    return optionHtml;
}


/*编辑题目  */
function questionEdit(obj){
	$('#loadQuestion_div div').show();
	oriQuestion = {};
	var thisDiv = $(obj).parentsUntil("div[questionId]").last().parent();
	editDivId = $(thisDiv).attr("id");
	editQNo = $(thisDiv).find(".question_no").text();
	var options = [];
	oriQuestion.type = $(thisDiv).attr("questionType");
	oriQuestion.isRequired = $(thisDiv).attr("isRequired");
	oriQuestion.content = $(thisDiv).find(".question_content").text();
	if($(thisDiv).attr("questionType") == 1 || $(thisDiv).attr("questionType") == 2){
		$(thisDiv).find("div[id=optionDiv]").find("p").each(function(index2,val2){
			var QuestionnaireQuestionOptionBean = {};
			QuestionnaireQuestionOptionBean.orderNum = (index2+1);
			//选项内容去掉前面的A.B.
			QuestionnaireQuestionOptionBean.content = $(this).find("span").text().substr(2);
			options.push(QuestionnaireQuestionOptionBean);
		});
	}
	oriQuestion.options = options;
	//alert(JSON.stringify(oriQuestion));
	$('#loadQuestion_div #loadQuestionIframe').height(389);
	$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/questionnaire/toAdd.action?fromPaper=2");
	dialogTitle = "修改问题";
	dialogArea = ['650px', '480px'];
	
	/*点击弹出层  */
  	loadQuestionDialog = layer.open({
        title: dialogTitle,
        type: 1,
        area: dialogArea,
        skin: 'layui-layer-rim',
        shadeClose: true, //点击遮罩关闭
        content: $('#loadQuestion_div')
    });
}

//删除题目
function questionDelete(obj){
	var thisDiv = $(obj).parentsUntil("div[questionId]").last().parent();
	var type = thisDiv.attr("questionType");
	//统计问题类型数量
	$("#type"+type).text(parseInt($("#type"+type).text())-1);
	//重新排序
	$(thisDiv).nextAll().each(function(index, val){
		var no = $(val).find(".question_no").text();
		$(val).find(".question_no").text(parseInt(no) - 1);
	});
	//删除问题
	$(thisDiv).remove();
	//题目序号减一
	q_index--;
}

//上移题目
function questionUp(obj){
	var thisDiv = $(obj).parentsUntil("div[questionId]").last().parent();
	if($(thisDiv).prev().length > 0){
		$(thisDiv).prev().before($(thisDiv));
	}
	//重新排序
	$(".q_m2 div[questionId]").each(function(index, val){
		$(val).find(".question_no").text(index + 1);
	});
}

//下移题目
function questionDown(obj){
	var thisDiv = $(obj).parentsUntil("div[questionId]").last().parent();
	if($(thisDiv).next().length > 0){
		$(thisDiv).next().after($(thisDiv));
	}
	//重新排序
	$(".q_m2 div[questionId]").each(function(index, val){
		$(val).find(".question_no").text(index + 1);
	});
}

/*保存  */
function saveBefore(){
	$('#questionForm').isValid(function(v) {
		if(v){
			saveQuestionnaire();
		}
	});
}

/*保存问卷  */
function saveQuestionnaire(){
	var layerIndex = layer.msg('保存中…', {icon:16, time:0});
	var newBean = {};
	var url = "<%=request.getContextPath()%>/questionnaire/add.action";
	if(qBean){
		//修改
		newBean.id = qBean.id;
		url = "<%=request.getContextPath()%>/questionnaire/modify.action";
	}
	newBean.name = $.trim($("#name").val());
	newBean.description = $.trim($("#description").val());
	newBean.categoryId = $.trim($("#categoryId").val());
	newBean.questionList = [];
	if($(".q_m2 div[questionId]").length > 0){
		$(".q_m2 div[questionId]").each(function(index, val){
			//alert($(this).find(".question_no").text());
			var options = [];
			var questionContent = $(this).find(".question_content").text();
			if($(val).attr("questionType") == 1 || $(val).attr("questionType") == 2){
				$(this).find("div[id=optionDiv]").find("p").each(function(index2,val2){
					var QuestionnaireQuestionOptionBean = {};
					QuestionnaireQuestionOptionBean.orderNum = (index2+1);
					QuestionnaireQuestionOptionBean.content = $(this).find("span").text().substr(2);
					options.push(QuestionnaireQuestionOptionBean);
				});
			}
			newBean.questionList.push({
									"content":questionContent,
									"type":$(val).attr("questionType"), 
									"isRequired":$(val).attr("isRequired"),
									"options":options});
		});
		//alert(JSON.stringify(newQuestionnaire));
		$.ajax({
	        type:"POST",
	        contentType:"application/json; charset=utf-8",
	        data: JSON.stringify(newBean),
	        url: url,
	        success:function(data){
	        	layer.close(layerIndex);
	            if(data == "SUCCESS"){
	            	dialog.alert("保存成功",function(){
	            		//返回问卷列表
		            	backList();
	            	});
	            }else{
	            	dialog.alert("保存失败");
	            }
	        }
	    });
	}else{
		layer.close(layerIndex);
		dialog.alert("问卷不可以没有试题!");
		return false;
	}

	
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
    <!-- <h3 class="paper_title" style="font-size:1.28em;background-position:left 4px;" >新增问卷</h3> -->
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
	                    <span>*</span>
	                    <em>问卷名称：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="text" style="width:200px;" id="name" name="name" />
	                    <span class="need_span"  >*</span>
	                </div>
	            </div>
	            <div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>问卷分类：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="hidden" id="categoryId" name="questionnaireCategory.categoryId" />
	                    <input id="categoryName" name="categoryName" type="text" style="width:200px;" readonly="readonly" />
	                    <input type="button" value="选择分类" id="chooseCategory" style="background-color:#f5a743"/>
	                    <span class="need_span"  >*</span>
	                </div>
	            </div>
		    	<div class="add_gr" style="">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>问卷说明：</em>
		            </div>
		             <div class="add_fr">
		            	<textarea style="height:70px;width:250px;" id="description" name="description" ></textarea>
		            	<span class="need_span" style="display:inline;" >*</span>
		            </div>
		    	</div>
	        </div>
	    </form>
    </div>
    <div style="border: 1px solid #ccc;padding-bottom: 5px;border-top: none;">
	    <div id="buttonDiv">
	    	<div id="loadQuestionBtu_div" style="padding-left: 5px;">
	            <input type="button" value="新增问题" class="loadQuestionBtu" btuType="2" />
	            <input type="button" value="导入问题" class="loadQuestionBtu" btuType="3" />
	            <input type="button" value="下载模板" class="loadQuestionBtu" btuType="4" />
	        </div>
	    </div>
		<div class="title_1 fl" style="margin-top:10px;">
	        <p>问卷信息</p>
	    </div>
	    <div>
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
    </div>
    <div class="" style="padding-top: 15px;">
          <input type="button" value="保存" class="buttonClass" style="background-color: #d60500" onclick="saveBefore()" />
          <input type="button" value="返回" class="buttonClass" style="background-color: #0085fe" onclick="backList()" />
      </div>
</div>

<!--问卷库Div  -->
<div id="categoryTreeContainer" style="display:none;">
      <ul id="categoryTree" class="ztree">
      </ul>
      <div align="center" style="margin-top:10px;">
      	<input id="closeCategoryTree" type="button" value="取消" style="padding:10px 12px;background:#FFF;color:#333;border:1px solid #C6C6C6;border-radius:3px;font-weight:bold;" />
	<input id="selectCategory" type="button" value="选择" style="padding:10px 12px;background:#428bca;color:#fff;border:none;border-radius:3px;font-weight:bold;margin-left:10px;" />
      </div>
  </div>
  
   <!-- 试题库导入 loadQuestion -->
    <div id="loadQuestion_div" style="display:none;">
    	<iframe id="loadQuestionIframe" name="loadQuestionIframeName" frameborder="0" style="width:100%;height:400px;overflow: hidden;" src="" ></iframe>
        <div align="center" style="padding-top:10px;">
			<input id="loadQuestion_ok" class="buttonClass" style="background-color: #d60500" type="button" value="确认"  />
        	<input id="loadQuestion_close" class="buttonClass" style="background-color: #0085fe" type="button" value="取消"  />
        </div>
    </div>
</body>
</html>
