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
				getAllMessage();
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
		type : "post",
		data : {
			"searchStr" : searchStr
		},
		success : function(data) {
			alert(searchStr)
			var obj = data.object;
			console.log(data);
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
	var message = $("#sendMessage").val();
	var form = new FormData(document.getElementById("form"));
	$.ajax({
		url : "/Message/sendMessage",
		type : "post",
		data : form,
		processData : false,
		contentType : false,
		success : function(data) {
			alert(data.msg);
			$("#sendMessage").val("");
			getAllMessage();
		},
		error : function(err) {
			console.log(err);
		}
	})
}

function getAllMessage() {
	var flag = true;
	$
			.ajax({
				url : "/Message/getAllMessage",
				type : "post",
				data : {

				},
				success : function(res) {
					console.log(res);
					var list = res.object;
					$("#itemDiv").empty();
					for (var i = 0; i < list.length; i++) {
						$("#itemDiv")
								.append(
										"<div class='media well' style='background: #ffffff'><div><div style='float: left; clear: both;'><img class='pimg' src='"
												+ list[i].uid.img
												+ "' width='50px;' height='50px;'style='border-radius: 25px; object-fit: cover;' /></div><div style='float: left;'>&nbsp;&nbsp;<a href='' target='_blank'>"
												+ list[i].uid.username
												+ "</a>  "
												+ isNew(list[i].createtime)
												+ "</div><br><div style='margin-left: 55px; margin-top: 20px;'><p>"
												+ list[i].message
												+ "</p><div style='text-align: left; width: 400px;'>"
												+ imgHtml(list[i].pid)
												+ "</div><div><small style='color: gray;'>"
												+ timeStamp2String(list[i].createtime)
												+ "</small></div></div></div><div style='width: 100%; margin-left: 5px; margin-top: 10px;'><button type='button' class='btn btn-default'aria-label='Left Align' style='width: 32%;'><span class='glyphicon glyphicon-share' aria-hidden='true'></span></button><button type='button' class='btn btn-default'aria-label='Center Align' style='width: 32%;'><span class='glyphicon glyphicon-comment'aria-hidden='true'></span></button><button type='button' class='btn btn-default'aria-label='Right Align' style='width: 32%;'><span class='glyphicon glyphicon-thumbs-up'aria-hidden='true'></span></button></div></div>");

					}
					$("img").click(function() {
						var _this = $(this);
						imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
					});
				},
				error : function(err) {
					console.log(err);
				}

			})
}

// 获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
	var myDate = new Date();
	return myDate.toLocaleString('chinese', {
		hour12 : false
	});
}

function timeStamp2String(time) {
	var dateee = new Date(time).toJSON();
	return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(
			/T/g, ' ').replace(/\.[\d]{3}Z/, '')

}

function imgHtml(pid) {
	if (pid == null) {
		return "";
	} else {
		var str = "<img class='pimg' src='"
				+ pid.src
				+ "' width='112.97'height='112.97' style='object-fit: cover;' />";
		return str;
	}
}

function isNew(time) {
	var itemTime = new Date(time);
	var nowTime = new Date();
	if (nowTime - itemTime > 1000 * 60 * 3) {
		return "";
	} else {
		return "<span class='label label-danger'>New</span>";
	}
}