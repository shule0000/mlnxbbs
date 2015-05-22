<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>

<c:set scope="page" value="0" var="num"></c:set>
<c:forEach items="${mores }" var="mor">
  <c:if test="${mor[0] == position }">
    <c:if test="${num < 5 }">
      <div>
        <c:if test="${sessionScope.uId==null }">
          <img
            src="/docs/upload/${mor[1].uicon3
                        }"
            style="width:16px" />
          <a onclick="goLogin()" style="cursor: pointer;">${mor[1].uagname
            }</a>&emsp;To&emsp;<img
            src="/docs/upload/${mor[2].uicon3
                        }"
            style="width:16px" />
          <a onclick="goLogin()" style="cursor: pointer;">${mor[2].uagname
            }</a>：${mor[3] }&emsp;
                          <f:formatDate value="${mor[4] }"
            pattern="yyyy-MM-dd HH:mm:ss" />
        </c:if>
        <c:if test="${sessionScope.uId!=null }">
          <img
            src="/docs/upload/${mor[1].uicon3
                        }"
            style="width:16px" />
          <a
            onclick="showRspToU('${mor[1].uagname
                            }', '${position }', '${poId }', '${mor[1].uid }')"
            style="cursor: pointer;">${mor[1].uagname }</a>&emsp;To&emsp;<img
            src="/docs/upload/${mor[2].uicon3
                        }"
            style="width:16px" />
          <a
            onclick="showRspToU('${mor[2].uagname
                            }', '${position }', '${poId }', '${mor[2].uid }')"
            style="cursor: pointer;">${mor[2].uagname }</a>：${mor[3] }&emsp;
                          <f:formatDate value="${mor[4] }"
            pattern="yyyy-MM-dd HH:mm:ss" />
        </c:if>
      </div>
    </c:if>
    <c:set scope="page" value="${num+1 }" var="num"></c:set>
  </c:if>
</c:forEach>

<div id="continue${position }"></div>

<div id="showMore${position }">
  <c:if test="${num>5 }">
    <a style="cursor: pointer;"
      onclick="showMore('${poId }', '${position }')">显示更多</a>
  </c:if>

</div>
