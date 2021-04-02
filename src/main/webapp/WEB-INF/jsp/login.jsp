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

    <style>
        /*------------------  LOGIN FORM  --------------------*/

        body {
            background-color: #eee;
        }

        .account-locked-form,
        .user-form {
            max-width: 600px;
            min-width: 600px;
            margin: auto;
            margin-top: 5em;
            padding: 0;
            padding: 1.5em;
            background-color: white;
            /*border: 1px solid #868e96;*/
            /*border-radius: 5px;*/
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

        .input-group textarea,
        .input-group input {
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

        .user-form {
        }

        .account-locked-form-modal .modal-content,
        .account-locked-form-modal .modal-dialog {
            /*position: fixed;*/
            background: #eee;
            margin: auto;
            height: 100%;
            width: 100%;
            padding: 0;
            border: 0;
        }

        .account-locked-form-modal .modal,
        .account-locked-form-modal .modal-dialog {
            max-width: none;
            height: 100%;
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

        .action-change {
            border-color: rgba(0, 0, 0, 0);
            margin: auto;
        }

        .btn-group {
            display: inline-block;
        }

        /*------------------  LOGIN FORM  --------------------*/

        #messageSendSuccess button {
            margin: auto;
            width: 3em;
        }

        #messageSendSuccess p {
            margin: 1em auto 2em auto ;
            display: block;
        }




    </style>

</head>
<body>

<c:import url="/WEB-INF/jsp/template/account_locked_mesage_tmpl.jsp"/>

<c:import url="/WEB-INF/jsp/template/authentication_form_tmpl.jsp"/>
</body>
<script>

    let principal = null;

    jQuery(function () {

        $(document).ready(function () {
            loginForm.hideAlert();
            registerForm.hideAlert();
            $("#registerForm").hide();
        });

        // const $registrationForm = $("#registrationForm");
        // const $loginForm = $("#loginForm");
        $(document).on("click", "#action-change", function () {
            console.log("click")
            $("#userForm").fadeOut(100, function () {
                $("#submit").value = "Register";
            })
        })

        <c:import url="/WEB-INF/jsp/js/view/authentication/LoginFormView.js"/>
        <c:import url="/WEB-INF/jsp/js/view/authentication/RegisterFormView.js"/>
        <c:import url="/WEB-INF/jsp/js/view/authentication/AccountLockedFormView.js"/>
        <c:import url="/WEB-INF/jsp/js/controller/authentication/AuthenticateLoginPageController.js"/>
        <c:import url="/WEB-INF/jsp/js/controller/authentication/AccountLockedFormController.js"/>
        <c:import url="/WEB-INF/jsp/js/model/AuthenticationModel.js"/>
        <c:import url="/WEB-INF/jsp/js/model/MessageModel.js"/>
        <c:import url="/WEB-INF/jsp/js/model/Dao.js"/>

    })
</script>
</html>
