$._messengerDefaults = {
	extraClasses : 'messenger-fixed messenger-theme-future messenger-on-top'
}

// $.globalMessenger().post "Your request has succeded!"

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

function login(flag) {
	var username = "";
	var password = "";
	if(flag == '0') {
		username = sessionStorage["username"];
		password = sessionStorage["password"];
	} else if(flag == '1') {
		username = $('#inputUsername').val();
		password = $('#inputPassword').val();
	} else {
		alert("请重试");
		return;
	}
	
	if(flag == '1') {
		$.globalMessenger().post({
	        message: "Logining...",
	        hideAfter: 2,
	        type: 'success'
	    });
	}
	
	$.ajax({
		url : "/User/getUserByUsernameAndPassword",
		type : "post",
		data : {
			"username" : username,
			"password" : password
		},
		success : function(data) {
			if (data.msg == '查找到用户') {
				if(flag == '1') {
					$.globalMessenger().post({
	                    message: "Login Success",
	                    id: "Only-one-message",
	                    type: "info"
	                });
				}
				
				$('#loginDiv').fadeOut("fast");
				
				saveStorage(username, password);
				sessionStorage.setItem("user",data.object);
				
				$('#a').html(data.object.username);
				
				$('#a').attr("href", "#user_info");
				$('#a').attr("role", "button");
				$('#a').attr("data-toggle", "modal");
				//$('#a').attr("onclick", "getUserInfo("+data.object.id+")");
				$('#b').html("退出");
				
				var object = data.object;
				$("#id").val(object.id + "");
				$("#name").html(object.username);
				$("#sex").html(object.sex);
				$("#year").html(object.birth.toString().substr(0, 4));
				$("#mouth").html(object.birth.toString().substr(5, 2));
				$("#day").html(object.birth.toString().substr(8, 2));
				$("#phonenumber").html(object.phonenumber);
				$("#major").html(object.major);
				$("#mail").html(object.mail);
			} else {
				$.globalMessenger().hideAll();
				$.globalMessenger().post({
                    message: "Login Error",
                    type: 'error'
                });
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

function saveStorage(username, password) {
	sessionStorage.setItem("username",username);
	sessionStorage.setItem("password",password);
}

function search() {
	var searchStr = $("#searchInput").val();
	
	$.ajax({
		url : "/User/searchUser",
		data : {
			"searchStr" : searchStr
		},
		success: function (data){
			var obj = data.object;
			console.log(obj);
		},
		error : function() {
			alert("通信错误");
		}
	});
}
