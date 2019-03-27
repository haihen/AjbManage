// JavaScript Document

/*搜索下拉*/
$(document).ready(function(e) {
	$('.sear').hover(function(){
		$(this).children('.sear_top').show();
	},function(){
		$(this).children('.sear_top').hide();
	})
	$('.sear_top').children('a').click(function(){
		$('.sear').find('span').children('a').html($(this).html());
		$('.sear_top').hide()
	});
});


/*login头像下拉*/
$('.zt_user').hover(function(){
	$(this).find('a.zt_tit').addClass('readerhover');
	$(this).find('.zt_userTop').show();
},function(){
	$(this).find('a.zt_tit').removeClass('readerhover');
	$(this).find('.zt_userTop').hide();
});