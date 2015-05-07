// 主页帖子分页查询
function queryPost(page) {
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!queryPost.action",// 发送请求地址
		data : {// 发送给数据库的数据
			page : page

		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

			$("#showPost").html(data);

		}

	});
}

// 改变第二级类型
function changeType2(type2) {
	document.getElementById("type2").value = type2;
	queryPostPdu(1);
}

// 改变第三级类型
function changeType3(type3) {
	document.getElementById("type3").value = type3;
	queryPostPdu(1);
}

// 产品帖子分页查询
function queryPostPdu(page) {
	var type1 = document.getElementById("type1").value;
	var type2 = document.getElementById("type2").value;
	var type3 = document.getElementById("type3").value;
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!queryPostPdu.action",// 发送请求地址
		data : {// 发送给数据库的数据
			page : page,
			type1 : type1,
			type2 : type2,
			type3 : type3

		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

			$("#showPostPdu").html(data);

		}

	});
}

// 分页查询回复
function queryRsp(page) {
	var poId = document.getElementById("poId").value;
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!queryRsp.action",// 发送请求地址
		data : {// 发送给数据库的数据
			page2 : page,
			poId2 : poId

		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

			$("#showRsp").html(data);

		}

	});
}

// 发帖
function doPost() {

	var title = document.getElementById("title").value;
	var content = document.getElementById("content").value;
	if (title.trim() == "") {
		document.getElementById("checkTitle").innerHTML = "标题不能为空！";
	} else if (content.trim() == "") {
		document.getElementById("checkPost").innerHTML = "内容不能为空！";
	} else {
		var type1 = document.getElementById("type1").value;
		var type2 = document.getElementById("type2").value;
		var type3 = document.getElementById("type3").value;
		$.ajax({
			type : "post",// 请求方式
			url : "ajax!doPost.action",// 发送请求地址
			data : {// 发送给数据库的数据
				type1_1 : type1,
				type2_1 : type2,
				type3_1 : type3,
				title : title,
				content : content
			},
			// 请求成功后的回调函数有两个参数

			success : function(data, textStatus) {
				if (type1 == 1) {
					window.location.assign("bbs!showPostAll.action?type=1");
				}
				if (type1 == 2) {
					window.location.assign("bbs!showPostAll.action?type=2");
				}
				if (type1 == 3) {
					window.location.assign("bbs!showPostAll.action?type=3");
				}
			}

		});
	}

}

// 论坛登录验证
function bbsLogin(url) {
	var uName = document.getElementById("uName").value;
	var uPass = document.getElementById("uPass").value;
	if (document.getElementById("remember").checked) {
		var remember = true;
	} else {
		var remember = false;
	}
	$
			.ajax({
				type : "post",// 请求方式
				url : "ajax!bbsLogin.action",// 发送请求地址
				data : {// 发送给数据库的数据
					uName : uName,
					uPass : uPass,
					remember : remember

				},
				// 请求成功后的回调函数有两个参数

				success : function(data, textStatus) {
					if (data == 0) {
						if (url != "") {
							window.location.assign(url);
						} else {
							window.location.assign("bbs!showBBSIndex.action");
						}

					}
					if (data == 1) {
						document.getElementById("error").innerHTML = "<span style='color: red;'>用户名密码不匹配！</span>";
					}
					if (data == 2) {
						document.getElementById("error").innerHTML = "<span style='color: red;'>该帐号已被停封！</span>";
					}
				}

			});
}

// 回车键触发论坛登录界面的登录按钮click（）
function eKeyup(e) {
	e = e ? e : (window.event ? window.event : null);
	if (e.keyCode == 13)// Enter
	{
		document.getElementById("bbsLogin").click();
	}
}

// 重新输入密码时取消之前错误提示
function reInput() {
	document.getElementById("error").innerHTML = "";
}

