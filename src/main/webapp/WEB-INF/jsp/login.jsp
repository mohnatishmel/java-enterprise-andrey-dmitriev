<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>main page</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
</head>
<body>
<section id="contentSection" class="d-none">
    <c:import url="/WEB-INF/jsp/template/account_locked_mesage_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/authentication_form_tmpl.jsp"/>
</section>
</body>
<script>
    let principal = null;

    jQuery(function () {

        $(document).ready(function () {
            loginForm.hideAlert();
            registerForm.hideAlert();
            $("#registerForm").hide();
            $("#contentSection").removeClass("d-none");
        });

        $(document).on("click", "#action-change", function () {
            console.log("click")
            $("#userForm").fadeOut(100, function () {
                $("#submit").value = "Register";
            })
        })
    })
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/view/authentication/LoginFormView.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/view/authentication/RegisterFormView.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/view/authentication/AccountLockedFormView.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/controller/authentication/AuthenticateLoginPageController.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/controller/authentication/AccountLockedFormController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/model/AuthenticationModel.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/model/MessageModel.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/model/MessageModel.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/model/Dao.js"></script>
</html>
