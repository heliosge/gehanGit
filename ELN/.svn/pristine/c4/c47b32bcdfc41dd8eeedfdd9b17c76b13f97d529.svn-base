<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>编辑学习计划基本信息</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- validation -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js"></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<style type="text/css">

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}
</style>

<script type="text/javascript">
function addIconInfo(categoryTree) {
    for (var idx in categoryTree) {
        categoryTree[idx]['icon'] = '<c:url value="/images/img/ico_txt.png" />';
        categoryTree[idx]['iconOpen'] = '<c:url value="/images/img/ico_sq.png" />';
        categoryTree[idx]['iconClose'] = '<c:url value="/images/img/ico_zk.png" />';
    }
}

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
            showTitle: false,
            showLine: false,
            dblClickExpand: true,
        },
        // callback: {
        //     onClick: zTreeOnClick
        // }
    };
    $.ajax({
        type:"GET",
        data:null,
        url:'<c:url value="/exam/questionCategory/list.action"/>',
        success:function(categoryTree){
            addIconInfo(categoryTree);
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            //全部展开
            zTree.expandAll(true);
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
                return;
            }
            $('#categoryId').val(category.id);
            $('#categoryName').val(category.name);
            layer.close(index);
        });
    });
   	
	
	//上传
	var uploader = WebUploader.create({
		auto: true,
	    // swf文件路径
	    swf:'<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

	    // 文件接收服务端。
	    server:'<%=request.getContextPath()%>/learnPlan/uploadImg.action',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',

	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,

	    // 只允许选择图片文件。
	 
	});
	
	uploader.on( 'fileQueued', function( file ) {
		iconfile = file;
	    $("#picker").append( '<div id="' + file.id + '" class="teacher-item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<a class="close" href="javascipt:void" onclick="cancelFile(this,'+file.id+')">X</a>'+
	    '</div>' );
	});
	
	
	uploader.on( 'uploadAccept', function( file ,ret ) {
		$("#filePath").val(ret._raw);
	    //$( '#'+file.id ).find('p.state').text('已上传');
	    //上传完成后，进行表单的提交 
	});
	
	
	
	//清除文件信息
	function cancelFile(_this,id){
		$(_this).closest("div.teacher-item").remove();
		//uploader.cancelFile( iconfile );
		$("#filePath").val("");
	}
	
	//返回按钮
	$("#backBtu").click(function(){
		$.ligerDialog.confirm('确认返回吗？', function(yes){ 
			if(yes){
		    	window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
		    }
		});
	});

	
	
	//验证名称(中文，数字，字母)
	$.validator.addMethod(
	    "onlyName",
	    function (value, element, param){
	    	var withOutChinese = /^([\u4E00-\u9FA5]|\w)*[^_]$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入正确的名称"
    );
	
	//开始验证     
	$('form').validate({
		debug:true,
	    /**//* 设置验证规则 */    
	    rules: {     
	    	name: {     
	            required:true,
	            rangelength:[2, 20],
	            //withOutChinese: true,
	             remote:{  
	                 url:"<%=request.getContextPath()%>/learnPlan/checkLearnPlanName.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{name:function(){
	                	 return $.trim($("#name").val());
	                 },id:function(){if(learnPlan){
	                	 return learnPlan.id;
	                 }else
	                	 return null;}},  
	                 dataFilter: function(data, type) {  
	                 	if(data=="SUCCESS"){
	                 	
	                 		return true;
	                 	}else{
	                 		return false;
	                 	}
	                 }
	              }
	           
	        },
	        type: {     
	            required:true,
	            rangelength:[2, 20]
	            //withOutChinese: true,
	           
	        },
	     
	        description: {
	        	maxlength:1000
	        }
	    },      
	    /**//* 设置错误信息 */    
	    messages: {     
	    	name: {         
	        	required:"计划名称不可为空",
	        	rangelength:"计划名称必须在2-20个字符之间",
	        	remote:"计划名称已经存在",
	            onlyName:"请输入正确的计划名称(中文,数字,字母)"
	        },
	        type: {         
	        	required:"计划类别不可为空",
	        	rangelength:"计划类别必须在2-20个字符之间",
	        },
	        description: {     
	            maxlength:"最多1000个字符"
	        }
	    },      
	    /**//* 设置验证触发事件 */
	    onsubmit:true,
	    /**//* 设置错误信息提示DOM */    
	    errorPlacement: function(error, element) {   
	    	$(element[0]).parent().find(".validation_span").text(error.html()).show();  
	    },
	    success:function(error, element){
	    	$(element[0]).parent().find(".validation_span").text("");  
        },
	    submitHandler: function (){
	    	//alert("sunmit");
	    	save();
	    }
	}); 
});

