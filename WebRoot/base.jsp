<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt"%>

<body>
  <div id="ct" class="ct2_a wp cl hmt20">
    <div id="changePage" class="mn">
      <div class="bm bw0">
        <h1 class="mt">个人资料</h1>
        <!--don't close the div here-->
        <ul class="tb cl">
          <li><a href="#">基本资料</a></li>
          <li><a href="#">联系方式</a></li>
          <li><a href="#">教育情况</a></li>
          <li><a href="#">工作情况</a></li>
          <li><a href="#">个人信息</a></li>
        </ul>
        <iframe src="index_1.html" id="frame_profile"
          name="frame_profile" style="display: none;"></iframe>
        <c:if test="${info==false }">
          <form action="bbs!saveBase.action" method="post">
            <input value="17b588b1" name="formhash" type="hidden">
            <table class="tfm" id="profilelist" cellpadding="0"
              cellspacing="0">
              <tbody>
                <tr>
                  <th>用户昵称</th>
                  <td><input name="uagname" class="px" value=""
                    id="uagname" type="text"></td>
                  <td>&nbsp;</td>
                </tr>
                <tr id="tr_realname">
                  <th id="th_realname">真实姓名</th>
                  <td id="td_realname"><input name="realname"
                    id="realname" class="px" value="" tabindex="1"
                    type="text">
                    <div class="rq mtn" id="showerror_realname"></div>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_realname">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr id="tr_gender">
                  <th id="th_gender">性别</th>
                  <td id="td_gender"><select name="gender"
                    id="gender" class="ps" tabindex="1"><option
                        value="保密" selected="selected">保密</option>
                      <option value="男">男</option>
                      <option value="女">女</option></select>
                    <div class="rq mtn" id="showerror_gender"></div>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_gender">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr id="tr_residecity">
                  <th id="th_residecity">居住地</th>
                  <td id="td_residecity">
                    <p id="residedistrictbox">
                      <select name="resideprovince" id="resideprovince"
                        class="ps"
                        onchange="showdistrict('residedistrictbox', ['resideprovince', 'residecity', 'residedist', 'residecommunity'], 1, 1, 'reside')"
                        tabindex="1"><c:forEach
                          items="${provinces }" var="prov">
                          <option value="${prov.provinceId }">${prov.provinceName
                            }</option>
                        </c:forEach></select>&nbsp;&nbsp;
                    </p>
                    <div class="rq mtn" id="showerror_residecity"></div>
                    <p class="d"></p>
                  </td>
                  <td class="p"><select name="privacy_residecity">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr id="tr_birthday">
                  <th id="th_birthday">生日</th>
                  <td id="td_birthday"><input type="date"
                    name="birthday" value="">
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_birthday">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr id="tr_birthcity">
                  <th id="th_birthcity">出生地</th>
                  <td id="td_birthcity">
                    <p id="birthdistrictbox">
                      <select name="birthprovince" id="birthprovince"
                        class="ps"
                        onchange="showdistrict('birthdistrictbox', ['birthprovince', 'birthcity', 'birthdist', 'birthcommunity'], 1, 1, 'birth')"
                        tabindex="1"><option value="">-省份-</option>
                        <option value="北京市">北京市</option>
                        <option value="天津市">天津市</option>
                        <option value="河北省">河北省</option>
                        <option value="山西省">山西省</option>
                        <option value="内蒙古自治区">内蒙古自治区</option>
                        <option value="辽宁省">辽宁省</option>
                        <option value="吉林省">吉林省</option>
                        <option value="黑龙江省">黑龙江省</option>
                        <option value="上海市">上海市</option>
                        <option value="江苏省">江苏省</option>
                        <option value="浙江省">浙江省</option>
                        <option value="安徽省">安徽省</option>
                        <option value="福建省">福建省</option>
                        <option value="江西省">江西省</option>
                        <option value="山东省">山东省</option>
                        <option value="河南省">河南省</option>
                        <option value="湖北省">湖北省</option>
                        <option value="湖南省">湖南省</option>
                        <option value="广东省">广东省</option>
                        <option value="广西壮族自治区">广西壮族自治区</option>
                        <option value="海南省">海南省</option>
                        <option value="重庆市">重庆市</option>
                        <option value="四川省">四川省</option>
                        <option value="贵州省">贵州省</option>
                        <option value="云南省">云南省</option>
                        <option value="西藏自治区">西藏自治区</option>
                        <option value="陕西省">陕西省</option>
                        <option value="甘肃省">甘肃省</option>
                        <option value="青海省">青海省</option>
                        <option value="宁夏回族自治区">宁夏回族自治区</option>
                        <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                        <option value="台湾省">台湾省</option>
                        <option value="香港特别行政区">香港特别行政区</option>
                        <option value="澳门特别行政区">澳门特别行政区</option>
                        <option value="海外">海外</option>
                        <option value="其他">其他</option></select>&nbsp;&nbsp;
                    </p>
                    <div class="rq mtn" id="showerror_birthcity"></div>
                    <p class="d"></p>
                  </td>
                  <td class="p"><select name="privacy_birthcity">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr id="tr_affectivestatus">
                  <th id="th_affectivestatus">情感状态</th>
                  <td id="td_affectivestatus"><input
                    name="affectivestatus" id="affectivestatus"
                    class="px" value="" tabindex="1" type="text">
                    <div class="rq mtn" id="showerror_affectivestatus"></div>
                    <p class="d"></p></td>
                  <td class="p"><select
                    name="privacy_affectivestatus">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr id="tr_lookingfor">
                  <th id="th_lookingfor">交友目的</th>
                  <td id="td_lookingfor"><input name="lookingfor"
                    id="lookingfor" class="px" value="" tabindex="1"
                    type="text">
                    <div class="rq mtn" id="showerror_lookingfor"></div>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_lookingfor">
                      <option value="0">公开</option>
                      <option value="1">好友可见</option>
                      <option value="3">保密</option>
                  </select></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td colspan="2"><input name="profilesubmit"
                    value="true" type="hidden">
                    <button type="submit" name="profilesubmitbtn"
                      id="profilesubmitbtn" value="true" class="pn pnc">
                      <strong>保存</strong>
                    </button> <span id="submit_result" class="rq"></span></td>
                </tr>
              </tbody>
            </table>
          </form>
        </c:if>

        <c:if test="${info==true }">
          <form action="bbs!saveBase.action" method="post">
            <input value="17b588b1" name="formhash" type="hidden">
            <table class="tfm" id="profilelist" cellpadding="0"
              cellspacing="0">
              <tbody>
                <tr>
                  <th>用户昵称</th>
                  <td><input name="uagname" class="px"
                    value="${user.uagname }" id="uagname" type="text"></td>
                  <td>&nbsp;</td>
                </tr>
                <tr id="tr_realname">
                  <th id="th_realname">真实姓名</th>
                  <td id="td_realname"><input name="realname"
                    id="realname" class="px"
                    value="${userinfo.urealname.substring(2) }"
                    tabindex="1" type="text">
                    <div class="rq mtn" id="showerror_realname"></div>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_realname">
                      <c:if
                        test="${userinfo.urealname.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.urealname.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.urealname.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr id="tr_gender">
                  <th id="th_gender">性别</th>
                  <td id="td_gender"><select name="gender"
                    id="gender" class="ps" tabindex="1">
                      <c:if test="${userinfo.usex.substring(2)=='保密' }">
                        <option value="保密" selected="selected">保密</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                      </c:if>
                      <c:if test="${userinfo.usex.substring(2)=='男' }">
                        <option value="保密">保密</option>
                        <option value="男" selected="selected">男</option>
                        <option value="女">女</option>
                      </c:if>
                      <c:if test="${userinfo.usex.substring(2)=='女' }">
                        <option value="保密">保密</option>
                        <option value="男">男</option>
                        <option value="女" selected="selected">女</option>
                      </c:if>
                  </select>
                    <div class="rq mtn" id="showerror_gender"></div>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_gender">
                      <c:if
                        test="${userinfo.usex.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.usex.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.usex.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr id="tr_residecity">
                  <th id="th_residecity">居住地</th>
                  <td id="td_residecity">
                    <p id="residedistrictbox">
                      <select name="resideprovince" id="resideprovince"
                        class="ps"
                        onchange="showdistrict('residedistrictbox', ['resideprovince', 'residecity', 'residedist', 'residecommunity'], 1, 1, 'reside')"
                        tabindex="1">
                        <c:forEach items="${provinces }" var="prov">
                          <c:if
                            test="${prov.provinceId==userinfo.uresidence.substring(2) }">
                            <option value="${prov.provinceId }"
                              selected="selected">${prov.provinceName
                              }</option>
                          </c:if>
                          <c:if
                            test="${prov.provinceId!=userinfo.uresidence.substring(2) }">
                            <option value="${prov.provinceId }">${prov.provinceName
                              }</option>
                          </c:if>
                        </c:forEach>
                      </select>&nbsp;&nbsp;
                    </p>
                    <div class="rq mtn" id="showerror_residecity"></div>
                    <p class="d"></p>
                  </td>
                  <td class="p"><select name="privacy_residecity">
                      <c:if
                        test="${userinfo.uresidence.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.uresidence.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.uresidence.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr id="tr_birthday">
                  <th id="th_birthday">生日</th>
                  <td id="td_birthday"><c:if
                      test="${userinfo.ubirthday.substring(2)=='null' }">
                      <input type="date" name="birthday" value="">
                    </c:if> <c:if
                      test="${userinfo.ubirthday.substring(2)!='null' }">
                      <input type="date" name="birthday"
                        value="${userinfo.ubirthday.substring(2, 12) }">
                    </c:if>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_birthday">
                      <c:if
                        test="${userinfo.ubirthday.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.ubirthday.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.ubirthday.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr id="tr_birthcity">
                  <th id="th_birthcity">出生地</th>
                  <td id="td_birthcity">
                    <p id="birthdistrictbox">
                      <select name="birthprovince" id="birthprovince"
                        class="ps"
                        onchange="showdistrict('birthdistrictbox', ['birthprovince', 'birthcity', 'birthdist', 'birthcommunity'], 1, 1, 'birth')"
                        tabindex="1"><c:forEach
                          items="${provinces }" var="prov">
                          <c:if
                            test="${prov.provinceId==userinfo.ubirthplace.substring(2) }">
                            <option value="${prov.provinceId }"
                              selected="selected">${prov.provinceName
                              }</option>
                          </c:if>
                          <c:if
                            test="${prov.provinceId!=userinfo.ubirthplace.substring(2) }">
                            <option value="${prov.provinceId }">${prov.provinceName
                              }</option>
                          </c:if>
                        </c:forEach></select>&nbsp;&nbsp;
                    </p>
                    <div class="rq mtn" id="showerror_birthcity"></div>
                    <p class="d"></p>
                  </td>
                  <td class="p"><select name="privacy_birthcity">
                      <c:if
                        test="${userinfo.ubirthplace.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.ubirthplace.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.ubirthplace.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr id="tr_affectivestatus">
                  <th id="th_affectivestatus">情感状态</th>
                  <td id="td_affectivestatus"><input
                    name="affectivestatus" id="affectivestatus"
                    class="px"
                    value="${userinfo.uremark1.substring(2) }"
                    tabindex="1" type="text">
                    <div class="rq mtn" id="showerror_affectivestatus"></div>
                    <p class="d"></p></td>
                  <td class="p"><select
                    name="privacy_affectivestatus">
                      <c:if
                        test="${userinfo.uremark1.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.uremark1.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.uremark1.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr id="tr_lookingfor">
                  <th id="th_lookingfor">交友目的</th>
                  <td id="td_lookingfor"><input name="lookingfor"
                    id="lookingfor" class="px"
                    value="${userinfo.uremark2.substring(2) }"
                    tabindex="1" type="text">
                    <div class="rq mtn" id="showerror_lookingfor"></div>
                    <p class="d"></p></td>
                  <td class="p"><select name="privacy_lookingfor">
                      <c:if
                        test="${userinfo.uremark2.substring(0, 1)=='0' }">
                        <option value="0" selected="selected">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.uremark2.substring(0, 1)=='1' }">
                        <option value="0">公开</option>
                        <option value="1" selected="selected">好友可见</option>
                        <option value="3">保密</option>
                      </c:if>
                      <c:if
                        test="${userinfo.uremark2.substring(0, 1)=='3' }">
                        <option value="0">公开</option>
                        <option value="1">好友可见</option>
                        <option value="3" selected="selected">保密</option>
                      </c:if>
                  </select></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td colspan="2"><input name="profilesubmit"
                    value="true" type="hidden">
                    <button type="submit" name="profilesubmitbtn"
                      id="profilesubmitbtn" value="true" class="pn pnc">
                      <strong>保存</strong>
                    </button> <span id="submit_result" class="rq"></span></td>
                </tr>
              </tbody>
            </table>
          </form>
        </c:if>
      </div>
    </div>
    <div class="appl">
      <div class="h-home-left">
        <h2 class="mt bbda">设置</h2>
        <ul>
          <li><a href="#maincontent" onclick="openpage('img')">修改头像</a></li>
          <li><a href="#maincontent" onclick="openpage('base')">个人资料</a></li>
          <li><a href="#maincontent">积分</a></li>
          <li><a href="#maincontent">用户组</a></li>
          <li><a href="#maincontent">隐私筛选</a></li>
          <li><a href="#maincontent">密码安全</a></li>
          <li><a href="#maincontent">QQ绑定</a></li>
        </ul>
      </div>
    </div>
  </div>
</body>
