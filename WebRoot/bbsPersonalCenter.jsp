<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width,inital-scale=1.0,maximum-scale=1.0,use-scalable=no">
<meta name="Author" content="bruce。bei">
<meta name="Description" content="宁波美灵思医疗科技有限公司官网专用">
<title>美灵思产品论坛</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/jquery.Jcrop.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="scripts/swfobject.js"></script>
<script type="text/javascript" src="scripts/fullAvatarEditor.js"></script>
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<link href="css/bbs_personal_data.css" type="text/css" rel="stylesheet" />
<link href="css/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style_2_common.css" rel="stylesheet">
</head>
<body>

  <nav class="navbar navbar-default" style="height: 70px;"
    role="navigation">

    <div class="navbar-header left">
      <c:forEach items="${headers }" var="hea">
        <a class="navbar-brand" href="#"><img
          src="/docs/upload/${hea.logoImg }"
          style="height: 50px; margin-top: -7.5px;"></a>
      </c:forEach>
    </div>

    <div>
      <ul class="nav navbar-nav head-size " style="font-size: 20px;">
        <c:forEach items="${BBSNavs }" var="bnav">
          <c:if test="${bnav.navName=='首页' }">
            <li><a href="bbs!showBBSIndex.action">${bnav.navName
                }</a></li>
          </c:if>
          <c:if test="${bnav.navName=='产品' }">
            <li><a href="bbs!showPostAll.action?type=1">${bnav.navName
                }</a></li>
          </c:if>
          <c:if test="${bnav.navName=='体验' }">
            <li><a href="bbs!showPostAll.action?type=2">${bnav.navName
                }</a></li>
          </c:if>
          <c:if test="${bnav.navName=='问题' }">
            <li><a href="bbs!showPostAll.action?type=3">${bnav.navName
                }</a></li>
          </c:if>

        </c:forEach>
      </ul>

      <p class="navbar-text navbar-right head-size right"
        style="margin-right: 0px;">
        <c:if test="${sessionScope.uId==null }">
          <a href="bbs!showBBSLogin.action">登录</a>
          &emsp;
          <a href="">注册</a>
        </c:if>
        <c:if test="${sessionScope.uId!=null }">
        欢迎 <a href="bbs!showPersonalCenter.action" class="navbar-link"><img
            src="/docs/upload/${sessionScope.uIcon }">${sessionScope.uAgname
            }</a>
          <a href="bbs!bbsLogout.action">注销</a>
        </c:if>
      </p>

      <form class="navbar-form navbar-right head-size hid" role="search">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Search"
            style="border: 0px" id="key"> <span
            class="input-group-addon"
            style="border: 0px; background-color: #fff;"><a
            onclick="saveKey()" href="bbs!doSearch.action"
            target="_blank"><img src="images/search.ico"
              height="20px;" /></a></span>
        </div>
      </form>

    </div>
  </nav>

  <div class="container">
    <div class="row">
      <div class="col-sm-12 bg-white">
        <div class="col-sm-4">
          <div class="mg-top-15">
            <div class="personal-img">
              <div class="left width-60">
                <img class="img-circle "
                  src="/docs/upload/${user.uicon1 }">
              </div>
              <div id="personal-list" class="left width-40">
                <ul class="list-unstyled mg-right-15">
                  <li>帖子<span>${user.posts.size() }</span></li>
                  <li>相册<span>0</span></li>
                  <li>好友<span>0</span></li>
                  <li>积分<span>${user.uscore }</span></li>
                  <li>签到<span>${user.historyDays }</span></li>
                </ul>
              </div>
            </div>
            <div class="mg-top-35">用户昵称：${user.uagname }</div>
            <div class="mg-top-15">http://bbs.dji.com/?24203（
              个人用户中心他人访问url访问地址）</div>
            <div class="mg-top-35">用户勋章</div>
            <div class="mg-top-15">
              <a style="cursor: pointer;" onclick="openpage('all')">
                查看全部资料资料</a>
            </div>
            <div style="position: relative;">
              <a style="cursor: pointer;" style="cursor: pointer;"
                class="user-setting" title="设置"
                onclick="openpage('base')"></a>
            </div>
          </div>
        </div>
        <div class="col-sm-8">
          <img src="images/mlsyl1.jpg" style="width: 100%;">
        </div>
      </div>
      <div id="maincontent" class="col-sm-12 bg-white mg-top-15 pd15">hello
        body@</div>
      <div style="width:632px;margin: 0 auto;text-align:center">
        <div>
          <p id="swfContainer">
            本组件需要安装Flash Player后才可使用，请从<a
              href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。
          </p>
        </div>
      </div>
      <script type="text/javascript">
							swfobject
									.addDomLoadEvent(function() {

										var swf = new fullAvatarEditor(
												"fullAvatarEditor.swf",
												"expressInstall.swf",
												"swfContainer",
												{
													id : 'swf',
													upload_url : 'upload.jsp', //上传接口
													method : 'post', //传递到上传接口中的查询参数的提交方式。更改该值时，请注意更改上传接口中的查询参数的接收方式
													src_upload : 0, //是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
													avatar_box_border_width : 2,
													avatar_sizes : '100*100|50*50|32*32',
													avatar_sizes_desc : '100*100像素|50*50像素|32*32像素'
												},
												function(msg) {
													switch (msg.code) {
													case 3:
														if (msg.type == 0) {
															alert("摄像头已准备就绪且用户已允许使用。");
														} else if (msg.type == 1) {
															alert("摄像头已准备就绪但用户未允许使用！");
														} else {
															alert("摄像头被占用！");
														}
														break;
													}
												});
										document.getElementById("upload").onclick = function() {
											swf.call("upload");
										};
									});
							var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
									: " http://");
							document
									.write(unescape("%3Cscript src='"
											+ _bdhmProtocol
											+ "hm.baidu.com/h.js%3F5f036dd99455cb8adc9de73e2f052f72' type='text/javascript'%3E%3C/script%3E"));
						</script>
    </div>
  </div>


  <div class="footer bg-white">
    <div class="row">
      <div class="col-xs-4 col-xs-offset-2 col-sm-3">
        <c:forEach items="${qrcode1 }" var="qr1">
          <img src="/docs/upload/${qr1.qrImg }" class="img-responsive"
            alt="nbmlnx_service" width="90px" align="bottom">
          <p style="font-size: 11px; margin: 12px;">${qr1.qrName }</p>
        </c:forEach>
      </div>

      <div class="col-xs-4 col-sm-3">
        <c:forEach items="${qrcode2 }" var="qr2">
          <img src="/docs/upload/${qr2.qrImg }" class="img-responsive"
            alt="nbmlnx_service" width="90px" align="bottom">
          <p style="font-size: 11px; margin: 12px;">${qr2.qrName }</p>
        </c:forEach>
      </div>

      <div class="col-xs-12 col-sm-3">
        <address class="hidden-xs">
          <strong style="font-size: 20px;">宁波市美灵思医疗科技有限公司</strong><br>
          <c:forEach items="${contact }" var="ct">
        地址：<a href="http://j.map.baidu.com/9FV9x" target="_blank">${ct.ctAdress
              }</a>
            <br>
        电话：<a href="tel:${ct.ctPhone }">${ct.ctPhone }</a>
            <br> 传真：<a href="tel:${ct.ctFax }">${ct.ctFax }</a>
            <br> 邮箱：<a href="${ct.ctEmail }">${ct.ctEmail }</a>
            <br>
          </c:forEach>
          <a href="http://weibo.com/u/1950616540" target="_blank"
            style="color: #c19b85">微博关注</a>
        </address>
      </div>
      <div class="center visible-xs">
        <address>
          <strong style="font-size: 20px;">宁波市美灵思医疗科技有限公司</strong><br>
          <c:forEach items="${contact }" var="ct">
        地址：<a href="http://j.map.baidu.com/9FV9x" target="_blank">${ct.ctAdress
              }</a>
            <br>
        电话：<a href="tel:${ct.ctPhone }">${ct.ctPhone }</a>
            <br> 传真：<a href="tel:${ct.ctFax }">${ct.ctFax }</a>
            <br> 邮箱：<a href="${ct.ctEmail }">${ct.ctEmail }</a>
            <br>
          </c:forEach>
          <a href="http://weibo.com/u/1950616540" target="_blank"
            style="color: #c19b85">微博关注</a>
        </address>
      </div>
      <div class="col-xs-12">
        <div class="center">
          <c:forEach items="${copyright }" var="cop">
            <p style="font-size: 10px">
              © 2015 Power by <a href="http://www.nbmlnx.com"
                target="_blank">${cop.cpDetail }</a>
            </p>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
  <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
  <script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
  <!-- 包括所有已编译的插件 -->
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/personal_center.js"></script>
</html>

