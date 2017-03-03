<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增试卷</title>
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

<script type="text/javascript">

//修改时的回填参数
var paperBean = ${paperBean};
var paperBean_questionList = ${paperBean_questionList};
var paperBean_ruleList = ${paperBean_ruleList};

//试题库导入
var loadQuestionDialog;

//试卷库
var zTree = null;
//试题库
var zTree2 = null;
var langType = '${language}' == "en_US" ? "en" : "zh_CN";
var validator = null;

//云试题库
var zTreeYun = null;

function addIconInfo(categoryTree) {
    for (var idx in categoryTree) {
        categoryTree[idx]['icon'] = '<c:url value="/images/img/ico_txt.png" />';
        categoryTree[idx]['iconOpen'] = '<c:url value="/images/img/ico_sq.png" />';
        categoryTree[idx]['iconClose'] = '<c:url value="/images/img/ico_zk.png" />';
    }
}
//
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
	
	//试卷库
    $.ajax({
        type:"POST",
        data:null,
        url:'<c:url value="/exam/paperCategory/list.action"/>',
        success:function(categoryTree){
            //addIconInfo(categoryTree);
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            //全部展开
            zTree.expandAll(true);
        }
    });
    
	//试题库
    $.ajax({
        type:"POST",
        data:null,
        url:'<c:url value="/exam/questionCategory/list.action"/>',
        success:function(categoryTree){
            //addIconInfo(categoryTree);
            zTree2 = $.fn.zTree.init($("#categoryTree2"), setting, categoryTree);
            expandZTree(zTree2, 1);
        }
    });
	
    /* <c:if test="${subCompanyId != 1}">
	  	//云试题库
	    $.ajax({
	        type:"POST",
	        //async:true,  //默认true,异步
	        data:null,
	        url:'<c:url value="/exam/questionCategory/listYun.action"/>',
	        success:function(categoryTree){
	            //addIconInfo(categoryTree);
	            zTreeYun = $.fn.zTree.init($("#categoryTreeYun"), setting, categoryTree);
	            expandZTree(zTreeYun, 1);
	        }
	    });
	</c:if>   */

	//数tab切换
	$(".tree_tab").click(function(){
		
		if($(this).hasClass("tree_tab_selected")){
			
		}else{
			var forUl = $(this).attr("for");
			
			$(".tree_tab_div").hide();
			$(".tree_tab").removeClass("tree_tab_selected");
			$(this).addClass("tree_tab_selected");
			$("#"+forUl).parent().show();
		}
	});	
	
	
	$('#chooseCategory').on('click', function() {
        var index = layer.open({
            title:'选择试卷库',
            type: 1,
            area: ['300px', '300px'],
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
	
	$('#questionForm').validator({
		theme : 'yellow_right',
		//msgStyle:"margin-top: 10px;",
		fields : {
			title : {
				rule : "required;length[~31]",
				msg : {
					required : "试卷名称不可为空",
					length : "试卷名称最多30个字符"
				}
				//msgStyle :"margin-top:0px;"
			},
			"categoryName" : {
				rule : "required",
				msg : {
					required : "试卷库不可为空"
				},
				msgStyle :"left:80px;"
			},
			description : {
				rule : "required;length[~201]",
				msg : {
					required : "试卷描述不可为空",
					length : "试卷描述最多200个字符"
				}
				//msgStyle :"margin-top:0px;"
			}
		}
	});
	
	//试题库导入
	var btuType = 0;
	$('.loadQuestionBtu').on('click', function() {
		
		//1：试题库导入  2:新增试题
		btuType = $(this).attr("btuType");
	    var dialogTitle = "试题库导入";
	    var dialogArea = ['900px', '450px'];
	    
	    $('#loadQuestion_div div').show();
	    
	
		if(btuType == 1){
			$('#loadQuestion_div #loadQuestionIframe').height(360);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/exam/paper/pageLoadQuestion.action");
		}else if(btuType == 2){
			$('#loadQuestion_div #loadQuestionIframe').height(360);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/exam/question/toAdd.action?fromPaper=1");
			
			dialogTitle = "新增试题";
			dialogArea = ['950px', '450px'];
		}else if(btuType == 3){
			$('#loadQuestion_div #loadQuestionIframe').height(300);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/exam/paper/paperFileInput.action");
			
			dialogTitle = "导入试题";
			dialogArea = ['500px', '340px'];
			
			$('#loadQuestion_div div').hide();
		}else if(btuType == 4){
			//下载模板
			window.location.href = "<%=request.getContextPath()%>/file/exam_paper_example.zip";
			return true;
		}else if(btuType == 5){
			//自由组卷
			$('#loadQuestion_div #loadQuestionIframe').height(360);
			$('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/exam/paper/pageLoadQuestionSJ.action");
			
			dialogTitle = "自由组卷";
			dialogArea = ['850px', '450px'];
		}
		
        loadQuestionDialog = layer.open({
            title: dialogTitle,
            type: 1,
            area: dialogArea,
            skin: 'layui-layer-rim',
            shadeClose: true, //点击遮罩关闭
            content: $('#loadQuestion_div')
        });
    });
	
	$('#loadQuestion_close').click(function () {
        layer.close(loadQuestionDialog);
    });

    $('#loadQuestion_ok').click(function () {
    	
    	if(btuType == 1){
			//试题库导入
            var questionObj = loadQuestionIframeName.getSelectedQuestion();
            
            var paramStr = "questionIdList=" + questionObj.idList.join("&questionIdList=");
            
            var layerIndex = layer.msg('试题插入中…', {icon:16, time:0});
            
            $.ajax({
                type:"POST",
                async: true,  //默认true,异步
                //contentType:"application/json; charset=utf-8",
                data: paramStr,
                //datatype: "string",
                url:'<%=request.getContextPath()%>/exam/paper/pageLoadQuestionDate.action',
                success:function(data){
                	//alert(JSON.stringify(data));
                	layer.close(layerIndex);
                	if(data){
                		//清除原试题
                		$(".q_m").first().html("");
                		
                		//导入试题
                    	var backStr = showQuestion(data, questionObj.scoreList);
                    	layer.close(loadQuestionDialog);
                    	
                    	//统计 题目 信息
                    	showQuestionInfo();
                    	
                    	if(backStr){
                    		dialog.alert("试题插入成功<BR>" + backStr);
                    	}else{
                    		dialog.alert("试题插入成功");
                    	}
                	}
                }
            });
    		
		}else if(btuType == 2){
			
			var question = loadQuestionIframeName.saveQuestionForPaper();
			
			if(question){
				var layerIndex = layer.msg('试题新建中…', {icon:16, time:0});
				
				$.ajax({
			        type:"POST",
			        contentType:"application/json; charset=utf-8",
			        data:JSON.stringify(question),
			        url:'<c:url value="/exam/question/add.action" />',
			        success:function(data){
			        	// data.rtnData: Question id for new question added.
			        	
			        	layer.close(loadQuestionDialog);
			        	
			        	if (data.rtnResult == 'SUCCESS') {
			                //dialog.alert('保存成功!');
			        		//$.ligerDialog.warn('新建成功');
			        		
			        		var paramStr = "questionIdList=" + data.rtnData;
			        		
			        		$.ajax({
			                    type:"POST",
			                    async: true,  //默认true,异步
			                    //contentType:"application/json; charset=utf-8",
			                    data: paramStr,
			                    //datatype: "string",
			                    url:'<%=request.getContextPath()%>/exam/paper/pageLoadQuestionDate.action',
			                    success:function(data2){
			                    	//alert(JSON.stringify(data));
			                    	
			                    	layer.close(layerIndex);
			                    	//导入试题
			                    	var backStr = showQuestion(data2);
			                    	
			                    	//统计 题目 信息
			                    	showQuestionInfo();
			                    	
			                    	if(backStr){
			                    		dialog.alert("新建试题成功<BR>" + backStr);
			                    	}else{
			                    		dialog.alert("新建试题成功");
			                    	}
			                    }
			                });
			        		
			        	} else {
			        		//dialog.alert('保存失败!');
			        		//$.ligerDialog.warn('新建失败');
			        		layer.close(layerIndex);
			        		dialog.alert("新建失败！");
			        	}
			        }
			    });
			}
		
		}else if(btuType == 5){
			//自由组卷
			var layerIndex = layer.msg('自由组卷中…', {icon:16, time:0});
			
            var questionObj = loadQuestionIframeName.getSelectedQuestion();
			//alert(JSON.stringify(questionObj));
			
			if(questionObj.flag == true){
				//验证通过, 继续
				//alert("验证通过, 继续");
				
				
				if(questionObj.list){
					//导入试题
	            	var backStr = showQuestion(questionObj.list, questionObj.scoreList);
	            	layer.close(loadQuestionDialog);
	            	
	            	layer.close(layerIndex);
	            	
	            	//统计 题目 信息
	            	showQuestionInfo();
	            	
	            	if(backStr){
                		dialog.alert("试题插入成功<BR>" + backStr);
                	}else{
                		dialog.alert("试题插入成功");
                	}
				}
			}
			
			layer.close(layerIndex);
		}
        
    });
    
    //普通试卷的 题型统计
	for(var key in displayTypeStrMap){
		
		$("#question_info tr").first().append('<td style="padding:0px 12px;font-size:12px;white-space:nowrap;" align="right">'+displayTypeStrMap[key]+':<span questionType="'+key+'" style="padding:0px 2px;" >0</span>题</td>');
		
		var oldScore = 1;
		
		$("#question_info tr").last().append('<td style="padding:0px 12px;font-size:12px;" align="right">每题<input questionType="'+key+'" type="text" style="width:22px;margin:0px 5px;height:22px;border:1px solid #CDCDCD;" value="'+oldScore+'"  />分</td>');
	}
    
    if(paperBean){
    	//alert(JSON.stringify(paperBean.paperCategory));
    	
    	$(".paper_title").text("修改试卷");
    	
    	//修改， 回填
    	$("#title").val(paperBean.title);
    	$("#questionForm input[name='organizingMode'][value='"+paperBean.organizingMode+"']").prop("checked", true);
    	$("#description").val(paperBean.description);
		
    	$("#categoryId").val(paperBean.categoryId);
		$("#categoryName").val(paperBean.paperCategory.name);
		
		if(paperBean.isEnabled){
			$("#questionForm input[name='isEnabled'][value='1']").prop("checked", true);
		}else{
			$("#questionForm input[name='isEnabled'][value='0']").prop("checked", true);
		}
		
		if(paperBean.isEnabled){
			$("#questionForm input[name='isEnabled'][value='1']").prop("checked", true);
		}else{
			$("#questionForm input[name='isEnabled'][value='0']").prop("checked", true);
		}
		
		if(paperBean.organizingMode == 2){
			//随机组卷
			
			//切换试卷类型
			paperOrganizingModeChange();
			
			$("#question_info_div_2 tbody tr").remove();
			
			for(var i=0; i<paperBean_ruleList.length; i++){
				
				$("#question_info_div_2 tbody").append($("#question_info_div_2 tfoot").html());
				
				var rule = paperBean_ruleList[i];
				
				var lastTr = $("#question_info_div_2 tbody tr").last();
				
				$(lastTr).find("select[name='questionDisplayType']").val(rule.questionDisplayType);
				$(lastTr).find("input[name='questionCategoryId']").val(rule.questionCategoryId);
				$(lastTr).find("input[class='paperCategoryName']").val(rule.questionCategoryName);
				
				var ruleLevels = rule.rule.split(";");
				for(var j=0; j<ruleLevels.length; j++){
					
					var hardNumScore = ruleLevels[j].split(":");
					
					var hardNumScoreDiv = $(lastTr).find("input[name='rule_level'][value='"+hardNumScore[0]+"']").parent();
					//alert($(hardNumScoreDiv).html());
					
					$(hardNumScoreDiv).find("input[name='rule_num']").val(hardNumScore[1]);
					$(hardNumScoreDiv).find("input[name='rule_score']").val(hardNumScore[2]);
				}
			}
				
			//计算随机试卷的总分
			setTotalByShuiJi();
			
		}else{
			//普通组卷
			
			//导入试题
	    	showQuestion(paperBean_questionList, paperBean.paperQuestionList);
	    	
	    	//统计 题目 信息
	    	showQuestionInfo("old");
		}
    }
	
});

function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
}

function saveBefore(){
	$('#questionForm').isValid(function(v) {
		
		if(v){
			
			savePaper();
		}
	});
}

//保存试卷
function savePaper(){
	//alert("save");
	
	var layerIndex = layer.msg('保存中…', {icon:16, time:0});
	
	var newPaper = {};
	var url = "<%=request.getContextPath()%>/exam/paper/add.action";
	
	if(paperBean){
		//修改
		newPaper.id = paperBean.id;
		url = "<%=request.getContextPath()%>/exam/paper/modify.action";
	}
	
	newPaper.title = $.trim($("#title").val());
	newPaper.organizingMode = $("#questionForm input[name='organizingMode']:checked").val();
	newPaper.description = $.trim($("#description").val());
	newPaper.categoryId = $.trim($("#categoryId").val());
	if($("#questionForm input[name='isEnabled']:checked").val() == "1"){
		newPaper.isEnabled = true;	
	}else{
		newPaper.isEnabled = false;
	}
	newPaper.isDeleted = false;
	
	if(newPaper.organizingMode == 2){
		//随机试卷
		$("#question_info_div_2 tbody .sj_alert").hide();
		
		//验证试题库
		var questionCategoryIdList = $("#question_info_div_2 tbody input[name='questionCategoryId']");
		
		if(questionCategoryIdList && questionCategoryIdList.length > 0){
			for(var i=0; i<questionCategoryIdList.length; i++){
				
				var zhi = $(questionCategoryIdList[i]).val();
				
				if(zhi == null || zhi == ""){
					layer.close(layerIndex);
					dialog.alert("随机试卷的试题库不可为空!");
					return false;
				}
			}
		}else{
			layer.close(layerIndex);
			dialog.alert("试卷不可以没有试题!");
			return false;
		}
		
		var typeAndCategoryCheck = {"check_flag":true};
		//验证 题型与试卷库的 组合不能重复
		var trs = $("#question_info_div_2 tbody tr");
		for(var i=0; i<trs.length; i++){
			
			var this_questionDisplayType = $(trs[i]).find("select[name='questionDisplayType']").val();
			var this_questionCategoryId = $(trs[i]).find("input[name='questionCategoryId']").val();
			
			var typeAndCategory = this_questionDisplayType + "_" + this_questionCategoryId;
			
			if(typeAndCategoryCheck[typeAndCategory]){
				$(trs[i]).find("td").eq(1).find(".n-msg").text("此题型与题库的组合已经重复").parent().parent().show();
				
				typeAndCategoryCheck["check_flag"] = false;
			}else{
				typeAndCategoryCheck[typeAndCategory] = typeAndCategory;
			}
		}

		if(typeAndCategoryCheck["check_flag"] == false){
			layer.close(layerIndex);
			return false;
		}
		
		//数量与分值只能是正整数
		var reg = /^[1-9]\d*|0$/; 
		var numScoreList = $("#question_info_div_2 tbody").find("input[name='rule_num'], input[name='rule_score']");
		
		for(var i=0; i<numScoreList.length; i++){

			if(!reg.test($.trim($(numScoreList[i]).val()))){
				//alert("数量与分值只能填写0或整数!");
				layer.close(layerIndex);
				dialog.alert("数量与分值只能填写0或整数!");
				return false;
			}
		}
		
		newPaper.organizingRuleList = [];
		var numCheckList = [];
		
		//1主观题，2单选题，3多选题，4判断题，5填空题，6组合题，7多媒体题
		var numCheckListMap = {
		        'q_1':'SUBJECTIVE',
		        'q_2':'SINGLE_CHOICE',
		        'q_3':'MULTI_CHOICE',
		        'q_4':'DETERMINE',
		        'q_5':'FILL_BLANK',
		        'q_6':'GROUP',
		        'q_7':'MULTIMEDIA'
		};
		
		$("#question_info_div_2 tbody tr").each(function(index, val){
			
			//数量验证bean
			var numCheckObj = [];
			
			var this_questionDisplayType = $(val).find("select[name='questionDisplayType']").val();
			var this_questionCategoryId = $(val).find("input[name='questionCategoryId']").val();
			var this_rule = [];
			var this_totalScore = 0;
			
			var levelList = $(val).find("td:eq(2) div");
			for(var i=0; i<levelList.length; i++){
				
				var level = $(levelList[i]).find("input[name='rule_level']").val();
				var num = $(levelList[i]).find("input[name='rule_num']").val();
				var score = $(levelList[i]).find("input[name='rule_score']").val();
				
				this_rule.push(level + ":" + num + ":" + score);
				this_totalScore += num*score;
				
				numCheckObj.push({"difficultyLevel":level, "count":num });
			}
			
			newPaper.organizingRuleList.push({"questionDisplayType":this_questionDisplayType, "questionCategoryId":this_questionCategoryId, "rule":this_rule.join(";"), "totalScore":this_totalScore});
			
			numCheckList.push({"displayType":numCheckListMap["q_"+this_questionDisplayType], "categoryId":this_questionCategoryId, "difficultyCountList":numCheckObj});
			//numCheckList["autoQuestionGroupList["+index+"]"] = {"displayType":numCheckListMap["q_"+this_questionDisplayType], "categoryId":this_questionCategoryId, "difficultyCountList":numCheckObj};
		});
		
		//alert(JSON.stringify(numCheckList));
		
		//验证数量 对应题型数量 不能超过 数据库中的数量
		var numCheckFlag = true;
		$.ajax({
	        type:"POST",
	        async:false,  //默认true,异步
	        contentType:"application/json; charset=utf-8",
	        data: JSON.stringify(numCheckList),
	        url: "<%=request.getContextPath()%>/exam/paper/difficultyLevelCountListAll.action",
	        success:function(data){
		    	//alert(JSON.stringify(data));
		    	
		    	for(var i=0; i<data.length; i++){
		    		//一种题型数量数据
		    		var difficultyLevelList = data[i];
		    		
		    		//数据库数据
					var dataMap = {};
					for(var j=0; j<difficultyLevelList.length; j++){
						
						var level = difficultyLevelList[j].difficultyLevel;
						var num = difficultyLevelList[j].count;
						var score = difficultyLevelList[j].score;
						
						dataMap["level_" + level] = num;
					}
		    		
		    		//易:1 中:2 难:3  页面上的数据
		    		var levelList = $("#question_info_div_2 tbody tr").eq(i).find("td:eq(2) div");
					var showText = "";
					var levelMap = {"level_1":"易", "level_2":"中", "level_3":"难"};
					//本题型的检测情况
					var thisCheckNumLevel = true;
					
					for(var j=0; j<levelList.length; j++){
						
						if(showText == ""){
							//var selectEdType = $(levelList[j]).find("select[name='questionDisplayType']").val();
							//showText += "对应题库的 " + $(levelList[j]).find("select[name='questionDisplayType'] option[value='"+selectEdType+"']").text() + " 其中";
							showText = "题目数量超过了对应题库，题型的数量";
						}
						
						var level = parseInt($(levelList[j]).find("input[name='rule_level']").val());
						var num = parseInt($(levelList[j]).find("input[name='rule_num']").val());
						var score = parseInt($(levelList[j]).find("input[name='rule_score']").val());
						
						if(dataMap["level_" + level]){
							//对应题库，对应类型，对应难度，有这样的试题
							
							if(dataMap["level_" + level] < num){
								//数据库中的试题数量 比 用户填写的少
								numCheckFlag = false;
								
								//本题型的检测情况
								thisCheckNumLevel = false;
							}
							
							showText += " " + levelMap["level_" + level] + " 最多：" + dataMap["level_" + level] + "题";
							
						}else{
							
							//数据库数量为0
							if(num > 0){
								//数据库中的试题数量 比 用户填写的少
								numCheckFlag = false;
								
								//本题型的检测情况
								thisCheckNumLevel = false;
							}
							
							showText += " " + levelMap["level_" + level] + " 最多：0题";
						}
					}
					
					if(thisCheckNumLevel == false){
						//数量验证没有通过
						$("#question_info_div_2 tbody tr").eq(i).find("td:eq(2) .sj_alert .n-msg").text(showText).parent().parent().show();
					}else{
						$("#question_info_div_2 tbody tr").eq(i).find("td:eq(2) .sj_alert .n-msg").text("").parent().parent().hide();
					}
		    	}
	       	}
    	});
		
		if(numCheckFlag == false){
			layer.close(layerIndex);
			return false;
		}
		
	}else{
		//普通试卷
		newPaper.paperQuestionList = [];
		
		if($(".q_m div[questionId]").length > 0){
			$(".q_m div[questionId]").each(function(index, val){
				if($(val).attr("questionType") == "GROUP"){
					//组合题
					var score = 0;
					var scoreDetail = [];
					
					$(val).find(".question_no_group_score").each(function(index_group, val_group){
						
						var thisScore = parseInt($(val_group).val());
						
						score += thisScore;
						scoreDetail.push(thisScore);
					});
					
					newPaper.paperQuestionList.push({"questionId":$(val).attr("questionId"), "score":score, "scoreDetail":scoreDetail.join(",") });
					
				}else{
					newPaper.paperQuestionList.push({"questionId":$(val).attr("questionId"), "score":$(val).find(".question_no").next("input").val() });
				}		
			});
		}else{
			layer.close(layerIndex);
			dialog.alert("试卷不可以没有试题!");
			return false;
		}
	}
	
	$.ajax({
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(newPaper),
        url: url,
        success:function(data){
        	
        	layer.close(layerIndex);
            if(data == "SUCCESS"){
            	dialog.alert("保存成功");
            	
            	//返回试卷列表
            	backPaper();
            	
            }else{
            	dialog.alert("保存失败");
            }
        }
    });
}

//返回试卷列表
function backPaper(){
	window.location.href = "<%=request.getContextPath()%>/exam/paper/page.action";
}

//统计 题目 信息 计算总分
function showQuestionInfo(oldType){

	var score = 0;
	
	var typeList = ["SUBJECTIVE", "SINGLE_CHOICE", "MULTI_CHOICE", "DETERMINE", "FILL_BLANK", "GROUP", "MULTIMEDIA"];
	
	for(var i=0; i<typeList.length; i++){
		
		$("#question_info tr").first().find("span[questionType='"+typeList[i]+"']").text($(".q_m").first().find("div[questionType='"+typeList[i]+"']").length);
	}
	
	$(".q_m").first().find("div[questionType]").each(function(index, val){
		//alert($(val).find(".question_no").next("input").val());
		//alert($(val).find(".question_no").next("input").length);
		if($(val).find(".question_no").next("input").length == 1){
			score += parseInt($(val).find(".question_no").next().val());
		}else{
			//组合题
			$(val).find(".question_no_group_score").each(function(index_group, val_group){
				score += parseInt($(val_group).val());
			});
		}
	});
	
	$("#totalScore").val(score);
}


//删除题目
function questionDelete(obj){
	
	var thisDiv = $(obj).parentsUntil("div[questionId]").last().parent();
	
	//重新排序
	$(thisDiv).nextAll().each(function(index, val){
		var no = $(val).find(".question_no").text();
		
		$(val).find(".question_no").text(parseInt(no) - 1);
	});
	
	//删除问题
	$(thisDiv).remove();
	
	//统计 题目 信息
	showQuestionInfo();
}

//上移题目
function questionUp(obj){
	
	var thisDiv = $(obj).parentsUntil("div[questionId]").last().parent();
	
	if($(thisDiv).prev().length > 0){
		
		$(thisDiv).prev().before($(thisDiv));
	}
	
	//重新排序
	$(".q_m div[questionId]").each(function(index, val){
		
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
	$(".q_m div[questionId]").each(function(index, val){
		
		$(val).find(".question_no").text(index + 1);
	});
}

//展示试题
var displayTypeStrMap = {
        'SUBJECTIVE': '主观题',
        'SINGLE_CHOICE': '单选题',
        'MULTI_CHOICE': '多选题',
        'DETERMINE': '判断题',
        'FILL_BLANK': '填空题',
        'GROUP': '组合题',
        'MULTIMEDIA': '多媒体题',
};
var optionName = ["","A","B","C","D","E","F"];
var isOrNo = ["错误","正确"];

function showQuestion(list, oldList){
	
	if(list && list.length > 0){
		
		var prevNo = $(".q_m div[questionId]").length;
		
		var repeatQuestion = [];
		
		for(var i=0; i<list.length; i++){
			
			var html = "";
			var questionBean = list[i];
			var id = questionBean.id;
			
			//判断题目是否已经存在
			if($(".q_m").first().find("div[questionId='"+id+"']").length > 0){
				repeatQuestion.push(questionBean.content);
				
				continue;
			}
			
		    var options = questionBean.options;
		    var type = questionBean.type;
		    var displayType = questionBean.displayType;
		    var isMedia = questionBean.isMultimedia;
		    var mediaType = questionBean.multimediaType;
		    var mediaUrl = questionBean.multimediaUrl;
		    var answerKeys = questionBean.answerKeys;
		    
		    var oldScore = $("#question_info tr").last().find("input[questionType='"+questionBean.questionDisplayType+"']").val();
		    //alert(oldScore);
		    if(oldList && oldList[i] && oldList[i].score){
		    	oldScore = oldList[i].score; 
		    }
		    
		    <%-- html += '<div questionId="'+id+'" questionType="'+questionBean.questionDisplayType+'" style="border-bottom: 1px solid #CCC;margin-bottom:10px;" >';
		    html += '<div><span class="question_no" >' + (prevNo+i+1) + "</span>.  该试题分数" + '<input type="text" style="width:24px;margin:0px 5px;height:24px;" value="'+oldScore+'" onblur="showQuestionInfo()" />';
		    html += '<span >' + questionBean.content + '</span>';
		    html += '<span class="question_png" >';
		    html += '<img title="上移" src="<%=request.getContextPath()%>/images/question_up.png" onclick="questionUp(this)" />';
		    html += '<img title="下移" src="<%=request.getContextPath()%>/images/question_down.png" onclick="questionDown(this)" />';
		    html += '<img title="删除" src="<%=request.getContextPath()%>/images/question_delete.png" onclick="questionDelete(this)" />';
		    html += '</span>';
		    html += '</div>'; --%>
		    
		    html += '<div questionId="'+id+'" questionType="'+questionBean.questionDisplayType+'" style="border-bottom:1px dashed #333;padding-bottom:10px;">';
		    html += '<div>';
		    html += '<span class="question_no">'+(prevNo+i+1)+'</span>. ';
		    
		    if(type != 6){
		    	//不是组合题
		    	html += '该试题分数';
		    	html += '<input class="question_no_score" type="text" style="width:24px;margin:0px 5px;height:24px;" value="'+oldScore+'" onblur="showQuestionInfo()" />';
		    }
		    
		    html += '<span class="question_content" style="font-weight:bold;font-size:14px;word-break:break-all;">'+questionBean.content+'</span>';
		    html += '<span class="question_png" >';
		    html += '<img title="上移" src="<%=request.getContextPath()%>/images/question_up.png" onclick="questionUp(this)" />';
		    html += '<img title="下移" src="<%=request.getContextPath()%>/images/question_down.png" onclick="questionDown(this)" />';
		    html += '<img title="删除" src="<%=request.getContextPath()%>/images/question_delete.png" onclick="questionDelete(this)" style="width:13px;height:16px;margin-left:5px;" />';
		    html += '</span>';
		    html += '</div>';
		    
		    
		    //html += '<p>题型：' + displayTypeStrMap[questionBean.questionDisplayType] + '</p>';
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
		                
		                if(oldList && oldList[i] && oldList[i].score && oldList[i].scoreDetail){
		                	var scoreDetailList = oldList[i].scoreDetail.split(",");

		                	if(scoreDetailList.length == questionBean.subQuestions.length){
		                		oldScore = scoreDetailList[j];
		                	}
		                }
		                
		                html += '<div style="margin-left:70px;">问题'+(j+1)+'：'+setNull(subQuestion.content);
		                html += '<span class="question_no_group" style="margin-left:20px;" ></span>该试题分数';
				    	html += '<input class="question_no_group_score" type="text" style="width:24px;margin:0px 5px;height:24px;" value="'+oldScore+'" onblur="showQuestionInfo()" />';
		                html += '</div>';
		                
		                //题目状态标识 1-已完成 2-未完成 3-已标记 
		                html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,options,questionBean);
		                //html += '<p>试题解析: ' + (questionBean.analysis ? questionBean.analysis : '暂无') + '</p>';
		            }
		        }
		    }else{
		    	
		    	//其它题型
		        html += concatOptions(id,type,isMedia,mediaType,mediaUrl,options,questionBean);
		        //html += '<p>试题解析: ' + (questionBean.analysis ? questionBean.analysis : '暂无') + '</p>';
		    }
		    
		    html += '</div>';
		    
		    //alert(html);
		    
		    $(".q_m").first().append(html);
			
		}
		
		//alert(repeatQuestion.length);
		if(repeatQuestion.length > 0){
			return	"本次选择的题目中 <BR>"+repeatQuestion.join("<BR>")+"<BR>已经存在试卷中，不再插入试卷!";
		}
	}
}

