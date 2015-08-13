// JavaScript Document
$(document).ready(function () {
	$('.switch').toggle(
	  function () {
		$(this).addClass('switchOn');
		ajaxPopup('.earth60');
	},
	  function () {
		ajaxPopupClose('.earth60');
		$(this).removeClass('switchOn');
	  }
	);
});

function ajaxPopup(popelem) {
	var popup = popelem;
	var maskHeight = $(document).height();
	var maskWidth = window.screen.width+"px";
	var popupMarginTop = $(popup).height()/2;
	var popupMarginLeft = $(popup).width()/2;
	var popupTop = $(window).height()/2-popupMarginTop;
	var popupLeft = $(window).width()/2-popupMarginLeft;	
	$('html').css('overflow-x','hidden');
	$('#popupMask').height(maskHeight).width(maskWidth).show();
	$(popup).css({
	'top':popupTop,
	'left':popupLeft,
	'z-index':'999',
	'margin-top':'-50px'
	}).fadeIn('normal');
	$(popup).find('.closeBox').click(function (){
		ajaxPopupClose(popup);
		return false;
	});
	//$('#popupMask').click(function() {
		//ajaxPopupClose(popup);
		//$('.switchOn').addClass('switch');
		//$('.switchOn').removeClass('switchOn');
	//});

};

function ajaxPopupClose(popelem) {
		var popup = popelem;
		$(popup).fadeOut('fast');
		$('#popupMask').hide().height(0).width(0);
		$('html').css('overflow-x','auto');
		$('embed').css('visibility','visible');
};
