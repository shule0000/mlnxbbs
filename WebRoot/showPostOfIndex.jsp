<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<c:forEach items="${posts }" var="pos">
  <div class="bg-white" style="height: 220px; margin-top: 15px;">
    <div class="col-sm-5">
      <img src="/docs/upload/${pos[1].uicon}" style="height: 220px;">
    </div>
    <div class="col-sm-7">
      <div class="post-title">${pos[0] }</div>
      <div class="post-header">
        作者：${pos[1].uagname }（${pos[1].uname }） 发帖时间：
        <f:formatDate value="${pos[2] }" pattern="yy-MM-dd HH:mm"></f:formatDate>
        点赞数：${pos[3] }
      </div>
      <div class="post-body">${pos[4] }</div>
      <div class="post-more">
        <a href="bbs!showPostContent.action?poId=${pos[5] }"
          target="_blank">阅读更多>></a>
      </div>
    </div>
  </div>
</c:forEach>
<div align="center" style="margin-top: 15px">
  <a style="cursor: pointer; text-decoration: none;"
    onclick="queryPost('1')">首页</a> &emsp;


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
          onclick="queryPost(${i })">${i }</a>
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
            onclick="queryPost(${i })">${i }</a>
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
            onclick="queryPost(${i })">${i }</a>
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
            onclick="queryPost(${i })">${i }</a>
          &emsp;
        </c:if>
        <c:set var="i" value="${i+1}"></c:set>
      </c:forEach>
    </c:if>
  </c:if>
  <a style="cursor: pointer; text-decoration: none;"
    onclick="queryPost(${pb.totalPageCount})">末页</a>
</div>
