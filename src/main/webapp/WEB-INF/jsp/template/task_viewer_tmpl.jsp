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
                $("#" + elementID).empty();
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
            document.getElementById("trashBox").classList.remove("active-task-view");
            document.getElementById("fixed").classList.remove("active-task-view");
            document.getElementById("date-container").classList.add("d-none");

            publishTaskList("LoadTaskListToday");
        });

        // ---------------------- TASK VIEW TODAY ----------------------
        //==============================================================
        // ---------------------- TASK VIEW TOMORROW -------------------
        $(document).on("click", "#tomorrowView", function () {
            currentView = "TOMORROW"
            console.log(currentView);

            document.getElementById("todayView").classList.remove("active-task-view");
            document.getElementById("tomorrowView").classList.add("active-task-view");
            document.getElementById("somedayView").classList.remove("active-task-view");
            document.getElementById("trashBox").classList.remove("active-task-view");
            document.getElementById("fixed").classList.remove("active-task-view");
            document.getElementById("date-container").classList.add("d-none");

            publishTaskList("LoadTaskListTomorrow");
        });

        // ---------------------- TASK VIEW TOMORROW -------------------
        //==============================================================
        // ---------------------- TASK VIEW SOMEDAY --------------------

        $(document).on("click", "#somedayView", function () {
            currentView = "SOMEDAY"
            console.log(currentView);

            document.getElementById("todayView").classList.remove("active-task-view");
            document.getElementById("tomorrowView").classList.remove("active-task-view");
            document.getElementById("somedayView").classList.add("active-task-view");
            document.getElementById("trashBox").classList.remove("active-task-view");
            document.getElementById("fixed").classList.remove("active-task-view");
            document.getElementById("date-container").classList.remove("d-none");

            publishTaskList("LoadTaskListSomeday");
        });

        // ---------------------- TASK VIEW SOMEDAY --------------------
        // ---------------------- TASK VIEW FIXED --------------------

        $(document).on("click", "#fixed", function () {
            currentView = "FIXED"
            console.log(currentView);

            document.getElementById("todayView").classList.remove("active-task-view");
            document.getElementById("tomorrowView").classList.remove("active-task-view");
            document.getElementById("somedayView").classList.remove("active-task-view");
            document.getElementById("trashBox").classList.remove("active-task-view");
            document.getElementById("fixed").classList.add("active-task-view");
            document.getElementById("date-container").classList.remove("d-none");

            publishTaskList("LoadTaskListFixed");
        });

        // ---------------------- TASK VIEW FIXED --------------------
        // ---------------------- TASK VIEW TRASH BOX --------------------

        $(document).on("click", "#trashBox", function () {
            currentView = "TRASH_BOX"
            console.log(currentView);

            document.getElementById("todayView").classList.remove("active-task-view");
            document.getElementById("tomorrowView").classList.remove("active-task-view");
            document.getElementById("somedayView").classList.remove("active-task-view");
            document.getElementById("trashBox").classList.add("active-task-view");
            document.getElementById("fixed").classList.remove("active-task-view");
            document.getElementById("date-container").classList.remove("d-none");

            publishTrashBox();

        });

        function publishTrashBox() {

            $.getJSON("/?command=LoadTaskListTrashBox", function (result) {

                clearBox("taskList");

                $.each(result, function (index, task) {
                    taskList = result;

                    let taskDeadline = new Date(task.deadLine + " UTC");
                    let daysLeft = getDaysLeft(taskDeadline);

                    let id = task.id;

                    var $template = $(".task-template");
                    var $li = $template.clone();

                    $li.find("#collapseControl").attr("data-target", "#collapse" + id);
                    $li.find("#collapseControl").attr("aria-controls", "collapse" + id);
                    $li.find("#collapseControl").attr("id", "collapseControl" + id);

                    $li.find("#collapse").attr("aria-labelledby", "headingTask" + id);
                    $li.find("#collapse").attr("id", "collapse" + id);

                    $template = $(".task-trash-tool-box-template");
                    var $toolBox = $template.clone();
                    $toolBox.removeClass("d-none");
                    $toolBox.removeClass("task-trash-tool-box-template");
                    $toolBox.find("#outOfTrashBox").attr("id", "outOfTrashBox" + id);
                    $toolBox.find("#delete").attr("id", "delete" + id);

                    $li.find("#fixedIcon").attr("id", "fixedIcon    " + id);

                    $li.find("#headingTask").attr("id", "headingTask" + id);
                    $li.find("#headingTask" + id).prepend($toolBox);

                    $li.find("#liTaskName").attr("id", "liTaskName" + id)
                        .text(task.name);
                    $li.find("#liTaskDescription").attr("id", "liTaskDescription" + id)
                        .text(task.description);
                    $li.find("#taskDeadline").attr("id", "taskDeadline" + id)
                        .text(taskDeadline.toDateString());
                    $li.find("#daysLeft").text(daysLeft + " days");

                    $li.removeClass("d-none");
                    $li.removeClass("task-template");

                    $("#taskList").append($li.fadeIn());

                    var del = document.getElementById("delete" + id);
                    del.addEventListener("click", function () {
                        deleteTask(id);
                    }, false);

                    var toTrashBox = document.getElementById("outOfTrashBox" + id);
                    toTrashBox.addEventListener("click", function () {
                        taskInBasketUpdate(id, "false")
                    }, false);
                });
            });
        }

        // ---------------------- TASK VIEW TRASH BOX --------------------
        //==============================================================
        // ---------------------- TASK VIEW ----------------------------
        function fillTaskTable() {
            switch (currentView) {
                case "TODAY":
                    document.getElementById('todayView').click();
                    break;
                case "TOMORROW":
                    document.getElementById('tomorrowView').click();
                    break;
                case "TRASH_BOX":
                    document.getElementById('trashBox').click();
                    break;
                default:
                    document.getElementById('somedayView').click();
            }
        }

        // ---------------------- TASK VIEW ----------------------------

        function publishTaskList(command) {
            $.getJSON("/?command=" + command.toString(), function (result) {

                clearBox("taskList");

                $.each(result, function (index, task) {
                    taskList = result;

                    let taskDeadline = new Date(task.deadLine + " UTC");
                    let daysLeft = getDaysLeft(taskDeadline);

                    let id = task.id;

                    var $template = $(".task-template");
                    var $li = $template.clone();

                    $li.find("#collapseControl").attr("data-target", "#collapse" + id);
                    $li.find("#collapseControl").attr("aria-controls", "collapse" + id);
                    $li.find("#collapseControl").attr("id", "collapseControl" + id);

                    $li.find("#collapse").attr("aria-labelledby", "headingTask" + id);
                    $li.find("#collapse").attr("id", "collapse" + id);

                    $template = $(".task-common-tool-box-template");
                    var $toolBox = $template.clone();
                    $toolBox.removeClass("d-none");
                    $toolBox.removeClass("task-common-tool-box-template");
                    $toolBox.find("#toTrashBox").attr("id", "toTrashBox" + id);
                    $toolBox.find("#update").attr("id", "update" + id);
                    $toolBox.find("#uploadToolboxBtn").attr("id", "uploadToolboxBtn" + id);
                    $toolBox.find("#downloadToolboxBtn").attr("id", "downloadToolboxBtn" + id);

                    if (task.fixed === true) {
                        $toolBox.find("#toggleSwitchFixed").children("input").prop("checked", true);
                        $li.find(".check-icon").removeClass("inactive")
                        console.log("Fixed - true")
                    }
                    $toolBox.find("#toggleSwitchFixed").attr("id", "toggleSwitchFixed" + id);

                    $li.find("#headingTask").attr("id", "headingTask" + id);
                    $li.find("#headingTask" + id).prepend($toolBox);

                    $li.find("#liTaskName").attr("id", "liTaskName" + id)
                        .text(task.name);
                    $li.find("#liTaskDescription").attr("id", "liTaskDescription" + id)
                        .text(task.description);
                    $li.find("#taskDeadline").attr("id", "taskDeadline" + id)
                        .text(taskDeadline.toDateString());
                    $li.find("#daysLeft").text(daysLeft + " days");

                    $li.removeClass("d-none");
                    $li.removeClass("task-template");

                    $("#taskList").append($li.fadeIn());

                    var upload = document.getElementById("uploadToolboxBtn" + id);
                    upload.addEventListener("click", function () {
                        initUploadFileForm(id)
                    }, false);

                    var download = document.getElementById("downloadToolboxBtn" + id);
                    download.addEventListener("click", function () {
                        downloadFile(id);
                    }, false);

                    var update = document.getElementById("update" + id);
                    update.addEventListener("click", function () {
                        initUpdateTaskForm(id)
                    }, false);

                    var toTrashBox = document.getElementById("toTrashBox" + id);
                    toTrashBox.addEventListener("click", function () {
                        taskInBasketUpdate(id, "true")
                    }, false);

                    var toTrashBox = document.getElementById("toggleSwitchFixed" + id);
                    if (task.fixed === true) {
                        toTrashBox.addEventListener("click", function () {
                            fixedUpdate(id, false);
                        }, false);
                    } else {
                        toTrashBox.addEventListener("click", function () {
                            fixedUpdate(id, true);
                        }, false);
                    }
                });
            });
        }

        function getDaysLeft(deadLine) {
            deadLine.setHours(0, 0, 0, 0);
            let today = new Date();
            today.setHours(0, 0, 0, 0);

            var daysLeft = deadLine.getTime() - today;
            daysLeft = Math.floor(daysLeft / (1000 * 3600 * 24));

            return daysLeft;
        }
        // -----------------------------------------------
        //                  TASK  VIEW
        // ===============================================
        //                  CREATE TASK
        // -----------------------------------------------
        function createTask() {
            var task = {};
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
            console.log(date.toDateString());
            task.deadLine = Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());
            task.fixed = false;
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
            var name = document.getElementById("liTaskName" + id).innerText;
            var deadlineString = document.getElementById("taskDeadline" + id).innerText;

            var date = new Date(deadlineString);
            deadlineString = "" + date.getFullYear();
            deadlineString += "-" + ("0" + (date.getMonth() + 1)).slice(-2);
            deadlineString += "-" + ("0" + date.getDate()).slice(-2);
            console.log(deadlineString)

            var description = document.getElementById("liTaskDescription" + id).innerText;

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

        $(document).on("click", "#uploadFileBtn", function () {
            console.log("clickUploadFileForm")
            uploadFile();
        });

        function taskInBasketUpdate(id, inBasket) {
            console.log("taskInBasketUpdate Called")
            var $li = $("#headingTask" + id).closest("li");
            fadeOut($li);

                $.each(taskList, function (index, task) {
                    if (task.id === id) {
                        task.inBasket = inBasket;
                        var date = new Date(task.deadLine);
                        task.deadLine = date.getTime();

                        var jsonTask = JSON.stringify({task});
                        console.log(jsonTask);

                        sendTask(jsonTask, "UpdateTask", function success (data) {
                            taskList = data;
                        });
                    }
                });
        }

        function fixedUpdate(id, fixed) {
            console.log("taskInBasketUpdate Called")
            var $li = $("#headingTask" + id).closest("li");
            if (fixed === true) {
                $li.find("#fixedIcon").removeClass("inactive")
                fadeOut($li)
            } else {
                $li.find("#fixedIcon").addClass("inactive")
                fadeOut($li)
            }

            $.each(taskList, function (index, task) {
                if (task.id === id) {
                    task.fixed = fixed;
                    var date = new Date(task.deadLine);
                    task.deadLine = date.getTime();

                    var jsonTask = JSON.stringify({task});
                    console.log(jsonTask);

                    sendTask(jsonTask, "UpdateTask", function success (data) {
                        taskList = data;
                    });
                }
            });
        }

        function deleteTask(id) {
            var $del = $("#delete" + id).closest("li");
            fadeOut($del);

            var task = {};
            task.id = id;

            var jsonTask = JSON.stringify({task});
            console.log(jsonTask);

            sendTask(jsonTask, "DeleteTask", function success (data) {
                taskList = data;
            });
        }

        function initTaskFromTheUpdateForm() {
            var task = {};
            var id = document.getElementById('updateTaskId').value;
            task.id = id;
            task.name = document.getElementById('updateTaskName').value;
            task.description = document.getElementById('updateTaskDescription').value;
            let date = new Date((document.getElementById('updateTaskDeadline').value));
            task.deadLine =  Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());


            $.each(taskList, function (index, oldTask) {
                var oldId = oldTask.id;
                if ( oldId == id.toString()) {
                    task.fixed = oldTask.fixed;
                    task.inBasket = oldTask.inBasket;
                }
            });

            // task.fixed = true;
            // task.inBasket = false;
            return task;
        }

        function initUploadFileForm(id) {
            console.log("initUploadFileForm")
            document.getElementById("uploadFileTaskId").value = id;
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

        function uploadFile() {
            console.log("uploadFile")
            // let form = $('#uploadForm')[0];
            // let formData = new FormData(form);
            var formData = new FormData();
            formData.append("file", document.getElementById("uploadFileInput").files[0]);
            formData.append("name", document.getElementById("uploadFileInput").files[0].name);
            let id = document.getElementById("uploadFileTaskId").value;
            $.ajax({
                url : "/?command=UploadFile&id=" + id,
                type : "POST",

                data : formData,
                // enctype : "multipart/form-data",
                processData : false,
                contentType: false,
                // cache : false,
                success: function () {
                    alert('The file has been uploaded successfully.');
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
            console.log(document.getElementById("uploadFileInput").files[0].name + "is uploaded")

            // $(document).ready(function() {
            //   $("#uploadFileInput").on("change", uploadFile);// await fetch('/?command=UploadFile', {
            // });
        }

        function downloadFile(id) {
            $.getJSON('/?command=DownloadFile&id=' + id, function (result) {

                var name = result.name;

                var b64Data = result.base64;
                const byteCharacters = atob(b64Data);
                const byteNumbers = new Array(byteCharacters.length);
                for (let i = 0; i < byteCharacters.length; i++) {
                    byteNumbers[i] = byteCharacters.charCodeAt(i);
                }
                const byteArray = new Uint8Array(byteNumbers);
                const blob = new Blob([byteArray]);
                var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                a.href = url;
                a.download = name.toString();
                document.body.append(a);
                a.click();
                a.remove();
                window.URL.revokeObjectURL(url);
            });
        };

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

            if(h === 0){
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
        // ------------------  EFFECT  --------------------
        function fadeOut($element) {
            $element.children(".tools").empty();
            $element.animate({
                height: 0,
                marginTop : 0,
                marginBottom : 0,
                paddingTop: 0,
                paddingBottom: 0,
                border: 0,
                opacity: -3,
            },400, function () {
                $element.hide()
            });
        }
        // ------------------  EFFECT  --------------------

    })
</script>

<%--    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editTaskModalCenter">--%>
<%--        Launch demo modal--%>
<%--    </button>--%>

    <!-- Modal -->
<%--    <div class="modal fade" id="deleteTaskModal" tabindex="-1" role="dialog" aria-labelledby="taskEditTitle" aria-hidden="true">--%>
<%--        <div class="modal-dialog modal-dialog-centered" role="document">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-body">--%>
<%--                    <span>Put this task into the 'Trash Box'?</span>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <input id="deleteTaskId" type="hidden" value=""/>--%>
<%--                    <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">NO</button>--%>
<%--                    <button id="deleteTask" type="button" class="form-control btn btn-secondary" data-dismiss="modal">YES</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<div class="modal fade" id="uploadFileModal" tabindex="-1" role="dialog" aria-labelledby="uploadFileModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="uploadFileModalLabel">Upload File</h5>--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                    <span aria-hidden="true">&times;</span>--%>
<%--                </button>--%>
<%--            </div>--%>
            <div class="modal-body">
                <div class="form-group">
                    <form id="uploadForm" method="POST" enctype="multipart/form-data">
                        <label for="uploadFileInput">Select a file to upload:</label>
                        <input type="file" class="form-control" id="uploadFileInput" name="file">
                    </form>
                    <input type="hidden" id="uploadFileTaskId">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="form-control btn btn-secondary" id="uploadFileBtn" data-dismiss="modal">Upload File</button>
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
                    <label for="updateTaskName">Task Name</label>
                    <input type="text" class="form-control" id="updateTaskName" placeholder="Task Name">
                </div>

                <div class="form-group">
                    <label for="updateTaskDescription">Description</label>
                    <textarea class="form-control" id="updateTaskDescription" rows="5" placeholder="Description" maxlength="600"></textarea>
                </div>

                <div class="form-group">
                    <label for="updateTaskDeadline">Select dead line date:</label>
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
                            <span class="task-view cursor-pointer inactive active-task-view" id="todayView" data-toggle="tooltip" data-placement="top" title="Today Tasks">Today</span>
                            <span class="task-view cursor-pointer inactive" id="tomorrowView" data-toggle="tooltip" data-placement="top" title="Tasks on Tomorrow">Tomorrow</span>
                            <span class="task-view cursor-pointer inactive" id="somedayView" data-toggle="tooltip" data-placement="top" title="Someday">Someday</span>
                            <span class="task-view cursor-pointer tools inactive" id="trashBox">
                                <i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Trash Box"></i>
                            </span>
                            <span class="task-view cursor-pointer tools inactive" id="fixed">
                                <i class="fa fa-check-square-o" aria-hidden="true"  data-toggle="tooltip" data-placement="top" title="Fixed"></i>
                            </span>
                        </div>

                        <div class="icons">
                            <span data-toggle="collapse"
                            data-target="#collapseTaskCreateForm" aria-expanded="false"
                            aria-controls="collapseTaskCreateForm">
                                <i class="fa fa-plus" aria-hidden="true" id="createButton" data-toggle="tooltip" data-placement="top" title="Add New Task"></i>
                            </span>
                            <i class="fa fa-search" ></i>
                            <i class="fa fa-ellipsis-h"></i>
                        </div>
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
                                    <input type="submit" class="form-control btn btn-outline-secondary" id="createTask" value="Create">
                                </div>

                            </div>

                        </div>
                        <ul class="list list-inline accordion task-list" id="taskList">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
</div>

<li class="d-none task-template">
<div id="headingTask">
    <div id="collapseControl" data-toggle="collapse" data-target="#collapse" aria-expanded="false"
         aria-controls="collapse">
        <div class="d-flex justify-content-between">
                <div class="d-flex flex-row align-items-center"><i id="fixedIcon" class="fa fa-check-circle check-icon inactive"></i>
                <div class="ml-2">
                    <h6 class="mb-0" id="liTaskName"></h6>
                    <div class="d-flex flex-row mt-1 text-black-50 date-time">
                        <div><i class="fa fa-calendar-o"></i>
                            <span class="ml-2" id="taskDeadline"></span>
                        </div>
                        <div class="ml-3"><i class="fa fa-clock-o"></i>
                            <span id="daysLeft" class="ml-2"></span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div id="collapse" class="collapse" aria-labelledby="headingTask" data-parent="#taskList">
            <div class="text-box card-body">
                <p id="liTaskDescription">
                </p>
            </div>
        </div>
    </div>
</div>
</li>


<div class="tools task-common-tool-box-template d-none">
    <div class="float-right">
        <label id="toggleSwitchFixed" class="switch">
            <input id="toggleSwitch" type="checkbox">
            <span class="slider round"></span>
        </label>
    </div>
    <div class="float-right">
        <i class="fa fa-pencil-square-o inactive" id="update" aria-hidden="true"
           data-toggle="modal" data-target="#editTaskModalCenter"></i>
    </div>
    <div class="float-right">
        <i class="fa fa-trash-o inactive" aria-hidden="true" id="toTrashBox"></i>
    </div>
    <div class="float-right">
        <i class="fa fa-download inactive" id="downloadToolboxBtn" aria-hidden="true"></i>
    </div>
    <div class="float-right">
        <i class="fa fa-upload inactive" aria-hidden="true" id="uploadToolBoxBtn" data-toggle="modal" data-target="#uploadFileModal"></i>
    </div>
</div>

<div class="tools task-trash-tool-box-template d-none">
    <div id="outOfTrashBox" class="outOfTrashBox float-right">
        <i class="fa fa-trash-o inactive" aria-hidden="true"><span class="fa fa-arrow-right" aria-hidden="true"></span></i>

    </div>
    <div id="delete" class="float-right delete">
        <i class="fa fa-minus-circle" aria-hidden="true"></i>
    </div>
</div>



<%--<input type="submit" id="somebutton" class="btn btn-primary" value="Fetch">--%>

