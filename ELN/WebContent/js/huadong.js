/**
 * 课程学习页面滑动特效
 */
(function($) {
	$.fn.huadong = function(obj, obja, leftDiv, time) {
		// 首先把最外层的标签对象设置为浮动， 上边为0， 右边也为0
		this.height($(window).height()).css({
			'position' : 'absolute',
			'top' : '20px',
			'right' : '0px'
		}); 
		
		//设置按钮样式
		$(obja).parent().css({
			'position' : 'absolute',
			'top' : '240px',
			'right' : '0px'
		});
		
		var right = $(obj).width(); // 取得左边栏的宽度
		var objaWidth = $(obja).width();//
		
		$(obj).height($(window).height()).width(0).hide(); // 将右边栏的高度设置为浏览器的高度，
															// 宽度为0，
															// 并隐藏掉！这样是为了页面载入的时候初始化
		
		var is_on = false;
		
		$(obja).click(function() { // 给触发按钮绑定点击事件，也就是鼠标点击触发按钮后执行的动作
			if(is_on){
				//右边栏效果
				$(obj).animate({
					'width' : '0px'
				}, time, function() {
					$(obj).hide();
				}); // 又把左边栏的宽度设置为0， 并且隐藏
				
				//按钮效果
				$(obja).parent().animate({
					'right' : '0px'
				},time);
				
				//左边栏效果
				$(leftDiv).animate({
					'width' : '96%'
				},time);
				
				is_on = false;
			}else{
				//右边栏效果
				$(obj).show().animate({
					'width' : right + 'px'
				}, time); // 把左边栏的宽度设置为原来的宽度并显示出来， 根据设定的时间慢慢展现
				
				//按钮效果
				$(obja).parent().animate({
					'right' : right + 'px'
				},time);
				
				//左边栏效果
				$(leftDiv).animate({
					'width' : '74%'
				},time);
				
				is_on = true;
			}
		});

	}
})(jQuery);