<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑版本</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- 上传插件 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.css">
<!--引入JS-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader-0.1.5/webuploader.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!-- 标签使用插件 -->
<script src="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tags.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jQuery-Tags-Input-master/src/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/courseTags.css"/>

<style type="text/css">

    .param_table td{
        padding: 2px;
    }
    .param_table .input_0{
        width: 200px;
    }

    .need_span{color:red;font-size:13px;}
    .validation_span{color:red;font-size:13px;}
    .n-icon,.n-arrow{
        margin-left: 0px!important;
        margin-right: 0px!important;
    }
</style>


<script type="text/javascript">

var mobileAppVersion={};
mobileAppVersion= ${mobileAppVersion};

$(function(){



   	if(mobileAppVersion&&mobileAppVersion.id){
   		//修改
   		$("#top_page_name").text("修改软件版本");

   		//回填值
   		$("#id").val(mobileAppVersion.id);
   		$("#name").val(mobileAppVersion.name);
   		$("#code").val(mobileAppVersion.code);
   		$("#descr").val(mobileAppVersion.descr);
        $("#type").val(mobileAppVersion.type);
        $("#path").val(mobileAppVersion.path);
        $("#fileName").val(mobileAppVersion.fileName);
        $("#fileNameDiv").text(mobileAppVersion.fileName);
        $.each($("input[name='isForce']"), function(index, val){
            if($(val).val() == mobileAppVersion.isForce){
                val.checked = true;
            }
        });
    }else{

   	}


	    //上传
   		var uploader = WebUploader
   				.create({
   					auto : true,
   					// swf文件路径
   					swf : '<%=request.getContextPath()%>/js/webuploader-0.1.5/Uploader.swf',

   					// 文件接收服务端。
   					server : '<%=request.getContextPath()%>/mobileAppVersion/upload.action',

   					// 选择文件的按钮。可选。
   					// 内部根据当前运行是创建，可能是input元素，也可能是flash.
   					pick : {
   						id : '#picker',
   						multiple : false
   					},

   					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
   					resize : false,

   					// 只允许选择图片文件。
   					accept : {
   						extensions : 'apk'
   					}
   				});

   		uploader.on('fileQueued', function(file) {
   		});
      uploader.on('error',function(type){
          if (type == 'Q_EXCEED_NUM_LIMIT') {
              dialog.alert('上传文件个数超过限制');
              return;
          }
          if (type == 'F_EXCEED_SIZE') {
              dialog.alert('上传文件过大');
              return;
          }
          if (type == 'Q_TYPE_DENIED') {
              dialog.alert('上传文件类型不匹配或文件大小为空');
              return;
          }
      })
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
    });
    //var $index;
    uploader.on( 'uploadFinished', function( file ,ret ) {
        //上传完成，去掉蒙层
        layer.closeAll('loading');
        uploader.reset();
    });
    uploader.on('uploadStart', function(file) {
        //开始上传，进行蒙层处理
        layer.load(1, {
            shade: [0.5,'#fff'] //0.1透明度的白色背景
        });
    });

   		// 接受文件后，进行赋值操作
   		uploader.on('uploadAccept', function(file, ret) {
            console.log(file)
            console.log(ret)
            $("#path").val(ret._raw);
            $("#fileName").val(file.file.name);
            $("#fileNameDiv").text(file.file.name);
   		});





	//返回按钮
	$("#backBtu").click(function(){
        window.location.href = "<%=request.getContextPath()%>/mobileAppVersion/toManagePage.action";

    });




    	initValidate();


});

/**
 * 表单验证
 */
