<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 16/02/2021
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main page</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%--    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--            crossorigin="anonymous"></script>--%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

<%--    <script>--%>
<%--        <c:import url="/WEB-INF/modules/moment.js"/>--%>
<%--    </script>--%>


<%--    <script>--%>
<%--        <c:import url="/WEB-INF/modules/date-time-picker-bootstrap-4/build/js/bootstrap-datetimepicker.min.js"/>--%>
<%--    </script>--%>

<%--    <script>--%>
<%--    $('#example').datetimepicker();--%>
<%--    </script>--%>



<%--    <link rel="stylesheet" href="src/main/webapp/WEB-INF/modules/font-awesome-4.7.0/css/font-awesome.min.css">--%>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
    <style>

<%--        <c:import url="/WEB-INF/modules/font-awesome-4.7.0/css/font-awesome.min.css"/>--%>
<%--        <c:import url="/WEB-INF/modules/date-time-picker-bootstrap-4/build/css/bootstrap-datetimepicker.min.css"/>--%>


        body {
            /*background-color: #FAFDFD;*/
        }

        .header {
            margin-bottom: 100px;
        }

        tr.table {
            border-radius: 5px;
        }

        .container {
            border-radius: 5px;
        }


    </style>
</head>
<body>
<header class="header">
    <c:import url="/WEB-INF/jsp/template/header_tmpl.jsp"/>
</header>
<div class="container">

    <c:import url="/WEB-INF/jsp/template/nav_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/task_viewer_tmpl.jsp"/>
</div>
</body>
</html>
