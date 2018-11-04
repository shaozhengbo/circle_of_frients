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
//$(document).ready(function() {
//
//	$('#a').mouseenter(function() {
//		$('#a').css("color", "#eb7350");
//	});
//
//	$('#a').mouseleave(function() {
//		$('#a').css("color", "");
//	});
//
//	$('#b').mouseenter(function() {
//		$('#b').css("color", "#eb7350");
//	});
//
//	$('#b').mouseleave(function() {
//		$('#b').css("color", "");
//	});
//
//	$('#c').mouseenter(function() {
//		$('#c').css("color", "#eb7350");
//	});
//
//	$('#c').mouseleave(function() {
//		$('#c').css("color", "");
//	});
//
//	$('#d').mouseenter(function() {
//		$('#d').css("color", "#eb7350");
//	});
//
//	$('#d').mouseleave(function() {
//		$('#d').css("color", "");
//	});
//	$('#tab-login').addClass("tab-active");
//	$('#tab-register').addClass("tab-unactive");
//	
//});

function imgShow(outerdiv, innerdiv, bigimg, _this) {
	var src = _this.attr("src");
	$(bigimg).attr("src", src);

	/* 获取当前点击图片的真实大小，并显示弹出层及大图 */
	$("<img/>").attr("src", src).load(function() {
		var windowW = $(window).width();
		var windowH = $(window).height();
		var realWidth = 1000;
		var realHeight = 1000;
		var imgWidth, imgHeight;
		var scale = 1;

		if (realHeight > windowH * scale) {
			imgHeight = windowH * scale;
			imgWidth = imgHeight / realHeight * realWidth;
			if (imgWidth > windowW * scale) {
				imgWidth = windowW * scale;
			}
		} else if (realWidth > windowW * scale) {
			imgWidth = windowW * scale;
			imgHeight = imgWidth / realWidth * realHeight;
		} else {
			imgWidth = realWidth;
			imgHeight = realHeight;
		}
		$(bigimg).css("width", imgWidth);

		var w = (windowW - imgWidth) / 2;
		var h = (windowH - imgHeight) / 2;
		$(innerdiv).css({
			"top" : h,
			"left" : w
		});
		$(outerdiv).fadeIn("fast");
	});

	$(outerdiv).click(function() {// 再次点击淡出消失弹出层
		$(this).fadeOut("fast");
	});
}

function login() {
	var username = $('#inputUsername').val();
	var password = $('#inputPassword').val();
	$.ajax({
		url : "/User/getUserByUsernameAndPassword",
		type : "post",
		data : {
			"username" : username,
			"password" : password
		},
		success : function(data) {
            if (data.msg == '查找到用户') {
                $('#loginDiv').fadeOut("fast");
                $('#a').html(data.object.username);
                $('#a').attr("href", "/User/getUserById?id=" + data.object.id);
                $('#b').html("退出");
            } else {
                $('#loginButton').css("background", "red");
                $('#loginButton').attr('disabled', true);
                $('#loginButton').html("用户名或密码错误");
            }
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function recoveryButton() {
    var loginButton = $('#loginButton');
    loginButton.attr('disabled', false);
    loginButton.css("background", "#ff8140");
    loginButton.html("登&nbsp;&nbsp;陆");
}
