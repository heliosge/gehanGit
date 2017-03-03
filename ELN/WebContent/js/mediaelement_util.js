var _ckplayer_cur_max_id_ = 1;

function appendMediaelementInitScript(oriStr) {
	var mediaelement_initPlayerScript = '<script type="text/javascript">$("audio").mediaelementplayer({'+
			'features: [\'playpause\',\'progress\',\'current\',\'duration\',\'tracks\',\'volume\']'+
		'});</' + 'script>';
	return oriStr + mediaelement_initPlayerScript;
}

function genAudioTag(url) {
	var mediaHtml = '<audio src="'+url+'" type="audio/mp3" controls="controls"></audio>';
	return appendMediaelementInitScript(mediaHtml);
}

function genVideoInitScript(url, _width, _height, _container_id) {
    var mediaHtml = '';
    mediaHtml += '<script type="text/javascript">\n';
    mediaHtml += '    var flashvars={f:\''+url+'\', p:2, e:1, ht:\'20\'};\n';
    mediaHtml += '    var params={bgcolor:\'#FFF\',allowFullScreen:true,allowScriptAccess:\'always\',wmode:\'transparent\'};\n';
    mediaHtml += '    CKobject.embedSWF(_ckplayer_url_, \''+_container_id+'\', \''+'ckplayer_'+_container_id+'\', \''+_width+'\', \''+_height+'\', flashvars, params);\n';
    mediaHtml += '</' + 'script>\n';
    return mediaHtml;
}

function genVideoTagInner(url, _width, _height, _container_id) {
    var mediaHtml = '';
    mediaHtml += '<div id="'+_container_id+'" class="_VIDEO_DIV_"></div>\n';
    mediaHtml += '<input type="hidden" name="_video_url" value="'+url+'"/>\n';
    mediaHtml += '<input type="hidden" name="_video_width" value="'+_width+'"/>\n';
    mediaHtml += '<input type="hidden" name="_video_height" value="'+_height+'"/>\n';
    mediaHtml += '<input type="hidden" name="_video_container_id" value="'+_container_id+'"/>\n';
    return mediaHtml;
}

// Generate video tag WITH video player init script.
function genVideoTag(url, width, height, container_id) {
	var _width = width ? width : 600;
	var _height = height ? height : 400;
	var _container_id = container_id ? container_id : '_video_random_id_' + _ckplayer_cur_max_id_;
	_ckplayer_cur_max_id_ += 1;
    var mediaHtml = '';
    mediaHtml += genVideoTagInner(url, _width, _height, _container_id);
    mediaHtml += genVideoInitScript(url, _width, _height, _container_id);
    // console.log(mediaHtml);
    return mediaHtml;
}

// Generate video tag WITHOUT video player init script.
function genVideoTagWithoutInitScript(url, width, height, container_id) {
	var _width = width ? width : 600;
	var _height = height ? height : 400;
	var _container_id = container_id ? container_id : '_video_random_id_' + _ckplayer_cur_max_id_;
	_ckplayer_cur_max_id_ += 1;
    var mediaHtml = genVideoTagInner(url, _width, _height, _container_id);
    // console.log(mediaHtml);
    return mediaHtml;
}

function initVideoPlayer(url, width, height, container_id) {
    var flashvars={f:url, p:2, e:1, ht:'20'};
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    CKobject.embedSWF(_ckplayer_url_, container_id, 'ckplayer_'+container_id, width, height, flashvars, params);
}

function showVideoPlayerDialog(dialogTargetId, title, width, height) {
	title = title ? title : '视窗';
	width = width ? width : 600;
	height = height ? height : 400;
	$(dialogTargetId).find('div._VIDEO_DIV_').html('');
    dialog({
        title : title,
        width : width,
        height : height,
        content :$(dialogTargetId),
        onshow: function() {
        	setTimeout(function() {
        		var url = $(dialogTargetId).find('[name=_video_url]').val();
        		var width = $(dialogTargetId).find('[name=_video_width]').val();
        		var height = $(dialogTargetId).find('[name=_video_height]').val();
        		var container_id = $(dialogTargetId).find('[name=_video_container_id]').val();
				initVideoPlayer(url, width, height, container_id);
        	}, 200);
        },
        fixed:true
    }).showModal();
}

/* mediaelement version */
/*
function genVideoTag(url, width, height) {
	var _width = width ? width : 600;
	var _height = height ? height : 400;
	var mediaHtml = '<video width="'+_width+'" height="'+_height+'" controls="controls" src="'+url+'">';
    mediaHtml += '</video>';
	return appendMediaelementInitScript(mediaHtml);
}
*/

//查看大图
function viewBigPic(obj){
	dialog({
		title : "查看大图",
		width : 980,
		height : 520,
		content :'<div style="width:980px;height:520px;overflow:auto;"><img src="'+$(obj).attr("src")+'"/></div>',
		fixed:true
	}).showModal();
}

