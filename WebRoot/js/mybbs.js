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