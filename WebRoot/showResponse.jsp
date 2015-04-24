<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>

<div class="bg-white comment">
  <ul class="list-unstyled">
    <c:forEach items="${responses }" var="rsp">
      <li><div class="col-sm-9 bg-white pd15">
          <a href="#" style="float: left;"><img
            src="/docs/upload/${rsp[1].uicon }" alt="头像"
            style="width: 60px" /></a>
          <p>
            <a href="#" style="margin: 0px 15px;">${rsp[1].uagname }</a>

          </p>
        </div>
        <div class="col-sm-3 bg-white pd15">
          <div style="height: 60px; float: right;">
            <f:formatDate value="${rsp[3] }"
              pattern="yyyy-MM-dd HH:mm:ss" />
            &emsp; <span id="position">${rsp[0] }楼</span>
          </div>
        </div>
        <div class="pd15" style="margin-left: 75px">${rsp[2] }</div>
        <div>
          <c:set scope="page" value="0" var="num"></c:set>
          <c:forEach items="${childResponses }" var="cRsp">
            <c:if test="${cRsp[0] == rsp[0] }">
              <c:if test="${num < 5 }">
                <div>
                  <c:if test="${sessionScope.uId==null }">
                    <img
                      src="/docs/upload/${cRsp[1].uicon
                        }"
                      style="width:16px" />
                    <a onclick="goLogin()" style="cursor: pointer;">${cRsp[1].uagname
                      }</a>&emsp;To&emsp;<img
                      src="/docs/upload/${cRsp[2].uicon
                        }"
                      style="width:16px" />
                    <a onclick="goLogin()" style="cursor: pointer;">${cRsp[2].uagname
                      }</a>：${cRsp[3] }&emsp;
                          <f:formatDate value="${cRsp[4] }"
                      pattern="yyyy-MM-dd HH:mm:ss" />
                  </c:if>
                  <c:if test="${sessionScope.uId!=null }">
                    <img
                      src="/docs/upload/${cRsp[1].uicon
                        }"
                      style="width:16px" />
                    <a
                      onclick="showRspToU('${cRsp[1].uagname
                            }', '${rsp[0] }', '${poId }', '${cRsp[1].uid }')"
                      style="cursor: pointer;">${cRsp[1].uagname }</a>&emsp;To&emsp;<img
                      src="/docs/upload/${cRsp[2].uicon
                        }"
                      style="width:16px" />
                    <a
                      onclick="showRspToU('${cRsp[2].uagname
                            }', '${rsp[0] }', '${poId }', '${cRsp[2].uid }')"
                      style="cursor: pointer;">${cRsp[2].uagname }</a>：${cRsp[3] }&emsp;
                          <f:formatDate value="${cRsp[4] }"
                      pattern="yyyy-MM-dd HH:mm:ss" />
                  </c:if>
                </div>
              </c:if>
              <c:set scope="page" value="${num+1 }" var="num"></c:set>
            </c:if>
          </c:forEach>

          <input type="hidden" id="clickNum${rsp[0] }" value=1 />

          <div id="continue${rsp[0] }"></div>

          <div id="showMore${rsp[0] }">
            <c:if test="${num>5 }">
              <a style="cursor: pointer;"
                onclick="showMore('${poId}', '${rsp[0] }')">显示更多</a>
            </c:if>
          </div>

          <div>
            <span id="rspToU${rsp[0] }"></span><span
              id="checkRspToU${rsp[0] }"></span>
          </div>
        </div>
        <div class="pd15 border-bottom">
          <c:if test="${sessionScope.uId==null }">
            <div class="pob cl " style="margin-bottom: 20px">
              <em> <a href="#">举报</a> <a onclick="goLogin()"
                style="cursor: pointer;">回复</a> <a title="2 人 支持"
                class="replyadd" href="#">支持 <span
                  id="review_support_90172">2</span>
              </a> <a title="0 人 反对" class="replysubtract" href="#">反对 <span
                  id="review_against_90172">0</span>
              </a>
              </em>
            </div>
          </c:if>
          <c:if test="${sessionScope.uId!=null }">
            <div class="pob cl " style="margin-bottom: 20px">
              <em> <a href="#">举报</a> <a style="cursor: pointer;"
                onclick="showRspToU('${rsp[1].uagname
                        }', '${rsp[0] }', '${poId }', '${rsp[1].uid }')">回复</a>
                <a title="2 人 支持" class="replyadd" href="#">支持 <span
                  id="review_support_90172">2</span>
              </a> <a title="0 人 反对" class="replysubtract" href="#">反对 <span
                  id="review_against_90172">0</span>
              </a>
              </em>
            </div>
          </c:if>
        </div></li>
    </c:forEach>
  </ul>
</div>
<div align="center" style="margin-top: 15px">
  <a style="cursor: pointer; text-decoration: none;"
    onclick="queryRsp('1')">首页</a> &emsp;


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
          onclick="queryRsp(${i })">${i }</a>
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
            onclick="queryRsp(${i })">${i }</a>
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
            onclick="queryRsp(${i })">${i }</a>
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
            onclick="queryRsp(${i })">${i }</a>
          &emsp;
        </c:if>
        <c:set var="i" value="${i+1}"></c:set>
      </c:forEach>
    </c:if>
  </c:if>
  <a style="cursor: pointer; text-decoration: none;"
    onclick="queryRsp(${pb.totalPageCount})">末页</a>
</div>