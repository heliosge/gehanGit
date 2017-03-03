KindEditor.plugin('multiimage',function(K){
	var self   = this,name = 'multiimage';
	self.clickToolbar(name,function(){
		layer.open({
			type:2,
			title: '选择图片',
			shade:[0.3,'#393D49'],
			area:['830px','490px'],
			content:'../uploadAction/toUploadImgPage.action'
		});
	});
});