// 签到
function signIn(uId) {
	$
			.ajax({
				type : "post",// 请求方式
				url : "ajax!doSignIn.action",// 发送请求地址
				data : {// 发送给数据库的数据
					uId : uId

				},
				// 请求成功后的回调函数有两个参数

				success : function(data, textStatus) {
					document.getElementById("signIn").innerHTML = "<button type='button' class='btn btn-primary'disabled='disabled'>√&nbsp;&nbsp;&nbsp;&nbsp;已签到</button><br><span style='display: block;margin-top:15px;'>已连续签到"
							+ data + "天</span>";
				}

			});
}

// 发送验证码
function sendCheck() {
	document.getElementById("status").value = "0";
	document.getElementById("emailBt").setAttribute("disabled", "disabled");
	var email = document.getElementById("uEmail").value;
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!sendCheck.action",// 发送请求地址
		data : {// 发送给数据库的数据
			email : email

		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

		}

	});

}

// 设置60秒后可重新发送验证码
var s = 60;
function time() {
	s--;
	document.getElementById("emailBt").value = "获取验证码" + "(" + s + ")";
	var t = setTimeout('time()', 1000);
	if (s <= 0) {
		s = 60;
		document.getElementById("status").value = "1";
		document.getElementById("emailBt").removeAttribute("disabled");
		clearTimeout(t);
	}

}
// 检查用户名是否符合
function checkName() {
	var name = document.getElementById("uName").value;

	if (name.length >= 6 && name.length <= 10 && name.match("^[a-zA-Z0-9\_]+$")) {
		checkExist()
		if (document.getElementById("ch1").value == 'true') {
			return true;
		} else {
			return false;
		}

	} else {
		document.getElementById("checkName").innerHTML = "<span style = 'font-size:10px; color: red'><b>用户名只能由字母、数字、下划线组成，长度为6-10个字符</b></span>";
		return false;
	}
}

// 检查用户名是否存在
function checkExist() {
	var name = document.getElementById("uName").value;
	$
			.ajax({
				type : "post",// 请求方式
				url : "ajax!checkExist.action",// 发送请求地址
				data : {// 发送给数据库的数据
					uName1 : name

				},
				// 请求成功后的回调函数有两个参数

				success : function(data, textStatus) {

					if (data == "1") {

						document.getElementById("checkName").innerHTML = "<span style = 'font-size:10px; color: red'><b>用户名已存在</b></span><input type='hidden' value='false' id='ch1'>";

					} else {

						document.getElementById("checkName").innerHTML = "<span style = 'font-size:10px; color: green'><b>√</b></span><input type='hidden' value='true' id='ch1'>";

					}

				}

			});

}

// 检查密码是否符合
function checkPass() {
	var pass = document.getElementById("uPass").value;
	if (pass.length >= 6) {
		document.getElementById("checkPass").innerHTML = "<span style = 'font-size:10px; color: green'><b>√</b></span>";
		return true;
	} else {
		document.getElementById("checkPass").innerHTML = "<span style = 'font-size:10px; color: red'><b>密码长度不得小于6个字符</b></span>";
		return false;
	}
}

function checkEmail() {
	var email = document.getElementById("uEmail").value;
	var status = document.getElementById("status").value;
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(email) && status == 1) {
		document.getElementById("emailBt").disabled = "";
		document.getElementById("checkEmail").innerHTML = "<span style = 'font-size:10px; color: green'><b>√</b></span>";
		return true;
	} else {
		document.getElementById("emailBt").disabled = "disabled";
		document.getElementById("checkEmail").innerHTML = "<span style = 'font-size:10px; color: red'><b>请输入有效的邮箱地址</b></span>";
		return false;
	}
}

// 检查是否同意协议
function checkBox() {
	if (document.getElementById("box").checked) {
		document.getElementById("register").disabled = "";
	} else {
		document.getElementById("register").disabled = "disabled";
	}

}