//设置多选题答案
function setUserAnswer(id,type,answer){
    //alert(answer);
    if(type == 2 || type == 4){//单选题//判断题
        $("input[type=radio][name=option_"+id+"][value='"+answer+"']").attr("checked",true);
    }
    if(type == 3){//多选题
        var an = answer.split(",");
        $.each(an,function(i,item){
            $("input[name=option_"+id+"][value='"+item+"']").attr("checked","checked");
        });
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

/*拼接选项 */
function concatOptions(questionId,type,isMedia,mediaType,mediaUrl,options,questionBean){
	
    var optionHtml = '<div class="" style="margin-top:10px;margin-left:90px;">';
    optionHtml += concatMedia(questionId,isMedia,mediaType,mediaUrl);
    
    if(type == 1){//主观题
    	optionHtml += '<textarea style="width:300px;height:100px;" disabled="disabled" ></textarea>';
    }else if(type == 2){//单选题
        for(var i=0;i<options.length;i++){
            var otype = options[i].type;
            if(otype == 1){//文本
                /* if(options[i].isAnswer){
                    optionHtml += '<p><input type="radio" checked="checked" disabled="disabled"/>'
                    +optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
                }else{
                    optionHtml += '<p><input type="radio" disabled="disabled"/>'
                    +optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
                } */
            	optionHtml += '<p style="line-height:30px;display:table-row;" ><input type="radio" disabled="disabled"/><span style="margin-left:5px;vertical-align:middle;">'+optionName[i+1]+'.'+setNull(options[i].content)+'</span></p>';
            }else{
                /* if(options[i].isAnswer){
                    optionHtml += '<p><input type="radio" checked="checked" disabled="disabled"/>'
                    +optionName[i+1]+'.<span><img style="max-width:200px;" src="'+options[i].content+'"/></span></p>';
                }else{
                    optionHtml += '<p><input type="radio" disabled="disabled"/>'
                    +optionName[i+1]+'.<span><img style="max-width:200px;" src="'+options[i].content+'"/></span></p>';
                } */
            	optionHtml += '<p style="line-height:30px;display:table-row;" ><input type="radio" disabled="disabled"/><span style="margin-left:5px;vertical-align:middle;">'+optionName[i+1]+'.<img style="max-width:200px;margin:5px;" src="'+options[i].content+'"/></span></p>';
            }
            
        }
    }else if(type == 3){//多选题
        for(var i=0;i<options.length;i++){
            var otype = options[i].type;
            if(otype == 1){//文本
            	/* if (options[i].isAnswer) {
            		optionHtml += '<p><input type="checkbox" checked="checked" disabled="disabled"/>'
                    +optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
            	} else {
            		optionHtml += '<p><input type="checkbox" disabled="disabled"/>'
                    +optionName[i+1]+'.<span>'+setNull(options[i].content)+'</span></p>';
            	} */
            	optionHtml += '<p style="line-height:30px;display:table-row;" ><input type="checkbox" disabled="disabled" style="vertical-align:middle;" /><span style="margin:5px;vertical-align:middle;">'+optionName[i+1]+"."+setNull(options[i].content)+'</span></p>';
            }else{
                /* if(options[i].isAnswer){
                    optionHtml += '<p><input type="checkbox" checked="checked" disabled="disabled"/>'
                    +optionName[i+1]+'.<span><img style="max-width:200px;" src="'+options[i].content+'"/></span></p>';
                }else{
                    optionHtml += '<p><input type="checkbox" disabled="disabled"/>'
                    +optionName[i+1]+'.<span><img style="max-width:200px;" src="'+options[i].content+'"/></span></p>';
                } */
            	optionHtml += '<p style="line-height:30px;display:table-row;" ><input type="checkbox" disabled="disabled" style="vertical-align:middle;" /><span style="margin:5px;vertical-align:middle;">'+optionName[i+1]+'.<img style="max-width:200px;margin:5px;" src="'+options[i].content+'"/></span></p>';
            }
            
        }
    }else if(type == 4){//判断题
        
    	optionHtml += '<p style="line-height:30px;display:table-row;" ><input type="radio" disabled="disabled"/><span style="margin-left:5px;vertical-align:middle;">A.正确</span></p>';
        optionHtml += '<p style="line-height:30px;display:table-row;" ><input type="radio" disabled="disabled"/><span style="margin-left:5px;vertical-align:middle;">B.错误</span></p>';
        
    }else if(type == 5){//填空题
        for (var i = 0; i < options.length; i++) {
        	optionHtml += '<p style="line-height:30px;" ><input type="text" value="" disabled="disabled"/><span>&nbsp;</span></p>';
        }
    }
    optionHtml += '</div>';
    return optionHtml;
}


/*拼接多媒体题  */
function concatMedia(questionId,isMedia,mediaType,mediaUrl){
    var mediaHtml = '';
    if(isMedia){

        var url = [];
        
        if(mediaUrl){
        	if(typeof(mediaUrl) == "object"){
        		url = mediaUrl;
        	}else if(mediaUrl != ''){
            	
                url = JSON.parse(mediaUrl);
            }
        }
        
        if(mediaType == 1){//图片
            mediaHtml += '<div id="focus_'+questionId+'" class="focus">';
            mediaHtml += '<ul>';
            for(var i=0;i<url.length;i++){
                mediaHtml += '<li><img style="max-width:200px;margin:5px;" src="'+url[i]+'"/></li>';
            }
            mediaHtml += '</ul></div>';
        }else if(mediaType == 2){//音频
            for(var i=0;i<url.length;i++){
                /* mediaHtml += '<div style="">';
                mediaHtml += '<embed src="'+url[i]+'" width="300" height="100"></embed>';
                mediaHtml += '</div>'; */
                
                var initMediaPlayer = '<script type="text/javascript">$("audio,video").mediaelementplayer();</' + 'script>';
                
            	mediaHtml += '<div style="margin-bottom:5px;">';
                //mediaHtml += '<audio src="'+url[i]+'" type="audio/mp3" controls="controls"></audio>';
                mediaHtml += genAudioTag(url[i]);
                mediaHtml += '</div>';
                mediaHtml += initMediaPlayer;
            }
        }else if(mediaType == 3){//视频
        	
        	var initMediaPlayer = '<script type="text/javascript">$("audio,video").mediaelementplayer();</' + 'script>';
        	
        	//mediaHtml += '<a href="javascript:void(0);" id="btn_show" name="'+questionId+'">查看</a>';
            //mediaHtml += '<span id="Content" style="">';
            mediaHtml += '<div style="margin-bottom:5px;">';
            for(var i=0;i<url.length;i++){
                //mediaHtml += '<video width="300" height="200" controls="controls">';
                //mediaHtml += '<source src="'+url[i]+'" type="video/mp4"/>';
                mediaHtml += genVideoTag(url[i], 300, 200);
                //mediaHtml += '</video>';
            }
            mediaHtml += '</div>';
            mediaHtml += initMediaPlayer;
            //mediaHtml += '</span>';
        }
    }
    return mediaHtml;
}

/*判空  */
function setNull(title){
    if(title == null){
        return "暂无";
    }
    return title;
}

//切换试卷类型
function paperOrganizingModeChange(){
	
	var paperOrganizingMode = $("#questionForm input[name='organizingMode']:checked").val();
	
	//普通试卷，有值，上一个情况是普通试卷
	var loadQuestionBtuLength = $("#loadQuestionBtu_div:visible").length;
	
	var oldMode = 1;
	if(loadQuestionBtuLength > 0){
		oldMode = 1;
	}else{
		oldMode = 2;
	}
	
	if(paperOrganizingMode != oldMode && paperOrganizingMode == 1){
		//切到普通试卷
		$("#loadQuestionBtu_div").show();
		$("#question_info_div_2").hide();
		$("#question_info_div_1").show();
	}else if(paperOrganizingMode != oldMode && paperOrganizingMode == 2){
		//切到随机试卷
		$("#loadQuestionBtu_div").hide();
		$("#question_info_div_2").show();
		$("#question_info_div_1").hide();
	}
	
}

//选择试题库
function selectQuestionCategory(obj){
	
	 var index = layer.open({
         title:'选择题库',
         type: 1,
         area: ['300px', '320px'],
         skin: 'layui-layer-rim',
         shadeClose: true, //点击遮罩关闭
         content: $('#categoryTreeContainer2')
     });
	 
	 $("#closeCategoryTree2, #selectCategory2").unbind("click");

     $('#closeCategoryTree2').click(function () {
         layer.close(index);
     });

     $('#selectCategory2').click(function () {
    	 
		var category = null;
    	 
    	 var forUl = $(".tree_tab_selected").attr("for");
    	    
   	    if(forUl == "categoryTree2"){
   	    	//ztree
   	    	if (zTree2 != null) {
   		        category = zTree2.getSelectedNodes();
   	    	}
   	    }else{
   	    	//ztree2
   	    	if (zTreeYun != null) {
   	    		category = zTreeYun.getSelectedNodes();
   	    	}
   	    }
    	 
    	 if (category && category.length>0) {
    		 category = category[0];
    	 }
    	 
         if (!category) {
             dialog.alert("请先选择数据");
             return;
         }else if(category && category.id == null){
         	layer.alert('不可以选择公司节点(根节点)');
        	return;
         }
         
         //alert(category.id + "  " + category.name + "  " + $(obj).parent().html());
         
         $(obj).prev().prev().val(category.id);
         var fullName = getZTreePathName(category);
         $(obj).prev().val(fullName);
         $(obj).prev().attr('title', fullName);
         
         layer.close(index);
     });
}

//增加题型
function addPaperType(){
	$("#question_info_div_2 table tbody").append($("#question_info_div_2 table tfoot").html());
	
	//计算随机试卷的总分
	setTotalByShuiJi();
}

//增加题型 难度
function addPaperTypeLevel(obj){
	
	$(obj).parent().prev().append($("#question_info_div_2 table tfoot td:eq(2)").html());
}

//删除题型
function delPaperType(obj){
	
	$(obj).parent().parent().remove();
	
	//计算随机试卷的总分
	setTotalByShuiJi();
}

//计算随机试卷的总分
function setTotalByShuiJi(){
	
	var totalScore = 0;
	
	$("#question_info_div_2 table tbody tr").each(function(index, val){
		
		var levelTds = $(val).find("td:eq(2) div");
		
		for(var i=0; i<levelTds.length; i++){
			
			var num = $(levelTds[i]).find("input[name='rule_num']").val();
			var score = $(levelTds[i]).find("input[name='rule_score']").val();
			
			//alert(parseInt(num)*parseInt(score));
			
			totalScore += parseInt(num)*parseInt(score);
		}
	});
	
	$("#totalScore").val(totalScore);
}


</script>
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

.ztree li span.button.ico_close{
	display: none;
}
.ztree li span.button.ico_open{
	display: none;
}
.ztree li ul.line{
	background: url("");
	
}
/* ztree end */

.groupSubQuestion {
  float: left;
  border: 5px solid #ccc;
  width: 950px;
}
.groupSubQuestionControl {
    float: left;
    margin-top: 10px;
}
.need_span{
	display: none;
	color: red;
}
.button_cz{
	float: none;
	
}
.question_png{
	
}

.question_png img{
	width: 16px;
	cursor: pointer;
	margin: 0px 2px;
	vertical-align: -3px;
}

#question_info_div_2{

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
}

.lesson_add .add_gr .add_fl{
	width: 110px;
	line-height: 50px;
}
.lesson_add .add_gr{
	height: auto;
	line-height: 50px;
}
ul.ztree{
	height: 200px;
	overflow-y:scroll;overflow-x:auto;
}
.n-arrow{display: none;}

.tree_tab{
	font-size:14px; 
	width:50%; 
	background:#333333; 
	color:#fff; 
	text-align:center; 
	height:30px; 
	line-height:30px; 
	font-weight:normal;
	display:inline-block;
	cursor:pointer;
}

.tree_tab_selected{
	background:#fff; 
	color:#333; 
	cursor:auto;
}

.question_content img{
	margin:2px;width:70px;height:70px;
}
</style>
</head>
<body>
<div class="content" style="margin-top:20px;padding-bottom:10px;">
    <!-- <h3 class="paper_title" style="font-size:1.28em;background-position:left 4px;" >新增试卷</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backPaper();"/> 
		<span  class="paper_title" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增试卷</span>
	</div>
    <div class="title_1 fl">
        <p>试卷基本信息</p>
    </div>
    <div class="lesson_add">
	    <form id="questionForm" action="">
	        <div id="basicInfoZone">
	        	<div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>试卷名称：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="text" style="width:135px;" id="title" name="title" />
	                    <span class="need_span"  >*</span>
	                </div>
	            </div>
	            <div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>试卷库：</em>
	                </div>
	                <div class="add_fr">
	                    <input type="hidden" id="categoryId" name="paperCategory.categoryId" />
	                    <input id="categoryName" name="categoryName" type="text" style="width:135px;" readonly="readonly" />
	                    <input type="button" value="选择试卷库" class="xz" id="chooseCategory" />
	                    <span class="need_span"  >*</span>
	                </div>
	            </div>
	            <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>试卷类型：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" checked="checked" id="organizingMode1" name="organizingMode" value="1" onclick="paperOrganizingModeChange()" />
		                <span>普通试卷</span>
		                <input type="radio" id="organizingMode2" name="organizingMode" value="2" onclick="paperOrganizingModeChange()" />
		                <span>随机试卷</span>
		            </div>
		        </div>
	            
	            <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>可用/禁用：</em>
		            </div>
		             <div class="add_fr">
		            	<input type="radio" checked="checked" id="isEnabled1" name="isEnabled" value="1" />
		                <span>可用</span>
		                <input type="radio" id="isEnabled2" name="isEnabled" value="0"  />
		                <span>禁用</span>
		            </div>
		    	</div>
		    	
		    	<div class="add_gr" style="">
		        	<div class="add_fl">
		        		<span>*</span>
		                <em>试卷描述：</em>
		            </div>
		             <div class="add_fr">
		            	<textarea style="height:70px;width:200px;" id="description" name="description" ></textarea>
		            	<span class="need_span" style="display:inline;"  >*</span>
		                <div id="loadQuestionBtu_div">
			                <input type="button" value="新增试题" class="xz loadQuestionBtu" btuType="2" />
			                <input type="button" value="从试题库导入" class="xz loadQuestionBtu" btuType="1" style="width:90px;" />
			                <input type="button" value="自由组卷" class="xz loadQuestionBtu" btuType="5" />
			                <input type="button" value="导入试题" class="xz loadQuestionBtu" btuType="3" />
			                <input type="button" value="下载模板" class="xz loadQuestionBtu" btuType="4" />
		                </div>
		            </div>
		    	</div>
		    	
		    	<div class="add_gr" style="">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>试卷总分：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="text" style="width:135px;" disabled="disabled" value="0" id="totalScore" name="totalScore" />
		            </div>
		        </div>
		        <div class="add_gr" style="height:auto;margin-bottom:20px;">
		        	<div class="add_fl">
		                <em>试题信息：</em>
		            </div>
		            <div id="question_info_div_1" class="add_fr">
		            	<div style="width:750px;background-color:#DDD;padding:5px;margin-top:10px;border:1px solid #CDCDCD;border-bottom:none;">
		            		<table id="question_info" style="line-height:20px;">
		            			<tr>
		            				<td style="white-space:nowrap;" >题型:</td>
		            				
		            			</tr>
		            			<tr>
		            				<td style="white-space:nowrap;" >分值:</td>
		            				
		            			</tr>
		            		</table>
		            		<div style="color:red;line-height:22px;margin-top:4px;" >注意：此处的分值代表新插入试题的初始默认分值，下面的试题的分数文本框，才是修改分数的地方。</div>
		            	</div>
		            	<div class="q_m" style="overflow:auto;width:752px;height:400px;border:1px solid #CDCDCD;padding:4px;" >
		            		
		            	</div>
		            </div>
		            <div id="question_info_div_2" class="add_fr" style="display:none;"  >
		            	
		            	<div class="button_gr" style="height:30px;line-height:30px;margin:10px 0px;">
            				<input type="button" value="新增题型" onclick="addPaperType();" />
        				</div>	 
		            	
		            	<table style="width:900px;">
		            		<tbody>
		            			<tr>
		            				<td style="width:150px;">
		            					<span>题型：</span>
		            					<select name="questionDisplayType" style="width:90px;" >
					                        <option value="1" selected="selected">主观题</option>
					                        <option value="2">单选题</option>
					                        <option value="3">多选题</option>
					                        <option value="4">判断题</option>
					                        <option value="5">填空题</option>
					                        <!-- <option value="6">组合题</option> -->
					                        <option value="7">多媒体题</option>
					                    </select>	
		            				</td>
		            				<td style="width:294px;" valign="middle">
		            					<span class="msg-box n-right sj_alert" style="margin-left:0px;margin-top:-30px;display:none;">
											<span class="msg-wrap n-error" role="alert">
												<span class="n-arrow"><b>◆</b><i>◆</i></span>
												<span class="n-icon"></span>
												<span class="n-msg"></span>
											</span>
										</span>
										
		            					<span style="" >试题库：</span>
		            					<input type="hidden" name="questionCategoryId" />
	                    				<input type="text" class="paperCategoryName" style="width:135px;" readonly="readonly" />
	                    				<input type="button" value="选择试题库" class="xz" onclick="selectQuestionCategory(this)" />
		            				</td>
		            				<td>
		            					<span class="msg-box n-right sj_alert" style="margin-left:-80px;margin-top:5px;display:none;">
											<span class="msg-wrap n-error" role="alert">
												<!-- <span class="n-arrow"><b>◆</b><i>◆</i></span> -->
												<span class="n-icon"></span>
												<span class="n-msg"></span>
											</span>
										</span>
											
		            					<div>
		            						<span style="">难度：易</span>
						                    <input type="hidden" style="width:40px;" name="rule_level" value="1" />	
						                    <span style="margin-left:10px;">数量：</span>
		            						<input type="text" style="width:40px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
		            						<span style="margin-left:10px;">分值：</span>
		            						<input type="text" style="width:40px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	

		            					</div>
		            					<div>
		            						<span style="">难度：中</span>
						                    <input type="hidden" style="width:40px;" name="rule_level" value="2" />	
						                    <span style="margin-left:10px;">数量：</span>
		            						<input type="text" style="width:40px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
		            						<span style="margin-left:10px;">分值：</span>
		            						<input type="text" style="width:40px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	
		            					</div>
		            					<div>
		            						<span style="">难度：难</span>
						                    <input type="hidden" style="width:40px;" name="rule_level" value="3" />	
						                    <span style="margin-left:10px;">数量：</span>
		            						<input type="text" style="width:40px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />		
		            						<span style="margin-left:10px;">分值：</span>
		            						<input type="text" style="width:40px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />		
		            					</div>
		            				</td>
		            				<td style="width:50px;">
		            					<input type="button" value="删除" class="btn_cx" onclick="delPaperType(this);" />
		            				</td>
		            			</tr>
		            		</tbody>
		            		<tfoot style="display: none;">
		            			<tr>
		            				<td style="width:150px;">
		            					<span>题型：</span>
		            					<select name="questionDisplayType" style="width:90px;" >
					                        <option value="1" selected="selected">主观题</option>
					                        <option value="2">单选题</option>
					                        <option value="3">多选题</option>
					                        <option value="4">判断题</option>
					                        <option value="5">填空题</option>
					                        <!-- <option value="6">组合题</option> -->
					                        <option value="7">多媒体题</option>
					                    </select>	
		            				</td>
		            				<td style="width:294px;" valign="middle">
		            					
		            					<span class="msg-box n-right sj_alert" style="margin-left:0px;margin-top:-30px;display:none;">
											<span class="msg-wrap n-error" role="alert">
												<span class="n-arrow"><b>◆</b><i>◆</i></span>
												<span class="n-icon"></span>
												<span class="n-msg"></span>
											</span>
										</span>
										
		            					<span style="" >试题库：</span>
		            					<input type="hidden" name="questionCategoryId" />
	                    				<input type="text" class="paperCategoryName" style="width:135px;" readonly="readonly" />
	                    				<input type="button" value="选择试题库" class="xz" onclick="selectQuestionCategory(this)" />
		            				</td>
		            				<td>
		            				
		            					<span class="msg-box n-right sj_alert" style="margin-left:-80px;margin-top:5px;display:none;">
											<span class="msg-wrap n-error" role="alert">
												<!-- <span class="n-arrow"><b>◆</b><i>◆</i></span> -->
												<span class="n-icon"></span>
												<span class="n-msg"></span>
											</span>
										</span>
											
		            					<div>
		            						<span style="">难度：易</span>
						                    <input type="hidden" style="width:40px;" name="rule_level" value="1" />	
						                    <span style="margin-left:10px;">数量：</span>
		            						<input type="text" style="width:40px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
		            						<span style="margin-left:10px;">分值：</span>
		            						<input type="text" style="width:40px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	

		            					</div>
		            					<div>
		            						<span style="">难度：中</span>
						                    <input type="hidden" style="width:40px;" name="rule_level" value="2" />	
						                    <span style="margin-left:10px;">数量：</span>
		            						<input type="text" style="width:40px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
		            						<span style="margin-left:10px;">分值：</span>
		            						<input type="text" style="width:40px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	
		            					</div>
		            					<div>
		            						<span style="">难度：难</span>
						                    <input type="hidden" style="width:40px;" name="rule_level" value="3" />	
						                    <span style="margin-left:10px;">数量：</span>
		            						<input type="text" style="width:40px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />		
		            						<span style="margin-left:10px;">分值：</span>
		            						<input type="text" style="width:40px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />		
		            					</div>
		            				</td>
		            				<td style="width:50px;">
		            					<input type="button" value="删除" class="btn_cx" onclick="delPaperType(this);" />
		            				</td>
		            			</tr>
		            		</tfoot>
		            	</table>
		            </div>
		        </div>
				
				<div class="button_cz" style="">
		            <input type="button" value="保存" onclick="saveBefore()" />
		            <input type="button" value="返回" class="back" onclick="backPaper()" />
		        </div>	    	
	
	        </div>
        
	    </form>

        
    </div>

    <div id="categoryTreeContainer" style="display:none;">
        <ul id="categoryTree" class="ztree" style="">
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
        	<input id="loadQuestion_close" type="button" value="取消" style="padding:10px 12px;background:#FFF;color:#333;border:1px solid #C6C6C6;border-radius:3px;font-weight:bold;" />
			<input id="loadQuestion_ok" type="button" value="确认" style="padding:10px 12px;background:#428bca;color:#fff;border:none;border-radius:3px;font-weight:bold;margin-left:10px;" />
        </div>
    </div>
    
    <!-- 试题库树 -->
    <!-- <div id="categoryTreeContainer2" style="display:none;">
        <ul id="categoryTree2" class="ztree" style="">
        </ul>
        <div align="center" style="margin-top:10px;">
        	
        	<input id="closeCategoryTree2" type="button" value="取消" style="padding:10px 12px;background:#FFF;color:#333;border:1px solid #C6C6C6;border-radius:3px;font-weight:bold;" />
			<input id="selectCategory2" type="button" value="选择" style="padding:10px 12px;background:#428bca;color:#fff;border:none;border-radius:3px;font-weight:bold;margin-left:10px;" />
        </div>
    </div> -->
    
    <div id="categoryTreeContainer2" class="" style="width:100%;display:none;" >
   		<%-- <c:if test="${subCompanyId != 1}">
   			<h2 for="categoryTree2" class="tree_tab tree_tab_selected" style="" >试题库</h2>
	   		<h2 for="categoryTreeYun" class="tree_tab" style="margin-left:-4px;" >云试题库</h2>
	        <div class="tree_tab_div">
	        	<ul id="categoryTree2" class="ztree"></ul>
	        </div>
	        <div class="tree_tab_div" style="display:none;">
	        	<ul id="categoryTreeYun" class="ztree"></ul>
	        </div>
   		</c:if> --%>
   		<%-- <c:if test="${subCompanyId == 1}"> --%>
   			<h2 for="categoryTree2" class="tree_tab tree_tab_selected" style="background:#333333;color:#FFF;width:100%;" >试题库</h2>
	        <div class="tree_tab_div">
	        	<ul id="categoryTree2" class="ztree"></ul>
	        </div>
   		<%-- </c:if> --%>
   		<div align="center" style="margin-top:10px;">
        	<input id="closeCategoryTree2" type="button" value="取消" style="padding:10px 12px;background:#FFF;color:#333;border:1px solid #C6C6C6;border-radius:3px;font-weight:bold;" />
			<input id="selectCategory2" type="button" value="选择" style="padding:10px 12px;background:#428bca;color:#fff;border:none;border-radius:3px;font-weight:bold;margin-left:10px;" />
        </div>
   	</div>
    
    <div>
    	<%-- <div style="border-bottom:1px dashed #333;padding-bottom:10px;">
    		<div>
    			<span class="question_no">1</span>
    			. 该试题分数
    			<input type="text" style="width:24px;margin:0px 5px;height:24px;" value="" onblur="showQuestionInfo()" />
    			<span style="font-weight:bold;font-size:14px;">中国是个怎样的国家?</span>
    			<span class="question_png" >
			    	<img title="上移" src="<%=request.getContextPath()%>/images/question_up.png" onclick="questionUp(this)" />
			    	<img title="下移" src="<%=request.getContextPath()%>/images/question_down.png" onclick="questionDown(this)" />
			    	<img title="删除" src="<%=request.getContextPath()%>/images/question_delete.png" onclick="questionDelete(this)" />
			    </span>
    		</div>
    		<div style="margin-top:10px;margin-left:90px;">
    			<textarea style="width:300px;height:100px;" disabled="disabled" ></textarea>
    		</div>
    	</div> --%>
    </div>
    
</div>
</body>
</html>
