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
<%--<nav class="navbar navbar-expand-lg navbar-light bg-header">--%>
<nav class="navbar navbar-light bg-header">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button id="searchBtn" class="btn my-2 my-sm-0 search-btn" type="button"
                    onmousedown="return false" onselectstart="return false">
<%--                Search--%>
                <i class="transparent" >|</i>
                <i class="fa fa-search" ></i>
                <i class="transparent" >|</i>
            </button>
        </form>

        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav mr-auto">
                <span id="taskViewBtn" class="nav-link cursor-pointer inactive">
                    <i class="fa fa-list-ul" aria-hidden="true"></i>  Tasks</span>
                <span id="userViewBtn" class="nav-link cursor-pointer inactive">
                    <i class="fa fa-users" aria-hidden="true"></i>  Users</span>
                <span id="personalInfoBtn" class="nav-link cursor-pointer inactive">
                    <i class="fa fa-address-card-o" aria-hidden="true"></i>  Personal Info</span>
                <span id="logOut"  class="nav-link cursor-pointer inactive">
                    <i class="fa fa-sign-out" aria-hidden="true"></i>  Logout</span>
            </div>
<%--            <div class="navbar-nav ml-auto">--%>
<%--                <div class="dropdown">--%>
<%--                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" id="userDropdownMenuButton"--%>
<%--                            data-toggle="dropdown"--%>
<%--                            aria-haspopup="true" aria-expanded="false" style="text-transform: capitalize">--%>
<%--                        <i class="fa fa-user-circle" aria-hidden="true"></i>--%>
<%--                    </button>--%>
<%--                    <div class="dropdown-menu" aria-labelledby="userDropdownMenuButton">--%>


<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>

</nav>
</body>
</html>
