<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 10/02/2021
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title>Snippet - BBBootstrap</title>
    <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' rel='stylesheet'>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>
    <style>body {
        background: #eee
    }

    .icons i {
        color: #b5b3b3;
        border: 1px solid #b5b3b3;
        padding: 6px;
        margin-left: 4px;
        border-radius: 5px;
        cursor: pointer
    }

    .activity-done {
        font-weight: 600
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

    .checkicon {
        color: green;
        font-size: 19px
    }

    .date-time {
        font-size: 12px
    }

    .profile-image img {
        margin-left: 3px
    }</style>
    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script type='text/javascript'></script>
</head>
<body oncontextmenu='return false' class='snippet-body'>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <div class="d-flex justify-content-between align-items-center activity">
                <div><i class="fa fa-clock-o"></i><span class="ml-2">11h 25m</span></div>
                <div><span class="activity-done">Done Activities(4)</span></div>
                <div class="icons"><i class="fa fa-search"></i><i class="fa fa-ellipsis-h"></i></div>
            </div>
            <div class="mt-3">
                <ul class="list list-inline">
                    <li class="d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                            <div class="ml-2">
                                <h6 class="mb-0">Kickoff meeting</h6>
                                <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                    <div><i class="fa fa-calendar-o"></i><span class="ml-2">22 May 2020 11:30 PM</span></div>
                                    <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">6h</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row align-items-center">
                            <div class="d-flex flex-column mr-2">
                                <div class="profile-image"><img class="rounded-circle" src="https://i.imgur.com/xbxOs06.jpg" width="30"><img class="rounded-circle" src="https://i.imgur.com/KIJewDa.jpg" width="30"><img class="rounded-circle" src="https://i.imgur.com/wwd9uNI.jpg" width="30"></div><span class="date-time">11/4/2020 12:55</span>
                            </div> <i class="fa fa-ellipsis-h"></i>
                        </div>
                    </li>
                    <li class="d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                            <div class="ml-2">
                                <h6 class="mb-0">User Interview</h6>
                                <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                    <div><i class="fa fa-calendar-o"></i><span class="ml-2">25 May 2020 12:34 AM</span></div>
                                    <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">4h</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row align-items-center">
                            <div class="d-flex flex-column mr-2">
                                <div class="profile-image"><img class="rounded-circle" src="https://i.imgur.com/xbxOs06.jpg" width="30"><img class="rounded-circle" src="https://i.imgur.com/wwd9uNI.jpg" width="30"></div><span class="date-time">12/5/2020 12:55</span>
                            </div><i class="fa fa-ellipsis-h"></i>
                        </div>
                    </li>
                    <li class="d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                            <div class="ml-2">
                                <h6 class="mb-0">Prototyping</h6>
                                <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                    <div><i class="fa fa-calendar-o"></i><span class="ml-2">17 May 2020 1:34 PM</span></div>
                                    <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">6h</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row align-items-center">
                            <div class="d-flex flex-column mr-2">
                                <div class="profile-image"><img class="rounded-circle" src="https://i.imgur.com/xbxOs06.jpg" width="30"><img class="rounded-circle" src="https://i.imgur.com/KIJewDa.jpg" width="30"></div><span class="date-time">16/4/2020 1:55</span>
                            </div><i class="fa fa-ellipsis-h"></i>
                        </div>
                    </li>
                    <li class="d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                            <div class="ml-2">
                                <h6 class="mb-0">Call with client</h6>
                                <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                    <div><i class="fa fa-calendar-o"></i><span class="ml-2">12 May 2020 12:35 AM</span></div>
                                    <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">6h</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row align-items-center">
                            <div class="d-flex flex-column mr-2">
                                <div class="profile-image"><img class="rounded-circle" src="https://i.imgur.com/wwd9uNI.jpg" width="30"></div><span class="date-time">11/4/2020 12:55</span>
                            </div><i class="fa fa-ellipsis-h"></i>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>