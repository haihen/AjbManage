// JavaScript Document

jQuery.fn.disappearDefault=function(options){

var opts=jQuery.extend({},jQuery.fn.disappearDefault.defaults,options);

$(this).val(opts.vals);

$(this).css({color:opts.defaultColor});	

$(this).focus(function(){

if($(this).val()==opts.vals){
	
$(this).val('');}else {
	
return false;	

}

$(this).css({color:opts.color});

});

$(this).blur(function(){

if(opts.showDefault){

if($(this).val()==''){
	
$(this).val(opts.vals);

$(this).css({color:opts.defaultColor});	
	

}
	
}
	
});


}


jQuery.fn.disappearDefault.defaults={
	
vals:"鏍囩鐢ㄩ€楀彿鎴栫┖鏍奸殧寮€",

defaultColor:"#999",

color:"#333",

showDefault:false

}