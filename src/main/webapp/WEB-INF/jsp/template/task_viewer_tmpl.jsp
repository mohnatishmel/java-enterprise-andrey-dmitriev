<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>
    // -----------------------------------------------
    //                  INIT
    // -----------------------------------------------
    var taskList = [];

    var currentView = "TODAY";

    jQuery(function () {

        $(document).ready(function () {
            fillTaskTable();
            showTime();
        });

        function clearBox(elementID) {
            document.getElementById(elementID).innerHTML = "";

        }

        // -----------------------------------------------
        //                  TASK  VIEW
        // -----------------------------------------------
        // ---------------- SORT TASKS -------------------

        function sortTasksByDateUP() {
            taskList.sort((function (a, b) {
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
            taskList.sort((function (a, b) {
                if (a.deadLine > b.deadLine) {
                    return -1;
                }
                if (a.deadLine < b.deadLine) {
                    return 1;
                }
                return 0;
            }))
        }

        // ---------------------- SORT TASKS ----------------------
        //=========================================================
        // ---------------------- TASK VIEW TODAY ----------------------

        $(document).on("click", "#todayView", function () {
            currentView = "TODAY";
            console.log(currentView);

            document.getElementById("todayView").classList.add("active-task-view");
            document.getElementById("tomorrowView").classList.remove("active-task-view");
            document.getElementById("somedayView").classList.remove("active-task-view");
            document.getElementById("date-container").classList.add("d-none");

            todayView();
        });

        function todayView() {
            let today = new Date();
            today.setHours(0, 0, 0, 0);

            $.getJSON("/?command=LoadTaskList", function (result) {

                clearBox("taskList");
                initTaskTableForTodayView();

                let i = 0;

                $.each(result, function (index, task) {
                    let taskDeadline = new Date(task.deadLine);
                    taskDeadline.setHours(0, 0, 0, 0)

                    if (taskDeadline.getTime() == today.getTime()) {
                        i += 1;
                        var taskLiString;
                        taskLiString = `
                        <div id="headingThree" data-toggle="collapse" data-target="#collapse` + i + `" aria-expanded="false" aria-controls="collapse` + i + `">
                                <div class="d-flex justify-content-between">
                                <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                                    <div class="ml-2">
                                        <h6 class="mb-0">`;
                        taskLiString += task.name +
                            `</h6>
                                        <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                            <div><i class="fa fa-calendar-o"></i><span class="ml-2">`;
                        taskLiString += taskDeadline.toDateString() +
                            `</span></div>
                                            <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">`
                        taskLiString += `today
                                            </span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center">
                                    <i class="fa fa-ellipsis-h"></i>
                                </div>
                                </div>
                                <div id="collapse` + i + `" class="collapse" aria-labelledby="headingThree" data-parent="#taskList">
                                    <div class="card-body">`;
                        taskLiString += task.description +
                            `</div>
                                </div>
                        </li>`;

                        var li = document.createElement("LI");
                        li.innerHTML = taskLiString;
                        document.getElementById("taskList").appendChild(li);
                    }
                });
            });
        }

        function initTaskTableForTodayView() {
        }

        // ---------------------- TASK VIEW TODAY ----------------------
        //==============================================================
        // ---------------------- TASK VIEW TOMORROW -------------------
        $(document).on("click", "#tomorrowView", function () {
            currentView = "TOMORROW"
            console.log(currentView);

            document.getElementById("todayView").classList.remove("active-task-view");
            document.getElementById("tomorrowView").classList.add("active-task-view");
            document.getElementById("somedayView").classList.remove("active-task-view");
            document.getElementById("date-container").classList.add("d-none");

            tomorrowView();
        });

        function tomorrowView() {
            let tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            tomorrow.setHours(0, 0, 0, 0);

            console.log(tomorrow);

            $.getJSON("/?command=LoadTaskList", function (result) {

                clearBox("taskList");
                initTaskTableForTomorrowView();

                let i = 0;

                $.each(result, function (index, task) {
                    let taskDeadline = new Date(task.deadLine);
                    taskDeadline.setHours(0, 0, 0, 0);
                    if (taskDeadline.getTime() == tomorrow.getTime()) {
                        i += 1;
                        var taskLiString;
                        taskLiString = `
                        <div id="headingThree" data-toggle="collapse" data-target="#collapse` + i + `" aria-expanded="false" aria-controls="collapse` + i + `">
                                <div class="d-flex justify-content-between">
                                <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                                    <div class="ml-2">
                                        <h6 class="mb-0">`;
                        taskLiString += task.name +
                            `</h6>
                                        <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                            <div><i class="fa fa-calendar-o"></i><span class="ml-2">`;
                        taskLiString += taskDeadline.toDateString() +
                            `</span></div>
                                            <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">`
                        taskLiString += `tomorrow
                                            </span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center">
                                    <i class="fa fa-ellipsis-h"></i>
                                </div>
                                </div>
                                <div id="collapse` + i + `" class="collapse" aria-labelledby="headingThree" data-parent="#taskList">
                                    <div class="card-body">`;
                        taskLiString += task.description +
                            `</div>
                                </div>
                        </li>`;

                        var li = document.createElement("LI");
                        li.innerHTML = taskLiString;
                        document.getElementById("taskList").appendChild(li);
                    }
                });
            });
        }

        function initTaskTableForTomorrowView() {
        }

        // ---------------------- TASK VIEW TOMORROW -------------------
        //==============================================================
        // ---------------------- TASK VIEW SOMEDAY --------------------

        $(document).on("click", "#somedayView", function () {
            currentView = "SOMEDAY"
            console.log(currentView);

            document.getElementById("todayView").classList.remove("active-task-view");
            document.getElementById("tomorrowView").classList.remove("active-task-view");
            document.getElementById("somedayView").classList.add("active-task-view");
            document.getElementById("date-container").classList.remove("d-none");

            somedayView();
        });

        function somedayView() {
            let tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            tomorrow.setHours(0, 0, 0, 0);

            $.getJSON("/?command=LoadTaskList", function (result) {

                clearBox("taskList");
                initTaskTableForSomedayView();



                $.each(result, function (index, task) {
                    taskList = result;
                    let taskDeadline = new Date(task.deadLine);
                    taskDeadline.setHours(0, 0, 0, 0)
                    var daysLeft = taskDeadline.getTime() - new Date().getTime();
                    daysLeft = Math.floor(daysLeft / (1000 * 3600 * 24));
                    if (taskDeadline.getTime() > tomorrow.getTime()) {
                        let id = task.id;
                        var taskLiString;
                        taskLiString = `
<div id="headingTask` + id + `">
    <div class="tools">
        <div class="float-right">
            <label class="switch">
                <input type="checkbox">
                <span class="slider round"></span>
            </label>
        </div>
        <div class="float-right">
            <i class="fa fa-pencil-square-o inactive" id="update` + id + `" aria-hidden="true"
               data-toggle="modal" data-target="#editTaskModalCenter"></i>
        </div>
        <div class="float-right">
            <i class="fa fa-trash-o inactive" aria-hidden="true" id="toTrashBox` + id + `"></i>
        </div>
        <div class="float-right">
            <i class="fa fa-download inactive" aria-hidden="true"></i>
        </div>
        <div class="float-right">
            <i class="fa fa-upload inactive" aria-hidden="true"></i>
        </div>
    </div>
    <div data-toggle="collapse" data-target="#collapse` + id + `" aria-expanded="false"
         aria-controls="collapse` + id + `">
        <div class="d-flex justify-content-between">
            <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                <div class="ml-2">
                    <h6 class="mb-0" id="taskName` + id + `">`;
                        taskLiString += task.name +
                            `</h6>
                    <div class="d-flex flex-row mt-1 text-black-50 date-time">
                        <div><i class="fa fa-calendar-o"></i>
                            <span class="ml-2" id="taskDeadline` + id + `">`;
                        taskLiString += taskDeadline.toDateString() +`</span>
                        </div>
                        <div class="ml-3"><i class="fa fa-clock-o"></i>
                            <span class="ml-2">`;
                        taskLiString += daysLeft + `days </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div id="collapse` + id + `" class="collapse" aria-labelledby="headingTask` + id + `" data-parent="#taskList">
            <div class="text-box card-body">
                <p id="taskDescription` + id + `">`;
                        taskLiString += task.description +
                            `</p>
            </div>
        </div>
    </div>
</div>`;

                        var li = document.createElement("LI");
                        li.innerHTML = taskLiString;
                        document.getElementById("taskList").appendChild(li);

                        var update = document.getElementById("update" + id);
                        update.addEventListener("click", function(){initUpdateTaskForm(id)}, false);

                        var toTrashBox = document.getElementById("toTrashBox" + id);
                        toTrashBox.addEventListener("click", function(){taskInBasketUpdate(id, "true")}, false);
                    }
                });
            });
        }


        function initTaskTableForSomedayView() {
        }

        // ---------------------- TASK VIEW SOMEDAY --------------------
        //==============================================================
        // ---------------------- TASK VIEW ----------------------------
        function fillTaskTable() {
            switch (currentView) {
                case "TODAY":
                    todayView();
                    break;
                case "TOMORROW":
                    tomorrowView();
                    break;
                default:
                    somedayView();
            }
        }

        // ---------------------- TASK VIEW ----------------------------

        // -----------------------------------------------
        //                  TASK  VIEW
        // ===============================================
        //                  CREATE TASK
        // -----------------------------------------------
        function createTask() {
            var task = new Object();
            task.id = 0;
            task.name = document.getElementById('taskName').value;
            task.description = document.getElementById('taskDescription').value;
            let date = new Date();
            switch (currentView) {
                case "TODAY":
                    date.setHours(0, 0, 0, 0);
                    break;
                case "TOMORROW":
                    date.setDate(date.getDate() + 1)
                    date.setHours(0, 0, 0, 0);
                    break;
                default:
                    date = new Date((document.getElementById('date').value));
            }
            task.deadLine = date.getTime();
            task.fixed = true;
            task.inBasket = false;

            var jsonTask = JSON.stringify({task});
            console.log(jsonTask);

            sendTask(jsonTask, "CreateTask",function (data) {
                taskList = data;
                document.getElementById('createButton').click();
                fillTaskTable();
            });
        }

        $(document).on("click", "#createTask", function () {
            createTask();
        });

        // -----------------------------------------------
        //                  CREATE TASK
        // -----------------------------------------------
        // -----------------------------------------------
        //                  UPDATE TASK
        // -----------------------------------------------

        function initUpdateTaskForm(id) {
            var name = document.getElementById("taskName" + id).innerText;
            var deadlineString = document.getElementById("taskDeadline" + id).innerText;

            var date = new Date(deadlineString);
            deadlineString = "" + date.getFullYear();
            deadlineString += "-" + ("0" + (date.getMonth() + 1)).slice(-2);
            deadlineString += "-" + ("0" + date.getDate()).slice(-2);
            console.log(deadlineString)

            var description = document.getElementById("taskDescription" + id).innerText;

            document.getElementById("taskEditTitle").innerText = "Edit `" + name + "` task";
            document.getElementById("updateTaskName").value = name;
            document.getElementById("updateTaskDeadline").value = deadlineString;
            document.getElementById("updateTaskDescription").innerText = description;
            document.getElementById("updateTaskId").value = id;
        }

        $(document).on("click", "#updateTask", function () {
            var task = initTaskFromTheUpdateForm();
            updateTask(task);
        });

        $(document).on("click", "#taskToTrashBox", function () {
            var task = initTaskFromTheUpdateForm();
            updateTask(task);
        });

        function taskInBasketUpdate(id, inBasket) {
            console.log("taskInBasketUpdate Called")
            $.each(taskList,function (index, task){
                if (task.id == id){
                    console.log(inBasket.toString())
                    task.inBasket = inBasket;
                    var date = new Date(task.deadLine);
                    task.deadLine = date.getTime();
                    updateTask(task);
                }
            });
        }

        function initTaskFromTheUpdateForm() {
            var task = new Object();
            task.id = document.getElementById('updateTaskId').value;;
            task.name = document.getElementById('updateTaskName').value;
            task.description = document.getElementById('updateTaskDescription').value;
            let date = new Date((document.getElementById('updateTaskDeadline').value));
            task.deadLine = date.getTime();
            task.fixed = true;
            task.inBasket = false;
            return task;
        }
        function updateTask(task) {
            var jsonTask = JSON.stringify({task});
            console.log(jsonTask);

            sendTask(jsonTask, "UpdateTask", function success (data) {
                taskList = data;
                fillTaskTable();
            });
        }



        function sendTask(data, command, success) {
            $.ajaxSetup({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                }
            });

            $.ajax({
                url: "/?command="+ command,
                type: 'POST',
                dataType: 'json',
                data: data,
                contentType: 'application/json',
                mimeType: 'application/json',

                success: function (data) {
                    success(data);
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
        }

        // -----------------------------------------------
        //                  UPDATE TASK
        // -----------------------------------------------
        // ------------------  CLOCK  --------------------
        function showTime(){
            var date = new Date();
            var h = date.getHours(); // 0 - 23
            var m = date.getMinutes(); // 0 - 59
            // var s = date.getSeconds(); // 0 - 59
            var session = "am";

            if(h == 0){
                h = 12;
            }

            if(h > 12){
                h = h - 12;
                session = "pm";
            }

            h = (h < 10) ? "0" + h : h;
            m = (m < 10) ? "0" + m : m;
            // s = (s < 10) ? "0" + s : s;

            var time = h + ":" + m + " " + session;
            // var time = h + ":" + m + ":" + s + " " + session;
            document.getElementById("clock").innerText = time;
            document.getElementById("clock").textContent = time;

            setTimeout(showTime, 1000);

        }

        showTime();
        // ------------------  CLOCK  --------------------
        // ------------------  SEARCH BUTTON  --------------------
        // ------------------  SEARCH BUTTON  --------------------

    })
</script>

<%--    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editTaskModalCenter">--%>
<%--        Launch demo modal--%>
<%--    </button>--%>

    <!-- Modal -->
    <div class="modal fade" id="deleteTaskModal" tabindex="-1" role="dialog" aria-labelledby="taskEditTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <span>Put this task into the 'Trash Box'?</span>
                </div>
                <div class="modal-footer">
                    <input id="deleteTaskId" type="hidden" value=""/>
                    <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">NO</button>
                    <button id="deleteTask" type="button" class="form-control btn btn-secondary" data-dismiss="modal">YES</button>
                </div>
            </div>
        </div>
    </div>

<div class="modal fade" id="editTaskModalCenter" tabindex="-1" role="dialog" aria-labelledby="taskEditTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="taskEditTitle"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="taskName">Task Name</label>
                    <input type="text" class="form-control" id="updateTaskName" placeholder="Task Name">
                </div>

                <div class="form-group">
                    <label for="taskDescription">Description</label>
                    <textarea class="form-control" id="updateTaskDescription" rows="5" placeholder="Description" maxlength="600"></textarea>
                </div>

                <div class="form-group">
                    <label for="date">Select dead line date:</label>
                    <input id="updateTaskDeadline" class="form-control" type="date">
                </div>

            </div>
            <div class="modal-footer">
                <input id="updateTaskId" type="hidden" value=""/>
                <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">Close</button>
                <button id="updateTask" type="button" class="form-control btn btn-secondary" data-dismiss="modal">Save changes</button>
            </div>
        </div>
    </div>
</div>

<div class="taskList">
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <div class="d-flex justify-content-between align-items-center activity">
                        <div class="task-view-nav">
                            <span class="clock">
                                <i class="fa fa-clock-o"></i><span class="ml-2" id="clock"></span>
                            </span>
                            <span class="task-view cursor-pointer inactive active-task-view" id="todayView">Today</span>
                            <span class="task-view cursor-pointer inactive" id="tomorrowView">Tomorrow</span>
                            <span class="task-view cursor-pointer inactive" id="somedayView">Someday</span>
                        </div>
                        <div>

                        </div>
                        <div class="icons">
                            <i class="fa fa-plus" aria-hidden="true" id="createButton" data-toggle="collapse"
                               data-target="#collapseTaskCreateForm" aria-expanded="false"
                               aria-controls="collapseTaskCreateForm"></i>
                            <i class="fa fa-search"></i>
                            <i class="fa fa-ellipsis-h"></i></div>
                    </div>

                    <div class="mt-3">
                        <div class="collapse" id="collapseTaskCreateForm">
                            <div class="card card-body">

                                <div class="form-group">
                                    <label for="taskName">Task Name</label>
                                    <input type="text" class="form-control" id="taskName" placeholder="Task Name">
                                </div>

                                <div class="form-group">
                                    <label for="taskDescription">Description</label>
                                    <textarea class="form-control" id="taskDescription" rows="3" placeholder="Description" maxlength="600"></textarea>
                                </div>

                                <div class="form-group d-none" id="date-container">
                                    <label for="date">Select dead line date:</label>
                                    <input id="date" class="form-control" type="date" placeholder="dd/mm/yyyy">
                                </div>

                                <div class="form-group">
                                    <label for="myfile">Select a file:</label>
                                    <input type="file" class="form-control" id="myfile" name="myfile">
                                </div>

                                <div class="form-group">
                                    <input type="submit" class="form-control btn btn-outline-secondary" id="createTask" value="Create">
                                </div>

                            </div>

                        </div>
                        <ul class="list list-inline accordion task-list" id="taskList">
<%--                            <li id="headingThree" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">--%>
<%--                                <div class="d-flex justify-content-between">--%>
<%--                                <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>--%>
<%--                                    <div class="ml-2">--%>
<%--                                        <h6 class="mb-0">Kickoff meeting</h6>--%>
<%--                                        <div class="d-flex flex-row mt-1 text-black-50 date-time">--%>
<%--                                            <div><i class="fa fa-calendar-o"></i><span class="ml-2">22 May 2020 11:30 PM</span></div>--%>
<%--                                            <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">6h</span></div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="d-flex flex-row align-items-center">--%>
<%--                                    <div class="d-flex flex-column mr-2">--%>
<%--                                        <div class="profile-image">--%>
<%--                                            <img class="rounded-circle" src="https://i.imgur.com/xbxOs06.jpg" width="30">--%>
<%--                                            <img class="rounded-circle" src="https://i.imgur.com/KIJewDa.jpg" width="30">--%>
<%--                                            <img class="rounded-circle" src="https://i.imgur.com/wwd9uNI.jpg" width="30">--%>
<%--                                        </div><span class="date-time">11/4/2020 12:55</span>--%>
<%--                                    </div> --%>

<%--                                    <i class="fa fa-ellipsis-h"></i>--%>
<%--                                </div>--%>
<%--                                </div>--%>
<%--                                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#taskList">--%>
<%--                                    <div class="card-body">--%>
<%--                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                            </li>--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
</div>
</div>
</div>






<%--<input type="submit" id="somebutton" class="btn btn-primary" value="Fetch">--%>

