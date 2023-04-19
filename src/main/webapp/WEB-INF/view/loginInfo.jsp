<%--
  Created by IntelliJ IDEA.
  User: seungyeon
  Date: 2023/04/18
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ page import="com.nhnacademy.studentmvc.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login-info">
  <span>아이디 : ${user.userId}</span><span>&nbsp;&nbsp;&nbsp;&nbsp;비밀번호: ${user.userName}</span><span></span>
  <form method="post" id="logoutForm" action="/logout">
    <button type="submit" id="btn-logout">로그아웃</button>
  </form>
</div>


