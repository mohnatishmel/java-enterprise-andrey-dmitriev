<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 10/02/2021
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
</head>

<style>body {
    background: #eee
}</style>

<body>
<div class="wrapper">
    <div class="container">
        <h1>Welcome</h1>

<%--        <p style="color: red;">${errorString}</p>--%>

        <form class="form" method="POST" action="<c:url value="/login" />">
            <input type="text" placeholder="Username" name="userName" value= "${user.userName}" />
            <input type="password" placeholder="Password" name="password" value= "${user.password}"/>
            <button type="submit" id="login-button">Login</button>
        </form>
    </div>
</div>
</body>
</html>