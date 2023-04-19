<%--
  Created by IntelliJ IDEA.
  User: seungyeon
  Date: 2023/04/18
  Time: 5:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>student/list</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/loginInfo.jsp"/>
<h1>학생 리스트</h1>
<p><a href="/student/register">학생(등록)</a></p>
<table border="1">
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="Student" items="${requestScope.get('students')}">
        <tr>
            <td>${Student.id}</td>
            <td>${Student.getName()}</td>
            <td>${Student.getGender()}</td>
            <td>${Student.getAge()}</td>
            <form method="get" action="/student/view">
                <td><a href ='/student/view/${Student.id}'/>조회</td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
