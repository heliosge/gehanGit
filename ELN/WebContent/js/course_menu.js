(function($) {
	$.fn.coursemenu = function() {
		var $head = $("#courseType");
		var $ul = $("#proinfo");
		var $lis = $ul.children("li");
		var inter = false;

		// 设置div格式
		var leftHeight = $ul.height();
		$lis.find(".prosmore").css({
			'min-height' : (leftHeight - 30) + 'px'
		});

		/*$head.click(function(e) {
			e.stopPropagation();
			if (!inter) {
				$ul.show();
			} else {
				$ul.hide();
			}
			inter = !inter;
		});*/
		
		$head.hover(function(){
			$ul.show();
		},function(){
			if(inter){
				$ul.hide();
			}else{
				$ul.show();
			}
			inter = false;
		});

		$ul.click(function(event) {
			event.stopPropagation();
		});
		
		$ul.hover(function(){
			inter = true;
			$ul.show();
		},function(){
			$ul.hide();
		});
		
		$('body').click(function() {
			$ul.hide();
		});

		$lis.hover(function() {
			$(this).addClass("prosahover");
			$(this).find(".prosmore").removeClass('hide');
		}, function() {
			if ($(this).hasClass("prosahover")) {
				$(this).removeClass("prosahover");
			}
			$(this).find(".prosmore").addClass('hide');
		});
	}
})(jQuery);