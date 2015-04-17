// 帖子分页查询
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

// 论坛登录验证
function bbsLogin() {
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
						window.location.assign("bbs!showBBSIndex.action");
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