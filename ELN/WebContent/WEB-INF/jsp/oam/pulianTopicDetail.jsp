<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>

<style>
	.ztree li span.button.noline_docu{width:0px;}
	.ztree li span.button.noline_open{position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.page_div {width: 250px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script type="text/javascript">

var topic = ${topic};
var bar = ${bar};

$(function(){
	initChoosenPage();
	initCourse();
	fillInfo();
	fillBarInfo();
});

function fillInfo(){
	$("#no").val(topic.no);
	$("#name").val(topic.name);
	$("#descr").val(topic.descr);
	$("#previewPath").attr("src",topic.coverImage);
	$.each($("input[name='isSpread']"), function(index, val){
		if($(val).val() == topic.isSpread){
			val.checked = true;
		}
	});
	//课程参数
	if(bar.id != ''){
		order = bar.order;
		type = bar.type;
		$("#bar"+bar.type+"_"+order).attr("style","color:red;");
	}
	$("#filePath").val(topic.coverImage);
}


function initCourse(){
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	 params: function(){
    		 var o = {};
    		 o.topicId = topic.id;
 	    	return o;
 	    },
    	url : '<%=request.getContextPath()%>/oam/selectOamCourseByTopic.action',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: false,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课程名称', name: 'name', width:60, align:'center'},
               {title: '课程编号', name: 'code', width:60, align:'center'},
			   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "直播课程";
				   }
				   return "";
			   }},
			   {title: '课程体系', name: 'categoryName', width:60, align:'center'}
           ]
    });
}





function cancel(){
	window.location.href="<%=request.getContextPath()%>/oam/toOamTopicListPage.action";
}

var notCheckSetting = {
		data: {
			key: {url: "xUrl"},
			simpleData: {enable: true, pIdKey: "parentId" }
		},
		check: {enable: false},
		view: {
			showLine: false,
			showIcon: true
		},
		callback: {
			beforeClick: function(treeId, treeNode, clickFlag){return false;}
		}
};
function initChoosenPage(){
	//获取所有权限页面
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			topicId:topic.id
		},
		url:"<%=request.getContextPath()%>/oam/selectOamIndustryByTopic.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#chooseTreePage"), notCheckSetting, data);
		}
	});
	
	$.fn.zTree.getZTreeObj("chooseTreePage").expandAll(true);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function fillBarInfo(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/oam/selectOamBar.action",
		success:function(data){
			fillBarSettingInfo(data);
		}
	});
}

function fillBarSettingInfo(data){
	var bars = $("div[name='bar']");
	$("#bar_"+data.style).show();
	if(data.data !=null){
		$.each(data.data, function(index, val){
			$.each(bars, function(i, bar){
				if($(bar).attr("id") == "bar"+val.order){
					if(val.type == 1){
						$(bar).append("<img style='z-index:-1;width:100%;height:100%' src='"+val.spreadObject.coverImage+"' />");
						$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【专题】</a>");
					}else if(val.type == 2){
						$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%' src='"+val.spreadObject.frontImage+"'/>");
						$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【课程】</a>");
					}else if(val.type == 3){
						$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%' src='"+val.spreadObject.frontImage+"'/>");
						$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【大赛】</a>");
					}
				}
			});
		});
	}
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/oam/toOamTopicListPage.action";
}
</script>


</head>
<body>

