<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="<c:url value="/" /> " method="post">
    <h3>Login form</h3>
    <div class="form-group">
        <label for="login">Enter Login</label>
        <input id="login" name="login" type="text" class="form-control" placeholder="Enter login.."/>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input id="password" name="password" type="password" class="form-control" placeholder="Enter password.."/>
    </div>

    <input name="command" type="hidden" value="Login"/>

    <input type="submit" class="btn btn-primary" value="Login">
</form>
