<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专题管理</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script src="<%=request.getContextPath()%>/js/webhr.js"></script>
<script type="text/javascript">



$(function(){
	
	fillInfo();
	
});

function fillInfo(){
	$("#bar_1").hide();
	$("#bar_2").hide();
	$("#bar_3").hide();
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
	if(data.data == null){
		$("#bar_1").show();
		return;
	}
	var bars = $("div[name='bar']");
	$("#bar_"+data.style).show();
	if(data.data !=null){
		$.each(data.data, function(index, val){
			$.each(bars, function(i, bar){
				if($(bar).attr("id") == "bar"+val.order){
					if(val.spreadObject != null){
						if(val.type == 1){
							$(bar).append("<img style='z-index:-1;width:100%;height:100%;cursor: pointer;' src='"+val.spreadObject.coverImage+"' onclick='toTopicDetail("+val.spreadObject.id+")'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【专题】</a>");
						}else if(val.type == 2){
							$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%;cursor: pointer;' src='"+val.spreadObject.frontImage+"' onclick='toCourseDetail("+val.spreadObject.id+")'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【课程】</a>");
						}else if(val.type == 3){
							$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%;cursor: pointer;' src='"+val.spreadObject.coverImageForList+"' onclick='toMegagame("+val.spreadObject.id+")'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【大赛】</a>");
						}
					}
				}
			});
		});
	}
}

function toTopicDetail(id){
	window.parent.location.href = "<%=request.getContextPath()%>/oam/toOamTopicDetail.action?id="+id;
}


function toCourseDetail(id){
	window.parent.location.href = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+id;
	//window.parent.location.href = "<%=request.getContextPath()%>/res/toCourseDetail.action?courseId="+id;
}

/**
 * 跳转查看大赛页面
 */
function toMegagame(id){
	window.parent.location.href = "<%=request.getContextPath()%>/MyMegagame/toMegagameInfo.action?megagameId="+id;
}

var order;
var id;
var artDialog;
function chooseSpread(a){
	order = a;
	artDialog = ({
        url:"<%=request.getContextPath()%>/oam/toChooseSpread.action",
        title:"选择推广资源" ,
        height: 500,
		width: 800
		}).showModal();
}

function updateSpread(a){
	id = a;
	artDialog = ({
        url:"<%=request.getContextPath()%>/oam/toUpdateSpread.action",
        title:"选择推广资源" ,
        height: 500,
		width: 800
		}).showModal();
}


function save(){
	var param = {};
	$.each($("input[name='style']"), function(index, val){
		if(val.checked){
			param.style = $(val).val();
		}
	});
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:param,
		url:"<%=request.getContextPath()%>/oam/updateBarStyle.action",
		success:function(data){
			if(data == 'SUCCESS'){
				dialog.alert("修改成功。");
			};
		}
	});
	
	fillInfo();
}

</script>
<body>


<div style="width: 1066px; margin-top: 0px;padding-bottom: 0px;">
		<div class="lesson_add">
        </div>
		<div class="pic_1" id="bar_1">
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
            	
                <div class="right_3" style="margin-left:0; width:542px; background-repeat:repeat-x;" id="bar4" name="bar">
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

</body>
</html>