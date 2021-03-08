<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 26/02/2021
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <div class="nav-item dropdown  navbar-brand">
            <button class="btn btn-outline-dark dropdown-toggle" type="button" id="userDropdownMenuButton"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false" style="text-transform: capitalize"><c:out value = "${sessionScope.principle.credential.login}"/>
            </button>
            <div class="dropdown-menu" aria-labelledby="userDropdownMenuButton">
<%--                <a class="dropdown-item" href="?command=Logout">Personal Info</a>--%>
                <span id="logOut" class="dropdown-item cursor-pointer">Logout</span>
            </div>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                    <a class="nav-link" href="#">Tasks</a>
                    <a class="nav-link" href="#">Users</a>

            </div>
        </div>
    </div>

</nav>
</body>
</html>
