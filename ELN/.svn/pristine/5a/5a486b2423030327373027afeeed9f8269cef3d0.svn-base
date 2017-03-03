<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增练习</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">
var zTree = null;
function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
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
            selectedMulti: false,
            showTitle: true,
            addDiyDom: addDiyDom
        }
    };
    $.ajax({
        type:"POST",
        data:null,
        url:'<%=request.getContextPath()%>/exam/questionCategory/list.action',
        success:function(categoryTree){
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            expandZTree(zTree, 1);
        }
    });
	
	 $('#chooseCategory').on('click', function() {
	        dialog({
				title : "选择题库",
				width : 400,
				height : 300,
				content :$('#categoryTreeContainer'),
				/* onshow: function() {
					setTimeout(initPaperGrid, 200);
				}, */
				okValue : '确定',
				fixed:true,
				ok : function() {
					var category = getSelectedZTreeNode();
					 if (!category) {
		            	dialog.alert('请先选择数据！');
		                return false;
		            }
					if (category.id == null) {
		            	dialog.alert('不可以选择公司节点(根节点)！');
		            	return false;
		            }
		            $('#categoryId').val(category.id);
		            $('#categoryName').val(getZTreePathName(category, 'name'));
		            $("#categoryName").attr('title', getZTreePathName(category, 'name'));
					
				},
				cancelValue : '取消',
				cancel : function() {
				}
			}).showModal();
	    });
});

//jquery获取复选框值 
function jqchk(){
	 var chk_value ='';
	$('input[name="difficultyLevel"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//获得单选按钮选中的值
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}

/*生成练习  */
function excuteExercise(){
	$('#questionForm').isValid(function(v) {
		var categoryName = $("#categoryName").val();
		if(!categoryName || categoryName == ""){
			$("#span_categoryName").show();
			v=false;
			document.getElementById("categoryName").focus();
		}else{
			$("#span_categoryName").hide();
		}
		if (!v) {
			//alert("表单验证不通过！");
			return false;
		}else{
			dialog.confirm('确认生成练习吗？', function(){
				var params = new Object;
				params.categoryId = $("#categoryId").val();
				params.categoryName = $("#categoryName").val();
				params.questionDisplayType = $("#questionDisplayType").val();
				params.difficultyLevelStr = jqchk();
				params.displayMode = GetRadioValue("displayMode");
				var keyword="";
				keyword += "categoryId="+$("#categoryId").val();
				keyword += "&categoryName="+$("#categoryName").val();
				keyword += "&questionDisplayType="+$("#questionDisplayType").val();
				var difficultyLevelStr = jqchk();
				if(difficultyLevelStr != ''){
					keyword += "&difficultyLevelStr="+jqchk();
				}
				keyword += "&displayMode="+GetRadioValue("displayMode");
				window.open("<%=request.getContextPath()%>/myExercise/gotoGenerateExercise.action?"+keyword);
			});
		}
});
	
}

/*进入错题集  */
function gotoExerciseWrongList(){
	window.location = "<%=request.getContextPath()%>/myExercise/gotoExerciseWrongList.action";
}

</script>
</head>
<body>
<div class="content" style="padding-bottom:10px;">
    <h3>新增练习</h3>
    <div class="lesson_add_2">
	    <form id="questionForm" action="">
	        <div id="">
	            <div class="add_gr">
	                <div class="add_fl">
	                    <span>*</span>
	                    <em>题库：</em>
	                </div>
	               <div class="add_fr">
	                    <input type="hidden" id="categoryId" name="categoryId" />
	                    <input id="categoryName" type="text" style="width:135px;" readonly="readonly" />
	                    <input type="button" value="选择题库" class="xz" id="chooseCategory" />
	                    <span id="span_categoryName" class="msg-box n-right" for="categoryName" style="display: none;">
							<span class="msg-wrap n-error" role="alert">
								<span class="n-msg">题库不能为空</span>
							</span>
						</span>
	                </div>
	            </div>
	             <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>题型：</em>
		            </div>
		             <div class="add_fr">
		             	<select id="questionDisplayType" name="questionDisplayType">
		             		<option value="SUBJECTIVE" selected="selected">主观题</option>
	                        <option value="SINGLE_CHOICE">单选题</option>
	                        <option value="MULTI_CHOICE">多选题</option>
	                        <option value="DETERMINE">判断题</option>
	                        <option value="FILL_BLANK">填空题</option>
	                        <option value="MULTIMEDIA">多媒体题</option>
	                        <option value="GROUP">组合题</option>
                     	<!-- <option value="0" selected="selected">全部</option> -->
                        <!-- <option value="2">单选题</option>
                        <option value="3">多选题</option>
                        <option value="1">主观题</option>
                        <option value="7">多媒体题</option>
                        <option value="4">判断题</option>
                        <option value="5">填空题</option>
                        <option value="6">组合题</option> -->
                     </select>
		            </div>
		    	</div>
		    	 <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>难度：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="checkbox"  name="difficultyLevel" value="1" checked="checked"/>
		                <span>易</span>
		                <input type="checkbox"  name="difficultyLevel" value="2"/>
		                <span>中</span>
		                <input type="checkbox"  name="difficultyLevel" value="3"/>
		                <span>难</span>
		            </div>
		        </div>
	            <div class="add_gr">
		        	<div class="add_fl">
		            	<span>*</span>
		                <em>显示方式：</em>
		            </div>
		            <div class="add_fr">
		            	<input type="radio" checked="checked" id="displayMode1" name="displayMode" value="1"/>
		                <span>顺序</span>
		                <input type="radio" id="displayMode2" name="displayMode" value="2"/>
		                <span>随机</span>
		            </div>
		        </div>
				<div class="button_cz" style="">
		            <input type="button" value="生成练习" onclick="excuteExercise();" />
		            <input type="button" value="进入错题集" class="back" onclick="gotoExerciseWrongList();" />
		        </div>	    	
	
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
