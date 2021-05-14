<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 11/05/2021
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body {
            background: #eee;
            font-family: "Open Sans", sans-serif;
        }

        .container {
            margin: 4em auto 0;
            max-width: 70%;
        }
    </style>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${!empty requestScope.errorMessage}">
            <h1>${requestScope.errorMessage}</h1>
        </c:when>
        <c:otherwise>
            <h1>Oops,</h1>
            <h1>Something went wrong! :(</h1>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
