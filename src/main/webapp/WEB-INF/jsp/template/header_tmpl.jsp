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
    <div class="nav-item dropdown  navbar-brand">
        <button class="btn btn-outline-dark dropdown-toggle" type="button" id="dropdownMenuButton"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false" style="text-transform: capitalize">
            <c:out value="${sessionScope.principle.login}"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="?command=Logout">Personal Info</a>
            <a class="dropdown-item" href="?command=Logout">Logout</a>
        </div>

    </div>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Tasks<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Users</a>
            </li>

        </ul>

    </div>
</nav>
</body>
</html>
