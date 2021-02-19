<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="<c:url value="/front?command=${pageScope.command}" /> " method="post">
    <h3>Simple login form</h3>
    <p>
        <label for="login">Login</label>
    </p>
    <p>
        <input id="login" name="login" type="text" placeholder="Enter login.."/>
    </p>

    <p>
        <label for="password">Password</label>
    </p>
    <p>
        <input id="password" name="password" type="password" placeholder="Enter password.."/>
    </p>
    <input type="submit" value="${pageScope.command}">
</form>
