(function($){

$.fn.focusPictures=function(opts){
	
var options=$.extend({},$.fn.focusPictures.defaults,opts);

var w,m=0,n=parseInt($('.' + options.scrollImg).find(options.scrollImgTag).width());

options.hoverTag();

function scrolls(){
if(m>=$('.'+options.scrollImg).find(options.scrollImgTag).length-1){
	$('.'+options.scrollImg).find(options.scrollImgWrap).animate({'margin-left':'0px'},options.scrollTime);
    m=0;
}else{
    m++;
    $('.'+options.scrollImg).find(options.scrollImgWrap).animate({'margin-left':-n*m +'px'},options.scrollTime);
};
$('.' + options.scrollNum).find(options.scrollNumTag).removeClass();
$('.' + options.scrollNum).find(options.scrollNumTag).eq(m).addClass(options.scrollNumTagCurrent);
}



$('.' + options.scrollNum).find(options.scrollNumTag).hover(function(){
	clearInterval(w);
	$('.'+options.scrollImg).find(options.scrollImgWrap).stop().animate({'margin-left':-n*$(this).index()+'px'},options.scrollTime);
	$('.' + options.scrollNum).find(options.scrollNumTag).removeClass();
	$(this).addClass(options.scrollNumTagCurrent);
},function(){
	m=$(this).index();
	w=setInterval(scrolls,options.scrollIntervals);	
});



	$('.'+options.scrollImg).find(options.scrollImgTag).hover(function(){	
	clearInterval(w);
	},function(){
		if(options.autoScroll){	
		   w=setInterval(scrolls,options.scrollIntervals);
		}
	});

	if(options.autoScroll){	
		   w=setInterval(scrolls,options.scrollIntervals);
		}

}	
})(jQuery);




$.fn.focusPictures.defaults={
	
scrollImg:'scrollImg',

scrollImgWrap:'ul',

scrollImgTag:'li',

scrollNum:'scrollNum',

scrollNumTag:'li',

scrollNumTagCurrent:'current',

scrollTime:250,

scrollIntervals:2500,

autoScroll:true,

hoverTag:function(){}



};