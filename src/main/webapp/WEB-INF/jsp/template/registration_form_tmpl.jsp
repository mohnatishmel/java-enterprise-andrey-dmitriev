<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="registrationForm" action="<c:url value="/" /> " method="post" class="input-form">
    <h3>Register</h3>

    <c:import url="/WEB-INF/jsp/template/error_tmpl.jsp"/>

    <div class="input-group">
        <%--        <label for="login">Enter Login</label>--%>
        <input id="loginInput" name="login" type="text" placeholder="Enter login.."/>
    </div>

    <div class="input-group">
        <%--        <label for="password">Enter password</label>--%>
        <input id="passwordInput" name="password" type="password" placeholder="Enter password.."/>
    </div>

    <input name="command" type="hidden" value="Register"/>

    <div class="form-group">
        <input class="btn btn-secondary form-control" type="submit" value="   Register   ">
    </div>
    <div class="form-group">
        <span class="btn btn-outline-secondary form-control">Login</span>
    </div>
</form>