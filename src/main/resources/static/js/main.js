$._messengerDefaults = {
	extraClasses : 'messenger-fixed messenger-theme-future messenger-on-top'
}

function clickTab(flag) {
	if (flag == 1) {
		$('#tab-login').removeClass("tab-unactive");
		$('#tab-login').addClass("tab-active");
		$("#tab-register").removeClass("tab-active");
		$("#tab-register").addClass("tab-unactive");
	} else if (flag == 2) {
		$('#tab-register').removeClass("tab-unactive");
		$('#tab-register').addClass("tab-active");
		$("#tab-login").removeClass("tab-active");
		$("#tab-login").addClass("tab-unactive");
	}
}

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

function checkUsernameIsExist(username) {
	var result = false;
	$.ajax({
		url : "/User/getUserByUsername",
		type : "post",
		async : false,
		data : {
			"username" : username,
		},
		success : function(data) {
			if (data.msg == "查找到用户" && data.object.id != sessionStorage["user_id"]) {
				result = true;
			}
		},
		error : function(msg) {
			console.log(msg);
		}
	});
	return result;
}

function register() {
	var username = $("#register_username").val();
	var password = $("#register_password").val();
	var result = checkUsernameIsExist(username);
	if (result) {
		$('#registerButton').css("background", "red");
		$('#registerButton').attr('disabled', true);
		$('#registerButton').html("用户名已存在");
	}
	$.ajax({
		url : "/User/registerUser",
		type : "post",
		data : {
			"username" : username,
			"password" : password
		},
		success : function(data) {
			if (data.msg == "注册成功") {
				saveStorage(username, password);
				login('0');
			} else {
				$('#registerButton').css("background", "red");
				$('#registerButton').attr('disabled', true);
				$('#registerButton').html(data.object);
			}
		},
		error : function(msg) {
			console.log(msg);
		}
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
				}

				$('#loginDiv').fadeOut("fast");

				$('#messageEditDiv').fadeIn("fast");

				saveStorage(username, password);
				sessionStorage.setItem("user_id", data.object.id);

				$('#a').html(data.object.username);
				$('#a').attr("href", "#user_info");
				$('#a').attr("role", "button");
				$('#a').attr("data-toggle", "modal");

				$('#b').html("退出");
				$('#b').attr("onclick", "exit()");
				
				$('#c').html("朋友");
				$('#c').attr("href", "#search_user");
				$('#c').attr("role", "button");
				$('#c').attr("data-toggle", "modal");

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

function recoveryButton1() {
	var loginButton = $('#loginButton');
	loginButton.attr('disabled', false);
	loginButton.css("background", "#ff8140");
	loginButton.html("登&nbsp;&nbsp;陆");
}

function recoveryButton2() {
	var registerButton = $('#registerButton');
	registerButton.attr('disabled', false);
	registerButton.css("background", "#ff8140");
	registerButton.html("注&nbsp;&nbsp;册");
}

function saveStorage(username, password) {
	sessionStorage.setItem("username", username);
	sessionStorage.setItem("password", password);
}

function search() {
	var searchStr = $("#searchInput").val();
	if(searchStr == "") {
		$("#searchResultTable").empty();
		return;
	}
	
	$.ajax({
		url : "/User/searchUser",
		type : "post",
		data : {
			"searchStr" : searchStr
		},
		success : function(data) {
			var arr = data.object;
			var searchResultHtml = "";
			for(var i = 0; i < arr.length; i++) {
				searchResultHtml = searchResultHtml + searchDivHtml(arr[i]);
			}
			$("#searchResultTable").empty();
			$("#searchResultTable").append(searchResultHtml);
		},
		error : function() {
			alert("通信错误");
		}
	});
}

function searchDivHtml(user) {
	var html = "<tr><td align='center'><img src='"+user.img+"' width='60'height='60'style='object-fit: cover; border-radius: 30px;' /></td><td width='20%' align='center'>"+user.username+"</td><td width='20%' align='center'>"+user.phonenumber+"</td><td width='30%' align='center'>"+user.mail+"</td><td width='18%' align='center'>"+getAddButtonHtml(user.id)+"</td></tr>";
	return html;
}

function getAddButtonHtml(id) {
	var str = "";
	$.ajax({
		url : "Friend/isFriend",
		type : "post",
		async : false,
		data : {
			"uid" : id + ""
		},
		success : function(data) {
			if(data.msg == "是好友") {
				str = "<button id='friend_"+data.object.uid2.id+"' type='button' disabled='disabled' class='btn btn-warning'>已关注</button>";
			} else {
				str = "<button id='friend_"+id+"' type='button' class='btn btn-warning' onclick='addFriend("+id+")'>+关注</button>";
			}
		},
		error : function(msg) {
			console.log(msg);
		}
	});
	return str;
	
}

function addFriend(id) {
	$.ajax({
		url : "/Friend/addFriend",
		type : "post",
		data : {
			"uid" : id
		},
		success : function(data) {
			alert(data.msg);
			$("#friend_"+data.object.uid2.id).attr("disabled","disabled");
			$("#friend_"+data.object.uid2.id).html("已关注");
		},
		error: function(msg) {
			console.log(msg);
		}
	});
}

function editUserInfo() {
	// check data
	var nameCheck = checkUsernameIsExist($("#name").val());
	var mail = $("#mail").val();
	if (nameCheck) {
		alert("用户名已存在");
		return;
	}
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
			sessionStorage.setItem("username", $("#name").val());
			login('0');
			$('#a').html(data.object.username);
		},
		error : function(err) {
			console.log("通信错误");
		}
	});

}

