<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width,inital-scale=1.0,maximum-scale=1.0,use-scalable=no">
<meta name="Author" content="bruce。bei">
<meta name="Description" content="宁波美灵思医疗科技有限公司官网专用">
<title>美灵思论坛</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<!-- 引入lightSlider式样，供轮播图片使用  -->
<link href="css/lightSlider.css" type="text/css" rel="stylesheet" />
<link href="css/bbs_layout.css" type="text/css" rel="stylesheet" />
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
        <li><a href="#">${bnav.navName }</a></li>
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
        欢迎 <a href="#" class="navbar-link">${sessionScope.uAgname }</a>
        <a href="bbs!bbsLogout.action">注销</a>
      </c:if>

    </p>
    <form class="navbar-form navbar-right head-size hid" role="search">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search"
          style="border: 0px"> <span class="input-group-addon"
          style="border: 0px; background-color: #fff;"><a
          href="#"><img src="images/search.ico" height="20px;" /></a></span>
      </div>

    </form>
  </div>
  </nav>

  <div class="container">
    <div class="row">
      <div id="bbs_Carousel" class="carousel slide col-sm-9">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
          <li data-target="#bbs_Carousel" data-slide-to="0"
            class="active"></li>
          <li data-target="#bbs_Carousel" data-slide-to="1"></li>
          <li data-target="#bbs_Carousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
          <c:forEach items="${banners }" var="ban" varStatus="i">
            <c:if test="${i.index==0 }">
              <div class="item active">
                <img src="/docs/upload/${ban.banImg }" alt="First slide"
                  style="height: 300px; width: 100%;">
              </div>
            </c:if>
            <c:if test="${i.index==1 }">
              <div class="item">
                <img src="/docs/upload/${ban.banImg }"
                  alt="Second slide" style="height: 300px; width: 100%;">
              </div>
            </c:if>
            <c:if test="${i.index==2 }">
              <div class="item">
                <img src="/docs/upload/${ban.banImg }" alt="Third slide"
                  style="height: 300px; width: 100%;">
              </div>
            </c:if>
          </c:forEach>
        </div>
      </div>

      <div class="col-sm-3">
        <div class="bg-white"
          style=" margin-bottom: 15px; margin-top: -11px;">
          <div class="table" style="padding:15px;">
            <ul class="list-unstyled table-cell"
              style="text-align: center; width: 50%; padding: 25px 0px; border-right: 1px solid rgb(212, 212, 212);">
              <li>${date }</li>
            </ul>
            <div id="signIn" class="table-cell"
              style="text-align: center;">
              <c:if test="${sessionScope.uId==null }">
                <a href="bbs!showBBSLogin.action"><button
                    type="button" class="btn btn-primary">√&nbsp;&nbsp;&nbsp;&nbsp;签到</button></a>
                <br>
                <span style="display: block;margin-top:15px;">已签到0天</span>
              </c:if>
              <c:if test="${sessionScope.uId!=null }">
                <c:if test="${sessionScope.signInFlag }">
                  <button type="button" class="btn btn-primary"
                    disabled="disabled">√&nbsp;&nbsp;&nbsp;&nbsp;已签到</button>
                </c:if>
                <c:if test="${!sessionScope.signInFlag }">
                  <button type="button" class="btn btn-primary"
                    onclick="signIn(${sessionScope.uId })">√&nbsp;&nbsp;&nbsp;&nbsp;签到</button>
                </c:if>
                <br>
                <span style="display: block;margin-top:15px;">已连续签到${sessionScope.runningDays
                  }天</span>
              </c:if>

            </div>
          </div>
        </div>
        <div class="bg-white" style="height: 185px;">热门板块</div>
      </div>
    </div>

    <div class="row">
      <div class="col-sm-9">
        <div>
          <c:forEach items="${topPosts }" var="tps">
            <div class="bg-white"
              style="height: 200px; margin-top: 15px;">
              置顶帖----${tps[0] } 作者：${tps[1].uagname }（${tps[1].uname }）
              发帖时间：
              <f:formatDate value="${tps[2] }" pattern="yy-MM-dd HH:mm"></f:formatDate>
              点赞数：${tps[3] } ${tps[4] }
            </div>
          </c:forEach>
        </div>
        <div id="showPost">
          <c:forEach items="${posts }" var="pos">
            <div class="bg-white"
              style="height: 200px; margin-top: 15px;">
              ${pos[0] } 作者：${pos[1].uagname }（${pos[1].uname }） 发帖时间：
              <f:formatDate value="${pos[2] }" pattern="yy-MM-dd HH:mm"></f:formatDate>
              点赞数：${pos[3] } ${pos[4] }
            </div>
          </c:forEach>
          <div align="center" style="margin-top: 15px">

            <a style="cursor: pointer; text-decoration: none;"
              onclick="queryPost('1')">首页</a> &emsp;

            <c:if test="${pb.totalPageCount<=5 }">
              <c:set var="i" value="1"></c:set>
              <c:forEach begin="0" end="${pb.totalPageCount-1 }">
                <c:if test="${i==1 }">
                  <span style="color: gray">${i }</span>
                  &emsp;
                </c:if>
                <c:if test="${i!=1 }">
                  <a
                    style="cursor: pointer;
                    text-decoration: none;"
                    onclick="queryPost(${i })">${i }</a>
                  &emsp;
                </c:if>
                <c:set var="i" value="${i+1}"></c:set>
              </c:forEach>
            </c:if>
            <c:if test="${pb.totalPageCount>5 }">
              <c:set var="i" value="1"></c:set>
              <c:forEach begin="0" end="4">
                <c:if test="${i==1 }">
                  <span style="color: gray">${i }</span>
                  &emsp;
                </c:if>
                <c:if test="${i!=1 }">
                  <a id="c${i }"
                    style="cursor: pointer;
                    text-decoration: none; color:"
                    onclick="queryPost(${i })">${i }</a>
                  &emsp;
                </c:if>
                <c:set var="i" value="${i+1}"></c:set>
              </c:forEach>
            </c:if>



            <!--  
            <c:if test="${pb.currentPageNum<=1}">
              <span style="color: gray">上一页</span>
            </c:if>

            <c:if test="${pb.currentPageNum>1}">
              <a style="cursor: pointer; text-decoration: none;"
                onclick="queryPost(${pb.currentPageNum-1})">上一页</a>
            </c:if>

            <c:if test="${pb.currentPageNum<pb.totalPageCount}">
              <a style="cursor: pointer; text-decoration: none;"
                onclick="queryPost(${pb.currentPageNum+1})">下一页</a>
            </c:if>

            <c:if test="${pb.currentPageNum>=pb.totalPageCount}">
              <span style="color: gray">下一页</span>
            </c:if>
            
  -->
            <a style="cursor: pointer; text-decoration: none;"
              onclick="queryPost(${pb.totalPageCount})">末页</a>
          </div>
        </div>
      </div>
      <div class="col-sm-3">
        <div class="bg-white" style="height: 600px; margin-top: 15px;">
          最新活动:<br />
          <c:forEach items="${topEvents }" var="tpe">
            <div style="margin-top: 20px">
              《${tpe.etitle }》 <br /> <a href="">${tpe.econtent.toString()
                .replaceAll("<.*?>", "").substring(0, 20) }……</a>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>

  <div class="footer bg-white">
    <div class="row">
      <div class="col-xs-4 col-xs-offset-2 col-sm-3">
        <c:forEach items="${qrcode1 }" var="qr1">
          <img src="/docs/upload/${qr1.qrImg }" class="img-responsive"
            alt="nbmlnx_service" width="90px" align="bottom">
          <p style="font-size:11px;margin: 12px;">${qr1.qrName }</p>
        </c:forEach>
      </div>

      <div class="col-xs-4 col-sm-3">
        <c:forEach items="${qrcode2 }" var="qr2">
          <img src="/docs/upload/${qr2.qrImg }" class="img-responsive"
            alt="nbmlnx_service" width="90px" align="bottom">
          <p style="font-size:11px;margin: 12px;">${qr2.qrName }</p>
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
            <p style="font-size:10px">
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
  <script type="text/javascript" src="js/bbs_init.js"></script>
  <script type="text/javascript" src="js/mybbs.js"></script>
</body>
</html>
