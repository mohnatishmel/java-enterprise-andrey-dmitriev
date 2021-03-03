<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="<c:url value="/" /> " method="post" class="input-form">
    <h3>Login</h3>

    <div class="input-group">
<%--        <label for="login">Enter Login</label>--%>
        <input id="login" name="login" type="text" placeholder="Enter login.."/>
    </div>

    <div class="input-group">
<%--        <label for="password">Enter password</label>--%>
        <input id="password" name="password" type="password" placeholder="Enter password.."/>
    </div>

    <div class="btn-outline-secondary">
    <input name="command" type="hidden" value="Login"/>
    <input class="form-control" type="submit"  value="   Login   ">
    </div>
</form>