function beforeSave(){
	$("form").submit();
}

//保存
function save(){
	
	var param = new Object();
	
	param.filePath = $.trim($("#name").val());
	param.categoryId = $.trim($("#categoryId").val());
	param.difficultyLevel = $.trim($("#difficultyLevel").val());
	
	
	var urlStr = "<%=request.getContextPath()%>/exam/question/wordImport.action";
	
	$.ligerDialog.confirm('确认保存吗？', function(yes){ 
		if(yes){
			$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		dataType:"json",
	    		url:urlStr,
	    		success:function(data){
	    			if(data&&data.result&&data.result == "SUCCESS"&&data.id){
	    				learPlan.id=data.id;
	    				$.ligerDialog.success('保存成功！', '提示', function(){
	    				});
	    				
	    			}else{
	    				
	    				$.ligerDialog.warn('保存失败！', '提示', function(){
	    				});
	    			}
	    		
			    	
	    	    }
	    	});
	    }
	});
}

</script>
</head>
<body style="">
<div class="content">
	<h3>安排课程</h3>
    <div class="make_sc">
    	<div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png" />
            <span>1</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect1.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png" />
            <span class="span_2">2</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png" />
            <span class="span_2">3</span>
        </div>
        <div class="bt">
        	<span class="bt_1">创建计划基本信息</span>
            <span>新增计划阶段与内容</span>
            <span class="last_bt">选择计划人员</span>
        </div>
        <h5>在这里，将自己想要学或者已经学的课程，创建成可把控进度的计划吧。</h5>
        
       
        
        
    </div>
    <form >
	<div class="lesson_add_2" style=" border:none;">
	   <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>所属题库：</em>
                </div>
                <div class="add_fr">
                    <input type="hidden" id="categoryId" />
                    <input id="categoryName" type="text" style="width:135px;" readonly="readonly" />
                    <input type="button" value="选择题库" class="xz" id="chooseCategory" />
                </div>
       </div>
    	  <div class="add_gr">
                <div class="add_fl">
                    <span>*</span>
                    <em>难度：</em>
                </div>
                 <div class="add_fr">
                    <select id="difficultyLevel">
                        <option value="1">易</option>
                        <option value="2" selected="selected">中</option>
                        <option value="3">难</option>
                    </select>
                </div>
            </div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>课程分类：</em>
            </div>
            <div class="add_fr">
            	<input id="type" type="text" name="type" />
                <em>最多20个字符</em><span class="validation_span"></span>
            </div>
        </div>


           <div class="add_gr" style='height: initial;'>
        	<div class="add_fl">
                <span>*</span>
                <em>选择文件：</em>
            </div>
             <div class="add_fr" style='margin-top: 7px;'>
            	<div id="uploader" class="wu-example">
				    <div id="thelist" class="uploader-list"></div>
				    <div class="btns">
				
				        <div id="picker">选择文件</div>
				    </div>
				</div>
				<img  class='dom-hide' id='userIcon' style='max-width: 150px;max-height: 150px;'>
            </div>
            
            <input id='filePath'name="filePath" class='text-p' type="hidden" >
    	</div>

    </div>
    </form >
      <div class="button_cz fl" style="margin-top:20px; width:1046px;">
        	<input type="button" name="saveBtn" onclick="save()" value="保存" />
            <input type="button" name="backBtn" id="backBtu" value="取消" class="btn_n" />
    </div>


    <div id="categoryTreeContainer" style="display:none; width:500px;">
        <ul id="categoryTree" class="ztree">
        </ul>
        <input id="selectCategory" type="button" value="选择" class="ly_bt1" />
        <input id="closeCategoryTree" type="button" value="取消" class="ly_bt2"/>
    </div>

        
</div>

	
</body>
</html>
