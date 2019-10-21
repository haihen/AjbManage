// JavaScript Document

(function($) {
	$.fn.showHide = function(opts) {
		var options = $.extend({}, $.fn.showHide.defaults, opts);
		$(this).hover(function() {
			$(this).children(options.hideClass).show();
		}, function() {
			$(this).children(options.hideClass).hide();
		});
	}
	$.fn.showHide.defaults = {
		hideClass : ".a2"
	}
})(jQuery);