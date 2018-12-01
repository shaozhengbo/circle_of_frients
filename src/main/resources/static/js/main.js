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
	if (flag == '0') {
		username = sessionStorage["username"];
		password = sessionStorage["password"];
	} else if (flag == '1') {
		username = $('#inputUsername').val();
		password = $('#inputPassword').val();
	} else {
		alert("请重试");
		return;
	}

	if (flag == '1') {
		$.globalMessenger().post({
			message : "Logining...",
			hideAfter : 2,
			type : 'success'
		});
	}

	$.ajax({
		url : "/User/login",
		type : "post",
		data : {
			"username" : username,
			"password" : password
		},
		success : function(data) {
			if (data.msg == '查找到用户') {
				if (flag == '1') {
					$.globalMessenger().post({
						message : "Login Success",
						id : "Only-one-message",
						type : "info"
					});
				}

				$('#loginDiv').fadeOut("fast");

				saveStorage(username, password);
				sessionStorage.setItem("user", data.object);

				$('#a').html(data.object.username);

				$('#a').attr("href", "#user_info");
				$('#a').attr("role", "button");
				$('#a').attr("data-toggle", "modal");
				// $('#a').attr("onclick", "getUserInfo("+data.object.id+")");
				$('#b').html("退出");
				$('#b').attr("onclick", "exit()");

				var object = data.object;
				$("#user_id").val(object.id);
				$("#name").val(object.username);
				if (object.sex == "男") {
					$("#sex_man").attr("selected", "true");
				} else {
					$("#sex_women").attr("selected", "true");
				}
				$("#year").val(object.birth.toString().substr(0, 4));
				$("#mouth").val(object.birth.toString().substr(5, 2));
				$("#day").val(object.birth.toString().substr(8, 2));
				$("#phonenumber").val(object.phonenumber);
				$("#major").val(object.major);
				$("#mail").val(object.mail);
				$("#user_img").attr("src", object.img);
			} else {
				$.globalMessenger().hideAll();
				$.globalMessenger().post({
					message : "Login Error",
					type : 'error'
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
	sessionStorage.setItem("username", username);
	sessionStorage.setItem("password", password);
}

function search() {
	var searchStr = $("#searchInput").val();

	$.ajax({
		url : "/User/searchUser",
		data : {
			"searchStr" : searchStr
		},
		success : function(data) {
			var obj = data.object;
			console.log(obj);
		},
		error : function() {
			alert("通信错误");
		}
	});
}

function editUserInfo() {

	// check data
	var mail = $("#mail").val()
	console.log(mail)
	if (mail.indexOf("@") == -1) {
		alert("邮箱格式不正确");
		return;
	}

	var user = {
		"id" : $("#user_id").val(),
		"username" : $("#name").val(),
		"sex" : $("#sex").val(),
		"birth" : $("#year").val() + "/" + $("#mouth").val() + "/"
				+ $("#day").val(),
		"phonenumber" : $("#phonenumber").val(),
		"mail" : $("#mail").val(),
		"major" : $("#major").val(),
		"img" : $("#user_img")[0].src.substring(22)
	}
	console.log(user);
	$.ajax({
		url : "/User/updateUser",
		type : "post",
		data : {
			"id" : $("#user_id").val(),
			"username" : $("#name").val(),
			"sex" : $("#sex").val(),
			"birth" : $("#year").val() + "/" + $("#mouth").val() + "/"
					+ $("#day").val(),
			"phonenumber" : $("#phonenumber").val(),
			"mail" : $("#mail").val(),
			"major" : $("#major").val(),
			"img" : $("#user_img")[0].src.substring(22)
		},
		success : function(data) {
			alert(data.msg);
			$("#userInfoCloseBtn").click();
		},
		error : function(err) {
			console.log("通信错误");
		}
	});

}
function changeUserImage() {
	$("#user_image_file").click();
}

function send() {
	var message = $("#sendMessage").html()
	$.ajax({
		url : "/Message/sendMessage",
		type : "post",
		data : {
			"message" : message,
			"createtime" : getNowFormatDate(),
		},
		success : function(data) {
			alert(data.msg);
		},
		error : function(err) {
			console.log(err);
		}
	})
}

// 获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "/";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}
