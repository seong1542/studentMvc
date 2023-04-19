<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: visualp
  Date: 2023/04/11
  Time: 7:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>login</title>
</head>
<body>
<div class="login-container">
    <form:form method="post" action="/login" modelAttribute="loginRequest" >
        <p><form:input path="userId" type="text" placeholder="회원아이디"  />
            <form:errors path="userId" cssClass="error-message" />
        </p>
        <p><form:input path="userPassword" type="password" placeholder="비밀번호" />
            <form:errors path="userPassword" cssClass="error-message" />
        </p>
        <c:if test="${not empty message}">
            <p class="error-message" >${message}</p>
        </c:if>
        <p><button type="submit">로그인</button> </p>
    </form:form>
</div>
</body>
</html>