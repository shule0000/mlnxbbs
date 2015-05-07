<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>

<c:if test="${existPost==0 }">无相关查询结果！</c:if>
<c:if test="${existPost==1 }">
  <c:forEach items="${posts }" var="pos">
    <div class="bg-white" style="height: 200px; margin-top: 15px;">
      ${pos[0].replaceAll(sessionScope.key, sessionScope.replace) }
      作者：${pos[1].uagname.replaceAll(sessionScope.key,
      sessionScope.replace) }
      （${pos[1].uname.replaceAll(sessionScope.key, sessionScope.replace)
      }） 发帖时间：
      <c:set var="time" value="${pos[2] }"></c:set>
      ${time.replaceAll(sessionScope.key, sessionScope.replace) }
      点赞数：${pos[3] } <a
        href="bbs!showPostContent.action?poId=${pos[5] }"
        target="_blank">${pos[4] }</a>
    </div>
  </c:forEach>
  <div align="center" style="margin-top: 15px">

    <a style="cursor: pointer; text-decoration: none;"
      onclick="querySearchPost('1')">首页</a> &emsp;


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
            onclick="querySearchPost(${i })">${i }</a>
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
              onclick="querySearchPost(${i })">${i }</a>
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
              onclick="querySearchPost(${i })">${i }</a>
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
              onclick="querySearchPost(${i })">${i }</a>
            &emsp;
          </c:if>
          <c:set var="i" value="${i+1}"></c:set>
        </c:forEach>
      </c:if>
    </c:if>
    <a style="cursor: pointer; text-decoration: none;"
      onclick="querySearchPost(${pb.totalPageCount})">末页</a>
  </div>
</c:if>