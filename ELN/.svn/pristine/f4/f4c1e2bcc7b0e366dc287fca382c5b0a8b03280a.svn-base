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
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script src="<%=request.getContextPath()%>/js/webhr.js"></script>
<script type="text/javascript">

var spreadList;

$(function(){
	
	fillInfo();
	
	$("input[name='style']").click(function(){
		$("#bar_1").hide();
		$("#bar_2").hide();
		$("#bar_3").hide();
		style = $(this).val()
		$("#bar_"+$(this).val()).show();
	})
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
			spreadList = data.data;
			fillBarSettingInfo(data);
		}
	});
}

function fillBarSettingInfo(data){
	
	if(style == undefined){
		if(data.style == null){
			$("#bar_1").show();
			return;
		}else{
			$("#bar_"+data.style).show();
		}
		$.each($("input[name='style']"), function(index, val){
			if($(val).val() == data.style){
				val.checked = true;
			}
		});
	}else{
		$("#bar_"+style).show();
		$.each($("input[name='style']"), function(index, val){
			if($(val).val() == style){
				val.checked = true;
			}
		});
	}
	var bars = $("div[name='bar']");
	if(data.data !=null){
		$.each(data.data, function(index, val){
			$.each(bars, function(i, bar){
				if($(bar).attr("id") == "bar"+val.order){
					if(val.spreadObject != null){
						$(bar).html('');
						$(bar).append("<a href='javascript:void(0);' onclick='updateSpread("+val.id+")'>修改推广</a>");
						if(val.type == 1){
							$(bar).append("<img style='z-index:-1;width:100%;height:100%' src='"+val.spreadObject.coverImage+"' />");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【专题】</a>");
						}else if(val.type == 2){
							$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%' src='"+val.spreadObject.frontImage+"'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【课程】</a>");
						}else if(val.type == 3){
							$(bar).append("<img style='top:0;z-index:-1;width:100%;height:100%' src='"+val.spreadObject.coverImageForList+"'/>");
							$(bar).append("<a style='left:0;top:0;width: 114px;height: 24px;'>"+val.spreadObject.name+"【大赛】</a>");
					}
					}
				}
			});
		});
	}
}
var order;
var id;
var artDialog;
var style;
function chooseSpread(a){
	order = a;
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/oam/toChooseSpread.action",
        title:"选择推广资源" ,
		height: 500,
		width: 800
		}).showModal();
}

function updateSpread(a){
	id = a;
	artDialog = dialog({
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
				dialog.alert("修改成功。",function(){fillInfo();});
			}else if(data == 'NO_SPREAD'){
				dialog.alert("请至少新增一个推广。");
			}else{
				dialog.alert("修改失败。");
			};
		}
	});
}

</script>
<body>


<div class="content">
	<h3>运维栏设置</h3>
		<div class="lesson_add">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>运维风格：</em>
            </div>
            <div class="add_fr">
            	<input type="radio" name="style" checked value="1"/> 6栏
                <input type="radio" name="style" value="2"/> 8栏
                <input type="radio" name="style" value="3"/> 10栏
            </div>
        </div>
        </div>
		<div class="pic_1" id="bar_1">
    	<div class="left_pic fl">
        	<div class="left_1" id="bar1" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(1)">新增推广</a>
            </div>
            <div class="left_2" id="bar2" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(2)">新增推广</a>
            </div>
            <div class="left_3" id="bar3" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(3)">新增推广</a>
            </div>
        </div>
        <div class="right_pic fl">
        	<div class="right_top clear">
            	
                <div class="right_3" style="margin-left:0; width:544px; background-repeat:repeat-x;" id="bar4" name="bar">
                <a href="javascript:void(0);" onclick="chooseSpread(4)">新增推广</a>
                </div>
            <div class="right_bottom">
            	<div class="right_4" id="bar5" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(5)">新增推广</a>
                </div>
                <div class="right_5" id="bar6" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(6)">新增推广</a>
                </div>
                
            </div>
        </div>
    </div>
    
      </div> 
      
      <div class="pic_1" id="bar_2" style="display:none;">
    	<div class="left_pic fl">
        	<div class="left_1" id="bar1" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(1)">新增推广</a>
            </div>
            <div class="left_2" id="bar2" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(2)">新增推广</a>
            </div>
            <div class="left_3" id="bar3" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(3)">新增推广</a>
            </div>
        </div>
        <div class="right_pic fl">
        	<div class="right_top clear">
            	<div class="top_12">
            		<div class="right_1" id="bar4" name="bar">
        				<a href="javascript:void(0);" onclick="chooseSpread(4)">新增推广</a>
                    </div>
               	 	<div class="right_2" id="bar5" name="bar">
        				<a href="javascript:void(0);" onclick="chooseSpread(5)">新增推广</a>
                	</div>
                </div>
                <div class="right_3" id="bar6" name="bar">
        			<a href="javascript:void(0);" onclick="chooseSpread(6)">新增推广</a>
                </div>
                	
            	
            <div class="right_bottom">
            	<div class="right_4" id="bar7" name="bar">
        			<a href="javascript:void(0);" onclick="chooseSpread(7)">新增推广</a>
                </div>
                <div class="right_5" id="bar8" name="bar">
        			<a href="javascript:void(0);" onclick="chooseSpread(8)">新增推广</a>
                </div>
            </div>
        </div>
    </div> 
    </div>
    
    <div class="pic_1" id="bar_3" style="display:none;">
    	<div class="left_pic fl">
        	<div class="left_4" id="bar1" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(1)">新增推广</a>
            </div>
             <div class="left_5" id="bar2" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(2)">新增推广</a>
            </div>
            <div class="left_6" id="bar3" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(3)">新增推广</a>
            </div>
            <div class="left_2" id="bar4" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(4)">新增推广</a>
            </div>
            <div class="left_3" id="bar5" name="bar">
        		<a href="javascript:void(0);" onclick="chooseSpread(5)">新增推广</a>
            </div>
        </div>
        <div class="right_pic fl">
        	<div class="right_top clear">
            	<div class="top_12">
            		<div class="right_1" id="bar6" name="bar">
        				<a href="javascript:void(0);" onclick="chooseSpread(6)">新增推广</a>
                    </div>
               	 	<div class="right_2" id="bar7" name="bar">
        				<a href="javascript:void(0);" onclick="chooseSpread(7)">新增推广</a>
                	</div>
                </div>
                <div class="right_3" id="bar8" name="bar">
        			<a href="javascript:void(0);" onclick="chooseSpread(8)">新增推广</a>
                </div>
                	
            	
            <div class="right_bottom">
            	<div class="right_4" id="bar9" name="bar">
        			<a href="javascript:void(0);" onclick="chooseSpread(9)">新增推广</a>
                </div>
                <div class="right_5" id="bar10" name="bar">
        			<a href="javascript:void(0);" onclick="chooseSpread(10)">新增推广</a>
                </div>
            </div>
        </div>
    </div> 
    </div>
    <div class="button_cz fl" style="margin-top:20px; margin-left:460px;">
      		<input type="button" value="保存" onclick="save()"/>
	</div>
</div>

</body>
</html>