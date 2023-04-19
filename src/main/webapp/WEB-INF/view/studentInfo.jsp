<%--
  Created by IntelliJ IDEA.
  User: seungyeon
  Date: 2023/04/18
  Time: 6:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border-collapse:collapse;
            width:400px;
        }
        table th, td{
            border: 1px;
            border-style: solid;
            border-color: thistle;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/loginInfo.jsp"/>
<h1>회원 정보 조회</h1>
<table>
    <tr>
        <td>아이디</td>
        <td>${student.id}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${student.name}</td>
    </tr>
    <tr>
        <td>성별</td>
        <td>${student.gender}</td>
    </tr>
    <tr>
        <td>나이</td>
        <td>${student.age}</td>
    </tr>
    <tr>
        <td>
            생성시간
        </td>
        <td>
            ${student.createdAt}
        </td>
    </tr>
</table>
</br>
<a href="/student/list">리스트로 다시 이동</a>
<a href="/student/update/${student.id}">수정</a>
<form name="form" action="/student/delete/${student.id}" method="post">
    <button type="submit" name = "btnDelete">삭제</button>
</form>

</body>
</html>
