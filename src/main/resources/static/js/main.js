//var vm = new Vue({
//	el : '#app',
//	data : {
//		username : "1",
//		password : "1",
//		result : "请登录",
//		a : false
//	},
//	methods : {
////		login() {
////			axios({
////		        method:'get',
////		         url:'User/getUserByUsername?username='+vm.username
////		     }).then(function(res){
////		     });
////		},
//		letFontBeOrange : function() {
//			a = true;
//		},
//		returnColor : function() {
//			a = false;
//		}
//		
//	}
//});
$(document).ready(function(){
 
	$('#a').mouseenter(function() {
		$('#a').css("color","#eb7350");
	});
	
	$('#a').mouseleave(function() {
		$('#a').css("color","");
	});
	
	$('#b').mouseenter(function() {
		$('#b').css("color","#eb7350");
	});
	
	$('#b').mouseleave(function() {
		$('#b').css("color","");
	});
	
	$('#c').mouseenter(function() {
		$('#c').css("color","#eb7350");
	});
	
	$('#c').mouseleave(function() {
		$('#c').css("color","");
	});
	
	$('#d').mouseenter(function() {
		$('#d').css("color","#eb7350");
	});
	
	$('#d').mouseleave(function() {
		$('#d').css("color","");
	});
	
	$('#tab-login').addClass("tab-active");
});

function clickTab(id) {
	var login = $('#tab-login');
	var register = $('#tab-register');
	if(id=='tab-login') {
		if(!login.hasClass("tab-active")) {
			login.addClass("tab-active");
			register.removeClass("tab-active");
			register.addClass("tab-unactive");
		}
	} else if(id=="tab-register") {
		if(!register.hasClass("tab-active")) {
			register.addClass("tab-active");
			login.removeClass("tab-active");
			login.addClass("tab-unactive");
		}
	}
}

