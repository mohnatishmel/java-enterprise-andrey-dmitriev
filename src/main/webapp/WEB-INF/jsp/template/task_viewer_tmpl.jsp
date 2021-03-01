<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>
    // -----------------------------------------------
    //                  INIT
    // -----------------------------------------------
    var taskList;

    var currentView = "TODAY";

    jQuery(function () {

        $(document).ready(function () {
            fetchTasks().done(function () {
                fillTaskTable();
            })
                .fail(function () {
                    console.log("error");
                })
                .always(function () {
                    console.log("complete");
                });
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
                if (a.deaLine > b.deaLine) {
                    return 1;
                }
                if (a.deaLine < b.deaLine) {
                    return -1;
                }
                return 0;
            }))
        }

        function sortTasksByDateDown() {
            taskList.sort((function (a, b) {
                if (a.deaLine > b.deaLine) {
                    return -1;
                }
                if (a.deaLine < b.deaLine) {
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
            todayView();
        });

        function todayView() {
            let today = new Date();
            today.setHours(0, 0, 0, 0);

            initTaskTableForTodayView();

            taskList.forEach(function (task, i) {
                let taskDeadline = new Date(task.deaLine);
                taskDeadline.setHours(0, 0, 0, 0)
                if (taskDeadline.getTime() == today.getTime()) {
                    $("<tr>").appendTo($("#taskTable"))
                        .append($("<td>").text(i + 1))
                        .append($("<td>").text(task.name))
                        .append($("<td>").text(task.description))
                        .append($("<td>").text(task.fixed))
                }
            });
        }

        function initTaskTableForTodayView() {
            document.getElementById('taskTable').innerHTML = `
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Status</th>
                </tr>
            `;
        }

        // ---------------------- TASK VIEW TODAY ----------------------
        //==============================================================
        // ---------------------- TASK VIEW TOMORROW -------------------
        $(document).on("click", "#tomorrowView", function () {
            currentView = "TOMORROW"
            console.log(currentView);
            tomorrowView();
        });

        function tomorrowView() {
            let tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            tomorrow.setHours(0, 0, 0, 0);

            initTaskTableForTomorrowView();

            console.log(tomorrow);

            taskList.forEach(function (task, i) {
                let taskDeadline = new Date(task.deaLine);
                taskDeadline.setHours(0, 0, 0, 0);
                if (taskDeadline.getTime() == tomorrow.getTime()) {
                    $("<tr>").appendTo($("#taskTable"))
                        .append($("<td>").text(i + 1))
                        .append($("<td>").text(task.name))
                        .append($("<td>").text(task.description))
                        .append($("<td>").text(task.fixed))
                }
            });
        }

        function initTaskTableForTomorrowView() {
            document.getElementById('taskTable').innerHTML = `
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Status</th>
                </tr>
            `;
        }
        // ---------------------- TASK VIEW TOMORROW -------------------
        //==============================================================
        // ---------------------- TASK VIEW SOMEDAY --------------------

        $(document).on("click", "#somedayView", function () {
            currentView = "SOMEDAY"
            console.log(currentView);
            somedayView();
        });

        function somedayView() {
            let tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            tomorrow.setHours(0, 0, 0, 0);

            initTaskTableForSomedayView();

            taskList.forEach(function (task, i) {
                let taskDeadline = new Date(task.deaLine);
                taskDeadline.setHours(0, 0, 0, 0)
                if (taskDeadline.getTime() > tomorrow.getTime()) {
                    $("<tr>").appendTo($("#taskTable"))
                        .append($("<td>").text(i + 1))
                        .append($("<td>").text(task.name))
                        .append($("<td>").text(task.description))
                        .append($("<td>").text(task.fixed))
                        .append($("<td>").text(taskDeadline.toDateString()));
                }
            });
        }

        function initTaskTableForSomedayView() {
            document.getElementById('taskTable').innerHTML = `
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Status</th>
                    <th scope="col">DeadLine</th>
                </tr>
            `;
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
        function fetchTasks() {

            return $.getJSON("/?command=LoadTaskList", function (result) {
                taskList = result;
                console.log(taskList);
            });
        }

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
                    date = new Date((document.getElementById('datetime').value));
            }
            task.deadLine = date.getTime();
            task.fixed = true;
            task.inBasket = false;

            var jsonTask = JSON.stringify({task});
            console.log(jsonTask);

            sendTask(jsonTask);
        }

        $(document).on("click", "#createTask", function () {
            createTask();
        });

        function sendTask(data) {
            $.ajaxSetup({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                }
            });

            $.ajax({
                url: "/?command=CreateTask",
                type: 'POST',
                dataType: 'json',
                data: data,
                contentType: 'application/json',
                mimeType: 'application/json',

                success: function (data) {
                    taskList = data;
                    document.getElementById('createButton').click();
                    clearBox("taskTable");
                    fillTaskTable();
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });

            fetchTasks().done(function () {
                fillTaskTable();
            })
                .fail(function () {
                    console.log("error");
                })
                .always(function () {
                    console.log("complete");
                });
        }

        // -----------------------------------------------
        //                  CREATE TASK
        // -----------------------------------------------

    })
</script>

<div>

</div>

<table class="table table-striped" id="taskTable">
    <thead class="thead-dark">
    </thead>

    <tbody>
    </tbody>
</table>

<%--<input type="submit" id="somebutton" class="btn btn-primary" value="Fetch">--%>

