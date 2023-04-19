<%--
  Created by IntelliJ IDEA.
  User: seungyeon
  Date: 2023/04/18
  Time: 6:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student/RegisterOrUpdate</title>
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
<c:set var="student" value="${requestScope.get('student')}"/>
<c:choose>
    <c:when test="${empty student}">
        <c:set var="action" value="/student/register"/>
        <c:set var="submit" value="등록"/>
        <c:set var="id_readonly" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/student/update/${student.id}"/>
        <c:set var="submit" value="수정"/>
        <c:set var="id_readonly" value="readonly"/>
        <c:choose>
            <c:when test="${student.gender eq 'M'}">
                <c:set var="man_check" value="checked"/>
                <c:set var="woman_check" value=""/>
            </c:when>
            <c:otherwise>
                <c:set var="man_check" value=""/>
                <c:set var="woman_check" value="checked"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<form name="form" action='${action}' method="post">
    <table>
        <tr>
            <td>아이디</td>
            <td><input type="text" name = "id" value ='${student.id}'  ${id_readonly} required/></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><input type="text" name = "name" value='${student.name}' required/></td>
        </tr>
        <tr>
            <td>성별</td>
            <td>
                <input type="radio" id="M" name="gender" value="M" ${man_check}><label for="M">남</label>
                <input type="radio" id="F" name="gender" value="F" ${woman_check}><label for="F">여</label>
                <input type="radio" id="C" name="gender" value="C" ${woman_check}><label for="C">??</label>
            </td>


        </tr>
        <tr>
            <td>나이</td>
            <td><input type="text" name = "age" value = "${student.age}" required/></td>
        </tr>

    </table>
    </br>
    <button type="submit">${submit}</button>
    <a href="/student/list">리스트로 다시 이동</a>
</form>
</body>
</html>
