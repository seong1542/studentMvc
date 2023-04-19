<%--
  Created by IntelliJ IDEA.
  User: seungyeon
  Date: 2023/04/19
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <title>Internal Server Error</title>
</head>
<body>
<h1>Internal Server error</h1>
<p>${exceptionMessage}</p>
<a href="/student/list">리스트로 돌아가기</a>
<p></p>
</body>
</html>
