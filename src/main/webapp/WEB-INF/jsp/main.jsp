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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

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

        /*------------------  LOGIN FORM  --------------------*/
        .user-form {
            max-width: 600px;
            min-width: 600px;
            margin: auto;
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

        .user-form {

        }

        .modal-content,
        .modal-dialog {
            /*position: fixed;*/
            background: #eee;
            margin: auto;
            height: 100%;
            width: 100%;
            padding: 0;
            border: 0;
        }
        .modal,
        .modal-dialog {
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
            border-color: rgba(0,0,0,0);
            margin: auto;
        }

        .btn-group {
            display: inline-block;
        }
        /*------------------  LOGIN FORM  --------------------*/
        /*------------------  TASK LIST  --------------------*/
        .tools i {
            /*background-color: white;*/
            color: #b5b3b3;
            border: 1px solid rgba(0,0,0,0);
            padding: 6px;
            /*margin-left: 4px;*/
            border-radius: 5px;
            cursor: pointer;
        }

        .tools i:hover{
            border: 1px solid #b5b3b3;
        }

        .icons i {
            background-color: white;
            color: #b5b3b3;
            border: 1px solid #b5b3b3;
            padding: 6px;
            margin-left: 4px;
            border-radius: 5px;
            cursor: pointer;
        }

        .task-view {
            margin-left: 0.7em;
            cursor: pointer;
        }

        .check-icon {
            font-size: 19px;
            color: forestgreen;
        }

         .inactive {
             color: #868e96;
         }

        .action-change:hover,
        .clock,
        .task-view:hover,
        .tools i:hover,
        .active-btn,
        .active-btn i{
            color: #343a40;
        }

        .list-group li {
            margin-bottom: 12px
        }

        .list-group-item {}

        .list li {
            list-style: none;
            padding: 10px;
            border: 1px solid #e3dada;
            margin-top: 12px;
            border-radius: 5px;
            background: #fff
        }

        .fixed {
        }


        .date-time {
            font-size: 12px
        }

        .profile-image img {
            margin-left: 3px
        }

        .task-list li {
            cursor: pointer;
        }

        /*------------------  TASK LIST  --------------------*/
        /*------------------  SEARCH BUTTON  --------------------*/

        /*------------------  SEARCH BUTTON  --------------------*/
        /*------------------  LINKS  --------------------*/
        .cursor-pointer {
            cursor: pointer;
        }
        /*------------------  LINKS  --------------------*/
        /*------------------  TOGGLE SWITCH  --------------------*/
        .switch {
            margin-top: 6px;
            position: relative;
            vertical-align: middle;
            display: inline-block;
            width: 40px;
            height: 23px;
        }

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            -webkit-transition: .4s;
            transition: .4s;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 17px;
            width: 17px;
            left: 3px;
            bottom: 3px;
            background-color: white;
            -webkit-transition: .4s;
            transition: .4s;
        }

        input:checked + .slider {
            background-color: #28a745;
        }

        input:focus + .slider {
            box-shadow: 0 0 1px #2196F3;
        }

        input:checked + .slider:before {
            -webkit-transform: translateX(17px);
            -ms-transform: translateX(17px);
            transform: translateX(17px);
        }

        /* Rounded sliders */
        .slider.round {
            border-radius: 22px;
        }

        .slider.round:before {
            border-radius: 50%;
        }
        /*------------------  TOGGLE SWITCH  --------------------*/

        .container {
            max-width: 800px;
        }

        .trash-box i {
            display: block;
        }

        .tools div{
            margin-top: 0.3em;
            margin-right: 1em;
            display: inline-block;
        }

        .bg-white {
            background-color: white;
        }
        .tools i:hover,
        .icons i:hover,
        .task-list li,
        .smooth-shadowbox {
            color: #343a40;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        .outOfTrashBox i:hover{
            color: darkgreen;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        .delete i:hover {
            color: #dc322f;
        }

        body {
            /*background-color: #bababa;*/
        }

        .header {
            border-bottom: 1px solid #495057;
        }

    </style>
</head>
<body>
<header class="header">
    <c:import url="/WEB-INF/jsp/template/header_tmpl.jsp"/>
</header>

<div class="container">
    <c:import url="/WEB-INF/jsp/template/task_viewer_tmpl.jsp"/>
</div>

</body>
</html>