function initValidate() {
	$('form').validator({
		theme : 'yellow_right',
		rules : {
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ],
			checkname: [/^([\u4E00-\u9FA5]|\w)*[^_]$/,'请输入正确的名称(中文,数字,字母)'],
			checkSameName:function(element,param,field){
				var str;
				$.ajax({
					type:"post",
	                 dataType:"text",
					async:true,  //默认true,异步
					data:{
						id:function(){if(mobileAppVersion){
		                	 return mobileAppVersion.id;
		                 }else
		                	 return 0;},
						name:$.trim($("#name").val())
						},
					url:"<%=request.getContextPath()%>/mobileAppVersion/checkName.action",
					success:function(data){
						var flag = true;
						if(data=="FAIL"){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}    ,
            checkSameCode:function(element,param,field){
                var str;
                $.ajax({
                    type:"post",
                    dataType:"text",
                    async:true,  //默认true,异步
                    data:{
                        id:function(){if(mobileAppVersion){
                            return mobileAppVersion.id;
                        }else
                            return 0;},
                        code:$.trim($("#code").val())
                    },
                    url:"<%=request.getContextPath()%>/mobileAppVersion/checkCode.action",
                    success:function(data){
                        var flag = true;
                        if(data=="FAIL"){
                            flag = false;
                        }
                        str =  flag || "已存在相同版本号";
                    }
                });
                return str;
            }
		},
		msgStyle:"margin-top:10px;",
		fields : {
			name : {
				rule : "required;length[~21];cheakname;checkSameName",
				msg : {
					required : "版本名称不可为空",
					length : "长度需小于等于20个字符"
				}
			},
            code : {
                rule : "required;length[~10];checknum;cheakcode;checkSameCode",
                msg : {
                    required : "版本号不可为空",
                    length : "长度需小于等于10个"
                }
            },
            path : {
                rule : "required",
                msg : {
                    required : "文件不能为空"
                }
            },
			descr : {
				rule : "length[~1001]",
				msg : {
					length : "最多1000个字符"
				}
			}
			/*,
			beginTime:{
				rule : "required",
				msg : {
					required : "开始时间不能为空"
				}
			},
			endTime:{
				rule : "required",
				msg : {
					required : "结束时间不能为空"
				}
			}*/
		}
	});
}




function beforeSave(){
	$("form").submit();

}

//保存
function save(){

	$('form').isValid(function(v) {
		var param = new Object();

		param.name = $.trim($("#name").val());
		param.code = $.trim($("#code").val());
		param.path = $.trim($("#path").val());
		param.descr = $.trim($("#descr").val());
		param.fileName = $.trim($("#fileName").val());
        $.each($("input[name='isForce']"), function(index, val){
            if(val.checked){
                param.isForce = $(val).val();
            }
        });


		if(v){

			var urlStr = "<%=request.getContextPath()%>/mobileAppVersion/insert.action";
			if(mobileAppVersion&&mobileAppVersion.id){
				//修改
				urlStr = "<%=request.getContextPath()%>/mobileAppVersion/update.action";
				param.id = mobileAppVersion.id

			}
			dialog.confirm('确认保存吗？', function(){
					$.ajax({
			    		type:"POST",
			    		async:true,  //默认true,异步
			    		contentType:"application/json;charset=utf-8",
			    		data:JSON.stringify(param),
			    		dataType:"json",
			    		url:urlStr,
			    		success:function(data){
			    			if(data&&data.result&&data.result == "SUCCESS"&&data.id){

                                mobileAppVersion=param;
                                mobileAppVersion.id=data.id;

			    					dialog.alert('保存成功！',function(){
                                        window.location.href = "<%=request.getContextPath()%>/mobileAppVersion/toManagePage.action";

                                    });

			    			}else if(data=="SUCCESS"){


			    					dialog.alert('保存成功！',function(){
                                        window.location.href = "<%=request.getContextPath()%>/mobileAppVersion/toManagePage.action";

                                    });


			    			}else {
			    				dialog.alert('保存失败！');

			    			}

			    	    }
			    	});

			});
		}else{

		}});

}

</script>
</head>
<body style="">
<div class="content">
	<!-- <h3>安排课程</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/>
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">编辑版本</span>
	 </div>

    <form >
	<div class="lesson_add_2" style=" border:none;">
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>版本名称：</em>
            </div>
            <div class="add_fr">
            	<input id="name" type="text" name="name"/>
                <em>最多20个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>

        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>版本号：</em>
            </div>
            <div class="add_fr">
            	<input id=code type="text" name="code" />
                <em>正整数</em>
                <span class="validation_span"></span>

            </div>
        </div>
        <div class="add_gr">
            <div class="add_fl">
                <span>*</span>
                <em>是否强制更新：</em>
            </div>
            <div class="add_fr">
                <input type="radio"  name="isForce" value="1"/>
                <span>是</span>
                <input type="radio"  name="isForce" value="0" checked="checked"/>
                <span>否</span>
            </div>
        </div>
       <div class="add_gr_1" style="margin-bottom:20px;">
        	<div class="add_fl">
                <em>描述：</em>
            </div>
            <div class="add_fr">
            	<textarea id="descr" name="descr"></textarea>
                <em>最多1000个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>
             <div class="add_gr"  style="height:auto;">
        	<div class="add_fl">
        	    <span>*</span>
                <em>版本文件：</em>
            </div>
            <div class="add_fr" style="width: 90px">
            	<div id="uploader" class="wu-example" style="line-height: 17px;">

						<!--用来存放文件信息-->
						<div id="thelist" class="uploader-list"></div>
						<div class="btns">
							<div id="picker">选择文件</div>
						</div>
                </div>
               </div>
                 <div class="add_fl" id="fileNameDiv"></div>
                 <input id="path" name="path" type="hidden">
                 <input id="fileName" name="fileName" type="hidden">
        </div>




    </div>
    </form >
      <div class="button_cz fl" style="margin-top:20px; width:1046px;">
        	<input type="button" name="saveBtn" onclick="save()" value="保存" />
           <input type="button" name="backBtn" id="backBtu" value="取消" class="btn_n" />
    </div>



</div>


</body>
</html>
