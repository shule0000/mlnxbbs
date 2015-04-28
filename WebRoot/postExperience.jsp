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
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<link href="css/bbs_topic.css" type="text/css" rel="stylesheet" />
<link href="css/font-awesome.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
  href="CLEditor1_3_0/jquery.cleditor.css" />
</head>
<body>

  <nav class="navbar navbar-default" style="height: 70px;"
    role="navigation">
    <div class="navbar-header left" id="top">
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
          <input type="hidden" id="type1" value="2">
          <input type="hidden" id="type2" value="0">
          <input type="hidden" id="type3" value="0">
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
      <div class="col-sm-9">
        <div class="bg-white">
          <ul class="nav nav-tabs nav-topic">
            <li><a style="cursor: pointer;"
              onclick="changeType2(0)">全部</a></li>
            <li><a style="cursor: pointer;"
              onclick="changeType2(1)">求助</a></li>
            <li><a style="cursor: pointer;"
              onclick="changeType2(2)">讨论</a></li>
            <li><a style="cursor: pointer;"
              onclick="changeType2(3)">专业产品</a></li>
          </ul>

          <ul class="nav nav-tabs nav-topic-model">
            <li><a style="cursor: pointer;"
              onclick="changeType3(0)">全部</a></li>
            <li><a style="cursor: pointer;"
              onclick="changeType3(1)">热门</a></li>
            <li><a style="cursor: pointer;"
              onclick="changeType3(2)">精华</a></li>
            <li style="float: right;"><a href="#doPost"><button
                  type="button" class="btn btn-primary">
                  <span class="fa fa-pencil"></span>&nbsp;&nbsp;&nbsp;&nbsp;发&nbsp;&nbsp;&nbsp;&nbsp;帖
                </button></a></li>
          </ul>

          <div id="showPostPdu">
            <ul id="bbs_content" class="list-unstyled">
              <c:forEach items="${posts }" var="pos">
                <li><div
                    class="col-sm-9 bg-white pd15 border-bottom">
                    <a><img style="width:60px"
                      src="/docs/upload/${pos[1].uicon }" alt="头像" /></a>
                    <p>
                      <a
                        href="bbs!showPostContent.action?poId=${pos[7] }"
                        target="_blank">${pos[0] }</a>
                    </p>
                    <p>
                      <span style="margin: 0px 15px;">发帖人：${pos[1].uagname
                        }</span> <em style="margin-right: 15px;">点赞数：${pos[3]
                        }</em>|<em style="margin: 0px 15px;">回复数：${pos[5]
                        }</em>
                    </p>
                  </div>
                  <div class="col-sm-3 bg-white pd15 border-bottom">
                    <div style="height: 60px; float: right;">
                      <f:formatDate value="${pos[2] }"
                        pattern="yyyy-MM-dd HH:mm" />
                    </div>
                  </div></li>
              </c:forEach>
            </ul>

            <c:if test="${pb.totalPageCount>0 }">
              <div align="center" style="margin-top: 15px">
                <a style="cursor: pointer; text-decoration: none;"
                  onclick="queryPostPdu('1')">首页</a> &emsp;

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
                        onclick="queryPostPdu(${i })">${i }</a>
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
                        onclick="queryPostPdu(${i })">${i }</a>
                      &emsp;
                    </c:if>
                    <c:set var="i" value="${i+1}"></c:set>
                  </c:forEach>
                </c:if>
                <a style="cursor: pointer; text-decoration: none;"
                  onclick="queryPostPdu(${pb.totalPageCount})">末页</a>
              </div>
            </c:if>

          </div>
        </div>
        <div id="doPost">

          <p>标题</p>
          <c:if test="${sessionScope.uId==null }">
            <input type="text" id="title" name="title"
              disabled="disabled" value="请先登录！">
          </c:if>
          <c:if test="${sessionScope.uId!=null }">
            <input type="text" id="title" name="title"
              onfocus="forDoPost()">
          </c:if>
          <span id="checkTitle"></span>

          <textarea id="content" name="content"></textarea>

          <span id="checkPost"></span> <br>
          <c:if test="${sessionScope.uId==null }">
            <a onclick="goLogin()" style="cursor: pointer;">发帖请先登录！</a>
          </c:if>
          <c:if test="${sessionScope.uId!=null }">
            <input type="submit" value="发帖" onclick="doPost()">
          </c:if>

        </div>
      </div>

      <div class="col-sm-3">
        <div class="bg-white"
          style="margin-bottom: 15px; margin-top: -11px;">
          <div class="table" style="padding: 15px;">
            <ul class="list-unstyled table-cell"
              style="text-align: center; width: 50%; padding: 25px 0px; border-right: 1px solid rgb(212, 212, 212);">
              <li>${date }</li>
            </ul>
            <div id="signIn" class="table-cell"
              style="text-align: center;">
              <c:if test="${sessionScope.uId==null }">
                <button onclick="goLogin()" type="button"
                  class="btn btn-primary">√&nbsp;&nbsp;&nbsp;&nbsp;签到</button>
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
        <div class="bg-white" style="height: 100%;">
          <div class="pd15">
            <em>板块信息</em><br>
            <hr>
            版主： aerohobby, 风火轮<br> 简介：
            【产品讨论】是为进阶型玩家设立的一个技术交流，生活分享版块。这里有一堆志同道合的好飞友和你一起讨论各种产品话题，这里有一整支专业的技术支持团队为你答疑解惑，这里还有一群因大疆无人机相识的小伙伴们陪你分享飞行生活的点点滴滴。<br>
            ----------本版规则----------<br>
            1.转帖请注明作者，出处，请勿贴外链。如未能注明而导致的版权问题，由发帖者自行承担。<br>
            2.禁止发布人身攻击的言辞，发帖或回帖中出现严重的政治言论。<br>
            3.请勿纯表情以及其他无意义的发帖回帖，标题党或发布严重误导的帖子。<br>
            4.请勿连续转载或发表内容相似的主题，违者将直接删帖。<br> 5.请勿在标题使用大量任何无意义符号加长标题。<br>
            6.禁止在本区发布任何涉及交易信息的帖子。<br> 7.禁止一句话的水帖，以及毫无意义的灌水帖。<br>

          </div>
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
  <script type="text/javascript" src="CLEditor1_3_0/jquery.min.js"></script>
  <script type="text/javascript"
    src="CLEditor1_3_0/jquery.cleditor.min.js"></script>
  <script type="text/javascript">
     $(document).ready(function() {
        $("#content").cleditor();
      });
  </script>
</body>
</html>
