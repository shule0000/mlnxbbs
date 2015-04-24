<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>

<ul id="bbs_content" class="list-unstyled">
  <c:forEach items="${posts }" var="pos">
    <li><div class="col-sm-9 bg-white pd15 border-bottom">
        <a><img style="width:60px"
          src="/docs/upload/${pos[1].uicon }" alt="头像" /></a>
        <p>
          <a href="bbs!showPostContent.action?poId=${pos[7] }"
            target="_blank">${pos[0] }</a>
        </p>
        <p>
          <span style="margin: 0px 15px;">发帖人：${pos[1].uagname }</span>
          <em style="margin-right: 15px;">点赞数：${pos[3] }</em>|<em
            style="margin: 0px 15px;">回复数：${pos[5] }</em>
        </p>
      </div>
      <div class="col-sm-3 bg-white pd15 border-bottom">
        <div style="height: 60px; float: right;">
          <f:formatDate value="${pos[2] }" pattern="yyyy-MM-dd HH:mm" />
        </div>
      </div></li>
  </c:forEach>
</ul>
<div align="center" style="margin-top: 15px">
  <a style="cursor: pointer; text-decoration: none;"
    onclick="queryPostPdu('1')">首页</a> &emsp;


  <c:if test="${pb.totalPageCount<=5 }">
    <c:set var="i" value="1"></c:set>
    <c:forEach begin="0" end="${pb.totalPageCount-1 }">
      <c:if test="${i==pb.currentPageNum }">
        <span style="color: gray">${i }</span>
        &emsp;
      </c:if>
      <c:if test="${i!=pb.currentPageNum }">
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
    <c:if test="${pb.currentPageNum<3 }">
      <c:set var="i" value="1"></c:set>
      <c:forEach begin="0" end="4">
        <c:if test="${i==pb.currentPageNum }">
          <span style="color: gray">${i }</span>
          &emsp;
        </c:if>
        <c:if test="${i!=pb.currentPageNum }">
          <a
            style="cursor: pointer;
                    text-decoration: none;"
            onclick="queryPostPdu(${i })">${i }</a>
          &emsp;
        </c:if>
        <c:set var="i" value="${i+1}"></c:set>
      </c:forEach>
    </c:if>
    <c:if
      test="${pb.currentPageNum>=3 && pb.currentPageNum<=pb.totalPageCount-2}">
      <c:set var="i" value="${pb.currentPageNum-2 }"></c:set>
      <c:forEach begin="0" end="4">
        <c:if test="${i==pb.currentPageNum }">
          <span style="color: gray">${i }</span>
          &emsp;
        </c:if>
        <c:if test="${i!=pb.currentPageNum }">
          <a
            style="cursor: pointer;
                    text-decoration: none;"
            onclick="queryPostPdu(${i })">${i }</a>
          &emsp;
        </c:if>
        <c:set var="i" value="${i+1}"></c:set>
      </c:forEach>
    </c:if>
    <c:if test="${pb.currentPageNum>pb.totalPageCount-2 }">
      <c:set var="i" value="${pb.totalPageCount-4 }"></c:set>
      <c:forEach begin="0" end="4">
        <c:if test="${i==pb.currentPageNum }">
          <span style="color: gray">${i }</span>
          &emsp;
        </c:if>
        <c:if test="${i!=pb.currentPageNum }">
          <a
            style="cursor: pointer;
                    text-decoration: none;"
            onclick="queryPostPdu(${i })">${i }</a>
          &emsp;
        </c:if>
        <c:set var="i" value="${i+1}"></c:set>
      </c:forEach>
    </c:if>
  </c:if>
  <a style="cursor: pointer; text-decoration: none;"
    onclick="queryPostPdu(${pb.totalPageCount})">末页</a>
</div>