function exit() {
	sessionStorage.removeItem("username");
	sessionStorage.removeItem("password");
	location.reload();
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
			$("#message").val("");
			$("#messagePic").attr("src", "");
			$("#messagePic").attr("width", "0px");
			$("#messagePic").attr("height", "0px");
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
					if (list.length == 0) {
						return;
					}
					for (var i = 0; i < list.length; i++) {
						
						$("#itemDiv")
								.append(
										"<div class='media well' style='background: #ffffff'><div><div style='float: left; clear: both;'><img class='pimg' src='"
												+ list[i].uid.img
												+ "' width='50px;' height='50px;'style='border-radius: 25px; object-fit: cover;' /></div><div style='float: left;'>&nbsp;&nbsp;"
												+ list[i].uid.username
												+ " "
												+ isNew(list[i].createtime)
												+ deleteHtml(list[i].uid.id,
														list[i].id)
												+ "</div><br><div style='margin-left: 55px; margin-top: 20px;'><p>"
												+ list[i].message
												+ "</p><div style='text-align: left; width: 400px;'>"
												+ imgHtml(list[i].pid)
												+ "</div><div><small style='color: gray;'>"
												+ timeStamp2String(list[i].createtime)
												+ "</small></div></div></div><div style='width: 100%; margin-left: 5px; margin-top: 10px;'><button type='button' disabled='disabled' class='btn btn-default'aria-label='Left Align' style='width: 32%;'><span class='glyphicon glyphicon-share' aria-hidden='true'></span></button><button type='button' disabled='disabled' class='btn btn-default'aria-label='Center Align' style='width: 32%;'><span class='glyphicon glyphicon-comment'aria-hidden='true'></span></button><button type='button' class='btn btn-default"
												+ isPointed(list[i].id)
												+ "'aria-label='Right Align' style='width: 32%;' onclick=point(this,"
												+ list[i].id
												+ ")><span class='glyphicon glyphicon-thumbs-up'aria-hidden='true'></span>&nbsp;<font id='f_"
												+ list[i].id
												+ "'>"
												+ getPointNum(list[i].id)
												+ "</font></button></div></div>");

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

function isPointed(mid) {
	var str = "";
	$.ajax({
		url : "/Point/isPointed",
		type : "post",
		async : false,
		data : {
			"mid" : mid
		},
		success : function(result) {
			if (!result.object) {
				str = " active";
			}
		},
		error : function(msg) {
			console.log(msg);
		}
	});
	return str;
}

// 点赞
function point(obj, id) {
	if (obj.className.indexOf("active") != -1) {
		obj.classList.remove("active");
		// 点赞减一
		$.ajax({
			url : "/Point/unPoint",
			type : "post",
			data : {
				"mid" : id
			},
			success : function(result) {
				var num = getPointNum(id);
				var dom = "f_"+id;
				$("#"+dom).html(num);
			}
		});
	} else {
		// 点赞加一
		$.ajax({
			url : "/Point/point",
			type : "post",
			data : {
				"mid" : id
			},
			success : function(result) {
				obj.classList.add("active");
				var num = getPointNum(id);
				var dom = "f_"+id;
				$("#"+dom).html(num);
			}
		});
	}
}

function deleteHtml(uid, id) {
	var user_id = sessionStorage["user_id"];
	if ((uid + "") == user_id) {
		return "<button class='btn btn-danger btn-xs' type='button' style='margin-left: 400px;' onclick='deleteMessage("
				+ id + ")'>删除</button>";
	} else {
		return "";
	}
}

function deleteMessage(mid) {
	console.info(mid);
	$.ajax({
		url : "/Message/deleteMessage",
		type : "post",
		data : {
			id : mid
		},
		success : function(result) {
			alert(result.msg);
			getAllMessage();
		},
		error : function(msg) {
			console.info(msg);
		}
	})
}

function getPointNum(mid) {
	var num = 0;
	$.ajax({
		url : "/Point/getPointNum",
		type : "post",
		async : false,
		data : {
			"mid" : mid
		},
		success : function(data) {
			num = data.object;
		},
		error : function(msg) {
			console.log(msg);
		}
	});
	return num;
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