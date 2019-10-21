// JavaScript Document


//澶撮儴鎼滅储妗唈s鏁堟灉
$('#search').find('.searchText').each(function() {
	//淇濆瓨褰撳墠鏂囨湰妗嗙殑鍊�
	var vdefault = this.value;
	$(this).focus(function() {
		//鑾峰緱鐒︾偣鏃讹紝濡傛灉鍊间负榛樿鍊硷紝鍒欒缃负绌�
		if (this.value == vdefault) {
			this.value = "";
		}
		$('#search').removeClass('search').addClass('searchfocuse');
			$(this).css({"color":"#333333"});
	});
	$(this).blur(function() {
		//澶卞幓鐒︾偣鏃讹紝濡傛灉鍊间负绌猴紝鍒欒缃负榛樿鍊�
		if (this.value == "") {
			this.value = vdefault;
		}
		$('#search').removeClass('searchfocuse').addClass('search');
		$(this).css({"color":"#999999"});
	});
});



/*login澶村儚涓嬫媺*/
$('.zt_user').hover(function(){
	$(this).find('a.zt_tit').addClass('readerhover');
	$(this).find('.zt_userTop').show();
},function(){
	$(this).find('a.zt_tit').removeClass('readerhover');
	$(this).find('.zt_userTop').hide();
});



//sidebar鍥哄畾 闅忓睆婊氬姩	
$(window).scroll(function(){
	if($(window).scrollTop()>264){
		if($.browser.msie && ($.browser.version == "6.0") && !$.support.style){
			$('.sidebarFix').css({top:function(){
				return document.documentElement.scrollTop+30;
			}
		});	
	}else{
		$('.sidebarFix').css({'position':'fixed','top':'0'});
	}			
	}else if($(window).scrollTop()<=264){
		$('.sidebarFix').css({'position':'absolute','top':'274px'});
	}	
});



//sidebar鍥哄畾 闅忓睆婊氬姩	  Left杈�
$(window).scroll(function(){
	if($(window).scrollTop()>350){
		if($.browser.msie && ($.browser.version == "6.0") && !$.support.style){
			$('.sidebarFixLeft').css({top:function(){
				return document.documentElement.scrollTop+30;
			}
		});	
	}else{
		$('.sidebarFixLeft').css({'position':'fixed','top':'30px'});
	}			
	}else if($(window).scrollTop()<=350){
		$('.sidebarFixLeft').css({'position':'absolute','top':'370px'});
	}	
});


//鍥句功鍗＄墖椤� js 璇勫垎

function choice(m){	
switch(m){
case 1:$('.Wopj span').next().text('鏋佸樊');break;
case 2:$('.Wopj span').next().text('宸�');break;
case 3:$('.Wopj span').next().text('涓€鑸�');break;
case 4:$('.Wopj span').next().text('寰堝ソ');break;
case 5:$('.Wopj span').next().text('闈炲父濂�');break;
}

}

$('.Wopj span a').hover(function(){
var y0=($(this).index()+1)*1;

$(this).parent().removeClass().addClass('ss'+ y0);
choice(y0);
},function(){
$(this).parent().removeClass().addClass('ss0');	
$(this).parent().next().text('');
});

$('.Wopj span a').click(function(){
var wk=($(this).index()+1)*1;alert(wk);
$(this).parent().removeClass().addClass('ss'+ wk);
choice(wk);
});