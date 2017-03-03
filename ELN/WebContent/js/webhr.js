 //JavaScript Document

window.onload = function(){
		//var Onav = document.getElementById('nav');
//		var aLi = Onav.getElementsByTagName('li');
//		var oUl = Onav.getElementsByTagName('ul');
//		var that = null;
//		var oTimer = null;
//		var flag = true;
		var nt = !1;
		$(window).bind("scroll",
			function() {
			var st = $(document).scrollTop();//往下滚的高度
			nt = nt ? nt: $(".nav_n").offset().top;
			// document.title=st;
			var sel=$(".nav_n");
			if (nt < st) {
				sel.addClass("nav_fixed");
			} else {
				sel.removeClass("nav_fixed");
			}
		});

		
		$('#nav_c').find('li').hover(
			function(){
				$('#nav_c li').attr('class','');
				$(this).attr('class','li_h');
				$('#nav_c li').find('div').eq($(this).index()).css('display','block');
			},
			function(){
				$('#nav_c li').attr('class','');
				$('#nav_c li').find('div').css('display','none');			
			}
		);
		
		$('#nav_c li').find('div').hover(
			function(){
				$(this).css('display','block');
			},
			function(){
				$(this).css('display','none');
			}
		);
		
		
		$('#Ul_1').find('li').click(function(){
			$('#Ul_1').find('li').removeClass('active');
			$(this).addClass('active');
		});
		$('#Ul_2').find('li').click(function(){
			$('#Ul_2').find('li').removeClass('active');
			$(this).addClass('active');
		});
		$('#Ul_3').find('li').click(function(){
			$('#Ul_3').find('li').removeClass('active');
			$(this).addClass('active');
		});
				//课程中心选择分类
		/*$('#cs_list li').click(function(){
			window.open('course_detail.html');
			});		
		$('#cs_list li').hover(function(){
			$(this).find('div').eq(1).slideDown();
			},function(){
			$(this).find('div').eq(1).slideUp();
				
				});
				//课程作者弹出层下拉显示
		$('#cs_list1 li').hover(function(){
			$(this).attr('class','li_1');
			},function(){
			$(this).attr('class','');
				
				});*/
		$('#log').find('li').click(function(){
			$('#log').find('li').attr('class','');
			$('#log>div').css('display','none');
			$(this).attr('class','change');
			$('#log>div').eq($(this).index()).css('display','block');

		});
			//课程评价、详情、目录tab切换
		$('#notes_list li').click(function(){
			$('#notes_list li').attr('class','');
			$(this).attr('class','now');
		});
			
		$('#map').find('a').eq(0).click(function(){
			$('#map>div').slideToggle();
			$('#map>h5').slideToggle();
			if(flag){
				$(this).attr('class','up');
				flag = false;
			}else{
				$(this).attr('class','down');
				flag = true;
			}
			$('#map>div').css('dislay','none');
			$('#map>h5').css('dislay','none');
		});
		//学习地图收起缩放
		$('#tab #tab_1 li').click(function(){
			$('#tab>div').css('display','none');
			$('#tab>div').eq($(this).index()).css('display','block');
			$('#tab #tab_1 li').attr('class','');
			$(this).attr('class','active_c');
		});
		$('#sc li').click(function(){
			$('#cp_5>div').css('display','none');
			$('#left_ul').css('display','block');
			$('#cp_5>div').eq($(this).index()+1).css('display','block');
			$('#sc li').attr('class','');
			$('#sc li').find('img').remove();
			$(this).attr('class','active_b');
			$(this).append('<img src="images/img/ico_21.png"/>');
		});
		$('#sc_1 li').click(function(){
			$('#cp_6>div').css('display','none');
			$('#left_ul1').css('display','block');
			$('#cp_6>div').eq($(this).index()+1).css('display','block');
			$('#sc_1 li').attr('class','');
			$('#sc_1 li').find('img').remove();
			$(this).attr('class','active_b');
			$(this).append('<img src="images/img/ico_21.png"/>');
		});
		$('#sc_2 li').click(function(){
			$('#cp_7>div').css('display','none');
			$('#left_ul2').css('display','block');
			$('#cp_7>div').eq($(this).index()+1).css('display','block');
			$('#sc_2 li').attr('class','');
			$('#sc_2 li').find('img').remove();
			$(this).attr('class','active_b');
			$(this).append('<img src="images/img/ico_21.png"/>');
		});
			
		$('#cp_7 p a').click(function(){
			$('#cp_7>div').css('display','none');
			$('#zx_de').css('display','block');
		});
		$('#back').click(function(){
			$('#left_ul2').css('display','block');
			$('#zx_1').css('display','block');
			$('#zx_de').css('display','none');
		});
				
					
//	for(i=0;i<aLi.length;i++){
//		aLi[i].index =i;
//		aLi[i].onmouseover =function(){
//			
//			if(oTimer)
//            {
//                clearTimeout(oTimer);
//                oTimer=null;
//            }
//            for(i=0;i<aLi.length;i++)
//            {
//                aLi[i].className='';
//                oUl[i].style.display='none';
//            }
//            
//            aLi[this.index].className='focus';
//            oUl[this.index].style.display='block';
//			}
//			
//		}	 
//		aLi[i].onmouseout=function ()
//        {
//            var index=this.index;
//            oTimer=setTimeout
//            (
//                function ()
//                {
//                    OUl[index].style.display='none';
//                    oTimer=null;
//                },1000
//            );
//        };
//		OUl[i].Index=i;
//        OUl[i].onmouseover=function ()
//        {
//            if(oTimer)
//            {
//                clearTimeout(oTimer);
//                oTimer=null;
//            }
//        };
//		 OUl[i].onmouseout=function ()
//        {
//            var index=this.Index;
//            oTimer=setTimeout
//            (
//                function ()
//                {
//                    OUl[index].style.display='none';
//                    oTimer=null;
//                },1000
//            );}
			//导航菜单
		$('.lesson_tab_1 ul').find('li').click(function(){
			$('.lesson_tab_1 ul').find('li').attr('class','');
			$(this).attr('class','li_this');
			$('.lesson_tab_1').siblings('div').css('display','none');
			$('.lesson_tab_1').siblings('div').eq($(this).index()).css('display','block');
		});
};
	
	
