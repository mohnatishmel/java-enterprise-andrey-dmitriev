
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
            background: #eee;
            font-family: "Open Sans", sans-serif;
        }

        /*------------------  VIEW LIST  --------------------*/

        .btn-group {
            margin: 0;
            padding: 0;
        }

        .name {
            min-height: 0.75em;
        }
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

        .icons i,
        .icon-btn {
            display: inline-block;
        }

        /*.icon-btn {*/
        /*    display:flex;*/
        /*}*/

        .icon-btn i {
            margin: auto;
        }

        .icons .icon-btn {
            height: 1.7rem;
            width: 1.7rem;
            background-color: white;
            color: #b5b3b3;
            border: 1px solid #b5b3b3;
            padding: 7px;
            margin-left: 4px;
            border-radius: 5px;
            cursor: pointer;
        }

        .item-view {
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

        .expired {
            color: #dc322f;
        }

        .done {
            color: forestgreen;
        }

        .action-change:hover,
        .clock,
        .item-view:hover,
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

        .view-container {
            min-height: 2.5em;
        }

        .view-container i {
            margin-left: 0.5em;
        }

        .viewList {
            min-height: 70%;
        }

        /*------------------  VIEW LIST  --------------------*/
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
            -webkit-transition: .2s;
            transition: .2s;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 17px;
            width: 17px;
            left: 3px;
            bottom: 3px;
            background-color: white;
            -webkit-transition: .2s;
            transition: .2s;
        }

        input:checked + .slider {
            background-color: #28a745;
        }

        .lockUserAccountToggleSwitch:checked + .slider {
            background-color: rgba(220,50,47,1);
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
        /*------------------  PAGINATION  --------------------*/
        .pagination {
        }

        /*.my-page-item {*/
        /*    border: none;*/
        /*}*/
        .page-btn {
            width: 1.8em;
            height: 1.8em;
            margin: 2px;
            border: 1px solid #eee;
            background-color: #eee;
            border-radius: 5px;
            color: #343a40;
        }

        .page-btn:hover,
        .page-btn-active {
            background-color: white;
            border: 1px solid #b5b3b3;

        }
        /*------------------  PAGINATION  --------------------*/


        .content-section {
            max-width: 800px;
            min-height: 80%;
            padding-top: 3%;
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

        .search-btn:hover,
        .tools i:hover,
        .icon-btn:hover,
        .task-list li,
        .smooth-shadowbox {
            color: #343a40;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        .outOfTrashBoxBtn i:hover{
            color: darkgreen;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        .deleteBtn i:hover {
            color: #dc322f;
        }

        body {
            height: 100%;
        }

        .user-label {
            padding: 0.3em;
            border: 1px solid;
            border-radius: 10px;
        }

        .user-label,
        .btn-outline-secondary {
            border-color:  #c7c7c7;
        }

        .bg-header {
            background-color: #f8f8f8;

        }
        .header {
            /*border-bottom: 1px solid #868e96;*/
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.10);
        }

        .search-btn {
            padding-left: 0.2rem;
            padding-right: 0.2rem;
            background-color: white;
            color: #b5b3b3;
            border-color: #b5b3b3;
        }

        .transparent {
            color: rgba(0,0,0,0);
        }

        .navbar-toggler {
            border: none;
        }




        /*.dropdown-item,*/
        /*.navbar-collapse {*/
        /*    text-align: center;*/
        /*}*/


    </style>
</head>
<body>
<header class="header">

    <c:import url="/WEB-INF/jsp/template/header_tmpl.jsp"/>

</header>

<section id="contentSection" class="d-none container content-section align-self-stretch">

    <c:import url="/WEB-INF/jsp/template/task/task_edit_form_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/user/user_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/file_upload_form_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/task/task_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/viewer_tmpl.jsp"/>

    <c:import url="/WEB-INF/jsp/template/pagination_tmpl.jsp"/>
</section>
</body>
<script>
    let viewList;

    let currentView = "TODAY";

    let principal = null;

    let pageCapacity = 6;

    <%--var logged = <c:out value = "${requestScope.alreadyLogged}"/>--%>

    jQuery(function () {

        $(document).ready(function () {
            $("#userForm").find(".alert").hide();
            fillViewList();
            showTime();
            getPrincipal();
            viewerNav.taskNav();
            $("#contentSection").removeClass("d-none");
        });

        function clearBox(elementID) {
            $("#" + elementID).empty();
        }

// ================================================================
        function sortTasksByDateUP() {
            viewList.sort((function (a, b) {
                if (a.deadLine > b.deadLine) {
                    return 1;
                }
                if (a.deadLine < b.deadLine) {
                    return -1;
                }
                return 0;
            }))
        }

        function sortTasksByDateDown() {
            viewList.sort((function (a, b) {
                if (a.deadLine > b.deadLine) {
                    return -1;
                }
                if (a.deadLine < b.deadLine) {
                    return 1;
                }
                return 0;
            }))
        }
    })



    <c:import url="/static/js/view/view.jsp"/>
    <c:import url="/static/js/controller/Controller.jsp"/>
    <c:import url="/static/js/model/model.jsp"/>
</script>
</html>