// 检查验证码以及用户名密码规范
function doRegister(name) {
	var cookie;
	var arrstr = document.cookie.split("; ");
	for ( var i = 0; i < arrstr.length; i++) {
		var temp = arrstr[i].split("=");
		if (temp[0] == name) {
			cookie = unescape(temp[1]);
			break;
		}

	}

	if (cookie == document.getElementById("check").value) {
		if (!checkName() || !checkPass() || !checkEmail()) {
			return false;
		} else {

			var exp = new Date();
			exp.setTime(exp.getTime() - 1);

			document.cookie = name + "=;expires=" + exp.toGMTString()
					+ ";path=/";
			return true;
		}
	} else {
		alert("验证码有误！请检查邮件或重新发送。");
		return false;
	}

}

// 需获取权限而登陆时记录当前url登陆后自动返回该页
function goLogin() {
	var url = window.location.href;
	window.location.assign("bbs!showBBSLogin.action?url=" + url);
}

// 回复帖子或用户
function doResponse(poId, toUid, position) {
	var repley;
	if (toUid > 0) {
		repley = document.getElementById("resCtToU" + position).value;
	} else {
		repley = document.getElementById("response").value;
	}

	if (repley.trim() == "") {
		if (toUid > 0) {
			document.getElementById("checkRspToU" + position).innerHTML = "回复不能为空！";
		} else {
			document.getElementById("checkResponse").innerHTML = "回复不能为空！";
		}
	} else {
		$.ajax({
			type : "post",// 请求方式
			url : "ajax!doResponse.action",// 发送请求地址
			data : {// 发送给数据库的数据
				poId : poId,
				toUid : toUid,
				position : position,
				repley : repley
			},
			// 请求成功后的回调函数有两个参数

			success : function(data, textStatus) {
				window.location.assign("bbs!showPostContent.action?poId="
						+ poId);
			}

		});
	}

}

// 发帖时提示标题或内容为空时重新输入取消提示
function forDoPost() {
	document.getElementById("checkTitle").innerHTML = "";
	document.getElementById("checkPost").innerHTML = "";
}

// 回复用户显示文本框
function showRspToU(uagname, position, poId, toUid) {
	document.getElementById("rspToU" + position).innerHTML = "@" + uagname
			+ "：<input type='text' id='resCtToU" + position
			+ "' /><input type='button' value='回复' onclick='doResponse(" + poId
			+ ", " + toUid + ", " + position + ")'>";
}

// 显示指定帖子的指定楼层的更多帖子
function showMore(poId, position) {
	var showMore = document.getElementById("showMore" + position);
	showMore.parentNode.removeChild(showMore);
	var clickNum = document.getElementById("clickNum" + position).value;
	document.getElementById("clickNum" + position).value = parseInt(clickNum) + 1;
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!showMore.action",// 发送请求地址
		data : {// 发送给数据库的数据
			poId3 : poId,
			position3 : position,
			clickNum : clickNum
		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {
			$("#continue" + position).html(data);
			document.getElementById("continue" + position).id = "oldPast";
		}

	});
}

// 点赞帖子
function doPraise(praiserId, toPid) {
	document.getElementById("doPraise").innerHTML = "<i class='fa fa-thumbs-up'></i>已赞";
	document.getElementById("doPraise").disabled = "disabled";
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!doPraise.action",// 发送请求地址
		data : {// 发送给数据库的数据
			praiserId : praiserId,
			toPid : toPid
		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

		}

	});
}

// 记忆关键字
function saveKey() {
	var key = document.getElementById("key").value;
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!saveKey.action",// 发送请求地址
		data : {// 发送给数据库的数据
			key : key
		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

		}

	});
}

// 分页显示查询结果
function querySearchPost(page) {
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!querySearchPost.action",// 发送请求地址
		data : {// 发送给数据库的数据
			page3 : page

		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

			$("#showPost").html(data);

		}

	});
}

// 分页显示查询结果
function querySearchPost2(page) {
	saveKey();
	$.ajax({
		type : "post",// 请求方式
		url : "ajax!querySearchPost.action",// 发送请求地址
		data : {// 发送给数据库的数据
			page3 : page

		},
		// 请求成功后的回调函数有两个参数

		success : function(data, textStatus) {

			$("#showPost").html(data);

		}

	});
}