<div class="content">
	<!-- <h3>专题信息</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">专题信息</span>
	 </div>
	<div class="lesson_add" >
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                 <em>专题编号：</em>
            </div>
            <div class="add_fr">
            	 ${topic.no }
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                 <em>专题名称：</em>
            </div>
            <div class="add_fr">
            	   ${topic.name }
            </div>
        </div>
       <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
            	<span>*</span>
                   <em>专题封面：</em>
            </div>
             <div class="add_fr">
            	<img id="previewPath" src="" width="278px" height="105px"/>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                    <em>专题说明：</em>
            </div>
            <div class="add_fr">
            	  ${topic.descr }
            </div>
        </div>
          <div class="add_gr" style="height:auto;">
        	<div class="add_fl">
            	<span>*</span>
                <em>专题课程：</em>
            </div>
            <div class="add_fr">
            	<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>是否推广：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" name="isSpread" value ="1" checked="checked"/><span>是</span>
            	<input type="radio" name="isSpread" value ="2"/><span>否</span>
            </div>
        </div>
         <div class="add_gr" style="height:auto;">
        	<div class="add_fl">
            	<span>*</span>
                <em>推广企业类型：</em>
            </div>
            <div class="add_fr">
            	<div class="page_div">
         				<h2>已分配企业类型</h2>
         				<ul id="chooseTreePage" class="ztree" style=""></ul>
         			</div>
            </div>
        </div>
         <div class="add_gr" style="margin-top:20px;">
		        	<div class="add_fl">
	            	<span>*</span>
	                <em>推广位置：</em>
	            </div>
	            <div class="add_fr">
	            &nbsp;
            	</div>
            	</div>
           <div class="add_gr" style="height:auto;margin-top:20px;">
		            	<div class="pic_1" id="bar_1" style="display:none;">
					    	<div class="left_pic fl">
					        	<div class="left_1" id="bar1" name="bar">
					            </div>
					            <div class="left_2" id="bar2" name="bar">
					            </div>
					            <div class="left_3" id="bar3" name="bar">
					            </div>
					        </div>
					        <div class="right_pic fl">
					        	<div class="right_top clear">
					                <div class="right_3" style="margin-left:0; width:544px; background-repeat:repeat-x;" id="bar4" name="bar">
					                </div>
					            <div class="right_bottom">
					            	<div class="right_4" id="bar5" name="bar">
					                </div>
					                <div class="right_5" id="bar6" name="bar">
					                </div>
					                
					            </div>
					        </div>
					    </div>
					    
					      </div> 
					      
					      <div class="pic_1" id="bar_2" style="display:none;">
					    	<div class="left_pic fl">
					        	<div class="left_1" id="bar1" name="bar">
					            </div>
					            <div class="left_2" id="bar2" name="bar">
					            </div>
					            <div class="left_3" id="bar3" name="bar">
					            </div>
					        </div>
					        <div class="right_pic fl">
					        	<div class="right_top clear">
					            	<div class="top_12">
					            		<div class="right_1" id="bar4" name="bar">
					                    </div>
					               	 	<div class="right_2" id="bar5" name="bar">
					                	</div>
					                </div>
					                <div class="right_3" id="bar6" name="bar">
					                </div>
					                	
					            	
					            <div class="right_bottom">
					            	<div class="right_4" id="bar7" name="bar">
					                </div>
					                <div class="right_5" id="bar8" name="bar">
					                </div>
					            </div>
					        </div>
					    </div> 
					    </div>
					    
					    <div class="pic_1" id="bar_3" style="display:none;">
					    	<div class="left_pic fl">
					        	<div class="left_4" id="bar1" name="bar">
					            </div>
					             <div class="left_5" id="bar2" name="bar">
					            </div>
					            <div class="left_6" id="bar3" name="bar">
					            </div>
					            <div class="left_2" id="bar4" name="bar">
					            </div>
					            <div class="left_3" id="bar5" name="bar">
					            </div>
					        </div>
					        <div class="right_pic fl">
					        	<div class="right_top clear">
					            	<div class="top_12">
					            		<div class="right_1" id="bar6" name="bar">
					                    </div>
					               	 	<div class="right_2" id="bar7" name="bar">
					                	</div>
					                </div>
					                <div class="right_3" id="bar8" name="bar">
					                </div>
					                	
					            	
					            <div class="right_bottom">
					            	<div class="right_4" id="bar9" name="bar">
					                </div>
					                <div class="right_5" id="bar10" name="bar">
					                </div>
					            </div>
					        </div>
					    </div> 
					    </div>
        	</div>
    </div>
    <div class="button_gr" style=" padding-left:250px;">
                <input type="button" value="返回" class="btn_4" onclick="window.history.back(-1);"/>
            </div>

         
</div>
<script>
</script>
</body>
</html>
