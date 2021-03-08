<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page..</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <style>

        body {
            background: #eee
        }

        .login-form {
            max-width: 600px;
            margin: 150px auto;
            padding: 0;
            padding: 1.5em;
            background-color: white;
            /*border: 1px solid #868e96;*/
            border-radius: 5px;
            box-shadow: 0 19px 38px rgba(0, 0, 0, 0.3), 0 15px 12px rgba(0, 0, 0, 0.22)
        }

        .login-btn {

        }

        .input-group {
            border: 2px solid #eee;
            position: relative;
            background: #eee;
            margin: 25px 0;
            border-radius: 2px;
            overflow: hidden;
            padding: 10px;
        }

        .input-group input{
            border: none;
            background: transparent;
            width: 100%;
            outline: none;
            font-weight: 500;
            font-family: "Open Sans", sans-serif;
            font-size: 16px;
        }

        .input-group label {
            position: absolute;
            top: 10px;
            left: 0;
            padding-left: 10px;
            font-weight: 500;
            color: #aaa;
        }

        input-form,
        .card-footer {
            margin: 0;
            padding: 0;
            width: 100%;
        }

        .card-footer input {
        width: 100%;
        padding: 25px;
        font-size: 24px;
        font-size: 1.5rem;
        text-transform: uppercase;
        font-weight: 600;
        background: #195f91;
        border: none;
        color: #fff;
        box-shadow: none;
        outline: none;
        cursor: pointer;
        }

    </style>

    <script>

        // const $registrationForm = $("#registrationForm");
        // const $loginForm = $("#loginForm");
        $(document).on("click","#action-change", function () {
            console.log("click")
            $("#userForm").fadeOut(100, function () {
                $("#submit").value = "Register";
            })
        })

    </script>
</head>
<body>

<div id="userForm" class="login-form">

    <c:import url="/WEB-INF/jsp/template/login_form_tmpl.jsp"/>

</div>

</body>
</html>
