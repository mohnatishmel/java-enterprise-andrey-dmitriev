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




<nav class="navbar navbar-expand-lg navbar-light bg-light">


    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item  active" id="todayView">
                <a class="nav-link" href="#">Today</a>
            </li>
            <li class="nav-item" id="tomorrowView">
                <a class="nav-link" href="#">Tomorrow</a>
            </li>
            <li class="nav-item" id="somedayView">
                <a class="nav-link" href="#">Someday</a>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="#">Done</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="#">Basket</a>--%>
<%--            </li>--%>
            <%--            <li class="nav-item dropdown">--%>
            <%--                <div class="input-group mb-3">--%>
            <%--                    <select class="custom-select" id="inputGroupSelect02">--%>
            <%--                        <option selected>Sort by</option>--%>
            <%--                        <option value="1">Date</option>--%>
            <%--                        <option value="2">Name</option>--%>
            <%--                    </select>--%>
            <%--                    <div class="input-group-append">--%>
            <%--                        <label class="input-group-text" for="inputGroupSelect02">Options</label>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </li>--%>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <button class="btn btn-primary btn-outline-dark mr-sm-2" type="button" id="createButton"
                    data-toggle="collapse" data-target="#collapseTaskCreateForm" aria-expanded="false"
                    aria-controls="collapseTaskCreateForm">
                Create New
            </button>

            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="collapse" id="collapseTaskCreateForm">
    <div class="card card-body">

        <div class="form-group">
            <label for="taskName">Task Name</label>
            <input type="text" class="form-control" id="taskName" placeholder="Task Name">
        </div>

        <div class="form-group">
            <label for="taskDescription">Description</label>
            <textarea class="form-control" id="taskDescription" rows="3" placeholder="Description"></textarea>
        </div>

        <div class="form-group">
            <label for="datetime">Select dead line date:</label>
            <input id="datetime" class="form-control" type="datetime-local">
        </div>

        <div class="form-group">
            <label for="myfile">Select a file:</label>
            <input type="file" class="form-control" id="myfile" name="myfile">
        </div>

        <div class="form-group">
            <input name="command" type="hidden" value="Login"/>
            <input type="submit" class="form-control btn btn-secondary" id="createTask" value="Create">
        </div>

    </div>

</div>
